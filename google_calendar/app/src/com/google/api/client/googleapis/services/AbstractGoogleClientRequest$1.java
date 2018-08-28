// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import java.io.IOException;

// Referenced classes of package com.google.api.client.googleapis.services:
//            AbstractGoogleClientRequest

public final class val.httpRequest
    implements HttpResponseInterceptor
{

    private final AbstractGoogleClientRequest this$0;
    private final HttpRequest val$httpRequest;
    private final HttpResponseInterceptor val$responseInterceptor;

    public final void interceptResponse(HttpResponse httpresponse)
        throws IOException
    {
        if (val$responseInterceptor != null)
        {
            val$responseInterceptor.interceptResponse(httpresponse);
        }
        int i = httpresponse.statusCode;
        boolean flag;
        if (i >= 200 && i < 300)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && val$httpRequest.throwExceptionOnExecuteError)
        {
            throw newExceptionOnError(httpresponse);
        } else
        {
            return;
        }
    }

    public ()
    {
        this$0 = final_abstractgoogleclientrequest;
        val$responseInterceptor = httpresponseinterceptor;
        val$httpRequest = HttpRequest.this;
        super();
    }
}
