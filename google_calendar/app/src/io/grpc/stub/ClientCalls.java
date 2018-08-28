// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClientCalls
{
    static final class GrpcFuture extends AbstractFuture
    {

        private final ClientCall call;

        protected final void interruptTask()
        {
            call.cancel("GrpcFuture was cancelled", null);
        }

        protected final String pendingToString()
        {
            return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("clientCall", call).toString();
        }

        protected final boolean set(Object obj)
        {
            return super.set(obj);
        }

        protected final boolean setException(Throwable throwable)
        {
            return super.setException(throwable);
        }

        GrpcFuture(ClientCall clientcall)
        {
            call = clientcall;
        }
    }

    static final class ThreadlessExecutor
        implements Executor
    {

        public static final Logger log = Logger.getLogger(io/grpc/stub/ClientCalls$ThreadlessExecutor.getName());
        public final BlockingQueue queue = new LinkedBlockingQueue();

        public final void execute(Runnable runnable)
        {
            queue.add(runnable);
        }


        ThreadlessExecutor()
        {
        }
    }

    static final class UnaryStreamToFuture extends io.grpc.ClientCall.Listener
    {

        private final GrpcFuture responseFuture;
        private Object value;

        public final void onClose(Status status, Metadata metadata)
        {
            boolean flag;
            if (io.grpc.Status.Code.OK == status.code)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (value == null)
                {
                    responseFuture.setException(new StatusRuntimeException(Status.INTERNAL.withDescription("No value received for unary call"), metadata));
                }
                responseFuture.set(value);
                return;
            } else
            {
                responseFuture.setException(new StatusRuntimeException(status, metadata));
                return;
            }
        }

        public final void onHeaders(Metadata metadata)
        {
        }

        public final void onMessage(Object obj)
        {
            if (value != null)
            {
                throw new StatusRuntimeException(Status.INTERNAL.withDescription("More than one value received for unary call"));
            } else
            {
                value = obj;
                return;
            }
        }

        UnaryStreamToFuture(GrpcFuture grpcfuture)
        {
            responseFuture = grpcfuture;
        }
    }


    private static final Logger logger = Logger.getLogger(io/grpc/stub/ClientCalls.getName());

    private ClientCalls()
    {
    }

    private static void asyncUnaryRequestCall(ClientCall clientcall, Object obj, io.grpc.ClientCall.Listener listener, boolean flag)
    {
        clientcall.start(listener, new Metadata());
        clientcall.request(2);
        try
        {
            clientcall.sendMessage(obj);
            clientcall.halfClose();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw cancelThrow(clientcall, ((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw cancelThrow(clientcall, ((Throwable) (obj)));
        }
    }

    public static Object blockingUnaryCall(Channel channel, MethodDescriptor methoddescriptor, CallOptions calloptions, Object obj)
    {
        ThreadlessExecutor threadlessexecutor;
        threadlessexecutor = new ThreadlessExecutor();
        calloptions = new CallOptions(calloptions);
        calloptions.executor = threadlessexecutor;
        methoddescriptor = channel.newCall(methoddescriptor, calloptions);
        calloptions = new GrpcFuture(methoddescriptor);
        asyncUnaryRequestCall(methoddescriptor, obj, new UnaryStreamToFuture(calloptions), false);
_L5:
        boolean flag = calloptions.isDone();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_162;
        }
        channel = (Runnable)threadlessexecutor.queue.take();
_L2:
        if (channel == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        channel.run();
_L3:
        channel = (Runnable)threadlessexecutor.queue.poll();
        if (true) goto _L2; else goto _L1
_L1:
        continue; /* Loop/switch isn't completed */
        channel;
        ThreadlessExecutor.log.logp(Level.WARNING, "io.grpc.stub.ClientCalls$ThreadlessExecutor", "waitAndDrain", "Runnable threw exception", channel);
          goto _L3
        channel;
        try
        {
            Thread.currentThread().interrupt();
            throw new StatusRuntimeException(Status.CANCELLED.withDescription("Call was interrupted").withCause(channel));
        }
        // Misplaced declaration of an exception variable
        catch (Channel channel)
        {
            throw cancelThrow(methoddescriptor, channel);
        }
        // Misplaced declaration of an exception variable
        catch (Channel channel)
        {
            throw cancelThrow(methoddescriptor, channel);
        }
        channel = ((Channel) (getUnchecked(calloptions)));
        return channel;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private static RuntimeException cancelThrow(ClientCall clientcall, Throwable throwable)
    {
        try
        {
            clientcall.cancel(null, throwable);
        }
        // Misplaced declaration of an exception variable
        catch (ClientCall clientcall)
        {
            logger.logp(Level.SEVERE, "io.grpc.stub.ClientCalls", "cancelThrow", "RuntimeException encountered while closing call", clientcall);
        }
        if (throwable instanceof RuntimeException)
        {
            throw (RuntimeException)throwable;
        }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        } else
        {
            throw new AssertionError(throwable);
        }
    }

    public static ListenableFuture futureUnaryCall(ClientCall clientcall, Object obj)
    {
        GrpcFuture grpcfuture = new GrpcFuture(clientcall);
        clientcall.start(new UnaryStreamToFuture(grpcfuture), new Metadata());
        clientcall.request(2);
        try
        {
            clientcall.sendMessage(obj);
            clientcall.halfClose();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw cancelThrow(clientcall, ((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw cancelThrow(clientcall, ((Throwable) (obj)));
        }
        return grpcfuture;
    }

    private static Object getUnchecked(Future future)
    {
        future = ((Future) (future.get()));
        return future;
        future;
        Thread.currentThread().interrupt();
        throw new StatusRuntimeException(Status.CANCELLED.withDescription("Call was interrupted").withCause(future));
        future;
        Throwable throwable;
        throwable = future.getCause();
        if (throwable == null)
        {
            throw new NullPointerException(String.valueOf("t"));
        }
        future = (Throwable)throwable;
_L1:
        if (future == null)
        {
            break MISSING_BLOCK_LABEL_137;
        }
        if (future instanceof StatusException)
        {
            future = (StatusException)future;
            future = new StatusRuntimeException(((StatusException) (future)).status, ((StatusException) (future)).trailers);
        } else
        {
label0:
            {
                if (!(future instanceof StatusRuntimeException))
                {
                    break label0;
                }
                future = (StatusRuntimeException)future;
                future = new StatusRuntimeException(((StatusRuntimeException) (future)).status, ((StatusRuntimeException) (future)).trailers);
            }
        }
_L2:
        throw future;
        future = future.getCause();
          goto _L1
        future = new StatusRuntimeException(Status.UNKNOWN.withDescription("unexpected exception").withCause(throwable));
          goto _L2
    }

}
