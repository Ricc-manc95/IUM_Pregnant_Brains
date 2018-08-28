// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskItem, TaskData, TaskComparators

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (TaskItem)obj;
        obj1 = (TaskItem)obj1;
        ComparisonChain comparisonchain = ComparisonChain.ACTIVE;
        TaskData taskdata = ((TaskItem) (obj)).getTaskData();
        long l = -Math.max(TaskComparators.nullToZero(taskdata.getCreatedTime()), TaskComparators.nullToZero(taskdata.getOriginalDueTimeMillis()));
        taskdata = ((TaskItem) (obj1)).getTaskData();
        return comparisonchain.compare(l, -Math.max(TaskComparators.nullToZero(taskdata.getCreatedTime()), TaskComparators.nullToZero(taskdata.getOriginalDueTimeMillis()))).compare(-TaskComparators.nullToZero(((TaskItem) (obj)).getTaskData().getCreatedTime()), -TaskComparators.nullToZero(((TaskItem) (obj1)).getTaskData().getCreatedTime())).compare(((TaskItem) (obj)).getTaskData().getId(), ((TaskItem) (obj1)).getTaskData().getId()).result();
    }

    ()
    {
    }
}
