// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.common.util.concurrent.AbstractFuture;

public final class  extends AbstractFuture
    implements com.android.volley.eyRequestFuture, com.android.volley.eyRequestFuture
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

    public ()
    {
    }
}
