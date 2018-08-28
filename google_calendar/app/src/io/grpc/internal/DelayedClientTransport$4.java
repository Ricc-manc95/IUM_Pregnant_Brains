// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            DelayedClientTransport

final class val.status
    implements Runnable
{

    private final DelayedClientTransport this$0;
    private final Status val$status;

    public final void run()
    {
        listener.transportShutdown(val$status);
    }

    stener()
    {
        this$0 = final_delayedclienttransport;
        val$status = Status.this;
        super();
    }
}
