// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class Promotion extends GenericJson
{

    public String description;
    public String imageUrl;

    public Promotion()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Promotion)clone();
    }

    public final volatile GenericData clone()
    {
        return (Promotion)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Promotion)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Promotion)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Promotion)super.set(s, obj);
    }
}
