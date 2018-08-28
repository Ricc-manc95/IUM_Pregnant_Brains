// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.BinaryLog;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Context;
import io.grpc.MethodDescriptor;
import io.grpc.PersistentHashArrayMappedTrie;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.unsafe.ContextUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;

// Referenced classes of package io.grpc.internal:
//            CensusTracingModule

final class this._cls0
    implements ClientInterceptor
{

    private final CensusTracingModule this$0;

    public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
    {
        CensusTracingModule censustracingmodule = CensusTracingModule.this;
        io.grpc.  = ContextUtils.CONTEXT_SPAN_KEY;
        Object obj = Context.current().keyValueEntries;
        class _cls1 extends io.grpc.ForwardingClientCall.SimpleForwardingClientCall
        {

            public final CensusTracingModule.ClientCallTracer val$tracerFactory;

            public final void start(io.grpc.ClientCall.Listener listener, Metadata metadata)
            {
                class _cls1 extends io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
                {

                    private final _cls1 this$2;

                    public final void onClose(Status status, Metadata metadata1)
                    {
                        CensusTracingModule.ClientCallTracer clientcalltracer = tracerFactory;
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

            _cls1(CensusTracingModule.ClientCallTracer clientcalltracer)
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
            .hashCode();
            obj = ((io.grpc.) (obj)).BMC4NMOOBECSNKUOJACLHN8EQ994KKOQJ1EPGIUR31DPJIUJR2D9IM6T1R0();
        }
        obj1 = obj;
        if (obj == null)
        {
            obj1 = .IUJR2D9IM6T1R0;
        }
        obj = new _cls6T1R0(censustracingmodule, (Span)obj1, methoddescriptor);
        return new _cls1(((_cls1) (obj)));
    }

    _cls1()
    {
        this$0 = CensusTracingModule.this;
        super();
    }
}
