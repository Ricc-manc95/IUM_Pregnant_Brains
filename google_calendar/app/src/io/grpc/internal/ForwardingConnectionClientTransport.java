// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            ConnectionClientTransport, LogId, ClientStream

abstract class ForwardingConnectionClientTransport
    implements ConnectionClientTransport
{

    ForwardingConnectionClientTransport()
    {
    }

    protected abstract ConnectionClientTransport _mthdelegate();

    public final Attributes getAttributes()
    {
        return _mthdelegate().getAttributes();
    }

    public final LogId getLogId()
    {
        return _mthdelegate().getLogId();
    }

    public ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        return _mthdelegate().newStream(methoddescriptor, metadata, calloptions);
    }

    public final void ping(ClientTransport.PingCallback pingcallback, Executor executor)
    {
        _mthdelegate().ping(pingcallback, executor);
    }

    public final void shutdown(Status status)
    {
        _mthdelegate().shutdown(status);
    }

    public final void shutdownNow(Status status)
    {
        _mthdelegate().shutdownNow(status);
    }

    public final Runnable start(ManagedClientTransport.Listener listener)
    {
        return _mthdelegate().start(listener);
    }

    public String toString()
    {
        return (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("delegate", _mthdelegate()).toString();
    }
}
