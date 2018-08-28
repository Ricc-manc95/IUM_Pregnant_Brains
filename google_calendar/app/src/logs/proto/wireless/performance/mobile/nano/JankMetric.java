// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            HistogramBucket

public final class JankMetric extends ExtendableMessageNano
{

    public Integer deviceRefreshRate;
    public Integer durationMs;
    public HistogramBucket frameTimeHistogram[];
    public Integer jankyFrameCount;
    public Integer maxFrameRenderTimeMs;
    public Integer renderedFrameCount;

    public JankMetric()
    {
        jankyFrameCount = null;
        renderedFrameCount = null;
        maxFrameRenderTimeMs = null;
        durationMs = null;
        frameTimeHistogram = HistogramBucket.emptyArray();
        deviceRefreshRate = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (jankyFrameCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, jankyFrameCount.intValue());
        }
        j = i;
        if (renderedFrameCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, renderedFrameCount.intValue());
        }
        int k = j;
        if (maxFrameRenderTimeMs != null)
        {
            k = j + CodedOutputByteBufferNano.computeInt32Size(3, maxFrameRenderTimeMs.intValue());
        }
        i = k;
        if (durationMs != null)
        {
            i = k + CodedOutputByteBufferNano.computeInt32Size(4, durationMs.intValue());
        }
        j = i;
        if (frameTimeHistogram != null)
        {
            j = i;
            if (frameTimeHistogram.length > 0)
            {
                for (j = 0; j < frameTimeHistogram.length;)
                {
                    HistogramBucket histogrambucket = frameTimeHistogram[j];
                    int l = i;
                    if (histogrambucket != null)
                    {
                        l = i + CodedOutputByteBufferNano.computeMessageSize(5, histogrambucket);
                    }
                    j++;
                    i = l;
                }

                j = i;
            }
        }
        i = j;
        if (deviceRefreshRate != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(6, deviceRefreshRate.intValue());
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
                jankyFrameCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                renderedFrameCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                maxFrameRenderTimeMs = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 32: // ' '
                durationMs = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 42: // '*'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 42);
                HistogramBucket ahistogrambucket[];
                int j;
                if (frameTimeHistogram == null)
                {
                    j = 0;
                } else
                {
                    j = frameTimeHistogram.length;
                }
                ahistogrambucket = new HistogramBucket[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(frameTimeHistogram, 0, ahistogrambucket, 0, j);
                    k = j;
                }
                for (; k < ahistogrambucket.length - 1; k++)
                {
                    ahistogrambucket[k] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket[k]);
                    codedinputbytebuffernano.readTag();
                }

                ahistogrambucket[k] = new HistogramBucket();
                codedinputbytebuffernano.readMessage(ahistogrambucket[k]);
                frameTimeHistogram = ahistogrambucket;
                break;

            case 48: // '0'
                deviceRefreshRate = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (jankyFrameCount != null)
        {
            codedoutputbytebuffernano.writeInt32(1, jankyFrameCount.intValue());
        }
        if (renderedFrameCount != null)
        {
            codedoutputbytebuffernano.writeInt32(2, renderedFrameCount.intValue());
        }
        if (maxFrameRenderTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt32(3, maxFrameRenderTimeMs.intValue());
        }
        if (durationMs != null)
        {
            codedoutputbytebuffernano.writeInt32(4, durationMs.intValue());
        }
        if (frameTimeHistogram != null && frameTimeHistogram.length > 0)
        {
            for (int i = 0; i < frameTimeHistogram.length; i++)
            {
                HistogramBucket histogrambucket = frameTimeHistogram[i];
                if (histogrambucket != null)
                {
                    codedoutputbytebuffernano.writeMessage(5, histogrambucket);
                }
            }

        }
        if (deviceRefreshRate != null)
        {
            codedoutputbytebuffernano.writeInt32(6, deviceRefreshRate.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
