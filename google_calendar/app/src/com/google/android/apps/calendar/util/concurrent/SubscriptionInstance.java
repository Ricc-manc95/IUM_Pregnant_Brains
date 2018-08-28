// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Executor;

public final class SubscriptionInstance
    implements AsyncFunction
{

    private final Consumer cancellationCallback;
    private boolean cancelled;
    private final Executor executor;
    public final Function function;
    public final Set pending = Collections.newSetFromMap(new IdentityHashMap());

    SubscriptionInstance(Function function1, Executor executor1, Consumer consumer)
    {
        function = function1;
        executor = executor1;
        cancellationCallback = consumer;
    }

    public final ListenableFuture apply(Object obj)
    {
label0:
        {
            synchronized (pending)
            {
                if (!cancelled)
                {
                    break label0;
                }
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateCancelledFuture();
            }
            return ((ListenableFuture) (obj));
        }
        class .Lambda._cls1
            implements Callable
        {

            private final SubscriptionInstance arg$1;
            private final Object arg$2;

            public final Object call()
            {
                SubscriptionInstance subscriptioninstance = arg$1;
                Object obj1 = arg$2;
                return subscriptioninstance.function.apply(obj1);
            }

            .Lambda._cls1(Object obj)
            {
                arg$1 = SubscriptionInstance.this;
                arg$2 = obj;
            }
        }

        obj = new ListenableFutureTask(new .Lambda._cls1(obj));
        class .Lambda._cls2
            implements Runnable
        {

            private final SubscriptionInstance arg$1;
            private final ListenableFutureTask arg$2;

            public final void run()
            {
                SubscriptionInstance subscriptioninstance = arg$1;
                ListenableFutureTask listenablefuturetask = arg$2;
                synchronized (subscriptioninstance.pending)
                {
                    subscriptioninstance.pending.remove(listenablefuturetask);
                }
                return;
                exception;
                set1;
                JVM INSTR monitorexit ;
                throw exception;
            }

            .Lambda._cls2(ListenableFutureTask listenablefuturetask)
            {
                arg$1 = SubscriptionInstance.this;
                arg$2 = listenablefuturetask;
            }
        }

        .Lambda._cls2 _lcls2 = new .Lambda._cls2(((ListenableFutureTask) (obj)));
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        ((ListenableFutureTask) (obj)).executionList.add(_lcls2, directexecutor);
        pending.add(obj);
        executor.execute(((Runnable) (obj)));
        set;
        JVM INSTR monitorexit ;
        return ((ListenableFuture) (obj));
        obj;
        set;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public final void cancel(boolean flag)
    {
label0:
        {
            synchronized (pending)
            {
                if (!cancelled)
                {
                    break label0;
                }
            }
            return;
        }
        ArrayList arraylist;
        int j;
        cancelled = true;
        arraylist = (ArrayList)new ArrayList(pending);
        j = arraylist.size();
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = arraylist.get(i);
        i++;
        ((ListenableFutureTask)obj).cancel(flag);
        if (true) goto _L2; else goto _L1
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        pending.clear();
        set;
        JVM INSTR monitorexit ;
        if (cancellationCallback != null)
        {
            cancellationCallback.accept(this);
        }
        return;
    }
}
