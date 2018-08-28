// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            HabitData

public final class Habit extends GenericJson
{

    public Boolean deleted;
    public String etag;
    public HabitData habitData;
    public String id;
    public String kind;
    public DateTime updated;

    public Habit()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Habit)clone();
    }

    public final volatile GenericData clone()
    {
        return (Habit)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Habit)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Habit)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Habit)super.set(s, obj);
    }
}
