// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.firebase.jobdispatcher:
//            ExecutionDelegator, JobInvocation, Constraint, ConstraintChecker, 
//            JobServiceConnection, Driver

final class val.jobInvocation
    implements Runnable
{

    private final ExecutionDelegator this$0;
    private final JobInvocation val$jobInvocation;

    public final void run()
    {
        Object obj;
        ExecutionDelegator executiondelegator;
        JobInvocation jobinvocation;
        boolean flag;
        int i;
        executiondelegator = ExecutionDelegator.this;
        jobinvocation = val$jobInvocation;
        obj = executiondelegator.constraintChecker;
        i = Constraint.compact(jobinvocation.constraints);
        if ((i & 2) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if ((i & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L3; else goto _L2
_L2:
        flag = true;
_L7:
        if (!flag)
        {
            flag = true;
        } else
        {
label0:
            {
                obj = (ConnectivityManager)((ConstraintChecker) (obj)).context.getSystemService("connectivity");
                NetworkInfo networkinfo = ((ConnectivityManager) (obj)).getActiveNetworkInfo();
                boolean flag1;
                if (networkinfo == null)
                {
                    flag1 = false;
                } else
                {
                    flag1 = networkinfo.isConnected();
                }
                if (!flag1)
                {
                    break label0;
                }
                if ((i & 1) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (!((ConnectivityManager) (obj)).isActiveNetworkMetered())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label0;
                    }
                }
                flag = true;
            }
        }
_L5:
        if (!flag)
        {
            executiondelegator.jobFinishedCallback.onJobFinished(jobinvocation, 1);
            return;
        }
        break MISSING_BLOCK_LABEL_196;
_L3:
        flag = false;
        if (true)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        if (true) goto _L7; else goto _L6
_L6:
        SimpleArrayMap simplearraymap = ExecutionDelegator.serviceConnections;
        simplearraymap;
        JVM INSTR monitorenter ;
        obj = (JobServiceConnection)ExecutionDelegator.serviceConnections.get(jobinvocation.service);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        ((JobServiceConnection) (obj)).startJob(jobinvocation);
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw exception;
        JobServiceConnection jobserviceconnection;
        jobserviceconnection = new JobServiceConnection(executiondelegator.execCallback, executiondelegator.context);
        ExecutionDelegator.serviceConnections.put(jobinvocation.service, jobserviceconnection);
        jobserviceconnection.startJob(jobinvocation);
        if (!executiondelegator.tryBindingToJobService(jobinvocation, jobserviceconnection)) goto _L9; else goto _L8
_L8:
        exception = new <init>(jobserviceconnection);
        executiondelegator.executorService.schedule(exception, 18L, TimeUnit.SECONDS);
_L13:
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
_L9:
        exception = String.valueOf(jobinvocation.service);
        if (exception.length() == 0) goto _L11; else goto _L10
_L10:
        exception = "Unable to bind to ".concat(exception);
_L14:
        Log.e("FJD.ExternalReceiver", exception);
        jobserviceconnection.unbind();
        if (executiondelegator.doesServiceExist(jobinvocation.service)) goto _L13; else goto _L12
_L12:
        exception = String.valueOf(jobinvocation.tag);
        if (exception.length() == 0)
        {
            break MISSING_BLOCK_LABEL_420;
        }
        exception = "Canceling job for removed service: ".concat(exception);
_L15:
        Log.w("FJD.ExternalReceiver", exception);
        executiondelegator.driver.cancel(jobinvocation.tag);
          goto _L13
_L11:
        exception = new String("Unable to bind to ");
          goto _L14
        exception = new String("Canceling job for removed service: ");
          goto _L15
    }

    bFinishedCallback()
    {
        this$0 = final_executiondelegator;
        val$jobInvocation = JobInvocation.this;
        super();
    }
}
