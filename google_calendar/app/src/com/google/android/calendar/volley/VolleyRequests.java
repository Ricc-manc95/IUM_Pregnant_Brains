// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.volley:
//            ByteArrayRequest, VolleyQueueHolder

public final class VolleyRequests extends Enum
{

    private static final VolleyRequests $VALUES[] = new VolleyRequests[0];

    public static ListenableFuture loadBytesAsync(String s)
    {
        if (s == null)
        {
            if (true)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        }
        VolleyRequestFuture volleyrequestfuture = new VolleyRequestFuture();
        s = new ByteArrayRequest(s, volleyrequestfuture, volleyrequestfuture);
        boolean flag;
        if (volleyrequestfuture.request == null)
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
        volleyrequestfuture.request = s;
        volleyrequestfuture.request.mTag = "calendar_volley_request";
        s = VolleyQueueHolder.requestQueue;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        } else
        {
            ((RequestQueue)s).add(volleyrequestfuture.request);
            return volleyrequestfuture;
        }
    }

    public static VolleyRequests[] values()
    {
        return (VolleyRequests[])$VALUES.clone();
    }


    private class VolleyRequestFuture extends AbstractFuture
        implements com.android.volley.Response.ErrorListener, com.android.volley.Response.Listener
    {

        public Request request;

        protected final void interruptTask()
        {
            request.cancel();
        }

        public final void onErrorResponse(VolleyError volleyerror)
        {
            setException(volleyerror);
        }

        public final void onResponse(Object obj)
        {
            set(obj);
        }

        public VolleyRequestFuture()
        {
        }
    }

}
