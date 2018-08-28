// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import com.firebase.jobdispatcher.JobParameters;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

final class jobFuture
{

    public final JobParameters job;
    public final ListenableFuture jobFuture;
    public final SettableFuture resultFuture = new SettableFuture();
    public final FutureJobService this$0;

    I(JobParameters jobparameters, ListenableFuture listenablefuture)
    {
        this$0 = FutureJobService.this;
        super();
        job = jobparameters;
        jobFuture = listenablefuture;
    }
}
