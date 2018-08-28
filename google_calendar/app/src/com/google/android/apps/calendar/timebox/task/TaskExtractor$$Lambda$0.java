// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskSet, TaskItem, TaskData

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        TimeRangeEntry timerangeentry = (TimeRangeEntry)obj;
        TaskSet taskset = (TaskSet)timerangeentry.getValue();
        int i = taskset.getItems().size();
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("An empty bundle."));
        }
        obj = timerangeentry;
        if (i == 1)
        {
            obj = (TaskItem)((UnmodifiableIterator)taskset.getItems().iterator()).next();
            obj = new AutoValue_TimeRangeEntry(((TaskItem) (obj)).getTaskData().getId(), obj, timerangeentry.getRange());
        }
        return obj;
    }


    private ()
    {
    }
}
