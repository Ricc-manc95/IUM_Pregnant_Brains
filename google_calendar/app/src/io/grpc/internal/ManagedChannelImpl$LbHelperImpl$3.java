// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ConnectivityState;
import java.util.Collection;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, TimeProvider, ChannelTracer, ConnectivityStateManager

final class val.newState
    implements Runnable
{

    private final val.newState this$1;
    private final io.grpc.erImpl val$newPicker;
    private final ConnectivityState val$newState;

    public final void run()
    {
        if (this._cls1.this == lbHelper) goto _L2; else goto _L1
_L1:
        return;
_L2:
        updateSubchannelPicker(val$newPicker);
        if (val$newState == ConnectivityState.SHUTDOWN) goto _L1; else goto _L3
_L3:
        if (channelTracer != null)
        {
            ChannelTracer channeltracer = channelTracer;
            r r = new r();
            Object obj1 = String.valueOf(val$newState);
            r.description = (new StringBuilder(String.valueOf(obj1).length() + 15)).append("Entering ").append(((String) (obj1))).append(" state").toString();
            r.severity = ty.CT_INFO;
            r.timestampNanos = Long.valueOf(timeProvider.currentTimeNanos());
            obj1 = r.build();
            synchronized (channeltracer.lock)
            {
                channeltracer.events.add(obj1);
            }
        }
        channelStateManager.gotoState(val$newState);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ty()
    {
        this$1 = final_ty;
        val$newPicker = erimpl;
        val$newState = ConnectivityState.this;
        super();
    }
}
