// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;

// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

public final class arg._cls3
    implements Runnable
{

    private final FirebaseJobDispatcher arg$1;
    private final Job arg$2;
    private final Runnable arg$3;

    public final void run()
    {
        FutureJobService.lambda$scheduleJob$1$FutureJobService(arg$1, arg$2, arg$3);
    }

    public I(FirebaseJobDispatcher firebasejobdispatcher, Job job, Runnable runnable)
    {
        arg$1 = firebasejobdispatcher;
        arg$2 = job;
        arg$3 = runnable;
    }
}
