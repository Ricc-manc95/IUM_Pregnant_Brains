// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, MetricStamper

final class MetricRecorder
{

    public final Supplier executorServiceSupplier;
    public final PrimesSampling instrumentationSampling;
    private final Supplier metricStamperSupplier;
    private final MetricTransmitter metricTransmitter;
    public final int whereToRun$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0;

    MetricRecorder(MetricTransmitter metrictransmitter, Supplier supplier, Supplier supplier1, int i, int j)
    {
        if (metrictransmitter == null)
        {
            throw new NullPointerException();
        }
        metricTransmitter = (MetricTransmitter)metrictransmitter;
        if (supplier == null)
        {
            throw new NullPointerException();
        } else
        {
            metricStamperSupplier = (Supplier)supplier;
            executorServiceSupplier = supplier1;
            whereToRun$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0 = i;
            instrumentationSampling = new PrimesSampling(j);
            return;
        }
    }

    final void recordInternal(String s, boolean flag, SystemHealthMetric systemhealthmetric, MetricExtension metricextension)
    {
        if (systemhealthmetric == null)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "metric is null, skipping recorded metric for event: ".concat(s);
            } else
            {
                s = new String("metric is null, skipping recorded metric for event: ");
            }
            systemhealthmetric = ((SystemHealthMetric) (new Object[0]));
            if (Log.isLoggable("MetricRecorder", 5))
            {
                if (systemhealthmetric.length != 0)
                {
                    s = String.format(Locale.US, s, systemhealthmetric);
                }
                Log.println(5, "MetricRecorder", s);
            }
            return;
        }
        systemhealthmetric = ((MetricStamper)metricStamperSupplier.get()).stamp(systemhealthmetric);
        if (flag)
        {
            systemhealthmetric.constantEventName = s;
        } else
        {
            systemhealthmetric.customEventName = s;
        }
        if (metricextension != null)
        {
            systemhealthmetric.metricExtension = metricextension;
        }
        metricTransmitter.send(systemhealthmetric);
        systemhealthmetric = instrumentationSampling;
        synchronized (((PrimesSampling) (systemhealthmetric)).mutex)
        {
            systemhealthmetric.samplesCount = ((PrimesSampling) (systemhealthmetric)).samplesCount + 1;
            long l = SystemClock.elapsedRealtime();
            if (l - ((PrimesSampling) (systemhealthmetric)).firstSampleInLastSecond > 1000L)
            {
                systemhealthmetric.samplesCount = 0;
                systemhealthmetric.firstSampleInLastSecond = l;
            }
        }
        return;
        systemhealthmetric;
        s;
        JVM INSTR monitorexit ;
        throw systemhealthmetric;
    }
}
