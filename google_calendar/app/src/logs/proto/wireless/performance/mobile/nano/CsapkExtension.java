// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class CsapkExtension extends ExtendableMessageNano
{

    private Boolean isRcsEnabled;
    private String mcc;
    private String mnc;

    public CsapkExtension()
    {
        isRcsEnabled = null;
        mcc = null;
        mnc = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (isRcsEnabled != null)
        {
            isRcsEnabled.booleanValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 1);
        }
        j = i;
        if (mcc != null)
        {
            String s = mcc;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        i = j;
        if (mnc != null)
        {
            String s1 = mnc;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
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
                boolean flag;
                if (codedinputbytebuffernano.readRawVarint32() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                isRcsEnabled = Boolean.valueOf(flag);
                break;

            case 18: // '\022'
                mcc = codedinputbytebuffernano.readString();
                break;

            case 26: // '\032'
                mnc = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (isRcsEnabled != null)
        {
            codedoutputbytebuffernano.writeBool(1, isRcsEnabled.booleanValue());
        }
        if (mcc != null)
        {
            codedoutputbytebuffernano.writeString(2, mcc);
        }
        if (mnc != null)
        {
            codedoutputbytebuffernano.writeString(3, mnc);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
