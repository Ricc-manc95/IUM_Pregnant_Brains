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
//            DataSourceEnum, ChipsRequestParams

public final class ChipsDataSourceRequest extends ExtendableMessageNano
{

    public ChipsRequestParams chipsRequestParams;
    public int label;

    public ChipsDataSourceRequest()
    {
        label = 0x80000000;
        chipsRequestParams = null;
        cachedSize = -1;
    }

    private final ChipsDataSourceRequest mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                int k = codedinputbytebuffernano.bufferStart;
                try
                {
                    label = DataSourceEnum.checkDataSourceOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 18: // '\022'
                if (chipsRequestParams == null)
                {
                    chipsRequestParams = new ChipsRequestParams();
                }
                codedinputbytebuffernano.readMessage(chipsRequestParams);
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
        if (chipsRequestParams != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, chipsRequestParams);
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
        if (label != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, label);
        }
        if (chipsRequestParams != null)
        {
            codedoutputbytebuffernano.writeMessage(2, chipsRequestParams);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
