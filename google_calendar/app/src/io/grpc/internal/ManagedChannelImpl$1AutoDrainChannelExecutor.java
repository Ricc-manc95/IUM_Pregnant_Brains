// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, ChannelExecutor

final class this._cls0
    implements Executor
{

    private final ManagedChannelImpl this$0;

    public final void execute(Runnable runnable)
    {
        channelExecutor.executeLater(runnable);
        channelExecutor.drain();
    }

    ()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
