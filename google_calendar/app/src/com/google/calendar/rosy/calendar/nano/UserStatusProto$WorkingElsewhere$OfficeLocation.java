// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.rosy.calendar.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private String buildingId;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (buildingId != null)
        {
            i = j;
            if (!buildingId.equals(""))
            {
                String s = buildingId;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
                int k = CodedOutputByteBufferNano.encodedLength(s);
                i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
            }
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        do
        {
            int i = codedinputbytebuffernano.readTag();
            switch (i)
            {
            default:
                if (super.storeUnknownField(codedinputbytebuffernano, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                buildingId = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (buildingId != null && !buildingId.equals(""))
        {
            String s = buildingId;
            codedoutputbytebuffernano.writeRawVarint32(10);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        buildingId = "";
        unknownFieldData = null;
        cachedSize = -1;
    }
}