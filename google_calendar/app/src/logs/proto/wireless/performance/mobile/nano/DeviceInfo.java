// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class DeviceInfo extends ExtendableMessageNano
{

    public Long availableDiskSizeKb;
    public Long totalDiskSizeKb;

    public DeviceInfo()
    {
        availableDiskSizeKb = null;
        totalDiskSizeKb = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (availableDiskSizeKb != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, availableDiskSizeKb.longValue());
        }
        j = i;
        if (totalDiskSizeKb != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, totalDiskSizeKb.longValue());
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
                availableDiskSizeKb = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                totalDiskSizeKb = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (availableDiskSizeKb != null)
        {
            codedoutputbytebuffernano.writeInt64(1, availableDiskSizeKb.longValue());
        }
        if (totalDiskSizeKb != null)
        {
            codedoutputbytebuffernano.writeInt64(2, totalDiskSizeKb.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
