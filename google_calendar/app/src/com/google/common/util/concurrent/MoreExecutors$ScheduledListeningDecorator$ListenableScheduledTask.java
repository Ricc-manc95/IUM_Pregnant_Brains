// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.common.util.concurrent:
//            ListenableScheduledFuture, ListenableFuture

final class scheduledDelegate extends scheduledDelegate
    implements ListenableScheduledFuture
{

    private final ScheduledFuture scheduledDelegate;

    public final boolean cancel(boolean flag)
    {
        boolean flag1 = super.(flag);
        if (flag1)
        {
            scheduledDelegate.cancel(flag);
        }
        return flag1;
    }

    public final int compareTo(Object obj)
    {
        obj = (Delayed)obj;
        return scheduledDelegate.compareTo(obj);
    }

    public final long getDelay(TimeUnit timeunit)
    {
        return scheduledDelegate.getDelay(timeunit);
    }

    public i(ListenableFuture listenablefuture, ScheduledFuture scheduledfuture)
    {
        super(listenablefuture);
        scheduledDelegate = scheduledfuture;
    }
}
