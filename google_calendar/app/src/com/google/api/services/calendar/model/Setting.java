// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class Setting extends GenericJson
{

    public Boolean deleted;
    public String etag;
    public String id;
    public String kind;
    public String name;
    public String namespace;
    public String value;

    public Setting()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Setting)clone();
    }

    public final volatile GenericData clone()
    {
        return (Setting)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Setting)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Setting)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Setting)super.set(s, obj);
    }
}
