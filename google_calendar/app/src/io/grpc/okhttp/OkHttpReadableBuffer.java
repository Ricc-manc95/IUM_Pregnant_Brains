// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.EOFException;
import okio.Buffer;

final class OkHttpReadableBuffer extends AbstractReadableBuffer
{

    private final Buffer buffer;

    OkHttpReadableBuffer(Buffer buffer1)
    {
        buffer = buffer1;
    }

    public final void close()
    {
        Buffer buffer1 = buffer;
        try
        {
            buffer1.skip(buffer1.size);
            return;
        }
        catch (EOFException eofexception)
        {
            throw new AssertionError(eofexception);
        }
    }

    public final ReadableBuffer readBytes(int i)
    {
        Buffer buffer1 = new Buffer();
        buffer1.write(buffer, i);
        return new OkHttpReadableBuffer(buffer1);
    }

    public final void readBytes(byte abyte0[], int i, int j)
    {
        while (j > 0) 
        {
            int k = buffer.read(abyte0, i, j);
            if (k == -1)
            {
                throw new IndexOutOfBoundsException((new StringBuilder(36)).append("EOF trying to read ").append(j).append(" bytes").toString());
            }
            j -= k;
            i += k;
        }
    }

    public final int readUnsignedByte()
    {
        return buffer.readByte() & 0xff;
    }

    public final int readableBytes()
    {
        return (int)buffer.size;
    }
}
