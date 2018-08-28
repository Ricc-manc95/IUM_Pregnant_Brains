// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package io.grpc.internal:
//            ManagedClientTransport, LogId, ChannelExecutor, PickSubchannelArgsImpl, 
//            GrpcUtil, ClientTransport, FailingClientStream, DelayedStream, 
//            ClientStream

final class DelayedClientTransport
    implements ManagedClientTransport
{

    public final ChannelExecutor channelExecutor;
    private final Executor defaultAppExecutor;
    private io.grpc.LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    public ManagedClientTransport.Listener listener;
    public final Object lock = new Object();
    private final LogId lodId;
    public Collection pendingStreams;
    private Runnable reportTransportInUse;
    public Runnable reportTransportNotInUse;
    public Runnable reportTransportTerminated;
    public Status shutdownStatus;

    DelayedClientTransport(Executor executor, ChannelExecutor channelexecutor)
    {
        lodId = new LogId(getClass().getName(), LogId.idAlloc.incrementAndGet());
        pendingStreams = new LinkedHashSet();
        defaultAppExecutor = executor;
        channelExecutor = channelexecutor;
    }

    private final PendingStream createPendingStream(io.grpc.LoadBalancer.PickSubchannelArgs picksubchannelargs)
    {
        picksubchannelargs = new PendingStream(picksubchannelargs);
        pendingStreams.add(picksubchannelargs);
        if (getPendingStreamsCount() == 1)
        {
            channelExecutor.executeLater(reportTransportInUse);
        }
        return picksubchannelargs;
    }

    private final int getPendingStreamsCount()
    {
        int i;
        synchronized (lock)
        {
            i = pendingStreams.size();
        }
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final LogId getLogId()
    {
        return lodId;
    }

    public final boolean hasPendingStreams()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (!pendingStreams.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        metadata = new PickSubchannelArgsImpl(methoddescriptor, metadata, calloptions);
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (shutdownStatus != null) goto _L2; else goto _L1
_L1:
        if (lastPicker != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        methoddescriptor = createPendingStream(metadata);
        channelExecutor.drain();
        return methoddescriptor;
        long l;
        methoddescriptor = lastPicker;
        l = lastPickerVersion;
        obj;
        JVM INSTR monitorexit ;
_L6:
        methoddescriptor = GrpcUtil.getTransportFromPickResult(methoddescriptor.pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0(), calloptions.waitForReady);
        if (methoddescriptor == null) goto _L4; else goto _L3
_L3:
        methoddescriptor = methoddescriptor.newStream(metadata.getMethodDescriptor(), metadata.getHeaders(), metadata.getCallOptions());
        channelExecutor.drain();
        return methoddescriptor;
_L2:
        methoddescriptor = new FailingClientStream(shutdownStatus);
        obj;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return methoddescriptor;
        methoddescriptor;
        obj;
        JVM INSTR monitorexit ;
        throw methoddescriptor;
        methoddescriptor;
        channelExecutor.drain();
        throw methoddescriptor;
_L4:
label0:
        {
            synchronized (lock)
            {
                if (shutdownStatus == null)
                {
                    break label0;
                }
                methoddescriptor = new FailingClientStream(shutdownStatus);
            }
            channelExecutor.drain();
            return methoddescriptor;
        }
        if (l != lastPickerVersion)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        methoddescriptor = createPendingStream(metadata);
        obj1;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return methoddescriptor;
        methoddescriptor = lastPicker;
        l = lastPickerVersion;
        obj1;
        JVM INSTR monitorexit ;
        if (true) goto _L6; else goto _L5
_L5:
        methoddescriptor;
        obj1;
        JVM INSTR monitorexit ;
        throw methoddescriptor;
    }

    public final void ping(ClientTransport.PingCallback pingcallback, Executor executor)
    {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    final void reprocess(io.grpc.LoadBalancer.SubchannelPicker subchannelpicker)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        lastPicker = subchannelpicker;
        lastPickerVersion = lastPickerVersion + 1L;
        if (subchannelpicker == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        if (hasPendingStreams())
        {
            break MISSING_BLOCK_LABEL_36;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayList arraylist1 = new ArrayList(pendingStreams);
        obj;
        JVM INSTR monitorexit ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        arraylist1 = (ArrayList)arraylist1;
        int j = arraylist1.size();
        for (int i = 0; i < j; i++)
        {
            final PendingStream stream = (PendingStream)arraylist1.get(i);
            obj = subchannelpicker.pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0();
            CallOptions calloptions = stream.args.getCallOptions();
            final ClientTransport transport = GrpcUtil.getTransportFromPickResult(((io.grpc.LoadBalancer.PickResult) (obj)), calloptions.waitForReady);
            if (transport != null)
            {
                obj = defaultAppExecutor;
                if (calloptions.executor != null)
                {
                    obj = calloptions.executor;
                }
                ((Executor) (obj)).execute(new _cls5());
                arraylist.add(stream);
            }
        }

        break MISSING_BLOCK_LABEL_183;
        subchannelpicker;
        obj;
        JVM INSTR monitorexit ;
        throw subchannelpicker;
        synchronized (lock)
        {
            if (hasPendingStreams())
            {
                break MISSING_BLOCK_LABEL_205;
            }
        }
        return;
        exception;
        subchannelpicker;
        JVM INSTR monitorexit ;
        throw exception;
        pendingStreams.removeAll(arraylist);
        if (pendingStreams.isEmpty())
        {
            pendingStreams = new LinkedHashSet();
        }
        if (!hasPendingStreams())
        {
            channelExecutor.executeLater(reportTransportNotInUse);
            if (shutdownStatus != null && reportTransportTerminated != null)
            {
                channelExecutor.executeLater(reportTransportTerminated);
                reportTransportTerminated = null;
            }
        }
        subchannelpicker;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return;
    }

    public final void shutdown(final Status status)
    {
label0:
        {
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
        channelExecutor.executeLater(new _cls4());
        if (!hasPendingStreams() && reportTransportTerminated != null)
        {
            channelExecutor.executeLater(reportTransportTerminated);
            reportTransportTerminated = null;
        }
        obj;
        JVM INSTR monitorexit ;
        channelExecutor.drain();
        return;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    public final void shutdownNow(Status status)
    {
        Runnable runnable;
        shutdown(status);
        Collection collection;
        synchronized (lock)
        {
            collection = pendingStreams;
            runnable = reportTransportTerminated;
            reportTransportTerminated = null;
            if (!pendingStreams.isEmpty())
            {
                pendingStreams = Collections.emptyList();
            }
        }
        if (runnable == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        for (obj = collection.iterator(); ((Iterator) (obj)).hasNext(); ((PendingStream)((Iterator) (obj)).next()).cancel(status)) { }
        break MISSING_BLOCK_LABEL_91;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
        channelExecutor.executeLater(runnable).drain();
    }

    public final Runnable start(final ManagedClientTransport.Listener listener)
    {
        this.listener = listener;
        reportTransportInUse = new _cls1();
        reportTransportNotInUse = new _cls2();
        reportTransportTerminated = new _cls3();
        return null;
    }

    private class PendingStream extends DelayedStream
    {

        public final io.grpc.LoadBalancer.PickSubchannelArgs args;
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
            clienttransport = clienttransport.newStream(args.getMethodDescriptor(), args.getHeaders(), args.getCallOptions());
            context.detach(context1);
            setStream(clienttransport);
            return;
            clienttransport;
            context.detach(context1);
            throw clienttransport;
        }

        PendingStream(io.grpc.LoadBalancer.PickSubchannelArgs picksubchannelargs)
        {
            this$0 = DelayedClientTransport.this;
            super();
            args = picksubchannelargs;
        }
    }


    private class _cls5
        implements Runnable
    {

        private final PendingStream val$stream;
        private final ClientTransport val$transport;

        public final void run()
        {
            stream.createRealStream(transport);
        }

        _cls5()
        {
            stream = pendingstream;
            transport = clienttransport;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final DelayedClientTransport this$0;
        private final Status val$status;

        public final void run()
        {
            listener.transportShutdown(status);
        }

        _cls4()
        {
            this$0 = DelayedClientTransport.this;
            status = status1;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final ManagedClientTransport.Listener val$listener;

        public final void run()
        {
            listener.transportInUse(true);
        }

        _cls1()
        {
            listener = listener1;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final ManagedClientTransport.Listener val$listener;

        public final void run()
        {
            listener.transportInUse(false);
        }

        _cls2()
        {
            listener = listener1;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final ManagedClientTransport.Listener val$listener;

        public final void run()
        {
            listener.transportTerminated();
        }

        _cls3()
        {
            listener = listener1;
            super();
        }
    }

}
