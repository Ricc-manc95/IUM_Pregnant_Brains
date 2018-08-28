// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.content.SharedPreferences;
import com.google.android.libraries.performance.primes.battery.BatteryCapture;
import com.google.android.libraries.performance.primes.battery.SystemHealthCapture;
import com.google.android.libraries.performance.primes.jank.FrameTimeHistogramFactory;
import com.google.android.libraries.performance.primes.leak.LeakWatcher;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            BatteryMetricService, PrimesConfigurations, PrimesBatteryConfigurations, Shutdown, 
//            ShutdownListener, CpuMetricService, PrimesCpuConfigurations, CrashMetricService, 
//            PrimesFlags, PrimesCrashConfigurations, FrameMetricService, PrimesJankConfigurations, 
//            MagicEyeLogService, MemoryLeakMetricService, AppLifecycleMonitor, PrimesMemoryLeakConfigurations, 
//            MemoryMetricService, PrimesMemoryConfigurations, PrimesForPrimesTransmitterWrapper, MiniHeapDumpMetricService, 
//            PrimesTraceConfigurations, TimerMetricService, TimerMetricServiceWithTracing, PrimesTimerConfigurations, 
//            TraceMetricService, PrimesTikTokTraceConfigurations, Supplier

final class LazyMetricServices
{

    public final Application application;
    private volatile BatteryMetricService batteryMetricServiceInstance;
    public final PrimesConfigurations configs;
    private volatile CpuMetricService cpuMetricServiceInstance;
    private volatile CrashMetricService crashMetricServiceInstance;
    public final Supplier executorServiceSupplier;
    private volatile FrameMetricService frameMetricServiceInstance;
    private volatile MagicEyeLogService magicEyeLogServiceInstance;
    private volatile MemoryLeakMetricService memoryLeakMetricServiceInstance;
    private volatile MemoryMetricService memoryMetricServiceInstance;
    public final Supplier metricStamperSupplier;
    private volatile MetricTransmitter metricTransmitterInstance;
    private volatile MiniHeapDumpMetricService miniHeapDumpMetricServiceInstance;
    public final PrimesFlags primesFlags;
    public final SharedPreferences sharedPreferences;
    public final Shutdown shutdown;
    private volatile TimerMetricService timerMetricServiceInstance;
    private volatile TraceMetricService traceMetricServiceInstance;

    LazyMetricServices(final Application application, Supplier supplier, Supplier supplier1, final PrimesConfigurations configs, PrimesFlags primesflags, SharedPreferences sharedpreferences, Shutdown shutdown1)
    {
        this.application = application;
        executorServiceSupplier = supplier;
        new Supplier.Lazy(supplier1);
        this.configs = configs;
        primesFlags = primesflags;
        sharedPreferences = sharedpreferences;
        shutdown = shutdown1;
        metricStamperSupplier = new Supplier.Lazy(new _cls1());
    }

    final BatteryMetricService batteryMetricService()
    {
        if (batteryMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/BatteryMetricService;
        JVM INSTR monitorenter ;
        if (batteryMetricServiceInstance == null)
        {
            Object obj = metricTransmitter();
            Application application1 = application;
            Supplier supplier = metricStamperSupplier;
            Supplier supplier1 = executorServiceSupplier;
            SharedPreferences sharedpreferences = sharedPreferences;
            PrimesBatteryConfigurations primesbatteryconfigurations = configs.batteryConfigurations();
            obj = new BatteryMetricService(((MetricTransmitter) (obj)), application1, supplier, supplier1, sharedpreferences, new BatteryCapture(supplier, new SystemHealthCapture(application1), BatteryMetricService..Lambda._cls0.$instance, BatteryMetricService..Lambda._cls1.$instance, primesbatteryconfigurations.metricExtensionProvider), primesbatteryconfigurations.deferredLogging);
            if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
            {
                ((ShutdownListener) (obj)).onShutdown();
            }
            batteryMetricServiceInstance = (BatteryMetricService)obj;
        }
        com/google/android/libraries/performance/primes/BatteryMetricService;
        JVM INSTR monitorexit ;
_L2:
        return batteryMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/BatteryMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final CpuMetricService cpuMetricService()
    {
        if (cpuMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/CpuMetricService;
        JVM INSTR monitorenter ;
        if (cpuMetricServiceInstance == null)
        {
            Object obj = metricTransmitter();
            Application application1 = application;
            Supplier supplier = metricStamperSupplier;
            Supplier supplier1 = executorServiceSupplier;
            PrimesCpuConfigurations primescpuconfigurations = configs.cpuConfigurations();
            obj = new CpuMetricService(((MetricTransmitter) (obj)), application1, supplier, supplier1, primescpuconfigurations.timeBetweenSamples, primescpuconfigurations.initialDelay, primescpuconfigurations.numSamples);
            if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
            {
                ((ShutdownListener) (obj)).onShutdown();
            }
            cpuMetricServiceInstance = (CpuMetricService)obj;
        }
        com/google/android/libraries/performance/primes/CpuMetricService;
        JVM INSTR monitorexit ;
_L2:
        return cpuMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/CpuMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final CrashMetricService crashMetricService()
    {
        if (crashMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/CrashMetricService;
        JVM INSTR monitorenter ;
        if (crashMetricServiceInstance != null)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        Object obj;
        Application application1;
        Supplier supplier;
        Supplier supplier1;
        PrimesCrashConfigurations primescrashconfigurations;
        boolean flag;
        boolean flag1;
        if (primesFlags.deferredStartupLoggingEnabled && ProcessStats.isMyProcessInForeground(application))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = metricTransmitter();
        application1 = application;
        supplier = metricStamperSupplier;
        supplier1 = executorServiceSupplier;
        primescrashconfigurations = configs.crashConfigurations();
        flag1 = primesFlags.persistCrashStatsEnabled;
        obj = new CrashMetricService(((MetricTransmitter) (obj)), primescrashconfigurations.metricExtensionProvider, primescrashconfigurations.stackTraceTransmitter, primescrashconfigurations.sendStackTraces, supplier, supplier1, application1, primescrashconfigurations.startupSamplePercentage, flag, flag1);
        if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
        {
            ((ShutdownListener) (obj)).onShutdown();
        }
        crashMetricServiceInstance = (CrashMetricService)obj;
        com/google/android/libraries/performance/primes/CrashMetricService;
        JVM INSTR monitorexit ;
_L2:
        return crashMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/CrashMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final FrameMetricService frameMetricService()
    {
        if (frameMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/FrameMetricService;
        JVM INSTR monitorenter ;
        Object obj;
        MetricTransmitter metrictransmitter;
        Application application1;
        Supplier supplier;
        Supplier supplier1;
        PrimesJankConfigurations primesjankconfigurations;
        if (frameMetricServiceInstance != null)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        obj = new FrameTimeHistogramFactory();
        metrictransmitter = metricTransmitter();
        application1 = application;
        supplier = metricStamperSupplier;
        supplier1 = executorServiceSupplier;
        primesjankconfigurations = configs.jankConfigurations();
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        throw new IllegalStateException();
        obj;
        com/google/android/libraries/performance/primes/FrameMetricService;
        JVM INSTR monitorexit ;
        throw obj;
        obj = new FrameMetricService(metrictransmitter, application1, supplier, supplier1, primesjankconfigurations.monitorActivities, primesjankconfigurations.sampleRatePerSecond, ((com.google.android.libraries.performance.primes.jank.FrameTimeMeasurementFactory) (obj)), primesjankconfigurations.metricExtensionProvider);
        if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
        {
            ((ShutdownListener) (obj)).onShutdown();
        }
        frameMetricServiceInstance = (FrameMetricService)obj;
        com/google/android/libraries/performance/primes/FrameMetricService;
        JVM INSTR monitorexit ;
_L2:
        return frameMetricServiceInstance;
    }

    final MagicEyeLogService magicEyeLogService()
    {
        if (magicEyeLogServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/MagicEyeLogService;
        JVM INSTR monitorenter ;
        if (magicEyeLogServiceInstance == null)
        {
            MagicEyeLogService magiceyelogservice = new MagicEyeLogService(metricTransmitter(), application, metricStamperSupplier, executorServiceSupplier);
            if (!shutdown.registerShutdownListener(magiceyelogservice))
            {
                magiceyelogservice.onShutdown();
            }
            magicEyeLogServiceInstance = (MagicEyeLogService)magiceyelogservice;
        }
        com/google/android/libraries/performance/primes/MagicEyeLogService;
        JVM INSTR monitorexit ;
_L2:
        return magicEyeLogServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/MagicEyeLogService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final MemoryLeakMetricService memoryLeakMetricService()
    {
        if (memoryLeakMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/MemoryLeakMetricService;
        JVM INSTR monitorenter ;
        if (memoryLeakMetricServiceInstance == null)
        {
            Object obj = metricTransmitter();
            Application application1 = application;
            boolean flag = primesFlags.leakDetectionV2Enabled;
            Supplier supplier = metricStamperSupplier;
            Supplier supplier1 = executorServiceSupplier;
            PrimesMemoryLeakConfigurations primesmemoryleakconfigurations = configs.memoryLeakConfigurations();
            AppLifecycleMonitor applifecyclemonitor = AppLifecycleMonitor.getInstance(application);
            obj = new MemoryLeakMetricService(application1, flag, primesmemoryleakconfigurations.heapDumpEnabled, applifecyclemonitor, supplier, supplier1, new LeakWatcher(), ((MetricTransmitter) (obj)));
            if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
            {
                ((ShutdownListener) (obj)).onShutdown();
            }
            memoryLeakMetricServiceInstance = (MemoryLeakMetricService)obj;
        }
        com/google/android/libraries/performance/primes/MemoryLeakMetricService;
        JVM INSTR monitorexit ;
_L2:
        return memoryLeakMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/MemoryLeakMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final MemoryMetricService memoryMetricService()
    {
        if (memoryMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/MemoryMetricService;
        JVM INSTR monitorenter ;
        if (memoryMetricServiceInstance == null)
        {
            Object obj = metricTransmitter();
            Application application1 = application;
            Supplier supplier = metricStamperSupplier;
            Supplier supplier1 = executorServiceSupplier;
            PrimesMemoryConfigurations primesmemoryconfigurations = configs.memoryConfigurations();
            boolean flag = primesFlags.memorySummaryDisabled;
            obj = new MemoryMetricService(new MemoryMetricService._cls1(), ((MetricTransmitter) (obj)), application1, supplier, supplier1, primesmemoryconfigurations.sampleRatePerSecond, primesmemoryconfigurations.recordMetricPerProcess, primesmemoryconfigurations.metricExtensionProvider, primesmemoryconfigurations.forceGcBeforeRecordMemory, flag);
            if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
            {
                ((ShutdownListener) (obj)).onShutdown();
            }
            memoryMetricServiceInstance = (MemoryMetricService)obj;
        }
        com/google/android/libraries/performance/primes/MemoryMetricService;
        JVM INSTR monitorexit ;
_L2:
        return memoryMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/MemoryMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final MetricTransmitter metricTransmitter()
    {
        if (metricTransmitterInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/transmitter/MetricTransmitter;
        JVM INSTR monitorenter ;
        if (metricTransmitterInstance != null) goto _L4; else goto _L3
_L3:
        Object obj;
        if (!primesFlags.primesForPrimesEnabled)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        obj = configs;
        obj.getClass();
        class .Lambda._cls0
            implements Supplier
        {

            private final PrimesConfigurations arg$1;

            public final Object get()
            {
                return arg$1.metricTransmitter();
            }

            .Lambda._cls0(PrimesConfigurations primesconfigurations)
            {
                arg$1 = primesconfigurations;
            }
        }

        obj = new PrimesForPrimesTransmitterWrapper(new .Lambda._cls0(((PrimesConfigurations) (obj))));
_L5:
        metricTransmitterInstance = ((MetricTransmitter) (obj));
_L4:
        com/google/android/libraries/performance/primes/transmitter/MetricTransmitter;
        JVM INSTR monitorexit ;
_L2:
        return metricTransmitterInstance;
        obj = configs.metricTransmitter();
          goto _L5
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/transmitter/MetricTransmitter;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final MiniHeapDumpMetricService miniHeapDumpMetricService()
    {
        if (miniHeapDumpMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/MiniHeapDumpMetricService;
        JVM INSTR monitorenter ;
        if (miniHeapDumpMetricServiceInstance == null)
        {
            MiniHeapDumpMetricService miniheapdumpmetricservice = MiniHeapDumpMetricService.createService(metricTransmitter(), application, metricStamperSupplier, executorServiceSupplier, sharedPreferences, 0.94999999999999996D);
            if (!shutdown.registerShutdownListener(miniheapdumpmetricservice))
            {
                miniheapdumpmetricservice.onShutdown();
            }
            miniHeapDumpMetricServiceInstance = (MiniHeapDumpMetricService)miniheapdumpmetricservice;
        }
        com/google/android/libraries/performance/primes/MiniHeapDumpMetricService;
        JVM INSTR monitorexit ;
_L2:
        return miniHeapDumpMetricServiceInstance;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/MiniHeapDumpMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final boolean primesTraceEnabled()
    {
        return configs.primesTraceConfigurations().isEnabled || primesFlags.primesTraceEnabled;
    }

    final TimerMetricService timerMetricService()
    {
        if (timerMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/TimerMetricService;
        JVM INSTR monitorenter ;
        if (timerMetricServiceInstance != null) goto _L4; else goto _L3
_L3:
        Object obj;
        if (!primesTraceEnabled())
        {
            break MISSING_BLOCK_LABEL_120;
        }
        obj = metricTransmitter();
        Application application1 = application;
        Supplier supplier = metricStamperSupplier;
        Supplier supplier2 = executorServiceSupplier;
        TraceMetricService tracemetricservice = traceMetricService();
        PrimesTimerConfigurations primestimerconfigurations1 = configs.timerConfigurations();
        obj = new TimerMetricServiceWithTracing(((MetricTransmitter) (obj)), application1, supplier, supplier2, tracemetricservice, primestimerconfigurations1.sampleRatePerSecond, primestimerconfigurations1.perEventConfigFlags);
_L5:
        if (!shutdown.registerShutdownListener(((ShutdownListener) (obj))))
        {
            ((ShutdownListener) (obj)).onShutdown();
        }
        timerMetricServiceInstance = (TimerMetricService)obj;
_L4:
        com/google/android/libraries/performance/primes/TimerMetricService;
        JVM INSTR monitorexit ;
_L2:
        return timerMetricServiceInstance;
        obj = metricTransmitter();
        Application application2 = application;
        Supplier supplier1 = metricStamperSupplier;
        Supplier supplier3 = executorServiceSupplier;
        PrimesTimerConfigurations primestimerconfigurations = configs.timerConfigurations();
        obj = new TimerMetricService(((MetricTransmitter) (obj)), application2, supplier1, supplier3, primestimerconfigurations.sampleRatePerSecond, primestimerconfigurations.perEventConfigFlags);
          goto _L5
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/TimerMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final TraceMetricService traceMetricService()
    {
        if (traceMetricServiceInstance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorenter ;
        if (traceMetricServiceInstance != null) goto _L4; else goto _L3
_L3:
        TraceMetricService tracemetricservice;
        if (!configs.tiktokTraceConfigurations().isEnabled)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        tracemetricservice = TraceMetricService.createServiceWithTikTokTracing(metricTransmitter(), application, metricStamperSupplier, executorServiceSupplier, configs.tiktokTraceConfigurations());
_L5:
        if (!shutdown.registerShutdownListener(tracemetricservice))
        {
            tracemetricservice.onShutdown();
        }
        traceMetricServiceInstance = (TraceMetricService)tracemetricservice;
_L4:
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorexit ;
_L2:
        return traceMetricServiceInstance;
        tracemetricservice = TraceMetricService.createServiceWithPrimesTracing(metricTransmitter(), application, metricStamperSupplier, executorServiceSupplier, configs.primesTraceConfigurations());
          goto _L5
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class _cls1
        implements Supplier
    {

        private final Application val$application;
        private final PrimesConfigurations val$configs;

        public final Object get()
        {
            MetricStamper.Builder builder = new MetricStamper.Builder();
            builder.applicationContext = application;
            builder.componentNameSupplier = configs.globalConfigurations().componentNameSupplier;
            return MetricStamper.createMetricStamper(builder.applicationContext, builder.componentNameSupplier);
        }

        _cls1()
        {
            application = application1;
            configs = primesconfigurations;
            super();
        }
    }

}
