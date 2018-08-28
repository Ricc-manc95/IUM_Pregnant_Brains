// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;

import com.google.apps.xplat.tracing.types.Level;

// Referenced classes of package com.google.apps.xplat.tracing:
//            TracingApi, XTracer, TracerConfig, TraceSampler, 
//            TracerBackend, AsyncTraceSection

final class WithLevelTracingApi
    implements TracingApi
{

    private final BridgeTraceSection bridgeSection;
    private final Level level;
    private final XTracer tracer;

    WithLevelTracingApi(XTracer xtracer, Level level1)
    {
        tracer = xtracer;
        level = level1;
        bridgeSection = new BridgeTraceSection(xtracer, level1);
    }

    public final AsyncTraceSection beginAsync(String s)
    {
        BridgeTraceSection bridgetracesection = bridgeSection;
        boolean flag;
        if (!XTracer.config.getSampler().isTracing())
        {
            XTracer.config.getBackend().shouldBridge();
            flag = false;
        } else
        {
            flag = true;
        }
        if (flag)
        {
            return XTracer.config.getBackend().beginAsyncAt$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTKOORFDKNMERRFCTM6ABR1E1O76BROE1M62T1FEHP62OR9DPJIUT3PE1IN6BQCCLR6AR1R9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UT3IC5HMIRJ75TA74OB3CL9MAORKD5NMSEQCD9GNCO9FDHGMSPPF8HNNAOJCCKTIIJ33DTMIUPRFDTJMOP9FC5O70SPFF1O6OOBK5TQ74OB3D5N6EBQ1EDSMSOQKE9GM6PAJCLHN8QBFDOTG____0(tracer.namespace, s, level, null);
        } else
        {
            return bridgeSection;
        }
    }

    private class BridgeTraceSection
        implements AsyncTraceSection, BlockingTraceSection
    {

        private final Level level;
        private final XTracer tracer;

        public final AsyncTraceSection annotate(String s, String s1)
        {
            return this;
        }

        public final void close()
        {
        }

        public final ListenableFuture endWhen(ListenableFuture listenablefuture)
        {
            return listenablefuture;
        }

        BridgeTraceSection(XTracer xtracer, Level level1)
        {
            tracer = xtracer;
            level = level1;
        }
    }

}
