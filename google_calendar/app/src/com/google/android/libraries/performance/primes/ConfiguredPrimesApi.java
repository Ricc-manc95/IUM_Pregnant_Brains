// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.os.Build;
import android.os.SystemClock;
import com.google.android.libraries.performance.primes.leak.LeakWatcher;
import java.util.ArrayList;
import java.util.List;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.PrimesScenario;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesApi, LazyMetricServices, PrimesConfigurations, PrimesCrashConfigurations, 
//            PrimesPackageConfigurations, PackageMetricService, PrimesDirStatsConfigurations, Shutdown, 
//            ShutdownListener, PrimesFlags, PrimesBatteryConfigurations, PrimesJankConfigurations, 
//            PrimesCpuConfigurations, PrimesTimerConfigurations, PrimesStartupMeasure, AppLifecycleMonitor, 
//            PrimesTraceConfigurations, PrimesStartupMetricHandler, PrimesMemoryLeakConfigurations, MemoryLeakMetricService, 
//            PrimesMiniHeapDumpConfigurations, MiniHeapDumpMetricService, AbstractMetricService, PrimesMemoryConfigurations, 
//            MemoryMetricService, TimerEvent, TimerMetricService

final class ConfiguredPrimesApi
    implements PrimesApi
{

    public final LazyMetricServices lazyServices;
    private final String packageName;

    ConfiguredPrimesApi(LazyMetricServices lazymetricservices, String s)
    {
        lazyServices = lazymetricservices;
        packageName = s;
    }

    final List initAndGetServices()
    {
        int i;
        boolean flag = true;
        obj = new ArrayList();
        if (lazyServices.configs.crashConfigurations().enabled)
        {
            ((List) (obj)).add(lazyServices.crashMetricService());
        }
        LazyMetricServices lazymetricservices = lazyServices;
        if (lazymetricservices.configs.packageConfigurations().enabled && !lazymetricservices.configs.packageConfigurations().manualCapture)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            lazymetricservices = lazyServices;
            Object obj3 = lazymetricservices.metricTransmitter();
            Application application = lazymetricservices.application;
            Supplier supplier = lazymetricservices.metricStamperSupplier;
            Supplier supplier1 = lazymetricservices.executorServiceSupplier;
            android.content.SharedPreferences sharedpreferences = lazymetricservices.sharedPreferences;
            PrimesDirStatsConfigurations primesdirstatsconfigurations = lazymetricservices.configs.packageConfigurations().dirStatsConfigs;
            obj3 = new PackageMetricService(((com.google.android.libraries.performance.primes.transmitter.MetricTransmitter) (obj3)), application, supplier, supplier1, sharedpreferences, primesdirstatsconfigurations.enabled, primesdirstatsconfigurations.maxFolderDepth, primesdirstatsconfigurations.getListFilesPatterns());
            if (!lazymetricservices.shutdown.registerShutdownListener(((ShutdownListener) (obj3))))
            {
                ((ShutdownListener) (obj3)).onShutdown();
            }
            ((List) (obj)).add((PackageMetricService)obj3);
        }
        lazymetricservices = lazyServices;
        if (android.os.Build.VERSION.SDK_INT >= 24 && (lazymetricservices.primesFlags.batteryExperimentEnabled || lazymetricservices.configs.batteryConfigurations().enabled))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            ((List) (obj)).add(lazyServices.batteryMetricService());
        }
        if (lazyServices.primesFlags.magicEyeLogEnabled)
        {
            ((List) (obj)).add(lazyServices.magicEyeLogService());
        }
        lazymetricservices = lazyServices;
        if (android.os.Build.VERSION.SDK_INT >= 24 && lazymetricservices.configs.jankConfigurations().enabled && !lazymetricservices.configs.jankConfigurations().useAnimator)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            ((List) (obj)).add(lazyServices.frameMetricService());
        }
        if (lazyServices.configs.cpuConfigurations().enabled)
        {
            ((List) (obj)).add(lazyServices.cpuMetricService());
        }
        if (lazyServices.configs.timerConfigurations().enabled && PrimesStartupMeasure.instance.appClassLoadedAt > 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            Object obj1 = lazyServices;
            Object obj4 = AppLifecycleMonitor.getInstance(((LazyMetricServices) (obj1)).application);
            LazyMetricServices._cls2 _lcls2 = new LazyMetricServices._cls2(((LazyMetricServices) (obj1)));
            LazyMetricServices._cls3 _lcls3 = new LazyMetricServices._cls3(((LazyMetricServices) (obj1)));
            boolean flag1;
            if (((LazyMetricServices) (obj1)).configs.primesTraceConfigurations().isEnabled || ((LazyMetricServices) (obj1)).primesFlags.primesTraceEnabled)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0 && ((LazyMetricServices) (obj1)).primesFlags.startupTraceEnabled)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            obj4 = new PrimesStartupMetricHandler(((AppLifecycleMonitor) (obj4)), _lcls2, _lcls3, flag1, ((LazyMetricServices) (obj1)).configs.primesTraceConfigurations());
            if (!((LazyMetricServices) (obj1)).shutdown.registerShutdownListener(((ShutdownListener) (obj4))))
            {
                ((ShutdownListener) (obj4)).onShutdown();
            }
            obj1 = (PrimesStartupMetricHandler)obj4;
        }
        obj1 = lazyServices;
        if (((LazyMetricServices) (obj1)).configs.memoryLeakConfigurations().enabled || ((LazyMetricServices) (obj1)).primesFlags.leakDetectionEnabled || ((LazyMetricServices) (obj1)).primesFlags.leakDetectionV2Enabled)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            synchronized (lazyServices.memoryLeakMetricService())
            {
                ((MemoryLeakMetricService) (obj2)).leakWatcher.start();
                ((MemoryLeakMetricService) (obj2)).appLifecycleMonitor.register(((AppLifecycleListener) (obj2)));
            }
        }
        obj2 = lazyServices;
        if (!((LazyMetricServices) (obj2)).configs.miniHeapDumpConfigurations().enabled || !MiniHeapDumpMetricService.isFileUploadEnabled(((LazyMetricServices) (obj2)).application))
        {
            break MISSING_BLOCK_LABEL_841;
        }
        obj2 = ((LazyMetricServices) (obj2)).application;
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        obj2 = (DevicePolicyManager)((Application) (obj2)).getSystemService("device_policy");
        if (obj2 == null)
        {
            i = 0;
        } else
        {
            i = ((DevicePolicyManager) (obj2)).getStorageEncryptionStatus();
        }
        if (i == 3 || i == 4 || i == 5)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || !Build.FINGERPRINT.contains("userdebug")) goto _L2; else goto _L3
_L3:
        i = 1;
_L4:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_841;
        }
        i = ((flag) ? 1 : 0);
_L5:
        if (i != 0)
        {
            obj2 = lazyServices.miniHeapDumpMetricService();
            if (!((AbstractMetricService) (obj2)).shutdown)
            {
                ((MiniHeapDumpMetricService) (obj2)).appLifecycleMonitor.register(((MiniHeapDumpMetricService) (obj2)).logTotalPssSampleCount);
                ((MiniHeapDumpMetricService) (obj2)).appLifecycleMonitor.register(((MiniHeapDumpMetricService) (obj2)).takeAndLogMemorySample);
                ((MiniHeapDumpMetricService) (obj2)).appLifecycleMonitor.register(((MiniHeapDumpMetricService) (obj2)).onAppToForeground);
            }
            return ((List) (obj));
        } else
        {
            return ((List) (obj));
        }
        obj;
        obj2;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        i = 0;
          goto _L4
        i = 0;
          goto _L5
    }

    public final void recordMemory(String s, boolean flag)
    {
        if (lazyServices.configs.memoryConfigurations().enabled)
        {
            lazyServices.memoryMetricService().recordEvent(s, flag, 0, null, null, null);
        }
    }

    public final void recordMemory(String s, boolean flag, MetricExtension metricextension, PrimesScenario primesscenario)
    {
        if (lazyServices.configs.memoryConfigurations().enabled)
        {
            lazyServices.memoryMetricService().recordEvent(s, flag, 0, null, metricextension, primesscenario);
        }
    }

    public final void shutdown()
    {
        lazyServices.shutdown.shutdown();
    }

    public final void startMemoryMonitor()
    {
        if (lazyServices.configs.memoryConfigurations().enabled)
        {
            lazyServices.memoryMetricService().startMonitoring();
        }
    }

    public final TimerEvent startTimer()
    {
        if (!lazyServices.configs.timerConfigurations().enabled)
        {
            return TimerEvent.EMPTY_TIMER;
        } else
        {
            return lazyServices.timerMetricService().start();
        }
    }

    public final void stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(TimerEvent timerevent, String s, boolean flag, MetricExtension metricextension, int i)
    {
        if (TimerEvent.isEmpty(timerevent) || !lazyServices.configs.timerConfigurations().enabled)
        {
            return;
        } else
        {
            timerevent.endMs = SystemClock.elapsedRealtime();
            timerevent.timerStatus$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQKD5MMASI5EPIMST14AHKMQPBIADQ62T3LECTG____0 = i;
            lazyServices.timerMetricService().recordTimer(timerevent, s, flag, metricextension);
            return;
        }
    }

    public final Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        if (lazyServices.configs.crashConfigurations().enabled)
        {
            return new CrashMetricService.PrimesUncaughtExceptionHandler(lazyServices.crashMetricService(), uncaughtexceptionhandler);
        } else
        {
            return uncaughtexceptionhandler;
        }
    }
}
