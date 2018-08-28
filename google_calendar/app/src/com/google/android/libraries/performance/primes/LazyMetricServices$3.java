// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, LazyMetricServices, PrimesFlags, PrimesConfigurations, 
//            PrimesTikTokTraceConfigurations, TraceMetricService, Shutdown, ShutdownListener

final class this._cls0
    implements Supplier
{

    private final LazyMetricServices this$0;

    public final Object get()
    {
        if (primesFlags.startupTraceEnabled)
        {
            LazyMetricServices lazymetricservices = LazyMetricServices.this;
            boolean flag;
            if (lazymetricservices.configs.tiktokTraceConfigurations().isEnabled || lazymetricservices.primesTraceEnabled())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return lazymetricservices.traceMetricService();
            }
            TraceMetricService tracemetricservice = new TraceMetricService(lazymetricservices.metricTransmitter(), lazymetricservices.application, lazymetricservices.metricStamperSupplier, lazymetricservices.executorServiceSupplier, 10, 1.0F, 0, 0);
            if (!lazymetricservices.shutdown.registerShutdownListener(tracemetricservice))
            {
                tracemetricservice.onShutdown();
            }
            return (TraceMetricService)tracemetricservice;
        } else
        {
            return null;
        }
    }

    igurations()
    {
        this$0 = LazyMetricServices.this;
        super();
    }
}
