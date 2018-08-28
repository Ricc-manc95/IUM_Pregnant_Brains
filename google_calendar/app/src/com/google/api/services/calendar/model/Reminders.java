// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            ReminderInstance

public final class Reminders extends GenericJson
{

    public Boolean enableFollowup;
    public Boolean enableRecommit;
    public List reminderOverrides;
    public Boolean useDefaultReminders;

    public Reminders()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Reminders)clone();
    }

    public final volatile GenericData clone()
    {
        return (Reminders)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Reminders)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Reminders)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Reminders)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/ReminderInstance);
    }
}
