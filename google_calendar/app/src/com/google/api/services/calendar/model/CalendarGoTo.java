// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class CalendarGoTo extends GenericJson
{

    public Boolean isClicked;
    public String optimization;
    public List sources;
    public String text;
    public String type;
    public String uri;

    public CalendarGoTo()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CalendarGoTo)clone();
    }

    public final volatile GenericData clone()
    {
        return (CalendarGoTo)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CalendarGoTo)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CalendarGoTo)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CalendarGoTo)super.set(s, obj);
    }
}
