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
//            AggregateFutureState, AggregateFuture, AbstractFuture, ListenableFuture, 
//            Uninterruptibles

abstract class collectsValues extends AggregateFutureState
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
            AggregateFuture.addCausalChain(set, ((n)value).ion);
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

    lper(ImmutableCollection immutablecollection, boolean flag, boolean flag1)
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
