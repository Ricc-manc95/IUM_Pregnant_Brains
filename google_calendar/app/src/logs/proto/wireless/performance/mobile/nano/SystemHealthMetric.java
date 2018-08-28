// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            AggregatedMetric, MemoryUsageMetric, TimerMetric, ApplicationInfo, 
//            NetworkUsageMetric, CrashMetric, PrimesStats, PackageMetric, 
//            BatteryUsageMetric, JankMetric, MemoryLeakMetric, MetricExtension, 
//            MagicEyeMetric, PrimesTrace, CpuUsageMetric, PrimesSpans, 
//            AccountableComponent, PrimesHeapDump, DeviceInfo, PrimesForPrimes, 
//            PrimesScenario

public final class SystemHealthMetric extends ExtendableMessageNano
{

    public AccountableComponent accountableComponent;
    private AggregatedMetric aggregatedMetrics[];
    public ApplicationInfo applicationInfo;
    public BatteryUsageMetric batteryUsageMetric;
    public String constantEventName;
    public CpuUsageMetric cpuUsageMetric;
    public CrashMetric crashMetric;
    public String customEventName;
    public DeviceInfo deviceInfo;
    private logs.proto.wireless.performance.mobile.SystemHealthProto.FrameRateMetric frameRateMetric;
    public Long hashedCustomEventName;
    public JankMetric jankMetric;
    public MagicEyeMetric magicEyeMetric;
    public MemoryLeakMetric memoryLeakMetric;
    public MemoryUsageMetric memoryUsageMetric;
    public MetricExtension metricExtension;
    public NetworkUsageMetric networkUsageMetric;
    public PackageMetric packageMetric;
    public PrimesForPrimes primesForPrimes;
    public PrimesHeapDump primesHeapDump;
    public PrimesScenario primesScenario;
    private PrimesSpans primesSpans;
    public PrimesStats primesStats;
    public PrimesTrace primesTrace;
    private logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric telemetryMetrics[];
    public TimerMetric timerMetric;

    public SystemHealthMetric()
    {
        memoryUsageMetric = null;
        hashedCustomEventName = null;
        customEventName = null;
        timerMetric = null;
        applicationInfo = null;
        networkUsageMetric = null;
        crashMetric = null;
        primesStats = null;
        packageMetric = null;
        batteryUsageMetric = null;
        jankMetric = null;
        memoryLeakMetric = null;
        metricExtension = null;
        magicEyeMetric = null;
        primesTrace = null;
        constantEventName = null;
        cpuUsageMetric = null;
        primesSpans = null;
        telemetryMetrics = new logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric[0];
        accountableComponent = null;
        primesHeapDump = null;
        deviceInfo = null;
        primesForPrimes = null;
        aggregatedMetrics = AggregatedMetric.emptyArray();
        primesScenario = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int i = super.computeSerializedSize();
        int j = i;
        if (memoryUsageMetric != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(1, memoryUsageMetric);
        }
        i = j;
        if (hashedCustomEventName != null)
        {
            hashedCustomEventName.longValue();
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(16) + 8);
        }
        j = i;
        if (customEventName != null)
        {
            String s = customEventName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int k = CodedOutputByteBufferNano.encodedLength(s);
            j = i + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + j);
        }
        i = j;
        if (timerMetric != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(4, timerMetric);
        }
        j = i;
        if (applicationInfo != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(5, applicationInfo);
        }
        i = j;
        if (networkUsageMetric != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(6, networkUsageMetric);
        }
        j = i;
        if (crashMetric != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(7, crashMetric);
        }
        i = j;
        if (primesStats != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(8, primesStats);
        }
        j = i;
        if (packageMetric != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(9, packageMetric);
        }
        i = j;
        if (batteryUsageMetric != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(10, batteryUsageMetric);
        }
        j = i;
        if (frameRateMetric != null)
        {
            j = i + CodedOutputStream.computeMessageSize(11, frameRateMetric);
        }
        i = j;
        if (jankMetric != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(12, jankMetric);
        }
        j = i;
        if (memoryLeakMetric != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(13, memoryLeakMetric);
        }
        i = j;
        if (metricExtension != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(14, metricExtension);
        }
        j = i;
        if (magicEyeMetric != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(15, magicEyeMetric);
        }
        i = j;
        if (primesTrace != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(16, primesTrace);
        }
        j = i;
        if (constantEventName != null)
        {
            String s1 = constantEventName;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(136);
            int l = CodedOutputByteBufferNano.encodedLength(s1);
            j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
        }
        int i1 = j;
        if (cpuUsageMetric != null)
        {
            i1 = j + CodedOutputByteBufferNano.computeMessageSize(18, cpuUsageMetric);
        }
        i = i1;
        if (primesSpans != null)
        {
            i = i1 + CodedOutputByteBufferNano.computeMessageSize(19, primesSpans);
        }
        j = i;
        if (telemetryMetrics != null)
        {
            j = i;
            if (telemetryMetrics.length > 0)
            {
                for (j = 0; j < telemetryMetrics.length;)
                {
                    logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric telemetrymetric = telemetryMetrics[j];
                    i1 = i;
                    if (telemetrymetric != null)
                    {
                        i1 = i + CodedOutputStream.computeMessageSize(20, telemetrymetric);
                    }
                    j++;
                    i = i1;
                }

                j = i;
            }
        }
        i = j;
        if (accountableComponent != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(21, accountableComponent);
        }
        j = i;
        if (primesHeapDump != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(22, primesHeapDump);
        }
        i1 = j;
        if (deviceInfo != null)
        {
            i1 = j + CodedOutputByteBufferNano.computeMessageSize(23, deviceInfo);
        }
        i = i1;
        if (primesForPrimes != null)
        {
            i = i1 + CodedOutputByteBufferNano.computeMessageSize(24, primesForPrimes);
        }
        j = i;
        if (aggregatedMetrics != null)
        {
            j = i;
            if (aggregatedMetrics.length > 0)
            {
                int j1 = ((flag) ? 1 : 0);
                do
                {
                    j = i;
                    if (j1 >= aggregatedMetrics.length)
                    {
                        break;
                    }
                    AggregatedMetric aggregatedmetric = aggregatedMetrics[j1];
                    j = i;
                    if (aggregatedmetric != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(25, aggregatedmetric);
                    }
                    j1++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (primesScenario != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(26, primesScenario);
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
                if (memoryUsageMetric == null)
                {
                    memoryUsageMetric = new MemoryUsageMetric();
                }
                codedinputbytebuffernano.readMessage(memoryUsageMetric);
                break;

            case 17: // '\021'
                hashedCustomEventName = Long.valueOf(codedinputbytebuffernano.readRawLittleEndian64());
                break;

            case 26: // '\032'
                customEventName = codedinputbytebuffernano.readString();
                break;

            case 34: // '"'
                if (timerMetric == null)
                {
                    timerMetric = new TimerMetric();
                }
                codedinputbytebuffernano.readMessage(timerMetric);
                break;

            case 42: // '*'
                if (applicationInfo == null)
                {
                    applicationInfo = new ApplicationInfo();
                }
                codedinputbytebuffernano.readMessage(applicationInfo);
                break;

            case 50: // '2'
                if (networkUsageMetric == null)
                {
                    networkUsageMetric = new NetworkUsageMetric();
                }
                codedinputbytebuffernano.readMessage(networkUsageMetric);
                break;

            case 58: // ':'
                if (crashMetric == null)
                {
                    crashMetric = new CrashMetric();
                }
                codedinputbytebuffernano.readMessage(crashMetric);
                break;

            case 66: // 'B'
                if (primesStats == null)
                {
                    primesStats = new PrimesStats();
                }
                codedinputbytebuffernano.readMessage(primesStats);
                break;

            case 74: // 'J'
                if (packageMetric == null)
                {
                    packageMetric = new PackageMetric();
                }
                codedinputbytebuffernano.readMessage(packageMetric);
                break;

            case 82: // 'R'
                if (batteryUsageMetric == null)
                {
                    batteryUsageMetric = new BatteryUsageMetric();
                }
                codedinputbytebuffernano.readMessage(batteryUsageMetric);
                break;

            case 90: // 'Z'
                frameRateMetric = (logs.proto.wireless.performance.mobile.SystemHealthProto.FrameRateMetric)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.SystemHealthProto.FrameRateMetric.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                break;

            case 98: // 'b'
                if (jankMetric == null)
                {
                    jankMetric = new JankMetric();
                }
                codedinputbytebuffernano.readMessage(jankMetric);
                break;

            case 106: // 'j'
                if (memoryLeakMetric == null)
                {
                    memoryLeakMetric = new MemoryLeakMetric();
                }
                codedinputbytebuffernano.readMessage(memoryLeakMetric);
                break;

            case 114: // 'r'
                if (metricExtension == null)
                {
                    metricExtension = new MetricExtension();
                }
                codedinputbytebuffernano.readMessage(metricExtension);
                break;

            case 122: // 'z'
                if (magicEyeMetric == null)
                {
                    magicEyeMetric = new MagicEyeMetric();
                }
                codedinputbytebuffernano.readMessage(magicEyeMetric);
                break;

            case 130: 
                if (primesTrace == null)
                {
                    primesTrace = new PrimesTrace();
                }
                codedinputbytebuffernano.readMessage(primesTrace);
                break;

            case 138: 
                constantEventName = codedinputbytebuffernano.readString();
                break;

            case 146: 
                if (cpuUsageMetric == null)
                {
                    cpuUsageMetric = new CpuUsageMetric();
                }
                codedinputbytebuffernano.readMessage(cpuUsageMetric);
                break;

            case 154: 
                if (primesSpans == null)
                {
                    primesSpans = new PrimesSpans();
                }
                codedinputbytebuffernano.readMessage(primesSpans);
                break;

            case 162: 
                int l = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 162);
                logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric atelemetrymetric[];
                int j;
                if (telemetryMetrics == null)
                {
                    j = 0;
                } else
                {
                    j = telemetryMetrics.length;
                }
                atelemetrymetric = new logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric[l + j];
                if (j != 0)
                {
                    System.arraycopy(telemetryMetrics, 0, atelemetrymetric, 0, j);
                }
                for (; j < atelemetrymetric.length - 1; j++)
                {
                    atelemetrymetric[j] = (logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                    codedinputbytebuffernano.readTag();
                }

                atelemetrymetric[j] = (logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                telemetryMetrics = atelemetrymetric;
                break;

            case 170: 
                if (accountableComponent == null)
                {
                    accountableComponent = new AccountableComponent();
                }
                codedinputbytebuffernano.readMessage(accountableComponent);
                break;

            case 178: 
                if (primesHeapDump == null)
                {
                    primesHeapDump = new PrimesHeapDump();
                }
                codedinputbytebuffernano.readMessage(primesHeapDump);
                break;

            case 186: 
                if (deviceInfo == null)
                {
                    deviceInfo = new DeviceInfo();
                }
                codedinputbytebuffernano.readMessage(deviceInfo);
                break;

            case 194: 
                if (primesForPrimes == null)
                {
                    primesForPrimes = new PrimesForPrimes();
                }
                codedinputbytebuffernano.readMessage(primesForPrimes);
                break;

            case 202: 
                int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 202);
                AggregatedMetric aaggregatedmetric[];
                int k;
                if (aggregatedMetrics == null)
                {
                    k = 0;
                } else
                {
                    k = aggregatedMetrics.length;
                }
                aaggregatedmetric = new AggregatedMetric[i1 + k];
                i1 = k;
                if (k != 0)
                {
                    System.arraycopy(aggregatedMetrics, 0, aaggregatedmetric, 0, k);
                    i1 = k;
                }
                for (; i1 < aaggregatedmetric.length - 1; i1++)
                {
                    aaggregatedmetric[i1] = new AggregatedMetric();
                    codedinputbytebuffernano.readMessage(aaggregatedmetric[i1]);
                    codedinputbytebuffernano.readTag();
                }

                aaggregatedmetric[i1] = new AggregatedMetric();
                codedinputbytebuffernano.readMessage(aaggregatedmetric[i1]);
                aggregatedMetrics = aaggregatedmetric;
                break;

            case 210: 
                if (primesScenario == null)
                {
                    primesScenario = new PrimesScenario();
                }
                codedinputbytebuffernano.readMessage(primesScenario);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (memoryUsageMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(1, memoryUsageMetric);
        }
        if (hashedCustomEventName != null)
        {
            codedoutputbytebuffernano.writeFixed64(2, hashedCustomEventName.longValue());
        }
        if (customEventName != null)
        {
            codedoutputbytebuffernano.writeString(3, customEventName);
        }
        if (timerMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(4, timerMetric);
        }
        if (applicationInfo != null)
        {
            codedoutputbytebuffernano.writeMessage(5, applicationInfo);
        }
        if (networkUsageMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(6, networkUsageMetric);
        }
        if (crashMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(7, crashMetric);
        }
        if (primesStats != null)
        {
            codedoutputbytebuffernano.writeMessage(8, primesStats);
        }
        if (packageMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(9, packageMetric);
        }
        if (batteryUsageMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(10, batteryUsageMetric);
        }
        if (frameRateMetric != null)
        {
            logs.proto.wireless.performance.mobile.SystemHealthProto.FrameRateMetric frameratemetric = frameRateMetric;
            CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
            codedoutputstream.writeMessage(11, frameratemetric);
            codedoutputstream.flush();
            codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
        }
        if (jankMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(12, jankMetric);
        }
        if (memoryLeakMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(13, memoryLeakMetric);
        }
        if (metricExtension != null)
        {
            codedoutputbytebuffernano.writeMessage(14, metricExtension);
        }
        if (magicEyeMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(15, magicEyeMetric);
        }
        if (primesTrace != null)
        {
            codedoutputbytebuffernano.writeMessage(16, primesTrace);
        }
        if (constantEventName != null)
        {
            codedoutputbytebuffernano.writeString(17, constantEventName);
        }
        if (cpuUsageMetric != null)
        {
            codedoutputbytebuffernano.writeMessage(18, cpuUsageMetric);
        }
        if (primesSpans != null)
        {
            codedoutputbytebuffernano.writeMessage(19, primesSpans);
        }
        if (telemetryMetrics != null && telemetryMetrics.length > 0)
        {
            for (int i = 0; i < telemetryMetrics.length; i++)
            {
                logs.proto.wireless.performance.mobile.TelemetryProto.TelemetryMetric telemetrymetric = telemetryMetrics[i];
                if (telemetrymetric != null)
                {
                    CodedOutputStream codedoutputstream1 = codedoutputbytebuffernano.getCodedOutputStream();
                    codedoutputstream1.writeMessage(20, telemetrymetric);
                    codedoutputstream1.flush();
                    codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
                }
            }

        }
        if (accountableComponent != null)
        {
            codedoutputbytebuffernano.writeMessage(21, accountableComponent);
        }
        if (primesHeapDump != null)
        {
            codedoutputbytebuffernano.writeMessage(22, primesHeapDump);
        }
        if (deviceInfo != null)
        {
            codedoutputbytebuffernano.writeMessage(23, deviceInfo);
        }
        if (primesForPrimes != null)
        {
            codedoutputbytebuffernano.writeMessage(24, primesForPrimes);
        }
        if (aggregatedMetrics != null && aggregatedMetrics.length > 0)
        {
            for (int j = ((flag) ? 1 : 0); j < aggregatedMetrics.length; j++)
            {
                AggregatedMetric aggregatedmetric = aggregatedMetrics[j];
                if (aggregatedmetric != null)
                {
                    codedoutputbytebuffernano.writeMessage(25, aggregatedmetric);
                }
            }

        }
        if (primesScenario != null)
        {
            codedoutputbytebuffernano.writeMessage(26, primesScenario);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
