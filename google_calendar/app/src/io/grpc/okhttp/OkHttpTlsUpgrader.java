// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import com.google.common.base.Strings;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.OkHostnameVerifier;
import io.grpc.okhttp.internal.Protocol;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

// Referenced classes of package io.grpc.okhttp:
//            OkHttpProtocolNegotiator

final class OkHttpTlsUpgrader
{

    private static final List TLS_PROTOCOLS;

    public static SSLSocket upgrade(SSLSocketFactory sslsocketfactory, HostnameVerifier hostnameverifier, Socket socket, String s, int i, ConnectionSpec connectionspec)
        throws IOException
    {
        if (sslsocketfactory == null)
        {
            throw new NullPointerException(String.valueOf("sslSocketFactory"));
        }
        if (socket == null)
        {
            throw new NullPointerException(String.valueOf("socket"));
        }
        if (connectionspec == null)
        {
            throw new NullPointerException(String.valueOf("spec"));
        }
        socket = (SSLSocket)sslsocketfactory.createSocket(socket, s, i, true);
        String as[];
        OkHttpProtocolNegotiator okhttpprotocolnegotiator;
        boolean flag;
        if (connectionspec.cipherSuites != null)
        {
            sslsocketfactory = socket.getEnabledCipherSuites();
            sslsocketfactory = (String[])Util.intersect(java/lang/String, connectionspec.cipherSuites, sslsocketfactory);
        } else
        {
            sslsocketfactory = null;
        }
        as = socket.getEnabledProtocols();
        as = (String[])Util.intersect(java/lang/String, connectionspec.tlsVersions, as);
        sslsocketfactory = new ConnectionSpec((new io.grpc.okhttp.internal.ConnectionSpec.Builder(connectionspec)).cipherSuites(sslsocketfactory).tlsVersions(as));
        socket.setEnabledProtocols(((ConnectionSpec) (sslsocketfactory)).tlsVersions);
        sslsocketfactory = ((ConnectionSpec) (sslsocketfactory)).cipherSuites;
        if (sslsocketfactory != null)
        {
            socket.setEnabledCipherSuites(sslsocketfactory);
        }
        okhttpprotocolnegotiator = OkHttpProtocolNegotiator.NEGOTIATOR;
        if (connectionspec.supportsTlsExtensions)
        {
            sslsocketfactory = TLS_PROTOCOLS;
        } else
        {
            sslsocketfactory = null;
        }
        sslsocketfactory = okhttpprotocolnegotiator.negotiate(socket, s, sslsocketfactory);
        flag = TLS_PROTOCOLS.contains(Protocol.get(sslsocketfactory));
        connectionspec = String.valueOf(TLS_PROTOCOLS);
        connectionspec = (new StringBuilder(String.valueOf(connectionspec).length() + 50)).append("Only ").append(connectionspec).append(" are supported, but negotiated protocol is %s").toString();
        if (!flag)
        {
            throw new IllegalStateException(Strings.lenientFormat(connectionspec, new Object[] {
                sslsocketfactory
            }));
        }
        sslsocketfactory = hostnameverifier;
        if (hostnameverifier == null)
        {
            sslsocketfactory = OkHostnameVerifier.INSTANCE;
        }
        if (s.startsWith("[") && s.endsWith("]"))
        {
            hostnameverifier = s.substring(1, s.length() - 1);
        } else
        {
            hostnameverifier = s;
        }
        if (!sslsocketfactory.verify(hostnameverifier, socket.getSession()))
        {
            sslsocketfactory = String.valueOf(s);
            if (sslsocketfactory.length() != 0)
            {
                sslsocketfactory = "Cannot verify hostname: ".concat(sslsocketfactory);
            } else
            {
                sslsocketfactory = new String("Cannot verify hostname: ");
            }
            throw new SSLPeerUnverifiedException(sslsocketfactory);
        } else
        {
            return socket;
        }
    }

    static 
    {
        TLS_PROTOCOLS = Collections.unmodifiableList(Arrays.asList(new Protocol[] {
            Protocol.GRPC_EXP, Protocol.HTTP_2
        }));
    }
}
