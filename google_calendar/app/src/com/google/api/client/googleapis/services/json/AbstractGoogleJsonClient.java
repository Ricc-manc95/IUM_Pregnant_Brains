// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.ObjectParser;

public class AbstractGoogleJsonClient extends AbstractGoogleClient
{

    public AbstractGoogleJsonClient(Builder builder)
    {
        super(builder);
    }

    public final ObjectParser getObjectParser()
    {
        return (JsonObjectParser)super.getObjectParser();
    }
}
