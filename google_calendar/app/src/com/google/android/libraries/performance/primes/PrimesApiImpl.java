// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Debug;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesApi, Supplier, PreInitPrimesApi, CpuWallTime, 
//            Shutdown, PrimesConfigurationsProvider, PrimesConfigurations, PrimesFlags, 
//            LazyMetricServices, ConfiguredPrimesApi, PrimesStartupListener, PrimesHprofFile, 
//            PrimesLog, NoopPrimesApi, AppLifecycleMonitor, AppLifecycleTracker, 
//            TimerEvent

final class PrimesApiImpl
    implements PrimesApi
{

    private static final AtomicInteger instanceCounter = new AtomicInteger();
    public final Application application;
    public final Supplier executorServiceSupplier;
    private final AtomicReference primesApiRef = new AtomicReference();

    PrimesApiImpl(Application application1, Supplier supplier)
    {
        if (application1 == null)
        {
            throw new NullPointerException();
        }
        application = (Application)application1;
        if (supplier == null)
        {
            throw new NullPointerException();
        } else
        {
            executorServiceSupplier = (Supplier)supplier;
            instanceCounter.incrementAndGet();
            primesApiRef.set(new PreInitPrimesApi());
            return;
        }
    }

    static boolean isPrimesSupported()
    {
        return true;
    }

    final void initializeInBackground(PrimesConfigurationsProvider primesconfigurationsprovider, Supplier supplier, final Supplier lazyMetricServices, Supplier supplier1, FirstActivityCreateListener firstactivitycreatelistener, FirstAppToBackgroundListener firstapptobackgroundlistener)
    {
        Object obj;
        PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement.primesInitStart = new CpuWallTime(System.nanoTime(), Debug.threadCpuTimeNanos());
        if (Integer.valueOf(0x7f1303b4) == null)
        {
            throw new NullPointerException();
        }
        supplier1 = (Shutdown)supplier1.get();
        supplier1.registerShutdownListener(firstactivitycreatelistener);
        supplier1.registerShutdownListener(firstapptobackgroundlistener);
        supplier1.init$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFADQN0S3CD5IN4EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUKRLE1O6OQB5E8TIILG_0(application, executorServiceSupplier);
        if (((Shutdown) (supplier1)).shutdown)
        {
            shutdown();
            return;
        }
        PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement.primesShutdownInitialized = new CpuWallTime(System.nanoTime(), Debug.threadCpuTimeNanos());
        lazyMetricServices = (SharedPreferences)lazyMetricServices.get();
        primesconfigurationsprovider = primesconfigurationsprovider.get();
        if (primesconfigurationsprovider == null)
        {
            throw new NullPointerException();
        }
        primesconfigurationsprovider = (PrimesConfigurations)primesconfigurationsprovider;
        if (!(primesconfigurationsprovider instanceof PrimesConfigurations.LazyValid))
        {
            primesconfigurationsprovider = new PrimesConfigurations.LazyValid(primesconfigurationsprovider);
        }
        PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement.primesConfigsCreated = new CpuWallTime(System.nanoTime(), Debug.threadCpuTimeNanos());
        supplier = (PrimesFlags)supplier.get();
        if (supplier == null)
        {
            throw new NullPointerException();
        }
        supplier = (PrimesFlags)supplier;
        if (((Shutdown) (supplier1)).shutdown)
        {
            shutdown();
            return;
        }
        PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement.primesFlagsCreated = new CpuWallTime(System.nanoTime(), Debug.threadCpuTimeNanos());
        obj = new _cls3();
        lazyMetricServices = new LazyMetricServices(application, executorServiceSupplier, ((Supplier) (obj)), primesconfigurationsprovider, supplier, lazyMetricServices, supplier1);
        supplier = new ConfiguredPrimesApi(lazyMetricServices, application.getPackageName());
        if (((Shutdown) (supplier1)).shutdown)
        {
            shutdown();
            return;
        }
        primesconfigurationsprovider = (PrimesApi)primesApiRef.get();
        if (!(primesconfigurationsprovider instanceof PreInitPrimesApi) || !primesApiRef.compareAndSet(primesconfigurationsprovider, supplier))
        {
            break MISSING_BLOCK_LABEL_602;
        }
        obj = supplier.initAndGetServices().iterator();
_L2:
        PrimesStartupListener primesstartuplistener;
        if (!((Iterator) (obj)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        primesstartuplistener = (PrimesStartupListener)((Iterator) (obj)).next();
        primesstartuplistener.onPrimesInitialize();
        firstactivitycreatelistener;
        JVM INSTR monitorenter ;
        if (firstactivitycreatelistener.activityCreated)
        {
            break MISSING_BLOCK_LABEL_407;
        }
        firstactivitycreatelistener.startupListeners.add(primesstartuplistener);
        firstactivitycreatelistener;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        primesconfigurationsprovider;
        firstactivitycreatelistener;
        JVM INSTR monitorexit ;
        throw primesconfigurationsprovider;
        firstactivitycreatelistener;
        JVM INSTR monitorexit ;
        primesstartuplistener.onFirstActivityCreated();
        if (true) goto _L2; else goto _L1
_L1:
        if (((Shutdown) (supplier1)).shutdown) goto _L4; else goto _L3
_L3:
        firstactivitycreatelistener = (PreInitPrimesApi)primesconfigurationsprovider;
        firstactivitycreatelistener.flushQueue(supplier);
        synchronized (((PreInitPrimesApi) (firstactivitycreatelistener)).scheduledApiCalls)
        {
            firstactivitycreatelistener.initializedPrimesApi = supplier;
        }
        firstactivitycreatelistener.flushQueue(supplier);
        lazyMetricServices = new _cls4();
        supplier = firstapptobackgroundlistener.firstToBackgroundTasks;
        supplier;
        JVM INSTR monitorenter ;
        if (!firstapptobackgroundlistener.appToBackground) goto _L6; else goto _L5
_L5:
        ((ScheduledExecutorService)firstapptobackgroundlistener.executorServiceSupplier.get()).submit(lazyMetricServices);
_L4:
        primesconfigurationsprovider.shutdown();
_L7:
        primesconfigurationsprovider = PrimesHprofFile.getHprofFile(application);
        if (primesconfigurationsprovider.exists())
        {
            primesconfigurationsprovider.delete();
        }
        primesconfigurationsprovider = PrimesHprofFile.getMiniHeapDumpHprofFile(application);
        if (primesconfigurationsprovider.exists())
        {
            primesconfigurationsprovider.delete();
        }
        PrimesForPrimesMeasurements.InitializationMeasurementHolder.initializationMeasurement.primesInitEnd = new CpuWallTime(System.nanoTime(), Debug.threadCpuTimeNanos());
        return;
        primesconfigurationsprovider;
        supplier1;
        JVM INSTR monitorexit ;
        throw primesconfigurationsprovider;
_L6:
        firstapptobackgroundlistener.firstToBackgroundTasks.add(lazyMetricServices);
          goto _L4
        primesconfigurationsprovider;
        supplier;
        JVM INSTR monitorexit ;
        throw primesconfigurationsprovider;
        primesconfigurationsprovider = "Primes shutdown during initialization";
        lazyMetricServices = ((Supplier) (new Object[0]));
        if (Log.isLoggable("Primes", 3))
        {
            if (lazyMetricServices.length != 0)
            {
                primesconfigurationsprovider = String.format(Locale.US, "Primes shutdown during initialization", lazyMetricServices);
            }
            Log.println(3, "Primes", primesconfigurationsprovider);
        }
        ((ConfiguredPrimesApi) (supplier)).lazyServices.shutdown.shutdown();
          goto _L7
    }

    public final void recordMemory(String s, boolean flag)
    {
        ((PrimesApi)primesApiRef.get()).recordMemory(s, flag);
    }

    final void scheduleInitialization(ExecutorService executorservice, final PrimesConfigurationsProvider configurationsProvider, final Supplier flagsSupplier, final Supplier sharedPreferencesSupplier, final Supplier shutdownSupplier, final FirstActivityCreateListener firstActivityCreateListener, final FirstAppToBackgroundListener firstAppToBackgroundListener)
    {
        try
        {
            executorservice.submit(new _cls2());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ExecutorService executorservice)
        {
            PrimesLog.log(5, "Primes", executorservice, "Primes failed to initialized", new Object[0]);
        }
        shutdown();
    }

    public final void shutdown()
    {
        ((PrimesApi)primesApiRef.getAndSet(new NoopPrimesApi())).shutdown();
        Application application1 = application;
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorenter ;
        if (AppLifecycleMonitor.instance != null)
        {
            AppLifecycleTracker applifecycletracker = AppLifecycleMonitor.instance.tracker;
            application1.unregisterActivityLifecycleCallbacks(applifecycletracker.callbacks);
            application1.unregisterComponentCallbacks(applifecycletracker.callbacks);
            AppLifecycleMonitor.instance = null;
        }
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/google/android/libraries/performance/primes/AppLifecycleMonitor;
        JVM INSTR monitorexit ;
        String s;
        try
        {
            throw exception;
        }
        catch (RuntimeException runtimeexception)
        {
            s = "Failed to shutdown app lifecycle monitor";
        }
        Object aobj[] = new Object[0];
        if (Log.isLoggable("Primes", 5))
        {
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, "Failed to shutdown app lifecycle monitor", aobj);
            }
            Log.println(5, "Primes", s);
            return;
        } else
        {
            return;
        }
    }

    public final void startMemoryMonitor()
    {
        ((PrimesApi)primesApiRef.get()).startMemoryMonitor();
    }

    public final TimerEvent startTimer()
    {
        return ((PrimesApi)primesApiRef.get()).startTimer();
    }

    public final void stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(TimerEvent timerevent, String s, boolean flag, MetricExtension metricextension, int i)
    {
        ((PrimesApi)primesApiRef.get()).stopTimer$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK7D66KOBMC4NMOOBECSNL6T3ID5N6EEQQ9HM6UPRJ5TO74RRKDSNNEQBICLM6ASRJ5TO6ASJ6DTP6QOBECDIIURBFC9KMOP9FDPGMSRPF9LIN8SJ9CD2NGT35DPPMIRRE7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFAHKMQPBI8LR6ARJK4HA6IRB5E99N8OBKELPJMAAM0(timerevent, s, flag, metricextension, i);
    }

    public final Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        return ((PrimesApi)primesApiRef.get()).wrapCrashReportingIntoUncaughtExceptionHandler(uncaughtexceptionhandler);
    }


    private class _cls3
        implements Supplier
    {

        private final PrimesApiImpl this$0;

        public final Object get()
        {
            return new ScenarioMetricService(PrimesApiImpl.this, executorServiceSupplier, new ProbabilitySampler(1.0F), 10, ScenarioMetricService.DEFAULT_TIMEOUT_MS);
        }

        _cls3()
        {
            this$0 = PrimesApiImpl.this;
            super();
        }
    }


    private class FirstActivityCreateListener
        implements AppLifecycleListener.OnActivityCreated, ShutdownListener
    {

        public boolean activityCreated;
        private final AppLifecycleMonitor appLifecycleMonitor;
        public final List startupListeners = new ArrayList();

        public final void onActivityCreated$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0()
        {
            this;
            JVM INSTR monitorenter ;
            activityCreated = true;
            this;
            JVM INSTR monitorexit ;
            appLifecycleMonitor.unregister(this);
            for (Iterator iterator = startupListeners.iterator(); iterator.hasNext(); ((PrimesStartupListener)iterator.next()).onFirstActivityCreated()) { }
            break MISSING_BLOCK_LABEL_58;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final void onShutdown()
        {
            appLifecycleMonitor.unregister(this);
        }

        FirstActivityCreateListener(AppLifecycleMonitor applifecyclemonitor)
        {
            appLifecycleMonitor = applifecyclemonitor;
            applifecyclemonitor.register(this);
        }
    }


    private class _cls4
        implements Runnable
    {

        private final LazyMetricServices val$lazyMetricServices;

        public final void run()
        {
            Object obj = lazyMetricServices;
            if (((LazyMetricServices) (obj)).primesFlags.primesForPrimesEnabled)
            {
                obj = (PrimesForPrimesTransmitterWrapper)((LazyMetricServices) (obj)).metricTransmitter();
            } else
            {
                obj = new PrimesForPrimesLogger.NoOpQueue();
            }
            ((PrimesForPrimesLogger.Queue) (obj)).enqueueMessage(new PrimesForPrimesLogger._cls1());
        }

        _cls4()
        {
            lazyMetricServices = lazymetricservices;
            super();
        }
    }


    private class FirstAppToBackgroundListener
        implements AppLifecycleListener.OnAppToBackground, ShutdownListener
    {

        private final AppLifecycleMonitor appLifecycleMonitor;
        public boolean appToBackground;
        public final Supplier executorServiceSupplier;
        public final ArrayList firstToBackgroundTasks = new ArrayList();

        public final void onAppToBackground(Activity activity)
        {
            activity = firstToBackgroundTasks;
            activity;
            JVM INSTR monitorenter ;
            ArrayList arraylist;
            int j;
            if (appToBackground)
            {
                break MISSING_BLOCK_LABEL_96;
            }
            appToBackground = true;
            appLifecycleMonitor.unregister(this);
            arraylist = (ArrayList)firstToBackgroundTasks;
            j = arraylist.size();
            int i = 0;
_L2:
            if (i >= j)
            {
                break; /* Loop/switch isn't completed */
            }
            Runnable runnable = (Runnable)arraylist.get(i);
            ((ScheduledExecutorService)executorServiceSupplier.get()).submit(runnable);
            i++;
            if (true) goto _L2; else goto _L1
_L1:
            firstToBackgroundTasks.clear();
            activity;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            activity;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final void onShutdown()
        {
            appLifecycleMonitor.unregister(this);
        }

        FirstAppToBackgroundListener(AppLifecycleMonitor applifecyclemonitor, Supplier supplier)
        {
            appLifecycleMonitor = applifecyclemonitor;
            executorServiceSupplier = supplier;
            applifecyclemonitor.register(this);
        }
    }


    private class _cls2
        implements Runnable
    {

        private final PrimesApiImpl this$0;
        private final PrimesConfigurationsProvider val$configurationsProvider;
        private final FirstActivityCreateListener val$firstActivityCreateListener;
        private final FirstAppToBackgroundListener val$firstAppToBackgroundListener;
        private final Supplier val$flagsSupplier;
        private final Supplier val$sharedPreferencesSupplier;
        private final Supplier val$shutdownSupplier;

        public final void run()
        {
            try
            {
                initializeInBackground(configurationsProvider, flagsSupplier, sharedPreferencesSupplier, shutdownSupplier, firstActivityCreateListener, firstAppToBackgroundListener);
                return;
            }
            catch (RuntimeException runtimeexception)
            {
                PrimesLog.log(5, "Primes", runtimeexception, "Primes failed to initialized in the background", new Object[0]);
            }
            shutdown();
        }

        _cls2()
        {
            this$0 = PrimesApiImpl.this;
            configurationsProvider = primesconfigurationsprovider;
            flagsSupplier = supplier;
            sharedPreferencesSupplier = supplier1;
            shutdownSupplier = supplier2;
            firstActivityCreateListener = firstactivitycreatelistener;
            firstAppToBackgroundListener = firstapptobackgroundlistener;
            super();
        }
    }

}
