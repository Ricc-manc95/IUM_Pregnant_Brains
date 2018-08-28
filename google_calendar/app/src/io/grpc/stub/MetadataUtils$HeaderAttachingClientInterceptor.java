// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

public final class extraHeaders
    implements ClientInterceptor
{

    public final Metadata extraHeaders;

    public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
    {
        class HeaderAttachingClientCall extends io.grpc.ForwardingClientCall.SimpleForwardingClientCall
        {

            private final MetadataUtils.HeaderAttachingClientInterceptor this$0;

            public final void start(io.grpc.ClientCall.Listener listener, Metadata metadata)
            {
                metadata.merge(extraHeaders);
                super.start(listener, metadata);
            }

            HeaderAttachingClientCall(ClientCall clientcall)
            {
                this$0 = MetadataUtils.HeaderAttachingClientInterceptor.this;
                super(clientcall);
            }
        }

        return new HeaderAttachingClientCall(channel.newCall(methoddescriptor, calloptions));
    }

    public HeaderAttachingClientCall(Metadata metadata)
    {
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf(metadata));
        } else
        {
            extraHeaders = (Metadata)metadata;
            return;
        }
    }
}
