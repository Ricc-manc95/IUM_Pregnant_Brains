// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.io.IOException;

// Referenced classes of package com.google.api.client.http:
//            HttpResponse, HttpHeaders

public class HttpResponseException extends IOException
{

    public static final long serialVersionUID = 0xe5f7c02544cd5c85L;
    public final transient HttpHeaders headers;
    public final int statusCode;
    public final String statusMessage;

    public HttpResponseException(HttpResponse httpresponse)
    {
        this(new Builder(httpresponse));
    }

    public HttpResponseException(Builder builder)
    {
        super(builder.message);
        statusCode = builder.statusCode;
        statusMessage = builder.statusMessage;
        headers = builder.headers;
    }

    public static StringBuilder computeMessageBuffer(HttpResponse httpresponse)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = httpresponse.statusCode;
        if (i != 0)
        {
            stringbuilder.append(i);
        }
        httpresponse = httpresponse.statusMessage;
        if (httpresponse != null)
        {
            if (i != 0)
            {
                stringbuilder.append(' ');
            }
            stringbuilder.append(httpresponse);
        }
        return stringbuilder;
    }

    private class Builder
    {

        public String content;
        public HttpHeaders headers;
        public String message;
        public int statusCode;
        public String statusMessage;

        public Builder(int i, String s, HttpHeaders httpheaders)
        {
            boolean flag;
            if (i >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            }
            statusCode = i;
            statusMessage = s;
            if (httpheaders == null)
            {
                throw new NullPointerException();
            } else
            {
                headers = (HttpHeaders)httpheaders;
                return;
            }
        }

        public Builder(HttpResponse httpresponse)
        {
            this(httpresponse.statusCode, httpresponse.statusMessage, httpresponse.request.responseHeaders);
            try
            {
                content = httpresponse.parseAsString();
                if (content.length() == 0)
                {
                    content = null;
                }
            }
            catch (IOException ioexception)
            {
                ThrowableExtension.STRATEGY.printStackTrace(ioexception);
            }
            httpresponse = HttpResponseException.computeMessageBuffer(httpresponse);
            if (content != null)
            {
                httpresponse.append(StringUtils.LINE_SEPARATOR).append(content);
            }
            message = httpresponse.toString();
        }
    }

}
