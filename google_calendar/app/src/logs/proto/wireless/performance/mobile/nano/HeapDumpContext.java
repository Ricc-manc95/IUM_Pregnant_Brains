// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class HeapDumpContext extends ExtendableMessageNano
{

    private Long allocatedBytes;
    private Long garbageCollectedBytes;
    public Integer totalPssKb;
    public int triggerType;

    public HeapDumpContext()
    {
        triggerType = 0x80000000;
        totalPssKb = null;
        allocatedBytes = null;
        garbageCollectedBytes = null;
        cachedSize = -1;
    }

    private final HeapDumpContext mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L8:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 5: default 56
    //                   0: 65
    //                   8: 67
    //                   16: 159
    //                   24: 173
    //                   32: 187;
           goto _L1 _L2 _L3 _L4 _L5 _L6
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
        if (l >= 0 && l <= 2)
        {
            try
            {
                triggerType = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(43)).append(l).append(" is not a valid enum TriggerType").toString());
_L4:
        totalPssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L5:
        allocatedBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L6:
        garbageCollectedBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (triggerType != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, triggerType);
        }
        j = i;
        if (totalPssKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, totalPssKb.intValue());
        }
        i = j;
        if (allocatedBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, allocatedBytes.longValue());
        }
        j = i;
        if (garbageCollectedBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(4, garbageCollectedBytes.longValue());
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
        if (triggerType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, triggerType);
        }
        if (totalPssKb != null)
        {
            codedoutputbytebuffernano.writeInt32(2, totalPssKb.intValue());
        }
        if (allocatedBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(3, allocatedBytes.longValue());
        }
        if (garbageCollectedBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(4, garbageCollectedBytes.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
