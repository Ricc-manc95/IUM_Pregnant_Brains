// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            SerializeReentrantCallsDirectExecutor, SerializingExecutor, ClientStream, RetriableStream, 
//            NoopClientStream, GrpcUtil, CallTracer, LongCounter, 
//            TimeProvider, LogExceptionRunnable, PickSubchannelArgsImpl, ClientTransport, 
//            FailingClientStream, ClientStreamListener

final class ClientCallImpl extends ClientCall
{
    final class ClientStreamListenerImpl
        implements ClientStreamListener
    {

        public boolean closed;
        public final io.grpc.ClientCall.Listener observer;
        public final ClientCallImpl this$0;

        final void close(Status status, Metadata metadata)
        {
            boolean flag;
            boolean flag2;
            flag2 = true;
            flag = true;
            closed = true;
            cancelListenersShouldBeRemoved = true;
            ClientCallImpl clientcallimpl = ClientCallImpl.this;
            ClientCallImpl.closeObserver(observer, status, metadata);
            removeContextListenerAndCancelDeadlineFuture();
            metadata = channelCallsTracer;
            if (io.grpc.Status.Code.OK != status.code)
            {
                flag = false;
            }
            if (flag)
            {
                ((CallTracer) (metadata)).callsSucceeded.add(1L);
                return;
            } else
            {
                ((CallTracer) (metadata)).callsFailed.add(1L);
                return;
            }
            metadata;
            removeContextListenerAndCancelDeadlineFuture();
            CallTracer calltracer = channelCallsTracer;
            boolean flag1;
            if (io.grpc.Status.Code.OK == status.code)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                calltracer.callsSucceeded.add(1L);
            } else
            {
                calltracer.callsFailed.add(1L);
            }
            throw metadata;
        }

        public final void closed(Status status, Metadata metadata)
        {
            closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(status, android.support.v4.content.ModernAsyncTask.Status.PROCESSED$9HKMUBR7E9O66BR9DPQ6ASJEC5M2UGRCD5IMST2JEHP6AOBD9HKN6T35DPIN492IE1HL0SJFCTP6ASRJ7C______0, metadata);
        }

        public final void closed$5166IRPFCTP70OPFADQ62T3LECTKOQBF5TJN4S335TKMST35E9N62R1F8DM6IPBEEH9N8SJ5C5MKOQBJEHIMSPBI4H970OQGE9NMESJ5EDPJMJ39DSNMESJGCCNKQPBKC5I62T317CKLC___0(Status status, int i, Metadata metadata)
        {
            Deadline deadline = effectiveDeadline();
            final Status savedStatus = status;
            final Metadata savedTrailers = metadata;
            if (status.code == io.grpc.Status.Code.CANCELLED)
            {
                savedStatus = status;
                savedTrailers = metadata;
                if (deadline != null)
                {
                    savedStatus = status;
                    savedTrailers = metadata;
                    if (deadline.isExpired())
                    {
                        savedStatus = Status.DEADLINE_EXCEEDED;
                        savedTrailers = new Metadata();
                    }
                }
            }
            class _cls1StreamClosed extends ContextRunnable
            {

                private final ClientStreamListenerImpl this$1;
                private final Status val$savedStatus;
                private final Metadata val$savedTrailers;

                public final void runInContext()
                {
                    if (closed)
                    {
                        return;
                    } else
                    {
                        close(savedStatus, savedTrailers);
                        return;
                    }
                }

                _cls1StreamClosed()
                {
                    this$1 = ClientStreamListenerImpl.this;
                    savedStatus = status;
                    savedTrailers = metadata;
                    super(context);
                }
            }

            callExecutor.execute(new _cls1StreamClosed());
        }

        public final void headersRead(final Metadata headers)
        {
            class _cls1HeadersRead extends ContextRunnable
            {

                private final ClientStreamListenerImpl this$1;
                private final Metadata val$headers;

                public final void runInContext()
                {
                    if (closed)
                    {
                        return;
                    }
                    Status status;
                    try
                    {
                        observer.onHeaders(headers);
                        return;
                    }
                    catch (Throwable throwable)
                    {
                        status = Status.CANCELLED.withCause(throwable).withDescription("Failed to read headers");
                    }
                    stream.cancel(status);
                    close(status, new Metadata());
                    return;
                }

                _cls1HeadersRead()
                {
                    this$1 = ClientStreamListenerImpl.this;
                    headers = metadata;
                    super(context);
                }
            }

            callExecutor.execute(new _cls1HeadersRead());
        }

        public final void messagesAvailable(final StreamListener.MessageProducer producer)
        {
            class _cls1MessagesAvailable extends ContextRunnable
            {

                private final ClientStreamListenerImpl this$1;
                private final StreamListener.MessageProducer val$producer;

                public final void runInContext()
                {
                    if (!closed) goto _L2; else goto _L1
_L1:
                    GrpcUtil.closeQuietly(producer);
_L4:
                    return;
_L2:
                    Object obj;
                    try
                    {
                        obj = producer.next();
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        GrpcUtil.closeQuietly(producer);
                        obj = Status.CANCELLED.withCause(((Throwable) (obj))).withDescription("Failed to read message.");
                        stream.cancel(((Status) (obj)));
                        close(((Status) (obj)), new Metadata());
                        return;
                    }
                    if (obj == null) goto _L4; else goto _L3
_L3:
                    observer.onMessage(method.responseMarshaller.parse(((InputStream) (obj))));
                    ((InputStream) (obj)).close();
                      goto _L2
                    Throwable throwable;
                    throwable;
                    GrpcUtil.closeQuietly(((InputStream) (obj)));
                    throw throwable;
                }

                _cls1MessagesAvailable()
                {
                    this$1 = ClientStreamListenerImpl.this;
                    producer = messageproducer;
                    super(context);
                }
            }

            callExecutor.execute(new _cls1MessagesAvailable());
        }

        public final void onReady()
        {
            class _cls1StreamOnReady extends ContextRunnable
            {

                private final ClientStreamListenerImpl this$1;

                public final void runInContext()
                {
                    Status status;
                    try
                    {
                        observer.onReady();
                        return;
                    }
                    catch (Throwable throwable)
                    {
                        status = Status.CANCELLED.withCause(throwable).withDescription("Failed to call onReady.");
                    }
                    stream.cancel(status);
                    close(status, new Metadata());
                }

                _cls1StreamOnReady()
                {
                    this$1 = ClientStreamListenerImpl.this;
                    super(context);
                }
            }

            callExecutor.execute(new _cls1StreamOnReady());
        }

        public ClientStreamListenerImpl(io.grpc.ClientCall.Listener listener)
        {
            this$0 = ClientCallImpl.this;
            super();
            if (listener == null)
            {
                throw new NullPointerException(String.valueOf("observer"));
            } else
            {
                observer = (io.grpc.ClientCall.Listener)listener;
                return;
            }
        }
    }

    static interface ClientTransportProvider
    {

        public abstract ClientTransport get(io.grpc.LoadBalancer.PickSubchannelArgs picksubchannelargs);

        public abstract RetriableStream newRetriableStream(MethodDescriptor methoddescriptor, CallOptions calloptions, Metadata metadata, Context context1);
    }

    final class ContextCancellationListener
        implements io.grpc.Context.CancellationListener
    {

        private final ClientCallImpl this$0;

        public final void cancelled(Context context1)
        {
            stream.cancel(Contexts.statusFromCancelled(context1));
        }

        ContextCancellationListener()
        {
            this$0 = ClientCallImpl.this;
            super();
        }
    }

    final class DeadlineTimer
        implements Runnable
    {

        private final long remainingNanos;
        private final ClientCallImpl this$0;

        public final void run()
        {
            stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(String.format("deadline exceeded after %dns", new Object[] {
                Long.valueOf(remainingNanos)
            })));
        }

        DeadlineTimer(long l)
        {
            this$0 = ClientCallImpl.this;
            super();
            remainingNanos = l;
        }
    }


    private static final byte FULL_STREAM_DECOMPRESSION_ENCODINGS[] = "gzip".getBytes(Charset.forName("US-ASCII"));
    private static final Logger log = Logger.getLogger(io/grpc/internal/ClientCallImpl.getName());
    public final Executor callExecutor;
    private final CallOptions callOptions;
    private boolean cancelCalled;
    public volatile boolean cancelListenersShouldBeRemoved;
    private final io.grpc.Context.CancellationListener cancellationListener = new ContextCancellationListener();
    public final CallTracer channelCallsTracer;
    private final ClientTransportProvider clientTransportProvider;
    public CompressorRegistry compressorRegistry;
    public final Context context = Context.current();
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture deadlineCancellationFuture;
    public DecompressorRegistry decompressorRegistry;
    public boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    public final MethodDescriptor method;
    private final boolean retryEnabled;
    public ClientStream stream;
    private final boolean unaryRequest;

    ClientCallImpl(MethodDescriptor methoddescriptor, Executor executor, CallOptions calloptions, ClientTransportProvider clienttransportprovider, ScheduledExecutorService scheduledexecutorservice, CallTracer calltracer, boolean flag)
    {
        decompressorRegistry = DecompressorRegistry.DEFAULT_INSTANCE;
        compressorRegistry = CompressorRegistry.DEFAULT_INSTANCE;
        method = methoddescriptor;
        boolean flag1;
        if (executor == com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE)
        {
            executor = new SerializeReentrantCallsDirectExecutor();
        } else
        {
            executor = new SerializingExecutor(executor);
        }
        callExecutor = executor;
        channelCallsTracer = calltracer;
        if (methoddescriptor.type == io.grpc.MethodDescriptor.MethodType.UNARY || methoddescriptor.type == io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        unaryRequest = flag1;
        callOptions = calloptions;
        clientTransportProvider = clienttransportprovider;
        deadlineCancellationExecutor = scheduledexecutorservice;
        retryEnabled = flag;
    }

    static void closeObserver(io.grpc.ClientCall.Listener listener, Status status, Metadata metadata)
    {
        listener.onClose(status, metadata);
    }

    public final void cancel(String s, Throwable throwable)
    {
        Object obj;
        obj = throwable;
        if (s == null)
        {
            obj = throwable;
            if (throwable == null)
            {
                obj = new CancellationException("Cancelled without a message or cause");
                log.logp(Level.WARNING, "io.grpc.internal.ClientCallImpl", "cancel", "Cancelling without a message or cause is suboptimal", ((Throwable) (obj)));
            }
        }
        if (!cancelCalled) goto _L2; else goto _L1
_L1:
        return;
_L2:
        cancelCalled = true;
        if (stream == null) goto _L4; else goto _L3
_L3:
        throwable = Status.CANCELLED;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        s = throwable.withDescription(s);
_L6:
        throwable = s;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        throwable = s.withCause(((Throwable) (obj)));
        stream.cancel(throwable);
_L4:
        context.removeListener(cancellationListener);
        s = deadlineCancellationFuture;
        if (s == null) goto _L1; else goto _L5
_L5:
        s.cancel(false);
        return;
        s = throwable.withDescription("Call cancelled without message");
          goto _L6
        s;
        context.removeListener(cancellationListener);
        throwable = deadlineCancellationFuture;
        if (throwable != null)
        {
            throwable.cancel(false);
        }
        throw s;
    }

    final Deadline effectiveDeadline()
    {
        Deadline deadline = callOptions.deadline;
        Deadline deadline1 = context.getDeadline();
        if (deadline != null)
        {
            if (deadline1 == null)
            {
                return deadline;
            }
            boolean flag;
            if (deadline.deadlineNanos - deadline1.deadlineNanos < 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return deadline;
            }
        }
        return deadline1;
    }

    public final void halfClose()
    {
        boolean flag1 = false;
        boolean flag;
        if (stream != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Not started"));
        }
        if (!cancelCalled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("call was cancelled"));
        }
        flag = flag1;
        if (!halfCloseCalled)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("call already half-closed"));
        } else
        {
            halfCloseCalled = true;
            stream.halfClose();
            return;
        }
    }

    final void removeContextListenerAndCancelDeadlineFuture()
    {
        context.removeListener(cancellationListener);
        ScheduledFuture scheduledfuture = deadlineCancellationFuture;
        if (scheduledfuture != null)
        {
            scheduledfuture.cancel(false);
        }
    }

    public final void request(int i)
    {
        boolean flag1 = true;
        boolean flag;
        if (stream != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Not started"));
        }
        if (i >= 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Number requested must be non-negative"));
        } else
        {
            stream.request(i);
            return;
        }
    }

    public final void sendMessage(Object obj)
    {
        boolean flag1 = true;
        boolean flag;
        if (stream != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Not started"));
        }
        if (!cancelCalled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("call was cancelled"));
        }
        if (!halfCloseCalled)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("call was half-closed"));
        }
        RetriableStream retriablestream;
        RetriableStream.State state;
        if (!(stream instanceof RetriableStream))
        {
            break MISSING_BLOCK_LABEL_221;
        }
        retriablestream = (RetriableStream)stream;
        state = retriablestream.state;
        if (!state.passThrough) goto _L2; else goto _L1
_L1:
        state.winningSubstream.stream.writeMessage(retriablestream.method.requestMarshaller.stream(obj));
_L3:
        if (!unaryRequest)
        {
            stream.flush();
        }
        return;
_L2:
        try
        {
            retriablestream.delayOrExecute(new RetriableStream._cls1SendMessageEntry(retriablestream, obj));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            stream.cancel(Status.CANCELLED.withCause(((Throwable) (obj))).withDescription("Failed to stream message"));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw obj;
        }
          goto _L3
        stream.writeMessage(method.requestMarshaller.stream(obj));
          goto _L3
    }

    public final void start(final io.grpc.ClientCall.Listener observer, Metadata metadata)
    {
        boolean flag;
        if (stream == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Already started"));
        }
        if (!cancelCalled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("call was cancelled"));
        }
        if (observer == null)
        {
            throw new NullPointerException(String.valueOf("observer"));
        }
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        if (!context.isCancelled()) goto _L2; else goto _L1
_L1:
        stream = NoopClientStream.INSTANCE;
        callExecutor.execute(new _cls1ClosedByContext());
_L4:
        return;
_L2:
        Object obj;
        final String compressorName = callOptions.compressorName;
        Object obj1;
        if (compressorName != null)
        {
            Compressor compressor = (Compressor)compressorRegistry.compressors.get(compressorName);
            obj1 = compressor;
            if (compressor == null)
            {
                stream = NoopClientStream.INSTANCE;
                callExecutor.execute(new _cls1ClosedByNotFoundCompressor());
                return;
            }
        } else
        {
            obj1 = io.grpc.Codec.Identity.NONE;
        }
        obj = decompressorRegistry;
        boolean flag2 = fullStreamDecompression;
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (obj1 != io.grpc.Codec.Identity.NONE)
        {
            metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, ((Compressor) (obj1)).getMessageEncoding());
        }
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte abyte0[] = ((DecompressorRegistry) (obj)).advertisedDecompressors;
        if (abyte0.length != 0)
        {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, abyte0);
        }
        metadata.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        metadata.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (flag2)
        {
            metadata.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
        Deadline deadline = callOptions.deadline;
        compressorName = context.getDeadline();
        boolean flag1;
        long l;
        if (deadline == null)
        {
            abyte0 = compressorName;
        } else
        {
            abyte0 = deadline;
            if (compressorName != null)
            {
                if (deadline.deadlineNanos - ((Deadline) (compressorName)).deadlineNanos < 0L)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                abyte0 = deadline;
                if (!flag1)
                {
                    abyte0 = compressorName;
                }
            }
        }
        if (abyte0 != null && abyte0.isExpired())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_968;
        }
        deadline = callOptions.deadline;
        compressorName = context.getDeadline();
        if (log.isLoggable(Level.FINE) && abyte0 != null && deadline == abyte0)
        {
            StringBuilder stringbuilder = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", new Object[] {
                Long.valueOf(Math.max(0L, abyte0.timeRemaining(TimeUnit.NANOSECONDS)))
            }));
            if (compressorName == null)
            {
                stringbuilder.append(" Explicit call timeout was not set.");
            } else
            {
                stringbuilder.append(String.format(" Explicit call timeout was '%d' ns.", new Object[] {
                    Long.valueOf(compressorName.timeRemaining(TimeUnit.NANOSECONDS))
                }));
            }
            log.logp(Level.FINE, "io.grpc.internal.ClientCallImpl", "logIfContextNarrowedTimeout", stringbuilder.toString());
        }
        if (!retryEnabled)
        {
            break; /* Loop/switch isn't completed */
        }
        stream = clientTransportProvider.newRetriableStream(method, callOptions, metadata, context);
_L5:
        if (callOptions.authority != null)
        {
            stream.setAuthority(callOptions.authority);
        }
        if (callOptions.maxInboundMessageSize != null)
        {
            stream.setMaxInboundMessageSize(callOptions.maxInboundMessageSize.intValue());
        }
        if (callOptions.maxOutboundMessageSize != null)
        {
            stream.setMaxOutboundMessageSize(callOptions.maxOutboundMessageSize.intValue());
        }
        if (abyte0 != null)
        {
            stream.setDeadline(abyte0);
        }
        stream.setCompressor(((Compressor) (obj1)));
        if (fullStreamDecompression)
        {
            stream.setFullStreamDecompression(fullStreamDecompression);
        }
        stream.setDecompressorRegistry(decompressorRegistry);
        metadata = channelCallsTracer;
        ((CallTracer) (metadata)).callsStarted.add(1L);
        metadata.lastCallStartedNanos = ((CallTracer) (metadata)).timeProvider.currentTimeNanos();
        stream.start(new ClientStreamListenerImpl(observer));
        context.addListener(cancellationListener, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        if (abyte0 != null && context.getDeadline() != abyte0 && deadlineCancellationExecutor != null)
        {
            l = abyte0.timeRemaining(TimeUnit.NANOSECONDS);
            deadlineCancellationFuture = deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(l)), l, TimeUnit.NANOSECONDS);
        }
        if (cancelListenersShouldBeRemoved)
        {
            context.removeListener(cancellationListener);
            observer = deadlineCancellationFuture;
            if (observer != null)
            {
                observer.cancel(false);
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        Context context1;
        ClientTransport clienttransport;
        clienttransport = clientTransportProvider.get(new PickSubchannelArgsImpl(method, metadata, callOptions));
        context1 = context.attach();
        stream = clienttransport.newStream(method, metadata, callOptions);
        context.detach(context1);
          goto _L5
        observer;
        context.detach(context1);
        throw observer;
        metadata = Status.DEADLINE_EXCEEDED;
        String s = String.valueOf(abyte0);
        stream = new FailingClientStream(metadata.withDescription((new StringBuilder(String.valueOf(s).length() + 19)).append("deadline exceeded: ").append(s).toString()));
          goto _L5
    }

    public final String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("method", method).toString();
    }


    private class _cls1ClosedByContext extends ContextRunnable
    {

        private final ClientCallImpl this$0;
        private final io.grpc.ClientCall.Listener val$observer;

        public final void runInContext()
        {
            ClientCallImpl clientcallimpl = ClientCallImpl.this;
            ClientCallImpl.closeObserver(observer, Contexts.statusFromCancelled(context), new Metadata());
        }

        _cls1ClosedByContext()
        {
            this$0 = ClientCallImpl.this;
            observer = listener;
            super(context);
        }
    }


    private class _cls1ClosedByNotFoundCompressor extends ContextRunnable
    {

        private final ClientCallImpl this$0;
        private final String val$compressorName;
        private final io.grpc.ClientCall.Listener val$observer;

        public final void runInContext()
        {
            ClientCallImpl clientcallimpl = ClientCallImpl.this;
            ClientCallImpl.closeObserver(observer, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", new Object[] {
                compressorName
            })), new Metadata());
        }

        _cls1ClosedByNotFoundCompressor()
        {
            this$0 = ClientCallImpl.this;
            observer = listener;
            compressorName = s;
            super(context);
        }
    }

}
