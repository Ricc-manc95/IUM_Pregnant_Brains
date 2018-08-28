// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            StackElement

public final class StackTrace extends ExtendableMessageNano
{

    private static volatile StackTrace _emptyArray[];
    public StackElement stackElements[];

    public StackTrace()
    {
        stackElements = StackElement.emptyArray();
        cachedSize = -1;
    }

    public static StackTrace[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new StackTrace[0];
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
        int i = super.computeSerializedSize();
        int k = i;
        if (stackElements != null)
        {
            k = i;
            if (stackElements.length > 0)
            {
                int j = 0;
                do
                {
                    k = i;
                    if (j >= stackElements.length)
                    {
                        break;
                    }
                    StackElement stackelement = stackElements[j];
                    k = i;
                    if (stackelement != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(1, stackelement);
                    }
                    j++;
                    i = k;
                } while (true);
            }
        }
        return k;
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
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                StackElement astackelement[];
                int j;
                if (stackElements == null)
                {
                    j = 0;
                } else
                {
                    j = stackElements.length;
                }
                astackelement = new StackElement[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(stackElements, 0, astackelement, 0, j);
                    k = j;
                }
                for (; k < astackelement.length - 1; k++)
                {
                    astackelement[k] = new StackElement();
                    codedinputbytebuffernano.readMessage(astackelement[k]);
                    codedinputbytebuffernano.readTag();
                }

                astackelement[k] = new StackElement();
                codedinputbytebuffernano.readMessage(astackelement[k]);
                stackElements = astackelement;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (stackElements != null && stackElements.length > 0)
        {
            for (int i = 0; i < stackElements.length; i++)
            {
                StackElement stackelement = stackElements[i];
                if (stackelement != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, stackelement);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
