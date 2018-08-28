// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Segment;

// Referenced classes of package io.grpc.okhttp.internal.framed:
//            FrameWriter, Http2, Settings, ErrorCode, 
//            Header, Hpack

final class maxFrameSize
    implements FrameWriter
{

    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer = new Buffer();
    private final sink hpackWriter;
    private int maxFrameSize;
    private final BufferedSink sink;

    private final void frameHeader(int i, int j, byte byte0, byte byte1)
        throws IOException
    {
        if (Http2.logger.isLoggable(Level.FINE))
        {
            Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "frameHeader", gger.formatHeader(false, i, j, byte0, byte1));
        }
        if (j > maxFrameSize)
        {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[] {
                Integer.valueOf(maxFrameSize), Integer.valueOf(j)
            });
        }
        if ((0x80000000 & i) != 0)
        {
            throw Http2.illegalArgument("reserved bit set: %s", new Object[] {
                Integer.valueOf(i)
            });
        } else
        {
            Http2.writeMedium(sink, j);
            sink.writeByte(byte0 & 0xff);
            sink.writeByte(byte1 & 0xff);
            sink.writeInt(0x7fffffff & i);
            return;
        }
    }

    public final void ackSettings(Settings settings1)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        settings1;
        this;
        JVM INSTR monitorexit ;
        throw settings1;
        int i = maxFrameSize;
        if ((settings1.set & 0x20) != 0)
        {
            i = settings1.values[5];
        }
        maxFrameSize = i;
        frameHeader(0, 0, (byte)4, (byte)1);
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    public final void close()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        closed = true;
        sink.close();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void connectionPreface()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        boolean flag = client;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_36;
        }
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        if (Http2.logger.isLoggable(Level.FINE))
        {
            Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "connectionPreface", String.format(">> CONNECTION %s", new Object[] {
                Http2.CONNECTION_PREFACE.hex()
            }));
        }
        sink.write(Http2.CONNECTION_PREFACE.toByteArray());
        sink.flush();
          goto _L1
    }

    public final void data(boolean flag, int i, Buffer buffer, int j)
        throws IOException
    {
        byte byte0 = 0;
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_27;
        buffer;
        this;
        JVM INSTR monitorexit ;
        throw buffer;
        if (flag)
        {
            byte0 = 1;
        }
        frameHeader(i, j, (byte)0, byte0);
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        sink.write(buffer, j);
        this;
        JVM INSTR monitorexit ;
    }

    public final void flush()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    public final void goAway(int i, ErrorCode errorcode, byte abyte0[])
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        errorcode;
        this;
        JVM INSTR monitorexit ;
        throw errorcode;
        if (errorcode.httpCode == -1)
        {
            throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
        }
        frameHeader(0, abyte0.length + 8, (byte)7, (byte)0);
        sink.writeInt(i);
        sink.writeInt(errorcode.httpCode);
        if (abyte0.length > 0)
        {
            sink.write(abyte0);
        }
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    public final int maxDataLength()
    {
        return maxFrameSize;
    }

    public final void ping(boolean flag, int i, int j)
        throws IOException
    {
        byte byte0 = 0;
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_29;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (flag)
        {
            byte0 = 1;
        }
        frameHeader(0, 8, (byte)6, byte0);
        sink.writeInt(i);
        sink.writeInt(j);
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    public final void rstStream(int i, ErrorCode errorcode)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        errorcode;
        this;
        JVM INSTR monitorexit ;
        throw errorcode;
        if (errorcode.httpCode == -1)
        {
            throw new IllegalArgumentException();
        }
        frameHeader(i, 4, (byte)3, (byte)0);
        sink.writeInt(errorcode.httpCode);
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    public final void settings(Settings settings1)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_24;
        settings1;
        this;
        JVM INSTR monitorexit ;
        throw settings1;
        frameHeader(0, Integer.bitCount(settings1.set) * 6, (byte)4, (byte)0);
        int i = 0;
_L7:
        if (i >= 10) goto _L2; else goto _L1
_L1:
        int j;
        if ((1 << i & settings1.set) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L3
_L5:
        sink.writeShort(j);
        sink.writeInt(settings1.values[i]);
          goto _L4
_L2:
        sink.flush();
        this;
        JVM INSTR monitorexit ;
        return;
_L9:
        j = i;
          goto _L5
_L3:
        if (j == 0) goto _L4; else goto _L6
_L6:
        if (i != 4)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = 3;
          goto _L5
_L4:
        i++;
          goto _L7
        if (i != 7) goto _L9; else goto _L8
_L8:
        j = 4;
          goto _L5
    }

    public final void synStream(boolean flag, boolean flag1, int i, int j, List list)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        throw new UnsupportedOperationException();
        list;
        this;
        JVM INSTR monitorexit ;
        throw list;
        sink sink1;
        int k;
        if (closed)
        {
            throw new IOException("closed");
        }
        if (closed)
        {
            throw new IOException("closed");
        }
        sink1 = hpackWriter;
        k = list.size();
        j = 0;
_L9:
        if (j >= k) goto _L2; else goto _L1
_L1:
        ByteString bytestring;
        Object obj;
        bytestring = ((Header)list.get(j)).name.toAsciiLowercase();
        obj = (Integer)Hpack.NAME_TO_FIRST_INDEX.get(bytestring);
        if (obj == null) goto _L4; else goto _L3
_L3:
        sink1.writeInt(((Integer) (obj)).intValue() + 1, 15, 0);
        bytestring = ((Header)list.get(j)).value;
        sink1.writeInt(bytestring.size(), 127, 0);
        obj = sink1.out;
        if (bytestring != null)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        throw new IllegalArgumentException("byteString == null");
        bytestring.write(((Buffer) (obj)));
          goto _L5
_L4:
        byte abyte0[];
        int l;
        obj = sink1.out;
        Segment segment = ((Buffer) (obj)).writableSegment(1);
        abyte0 = segment.data;
        l = segment.limit;
        segment.limit = l + 1;
        abyte0[l] = (byte)0;
        obj.size = ((Buffer) (obj)).size + 1L;
        obj = (Buffer)obj;
        sink1.writeInt(bytestring.size(), 127, 0);
        obj = sink1.out;
        if (bytestring != null)
        {
            break MISSING_BLOCK_LABEL_296;
        }
        throw new IllegalArgumentException("byteString == null");
        bytestring.write(((Buffer) (obj)));
        bytestring = ((Header)list.get(j)).value;
        sink1.writeInt(bytestring.size(), 127, 0);
        obj = sink1.out;
        if (bytestring != null)
        {
            break MISSING_BLOCK_LABEL_355;
        }
        throw new IllegalArgumentException("byteString == null");
        bytestring.write(((Buffer) (obj)));
          goto _L5
_L2:
        long l1;
        l1 = hpackBuffer.size;
        j = (int)Math.min(maxFrameSize, l1);
        byte byte0;
        byte byte1;
        if (l1 == (long)j)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        break MISSING_BLOCK_LABEL_537;
_L10:
        frameHeader(i, j, (byte)1, byte1);
        sink.write(hpackBuffer, j);
        if (l1 <= (long)j) goto _L7; else goto _L6
_L6:
        l1 -= j;
_L8:
        if (l1 <= 0L)
        {
            break; /* Loop/switch isn't completed */
        }
        j = (int)Math.min(maxFrameSize, l1);
        l1 -= j;
        if (l1 == 0L)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        frameHeader(i, j, (byte)9, byte0);
        sink.write(hpackBuffer, j);
        if (true) goto _L8; else goto _L7
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L5:
        j++;
          goto _L9
        byte1 = byte0;
        if (flag)
        {
            byte1 = (byte)(byte0 | 1);
        }
          goto _L10
    }

    public final void windowUpdate(int i, long l)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (closed)
        {
            throw new IOException("closed");
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (l != 0L && l <= 0x7fffffffL)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] {
            Long.valueOf(l)
        });
        frameHeader(i, 4, (byte)8, (byte)0);
        sink.writeInt((int)l);
        sink.flush();
        this;
        JVM INSTR monitorexit ;
    }

    gger(BufferedSink bufferedsink, boolean flag)
    {
        sink = bufferedsink;
        client = flag;
        hpackWriter = new <init>(hpackBuffer);
        maxFrameSize = 16384;
    }
}
