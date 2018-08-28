// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

public final class arg._cls3
    implements AsyncCallable
{

    private final FutureJobService arg$1;
    private final String arg$2;
    private final AsyncCallable arg$3;

    public final ListenableFuture call()
    {
        Object obj = arg$1;
        String s = arg$2;
        AsyncCallable asynccallable = arg$3;
        obj = (arg._cls3)((FutureJobService) (obj)).currentJobs.get(s);
        if (obj == null)
        {
            return asynccallable.call();
        } else
        {
            return AbstractTransformFuture.create(((s) (obj)).resultFuture, new <init>(asynccallable), new com.google.android.apps.calendar.util.concurrent.<init>(CalendarExecutor.MAIN));
        }
    }

    public ambda._cls0(FutureJobService futurejobservice, String s, AsyncCallable asynccallable)
    {
        arg$1 = futurejobservice;
        arg$2 = s;
        arg$3 = asynccallable;
    }
}
