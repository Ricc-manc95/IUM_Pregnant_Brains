// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.util.Log;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.PrimesScenario;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, MetricRecorder, Supplier, MemoryMetricMonitor, 
//            AppLifecycleMonitor, MemoryMetricExtensionProvider

final class MemoryMetricService extends AbstractMetricService
{
    public static interface TimeCapture
    {
    }


    public final boolean forceGcBeforeRecordMemory;
    public final boolean memorySummaryDisabled;
    public final MemoryMetricExtensionProvider metricExtensionProvider;
    private MemoryMetricMonitor metricMonitor;
    public final boolean recordMemoryPerProcess;

    MemoryMetricService(TimeCapture timecapture, MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, int i, boolean flag, 
            MemoryMetricExtensionProvider memorymetricextensionprovider, boolean flag1, boolean flag2)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0, i);
        new AtomicReference(null);
        new ConcurrentHashMap();
        recordMemoryPerProcess = flag;
        metricExtensionProvider = memorymetricextensionprovider;
        forceGcBeforeRecordMemory = flag1;
        memorySummaryDisabled = flag2;
    }

    final void recordEvent(final String customEventName, final boolean isEventNameConstant, final int eventCode, final String activityName, final MetricExtension metricExtension, final PrimesScenario primesScenario)
    {
        boolean flag;
        if (!super.metricRecorder.instrumentationSampling.isSampleRateExceeded())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls5());
        }
    }

    final void shutdownService()
    {
        this;
        JVM INSTR monitorenter ;
        if (metricMonitor != null)
        {
            MemoryMetricMonitor memorymetricmonitor = metricMonitor;
            memorymetricmonitor.appLifecycleMonitor.unregister(memorymetricmonitor.onAppToBackground);
            memorymetricmonitor.appLifecycleMonitor.unregister(memorymetricmonitor.onAppToForeground);
            metricMonitor = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void startMonitoring()
    {
        this;
        JVM INSTR monitorenter ;
        if (super.shutdown || metricMonitor != null) goto _L2; else goto _L1
_L1:
        Object obj;
        metricMonitor = new MemoryMetricMonitor(new _cls2(), super.application, super.executorServiceSupplier);
        obj = metricMonitor;
        if (!((MemoryMetricMonitor) (obj)).hasMemoryMonitorStarted.getAndSet(true)) goto _L4; else goto _L3
_L3:
        obj = "Memory Monitor has already started. This MemoryMetricMonitor.start() is ignored.";
        Object aobj[] = new Object[0];
        if (!Log.isLoggable("MemoryMetricMonitor", 5)) goto _L2; else goto _L5
_L5:
        if (aobj.length != 0) goto _L7; else goto _L6
_L6:
        Log.println(5, "MemoryMetricMonitor", ((String) (obj)));
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L7:
        obj = String.format(Locale.US, "Memory Monitor has already started. This MemoryMetricMonitor.start() is ignored.", aobj);
          goto _L6
_L4:
        ((MemoryMetricMonitor) (obj)).appLifecycleMonitor.register(((MemoryMetricMonitor) (obj)).onAppToBackground);
        ((MemoryMetricMonitor) (obj)).appLifecycleMonitor.register(((MemoryMetricMonitor) (obj)).onAppToForeground);
          goto _L2
        Exception exception;
        exception;
        throw exception;
          goto _L6
    }

    private class _cls5
        implements Runnable
    {

        private final MemoryMetricService this$0;
        private final String val$activityName;
        private final String val$customEventName;
        private final int val$eventCode;
        private final boolean val$isEventNameConstant;
        private final MetricExtension val$metricExtension;
        private final PrimesScenario val$primesScenario;

        public final void run()
        {
            Object obj1;
            if (forceGcBeforeRecordMemory)
            {
                System.gc();
                System.runFinalization();
                System.gc();
            }
            obj1 = metricExtension;
            if (metricExtension != null || metricExtensionProvider == null)
            {
                break MISSING_BLOCK_LABEL_276;
            }
            Object obj = metricExtensionProvider.getMetricExtension(customEventName, eventCode);
_L1:
            Object aobj[];
            if (recordMemoryPerProcess)
            {
                obj1 = MemoryMetricService.this;
                String s = customEventName;
                boolean flag = isEventNameConstant;
                int i = eventCode;
                String s2 = activityName;
                PrimesScenario primesscenario = primesScenario;
                Object obj3 = ProcessStats.getActivityManager(((AbstractMetricService) (obj1)).application).getRunningAppProcesses();
                if (obj3 != null)
                {
                    String s4 = ((AbstractMetricService) (obj1)).application.getPackageName();
                    obj3 = ((List) (obj3)).iterator();
                    do
                    {
                        if (!((Iterator) (obj3)).hasNext())
                        {
                            break;
                        }
                        android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj3)).next();
                        if (android.os.Build.VERSION.SDK_INT > 22 || runningappprocessinfo.processName.contains(s4))
                        {
                            SystemHealthMetric systemhealthmetric1 = new SystemHealthMetric();
                            systemhealthmetric1.primesScenario = primesscenario;
                            systemhealthmetric1.memoryUsageMetric = MemoryUsageCapture.getMemoryUsageMetric(i, runningappprocessinfo.pid, runningappprocessinfo.processName, ((AbstractMetricService) (obj1)).application, s2, ((MemoryMetricService) (obj1)).memorySummaryDisabled);
                            ((AbstractMetricService) (obj1)).recordSystemHealthMetric(s, flag, systemhealthmetric1, ((MetricExtension) (obj)));
                        }
                    } while (true);
                }
            } else
            {
                MemoryMetricService memorymetricservice = MemoryMetricService.this;
                String s1 = customEventName;
                boolean flag1 = isEventNameConstant;
                int j = eventCode;
                String s3 = activityName;
                Object obj2 = primesScenario;
                SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
                systemhealthmetric.primesScenario = ((PrimesScenario) (obj2));
                obj2 = ((AbstractMetricService) (memorymetricservice)).application;
                boolean flag2 = memorymetricservice.memorySummaryDisabled;
                systemhealthmetric.memoryUsageMetric = MemoryUsageCapture.getMemoryUsageMetric(j, Process.myPid(), null, ((android.content.Context) (obj2)), s3, flag2);
                memorymetricservice.recordSystemHealthMetric(s1, flag1, systemhealthmetric, ((MetricExtension) (obj)));
            }
            break MISSING_BLOCK_LABEL_386;
            obj;
            obj = "Metric metric extension provider failed.";
            aobj = new Object[0];
            if (Log.isLoggable("MemoryMetricService", 6))
            {
                if (aobj.length != 0)
                {
                    obj = String.format(Locale.US, "Metric metric extension provider failed.", aobj);
                }
                Log.println(6, "MemoryMetricService", ((String) (obj)));
            }
            obj = obj1;
              goto _L1
        }

        _cls5()
        {
            this$0 = MemoryMetricService.this;
            metricExtension = metricextension;
            customEventName = s;
            eventCode = i;
            isEventNameConstant = flag;
            activityName = s1;
            primesScenario = primesscenario;
            super();
        }
    }


    private class _cls2
        implements MemoryMetricMonitor.Callback
    {

        private final MemoryMetricService this$0;

        public final void onEvent(int i, String s)
        {
            recordEvent(null, false, i, s, null, null);
        }

        _cls2()
        {
            this$0 = MemoryMetricService.this;
            super();
        }
    }

}
