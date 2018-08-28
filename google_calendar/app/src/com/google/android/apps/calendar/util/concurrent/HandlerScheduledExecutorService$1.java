// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import android.os.Handler;
import android.os.SystemClock;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class val.startMs
    implements ScheduledFuture
{

    private final Handler val$handler;
    private final long val$startMs;
    private final FutureTask val$task;

    public final boolean cancel(boolean flag)
    {
        val$handler.removeCallbacks(val$task);
        return val$task.cancel(flag);
    }

    public final int compareTo(Object obj)
    {
        obj = (Delayed)obj;
        return Long.signum(getDelay(TimeUnit.MILLISECONDS) - ((Delayed) (obj)).getDelay(TimeUnit.MILLISECONDS));
    }

    public final Object get()
        throws InterruptedException, ExecutionException
    {
        return val$task.get();
    }

    public final Object get(long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return val$task.get(l, timeunit);
    }

    public final long getDelay(TimeUnit timeunit)
    {
        return timeunit.convert(val$startMs - SystemClock.uptimeMillis(), TimeUnit.MILLISECONDS);
    }

    public final boolean isCancelled()
    {
        return val$task.isCancelled();
    }

    public final boolean isDone()
    {
        return val$task.isDone();
    }

    ()
    {
        val$handler = handler1;
        val$task = futuretask;
        val$startMs = l;
        super();
    }
}
