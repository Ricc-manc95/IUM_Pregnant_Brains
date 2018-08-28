// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            ServiceHealthProto, Counter, HashedString

public final class PackageHealthProto extends ExtendableMessageNano
{

    private static volatile PackageHealthProto _emptyArray[];
    public HashedString name;
    public ServiceHealthProto statsServices[];
    public Counter wakeupAlarmsCount[];

    public PackageHealthProto()
    {
        statsServices = ServiceHealthProto.emptyArray();
        wakeupAlarmsCount = Counter.emptyArray();
        name = null;
        cachedSize = -1;
    }

    public static PackageHealthProto[] emptyArray()
    {
        if (_emptyArray == null)
        {
            synchronized (InternalNano.LAZY_INIT_LOCK)
            {
                if (_emptyArray == null)
                {
                    _emptyArray = new PackageHealthProto[0];
                }
            }
        }
        return _emptyArray;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (statsServices != null)
        {
            i = j;
            if (statsServices.length > 0)
            {
                i = j;
                for (j = 0; j < statsServices.length;)
                {
                    ServiceHealthProto servicehealthproto = statsServices[j];
                    int k = i;
                    if (servicehealthproto != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(1, servicehealthproto);
                    }
                    j++;
                    i = k;
                }

            }
        }
        j = i;
        if (wakeupAlarmsCount != null)
        {
            j = i;
            if (wakeupAlarmsCount.length > 0)
            {
                int l = ((flag) ? 1 : 0);
                do
                {
                    j = i;
                    if (l >= wakeupAlarmsCount.length)
                    {
                        break;
                    }
                    Counter counter = wakeupAlarmsCount[l];
                    j = i;
                    if (counter != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(2, counter);
                    }
                    l++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (name != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(3, name);
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
                int l = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                ServiceHealthProto aservicehealthproto[];
                int j;
                if (statsServices == null)
                {
                    j = 0;
                } else
                {
                    j = statsServices.length;
                }
                aservicehealthproto = new ServiceHealthProto[l + j];
                l = j;
                if (j != 0)
                {
                    System.arraycopy(statsServices, 0, aservicehealthproto, 0, j);
                    l = j;
                }
                for (; l < aservicehealthproto.length - 1; l++)
                {
                    aservicehealthproto[l] = new ServiceHealthProto();
                    codedinputbytebuffernano.readMessage(aservicehealthproto[l]);
                    codedinputbytebuffernano.readTag();
                }

                aservicehealthproto[l] = new ServiceHealthProto();
                codedinputbytebuffernano.readMessage(aservicehealthproto[l]);
                statsServices = aservicehealthproto;
                break;

            case 18: // '\022'
                int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
                Counter acounter[];
                int k;
                if (wakeupAlarmsCount == null)
                {
                    k = 0;
                } else
                {
                    k = wakeupAlarmsCount.length;
                }
                acounter = new Counter[i1 + k];
                i1 = k;
                if (k != 0)
                {
                    System.arraycopy(wakeupAlarmsCount, 0, acounter, 0, k);
                    i1 = k;
                }
                for (; i1 < acounter.length - 1; i1++)
                {
                    acounter[i1] = new Counter();
                    codedinputbytebuffernano.readMessage(acounter[i1]);
                    codedinputbytebuffernano.readTag();
                }

                acounter[i1] = new Counter();
                codedinputbytebuffernano.readMessage(acounter[i1]);
                wakeupAlarmsCount = acounter;
                break;

            case 26: // '\032'
                if (name == null)
                {
                    name = new HashedString();
                }
                codedinputbytebuffernano.readMessage(name);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (statsServices != null && statsServices.length > 0)
        {
            for (int i = 0; i < statsServices.length; i++)
            {
                ServiceHealthProto servicehealthproto = statsServices[i];
                if (servicehealthproto != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, servicehealthproto);
                }
            }

        }
        if (wakeupAlarmsCount != null && wakeupAlarmsCount.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < wakeupAlarmsCount.length; j++)
            {
                Counter counter = wakeupAlarmsCount[j];
                if (counter != null)
                {
                    codedoutputbytebuffernano.writeMessage(2, counter);
                }
            }

        }
        if (name != null)
        {
            codedoutputbytebuffernano.writeMessage(3, name);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
