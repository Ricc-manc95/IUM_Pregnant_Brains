// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, ByteString, AbstractMessageLite, Schema, 
//            MessageLite, Utf8, UnsafeUtil

static class limit extends CodedOutputStream
{

    private final byte buffer[];
    private final int limit;
    public final int offset;
    public int position;

    public void flush()
    {
    }

    public final int spaceLeft()
    {
        return limit - position;
    }

    public final void write(byte byte0)
        throws IOException
    {
        byte abyte0[];
        int i;
        try
        {
            abyte0 = buffer;
            i = position;
            position = i + 1;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
            }), indexoutofboundsexception);
        }
        abyte0[i] = byte0;
    }

    public final void write(byte abyte0[], int i, int j)
        throws IOException
    {
        try
        {
            System.arraycopy(abyte0, i, buffer, position, j);
            position = position + j;
            return;
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(j)
            }), abyte0);
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
        byte abyte0[];
        int j;
        try
        {
            abyte0 = buffer;
            j = position;
            position = j + 1;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
            }), indexoutofboundsexception);
        }
        abyte0[j] = (byte)i;
        abyte0 = buffer;
        j = position;
        position = j + 1;
        abyte0[j] = (byte)(i >> 8);
        abyte0 = buffer;
        j = position;
        position = j + 1;
        abyte0[j] = (byte)(i >> 16);
        abyte0 = buffer;
        j = position;
        position = j + 1;
        abyte0[j] = (byte)(i >>> 24);
        return;
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
        byte abyte0[];
        int i;
        try
        {
            abyte0 = buffer;
            i = position;
            position = i + 1;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
            }), indexoutofboundsexception);
        }
        abyte0[i] = (byte)(int)l;
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 8);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 16);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 24);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 32);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 40);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 48);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 56);
        return;
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
        AbstractMessageLite abstractmessagelite = (AbstractMessageLite)messagelite;
        int j = abstractmessagelite.getMemoizedSerializedSize();
        i = j;
        if (j == -1)
        {
            i = schema.getSerializedSize(abstractmessagelite);
            abstractmessagelite.setMemoizedSerializedSize(i);
        }
        writeUInt32NoTag(i);
        schema.writeTo(messagelite, wrapper);
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
        int i = position;
        byte abyte0[];
        int j;
        int k;
        int l;
        try
        {
            k = computeUInt32SizeNoTag(s.length() * 3);
            j = computeUInt32SizeNoTag(s.length());
        }
        catch ( )
        {
            position = i;
            inefficientWriteStringNoTag(s, );
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new eption(s);
        }
        if (j != k)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        position = i + j;
        abyte0 = buffer;
        k = position;
        l = spaceLeft();
        k = Utf8.processor.position(s, abyte0, k, l);
        position = i;
        writeUInt32NoTag(k - i - j);
        position = k;
        return;
        writeUInt32NoTag(Utf8.encodedLength(s));
        abyte0 = buffer;
        j = position;
        k = spaceLeft();
        position = Utf8.processor.position(s, abyte0, j, k);
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
        int j = i;
        if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            j = i;
            if (spaceLeft() >= 10)
            {
                do
                {
                    if ((i & 0xffffff80) == 0)
                    {
                        byte abyte0[] = buffer;
                        j = position;
                        position = j + 1;
                        long l = j;
                        byte byte0 = (byte)i;
                        UnsafeUtil.MEMORY_ACCESSOR.te(abyte0, l + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                        return;
                    }
                    byte abyte1[] = buffer;
                    j = position;
                    position = j + 1;
                    long l1 = j;
                    byte byte1 = (byte)(i & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.te(abyte1, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                    i >>>= 7;
                } while (true);
            }
        }
        while ((j & 0xffffff80) != 0) 
        {
            byte abyte2[];
            try
            {
                abyte2 = buffer;
                i = position;
                position = i + 1;
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                }), indexoutofboundsexception);
            }
            abyte2[i] = (byte)(j & 0x7f | 0x80);
            j >>>= 7;
        }
        abyte2 = buffer;
        i = position;
        position = i + 1;
        abyte2[i] = (byte)j;
        return;
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
        long l1 = l;
        if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            l1 = l;
            if (spaceLeft() >= 10)
            {
                do
                {
                    if ((l & -128L) == 0L)
                    {
                        byte abyte0[] = buffer;
                        int i = position;
                        position = i + 1;
                        l1 = i;
                        byte byte0 = (byte)(int)l;
                        UnsafeUtil.MEMORY_ACCESSOR.te(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                        return;
                    }
                    byte abyte1[] = buffer;
                    int j = position;
                    position = j + 1;
                    l1 = j;
                    byte byte1 = (byte)((int)l & 0x7f | 0x80);
                    UnsafeUtil.MEMORY_ACCESSOR.te(abyte1, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                    l >>>= 7;
                } while (true);
            }
        }
        while ((l1 & -128L) != 0L) 
        {
            byte abyte2[];
            int k;
            try
            {
                abyte2 = buffer;
                k = position;
                position = k + 1;
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new eption(String.format("Pos: %d, limit: %d, len: %d", new Object[] {
                    Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(1)
                }), indexoutofboundsexception);
            }
            abyte2[k] = (byte)((int)l1 & 0x7f | 0x80);
            l1 >>>= 7;
        }
        abyte2 = buffer;
        k = position;
        position = k + 1;
        abyte2[k] = (byte)(int)l1;
        return;
    }

    (byte abyte0[], int i, int j)
    {
        if (abyte0 == null)
        {
            throw new NullPointerException("buffer");
        }
        if ((i | j | abyte0.length - (i + j)) < 0)
        {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] {
                Integer.valueOf(abyte0.length), Integer.valueOf(i), Integer.valueOf(j)
            }));
        } else
        {
            buffer = abyte0;
            offset = i;
            position = i;
            limit = i + j;
            return;
        }
    }
}
