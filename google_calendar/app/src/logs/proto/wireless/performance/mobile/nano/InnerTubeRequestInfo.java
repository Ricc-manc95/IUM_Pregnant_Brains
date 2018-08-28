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

public final class InnerTubeRequestInfo extends ExtendableMessageNano
{

    private Long parsingTimeMs;
    private Integer responseProtoSizeBytes;
    private String serviceAnnotation[];

    public InnerTubeRequestInfo()
    {
        parsingTimeMs = null;
        responseProtoSizeBytes = null;
        serviceAnnotation = WireFormatNano.EMPTY_STRING_ARRAY;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int i = super.computeSerializedSize();
        int j = i;
        if (parsingTimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(1, parsingTimeMs.longValue());
        }
        i = j;
        if (responseProtoSizeBytes != null)
        {
            i = responseProtoSizeBytes.intValue();
            int k = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i) + k);
        }
        j = i;
        if (serviceAnnotation != null)
        {
            j = i;
            if (serviceAnnotation.length > 0)
            {
                int l = 0;
                int i1 = 0;
                for (j = ((flag) ? 1 : 0); j < serviceAnnotation.length;)
                {
                    String s = serviceAnnotation[j];
                    int k1 = l;
                    int j1 = i1;
                    if (s != null)
                    {
                        j1 = i1 + 1;
                        i1 = CodedOutputByteBufferNano.encodedLength(s);
                        k1 = l + (i1 + CodedOutputByteBufferNano.computeRawVarint32Size(i1));
                    }
                    j++;
                    l = k1;
                    i1 = j1;
                }

                j = i + l + i1 * 1;
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

            case 8: // '\b'
                parsingTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                responseProtoSizeBytes = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 26: // '\032'
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 26);
                String as[];
                int j;
                if (serviceAnnotation == null)
                {
                    j = 0;
                } else
                {
                    j = serviceAnnotation.length;
                }
                as = new String[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(serviceAnnotation, 0, as, 0, j);
                    k = j;
                }
                for (; k < as.length - 1; k++)
                {
                    as[k] = codedinputbytebuffernano.readString();
                    codedinputbytebuffernano.readTag();
                }

                as[k] = codedinputbytebuffernano.readString();
                serviceAnnotation = as;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (parsingTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(1, parsingTimeMs.longValue());
        }
        if (responseProtoSizeBytes != null)
        {
            codedoutputbytebuffernano.writeUInt32(2, responseProtoSizeBytes.intValue());
        }
        if (serviceAnnotation != null && serviceAnnotation.length > 0)
        {
            for (int i = 0; i < serviceAnnotation.length; i++)
            {
                String s = serviceAnnotation[i];
                if (s != null)
                {
                    codedoutputbytebuffernano.writeString(3, s);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
