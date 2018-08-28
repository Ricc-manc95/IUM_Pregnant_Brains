// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TaskBundleComparators, TimelineTask, TimelineItem

static final class Q
    implements Comparator
{

    private static long getKey(TimelineTask timelinetask)
    {
        if (timelinetask.archivedTimeMillis != null)
        {
            return timelinetask.archivedTimeMillis.longValue();
        }
        if (timelinetask.createdTimeMillis == null)
        {
            LogUtils.wtf(TaskBundleComparators.TAG, "Both creationTime and archivedTime null.", new Object[0]);
            return 0L;
        } else
        {
            return timelinetask.createdTimeMillis.longValue();
        }
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (TimelineItem)obj;
        obj1 = (TimelineItem)obj1;
        return -(getKey((TimelineTask)obj) != getKey((TimelineTask)obj1));
    }

    Q()
    {
    }
}
