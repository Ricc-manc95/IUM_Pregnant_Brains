// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MethodOverride
    implements HttpExecuteInterceptor, HttpRequestInitializer
{

    private final boolean overrideAllMethods;

    public MethodOverride()
    {
        this(false);
    }

    private MethodOverride(boolean flag)
    {
        overrideAllMethods = false;
    }

    public final void initialize(HttpRequest httprequest)
    {
        httprequest.executeInterceptor = this;
    }

    public final void intercept(HttpRequest httprequest)
        throws IOException
    {
        String s;
        boolean flag1;
        flag1 = false;
        s = httprequest.requestMethod;
        if (s.equals("POST")) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (s.equals("GET") ? httprequest.url.build().length() > 2048 : overrideAllMethods) goto _L4; else goto _L3
_L4:
        flag = true;
_L6:
label0:
        {
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_181;
            }
            s = httprequest.requestMethod;
            if ("POST" != null)
            {
                flag = flag1;
                if (!HttpMediaType.TOKEN_REGEX.matcher("POST").matches())
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        break; /* Loop/switch isn't completed */
_L3:
        if (!httprequest.transport.supportsMethod(s))
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
        httprequest.requestMethod = "POST";
        HttpHeaders httpheaders = (HttpHeaders)httprequest.headers.set("X-HTTP-Method-Override", s);
        if (s.equals("GET"))
        {
            httprequest.content = new UrlEncodedContent((GenericUrl)httprequest.url.clone());
            httprequest.url.clear();
        } else
        if (httprequest.content == null)
        {
            httprequest.content = new EmptyContent();
            return;
        }
    }
}
