// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request, VolleyError, Response

public interface ResponseDelivery
{

    public abstract void postError(Request request, VolleyError volleyerror);

    public abstract void postResponse(Request request, Response response);

    public abstract void postResponse(Request request, Response response, Runnable runnable);
}
