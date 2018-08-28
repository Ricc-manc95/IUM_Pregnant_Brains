// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.proto.primes.persistent.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class MemorySample extends ExtendableMessageNano
{

    private static volatile MemorySample _emptyArray[];
    public Integer totalPssKb;

    public MemorySample()
    {
        totalPssKb = null;
        cachedSize = -1;
    }

    public static MemorySample[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new MemorySample[0];
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
        int j = super.computeSerializedSize();
        if (totalPssKb != null)
        {
            int i = totalPssKb.intValue();
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            return i + k + j;
        } else
        {
            return j;
        }
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
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
                totalPssKb = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (totalPssKb != null)
        {
            int i = totalPssKb.intValue();
            codedoutputbytebuffernano.writeRawVarint32(8);
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
}
