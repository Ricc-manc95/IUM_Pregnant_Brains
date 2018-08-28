// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class ApplicationInfo extends ExtendableMessageNano
{

    public String applicationPackage;
    public String applicationVersionName;
    public int hardwareVariant;
    public Long primesVersion;
    public String shortProcessName;

    public ApplicationInfo()
    {
        applicationPackage = null;
        applicationVersionName = null;
        hardwareVariant = 0x80000000;
        primesVersion = null;
        shortProcessName = null;
        cachedSize = -1;
    }

    private final ApplicationInfo mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L9:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 6: default 64
    //                   0: 73
    //                   10: 75
    //                   18: 86
    //                   24: 97
    //                   32: 189
    //                   42: 203;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        applicationPackage = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L4:
        applicationVersionName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L5:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 3)
        {
            try
            {
                hardwareVariant = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(47)).append(l).append(" is not a valid enum HardwareVariant").toString());
_L6:
        primesVersion = Long.valueOf(codedinputbytebuffernano.readInt64());
        continue; /* Loop/switch isn't completed */
_L7:
        shortProcessName = codedinputbytebuffernano.readString();
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (applicationPackage != null)
        {
            String s = applicationPackage;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (applicationVersionName != null)
        {
            String s1 = applicationVersionName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        int i1 = j;
        if (hardwareVariant != 0x80000000)
        {
            i1 = j + CodedOutputByteBufferNano.computeInt32Size(3, hardwareVariant);
        }
        i = i1;
        if (primesVersion != null)
        {
            i = i1 + CodedOutputByteBufferNano.computeInt64Size(4, primesVersion.longValue());
        }
        j = i;
        if (shortProcessName != null)
        {
            String s2 = shortProcessName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(40);
            int j1 = CodedOutputByteBufferNano.encodedLength(s2);
            j = i + (j1 + CodedOutputByteBufferNano.computeRawVarint32Size(j1) + j);
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
        if (applicationPackage != null)
        {
            codedoutputbytebuffernano.writeString(1, applicationPackage);
        }
        if (applicationVersionName != null)
        {
            codedoutputbytebuffernano.writeString(2, applicationVersionName);
        }
        if (hardwareVariant != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(3, hardwareVariant);
        }
        if (primesVersion != null)
        {
            codedoutputbytebuffernano.writeInt64(4, primesVersion.longValue());
        }
        if (shortProcessName != null)
        {
            codedoutputbytebuffernano.writeString(5, shortProcessName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
