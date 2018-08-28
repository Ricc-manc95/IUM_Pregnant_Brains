// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskData

public abstract class 
{

    public abstract TaskData build();

    public abstract  setAccountName(String s);

    public abstract  setArchivedTime(Long long1);

    public abstract  setColor(int i);

    public abstract  setCreatedTime(Long long1);

    public abstract  setDone(boolean flag);

    public abstract  setId(String s);

    public abstract  setOriginalDueTimeMillis(Long long1);

    public abstract  setRecurrenceId(String s);

    public abstract  setRecurringSometimeToday(boolean flag);

    public abstract  setTaskAssistanceProtoBytesInternal(byte abyte0[]);

    public abstract  setTimeRange(TimeRange timerange);

    public abstract  setTitle(String s);

    public abstract  setUnscheduled(boolean flag);

    public ()
    {
    }
}
