// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.util.SparseArray;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl, CalendarWeekCache, CalendarWeek

final class arg._cls1
    implements Predicate
{

    private final CalendarContentStoreImpl arg$1;

    public final boolean apply(Object obj)
    {
        CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
        obj = (Integer)obj;
        Object obj1 = calendarcontentstoreimpl.serialExecutor;
        boolean flag;
        if (CalendarExecutors.serialExecutorTag.get() == obj1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        obj1 = calendarcontentstoreimpl.weekCache;
        int i = ((Integer) (obj)).intValue();
        obj = ((CalendarWeekCache) (obj1)).serialExecutor;
        if (CalendarExecutors.serialExecutorTag.get() == obj)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        obj = (CalendarWeek)((CalendarWeekCache) (obj1)).weeks.get(i);
        return obj == null || ((CalendarWeek) (obj)).getCacheGeneration() != (long)calendarcontentstoreimpl.cacheGeneration;
    }

    (CalendarContentStoreImpl calendarcontentstoreimpl)
    {
        arg$1 = calendarcontentstoreimpl;
    }
}
