// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            CrashMetric, PrimesHeapDumpEvent, PrimesHeapDumpCalibrationStatus

public final class cachedSize extends ExtendableMessageNano
{

    public CrashMetric previousCrash;
    public PrimesHeapDumpCalibrationStatus primesHeapDumpCalibrationStatus;
    public PrimesHeapDumpEvent primesHeapDumpEvent;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (previousCrash != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, previousCrash);
        }
        j = i;
        if (primesHeapDumpEvent != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, primesHeapDumpEvent);
        }
        i = j;
        if (primesHeapDumpCalibrationStatus != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, primesHeapDumpCalibrationStatus);
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
                if (previousCrash == null)
                {
                    previousCrash = new CrashMetric();
                }
                codedinputbytebuffernano.readMessage(previousCrash);
                break;

            case 18: // '\022'
                if (primesHeapDumpEvent == null)
                {
                    primesHeapDumpEvent = new PrimesHeapDumpEvent();
                }
                codedinputbytebuffernano.readMessage(primesHeapDumpEvent);
                break;

            case 26: // '\032'
                if (primesHeapDumpCalibrationStatus == null)
                {
                    primesHeapDumpCalibrationStatus = new PrimesHeapDumpCalibrationStatus();
                }
                codedinputbytebuffernano.readMessage(primesHeapDumpCalibrationStatus);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (previousCrash != null)
        {
            codedoutputbytebuffernano.writeMessage(1, previousCrash);
        }
        if (primesHeapDumpEvent != null)
        {
            codedoutputbytebuffernano.writeMessage(2, primesHeapDumpEvent);
        }
        if (primesHeapDumpCalibrationStatus != null)
        {
            codedoutputbytebuffernano.writeMessage(3, primesHeapDumpCalibrationStatus);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        previousCrash = null;
        primesHeapDumpEvent = null;
        primesHeapDumpCalibrationStatus = null;
        cachedSize = -1;
    }
}
