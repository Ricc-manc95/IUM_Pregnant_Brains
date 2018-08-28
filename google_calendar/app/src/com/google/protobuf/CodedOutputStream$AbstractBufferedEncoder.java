// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, UnsafeUtil

static abstract class buffer extends CodedOutputStream
{

    public final byte buffer[];
    public final int limit;
    public int position;
    public int totalBytesWritten;

    final void bufferFixed32NoTag(int i)
    {
        byte abyte0[] = buffer;
        int j = position;
        position = j + 1;
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
        totalBytesWritten = totalBytesWritten + 4;
    }

    final void bufferFixed64NoTag(long l)
    {
        byte abyte0[] = buffer;
        int i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l & 255L);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 8 & 255L);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 16 & 255L);
        abyte0 = buffer;
        i = position;
        position = i + 1;
        abyte0[i] = (byte)(int)(l >> 24 & 255L);
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
        totalBytesWritten = totalBytesWritten + 8;
    }

    final void bufferUInt32NoTag(int i)
    {
        int j = i;
        if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            long l = position;
            do
            {
                if ((i & 0xffffff80) == 0)
                {
                    byte abyte0[] = buffer;
                    j = position;
                    position = j + 1;
                    long l1 = j;
                    byte byte0 = (byte)i;
                    UnsafeUtil.MEMORY_ACCESSOR.position(abyte0, l1 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                    totalBytesWritten = (int)((long)position - l) + totalBytesWritten;
                    return;
                }
                byte abyte1[] = buffer;
                j = position;
                position = j + 1;
                long l2 = j;
                byte byte1 = (byte)(i & 0x7f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.position(abyte1, l2 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                i >>>= 7;
            } while (true);
        }
        for (; (j & 0xffffff80) != 0; j >>>= 7)
        {
            byte abyte2[] = buffer;
            i = position;
            position = i + 1;
            abyte2[i] = (byte)(j & 0x7f | 0x80);
            totalBytesWritten = totalBytesWritten + 1;
        }

        byte abyte3[] = buffer;
        i = position;
        position = i + 1;
        abyte3[i] = (byte)j;
        totalBytesWritten = totalBytesWritten + 1;
    }

    final void bufferUInt64NoTag(long l)
    {
        long l1 = l;
        if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            l1 = position;
            do
            {
                if ((-128L & l) == 0L)
                {
                    byte abyte0[] = buffer;
                    int i = position;
                    position = i + 1;
                    long l2 = i;
                    byte byte0 = (byte)(int)l;
                    UnsafeUtil.MEMORY_ACCESSOR.position(abyte0, l2 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte0);
                    totalBytesWritten = (int)((long)position - l1) + totalBytesWritten;
                    return;
                }
                byte abyte1[] = buffer;
                int j = position;
                position = j + 1;
                long l3 = j;
                byte byte1 = (byte)((int)l & 0x7f | 0x80);
                UnsafeUtil.MEMORY_ACCESSOR.position(abyte1, l3 + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET, byte1);
                l >>>= 7;
            } while (true);
        }
        for (; (-128L & l1) != 0L; l1 >>>= 7)
        {
            byte abyte2[] = buffer;
            int k = position;
            position = k + 1;
            abyte2[k] = (byte)((int)l1 & 0x7f | 0x80);
            totalBytesWritten = totalBytesWritten + 1;
        }

        byte abyte3[] = buffer;
        int i1 = position;
        position = i1 + 1;
        abyte3[i1] = (byte)(int)l1;
        totalBytesWritten = totalBytesWritten + 1;
    }

    public final int spaceLeft()
    {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    (int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        } else
        {
            buffer = new byte[Math.max(i, 20)];
            limit = buffer.length;
            return;
        }
    }
}
