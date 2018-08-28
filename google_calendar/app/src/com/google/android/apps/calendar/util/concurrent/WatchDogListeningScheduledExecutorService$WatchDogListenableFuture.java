// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ListenableFutureWrapper, UnhandledThrowableReference

final class reference extends ListenableFutureWrapper
{

    private UnhandledThrowableReference reference;

    private final void clearThrowable()
    {
        this;
        JVM INSTR monitorenter ;
        if (reference != null)
        {
            reference.clearThrowable();
            reference = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object get()
        throws InterruptedException, ExecutionException
    {
        clearThrowable();
        return super.get();
    }

    public final Object get(long l, TimeUnit timeunit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        clearThrowable();
        return super.get(l, timeunit);
    }

    y(ListenableFuture listenablefuture)
    {
        super(listenablefuture);
        reference = new UnhandledThrowableReference(listenablefuture);
        y y = new nit>(reference);
        com.google.common.util.concurrent.tureCallback turecallback = com.google.common.util.concurrent.stenableFuture.reference;
        if (y == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.stenableFuture.reference(listenablefuture, y), turecallback);
            return;
        }
    }
}
