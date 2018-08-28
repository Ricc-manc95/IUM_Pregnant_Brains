// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class NetworkExecutor
{

    private static volatile Executor networkExecutor;
    private static final Object networkExecutorLock = new Object();

    public static Executor getNetworkExecutor()
    {
        if (networkExecutor == null)
        {
            synchronized (networkExecutorLock)
            {
                if (networkExecutor == null)
                {
                    ThreadPoolExecutor threadpoolexecutor = new ThreadPoolExecutor(1, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new _cls1());
                    networkExecutor = threadpoolexecutor;
                    ((ThreadPoolExecutor)threadpoolexecutor).allowCoreThreadTimeOut(true);
                }
            }
        }
        return networkExecutor;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }


    private class _cls1
        implements ThreadFactory
    {

        private final AtomicInteger threadCount = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable)
        {
            int i = threadCount.getAndIncrement();
            return new Thread(runnable, (new StringBuilder(17)).append("HaTS #").append(i).toString());
        }

        _cls1()
        {
        }
    }

}
