// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.BinaryLog;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.PersistentHashArrayMappedTrie;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.BinaryFormat;
import io.opencensus.trace.unsafe.ContextUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

final class CensusTracingModule
{
    final class ClientCallTracer extends io.grpc.ClientStreamTracer.Factory
    {

        public volatile int callEnded;
        public final boolean isSampledToLocalTracing;
        public final Span span;
        private final CensusTracingModule this$0;

        public final ClientStreamTracer newClientStreamTracer$5166IRPFCTP70OPF8DGMOR2FE1Q6IRREECTKOQBF5TJN4S335T6MAT31CHGN8O9R5566IRPFCTP70OPF8DM6IPBEEH9N8SJ5C5ML8SJ1CDIN4EO_0(Metadata metadata)
        {
            metadata.discardAll(tracingHeader);
            metadata.put(tracingHeader, span.context);
            return new ClientTracer(span);
        }

        ClientCallTracer(Span span1, MethodDescriptor methoddescriptor)
        {
            this$0 = CensusTracingModule.this;
            super();
            if (methoddescriptor == null)
            {
                throw new NullPointerException(String.valueOf("method"));
            }
            isSampledToLocalTracing = methoddescriptor.sampledToLocalTracing;
            censustracingmodule = censusTracer.spanBuilderWithExplicitParent(CensusTracingModule.generateTraceSpanName(false, methoddescriptor.fullMethodName), span1);
            if (CensusTracingModule.this == null)
            {
                throw null;
            } else
            {
                span = startSpan();
                return;
            }
        }
    }

    static final class ClientTracer extends ClientStreamTracer
    {

        private final Span span;

        public final void inboundMessageRead(int i, long l, long l1)
        {
            CensusTracingModule.recordMessageEvent(span, io.opencensus.trace.MessageEvent.Type.RECEIVED, i, l, l1);
        }

        public final void outboundMessageSent(int i, long l, long l1)
        {
            CensusTracingModule.recordMessageEvent(span, io.opencensus.trace.MessageEvent.Type.SENT, i, l, l1);
        }

        ClientTracer(Span span1)
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

    final class ServerTracer extends ServerStreamTracer
    {

        public volatile int streamClosed;

        public final void inboundMessageRead(int i, long l, long l1)
        {
            throw new NoSuchMethodError();
        }

        public final void outboundMessageSent(int i, long l, long l1)
        {
            throw new NoSuchMethodError();
        }

        public final void streamClosed$5166IRPFCTP70OPFADQ62T3LECTIILG_0()
        {
            throw new NoSuchMethodError();
        }
    }

    final class ServerTracerFactory extends io.grpc.ServerStreamTracer.Factory
    {

        ServerTracerFactory()
        {
        }
    }

    final class TracingClientInterceptor
        implements ClientInterceptor
    {

        private final CensusTracingModule this$0;

        public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
        {
            CensusTracingModule censustracingmodule = CensusTracingModule.this;
            io.grpc.Context.Key key = ContextUtils.CONTEXT_SPAN_KEY;
            Object obj = Context.current().keyValueEntries;
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
                            if (CensusTracingModule.callEndedUpdater == null) goto _L2; else goto _L1
_L1:
                            if (CensusTracingModule.callEndedUpdater.getAndSet(clientcalltracer, 1) == 0) goto _L4; else goto _L3
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
                            clientcalltracer.span.end(CensusTracingModule.createEndSpanOptions(status, clientcalltracer.isSampledToLocalTracing));
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

            Object obj1;
            if (((PersistentHashArrayMappedTrie) (obj)).root == null)
            {
                obj = null;
            } else
            {
                obj = ((PersistentHashArrayMappedTrie) (obj)).root;
                key.hashCode();
                obj = ((io.grpc.PersistentHashArrayMappedTrie.Node) (obj)).get$5166KOBMC4NMOOBECSNKUOJACLHN8EQ994KKOQJ1EPGIUR31DPJIUJR2D9IM6T1R0();
            }
            obj1 = obj;
            if (obj == null)
            {
                obj1 = key.defaultValue;
            }
            obj = censustracingmodule. new ClientCallTracer((Span)obj1, methoddescriptor);
            return new _cls1(((ClientCallTracer) (obj)));
        }

        TracingClientInterceptor()
        {
            this$0 = CensusTracingModule.this;
            super();
        }
    }


    public static final AtomicIntegerFieldUpdater callEndedUpdater;
    public static final Logger logger;
    private static final AtomicIntegerFieldUpdater streamClosedUpdater;
    public final Tracer censusTracer;
    public final TracingClientInterceptor clientInterceptor = new TracingClientInterceptor();
    public final io.grpc.Metadata.Key tracingHeader;

    CensusTracingModule(Tracer tracer, final BinaryFormat censusPropagationBinaryFormat)
    {
        new ServerTracerFactory();
        if (tracer == null)
        {
            throw new NullPointerException(String.valueOf("censusTracer"));
        }
        censusTracer = (Tracer)tracer;
        if (censusPropagationBinaryFormat == null)
        {
            throw new NullPointerException(String.valueOf("censusPropagationBinaryFormat"));
        } else
        {
            tracingHeader = io.grpc.Metadata.Key.of("grpc-trace-bin", new _cls1());
            return;
        }
    }

    static EndSpanOptions createEndSpanOptions(Status status, boolean flag)
    {
        io.opencensus.trace.EndSpanOptions.Builder builder = EndSpanOptions.builder();
        status.code.ordinal();
        JVM INSTR tableswitch 0 16: default 96
    //                   0 141
    //                   1 196
    //                   2 203
    //                   3 210
    //                   4 217
    //                   5 224
    //                   6 231
    //                   7 238
    //                   8 245
    //                   9 252
    //                   10 259
    //                   11 266
    //                   12 273
    //                   13 280
    //                   14 287
    //                   15 294
    //                   16 301;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L18:
        break MISSING_BLOCK_LABEL_301;
_L1:
        status = String.valueOf(status.code);
        throw new AssertionError((new StringBuilder(String.valueOf(status).length() + 22)).append("Unhandled status code ").append(status).toString());
_L2:
        io.opencensus.trace.Status status1 = io.opencensus.trace.Status.OK;
_L19:
        Object obj = status1;
        if (status.description != null)
        {
            status = status.description;
            obj = status1.description;
            boolean flag1;
            if (obj == null)
            {
                if (status == null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            } else
            {
                flag1 = obj.equals(status);
            }
            if (flag1)
            {
                obj = status1;
            } else
            {
                obj = new io.opencensus.trace.Status(status1.canonicalCode, status);
            }
        }
        return builder.setStatus(((io.opencensus.trace.Status) (obj))).setSampleToLocalSpanStore(flag).build();
_L3:
        status1 = io.opencensus.trace.Status.CANCELLED;
          goto _L19
_L4:
        status1 = io.opencensus.trace.Status.UNKNOWN;
          goto _L19
_L5:
        status1 = io.opencensus.trace.Status.INVALID_ARGUMENT;
          goto _L19
_L6:
        status1 = io.opencensus.trace.Status.DEADLINE_EXCEEDED;
          goto _L19
_L7:
        status1 = io.opencensus.trace.Status.NOT_FOUND;
          goto _L19
_L8:
        status1 = io.opencensus.trace.Status.ALREADY_EXISTS;
          goto _L19
_L9:
        status1 = io.opencensus.trace.Status.PERMISSION_DENIED;
          goto _L19
_L10:
        status1 = io.opencensus.trace.Status.RESOURCE_EXHAUSTED;
          goto _L19
_L11:
        status1 = io.opencensus.trace.Status.FAILED_PRECONDITION;
          goto _L19
_L12:
        status1 = io.opencensus.trace.Status.ABORTED;
          goto _L19
_L13:
        status1 = io.opencensus.trace.Status.OUT_OF_RANGE;
          goto _L19
_L14:
        status1 = io.opencensus.trace.Status.UNIMPLEMENTED;
          goto _L19
_L15:
        status1 = io.opencensus.trace.Status.INTERNAL;
          goto _L19
_L16:
        status1 = io.opencensus.trace.Status.UNAVAILABLE;
          goto _L19
_L17:
        status1 = io.opencensus.trace.Status.DATA_LOSS;
          goto _L19
        status1 = io.opencensus.trace.Status.UNAUTHENTICATED;
          goto _L19
    }

    static String generateTraceSpanName(boolean flag, String s)
    {
        s = s.replace('/', '.');
        return (new StringBuilder(String.valueOf("Sent").length() + 1 + String.valueOf(s).length())).append("Sent").append(".").append(s).toString();
    }

    static void recordMessageEvent(Span span, io.opencensus.trace.MessageEvent.Type type, int i, long l, long l1)
    {
        long l2 = i;
        io.opencensus.trace.AutoValue_MessageEvent.Builder builder = new io.opencensus.trace.AutoValue_MessageEvent.Builder();
        if (type == null)
        {
            throw new NullPointerException("type");
        }
        type = builder.setType((io.opencensus.trace.MessageEvent.Type)type).setMessageId(l2).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
        if (l1 != -1L)
        {
            type.setUncompressedMessageSize(l1);
        }
        if (l != -1L)
        {
            type.setCompressedMessageSize(l);
        }
        span.addMessageEvent(type.build());
    }

    static 
    {
        AtomicIntegerFieldUpdater atomicintegerfieldupdater1;
        atomicintegerfieldupdater1 = null;
        logger = Logger.getLogger(io/grpc/internal/CensusTracingModule.getName());
        AtomicIntegerFieldUpdater atomicintegerfieldupdater;
        AtomicIntegerFieldUpdater atomicintegerfieldupdater2;
        atomicintegerfieldupdater2 = AtomicIntegerFieldUpdater.newUpdater(io/grpc/internal/CensusTracingModule$ClientCallTracer, "callEnded");
        atomicintegerfieldupdater = AtomicIntegerFieldUpdater.newUpdater(io/grpc/internal/CensusTracingModule$ServerTracer, "streamClosed");
        atomicintegerfieldupdater1 = atomicintegerfieldupdater2;
_L2:
        callEndedUpdater = atomicintegerfieldupdater1;
        streamClosedUpdater = atomicintegerfieldupdater;
        return;
        Throwable throwable;
        throwable;
        logger.logp(Level.SEVERE, "io.grpc.internal.CensusTracingModule", "<clinit>", "Creating atomic field updaters failed", throwable);
        throwable = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private class _cls1
        implements io.grpc.Metadata.BinaryMarshaller
    {

        private final BinaryFormat val$censusPropagationBinaryFormat;

        private final SpanContext parseBytes(byte abyte0[])
        {
            try
            {
                abyte0 = censusPropagationBinaryFormat.fromByteArray(abyte0);
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                CensusTracingModule.logger.logp(Level.FINE, "io.grpc.internal.CensusTracingModule$1", "parseBytes", "Failed to parse tracing header", abyte0);
                return SpanContext.INVALID;
            }
            return abyte0;
        }

        public final volatile Object parseBytes(byte abyte0[])
        {
            return parseBytes(abyte0);
        }

        public final byte[] toBytes(Object obj)
        {
            obj = (SpanContext)obj;
            return censusPropagationBinaryFormat.toByteArray(((SpanContext) (obj)));
        }

        _cls1()
        {
            censusPropagationBinaryFormat = binaryformat;
            super();
        }
    }

}
