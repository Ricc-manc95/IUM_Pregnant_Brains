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
        TaskItem taskitem = (TaskItem)obj;
        TaskItem taskitem1 = (TaskItem)obj1;
        ComparisonChain comparisonchain = ComparisonChain.ACTIVE;
        obj1 = taskitem.getTaskData();
        obj = ((TaskData) (obj1)).getArchivedTime();
        obj1 = Long.valueOf(TaskComparators.nullToZero(((TaskData) (obj1)).getCreatedTime()));
        long l;
        if (obj == null)
        {
            if (obj1 != null)
            {
                obj = obj1;
            } else
            {
                throw new NullPointerException("Both parameters are null");
            }
        }
        l = -((Long)obj).longValue();
        obj1 = taskitem1.getTaskData();
        obj = ((TaskData) (obj1)).getArchivedTime();
        obj1 = Long.valueOf(TaskComparators.nullToZero(((TaskData) (obj1)).getCreatedTime()));
        if (obj == null)
        {
            if (obj1 != null)
            {
                obj = obj1;
            } else
            {
                throw new NullPointerException("Both parameters are null");
            }
        }
        return comparisonchain.compare(l, -((Long)obj).longValue()).compare(taskitem.getTaskData().getId(), taskitem1.getTaskData().getId()).result();
    }

    ()
    {
    }
}
