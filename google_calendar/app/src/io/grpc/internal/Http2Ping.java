// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Http2Ping
{

    public static final Logger log = Logger.getLogger(io/grpc/internal/Http2Ping.getName());
    public Map callbacks;
    public boolean completed;
    public final long data;
    public Throwable failureCause;
    public long roundTripTimeNanos;
    private final Stopwatch stopwatch;

    public Http2Ping(long l, Stopwatch stopwatch1)
    {
        callbacks = new LinkedHashMap();
        data = l;
        stopwatch = stopwatch1;
    }

    public static void doExecute(Executor executor, Runnable runnable)
    {
        try
        {
            executor.execute(runnable);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Executor executor)
        {
            log.logp(Level.SEVERE, "io.grpc.internal.Http2Ping", "doExecute", "Failed to execute PingCallback", executor);
        }
    }

    public static void notifyFailed(final ClientTransport.PingCallback callback, Executor executor, final Throwable failureCause)
    {
        callback = new _cls2();
        try
        {
            executor.execute(callback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (final ClientTransport.PingCallback callback)
        {
            log.logp(Level.SEVERE, "io.grpc.internal.Http2Ping", "doExecute", "Failed to execute PingCallback", callback);
        }
    }

    public final boolean complete()
    {
        this;
        JVM INSTR monitorenter ;
        if (!completed)
        {
            break MISSING_BLOCK_LABEL_13;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        Object obj;
        long l;
        completed = true;
        l = stopwatch.elapsed(TimeUnit.NANOSECONDS);
        roundTripTimeNanos = l;
        obj = callbacks;
        callbacks = null;
        this;
        JVM INSTR monitorexit ;
        for (obj = ((Map) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext();)
        {
            Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
            Executor executor = (Executor)((java.util.Map.Entry) (obj1)).getValue();
            obj1 = new _cls1();
            try
            {
                executor.execute(((Runnable) (obj1)));
            }
            catch (Throwable throwable)
            {
                log.logp(Level.SEVERE, "io.grpc.internal.Http2Ping", "doExecute", "Failed to execute PingCallback", throwable);
            }
        }

        break MISSING_BLOCK_LABEL_143;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        return true;
    }


    private class _cls2
        implements Runnable
    {

        private final ClientTransport.PingCallback val$callback;
        private final Throwable val$failureCause;

        public final void run()
        {
            callback.onFailure$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0();
        }

        public _cls2()
        {
            callback = pingcallback;
            failureCause = throwable;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final ClientTransport.PingCallback val$callback;
        private final long val$roundTripTimeNanos;

        public final void run()
        {
            callback.onSuccess$5152ILG_0();
        }

        public _cls1()
        {
            callback = pingcallback;
            roundTripTimeNanos = l;
            super();
        }
    }

}
