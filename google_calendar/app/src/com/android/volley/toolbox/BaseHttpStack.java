// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package com.android.volley.toolbox:
//            HttpResponse

public abstract class BaseHttpStack
{

    public BaseHttpStack()
    {
    }

    public abstract HttpResponse executeRequest(Request request, Map map)
        throws IOException, AuthFailureError;
}
