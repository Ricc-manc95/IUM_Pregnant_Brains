// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.StatusException;

// Referenced classes of package io.grpc.internal:
//            FailingClientTransport

final class ack
    implements Runnable
{

    private final FailingClientTransport this$0;
    private final ack val$callback;

    public final void run()
    {
        ack ack = val$callback;
        new StatusException(error);
        ack._mth5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIILG_0();
    }

    ack()
    {
        this$0 = final_failingclienttransport;
        val$callback = ack.this;
        super();
    }
}
