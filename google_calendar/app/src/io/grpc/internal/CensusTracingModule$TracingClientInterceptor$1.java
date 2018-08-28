// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;

final class val.tracerFactory extends io.grpc.
{

    public final _cls1 val$tracerFactory;

    public final void start(io.grpc.ceptor._cls1 _pcls1, Metadata metadata)
    {
        class _cls1 extends io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
        {

            private final CensusTracingModule.TracingClientInterceptor._cls1 this$2;

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
                this$2 = CensusTracingModule.TracingClientInterceptor._cls1.this;
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
