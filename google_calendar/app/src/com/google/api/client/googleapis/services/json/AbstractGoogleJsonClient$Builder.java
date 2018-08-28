// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services.json;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import java.util.Collections;

public class  extends com.google.api.client.googleapis.services.lder
{

    public volatile com.google.api.client.googleapis.services.lder setApplicationName(String s)
    {
        return setApplicationName(s);
    }

    public setApplicationName setApplicationName(String s)
    {
        return (setApplicationName)super.pplicationName(s);
    }

    public volatile com.google.api.client.googleapis.services.lder setRootUrl(String s)
    {
        return setRootUrl(s);
    }

    public setRootUrl setRootUrl(String s)
    {
        return (setRootUrl)super.ootUrl(s);
    }

    public volatile com.google.api.client.googleapis.services.lder setServicePath(String s)
    {
        return setServicePath(s);
    }

    public setServicePath setServicePath(String s)
    {
        return (setServicePath)super.ervicePath(s);
    }

    public (HttpTransport httptransport, JsonFactory jsonfactory, String s, String s1, HttpRequestInitializer httprequestinitializer, boolean flag)
    {
        jsonfactory = new com.google.api.client.json.eJsonClient.Builder(jsonfactory);
        jsonfactory.eys = Collections.emptySet();
        super(httptransport, s, s1, new JsonObjectParser(jsonfactory), httprequestinitializer);
    }
}
