// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class val.forceCacheTimeMs extends StringRequest
{

    private final long val$forceCacheTimeMs;

    protected final Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        Map map = networkresponse.headers;
        long l = TimeUnit.MILLISECONDS.toSeconds(val$forceCacheTimeMs);
        map.put("cache-control", (new StringBuilder(36)).append("public, max-age=").append(l).toString());
        return super.parseNetworkResponse(networkresponse);
    }

    public _cls9(com.android.volley. , com.android.volley.tener tener, long l)
    {
        val$forceCacheTimeMs = l;
        super(final_s, , tener);
    }
}
