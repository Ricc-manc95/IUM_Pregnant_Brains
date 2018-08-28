// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

// Referenced classes of package com.google.protobuf:
//            ByteString, Internal

final class bytesLength extends bytesLength
{

    public static final long serialVersionUID = 1L;
    private final int bytesLength;
    private final int bytesOffset;

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException
    {
        throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
    }

    public final byte byteAt(int i)
    {
        checkIndex(i, size());
        return bytes[bytesOffset + i];
    }

    protected final void copyToInternal(byte abyte0[], int i, int j, int k)
    {
        System.arraycopy(bytes, getOffsetIntoBytes() + i, abyte0, j, k);
    }

    protected final int getOffsetIntoBytes()
    {
        return bytesOffset;
    }

    public final int size()
    {
        return bytesLength;
    }

    final Object writeReplace()
    {
        int i = size();
        byte abyte0[];
        if (i == 0)
        {
            abyte0 = Internal.EMPTY_BYTE_ARRAY;
        } else
        {
            abyte0 = new byte[i];
            copyToInternal(abyte0, 0, 0, i);
        }
        return new <init>(abyte0);
    }

    (byte abyte0[], int i, int j)
    {
        super(abyte0);
        checkRange(i, i + j, abyte0.length);
        bytesOffset = i;
        bytesLength = j;
    }
}
