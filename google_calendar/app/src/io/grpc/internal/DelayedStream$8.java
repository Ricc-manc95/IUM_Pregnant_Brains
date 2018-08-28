// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream

final class val.reason
    implements Runnable
{

    private final DelayedStream this$0;
    private final Status val$reason;

    public final void run()
    {
        realStream.cancel(val$reason);
    }

    ()
    {
        this$0 = final_delayedstream;
        val$reason = Status.this;
        super();
    }
}
