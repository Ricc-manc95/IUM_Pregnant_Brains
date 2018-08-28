// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesScheduledExecutorService

final class val.callable
    implements Callable
{

    private final PrimesScheduledExecutorService this$0;
    private final Callable val$callable;

    public final Object call()
        throws Exception
    {
        Object obj;
        try
        {
            obj = val$callable.call();
        }
        catch (Throwable throwable)
        {
            failureCallback.onFailure(throwable);
            throw throwable;
        }
        return obj;
    }

    ilureCallback()
    {
        this$0 = final_primesscheduledexecutorservice;
        val$callable = Callable.this;
        super();
    }
}
