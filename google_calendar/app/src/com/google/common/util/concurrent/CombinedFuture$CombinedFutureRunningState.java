// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture, InterruptibleTask, CombinedFuture

final class task extends task
{

    private Task task;
    private final CombinedFuture this$0;

    final void collectOneValue(boolean flag, int i, Object obj)
    {
    }

    final void handleAllCompleted()
    {
        Task task1;
        task1 = task;
        if (task1 == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        task1.listenerExecutor.execute(task1);
_L2:
        return;
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        if (!task1.thrownByExecute) goto _L2; else goto _L1
_L1:
        task1._fld0.setException(rejectedexecutionexception);
        return;
        if (!isDone())
        {
            throw new IllegalStateException();
        }
          goto _L2
    }

    final void interruptTask()
    {
        Task task1 = task;
        if (task1 != null)
        {
            task1.interruptTask();
        }
    }

    final void releaseResourcesAfterFailure()
    {
        super.cesAfterFailure();
        task = null;
    }

    Task(ImmutableCollection immutablecollection, boolean flag, Task task1)
    {
        this$0 = CombinedFuture.this;
        super(CombinedFuture.this, immutablecollection, flag, false);
        task = task1;
    }
}
