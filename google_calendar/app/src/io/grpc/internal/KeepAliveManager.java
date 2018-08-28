// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            LogExceptionRunnable

public final class KeepAliveManager
{

    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private final boolean keepAliveDuringTransportIdle;
    public final KeepAlivePinger keepAlivePinger;
    private long keepAliveTimeInNanos;
    public long keepAliveTimeoutInNanos;
    public long nextKeepaliveTime;
    public ScheduledFuture pingFuture;
    public final ScheduledExecutorService scheduler;
    public final Runnable sendPing;
    public final Runnable shutdown;
    public ScheduledFuture shutdownFuture;
    private int state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
    public final Ticker ticker;

    public KeepAliveManager(KeepAlivePinger keepalivepinger, ScheduledExecutorService scheduledexecutorservice, long l, long l1, boolean flag)
    {
        this(keepalivepinger, scheduledexecutorservice, ((Ticker) (SYSTEM_TICKER)), l, l1, flag);
    }

    private KeepAliveManager(KeepAlivePinger keepalivepinger, ScheduledExecutorService scheduledexecutorservice, Ticker ticker1, long l, long l1, 
            boolean flag)
    {
        state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.IDLE$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        shutdown = new LogExceptionRunnable(new _cls1());
        sendPing = new LogExceptionRunnable(new _cls2());
        if (keepalivepinger == null)
        {
            throw new NullPointerException(String.valueOf("keepAlivePinger"));
        }
        keepAlivePinger = (KeepAlivePinger)keepalivepinger;
        if (scheduledexecutorservice == null)
        {
            throw new NullPointerException(String.valueOf("scheduler"));
        }
        scheduler = (ScheduledExecutorService)scheduledexecutorservice;
        if (ticker1 == null)
        {
            throw new NullPointerException(String.valueOf("ticker"));
        } else
        {
            ticker = (Ticker)ticker1;
            keepAliveTimeInNanos = l;
            keepAliveTimeoutInNanos = l1;
            keepAliveDuringTransportIdle = flag;
            nextKeepaliveTime = ticker1.read() + l;
            return;
        }
    }

    public final void onDataReceived()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        nextKeepaliveTime = ticker.read() + keepAliveTimeInNanos;
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L2; else goto _L1
_L1:
        state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_DELAYED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 && state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.IDLE_AND_PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L4; else goto _L3
_L3:
        if (shutdownFuture != null)
        {
            shutdownFuture.cancel(false);
        }
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.IDLE_AND_PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.IDLE$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
          goto _L4
        Exception exception;
        exception;
        throw exception;
        state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        if (pingFuture == null)
        {
            flag = true;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        throw new IllegalStateException(String.valueOf("There should be no outstanding pingFuture"));
        pingFuture = scheduler.schedule(sendPing, keepAliveTimeInNanos, TimeUnit.NANOSECONDS);
          goto _L4
    }

    public final void onTransportActive()
    {
        this;
        JVM INSTR monitorenter ;
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.IDLE$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L2; else goto _L1
_L1:
        state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        if (pingFuture == null)
        {
            pingFuture = scheduler.schedule(sendPing, nextKeepaliveTime - ticker.read(), TimeUnit.NANOSECONDS);
        }
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 == android.support.v4.content.ModernAsyncTask.Status.IDLE_AND_PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
        {
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        }
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void onTransportIdle()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = keepAliveDuringTransportIdle;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 == android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 || state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 == android.support.v4.content.ModernAsyncTask.Status.PING_DELAYED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
        {
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.IDLE$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        }
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 == android.support.v4.content.ModernAsyncTask.Status.PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
        {
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.IDLE_AND_PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void onTransportStarted()
    {
        this;
        JVM INSTR monitorenter ;
        if (keepAliveDuringTransportIdle)
        {
            onTransportActive();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onTransportTermination()
    {
        this;
        JVM INSTR monitorenter ;
        if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
        {
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
            if (shutdownFuture != null)
            {
                shutdownFuture.cancel(false);
            }
            if (pingFuture != null)
            {
                pingFuture.cancel(false);
                pingFuture = null;
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static 
    {
        TimeUnit.SECONDS.toNanos(10L);
        TimeUnit.MILLISECONDS.toNanos(10L);
    }



/*
    static int access$102$5166IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI7D66IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI4H9N8OBKCKTIIJ39DSNMESJGCCNMIRJKCLP6SOBC5T5MAPBG85M6ITJ59LGMSOB7CLP28KRKC5Q6AEO_0(KeepAliveManager keepalivemanager, int i)
    {
        keepalivemanager.state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = i;
        return i;
    }

*/

    private class _cls1
        implements Runnable
    {

        private final KeepAliveManager this$0;

        public final void run()
        {
            boolean flag = false;
            KeepAliveManager keepalivemanager = KeepAliveManager.this;
            keepalivemanager;
            JVM INSTR monitorenter ;
            if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 == android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0)
            {
                break MISSING_BLOCK_LABEL_35;
            }
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
            flag = true;
            keepalivemanager;
            JVM INSTR monitorexit ;
            if (flag)
            {
                keepAlivePinger.onPingTimeout();
            }
            return;
            Exception exception;
            exception;
            keepalivemanager;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1()
        {
            this$0 = KeepAliveManager.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final KeepAliveManager this$0;

        public final void run()
        {
            boolean flag1;
            pingFuture = null;
            flag1 = false;
            KeepAliveManager keepalivemanager = KeepAliveManager.this;
            keepalivemanager;
            JVM INSTR monitorenter ;
            if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L2; else goto _L1
_L1:
            boolean flag = true;
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_SENT$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
            shutdownFuture = scheduler.schedule(shutdown, keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
_L4:
            keepalivemanager;
            JVM INSTR monitorexit ;
            if (flag)
            {
                keepAlivePinger.ping();
            }
            return;
_L2:
            flag = flag1;
            if (state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.PING_DELAYED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L4; else goto _L3
_L3:
            pingFuture = scheduler.schedule(sendPing, nextKeepaliveTime - ticker.read(), TimeUnit.NANOSECONDS);
            state$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.PING_SCHEDULED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0;
            flag = flag1;
              goto _L4
            Exception exception;
            exception;
            keepalivemanager;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls2()
        {
            this$0 = KeepAliveManager.this;
            super();
        }
    }


    private class KeepAlivePinger
    {

        public abstract void onPingTimeout();

        public abstract void ping();
    }


    private class Ticker
    {

        public abstract long read();

        Ticker()
        {
        }
    }


    private class SystemTicker extends Ticker
    {

        public final long read()
        {
            return System.nanoTime();
        }

        SystemTicker()
        {
        }
    }

}
