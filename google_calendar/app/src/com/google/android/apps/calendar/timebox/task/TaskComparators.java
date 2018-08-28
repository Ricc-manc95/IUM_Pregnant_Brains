// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import java.util.Comparator;

public final class TaskComparators
{

    private static final Comparator COMPARATOR = new _cls1();
    private static final Comparator DONE_COMPARATOR = new _cls2();

    public static Comparator get(boolean flag)
    {
        if (flag)
        {
            return DONE_COMPARATOR;
        } else
        {
            return COMPARATOR;
        }
    }

    static long nullToZero(Long long1)
    {
        if (long1 != null)
        {
            return long1.longValue();
        } else
        {
            return 0L;
        }
    }


    private class _cls1
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

        _cls1()
        {
        }
    }


    private class _cls2
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

        _cls2()
        {
        }
    }

}
