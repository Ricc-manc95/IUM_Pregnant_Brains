// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private String componentName;
    private String constantComponentName;
    private String constantCounterName;
    private String customCounterName;
    private Long hashedComponentName;
    private Long hashedCounterName;
    private int metric;

    private final cachedSize mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L11:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 8: default 80
    //                   0: 89
    //                   10: 91
    //                   16: 102
    //                   26: 195
    //                   34: 206
    //                   41: 217
    //                   50: 231
    //                   57: 242;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        componentName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 6)
        {
            try
            {
                metric = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(38)).append(l).append(" is not a valid enum Metric").toString());
_L5:
        customCounterName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L6:
        constantComponentName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L7:
        hashedComponentName = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        continue; /* Loop/switch isn't completed */
_L8:
        constantCounterName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L9:
        hashedCounterName = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
        if (true) goto _L11; else goto _L10
_L10:
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int j = i;
        if (componentName != null)
        {
            String s = componentName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        i = j;
        if (metric != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(2, metric);
        }
        j = i;
        if (customCounterName != null)
        {
            String s1 = customCounterName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        int i1 = j;
        if (constantComponentName != null)
        {
            String s2 = constantComponentName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            i1 = CodedOutputByteBufferNano.encodedLength(s2);
            i1 = j + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i);
        }
        i = i1;
        if (hashedComponentName != null)
        {
            hashedComponentName.longValue();
            i = i1 + (CodedOutputByteBufferNano.computeRawVarint32Size(40) + 8);
        }
        j = i;
        if (constantCounterName != null)
        {
            String s3 = constantCounterName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            int j1 = CodedOutputByteBufferNano.encodedLength(s3);
            j = i + (j1 + CodedOutputByteBufferNano.computeRawVarint32Size(j1) + j);
        }
        i = j;
        if (hashedCounterName != null)
        {
            hashedCounterName.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(56) + 8);
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
        if (componentName != null)
        {
            codedoutputbytebuffernano.writeString(1, componentName);
        }
        if (metric != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, metric);
        }
        if (customCounterName != null)
        {
            codedoutputbytebuffernano.writeString(3, customCounterName);
        }
        if (constantComponentName != null)
        {
            codedoutputbytebuffernano.writeString(4, constantComponentName);
        }
        if (hashedComponentName != null)
        {
            codedoutputbytebuffernano.writeFixed64(5, hashedComponentName.longValue());
        }
        if (constantCounterName != null)
        {
            codedoutputbytebuffernano.writeString(6, constantCounterName);
        }
        if (hashedCounterName != null)
        {
            codedoutputbytebuffernano.writeFixed64(7, hashedCounterName.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        componentName = null;
        constantComponentName = null;
        hashedComponentName = null;
        metric = 0x80000000;
        customCounterName = null;
        constantCounterName = null;
        hashedCounterName = null;
        cachedSize = -1;
    }
}
