// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            HabitInstanceData

public final class EventHabitInstance extends GenericJson
{

    public HabitInstanceData data;
    public String parentId;

    public EventHabitInstance()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventHabitInstance)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventHabitInstance)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventHabitInstance)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventHabitInstance)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventHabitInstance)super.set(s, obj);
    }
}