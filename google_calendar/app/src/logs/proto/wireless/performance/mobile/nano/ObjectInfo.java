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

public final class ObjectInfo extends ExtendableMessageNano
{

    private static volatile ObjectInfo _emptyArray[];
    public String className;
    public String leakPath;
    public Integer leakedCount;
    public Integer releasedCount;
    public Integer retainedHeapBytes;

    public ObjectInfo()
    {
        className = null;
        releasedCount = null;
        leakedCount = null;
        leakPath = null;
        retainedHeapBytes = null;
        cachedSize = -1;
    }

    public static ObjectInfo[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new ObjectInfo[0];
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
        if (className != null)
        {
            String s = className;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (releasedCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, releasedCount.intValue());
        }
        i = j;
        if (leakedCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(3, leakedCount.intValue());
        }
        j = i;
        if (leakPath != null)
        {
            String s1 = leakPath;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        i = j;
        if (retainedHeapBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, retainedHeapBytes.intValue());
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

            case 10: // '\n'
                className = codedinputbytebuffernano.readString();
                break;

            case 16: // '\020'
                releasedCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 24: // '\030'
                leakedCount = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 34: // '"'
                leakPath = codedinputbytebuffernano.readString();
                break;

            case 40: // '('
                retainedHeapBytes = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (className != null)
        {
            codedoutputbytebuffernano.writeString(1, className);
        }
        if (releasedCount != null)
        {
            codedoutputbytebuffernano.writeInt32(2, releasedCount.intValue());
        }
        if (leakedCount != null)
        {
            codedoutputbytebuffernano.writeInt32(3, leakedCount.intValue());
        }
        if (leakPath != null)
        {
            codedoutputbytebuffernano.writeString(4, leakPath);
        }
        if (retainedHeapBytes != null)
        {
            codedoutputbytebuffernano.writeInt32(5, retainedHeapBytes.intValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
