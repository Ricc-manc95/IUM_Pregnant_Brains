// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;

public final class EventDateTime extends GenericJson
{

    public DateTime date;
    public DateTime dateTime;
    public String timeZone;

    public EventDateTime()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventDateTime)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventDateTime)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventDateTime)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventDateTime)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventDateTime)super.set(s, obj);
    }
}