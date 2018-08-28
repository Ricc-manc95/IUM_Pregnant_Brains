// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            BugleMobileCode

public final class BugleExtension extends ExtendableMessageNano
{

    private Boolean isRcsEnabled;
    private BugleMobileCode mobileCode;

    public BugleExtension()
    {
        isRcsEnabled = null;
        mobileCode = null;
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
        if (mobileCode != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(2, mobileCode);
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
                if (mobileCode == null)
                {
                    mobileCode = new BugleMobileCode();
                }
                codedinputbytebuffernano.readMessage(mobileCode);
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
        if (mobileCode != null)
        {
            codedoutputbytebuffernano.writeMessage(2, mobileCode);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
