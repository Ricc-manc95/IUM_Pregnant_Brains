// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class BugleMobileCode extends ExtendableMessageNano
{

    private Integer mcc;
    private Integer mnc;
    private int mvno;
    private String networkOperatorName;
    private String simOperatorName;

    public BugleMobileCode()
    {
        mcc = null;
        mnc = null;
        simOperatorName = null;
        networkOperatorName = null;
        mvno = 0x80000000;
        cachedSize = -1;
    }

    private final BugleMobileCode mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L2:
        int i;
        int j;
        int k;
        i = codedinputbytebuffernano.readTag();
        switch (i)
        {
        default:
            if (super.storeUnknownField(codedinputbytebuffernano, i))
            {
                continue; /* Loop/switch isn't completed */
            }
            // fall through

        case 0: // '\0'
            return this;

        case 8: // '\b'
            mcc = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 16: // '\020'
            mnc = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
            continue; /* Loop/switch isn't completed */

        case 26: // '\032'
            simOperatorName = codedinputbytebuffernano.readString();
            continue; /* Loop/switch isn't completed */

        case 34: // '"'
            networkOperatorName = codedinputbytebuffernano.readString();
            continue; /* Loop/switch isn't completed */

        case 40: // '('
            j = codedinputbytebuffernano.bufferPos;
            k = codedinputbytebuffernano.bufferStart;
            break;
        }
        int l = codedinputbytebuffernano.readRawVarint32();
        if (l < 0 || l > 1)
        {
            break; /* Loop/switch isn't completed */
        }
        try
        {
            mvno = l;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
            storeUnknownField(codedinputbytebuffernano, i);
        }
        if (true) goto _L2; else goto _L1
_L1:
        throw new IllegalArgumentException((new StringBuilder(36)).append(l).append(" is not a valid enum Mvno").toString());
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int j = i;
        if (mcc != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(1, mcc.intValue());
        }
        i = j;
        if (mnc != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(2, mnc.intValue());
        }
        j = i;
        if (simOperatorName != null)
        {
            String s = simOperatorName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        i = j;
        if (networkOperatorName != null)
        {
            String s1 = networkOperatorName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
        }
        j = i;
        if (mvno != 0x80000000)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(5, mvno);
        }
        return j;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (mcc != null)
        {
            codedoutputbytebuffernano.writeInt32(1, mcc.intValue());
        }
        if (mnc != null)
        {
            codedoutputbytebuffernano.writeInt32(2, mnc.intValue());
        }
        if (simOperatorName != null)
        {
            codedoutputbytebuffernano.writeString(3, simOperatorName);
        }
        if (networkOperatorName != null)
        {
            codedoutputbytebuffernano.writeString(4, networkOperatorName);
        }
        if (mvno != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(5, mvno);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
