// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThrottlingExecutor
    implements Executor
{

    private final long delayMs;
    private final ScheduledExecutorService executor;
    public final AtomicInteger requestCount = new AtomicInteger();

    public ThrottlingExecutor(ScheduledExecutorService scheduledexecutorservice, long l)
    {
        executor = scheduledexecutorservice;
        delayMs = l;
    }

    public final void execute(Runnable runnable)
    {
        int i = requestCount.incrementAndGet();
        class .Lambda._cls0
            implements Runnable
        {

            private final ThrottlingExecutor arg$1;
            private final int arg$2;
            private final Runnable arg$3;

            public final void run()
            {
                ThrottlingExecutor throttlingexecutor = arg$1;
                int j = arg$2;
                Runnable runnable1 = arg$3;
                if (j == throttlingexecutor.requestCount.get())
                {
                    runnable1.run();
                }
            }

            .Lambda._cls0(int i, Runnable runnable)
            {
                arg$1 = ThrottlingExecutor.this;
                arg$2 = i;
                arg$3 = runnable;
            }
        }

        executor.schedule(new .Lambda._cls0(i, runnable), delayMs, TimeUnit.MILLISECONDS);
    }
}
