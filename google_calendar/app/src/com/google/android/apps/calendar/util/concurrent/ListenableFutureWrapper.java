// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ListenableFutureWrapper
    implements ListenableFuture
{

    private final ListenableFuture mFuture;

    protected ListenableFutureWrapper(ListenableFuture listenablefuture)
    {
        mFuture = listenablefuture;
    }

    public final void addListener(Runnable runnable, Executor executor)
    {
        mFuture.addListener(runnable, executor);
    }

    public boolean cancel(boolean flag)
    {
        return mFuture.cancel(flag);
    }

    public Object get()
        throws InterruptedException, ExecutionException
    {
        return mFuture.get();
    }

    public Object get(long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        return mFuture.get(l, timeunit);
    }

    public boolean isCancelled()
    {
        return mFuture.isCancelled();
    }

    public boolean isDone()
    {
        return mFuture.isDone();
    }
}
