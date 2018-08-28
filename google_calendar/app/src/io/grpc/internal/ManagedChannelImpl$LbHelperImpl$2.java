// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Set;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, InternalSubchannel

final class val.internalSubchannel
    implements Runnable
{

    private final val.internalSubchannel this$1;
    private final InternalSubchannel val$internalSubchannel;

    public final void run()
    {
        if (terminating)
        {
            val$internalSubchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
        }
        if (!terminated)
        {
            subchannels.add(val$internalSubchannel);
        }
    }

    ()
    {
        this$1 = final_;
        val$internalSubchannel = InternalSubchannel.this;
        super();
    }
}
