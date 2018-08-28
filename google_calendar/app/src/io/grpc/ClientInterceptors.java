// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package io.grpc:
//            ClientInterceptor, ClientCall, Channel

public final class ClientInterceptors
{

    public static final ClientCall NOOP_CALL = new _cls2();

    public static Channel intercept(Channel channel, List list)
    {
        if (channel == null)
        {
            throw new NullPointerException(String.valueOf("channel"));
        }
        for (list = list.iterator(); list.hasNext();)
        {
            channel = new InterceptorChannel(channel, (ClientInterceptor)list.next());
        }

        return channel;
    }

    public static transient Channel intercept(Channel channel, ClientInterceptor aclientinterceptor[])
    {
        return intercept(channel, Arrays.asList(aclientinterceptor));
    }


    private class InterceptorChannel extends Channel
    {

        private final Channel channel;
        private final ClientInterceptor interceptor;

        public final String authority()
        {
            return channel.authority();
        }

        public final ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions)
        {
            return interceptor.interceptCall(methoddescriptor, calloptions, channel);
        }

        InterceptorChannel(Channel channel1, ClientInterceptor clientinterceptor)
        {
            channel = channel1;
            if (clientinterceptor == null)
            {
                throw new NullPointerException(String.valueOf("interceptor"));
            } else
            {
                interceptor = (ClientInterceptor)clientinterceptor;
                return;
            }
        }
    }


    private class _cls2 extends ClientCall
    {

        public final void cancel(String s, Throwable throwable)
        {
        }

        public final void halfClose()
        {
        }

        public final void request(int i)
        {
        }

        public final void sendMessage(Object obj)
        {
        }

        public final void start(ClientCall.Listener listener, Metadata metadata)
        {
        }

        _cls2()
        {
        }
    }

}
