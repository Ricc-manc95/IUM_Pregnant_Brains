// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.common.util.concurrent:
//            InterruptibleTask, AbstractFuture, TrustedListenableFutureTask

final class callable extends InterruptibleTask
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

    (Callable callable1)
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
