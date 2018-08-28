// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.Future;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ListeningScheduledExecutorServiceWrapper

final class WatchDogListeningScheduledExecutorService extends ListeningScheduledExecutorServiceWrapper
{

    WatchDogListeningScheduledExecutorService(ListeningScheduledExecutorService listeningscheduledexecutorservice)
    {
        super(listeningscheduledexecutorservice);
    }

    protected final ListenableFuture decorate(ListenableFuture listenablefuture)
    {
        return new WatchDogListenableFuture(listenablefuture);
    }

    protected final ListenableScheduledFuture decorate(ListenableScheduledFuture listenablescheduledfuture)
    {
        return new WatchDogListenableScheduledFuture(listenablescheduledfuture);
    }

    protected final Future decorate(Future future)
    {
        throw new UnsupportedOperationException();
    }

    private class WatchDogListenableFuture extends ListenableFutureWrapper
    {
        private class WatchDogFutureCallback
            implements FutureCallback
        {

            private final UnhandledThrowableReference reference;

            public final void onFailure(Throwable throwable)
            {
                if (throwable instanceof CancellationException)
                {
                    return;
                } else
                {
                    reference.setThrowable(Thread.currentThread(), throwable);
                    return;
                }
            }

            public final void onSuccess(Object obj)
            {
            }

            WatchDogFutureCallback(UnhandledThrowableReference unhandledthrowablereference)
            {
                reference = unhandledthrowablereference;
            }
        }


        private UnhandledThrowableReference reference;

        private final void clearThrowable()
        {
            this;
            JVM INSTR monitorenter ;
            if (reference != null)
            {
                reference.clearThrowable();
                reference = null;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final Object get()
            throws InterruptedException, ExecutionException
        {
            clearThrowable();
            return super.get();
        }

        public final Object get(long l, TimeUnit timeunit)
            throws InterruptedException, ExecutionException, TimeoutException
        {
            clearThrowable();
            return super.get(l, timeunit);
        }

        WatchDogListenableFuture(ListenableFuture listenablefuture)
        {
            super(listenablefuture);
            reference = new UnhandledThrowableReference(listenablefuture);
            WatchDogFutureCallback watchdogfuturecallback = new WatchDogFutureCallback(reference);
            com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            if (watchdogfuturecallback == null)
            {
                throw new NullPointerException();
            } else
            {
                listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, watchdogfuturecallback), directexecutor);
                return;
            }
        }
    }


    private class WatchDogListenableScheduledFuture extends ListenableScheduledFutureWrapper
    {

        private UnhandledThrowableReference reference;

        private final void clearThrowable()
        {
            this;
            JVM INSTR monitorenter ;
            if (reference != null)
            {
                reference.clearThrowable();
                reference = null;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final Object get()
            throws InterruptedException, ExecutionException
        {
            clearThrowable();
            return super.get();
        }

        public final Object get(long l, TimeUnit timeunit)
            throws InterruptedException, ExecutionException, TimeoutException
        {
            clearThrowable();
            return super.get(l, timeunit);
        }

        WatchDogListenableScheduledFuture(ListenableScheduledFuture listenablescheduledfuture)
        {
            super(listenablescheduledfuture);
            reference = new UnhandledThrowableReference(listenablescheduledfuture);
            WatchDogFutureCallback watchdogfuturecallback = new WatchDogFutureCallback(reference);
            com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            if (watchdogfuturecallback == null)
            {
                throw new NullPointerException();
            } else
            {
                listenablescheduledfuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablescheduledfuture, watchdogfuturecallback), directexecutor);
                return;
            }
        }
    }

}
