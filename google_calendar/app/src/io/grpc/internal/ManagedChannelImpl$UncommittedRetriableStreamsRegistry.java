// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;
import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, DelayedClientTransport, RetriableStream

final class uncommittedRetriableStreams
{

    public final Object lock = new Object();
    public Status shutdownStatus;
    public final ManagedChannelImpl this$0;
    public Collection uncommittedRetriableStreams;

    final Status add(RetriableStream retriablestream)
    {
label0:
        {
            synchronized (lock)
            {
                if (shutdownStatus == null)
                {
                    break label0;
                }
                retriablestream = shutdownStatus;
            }
            return retriablestream;
        }
        uncommittedRetriableStreams.add(retriablestream);
        obj;
        JVM INSTR monitorexit ;
        return null;
        retriablestream;
        obj;
        JVM INSTR monitorexit ;
        throw retriablestream;
    }

    final void onShutdown(Status status)
    {
        boolean flag;
label0:
        {
            flag = false;
            synchronized (lock)
            {
                if (shutdownStatus == null)
                {
                    break label0;
                }
            }
            return;
        }
        shutdownStatus = status;
        if (uncommittedRetriableStreams.isEmpty())
        {
            flag = true;
        }
        obj;
        JVM INSTR monitorexit ;
        if (flag)
        {
            delayedTransport.shutdown(status);
            return;
        } else
        {
            return;
        }
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    ()
    {
        this$0 = ManagedChannelImpl.this;
        super();
        uncommittedRetriableStreams = new HashSet();
    }
}
