// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ClientStreamTracer;
import io.opencensus.trace.Span;

// Referenced classes of package io.grpc.internal:
//            CensusTracingModule

static final class span extends ClientStreamTracer
{

    private final Span span;

    public final void inboundMessageRead(int i, long l, long l1)
    {
        CensusTracingModule.recordMessageEvent(span, io.opencensus.trace.n, i, l, l1);
    }

    public final void outboundMessageSent(int i, long l, long l1)
    {
        CensusTracingModule.recordMessageEvent(span, io.opencensus.trace.n, i, l, l1);
    }

    (Span span1)
    {
        if (span1 == null)
        {
            throw new NullPointerException(String.valueOf("span"));
        } else
        {
            span = (Span)span1;
            return;
        }
    }
}
