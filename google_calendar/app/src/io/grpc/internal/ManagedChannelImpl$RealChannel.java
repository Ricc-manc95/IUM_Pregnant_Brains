// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;

// Referenced classes of package io.grpc.internal:
//            ManagedChannelImpl, ClientCallImpl, ClientTransportFactory

final class this._cls0 extends Channel
{

    private final ManagedChannelImpl this$0;

    public final String authority()
    {
        String s = nameResolver.getServiceAuthority();
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        } else
        {
            return (String)s;
        }
    }

    public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
    {
        Object obj1 = ManagedChannelImpl.this;
        Object obj = calloptions.executor;
        java.util.concurrent.Executor executor = ((java.util.concurrent.Executor) (obj));
        if (obj == null)
        {
            executor = ((ManagedChannelImpl) (obj1)).executor;
        }
        obj1 = transportProvider;
        if (terminated)
        {
            obj = null;
        } else
        {
            obj = transportFactory.getScheduledExecutorService();
        }
        methoddescriptor = new ClientCallImpl(methoddescriptor, executor, calloptions, ((rovider) (obj1)), ((java.util.concurrent.ScheduledExecutorService) (obj)), channelCallTracer, retryEnabled);
        methoddescriptor.fullStreamDecompression = fullStreamDecompression;
        methoddescriptor.decompressorRegistry = decompressorRegistry;
        methoddescriptor.compressorRegistry = compressorRegistry;
        return methoddescriptor;
    }

    ()
    {
        this$0 = ManagedChannelImpl.this;
        super();
    }
}
