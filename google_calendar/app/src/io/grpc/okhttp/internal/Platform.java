// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import io.grpc.internal.GrpcUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import okio.Buffer;
import okio.Segment;

// Referenced classes of package io.grpc.okhttp.internal:
//            Protocol, OptionalMethod, Util

public class Platform
{
    static final class Android extends Platform
    {

        private final OptionalMethod getAlpnSelectedProtocol;
        private final OptionalMethod setAlpnProtocols;
        private final OptionalMethod setHostname;
        private final OptionalMethod setUseSessionTickets;
        private final int tlsExtensionType$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;

        public final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
        {
            if (s != null)
            {
                setUseSessionTickets.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                    Boolean.valueOf(true)
                });
                setHostname.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                    s
                });
            }
            boolean flag;
            if (setAlpnProtocols.getMethod(sslsocket.getClass()) != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                s = concatLengthPrefixed(list);
                setAlpnProtocols.invokeWithoutCheckedException(sslsocket, new Object[] {
                    s
                });
            }
        }

        public final String getSelectedProtocol(SSLSocket sslsocket)
        {
            boolean flag;
            if (getAlpnSelectedProtocol.getMethod(sslsocket.getClass()) != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return null;
            }
            sslsocket = (byte[])getAlpnSelectedProtocol.invokeWithoutCheckedException(sslsocket, new Object[0]);
            if (sslsocket != null)
            {
                return new String(sslsocket, Util.UTF_8);
            } else
            {
                return null;
            }
        }

        public final int getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0()
        {
            return tlsExtensionType$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        }

        public Android(OptionalMethod optionalmethod, OptionalMethod optionalmethod1, Method method, Method method1, OptionalMethod optionalmethod2, OptionalMethod optionalmethod3, Provider provider, 
                int i)
        {
            super(provider);
            setUseSessionTickets = optionalmethod;
            setHostname = optionalmethod1;
            getAlpnSelectedProtocol = optionalmethod2;
            setAlpnProtocols = optionalmethod3;
            tlsExtensionType$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0 = i;
        }
    }

    static final class JdkAlpnPlatform extends Platform
    {

        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        public final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
        {
            s = sslsocket.getSSLParameters();
            ArrayList arraylist = new ArrayList(list.size());
            list = list.iterator();
            do
            {
                if (!list.hasNext())
                {
                    break;
                }
                Protocol protocol = (Protocol)list.next();
                if (protocol != Protocol.HTTP_1_0)
                {
                    arraylist.add(protocol.toString());
                }
            } while (true);
            try
            {
                setApplicationProtocols.invoke(s, new Object[] {
                    arraylist.toArray(new String[arraylist.size()])
                });
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new RuntimeException(sslsocket);
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new RuntimeException(sslsocket);
            }
            sslsocket.setSSLParameters(s);
        }

        public final String getSelectedProtocol(SSLSocket sslsocket)
        {
            try
            {
                sslsocket = (String)getApplicationProtocol.invoke(sslsocket, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new RuntimeException(sslsocket);
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new RuntimeException(sslsocket);
            }
            return sslsocket;
        }

        public final int getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0()
        {
            return android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        }

        JdkAlpnPlatform(Provider provider, Method method, Method method1)
        {
            super(provider);
            setApplicationProtocols = method;
            getApplicationProtocol = method1;
        }
    }

    static final class JdkWithJettyBootPlatform extends Platform
    {

        private final Class clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class serverProviderClass;

        public final void afterHandshake(SSLSocket sslsocket)
        {
            try
            {
                removeMethod.invoke(null, new Object[] {
                    sslsocket
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new AssertionError();
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                return;
            }
        }

        public final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
        {
            s = new ArrayList(list.size());
            int j = list.size();
            for (int i = 0; i < j; i++)
            {
                Protocol protocol = (Protocol)list.get(i);
                if (protocol != Protocol.HTTP_1_0)
                {
                    s.add(protocol.toString());
                }
            }

            try
            {
                list = io/grpc/okhttp/internal/Platform.getClassLoader();
                Class class1 = clientProviderClass;
                Class class2 = serverProviderClass;
                s = new JettyNegoProvider(s);
                s = ((String) (Proxy.newProxyInstance(list, new Class[] {
                    class1, class2
                }, s)));
                putMethod.invoke(null, new Object[] {
                    sslsocket, s
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new AssertionError(sslsocket);
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new AssertionError(sslsocket);
            }
        }

        public final String getSelectedProtocol(SSLSocket sslsocket)
        {
            sslsocket = (JettyNegoProvider)Proxy.getInvocationHandler(getMethod.invoke(null, new Object[] {
                sslsocket
            }));
            if (((JettyNegoProvider) (sslsocket)).unsupported || ((JettyNegoProvider) (sslsocket)).selected != null)
            {
                break MISSING_BLOCK_LABEL_54;
            }
            logger.logp(Level.INFO, "io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform", "getSelectedProtocol", "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
            return null;
            if (((JettyNegoProvider) (sslsocket)).unsupported)
            {
                return null;
            }
            try
            {
                sslsocket = ((JettyNegoProvider) (sslsocket)).selected;
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new AssertionError();
            }
            // Misplaced declaration of an exception variable
            catch (SSLSocket sslsocket)
            {
                throw new AssertionError();
            }
            return sslsocket;
        }

        public final int getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0()
        {
            return android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        }

        public JdkWithJettyBootPlatform(Method method, Method method1, Method method2, Class class1, Class class2, Provider provider)
        {
            super(provider);
            putMethod = method;
            getMethod = method1;
            removeMethod = method2;
            clientProviderClass = class1;
            serverProviderClass = class2;
        }
    }

    static final class JettyNegoProvider
        implements InvocationHandler
    {

        private final List protocols;
        public String selected;
        public boolean unsupported;

        public final Object invoke(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            String s = method.getName();
            Class class1 = method.getReturnType();
            obj = ((Object) (aobj));
            if (aobj == null)
            {
                obj = Util.EMPTY_STRING_ARRAY;
            }
            if (s.equals("supports") && Boolean.TYPE == class1)
            {
                return Boolean.valueOf(true);
            }
            if (s.equals("unsupported") && Void.TYPE == class1)
            {
                unsupported = true;
                return null;
            }
            if (s.equals("protocols") && obj.length == 0)
            {
                return protocols;
            }
            if ((s.equals("selectProtocol") || s.equals("select")) && java/lang/String == class1 && obj.length == 1 && (obj[0] instanceof List))
            {
                obj = (List)obj[0];
                int j = ((List) (obj)).size();
                for (int i = 0; i < j; i++)
                {
                    if (protocols.contains(((List) (obj)).get(i)))
                    {
                        obj = (String)((List) (obj)).get(i);
                        selected = ((String) (obj));
                        return obj;
                    }
                }

                obj = (String)protocols.get(0);
                selected = ((String) (obj));
                return obj;
            }
            if ((s.equals("protocolSelected") || s.equals("selected")) && obj.length == 1)
            {
                selected = (String)obj[0];
                return null;
            } else
            {
                return method.invoke(this, ((Object []) (obj)));
            }
        }

        public JettyNegoProvider(List list)
        {
            protocols = list;
        }
    }


    private static final String ANDROID_SECURITY_PROVIDERS[] = {
        "com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider"
    };
    public static final Platform PLATFORM = findPlatform();
    public static final Logger logger = Logger.getLogger(io/grpc/okhttp/internal/Platform.getName());
    public final Provider sslProvider;

    public Platform(Provider provider)
    {
        sslProvider = provider;
    }

    public static byte[] concatLengthPrefixed(List list)
    {
        Buffer buffer = new Buffer();
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            Object obj = (Protocol)list.get(i);
            if (obj != Protocol.HTTP_1_0)
            {
                int k = ((Protocol) (obj)).toString().length();
                Object obj1 = buffer.writableSegment(1);
                byte abyte0[] = ((Segment) (obj1)).data;
                int l = ((Segment) (obj1)).limit;
                obj1.limit = l + 1;
                abyte0[l] = (byte)k;
                buffer.size = buffer.size + 1L;
                obj1 = (Buffer)buffer;
                obj = ((Protocol) (obj)).toString();
                obj = (Buffer)buffer.writeUtf8(((String) (obj)), 0, ((String) (obj)).length());
            }
        }

        return buffer.readByteArray();
    }

    private static Platform findPlatform()
    {
        if (!GrpcUtil.IS_RESTRICTED_APPENGINE) goto _L2; else goto _L1
_L1:
        Object obj2 = getAppEngineProvider();
_L5:
        if (obj2 == null) goto _L4; else goto _L3
_L3:
        Object obj6;
        OptionalMethod optionalmethod;
        OptionalMethod optionalmethod1;
        OptionalMethod optionalmethod2;
        obj6 = new OptionalMethod(null, "setUseSessionTickets", new Class[] {
            Boolean.TYPE
        });
        optionalmethod = new OptionalMethod(null, "setHostname", new Class[] {
            java/lang/String
        });
        optionalmethod1 = new OptionalMethod([B, "getAlpnSelectedProtocol", new Class[0]);
        optionalmethod2 = new OptionalMethod(null, "setAlpnProtocols", new Class[] {
            [B
        });
        Object obj;
        Object obj4;
        obj4 = Class.forName("android.net.TrafficStats");
        obj = ((Class) (obj4)).getMethod("tagSocket", new Class[] {
            java/net/Socket
        });
        obj4 = ((Class) (obj4)).getMethod("untagSocket", new Class[] {
            java/net/Socket
        });
_L8:
        Object obj1;
        int i;
        int j;
        int k;
        int l;
        if (GrpcUtil.IS_RESTRICTED_APPENGINE)
        {
            i = android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        } else
        if (((Provider) (obj2)).getName().equals("GmsCore_OpenSSL") || ((Provider) (obj2)).getName().equals("Conscrypt"))
        {
            i = android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        } else
        if (isAtLeastAndroid5())
        {
            i = android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        } else
        if (isAtLeastAndroid41())
        {
            i = android.support.v4.content.ModernAsyncTask.Status.NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        } else
        {
            i = android.support.v4.content.ModernAsyncTask.Status.NONE$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
        }
        return new Android(((OptionalMethod) (obj6)), optionalmethod, ((Method) (obj)), ((Method) (obj4)), optionalmethod1, optionalmethod2, ((Provider) (obj2)), i);
_L2:
        obj = Security.getProviders();
        k = obj.length;
        i = 0;
_L7:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_260;
        }
        obj2 = obj[i];
        obj4 = ANDROID_SECURITY_PROVIDERS;
        l = obj4.length;
        j = 0;
_L6:
label0:
        {
            if (j >= l)
            {
                break MISSING_BLOCK_LABEL_251;
            }
            obj6 = obj4[j];
            if (!((String) (obj6)).equals(obj2.getClass().getName()))
            {
                break label0;
            }
            logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "getAndroidSecurityProvider", "Found registered provider {0}", obj6);
        }
          goto _L5
        j++;
          goto _L6
        i++;
          goto _L7
        logger.logp(Level.WARNING, "io.grpc.okhttp.internal.Platform", "getAndroidSecurityProvider", "Unable to find Conscrypt");
        obj2 = null;
          goto _L5
        obj1;
        obj1 = null;
_L14:
        obj4 = null;
          goto _L8
        obj1;
        obj1 = null;
_L13:
        obj4 = null;
          goto _L8
_L4:
        try
        {
            obj1 = SSLContext.getDefault().getProvider();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj1)
        {
            throw new RuntimeException(((Throwable) (obj1)));
        }
        obj2 = SSLContext.getInstance("TLS", ((Provider) (obj1)));
        ((SSLContext) (obj2)).init(null, null, null);
        obj2 = ((SSLContext) (obj2)).createSSLEngine();
        ((Method)AccessController.doPrivileged(new _cls1())).invoke(obj2, new Object[0]);
        obj2 = new JdkAlpnPlatform(((Provider) (obj1)), (Method)AccessController.doPrivileged(new _cls2()), (Method)AccessController.doPrivileged(new _cls3()));
        return ((Platform) (obj2));
        Object obj3;
        obj3;
_L12:
        obj3 = Class.forName("org.eclipse.jetty.alpn.ALPN");
        Class class1 = Class.forName(String.valueOf("org.eclipse.jetty.alpn.ALPN").concat("$Provider"));
        Class class2 = Class.forName(String.valueOf("org.eclipse.jetty.alpn.ALPN").concat("$ClientProvider"));
        Class class3 = Class.forName(String.valueOf("org.eclipse.jetty.alpn.ALPN").concat("$ServerProvider"));
        obj3 = new JdkWithJettyBootPlatform(((Class) (obj3)).getMethod("put", new Class[] {
            javax/net/ssl/SSLSocket, class1
        }), ((Class) (obj3)).getMethod("get", new Class[] {
            javax/net/ssl/SSLSocket
        }), ((Class) (obj3)).getMethod("remove", new Class[] {
            javax/net/ssl/SSLSocket
        }), class2, class3, ((Provider) (obj1)));
        return ((Platform) (obj3));
        obj3;
_L10:
        return new Platform(((Provider) (obj1)));
        obj3;
        if (true) goto _L10; else goto _L9
_L9:
        obj3;
        continue; /* Loop/switch isn't completed */
        obj3;
        continue; /* Loop/switch isn't completed */
        obj3;
        continue; /* Loop/switch isn't completed */
        obj3;
        if (true) goto _L12; else goto _L11
_L11:
        Object obj5;
        obj5;
          goto _L13
        obj5;
          goto _L14
    }

    private static Provider getAppEngineProvider()
    {
        Provider provider;
        try
        {
            provider = (Provider)Class.forName("org.conscrypt.OpenSSLProvider").getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Throwable throwable)
        {
            throw new RuntimeException("Unable to load conscrypt security provider", throwable);
        }
        return provider;
    }

    private static boolean isAtLeastAndroid41()
    {
        try
        {
            io/grpc/okhttp/internal/Platform.getClassLoader().loadClass("android.app.ActivityOptions");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "isAtLeastAndroid41", "Can't find class", classnotfoundexception);
            return false;
        }
        return true;
    }

    private static boolean isAtLeastAndroid5()
    {
        try
        {
            io/grpc/okhttp/internal/Platform.getClassLoader().loadClass("android.net.Network");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "isAtLeastAndroid5", "Can't find class", classnotfoundexception);
            return false;
        }
        return true;
    }

    public void afterHandshake(SSLSocket sslsocket)
    {
    }

    public void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
    {
    }

    public String getSelectedProtocol(SSLSocket sslsocket)
    {
        return null;
    }

    public int getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0()
    {
        return android.support.v4.content.ModernAsyncTask.Status.NONE$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
    }


    private class _cls1
        implements PrivilegedExceptionAction
    {

        public final Object run()
            throws Exception
        {
            return javax/net/ssl/SSLEngine.getMethod("getApplicationProtocol", new Class[0]);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements PrivilegedExceptionAction
    {

        public final Object run()
            throws Exception
        {
            return javax/net/ssl/SSLParameters.getMethod("setApplicationProtocols", new Class[] {
                [Ljava/lang/String;
            });
        }

        _cls2()
        {
        }
    }


    private class _cls3
        implements PrivilegedExceptionAction
    {

        public final Object run()
            throws Exception
        {
            return javax/net/ssl/SSLSocket.getMethod("getApplicationProtocol", new Class[0]);
        }

        _cls3()
        {
        }
    }

}
