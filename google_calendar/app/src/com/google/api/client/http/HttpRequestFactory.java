// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.io.IOException;

// Referenced classes of package com.google.api.client.http:
//            HttpRequest, HttpRequestInitializer, GenericUrl, HttpTransport, 
//            HttpContent

public final class HttpRequestFactory
{

    public final HttpRequestInitializer initializer;
    private final HttpTransport transport;

    public HttpRequestFactory(HttpTransport httptransport, HttpRequestInitializer httprequestinitializer)
    {
        transport = httptransport;
        initializer = httprequestinitializer;
    }

    public final HttpRequest buildRequest(String s, GenericUrl genericurl, HttpContent httpcontent)
        throws IOException
    {
        HttpRequest httprequest = new HttpRequest(transport, null);
        if (initializer != null)
        {
            initializer.initialize(httprequest);
        }
        httprequest.setRequestMethod(s);
        if (genericurl != null)
        {
            if (genericurl == null)
            {
                throw new NullPointerException();
            }
            httprequest.url = (GenericUrl)genericurl;
        }
        if (httpcontent != null)
        {
            httprequest.content = httpcontent;
        }
        return httprequest;
    }
}
