// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            VolleyLog, Request, Cache, NetworkResponse, 
//            Response, ResponseDelivery

public final class CacheDispatcher extends Thread
{

    private static final boolean DEBUG;
    private final Cache mCache;
    private final BlockingQueue mCacheQueue;
    public final ResponseDelivery mDelivery;
    public final BlockingQueue mNetworkQueue;
    public volatile boolean mQuit;
    private final WaitingRequestManager mWaitingRequestManager = new WaitingRequestManager();

    public CacheDispatcher(BlockingQueue blockingqueue, BlockingQueue blockingqueue1, Cache cache, ResponseDelivery responsedelivery)
    {
        mQuit = false;
        mCacheQueue = blockingqueue;
        mNetworkQueue = blockingqueue1;
        mCache = cache;
        mDelivery = responsedelivery;
    }

    private final void processRequest()
        throws InterruptedException
    {
        final Request request;
        boolean flag2;
        flag2 = false;
        request = (Request)mCacheQueue.take();
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("cache-queue-take", Thread.currentThread().getId());
        }
        if (!request.isCanceled()) goto _L2; else goto _L1
_L1:
        request.finish("cache-discard-canceled");
_L4:
        return;
_L2:
        Cache.Entry entry;
        entry = mCache.get(request.getCacheKey());
        if (entry != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("cache-miss", Thread.currentThread().getId());
        }
        if (!mWaitingRequestManager.maybeAddToWaitingRequests(request))
        {
            mNetworkQueue.put(request);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (entry.ttl < System.currentTimeMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("cache-hit-expired", Thread.currentThread().getId());
            }
            request.mCacheEntry = entry;
            if (!mWaitingRequestManager.maybeAddToWaitingRequests(request))
            {
                mNetworkQueue.put(request);
                return;
            }
        } else
        {
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("cache-hit", Thread.currentThread().getId());
            }
            Response response = request.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("cache-hit-parsed", Thread.currentThread().getId());
            }
            boolean flag1 = flag2;
            if (entry.softTtl < System.currentTimeMillis())
            {
                flag1 = true;
            }
            if (flag1)
            {
                if (VolleyLog.MarkerLog.ENABLED)
                {
                    request.mEventLog.add("cache-hit-refresh-needed", Thread.currentThread().getId());
                }
                request.mCacheEntry = entry;
                response.intermediate = true;
                if (!mWaitingRequestManager.maybeAddToWaitingRequests(request))
                {
                    mDelivery.postResponse(request, response, new _cls1());
                    return;
                }
            }
            mDelivery.postResponse(request, response);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final void run()
    {
        if (DEBUG)
        {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        mCache.initialize();
        do
        {
            try
            {
                do
                {
                    processRequest();
                } while (true);
            }
            catch (InterruptedException interruptedexception) { }
            if (mQuit)
            {
                Thread.currentThread().interrupt();
                return;
            }
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]));
        } while (true);
    }

    static 
    {
        DEBUG = VolleyLog.DEBUG;
    }

    private class WaitingRequestManager
        implements Request.NetworkRequestCompleteListener
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
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("waiting-for-response", Thread.currentThread().getId());
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
            if (response.cacheEntry.ttl < System.currentTimeMillis())
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

        WaitingRequestManager()
        {
            mCacheDispatcher = CacheDispatcher.this;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final CacheDispatcher this$0;
        private final Request val$request;

        public final void run()
        {
            try
            {
                mNetworkQueue.put(request);
                return;
            }
            catch (InterruptedException interruptedexception)
            {
                Thread.currentThread().interrupt();
            }
        }

        _cls1()
        {
            this$0 = CacheDispatcher.this;
            request = request1;
            super();
        }
    }

}
