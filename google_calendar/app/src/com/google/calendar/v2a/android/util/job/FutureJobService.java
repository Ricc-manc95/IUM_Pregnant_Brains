// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import android.support.v4.util.SimpleArrayMap;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.apps.xplat.logging.LoggingApi;
import com.google.apps.xplat.logging.XLogLevel;
import com.google.apps.xplat.logging.XLogger;
import com.google.apps.xplat.tracing.AsyncTraceSection;
import com.google.apps.xplat.tracing.TracingApi;
import com.google.apps.xplat.tracing.XTracer;
import com.google.apps.xplat.tracing.types.Level;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TimeoutFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class FutureJobService extends JobService
{

    private static final ScheduledExecutorService TIMEOUT_EXECUTOR;
    private static boolean throwOnPreconditionFailures = true;
    private static final XTracer tracer = new XTracer(null);
    public final SimpleArrayMap currentJobs = new SimpleArrayMap();
    public final XLogger logger = new XLogger(getClass());

    public FutureJobService()
    {
    }

    private final boolean checkStateWtf(boolean flag)
    {
        if (!flag)
        {
            if (throwOnPreconditionFailures)
            {
                throw new IllegalStateException();
            }
            logger.getLoggingApi(XLogLevel.ERROR).withCause(new IllegalStateException()).log("Precondition failure");
        }
        return flag;
    }

    static final ListenableFuture lambda$callAsyncAfterJob$2$FutureJobService$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T0N6UBECD1M2R3CC5H6OP9R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0(AsyncCallable asynccallable)
        throws Exception
    {
        return asynccallable.call();
    }

    static final void lambda$scheduleJob$0$FutureJobService()
    {
    }

    static final void lambda$scheduleJob$1$FutureJobService(FirebaseJobDispatcher firebasejobdispatcher, Job job, Runnable runnable)
    {
        firebasejobdispatcher.mustSchedule(job);
        runnable.run();
    }

    public static void setThrowOnPreconditionFailures(boolean flag)
    {
        throwOnPreconditionFailures = flag;
    }

    public abstract ListenableFuture createFuture(JobParameters jobparameters);

    public void onBeforeStartJob(JobParameters jobparameters)
    {
    }

    public abstract JobStatus onFinishJob(JobParameters jobparameters, Object obj, Throwable throwable);

    final JobStatus onFinishJobInternal(JobParameters jobparameters, Object obj, Throwable throwable)
    {
        boolean flag;
        if (currentJobs.remove(jobparameters.getTag()) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        checkStateWtf(flag);
        logger.getLoggingApi(XLogLevel.DEBUG).log("onFinishJobInternal, tag=%s", jobparameters.getTag());
        logger.getLoggingApi(XLogLevel.VERBOSE).log(" extras=%s", jobparameters.getExtras());
        logger.getLoggingApi(XLogLevel.DEBUG).log(" result=%s, error=%s", obj, throwable);
        if (throwable != null)
        {
            logger.getLoggingApi(XLogLevel.VERBOSE).withCause(throwable).log("error");
        }
        try
        {
            obj = onFinishJob(jobparameters, obj, throwable);
            logger.getLoggingApi(XLogLevel.INFO).log("onFinishJob, tag=%s, status=%s", jobparameters.getTag(), obj);
        }
        // Misplaced declaration of an exception variable
        catch (JobParameters jobparameters)
        {
            logger.getLoggingApi(XLogLevel.DEBUG).withCause(jobparameters).log("Exception in onFinishJob. Treating as SOFT_ERROR");
            return JobStatus.SOFT_ERROR;
        }
        return ((JobStatus) (obj));
    }

    public final boolean onStartJob(JobParameters jobparameters)
    {
        boolean flag;
        if (!currentJobs.containsKey(jobparameters.getTag()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (checkStateWtf(flag))
        {
            onBeforeStartJob(jobparameters);
            logger.getLoggingApi(XLogLevel.INFO).log("onStartJob, tag=%s", jobparameters.getTag());
            logger.getLoggingApi(XLogLevel.VERBOSE).log(" extras=%s", jobparameters.getExtras());
            Object obj = tracer.tracingAt(Level.INFO).beginAsync("FutureJob").annotate("service", getClass().getSimpleName());
            Object obj1 = createFuture(jobparameters);
            Object obj2 = TimeUnit.MINUTES;
            ScheduledExecutorService scheduledexecutorservice = TIMEOUT_EXECUTOR;
            TimeoutFuture timeoutfuture = new TimeoutFuture(((ListenableFuture) (obj1)));
            com.google.common.util.concurrent.TimeoutFuture.Fire fire = new com.google.common.util.concurrent.TimeoutFuture.Fire(timeoutfuture);
            timeoutfuture.timer = scheduledexecutorservice.schedule(fire, 2L, ((TimeUnit) (obj2)));
            ((ListenableFuture) (obj1)).addListener(fire, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            obj = new JobFuture(jobparameters, ((AsyncTraceSection) (obj)).endWhen(timeoutfuture));
            currentJobs.put(jobparameters.getTag(), obj);
            jobparameters = ((JobFuture) (obj)).resultFuture;
            class JobFuture._cls1
                implements FutureCallback
            {

                private final JobFuture this$1;

                public final void onFailure(Throwable throwable)
                {
                    FutureJobService futurejobservice = _fld0;
                    JobParameters jobparameters1 = job;
                    futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("finishJob, tag=%s", jobparameters1.getTag());
                    boolean flag3;
                    if (futurejobservice.onFinishJobInternal(jobparameters1, null, throwable) == JobStatus.SOFT_ERROR)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("-> %s", Boolean.valueOf(flag3));
                    futurejobservice.jobFinished(jobparameters1, flag3);
                }

                public final void onSuccess(Object obj3)
                {
                    FutureJobService futurejobservice = _fld0;
                    JobParameters jobparameters1 = job;
                    futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("finishJob, tag=%s", jobparameters1.getTag());
                    boolean flag3;
                    if (futurejobservice.onFinishJobInternal(jobparameters1, obj3, null) == JobStatus.SOFT_ERROR)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    futurejobservice.logger.getLoggingApi(XLogLevel.DEBUG).log("-> %s", Boolean.valueOf(flag3));
                    futurejobservice.jobFinished(jobparameters1, flag3);
                }

            JobFuture._cls1()
            {
                this$1 = JobFuture.this;
                super();
            }
            }

            obj1 = ((JobFuture._cls1) (obj)). new JobFuture._cls1();
            obj2 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            jobparameters.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(jobparameters, ((FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
            class JobFuture..Lambda._cls0
                implements Runnable
            {

                private final JobFuture arg$1;

                public final void run()
                {
                    JobFuture jobfuture = arg$1;
                    jobfuture.resultFuture.setFuture(jobfuture.jobFuture);
                }

            JobFuture..Lambda._cls0(JobFuture jobfuture)
            {
                arg$1 = jobfuture;
            }
            }

            ((JobFuture) (obj)).jobFuture.addListener(new JobFuture..Lambda._cls0(((JobFuture) (obj))), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
            boolean flag2 = ((JobFuture) (obj)).resultFuture.isDone();
            jobparameters = logger.getLoggingApi(XLogLevel.DEBUG);
            boolean flag1;
            if (!flag2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            jobparameters.log("-> %s", Boolean.valueOf(flag1));
            if (!flag2)
            {
                return true;
            }
        }
        return false;
    }

    public final boolean onStopJob(JobParameters jobparameters)
    {
        boolean flag1 = true;
        logger.getLoggingApi(XLogLevel.INFO).log("onStopJob, tag=%s", jobparameters.getTag());
        logger.getLoggingApi(XLogLevel.VERBOSE).log(" extras=%s", jobparameters.getExtras());
        boolean flag;
        if (currentJobs.containsKey(jobparameters.getTag()))
        {
            JobFuture jobfuture = (JobFuture)currentJobs.get(jobparameters.getTag());
            flag = jobfuture.jobFuture.cancel(true);
            jobfuture.resultFuture.setFuture(jobfuture.jobFuture);
            logger.getLoggingApi(XLogLevel.DEBUG).log(" canceled=%s", Boolean.valueOf(flag));
            if (!currentJobs.containsKey(jobparameters.getTag()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            checkStateWtf(flag);
            flag = flag1;
        } else
        {
            logger.getLoggingApi(XLogLevel.DEBUG).log(" (was already finished)");
            flag = false;
        }
        logger.getLoggingApi(XLogLevel.DEBUG).log("-> %s", Boolean.valueOf(flag));
        return flag;
    }

    static 
    {
        TIMEOUT_EXECUTOR = CalendarExecutor.MAIN;
    }

    private class JobStatus extends Enum
    {

        private static final JobStatus $VALUES[];
        public static final JobStatus HARD_ERROR;
        public static final JobStatus SOFT_ERROR;
        public static final JobStatus SUCCESS;

        public static JobStatus[] values()
        {
            return (JobStatus[])$VALUES.clone();
        }

        static 
        {
            SUCCESS = new JobStatus("SUCCESS", 0);
            SOFT_ERROR = new JobStatus("SOFT_ERROR", 1);
            HARD_ERROR = new JobStatus("HARD_ERROR", 2);
            $VALUES = (new JobStatus[] {
                SUCCESS, SOFT_ERROR, HARD_ERROR
            });
        }

        private JobStatus(String s, int i)
        {
            super(s, i);
        }
    }


    private class JobFuture
    {

        public final JobParameters job;
        public final ListenableFuture jobFuture;
        public final SettableFuture resultFuture = new SettableFuture();
        public final FutureJobService this$0;

        JobFuture(JobParameters jobparameters, ListenableFuture listenablefuture)
        {
            this$0 = FutureJobService.this;
            super();
            job = jobparameters;
            jobFuture = listenablefuture;
        }
    }

}
