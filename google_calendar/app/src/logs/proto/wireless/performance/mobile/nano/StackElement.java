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

public final class StackElement extends ExtendableMessageNano
{

    private static volatile StackElement _emptyArray[];
    public String functionName;

    public StackElement()
    {
        functionName = null;
        cachedSize = -1;
    }

    public static StackElement[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new StackElement[0];
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
        if (functionName != null)
        {
            String s = functionName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
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
                functionName = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (functionName != null)
        {
            codedoutputbytebuffernano.writeString(1, functionName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
