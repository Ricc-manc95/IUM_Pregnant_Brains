// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;

public final class InvalidateFlairsRequest extends Request
{

    private final Cache cache;
    private final String flairKeys[];

    public InvalidateFlairsRequest(Cache cache1, String as[])
    {
        super(0, null, null);
        cache = cache1;
        flairKeys = as;
    }

    protected final volatile void deliverResponse(Object obj)
    {
    }

    public final com.android.volley.Request.Priority getPriority()
    {
        return com.android.volley.Request.Priority.IMMEDIATE;
    }

    public final boolean isCanceled()
    {
        String as[] = flairKeys;
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            String s = as[i];
            cache.invalidate(FlairAllocatorFactory.getFlairUrlStringFromKey(s), true);
        }

        return true;
    }

    protected final Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        return null;
    }
}
