// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.text.format.DateUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobParameters, IJobCallback

public abstract class JobService extends Service
{

    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    public final ExecutorService backgroundExecutor;
    private final IRemoteJobService.Stub binder = new _cls2();
    private final SimpleArrayMap runningJobs = new SimpleArrayMap(1);

    public JobService()
    {
        backgroundExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new _cls1());
    }

    protected final void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
label0:
        {
            synchronized (runningJobs)
            {
                if (!runningJobs.isEmpty())
                {
                    break label0;
                }
                printwriter.println("No running jobs");
            }
            return;
        }
        long l;
        l = SystemClock.elapsedRealtime();
        printwriter.println("Running jobs:");
        int i = 0;
_L2:
        if (i >= runningJobs.size())
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = (JobCallback)runningJobs.get(runningJobs.mArray[i << 1]);
        as = JSONObject.quote(((JobCallback) (obj)).job.getTag());
        obj = DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(l - ((JobCallback) (obj)).startedAtElapsed));
        printwriter.println((new StringBuilder(String.valueOf(as).length() + 28 + String.valueOf(obj).length())).append("    * ").append(as).append(" has been running for ").append(((String) (obj))).toString());
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        filedescriptor;
        JVM INSTR monitorexit ;
        return;
        printwriter;
        filedescriptor;
        JVM INSTR monitorexit ;
        throw printwriter;
    }

    final void handleOnUnbindEventImpl$51662RJ4E9NMIP1FCDNMST35DPQ2UIBEEHIMST1R55B0____0()
    {
        SimpleArrayMap simplearraymap = runningJobs;
        simplearraymap;
        JVM INSTR monitorenter ;
        int i = runningJobs.size() - 1;
_L2:
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        JobCallback jobcallback = (JobCallback)runningJobs.remove(runningJobs.mArray[i << 1]);
        if (jobcallback == null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        mainHandler.post(new UnitOfWork(null, null, jobcallback, null, true, 2));
        break MISSING_BLOCK_LABEL_80;
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw exception;
        i--;
        if (true) goto _L2; else goto _L1
_L1:
    }

    final void handleStartJobRequestImpl(JobParameters jobparameters, IJobCallback ijobcallback)
    {
label0:
        {
            synchronized (runningJobs)
            {
                if (!runningJobs.containsKey(jobparameters.getTag()))
                {
                    break label0;
                }
                Log.w("FJD.JobService", String.format(Locale.US, "Job with tag = %s was already running.", new Object[] {
                    jobparameters.getTag()
                }));
            }
            return;
        }
        runningJobs.put(jobparameters.getTag(), new JobCallback(jobparameters, ijobcallback, SystemClock.elapsedRealtime()));
        simplearraymap;
        JVM INSTR monitorexit ;
        mainHandler.post(new UnitOfWork(jobparameters, null, null, null, false, 0));
        return;
        jobparameters;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobparameters;
    }

    final void handleStopJobRequestImpl(JobParameters jobparameters, boolean flag)
    {
        SimpleArrayMap simplearraymap = runningJobs;
        simplearraymap;
        JVM INSTR monitorenter ;
        jobparameters = (JobCallback)runningJobs.remove(jobparameters.getTag());
        if (jobparameters != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
        mainHandler.post(new UnitOfWork(null, null, jobparameters, null, flag, 0));
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
        jobparameters;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobparameters;
    }

    public final void jobFinished(JobParameters jobparameters, boolean flag)
    {
        if (jobparameters == null)
        {
            Log.e("FJD.JobService", "jobFinished called with a null JobParameters");
            return;
        }
        ExecutorService executorservice = backgroundExecutor;
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        executorservice.execute(new UnitOfWork(jobparameters, null, null, null, false, i));
    }

    public final IBinder onBind(Intent intent)
    {
        return binder;
    }

    public final void onStart(Intent intent, int i)
    {
    }

    public final int onStartCommand(Intent intent, int i, int j)
    {
        stopSelf(j);
        return 2;
    }

    public abstract boolean onStartJob(JobParameters jobparameters);

    public abstract boolean onStopJob(JobParameters jobparameters);

    public final boolean onUnbind(Intent intent)
    {
        backgroundExecutor.execute(new UnitOfWork(null, null, null, intent, false, 0));
        return super.onUnbind(intent);
    }

    final void removeAndFinishJobWithResultImpl(JobParameters jobparameters, int i)
    {
        SimpleArrayMap simplearraymap = runningJobs;
        simplearraymap;
        JVM INSTR monitorenter ;
        jobparameters = (JobCallback)runningJobs.remove(jobparameters.getTag());
        if (jobparameters == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        jobparameters.sendResult(i);
        simplearraymap;
        JVM INSTR monitorexit ;
        return;
        jobparameters;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw jobparameters;
    }


    private class _cls1
        implements ThreadFactory
    {

        private final JobService this$0;

        public final Thread newThread(Runnable runnable)
        {
            String s = String.valueOf("FJD.JobService ");
            String s1 = String.valueOf(getClass().getName());
            if (s1.length() != 0)
            {
                s = s.concat(s1);
            } else
            {
                s = new String(s);
            }
            return new Thread(runnable, s);
        }

        _cls1()
        {
            this$0 = JobService.this;
            super();
        }
    }


    private class _cls2 extends IRemoteJobService.Stub
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
                obj = ((JobInvocation.Builder) (obj)).build();
                ((JobService) (bundle)).backgroundExecutor.execute(bundle. new UnitOfWork(((JobParameters) (obj)), ijobcallback, null, null, false, 0));
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
                obj = ((JobInvocation.Builder) (obj)).build();
                ((JobService) (bundle)).backgroundExecutor.execute(bundle. new UnitOfWork(((JobParameters) (obj)), null, null, null, flag, 0));
                return;
            }
        }

        _cls2()
        {
            this$0 = JobService.this;
            super();
        }
    }


    private class JobCallback
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

        JobCallback(JobParameters jobparameters, IJobCallback ijobcallback, long l)
        {
            job = jobparameters;
            remoteCallback = ijobcallback;
            startedAtElapsed = l;
        }
    }


    private class UnitOfWork
        implements Runnable
    {

        private final boolean boolValue;
        private final JobCallback jobCallback;
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
        //                       1 58
        //                       2 102
        //                       3 173
        //                       4 188
        //                       5 204
        //                       6 220
        //                       7 232;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new AssertionError("unreachable");
_L2:
            JobService jobservice = jobService;
            JobParameters jobparameters = jobParameters;
            if (!jobservice.onStartJob(jobparameters))
            {
                jobservice.backgroundExecutor.execute(jobservice. new UnitOfWork(jobparameters, null, null, null, false, 0));
            }
_L10:
            return;
_L3:
            JobCallback jobcallback;
            Object obj;
            int i;
            boolean flag;
            boolean flag1;
            obj = jobService;
            jobcallback = jobCallback;
            flag = boolValue;
            i = terminatingResult;
            flag1 = ((JobService) (obj)).onStopJob(jobcallback.job);
            if (!flag) goto _L10; else goto _L9
_L9:
            obj = ((JobService) (obj)).backgroundExecutor;
            if (flag1)
            {
                i = 1;
            }
            ((ExecutorService) (obj)).execute(null. new UnitOfWork(null, null, jobcallback, null, false, i));
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

        UnitOfWork(JobParameters jobparameters, IJobCallback ijobcallback, JobCallback jobcallback, Intent intent, boolean flag, 
                int j)
        {
            workType = final_i;
            jobService = JobService.this;
            jobParameters = jobparameters;
            remoteJobCallback = ijobcallback;
            jobCallback = jobcallback;
            unbindIntent = intent;
            boolValue = flag;
            terminatingResult = j;
        }
    }

}
