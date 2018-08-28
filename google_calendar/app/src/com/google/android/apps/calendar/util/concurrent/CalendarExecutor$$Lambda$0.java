// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarExecutor

public final class arg._cls1
    implements Executor
{

    private final CalendarExecutor arg$1;

    public final void execute(Runnable runnable)
    {
        CalendarExecutor calendarexecutor = arg$1;
        if (CalendarExecutor.currentExecutor() == calendarexecutor)
        {
            runnable.run();
            return;
        } else
        {
            calendarexecutor.execute(runnable);
            return;
        }
    }

    public Q(CalendarExecutor calendarexecutor)
    {
        arg$1 = calendarexecutor;
    }
}
