// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request, Response

interface 
{

    public abstract void onNoUsableResponseReceived(Request request);

    public abstract void onResponseReceived(Request request, Response response);
}
