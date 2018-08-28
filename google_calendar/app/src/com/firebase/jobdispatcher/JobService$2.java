// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayReceiver, JobCoder, JobService, IJobCallback

final class vice.Stub extends vice.Stub
{

    private final JobService this$0;

    public final void start(Bundle bundle, IJobCallback ijobcallback)
    {
        Object obj = GooglePlayReceiver.prefixedCoder.decode(bundle);
        if (obj == null)
        {
            Log.wtf("FJD.JobService", "start: unknown invocation provided");
            return;
        } else
        {
            bundle = JobService.this;
            obj = ((.Builder) (obj)).build();
            ((JobService) (bundle)).backgroundExecutor.execute(new itOfWork(4, bundle, ((JobParameters) (obj)), ijobcallback, null, null, false, 0));
            return;
        }
    }

    public final void stop(Bundle bundle, boolean flag)
    {
        Object obj = GooglePlayReceiver.prefixedCoder.decode(bundle);
        if (obj == null)
        {
            Log.wtf("FJD.JobService", "stop: unknown invocation provided");
            return;
        } else
        {
            bundle = JobService.this;
            obj = ((.Builder) (obj)).build();
            ((JobService) (bundle)).backgroundExecutor.execute(new itOfWork(5, bundle, ((JobParameters) (obj)), null, null, null, flag, 0));
            return;
        }
    }

    itOfWork()
    {
        this$0 = JobService.this;
        super();
    }
}
