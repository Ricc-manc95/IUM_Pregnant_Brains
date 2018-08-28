// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class ReminderInstance extends GenericJson
{

    public String method;
    public Integer minutes;

    public ReminderInstance()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ReminderInstance)clone();
    }

    public final volatile GenericData clone()
    {
        return (ReminderInstance)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ReminderInstance)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ReminderInstance)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ReminderInstance)super.set(s, obj);
    }
}
