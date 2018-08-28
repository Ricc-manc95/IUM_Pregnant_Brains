// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private static volatile minutes _emptyArray[];
    public int method;
    public int minutes;

    public static cachedSize[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new _emptyArray[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final _emptyArray mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L6:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   0: 49
    //                   8: 51
    //                   16: 144;
           goto _L1 _L2 _L3 _L4
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
        if (l >= 2 && l <= 2)
        {
            try
            {
                method = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(38)).append(l).append(" is not a valid enum Method").toString());
_L4:
        minutes = codedinputbytebuffernano.readRawVarint32();
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final int computeSerializedSize()
    {
        byte byte0 = 10;
        int j = super.computeSerializedSize();
        int i;
        if (method != 2)
        {
            i = method;
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int l;
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            i = i + k + j;
        } else
        {
            i = j;
        }
        j = i;
        if (minutes != 0)
        {
            l = minutes;
            k = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            j = byte0;
            if (l >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(l);
            }
            j = i + (j + k);
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
        int i;
        if (method != 2)
        {
            i = method;
            codedoutputbytebuffernano.writeRawVarint32(8);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (minutes != 0)
        {
            i = minutes;
            codedoutputbytebuffernano.writeRawVarint32(16);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public ()
    {
        method = 2;
        minutes = 0;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
