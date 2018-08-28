// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

public final class SerializingExecutor
    implements Executor
{

    public static final Logger logger = new Logger();
    private final Executor executor;
    public final Object internalLock;
    public boolean isWorkerRunning;
    public final boolean passThroughExceptions;
    public final Deque queue;
    private int suspensions;

    public SerializingExecutor(Executor executor1)
    {
        this(executor1, false);
    }

    private SerializingExecutor(Executor executor1, boolean flag)
    {
        queue = new ArrayDeque();
        isWorkerRunning = false;
        suspensions = 0;
        internalLock = new Object();
        if (executor1 == null)
        {
            throw new NullPointerException();
        } else
        {
            executor = (Executor)executor1;
            passThroughExceptions = false;
            return;
        }
    }

    public final void execute(Runnable runnable)
    {
        synchronized (internalLock)
        {
            queue.add(runnable);
        }
        synchronized (internalLock)
        {
            if (queue.peek() != null)
            {
                break MISSING_BLOCK_LABEL_47;
            }
        }
        return;
        runnable;
        obj;
        JVM INSTR monitorexit ;
        throw runnable;
        if (!isWorkerRunning)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        runnable;
        JVM INSTR monitorexit ;
        return;
        exception;
        runnable;
        JVM INSTR monitorexit ;
        throw exception;
        isWorkerRunning = true;
        runnable;
        JVM INSTR monitorexit ;
        executor.execute(new QueueWorker());
        return;
        Exception exception1;
        exception1;
        synchronized (internalLock)
        {
            isWorkerRunning = false;
        }
        throw exception1;
        exception2;
        runnable;
        JVM INSTR monitorexit ;
        throw exception2;
    }


    private class QueueWorker
        implements Runnable
    {

        private final SerializingExecutor this$0;

        public final void run()
        {
_L4:
            Object obj1;
            boolean flag;
            try
            {
                flag = Thread.interrupted();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                synchronized (internalLock)
                {
                    isWorkerRunning = false;
                }
                throw obj1;
            }
            obj = internalLock;
            obj;
            JVM INSTR monitorenter ;
            obj1 = SerializingExecutor.this;
            obj1 = (Runnable)queue.poll();
            if (obj1 != null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            isWorkerRunning = false;
            obj;
            JVM INSTR monitorexit ;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            Thread.currentThread().interrupt();
            return;
            obj;
            JVM INSTR monitorexit ;
            ((Runnable) (obj1)).run();
_L2:
            if (!flag)
            {
                continue; /* Loop/switch isn't completed */
            }
            Thread.currentThread().interrupt();
            continue; /* Loop/switch isn't completed */
            obj1;
            obj;
            JVM INSTR monitorexit ;
            throw obj1;
            obj;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_120;
            }
            Thread.currentThread().interrupt();
            throw obj;
            obj;
            if (passThroughExceptions)
            {
                throw obj;
            }
            SerializingExecutor.logger.e(((Throwable) (obj)), "Exception while executing runnable %s", new Object[] {
                obj1
            });
            if (true) goto _L2; else goto _L1
_L1:
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            if (true) goto _L4; else goto _L3
_L3:
        }

        QueueWorker()
        {
            this$0 = SerializingExecutor.this;
            super();
        }
    }

}
