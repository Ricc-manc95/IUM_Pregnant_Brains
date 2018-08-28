// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package io.grpc.internal:
//            InUseStateAggregator, ManagedChannelImpl

final class <init> extends InUseStateAggregator
{

    private final ManagedChannelImpl this$0;

    final void handleInUse()
    {
        exitIdleMode();
    }

    final void handleNotInUse()
    {
        if (shutdown.get())
        {
            return;
        } else
        {
            rescheduleIdleTimer();
            return;
        }
    }

    ean()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
