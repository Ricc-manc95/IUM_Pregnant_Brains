// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class PrimesHeapDumpCalibrationStatus extends ExtendableMessageNano
{

    public Integer currentSampleCount;
    public Float newSamplePercentile;

    public PrimesHeapDumpCalibrationStatus()
    {
        currentSampleCount = null;
        newSamplePercentile = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (currentSampleCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, currentSampleCount.intValue());
        }
        j = i;
        if (newSamplePercentile != null)
        {
            newSamplePercentile.floatValue();
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 4);
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
                currentSampleCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 21: // '\025'
                newSamplePercentile = Float.valueOf(Float.intBitsToFloat(codedinputbytebuffernano.readRawLittleEndian32()));
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (currentSampleCount != null)
        {
            codedoutputbytebuffernano.writeInt32(1, currentSampleCount.intValue());
        }
        if (newSamplePercentile != null)
        {
            float f = newSamplePercentile.floatValue();
            codedoutputbytebuffernano.writeRawVarint32(21);
            int i = Float.floatToIntBits(f);
            if (codedoutputbytebuffernano.buffer.remaining() < 4)
            {
                throw new com.google.protobuf.nano.CodedOutputByteBufferNano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
            codedoutputbytebuffernano.buffer.putInt(i);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
