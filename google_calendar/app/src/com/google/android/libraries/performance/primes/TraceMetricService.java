// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.performance.primes.tracing.Tracer;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.UUID;
import logs.proto.wireless.performance.mobile.nano.AccountableComponent;
import logs.proto.wireless.performance.mobile.nano.PrimesTrace;
import logs.proto.wireless.performance.mobile.nano.Span;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesTraceConfigurations, PrimesTikTokTraceConfigurations, PrimesLog, 
//            PrimesToken, Supplier

class TraceMetricService extends AbstractMetricService
{

    public final int maxTracingBufferSize;
    public final int minSpanDurationMs;
    public final ProbabilitySampler probabilitySampler;

    TraceMetricService(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, int i, float f, int j, 
            int k)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.BACKGROUND_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0, i);
        probabilitySampler = new ProbabilitySampler(f);
        minSpanDurationMs = j;
        maxTracingBufferSize = k;
    }

    static TraceMetricService createServiceWithPrimesTracing(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, PrimesTraceConfigurations primestraceconfigurations)
    {
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorenter ;
        metrictransmitter = new TraceMetricService(metrictransmitter, application, supplier, supplier1, 10, primestraceconfigurations.samplingPropability, primestraceconfigurations.minSpanDurationMs, primestraceconfigurations.maxTracingBufferSize);
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorexit ;
        return metrictransmitter;
        metrictransmitter;
        throw metrictransmitter;
    }

    static TraceMetricService createServiceWithTikTokTracing(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, PrimesTikTokTraceConfigurations primestiktoktraceconfigurations)
    {
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorenter ;
        metrictransmitter = new TraceMetricService(metrictransmitter, application, supplier, supplier1, primestiktoktraceconfigurations.sampleRatePerSecond, 1.0F, 0, 0);
        com/google/android/libraries/performance/primes/TraceMetricService;
        JVM INSTR monitorexit ;
        return metrictransmitter;
        metrictransmitter;
        throw metrictransmitter;
    }

    final void record(Span aspan[], String s)
    {
        PrimesTrace primestrace = new PrimesTrace();
        primestrace.traceId = Long.valueOf(UUID.randomUUID().getLeastSignificantBits());
        primestrace.spans = aspan;
        PrimesLog.log(3, "TraceMetricService", "Recording trace %d: %s", new Object[] {
            primestrace.traceId, primestrace.spans[0].constantName
        });
        aspan = new SystemHealthMetric();
        aspan.primesTrace = primestrace;
        if (s != null)
        {
            aspan.accountableComponent = new AccountableComponent();
            ((SystemHealthMetric) (aspan)).accountableComponent.customName = s;
        }
        recordSystemHealthMetric(null, true, aspan, null);
    }

    final void shutdownService()
    {
        Tracer.shutdown(PrimesToken.PRIMES_TOKEN);
    }
}
