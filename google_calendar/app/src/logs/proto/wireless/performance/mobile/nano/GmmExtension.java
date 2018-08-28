// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import logs.proto.wireless.performance.mobile.android.gmm.nano.SystemHealthMetricExtension;

public final class GmmExtension extends ExtendableMessageNano
{

    private Integer externalInvocationType;
    private Integer requestId;
    private SystemHealthMetricExtension systemHealthMetricExtension;

    public GmmExtension()
    {
        requestId = null;
        externalInvocationType = null;
        systemHealthMetricExtension = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (requestId != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, requestId.intValue());
        }
        j = i;
        if (externalInvocationType != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt32Size(2, externalInvocationType.intValue());
        }
        i = j;
        if (systemHealthMetricExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, systemHealthMetricExtension);
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
                requestId = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 16: // '\020'
                externalInvocationType = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 26: // '\032'
                if (systemHealthMetricExtension == null)
                {
                    systemHealthMetricExtension = new SystemHealthMetricExtension();
                }
                codedinputbytebuffernano.readMessage(systemHealthMetricExtension);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (requestId != null)
        {
            codedoutputbytebuffernano.writeInt32(1, requestId.intValue());
        }
        if (externalInvocationType != null)
        {
            codedoutputbytebuffernano.writeInt32(2, externalInvocationType.intValue());
        }
        if (systemHealthMetricExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(3, systemHealthMetricExtension);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
