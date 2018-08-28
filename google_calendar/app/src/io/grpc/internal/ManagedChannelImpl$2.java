// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, InUseStateAggregator, Channelz, WithLogId, 
//            LogId, ObjectPool, ClientTransportFactory

final class this._cls0
    implements t.Listener
{

    private final ManagedChannelImpl this$0;

    public final void transportInUse(boolean flag)
    {
        inUseStateAggregator.updateObjectInUse(delayedTransport, flag);
    }

    public final void transportReady()
    {
    }

    public final void transportShutdown(Status status)
    {
        if (!shutdown.get())
        {
            throw new IllegalStateException(String.valueOf("Channel must have been shut down"));
        } else
        {
            return;
        }
    }

    public final void transportTerminated()
    {
        if (!shutdown.get())
        {
            throw new IllegalStateException(String.valueOf("Channel must have been shut down"));
        }
        terminating = true;
        shutdownNameResolverAndLoadBalancer(false);
        maybeShutdownNowSubchannels();
        ManagedChannelImpl managedchannelimpl = ManagedChannelImpl.this;
        if (!managedchannelimpl.terminated && managedchannelimpl.shutdown.get() && managedchannelimpl.subchannels.isEmpty() && managedchannelimpl.oobChannels.isEmpty())
        {
            ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "maybeTerminateChannel", "[{0}] Terminated", managedchannelimpl.logId);
            managedchannelimpl.channelz.rootChannels.remove(Long.valueOf(managedchannelimpl.getLogId().id));
            managedchannelimpl.terminated = true;
            managedchannelimpl.terminatedLatch.countDown();
            managedchannelimpl.executorPool.returnObject(managedchannelimpl.executor);
            managedchannelimpl.transportFactory.close();
        }
    }

    y()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
