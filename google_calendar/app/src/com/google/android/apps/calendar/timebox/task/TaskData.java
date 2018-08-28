// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;
import java.util.Arrays;

public abstract class TaskData
{

    public TaskData()
    {
    }

    public abstract String getAccountName();

    public abstract Long getArchivedTime();

    public abstract int getColor();

    public abstract Long getCreatedTime();

    public abstract String getId();

    public abstract Long getOriginalDueTimeMillis();

    public abstract String getRecurrenceId();

    public byte[] getTaskAssistanceProtoBytes()
    {
        byte abyte0[] = getTaskAssistanceProtoBytesInternal();
        if (abyte0 != null)
        {
            return Arrays.copyOf(abyte0, abyte0.length);
        } else
        {
            return null;
        }
    }

    abstract byte[] getTaskAssistanceProtoBytesInternal();

    public abstract TimeRange getTimeRange();

    public abstract String getTitle();

    public abstract boolean isDone();

    public abstract boolean isRecurringSometimeToday();

    public abstract boolean isUnscheduled();

    public abstract Builder toBuilder();
}
