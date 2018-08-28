// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

// Referenced classes of package io.grpc.okhttp.internal:
//            Platform, Protocol

static final class serverProviderClass extends Platform
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
            s = new serverProviderClass(s);
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
        sslsocket = (putMethod)Proxy.getInvocationHandler(getMethod.invoke(null, new Object[] {
            sslsocket
        }));
        if (((getMethod) (sslsocket)).rted || ((rted) (sslsocket)).d != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        logger.logp(Level.INFO, "io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform", "getSelectedProtocol", "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
        return null;
        if (((logger) (sslsocket)).rted)
        {
            return null;
        }
        try
        {
            sslsocket = ((rted) (sslsocket)).d;
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
        return android.support.v4.content.N._fld9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0;
    }

    public (Method method, Method method1, Method method2, Class class1, Class class2, Provider provider)
    {
        super(provider);
        putMethod = method;
        getMethod = method1;
        removeMethod = method2;
        clientProviderClass = class1;
        serverProviderClass = class2;
    }
}
