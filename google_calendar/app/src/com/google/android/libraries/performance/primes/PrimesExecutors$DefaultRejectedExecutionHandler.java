// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.util.Log;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

final class 
    implements RejectedExecutionHandler
{

    public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadpoolexecutor)
    {
        runnable = String.valueOf(runnable);
        runnable = (new StringBuilder(String.valueOf(runnable).length() + 30)).append("Service rejected execution of ").append(runnable).toString();
        threadpoolexecutor = ((ThreadPoolExecutor) (new Object[0]));
        if (Log.isLoggable("PrimesExecutors", 3))
        {
            if (threadpoolexecutor.length != 0)
            {
                runnable = String.format(Locale.US, runnable, threadpoolexecutor);
            }
            Log.println(3, "PrimesExecutors", runnable);
        }
    }

    ()
    {
    }
}
