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

public final class PrimesScenario extends ExtendableMessageNano
{

    private static volatile PrimesScenario _emptyArray[];
    private String end;
    private String name;
    private String start;

    public PrimesScenario()
    {
        name = null;
        start = null;
        end = null;
        cachedSize = -1;
    }

    public static PrimesScenario[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new PrimesScenario[0];
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
        if (name != null)
        {
            String s = name;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
        }
        j = i;
        if (start != null)
        {
            String s1 = start;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        i = j;
        if (end != null)
        {
            String s2 = end;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int i1 = CodedOutputByteBufferNano.encodedLength(s2);
            i = j + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i);
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
                name = codedinputbytebuffernano.readString();
                break;

            case 18: // '\022'
                start = codedinputbytebuffernano.readString();
                break;

            case 26: // '\032'
                end = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (name != null)
        {
            codedoutputbytebuffernano.writeString(1, name);
        }
        if (start != null)
        {
            codedoutputbytebuffernano.writeString(2, start);
        }
        if (end != null)
        {
            codedoutputbytebuffernano.writeString(3, end);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
