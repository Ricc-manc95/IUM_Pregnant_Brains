// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ListeningScheduledExecutorServiceWrapper
    implements ListeningScheduledExecutorService
{

    private final ListeningScheduledExecutorService mService;

    protected ListeningScheduledExecutorServiceWrapper(ListeningScheduledExecutorService listeningscheduledexecutorservice)
    {
        mService = listeningscheduledexecutorservice;
    }

    private final List decorate(List list)
    {
        ArrayList arraylist = new ArrayList(list.size());
        for (list = list.iterator(); list.hasNext(); arraylist.add(decorate((Future)list.next()))) { }
        return arraylist;
    }

    public boolean awaitTermination(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return mService.awaitTermination(l, timeunit);
    }

    protected ListenableFuture decorate(ListenableFuture listenablefuture)
    {
        return listenablefuture;
    }

    protected ListenableScheduledFuture decorate(ListenableScheduledFuture listenablescheduledfuture)
    {
        return listenablescheduledfuture;
    }

    protected Future decorate(Future future)
    {
        return future;
    }

    public void execute(Runnable runnable)
    {
        mService.execute(runnable);
    }

    public List invokeAll(Collection collection)
        throws InterruptedException
    {
        return decorate(mService.invokeAll(collection));
    }

    public List invokeAll(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException
    {
        return decorate(mService.invokeAll(collection, l, timeunit));
    }

    public Object invokeAny(Collection collection)
        throws InterruptedException, ExecutionException
    {
        return mService.invokeAny(collection);
    }

    public Object invokeAny(Collection collection, long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return mService.invokeAny(collection, l, timeunit);
    }

    public boolean isShutdown()
    {
        return mService.isShutdown();
    }

    public boolean isTerminated()
    {
        return mService.isTerminated();
    }

    public final ListenableScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return decorate(mService.schedule(runnable, l, timeunit));
    }

    public final ListenableScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return decorate(mService.schedule(callable, l, timeunit));
    }

    public ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return decorate(mService.schedule(runnable, l, timeunit));
    }

    public ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return decorate(mService.schedule(callable, l, timeunit));
    }

    public final ListenableScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return decorate(mService.scheduleAtFixedRate(runnable, l, l1, timeunit));
    }

    public ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return decorate(mService.scheduleAtFixedRate(runnable, l, l1, timeunit));
    }

    public final ListenableScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return decorate(mService.scheduleWithFixedDelay(runnable, l, l1, timeunit));
    }

    public ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return decorate(mService.scheduleWithFixedDelay(runnable, l, l1, timeunit));
    }

    public void shutdown()
    {
        mService.shutdown();
    }

    public List shutdownNow()
    {
        return mService.shutdownNow();
    }

    public final ListenableFuture submit(Runnable runnable)
    {
        return decorate(mService.submit(runnable));
    }

    public final ListenableFuture submit(Runnable runnable, Object obj)
    {
        return decorate(mService.submit(runnable, obj));
    }

    public final ListenableFuture submit(Callable callable)
    {
        return decorate(mService.submit(callable));
    }

    public Future submit(Runnable runnable)
    {
        return decorate(mService.submit(runnable));
    }

    public Future submit(Runnable runnable, Object obj)
    {
        return decorate(mService.submit(runnable, obj));
    }

    public Future submit(Callable callable)
    {
        return decorate(mService.submit(callable));
    }
}
