// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.util.Pair;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobCoder, GooglePlayCallbackExtractor, GooglePlayDriver, GooglePlayMessageHandler, 
//            ValidationEnforcer, Driver, TriggerReason, JobInvocation, 
//            JobCallback, ExecutionDelegator, ConstraintChecker, JobParameters, 
//            JobValidator, Job

public class GooglePlayReceiver extends Service
    implements ExecutionDelegator.JobFinishedCallback
{

    public static final SimpleArrayMap callbacks = new SimpleArrayMap(1);
    public static final JobCoder prefixedCoder = new JobCoder("com.firebase.jobdispatcher.");
    private final GooglePlayCallbackExtractor callbackExtractor = new GooglePlayCallbackExtractor();
    private Driver driver;
    private ExecutionDelegator executionDelegator;
    private int latestStartId;
    private Messenger serviceMessenger;
    private ValidationEnforcer validationEnforcer;

    public GooglePlayReceiver()
    {
    }

    private final Driver getGooglePlayDriver()
    {
        this;
        JVM INSTR monitorenter ;
        Driver driver1;
        if (driver == null)
        {
            driver = new GooglePlayDriver(getApplicationContext());
        }
        driver1 = driver;
        this;
        JVM INSTR monitorexit ;
        return driver1;
        Exception exception;
        exception;
        throw exception;
    }

    private final Messenger getServiceMessenger()
    {
        this;
        JVM INSTR monitorenter ;
        Messenger messenger;
        if (serviceMessenger == null)
        {
            serviceMessenger = new Messenger(new GooglePlayMessageHandler(Looper.getMainLooper(), this));
        }
        messenger = serviceMessenger;
        this;
        JVM INSTR monitorexit ;
        return messenger;
        Exception exception;
        exception;
        throw exception;
    }

    private final ValidationEnforcer getValidationEnforcer()
    {
        this;
        JVM INSTR monitorenter ;
        ValidationEnforcer validationenforcer;
        if (validationEnforcer == null)
        {
            validationEnforcer = new ValidationEnforcer(getGooglePlayDriver().getValidator());
        }
        validationenforcer = validationEnforcer;
        this;
        JVM INSTR monitorexit ;
        return validationenforcer;
        Exception exception;
        exception;
        throw exception;
    }

    static JobInvocation prepareJob(JobCallback jobcallback, Bundle bundle)
    {
        Object obj = prefixedCoder;
        if (bundle == null)
        {
            Log.e("FJD.ExternalReceiver", "Unexpected null Bundle provided");
            bundle = null;
        } else
        {
            Bundle bundle1 = bundle.getBundle("extras");
            if (bundle1 == null)
            {
                bundle = null;
            } else
            {
                obj = ((JobCoder) (obj)).decode(bundle1);
                bundle = bundle.getParcelableArrayList("triggered_uris");
                if (bundle != null)
                {
                    obj.triggerReason = new TriggerReason(bundle);
                }
                bundle = ((JobInvocation.Builder) (obj)).build();
            }
        }
        if (bundle == null)
        {
            Log.e("FJD.GooglePlayReceiver", "unable to decode job");
            sendResultSafely(jobcallback, 2);
            return null;
        }
        SimpleArrayMap simplearraymap2 = callbacks;
        simplearraymap2;
        JVM INSTR monitorenter ;
        SimpleArrayMap simplearraymap1 = (SimpleArrayMap)callbacks.get(((JobInvocation) (bundle)).service);
        SimpleArrayMap simplearraymap;
        simplearraymap = simplearraymap1;
        if (simplearraymap1 != null)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        simplearraymap = new SimpleArrayMap(1);
        callbacks.put(((JobInvocation) (bundle)).service, simplearraymap);
        simplearraymap.put(((JobInvocation) (bundle)).tag, jobcallback);
        simplearraymap2;
        JVM INSTR monitorexit ;
        return bundle;
        jobcallback;
        simplearraymap2;
        JVM INSTR monitorexit ;
        throw jobcallback;
    }

    private static void sendResultSafely(JobCallback jobcallback, int i)
    {
        try
        {
            jobcallback.jobFinished(i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (JobCallback jobcallback)
        {
            jobcallback = String.valueOf(jobcallback.getMessage());
        }
        if (jobcallback.length() != 0)
        {
            jobcallback = "Encountered error running callback: ".concat(jobcallback);
        } else
        {
            jobcallback = new String("Encountered error running callback: ");
        }
        Log.e("FJD.GooglePlayReceiver", jobcallback);
    }

    final ExecutionDelegator getExecutionDelegator()
    {
        this;
        JVM INSTR monitorenter ;
        ExecutionDelegator executiondelegator;
        if (executionDelegator == null)
        {
            executionDelegator = new ExecutionDelegator(new ConstraintChecker(getApplicationContext()), this, getGooglePlayDriver(), this, Executors.newSingleThreadScheduledExecutor());
        }
        executiondelegator = executionDelegator;
        this;
        JVM INSTR monitorexit ;
        return executiondelegator;
        Exception exception;
        exception;
        throw exception;
    }

    public IBinder onBind(Intent intent)
    {
        if (intent == null || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction()))
        {
            return null;
        } else
        {
            return getServiceMessenger().getBinder();
        }
    }

    public final void onJobFinished(JobInvocation jobinvocation, int i)
    {
        obj = callbacks;
        obj;
        JVM INSTR monitorenter ;
        SimpleArrayMap simplearraymap = (SimpleArrayMap)callbacks.get(jobinvocation.service);
        if (simplearraymap != null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        obj;
        JVM INSTR monitorexit ;
        synchronized (callbacks)
        {
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return;
        obj;
        jobinvocation;
        JVM INSTR monitorexit ;
        throw obj;
        JobCallback jobcallback = (JobCallback)simplearraymap.remove(jobinvocation.tag);
        if (jobcallback != null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        obj;
        JVM INSTR monitorexit ;
        synchronized (callbacks)
        {
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return;
        obj;
        jobinvocation;
        JVM INSTR monitorexit ;
        throw obj;
        if (simplearraymap.isEmpty())
        {
            callbacks.remove(jobinvocation.service);
        }
        obj;
        JVM INSTR monitorexit ;
        boolean flag;
        if (jobinvocation.isRecurring() && (jobinvocation.getTrigger() instanceof JobTrigger.ContentUriTrigger) && i != 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        jobinvocation = new Job.Builder(getValidationEnforcer(), jobinvocation);
        jobinvocation.replaceCurrent = true;
        obj = ((Job.Builder) (jobinvocation)).validator.validator.validate(jobinvocation);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_247;
        }
        throw new ValidationEnforcer.ValidationException("JobParameters is invalid", ((java.util.List) (obj)));
        obj;
        synchronized (callbacks)
        {
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        throw obj;
        jobinvocation;
        obj;
        JVM INSTR monitorexit ;
        throw jobinvocation;
        jobinvocation = new Job(jobinvocation);
        getGooglePlayDriver().schedule(jobinvocation);
_L4:
        synchronized (callbacks)
        {
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return;
        exception;
        jobinvocation;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        sendResultSafely(jobcallback, i);
        if (true) goto _L4; else goto _L3
_L3:
        exception1;
        jobinvocation;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public final int onStartCommand(Intent intent, int i, int j)
    {
        obj = null;
        super.onStartCommand(intent, i, j);
        if (intent != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        Log.w("FJD.GooglePlayReceiver", "Null Intent passed, terminating");
        synchronized (callbacks)
        {
            latestStartId = j;
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return 2;
        obj;
        intent;
        JVM INSTR monitorexit ;
        throw obj;
        Object obj1 = intent.getAction();
        if (!"com.google.android.gms.gcm.ACTION_TASK_READY".equals(obj1)) goto _L2; else goto _L1
_L1:
        obj1 = getExecutionDelegator();
        intent = intent.getExtras();
        if (intent != null) goto _L4; else goto _L3
_L3:
        Log.e("FJD.GooglePlayReceiver", "No data provided, terminating");
_L7:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        ((ExecutionDelegator) (obj1)).executorService.execute(new ExecutionDelegator._cls2(((ExecutionDelegator) (obj1)), ((JobInvocation) (obj))));
        synchronized (callbacks)
        {
            latestStartId = j;
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return 2;
        obj;
        intent;
        JVM INSTR monitorexit ;
        throw obj;
_L4:
        if (intent != null)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        Log.e("FJD.GooglePlayReceiver", "No callback received, terminating");
        intent = null;
_L5:
        if (intent == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = prepareJob((JobCallback)((Pair) (intent)).first, (Bundle)((Pair) (intent)).second);
        continue; /* Loop/switch isn't completed */
        intent = GooglePlayCallbackExtractor.extractWrappedBinderFromParcel(intent);
          goto _L5
_L2:
        boolean flag = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(obj1);
        if (flag)
        {
            synchronized (callbacks)
            {
                latestStartId = j;
                if (callbacks.isEmpty())
                {
                    stopSelf(latestStartId);
                }
            }
            return 2;
        }
        break MISSING_BLOCK_LABEL_271;
        exception;
        intent;
        JVM INSTR monitorexit ;
        throw exception;
        Log.e("FJD.GooglePlayReceiver", "Unknown action received, terminating");
        synchronized (callbacks)
        {
            latestStartId = j;
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        return 2;
        exception1;
        intent;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception2;
        exception2;
        synchronized (callbacks)
        {
            latestStartId = j;
            if (callbacks.isEmpty())
            {
                stopSelf(latestStartId);
            }
        }
        throw exception2;
        exception3;
        intent;
        JVM INSTR monitorexit ;
        throw exception3;
        if (true) goto _L7; else goto _L6
_L6:
    }

}
