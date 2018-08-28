// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.common.util.concurrent:
//            ListeningScheduledExecutorService, TrustedListenableFutureTask, ListenableScheduledFuture

public final class delegate extends delegate
    implements ListeningScheduledExecutorService
{

    private final ScheduledExecutorService _flddelegate;

    public final ListenableScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        runnable = new TrustedListenableFutureTask(Executors.callable(runnable, null));
        class ListenableScheduledTask extends ForwardingListenableFuture.SimpleForwardingListenableFuture
            implements ListenableScheduledFuture
        {

            private final ScheduledFuture scheduledDelegate;

            public final boolean cancel(boolean flag)
            {
                boolean flag1 = super.cancel(flag);
                if (flag1)
                {
                    scheduledDelegate.cancel(flag);
                }
                return flag1;
            }

            public final int compareTo(Object obj)
            {
                obj = (Delayed)obj;
                return scheduledDelegate.compareTo(obj);
            }

            public final long getDelay(TimeUnit timeunit1)
            {
                return scheduledDelegate.getDelay(timeunit1);
            }

            public ListenableScheduledTask(ListenableFuture listenablefuture, ScheduledFuture scheduledfuture)
            {
                super(listenablefuture);
                scheduledDelegate = scheduledfuture;
            }
        }

        return new ListenableScheduledTask(runnable, _flddelegate.schedule(runnable, l, timeunit));
    }

    public final ListenableScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        callable = new TrustedListenableFutureTask(callable);
        return new ListenableScheduledTask(callable, _flddelegate.schedule(callable, l, timeunit));
    }

    public final volatile ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
    {
        return schedule(runnable, l, timeunit);
    }

    public final volatile ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
    {
        return schedule(callable, l, timeunit);
    }

    public final ListenableScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        class NeverSuccessfulListenableFutureTask extends AbstractFuture
            implements Runnable
        {

            private final Runnable _flddelegate;

            public final void run()
            {
                try
                {
                    _flddelegate.run();
                    return;
                }
                catch (Throwable throwable)
                {
                    setException(throwable);
                    throw Throwables.propagate(throwable);
                }
            }

            public NeverSuccessfulListenableFutureTask(Runnable runnable)
            {
                if (runnable == null)
                {
                    throw new NullPointerException();
                } else
                {
                    _flddelegate = (Runnable)runnable;
                    return;
                }
            }
        }

        runnable = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(runnable, _flddelegate.scheduleAtFixedRate(runnable, l, l1, timeunit));
    }

    public final volatile ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return scheduleAtFixedRate(runnable, l, l1, timeunit);
    }

    public final ListenableScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        runnable = new NeverSuccessfulListenableFutureTask(runnable);
        return new ListenableScheduledTask(runnable, _flddelegate.scheduleWithFixedDelay(runnable, l, l1, timeunit));
    }

    public final volatile ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
    {
        return scheduleWithFixedDelay(runnable, l, l1, timeunit);
    }

    public NeverSuccessfulListenableFutureTask(ScheduledExecutorService scheduledexecutorservice)
    {
        super(scheduledexecutorservice);
        if (scheduledexecutorservice == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (ScheduledExecutorService)scheduledexecutorservice;
            return;
        }
    }
}
