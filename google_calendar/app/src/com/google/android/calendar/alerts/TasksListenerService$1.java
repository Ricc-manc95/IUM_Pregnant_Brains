// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.google.android.calendar.task.TaskDataFactory;
import java.util.List;

final class val.reminderEvents
    implements Runnable
{

    private final List val$reminderEvents;

    public final void run()
    {
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskDataFactory.instance.onTasksChanged(val$reminderEvents);
    }

    ()
    {
        val$reminderEvents = list;
        super();
    }
}
