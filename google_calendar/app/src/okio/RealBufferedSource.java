// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.EOFException;
import java.io.IOException;

// Referenced classes of package okio:
//            BufferedSource, Buffer, Source, ByteString

final class RealBufferedSource
    implements BufferedSource
{

    private final Buffer buffer = new Buffer();
    private boolean closed;
    private final Source source;

    RealBufferedSource(Source source1)
    {
        if (source1 == null)
        {
            throw new NullPointerException("source == null");
        } else
        {
            source = source1;
            return;
        }
    }

    public final Buffer buffer()
    {
        return buffer;
    }

    public final void close()
        throws IOException
    {
        if (closed)
        {
            return;
        }
        closed = true;
        source.close();
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

    public final boolean exhausted()
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        boolean flag;
        if (buffer.size == 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && source.read(buffer, 8192L) == -1L;
    }

    public final long read(Buffer buffer1, long l)
        throws IOException
    {
        if (buffer1 == null)
        {
            throw new IllegalArgumentException("sink == null");
        }
        if (l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount < 0: ")).append(l).toString());
        }
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        if (buffer.size == 0L && source.read(buffer, 8192L) == -1L)
        {
            return -1L;
        } else
        {
            l = Math.min(l, buffer.size);
            return buffer.read(buffer1, l);
        }
    }

    public final byte readByte()
        throws IOException
    {
        require(1L);
        return buffer.readByte();
    }

    public final byte[] readByteArray(long l)
        throws IOException
    {
        require(l);
        return buffer.readByteArray(l);
    }

    public final ByteString readByteString(long l)
        throws IOException
    {
        require(l);
        return new ByteString(buffer.readByteArray(l));
    }

    public final int readInt()
        throws IOException
    {
        require(4L);
        return buffer.readInt();
    }

    public final short readShort()
        throws IOException
    {
        require(2L);
        return buffer.readShort();
    }

    public final void require(long l)
        throws IOException
    {
        boolean flag;
        if (l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount < 0: ")).append(l).toString());
        }
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        do
        {
            if (buffer.size >= l)
            {
                break MISSING_BLOCK_LABEL_96;
            }
        } while (source.read(buffer, 8192L) != -1L);
        flag = false;
_L1:
        if (!flag)
        {
            throw new EOFException();
        } else
        {
            return;
        }
        flag = true;
          goto _L1
    }

    public final void skip(long l)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
_L3:
        if (l > 0L)
        {
            if (buffer.size == 0L && source.read(buffer, 8192L) == -1L)
            {
                throw new EOFException();
            }
        } else
        {
            return;
        }
        if (true) goto _L2; else goto _L1
_L2:
        long l1 = Math.min(l, buffer.size);
        buffer.skip(l1);
        l -= l1;
        if (true) goto _L3; else goto _L1
_L1:
    }

    public final String toString()
    {
        return (new StringBuilder("buffer(")).append(source).append(")").toString();
    }
}
