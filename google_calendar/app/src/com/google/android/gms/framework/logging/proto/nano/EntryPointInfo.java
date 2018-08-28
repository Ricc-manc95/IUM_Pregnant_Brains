// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.framework.logging.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class EntryPointInfo extends ExtendableMessageNano
{

    private String name;
    private int type;

    public EntryPointInfo()
    {
        type = 0x80000000;
        name = null;
        cachedSize = -1;
    }

    private final EntryPointInfo mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L6:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   0: 49
    //                   8: 51
    //                   18: 144;
           goto _L1 _L2 _L3 _L4
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 6)
        {
            try
            {
                type = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(46)).append(l).append(" is not a valid enum EntryPointType").toString());
_L4:
        name = codedinputbytebuffernano.readString();
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i;
        if (type != 0x80000000)
        {
            i = type;
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            String s;
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            i = i + k + j;
        } else
        {
            i = j;
        }
        j = i;
        if (name != null)
        {
            s = name;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        return j;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (type != 0x80000000)
        {
            int i = type;
            codedoutputbytebuffernano.writeRawVarint32(8);
            String s;
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (name != null)
        {
            s = name;
            codedoutputbytebuffernano.writeRawVarint32(18);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
