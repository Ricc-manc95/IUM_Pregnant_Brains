// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesScheduledExecutorService

final class val.runnable
    implements Runnable
{

    private final PrimesScheduledExecutorService this$0;
    private final Runnable val$runnable;

    public final void run()
    {
        try
        {
            val$runnable.run();
            return;
        }
        catch (Throwable throwable)
        {
            failureCallback.onFailure(throwable);
            throw throwable;
        }
    }

    ilureCallback()
    {
        this$0 = final_primesscheduledexecutorservice;
        val$runnable = Runnable.this;
        super();
    }
}
