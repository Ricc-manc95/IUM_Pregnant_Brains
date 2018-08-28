// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class ClassInstance extends ExtendableMessageNano
{

    private static volatile ClassInstance _emptyArray[];
    public Integer clazz;
    public int values[];

    public ClassInstance()
    {
        clazz = null;
        values = WireFormatNano.EMPTY_INT_ARRAY;
        cachedSize = -1;
    }

    public static ClassInstance[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new ClassInstance[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (clazz != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, clazz.intValue());
        }
        j = i;
        if (values != null)
        {
            j = i;
            if (values.length > 0)
            {
                int k = 0;
                j = ((flag) ? 1 : 0);
                while (j < values.length) 
                {
                    int l = values[j];
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
                j = i + k + values.length * 1;
            }
        }
        return j;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int j1;
        int k1;
        int l1;
        int i2;
        int i = codedinputbytebuffernano.readTag();
        switch (i)
        {
        default:
            if (super.storeUnknownField(codedinputbytebuffernano, i))
            {
                continue; /* Loop/switch isn't completed */
            }
            // fall through

        case 0: // '\0'
            return this;

        case 8: // '\b'
            clazz = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 16);
            int ai[];
            int j;
            if (values == null)
            {
                j = 0;
            } else
            {
                j = values.length;
            }
            ai = new int[i1 + j];
            i1 = j;
            if (j != 0)
            {
                System.arraycopy(values, 0, ai, 0, j);
                i1 = j;
            }
            for (; i1 < ai.length - 1; i1++)
            {
                ai[i1] = codedinputbytebuffernano.readRawVarint32();
                codedinputbytebuffernano.readTag();
            }

            ai[i1] = codedinputbytebuffernano.readRawVarint32();
            values = ai;
            continue; /* Loop/switch isn't completed */

        case 18: // '\022'
            k1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
            l1 = codedinputbytebuffernano.bufferPos;
            i2 = codedinputbytebuffernano.bufferStart;
            j1 = 0;
            break;
        }
        do
        {
label0:
            {
                int k;
                if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
                {
                    k = -1;
                } else
                {
                    k = codedinputbytebuffernano.bufferPos;
                    k = codedinputbytebuffernano.currentLimit - k;
                }
                if (k <= 0)
                {
                    break label0;
                }
                codedinputbytebuffernano.readRawVarint32();
                j1++;
            }
        } while (true);
        codedinputbytebuffernano.rewindToPositionAndTag(l1 - i2, codedinputbytebuffernano.lastTag);
        int ai1[];
        int l;
        if (values == null)
        {
            l = 0;
        } else
        {
            l = values.length;
        }
        ai1 = new int[j1 + l];
        j1 = l;
        if (l != 0)
        {
            System.arraycopy(values, 0, ai1, 0, l);
            j1 = l;
        }
        for (; j1 < ai1.length; j1++)
        {
            ai1[j1] = codedinputbytebuffernano.readRawVarint32();
        }

        values = ai1;
        codedinputbytebuffernano.currentLimit = k1;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (clazz != null)
        {
            codedoutputbytebuffernano.writeInt32(1, clazz.intValue());
        }
        if (values != null && values.length > 0)
        {
            for (int i = 0; i < values.length; i++)
            {
                codedoutputbytebuffernano.writeInt32(2, values[i]);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
