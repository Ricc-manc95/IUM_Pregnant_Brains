// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.common.util.concurrent:
//            FluentFuture, ListenableFuture

public final class ForwardingFluentFuture extends FluentFuture
{

    private final ListenableFuture _flddelegate;

    public ForwardingFluentFuture(ListenableFuture listenablefuture)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (ListenableFuture)listenablefuture;
            return;
        }
    }

    public final void addListener(Runnable runnable, Executor executor)
    {
        _flddelegate.addListener(runnable, executor);
    }

    public final boolean cancel(boolean flag)
    {
        return _flddelegate.cancel(flag);
    }

    public final Object get()
        throws InterruptedException, ExecutionException
    {
        return _flddelegate.get();
    }

    public final Object get(long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return _flddelegate.get(l, timeunit);
    }

    public final boolean isCancelled()
    {
        return _flddelegate.isCancelled();
    }

    public final boolean isDone()
    {
        return _flddelegate.isDone();
    }
}
