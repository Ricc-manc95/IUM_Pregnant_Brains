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
//            Span

public final class PrimesSpans extends ExtendableMessageNano
{

    private Span spans[];

    public PrimesSpans()
    {
        spans = Span.emptyArray();
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int k = i;
        if (spans != null)
        {
            k = i;
            if (spans.length > 0)
            {
                int j = 0;
                do
                {
                    k = i;
                    if (j >= spans.length)
                    {
                        break;
                    }
                    Span span = spans[j];
                    k = i;
                    if (span != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(1, span);
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
                Span aspan[];
                int j;
                if (spans == null)
                {
                    j = 0;
                } else
                {
                    j = spans.length;
                }
                aspan = new Span[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(spans, 0, aspan, 0, j);
                    k = j;
                }
                for (; k < aspan.length - 1; k++)
                {
                    aspan[k] = new Span();
                    codedinputbytebuffernano.readMessage(aspan[k]);
                    codedinputbytebuffernano.readTag();
                }

                aspan[k] = new Span();
                codedinputbytebuffernano.readMessage(aspan[k]);
                spans = aspan;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (spans != null && spans.length > 0)
        {
            for (int i = 0; i < spans.length; i++)
            {
                Span span = spans[i];
                if (span != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, span);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
