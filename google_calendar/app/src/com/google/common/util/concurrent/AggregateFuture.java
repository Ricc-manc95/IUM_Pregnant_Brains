// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, ListenableFuture, AggregateFutureState, Uninterruptibles

class AggregateFuture extends AbstractFuture.TrustedFuture
{
    abstract class RunningState extends AggregateFutureState
        implements Runnable
    {

        public final boolean allMustSucceed;
        private final boolean collectsValues;
        public ImmutableCollection futures;
        private final AggregateFuture this$0;

        private final void handleException(Throwable throwable)
        {
            boolean flag = true;
            if (throwable == null)
            {
                throw new NullPointerException();
            }
            boolean flag1;
            boolean flag2;
            if (allMustSucceed)
            {
                flag2 = setException(throwable);
                boolean flag3;
                boolean flag4;
                if (flag2)
                {
                    releaseResourcesAfterFailure();
                    flag1 = true;
                } else
                {
                    Set set = super.seenExceptions;
                    obj = set;
                    if (set == null)
                    {
                        obj = Collections.newSetFromMap(new ConcurrentHashMap());
                        addInitialException(((Set) (obj)));
                        AggregateFutureState.ATOMIC_HELPER.compareAndSetSeenExceptions(this, null, ((Set) (obj)));
                        obj = super.seenExceptions;
                    }
                    flag1 = AggregateFuture.addCausalChain(((Set) (obj)), throwable);
                }
            } else
            {
                flag1 = true;
                flag2 = false;
            }
            flag3 = throwable instanceof Error;
            flag4 = allMustSucceed;
            if (flag2)
            {
                flag = false;
            }
            if (flag1 & (flag & flag4) | flag3)
            {
                Object obj;
                if (throwable instanceof Error)
                {
                    obj = "Input Future failed with Error";
                } else
                {
                    obj = "Got more than one input Future failure. Logging failures after the first";
                }
                AggregateFuture.logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture$RunningState", "handleException", ((String) (obj)), throwable);
            }
        }

        final void addInitialException(Set set)
        {
            if (!isCancelled())
            {
                AggregateFuture.addCausalChain(set, ((AbstractFuture.Failure)value).exception);
            }
        }

        abstract void collectOneValue(boolean flag, int i, Object obj);

        final void decrementCountAndMaybeComplete()
        {
            boolean flag2 = true;
            boolean flag1 = false;
            int j = AggregateFutureState.ATOMIC_HELPER.decrementAndGetRemainingCount(this);
            boolean flag;
            if (j >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Less than 0 remaining futures"));
            }
            if (j == 0)
            {
                boolean flag3 = collectsValues;
                int i;
                if (!allMustSucceed)
                {
                    i = ((flag2) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (flag3 & i)
                {
                    UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)futures.iterator();
                    for (i = ((flag1) ? 1 : 0); unmodifiableiterator.hasNext(); i++)
                    {
                        handleOneInputDone(i, (ListenableFuture)unmodifiableiterator.next());
                    }

                }
                handleAllCompleted();
            }
        }

        abstract void handleAllCompleted();

        final void handleOneInputDone(int i, Future future)
        {
            boolean flag = false;
            if (allMustSucceed || !isDone() || isCancelled())
            {
                flag = true;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Future was done before all dependencies completed"));
            }
            if (!future.isDone())
            {
                throw new IllegalStateException(String.valueOf("Tried to set value from future which is not done"));
            }
              goto _L1
_L3:
            return;
_L1:
            try
            {
                if (!allMustSucceed)
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (future.isCancelled())
                {
                    runningState = null;
                    cancel(false);
                    return;
                }
                break MISSING_BLOCK_LABEL_121;
            }
            // Misplaced declaration of an exception variable
            catch (Future future)
            {
                handleException(future.getCause());
            }
            // Misplaced declaration of an exception variable
            catch (Future future)
            {
                handleException(future);
                return;
            }
            if (true) goto _L3; else goto _L2
_L2:
            if (!future.isDone())
            {
                throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                    future
                }));
            }
            future = ((Future) (Uninterruptibles.getUninterruptibly(future)));
            if (!collectsValues) goto _L3; else goto _L4
_L4:
            collectOneValue(allMustSucceed, i, future);
            return;
            if (!collectsValues || future.isCancelled()) goto _L3; else goto _L5
_L5:
            boolean flag1 = allMustSucceed;
            if (!future.isDone())
            {
                throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                    future
                }));
            } else
            {
                collectOneValue(flag1, i, Uninterruptibles.getUninterruptibly(future));
                return;
            }
        }

        void interruptTask()
        {
        }

        void releaseResourcesAfterFailure()
        {
            futures = null;
        }

        public final void run()
        {
            boolean flag2 = true;
            boolean flag1 = false;
            int j = AggregateFutureState.ATOMIC_HELPER.decrementAndGetRemainingCount(this);
            boolean flag;
            if (j >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Less than 0 remaining futures"));
            }
            if (j == 0)
            {
                boolean flag3 = collectsValues;
                int i;
                if (!allMustSucceed)
                {
                    i = ((flag2) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (flag3 & i)
                {
                    UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)futures.iterator();
                    for (i = ((flag1) ? 1 : 0); unmodifiableiterator.hasNext(); i++)
                    {
                        handleOneInputDone(i, (ListenableFuture)unmodifiableiterator.next());
                    }

                }
                handleAllCompleted();
            }
        }

        RunningState(ImmutableCollection immutablecollection, boolean flag, boolean flag1)
        {
            this$0 = AggregateFuture.this;
            super(immutablecollection.size());
            if (immutablecollection == null)
            {
                throw new NullPointerException();
            } else
            {
                futures = (ImmutableCollection)immutablecollection;
                allMustSucceed = flag;
                collectsValues = flag1;
                return;
            }
        }
    }


    public static final Logger logger = Logger.getLogger(com/google/common/util/concurrent/AggregateFuture.getName());
    public RunningState runningState;

    AggregateFuture()
    {
    }

    static boolean addCausalChain(Set set, Throwable throwable)
    {
        for (; throwable != null; throwable = throwable.getCause())
        {
            if (!set.add(throwable))
            {
                return false;
            }
        }

        return true;
    }

    protected final void afterDone()
    {
        super.afterDone();
        RunningState runningstate = runningState;
        if (runningstate != null)
        {
            runningState = null;
            ImmutableCollection immutablecollection = runningstate.futures;
            Object obj = super.value;
            boolean flag;
            boolean flag1;
            boolean flag2;
            if ((obj instanceof AbstractFuture.Cancellation) && ((AbstractFuture.Cancellation)obj).wasInterrupted)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                runningstate.interruptTask();
            }
            flag2 = isCancelled();
            if (immutablecollection != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag & flag2)
            {
                for (UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)immutablecollection.iterator(); unmodifiableiterator.hasNext(); ((ListenableFuture)unmodifiableiterator.next()).cancel(flag1)) { }
            }
        }
    }

    final void init(final RunningState final_runningstate)
    {
        runningState = final_runningstate;
        if (!final_runningstate.futures.isEmpty()) goto _L2; else goto _L1
_L1:
        final_runningstate.handleAllCompleted();
_L4:
        return;
_L2:
        if (!final_runningstate.allMustSucceed)
        {
            break; /* Loop/switch isn't completed */
        }
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)final_runningstate.futures.iterator();
        final int index = 0;
        while (unmodifiableiterator.hasNext()) 
        {
            ListenableFuture listenablefuture = (ListenableFuture)unmodifiableiterator.next();
            class RunningState._cls1
                implements Runnable
            {

                private final RunningState this$1;
                private final int val$index;
                private final ListenableFuture val$listenable;

                public final void run()
                {
                    handleOneInputDone(index, listenable);
                    decrementCountAndMaybeComplete();
                    return;
                    Exception exception;
                    exception;
                    decrementCountAndMaybeComplete();
                    throw exception;
                }

            RunningState._cls1()
            {
                this$1 = final_runningstate;
                index = i;
                listenable = ListenableFuture.this;
                super();
            }
            }

            listenablefuture.addListener(listenablefuture. new RunningState._cls1(), MoreExecutors.DirectExecutor.INSTANCE);
            index++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        UnmodifiableIterator unmodifiableiterator1 = (UnmodifiableIterator)final_runningstate.futures.iterator();
        while (unmodifiableiterator1.hasNext()) 
        {
            ((ListenableFuture)unmodifiableiterator1.next()).addListener(final_runningstate, MoreExecutors.DirectExecutor.INSTANCE);
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected final String pendingToString()
    {
        Object obj = runningState;
        if (obj != null)
        {
            if ((obj = ((RunningState) (obj)).futures) != null)
            {
                obj = String.valueOf(obj);
                return (new StringBuilder(String.valueOf(obj).length() + 10)).append("futures=[").append(((String) (obj))).append("]").toString();
            }
        }
        return null;
    }

}
