// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.net:
//            BaseClient, Client

public abstract class BaseClientFragment extends Fragment
    implements BaseClient
{

    public final Client client = new Client(new .Lambda._cls0());

    public BaseClientFragment()
    {
        class .Lambda._cls0
            implements Client.DataProvider
        {

            private final BaseClientFragment arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.retrieveData(obj);
            }

            .Lambda._cls0()
            {
                arg$1 = BaseClientFragment.this;
            }
        }

    }

    public final void injectResponse(Object obj, Object obj1)
    {
        Client client1 = client;
        CalendarExecutor.MAIN.checkOnThread();
        if (client1.runningRequest != null)
        {
            client1.runningRequest.cancel(true);
        }
        client1.lastResult.put(obj, obj1);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        super.mRetainInstance = true;
    }

    public final void onDestroy()
    {
        Client client1 = client;
        if (client1.runningRequest != null)
        {
            client1.runningRequest.cancel(true);
        }
        super.onDestroy();
    }

    public abstract Object retrieveData(Object obj)
        throws Exception;

    public final ListenableFuture sendRequest(Object obj)
    {
        return client.sendRequest(obj);
    }

    public final void wipeCache()
    {
        client.lastResult.evictAll();
    }
}
