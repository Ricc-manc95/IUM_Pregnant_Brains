// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            CacheDispatcher, Request

final class val.request
    implements Runnable
{

    private final CacheDispatcher this$0;
    private final Request val$request;

    public final void run()
    {
        try
        {
            mNetworkQueue.put(val$request);
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            Thread.currentThread().interrupt();
        }
    }

    ()
    {
        this$0 = final_cachedispatcher;
        val$request = Request.this;
        super();
    }
}
