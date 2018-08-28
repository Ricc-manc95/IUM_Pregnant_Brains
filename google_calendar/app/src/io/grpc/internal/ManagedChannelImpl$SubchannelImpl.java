// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            AbstractSubchannel, ManagedChannelImpl, InternalSubchannel, ClientTransportFactory, 
//            LogExceptionRunnable, LogId, ClientTransport

final class this._cls0 extends AbstractSubchannel
{

    private ScheduledFuture delayedShutdownTask;
    private final Object shutdownLock = new Object();
    private boolean shutdownRequested;
    public InternalSubchannel subchannel;
    private final ManagedChannelImpl this$0;

    final ClientTransport obtainActiveTransport()
    {
        return subchannel.obtainActiveTransport();
    }

    public final void requestConnection()
    {
        subchannel.obtainActiveTransport();
    }

    public final void shutdown()
    {
        Object obj = shutdownLock;
        obj;
        JVM INSTR monitorenter ;
        if (!shutdownRequested)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        if (!terminating || delayedShutdownTask == null) goto _L2; else goto _L1
_L1:
        delayedShutdownTask.cancel(false);
        delayedShutdownTask = null;
_L3:
        if (terminating)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        class _cls1
            implements Runnable
        {

            private final ManagedChannelImpl.SubchannelImpl this$1;

            public final void run()
            {
                subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
            }

            _cls1()
            {
                this$1 = ManagedChannelImpl.SubchannelImpl.this;
                super();
            }
        }

        delayedShutdownTask = transportFactory.getScheduledExecutorService().schedule(new LogExceptionRunnable(new _cls1()), 5L, TimeUnit.SECONDS);
        return;
_L2:
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        shutdownRequested = true;
          goto _L3
        obj;
        JVM INSTR monitorexit ;
        subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
        return;
    }

    public final String toString()
    {
        return subchannel.logId.toString();
    }

    _cls1(Attributes attributes)
    {
        this$0 = ManagedChannelImpl.this;
        super();
        if (attributes == null)
        {
            throw new NullPointerException(String.valueOf("attrs"));
        } else
        {
            return;
        }
    }
}
