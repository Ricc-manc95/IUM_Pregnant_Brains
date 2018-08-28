// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TaskBundleComparators, TimelineTask, TimelineItem

public static final class 
    implements Comparator
{

    private static long getKey(TimelineTask timelinetask)
    {
        long l = TaskBundleComparators.coalesce(timelinetask.createdTimeMillis);
        long l1 = TaskBundleComparators.coalesce(timelinetask.originalDueMillis);
        if (l == 0L && l1 == 0L)
        {
            LogUtils.wtf(TaskBundleComparators.TAG, "Both creationTime and dueTime null.", new Object[0]);
        }
        return Math.max(l, l1);
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (TimelineItem)obj;
        obj1 = (TimelineItem)obj1;
        long l = getKey((TimelineTask)obj);
        long l1 = getKey((TimelineTask)obj1);
        if (l != l1)
        {
            return -(l != l1);
        } else
        {
            return -(TaskBundleComparators.coalesce(((TimelineTask)obj).createdTimeMillis) != TaskBundleComparators.coalesce(((TimelineTask)obj1).createdTimeMillis));
        }
    }

    public ()
    {
    }
}
