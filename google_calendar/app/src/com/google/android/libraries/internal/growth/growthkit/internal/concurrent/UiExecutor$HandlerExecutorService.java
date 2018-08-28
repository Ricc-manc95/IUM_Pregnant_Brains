// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public final class mHandler extends AbstractExecutorService
{

    private final Handler mHandler;

    public final boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }

    public final void execute(Runnable runnable)
    {
        mHandler.post(runnable);
    }

    public final boolean isShutdown()
    {
        return false;
    }

    public final boolean isTerminated()
    {
        return false;
    }

    public final void shutdown()
    {
        throw new UnsupportedOperationException();
    }

    public final List shutdownNow()
    {
        throw new UnsupportedOperationException();
    }

    public final Future submit(Runnable runnable)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Task must not be null"));
        } else
        {
            return submit(runnable, null);
        }
    }

    public final Future submit(Runnable runnable, Object obj)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Task must not be null"));
        } else
        {
            return submit(Executors.callable(runnable, obj));
        }
    }

    public final Future submit(Callable callable)
    {
        if (callable == null)
        {
            throw new NullPointerException(String.valueOf("Task must not be null"));
        }
        callable = new FutureTask(callable);
        if (Looper.myLooper() == mHandler.getLooper())
        {
            callable.run();
            return callable;
        } else
        {
            mHandler.post(callable);
            return callable;
        }
    }

    public (Handler handler)
    {
        mHandler = handler;
    }
}
