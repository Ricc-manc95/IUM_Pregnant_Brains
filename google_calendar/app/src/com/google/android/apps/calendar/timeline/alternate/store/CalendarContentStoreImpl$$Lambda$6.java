// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.util.concurrent.FluentFuture;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl, CalendarWeek, CalendarWeekCache

final class arg._cls2
    implements Runnable
{

    private final CalendarContentStoreImpl arg$1;
    private final FluentFuture arg$2;

    public final void run()
    {
        CalendarContentStoreImpl calendarcontentstoreimpl;
        Object obj;
        calendarcontentstoreimpl = arg$1;
        obj = arg$2;
        com.google.android.apps.calendar.util.concurrent.Lambda._cls6 _lcls6 = calendarcontentstoreimpl.serialExecutor;
        boolean flag;
        if (CalendarExecutors.serialExecutorTag.get() == _lcls6)
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
        if (calendarcontentstoreimpl.updateCount != 0)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        obj = (List)((FluentFuture) (obj)).get();
        CalendarWeek calendarweek;
        CalendarWeekCache calendarweekcache;
        int i;
        boolean flag1;
        if (((List) (obj)).size() == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        com.google.android.apps.calendar.util.concurrent.Lambda._cls6 _lcls6_1;
        try
        {
            throw new IllegalStateException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally
        {
            calendarcontentstoreimpl.currentFuture.addListener(new (calendarcontentstoreimpl), calendarcontentstoreimpl.serialExecutor);
        }
        Log.e("CalendarContentStore", "Unable to load weeks", ((Throwable) (obj)));
        calendarcontentstoreimpl.currentFuture.addListener(new <init>(calendarcontentstoreimpl), calendarcontentstoreimpl.serialExecutor);
        return;
        obj = ((List) (obj)).iterator();
_L1:
        if (!((Iterator) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_251;
        }
        calendarweek = (CalendarWeek)((Iterator) (obj)).next();
        calendarweekcache = calendarcontentstoreimpl.weekCache;
        i = calendarweek.getJulianWeek();
        _lcls6_1 = calendarweekcache.serialExecutor;
        Exception exception;
        boolean flag2;
        if (CalendarExecutors.serialExecutorTag.get() == _lcls6_1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_224;
        }
        throw new IllegalStateException();
        throw exception;
        calendarweekcache.weeks.put(i, calendarweek);
        calendarcontentstoreimpl.calendarsObservable.set(Collections.singleton(calendarweek));
          goto _L1
        calendarcontentstoreimpl.currentFuture.addListener(new <init>(calendarcontentstoreimpl), calendarcontentstoreimpl.serialExecutor);
        return;
    }

    (CalendarContentStoreImpl calendarcontentstoreimpl, FluentFuture fluentfuture)
    {
        arg$1 = calendarcontentstoreimpl;
        arg$2 = fluentfuture;
    }
}
