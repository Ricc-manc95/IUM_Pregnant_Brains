// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.nio.charset.Charset;

// Referenced classes of package com.google.protobuf:
//            ByteString, Utf8, Internal, ByteOutput

class bytes extends bytes
{

    public static final long serialVersionUID = 1L;
    public final byte bytes[];

    public byte byteAt(int i)
    {
        return bytes[i];
    }

    protected void copyToInternal(byte abyte0[], int i, int j, int k)
    {
        System.arraycopy(bytes, i, abyte0, j, k);
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof ByteString))
        {
            return false;
        }
        if (size() != ((ByteString)obj).size())
        {
            return false;
        }
        if (size() == 0)
        {
            return true;
        }
        if (obj instanceof bytes)
        {
            bytes bytes1 = (bytes)obj;
            int i = super.hash;
            int j = ((ByteString) (bytes1)).hash;
            if (i != 0 && j != 0 && i != j)
            {
                return false;
            } else
            {
                return alsRange((alsRange)obj, 0, size());
            }
        } else
        {
            return obj.equals(this);
        }
    }

    final boolean equalsRange(ByteString bytestring, int i, int j)
    {
        if (j > bytestring.size())
        {
            i = size();
            throw new IllegalArgumentException((new StringBuilder(40)).append("Length too large: ").append(j).append(i).toString());
        }
        if (i + j > bytestring.size())
        {
            int k = bytestring.size();
            throw new IllegalArgumentException((new StringBuilder(59)).append("Ran off end of other: ").append(i).append(", ").append(j).append(", ").append(k).toString());
        }
        if (bytestring instanceof alsRange)
        {
            bytestring = (alsRange)bytestring;
            byte abyte0[] = bytes;
            byte abyte1[] = ((bytes) (bytestring)).bytes;
            int i1 = getOffsetIntoBytes();
            int l = getOffsetIntoBytes();
            for (i = bytestring.getOffsetIntoBytes() + i; l < i1 + j; i++)
            {
                if (abyte0[l] != abyte1[i])
                {
                    return false;
                }
                l++;
            }

            return true;
        } else
        {
            return bytestring.substring(i, i + j).equals(substring(0, j));
        }
    }

    protected int getOffsetIntoBytes()
    {
        return 0;
    }

    public final boolean isValidUtf8()
    {
        boolean flag = false;
        int i = getOffsetIntoBytes();
        byte abyte0[] = bytes;
        int j = size();
        if (Utf8.processor.Utf8(0, abyte0, i, j + i) == 0)
        {
            flag = true;
        }
        return flag;
    }

    protected final int partialHash(int i, int j, int k)
    {
        return Internal.partialHash(i, bytes, getOffsetIntoBytes() + j, k);
    }

    protected final int partialIsValidUtf8(int i, int j, int k)
    {
        j = getOffsetIntoBytes() + j;
        byte abyte0[] = bytes;
        return Utf8.processor.Utf8(i, abyte0, j, j + k);
    }

    public int size()
    {
        return bytes.length;
    }

    public final ByteString substring(int i, int j)
    {
        j = checkRange(i, j, size());
        if (j == 0)
        {
            return ByteString.EMPTY;
        } else
        {
            return new <init>(bytes, getOffsetIntoBytes() + i, j);
        }
    }

    protected final String toStringInternal(Charset charset)
    {
        return new String(bytes, getOffsetIntoBytes(), size(), charset);
    }

    final void writeTo(ByteOutput byteoutput)
        throws IOException
    {
        byteoutput.writeLazy(bytes, getOffsetIntoBytes(), size());
    }

    (byte abyte0[])
    {
        bytes = abyte0;
    }
}
