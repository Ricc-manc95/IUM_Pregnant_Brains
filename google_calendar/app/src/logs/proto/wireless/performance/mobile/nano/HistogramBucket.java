// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class HistogramBucket extends ExtendableMessageNano
{

    private static volatile HistogramBucket _emptyArray[];
    public Integer count;
    public Integer max;
    public Integer min;

    public HistogramBucket()
    {
        count = null;
        min = null;
        max = null;
        cachedSize = -1;
    }

    public static HistogramBucket[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new HistogramBucket[0];
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
        int i = j;
        if (count != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, count.intValue());
        }
        j = i;
        if (min != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, min.intValue());
        }
        i = j;
        if (max != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, max.intValue());
        }
        return i;
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
                count = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                min = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                max = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (count != null)
        {
            codedoutputbytebuffernano.writeInt32(1, count.intValue());
        }
        if (min != null)
        {
            codedoutputbytebuffernano.writeInt32(2, min.intValue());
        }
        if (max != null)
        {
            codedoutputbytebuffernano.writeInt32(3, max.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
