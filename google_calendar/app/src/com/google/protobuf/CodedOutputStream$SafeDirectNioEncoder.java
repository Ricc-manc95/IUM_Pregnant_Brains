// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, ByteString, MessageLite, AbstractMessageLite, 
//            Schema, Utf8

static final class buffer extends CodedOutputStream
{

    private final ByteBuffer buffer;
    private final ByteBuffer originalBuffer;

    public final void flush()
    {
        originalBuffer.position(buffer.position());
    }

    public final int spaceLeft()
    {
        return buffer.remaining();
    }

    public final void write(byte byte0)
        throws IOException
    {
        try
        {
            buffer.put(byte0);
            return;
        }
        catch (BufferOverflowException bufferoverflowexception)
        {
            throw new init>(bufferoverflowexception);
        }
    }

    public final void write(byte abyte0[], int i, int j)
        throws IOException
    {
        try
        {
            buffer.put(abyte0, i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new init>(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new init>(abyte0);
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
        try
        {
            buffer.putInt(i);
            return;
        }
        catch (BufferOverflowException bufferoverflowexception)
        {
            throw new init>(bufferoverflowexception);
        }
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
        try
        {
            buffer.putLong(l);
            return;
        }
        catch (BufferOverflowException bufferoverflowexception)
        {
            throw new init>(bufferoverflowexception);
        }
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
        int i = buffer.position();
        int j;
        int l;
        j = computeUInt32SizeNoTag(s.length() * 3);
        l = computeUInt32SizeNoTag(s.length());
        if (l != j)
        {
            break MISSING_BLOCK_LABEL_221;
        }
        j = buffer.position() + l;
        buffer.position(j);
        Object obj;
        byte abyte0[];
        obj = buffer;
        abyte0 = Utf8.processor;
        if (!((ByteBuffer) (obj)).hasArray()) goto _L2; else goto _L1
_L1:
        int i1 = ((ByteBuffer) (obj)).arrayOffset();
        abyte0 = ((ByteBuffer) (obj)).array();
        int l1 = ((ByteBuffer) (obj)).position();
        int j2 = ((ByteBuffer) (obj)).remaining();
        ((ByteBuffer) (obj)).position(Utf8.processor.buffer(s, abyte0, l1 + i1, j2) - i1);
_L3:
        int j1 = buffer.position();
        buffer.position(i);
        writeUInt32NoTag(j1 - j);
        buffer.position(j1);
        return;
_L2:
        try
        {
            if (!((ByteBuffer) (obj)).isDirect())
            {
                break MISSING_BLOCK_LABEL_203;
            }
            abyte0.buffer(s, ((ByteBuffer) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            try
            {
                throw new init>(((Throwable) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                buffer.position(i);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new init>(s);
            }
            inefficientWriteStringNoTag(s, ((gNoTag) (obj)));
            return;
        }
          goto _L3
        gNoTag(s, ((ByteBuffer) (obj)));
          goto _L3
        writeUInt32NoTag(Utf8.encodedLength(s));
        try
        {
            obj = buffer;
            abyte0 = Utf8.processor;
            if (((ByteBuffer) (obj)).hasArray())
            {
                int k = ((ByteBuffer) (obj)).arrayOffset();
                abyte0 = ((ByteBuffer) (obj)).array();
                int k1 = ((ByteBuffer) (obj)).position();
                int i2 = ((ByteBuffer) (obj)).remaining();
                ((ByteBuffer) (obj)).position(Utf8.processor.buffer(s, abyte0, k1 + k, i2) - k);
                return;
            }
            break MISSING_BLOCK_LABEL_302;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        throw new init>(((Throwable) (obj)));
        if (((ByteBuffer) (obj)).isDirect())
        {
            abyte0.init>(s, ((ByteBuffer) (obj)));
            return;
        }
        init>(s, ((ByteBuffer) (obj)));
        return;
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
_L1:
        if ((i & 0xffffff80) == 0)
        {
            try
            {
                buffer.put((byte)i);
                return;
            }
            catch (BufferOverflowException bufferoverflowexception)
            {
                throw new init>(bufferoverflowexception);
            }
        }
        buffer.put((byte)(i & 0x7f | 0x80));
        i >>>= 7;
          goto _L1
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
_L1:
        if ((-128L & l) == 0L)
        {
            try
            {
                buffer.put((byte)(int)l);
                return;
            }
            catch (BufferOverflowException bufferoverflowexception)
            {
                throw new init>(bufferoverflowexception);
            }
        }
        buffer.put((byte)((int)l & 0x7f | 0x80));
        l >>>= 7;
          goto _L1
    }

    (ByteBuffer bytebuffer)
    {
        originalBuffer = bytebuffer;
        buffer = bytebuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        bytebuffer.position();
    }
}
