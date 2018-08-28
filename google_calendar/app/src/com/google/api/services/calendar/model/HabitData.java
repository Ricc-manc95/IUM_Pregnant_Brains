// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            Contract, Reminders

public final class HabitData extends GenericJson
{

    public String color;
    public Contract contract;
    public String customName;
    public Reminders reminders;
    public String summary;
    public String transparency;
    public String type;
    public String visibility;

    public HabitData()
    {
    }

    public final volatile GenericJson clone()
    {
        return (HabitData)clone();
    }

    public final volatile GenericData clone()
    {
        return (HabitData)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (HabitData)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (HabitData)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (HabitData)super.set(s, obj);
    }
}
