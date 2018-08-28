// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.corp.android.volley.proto;

import android.net.Uri;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class MessageLiteVolleyRequest extends Request
{

    private static final RetryPolicy DEFAULT_RETRY_POLICY;
    public final SettableFuture future = new SettableFuture();
    private final ImmutableMap headers;
    private final Parser messageParser;
    private final Optional postBody;
    private final com.android.volley.Request.Priority priority;
    private final com.android.volley.Response.Listener responseListener;

    public MessageLiteVolleyRequest(Builder builder1)
    {
        Optional optional;
        int i;
        if (builder1.postBody.isPresent())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        super(i, builder1.requestUrl.toString(), null);
        messageParser = builder1.messageParser;
        headers = builder1.headers.build();
        optional = builder1.postBody;
        if (optional == null)
        {
            throw new NullPointerException();
        }
        postBody = (Optional)optional;
        builder1 = builder1.priority;
        if (builder1 == null)
        {
            throw new NullPointerException();
        } else
        {
            priority = (com.android.volley.Request.Priority)builder1;
            responseListener = null;
            super.mRetryPolicy = DEFAULT_RETRY_POLICY;
            future.addListener(new _cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return;
        }
    }

    public static Builder builder(Parser parser)
    {
        return new Builder(parser);
    }

    public final void deliverError(VolleyError volleyerror)
    {
        super.deliverError(volleyerror);
        future.setException(volleyerror);
    }

    protected final void deliverResponse(Object obj)
    {
        obj = (MessageLite)obj;
        future.set(obj);
        if (responseListener != null)
        {
            responseListener.onResponse(obj);
        }
    }

    public final byte[] getBody()
        throws AuthFailureError
    {
        if (postBody.isPresent())
        {
            return ((MessageLite)postBody.get()).toByteArray();
        } else
        {
            return super.getBody();
        }
    }

    public final String getBodyContentType()
    {
        return "application/protobuf";
    }

    public final Map getHeaders()
        throws AuthFailureError
    {
        ImmutableMap immutablemap = headers;
        com.google.common.base.Suppliers.SupplierFunctionImpl supplierfunctionimpl = com.google.common.base.Suppliers.SupplierFunctionImpl.INSTANCE;
        if (supplierfunctionimpl == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.collect.Maps.TransformedEntriesMap(immutablemap, new com.google.common.collect.Maps._cls9(supplierfunctionimpl));
        }
    }

    public final com.android.volley.Request.Priority getPriority()
    {
        return priority;
    }

    protected final Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        try
        {
            networkresponse = new Response((MessageLite)messageParser.parseFrom(networkresponse.data), HttpHeaderParser.parseCacheHeaders(networkresponse));
        }
        // Misplaced declaration of an exception variable
        catch (NetworkResponse networkresponse)
        {
            return new Response(new ParseError(networkresponse));
        }
        return networkresponse;
    }

    static 
    {
        DEFAULT_RETRY_POLICY = new DefaultRetryPolicy((int)TimeUnit.SECONDS.toMillis(10L), 2, 1.0F);
    }

    private class Builder
    {

        public final com.google.common.collect.ImmutableMap.Builder headers = new com.google.common.collect.ImmutableMap.Builder();
        public final Parser messageParser;
        public Optional postBody;
        public com.android.volley.Request.Priority priority;
        public Uri requestUrl;

        Builder(Parser parser)
        {
            postBody = Absent.INSTANCE;
            priority = com.android.volley.Request.Priority.NORMAL;
            if (parser == null)
            {
                throw new NullPointerException();
            } else
            {
                messageParser = (Parser)parser;
                return;
            }
        }
    }


    private class _cls1
        implements Runnable
    {

        private final MessageLiteVolleyRequest this$0;

        public final void run()
        {
            if (future.isCancelled())
            {
                cancel();
            }
        }

        _cls1()
        {
            this$0 = MessageLiteVolleyRequest.this;
            super();
        }
    }

}
