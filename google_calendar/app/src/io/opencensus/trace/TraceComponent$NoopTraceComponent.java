// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import io.opencensus.trace.propagation.PropagationComponent;

// Referenced classes of package io.opencensus.trace:
//            TraceComponent, Tracer

static final class omponent extends TraceComponent
{

    public final PropagationComponent getPropagationComponent()
    {
        return PropagationComponent.NOOP_PROPAGATION_COMPONENT;
    }

    public final Tracer getTracer()
    {
        return Tracer.noopTracer;
    }

    omponent()
    {
        new io.opencensus.trace.export.t();
    }
}
