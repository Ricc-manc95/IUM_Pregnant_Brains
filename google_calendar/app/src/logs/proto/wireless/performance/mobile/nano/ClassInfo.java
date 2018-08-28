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

public final class ClassInfo extends ExtendableMessageNano
{

    private static volatile ClassInfo _emptyArray[];
    public String className;
    public Integer instanceSize;
    public Integer superClass;
    public int values[];

    public ClassInfo()
    {
        superClass = null;
        className = null;
        values = WireFormatNano.EMPTY_INT_ARRAY;
        instanceSize = null;
        cachedSize = -1;
    }

    public static ClassInfo[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new ClassInfo[0];
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
        int i = super.computeSerializedSize();
        int j = i;
        if (superClass != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(1, superClass.intValue());
        }
        i = j;
        if (className != null)
        {
            String s = className;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (values != null)
        {
            j = i;
            if (values.length > 0)
            {
                int l = 0;
                j = ((flag) ? 1 : 0);
                while (j < values.length) 
                {
                    int i1 = values[j];
                    if (i1 >= 0)
                    {
                        i1 = CodedOutputByteBufferNano.computeRawVarint32Size(i1);
                    } else
                    {
                        i1 = 10;
                    }
                    l += i1;
                    j++;
                }
                j = i + l + values.length * 1;
            }
        }
        i = j;
        if (instanceSize != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(6, instanceSize.intValue());
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
label0:
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
                superClass = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                continue;

            case 18: // '\022'
                className = codedinputbytebuffernano.readString();
                continue;

            case 40: // '('
                int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 40);
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
                continue;

            case 42: // '*'
                int k1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                int l1 = codedinputbytebuffernano.bufferPos;
                int i2 = codedinputbytebuffernano.bufferStart;
                int j1 = 0;
                do
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
                    if (k > 0)
                    {
                        codedinputbytebuffernano.readRawVarint32();
                        j1++;
                    } else
                    {
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
                        continue label0;
                    }
                } while (true);

            case 48: // '0'
                instanceSize = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (superClass != null)
        {
            codedoutputbytebuffernano.writeInt32(1, superClass.intValue());
        }
        if (className != null)
        {
            codedoutputbytebuffernano.writeString(2, className);
        }
        if (values != null && values.length > 0)
        {
            for (int i = 0; i < values.length; i++)
            {
                codedoutputbytebuffernano.writeInt32(5, values[i]);
            }

        }
        if (instanceSize != null)
        {
            codedoutputbytebuffernano.writeInt32(6, instanceSize.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
