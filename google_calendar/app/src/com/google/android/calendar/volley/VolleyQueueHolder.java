// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.volley;

import android.content.Context;
import com.android.volley.CacheDispatcher;
import com.android.volley.NetworkDispatcher;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

// Referenced classes of package com.google.android.calendar.volley:
//            GoogleUrlRewriter

public final class VolleyQueueHolder
{

    public static RequestQueue requestQueue = null;

    public static void initialize(Context context, boolean flag)
    {
        boolean flag1 = false;
        com/google/android/calendar/volley/VolleyQueueHolder;
        JVM INSTR monitorenter ;
        if (requestQueue != null) goto _L2; else goto _L1
_L1:
        NetworkDispatcher anetworkdispatcher[];
        int j;
        context = context.getApplicationContext();
        BasicNetwork basicnetwork = new BasicNetwork(new HurlStack(new GoogleUrlRewriter(context, flag)));
        context = new RequestQueue(new DiskBasedCache(context.getCacheDir(), 0x1400000), basicnetwork);
        requestQueue = context;
        if (((RequestQueue) (context)).mCacheDispatcher != null)
        {
            CacheDispatcher cachedispatcher = ((RequestQueue) (context)).mCacheDispatcher;
            cachedispatcher.mQuit = true;
            cachedispatcher.interrupt();
        }
        anetworkdispatcher = ((RequestQueue) (context)).mDispatchers;
        j = anetworkdispatcher.length;
        int i = 0;
_L9:
        if (i >= j) goto _L4; else goto _L3
_L3:
        NetworkDispatcher networkdispatcher1 = anetworkdispatcher[i];
        if (networkdispatcher1 == null) goto _L6; else goto _L5
_L5:
        networkdispatcher1.mQuit = true;
        networkdispatcher1.interrupt();
          goto _L6
_L4:
        context.mCacheDispatcher = new CacheDispatcher(((RequestQueue) (context)).mCacheQueue, ((RequestQueue) (context)).mNetworkQueue, ((RequestQueue) (context)).mCache, ((RequestQueue) (context)).mDelivery);
        ((RequestQueue) (context)).mCacheDispatcher.start();
        i = ((flag1) ? 1 : 0);
_L7:
        if (i >= ((RequestQueue) (context)).mDispatchers.length)
        {
            break; /* Loop/switch isn't completed */
        }
        NetworkDispatcher networkdispatcher = new NetworkDispatcher(((RequestQueue) (context)).mNetworkQueue, ((RequestQueue) (context)).mNetwork, ((RequestQueue) (context)).mCache, ((RequestQueue) (context)).mDelivery);
        ((RequestQueue) (context)).mDispatchers[i] = networkdispatcher;
        networkdispatcher.start();
        i++;
        if (true) goto _L7; else goto _L2
_L2:
        com/google/android/calendar/volley/VolleyQueueHolder;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
_L6:
        i++;
        if (true) goto _L9; else goto _L8
_L8:
    }

}
