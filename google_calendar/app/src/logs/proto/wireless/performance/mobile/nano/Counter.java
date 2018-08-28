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
//            HashedString

public final class Counter extends ExtendableMessageNano
{

    private static volatile Counter _emptyArray[];
    public Integer count;
    public HashedString name;

    public Counter()
    {
        count = null;
        name = null;
        cachedSize = -1;
    }

    public static Counter[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new Counter[0];
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
        if (name != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, name);
        }
        return j;
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

            case 18: // '\022'
                if (name == null)
                {
                    name = new HashedString();
                }
                codedinputbytebuffernano.readMessage(name);
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
        if (name != null)
        {
            codedoutputbytebuffernano.writeMessage(2, name);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
