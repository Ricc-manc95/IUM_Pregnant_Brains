// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.KnownLength;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package io.grpc.internal:
//            ReadableBuffer

final class buffer extends InputStream
    implements KnownLength
{

    private final ReadableBuffer buffer;

    public final int available()
        throws IOException
    {
        return buffer.readableBytes();
    }

    public final void close()
        throws IOException
    {
        buffer.close();
    }

    public final int read()
    {
        if (buffer.readableBytes() == 0)
        {
            return -1;
        } else
        {
            return buffer.readUnsignedByte();
        }
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if (buffer.readableBytes() == 0)
        {
            return -1;
        } else
        {
            j = Math.min(buffer.readableBytes(), j);
            buffer.readBytes(abyte0, i, j);
            return j;
        }
    }

    public (ReadableBuffer readablebuffer)
    {
        if (readablebuffer == null)
        {
            throw new NullPointerException(String.valueOf("buffer"));
        } else
        {
            buffer = (ReadableBuffer)readablebuffer;
            return;
        }
    }
}
