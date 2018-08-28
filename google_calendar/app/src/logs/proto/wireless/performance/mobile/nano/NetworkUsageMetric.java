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
//            NetworkEventUsage, AndroidProcessStats

public final class NetworkUsageMetric extends ExtendableMessageNano
{

    public NetworkEventUsage networkEventUsage[];
    private AndroidProcessStats processStats;

    public NetworkUsageMetric()
    {
        networkEventUsage = NetworkEventUsage.emptyArray();
        processStats = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int i = super.computeSerializedSize();
        int j = i;
        if (networkEventUsage != null)
        {
            j = i;
            if (networkEventUsage.length > 0)
            {
                int k = 0;
                do
                {
                    j = i;
                    if (k >= networkEventUsage.length)
                    {
                        break;
                    }
                    NetworkEventUsage networkeventusage = networkEventUsage[k];
                    j = i;
                    if (networkeventusage != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(1, networkeventusage);
                    }
                    k++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (processStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(2, processStats);
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
                int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                NetworkEventUsage anetworkeventusage[];
                int j;
                if (networkEventUsage == null)
                {
                    j = 0;
                } else
                {
                    j = networkEventUsage.length;
                }
                anetworkeventusage = new NetworkEventUsage[k + j];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(networkEventUsage, 0, anetworkeventusage, 0, j);
                    k = j;
                }
                for (; k < anetworkeventusage.length - 1; k++)
                {
                    anetworkeventusage[k] = new NetworkEventUsage();
                    codedinputbytebuffernano.readMessage(anetworkeventusage[k]);
                    codedinputbytebuffernano.readTag();
                }

                anetworkeventusage[k] = new NetworkEventUsage();
                codedinputbytebuffernano.readMessage(anetworkeventusage[k]);
                networkEventUsage = anetworkeventusage;
                break;

            case 18: // '\022'
                if (processStats == null)
                {
                    processStats = new AndroidProcessStats();
                }
                codedinputbytebuffernano.readMessage(processStats);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (networkEventUsage != null && networkEventUsage.length > 0)
        {
            for (int i = 0; i < networkEventUsage.length; i++)
            {
                NetworkEventUsage networkeventusage = networkEventUsage[i];
                if (networkeventusage != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, networkeventusage);
                }
            }

        }
        if (processStats != null)
        {
            codedoutputbytebuffernano.writeMessage(2, processStats);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
