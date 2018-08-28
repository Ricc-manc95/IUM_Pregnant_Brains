// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Compressor;

// Referenced classes of package io.grpc.internal:
//            ClientStream

final class val.compressor
    implements val.compressor
{

    private final Compressor val$compressor;

    public final void runWith(val.compressor compressor1)
    {
        compressor1.compressor.setCompressor(val$compressor);
    }

    ()
    {
        val$compressor = compressor1;
        super();
    }
}
