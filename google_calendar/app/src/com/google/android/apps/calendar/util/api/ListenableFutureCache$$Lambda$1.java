// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

// Referenced classes of package com.google.android.apps.calendar.util.api:
//            ListenableFutureCache

final class arg._cls3
    implements Runnable
{

    private final ListenableFutureCache arg$1;
    private final ListenableFuture arg$2;
    private final long arg$3;

    public final void run()
    {
        ListenableFutureCache listenablefuturecache;
        listenablefuturecache = arg$1;
        ListenableFuture listenablefuture = arg$2;
        long l1 = arg$3;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        LogUtils.d(listenablefuturecache.tag, "Loaded in %s", new Object[] {
            Long.valueOf(l - l1)
        });
        listenablefuturecache;
        JVM INSTR monitorenter ;
        if (listenablefuturecache.future == listenablefuture)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        listenablefuturecache;
        JVM INSTR monitorexit ;
        return;
        listenablefuturecache.result = listenablefuturecache.future.get();
        Collection collection = (Collection)listenablefuturecache.subscriptions.apply(listenablefuturecache.result);
_L1:
        listenablefuturecache;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        listenablefuturecache;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        LogUtils.e(listenablefuturecache.tag, ((Throwable) (obj)), "Unable to load value", new Object[0]);
        listenablefuturecache.future = null;
          goto _L1
    }

    (ListenableFutureCache listenablefuturecache, ListenableFuture listenablefuture, long l)
    {
        arg$1 = listenablefuturecache;
        arg$2 = listenablefuture;
        arg$3 = l;
    }
}
