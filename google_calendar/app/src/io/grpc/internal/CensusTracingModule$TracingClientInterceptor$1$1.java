// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.Status;
import io.opencensus.trace.Span;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

// Referenced classes of package io.grpc.internal:
//            CensusTracingModule

final class tener extends io.grpc.ntCallListener
{

    private final ocalTracing this$2;

    public final void onClose(Status status, Metadata metadata)
    {
        tener tener = tracerFactory;
        if (CensusTracingModule.callEndedUpdater == null) goto _L2; else goto _L1
_L1:
        if (CensusTracingModule.callEndedUpdater.getAndSet(tener, 1) == 0) goto _L4; else goto _L3
_L3:
        super.onClose(status, metadata);
        return;
_L2:
        if (tener.tracerFactory != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        tener.tracerFactory = 1;
_L4:
        tener.tracerFactory.end(CensusTracingModule.createEndSpanOptions(status, tener.ocalTracing));
        if (true) goto _L3; else goto _L5
_L5:
    }

    tener(io.grpc.ptor._cls1 _pcls1)
    {
        this$2 = this._cls2.this;
        super(_pcls1);
    }
}
