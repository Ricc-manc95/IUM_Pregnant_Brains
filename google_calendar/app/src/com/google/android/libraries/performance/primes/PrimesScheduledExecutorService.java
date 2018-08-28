// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class PrimesScheduledExecutorService
    implements ScheduledExecutorService
{

    private final ScheduledExecutorService executor;
    public final FailureCallback failureCallback;

    PrimesScheduledExecutorService(ScheduledExecutorService scheduledexecutorservice, FailureCallback failurecallback)
    {
        if (scheduledexecutorservice == null)
        {
            throw new NullPointerException();
        }
        executor = (ScheduledExecutorService)scheduledexecutorservice;
        if (failurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            failureCallback = (FailureCallback)failurecallback;
            return;
        }
    }

    private final List wrapAll(Collection collection)
    {
        ArrayList arraylist = new ArrayList();
        for (collection = collection.iterator(); collection.hasNext(); arraylist.add(new _cls2())) { }
        return arraylist;
    }

    public final boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return executor.awaitTermination(l, timeunit);
    }

    public final void execute(final Runnable runnable)
    {
        executor.execute(new _cls1());
    }

    public final List invokeAll(Collection collection)
        throws InterruptedException
    {
        return executor.invokeAll(wrapAll(collection));
    }

    public final List invokeAll(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return executor.invokeAll(wrapAll(collection), l, timeunit);
    }

    public final Object invokeAny(Collection collection)
        throws InterruptedException, ExecutionException
    {
        return executor.invokeAny(wrapAll(collection));
    }

    public final Object invokeAny(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return executor.invokeAny(wrapAll(collection), l, timeunit);
    }

    public final boolean isShutdown()
    {
        return executor.isShutdown();
    }

    public final boolean isTerminated()
    {
        return executor.isTerminated();
    }

    public final ScheduledFuture schedule(final Runnable runnable, long l, TimeUnit timeunit)
    {
        return executor.schedule(new _cls1(), l, timeunit);
    }

    public final ScheduledFuture schedule(final Callable callable, long l, TimeUnit timeunit)
    {
        return executor.schedule(new _cls2(), l, timeunit);
    }

    public final ScheduledFuture scheduleAtFixedRate(final Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return executor.scheduleAtFixedRate(new _cls1(), l, l1, timeunit);
    }

    public final ScheduledFuture scheduleWithFixedDelay(final Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return executor.scheduleWithFixedDelay(new _cls1(), l, l1, timeunit);
    }

    public final void shutdown()
    {
        executor.shutdown();
    }

    public final List shutdownNow()
    {
        return executor.shutdownNow();
    }

    public final Future submit(final Runnable runnable)
    {
        return executor.submit(new _cls1());
    }

    public final Future submit(final Runnable runnable, Object obj)
    {
        return executor.submit(new _cls1(), obj);
    }

    public final Future submit(final Callable callable)
    {
        return executor.submit(new _cls2());
    }

    private class FailureCallback
    {

        public abstract void onFailure(Throwable throwable);
    }


    private class _cls2
        implements Callable
    {

        private final PrimesScheduledExecutorService this$0;
        private final Callable val$callable;

        public final Object call()
            throws Exception
        {
            Object obj;
            try
            {
                obj = callable.call();
            }
            catch (Throwable throwable)
            {
                failureCallback.onFailure(throwable);
                throw throwable;
            }
            return obj;
        }

        _cls2()
        {
            this$0 = PrimesScheduledExecutorService.this;
            callable = callable1;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final PrimesScheduledExecutorService this$0;
        private final Runnable val$runnable;

        public final void run()
        {
            try
            {
                runnable.run();
                return;
            }
            catch (Throwable throwable)
            {
                failureCallback.onFailure(throwable);
                throw throwable;
            }
        }

        _cls1()
        {
            this$0 = PrimesScheduledExecutorService.this;
            runnable = runnable1;
            super();
        }
    }

}
