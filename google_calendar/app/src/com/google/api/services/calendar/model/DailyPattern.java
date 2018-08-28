// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class DailyPattern extends GenericJson
{

    public Boolean afternoon;
    public Boolean any;
    public Boolean evening;
    public Boolean fixedTimeScheduling;
    public Boolean morning;

    public DailyPattern()
    {
    }

    public final volatile GenericJson clone()
    {
        return (DailyPattern)clone();
    }

    public final volatile GenericData clone()
    {
        return (DailyPattern)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (DailyPattern)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (DailyPattern)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (DailyPattern)super.set(s, obj);
    }
}
