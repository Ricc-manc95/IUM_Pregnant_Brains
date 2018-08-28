// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;

// Referenced classes of package io.grpc.internal:
//            DelayedStream, ClientStream

final class val.compressor
    implements Runnable
{

    private final DelayedStream this$0;
    private final Compressor val$compressor;

    public final void run()
    {
        realStream.setCompressor(val$compressor);
    }

    ()
    {
        this$0 = final_delayedstream;
        val$compressor = Compressor.this;
        super();
    }
}
