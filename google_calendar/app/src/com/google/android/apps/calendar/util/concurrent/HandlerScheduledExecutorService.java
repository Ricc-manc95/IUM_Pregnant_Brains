// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import android.os.Handler;
import android.os.SystemClock;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class HandlerScheduledExecutorService extends AbstractExecutorService
    implements ScheduledExecutorService
{

    private final Handler handler;

    HandlerScheduledExecutorService(Handler handler1)
    {
        handler = handler1;
    }

    private final ScheduledFuture schedule(FutureTask futuretask, long l, TimeUnit timeunit)
    {
        l = SystemClock.uptimeMillis() + TimeUnit.MILLISECONDS.convert(l, timeunit);
        timeunit = new _cls1();
        handler.postAtTime(futuretask, l);
        return timeunit;
    }

    public final boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }

    public final void execute(Runnable runnable)
    {
        handler.post(runnable);
    }

    public final boolean isShutdown()
    {
        return false;
    }

    public final boolean isTerminated()
    {
        return false;
    }

    public final ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return schedule(new FutureTask(runnable, null), l, timeunit);
    }

    public final ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return schedule(new FutureTask(callable), l, timeunit);
    }

    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        throw new UnsupportedOperationException();
    }

    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        throw new UnsupportedOperationException();
    }

    public final void shutdown()
    {
        throw new UnsupportedOperationException();
    }

    public final List shutdownNow()
    {
        throw new UnsupportedOperationException();
    }

    private class _cls1
        implements ScheduledFuture
    {

        private final Handler val$handler;
        private final long val$startMs;
        private final FutureTask val$task;

        public final boolean cancel(boolean flag)
        {
            handler.removeCallbacks(task);
            return task.cancel(flag);
        }

        public final int compareTo(Object obj)
        {
            obj = (Delayed)obj;
            return Long.signum(getDelay(TimeUnit.MILLISECONDS) - ((Delayed) (obj)).getDelay(TimeUnit.MILLISECONDS));
        }

        public final Object get()
            throws InterruptedException, ExecutionException
        {
            return task.get();
        }

        public final Object get(long l, TimeUnit timeunit)
            throws InterruptedException, ExecutionException, TimeoutException
        {
            return task.get(l, timeunit);
        }

        public final long getDelay(TimeUnit timeunit)
        {
            return timeunit.convert(startMs - SystemClock.uptimeMillis(), TimeUnit.MILLISECONDS);
        }

        public final boolean isCancelled()
        {
            return task.isCancelled();
        }

        public final boolean isDone()
        {
            return task.isDone();
        }

        _cls1()
        {
            handler = handler1;
            task = futuretask;
            startMs = l;
            super();
        }
    }

}
