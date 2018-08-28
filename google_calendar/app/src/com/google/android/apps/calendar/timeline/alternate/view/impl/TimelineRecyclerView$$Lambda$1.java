// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineRecyclerView

final class arg._cls2
    implements Runnable
{

    private final TimelineRecyclerView arg$1;
    private final Supplier arg$2;

    public final void run()
    {
        Object obj = arg$1;
        Object obj1 = arg$2;
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        obj = new <init>(((TimelineRecyclerView) (obj)), ((Supplier) (obj1)));
        obj1 = TimeUnit.SECONDS;
        calendarexecutor.getDelegate().schedule(((Runnable) (obj)), 1L, ((TimeUnit) (obj1)));
    }

    (TimelineRecyclerView timelinerecyclerview, Supplier supplier)
    {
        arg$1 = timelinerecyclerview;
        arg$2 = supplier;
    }
}
