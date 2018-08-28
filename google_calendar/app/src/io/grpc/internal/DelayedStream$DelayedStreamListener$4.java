// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

// Referenced classes of package io.grpc.internal:
//            ClientStreamListener

final class val.trailers
    implements Runnable
{

    private final val.trailers this$0;
    private final Status val$status;
    private final Metadata val$trailers;

    public final void run()
    {
        alListener.closed(val$status, val$trailers);
    }

    ()
    {
        this$0 = final_;
        val$status = status1;
        val$trailers = Metadata.this;
        super();
    }
}
