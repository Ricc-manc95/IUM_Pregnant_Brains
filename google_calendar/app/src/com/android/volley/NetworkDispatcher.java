// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            VolleyError, Request, Network, NetworkResponse, 
//            ResponseDelivery, Response, Cache, VolleyLog

public final class NetworkDispatcher extends Thread
{

    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue mQueue;
    public volatile boolean mQuit;

    public NetworkDispatcher(BlockingQueue blockingqueue, Network network, Cache cache, ResponseDelivery responsedelivery)
    {
        mQuit = false;
        mQueue = blockingqueue;
        mNetwork = network;
        mCache = cache;
        mDelivery = responsedelivery;
    }

    private final void processRequest()
        throws InterruptedException
    {
        Request request = (Request)mQueue.take();
        SystemClock.elapsedRealtime();
        Object obj;
        try
        {
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("network-queue-take", Thread.currentThread().getId());
            }
            if (request.isCanceled())
            {
                request.finish("network-discard-cancelled");
                request.notifyListenerResponseNotUsable();
                return;
            }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            SystemClock.elapsedRealtime();
            mDelivery.postError(request, ((VolleyError) (obj)));
            request.notifyListenerResponseNotUsable();
            return;
        }
        catch (Exception exception)
        {
            String s = exception.toString();
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Unhandled exception %s", new Object[] {
                s
            }), exception);
            VolleyError volleyerror = new VolleyError(exception);
            SystemClock.elapsedRealtime();
            mDelivery.postError(request, volleyerror);
            request.notifyListenerResponseNotUsable();
            return;
        }
        TrafficStats.setThreadStatsTag(request.mDefaultTrafficStatsTag);
        obj = mNetwork.performRequest(request);
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("network-http-complete", Thread.currentThread().getId());
        }
        if (((NetworkResponse) (obj)).notModified && request.hasHadResponseDelivered())
        {
            request.finish("not-modified");
            request.notifyListenerResponseNotUsable();
            return;
        }
        obj = request.parseNetworkResponse(((NetworkResponse) (obj)));
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("network-parse-complete", Thread.currentThread().getId());
        }
        if (request.mShouldCache && ((Response) (obj)).cacheEntry != null)
        {
            mCache.put(request.getCacheKey(), ((Response) (obj)).cacheEntry);
            if (VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add("network-cache-written", Thread.currentThread().getId());
            }
        }
        request.markDelivered();
        mDelivery.postResponse(request, ((Response) (obj)));
        request.notifyListenerResponseReceived(((Response) (obj)));
        return;
    }

    public final void run()
    {
        Process.setThreadPriority(10);
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
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]));
        } while (true);
    }
}
