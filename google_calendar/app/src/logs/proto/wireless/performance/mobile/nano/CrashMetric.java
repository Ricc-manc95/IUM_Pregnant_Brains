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
//            ProcessStats

public final class CrashMetric extends ExtendableMessageNano
{

    public String activeComponentName;
    public String crashClassName;
    public int crashType;
    public Boolean hasCrashed;
    public Long hashedStackTrace;
    public ProcessStats processStats;
    public String threadName;

    public CrashMetric()
    {
        hasCrashed = null;
        processStats = null;
        activeComponentName = null;
        threadName = null;
        crashType = 0x80000000;
        hashedStackTrace = null;
        crashClassName = null;
        cachedSize = -1;
    }

    private final CrashMetric mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L11:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 8: default 80
    //                   0: 89
    //                   8: 91
    //                   18: 119
    //                   26: 148
    //                   34: 159
    //                   40: 170
    //                   49: 262
    //                   58: 276;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        boolean flag;
        if (codedinputbytebuffernano.readRawVarint32() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hasCrashed = Boolean.valueOf(flag);
        continue; /* Loop/switch isn't completed */
_L4:
        if (processStats == null)
        {
            processStats = new ProcessStats();
        }
        codedinputbytebuffernano.readMessage(processStats);
        continue; /* Loop/switch isn't completed */
_L5:
        activeComponentName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L6:
        threadName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L7:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 4)
        {
            try
            {
                crashType = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(41)).append(l).append(" is not a valid enum CrashType").toString());
_L8:
        hashedStackTrace = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        continue; /* Loop/switch isn't completed */
_L9:
        crashClassName = codedinputbytebuffernano.readString();
        if (true) goto _L11; else goto _L10
_L10:
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int j = i;
        if (hasCrashed != null)
        {
            hasCrashed.booleanValue();
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        i = j;
        if (processStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(2, processStats);
        }
        j = i;
        if (activeComponentName != null)
        {
            String s = activeComponentName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        i = j;
        if (threadName != null)
        {
            String s1 = threadName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
        }
        j = i;
        if (crashType != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(5, crashType);
        }
        i = j;
        if (hashedStackTrace != null)
        {
            hashedStackTrace.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(48) + 8);
        }
        j = i;
        if (crashClassName != null)
        {
            String s2 = crashClassName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            int i1 = CodedOutputByteBufferNano.encodedLength(s2);
            j = i + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + j);
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
        if (hasCrashed != null)
        {
            codedoutputbytebuffernano.writeBool(1, hasCrashed.booleanValue());
        }
        if (processStats != null)
        {
            codedoutputbytebuffernano.writeMessage(2, processStats);
        }
        if (activeComponentName != null)
        {
            codedoutputbytebuffernano.writeString(3, activeComponentName);
        }
        if (threadName != null)
        {
            codedoutputbytebuffernano.writeString(4, threadName);
        }
        if (crashType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(5, crashType);
        }
        if (hashedStackTrace != null)
        {
            codedoutputbytebuffernano.writeFixed64(6, hashedStackTrace.longValue());
        }
        if (crashClassName != null)
        {
            codedoutputbytebuffernano.writeString(7, crashClassName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
