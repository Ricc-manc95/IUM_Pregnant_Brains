// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            DailyPattern

public final class TimePattern extends GenericJson
{

    public DailyPattern dailyPattern;
    public List dayRestrictions;

    public TimePattern()
    {
    }

    public final volatile GenericJson clone()
    {
        return (TimePattern)clone();
    }

    public final volatile GenericData clone()
    {
        return (TimePattern)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (TimePattern)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (TimePattern)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (TimePattern)super.set(s, obj);
    }
}
