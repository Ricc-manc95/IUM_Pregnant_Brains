// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.content.Intent;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobService, JobParameters, IJobCallback

final class terminatingResult
    implements Runnable
{

    private final boolean boolValue;
    private final  jobCallback;
    private final JobParameters jobParameters;
    private final JobService jobService;
    private final IJobCallback remoteJobCallback;
    private final int terminatingResult;
    private final Intent unbindIntent;
    private final int workType;

    public final void run()
    {
        workType;
        JVM INSTR tableswitch 1 7: default 48
    //                   1 58
    //                   2 102
    //                   3 173
    //                   4 188
    //                   5 204
    //                   6 220
    //                   7 232;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new AssertionError("unreachable");
_L2:
        JobService jobservice = jobService;
        JobParameters jobparameters = jobParameters;
        if (!jobservice.onStartJob(jobparameters))
        {
            jobservice.backgroundExecutor.execute(new <init>(7, jobservice, jobparameters, null, null, null, false, 0));
        }
_L10:
        return;
_L3:
         ;
        Object obj;
        int i;
        boolean flag;
        boolean flag1;
        obj = jobService;
         = jobCallback;
        flag = boolValue;
        i = terminatingResult;
        flag1 = ((JobService) (obj)).onStopJob(.job);
        if (!flag) goto _L10; else goto _L9
_L9:
        obj = ((JobService) (obj)).backgroundExecutor;
        if (flag1)
        {
            i = 1;
        }
        ((ExecutorService) (obj)).execute(new <init>(6, null, null, null, , null, false, i));
        return;
_L4:
        JobService jobservice1 = jobService;
        Intent intent = unbindIntent;
        jobservice1.handleOnUnbindEventImpl$51662RJ4E9NMIP1FCDNMST35DPQ2UIBEEHIMST1R55B0____0();
        return;
_L5:
        jobService.handleStartJobRequestImpl(jobParameters, remoteJobCallback);
        return;
_L6:
        jobService.handleStopJobRequestImpl(jobParameters, boolValue);
        return;
_L7:
        jobCallback.sendResult(terminatingResult);
        return;
_L8:
        jobService.removeAndFinishJobWithResultImpl(jobParameters, terminatingResult);
        return;
    }

    (int i, JobService jobservice, JobParameters jobparameters, IJobCallback ijobcallback,  , Intent intent, boolean flag, 
            int j)
    {
        workType = i;
        jobService = jobservice;
        jobParameters = jobparameters;
        remoteJobCallback = ijobcallback;
        jobCallback = ;
        unbindIntent = intent;
        boolValue = flag;
        terminatingResult = j;
    }
}
