// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.health.SystemHealthManager;
import android.util.Base64;
import android.util.Log;
import com.google.android.libraries.performance.primes.battery.BatteryCapture;
import com.google.android.libraries.performance.primes.battery.HashingNameSanitizer;
import com.google.android.libraries.performance.primes.battery.HealthStatsProtos;
import com.google.android.libraries.performance.primes.battery.StatsStorage;
import com.google.android.libraries.performance.primes.battery.SystemHealthCapture;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.proto.primes.persistent.nano.BatterySnapshot;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.nano.BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.nano.BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;
import logs.proto.wireless.performance.mobile.nano.UidHealthProto;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, Supplier, BatteryMetricExtensionProvider, 
//            AppLifecycleMonitor

final class BatteryMetricService extends AbstractMetricService
    implements AppLifecycleListener.OnAppToBackground, AppLifecycleListener.OnAppToForeground, PrimesStartupListener
{

    private final BatteryCapture batteryCapture;
    private final List batteryCaptures;
    private final AtomicBoolean inForeground = new AtomicBoolean();
    private final boolean logDeferred;
    private final Object monitorMutex = new Object();
    private volatile boolean monitoring;
    public final StatsStorage storage;

    BatteryMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, SharedPreferences sharedpreferences, BatteryCapture batterycapture, boolean flag)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        monitoring = false;
        new ConcurrentHashMap();
        storage = new StatsStorage(sharedpreferences);
        batteryCapture = batterycapture;
        logDeferred = flag;
        if (flag)
        {
            metrictransmitter = new ArrayList();
        } else
        {
            metrictransmitter = null;
        }
        batteryCaptures = metrictransmitter;
    }

    private final Future captureForDeferredLogging(int i, String s, boolean flag)
    {
label0:
        {
            class .Lambda._cls4
                implements Callable
            {

                private final BatteryMetricService arg$1;
                private final int arg$2;
                private final String arg$3;
                private final boolean arg$4;

                public final Object call()
                {
                    return arg$1.captureBattery(arg$2, arg$3, arg$4);
                }

            .Lambda._cls4(int i, String s, boolean flag)
            {
                arg$1 = BatteryMetricService.this;
                arg$2 = i;
                arg$3 = s;
                arg$4 = flag;
            }
            }

            Future future = ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new .Lambda._cls4(i, s, flag));
            s = "adding future BatteryCapture";
            Object aobj[] = new Object[0];
            if (Log.isLoggable("BatteryMetricService", 3))
            {
                if (aobj.length != 0)
                {
                    s = String.format(Locale.US, "adding future BatteryCapture", aobj);
                }
                Log.println(3, "BatteryMetricService", s);
            }
            synchronized (batteryCaptures)
            {
                batteryCaptures.add(future);
                if (!inForeground.get())
                {
                    break label0;
                }
            }
            return future;
        }
        Future future1 = logDeferredData();
        s;
        JVM INSTR monitorexit ;
        return future1;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final Future logDeferredData()
    {
        ArrayList arraylist;
        synchronized (batteryCaptures)
        {
            arraylist = new ArrayList(batteryCaptures);
            batteryCaptures.clear();
        }
        obj = "Logging captures: %d";
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(arraylist.size());
        if (Log.isLoggable("BatteryMetricService", 4))
        {
            class .Lambda._cls5
                implements Runnable
            {

                private final BatteryMetricService arg$1;
                private final List arg$2;

                public final void run()
                {
                    com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord statsrecord;
                    BatteryMetricService batterymetricservice;
                    Iterator iterator;
                    batterymetricservice = arg$1;
                    List list = arg$2;
                    statsrecord = batterymetricservice.fromStorage();
                    iterator = list.iterator();
_L2:
                    Object obj1;
                    if (!iterator.hasNext())
                    {
                        break; /* Loop/switch isn't completed */
                    }
                    obj1 = (Future)iterator.next();
                    obj1 = ((com.google.android.libraries.performance.primes.battery.BatteryCapture.Snapshot)((Future) (obj1)).get()).toStatsRecord();
                    if (statsrecord == null)
                    {
                        break MISSING_BLOCK_LABEL_70;
                    }
                    batterymetricservice.log(statsrecord, ((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj1)));
                    statsrecord = ((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj1));
                    continue; /* Loop/switch isn't completed */
                    Exception exception1;
                    exception1;
                    obj1 = statsrecord;
_L3:
                    PrimesLog.log(6, "BatteryMetricService", exception1, "unpexpected failure", new Object[0]);
                    statsrecord = ((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj1));
                    if (true) goto _L2; else goto _L1
_L1:
                    batterymetricservice.toStorage(statsrecord);
                    return;
                    exception1;
                      goto _L3
                }

            .Lambda._cls5(List list)
            {
                arg$1 = BatteryMetricService.this;
                arg$2 = list;
            }
            }

            if (aobj.length != 0)
            {
                obj = String.format(Locale.US, "Logging captures: %d", aobj);
            }
            Log.println(4, "BatteryMetricService", ((String) (obj)));
        }
        return ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new .Lambda._cls5(arraylist));
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final com.google.android.libraries.performance.primes.battery.BatteryCapture.Snapshot captureBattery(int i, String s, boolean flag)
    {
        BatteryCapture batterycapture = batteryCapture;
        long l = batterycapture.systemClockElapsedRealtimeCapture.getTime();
        long l1 = batterycapture.systemCurrentTimeCapture.getTime();
        Object obj = (SystemHealthManager)batterycapture.systemHealthCapture.context.getSystemService("systemhealth");
        if (obj != null)
        {
            obj = ((SystemHealthManager) (obj)).takeMyUidSnapshot();
        } else
        {
            obj = null;
        }
        return new com.google.android.libraries.performance.primes.battery.BatteryCapture.Snapshot(batterycapture, Long.valueOf(l), Long.valueOf(l1), ((android.os.health.HealthStats) (obj)), Integer.valueOf(i), s, Boolean.valueOf(flag), batterycapture.metricExtensionProvider.getMetricExtension$5166KOBMC4NMOOBECSNL6T3ID5N6EEQ95566ORR7ECNN0SJFEHNIUTR9E9IMOPBJECNN0PBICPNN4RB1DPHMABRDDTH6IR355TN62RJF5T6MAT3ID5HKAU3KCLN76QBFDOTG____0());
    }

    final com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord fromStorage()
    {
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        StatsStorage statsstorage = storage;
        statsstorage;
        JVM INSTR monitorenter ;
        Object obj;
        obj = storage;
        BatterySnapshot batterysnapshot = new BatterySnapshot();
        if (!((StatsStorage) (obj)).storage.readProto("primes.battery.snapshot", batterysnapshot))
        {
            break MISSING_BLOCK_LABEL_135;
        }
        obj = new com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord(batterysnapshot.uidHealthProto, batterysnapshot.elapsedTime, batterysnapshot.currentTime, batterysnapshot.primesVersion, batterysnapshot.versionNameHash, batterysnapshot.sampleInfo, batterysnapshot.customEventName, batterysnapshot.isEventNameConstant, batterysnapshot.metricExtension);
_L2:
        return ((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj));
        obj;
        statsstorage;
        JVM INSTR monitorexit ;
        throw obj;
        obj = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    final void log(com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord statsrecord, com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord statsrecord1)
    {
        Object obj;
        boolean flag;
        boolean flag2 = true;
        obj = "log start: %s\nend: %s";
        Object aobj[] = new Object[2];
        aobj[0] = statsrecord;
        aobj[1] = statsrecord1;
        boolean flag1;
        if (Log.isLoggable("BatteryMetricService", 2))
        {
            if (aobj.length != 0)
            {
                obj = String.format(Locale.US, "log start: %s\nend: %s", aobj);
            }
            Log.println(2, "BatteryMetricService", ((String) (obj)));
        }
        obj = batteryCapture;
        if (statsrecord == null || statsrecord1 == null)
        {
            break MISSING_BLOCK_LABEL_401;
        }
        if (statsrecord.primesVersion != null && statsrecord1.primesVersion != null && statsrecord.primesVersion.longValue() == statsrecord1.primesVersion.longValue())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (statsrecord.versionNameHash == null || statsrecord1.versionNameHash == null)
        {
            if (statsrecord.versionNameHash == null && statsrecord1.versionNameHash == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        if (statsrecord.versionNameHash.longValue() == statsrecord1.versionNameHash.longValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_401;
        }
        if (statsrecord.elapsedTime == null || statsrecord.currentTime == null || statsrecord1.elapsedTime == null || statsrecord1.currentTime == null)
        {
            flag = false;
        } else
        {
label0:
            {
                long l1 = statsrecord1.elapsedTime.longValue();
                long l2 = statsrecord.elapsedTime.longValue();
                long l = statsrecord1.currentTime.longValue() - statsrecord.currentTime.longValue();
                if (l <= 0L)
                {
                    break label0;
                }
                l1 = Math.abs(l1 - l2 - l);
                if (l1 >= 25L && (double)l1 / (double)l > 3.4722222222222222E-05D)
                {
                    break label0;
                }
                flag = true;
            }
        }
_L1:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_401;
        }
        flag = flag2;
_L2:
        if (!flag)
        {
            statsrecord = "inconsistent stats";
            obj = ((Object) (new Object[0]));
            if (Log.isLoggable("BatteryCapture", 3))
            {
                if (obj.length != 0)
                {
                    statsrecord = String.format(Locale.US, "inconsistent stats", ((Object []) (obj)));
                }
                Log.println(3, "BatteryCapture", statsrecord);
            }
            statsrecord = null;
        } else
        {
            SystemHealthCapture systemhealthcapture = ((BatteryCapture) (obj)).systemHealthCapture;
            obj = statsrecord1.proto;
            UidHealthProto uidhealthproto1 = statsrecord.proto;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            HashingNameSanitizer hashingnamesanitizer;
            if (uidhealthproto1 != null)
            {
                UidHealthProto uidhealthproto = new UidHealthProto();
                uidhealthproto.realtimeBatteryMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).realtimeBatteryMs, uidhealthproto1.realtimeBatteryMs);
                uidhealthproto.realtimeScreenOffBatteryMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).realtimeScreenOffBatteryMs, uidhealthproto1.realtimeScreenOffBatteryMs);
                Object aobj1[] = ((UidHealthProto) (obj)).wakelocksFull;
                Object aobj2[] = uidhealthproto1.wakelocksFull;
                uidhealthproto.wakelocksFull = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).wakelocksPartial;
                aobj2 = uidhealthproto1.wakelocksPartial;
                uidhealthproto.wakelocksPartial = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).wakelocksWindow;
                aobj2 = uidhealthproto1.wakelocksWindow;
                uidhealthproto.wakelocksWindow = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).wakelocksDraw;
                aobj2 = uidhealthproto1.wakelocksDraw;
                uidhealthproto.wakelocksDraw = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).syncs;
                aobj2 = uidhealthproto1.syncs;
                uidhealthproto.syncs = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).jobs;
                aobj2 = uidhealthproto1.jobs;
                uidhealthproto.jobs = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                uidhealthproto.gpsSensor = HealthStatsProtos.subtract(((UidHealthProto) (obj)).gpsSensor, uidhealthproto1.gpsSensor);
                aobj1 = ((UidHealthProto) (obj)).sensors;
                aobj2 = uidhealthproto1.sensors;
                uidhealthproto.sensors = (logs.proto.wireless.performance.mobile.nano.Timer[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.TimerOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).statsProcesses;
                aobj2 = uidhealthproto1.statsProcesses;
                uidhealthproto.statsProcesses = (logs.proto.wireless.performance.mobile.nano.ProcessHealthProto[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.ProcessOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                aobj1 = ((UidHealthProto) (obj)).statsPackages;
                aobj2 = uidhealthproto1.statsPackages;
                uidhealthproto.statsPackages = (logs.proto.wireless.performance.mobile.nano.PackageHealthProto[])com.google.android.libraries.performance.primes.battery.HealthStatsProtos.PackageOps.INSTANCE.subtract(((MessageNano []) (aobj1)), ((MessageNano []) (aobj2)));
                uidhealthproto.wifiIdleMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiIdleMs, uidhealthproto1.wifiIdleMs);
                uidhealthproto.wifiRxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiRxMs, uidhealthproto1.wifiRxMs);
                uidhealthproto.wifiTxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiTxMs, uidhealthproto1.wifiTxMs);
                uidhealthproto.wifiPowerMams = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiPowerMams, uidhealthproto1.wifiPowerMams);
                uidhealthproto.bluetoothIdleMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothIdleMs, uidhealthproto1.bluetoothIdleMs);
                uidhealthproto.bluetoothRxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothRxMs, uidhealthproto1.bluetoothRxMs);
                uidhealthproto.bluetoothTxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothTxMs, uidhealthproto1.bluetoothTxMs);
                uidhealthproto.bluetoothPowerMams = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothPowerMams, uidhealthproto1.bluetoothPowerMams);
                uidhealthproto.mobileIdleMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileIdleMs, uidhealthproto1.mobileIdleMs);
                uidhealthproto.mobileRxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileRxMs, uidhealthproto1.mobileRxMs);
                uidhealthproto.mobileTxMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileTxMs, uidhealthproto1.mobileTxMs);
                uidhealthproto.mobilePowerMams = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobilePowerMams, uidhealthproto1.mobilePowerMams);
                uidhealthproto.wifiRunningMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiRunningMs, uidhealthproto1.wifiRunningMs);
                uidhealthproto.wifiFullLockMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiFullLockMs, uidhealthproto1.wifiFullLockMs);
                uidhealthproto.wifiScan = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiScan, uidhealthproto1.wifiScan);
                uidhealthproto.wifiMulticastMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiMulticastMs, uidhealthproto1.wifiMulticastMs);
                uidhealthproto.audio = HealthStatsProtos.subtract(((UidHealthProto) (obj)).audio, uidhealthproto1.audio);
                uidhealthproto.video = HealthStatsProtos.subtract(((UidHealthProto) (obj)).video, uidhealthproto1.video);
                uidhealthproto.flashlight = HealthStatsProtos.subtract(((UidHealthProto) (obj)).flashlight, uidhealthproto1.flashlight);
                uidhealthproto.camera = HealthStatsProtos.subtract(((UidHealthProto) (obj)).camera, uidhealthproto1.camera);
                uidhealthproto.foregroundActivity = HealthStatsProtos.subtract(((UidHealthProto) (obj)).foregroundActivity, uidhealthproto1.foregroundActivity);
                uidhealthproto.bluetoothScan = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothScan, uidhealthproto1.bluetoothScan);
                uidhealthproto.processStateTopMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateTopMs, uidhealthproto1.processStateTopMs);
                uidhealthproto.processStateForegroundServiceMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateForegroundServiceMs, uidhealthproto1.processStateForegroundServiceMs);
                uidhealthproto.processStateTopSleepingMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateTopSleepingMs, uidhealthproto1.processStateTopSleepingMs);
                uidhealthproto.processStateForegroundMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateForegroundMs, uidhealthproto1.processStateForegroundMs);
                uidhealthproto.processStateBackgroundMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateBackgroundMs, uidhealthproto1.processStateBackgroundMs);
                uidhealthproto.processStateCachedMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).processStateCachedMs, uidhealthproto1.processStateCachedMs);
                uidhealthproto.vibrator = HealthStatsProtos.subtract(((UidHealthProto) (obj)).vibrator, uidhealthproto1.vibrator);
                uidhealthproto.otherUserActivityCount = HealthStatsProtos.subtract(((UidHealthProto) (obj)).otherUserActivityCount, uidhealthproto1.otherUserActivityCount);
                uidhealthproto.buttonUserActivityCount = HealthStatsProtos.subtract(((UidHealthProto) (obj)).buttonUserActivityCount, uidhealthproto1.buttonUserActivityCount);
                uidhealthproto.touchUserActivityCount = HealthStatsProtos.subtract(((UidHealthProto) (obj)).touchUserActivityCount, uidhealthproto1.touchUserActivityCount);
                uidhealthproto.mobileRxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileRxBytes, uidhealthproto1.mobileRxBytes);
                uidhealthproto.mobileTxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileTxBytes, uidhealthproto1.mobileTxBytes);
                uidhealthproto.wifiRxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiRxBytes, uidhealthproto1.wifiRxBytes);
                uidhealthproto.wifiTxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiTxBytes, uidhealthproto1.wifiTxBytes);
                uidhealthproto.bluetoothRxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothRxBytes, uidhealthproto1.bluetoothRxBytes);
                uidhealthproto.bluetoothTxBytes = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothTxBytes, uidhealthproto1.bluetoothTxBytes);
                uidhealthproto.mobileRxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileRxPackets, uidhealthproto1.mobileRxPackets);
                uidhealthproto.mobileTxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileTxPackets, uidhealthproto1.mobileTxPackets);
                uidhealthproto.wifiRxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiRxPackets, uidhealthproto1.wifiRxPackets);
                uidhealthproto.wifiTxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).wifiTxPackets, uidhealthproto1.wifiTxPackets);
                uidhealthproto.bluetoothRxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothRxPackets, uidhealthproto1.bluetoothRxPackets);
                uidhealthproto.bluetoothTxPackets = HealthStatsProtos.subtract(((UidHealthProto) (obj)).bluetoothTxPackets, uidhealthproto1.bluetoothTxPackets);
                uidhealthproto.mobileRadioActive = HealthStatsProtos.subtract(((UidHealthProto) (obj)).mobileRadioActive, uidhealthproto1.mobileRadioActive);
                uidhealthproto.userCpuTimeMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).userCpuTimeMs, uidhealthproto1.userCpuTimeMs);
                uidhealthproto.systemCpuTimeMs = HealthStatsProtos.subtract(((UidHealthProto) (obj)).systemCpuTimeMs, uidhealthproto1.systemCpuTimeMs);
                uidhealthproto.cpuPowerMams = HealthStatsProtos.subtract(((UidHealthProto) (obj)).cpuPowerMams, uidhealthproto1.cpuPowerMams);
                obj = uidhealthproto;
            }
            hashingnamesanitizer = systemhealthcapture.hashingNameSanitizer;
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).wakelocksFull);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).wakelocksPartial);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).wakelocksWindow);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).wakelocksDraw);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).syncs);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).jobs);
            hashingnamesanitizer.sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(((UidHealthProto) (obj)).sensors);
            if (((UidHealthProto) (obj)).realtimeBatteryMs == null || ((UidHealthProto) (obj)).realtimeBatteryMs.longValue() <= 0L)
            {
                statsrecord = "invalid realtime";
                obj = ((Object) (new Object[0]));
                if (Log.isLoggable("BatteryCapture", 3))
                {
                    if (obj.length != 0)
                    {
                        statsrecord = String.format(Locale.US, "invalid realtime", ((Object []) (obj)));
                    }
                    Log.println(3, "BatteryCapture", statsrecord);
                }
                statsrecord = null;
            } else
            {
                BatteryStatsDiff batterystatsdiff = new BatteryStatsDiff();
                batterystatsdiff.durationMs = Long.valueOf(statsrecord1.elapsedTime.longValue() - statsrecord.elapsedTime.longValue());
                batterystatsdiff.startInfo = statsrecord.sampleInfo.intValue();
                if (statsrecord.isEventNameConstant.booleanValue())
                {
                    batterystatsdiff.startConstantEventName = statsrecord.customEventName;
                } else
                {
                    batterystatsdiff.startCustomEventName = statsrecord.customEventName;
                }
                batterystatsdiff.startMetricExtension = statsrecord.metricExtension;
                batterystatsdiff.endInfo = statsrecord1.sampleInfo.intValue();
                batterystatsdiff.elapedRealtimeMs = statsrecord1.elapsedTime;
                batterystatsdiff.uidHealthProtoDiff = ((UidHealthProto) (obj));
                obj = new BatteryUsageMetric();
                obj.batteryStatsDiff = batterystatsdiff;
                statsrecord = new SystemHealthMetric();
                statsrecord.batteryUsageMetric = ((BatteryUsageMetric) (obj));
            }
        }
        if (statsrecord != null)
        {
            recordSystemHealthMetric(statsrecord1.customEventName, statsrecord1.isEventNameConstant.booleanValue(), statsrecord, statsrecord1.metricExtension);
        }
        return;
        flag = false;
          goto _L1
        flag = false;
          goto _L2
    }

    public final void onAppToBackground(Activity activity)
    {
        if (!inForeground.getAndSet(false))
        {
            activity = "unexpected state onAppToBackground";
            Object aobj[] = new Object[0];
            if (Log.isLoggable("BatteryMetricService", 5))
            {
                if (aobj.length != 0)
                {
                    activity = String.format(Locale.US, "unexpected state onAppToBackground", aobj);
                }
                Log.println(5, "BatteryMetricService", activity);
            }
            return;
        }
        if (logDeferred)
        {
            captureForDeferredLogging(1, null, true);
            return;
        } else
        {
            class .Lambda._cls6
                implements Runnable
            {

                private final BatteryMetricService arg$1;
                private final int arg$2;
                private final String arg$3;
                private final boolean arg$4;

                public final void run()
                {
                    Object obj;
                    Object obj1;
                    int i;
                    boolean flag1;
                    obj = arg$1;
                    i = arg$2;
                    obj1 = arg$3;
                    flag1 = arg$4;
                    if (ThreadUtil.sMainThread == null)
                    {
                        ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
                    }
                    boolean flag;
                    if (Thread.currentThread() == ThreadUtil.sMainThread)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        throw new RuntimeException("Must be called on a background thread");
                    }
                    if (((AbstractMetricService) (obj)).shutdown)
                    {
                        break MISSING_BLOCK_LABEL_176;
                    }
                    StatsStorage statsstorage = ((BatteryMetricService) (obj)).storage;
                    statsstorage;
                    JVM INSTR monitorenter ;
                    com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord statsrecord;
                    obj1 = ((BatteryMetricService) (obj)).captureBattery(i, ((String) (obj1)), flag1).toStatsRecord();
                    statsrecord = ((BatteryMetricService) (obj)).fromStorage();
                    if (!((BatteryMetricService) (obj)).toStorage(((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj1)))) goto _L2; else goto _L1
_L1:
                    ((BatteryMetricService) (obj)).log(statsrecord, ((com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord) (obj1)));
_L4:
                    return;
_L2:
                    ((AbstractMetricService) (obj)).shutdownService();
                    obj = "Failure storing persistent snapshot and helper data";
                    Object aobj2[] = new Object[0];
                    if (!Log.isLoggable("BatteryMetricService", 5)) goto _L4; else goto _L3
_L3:
                    if (aobj2.length != 0)
                    {
                        break MISSING_BLOCK_LABEL_163;
                    }
_L5:
                    Log.println(5, "BatteryMetricService", ((String) (obj)));
                      goto _L4
                    obj;
                    statsstorage;
                    JVM INSTR monitorexit ;
                    throw obj;
                    obj = String.format(Locale.US, "Failure storing persistent snapshot and helper data", aobj2);
                      goto _L5
                    String s = "shutdown - skipping capture";
                    Object aobj1[] = new Object[0];
                    if (Log.isLoggable("BatteryMetricService", 3))
                    {
                        if (aobj1.length != 0)
                        {
                            s = String.format(Locale.US, "shutdown - skipping capture", aobj1);
                        }
                        Log.println(3, "BatteryMetricService", s);
                        return;
                    } else
                    {
                        return;
                    }
                }

            .Lambda._cls6(int i, String s, boolean flag)
            {
                arg$1 = BatteryMetricService.this;
                arg$2 = i;
                arg$3 = s;
                arg$4 = flag;
            }
            }

            ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new .Lambda._cls6(1, null, true));
            return;
        }
    }

    public final void onAppToForeground(Activity activity)
    {
        if (inForeground.getAndSet(true))
        {
            activity = "unexpected state onAppToForeground";
            Object aobj[] = new Object[0];
            if (Log.isLoggable("BatteryMetricService", 5))
            {
                if (aobj.length != 0)
                {
                    activity = String.format(Locale.US, "unexpected state onAppToForeground", aobj);
                }
                Log.println(5, "BatteryMetricService", activity);
            }
            return;
        }
        if (logDeferred)
        {
            captureForDeferredLogging(2, null, true);
            return;
        } else
        {
            ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new .Lambda._cls6(2, null, true));
            return;
        }
    }

    public final void onFirstActivityCreated()
    {
        if (!inForeground.get())
        {
            if (inForeground.getAndSet(true))
            {
                obj = "unexpected state onAppToForeground";
                Object aobj[] = new Object[0];
                if (Log.isLoggable("BatteryMetricService", 5))
                {
                    if (aobj.length != 0)
                    {
                        obj = String.format(Locale.US, "unexpected state onAppToForeground", aobj);
                    }
                    Log.println(5, "BatteryMetricService", ((String) (obj)));
                }
            } else
            if (logDeferred)
            {
                captureForDeferredLogging(2, null, true);
            } else
            {
                ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new .Lambda._cls6(2, null, true));
            }
        }
        synchronized (monitorMutex)
        {
            if (!monitoring)
            {
                AppLifecycleMonitor.getInstance(super.application).register(this);
                monitoring = true;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onPrimesInitialize()
    {
    }

    final void shutdownService()
    {
        synchronized (monitorMutex)
        {
            if (monitoring)
            {
                AppLifecycleMonitor.getInstance(super.application).unregister(this);
                monitoring = false;
            }
        }
        synchronized (storage)
        {
            storage.storage.sharedPreferences.edit().remove("primes.battery.snapshot").commit();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    final boolean toStorage(com.google.android.libraries.performance.primes.battery.StatsStorage.StatsRecord statsrecord)
    {
        boolean flag = true;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        if (Thread.currentThread() != ThreadUtil.sMainThread)
        {
            flag = false;
        }
        if (flag)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        StatsStorage statsstorage = storage;
        statsstorage;
        JVM INSTR monitorenter ;
        BatterySnapshot batterysnapshot;
        StatsStorage statsstorage1 = storage;
        batterysnapshot = new BatterySnapshot();
        batterysnapshot.uidHealthProto = statsrecord.proto;
        batterysnapshot.elapsedTime = statsrecord.elapsedTime;
        batterysnapshot.currentTime = statsrecord.currentTime;
        batterysnapshot.primesVersion = statsrecord.primesVersion;
        batterysnapshot.versionNameHash = statsrecord.versionNameHash;
        batterysnapshot.sampleInfo = statsrecord.sampleInfo;
        batterysnapshot.customEventName = statsrecord.customEventName;
        batterysnapshot.isEventNameConstant = statsrecord.isEventNameConstant;
        batterysnapshot.metricExtension = statsrecord.metricExtension;
        statsrecord = statsstorage1.storage;
        if (batterysnapshot != null)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        throw new NullPointerException();
        statsrecord;
        statsstorage;
        JVM INSTR monitorexit ;
        throw statsrecord;
        byte abyte0[];
        byte abyte1[];
        MessageNano messagenano = (MessageNano)batterysnapshot;
        int i = messagenano.computeSerializedSize();
        messagenano.cachedSize = i;
        abyte0 = new byte[i];
        MessageNano.toByteArray(messagenano, abyte0, 0, abyte0.length);
        abyte1 = new byte[abyte0.length + 1];
        abyte1[0] = 1;
        boolean flag1;
        System.arraycopy(abyte0, 0, abyte1, 1, abyte0.length);
        flag1 = ((PersistentStorage) (statsrecord)).sharedPreferences.edit().putString("primes.battery.snapshot", Base64.encodeToString(abyte1, 0)).commit();
        statsstorage;
        JVM INSTR monitorexit ;
        return flag1;
    }
}
