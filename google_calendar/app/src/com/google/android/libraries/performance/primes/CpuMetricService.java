// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import logs.proto.wireless.performance.mobile.nano.CpuUsageMetric;
import logs.proto.wireless.performance.mobile.nano.StackElement;
import logs.proto.wireless.performance.mobile.nano.StackTrace;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, Supplier, MetricRecorder

final class CpuMetricService extends AbstractMetricService
    implements PrimesStartupListener
{
    final class CpuCollectionTask
        implements Runnable
    {

        private final AtomicInteger samplesCollectedSoFar = new AtomicInteger(0);
        private final CpuMetricService this$0;

        public final void run()
        {
            int i;
            if (!metricRecorder.instrumentationSampling.isSampleRateExceeded())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0 && samplesCollectedSoFar.getAndIncrement() < numSamples)
            {
                CpuMetricService cpumetricservice = CpuMetricService.this;
                SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
                Object obj = new HashSet(Thread.getAllStackTraces().values());
                CpuUsageMetric cpuusagemetric = new CpuUsageMetric();
                cpuusagemetric.stackTraces = new StackTrace[((HashSet) (obj)).size()];
                obj = ((HashSet) (obj)).iterator();
                for (i = 0; ((Iterator) (obj)).hasNext(); i++)
                {
                    StackTraceElement astacktraceelement[] = (StackTraceElement[])((Iterator) (obj)).next();
                    int k = astacktraceelement.length;
                    StackElement astackelement[] = new StackElement[k];
                    for (int j = 0; j < k; j++)
                    {
                        Object obj1 = astacktraceelement[j];
                        StackElement stackelement = new StackElement();
                        String s = ((StackTraceElement) (obj1)).getClassName();
                        obj1 = ((StackTraceElement) (obj1)).getMethodName();
                        stackelement.functionName = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(obj1).length())).append(s).append(".").append(((String) (obj1))).toString();
                        astackelement[j] = stackelement;
                    }

                    StackTrace stacktrace = new StackTrace();
                    stacktrace.stackElements = astackelement;
                    cpuusagemetric.stackTraces[i] = stacktrace;
                }

                systemhealthmetric.cpuUsageMetric = cpuusagemetric;
                cpumetricservice.recordSystemHealthMetric(null, true, systemhealthmetric, null);
            }
            if (samplesCollectedSoFar.get() >= numSamples)
            {
                shutdownService(false);
            }
        }

        CpuCollectionTask()
        {
            this$0 = CpuMetricService.this;
            super();
        }
    }


    private final int initialDelay;
    public final int numSamples;
    private ScheduledFuture scheduledFutureCollectCpuUsage;
    private final int timeBetweenSamples;

    CpuMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, int i, int j, int k)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.BACKGROUND_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        timeBetweenSamples = i;
        initialDelay = j;
        numSamples = k;
    }

    private final void startMonitoring()
    {
        this;
        JVM INSTR monitorenter ;
        if (scheduledFutureCollectCpuUsage == null && !super.shutdown)
        {
            scheduledFutureCollectCpuUsage = ((ScheduledExecutorService)super.executorServiceSupplier.get()).scheduleAtFixedRate(new CpuCollectionTask(), initialDelay, timeBetweenSamples, TimeUnit.MILLISECONDS);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onFirstActivityCreated()
    {
        startMonitoring();
    }

    public final void onPrimesInitialize()
    {
    }

    final void shutdownService()
    {
        this;
        JVM INSTR monitorenter ;
        shutdownService(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void shutdownService(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (scheduledFutureCollectCpuUsage != null)
        {
            scheduledFutureCollectCpuUsage.cancel(flag);
            scheduledFutureCollectCpuUsage = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
