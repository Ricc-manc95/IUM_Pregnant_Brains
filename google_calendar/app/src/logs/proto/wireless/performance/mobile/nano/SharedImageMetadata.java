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

public final class SharedImageMetadata extends ExtendableMessageNano
{

    private static volatile SharedImageMetadata _emptyArray[];
    private Integer height;
    private Long sizeInBytes;
    private Integer width;

    public SharedImageMetadata()
    {
        height = null;
        width = null;
        sizeInBytes = null;
        cachedSize = -1;
    }

    public static SharedImageMetadata[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new SharedImageMetadata[0];
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
        if (height != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, height.intValue());
        }
        j = i;
        if (width != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, width.intValue());
        }
        i = j;
        if (sizeInBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(3, sizeInBytes.longValue());
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
                height = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                width = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                sizeInBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (height != null)
        {
            codedoutputbytebuffernano.writeInt32(1, height.intValue());
        }
        if (width != null)
        {
            codedoutputbytebuffernano.writeInt32(2, width.intValue());
        }
        if (sizeInBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(3, sizeInBytes.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
