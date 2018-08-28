// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayReceiver, JobCoder, IJobCallback, JobParameters

final class startedAtElapsed
{

    public final JobParameters job;
    private final IJobCallback remoteCallback;
    public final long startedAtElapsed;

    final void sendResult(int i)
    {
        try
        {
            remoteCallback.jobFinished(GooglePlayReceiver.prefixedCoder.encode(job, new Bundle()), i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.e("FJD.JobService", "Failed to send result to driver", remoteexception);
        }
    }

    (JobParameters jobparameters, IJobCallback ijobcallback, long l)
    {
        job = jobparameters;
        remoteCallback = ijobcallback;
        startedAtElapsed = l;
    }
}
