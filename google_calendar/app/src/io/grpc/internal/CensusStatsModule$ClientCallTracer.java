// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.opencensus.contrib.grpc.metrics.RpcMeasureConstants;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            CensusStatsModule

static final class startCtx extends io.grpc.llTracer
{

    public static final AtomicIntegerFieldUpdater callEndedUpdater;
    private static final AtomicReferenceFieldUpdater streamTracerUpdater;
    public volatile int callEnded;
    public final CensusStatsModule module;
    private final TagContext parentCtx;
    public final boolean recordFinishedRpcs;
    public final TagContext startCtx;
    public final Stopwatch stopwatch;
    public volatile ter streamTracer;

    public final ClientStreamTracer newClientStreamTracer$5166IRPFCTP70OPF8DGMOR2FE1Q6IRREECTKOQBF5TJN4S335T6MAT31CHGN8O9R5566IRPFCTP70OPF8DM6IPBEEH9N8SJ5C5ML8SJ1CDIN4EO_0(Metadata metadata)
    {
        startCtx startctx = new t>();
        if (streamTracerUpdater != null)
        {
            if (!streamTracerUpdater.compareAndSet(this, null, startctx))
            {
                throw new IllegalStateException(String.valueOf("Are you creating multiple streams per call? This class doesn't yet support this case"));
            }
        } else
        {
            boolean flag;
            if (streamTracer == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Are you creating multiple streams per call? This class doesn't yet support this case"));
            }
            streamTracer = startctx;
        }
        if (module.propagateTags)
        {
            metadata.discardAll(module.statsHeader);
            if (!module.tagger.empty().equals(parentCtx))
            {
                metadata.put(module.statsHeader, parentCtx);
            }
        }
        return startctx;
    }

    static 
    {
        AtomicReferenceFieldUpdater atomicreferencefieldupdater = null;
        AtomicIntegerFieldUpdater atomicintegerfieldupdater;
        AtomicReferenceFieldUpdater atomicreferencefieldupdater1;
        atomicreferencefieldupdater1 = AtomicReferenceFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientCallTracer, io/grpc/internal/CensusStatsModule$ClientTracer, "streamTracer");
        atomicintegerfieldupdater = AtomicIntegerFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientCallTracer, "callEnded");
        atomicreferencefieldupdater = atomicreferencefieldupdater1;
_L2:
        streamTracerUpdater = atomicreferencefieldupdater;
        callEndedUpdater = atomicintegerfieldupdater;
        return;
        Throwable throwable;
        throwable;
        CensusStatsModule.logger.logp(Level.SEVERE, "io.grpc.internal.CensusStatsModule$ClientCallTracer", "<clinit>", "Creating atomic field updaters failed", throwable);
        throwable = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    ter(CensusStatsModule censusstatsmodule, TagContext tagcontext, String s, boolean flag, boolean flag1)
    {
        module = censusstatsmodule;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fullMethodName"));
        }
        if (tagcontext == null)
        {
            throw new NullPointerException();
        }
        parentCtx = (TagContext)tagcontext;
        startCtx = censusstatsmodule.tagger.toBuilder(tagcontext).put(RpcMeasureConstants.RPC_METHOD, TagValue.create(s)).build();
        stopwatch = ((Stopwatch)censusstatsmodule.stopwatchSupplier.get()).start();
        recordFinishedRpcs = flag1;
        if (flag)
        {
            censusstatsmodule.statsRecorder.newMeasureMap().put$5166IRPFDTO6ARJ3CLN76TBJ5TPN8OBKECNKQPB1EDQN4P949LIM2SRLE9IKORRECSTKKAACD5NIURRGCLN66PBEEDQN6BRJEHGN8SPF9LIM2SRLE9IKQOBG7C______0().record(startCtx);
        }
    }
}
