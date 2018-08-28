// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http.javanet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

// Referenced classes of package com.google.api.client.http.javanet:
//            ConnectionFactory

public final class DefaultConnectionFactory
    implements ConnectionFactory
{

    private final Proxy proxy;

    public DefaultConnectionFactory()
    {
        this(null);
    }

    public DefaultConnectionFactory(Proxy proxy1)
    {
        proxy = proxy1;
    }

    public final HttpURLConnection openConnection(URL url)
        throws IOException
    {
        if (proxy == null)
        {
            url = url.openConnection();
        } else
        {
            url = url.openConnection(proxy);
        }
        return (HttpURLConnection)url;
    }
}
