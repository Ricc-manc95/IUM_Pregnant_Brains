// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, UnsafeUtil, ByteString, MessageLite, 
//            AbstractMessageLite, Schema, Utf8

static final class initialPosition extends CodedOutputStream
{

    private final long address;
    private final ByteBuffer buffer;
    private final long initialPosition;
    private final long limit;
    private final long oneVarintLimit;
    private final ByteBuffer originalBuffer;
    private long position;

    public final void flush()
    {
        originalBuffer.position((int)(position - address));
    }

    public final int spaceLeft()
    {
        return (int)(limit - position);
    }

    public final void write(byte byte0)
        throws IOException
    {
        if (position >= limit)
        {
            throw new it>(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
            }));
        } else
        {
            long l = position;
            position = 1L + l;
            UnsafeUtil.MEMORY_ACCESSOR.position(l, byte0);
            return;
        }
    }

    public final void write(byte abyte0[], int i, int j)
        throws IOException
    {
        if (abyte0 == null || i < 0 || j < 0 || abyte0.length - j < i || limit - (long)j < position)
        {
            if (abyte0 == null)
            {
                throw new NullPointerException("value");
            } else
            {
                throw new it>(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(j)
                }));
            }
        } else
        {
            long l = i;
            long l1 = position;
            long l2 = j;
            UnsafeUtil.MEMORY_ACCESSOR.position(abyte0, l, l1, l2);
            position = position + (long)j;
            return;
        }
    }

    public final void writeBool(int i, boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        writeTag(i, 0);
        i = ((flag1) ? 1 : 0);
        if (flag)
        {
            i = 1;
        }
        write((byte)i);
    }

    public final void writeByteArrayNoTag(byte abyte0[], int i, int j)
        throws IOException
    {
        writeUInt32NoTag(j);
        write(abyte0, 0, j);
    }

    public final void writeBytes(int i, ByteString bytestring)
        throws IOException
    {
        writeTag(i, 2);
        writeBytesNoTag(bytestring);
    }

    public final void writeBytesNoTag(ByteString bytestring)
        throws IOException
    {
        writeUInt32NoTag(bytestring.size());
        bytestring.writeTo(this);
    }

    public final void writeFixed32(int i, int j)
        throws IOException
    {
        writeTag(i, 5);
        writeFixed32NoTag(j);
    }

    public final void writeFixed32NoTag(int i)
        throws IOException
    {
        buffer.putInt((int)(position - address), i);
        position = position + 4L;
    }

    public final void writeFixed64(int i, long l)
        throws IOException
    {
        writeTag(i, 1);
        writeFixed64NoTag(l);
    }

    public final void writeFixed64NoTag(long l)
        throws IOException
    {
        buffer.putLong((int)(position - address), l);
        position = position + 8L;
    }

    public final void writeInt32(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeInt32NoTag(j);
    }

    public final void writeInt32NoTag(int i)
        throws IOException
    {
        if (i >= 0)
        {
            writeUInt32NoTag(i);
            return;
        } else
        {
            writeUInt64NoTag(i);
            return;
        }
    }

    public final void writeLazy(byte abyte0[], int i, int j)
        throws IOException
    {
        write(abyte0, i, j);
    }

    public final void writeMessage(int i, MessageLite messagelite)
        throws IOException
    {
        writeTag(i, 2);
        writeMessageNoTag(messagelite);
    }

    final void writeMessage(int i, MessageLite messagelite, Schema schema)
        throws IOException
    {
        writeTag(i, 2);
        writeMessageNoTag(messagelite, schema);
    }

    public final void writeMessageNoTag(MessageLite messagelite)
        throws IOException
    {
        writeUInt32NoTag(messagelite.getSerializedSize());
        messagelite.writeTo(this);
    }

    final void writeMessageNoTag(MessageLite messagelite, Schema schema)
        throws IOException
    {
        AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
        int j = abstractmessagelite.getMemoizedSerializedSize();
        int i = j;
        if (j == -1)
        {
            i = schema.getSerializedSize(abstractmessagelite);
            abstractmessagelite.setMemoizedSerializedSize(i);
        }
        writeUInt32NoTag(i);
        schema.writeTo(messagelite, wrapper);
    }

    public final void writeMessageSetExtension(int i, MessageLite messagelite)
        throws IOException
    {
        writeTag(1, 3);
        writeUInt32(2, i);
        writeMessage(3, messagelite);
        writeTag(1, 4);
    }

    public final void writeRawMessageSetExtension(int i, ByteString bytestring)
        throws IOException
    {
        writeTag(1, 3);
        writeUInt32(2, i);
        writeBytes(3, bytestring);
        writeTag(1, 4);
    }

    public final void writeString(int i, String s)
        throws IOException
    {
        writeTag(i, 2);
        writeStringNoTag(s);
    }

    public final void writeStringNoTag(String s)
        throws IOException
    {
        Object obj;
        byte abyte0[];
        long l1 = position;
        int i;
        int j;
        int k;
        int l;
        long l2;
        try
        {
            i = computeUInt32SizeNoTag(s.length() * 3);
            j = computeUInt32SizeNoTag(s.length());
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            position = l1;
            l1 = position;
            buffer.position((int)(l1 - address));
            inefficientWriteStringNoTag(s, ((oTag) (obj)));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new it>(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new it>(s);
        }
        if (j != i)
        {
            break MISSING_BLOCK_LABEL_225;
        }
        i = (int)(position - address) + j;
        buffer.position(i);
        obj = buffer;
        abyte0 = Utf8.processor;
        if (!((ByteBuffer) (obj)).hasArray()) goto _L2; else goto _L1
_L1:
        j = ((ByteBuffer) (obj)).arrayOffset();
        abyte0 = ((ByteBuffer) (obj)).array();
        k = ((ByteBuffer) (obj)).position();
        l = ((ByteBuffer) (obj)).remaining();
        ((ByteBuffer) (obj)).position(Utf8.processor.buffer(s, abyte0, k + j, l) - j);
_L3:
        i = buffer.position() - i;
        writeUInt32NoTag(i);
        l2 = position;
        position = (long)i + l2;
        return;
_L2:
label0:
        {
            if (!((ByteBuffer) (obj)).isDirect())
            {
                break label0;
            }
            abyte0.position(s, ((ByteBuffer) (obj)));
        }
          goto _L3
        position(s, ((ByteBuffer) (obj)));
          goto _L3
        i = Utf8.encodedLength(s);
        writeUInt32NoTag(i);
        l2 = position;
        buffer.position((int)(l2 - address));
        obj = buffer;
        abyte0 = Utf8.processor;
        if (!((ByteBuffer) (obj)).hasArray()) goto _L5; else goto _L4
_L4:
        j = ((ByteBuffer) (obj)).arrayOffset();
        abyte0 = ((ByteBuffer) (obj)).array();
        k = ((ByteBuffer) (obj)).position();
        l = ((ByteBuffer) (obj)).remaining();
        ((ByteBuffer) (obj)).position(Utf8.processor.buffer(s, abyte0, k + j, l) - j);
_L6:
        l2 = position;
        position = (long)i + l2;
        return;
_L5:
label1:
        {
            if (!((ByteBuffer) (obj)).isDirect())
            {
                break label1;
            }
            abyte0.position(s, ((ByteBuffer) (obj)));
        }
          goto _L6
        position(s, ((ByteBuffer) (obj)));
          goto _L6
    }

    public final void writeTag(int i, int j)
        throws IOException
    {
        writeUInt32NoTag(i << 3 | j);
    }

    public final void writeUInt32(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeUInt32NoTag(j);
    }

    public final void writeUInt32NoTag(int i)
        throws IOException
    {
        int j;
        j = i;
        if (position <= oneVarintLimit)
        {
            do
            {
                if ((i & 0xffffff80) == 0)
                {
                    long l = position;
                    position = l + 1L;
                    byte byte0 = (byte)i;
                    UnsafeUtil.MEMORY_ACCESSOR.position(l, byte0);
                    return;
                }
                long l1 = position;
                position = l1 + 1L;
                byte byte1 = (byte)(i & 0x7f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.position(l1, byte1);
                i >>>= 7;
            } while (true);
        }
_L3:
        if (position < limit)
        {
            if ((j & 0xffffff80) == 0)
            {
                long l3 = position;
                position = l3 + 1L;
                byte byte3 = (byte)j;
                UnsafeUtil.MEMORY_ACCESSOR.position(l3, byte3);
                return;
            }
        } else
        {
            throw new it>(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
            }));
        }
        if (true) goto _L2; else goto _L1
_L2:
        long l2 = position;
        position = l2 + 1L;
        byte byte2 = (byte)(j & 0x7f | 0x80);
        UnsafeUtil.MEMORY_ACCESSOR.position(l2, byte2);
        j >>>= 7;
        if (true) goto _L3; else goto _L1
_L1:
    }

    public final void writeUInt64(int i, long l)
        throws IOException
    {
        writeTag(i, 0);
        writeUInt64NoTag(l);
    }

    public final void writeUInt64NoTag(long l)
        throws IOException
    {
        long l1;
        l1 = l;
        if (position <= oneVarintLimit)
        {
            do
            {
                if ((l & -128L) == 0L)
                {
                    l1 = position;
                    position = l1 + 1L;
                    byte byte0 = (byte)(int)l;
                    UnsafeUtil.MEMORY_ACCESSOR.position(l1, byte0);
                    return;
                }
                l1 = position;
                position = l1 + 1L;
                byte byte1 = (byte)((int)l & 0x7f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.position(l1, byte1);
                l >>>= 7;
            } while (true);
        }
_L3:
        if (position < limit)
        {
            if ((l1 & -128L) == 0L)
            {
                l = position;
                position = l + 1L;
                byte byte3 = (byte)(int)l1;
                UnsafeUtil.MEMORY_ACCESSOR.position(l, byte3);
                return;
            }
        } else
        {
            throw new it>(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(1)
            }));
        }
        if (true) goto _L2; else goto _L1
_L2:
        l = position;
        position = l + 1L;
        byte byte2 = (byte)((int)l1 & 0x7f | 0x80);
        UnsafeUtil.MEMORY_ACCESSOR.position(l, byte2);
        l1 >>>= 7;
        if (true) goto _L3; else goto _L1
_L1:
    }

    (ByteBuffer bytebuffer)
    {
        originalBuffer = bytebuffer;
        buffer = bytebuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
          = UnsafeUtil.MEMORY_ACCESSOR;
        long l = UnsafeUtil.BUFFER_ADDRESS_OFFSET;
        address = .address.getLong(bytebuffer, l);
        initialPosition = address + (long)bytebuffer.position();
        limit = address + (long)bytebuffer.limit();
        oneVarintLimit = limit - 10L;
        position = initialPosition;
    }
}
