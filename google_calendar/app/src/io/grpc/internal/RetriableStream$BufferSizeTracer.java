// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ClientStreamTracer;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package io.grpc.internal:
//            RetriableStream

final class substream extends ClientStreamTracer
{

    private long bufferNeeded;
    private final imitExceeded substream;
    private final RetriableStream this$0;

    public final void outboundWireSize(long l)
    {
        if (state.tream == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        obj = null;
        synchronized (lock)
        {
            if (state.tream == null && !substream.substream)
            {
                break MISSING_BLOCK_LABEL_61;
            }
        }
        return;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        bufferNeeded = bufferNeeded + l;
        if (bufferNeeded > perRpcBufferUsed)
        {
            break MISSING_BLOCK_LABEL_90;
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
        if (bufferNeeded <= perRpcBufferLimit)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        substream.imitExceeded = true;
_L4:
        if (substream.imitExceeded)
        {
            obj = commit(substream);
        }
        obj1;
        JVM INSTR monitorexit ;
        if (obj == null) goto _L1; else goto _L3
_L3:
        ((Runnable) (obj)).run();
        return;
        r r = channelBufferUsed;
        l = bufferNeeded;
        long l1 = perRpcBufferUsed;
        l = r.bufferUsed.addAndGet(l - l1);
        perRpcBufferUsed = bufferNeeded;
        if (l > channelBufferLimit)
        {
            substream.imitExceeded = true;
        }
          goto _L4
    }

    r(r r)
    {
        this$0 = RetriableStream.this;
        super();
        substream = r;
    }
}
