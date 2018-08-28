// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.text.TextUtils;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.performance.primes.tracing.Tracer;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledExecutorService;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            TimerMetricService, TimerEvent, PrimesToken, AbstractMetricService, 
//            Supplier, TraceMetricService, PrimesPerEventConfigurationFlags

final class TimerMetricServiceWithTracing extends TimerMetricService
{

    private final Map timerEventToThreadIdMap = new WeakHashMap();
    private final TraceMetricService traceMetricService;

    TimerMetricServiceWithTracing(MetricTransmitter metrictransmitter, Application application, Supplier supplier, Supplier supplier1, TraceMetricService tracemetricservice, int i, PrimesPerEventConfigurationFlags primespereventconfigurationflags)
    {
        super(metrictransmitter, application, supplier, supplier1, i, primespereventconfigurationflags);
        traceMetricService = tracemetricservice;
    }

    final void recordTimer(TimerEvent timerevent, String s, boolean flag, MetricExtension metricextension)
    {
        this;
        JVM INSTR monitorenter ;
        super.recordTimer(timerevent, s, flag, metricextension);
        if (TimerEvent.isEmpty(timerevent)) goto _L2; else goto _L1
_L1:
        if (!timerevent.hasTrace) goto _L4; else goto _L3
_L3:
        timerevent = traceMetricService;
        metricextension = PrimesToken.PRIMES_TOKEN;
        if (TextUtils.isEmpty(s));
        s = Tracer.stop(metricextension, s);
        if (s == null || s == null) goto _L2; else goto _L5
_L5:
        ((ScheduledExecutorService)((AbstractMetricService) (timerevent)).executorServiceSupplier.get()).submit(new TraceMetricService._cls1(timerevent, s, null));
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        long l;
        long l1;
        if (!timerEventToThreadIdMap.containsKey(timerevent) || ((Long)timerEventToThreadIdMap.get(timerevent)).longValue() != Thread.currentThread().getId())
        {
            break MISSING_BLOCK_LABEL_169;
        }
        l = timerevent.startMs;
        l1 = timerevent.endMs - timerevent.startMs;
        if (TextUtils.isEmpty(s) || l1 <= 0L)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        Tracer.sideLoadSpan(PrimesToken.PRIMES_TOKEN, s, l, l1);
        timerEventToThreadIdMap.remove(timerevent);
        if (true) goto _L2; else goto _L6
_L6:
        timerevent;
        throw timerevent;
    }

    final void shutdownService()
    {
        super.shutdownService();
        Tracer.cancel(PrimesToken.PRIMES_TOKEN);
    }

    final TimerEvent start()
    {
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        TimerEvent timerevent = super.start();
        if (TimerEvent.isEmpty(timerevent)) goto _L2; else goto _L1
_L1:
        TraceMetricService tracemetricservice;
        ProbabilitySampler probabilitysampler;
        tracemetricservice = traceMetricService;
        probabilitysampler = tracemetricservice.probabilitySampler;
        Exception exception;
        boolean flag;
        if (probabilitysampler.samplingRate != 1.0F && probabilitysampler.random.nextFloat() > probabilitysampler.samplingRate)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        if (!Tracer.start(PrimesToken.PRIMES_TOKEN, tracemetricservice.minSpanDurationMs, tracemetricservice.maxTracingBufferSize)) goto _L4; else goto _L5
_L5:
        Tracer.createRootSpan(PrimesToken.PRIMES_TOKEN, "");
        flag = flag1;
_L9:
        if (!flag) goto _L7; else goto _L6
_L6:
        timerevent.hasTrace = true;
_L2:
        this;
        JVM INSTR monitorexit ;
        return timerevent;
_L4:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L7:
        Tracer.activateSideLoadSpans(PrimesToken.PRIMES_TOKEN);
        timerEventToThreadIdMap.put(timerevent, Long.valueOf(Thread.currentThread().getId()));
          goto _L2
        exception;
        throw exception;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
