// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package social.graph.chips.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package social.graph.chips.nano:
//            DataSourceEnum, ResponseStatusEnum, ChipsRequestParams

public final class ChipsDataSourceResponse extends ExtendableMessageNano
{

    public ChipsRequestParams chipsRequestParams;
    public int label;
    private Long latencyUsec;
    public Integer numResults;
    public int status;

    public ChipsDataSourceResponse()
    {
        label = 0x80000000;
        status = 0x80000000;
        chipsRequestParams = null;
        numResults = null;
        latencyUsec = null;
        cachedSize = -1;
    }

    private final ChipsDataSourceResponse mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                int j = codedinputbytebuffernano.bufferPos;
                int l = codedinputbytebuffernano.bufferStart;
                try
                {
                    label = DataSourceEnum.checkDataSourceOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(j - l, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 16: // '\020'
                int k = codedinputbytebuffernano.bufferPos;
                int i1 = codedinputbytebuffernano.bufferStart;
                try
                {
                    status = ResponseStatusEnum.checkResponseStatusOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception1)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(k - i1, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 26: // '\032'
                if (chipsRequestParams == null)
                {
                    chipsRequestParams = new ChipsRequestParams();
                }
                codedinputbytebuffernano.readMessage(chipsRequestParams);
                break;

            case 32: // ' '
                numResults = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 40: // '('
                latencyUsec = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (label != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, label);
        }
        j = i;
        if (status != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, status);
        }
        i = j;
        if (chipsRequestParams != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, chipsRequestParams);
        }
        j = i;
        if (numResults != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, numResults.intValue());
        }
        i = j;
        if (latencyUsec != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(5, latencyUsec.longValue());
        }
        return i;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (label != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, label);
        }
        if (status != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, status);
        }
        if (chipsRequestParams != null)
        {
            codedoutputbytebuffernano.writeMessage(3, chipsRequestParams);
        }
        if (numResults != null)
        {
            codedoutputbytebuffernano.writeInt32(4, numResults.intValue());
        }
        if (latencyUsec != null)
        {
            codedoutputbytebuffernano.writeInt64(5, latencyUsec.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
