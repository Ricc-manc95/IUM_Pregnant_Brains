// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, ChannelExecutor, GrpcUtil, ClientTransportFactory, 
//            ServiceConfigInterceptor, ClientTransport, RetriableStream

final class this._cls0
    implements TransportProvider
{

    public final ManagedChannelImpl this$0;

    public final ClientTransport get(io.grpc.hannelArgs hannelargs)
    {
        Object obj = subchannelPicker;
        if (shutdown.get())
        {
            hannelargs = delayedTransport;
        } else
        {
            if (obj == null)
            {
                class _cls1
                    implements Runnable
                {

                    private final ManagedChannelImpl._cls5 this$1;

                    public final void run()
                    {
                        exitIdleMode();
                    }

            _cls1()
            {
                this$1 = ManagedChannelImpl._cls5.this;
                super();
            }
                }

                channelExecutor.executeLater(new _cls1()).drain();
                return delayedTransport;
            }
            obj = GrpcUtil.getTransportFromPickResult(((io.grpc.elPicker) (obj))._mth5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0(), hannelargs.getCallOptions().waitForReady);
            hannelargs = ((io.grpc.hannelArgs) (obj));
            if (obj == null)
            {
                return delayedTransport;
            }
        }
        return hannelargs;
    }

    public final RetriableStream newRetriableStream(final MethodDescriptor final_methoddescriptor, CallOptions calloptions, final Metadata final_metadata, Context context)
    {
        Executor executor;
        Object obj;
        final elBufferMeter final_channelbuffermeter;
        Object obj1;
        int i;
        long l;
        long l1;
        if (!retryEnabled)
        {
            throw new IllegalStateException(String.valueOf("retry should be enabled"));
        }
        final_channelbuffermeter = channelBufferUsed;
        l = perRpcBufferLimit;
        l1 = channelBufferLimit;
        obj1 = ManagedChannelImpl.this;
        obj = calloptions.executor;
        executor = ((Executor) (obj));
        if (obj == null)
        {
            executor = ((ManagedChannelImpl) (obj1)).executor;
        }
        obj1 = transportFactory.getScheduledExecutorService();
        obj = ServiceConfigInterceptor.RETRY_POLICY_KEY;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("key"));
        }
        i = 0;
_L3:
        if (i >= calloptions.customOptions.length)
        {
            break MISSING_BLOCK_LABEL_202;
        }
        if (!obj.equals(calloptions.customOptions[i][0])) goto _L2; else goto _L1
_L1:
        obj = calloptions.customOptions[i][1];
_L4:
        class _cls2 extends RetriableStream
        {

            private final ManagedChannelImpl._cls5 this$1;
            private final CallOptions val$callOptions;
            private final Context val$context;
            private final MethodDescriptor val$method;

            final ClientStream newSubstream(io.grpc.ClientStreamTracer.Factory factory, Metadata metadata)
            {
                CallOptions calloptions1;
                ClientTransport clienttransport;
                calloptions1 = callOptions.withStreamTracerFactory(factory);
                clienttransport = get(new PickSubchannelArgsImpl(method, metadata, calloptions1));
                factory = context.attach();
                metadata = clienttransport.newStream(method, metadata, calloptions1);
                context.detach(factory);
                return metadata;
                metadata;
                context.detach(factory);
                throw metadata;
            }

            final void postCommit()
            {
                ManagedChannelImpl.UncommittedRetriableStreamsRegistry uncommittedretriablestreamsregistry = uncommittedRetriableStreamsRegistry;
                Status status = null;
                synchronized (uncommittedretriablestreamsregistry.lock)
                {
                    uncommittedretriablestreamsregistry.uncommittedRetriableStreams.remove(this);
                    if (uncommittedretriablestreamsregistry.uncommittedRetriableStreams.isEmpty())
                    {
                        status = uncommittedretriablestreamsregistry.shutdownStatus;
                        uncommittedretriablestreamsregistry.uncommittedRetriableStreams = new HashSet();
                    }
                }
                if (status != null)
                {
                    uncommittedretriablestreamsregistry.this$0.delayedTransport.shutdown(status);
                }
                return;
                exception;
                obj2;
                JVM INSTR monitorexit ;
                throw exception;
            }

            final Status prestart()
            {
                return uncommittedRetriableStreamsRegistry.add(this);
            }

            _cls2(long l, long l1, Executor executor, ScheduledExecutorService scheduledexecutorservice, RetryPolicy.Provider provider, RetriableStream.Throttle throttle, CallOptions calloptions, MethodDescriptor methoddescriptor1, 
                    Context context1)
            {
                this$1 = ManagedChannelImpl._cls5.this;
                callOptions = calloptions;
                method = methoddescriptor1;
                context = context1;
                super(final_methoddescriptor, final_metadata, final_channelbuffermeter, l, l1, executor, scheduledexecutorservice, provider, throttle);
            }
        }

        return new _cls2(l, l1, executor, ((ScheduledExecutorService) (obj1)), (Service)obj, throttle, calloptions, final_methoddescriptor, context);
_L2:
        i++;
          goto _L3
        obj = ((io.grpc.eam.Throttle) (obj)).ltValue;
          goto _L4
    }

    s()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
