// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.io.InputStream;
import java.util.List;

public final class HttpResponse
{

    public final InputStream mContent;
    public final int mContentLength;
    public final List mHeaders;
    public final int mStatusCode;

    public HttpResponse(int i, List list)
    {
        this(i, list, -1, null);
    }

    public HttpResponse(int i, List list, int j, InputStream inputstream)
    {
        mStatusCode = i;
        mHeaders = list;
        mContentLength = j;
        mContent = inputstream;
    }
}
