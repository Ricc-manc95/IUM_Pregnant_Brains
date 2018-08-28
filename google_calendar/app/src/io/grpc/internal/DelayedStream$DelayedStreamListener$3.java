// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Metadata;

// Referenced classes of package io.grpc.internal:
//            ClientStreamListener

final class val.headers
    implements Runnable
{

    private final val.headers this$0;
    private final Metadata val$headers;

    public final void run()
    {
        alListener.headersRead(val$headers);
    }

    ()
    {
        this$0 = final_;
        val$headers = Metadata.this;
        super();
    }
}
