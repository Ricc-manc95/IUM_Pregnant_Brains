// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;
import okio.Segment;

final class OkHttpWritableBuffer
    implements WritableBuffer
{

    public final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    OkHttpWritableBuffer(Buffer buffer1, int i)
    {
        buffer = buffer1;
        writableBytes = i;
    }

    public final int readableBytes()
    {
        return readableBytes;
    }

    public final void release()
    {
    }

    public final int writableBytes()
    {
        return writableBytes;
    }

    public final void write(byte byte0)
    {
        Buffer buffer1 = buffer;
        Segment segment = buffer1.writableSegment(1);
        byte abyte0[] = segment.data;
        int i = segment.limit;
        segment.limit = i + 1;
        abyte0[i] = byte0;
        buffer1.size = buffer1.size + 1L;
        buffer1 = (Buffer)buffer1;
        writableBytes = writableBytes - 1;
        readableBytes = readableBytes + 1;
    }

    public final void write(byte abyte0[], int i, int j)
    {
        buffer.write(abyte0, i, j);
        writableBytes = writableBytes - j;
        readableBytes = readableBytes + j;
    }
}
