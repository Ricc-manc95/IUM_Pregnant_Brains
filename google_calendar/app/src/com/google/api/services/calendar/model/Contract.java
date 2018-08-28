// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            TimePattern

public final class Contract extends GenericJson
{

    public Integer durationMinutes;
    public String interval;
    public Integer numInstancesPerInterval;
    public TimePattern timePattern;
    public Long untilMillisUtc;

    public Contract()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Contract)clone();
    }

    public final volatile GenericData clone()
    {
        return (Contract)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Contract)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Contract)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Contract)super.set(s, obj);
    }
}
