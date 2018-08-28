// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

final class val.executor
    implements rialExecutor
{

    private Runnable active;
    private final ArrayDeque tasks = new ArrayDeque();
    private final Executor val$executor;

    public final void execute(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        class .Lambda._cls0
            implements Runnable
        {

            private final CalendarExecutors._cls1 arg$1;
            private final Runnable arg$2;

            public final void run()
            {
                CalendarExecutors._cls1 _lcls1;
                Runnable runnable1;
                _lcls1 = arg$1;
                runnable1 = arg$2;
                CalendarExecutors.serialExecutorTag.set(_lcls1);
                runnable1.run();
                CalendarExecutors.serialExecutorTag.set(null);
                _lcls1.scheduleNext();
                return;
                Exception exception;
                exception;
                CalendarExecutors.serialExecutorTag.set(null);
                _lcls1.scheduleNext();
                throw exception;
            }

            .Lambda._cls0(Runnable runnable)
            {
                arg$1 = CalendarExecutors._cls1.this;
                arg$2 = runnable;
            }
        }

        tasks.offer(new .Lambda._cls0(runnable));
        if (active == null)
        {
            scheduleNext();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    final void scheduleNext()
    {
        this;
        JVM INSTR monitorenter ;
        Runnable runnable;
        runnable = (Runnable)tasks.poll();
        active = runnable;
        if (runnable == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        val$executor.execute(active);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    .Lambda._cls0()
    {
        val$executor = executor1;
        super();
    }
}
