// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class HashedString extends ExtendableMessageNano
{

    public Long hash;
    public String unhashedName;

    public HashedString()
    {
        hash = null;
        unhashedName = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (hash != null)
        {
            hash.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(8) + 8);
        }
        j = i;
        if (unhashedName != null)
        {
            String s = unhashedName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
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
                hash = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
                break;

            case 18: // '\022'
                unhashedName = codedinputbytebuffernano.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (hash != null)
        {
            codedoutputbytebuffernano.writeFixed64(1, hash.longValue());
        }
        if (unhashedName != null)
        {
            codedoutputbytebuffernano.writeString(2, unhashedName);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
