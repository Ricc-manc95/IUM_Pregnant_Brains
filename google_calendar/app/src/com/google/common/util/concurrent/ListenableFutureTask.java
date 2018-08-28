// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

// Referenced classes of package com.google.common.util.concurrent:
//            ListenableFuture, ExecutionList

public final class ListenableFutureTask extends FutureTask
    implements ListenableFuture
{

    public final ExecutionList executionList = new ExecutionList();

    public ListenableFutureTask(Callable callable)
    {
        super(callable);
    }

    public final void addListener(Runnable runnable, Executor executor)
    {
        ExecutionList executionlist;
        executionlist = executionList;
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Runnable was null."));
        }
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("Executor was null."));
        }
        executionlist;
        JVM INSTR monitorenter ;
        if (executionlist.executed)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        executionlist.runnables = new ExecutionList.RunnableExecutorPair(runnable, executor, executionlist.runnables);
        executionlist;
        JVM INSTR monitorexit ;
        return;
        executionlist;
        JVM INSTR monitorexit ;
        ExecutionList.executeListener(runnable, executor);
        return;
        runnable;
        executionlist;
        JVM INSTR monitorexit ;
        throw runnable;
    }

    protected final void done()
    {
        ExecutionList.RunnableExecutorPair runnableexecutorpair;
label0:
        {
            runnableexecutorpair = null;
            synchronized (executionList)
            {
                if (!((ExecutionList) (obj1)).executed)
                {
                    break label0;
                }
            }
            return;
        }
        Object obj;
        obj1.executed = true;
        obj = ((ExecutionList) (obj1)).runnables;
        obj1.runnables = null;
        obj1;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        do
        {
            obj1 = runnableexecutorpair;
            if (obj == null)
            {
                break;
            }
            obj1 = ((ExecutionList.RunnableExecutorPair) (obj)).next;
            obj.next = runnableexecutorpair;
            runnableexecutorpair = ((ExecutionList.RunnableExecutorPair) (obj));
            obj = obj1;
        } while (true);
        for (; obj1 != null; obj1 = ((ExecutionList.RunnableExecutorPair) (obj1)).next)
        {
            ExecutionList.executeListener(((ExecutionList.RunnableExecutorPair) (obj1)).runnable, ((ExecutionList.RunnableExecutorPair) (obj1)).executor);
        }

        return;
    }
}
