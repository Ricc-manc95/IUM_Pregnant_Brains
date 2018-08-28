// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.common.util.concurrent:
//            AsyncCallable, ListenableFuture, AbstractFuture, CombinedFuture

final class callable extends 
{

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

    public (AsyncCallable asynccallable, Executor executor)
    {
        this$0 = CombinedFuture.this;
        super(CombinedFuture.this, executor);
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
