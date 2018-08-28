// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

class ChannelExecutor
{

    private static final Logger log = Logger.getLogger(io/grpc/internal/ChannelExecutor.getName());
    private boolean draining;
    private final Object lock = new Object();
    private final Queue queue = new ArrayDeque();

    ChannelExecutor()
    {
    }

    final void drain()
    {
        boolean flag = false;
_L2:
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        if (!draining)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        draining = true;
        flag = true;
        Object obj1 = (Runnable)queue.poll();
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        draining = false;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            ((Runnable) (obj1)).run();
        }
        catch (Throwable throwable)
        {
            handleUncaughtThrowable(throwable);
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    final ChannelExecutor executeLater(Runnable runnable)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Queue queue1 = queue;
        if (runnable != null)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        throw new NullPointerException(String.valueOf("runnable is null"));
        runnable;
        obj;
        JVM INSTR monitorexit ;
        throw runnable;
        queue1.add((Runnable)runnable);
        obj;
        JVM INSTR monitorexit ;
        return this;
    }

    void handleUncaughtThrowable(Throwable throwable)
    {
        log.logp(Level.WARNING, "io.grpc.internal.ChannelExecutor", "handleUncaughtThrowable", "Runnable threw exception in ChannelExecutor", throwable);
    }

}
