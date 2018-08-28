// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    public  dailyPattern;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (dailyPattern != null)
        {
              = dailyPattern;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = .computeSerializedSize();
            .cachedSize = k;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + i);
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
                if (dailyPattern == null)
                {
                    dailyPattern = new ();
                }
                codedinputbytebuffernano.readMessage(dailyPattern);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (dailyPattern != null)
        {
              = dailyPattern;
            codedoutputbytebuffernano.writeRawVarint32(10);
            if (((MessageNano) ()).cachedSize < 0)
            {
                .cachedSize = .computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) ()).cachedSize);
            .writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        dailyPattern = null;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
