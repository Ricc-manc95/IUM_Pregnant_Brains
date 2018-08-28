// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class PrimesStats extends ExtendableMessageNano
{

    public Integer estimatedCount;
    public PrimesDebugMessage primesDebugMessage;
    public int primesEvent;

    public PrimesStats()
    {
        primesEvent = 0x80000000;
        estimatedCount = null;
        primesDebugMessage = null;
        cachedSize = -1;
    }

    private final PrimesStats mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L7:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   0: 57
    //                   8: 59
    //                   16: 151
    //                   26: 165;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 3)
        {
            try
            {
                primesEvent = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(43)).append(l).append(" is not a valid enum PrimesEvent").toString());
_L4:
        estimatedCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L5:
        if (primesDebugMessage == null)
        {
            primesDebugMessage = new PrimesDebugMessage();
        }
        codedinputbytebuffernano.readMessage(primesDebugMessage);
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (primesEvent != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, primesEvent);
        }
        j = i;
        if (estimatedCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, estimatedCount.intValue());
        }
        i = j;
        if (primesDebugMessage != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, primesDebugMessage);
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
        if (primesEvent != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, primesEvent);
        }
        if (estimatedCount != null)
        {
            codedoutputbytebuffernano.writeInt32(2, estimatedCount.intValue());
        }
        if (primesDebugMessage != null)
        {
            codedoutputbytebuffernano.writeMessage(3, primesDebugMessage);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    private class PrimesDebugMessage extends ExtendableMessageNano
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

        public PrimesDebugMessage()
        {
            previousCrash = null;
            primesHeapDumpEvent = null;
            primesHeapDumpCalibrationStatus = null;
            cachedSize = -1;
        }
    }

}
