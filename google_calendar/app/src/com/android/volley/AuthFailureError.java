// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            VolleyError, NetworkResponse

public final class AuthFailureError extends VolleyError
{

    public AuthFailureError()
    {
    }

    public AuthFailureError(NetworkResponse networkresponse)
    {
        super(networkresponse);
    }
}
