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
//            BatteryStatsDiff

public final class BatteryUsageMetric extends ExtendableMessageNano
{

    public BatteryStatsDiff batteryStatsDiff;

    public BatteryUsageMetric()
    {
        batteryStatsDiff = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (batteryStatsDiff != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(1, batteryStatsDiff);
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

            case 10: // '\n'
                if (batteryStatsDiff == null)
                {
                    batteryStatsDiff = new BatteryStatsDiff();
                }
                codedinputbytebuffernano.readMessage(batteryStatsDiff);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (batteryStatsDiff != null)
        {
            codedoutputbytebuffernano.writeMessage(1, batteryStatsDiff);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
