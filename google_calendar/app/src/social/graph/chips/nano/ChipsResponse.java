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
//            ChipsRequestParams, ResponseStatusEnum

public final class ChipsResponse extends ExtendableMessageNano
{

    public ChipsRequestParams chipsRequestParams;
    public Long latencyUsec;
    public Integer numResults;
    public int status;

    public ChipsResponse()
    {
        chipsRequestParams = null;
        status = 0x80000000;
        numResults = null;
        latencyUsec = null;
        cachedSize = -1;
    }

    private final ChipsResponse mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                if (chipsRequestParams == null)
                {
                    chipsRequestParams = new ChipsRequestParams();
                }
                codedinputbytebuffernano.readMessage(chipsRequestParams);
                break;

            case 16: // '\020'
                int j = codedinputbytebuffernano.bufferPos;
                int k = codedinputbytebuffernano.bufferStart;
                try
                {
                    status = ResponseStatusEnum.checkResponseStatusOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 24: // '\030'
                numResults = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 32: // ' '
                latencyUsec = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (chipsRequestParams != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, chipsRequestParams);
        }
        j = i;
        if (status != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, status);
        }
        i = j;
        if (numResults != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, numResults.intValue());
        }
        j = i;
        if (latencyUsec != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, latencyUsec.longValue());
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
        if (chipsRequestParams != null)
        {
            codedoutputbytebuffernano.writeMessage(1, chipsRequestParams);
        }
        if (status != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, status);
        }
        if (numResults != null)
        {
            codedoutputbytebuffernano.writeInt32(3, numResults.intValue());
        }
        if (latencyUsec != null)
        {
            codedoutputbytebuffernano.writeInt64(4, latencyUsec.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
