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
//            Timer, ProcessHealthProto, PackageHealthProto

public final class UidHealthProto extends ExtendableMessageNano
{

    public Timer audio;
    public Long bluetoothIdleMs;
    public Long bluetoothPowerMams;
    public Long bluetoothRxBytes;
    public Long bluetoothRxMs;
    public Long bluetoothRxPackets;
    public Timer bluetoothScan;
    public Long bluetoothTxBytes;
    public Long bluetoothTxMs;
    public Long bluetoothTxPackets;
    public Long buttonUserActivityCount;
    public Timer camera;
    public Long cpuPowerMams;
    public Timer flashlight;
    public Timer foregroundActivity;
    public Timer gpsSensor;
    public Timer jobs[];
    public Long mobileIdleMs;
    public Long mobilePowerMams;
    public Timer mobileRadioActive;
    public Long mobileRxBytes;
    public Long mobileRxMs;
    public Long mobileRxPackets;
    public Long mobileTxBytes;
    public Long mobileTxMs;
    public Long mobileTxPackets;
    public Long otherUserActivityCount;
    public Timer processStateBackgroundMs;
    public Timer processStateCachedMs;
    public Timer processStateForegroundMs;
    public Timer processStateForegroundServiceMs;
    public Timer processStateTopMs;
    public Timer processStateTopSleepingMs;
    public Long realtimeBatteryMs;
    public Long realtimeScreenOffBatteryMs;
    public Timer sensors[];
    public PackageHealthProto statsPackages[];
    private logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto statsPids[];
    public ProcessHealthProto statsProcesses[];
    public Timer syncs[];
    public Long systemCpuTimeMs;
    public Long touchUserActivityCount;
    private Long uptimeBatteryMs;
    private Long uptimeScreenOffBatteryMs;
    public Long userCpuTimeMs;
    public Timer vibrator;
    public Timer video;
    public Timer wakelocksDraw[];
    public Timer wakelocksFull[];
    public Timer wakelocksPartial[];
    public Timer wakelocksWindow[];
    public Long wifiFullLockMs;
    public Long wifiIdleMs;
    public Long wifiMulticastMs;
    public Long wifiPowerMams;
    public Long wifiRunningMs;
    public Long wifiRxBytes;
    public Long wifiRxMs;
    public Long wifiRxPackets;
    public Timer wifiScan;
    public Long wifiTxBytes;
    public Long wifiTxMs;
    public Long wifiTxPackets;

    public UidHealthProto()
    {
        realtimeBatteryMs = null;
        uptimeBatteryMs = null;
        realtimeScreenOffBatteryMs = null;
        uptimeScreenOffBatteryMs = null;
        wakelocksFull = Timer.emptyArray();
        wakelocksPartial = Timer.emptyArray();
        wakelocksWindow = Timer.emptyArray();
        wakelocksDraw = Timer.emptyArray();
        syncs = Timer.emptyArray();
        jobs = Timer.emptyArray();
        gpsSensor = null;
        sensors = Timer.emptyArray();
        statsPids = new logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto[0];
        statsProcesses = ProcessHealthProto.emptyArray();
        statsPackages = PackageHealthProto.emptyArray();
        wifiIdleMs = null;
        wifiRxMs = null;
        wifiTxMs = null;
        wifiPowerMams = null;
        bluetoothIdleMs = null;
        bluetoothRxMs = null;
        bluetoothTxMs = null;
        bluetoothPowerMams = null;
        mobileIdleMs = null;
        mobileRxMs = null;
        mobileTxMs = null;
        mobilePowerMams = null;
        wifiRunningMs = null;
        wifiFullLockMs = null;
        wifiScan = null;
        wifiMulticastMs = null;
        audio = null;
        video = null;
        flashlight = null;
        camera = null;
        foregroundActivity = null;
        bluetoothScan = null;
        processStateTopMs = null;
        processStateForegroundServiceMs = null;
        processStateTopSleepingMs = null;
        processStateForegroundMs = null;
        processStateBackgroundMs = null;
        processStateCachedMs = null;
        vibrator = null;
        otherUserActivityCount = null;
        buttonUserActivityCount = null;
        touchUserActivityCount = null;
        mobileRxBytes = null;
        mobileTxBytes = null;
        wifiRxBytes = null;
        wifiTxBytes = null;
        bluetoothRxBytes = null;
        bluetoothTxBytes = null;
        mobileRxPackets = null;
        mobileTxPackets = null;
        wifiRxPackets = null;
        wifiTxPackets = null;
        bluetoothRxPackets = null;
        bluetoothTxPackets = null;
        mobileRadioActive = null;
        userCpuTimeMs = null;
        systemCpuTimeMs = null;
        cpuPowerMams = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (realtimeBatteryMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(1, realtimeBatteryMs.longValue());
        }
        j = i;
        if (uptimeBatteryMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(2, uptimeBatteryMs.longValue());
        }
        int k = j;
        if (realtimeScreenOffBatteryMs != null)
        {
            k = j + CodedOutputByteBufferNano.computeInt64Size(3, realtimeScreenOffBatteryMs.longValue());
        }
        i = k;
        if (uptimeScreenOffBatteryMs != null)
        {
            i = k + CodedOutputByteBufferNano.computeInt64Size(4, uptimeScreenOffBatteryMs.longValue());
        }
        j = i;
        if (wakelocksFull != null)
        {
            j = i;
            if (wakelocksFull.length > 0)
            {
                for (j = 0; j < wakelocksFull.length;)
                {
                    Timer timer = wakelocksFull[j];
                    k = i;
                    if (timer != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(5, timer);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        i = j;
        if (wakelocksPartial != null)
        {
            i = j;
            if (wakelocksPartial.length > 0)
            {
                i = j;
                for (j = 0; j < wakelocksPartial.length;)
                {
                    Timer timer1 = wakelocksPartial[j];
                    k = i;
                    if (timer1 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(6, timer1);
                    }
                    j++;
                    i = k;
                }

            }
        }
        j = i;
        if (wakelocksWindow != null)
        {
            j = i;
            if (wakelocksWindow.length > 0)
            {
                for (j = 0; j < wakelocksWindow.length;)
                {
                    Timer timer2 = wakelocksWindow[j];
                    k = i;
                    if (timer2 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(7, timer2);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        i = j;
        if (wakelocksDraw != null)
        {
            i = j;
            if (wakelocksDraw.length > 0)
            {
                i = j;
                for (j = 0; j < wakelocksDraw.length;)
                {
                    Timer timer3 = wakelocksDraw[j];
                    k = i;
                    if (timer3 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(8, timer3);
                    }
                    j++;
                    i = k;
                }

            }
        }
        j = i;
        if (syncs != null)
        {
            j = i;
            if (syncs.length > 0)
            {
                for (j = 0; j < syncs.length;)
                {
                    Timer timer4 = syncs[j];
                    k = i;
                    if (timer4 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(9, timer4);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        k = j;
        if (jobs != null)
        {
            k = j;
            if (jobs.length > 0)
            {
                i = j;
                for (j = 0; j < jobs.length;)
                {
                    Timer timer5 = jobs[j];
                    k = i;
                    if (timer5 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(10, timer5);
                    }
                    j++;
                    i = k;
                }

                k = i;
            }
        }
        i = k;
        if (gpsSensor != null)
        {
            i = k + CodedOutputByteBufferNano.computeMessageSize(11, gpsSensor);
        }
        j = i;
        if (sensors != null)
        {
            j = i;
            if (sensors.length > 0)
            {
                for (j = 0; j < sensors.length;)
                {
                    Timer timer6 = sensors[j];
                    k = i;
                    if (timer6 != null)
                    {
                        k = i + CodedOutputByteBufferNano.computeMessageSize(12, timer6);
                    }
                    j++;
                    i = k;
                }

                j = i;
            }
        }
        k = j;
        if (statsPids != null)
        {
            k = j;
            if (statsPids.length > 0)
            {
                i = j;
                for (j = 0; j < statsPids.length;)
                {
                    logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto pidhealthproto = statsPids[j];
                    k = i;
                    if (pidhealthproto != null)
                    {
                        k = i + CodedOutputStream.computeMessageSize(13, pidhealthproto);
                    }
                    j++;
                    i = k;
                }

                k = i;
            }
        }
        i = k;
        if (statsProcesses != null)
        {
            i = k;
            if (statsProcesses.length > 0)
            {
                i = k;
                for (j = 0; j < statsProcesses.length;)
                {
                    ProcessHealthProto processhealthproto = statsProcesses[j];
                    int l = i;
                    if (processhealthproto != null)
                    {
                        l = i + CodedOutputByteBufferNano.computeMessageSize(14, processhealthproto);
                    }
                    j++;
                    i = l;
                }

            }
        }
        j = i;
        if (statsPackages != null)
        {
            j = i;
            if (statsPackages.length > 0)
            {
                int i1 = ((flag) ? 1 : 0);
                do
                {
                    j = i;
                    if (i1 >= statsPackages.length)
                    {
                        break;
                    }
                    PackageHealthProto packagehealthproto = statsPackages[i1];
                    j = i;
                    if (packagehealthproto != null)
                    {
                        j = i + CodedOutputByteBufferNano.computeMessageSize(15, packagehealthproto);
                    }
                    i1++;
                    i = j;
                } while (true);
            }
        }
        i = j;
        if (wifiIdleMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(16, wifiIdleMs.longValue());
        }
        j = i;
        if (wifiRxMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(17, wifiRxMs.longValue());
        }
        i = j;
        if (wifiTxMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(18, wifiTxMs.longValue());
        }
        j = i;
        if (wifiPowerMams != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(19, wifiPowerMams.longValue());
        }
        i = j;
        if (bluetoothIdleMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(20, bluetoothIdleMs.longValue());
        }
        j = i;
        if (bluetoothRxMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(21, bluetoothRxMs.longValue());
        }
        i = j;
        if (bluetoothTxMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(22, bluetoothTxMs.longValue());
        }
        j = i;
        if (bluetoothPowerMams != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(23, bluetoothPowerMams.longValue());
        }
        i = j;
        if (mobileIdleMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(24, mobileIdleMs.longValue());
        }
        j = i;
        if (mobileRxMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(25, mobileRxMs.longValue());
        }
        i = j;
        if (mobileTxMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(26, mobileTxMs.longValue());
        }
        j = i;
        if (mobilePowerMams != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(27, mobilePowerMams.longValue());
        }
        i = j;
        if (wifiRunningMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(28, wifiRunningMs.longValue());
        }
        j = i;
        if (wifiFullLockMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(29, wifiFullLockMs.longValue());
        }
        i = j;
        if (wifiScan != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(30, wifiScan);
        }
        j = i;
        if (wifiMulticastMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(31, wifiMulticastMs.longValue());
        }
        i = j;
        if (audio != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(32, audio);
        }
        j = i;
        if (video != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(33, video);
        }
        i = j;
        if (flashlight != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(34, flashlight);
        }
        j = i;
        if (camera != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(35, camera);
        }
        i = j;
        if (foregroundActivity != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(36, foregroundActivity);
        }
        j = i;
        if (bluetoothScan != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(37, bluetoothScan);
        }
        i = j;
        if (processStateTopMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(38, processStateTopMs);
        }
        j = i;
        if (processStateForegroundServiceMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(39, processStateForegroundServiceMs);
        }
        i = j;
        if (processStateTopSleepingMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(40, processStateTopSleepingMs);
        }
        j = i;
        if (processStateForegroundMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(41, processStateForegroundMs);
        }
        i = j;
        if (processStateBackgroundMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(42, processStateBackgroundMs);
        }
        j = i;
        if (processStateCachedMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeMessageSize(43, processStateCachedMs);
        }
        i = j;
        if (vibrator != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(44, vibrator);
        }
        j = i;
        if (otherUserActivityCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(45, otherUserActivityCount.longValue());
        }
        i = j;
        if (buttonUserActivityCount != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(46, buttonUserActivityCount.longValue());
        }
        j = i;
        if (touchUserActivityCount != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(47, touchUserActivityCount.longValue());
        }
        i = j;
        if (mobileRxBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(48, mobileRxBytes.longValue());
        }
        j = i;
        if (mobileTxBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(49, mobileTxBytes.longValue());
        }
        i = j;
        if (wifiRxBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(50, wifiRxBytes.longValue());
        }
        j = i;
        if (wifiTxBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(51, wifiTxBytes.longValue());
        }
        i = j;
        if (bluetoothRxBytes != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(52, bluetoothRxBytes.longValue());
        }
        j = i;
        if (bluetoothTxBytes != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(53, bluetoothTxBytes.longValue());
        }
        i = j;
        if (mobileRxPackets != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(54, mobileRxPackets.longValue());
        }
        j = i;
        if (mobileTxPackets != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(55, mobileTxPackets.longValue());
        }
        i = j;
        if (wifiRxPackets != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(56, wifiRxPackets.longValue());
        }
        j = i;
        if (wifiTxPackets != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(57, wifiTxPackets.longValue());
        }
        i = j;
        if (bluetoothRxPackets != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(58, bluetoothRxPackets.longValue());
        }
        j = i;
        if (bluetoothTxPackets != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(59, bluetoothTxPackets.longValue());
        }
        i = j;
        if (mobileRadioActive != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(61, mobileRadioActive);
        }
        j = i;
        if (userCpuTimeMs != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(62, userCpuTimeMs.longValue());
        }
        i = j;
        if (systemCpuTimeMs != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt64Size(63, systemCpuTimeMs.longValue());
        }
        j = i;
        if (cpuPowerMams != null)
        {
            j = i + CodedOutputByteBufferNano.computeInt64Size(64, cpuPowerMams.longValue());
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
                realtimeBatteryMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 16: // '\020'
                uptimeBatteryMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 24: // '\030'
                realtimeScreenOffBatteryMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 32: // ' '
                uptimeScreenOffBatteryMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 42: // '*'
                int l2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 42);
                Timer atimer[];
                int j;
                if (wakelocksFull == null)
                {
                    j = 0;
                } else
                {
                    j = wakelocksFull.length;
                }
                atimer = new Timer[l2 + j];
                l2 = j;
                if (j != 0)
                {
                    System.arraycopy(wakelocksFull, 0, atimer, 0, j);
                    l2 = j;
                }
                for (; l2 < atimer.length - 1; l2++)
                {
                    atimer[l2] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer[l2]);
                    codedinputbytebuffernano.readTag();
                }

                atimer[l2] = new Timer();
                codedinputbytebuffernano.readMessage(atimer[l2]);
                wakelocksFull = atimer;
                break;

            case 50: // '2'
                int i3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 50);
                Timer atimer1[];
                int k;
                if (wakelocksPartial == null)
                {
                    k = 0;
                } else
                {
                    k = wakelocksPartial.length;
                }
                atimer1 = new Timer[i3 + k];
                i3 = k;
                if (k != 0)
                {
                    System.arraycopy(wakelocksPartial, 0, atimer1, 0, k);
                    i3 = k;
                }
                for (; i3 < atimer1.length - 1; i3++)
                {
                    atimer1[i3] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer1[i3]);
                    codedinputbytebuffernano.readTag();
                }

                atimer1[i3] = new Timer();
                codedinputbytebuffernano.readMessage(atimer1[i3]);
                wakelocksPartial = atimer1;
                break;

            case 58: // ':'
                int j3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 58);
                Timer atimer2[];
                int l;
                if (wakelocksWindow == null)
                {
                    l = 0;
                } else
                {
                    l = wakelocksWindow.length;
                }
                atimer2 = new Timer[j3 + l];
                j3 = l;
                if (l != 0)
                {
                    System.arraycopy(wakelocksWindow, 0, atimer2, 0, l);
                    j3 = l;
                }
                for (; j3 < atimer2.length - 1; j3++)
                {
                    atimer2[j3] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer2[j3]);
                    codedinputbytebuffernano.readTag();
                }

                atimer2[j3] = new Timer();
                codedinputbytebuffernano.readMessage(atimer2[j3]);
                wakelocksWindow = atimer2;
                break;

            case 66: // 'B'
                int k3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 66);
                Timer atimer3[];
                int i1;
                if (wakelocksDraw == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = wakelocksDraw.length;
                }
                atimer3 = new Timer[k3 + i1];
                k3 = i1;
                if (i1 != 0)
                {
                    System.arraycopy(wakelocksDraw, 0, atimer3, 0, i1);
                    k3 = i1;
                }
                for (; k3 < atimer3.length - 1; k3++)
                {
                    atimer3[k3] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer3[k3]);
                    codedinputbytebuffernano.readTag();
                }

                atimer3[k3] = new Timer();
                codedinputbytebuffernano.readMessage(atimer3[k3]);
                wakelocksDraw = atimer3;
                break;

            case 74: // 'J'
                int l3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 74);
                Timer atimer4[];
                int j1;
                if (syncs == null)
                {
                    j1 = 0;
                } else
                {
                    j1 = syncs.length;
                }
                atimer4 = new Timer[l3 + j1];
                l3 = j1;
                if (j1 != 0)
                {
                    System.arraycopy(syncs, 0, atimer4, 0, j1);
                    l3 = j1;
                }
                for (; l3 < atimer4.length - 1; l3++)
                {
                    atimer4[l3] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer4[l3]);
                    codedinputbytebuffernano.readTag();
                }

                atimer4[l3] = new Timer();
                codedinputbytebuffernano.readMessage(atimer4[l3]);
                syncs = atimer4;
                break;

            case 82: // 'R'
                int i4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 82);
                Timer atimer5[];
                int k1;
                if (jobs == null)
                {
                    k1 = 0;
                } else
                {
                    k1 = jobs.length;
                }
                atimer5 = new Timer[i4 + k1];
                i4 = k1;
                if (k1 != 0)
                {
                    System.arraycopy(jobs, 0, atimer5, 0, k1);
                    i4 = k1;
                }
                for (; i4 < atimer5.length - 1; i4++)
                {
                    atimer5[i4] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer5[i4]);
                    codedinputbytebuffernano.readTag();
                }

                atimer5[i4] = new Timer();
                codedinputbytebuffernano.readMessage(atimer5[i4]);
                jobs = atimer5;
                break;

            case 90: // 'Z'
                if (gpsSensor == null)
                {
                    gpsSensor = new Timer();
                }
                codedinputbytebuffernano.readMessage(gpsSensor);
                break;

            case 98: // 'b'
                int j4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 98);
                Timer atimer6[];
                int l1;
                if (sensors == null)
                {
                    l1 = 0;
                } else
                {
                    l1 = sensors.length;
                }
                atimer6 = new Timer[j4 + l1];
                j4 = l1;
                if (l1 != 0)
                {
                    System.arraycopy(sensors, 0, atimer6, 0, l1);
                    j4 = l1;
                }
                for (; j4 < atimer6.length - 1; j4++)
                {
                    atimer6[j4] = new Timer();
                    codedinputbytebuffernano.readMessage(atimer6[j4]);
                    codedinputbytebuffernano.readTag();
                }

                atimer6[j4] = new Timer();
                codedinputbytebuffernano.readMessage(atimer6[j4]);
                sensors = atimer6;
                break;

            case 106: // 'j'
                int k4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 106);
                logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto apidhealthproto[];
                int i2;
                if (statsPids == null)
                {
                    i2 = 0;
                } else
                {
                    i2 = statsPids.length;
                }
                apidhealthproto = new logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto[k4 + i2];
                if (i2 != 0)
                {
                    System.arraycopy(statsPids, 0, apidhealthproto, 0, i2);
                }
                for (; i2 < apidhealthproto.length - 1; i2++)
                {
                    apidhealthproto[i2] = (logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                    codedinputbytebuffernano.readTag();
                }

                apidhealthproto[i2] = (logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto)codedinputbytebuffernano.readMessageLite((Parser)logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
                statsPids = apidhealthproto;
                break;

            case 114: // 'r'
                int l4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 114);
                ProcessHealthProto aprocesshealthproto[];
                int j2;
                if (statsProcesses == null)
                {
                    j2 = 0;
                } else
                {
                    j2 = statsProcesses.length;
                }
                aprocesshealthproto = new ProcessHealthProto[l4 + j2];
                l4 = j2;
                if (j2 != 0)
                {
                    System.arraycopy(statsProcesses, 0, aprocesshealthproto, 0, j2);
                    l4 = j2;
                }
                for (; l4 < aprocesshealthproto.length - 1; l4++)
                {
                    aprocesshealthproto[l4] = new ProcessHealthProto();
                    codedinputbytebuffernano.readMessage(aprocesshealthproto[l4]);
                    codedinputbytebuffernano.readTag();
                }

                aprocesshealthproto[l4] = new ProcessHealthProto();
                codedinputbytebuffernano.readMessage(aprocesshealthproto[l4]);
                statsProcesses = aprocesshealthproto;
                break;

            case 122: // 'z'
                int i5 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 122);
                PackageHealthProto apackagehealthproto[];
                int k2;
                if (statsPackages == null)
                {
                    k2 = 0;
                } else
                {
                    k2 = statsPackages.length;
                }
                apackagehealthproto = new PackageHealthProto[i5 + k2];
                i5 = k2;
                if (k2 != 0)
                {
                    System.arraycopy(statsPackages, 0, apackagehealthproto, 0, k2);
                    i5 = k2;
                }
                for (; i5 < apackagehealthproto.length - 1; i5++)
                {
                    apackagehealthproto[i5] = new PackageHealthProto();
                    codedinputbytebuffernano.readMessage(apackagehealthproto[i5]);
                    codedinputbytebuffernano.readTag();
                }

                apackagehealthproto[i5] = new PackageHealthProto();
                codedinputbytebuffernano.readMessage(apackagehealthproto[i5]);
                statsPackages = apackagehealthproto;
                break;

            case 128: 
                wifiIdleMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 136: 
                wifiRxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 144: 
                wifiTxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 152: 
                wifiPowerMams = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 160: 
                bluetoothIdleMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 168: 
                bluetoothRxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 176: 
                bluetoothTxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 184: 
                bluetoothPowerMams = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 192: 
                mobileIdleMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 200: 
                mobileRxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 208: 
                mobileTxMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 216: 
                mobilePowerMams = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 224: 
                wifiRunningMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 232: 
                wifiFullLockMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 242: 
                if (wifiScan == null)
                {
                    wifiScan = new Timer();
                }
                codedinputbytebuffernano.readMessage(wifiScan);
                break;

            case 248: 
                wifiMulticastMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 258: 
                if (audio == null)
                {
                    audio = new Timer();
                }
                codedinputbytebuffernano.readMessage(audio);
                break;

            case 266: 
                if (video == null)
                {
                    video = new Timer();
                }
                codedinputbytebuffernano.readMessage(video);
                break;

            case 274: 
                if (flashlight == null)
                {
                    flashlight = new Timer();
                }
                codedinputbytebuffernano.readMessage(flashlight);
                break;

            case 282: 
                if (camera == null)
                {
                    camera = new Timer();
                }
                codedinputbytebuffernano.readMessage(camera);
                break;

            case 290: 
                if (foregroundActivity == null)
                {
                    foregroundActivity = new Timer();
                }
                codedinputbytebuffernano.readMessage(foregroundActivity);
                break;

            case 298: 
                if (bluetoothScan == null)
                {
                    bluetoothScan = new Timer();
                }
                codedinputbytebuffernano.readMessage(bluetoothScan);
                break;

            case 306: 
                if (processStateTopMs == null)
                {
                    processStateTopMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateTopMs);
                break;

            case 314: 
                if (processStateForegroundServiceMs == null)
                {
                    processStateForegroundServiceMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateForegroundServiceMs);
                break;

            case 322: 
                if (processStateTopSleepingMs == null)
                {
                    processStateTopSleepingMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateTopSleepingMs);
                break;

            case 330: 
                if (processStateForegroundMs == null)
                {
                    processStateForegroundMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateForegroundMs);
                break;

            case 338: 
                if (processStateBackgroundMs == null)
                {
                    processStateBackgroundMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateBackgroundMs);
                break;

            case 346: 
                if (processStateCachedMs == null)
                {
                    processStateCachedMs = new Timer();
                }
                codedinputbytebuffernano.readMessage(processStateCachedMs);
                break;

            case 354: 
                if (vibrator == null)
                {
                    vibrator = new Timer();
                }
                codedinputbytebuffernano.readMessage(vibrator);
                break;

            case 360: 
                otherUserActivityCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 368: 
                buttonUserActivityCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 376: 
                touchUserActivityCount = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 384: 
                mobileRxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 392: 
                mobileTxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 400: 
                wifiRxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 408: 
                wifiTxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 416: 
                bluetoothRxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 424: 
                bluetoothTxBytes = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 432: 
                mobileRxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 440: 
                mobileTxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 448: 
                wifiRxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 456: 
                wifiTxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 464: 
                bluetoothRxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 472: 
                bluetoothTxPackets = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 490: 
                if (mobileRadioActive == null)
                {
                    mobileRadioActive = new Timer();
                }
                codedinputbytebuffernano.readMessage(mobileRadioActive);
                break;

            case 496: 
                userCpuTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 504: 
                systemCpuTimeMs = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;

            case 512: 
                cpuPowerMams = Long.valueOf(codedinputbytebuffernano.readInt64());
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (realtimeBatteryMs != null)
        {
            codedoutputbytebuffernano.writeInt64(1, realtimeBatteryMs.longValue());
        }
        if (uptimeBatteryMs != null)
        {
            codedoutputbytebuffernano.writeInt64(2, uptimeBatteryMs.longValue());
        }
        if (realtimeScreenOffBatteryMs != null)
        {
            codedoutputbytebuffernano.writeInt64(3, realtimeScreenOffBatteryMs.longValue());
        }
        if (uptimeScreenOffBatteryMs != null)
        {
            codedoutputbytebuffernano.writeInt64(4, uptimeScreenOffBatteryMs.longValue());
        }
        if (wakelocksFull != null && wakelocksFull.length > 0)
        {
            for (int i = 0; i < wakelocksFull.length; i++)
            {
                Timer timer = wakelocksFull[i];
                if (timer != null)
                {
                    codedoutputbytebuffernano.writeMessage(5, timer);
                }
            }

        }
        if (wakelocksPartial != null && wakelocksPartial.length > 0)
        {
            for (int j = 0; j < wakelocksPartial.length; j++)
            {
                Timer timer1 = wakelocksPartial[j];
                if (timer1 != null)
                {
                    codedoutputbytebuffernano.writeMessage(6, timer1);
                }
            }

        }
        if (wakelocksWindow != null && wakelocksWindow.length > 0)
        {
            for (int k = 0; k < wakelocksWindow.length; k++)
            {
                Timer timer2 = wakelocksWindow[k];
                if (timer2 != null)
                {
                    codedoutputbytebuffernano.writeMessage(7, timer2);
                }
            }

        }
        if (wakelocksDraw != null && wakelocksDraw.length > 0)
        {
            for (int l = 0; l < wakelocksDraw.length; l++)
            {
                Timer timer3 = wakelocksDraw[l];
                if (timer3 != null)
                {
                    codedoutputbytebuffernano.writeMessage(8, timer3);
                }
            }

        }
        if (syncs != null && syncs.length > 0)
        {
            for (int i1 = 0; i1 < syncs.length; i1++)
            {
                Timer timer4 = syncs[i1];
                if (timer4 != null)
                {
                    codedoutputbytebuffernano.writeMessage(9, timer4);
                }
            }

        }
        if (jobs != null && jobs.length > 0)
        {
            for (int j1 = 0; j1 < jobs.length; j1++)
            {
                Timer timer5 = jobs[j1];
                if (timer5 != null)
                {
                    codedoutputbytebuffernano.writeMessage(10, timer5);
                }
            }

        }
        if (gpsSensor != null)
        {
            codedoutputbytebuffernano.writeMessage(11, gpsSensor);
        }
        if (sensors != null && sensors.length > 0)
        {
            for (int k1 = 0; k1 < sensors.length; k1++)
            {
                Timer timer6 = sensors[k1];
                if (timer6 != null)
                {
                    codedoutputbytebuffernano.writeMessage(12, timer6);
                }
            }

        }
        if (statsPids != null && statsPids.length > 0)
        {
            for (int l1 = 0; l1 < statsPids.length; l1++)
            {
                logs.proto.wireless.performance.mobile.BatteryMetric.PidHealthProto pidhealthproto = statsPids[l1];
                if (pidhealthproto != null)
                {
                    CodedOutputStream codedoutputstream = codedoutputbytebuffernano.getCodedOutputStream();
                    codedoutputstream.writeMessage(13, pidhealthproto);
                    codedoutputstream.flush();
                    codedoutputbytebuffernano.codedOutputStreamPosition = codedoutputbytebuffernano.buffer.position();
                }
            }

        }
        if (statsProcesses != null && statsProcesses.length > 0)
        {
            for (int i2 = 0; i2 < statsProcesses.length; i2++)
            {
                ProcessHealthProto processhealthproto = statsProcesses[i2];
                if (processhealthproto != null)
                {
                    codedoutputbytebuffernano.writeMessage(14, processhealthproto);
                }
            }

        }
        if (statsPackages != null && statsPackages.length > 0)
        {
            for (int j2 = ((flag) ? 1 : 0); j2 < statsPackages.length; j2++)
            {
                PackageHealthProto packagehealthproto = statsPackages[j2];
                if (packagehealthproto != null)
                {
                    codedoutputbytebuffernano.writeMessage(15, packagehealthproto);
                }
            }

        }
        if (wifiIdleMs != null)
        {
            codedoutputbytebuffernano.writeInt64(16, wifiIdleMs.longValue());
        }
        if (wifiRxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(17, wifiRxMs.longValue());
        }
        if (wifiTxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(18, wifiTxMs.longValue());
        }
        if (wifiPowerMams != null)
        {
            codedoutputbytebuffernano.writeInt64(19, wifiPowerMams.longValue());
        }
        if (bluetoothIdleMs != null)
        {
            codedoutputbytebuffernano.writeInt64(20, bluetoothIdleMs.longValue());
        }
        if (bluetoothRxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(21, bluetoothRxMs.longValue());
        }
        if (bluetoothTxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(22, bluetoothTxMs.longValue());
        }
        if (bluetoothPowerMams != null)
        {
            codedoutputbytebuffernano.writeInt64(23, bluetoothPowerMams.longValue());
        }
        if (mobileIdleMs != null)
        {
            codedoutputbytebuffernano.writeInt64(24, mobileIdleMs.longValue());
        }
        if (mobileRxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(25, mobileRxMs.longValue());
        }
        if (mobileTxMs != null)
        {
            codedoutputbytebuffernano.writeInt64(26, mobileTxMs.longValue());
        }
        if (mobilePowerMams != null)
        {
            codedoutputbytebuffernano.writeInt64(27, mobilePowerMams.longValue());
        }
        if (wifiRunningMs != null)
        {
            codedoutputbytebuffernano.writeInt64(28, wifiRunningMs.longValue());
        }
        if (wifiFullLockMs != null)
        {
            codedoutputbytebuffernano.writeInt64(29, wifiFullLockMs.longValue());
        }
        if (wifiScan != null)
        {
            codedoutputbytebuffernano.writeMessage(30, wifiScan);
        }
        if (wifiMulticastMs != null)
        {
            codedoutputbytebuffernano.writeInt64(31, wifiMulticastMs.longValue());
        }
        if (audio != null)
        {
            codedoutputbytebuffernano.writeMessage(32, audio);
        }
        if (video != null)
        {
            codedoutputbytebuffernano.writeMessage(33, video);
        }
        if (flashlight != null)
        {
            codedoutputbytebuffernano.writeMessage(34, flashlight);
        }
        if (camera != null)
        {
            codedoutputbytebuffernano.writeMessage(35, camera);
        }
        if (foregroundActivity != null)
        {
            codedoutputbytebuffernano.writeMessage(36, foregroundActivity);
        }
        if (bluetoothScan != null)
        {
            codedoutputbytebuffernano.writeMessage(37, bluetoothScan);
        }
        if (processStateTopMs != null)
        {
            codedoutputbytebuffernano.writeMessage(38, processStateTopMs);
        }
        if (processStateForegroundServiceMs != null)
        {
            codedoutputbytebuffernano.writeMessage(39, processStateForegroundServiceMs);
        }
        if (processStateTopSleepingMs != null)
        {
            codedoutputbytebuffernano.writeMessage(40, processStateTopSleepingMs);
        }
        if (processStateForegroundMs != null)
        {
            codedoutputbytebuffernano.writeMessage(41, processStateForegroundMs);
        }
        if (processStateBackgroundMs != null)
        {
            codedoutputbytebuffernano.writeMessage(42, processStateBackgroundMs);
        }
        if (processStateCachedMs != null)
        {
            codedoutputbytebuffernano.writeMessage(43, processStateCachedMs);
        }
        if (vibrator != null)
        {
            codedoutputbytebuffernano.writeMessage(44, vibrator);
        }
        if (otherUserActivityCount != null)
        {
            codedoutputbytebuffernano.writeInt64(45, otherUserActivityCount.longValue());
        }
        if (buttonUserActivityCount != null)
        {
            codedoutputbytebuffernano.writeInt64(46, buttonUserActivityCount.longValue());
        }
        if (touchUserActivityCount != null)
        {
            codedoutputbytebuffernano.writeInt64(47, touchUserActivityCount.longValue());
        }
        if (mobileRxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(48, mobileRxBytes.longValue());
        }
        if (mobileTxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(49, mobileTxBytes.longValue());
        }
        if (wifiRxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(50, wifiRxBytes.longValue());
        }
        if (wifiTxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(51, wifiTxBytes.longValue());
        }
        if (bluetoothRxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(52, bluetoothRxBytes.longValue());
        }
        if (bluetoothTxBytes != null)
        {
            codedoutputbytebuffernano.writeInt64(53, bluetoothTxBytes.longValue());
        }
        if (mobileRxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(54, mobileRxPackets.longValue());
        }
        if (mobileTxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(55, mobileTxPackets.longValue());
        }
        if (wifiRxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(56, wifiRxPackets.longValue());
        }
        if (wifiTxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(57, wifiTxPackets.longValue());
        }
        if (bluetoothRxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(58, bluetoothRxPackets.longValue());
        }
        if (bluetoothTxPackets != null)
        {
            codedoutputbytebuffernano.writeInt64(59, bluetoothTxPackets.longValue());
        }
        if (mobileRadioActive != null)
        {
            codedoutputbytebuffernano.writeMessage(61, mobileRadioActive);
        }
        if (userCpuTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(62, userCpuTimeMs.longValue());
        }
        if (systemCpuTimeMs != null)
        {
            codedoutputbytebuffernano.writeInt64(63, systemCpuTimeMs.longValue());
        }
        if (cpuPowerMams != null)
        {
            codedoutputbytebuffernano.writeInt64(64, cpuPowerMams.longValue());
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
