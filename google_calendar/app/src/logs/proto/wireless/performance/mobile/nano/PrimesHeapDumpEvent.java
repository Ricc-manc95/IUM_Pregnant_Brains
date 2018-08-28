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

public final class PrimesHeapDumpEvent extends ExtendableMessageNano
{

    public int error;
    public Integer serializedSizeKb;
    public int totalPssKbSamples[];

    public PrimesHeapDumpEvent()
    {
        error = 0x80000000;
        serializedSizeKb = null;
        totalPssKbSamples = WireFormatNano.EMPTY_INT_ARRAY;
        cachedSize = -1;
    }

    private final PrimesHeapDumpEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L11:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 5: default 56
    //                   0: 65
    //                   8: 67
    //                   16: 159
    //                   24: 173
    //                   26: 274;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int k;
        int l;
        k = codedinputbytebuffernano.bufferPos;
        l = codedinputbytebuffernano.bufferStart;
        int i1 = codedinputbytebuffernano.readRawVarint32();
        if (i1 >= 0 && i1 <= 4)
        {
            try
            {
                error = i1;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(k - l, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(51)).append(i1).append(" is not a valid enum PrimesHeapDumpError").toString());
_L4:
        serializedSizeKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
        continue; /* Loop/switch isn't completed */
_L5:
        k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 24);
        int ai[];
        if (totalPssKbSamples == null)
        {
            i = 0;
        } else
        {
            i = totalPssKbSamples.length;
        }
        ai = new int[k + i];
        k = i;
        if (i != 0)
        {
            System.arraycopy(totalPssKbSamples, 0, ai, 0, i);
            k = i;
        }
        for (; k < ai.length - 1; k++)
        {
            ai[k] = codedinputbytebuffernano.readRawVarint32();
            codedinputbytebuffernano.readTag();
        }

        ai[k] = codedinputbytebuffernano.readRawVarint32();
        totalPssKbSamples = ai;
        continue; /* Loop/switch isn't completed */
_L6:
        int j1;
        l = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
        i1 = codedinputbytebuffernano.bufferPos;
        j1 = codedinputbytebuffernano.bufferStart;
        k = 0;
_L9:
        if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
        {
            i = -1;
        } else
        {
            i = codedinputbytebuffernano.bufferPos;
            i = codedinputbytebuffernano.currentLimit - i;
        }
        if (i <= 0) goto _L8; else goto _L7
_L7:
        codedinputbytebuffernano.readRawVarint32();
        k++;
          goto _L9
_L8:
        codedinputbytebuffernano.rewindToPositionAndTag(i1 - j1, codedinputbytebuffernano.lastTag);
        int ai1[];
        int j;
        if (totalPssKbSamples == null)
        {
            j = 0;
        } else
        {
            j = totalPssKbSamples.length;
        }
        ai1 = new int[k + j];
        k = j;
        if (j != 0)
        {
            System.arraycopy(totalPssKbSamples, 0, ai1, 0, j);
            k = j;
        }
        for (; k < ai1.length; k++)
        {
            ai1[k] = codedinputbytebuffernano.readRawVarint32();
        }

        totalPssKbSamples = ai1;
        codedinputbytebuffernano.currentLimit = l;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        if (true) goto _L11; else goto _L10
_L10:
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int i = super.computeSerializedSize();
        int j = i;
        if (error != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(1, error);
        }
        i = j;
        if (serializedSizeKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(2, serializedSizeKb.intValue());
        }
        j = i;
        if (totalPssKbSamples != null)
        {
            j = i;
            if (totalPssKbSamples.length > 0)
            {
                int k = 0;
                j = ((flag) ? 1 : 0);
                while (j < totalPssKbSamples.length) 
                {
                    int l = totalPssKbSamples[j];
                    if (l >= 0)
                    {
                        l = CodedOutputByteBufferNano.computeRawVarint32Size(l);
                    } else
                    {
                        l = 10;
                    }
                    k += l;
                    j++;
                }
                j = i + k + totalPssKbSamples.length * 1;
            }
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
        if (error != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, error);
        }
        if (serializedSizeKb != null)
        {
            codedoutputbytebuffernano.writeInt32(2, serializedSizeKb.intValue());
        }
        if (totalPssKbSamples != null && totalPssKbSamples.length > 0)
        {
            for (int i = 0; i < totalPssKbSamples.length; i++)
            {
                codedoutputbytebuffernano.writeInt32(3, totalPssKbSamples[i]);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
