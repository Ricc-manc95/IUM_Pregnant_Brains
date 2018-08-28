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

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            PrimesHeapDumpProtoNano

public final class PrimitiveArrayInstance extends ExtendableMessageNano
{

    private static volatile PrimitiveArrayInstance _emptyArray[];
    public Integer numElements;
    public int type;

    public PrimitiveArrayInstance()
    {
        type = 0x80000000;
        numElements = null;
        cachedSize = -1;
    }

    public static PrimitiveArrayInstance[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new PrimitiveArrayInstance[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final PrimitiveArrayInstance mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                int j = codedinputbytebuffernano.bufferPos;
                int k = codedinputbytebuffernano.bufferStart;
                try
                {
                    type = PrimesHeapDumpProtoNano.checkPrimitiveTypeOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 16: // '\020'
                numElements = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (type != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, type);
        }
        j = i;
        if (numElements != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, numElements.intValue());
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
        if (type != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, type);
        }
        if (numElements != null)
        {
            codedoutputbytebuffernano.writeInt32(2, numElements.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
