// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.util.SparseArray;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl, CalendarWeekCache, CalendarWeek

final class arg._cls3
    implements Runnable
{

    private final CalendarContentStoreImpl arg$1;
    private final Consumer arg$2;
    private final SettableFuture arg$3;

    public final void run()
    {
        CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
        Object obj = arg$2;
        SettableFuture settablefuture = arg$3;
        calendarcontentstoreimpl.cacheGeneration = calendarcontentstoreimpl.cacheGeneration + 1;
        Object obj1 = new HashSet();
        boolean aflag[] = new boolean[1];
        aflag[0] = true;
        ((Consumer) (obj)).accept(new ation(calendarcontentstoreimpl, aflag, ((Set) (obj1))));
        aflag[0] = false;
        obj = new ArrayList(((Set) (obj1)).size());
        CalendarWeekCache calendarweekcache;
        boolean flag;
        int i;
        for (obj1 = ((Set) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); ((List) (obj)).add((CalendarWeek)calendarweekcache.weeks.get(i)))
        {
            i = ((Integer)((Iterator) (obj1)).next()).intValue();
            calendarweekcache = calendarcontentstoreimpl.weekCache;
            com.google.android.apps.calendar.util.concurrent.Lambda._cls2 _lcls2 = calendarweekcache.serialExecutor;
            if (CalendarExecutors.serialExecutorTag.get() == _lcls2)
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
        }

        calendarcontentstoreimpl.calendarsObservable.set(obj, new (settablefuture), com.google.common.util.concurrent.CE);
    }

    (CalendarContentStoreImpl calendarcontentstoreimpl, Consumer consumer, SettableFuture settablefuture)
    {
        arg$1 = calendarcontentstoreimpl;
        arg$2 = consumer;
        arg$3 = settablefuture;
    }
}
