// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import logs.proto.wireless.performance.mobile.nano.CpuUsageMetric;
import logs.proto.wireless.performance.mobile.nano.StackElement;
import logs.proto.wireless.performance.mobile.nano.StackTrace;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            CpuMetricService, AbstractMetricService, MetricRecorder

final class this._cls0
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

    ()
    {
        this$0 = CpuMetricService.this;
        super();
    }
}
