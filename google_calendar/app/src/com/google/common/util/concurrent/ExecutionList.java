// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExecutionList
{
    static final class RunnableExecutorPair
    {

        public final Executor executor;
        public RunnableExecutorPair next;
        public final Runnable runnable;

        RunnableExecutorPair(Runnable runnable1, Executor executor1, RunnableExecutorPair runnableexecutorpair)
        {
            runnable = runnable1;
            executor = executor1;
            next = runnableexecutorpair;
        }
    }


    private static final Logger log = Logger.getLogger(com/google/common/util/concurrent/ExecutionList.getName());
    public boolean executed;
    public RunnableExecutorPair runnables;

    public ExecutionList()
    {
    }

    static void executeListener(Runnable runnable, Executor executor)
    {
        try
        {
            executor.execute(runnable);
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            Logger logger = log;
            Level level = Level.SEVERE;
            runnable = String.valueOf(runnable);
            executor = String.valueOf(executor);
            logger.logp(level, "com.google.common.util.concurrent.ExecutionList", "executeListener", (new StringBuilder(String.valueOf(runnable).length() + 57 + String.valueOf(executor).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
            return;
        }
    }

    public final void add(Runnable runnable, Executor executor)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Runnable was null."));
        }
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("Executor was null."));
        }
        this;
        JVM INSTR monitorenter ;
        if (executed)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        runnables = new RunnableExecutorPair(runnable, executor, runnables);
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        executeListener(runnable, executor);
        return;
        runnable;
        this;
        JVM INSTR monitorexit ;
        throw runnable;
    }

}
