// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            AbstractReadableBuffer, ReadableBuffer

final class end extends AbstractReadableBuffer
{

    private final byte bytes[];
    private final int end;
    private int offset;

    public final ReadableBuffer readBytes(int i)
    {
        if (readableBytes() < i)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            int j = offset;
            offset = offset + i;
            return new <init>(bytes, j, i);
        }
    }

    public final void readBytes(byte abyte0[], int i, int j)
    {
        System.arraycopy(bytes, offset, abyte0, i, j);
        offset = offset + j;
    }

    public final int readUnsignedByte()
    {
        if (readableBytes() < 1)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            byte abyte0[] = bytes;
            int i = offset;
            offset = i + 1;
            return abyte0[i] & 0xff;
        }
    }

    public final int readableBytes()
    {
        return end - offset;
    }

    (byte abyte0[])
    {
        this(abyte0, 0, abyte0.length);
    }

    <init>(byte abyte0[], int i, int j)
    {
        boolean flag1 = true;
        super();
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("offset must be >= 0"));
        }
        if (j >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("length must be >= 0"));
        }
        if (i + j <= abyte0.length)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("offset + length exceeds array boundary"));
        }
        if (abyte0 == null)
        {
            throw new NullPointerException(String.valueOf("bytes"));
        } else
        {
            bytes = (byte[])abyte0;
            offset = i;
            end = i + j;
            return;
        }
    }
}
