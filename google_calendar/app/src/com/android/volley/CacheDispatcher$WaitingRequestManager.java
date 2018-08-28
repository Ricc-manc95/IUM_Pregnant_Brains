// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            Request, CacheDispatcher, VolleyLog, Response, 
//            ResponseDelivery

final class mCacheDispatcher
    implements 
{

    private final CacheDispatcher mCacheDispatcher;
    private final Map mWaitingRequests = new HashMap();

    final boolean maybeAddToWaitingRequests(Request request)
    {
        this;
        JVM INSTR monitorenter ;
        String s = request.getCacheKey();
        if (!mWaitingRequests.containsKey(s)) goto _L2; else goto _L1
_L1:
        List list = (List)mWaitingRequests.get(s);
        Object obj;
        obj = list;
        if (list != null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        obj = new ArrayList();
        if (mWaitingRequests)
        {
            request.mEventLog.mWaitingRequests("waiting-for-response", Thread.currentThread().getId());
        }
        ((List) (obj)).add(request);
        mWaitingRequests.put(s, obj);
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        mWaitingRequests.put(s, null);
        synchronized (request.mLock)
        {
            request.mRequestCompleteListener = this;
        }
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        request;
        obj1;
        JVM INSTR monitorexit ;
        throw request;
        request;
        this;
        JVM INSTR monitorexit ;
        throw request;
    }

    public final void onNoUsableResponseReceived(Request request)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        List list;
        s = request.getCacheKey();
        list = (List)mWaitingRequests.remove(s);
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        if (list.isEmpty())
        {
            break MISSING_BLOCK_LABEL_84;
        }
        request = (Request)list.remove(0);
        mWaitingRequests.put(s, list);
        synchronized (request.mLock)
        {
            request.mRequestCompleteListener = this;
        }
        mCacheDispatcher.mNetworkQueue.put(request);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        request;
        obj;
        JVM INSTR monitorexit ;
        throw request;
        request;
        this;
        JVM INSTR monitorexit ;
        throw request;
        request;
        request = request.toString();
        Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Couldn't add request to queue. %s", new Object[] {
            request
        }));
        Thread.currentThread().interrupt();
        request = mCacheDispatcher;
        request.mQuit = true;
        request.interrupt();
          goto _L1
    }

    public final void onResponseReceived(Request request, Response response)
    {
        if (response.cacheEntry == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (response.cacheEntry.mCacheDispatcher < System.currentTimeMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L3; else goto _L2
_L2:
        onNoUsableResponseReceived(request);
_L5:
        return;
_L3:
        request = request.getCacheKey();
        this;
        JVM INSTR monitorenter ;
        request = (List)mWaitingRequests.remove(request);
        this;
        JVM INSTR monitorexit ;
        if (request != null)
        {
            request = request.iterator();
            while (request.hasNext()) 
            {
                Request request1 = (Request)request.next();
                mCacheDispatcher.mDelivery.postResponse(request1, response);
            }
        }
        if (true) goto _L5; else goto _L4
_L4:
        request;
        this;
        JVM INSTR monitorexit ;
        throw request;
    }

    (CacheDispatcher cachedispatcher)
    {
        mCacheDispatcher = cachedispatcher;
    }
}
