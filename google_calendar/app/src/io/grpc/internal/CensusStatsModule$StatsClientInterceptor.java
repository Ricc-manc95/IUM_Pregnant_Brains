// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import io.opencensus.tags.Tagger;

// Referenced classes of package io.grpc.internal:
//            CensusStatsModule

final class recordFinishedRpcs
    implements ClientInterceptor
{

    private final boolean recordFinishedRpcs;
    private final boolean recordStartedRpcs;
    private final CensusStatsModule this$0;

    public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
    {
        Object obj = tagger.getCurrentTagContext();
        obj = new (CensusStatsModule.this, ((io.opencensus.tags.TagContext) (obj)), methoddescriptor.fullMethodName, recordStartedRpcs, recordFinishedRpcs);
        class _cls1 extends io.grpc.ForwardingClientCall.SimpleForwardingClientCall
        {

            public final CensusStatsModule.ClientCallTracer val$tracerFactory;

            public final void start(io.grpc.ClientCall.Listener listener, Metadata metadata)
            {
                class _cls1 extends io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
                {

                    private final _cls1 this$2;

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
                            Object obj1 = clientcalltracer.stopwatch;
                            long l = ((Stopwatch) (obj1)).ticker.read();
                            if (!((Stopwatch) (obj1)).isRunning)
                            {
                                throw new IllegalStateException(String.valueOf("This stopwatch is already stopped."));
                            }
                            obj1.isRunning = false;
                            long l1 = ((Stopwatch) (obj1)).elapsedNanos;
                            obj1.elapsedNanos = (l - ((Stopwatch) (obj1)).startTick) + l1;
                            clientcalltracer.stopwatch.elapsed(TimeUnit.NANOSECONDS);
                            Object obj2 = clientcalltracer.streamTracer;
                            obj1 = obj2;
                            if (obj2 == null)
                            {
                                obj1 = CensusStatsModule.BLANK_CLIENT_TRACER;
                            }
                            obj2 = clientcalltracer.module.statsRecorder.newMeasureMap().put$5166IRPFDTO6ARJ3CLN76TBJ5TPN8OBKECNKQPB1EDQN4P949LIM2SRLE9IKORRECSTKKAACD5NIURRGCLN66PBEEDQN6BRJEHGN8SPF9LIM2SRLE9IKQOBG7C______0();
                            double d = CensusStatsModule.NANOS_PER_MILLI;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).outboundMessageCount;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).inboundMessageCount;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).outboundWireSize;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).inboundWireSize;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).outboundUncompressedSize;
                            if (obj2 == null)
                            {
                                throw null;
                            }
                            l = ((CensusStatsModule.ClientTracer) (obj1)).inboundUncompressedSize;
                            if (obj2 == null)
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
                            if (!flag && obj2 == null)
                            {
                                throw null;
                            }
                            ((MeasureMap) (obj2)).record(clientcalltracer.module.tagger.toBuilder(clientcalltracer.startCtx).put(RpcMeasureConstants.RPC_STATUS, TagValue.create(status.code.toString())).build());
                        }
                        if (true) goto _L3; else goto _L5
_L5:
                    }

                        _cls1(io.grpc.ClientCall.Listener listener)
                        {
                            this$2 = _cls1.this;
                            super(listener);
                        }
                }

                _mthdelegate().start(new _cls1(listener), metadata);
            }

            _cls1(CensusStatsModule.ClientCallTracer clientcalltracer)
            {
                tracerFactory = clientcalltracer;
                super(final_clientcall);
            }
        }

        return new _cls1(((_cls1) (obj)));
    }

    _cls1(boolean flag, boolean flag1)
    {
        this$0 = CensusStatsModule.this;
        super();
        recordStartedRpcs = flag;
        recordFinishedRpcs = flag1;
    }
}
