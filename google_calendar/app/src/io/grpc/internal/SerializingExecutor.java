// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SerializingExecutor
    implements Runnable, Executor
{
    static abstract class AtomicHelper
    {

        public abstract boolean runStateCompareAndSet(SerializingExecutor serializingexecutor, int i, int j);

        public abstract void runStateSet(SerializingExecutor serializingexecutor, int i);

        AtomicHelper()
        {
        }
    }

    static final class FieldUpdaterAtomicHelper extends AtomicHelper
    {

        private final AtomicIntegerFieldUpdater runStateUpdater;

        public final boolean runStateCompareAndSet(SerializingExecutor serializingexecutor, int i, int j)
        {
            return runStateUpdater.compareAndSet(serializingexecutor, 0, -1);
        }

        public final void runStateSet(SerializingExecutor serializingexecutor, int i)
        {
            runStateUpdater.set(serializingexecutor, 0);
        }

        FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater atomicintegerfieldupdater)
        {
            runStateUpdater = atomicintegerfieldupdater;
        }
    }

    static final class SynchronizedAtomicHelper extends AtomicHelper
    {

        public final boolean runStateCompareAndSet(SerializingExecutor serializingexecutor, int i, int j)
        {
            serializingexecutor;
            JVM INSTR monitorenter ;
            if (serializingexecutor.runState != 0)
            {
                break MISSING_BLOCK_LABEL_18;
            }
            serializingexecutor.runState = -1;
            return true;
            serializingexecutor;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            serializingexecutor;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final void runStateSet(SerializingExecutor serializingexecutor, int i)
        {
            serializingexecutor;
            JVM INSTR monitorenter ;
            serializingexecutor.runState = 0;
            serializingexecutor;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            serializingexecutor;
            JVM INSTR monitorexit ;
            throw exception;
        }

        SynchronizedAtomicHelper()
        {
        }
    }


    private static final AtomicHelper atomicHelper = getAtomicHelper();
    private static final Logger log = Logger.getLogger(io/grpc/internal/SerializingExecutor.getName());
    private final Executor executor;
    private final Queue runQueue = new ConcurrentLinkedQueue();
    public volatile int runState;

    public SerializingExecutor(Executor executor1)
    {
        runState = 0;
        if (executor1 == null)
        {
            throw new NullPointerException(String.valueOf("'executor' must not be null."));
        } else
        {
            executor = executor1;
            return;
        }
    }

    private static AtomicHelper getAtomicHelper()
    {
        FieldUpdaterAtomicHelper fieldupdateratomichelper;
        try
        {
            fieldupdateratomichelper = new FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater.newUpdater(io/grpc/internal/SerializingExecutor, "runState"));
        }
        catch (Throwable throwable)
        {
            log.logp(Level.SEVERE, "io.grpc.internal.SerializingExecutor", "getAtomicHelper", "FieldUpdaterAtomicHelper failed", throwable);
            return new SynchronizedAtomicHelper();
        }
        return fieldupdateratomichelper;
    }

    private final void schedule(Runnable runnable)
    {
        if (!atomicHelper.runStateCompareAndSet(this, 0, -1))
        {
            break MISSING_BLOCK_LABEL_22;
        }
        executor.execute(this);
        return;
        Exception exception;
        exception;
        if (runnable != null)
        {
            runQueue.remove(runnable);
        }
        atomicHelper.runStateSet(this, 0);
        throw exception;
    }

    public final void execute(Runnable runnable)
    {
        Queue queue = runQueue;
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("'r' must not be null."));
        } else
        {
            queue.add((Runnable)runnable);
            schedule(runnable);
            return;
        }
    }

    public final void run()
    {
_L1:
        Object obj1 = (Runnable)runQueue.poll();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        ((Runnable) (obj1)).run();
          goto _L1
        Object obj;
        obj;
        Logger logger = log;
        Level level = Level.SEVERE;
        obj1 = String.valueOf(obj1);
        logger.logp(level, "io.grpc.internal.SerializingExecutor", "run", (new StringBuilder(String.valueOf(obj1).length() + 35)).append("Exception while executing runnable ").append(((String) (obj1))).toString(), ((Throwable) (obj)));
          goto _L1
        obj;
        atomicHelper.runStateSet(this, 0);
        throw obj;
        atomicHelper.runStateSet(this, 0);
        if (!runQueue.isEmpty())
        {
            schedule(null);
        }
        return;
    }

}
