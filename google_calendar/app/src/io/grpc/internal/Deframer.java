// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Decompressor;

// Referenced classes of package io.grpc.internal:
//            ReadableBuffer, GzipInflatingBuffer

public interface Deframer
{

    public abstract void close();

    public abstract void closeWhenComplete();

    public abstract void deframe(ReadableBuffer readablebuffer);

    public abstract void request(int i);

    public abstract void setDecompressor(Decompressor decompressor);

    public abstract void setFullStreamDecompressor(GzipInflatingBuffer gzipinflatingbuffer);

    public abstract void setMaxInboundMessageSize(int i);
}
