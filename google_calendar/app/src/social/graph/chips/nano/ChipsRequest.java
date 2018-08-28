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
//            ChipsRequestParams

public final class ChipsRequest extends ExtendableMessageNano
{

    public ChipsRequestParams chipsRequestParams;

    public ChipsRequest()
    {
        chipsRequestParams = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (chipsRequestParams != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, chipsRequestParams);
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
                if (chipsRequestParams == null)
                {
                    chipsRequestParams = new ChipsRequestParams();
                }
                codedinputbytebuffernano.readMessage(chipsRequestParams);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (chipsRequestParams != null)
        {
            codedoutputbytebuffernano.writeMessage(1, chipsRequestParams);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
