// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            StackTrace

public final class CpuUsageMetric extends ExtendableMessageNano
{

    public StackTrace stackTraces[];

    public CpuUsageMetric()
    {
        stackTraces = StackTrace.emptyArray();
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int k = i;
        if (stackTraces != null)
        {
            k = i;
            if (stackTraces.length > 0)
            {
                int j = 0;
                do
                {
                    k = i;
                    if (j >= stackTraces.length)
                    {
                        break;
                    }
                    StackTrace stacktrace = stackTraces[j];
                    k = i;
                    if (stacktrace != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(1, stacktrace);
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
                StackTrace astacktrace[];
                int j;
                if (stackTraces == null)
                {
                    j = 0;
                } else
                {
                    j = stackTraces.length;
                }
                astacktrace = new StackTrace[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(stackTraces, 0, astacktrace, 0, j);
                    k = j;
                }
                for (; k < astacktrace.length - 1; k++)
                {
                    astacktrace[k] = new StackTrace();
                    codedinputbytebuffernano.readMessage(astacktrace[k]);
                    codedinputbytebuffernano.readTag();
                }

                astacktrace[k] = new StackTrace();
                codedinputbytebuffernano.readMessage(astacktrace[k]);
                stackTraces = astacktrace;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (stackTraces != null && stackTraces.length > 0)
        {
            for (int i = 0; i < stackTraces.length; i++)
            {
                StackTrace stacktrace = stackTraces[i];
                if (stacktrace != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, stacktrace);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
