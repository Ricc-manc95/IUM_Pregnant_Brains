// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.NameResolver;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, InUseStateAggregator, Channelz, WithLogId, 
//            LogId, ObjectPool, ClientTransportFactory, InternalSubchannel

final class  extends 
{

    private final y this$1;
    private final y val$subchannel;

    final void onInUse(InternalSubchannel internalsubchannel)
    {
        inUseStateAggregator.updateObjectInUse(internalsubchannel, true);
    }

    final void onNotInUse(InternalSubchannel internalsubchannel)
    {
        inUseStateAggregator.updateObjectInUse(internalsubchannel, false);
    }

    final void onStateChange$5166IRPFCTP70OPFD5N78PBIDPGMOBQ9DPQ6ASJEC5M56TB2CDK62RJECLM3MJ39DSNMESJGCCNK6RREDPIM6T39EPKN8UAJEHGN8PA9DPJ6UEP9AO______0(ConnectivityStateInfo connectivitystateinfo)
    {
        nUse nuse = this._cls1.this;
        if (connectivitystateinfo.state == ConnectivityState.TRANSIENT_FAILURE || connectivitystateinfo.state == ConnectivityState.IDLE)
        {
            nuse..refresh();
        }
        if (this._cls1.this == lbHelper)
        {
            .handleSubchannelState(val$subchannel, connectivitystateinfo);
        }
    }

    final void onTerminated(InternalSubchannel internalsubchannel)
    {
        subchannels.remove(internalsubchannel);
        channelz.subchannels.remove(Long.valueOf(internalsubchannel.getLogId().id));
        internalsubchannel = _fld0;
        if (!((ManagedChannelImpl) (internalsubchannel)).terminated && ((ManagedChannelImpl) (internalsubchannel)).shutdown.get() && ((ManagedChannelImpl) (internalsubchannel)).subchannels.isEmpty() && ((ManagedChannelImpl) (internalsubchannel)).oobChannels.isEmpty())
        {
            ManagedChannelImpl.logger.logp(Level.FINE, "io.grpc.internal.ManagedChannelImpl", "maybeTerminateChannel", "[{0}] Terminated", ((ManagedChannelImpl) (internalsubchannel)).logId);
            ((ManagedChannelImpl) (internalsubchannel)).channelz.rootChannels.remove(Long.valueOf(internalsubchannel.getLogId().id));
            internalsubchannel.terminated = true;
            ((ManagedChannelImpl) (internalsubchannel)).terminatedLatch.countDown();
            ((ManagedChannelImpl) (internalsubchannel)).executorPool.returnObject(((ManagedChannelImpl) (internalsubchannel)).executor);
            ((ManagedChannelImpl) (internalsubchannel)).transportFactory.close();
        }
    }

    ()
    {
        this$1 = final_;
        val$subchannel = val.subchannel.this;
        super();
    }
}
