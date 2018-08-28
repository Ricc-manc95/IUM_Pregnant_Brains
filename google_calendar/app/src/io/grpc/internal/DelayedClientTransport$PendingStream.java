// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Context;
import io.grpc.Status;
import java.util.Collection;

// Referenced classes of package io.grpc.internal:
//            DelayedStream, DelayedClientTransport, ChannelExecutor, ClientTransport

final class args extends DelayedStream
{

    public final io.grpc.ngStream.context args;
    private final Context context = Context.current();
    private final DelayedClientTransport this$0;

    public final void cancel(Status status)
    {
        super.cancel(status);
        status = ((Status) (lock));
        status;
        JVM INSTR monitorenter ;
        boolean flag;
        if (reportTransportTerminated == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        flag = pendingStreams.remove(this);
        if (hasPendingStreams() || !flag)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        channelExecutor.executeLater(reportTransportNotInUse);
        if (shutdownStatus != null)
        {
            channelExecutor.executeLater(reportTransportTerminated);
            reportTransportTerminated = null;
        }
        status;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return;
        Exception exception;
        exception;
        status;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void createRealStream(ClientTransport clienttransport)
    {
        Context context1 = context.attach();
        clienttransport = clienttransport.newStream(args.thodDescriptor(), args.aders(), args.llOptions());
        context.detach(context1);
        setStream(clienttransport);
        return;
        clienttransport;
        context.detach(context1);
        throw clienttransport;
    }

    (io.grpc.ngStream ngstream)
    {
        this$0 = DelayedClientTransport.this;
        super();
        args = ngstream;
    }
}
