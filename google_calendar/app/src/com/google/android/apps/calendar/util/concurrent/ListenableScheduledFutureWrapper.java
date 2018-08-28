// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            ListenableFutureWrapper

public class ListenableScheduledFutureWrapper extends ListenableFutureWrapper
    implements ListenableScheduledFuture
{

    private final ListenableScheduledFuture mFuture;

    protected ListenableScheduledFutureWrapper(ListenableScheduledFuture listenablescheduledfuture)
    {
        super(listenablescheduledfuture);
        mFuture = listenablescheduledfuture;
    }

    public int compareTo(Object obj)
    {
        obj = (Delayed)obj;
        return mFuture.compareTo(obj);
    }

    public long getDelay(TimeUnit timeunit)
    {
        return mFuture.getDelay(timeunit);
    }
}
