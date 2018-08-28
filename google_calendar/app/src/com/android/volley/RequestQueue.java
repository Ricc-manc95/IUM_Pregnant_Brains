// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.android.volley:
//            ExecutorDelivery, NetworkDispatcher, Request, Cache, 
//            CacheDispatcher, ResponseDelivery, Network

public final class RequestQueue
{

    public final Cache mCache;
    public CacheDispatcher mCacheDispatcher;
    public final PriorityBlockingQueue mCacheQueue;
    public final Set mCurrentRequests;
    public final ResponseDelivery mDelivery;
    public final NetworkDispatcher mDispatchers[];
    public final List mFinishedListeners;
    public final Network mNetwork;
    public final PriorityBlockingQueue mNetworkQueue;
    private final AtomicInteger mSequenceGenerator;

    public RequestQueue(Cache cache, Network network)
    {
        this(cache, network, 4);
    }

    private RequestQueue(Cache cache, Network network, int i)
    {
        this(cache, network, 4, ((ResponseDelivery) (new ExecutorDelivery(new Handler(Looper.getMainLooper())))));
    }

    private RequestQueue(Cache cache, Network network, int i, ResponseDelivery responsedelivery)
    {
        mSequenceGenerator = new AtomicInteger();
        mCurrentRequests = new HashSet();
        mCacheQueue = new PriorityBlockingQueue();
        mNetworkQueue = new PriorityBlockingQueue();
        mFinishedListeners = new ArrayList();
        mCache = cache;
        mNetwork = network;
        mDispatchers = new NetworkDispatcher[i];
        mDelivery = responsedelivery;
    }

    public final Request add(Request request)
    {
        request.mRequestQueue = this;
        synchronized (mCurrentRequests)
        {
            mCurrentRequests.add(request);
        }
        request.mSequence = Integer.valueOf(mSequenceGenerator.incrementAndGet());
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("add-to-queue", Thread.currentThread().getId());
        }
        if (!request.mShouldCache)
        {
            mNetworkQueue.add(request);
            return request;
        } else
        {
            mCacheQueue.add(request);
            return request;
        }
        request;
        set;
        JVM INSTR monitorexit ;
        throw request;
    }

    public final void cancelAll(final Object tag)
    {
        _cls1 _lcls1 = new _cls1();
        tag = mCurrentRequests;
        tag;
        JVM INSTR monitorenter ;
        Iterator iterator = mCurrentRequests.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Request request = (Request)iterator.next();
            if (_lcls1.apply(request))
            {
                request.cancel();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_70;
        Exception exception;
        exception;
        tag;
        JVM INSTR monitorexit ;
        throw exception;
        tag;
        JVM INSTR monitorexit ;
    }

    private class _cls1
        implements RequestFilter
    {

        private final Object val$tag;

        public final boolean apply(Request request)
        {
            return request.mTag == tag;
        }

        _cls1()
        {
            tag = obj;
            super();
        }
    }


    private class RequestFilter
    {

        public abstract boolean apply(Request request);
    }

}
