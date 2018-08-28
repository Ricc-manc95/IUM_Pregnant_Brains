// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import io.opencensus.trace.propagation.PropagationComponent;

// Referenced classes of package io.opencensus.trace:
//            Tracer

public abstract class TraceComponent
{
    static final class NoopTraceComponent extends TraceComponent
    {

        public final PropagationComponent getPropagationComponent()
        {
            return PropagationComponent.NOOP_PROPAGATION_COMPONENT;
        }

        public final Tracer getTracer()
        {
            return Tracer.noopTracer;
        }

        NoopTraceComponent()
        {
            new io.opencensus.trace.export.ExportComponent.NoopExportComponent();
        }
    }


    public TraceComponent()
    {
    }

    public abstract PropagationComponent getPropagationComponent();

    public abstract Tracer getTracer();
}
