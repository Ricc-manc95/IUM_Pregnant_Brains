// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import java.io.IOException;

// Referenced classes of package com.google.protobuf.nano:
//            CodedInputByteBufferNano

public final class WireFormatNano
{

    public static final byte EMPTY_BYTES[] = new byte[0];
    public static final int EMPTY_INT_ARRAY[] = new int[0];
    public static final long EMPTY_LONG_ARRAY[] = new long[0];
    public static final String EMPTY_STRING_ARRAY[] = new String[0];

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano codedinputbytebuffernano, int i)
        throws IOException
    {
        int j = 1;
        int k = codedinputbytebuffernano.bufferPos;
        int l = codedinputbytebuffernano.bufferStart;
        codedinputbytebuffernano.skipField(i);
        while (codedinputbytebuffernano.readTag() == i) 
        {
            codedinputbytebuffernano.skipField(i);
            j++;
        }
        codedinputbytebuffernano.rewindToPositionAndTag(k - l, i);
        return j;
    }

}
