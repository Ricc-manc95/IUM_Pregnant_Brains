// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class Time extends GenericJson
{

    public Boolean dateOnly;
    public Boolean floatingTime;
    public Long timeMs;
    public Integer timeZoneOffsetMinutes;

    public Time()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Time)clone();
    }

    public final volatile GenericData clone()
    {
        return (Time)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Time)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Time)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Time)super.set(s, obj);
    }
}
