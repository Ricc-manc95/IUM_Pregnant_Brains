// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ConnectivityState;
import java.util.Collection;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, TimeProvider, ChannelTracer, ConnectivityStateManager

final class this._cls0
    implements Runnable
{

    private final ManagedChannelImpl this$0;

    public final void run()
    {
        if (channelTracer != null)
        {
            ChannelTracer channeltracer = channelTracer;
            .Event.Builder builder = new .Event.Builder();
            builder.description = "Entering SHUTDOWN state";
            builder.severity = .Event.Severity.CT_INFO;
            builder.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
            .Event event = builder.build();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(event);
            }
        }
        channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ger()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
