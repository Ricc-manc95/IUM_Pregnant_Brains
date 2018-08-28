// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayReceiver, JobCoder, IJobCallback, JobInvocation, 
//            IRemoteJobService, JobParameters

final class JobServiceConnection
    implements ServiceConnection
{

    private IRemoteJobService binder;
    private final IJobCallback callback;
    private final Context context;
    private final Map jobStatuses = new HashMap();
    private boolean wasUnbound;

    JobServiceConnection(IJobCallback ijobcallback, Context context1)
    {
        wasUnbound = false;
        callback = ijobcallback;
        context = context1;
    }

    private final boolean isConnected()
    {
        this;
        JVM INSTR monitorenter ;
        IRemoteJobService iremotejobservice = binder;
        boolean flag;
        if (iremotejobservice != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private final void requestRetryForJob(JobInvocation jobinvocation)
    {
        try
        {
            callback.jobFinished(GooglePlayReceiver.prefixedCoder.encode(jobinvocation, new Bundle()), 1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            jobinvocation = jobinvocation.tag;
            String s = String.valueOf(remoteexception);
            Log.e("FJD.ExternalReceiver", (new StringBuilder(String.valueOf(jobinvocation).length() + 31 + String.valueOf(s).length())).append("Error sending result for job ").append(jobinvocation).append(": ").append(s).toString());
            return;
        }
    }

    private final void stopJob(boolean flag, JobInvocation jobinvocation)
    {
        this;
        JVM INSTR monitorenter ;
        binder.stop(GooglePlayReceiver.prefixedCoder.encode(jobinvocation, new Bundle()), flag);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        jobinvocation;
        Log.e("FJD.ExternalReceiver", "Failed to stop a job", jobinvocation);
        unbind();
        if (true) goto _L2; else goto _L1
_L1:
        jobinvocation;
        throw jobinvocation;
    }

    final void enforceInitialBindTimeout()
    {
        this;
        JVM INSTR monitorenter ;
        if (!isConnected() && !wasUnbound())
        {
            Log.w("FJD.ExternalReceiver", "Binder connection to JobService timed out");
            unbind();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void onJobFinished(JobInvocation jobinvocation)
    {
        this;
        JVM INSTR monitorenter ;
        jobStatuses.remove(jobinvocation);
        if (jobStatuses.isEmpty())
        {
            unbind();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        jobinvocation;
        throw jobinvocation;
    }

    public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        this;
        JVM INSTR monitorenter ;
        if (!wasUnbound()) goto _L2; else goto _L1
_L1:
        Log.w("FJD.ExternalReceiver", "Connection have been used already.");
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (ibinder != null) goto _L4; else goto _L3
_L3:
        componentname = null;
_L8:
        Iterator iterator;
        binder = componentname;
        ibinder = new HashSet();
        iterator = jobStatuses.entrySet().iterator();
_L6:
        boolean flag;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_249;
            }
            componentname = (java.util.Map.Entry)iterator.next();
            flag = Boolean.FALSE.equals(componentname.getValue());
        } while (!flag);
        IRemoteJobService iremotejobservice = binder;
        JobParameters jobparameters = (JobParameters)componentname.getKey();
        iremotejobservice.start(GooglePlayReceiver.prefixedCoder.encode(jobparameters, new Bundle()), callback);
        ibinder.add((JobInvocation)componentname.getKey());
        if (true) goto _L6; else goto _L5
_L5:
        ibinder;
        componentname = String.valueOf(componentname.getKey());
        Log.e("FJD.ExternalReceiver", (new StringBuilder(String.valueOf(componentname).length() + 20)).append("Failed to start job ").append(componentname).toString(), ibinder);
        unbind();
          goto _L7
        componentname;
        throw componentname;
_L4:
label0:
        {
            componentname = ibinder.queryLocalInterface("com.firebase.jobdispatcher.IRemoteJobService");
            if (!(componentname instanceof IRemoteJobService))
            {
                break label0;
            }
            componentname = (IRemoteJobService)componentname;
        }
          goto _L8
        componentname = new IRemoteJobService.Stub.Proxy(ibinder);
          goto _L8
        componentname = ibinder.iterator();
        while (componentname.hasNext()) 
        {
            ibinder = (JobInvocation)componentname.next();
            jobStatuses.put(ibinder, Boolean.valueOf(true));
        }
          goto _L7
    }

    public final void onServiceDisconnected(ComponentName componentname)
    {
        this;
        JVM INSTR monitorenter ;
        unbind();
        this;
        JVM INSTR monitorexit ;
        return;
        componentname;
        throw componentname;
    }

    final void onStop(JobInvocation jobinvocation, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (wasUnbound()) goto _L2; else goto _L1
_L1:
        if (Boolean.TRUE.equals(jobStatuses.remove(jobinvocation)) && isConnected())
        {
            stopJob(flag, jobinvocation);
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        if (jobStatuses.isEmpty())
        {
            unbind();
        }
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Log.w("FJD.ExternalReceiver", "Can't send stop request because service was unbound.");
        if (true) goto _L4; else goto _L3
_L3:
        jobinvocation;
        throw jobinvocation;
    }

    final boolean startJob(JobInvocation jobinvocation)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if (wasUnbound())
        {
            requestRetryForJob(jobinvocation);
        }
        flag = isConnected();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        Boolean boolean1 = (Boolean)jobStatuses.get(jobinvocation);
        if (Boolean.TRUE.equals(boolean1))
        {
            String s = String.valueOf(jobinvocation);
            Log.w("FJD.ExternalReceiver", (new StringBuilder(String.valueOf(s).length() + 54)).append("Received an execution request for already running job ").append(s).toString());
            stopJob(false, jobinvocation);
        }
        binder.start(GooglePlayReceiver.prefixedCoder.encode(jobinvocation, new Bundle()), callback);
        jobStatuses.put(jobinvocation, Boolean.valueOf(flag));
_L2:
        this;
        JVM INSTR monitorexit ;
        return flag;
        RemoteException remoteexception;
        remoteexception;
        jobinvocation = String.valueOf(jobinvocation);
        Log.e("FJD.ExternalReceiver", (new StringBuilder(String.valueOf(jobinvocation).length() + 24)).append("Failed to start the job ").append(jobinvocation).toString(), remoteexception);
        unbind();
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
        jobinvocation;
        throw jobinvocation;
    }

    final void unbind()
    {
        this;
        JVM INSTR monitorenter ;
        if (wasUnbound()) goto _L2; else goto _L1
_L1:
        binder = null;
        wasUnbound = true;
        context.unbindService(this);
_L3:
        Object obj;
        obj = new ArrayList(jobStatuses.size());
        for (Iterator iterator = jobStatuses.keySet().iterator(); iterator.hasNext(); iterator.remove())
        {
            ((List) (obj)).add((JobInvocation)iterator.next());
        }

        break MISSING_BLOCK_LABEL_146;
        obj;
        throw obj;
        obj;
        obj = String.valueOf(((IllegalArgumentException) (obj)).getMessage());
        if (((String) (obj)).length() == 0)
        {
            break MISSING_BLOCK_LABEL_132;
        }
        obj = "Error unbinding service: ".concat(((String) (obj)));
_L4:
        Log.w("FJD.ExternalReceiver", ((String) (obj)));
          goto _L3
        obj = new String("Error unbinding service: ");
          goto _L4
        int j;
        obj = (ArrayList)obj;
        j = ((ArrayList) (obj)).size();
        int i = 0;
_L5:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = ((ArrayList) (obj)).get(i);
        i++;
        requestRetryForJob((JobInvocation)obj1);
        if (true) goto _L5; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
    }

    final boolean wasUnbound()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = wasUnbound;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }
}
