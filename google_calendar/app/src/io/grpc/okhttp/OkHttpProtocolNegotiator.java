// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

class OkHttpProtocolNegotiator
{
    static final class AndroidNegotiator extends OkHttpProtocolNegotiator
    {

        private static final OptionalMethod GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod([B, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod GET_NPN_SELECTED_PROTOCOL = new OptionalMethod([B, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod SET_ALPN_PROTOCOLS = new OptionalMethod(null, "setAlpnProtocols", new Class[] {
            [B
        });
        private static final OptionalMethod SET_HOSTNAME = new OptionalMethod(null, "setHostname", new Class[] {
            java/lang/String
        });
        private static final OptionalMethod SET_NPN_PROTOCOLS = new OptionalMethod(null, "setNpnProtocols", new Class[] {
            [B
        });
        private static final OptionalMethod SET_USE_SESSION_TICKETS;

        protected final void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
        {
            if (s != null)
            {
                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                    Boolean.valueOf(true)
                });
                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sslsocket, new Object[] {
                    s
                });
            }
            s = ((String) (new Object[1]));
            s[0] = Platform.concatLengthPrefixed(list);
            if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() == android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
            {
                SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sslsocket, s);
            }
            if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() != android.support.v4.content.ModernAsyncTask.Status.NONE$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
            {
                SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sslsocket, s);
                return;
            } else
            {
                throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
            }
        }

        public final String getSelectedProtocol(SSLSocket sslsocket)
        {
            if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() != android.support.v4.content.ModernAsyncTask.Status.ALPN_AND_NPN$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
            {
                break MISSING_BLOCK_LABEL_47;
            }
            byte abyte0[] = (byte[])GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sslsocket, new Object[0]);
            if (abyte0 == null)
            {
                break MISSING_BLOCK_LABEL_47;
            }
            String s = new String(abyte0, Util.UTF_8);
            return s;
            Exception exception;
            exception;
            if (platform.getTlsExtensionType$50KKOQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNL0R31EHJ6USJD4HA6OSQ5F1Q6ARJJD5NMSL3PE1IJM___0() == android.support.v4.content.ModernAsyncTask.Status.NONE$9HKMUBR7E9O66BRFDDK78T3G5TKMST35E9N62R1FA1M62T36DTP6Q92KDHPKAU3KCLN76QBFDPA7IS357C______0)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            sslsocket = (byte[])GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sslsocket, new Object[0]);
            if (sslsocket == null)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            sslsocket = new String(sslsocket, Util.UTF_8);
            return sslsocket;
            sslsocket;
            return null;
        }

        public final String negotiate(SSLSocket sslsocket, String s, List list)
            throws IOException
        {
            String s2 = getSelectedProtocol(sslsocket);
            String s1 = s2;
            if (s2 == null)
            {
                s1 = negotiate(sslsocket, s, list);
            }
            return s1;
        }

        static 
        {
            SET_USE_SESSION_TICKETS = new OptionalMethod(null, "setUseSessionTickets", new Class[] {
                Boolean.TYPE
            });
        }

        AndroidNegotiator(Platform platform1)
        {
            super(platform1);
        }
    }


    private static final Platform DEFAULT_PLATFORM;
    public static OkHttpProtocolNegotiator NEGOTIATOR = createNegotiator(io/grpc/okhttp/OkHttpProtocolNegotiator.getClassLoader());
    private static final Logger logger = Logger.getLogger(io/grpc/okhttp/OkHttpProtocolNegotiator.getName());
    public final Platform platform;

    OkHttpProtocolNegotiator(Platform platform1)
    {
        if (platform1 == null)
        {
            throw new NullPointerException(String.valueOf("platform"));
        } else
        {
            platform = (Platform)platform1;
            return;
        }
    }

    private static OkHttpProtocolNegotiator createNegotiator(ClassLoader classloader)
    {
        classloader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        boolean flag = true;
_L1:
        ClassNotFoundException classnotfoundexception;
        if (flag)
        {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        } else
        {
            return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
        }
        classnotfoundexception;
        logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator", "createNegotiator", "Unable to find Conscrypt. Skipping", classnotfoundexception);
        classloader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
        flag = true;
          goto _L1
        classloader;
        logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator", "createNegotiator", "Unable to find any OpenSSLSocketImpl. Skipping", classloader);
        flag = false;
          goto _L1
    }

    protected void configureTlsExtensions(SSLSocket sslsocket, String s, List list)
    {
        platform.configureTlsExtensions(sslsocket, s, list);
    }

    public String getSelectedProtocol(SSLSocket sslsocket)
    {
        return platform.getSelectedProtocol(sslsocket);
    }

    public String negotiate(SSLSocket sslsocket, String s, List list)
        throws IOException
    {
        if (list != null)
        {
            configureTlsExtensions(sslsocket, s, list);
        }
        sslsocket.startHandshake();
        s = getSelectedProtocol(sslsocket);
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        throw new RuntimeException("protocol negotiation failed");
        s;
        platform.afterHandshake(sslsocket);
        throw s;
        platform.afterHandshake(sslsocket);
        return s;
    }

    static 
    {
        DEFAULT_PLATFORM = Platform.PLATFORM;
    }
}
