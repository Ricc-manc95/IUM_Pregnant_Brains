// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;

// Referenced classes of package com.google.api.client.http:
//            LowLevelHttpResponse

public abstract class LowLevelHttpRequest
{

    public String contentEncoding;
    public long contentLength;
    public String contentType;
    public StreamingContent streamingContent;

    public LowLevelHttpRequest()
    {
        contentLength = -1L;
    }

    public abstract void addHeader(String s, String s1)
        throws IOException;

    public abstract LowLevelHttpResponse execute()
        throws IOException;

    public void setTimeout(int i, int j)
        throws IOException
    {
    }
}
