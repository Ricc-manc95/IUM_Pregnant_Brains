// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.opencensus.contrib.grpc.metrics.RpcMeasureConstants;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsComponent;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.tags.TagsComponent;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CensusStatsModule
{
    static final class ClientCallTracer extends io.grpc.ClientStreamTracer.Factory
    {

        public static final AtomicIntegerFieldUpdater callEndedUpdater;
        private static final AtomicReferenceFieldUpdater streamTracerUpdater;
        public volatile int callEnded;
        public final CensusStatsModule module;
        private final TagContext parentCtx;
        public final boolean recordFinishedRpcs;
        public final TagContext startCtx;
        public final Stopwatch stopwatch;
        public volatile ClientTracer streamTracer;

        public final ClientStreamTracer newClientStreamTracer$5166IRPFCTP70OPF8DGMOR2FE1Q6IRREECTKOQBF5TJN4S335T6MAT31CHGN8O9R5566IRPFCTP70OPF8DM6IPBEEH9N8SJ5C5ML8SJ1CDIN4EO_0(Metadata metadata)
        {
            ClientTracer clienttracer = new ClientTracer();
            if (streamTracerUpdater != null)
            {
                if (!streamTracerUpdater.compareAndSet(this, null, clienttracer))
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
                streamTracer = clienttracer;
            }
            if (module.propagateTags)
            {
                metadata.discardAll(module.statsHeader);
                if (!module.tagger.empty().equals(parentCtx))
                {
                    metadata.put(module.statsHeader, parentCtx);
                }
            }
            return clienttracer;
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

        ClientCallTracer(CensusStatsModule censusstatsmodule, TagContext tagcontext, String s, boolean flag, boolean flag1)
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

    static final class ClientTracer extends ClientStreamTracer
    {

        private static final AtomicLongFieldUpdater inboundMessageCountUpdater;
        private static final AtomicLongFieldUpdater inboundUncompressedSizeUpdater;
        private static final AtomicLongFieldUpdater inboundWireSizeUpdater;
        private static final AtomicLongFieldUpdater outboundMessageCountUpdater;
        private static final AtomicLongFieldUpdater outboundUncompressedSizeUpdater;
        private static final AtomicLongFieldUpdater outboundWireSizeUpdater;
        public volatile long inboundMessageCount;
        public volatile long inboundUncompressedSize;
        public volatile long inboundWireSize;
        public volatile long outboundMessageCount;
        public volatile long outboundUncompressedSize;
        public volatile long outboundWireSize;

        public final void inboundMessage$514IILG_0()
        {
            if (inboundMessageCountUpdater != null)
            {
                inboundMessageCountUpdater.getAndIncrement(this);
                return;
            } else
            {
                inboundMessageCount = inboundMessageCount + 1L;
                return;
            }
        }

        public final void inboundUncompressedSize(long l)
        {
            if (inboundUncompressedSizeUpdater != null)
            {
                inboundUncompressedSizeUpdater.getAndAdd(this, l);
                return;
            } else
            {
                inboundUncompressedSize = inboundUncompressedSize + l;
                return;
            }
        }

        public final void inboundWireSize(long l)
        {
            if (inboundWireSizeUpdater != null)
            {
                inboundWireSizeUpdater.getAndAdd(this, l);
                return;
            } else
            {
                inboundWireSize = inboundWireSize + l;
                return;
            }
        }

        public final void outboundMessage$514IILG_0()
        {
            if (outboundMessageCountUpdater != null)
            {
                outboundMessageCountUpdater.getAndIncrement(this);
                return;
            } else
            {
                outboundMessageCount = outboundMessageCount + 1L;
                return;
            }
        }

        public final void outboundUncompressedSize(long l)
        {
            if (outboundUncompressedSizeUpdater != null)
            {
                outboundUncompressedSizeUpdater.getAndAdd(this, l);
                return;
            } else
            {
                outboundUncompressedSize = outboundUncompressedSize + l;
                return;
            }
        }

        public final void outboundWireSize(long l)
        {
            if (outboundWireSizeUpdater != null)
            {
                outboundWireSizeUpdater.getAndAdd(this, l);
                return;
            } else
            {
                outboundWireSize = outboundWireSize + l;
                return;
            }
        }

        static 
        {
            AtomicLongFieldUpdater atomiclongfieldupdater5 = null;
            AtomicLongFieldUpdater atomiclongfieldupdater;
            AtomicLongFieldUpdater atomiclongfieldupdater1;
            AtomicLongFieldUpdater atomiclongfieldupdater2;
            AtomicLongFieldUpdater atomiclongfieldupdater3;
            AtomicLongFieldUpdater atomiclongfieldupdater4;
            AtomicLongFieldUpdater atomiclongfieldupdater6;
            atomiclongfieldupdater4 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundMessageCount");
            atomiclongfieldupdater3 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundMessageCount");
            atomiclongfieldupdater2 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundWireSize");
            atomiclongfieldupdater1 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundWireSize");
            atomiclongfieldupdater6 = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "outboundUncompressedSize");
            atomiclongfieldupdater = AtomicLongFieldUpdater.newUpdater(io/grpc/internal/CensusStatsModule$ClientTracer, "inboundUncompressedSize");
            atomiclongfieldupdater5 = atomiclongfieldupdater6;
_L2:
            outboundMessageCountUpdater = atomiclongfieldupdater4;
            inboundMessageCountUpdater = atomiclongfieldupdater3;
            outboundWireSizeUpdater = atomiclongfieldupdater2;
            inboundWireSizeUpdater = atomiclongfieldupdater1;
            outboundUncompressedSizeUpdater = atomiclongfieldupdater5;
            inboundUncompressedSizeUpdater = atomiclongfieldupdater;
            return;
            Throwable throwable;
            throwable;
            CensusStatsModule.logger.logp(Level.SEVERE, "io.grpc.internal.CensusStatsModule$ClientTracer", "<clinit>", "Creating atomic field updaters failed", throwable);
            throwable = null;
            atomiclongfieldupdater1 = null;
            atomiclongfieldupdater2 = null;
            atomiclongfieldupdater3 = null;
            atomiclongfieldupdater4 = null;
            if (true) goto _L2; else goto _L1
_L1:
        }

        ClientTracer()
        {
        }
    }

    final class StatsClientInterceptor
        implements ClientInterceptor
    {

        private final boolean recordFinishedRpcs;
        private final boolean recordStartedRpcs;
        private final CensusStatsModule this$0;

        public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
        {
            Object obj = tagger.getCurrentTagContext();
            obj = new ClientCallTracer(CensusStatsModule.this, ((TagContext) (obj)), methoddescriptor.fullMethodName, recordStartedRpcs, recordFinishedRpcs);
            class _cls1 extends io.grpc.ForwardingClientCall.SimpleForwardingClientCall
            {

                public final ClientCallTracer val$tracerFactory;

                public final void start(io.grpc.ClientCall.Listener listener, Metadata metadata)
                {
                    class _cls1 extends io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
                    {

                        private final _cls1 this$2;

                        public final void onClose(Status status, Metadata metadata1)
                        {
                            ClientCallTracer clientcalltracer = tracerFactory;
                            if (ClientCallTracer.callEndedUpdater == null) goto _L2; else goto _L1
_L1:
                            if (ClientCallTracer.callEndedUpdater.getAndSet(clientcalltracer, 1) == 0) goto _L4; else goto _L3
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
                                l = ((ClientTracer) (obj1)).outboundMessageCount;
                                if (obj2 == null)
                                {
                                    throw null;
                                }
                                l = ((ClientTracer) (obj1)).inboundMessageCount;
                                if (obj2 == null)
                                {
                                    throw null;
                                }
                                l = ((ClientTracer) (obj1)).outboundWireSize;
                                if (obj2 == null)
                                {
                                    throw null;
                                }
                                l = ((ClientTracer) (obj1)).inboundWireSize;
                                if (obj2 == null)
                                {
                                    throw null;
                                }
                                l = ((ClientTracer) (obj1)).outboundUncompressedSize;
                                if (obj2 == null)
                                {
                                    throw null;
                                }
                                l = ((ClientTracer) (obj1)).inboundUncompressedSize;
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

                _cls1(ClientCallTracer clientcalltracer)
                {
                    tracerFactory = clientcalltracer;
                    super(final_clientcall);
                }
            }

            return new _cls1(((ClientCallTracer) (obj)));
        }

        StatsClientInterceptor(boolean flag, boolean flag1)
        {
            this$0 = CensusStatsModule.this;
            super();
            recordStartedRpcs = flag;
            recordFinishedRpcs = flag1;
        }
    }


    public static final ClientTracer BLANK_CLIENT_TRACER = new ClientTracer();
    public static final double NANOS_PER_MILLI;
    public static final Logger logger = Logger.getLogger(io/grpc/internal/CensusStatsModule.getName());
    public final boolean propagateTags;
    public final io.grpc.Metadata.Key statsHeader;
    public final StatsRecorder statsRecorder;
    public final Supplier stopwatchSupplier;
    public final Tagger tagger;

    CensusStatsModule(Supplier supplier, boolean flag)
    {
        this(Tags.tagsComponent.getTagger(), Tags.tagsComponent.getTagPropagationComponent().getBinarySerializer(), Stats.statsComponent.getStatsRecorder(), supplier, true);
    }

    private CensusStatsModule(final Tagger tagger, final TagContextBinarySerializer tagCtxSerializer, StatsRecorder statsrecorder, Supplier supplier, boolean flag)
    {
        if (tagger == null)
        {
            throw new NullPointerException(String.valueOf("tagger"));
        }
        this.tagger = (Tagger)tagger;
        if (statsrecorder == null)
        {
            throw new NullPointerException(String.valueOf("statsRecorder"));
        }
        statsRecorder = (StatsRecorder)statsrecorder;
        if (tagCtxSerializer == null)
        {
            throw new NullPointerException(String.valueOf("tagCtxSerializer"));
        }
        if (supplier == null)
        {
            throw new NullPointerException(String.valueOf("stopwatchSupplier"));
        } else
        {
            stopwatchSupplier = (Supplier)supplier;
            propagateTags = flag;
            statsHeader = io.grpc.Metadata.Key.of("grpc-tags-bin", new _cls1());
            return;
        }
    }

    static 
    {
        NANOS_PER_MILLI = TimeUnit.MILLISECONDS.toNanos(1L);
    }

    private class _cls1
        implements io.grpc.Metadata.BinaryMarshaller
    {

        private final TagContextBinarySerializer val$tagCtxSerializer;
        private final Tagger val$tagger;

        private final TagContext parseBytes(byte abyte0[])
        {
            try
            {
                abyte0 = tagCtxSerializer.fromByteArray(abyte0);
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                CensusStatsModule.logger.logp(Level.FINE, "io.grpc.internal.CensusStatsModule$1", "parseBytes", "Failed to parse stats header", abyte0);
                return tagger.empty();
            }
            return abyte0;
        }

        private final byte[] toBytes(TagContext tagcontext)
        {
            try
            {
                tagcontext = tagCtxSerializer.toByteArray(tagcontext);
            }
            // Misplaced declaration of an exception variable
            catch (TagContext tagcontext)
            {
                throw new RuntimeException(tagcontext);
            }
            return tagcontext;
        }

        public final volatile Object parseBytes(byte abyte0[])
        {
            return parseBytes(abyte0);
        }

        public final volatile byte[] toBytes(Object obj)
        {
            return toBytes((TagContext)obj);
        }

        _cls1()
        {
            tagCtxSerializer = tagcontextbinaryserializer;
            tagger = tagger1;
            super();
        }
    }

}
