// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class AggregatedData extends ExtendableMessageNano
{

    private Integer count;
    private Long max;
    private Long min;
    private Long sum;

    public AggregatedData()
    {
        count = null;
        sum = null;
        max = null;
        min = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (count != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, count.intValue());
        }
        j = i;
        if (sum != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, sum.longValue());
        }
        i = j;
        if (max != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, max.longValue());
        }
        j = i;
        if (min != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, min.longValue());
        }
        return j;
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

            case 8: // '\b'
                count = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                sum = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 24: // '\030'
                max = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 32: // ' '
                min = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (count != null)
        {
            codedoutputbytebuffernano.writeInt32(1, count.intValue());
        }
        if (sum != null)
        {
            codedoutputbytebuffernano.writeInt64(2, sum.longValue());
        }
        if (max != null)
        {
            codedoutputbytebuffernano.writeInt64(3, max.longValue());
        }
        if (min != null)
        {
            codedoutputbytebuffernano.writeInt64(4, min.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
