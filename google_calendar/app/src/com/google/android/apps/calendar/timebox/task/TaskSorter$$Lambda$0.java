// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskSet, TaskComparators

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        TimeRangeEntry timerangeentry = (TimeRangeEntry)obj;
        obj = timerangeentry;
        if (timerangeentry.getValue() instanceof TaskSet)
        {
            obj = timerangeentry.getKey();
            TaskSet taskset = (TaskSet)timerangeentry.getValue();
            obj = new AutoValue_TimeRangeEntry(obj, taskset.toBuilder().ems(ImmutableList.sortedCopyOf(TaskComparators.get(taskset.isDone()), taskset.getItems())).(), timerangeentry.getRange());
        }
        return obj;
    }


    private ()
    {
    }
}
