// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Debug;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.backgroundjobs.PrimesJobScheduler;
import com.google.android.libraries.performance.primes.hprof.HprofSerializer;
import com.google.android.libraries.performance.primes.miniheapdump.MiniHeapDumpMemorySampler;
import com.google.android.libraries.performance.primes.miniheapdump.SerializedMiniHeapDumpFile;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.proto.primes.persistent.nano.PersistentMemorySamples;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import logs.proto.wireless.performance.mobile.nano.HeapDumpContext;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDumpCalibrationStatus;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDumpEvent;
import logs.proto.wireless.performance.mobile.nano.PrimesStats;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, Supplier, MetricStamper, AppLifecycleMonitor, 
//            PrimesLog, PrimesHprofFile, HeapDumpProcessor

final class MiniHeapDumpMetricService extends AbstractMetricService
{

    private static final long MIN_HEAP_DUMP_INTERVAL_MS;
    private static volatile MiniHeapDumpMetricService service;
    public final AppLifecycleMonitor appLifecycleMonitor;
    public final Supplier executorServiceSupplier;
    public volatile ScheduledFuture futureMemoryCollectionTask;
    private final ReentrantLock heapDumpLock = new ReentrantLock();
    private final HprofSerializer hprofSerializer;
    private final AtomicLong lastSent = new AtomicLong();
    public final AppLifecycleListener.OnAppToBackground logTotalPssSampleCount = new _cls2();
    private final double memoryUsagePercentileThreshold;
    private final Supplier metricStamperSupplier;
    public final MiniHeapDumpMemorySampler miniHeapDumpSampler;
    public final AppLifecycleListener.OnAppToForeground onAppToForeground = new _cls3();
    public final SharedPreferences preferences;
    public final AppLifecycleListener.OnAppToBackground takeAndLogMemorySample = new _cls1();

    private MiniHeapDumpMetricService(MetricTransmitter metrictransmitter, Application application, AppLifecycleMonitor applifecyclemonitor, double d, MiniHeapDumpMemorySampler miniheapdumpmemorysampler, HprofSerializer hprofserializer, 
            Supplier supplier, Supplier supplier1, SharedPreferences sharedpreferences)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.BACKGROUND_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        appLifecycleMonitor = applifecyclemonitor;
        memoryUsagePercentileThreshold = d;
        if (miniheapdumpmemorysampler == null)
        {
            throw new NullPointerException();
        }
        miniHeapDumpSampler = (MiniHeapDumpMemorySampler)miniheapdumpmemorysampler;
        if (hprofserializer == null)
        {
            throw new NullPointerException();
        }
        hprofSerializer = (HprofSerializer)hprofserializer;
        if (supplier == null)
        {
            throw new NullPointerException();
        }
        metricStamperSupplier = (Supplier)supplier;
        if (supplier1 == null)
        {
            throw new NullPointerException();
        } else
        {
            executorServiceSupplier = (Supplier)supplier1;
            preferences = sharedpreferences;
            return;
        }
    }

    static MiniHeapDumpMetricService createService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, SharedPreferences sharedpreferences, double d)
    {
        com/google/android/libraries/performance/primes/MiniHeapDumpMetricService;
        JVM INSTR monitorenter ;
        Object obj = (JobScheduler)application.getSystemService("jobscheduler");
        if (android.os.Build.VERSION.SDK_INT >= 24) goto _L2; else goto _L1
_L1:
        obj = ((JobScheduler) (obj)).getAllPendingJobs().iterator();
_L6:
        if (!((Iterator) (obj)).hasNext()) goto _L4; else goto _L3
_L3:
        int i = ((JobInfo)((Iterator) (obj)).next()).getId();
        if (i != 0xafa8024) goto _L6; else goto _L5
_L5:
        i = 1;
_L14:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        obj = SerializedMiniHeapDumpFile.getSerializedObjectGraphFile(application);
        if (((File) (obj)).exists())
        {
            ((File) (obj)).delete();
        }
_L11:
        if (service != null) goto _L8; else goto _L7
_L7:
        obj = ((MetricStamper)supplier.get()).versionName;
        if (obj == null) goto _L10; else goto _L9
_L9:
        i = ((String) (obj)).hashCode();
_L12:
        PersistentMemorySamples persistentmemorysamples;
        AppLifecycleMonitor applifecyclemonitor;
        PersistentStorage persistentstorage;
        applifecyclemonitor = AppLifecycleMonitor.getInstance(application);
        persistentstorage = new PersistentStorage(sharedpreferences);
        persistentmemorysamples = new PersistentMemorySamples();
        persistentstorage.readProto("primes.miniheapdump.memorySamples", persistentmemorysamples);
        obj = persistentmemorysamples;
        if (persistentmemorysamples.versionNameHash == null)
        {
            break MISSING_BLOCK_LABEL_200;
        }
        obj = persistentmemorysamples;
        if (persistentmemorysamples.versionNameHash.intValue() != i)
        {
            obj = new PersistentMemorySamples();
        }
        service = new MiniHeapDumpMetricService(metrictransmitter, application, applifecyclemonitor, 0.94999999999999996D, new MiniHeapDumpMemorySampler(persistentstorage, ((PersistentMemorySamples) (obj)), 1.2D, i, new Random()), new HprofSerializer(), supplier, supplier1, sharedpreferences);
_L8:
        metrictransmitter = service;
        com/google/android/libraries/performance/primes/MiniHeapDumpMetricService;
        JVM INSTR monitorexit ;
        return metrictransmitter;
_L2:
        SecurityException securityexception;
        if (((JobScheduler) (obj)).getPendingJob(0xafa8024) != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        continue; /* Loop/switch isn't completed */
        securityexception;
        PrimesLog.log(6, "SerializedMhdFile", securityexception, "Error deleting file", new Object[0]);
          goto _L11
        metrictransmitter;
        throw metrictransmitter;
_L10:
        i = 0;
          goto _L12
_L4:
        i = 0;
        if (true) goto _L14; else goto _L13
_L13:
    }

    static boolean isFileUploadEnabled(Application application)
    {
        return PrimesJobScheduler.isJobEnabled(application, "com.google.android.libraries.performance.primes.backgroundjobs.logger.LoggerJob");
    }

    private final void mergeAndRecordHeapDumpEvents(PrimesHeapDumpEvent primesheapdumpevent, List list)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            Object obj = (PrimesHeapDumpEvent)list.next();
            try
            {
                int i = primesheapdumpevent.computeSerializedSize();
                primesheapdumpevent.cachedSize = i;
                byte abyte0[] = new byte[i];
                MessageNano.toByteArray(primesheapdumpevent, abyte0, 0, abyte0.length);
                PrimesHeapDumpEvent primesheapdumpevent1 = (PrimesHeapDumpEvent)MessageNano.mergeFrom(new PrimesHeapDumpEvent(), abyte0, 0, abyte0.length);
                i = ((MessageNano) (obj)).computeSerializedSize();
                obj.cachedSize = i;
                byte abyte1[] = new byte[i];
                MessageNano.toByteArray(((MessageNano) (obj)), abyte1, 0, abyte1.length);
                MessageNano.mergeFrom(primesheapdumpevent1, abyte1, 0, abyte1.length);
                obj = new SystemHealthMetric();
                obj.primesStats = new PrimesStats();
                ((SystemHealthMetric) (obj)).primesStats.primesDebugMessage = new logs.proto.wireless.performance.mobile.nano.PrimesStats.PrimesDebugMessage();
                ((SystemHealthMetric) (obj)).primesStats.primesDebugMessage.primesHeapDumpEvent = primesheapdumpevent1;
                recordSystemHealthMetric(null, true, ((SystemHealthMetric) (obj)), null);
            }
            catch (InvalidProtocolBufferNanoException invalidprotocolbuffernanoexception)
            {
                PrimesLog.log(3, "MiniHeapDumpMetric", invalidprotocolbuffernanoexception, "Failed to merge protos: ", new Object[0]);
            }
        }

    }

    final void addMemorySample(int i)
    {
        miniHeapDumpSampler.addSample(i);
        if (super.shutdown) goto _L2; else goto _L1
_L1:
        if (!PrimesHprofFile.getMiniHeapDumpHprofFile(super.application).exists() && !SerializedMiniHeapDumpFile.getSerializedObjectGraphFile(super.application).exists()) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L10:
        if (!flag) goto _L6; else goto _L5
_L5:
        PrimesHeapDumpEvent primesheapdumpevent;
        HeapDumpContext heapdumpcontext;
        Object obj = miniHeapDumpSampler;
        double d = memoryUsagePercentileThreshold;
        long l;
        if (((MiniHeapDumpMemorySampler) (obj)).canComputePercentile())
        {
            if (((MiniHeapDumpMemorySampler) (obj)).calculateQuantile(i) >= d)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        heapdumpcontext = new HeapDumpContext();
        heapdumpcontext.triggerType = 1;
        heapdumpcontext.totalPssKb = Integer.valueOf(i);
        obj = miniHeapDumpSampler.samples;
        primesheapdumpevent = new PrimesHeapDumpEvent();
        primesheapdumpevent.totalPssKbSamples = new int[((List) (obj)).size()];
        for (i = 0; i < ((List) (obj)).size(); i++)
        {
            primesheapdumpevent.totalPssKbSamples[i] = ((Integer)((List) (obj)).get(i)).intValue();
        }

          goto _L8
_L4:
        l = lastSent.get();
        if (l != 0L && l + MIN_HEAP_DUMP_INTERVAL_MS > SystemClock.elapsedRealtime()) goto _L2; else goto _L9
_L9:
        flag = true;
          goto _L10
_L2:
        flag = false;
          goto _L10
_L8:
        if (!heapDumpLock.tryLock()) goto _L6; else goto _L11
_L11:
        lastSent.set(SystemClock.elapsedRealtime());
        Debug.dumpHprofData(PrimesHprofFile.getMiniHeapDumpHprofFile(super.application).getAbsolutePath());
        Object obj1 = "Hprof data dumped";
        Object obj2 = ((Object) (new Object[0]));
        if (!Log.isLoggable("MiniHeapDumpMetric", 3)) goto _L13; else goto _L12
_L12:
        if (obj2.length != 0) goto _L15; else goto _L14
_L14:
        Log.println(3, "MiniHeapDumpMetric", ((String) (obj1)));
_L13:
        Object obj3;
        File file1;
        HeapDumpProcessor heapdumpprocessor;
        file1 = PrimesHprofFile.getMiniHeapDumpHprofFile(super.application);
        obj3 = SerializedMiniHeapDumpFile.getSerializedObjectGraphFile(super.application);
        heapdumpprocessor = new HeapDumpProcessor(hprofSerializer, (MetricStamper)metricStamperSupplier.get());
        if (file1.exists()) goto _L17; else goto _L16
_L16:
        obj1 = Collections.emptyList();
_L28:
        mergeAndRecordHeapDumpEvents(primesheapdumpevent, ((List) (obj1)));
        if (((File) (obj3)).exists()) goto _L19; else goto _L18
_L18:
        obj1 = "Failed to serialize to file.";
        obj2 = ((Object) (new Object[0]));
        if (!Log.isLoggable("MiniHeapDumpMetric", 3)) goto _L19; else goto _L20
_L20:
        if (obj2.length != 0) goto _L22; else goto _L21
_L21:
        Log.println(3, "MiniHeapDumpMetric", ((String) (obj1)));
_L19:
        obj1 = "Scheduling heap dump upload";
        obj2 = ((Object) (new Object[0]));
        if (!Log.isLoggable("MiniHeapDumpMetric", 3)) goto _L24; else goto _L23
_L23:
        if (obj2.length != 0) goto _L26; else goto _L25
_L25:
        Log.println(3, "MiniHeapDumpMetric", ((String) (obj1)));
_L24:
        obj1 = super.application;
        obj2 = ((File) (obj3)).getAbsolutePath();
        if (PrimesJobScheduler.isJobEnabled(((Application) (obj1)), "com.google.android.libraries.performance.primes.backgroundjobs.logger.LoggerJob"))
        {
            obj3 = new PersistableBundle();
            ((PersistableBundle) (obj3)).putString("file_name", ((String) (obj2)));
            ((PersistableBundle) (obj3)).putString("log_source", "PRIMES_INTERNAL_ANDROID_PRIMES");
            obj2 = (new android.app.job.JobInfo.Builder(0xafa8024, new ComponentName(((android.content.Context) (obj1)), "com.google.android.libraries.performance.primes.backgroundjobs.logger.LoggerJob"))).setRequiredNetworkType(2).setRequiresCharging(true).setRequiresDeviceIdle(true).setExtras(((PersistableBundle) (obj3))).build();
            ((JobScheduler)((Application) (obj1)).getSystemService("jobscheduler")).schedule(((JobInfo) (obj2)));
        }
        obj1 = PrimesHprofFile.getMiniHeapDumpHprofFile(super.application);
        if (((File) (obj1)).exists())
        {
            ((File) (obj1)).delete();
        }
        heapDumpLock.unlock();
_L6:
        return;
_L15:
        obj1 = String.format(Locale.US, "Hprof data dumped", ((Object []) (obj2)));
          goto _L14
_L17:
        PrimesHeapDumpEvent primesheapdumpevent1;
        obj2 = new ArrayList(2);
        primesheapdumpevent1 = heapdumpprocessor.executeSerializer(new HeapDumpProcessor._cls1(heapdumpprocessor, file1), heapdumpcontext, ((File) (obj3)));
        ((List) (obj2)).add(primesheapdumpevent1);
        obj1 = obj2;
        if (primesheapdumpevent1.error != 3) goto _L28; else goto _L27
_L27:
        ((List) (obj2)).add(heapdumpprocessor.executeSerializer(new HeapDumpProcessor._cls2(heapdumpprocessor, file1), heapdumpcontext, ((File) (obj3))));
        obj1 = obj2;
          goto _L28
        obj1;
        PrimesLog.log(3, "MiniHeapDumpMetric", ((Throwable) (obj1)), "Failed to dump hprof data", new Object[0]);
        obj1 = PrimesHprofFile.getMiniHeapDumpHprofFile(super.application);
        if (((File) (obj1)).exists())
        {
            ((File) (obj1)).delete();
        }
        heapDumpLock.unlock();
        return;
_L22:
        obj1 = String.format(Locale.US, "Failed to serialize to file.", ((Object []) (obj2)));
          goto _L21
_L26:
        obj1 = String.format(Locale.US, "Scheduling heap dump upload", ((Object []) (obj2)));
          goto _L25
        Exception exception;
        exception;
        File file = PrimesHprofFile.getMiniHeapDumpHprofFile(super.application);
        if (file.exists())
        {
            file.delete();
        }
        heapDumpLock.unlock();
        throw exception;
          goto _L14
    }

    final void recordStatus(PrimesHeapDumpCalibrationStatus primesheapdumpcalibrationstatus)
    {
        SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
        systemhealthmetric.primesStats = new PrimesStats();
        systemhealthmetric.primesStats.primesDebugMessage = new logs.proto.wireless.performance.mobile.nano.PrimesStats.PrimesDebugMessage();
        systemhealthmetric.primesStats.primesDebugMessage.primesHeapDumpCalibrationStatus = primesheapdumpcalibrationstatus;
        recordSystemHealthMetric(null, true, systemhealthmetric, null);
    }

    final void shutdownService()
    {
        if (futureMemoryCollectionTask != null)
        {
            futureMemoryCollectionTask.cancel(true);
            futureMemoryCollectionTask = null;
        }
        appLifecycleMonitor.unregister(takeAndLogMemorySample);
        appLifecycleMonitor.unregister(onAppToForeground);
        File file = PrimesHprofFile.getMiniHeapDumpHprofFile(super.application);
        if (file.exists())
        {
            file.delete();
        }
    }

    static 
    {
        MIN_HEAP_DUMP_INTERVAL_MS = TimeUnit.DAYS.toMillis(1L);
    }

    private class _cls1
        implements AppLifecycleListener.OnAppToBackground
    {

        public final MiniHeapDumpMetricService this$0;

        public final void onAppToBackground(Activity activity)
        {
            activity = MiniHeapDumpMetricService.this;
            if (((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask != null)
            {
                ((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask.cancel(true);
                activity.futureMemoryCollectionTask = null;
            }
            class _cls1
                implements Runnable
            {

                private final _cls1 this$1;

                public final void run()
                {
                    int i = ProcessStats.getActivityManager(application).getProcessMemoryInfo(new int[] {
                        Process.myPid()
                    })[0].getTotalPss();
                    Object obj = (new StringBuilder(36)).append("Background total pss kb: ").append(i).toString();
                    Object obj1 = ((Object) (new Object[0]));
                    if (Log.isLoggable("MiniHeapDumpMetric", 3))
                    {
                        if (obj1.length != 0)
                        {
                            obj = String.format(Locale.US, ((String) (obj)), ((Object []) (obj1)));
                        }
                        Log.println(3, "MiniHeapDumpMetric", ((String) (obj)));
                    }
                    addMemorySample(i);
                    obj = _fld0;
                    if (((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.canComputePercentile())
                    {
                        ((MiniHeapDumpMetricService) (obj)).preferences.edit().putBoolean("primes.miniheapdump.isCalibrated", true).apply();
                        obj1 = new PrimesHeapDumpCalibrationStatus();
                        obj1.newSamplePercentile = Float.valueOf((float)((MiniHeapDumpMetricService) (obj)).miniHeapDumpSampler.calculateQuantile(i));
                        ((MiniHeapDumpMetricService) (obj)).recordStatus(((PrimesHeapDumpCalibrationStatus) (obj1)));
                    }
                }

                _cls1()
                {
                    this$1 = _cls1.this;
                    super();
                }
            }

            futureMemoryCollectionTask = ((ScheduledExecutorService)executorServiceSupplier.get()).schedule(new _cls1(), 10L, TimeUnit.SECONDS);
        }

        _cls1()
        {
            this$0 = MiniHeapDumpMetricService.this;
            super();
        }
    }


    private class _cls2
        implements AppLifecycleListener.OnAppToBackground
    {

        public final MiniHeapDumpMetricService this$0;

        public final void onAppToBackground(Activity activity)
        {
            class _cls1
                implements Runnable
            {

                private final _cls2 this$1;

                public final void run()
                {
                    appLifecycleMonitor.unregister(logTotalPssSampleCount);
                    if (preferences.getBoolean("primes.miniheapdump.isCalibrated", false))
                    {
                        return;
                    }
                    Object obj = "Logging calibration status";
                    Object aobj[] = new Object[0];
                    if (Log.isLoggable("MiniHeapDumpMetric", 3))
                    {
                        if (aobj.length != 0)
                        {
                            obj = String.format(Locale.US, "Logging calibration status", aobj);
                        }
                        Log.println(3, "MiniHeapDumpMetric", ((String) (obj)));
                    }
                    obj = new PrimesHeapDumpCalibrationStatus();
                    obj.currentSampleCount = Integer.valueOf(miniHeapDumpSampler.samples.size());
                    recordStatus(((PrimesHeapDumpCalibrationStatus) (obj)));
                }

                _cls1()
                {
                    this$1 = _cls2.this;
                    super();
                }
            }

            ((ScheduledExecutorService)executorServiceSupplier.get()).submit(new _cls1());
        }

        _cls2()
        {
            this$0 = MiniHeapDumpMetricService.this;
            super();
        }
    }


    private class _cls3
        implements AppLifecycleListener.OnAppToForeground
    {

        private final MiniHeapDumpMetricService this$0;

        public final void onAppToForeground(Activity activity)
        {
            activity = MiniHeapDumpMetricService.this;
            if (((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask != null)
            {
                ((MiniHeapDumpMetricService) (activity)).futureMemoryCollectionTask.cancel(true);
                activity.futureMemoryCollectionTask = null;
            }
        }

        _cls3()
        {
            this$0 = MiniHeapDumpMetricService.this;
            super();
        }
    }

}
