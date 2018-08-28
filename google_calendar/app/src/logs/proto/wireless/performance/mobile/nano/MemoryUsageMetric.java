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
//            MemoryStats, ProcessStats, DeviceStats

public final class MemoryUsageMetric extends ExtendableMessageNano
{

    public String activityName;
    public DeviceStats deviceStats;
    public int memoryEventCode;
    public MemoryStats memoryStats;
    public ProcessStats processStats;

    public MemoryUsageMetric()
    {
        memoryStats = null;
        processStats = null;
        memoryEventCode = 0x80000000;
        deviceStats = null;
        activityName = null;
        cachedSize = -1;
    }

    private final MemoryUsageMetric mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L9:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 6: default 64
    //                   0: 73
    //                   10: 75
    //                   18: 104
    //                   24: 133
    //                   34: 226
    //                   42: 255;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        if (memoryStats == null)
        {
            memoryStats = new MemoryStats();
        }
        codedinputbytebuffernano.readMessage(memoryStats);
        continue; /* Loop/switch isn't completed */
_L4:
        if (processStats == null)
        {
            processStats = new ProcessStats();
        }
        codedinputbytebuffernano.readMessage(processStats);
        continue; /* Loop/switch isn't completed */
_L5:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 6)
        {
            try
            {
                memoryEventCode = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(47)).append(l).append(" is not a valid enum MemoryEventCode").toString());
_L6:
        if (deviceStats == null)
        {
            deviceStats = new DeviceStats();
        }
        codedinputbytebuffernano.readMessage(deviceStats);
        continue; /* Loop/switch isn't completed */
_L7:
        activityName = codedinputbytebuffernano.readString();
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (memoryStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, memoryStats);
        }
        j = i;
        if (processStats != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, processStats);
        }
        int k = j;
        if (memoryEventCode != 0x80000000)
        {
            k = j + CodedOutputByteBufferNano.computeInt32Size(3, memoryEventCode);
        }
        i = k;
        if (deviceStats != null)
        {
            i = k + CodedOutputByteBufferNano.computeMessageSize(4, deviceStats);
        }
        j = i;
        if (activityName != null)
        {
            String s = activityName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(40);
            int l = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
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
        if (memoryStats != null)
        {
            codedoutputbytebuffernano.writeMessage(1, memoryStats);
        }
        if (processStats != null)
        {
            codedoutputbytebuffernano.writeMessage(2, processStats);
        }
        if (memoryEventCode != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(3, memoryEventCode);
        }
        if (deviceStats != null)
        {
            codedoutputbytebuffernano.writeMessage(4, deviceStats);
        }
        if (activityName != null)
        {
            codedoutputbytebuffernano.writeString(5, activityName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
