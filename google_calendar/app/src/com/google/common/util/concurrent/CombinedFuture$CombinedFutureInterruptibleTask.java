// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.common.util.concurrent:
//            InterruptibleTask, AbstractFuture, CombinedFuture

abstract class listenerExecutor extends InterruptibleTask
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

    public (Executor executor)
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
