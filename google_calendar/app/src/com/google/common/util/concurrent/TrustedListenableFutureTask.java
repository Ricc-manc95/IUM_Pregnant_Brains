// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, InterruptibleTask, AsyncCallable

public final class TrustedListenableFutureTask extends AbstractFuture.TrustedFuture
    implements RunnableFuture
{

    private volatile InterruptibleTask task;

    public TrustedListenableFutureTask(AsyncCallable asynccallable)
    {
        task = new TrustedFutureInterruptibleAsyncTask(asynccallable);
    }

    TrustedListenableFutureTask(Callable callable)
    {
        task = new TrustedFutureInterruptibleTask(callable);
    }

    protected final void afterDone()
    {
        super.afterDone();
        Object obj = super.value;
        boolean flag;
        if ((obj instanceof AbstractFuture.Cancellation) && ((AbstractFuture.Cancellation)obj).wasInterrupted)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            InterruptibleTask interruptibletask = task;
            if (interruptibletask != null)
            {
                interruptibletask.interruptTask();
            }
        }
        task = null;
    }

    protected final String pendingToString()
    {
        Object obj = task;
        if (obj != null)
        {
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(obj).length() + 7)).append("task=[").append(((String) (obj))).append("]").toString();
        } else
        {
            return super.pendingToString();
        }
    }

    public final void run()
    {
        InterruptibleTask interruptibletask = task;
        if (interruptibletask != null)
        {
            interruptibletask.run();
        }
        task = null;
    }

    private class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask
    {

        private final AsyncCallable callable;
        private final TrustedListenableFutureTask this$0;

        final void afterRanInterruptibly(Object obj, Throwable throwable)
        {
            obj = (ListenableFuture)obj;
            if (throwable == null)
            {
                setFuture(((ListenableFuture) (obj)));
                return;
            } else
            {
                setException(throwable);
                return;
            }
        }

        final boolean isDone()
        {
            return AbstractFuture.this.isDone();
        }

        final Object runInterruptibly()
            throws Exception
        {
            ListenableFuture listenablefuture = callable.call();
            if (listenablefuture == null)
            {
                throw new NullPointerException(String.valueOf("AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)?"));
            } else
            {
                return (ListenableFuture)listenablefuture;
            }
        }

        final String toPendingString()
        {
            return callable.toString();
        }

        TrustedFutureInterruptibleAsyncTask(AsyncCallable asynccallable)
        {
            this$0 = TrustedListenableFutureTask.this;
            super();
            if (asynccallable == null)
            {
                throw new NullPointerException();
            } else
            {
                callable = (AsyncCallable)asynccallable;
                return;
            }
        }
    }


    private class TrustedFutureInterruptibleTask extends InterruptibleTask
    {

        private final Callable callable;
        private final TrustedListenableFutureTask this$0;

        final void afterRanInterruptibly(Object obj, Throwable throwable)
        {
            if (throwable == null)
            {
                set(obj);
                return;
            } else
            {
                setException(throwable);
                return;
            }
        }

        final boolean isDone()
        {
            return AbstractFuture.this.isDone();
        }

        final Object runInterruptibly()
            throws Exception
        {
            return callable.call();
        }

        final String toPendingString()
        {
            return callable.toString();
        }

        TrustedFutureInterruptibleTask(Callable callable1)
        {
            this$0 = TrustedListenableFutureTask.this;
            super();
            if (callable1 == null)
            {
                throw new NullPointerException();
            } else
            {
                callable = (Callable)callable1;
                return;
            }
        }
    }

}
