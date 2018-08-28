// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.common.util.concurrent:
//            AggregateFuture, AsyncCallable

public final class CombinedFuture extends AggregateFuture
{

    public CombinedFuture(ImmutableCollection immutablecollection, boolean flag, Executor executor, AsyncCallable asynccallable)
    {
        init(new CombinedFutureRunningState(immutablecollection, flag, new AsyncCallableInterruptibleTask(asynccallable, executor)));
    }

    public CombinedFuture(ImmutableCollection immutablecollection, boolean flag, Executor executor, Callable callable)
    {
        init(new CombinedFutureRunningState(immutablecollection, flag, new CallableInterruptibleTask(callable, executor)));
    }

    private class CombinedFutureRunningState extends AggregateFuture.RunningState
    {

        private CombinedFutureInterruptibleTask task;
        private final CombinedFuture this$0;

        final void collectOneValue(boolean flag, int i, Object obj)
        {
        }

        final void handleAllCompleted()
        {
            CombinedFutureInterruptibleTask combinedfutureinterruptibletask;
            combinedfutureinterruptibletask = task;
            if (combinedfutureinterruptibletask == null)
            {
                break MISSING_BLOCK_LABEL_38;
            }
            combinedfutureinterruptibletask.listenerExecutor.execute(combinedfutureinterruptibletask);
_L2:
            return;
            RejectedExecutionException rejectedexecutionexception;
            rejectedexecutionexception;
            if (!combinedfutureinterruptibletask.thrownByExecute) goto _L2; else goto _L1
_L1:
            combinedfutureinterruptibletask._fld0.setException(rejectedexecutionexception);
            return;
            if (!isDone())
            {
                throw new IllegalStateException();
            }
              goto _L2
        }

        final void interruptTask()
        {
            CombinedFutureInterruptibleTask combinedfutureinterruptibletask = task;
            if (combinedfutureinterruptibletask != null)
            {
                combinedfutureinterruptibletask.interruptTask();
            }
        }

        final void releaseResourcesAfterFailure()
        {
            super.releaseResourcesAfterFailure();
            task = null;
        }

        CombinedFutureRunningState(ImmutableCollection immutablecollection, boolean flag, CombinedFutureInterruptibleTask combinedfutureinterruptibletask)
        {
            this$0 = CombinedFuture.this;
            super(CombinedFuture.this, immutablecollection, flag, false);
            task = combinedfutureinterruptibletask;
        }
    }


    private class AsyncCallableInterruptibleTask extends CombinedFutureInterruptibleTask
    {
        private class CombinedFutureInterruptibleTask extends InterruptibleTask
        {

            public final Executor listenerExecutor;
            public final CombinedFuture this$0;
            public boolean thrownByExecute;

            final void afterRanInterruptibly(Object obj, Throwable throwable)
            {
                if (throwable != null)
                {
                    if (throwable instanceof ExecutionException)
                    {
                        setException(throwable.getCause());
                        return;
                    }
                    if (throwable instanceof CancellationException)
                    {
                        cancel(false);
                        return;
                    } else
                    {
                        setException(throwable);
                        return;
                    }
                } else
                {
                    setValue(obj);
                    return;
                }
            }

            final boolean isDone()
            {
                return AbstractFuture.this.isDone();
            }

            abstract void setValue(Object obj);

            public CombinedFutureInterruptibleTask(Executor executor)
            {
                this$0 = CombinedFuture.this;
                super();
                thrownByExecute = true;
                if (executor == null)
                {
                    throw new NullPointerException();
                } else
                {
                    listenerExecutor = (Executor)executor;
                    return;
                }
            }
        }


        private final AsyncCallable callable;
        private final CombinedFuture this$0;

        final Object runInterruptibly()
            throws Exception
        {
            thrownByExecute = false;
            ListenableFuture listenablefuture = callable.call();
            if (listenablefuture == null)
            {
                throw new NullPointerException(String.valueOf("AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)?"));
            } else
            {
                return (ListenableFuture)listenablefuture;
            }
        }

        final void setValue(Object obj)
        {
            obj = (ListenableFuture)obj;
            setFuture(((ListenableFuture) (obj)));
        }

        final String toPendingString()
        {
            return callable.toString();
        }

        public AsyncCallableInterruptibleTask(AsyncCallable asynccallable, Executor executor)
        {
            this$0 = CombinedFuture.this;
            super(executor);
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


    private class CallableInterruptibleTask extends CombinedFutureInterruptibleTask
    {

        private final Callable callable;
        private final CombinedFuture this$0;

        final Object runInterruptibly()
            throws Exception
        {
            thrownByExecute = false;
            return callable.call();
        }

        final void setValue(Object obj)
        {
            set(obj);
        }

        final String toPendingString()
        {
            return callable.toString();
        }

        public CallableInterruptibleTask(Callable callable1, Executor executor)
        {
            this$0 = CombinedFuture.this;
            super(executor);
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
