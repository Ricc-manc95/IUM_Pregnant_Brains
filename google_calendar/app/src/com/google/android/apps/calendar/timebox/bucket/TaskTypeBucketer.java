// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.AutoValue_TaskSet_Key;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import java.util.Arrays;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

final class TaskTypeBucketer extends TypeBucketer
{

    TaskTypeBucketer()
    {
    }

    final void addToBucket(Object obj, TimeRangeEntry timerangeentry)
    {
        obj = (com.google.common.collect.ImmutableList.Builder)((com.google.android.apps.calendar.timebox.task.TaskSet.Builder)((TimeRangeEntry)obj).getValue()).itemsBuilder().add((TaskItem)timerangeentry.getValue());
    }

    final TimeRangeEntry finalizeBucket(Object obj)
    {
        obj = (TimeRangeEntry)obj;
        return new AutoValue_TimeRangeEntry(((TimeRangeEntry) (obj)).getKey(), ((com.google.android.apps.calendar.timebox.task.TaskSet.Builder)((TimeRangeEntry) (obj)).getValue()).build(), ((TimeRangeEntry) (obj)).getRange());
    }

    final int hashEntry(TimeRangeEntry timerangeentry)
    {
        TaskData taskdata = ((TaskItem)timerangeentry.getValue()).getTaskData();
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(timerangeentry.getRange().hashCode()), taskdata.getAccountName(), Boolean.valueOf(taskdata.isDone())
        });
    }

    final Object newBucket(TimeRangeEntry timerangeentry)
    {
        com.google.android.apps.calendar.timebox.task.AutoValue_TaskSet.Builder builder = new com.google.android.apps.calendar.timebox.task.AutoValue_TaskSet.Builder();
        Object obj = (TaskItem)timerangeentry.getValue();
        com.google.common.collect.ImmutableList.Builder builder1 = (com.google.common.collect.ImmutableList.Builder)builder.setDone(((TaskItem) (obj)).getTaskData().isDone()).itemsBuilder().add(obj);
        obj = ((TaskItem) (obj)).getTaskData();
        return new AutoValue_TimeRangeEntry(new AutoValue_TaskSet_Key(timerangeentry.getRange(), ((TaskData) (obj)).getAccountName(), ((TaskData) (obj)).isDone()), builder, timerangeentry.getRange());
    }
}
