// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.Range;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl

final class arg._cls1
    implements Consumer
{

    private final CalendarContentStoreImpl arg$1;

    public final void accept(Object obj)
    {
        CalendarContentStoreImpl calendarcontentstoreimpl = arg$1;
        obj = (Range)obj;
        com.google.android.apps.calendar.util.concurrent.Lambda._cls1 _lcls1 = calendarcontentstoreimpl.serialExecutor;
        boolean flag;
        if (CalendarExecutors.serialExecutorTag.get() == _lcls1)
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
        if (!((Range) (obj)).equals(calendarcontentstoreimpl.currentViewPort))
        {
            calendarcontentstoreimpl.currentViewPort = ((Range) (obj));
            calendarcontentstoreimpl.weeksToCache = calendarcontentstoreimpl.newWeekCacheIterator(((Range) (obj)));
            calendarcontentstoreimpl.currentFuture.addListener(new <init>(calendarcontentstoreimpl), calendarcontentstoreimpl.serialExecutor);
        }
    }

    (CalendarContentStoreImpl calendarcontentstoreimpl)
    {
        arg$1 = calendarcontentstoreimpl;
    }
}
