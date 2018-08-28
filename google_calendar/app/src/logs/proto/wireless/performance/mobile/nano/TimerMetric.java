// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class TimerMetric extends ExtendableMessageNano
{

    public Long durationMs;
    public int endStatus;

    public TimerMetric()
    {
        durationMs = null;
        endStatus = 0x80000000;
        cachedSize = -1;
    }

    private final TimerMetric mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int i;
        int j;
        int k;
        i = codedinputbytebuffernano.readTag();
        switch (i)
        {
        default:
            if (super.storeUnknownField(codedinputbytebuffernano, i))
            {
                continue; /* Loop/switch isn't completed */
            }
            // fall through

        case 0: // '\0'
            return this;

        case 8: // '\b'
            durationMs = Long.valueOf(codedinputbytebuffernano.readInt64());
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            j = codedinputbytebuffernano.bufferPos;
            k = codedinputbytebuffernano.bufferStart;
            break;
        }
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l < 0 || l > 3)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            endStatus = l;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(41)).append(l).append(" is not a valid enum EndStatus").toString());
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (durationMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, durationMs.longValue());
        }
        j = i;
        if (endStatus != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, endStatus);
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
        if (durationMs != null)
        {
            codedoutputbytebuffernano.writeInt64(1, durationMs.longValue());
        }
        if (endStatus != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, endStatus);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
