// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import android.support.v4.util.LruCache;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.timely.net:
//            Client

final class val.request
    implements FutureCallback
{

    private final Client this$0;
    private final Object val$request;

    public final void onFailure(Throwable throwable)
    {
    }

    public final void onSuccess(Object obj)
    {
        lastResult.put(val$request, obj);
    }

    ()
    {
        this$0 = final_client;
        val$request = Object.this;
        super();
    }
}
