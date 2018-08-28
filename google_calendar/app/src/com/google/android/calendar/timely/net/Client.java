// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import android.support.v4.util.LruCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.net:
//            BaseClient

public final class Client
    implements BaseClient
{

    public final DataProvider dataProvider;
    public final LruCache lastResult = new LruCache(1);
    public ListenableFuture runningRequest;

    public Client(DataProvider dataprovider)
    {
        dataProvider = dataprovider;
    }

    public final void injectResponse(Object obj, Object obj1)
    {
        CalendarExecutor.MAIN.checkOnThread();
        if (runningRequest != null)
        {
            runningRequest.cancel(true);
        }
        lastResult.put(obj, obj1);
    }

    public final ListenableFuture sendRequest(final Object request)
    {
        CalendarExecutor.MAIN.checkOnThread();
        if (runningRequest != null)
        {
            runningRequest.cancel(true);
        }
        Object obj = lastResult.get(request);
        if (obj != null)
        {
            if (obj == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
        }
        class .Lambda._cls0
            implements Callable
        {

            private final Client arg$1;
            private final Object arg$2;

            public final Object call()
            {
                Client client = arg$1;
                Object obj1 = arg$2;
                return client.dataProvider.apply(obj1);
            }

            .Lambda._cls0(Object obj)
            {
                arg$1 = Client.this;
                arg$2 = obj;
            }

            private class DataProvider
            {

                public abstract Object apply(Object obj)
                    throws Exception;
            }

        }

        runningRequest = (FluentFuture)CalendarExecutor.NET.submit(new .Lambda._cls0(request));
        obj = runningRequest;
        request = new _cls1();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (request == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (request))), calendarexecutor);
            return runningRequest;
        }
    }

    public final void wipeCache()
    {
        lastResult.trimToSize(-1);
    }

    private class _cls1
        implements FutureCallback
    {

        private final Client this$0;
        private final Object val$request;

        public final void onFailure(Throwable throwable)
        {
        }

        public final void onSuccess(Object obj)
        {
            lastResult.put(request, obj);
        }

        _cls1()
        {
            this$0 = Client.this;
            request = obj;
            super();
        }
    }

}
