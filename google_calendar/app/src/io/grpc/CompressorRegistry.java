// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package io.grpc:
//            Compressor

public final class CompressorRegistry
{

    public static final CompressorRegistry DEFAULT_INSTANCE;
    public final ConcurrentMap compressors = new ConcurrentHashMap();

    private transient CompressorRegistry(Compressor acompressor[])
    {
        int j = acompressor.length;
        for (int i = 0; i < j; i++)
        {
            Compressor compressor = acompressor[i];
            compressors.put(compressor.getMessageEncoding(), compressor);
        }

    }

    static 
    {
        DEFAULT_INSTANCE = new CompressorRegistry(new Compressor[] {
            new Codec.Gzip(), Codec.Identity.NONE
        });
    }
}
