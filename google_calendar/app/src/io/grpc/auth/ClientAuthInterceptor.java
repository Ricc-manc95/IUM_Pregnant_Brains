// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.auth;

import com.google.auth.Credentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public final class ClientAuthInterceptor
    implements ClientInterceptor
{

    public Metadata cached;
    private final Credentials credentials;
    public Map lastMetadata;

    public ClientAuthInterceptor(Credentials credentials1)
    {
        if (credentials1 == null)
        {
            throw new NullPointerException(String.valueOf("credentials"));
        } else
        {
            credentials = (Credentials)credentials1;
            return;
        }
    }

    private static URI removePort(URI uri)
        throws StatusException
    {
        try
        {
            uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        }
        // Misplaced declaration of an exception variable
        catch (URI uri)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(uri));
        }
        return uri;
    }

    final Map getRequestMetadata(URI uri)
        throws StatusException
    {
        try
        {
            uri = credentials.getRequestMetadata$5166KOBMC4NMSPBK5TAL4I9R5566KOBMC4NNAT39DGNKQOBG7C______0();
        }
        // Misplaced declaration of an exception variable
        catch (URI uri)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to get request metadata").withCause(uri));
        }
        return uri;
    }

    public final ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel)
    {
        return new _cls1(methoddescriptor);
    }

    final URI serviceUri(Channel channel, MethodDescriptor methoddescriptor)
        throws StatusException
    {
        String s = channel.authority();
        if (s == null)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Channel has no authority"));
        }
        channel = String.valueOf(MethodDescriptor.extractFullServiceName(methoddescriptor.fullMethodName));
        if (channel.length() != 0)
        {
            channel = "/".concat(channel);
        } else
        {
            channel = new String("/");
        }
        try
        {
            methoddescriptor = new URI("https", s, channel, null, null);
        }
        // Misplaced declaration of an exception variable
        catch (Channel channel)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(channel));
        }
        channel = methoddescriptor;
        if (methoddescriptor.getPort() == 443)
        {
            channel = removePort(methoddescriptor);
        }
        return channel;
    }

    private class _cls1 extends io.grpc.ClientInterceptors.CheckedForwardingClientCall
    {

        private final ClientAuthInterceptor this$0;
        private final MethodDescriptor val$method;
        private final Channel val$next;

        protected final void checkedStart(io.grpc.ClientCall.Listener listener, Metadata metadata)
            throws StatusException
        {
            Object obj = serviceUri(next, method);
            ClientAuthInterceptor clientauthinterceptor = ClientAuthInterceptor.this;
            clientauthinterceptor;
            JVM INSTR monitorenter ;
            Map map;
            Metadata metadata1;
            obj = getRequestMetadata(((URI) (obj)));
            if (lastMetadata != null && lastMetadata == obj)
            {
                break MISSING_BLOCK_LABEL_202;
            }
            lastMetadata = ((Map) (obj));
            obj = ClientAuthInterceptor.this;
            map = lastMetadata;
            metadata1 = new Metadata();
            if (map == null)
            {
                break MISSING_BLOCK_LABEL_195;
            }
            for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
            {
                Object obj1 = (String)iterator.next();
                io.grpc.Metadata.Key key = io.grpc.Metadata.Key.of(((String) (obj1)), Metadata.ASCII_STRING_MARSHALLER);
                obj1 = ((List)map.get(obj1)).iterator();
                while (((Iterator) (obj1)).hasNext()) 
                {
                    metadata1.put(key, (String)((Iterator) (obj1)).next());
                }
            }

            break MISSING_BLOCK_LABEL_195;
            listener;
            clientauthinterceptor;
            JVM INSTR monitorexit ;
            throw listener;
            obj.cached = metadata1;
            obj = cached;
            clientauthinterceptor;
            JVM INSTR monitorexit ;
            metadata.merge(((Metadata) (obj)));
            _mthdelegate().start(listener, metadata);
            return;
        }

        _cls1(MethodDescriptor methoddescriptor)
        {
            this$0 = ClientAuthInterceptor.this;
            next = channel;
            method = methoddescriptor;
            super(final_clientcall);
        }
    }

}
