// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.HealthStats;
import com.google.android.libraries.performance.primes.MetricStamper;
import com.google.android.libraries.performance.primes.Supplier;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.UidHealthProto;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            BatteryCapture, HealthStatsProtos, SystemHealthCapture, HashingNameSanitizer

public final class metricExtension
{

    private final Long currentTime;
    private final String customEventName;
    private final Long elapsedTime;
    private final HealthStats healthStats;
    private final Boolean isEventNameConstant;
    private final MetricExtension metricExtension;
    private final Integer sampleInfo;
    private final BatteryCapture this$0;

    public final  toStatsRecord()
    {
        Long long1 = null;
        Object obj1 = systemHealthCapture;
        Object obj2 = healthStats;
        UidHealthProto uidhealthproto = new UidHealthProto();
        uidhealthproto.realtimeBatteryMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10001);
        uidhealthproto.realtimeScreenOffBatteryMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10003);
        Object obj;
        long l;
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10005))
        {
            obj = ((HealthStats) (obj2)).getTimers(10005);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.wakelocksFull = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10006))
        {
            obj = ((HealthStats) (obj2)).getTimers(10006);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.wakelocksPartial = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10007))
        {
            obj = ((HealthStats) (obj2)).getTimers(10007);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.wakelocksWindow = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10008))
        {
            obj = ((HealthStats) (obj2)).getTimers(10008);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.wakelocksDraw = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10009))
        {
            obj = ((HealthStats) (obj2)).getTimers(10009);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.syncs = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10010))
        {
            obj = ((HealthStats) (obj2)).getTimers(10010);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.jobs = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10011))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10011));
        } else
        {
            obj = null;
        }
        uidhealthproto.gpsSensor = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimers(10012))
        {
            obj = ((HealthStats) (obj2)).getTimers(10012);
            obj = (logs.proto.wireless.performance.mobile.nano.Timer[])ps.INSTANCE.convert(((java.util.Map) (obj)));
        } else
        {
            obj = null;
        }
        uidhealthproto.sensors = ((logs.proto.wireless.performance.mobile.nano.Timer []) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasStats(10014))
        {
            obj = ((HealthStats) (obj2)).getStats(10014);
        } else
        {
            obj = null;
        }
        uidhealthproto.statsProcesses = (logs.proto.wireless.performance.mobile.nano.ProcessHealthProto[])sOps.INSTANCE.convert(((java.util.Map) (obj)));
        if (obj2 != null && ((HealthStats) (obj2)).hasStats(10015))
        {
            obj = ((HealthStats) (obj2)).getStats(10015);
        } else
        {
            obj = null;
        }
        uidhealthproto.statsPackages = (logs.proto.wireless.performance.mobile.nano.PackageHealthProto[])eOps.INSTANCE.convert(((java.util.Map) (obj)));
        uidhealthproto.wifiIdleMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10016);
        uidhealthproto.wifiRxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10017);
        uidhealthproto.wifiTxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10018);
        uidhealthproto.wifiPowerMams = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10019);
        uidhealthproto.bluetoothIdleMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10020);
        uidhealthproto.bluetoothRxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10021);
        uidhealthproto.bluetoothTxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10022);
        uidhealthproto.bluetoothPowerMams = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10023);
        uidhealthproto.mobileIdleMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10024);
        uidhealthproto.mobileRxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10025);
        uidhealthproto.mobileTxMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10026);
        uidhealthproto.mobilePowerMams = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10027);
        uidhealthproto.wifiRunningMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10028);
        uidhealthproto.wifiFullLockMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10029);
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10030))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10030));
        } else
        {
            obj = null;
        }
        uidhealthproto.wifiScan = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        uidhealthproto.wifiMulticastMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10031);
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10032))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10032));
        } else
        {
            obj = null;
        }
        uidhealthproto.audio = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10033))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10033));
        } else
        {
            obj = null;
        }
        uidhealthproto.video = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10034))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10034));
        } else
        {
            obj = null;
        }
        uidhealthproto.flashlight = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10035))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10035));
        } else
        {
            obj = null;
        }
        uidhealthproto.camera = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10036))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10036));
        } else
        {
            obj = null;
        }
        uidhealthproto.foregroundActivity = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10037))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10037));
        } else
        {
            obj = null;
        }
        uidhealthproto.bluetoothScan = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10038))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10038));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateTopMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10039))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10039));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateForegroundServiceMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10040))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10040));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateTopSleepingMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10041))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10041));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateForegroundMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10042))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10042));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateBackgroundMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10043))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10043));
        } else
        {
            obj = null;
        }
        uidhealthproto.processStateCachedMs = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        if (obj2 != null && ((HealthStats) (obj2)).hasTimer(10044))
        {
            obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10044));
        } else
        {
            obj = null;
        }
        uidhealthproto.vibrator = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        uidhealthproto.otherUserActivityCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10045);
        uidhealthproto.buttonUserActivityCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10046);
        uidhealthproto.touchUserActivityCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10047);
        uidhealthproto.mobileRxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10048);
        uidhealthproto.mobileTxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10049);
        uidhealthproto.wifiRxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10050);
        uidhealthproto.wifiTxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10051);
        uidhealthproto.bluetoothRxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10052);
        uidhealthproto.bluetoothTxBytes = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10053);
        uidhealthproto.mobileRxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10054);
        uidhealthproto.mobileTxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10055);
        uidhealthproto.wifiRxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10056);
        uidhealthproto.wifiTxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10057);
        uidhealthproto.bluetoothRxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10058);
        uidhealthproto.bluetoothTxPackets = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10059);
        obj = long1;
        if (obj2 != null)
        {
            obj = long1;
            if (((HealthStats) (obj2)).hasTimer(10061))
            {
                obj = HealthStatsProtos.timer(null, ((HealthStats) (obj2)).getTimer(10061));
            }
        }
        uidhealthproto.mobileRadioActive = ((logs.proto.wireless.performance.mobile.nano.Timer) (obj));
        uidhealthproto.userCpuTimeMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10062);
        uidhealthproto.systemCpuTimeMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10063);
        uidhealthproto.cpuPowerMams = HealthStatsProtos.getMeasurement(((HealthStats) (obj2)), 10064);
        obj = ((SystemHealthCapture) (obj1)).hashingNameSanitizer;
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.WAKELOCK, uidhealthproto.wakelocksFull);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.WAKELOCK, uidhealthproto.wakelocksPartial);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.WAKELOCK, uidhealthproto.wakelocksWindow);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.WAKELOCK, uidhealthproto.wakelocksDraw);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.SYNC, uidhealthproto.syncs);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.JOB, uidhealthproto.jobs);
        ((HashingNameSanitizer) (obj)).hashRawTimerNames(eType.SENSOR, uidhealthproto.sensors);
        obj = elapsedTime;
        long1 = currentTime;
        obj1 = ((MetricStamper)metricStamperSupplier.get()).primesVersion;
        obj2 = ((MetricStamper)metricStamperSupplier.get()).versionName;
        if (obj2 == null)
        {
            l = 0L;
        } else
        {
            l = ((String) (obj2)).hashCode();
        }
        return new (uidhealthproto, ((Long) (obj)), long1, ((Long) (obj1)), Long.valueOf(l), sampleInfo, customEventName, isEventNameConstant, metricExtension);
    }

    public (Long long1, Long long2, HealthStats healthstats, Integer integer, String s, Boolean boolean1, 
            MetricExtension metricextension)
    {
        this$0 = BatteryCapture.this;
        super();
        elapsedTime = long1;
        currentTime = long2;
        healthStats = healthstats;
        sampleInfo = integer;
        customEventName = s;
        isEventNameConstant = boolean1;
        metricExtension = metricextension;
    }
}
