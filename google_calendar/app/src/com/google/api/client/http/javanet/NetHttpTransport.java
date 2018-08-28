// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http.javanet;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.common.base.Strings;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

// Referenced classes of package com.google.api.client.http.javanet:
//            DefaultConnectionFactory, ConnectionFactory, NetHttpRequest

public final class NetHttpTransport extends HttpTransport
{

    private static final String SUPPORTED_METHODS[];
    private final ConnectionFactory connectionFactory;
    private final HostnameVerifier hostnameVerifier;
    private final SSLSocketFactory sslSocketFactory;

    public NetHttpTransport()
    {
        this(null, null, null);
    }

    private NetHttpTransport(ConnectionFactory connectionfactory, SSLSocketFactory sslsocketfactory, HostnameVerifier hostnameverifier)
    {
        if (true)
        {
            if (System.getProperty("com.google.api.client.should_use_proxy") != null)
            {
                connectionfactory = new DefaultConnectionFactory(new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("https.proxyHost"), Integer.parseInt(System.getProperty("https.proxyPort")))));
            } else
            {
                connectionfactory = new DefaultConnectionFactory();
            }
        } else
        {
            connectionfactory = null;
        }
        connectionFactory = connectionfactory;
        sslSocketFactory = null;
        hostnameVerifier = null;
    }

    protected final LowLevelHttpRequest buildRequest(String s, String s1)
        throws IOException
    {
        if (!supportsMethod(s))
        {
            throw new IllegalArgumentException(Strings.lenientFormat("HTTP method %s not supported", new Object[] {
                s
            }));
        }
        s1 = new URL(s1);
        s1 = connectionFactory.openConnection(s1);
        s1.setRequestMethod(s);
        if (s1 instanceof HttpsURLConnection)
        {
            s = (HttpsURLConnection)s1;
            if (hostnameVerifier != null)
            {
                s.setHostnameVerifier(hostnameVerifier);
            }
            if (sslSocketFactory != null)
            {
                s.setSSLSocketFactory(sslSocketFactory);
            }
        }
        return new NetHttpRequest(s1);
    }

    public final boolean supportsMethod(String s)
    {
        return Arrays.binarySearch(SUPPORTED_METHODS, s) >= 0;
    }

    static 
    {
        String as[] = new String[7];
        as[0] = "DELETE";
        as[1] = "GET";
        as[2] = "HEAD";
        as[3] = "OPTIONS";
        as[4] = "POST";
        as[5] = "PUT";
        as[6] = "TRACE";
        SUPPORTED_METHODS = as;
        Arrays.sort(as);
    }
}
