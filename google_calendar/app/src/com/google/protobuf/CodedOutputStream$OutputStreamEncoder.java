// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, ByteString, MessageLite, AbstractMessageLite, 
//            Schema, Utf8, ByteOutput

static final class out extends der
{

    private final OutputStream out;

    public final void flush()
        throws IOException
    {
        if (position > 0)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
    }

    public final void write(byte byte0)
        throws IOException
    {
        if (position == limit)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        byte abyte0[] = super.buffer;
        int i = super.position;
        super.position = i + 1;
        abyte0[i] = byte0;
        super.totalBytesWritten = super.totalBytesWritten + 1;
    }

    public final void write(byte abyte0[], int i, int j)
        throws IOException
    {
        if (limit - position >= j)
        {
            System.arraycopy(abyte0, i, buffer, position, j);
            position = position + j;
        } else
        {
            int k = limit - position;
            System.arraycopy(abyte0, i, buffer, position, k);
            i += k;
            j -= k;
            position = limit;
            totalBytesWritten = k + totalBytesWritten;
            out.write(buffer, 0, position);
            position = 0;
            if (j <= limit)
            {
                System.arraycopy(abyte0, i, buffer, 0, j);
                position = j;
            } else
            {
                out.write(abyte0, i, j);
            }
        }
        totalBytesWritten = totalBytesWritten + j;
    }

    public final void writeBool(int i, boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        if (limit - position < 11)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(i << 3 | 0);
        i = ((flag1) ? 1 : 0);
        if (flag)
        {
            i = 1;
        }
        byte byte0 = (byte)i;
        byte abyte0[] = super.buffer;
        i = super.position;
        super.position = i + 1;
        abyte0[i] = byte0;
        super.totalBytesWritten = super.totalBytesWritten + 1;
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
        if (limit - position < 14)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(5 | i << 3);
        bufferFixed32NoTag(j);
    }

    public final void writeFixed32NoTag(int i)
        throws IOException
    {
        if (limit - position < 4)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferFixed32NoTag(i);
    }

    public final void writeFixed64(int i, long l)
        throws IOException
    {
        if (limit - position < 18)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(1 | i << 3);
        bufferFixed64NoTag(l);
    }

    public final void writeFixed64NoTag(long l)
        throws IOException
    {
        if (limit - position < 8)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferFixed64NoTag(l);
    }

    public final void writeInt32(int i, int j)
        throws IOException
    {
        if (limit - position < 20)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(i << 3 | 0);
        if (j >= 0)
        {
            bufferUInt32NoTag(j);
            return;
        } else
        {
            bufferUInt64NoTag(j);
            return;
        }
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
        int i;
        int k;
        i = s.length() * 3;
        k = computeUInt32SizeNoTag(i);
        if (k + i > limit)
        {
            byte abyte0[] = new byte[i];
            i = Utf8.processor.limit(s, abyte0, 0, i);
            writeUInt32NoTag(i);
            writeLazy(abyte0, 0, i);
            return;
        }
        int j;
        if (i + k > limit - position)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        i = computeUInt32SizeNoTag(s.length());
        j = position;
        if (i != k)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        position = j + i;
        byte abyte1[] = buffer;
        k = position;
        int i1 = limit;
        int j1 = position;
        k = Utf8.processor.position(s, abyte1, k, i1 - j1);
        position = j;
        i = k - j - i;
        bufferUInt32NoTag(i);
        position = k;
_L1:
        totalBytesWritten = i + totalBytesWritten;
        return;
        Object obj;
        obj;
        try
        {
            totalBytesWritten = totalBytesWritten - (position - j);
            position = j;
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            inefficientWriteStringNoTag(s, ((ngNoTag) (obj)));
        }
        return;
        i = Utf8.encodedLength(s);
        bufferUInt32NoTag(i);
        byte abyte2[] = buffer;
        int l = position;
        position = Utf8.processor.position(s, abyte2, l, i);
          goto _L1
        abyte2;
        throw new <init>(abyte2);
    }

    public final void writeTag(int i, int j)
        throws IOException
    {
        writeUInt32NoTag(i << 3 | j);
    }

    public final void writeUInt32(int i, int j)
        throws IOException
    {
        if (limit - position < 20)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(i << 3 | 0);
        bufferUInt32NoTag(j);
    }

    public final void writeUInt32NoTag(int i)
        throws IOException
    {
        if (limit - position < 10)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(i);
    }

    public final void writeUInt64(int i, long l)
        throws IOException
    {
        if (limit - position < 20)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt32NoTag(i << 3 | 0);
        bufferUInt64NoTag(l);
    }

    public final void writeUInt64NoTag(long l)
        throws IOException
    {
        if (limit - position < 10)
        {
            out.write(buffer, 0, position);
            position = 0;
        }
        bufferUInt64NoTag(l);
    }

    der(OutputStream outputstream, int i)
    {
        super(i);
        if (outputstream == null)
        {
            throw new NullPointerException("out");
        } else
        {
            out = outputstream;
            return;
        }
    }
}
