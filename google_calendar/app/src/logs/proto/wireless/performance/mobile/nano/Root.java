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

public final class Root extends ExtendableMessageNano
{

    private static volatile Root _emptyArray[];
    public int nodes[];
    public int tag;

    public Root()
    {
        tag = 0x80000000;
        nodes = WireFormatNano.EMPTY_INT_ARRAY;
        cachedSize = -1;
    }

    public static Root[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new Root[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final Root mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L10:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   0: 57
    //                   8: 59
    //                   16: 200
    //                   18: 301;
           goto _L1 _L2 _L3 _L4 _L5
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
        while (i1 >= 0 && i1 <= 8 || i1 >= 137 && i1 <= 142 || i1 >= 144 && i1 <= 144 || i1 >= 255 && i1 <= 255) 
        {
            try
            {
                tag = i1;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(k - l, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(39)).append(i1).append(" is not a valid enum RootTag").toString());
_L4:
        k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 16);
        int ai[];
        if (nodes == null)
        {
            i = 0;
        } else
        {
            i = nodes.length;
        }
        ai = new int[k + i];
        k = i;
        if (i != 0)
        {
            System.arraycopy(nodes, 0, ai, 0, i);
            k = i;
        }
        for (; k < ai.length - 1; k++)
        {
            ai[k] = codedinputbytebuffernano.readRawVarint32();
            codedinputbytebuffernano.readTag();
        }

        ai[k] = codedinputbytebuffernano.readRawVarint32();
        nodes = ai;
        continue; /* Loop/switch isn't completed */
_L5:
        int j1;
        l = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
        i1 = codedinputbytebuffernano.bufferPos;
        j1 = codedinputbytebuffernano.bufferStart;
        k = 0;
_L8:
        if (codedinputbytebuffernano.currentLimit == 0x7fffffff)
        {
            i = -1;
        } else
        {
            i = codedinputbytebuffernano.bufferPos;
            i = codedinputbytebuffernano.currentLimit - i;
        }
        if (i <= 0) goto _L7; else goto _L6
_L6:
        codedinputbytebuffernano.readRawVarint32();
        k++;
          goto _L8
_L7:
        codedinputbytebuffernano.rewindToPositionAndTag(i1 - j1, codedinputbytebuffernano.lastTag);
        int ai1[];
        int j;
        if (nodes == null)
        {
            j = 0;
        } else
        {
            j = nodes.length;
        }
        ai1 = new int[k + j];
        k = j;
        if (j != 0)
        {
            System.arraycopy(nodes, 0, ai1, 0, j);
            k = j;
        }
        for (; k < ai1.length; k++)
        {
            ai1[k] = codedinputbytebuffernano.readRawVarint32();
        }

        nodes = ai1;
        codedinputbytebuffernano.currentLimit = l;
        codedinputbytebuffernano.recomputeBufferSizeAfterLimit();
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (tag != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, tag);
        }
        j = i;
        if (nodes != null)
        {
            j = i;
            if (nodes.length > 0)
            {
                int k = 0;
                j = ((flag) ? 1 : 0);
                while (j < nodes.length) 
                {
                    int l = nodes[j];
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
                j = i + k + nodes.length * 1;
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
        if (tag != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, tag);
        }
        if (nodes != null && nodes.length > 0)
        {
            for (int i = 0; i < nodes.length; i++)
            {
                codedoutputbytebuffernano.writeInt32(2, nodes[i]);
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
