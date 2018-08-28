// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ExecutorFactory, CalendarExecutor

final class val.calendarExecutor extends ScheduledThreadPoolExecutor
{

    private final ExecutorFactory this$0;
    private final CalendarExecutor val$calendarExecutor;

    public final void execute(Runnable runnable)
    {
        ExecutorFactory.onBeforeExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
        class .Lambda._cls0
            implements Runnable
        {

            private final ExecutorFactory._cls1 arg$1;
            private final Runnable arg$2;
            private final CalendarExecutor arg$3;

            public final void run()
            {
                Object obj;
                obj = arg$1;
                obj = arg$2;
                CalendarExecutor calendarexecutor = arg$3;
                ((Runnable) (obj)).run();
                ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                return;
                Object obj1;
                obj1;
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), ((Throwable) (obj1)));
                ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                return;
                obj1;
                ExecutorFactory.onAfterExecute$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T1M2R35DPI62SI5F1IM6TBKDTP3MAAM0();
                throw obj1;
            }

            .Lambda._cls0(Runnable runnable, CalendarExecutor calendarexecutor)
            {
                arg$1 = ExecutorFactory._cls1.this;
                arg$2 = runnable;
                arg$3 = calendarexecutor;
            }
        }

        super.execute(new .Lambda._cls0(runnable, val$calendarExecutor));
    }

    .Lambda._cls0(ThreadFactory threadfactory, CalendarExecutor calendarexecutor)
    {
        this$0 = final_executorfactory;
        val$calendarExecutor = calendarexecutor;
        super(I.this, threadfactory);
    }
}
