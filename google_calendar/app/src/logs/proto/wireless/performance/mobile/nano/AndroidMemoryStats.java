// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class AndroidMemoryStats extends ExtendableMessageNano
{

    public Integer availableMemoryKb;
    public Integer dalvikPrivateDirtyKb;
    public Integer dalvikPssKb;
    public Integer nativePrivateDirtyKb;
    public Integer nativePssKb;
    public Integer otherGraphicsPssKb;
    public Integer otherPrivateDirtyKb;
    public Integer otherPssKb;
    public Integer summaryCodeKb;
    public Integer summaryGraphicsKb;
    public Integer summaryJavaHeapKb;
    public Integer summaryPrivateOtherKb;
    public Integer summaryStackKb;
    public Integer summarySystemKb;
    public Integer totalMemoryMb;
    public Integer totalPrivateCleanKb;
    public Integer totalPssByMemInfoKb;
    public Integer totalSharedDirtyKb;
    public Integer totalSwappablePssKb;

    public AndroidMemoryStats()
    {
        dalvikPssKb = null;
        nativePssKb = null;
        otherPssKb = null;
        dalvikPrivateDirtyKb = null;
        nativePrivateDirtyKb = null;
        otherPrivateDirtyKb = null;
        totalPssByMemInfoKb = null;
        totalPrivateCleanKb = null;
        totalSharedDirtyKb = null;
        totalSwappablePssKb = null;
        otherGraphicsPssKb = null;
        summaryJavaHeapKb = null;
        summaryCodeKb = null;
        summaryStackKb = null;
        summaryGraphicsKb = null;
        summaryPrivateOtherKb = null;
        summarySystemKb = null;
        availableMemoryKb = null;
        totalMemoryMb = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (dalvikPssKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, dalvikPssKb.intValue());
        }
        j = i;
        if (nativePssKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, nativePssKb.intValue());
        }
        i = j;
        if (otherPssKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, otherPssKb.intValue());
        }
        j = i;
        if (dalvikPrivateDirtyKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, dalvikPrivateDirtyKb.intValue());
        }
        i = j;
        if (nativePrivateDirtyKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, nativePrivateDirtyKb.intValue());
        }
        j = i;
        if (otherPrivateDirtyKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(6, otherPrivateDirtyKb.intValue());
        }
        i = j;
        if (totalPrivateCleanKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(7, totalPrivateCleanKb.intValue());
        }
        j = i;
        if (totalSharedDirtyKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(8, totalSharedDirtyKb.intValue());
        }
        i = j;
        if (totalSwappablePssKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(9, totalSwappablePssKb.intValue());
        }
        j = i;
        if (otherGraphicsPssKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(10, otherGraphicsPssKb.intValue());
        }
        i = j;
        if (summaryJavaHeapKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(11, summaryJavaHeapKb.intValue());
        }
        j = i;
        if (summaryCodeKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(12, summaryCodeKb.intValue());
        }
        i = j;
        if (summaryStackKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(13, summaryStackKb.intValue());
        }
        j = i;
        if (summaryGraphicsKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(14, summaryGraphicsKb.intValue());
        }
        i = j;
        if (summaryPrivateOtherKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(15, summaryPrivateOtherKb.intValue());
        }
        j = i;
        if (summarySystemKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(16, summarySystemKb.intValue());
        }
        i = j;
        if (availableMemoryKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(17, availableMemoryKb.intValue());
        }
        j = i;
        if (totalMemoryMb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(18, totalMemoryMb.intValue());
        }
        i = j;
        if (totalPssByMemInfoKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(19, totalPssByMemInfoKb.intValue());
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
                dalvikPssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                nativePssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                otherPssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 32: // ' '
                dalvikPrivateDirtyKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 40: // '('
                nativePrivateDirtyKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 48: // '0'
                otherPrivateDirtyKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 56: // '8'
                totalPrivateCleanKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 64: // '@'
                totalSharedDirtyKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 72: // 'H'
                totalSwappablePssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 80: // 'P'
                otherGraphicsPssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 88: // 'X'
                summaryJavaHeapKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 96: // '`'
                summaryCodeKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 104: // 'h'
                summaryStackKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 112: // 'p'
                summaryGraphicsKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 120: // 'x'
                summaryPrivateOtherKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 128: 
                summarySystemKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 136: 
                availableMemoryKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 144: 
                totalMemoryMb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 152: 
                totalPssByMemInfoKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (dalvikPssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(1, dalvikPssKb.intValue());
        }
        if (nativePssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(2, nativePssKb.intValue());
        }
        if (otherPssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(3, otherPssKb.intValue());
        }
        if (dalvikPrivateDirtyKb != null)
        {
            codedoutputbytebuffernano.writeInt32(4, dalvikPrivateDirtyKb.intValue());
        }
        if (nativePrivateDirtyKb != null)
        {
            codedoutputbytebuffernano.writeInt32(5, nativePrivateDirtyKb.intValue());
        }
        if (otherPrivateDirtyKb != null)
        {
            codedoutputbytebuffernano.writeInt32(6, otherPrivateDirtyKb.intValue());
        }
        if (totalPrivateCleanKb != null)
        {
            codedoutputbytebuffernano.writeInt32(7, totalPrivateCleanKb.intValue());
        }
        if (totalSharedDirtyKb != null)
        {
            codedoutputbytebuffernano.writeInt32(8, totalSharedDirtyKb.intValue());
        }
        if (totalSwappablePssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(9, totalSwappablePssKb.intValue());
        }
        if (otherGraphicsPssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(10, otherGraphicsPssKb.intValue());
        }
        if (summaryJavaHeapKb != null)
        {
            codedoutputbytebuffernano.writeInt32(11, summaryJavaHeapKb.intValue());
        }
        if (summaryCodeKb != null)
        {
            codedoutputbytebuffernano.writeInt32(12, summaryCodeKb.intValue());
        }
        if (summaryStackKb != null)
        {
            codedoutputbytebuffernano.writeInt32(13, summaryStackKb.intValue());
        }
        if (summaryGraphicsKb != null)
        {
            codedoutputbytebuffernano.writeInt32(14, summaryGraphicsKb.intValue());
        }
        if (summaryPrivateOtherKb != null)
        {
            codedoutputbytebuffernano.writeInt32(15, summaryPrivateOtherKb.intValue());
        }
        if (summarySystemKb != null)
        {
            codedoutputbytebuffernano.writeInt32(16, summarySystemKb.intValue());
        }
        if (availableMemoryKb != null)
        {
            codedoutputbytebuffernano.writeInt32(17, availableMemoryKb.intValue());
        }
        if (totalMemoryMb != null)
        {
            codedoutputbytebuffernano.writeInt32(18, totalMemoryMb.intValue());
        }
        if (totalPssByMemInfoKb != null)
        {
            codedoutputbytebuffernano.writeInt32(19, totalPssByMemInfoKb.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
