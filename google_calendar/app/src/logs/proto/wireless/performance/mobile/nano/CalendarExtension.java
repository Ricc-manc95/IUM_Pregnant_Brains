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

public final class CalendarExtension extends ExtendableMessageNano
{

    public int activeExperiments[];
    public String remoteFeatureConfig[];
    public Integer resultCode;
    public Integer resultSource;
    public int resultStatus;

    public CalendarExtension()
    {
        activeExperiments = WireFormatNano.EMPTY_INT_ARRAY;
        resultStatus = 0x80000000;
        resultSource = null;
        resultCode = null;
        remoteFeatureConfig = WireFormatNano.EMPTY_STRING_ARRAY;
        cachedSize = -1;
    }

    private final CalendarExtension mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L10:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 7: default 72
    //                   0: 81
    //                   8: 83
    //                   10: 184
    //                   16: 354
    //                   24: 446
    //                   32: 460
    //                   42: 474;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 8);
        int ai[];
        if (activeExperiments == null)
        {
            i = 0;
        } else
        {
            i = activeExperiments.length;
        }
        ai = new int[k + i];
        k = i;
        if (i != 0)
        {
            System.arraycopy(activeExperiments, 0, ai, 0, i);
            k = i;
        }
        for (; k < ai.length - 1; k++)
        {
            ai[k] = codedinputbytebuffernano.readRawVarint32();
            codedinputbytebuffernano.readTag();
        }

        ai[k] = codedinputbytebuffernano.readRawVarint32();
        activeExperiments = ai;
        continue; /* Loop/switch isn't completed */
_L4:
        int k1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
        int i2 = codedinputbytebuffernano.bufferPos;
        int k2 = codedinputbytebuffernano.bufferStart;
        int l = 0;
        do
        {
            if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
            {
                i = -1;
            } else
            {
                i = codedinputbytebuffernano.bufferPos;
                i = codedinputbytebuffernano.currentLimit - i;
            }
            if (i > 0)
            {
                codedinputbytebuffernano.readRawVarint32();
                l++;
            } else
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i2 - k2, codedinputbytebuffernano.lastTag);
                int ai1[];
                if (activeExperiments == null)
                {
                    i = 0;
                } else
                {
                    i = activeExperiments.length;
                }
                ai1 = new int[l + i];
                l = i;
                if (i != 0)
                {
                    System.arraycopy(activeExperiments, 0, ai1, 0, i);
                    l = i;
                }
                for (; l < ai1.length; l++)
                {
                    ai1[l] = codedinputbytebuffernano.readRawVarint32();
                }

                activeExperiments = ai1;
                codedinputbytebuffernano.currentLimit = k1;
                codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
                continue; /* Loop/switch isn't completed */
            }
        } while (true);
_L5:
        int i1;
        int l1;
        i1 = codedinputbytebuffernano.bufferPos;
        l1 = codedinputbytebuffernano.bufferStart;
        int j2 = codedinputbytebuffernano.readRawVarint32();
        if (j2 >= 0 && j2 <= 3)
        {
            try
            {
                resultStatus = j2;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(i1 - l1, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(44)).append(j2).append(" is not a valid enum ResultStatus").toString());
_L6:
        resultSource = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L7:
        resultCode = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L8:
        int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 42);
        String as[];
        int j;
        if (remoteFeatureConfig == null)
        {
            j = 0;
        } else
        {
            j = remoteFeatureConfig.length;
        }
        as = new String[j1 + j];
        j1 = j;
        if (j != 0)
        {
            System.arraycopy(remoteFeatureConfig, 0, as, 0, j);
            j1 = j;
        }
        for (; j1 < as.length - 1; j1++)
        {
            as[j1] = codedinputbytebuffernano.readString();
            codedinputbytebuffernano.readTag();
        }

        as[j1] = codedinputbytebuffernano.readString();
        remoteFeatureConfig = as;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j1 = super.computeSerializedSize();
        int j;
        int k;
        if (activeExperiments != null && activeExperiments.length > 0)
        {
            int i = 0;
            k = 0;
            while (i < activeExperiments.length) 
            {
                int l = activeExperiments[i];
                if (l >= 0)
                {
                    l = CodedOutputByteBufferNano.computeRawVarint32Size(l);
                } else
                {
                    l = 10;
                }
                k += l;
                i++;
            }
            k = j1 + k + activeExperiments.length * 1;
        } else
        {
            k = j1;
        }
        j = k;
        if (resultStatus != 0x80000000)
        {
            j = k + CodedOutputByteBufferNano.computeInt32Size(2, resultStatus);
        }
        k = j;
        if (resultSource != null)
        {
            k = j + CodedOutputByteBufferNano.computeInt32Size(3, resultSource.intValue());
        }
        j = k;
        if (resultCode != null)
        {
            j = k + CodedOutputByteBufferNano.computeInt32Size(4, resultCode.intValue());
        }
        k = j;
        if (remoteFeatureConfig != null)
        {
            k = j;
            if (remoteFeatureConfig.length > 0)
            {
                int i1 = 0;
                j1 = 0;
                for (k = ((flag) ? 1 : 0); k < remoteFeatureConfig.length;)
                {
                    String s = remoteFeatureConfig[k];
                    int l1 = i1;
                    int k1 = j1;
                    if (s != null)
                    {
                        k1 = j1 + 1;
                        j1 = CodedOutputByteBufferNano.encodedLength(s);
                        l1 = i1 + (j1 + CodedOutputByteBufferNano.computeRawVarint32Size(j1));
                    }
                    k++;
                    i1 = l1;
                    j1 = k1;
                }

                k = j + i1 + j1 * 1;
            }
        }
        return k;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (activeExperiments != null && activeExperiments.length > 0)
        {
            for (int i = 0; i < activeExperiments.length; i++)
            {
                codedoutputbytebuffernano.writeInt32(1, activeExperiments[i]);
            }

        }
        if (resultStatus != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, resultStatus);
        }
        if (resultSource != null)
        {
            codedoutputbytebuffernano.writeInt32(3, resultSource.intValue());
        }
        if (resultCode != null)
        {
            codedoutputbytebuffernano.writeInt32(4, resultCode.intValue());
        }
        if (remoteFeatureConfig != null && remoteFeatureConfig.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < remoteFeatureConfig.length; j++)
            {
                String s = remoteFeatureConfig[j];
                if (s != null)
                {
                    codedoutputbytebuffernano.writeString(5, s);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
