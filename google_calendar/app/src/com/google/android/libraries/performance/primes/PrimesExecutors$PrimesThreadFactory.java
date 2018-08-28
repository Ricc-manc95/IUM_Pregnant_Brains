// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class prefix
    implements ThreadFactory
{

    private final AtomicInteger count;
    private final String prefix;
    public final int priority;

    public final Thread newThread(final Runnable runnable)
    {
        class _cls1
            implements Runnable
        {

            private final PrimesExecutors.PrimesThreadFactory this$0;
            private final Runnable val$runnable;

            public final void run()
            {
                if (priority != 0)
                {
                    Process.setThreadPriority(priority);
                }
                runnable.run();
            }

            _cls1()
            {
                this$0 = PrimesExecutors.PrimesThreadFactory.this;
                runnable = runnable1;
                super();
            }
        }

        runnable = new _cls1();
        String s = prefix;
        int i = count.getAndIncrement();
        runnable = new Thread(runnable, (new StringBuilder(String.valueOf(s).length() + 12)).append(s).append("-").append(i).toString());
        if (runnable.isDaemon())
        {
            runnable.setDaemon(false);
        }
        return runnable;
    }

    _cls1(int i)
    {
        this("Primes", i);
    }

    <init>(String s, int i)
    {
        count = new AtomicInteger(1);
        priority = i;
        prefix = s;
    }
}
