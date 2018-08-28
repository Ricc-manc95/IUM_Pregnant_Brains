// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Channel, ClientInterceptor, MethodDescriptor, CallOptions, 
//            ClientCall

final class interceptor extends Channel
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

    (Channel channel1, ClientInterceptor clientinterceptor)
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
