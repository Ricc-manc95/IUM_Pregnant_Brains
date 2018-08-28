// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class EventEventUpdate extends GenericJson
{

    public Boolean attendeesChanged;
    public Boolean descriptionChanged;
    public Boolean endTimeChanged;
    public Boolean locationChanged;
    public Boolean nameChanged;
    public Boolean startTimeChanged;

    public EventEventUpdate()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventEventUpdate)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventEventUpdate)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventEventUpdate)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventEventUpdate)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventEventUpdate)super.set(s, obj);
    }
}
