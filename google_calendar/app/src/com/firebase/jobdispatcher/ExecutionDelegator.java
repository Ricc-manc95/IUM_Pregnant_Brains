// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobInvocation, JobServiceConnection, ConstraintChecker, Driver, 
//            IJobCallback

final class ExecutionDelegator
{

    public static final SimpleArrayMap serviceConnections = new SimpleArrayMap();
    public final ConstraintChecker constraintChecker;
    public final Context context;
    public final Driver driver;
    public final IJobCallback execCallback = new _cls1();
    public final ScheduledExecutorService executorService;
    public final JobFinishedCallback jobFinishedCallback;

    ExecutionDelegator(ConstraintChecker constraintchecker, Context context1, Driver driver1, JobFinishedCallback jobfinishedcallback, ScheduledExecutorService scheduledexecutorservice)
    {
        constraintChecker = constraintchecker;
        context = context1;
        driver = driver1;
        executorService = scheduledexecutorservice;
        jobFinishedCallback = jobfinishedcallback;
    }

    static void stopJob(JobInvocation jobinvocation, boolean flag)
    {
        JobServiceConnection jobserviceconnection;
        synchronized (serviceConnections)
        {
            jobserviceconnection = (JobServiceConnection)serviceConnections.get(jobinvocation.service);
        }
        if (jobserviceconnection != null)
        {
            jobserviceconnection.onStop(jobinvocation, flag);
            if (jobserviceconnection.wasUnbound())
            {
                synchronized (serviceConnections)
                {
                    serviceConnections.remove(jobinvocation.service);
                }
            }
        }
        return;
        jobinvocation;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobinvocation;
        jobinvocation;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobinvocation;
    }

    final boolean doesServiceExist(String s)
    {
        boolean flag = false;
        try
        {
            s = context.getPackageManager().getServiceInfo(new ComponentName(context, s), 0);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return false;
        }
        if (s != null)
        {
            flag = true;
        }
        return flag;
    }

    final void onJobFinishedMessage(JobInvocation jobinvocation, int i)
    {
        JobServiceConnection jobserviceconnection;
        synchronized (serviceConnections)
        {
            jobserviceconnection = (JobServiceConnection)serviceConnections.get(jobinvocation.service);
        }
        if (jobserviceconnection != null)
        {
            jobserviceconnection.onJobFinished(jobinvocation);
            if (jobserviceconnection.wasUnbound())
            {
                synchronized (serviceConnections)
                {
                    serviceConnections.remove(jobinvocation.service);
                }
            }
        }
        jobFinishedCallback.onJobFinished(jobinvocation, i);
        return;
        jobinvocation;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobinvocation;
        jobinvocation;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobinvocation;
    }

    final boolean tryBindingToJobService(JobInvocation jobinvocation, JobServiceConnection jobserviceconnection)
    {
        Intent intent = (new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE")).setClassName(context, jobinvocation.service);
        boolean flag;
        try
        {
            flag = context.bindService(intent, jobserviceconnection, 1);
        }
        // Misplaced declaration of an exception variable
        catch (JobServiceConnection jobserviceconnection)
        {
            jobinvocation = jobinvocation.service;
            jobserviceconnection = String.valueOf(jobserviceconnection);
            Log.e("FJD.ExternalReceiver", (new StringBuilder(String.valueOf(jobinvocation).length() + 20 + String.valueOf(jobserviceconnection).length())).append("Failed to bind to ").append(jobinvocation).append(": ").append(jobserviceconnection).toString());
            return false;
        }
        return flag;
    }


    private class _cls1 extends IJobCallback.Stub
    {

        private final ExecutionDelegator this$0;

        public final void jobFinished(Bundle bundle, int i)
        {
            bundle = GooglePlayReceiver.prefixedCoder.decode(bundle);
            if (bundle == null)
            {
                Log.wtf("FJD.ExternalReceiver", "jobFinished: unknown invocation provided");
                return;
            } else
            {
                onJobFinishedMessage(bundle.build(), i);
                return;
            }
        }

        _cls1()
        {
            this$0 = ExecutionDelegator.this;
            super();
        }
    }


    private class JobFinishedCallback
    {

        public abstract void onJobFinished(JobInvocation jobinvocation, int i);
    }

}
