// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.StreamTracer;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class StatsTraceContext
{

    private static final StatsTraceContext NOOP = new StatsTraceContext(new StreamTracer[0]);
    public final AtomicBoolean closed = new AtomicBoolean(false);
    public final StreamTracer tracers[];

    private StatsTraceContext(StreamTracer astreamtracer[])
    {
        tracers = astreamtracer;
    }

    public static StatsTraceContext newClientContext(CallOptions calloptions, Metadata metadata)
    {
        calloptions = calloptions.streamTracerFactories;
        if (calloptions.isEmpty())
        {
            return NOOP;
        }
        StreamTracer astreamtracer[] = new StreamTracer[calloptions.size()];
        for (int i = 0; i < astreamtracer.length; i++)
        {
            astreamtracer[i] = ((io.grpc.ClientStreamTracer.Factory)calloptions.get(i)).newClientStreamTracer$5166IRPFCTP70OPF8DGMOR2FE1Q6IRREECTKOQBF5TJN4S335T6MAT31CHGN8O9R5566IRPFCTP70OPF8DM6IPBEEH9N8SJ5C5ML8SJ1CDIN4EO_0(metadata);
        }

        return new StatsTraceContext(astreamtracer);
    }

    public final void inboundUncompressedSize(long l)
    {
        StreamTracer astreamtracer[] = tracers;
        int j = astreamtracer.length;
        for (int i = 0; i < j; i++)
        {
            astreamtracer[i].inboundUncompressedSize(l);
        }

    }

    public final void inboundWireSize(long l)
    {
        StreamTracer astreamtracer[] = tracers;
        int j = astreamtracer.length;
        for (int i = 0; i < j; i++)
        {
            astreamtracer[i].inboundWireSize(l);
        }

    }

    public final void outboundMessage(int i)
    {
        StreamTracer astreamtracer[] = tracers;
        int j = astreamtracer.length;
        for (i = 0; i < j; i++)
        {
            astreamtracer[i].outboundMessage$514IILG_0();
        }

    }

    public final void outboundMessageSent(int i, long l, long l1)
    {
        StreamTracer astreamtracer[] = tracers;
        int k = astreamtracer.length;
        for (int j = 0; j < k; j++)
        {
            astreamtracer[j].outboundMessageSent(i, l, l1);
        }

    }

    public final void outboundUncompressedSize(long l)
    {
        StreamTracer astreamtracer[] = tracers;
        int j = astreamtracer.length;
        for (int i = 0; i < j; i++)
        {
            astreamtracer[i].outboundUncompressedSize(l);
        }

    }

    public final void outboundWireSize(long l)
    {
        StreamTracer astreamtracer[] = tracers;
        int j = astreamtracer.length;
        for (int i = 0; i < j; i++)
        {
            astreamtracer[i].outboundWireSize(l);
        }

    }

}
