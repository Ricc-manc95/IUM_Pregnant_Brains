// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class ListenableFutureCache
{

    public ListenableFuture future;
    private final Supplier futureSupplier;
    private ListenableFuture invalidationFuture;
    private final Function invalidationSubscriber;
    private Subscription invalidationSubscription;
    public Object result;
    public final SubscriptionManager subscriptions = new SubscriptionManager();
    public final String tag;

    public ListenableFutureCache(String s, Supplier supplier, Function function)
    {
        tag = s;
        futureSupplier = supplier;
        invalidationSubscriber = function;
    }

    public final ListenableFuture getValueAsync()
    {
        this;
        JVM INSTR monitorenter ;
        if (future != null) goto _L2; else goto _L1
_L1:
        if (Clock.mockedTimestamp <= 0L) goto _L4; else goto _L3
_L3:
        long l = Clock.mockedTimestamp;
_L7:
        Object obj;
        obj = (ListenableFuture)futureSupplier.get();
        future = ((ListenableFuture) (obj));
        class .Lambda._cls1
            implements Runnable
        {

            private final ListenableFutureCache arg$1;
            private final ListenableFuture arg$2;
            private final long arg$3;

            public final void run()
            {
                ListenableFutureCache listenablefuturecache;
                listenablefuturecache = arg$1;
                ListenableFuture listenablefuture = arg$2;
                long l2 = arg$3;
                long l1;
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                LogUtils.d(listenablefuturecache.tag, "Loaded in %s", new Object[] {
                    Long.valueOf(l1 - l2)
                });
                listenablefuturecache;
                JVM INSTR monitorenter ;
                if (listenablefuturecache.future == listenablefuture)
                {
                    break MISSING_BLOCK_LABEL_72;
                }
                listenablefuturecache;
                JVM INSTR monitorexit ;
                return;
                listenablefuturecache.result = listenablefuturecache.future.get();
                Collection collection = (Collection)listenablefuturecache.subscriptions.apply(listenablefuturecache.result);
_L1:
                listenablefuturecache;
                JVM INSTR monitorexit ;
                return;
                Object obj1;
                obj1;
                listenablefuturecache;
                JVM INSTR monitorexit ;
                throw obj1;
                obj1;
                LogUtils.e(listenablefuturecache.tag, ((Throwable) (obj1)), "Unable to load value", new Object[0]);
                listenablefuturecache.future = null;
                  goto _L1
            }

            .Lambda._cls1(ListenableFuture listenablefuture, long l)
            {
                arg$1 = ListenableFutureCache.this;
                arg$2 = listenablefuture;
                arg$3 = l;
            }
        }

        ((ListenableFuture) (obj)).addListener(new .Lambda._cls1(((ListenableFuture) (obj)), l), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
_L8:
        if (!((ListenableFuture) (obj)).isDone()) goto _L6; else goto _L5
_L5:
        this;
        JVM INSTR monitorexit ;
        return ((ListenableFuture) (obj));
_L4:
        l = System.currentTimeMillis();
          goto _L7
_L2:
        obj = future;
          goto _L8
_L6:
        com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture noncancellationpropagatingfuture;
        noncancellationpropagatingfuture = new com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture(((ListenableFuture) (obj)));
        ((ListenableFuture) (obj)).addListener(noncancellationpropagatingfuture, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        obj = noncancellationpropagatingfuture;
          goto _L5
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
          goto _L7
    }

    public final void invalidate()
    {
        this;
        JVM INSTR monitorenter ;
        LogUtils.d(tag, "invalidated", new Object[0]);
        future = null;
        if (invalidationFuture != null)
        {
            invalidationFuture.cancel(false);
        }
        class .Lambda._cls2
            implements AsyncCallable
        {

            private final ListenableFutureCache arg$1;

            public final ListenableFuture call()
            {
                return arg$1.getValueAsync();
            }

            .Lambda._cls2()
            {
                arg$1 = ListenableFutureCache.this;
            }
        }

        Object obj = new .Lambda._cls2();
        TimeUnit timeunit = TimeUnit.MILLISECONDS;
        CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
        obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
        ((AbstractFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures._cls1(calendarexecutor.schedule(((Runnable) (obj)), 50L, timeunit)), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        invalidationFuture = ((ListenableFuture) (obj));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void start()
    {
        this;
        JVM INSTR monitorenter ;
        if (invalidationSubscription == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        LogUtils.e(tag, "Trying to start, but already running.", new Object[0]);
_L2:
        invalidate();
        this;
        JVM INSTR monitorexit ;
        return;
        class .Lambda._cls3
            implements Runnable
        {

            private final ListenableFutureCache arg$1;

            public final void run()
            {
                arg$1.invalidate();
            }

            .Lambda._cls3()
            {
                arg$1 = ListenableFutureCache.this;
            }
        }

        invalidationSubscription = (Subscription)invalidationSubscriber.apply(new .Lambda._cls3());
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void stop()
    {
        this;
        JVM INSTR monitorenter ;
        if (invalidationSubscription != null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        LogUtils.e(tag, "Trying to stop, but not running.", new Object[0]);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        invalidationSubscription.cancel(false);
        invalidationSubscription = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final Subscription subscribe(Consumer consumer, Executor executor, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Subscription subscription = subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(consumer), executor);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        class .Lambda._cls0
            implements Runnable
        {

            private final Consumer arg$1;
            private final Object arg$2;

            public final void run()
            {
                arg$1.accept(arg$2);
            }

            .Lambda._cls0(Consumer consumer, Object obj)
            {
                arg$1 = consumer;
                arg$2 = obj;
            }
        }

        if (result != null)
        {
            executor.execute(new .Lambda._cls0(consumer, result));
        }
        this;
        JVM INSTR monitorexit ;
        return subscription;
        consumer;
        this;
        JVM INSTR monitorexit ;
        throw consumer;
    }
}
