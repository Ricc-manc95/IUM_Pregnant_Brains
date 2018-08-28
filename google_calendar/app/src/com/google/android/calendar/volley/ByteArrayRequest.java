// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public final class ByteArrayRequest extends Request
{

    private final com.android.volley.Response.Listener listener;

    private ByteArrayRequest(int i, String s, com.android.volley.Response.Listener listener1, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, errorlistener);
        listener = listener1;
    }

    public ByteArrayRequest(String s, com.android.volley.Response.Listener listener1, com.android.volley.Response.ErrorListener errorlistener)
    {
        this(0, s, listener1, errorlistener);
    }

    protected final void deliverResponse(Object obj)
    {
        obj = (byte[])obj;
        listener.onResponse(obj);
    }

    protected final Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        return new Response(networkresponse.data, HttpHeaderParser.parseCacheHeaders(networkresponse));
    }
}
