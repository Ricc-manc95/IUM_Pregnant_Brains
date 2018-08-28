// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

public final class HabitInstanceData extends GenericJson
{

    public String status;
    public Boolean statusInferred;
    public String type;

    public HabitInstanceData()
    {
    }

    public final volatile GenericJson clone()
    {
        return (HabitInstanceData)clone();
    }

    public final volatile GenericData clone()
    {
        return (HabitInstanceData)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (HabitInstanceData)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (HabitInstanceData)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (HabitInstanceData)super.set(s, obj);
    }
}
