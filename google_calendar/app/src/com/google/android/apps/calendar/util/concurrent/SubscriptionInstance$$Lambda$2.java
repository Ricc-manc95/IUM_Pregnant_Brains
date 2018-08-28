// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            SubscriptionInstance

final class arg._cls2
    implements Runnable
{

    private final SubscriptionInstance arg$1;
    private final ListenableFutureTask arg$2;

    public final void run()
    {
        SubscriptionInstance subscriptioninstance = arg$1;
        ListenableFutureTask listenablefuturetask = arg$2;
        synchronized (subscriptioninstance.pending)
        {
            subscriptioninstance.pending.remove(listenablefuturetask);
        }
        return;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    (SubscriptionInstance subscriptioninstance, ListenableFutureTask listenablefuturetask)
    {
        arg$1 = subscriptioninstance;
        arg$2 = listenablefuturetask;
    }
}
