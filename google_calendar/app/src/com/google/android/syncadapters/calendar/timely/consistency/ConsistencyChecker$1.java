// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.android.calendarcommon2.LogUtils;
import java.util.Map;
import java.util.concurrent.Future;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            ConsistencyChecker, BackendEventFetcher, ProviderEventFetcher

public final class val.providerFetcher
    implements Runnable
{

    private final ConsistencyChecker this$0;
    private final BackendEventFetcher val$backendFetcher;
    private final Future val$backendFuture;
    private final ProviderEventFetcher val$providerFetcher;
    private final Future val$providerFuture;

    public final void run()
    {
        Map map;
        Map map1;
        port port;
        try
        {
            map = (Map)val$providerFuture.get();
        }
        catch (Exception exception)
        {
            LogUtils.e(ConsistencyChecker.TAG, exception, "Exception in Client Fetch", new Object[0]);
            exception = null;
        }
        try
        {
            map1 = (Map)val$backendFuture.get();
        }
        catch (Exception exception1)
        {
            LogUtils.e(ConsistencyChecker.TAG, exception1, "Exception in Backend Fetch", new Object[0]);
            exception1 = null;
        }
        if (map == null)
        {
            registerAttemptAsFailed("FailedClientFetch", 1L, 0x5265c00L);
        }
        if (map1 == null)
        {
            registerAttemptAsFailed("FailedBackendFetch", 1L, 0x5265c00L);
        }
        port = new port();
        port.requests.backendRequests = val$backendFetcher.executedRequests;
        port.requests.providerRequests = val$providerFetcher.executedRequests;
        compareEventsIfBothFetched(map, map1, port);
    }

    public port.Requests()
    {
        this$0 = final_consistencychecker;
        val$providerFuture = future;
        val$backendFuture = future1;
        val$backendFetcher = backendeventfetcher;
        val$providerFetcher = ProviderEventFetcher.this;
        super();
    }
}
