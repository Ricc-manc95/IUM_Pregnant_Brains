// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;

// Referenced classes of package okio:
//            BufferedSink, Buffer, Sink, Segment, 
//            Util

final class RealBufferedSink
    implements BufferedSink
{

    private final Buffer buffer = new Buffer();
    private boolean closed;
    private final Sink sink;

    RealBufferedSink(Sink sink1)
    {
        if (sink1 == null)
        {
            throw new NullPointerException("sink == null");
        } else
        {
            sink = sink1;
            return;
        }
    }

    private final BufferedSink emitCompleteSegments()
        throws IOException
    {
        Object obj;
        long l1;
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        obj = buffer;
        l1 = ((Buffer) (obj)).size;
        if (l1 != 0L) goto _L2; else goto _L1
_L1:
        long l = 0L;
_L4:
        if (l > 0L)
        {
            sink.write(buffer, l);
        }
        return this;
_L2:
        obj = ((Buffer) (obj)).head.prev;
        l = l1;
        if (((Segment) (obj)).limit < 8192)
        {
            l = l1;
            if (((Segment) (obj)).owner)
            {
                l = l1 - (long)(((Segment) (obj)).limit - ((Segment) (obj)).pos);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void close()
        throws IOException
    {
        if (!closed) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Throwable throwable;
        Throwable throwable1;
        throwable1 = null;
        throwable = throwable1;
        if (buffer.size <= 0L)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        sink.write(buffer, buffer.size);
        throwable = throwable1;
_L4:
        sink.close();
        throwable1 = throwable;
_L3:
        closed = true;
        if (throwable1 != null)
        {
            Util.sneakyRethrow(throwable1);
            return;
        }
          goto _L1
        Throwable throwable2;
        throwable2;
        throwable1 = throwable;
        if (throwable == null)
        {
            throwable1 = throwable2;
        }
          goto _L3
        throwable;
          goto _L4
    }

    public final void flush()
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        if (buffer.size > 0L)
        {
            sink.write(buffer, buffer.size);
        }
        sink.flush();
    }

    public final String toString()
    {
        return (new StringBuilder("buffer(")).append(sink).append(")").toString();
    }

    public final BufferedSink write(byte abyte0[])
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        }
        Buffer buffer1 = buffer;
        if (abyte0 == null)
        {
            throw new IllegalArgumentException("source == null");
        } else
        {
            abyte0 = (Buffer)buffer1.write(abyte0, 0, abyte0.length);
            return emitCompleteSegments();
        }
    }

    public final void write(Buffer buffer1, long l)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        } else
        {
            buffer.write(buffer1, l);
            emitCompleteSegments();
            return;
        }
    }

    public final BufferedSink writeByte(int i)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        } else
        {
            Buffer buffer1 = (Buffer)buffer.writeByte(i);
            return emitCompleteSegments();
        }
    }

    public final BufferedSink writeInt(int i)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        } else
        {
            Buffer buffer1 = (Buffer)buffer.writeInt(i);
            return emitCompleteSegments();
        }
    }

    public final BufferedSink writeShort(int i)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        } else
        {
            Buffer buffer1 = (Buffer)buffer.writeShort(i);
            return emitCompleteSegments();
        }
    }

    public final BufferedSink writeUtf8(String s)
        throws IOException
    {
        if (closed)
        {
            throw new IllegalStateException("closed");
        } else
        {
            s = (Buffer)buffer.writeUtf8(s, 0, s.length());
            return emitCompleteSegments();
        }
    }
}
