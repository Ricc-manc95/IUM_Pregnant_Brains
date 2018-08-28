// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;

final class t> extends io.grpc.Call
{

    public final _cls1 val$tracerFactory;

    public final void start(io.grpc.ceptor._cls1 _pcls1, Metadata metadata)
    {
        class _cls1 extends io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
        {

            private final CensusStatsModule.StatsClientInterceptor._cls1 this$2;

            public final void onClose(Status status, Metadata metadata1)
            {
                CensusStatsModule.ClientCallTracer clientcalltracer = tracerFactory;
                if (CensusStatsModule.ClientCallTracer.callEndedUpdater == null) goto _L2; else goto _L1
_L1:
                if (CensusStatsModule.ClientCallTracer.callEndedUpdater.getAndSet(clientcalltracer, 1) == 0) goto _L4; else goto _L3
_L3:
                super.onClose(status, metadata1);
                return;
_L2:
                if (clientcalltracer.callEnded != 0)
                {
                    continue; /* Loop/switch isn't completed */
                }
                clientcalltracer.callEnded = 1;
_L4:
                if (clientcalltracer.recordFinishedRpcs)
                {
                    Object obj = clientcalltracer.stopwatch;
                    long l = ((Stopwatch) (obj)).ticker.read();
                    if (!((Stopwatch) (obj)).isRunning)
                    {
                        throw new IllegalStateException(String.valueOf("This stopwatch is already stopped."));
                    }
                    obj.isRunning = false;
                    long l1 = ((Stopwatch) (obj)).elapsedNanos;
                    obj.elapsedNanos = (l - ((Stopwatch) (obj)).startTick) + l1;
                    clientcalltracer.stopwatch.elapsed(TimeUnit.NANOSECONDS);
                    Object obj1 = clientcalltracer.streamTracer;
                    obj = obj1;
                    if (obj1 == null)
                    {
                        obj = CensusStatsModule.BLANK_CLIENT_TRACER;
                    }
                    obj1 = clientcalltracer.module.statsRecorder.newMeasureMap().put$5166IRPFDTO6ARJ3CLN76TBJ5TPN8OBKECNKQPB1EDQN4P949LIM2SRLE9IKORRECSTKKAACD5NIURRGCLN66PBEEDQN6BRJEHGN8SPF9LIM2SRLE9IKQOBG7C______0();
                    double d = CensusStatsModule.NANOS_PER_MILLI;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).outboundMessageCount;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).inboundMessageCount;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).outboundWireSize;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).inboundWireSize;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).outboundUncompressedSize;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    l = ((CensusStatsModule.ClientTracer) (obj)).inboundUncompressedSize;
                    if (obj1 == null)
                    {
                        throw null;
                    }
                    boolean flag;
                    if (io.grpc.Status.Code.OK == status.code)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag && obj1 == null)
                    {
                        throw null;
                    }
                    ((MeasureMap) (obj1)).record(clientcalltracer.module.tagger.toBuilder(clientcalltracer.startCtx).put(RpcMeasureConstants.RPC_STATUS, TagValue.create(status.code.toString())).build());
                }
                if (true) goto _L3; else goto _L5
_L5:
            }

            _cls1(io.grpc.ClientCall.Listener listener)
            {
                this$2 = CensusStatsModule.StatsClientInterceptor._cls1.this;
                super(listener);
            }
        }

        _mthdelegate().start(new _cls1(_pcls1), metadata);
    }

    _cls1(_cls1 _pcls1)
    {
        val$tracerFactory = _pcls1;
        super(final_clientcall);
    }
}
