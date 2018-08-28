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

public final class PrimesTrace extends ExtendableMessageNano
{

    public Span spans[];
    public Long traceId;

    public PrimesTrace()
    {
        traceId = null;
        spans = Span.emptyArray();
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (traceId != null)
        {
            traceId.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 8);
        }
        j = i;
        if (spans != null)
        {
            j = i;
            if (spans.length > 0)
            {
                for (j = 0; j < spans.length;)
                {
                    Span span = spans[j];
                    int k = i;
                    if (span != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(2, span);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
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

            case 9: // '\t'
                traceId = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
                break;

            case 18: // '\022'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
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
        if (traceId != null)
        {
            codedoutputbytebuffernano.writeFixed64(1, traceId.longValue());
        }
        if (spans != null && spans.length > 0)
        {
            for (int i = 0; i < spans.length; i++)
            {
                Span span = spans[i];
                if (span != null)
                {
                    codedoutputbytebuffernano.writeMessage(2, span);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
