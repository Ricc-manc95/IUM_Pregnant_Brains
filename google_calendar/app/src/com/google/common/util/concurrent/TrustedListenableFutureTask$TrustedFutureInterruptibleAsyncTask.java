// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            InterruptibleTask, AsyncCallable, ListenableFuture, AbstractFuture, 
//            TrustedListenableFutureTask

final class callable extends InterruptibleTask
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

    (AsyncCallable asynccallable)
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
