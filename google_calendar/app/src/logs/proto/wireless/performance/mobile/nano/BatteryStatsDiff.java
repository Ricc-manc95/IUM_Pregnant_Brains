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
//            WakelockStats, SyncStats, UidHealthProto, MetricExtension

public final class BatteryStatsDiff extends ExtendableMessageNano
{

    public Long durationMs;
    public Long elapedRealtimeMs;
    public int endInfo;
    public String startConstantEventName;
    public String startCustomEventName;
    public Long startHashedCustomEventName;
    public int startInfo;
    public MetricExtension startMetricExtension;
    private SyncStats syncStats[];
    public UidHealthProto uidHealthProtoDiff;
    private WakelockStats wakelockStats[];

    public BatteryStatsDiff()
    {
        startInfo = 0x80000000;
        startHashedCustomEventName = null;
        startCustomEventName = null;
        startConstantEventName = null;
        startMetricExtension = null;
        endInfo = 0x80000000;
        durationMs = null;
        wakelockStats = WakelockStats.emptyArray();
        syncStats = SyncStats.emptyArray();
        uidHealthProtoDiff = null;
        elapedRealtimeMs = null;
        cachedSize = -1;
    }

    private static int checkSampleInfoOrThrow(int i)
    {
        if (i >= 0 && i <= 6)
        {
            return i;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(42)).append(i).append(" is not a valid enum SampleInfo").toString());
        }
    }

    private final BatteryStatsDiff mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                int l = codedinputbytebuffernano.bufferPos;
                int l1 = codedinputbytebuffernano.bufferStart;
                try
                {
                    startInfo = checkSampleInfoOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(l - l1, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 16: // '\020'
                int i1 = codedinputbytebuffernano.bufferPos;
                int i2 = codedinputbytebuffernano.bufferStart;
                try
                {
                    endInfo = checkSampleInfoOrThrow(codedinputbytebuffernano.readRawVarint32());
                }
                catch (IllegalArgumentException illegalargumentexception1)
                {
                    codedinputbytebuffernano.rewindToPositionAndTag(i1 - i2, codedinputbytebuffernano.lastTag);
                    storeUnknownField(codedinputbytebuffernano, i);
                }
                break;

            case 24: // '\030'
                durationMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 34: // '"'
                int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 34);
                WakelockStats awakelockstats[];
                int j;
                if (wakelockStats == null)
                {
                    j = 0;
                } else
                {
                    j = wakelockStats.length;
                }
                awakelockstats = new WakelockStats[j1 + j];
                j1 = j;
                if (j != 0)
                {
                    System.arraycopy(wakelockStats, 0, awakelockstats, 0, j);
                    j1 = j;
                }
                for (; j1 < awakelockstats.length - 1; j1++)
                {
                    awakelockstats[j1] = new WakelockStats();
                    codedinputbytebuffernano.readMessage(awakelockstats[j1]);
                    codedinputbytebuffernano.readTag();
                }

                awakelockstats[j1] = new WakelockStats();
                codedinputbytebuffernano.readMessage(awakelockstats[j1]);
                wakelockStats = awakelockstats;
                break;

            case 42: // '*'
                int k1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 42);
                SyncStats asyncstats[];
                int k;
                if (syncStats == null)
                {
                    k = 0;
                } else
                {
                    k = syncStats.length;
                }
                asyncstats = new SyncStats[k1 + k];
                k1 = k;
                if (k != 0)
                {
                    System.arraycopy(syncStats, 0, asyncstats, 0, k);
                    k1 = k;
                }
                for (; k1 < asyncstats.length - 1; k1++)
                {
                    asyncstats[k1] = new SyncStats();
                    codedinputbytebuffernano.readMessage(asyncstats[k1]);
                    codedinputbytebuffernano.readTag();
                }

                asyncstats[k1] = new SyncStats();
                codedinputbytebuffernano.readMessage(asyncstats[k1]);
                syncStats = asyncstats;
                break;

            case 50: // '2'
                if (uidHealthProtoDiff == null)
                {
                    uidHealthProtoDiff = new UidHealthProto();
                }
                codedinputbytebuffernano.readMessage(uidHealthProtoDiff);
                break;

            case 56: // '8'
                elapedRealtimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 65: // 'A'
                startHashedCustomEventName = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
                break;

            case 74: // 'J'
                startCustomEventName = codedinputbytebuffernano.readString();
                break;

            case 82: // 'R'
                startConstantEventName = codedinputbytebuffernano.readString();
                break;

            case 90: // 'Z'
                if (startMetricExtension == null)
                {
                    startMetricExtension = new MetricExtension();
                }
                codedinputbytebuffernano.readMessage(startMetricExtension);
                break;
            }
        } while (true);
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (startInfo != 0x80000000)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(1, startInfo);
        }
        int k = i;
        if (endInfo != 0x80000000)
        {
            k = i + CodedOutputByteBufferNano.computeInt32Size(2, endInfo);
        }
        j = k;
        if (durationMs != null)
        {
            j = k + CodedOutputByteBufferNano.computeInt64Size(3, durationMs.longValue());
        }
        i = j;
        if (wakelockStats != null)
        {
            i = j;
            if (wakelockStats.length > 0)
            {
                i = j;
                for (j = 0; j < wakelockStats.length;)
                {
                    WakelockStats wakelockstats = wakelockStats[j];
                    int l = i;
                    if (wakelockstats != null)
                    {
                        l = i + CodedOutputByteBufferNano.computeMessageSize(4, wakelockstats);
                    }
                    j++;
                    i = l;
                }

            }
        }
        j = i;
        if (syncStats != null)
        {
            j = i;
            if (syncStats.length > 0)
            {
                int i1 = ((flag) ? 1 : 0);
                do
                {
                    j = i;
                    if (i1 >= syncStats.length)
                    {
                        break;
                    }
                    SyncStats syncstats = syncStats[i1];
                    j = i;
                    if (syncstats != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(5, syncstats);
                    }
                    i1++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (uidHealthProtoDiff != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(6, uidHealthProtoDiff);
        }
        j = i;
        if (elapedRealtimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(7, elapedRealtimeMs.longValue());
        }
        i = j;
        if (startHashedCustomEventName != null)
        {
            startHashedCustomEventName.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(64) + 8);
        }
        j = i;
        if (startCustomEventName != null)
        {
            String s = startCustomEventName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(72);
            int j1 = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (j1 + CodedOutputByteBufferNano.computeRawVarint32Size(j1) + j);
        }
        i = j;
        if (startConstantEventName != null)
        {
            String s1 = startConstantEventName;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(80);
            int k1 = CodedOutputByteBufferNano.encodedLength(s1);
            i = j + (k1 + CodedOutputByteBufferNano.computeRawVarint32Size(k1) + i);
        }
        j = i;
        if (startMetricExtension != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(11, startMetricExtension);
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
        boolean flag = false;
        if (startInfo != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(1, startInfo);
        }
        if (endInfo != 0x80000000)
        {
            codedoutputbytebuffernano.writeInt32(2, endInfo);
        }
        if (durationMs != null)
        {
            codedoutputbytebuffernano.writeInt64(3, durationMs.longValue());
        }
        if (wakelockStats != null && wakelockStats.length > 0)
        {
            for (int i = 0; i < wakelockStats.length; i++)
            {
                WakelockStats wakelockstats = wakelockStats[i];
                if (wakelockstats != null)
                {
                    codedoutputbytebuffernano.writeMessage(4, wakelockstats);
                }
            }

        }
        if (syncStats != null && syncStats.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < syncStats.length; j++)
            {
                SyncStats syncstats = syncStats[j];
                if (syncstats != null)
                {
                    codedoutputbytebuffernano.writeMessage(5, syncstats);
                }
            }

        }
        if (uidHealthProtoDiff != null)
        {
            codedoutputbytebuffernano.writeMessage(6, uidHealthProtoDiff);
        }
        if (elapedRealtimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(7, elapedRealtimeMs.longValue());
        }
        if (startHashedCustomEventName != null)
        {
            codedoutputbytebuffernano.writeFixed64(8, startHashedCustomEventName.longValue());
        }
        if (startCustomEventName != null)
        {
            codedoutputbytebuffernano.writeString(9, startCustomEventName);
        }
        if (startConstantEventName != null)
        {
            codedoutputbytebuffernano.writeString(10, startConstantEventName);
        }
        if (startMetricExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(11, startMetricExtension);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
