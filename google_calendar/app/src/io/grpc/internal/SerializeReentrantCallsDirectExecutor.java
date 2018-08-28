// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

class SerializeReentrantCallsDirectExecutor
    implements Executor
{

    private static final Logger log = Logger.getLogger(io/grpc/internal/SerializeReentrantCallsDirectExecutor.getName());
    private boolean executing;
    private ArrayDeque taskQueue;

    SerializeReentrantCallsDirectExecutor()
    {
    }

    private final void completeQueuedTasks()
    {
        do
        {
            Object obj = (Runnable)taskQueue.poll();
            if (obj != null)
            {
                try
                {
                    ((Runnable) (obj)).run();
                }
                catch (Throwable throwable)
                {
                    Logger logger = log;
                    Level level = Level.SEVERE;
                    obj = String.valueOf(obj);
                    logger.logp(level, "io.grpc.internal.SerializeReentrantCallsDirectExecutor", "completeQueuedTasks", (new StringBuilder(String.valueOf(obj).length() + 35)).append("Exception while executing runnable ").append(((String) (obj))).toString(), throwable);
                }
            } else
            {
                return;
            }
        } while (true);
    }

    public void execute(Runnable runnable)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("'task' must not be null."));
        }
        if (executing)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        executing = true;
        runnable.run();
        if (taskQueue != null)
        {
            completeQueuedTasks();
        }
        executing = false;
        return;
        Throwable throwable;
        throwable;
        Logger logger = log;
        Level level = Level.SEVERE;
        runnable = String.valueOf(runnable);
        logger.logp(level, "io.grpc.internal.SerializeReentrantCallsDirectExecutor", "execute", (new StringBuilder(String.valueOf(runnable).length() + 35)).append("Exception while executing runnable ").append(runnable).toString(), throwable);
        if (taskQueue != null)
        {
            completeQueuedTasks();
        }
        executing = false;
        return;
        runnable;
        if (taskQueue != null)
        {
            completeQueuedTasks();
        }
        executing = false;
        throw runnable;
        if (taskQueue == null)
        {
            taskQueue = new ArrayDeque(4);
        }
        taskQueue.add(runnable);
        return;
    }

}
