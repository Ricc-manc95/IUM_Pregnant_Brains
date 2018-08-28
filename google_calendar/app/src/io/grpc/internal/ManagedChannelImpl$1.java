// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ConnectivityState;
import java.util.Collection;

// Referenced classes of package io.grpc.internal:
//            ChannelExecutor, ManagedChannelImpl, DelayedClientTransport, TimeProvider, 
//            ChannelTracer, ConnectivityStateManager

final class > extends ChannelExecutor
{

    private final ManagedChannelImpl this$0;

    final void handleUncaughtThrowable(Throwable throwable)
    {
        super.handleUncaughtThrowable(throwable);
        ManagedChannelImpl managedchannelimpl = ManagedChannelImpl.this;
        if (!managedchannelimpl.panicMode)
        {
            managedchannelimpl.panicMode = true;
            managedchannelimpl.cancelIdleTimer(true);
            managedchannelimpl.shutdownNameResolverAndLoadBalancer(false);
            throwable = new (throwable);
            managedchannelimpl.subchannelPicker = throwable;
            managedchannelimpl.delayedTransport.reprocess(throwable);
            if (managedchannelimpl.channelTracer != null)
            {
                throwable = managedchannelimpl.channelTracer;
                .Event.Builder builder = new .Event.Builder();
                builder.description = "Entering TRANSIENT_FAILURE state";
                builder.severity = .Event.Severity.CT_INFO;
                builder.timestampNanos = Long.valueOf(managedchannelimpl.timeProvider.currentTimeNanos());
                .Event event = builder.build();
                synchronized (((ChannelTracer) (throwable)).lock)
                {
                    ((ChannelTracer) (throwable)).events.add(event);
                }
            }
            managedchannelimpl.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
        return;
        throwable;
        obj;
        JVM INSTR monitorexit ;
        throw throwable;
    }

    ger()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
