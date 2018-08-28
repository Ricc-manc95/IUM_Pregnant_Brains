// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ListenableScheduledFutureWrapper, UnhandledThrowableReference

final class reference extends ListenableScheduledFutureWrapper
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

    (ListenableScheduledFuture listenablescheduledfuture)
    {
        super(listenablescheduledfuture);
        reference = new UnhandledThrowableReference(listenablescheduledfuture);
          = new reference(reference);
        com.google.common.util.concurrent.ack ack = com.google.common.util.concurrent.cheduledFuture.reference;
        if ( == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablescheduledfuture.addListener(new com.google.common.util.concurrent.cheduledFuture.reference(listenablescheduledfuture, ), ack);
            return;
        }
    }
}
