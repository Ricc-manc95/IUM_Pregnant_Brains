// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ClientCallImpl, ClientStream

final class remainingNanos
    implements Runnable
{

    private final long remainingNanos;
    private final ClientCallImpl this$0;

    public final void run()
    {
        stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(String.format("deadline exceeded after %dns", new Object[] {
            Long.valueOf(remainingNanos)
        })));
    }

    (long l)
    {
        this$0 = ClientCallImpl.this;
        super();
        remainingNanos = l;
    }
}
