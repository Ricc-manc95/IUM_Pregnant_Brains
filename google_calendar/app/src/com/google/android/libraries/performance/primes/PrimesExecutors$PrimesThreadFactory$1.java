// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.Process;

final class val.runnable
    implements Runnable
{

    private final val.runnable this$0;
    private final Runnable val$runnable;

    public final void run()
    {
        if (iority != 0)
        {
            Process.setThreadPriority(iority);
        }
        val$runnable.run();
    }

    ()
    {
        this$0 = final_;
        val$runnable = Runnable.this;
        super();
    }
}
