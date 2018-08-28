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

public final class CollapsedArrayInstance extends ExtendableMessageNano
{

    private static volatile CollapsedArrayInstance _emptyArray[];
    private Integer clazz;
    private Integer elementClass;
    private Integer length;
    private Integer numElements;
    private int primitiveArrayType;
    private Integer retainedBytes;

    public CollapsedArrayInstance()
    {
        clazz = null;
        elementClass = null;
        primitiveArrayType = 0x80000000;
        numElements = null;
        length = null;
        retainedBytes = null;
        cachedSize = -1;
    }

    public static CollapsedArrayInstance[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new CollapsedArrayInstance[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final CollapsedArrayInstance mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                clazz = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                elementClass = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                int j = codedinputbytebuffernano.bufferPos;
                int k = codedinputbytebuffernano.bufferStart;
                try
                {
                    primitiveArrayType = PrimesHeapDumpProtoNano.checkPrimitiveTypeOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 32: // ' '
                numElements = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 40: // '('
                length = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 48: // '0'
                retainedBytes = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (clazz != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, clazz.intValue());
        }
        j = i;
        if (elementClass != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, elementClass.intValue());
        }
        i = j;
        if (primitiveArrayType != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, primitiveArrayType);
        }
        j = i;
        if (numElements != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(4, numElements.intValue());
        }
        i = j;
        if (length != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, length.intValue());
        }
        j = i;
        if (retainedBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(6, retainedBytes.intValue());
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
        if (clazz != null)
        {
            codedoutputbytebuffernano.writeInt32(1, clazz.intValue());
        }
        if (elementClass != null)
        {
            codedoutputbytebuffernano.writeInt32(2, elementClass.intValue());
        }
        if (primitiveArrayType != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(3, primitiveArrayType);
        }
        if (numElements != null)
        {
            codedoutputbytebuffernano.writeInt32(4, numElements.intValue());
        }
        if (length != null)
        {
            codedoutputbytebuffernano.writeInt32(5, length.intValue());
        }
        if (retainedBytes != null)
        {
            codedoutputbytebuffernano.writeInt32(6, retainedBytes.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
