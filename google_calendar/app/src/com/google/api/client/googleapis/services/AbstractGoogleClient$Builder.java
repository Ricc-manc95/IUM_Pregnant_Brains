// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.ObjectParser;

// Referenced classes of package com.google.api.client.googleapis.services:
//            AbstractGoogleClient

public static class httpRequestInitializer
{

    public String applicationName;
    public String batchPath;
    public HttpRequestInitializer httpRequestInitializer;
    public final ObjectParser objectParser;
    public String rootUrl;
    public String servicePath;
    public final HttpTransport transport;

    public httpRequestInitializer setApplicationName(String s)
    {
        applicationName = s;
        return this;
    }

    public applicationName setBatchPath(String s)
    {
        batchPath = s;
        return this;
    }

    public batchPath setRootUrl(String s)
    {
        rootUrl = AbstractGoogleClient.normalizeRootUrl(s);
        return this;
    }

    public eRootUrl setServicePath(String s)
    {
        servicePath = AbstractGoogleClient.normalizeServicePath(s);
        return this;
    }

    public (HttpTransport httptransport, String s, String s1, ObjectParser objectparser, HttpRequestInitializer httprequestinitializer)
    {
        if (httptransport == null)
        {
            throw new NullPointerException();
        } else
        {
            transport = (HttpTransport)httptransport;
            objectParser = objectparser;
            setRootUrl(s);
            setServicePath(s1);
            httpRequestInitializer = httprequestinitializer;
            return;
        }
    }
}
