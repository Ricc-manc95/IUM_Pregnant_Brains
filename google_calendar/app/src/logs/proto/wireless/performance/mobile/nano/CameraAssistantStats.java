// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class CameraAssistantStats extends ExtendableMessageNano
{

    private Boolean isActionUnfinished;

    public CameraAssistantStats()
    {
        isActionUnfinished = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (isActionUnfinished != null)
        {
            isActionUnfinished.booleanValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
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

            case 8: // '\b'
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                isActionUnfinished = Boolean.valueOf(flag);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (isActionUnfinished != null)
        {
            codedoutputbytebuffernano.writeBool(1, isActionUnfinished.booleanValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
