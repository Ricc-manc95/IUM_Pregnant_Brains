// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.inject.ServiceInjector;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJob;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.Counter3;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Provider;

public final class GrowthKitJobService extends JobService
{
    public static interface GrowthKitJobServiceSubcomponent
        extends ServiceInjector
    {
    }


    public static final Logger logger = new Logger();
    public final Map currentlyRunningJobs = new HashMap();
    public Provider enableFlag;
    public FirebaseJobDispatcher firebaseJobDispatcher;
    private boolean initialized;
    public Counter3 jobCounter;
    public Lazy jobs;
    public String packageName;
    public StreamzIncrements streamzIncrements;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
    public Trace trace;
    private final ListeningExecutorService uiExecutor = MoreExecutors.listeningDecorator(new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.UiExecutor.HandlerExecutorService(new Handler(Looper.getMainLooper())));

    public GrowthKitJobService()
    {
    }

    public final void onCreate()
    {
        try
        {
            ((ServiceInjector)((Provider)GrowthKit.get(this).internalServiceInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/jobs/impl/GrowthKitJobService)).get()).inject(this);
            initialized = true;
            super.onCreate();
            return;
        }
        catch (Exception exception)
        {
            logger.w(exception, "Failed to initialize SyncGcoreGcmTaskService", new Object[0]);
        }
    }

    public final boolean onStartJob(final JobParameters job)
    {
        boolean flag;
        flag = false;
        if (!initialized)
        {
            return false;
        }
        trace.begin();
        Object aobj[];
        if (((Boolean)enableFlag.get()).booleanValue())
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Logger logger1 = logger;
        aobj = new Object[0];
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        if (aobj.length > 0)
        {
            String.format("GrowthKit is disabled by Phenotype flag.", aobj);
        }
        trace.end();
        return false;
        final Object history;
        final String jobTag;
        jobTag = job.getTag();
        Logger logger2 = logger;
        history = job.getExtras().getStringArrayList("GrowthKitJobService.extra_history");
        if (history == null) goto _L2; else goto _L1
_L1:
        Object obj;
        Provider provider;
        ((ArrayList) (history)).add(timeFormat.format(new Date()));
        obj = job.getTag();
        provider = (Provider)((Map)jobs.get()).get(obj);
        if (provider == null) goto _L4; else goto _L3
_L3:
        obj = logger;
        ((List) (history)).toArray();
        obj = ((GrowthKitJob)provider.get()).executeJob$51666RRD5TJ6ISJ5C9GN6P9FD9NM4P39EDO62T33D1IN4BQADTH50OBIC5MMAT35E9PJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0();
_L7:
        currentlyRunningJobs.put(jobTag, obj);
        history = new _cls1();
        jobTag = uiExecutor;
        if (history != null) goto _L6; else goto _L5
_L5:
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally
        {
            trace.end();
        }
        obj = streamzIncrements;
        jobCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            packageName, job.getTag(), "ERROR"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts < ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
        {
            break MISSING_BLOCK_LABEL_377;
        }
        job = ((StreamzIncrements) (obj)).streamzLogger;
        obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
        if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
        {
            flag = true;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_373;
        }
        ((GcoreClearcutStreamzLogger) (job)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (job)).logSourceName).logAsync();
        StreamzIncrements.incrementCounts = 0;
        trace.end();
        return true;
_L2:
        history = new ArrayList();
        continue; /* Loop/switch isn't completed */
_L4:
        logger.w("Job %s not found, cancelling", new Object[] {
            obj
        });
        FirebaseJobDispatcher firebasejobdispatcher = firebaseJobDispatcher;
        firebasejobdispatcher.driver.isAvailable();
        firebasejobdispatcher.driver.cancel(((String) (obj)));
        if (false)
        {
            break MISSING_BLOCK_LABEL_456;
        }
        obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
          goto _L7
        obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
          goto _L7
        throw job;
_L6:
        ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (history))), jobTag);
        trace.end();
        return true;
        if (true) goto _L1; else goto _L8
_L8:
    }

    public final boolean onStopJob(JobParameters jobparameters)
    {
        jobparameters = jobparameters.getTag();
        Logger logger1 = logger;
        jobparameters = (ListenableFuture)currentlyRunningJobs.get(jobparameters);
        if (jobparameters != null && !jobparameters.isDone())
        {
            jobparameters.cancel(true);
            return true;
        } else
        {
            return false;
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final GrowthKitJobService this$0;
        private final ArrayList val$history;
        private final JobParameters val$job;
        private final String val$jobTag;

        public final void onFailure(Throwable throwable)
        {
            GrowthKitJobService.logger.e("job %s failed", new Object[] {
                jobTag
            });
            Object obj = streamzIncrements;
            jobCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                packageName, jobTag, "ERROR"
            }));
            StreamzIncrements.incrementCounts++;
            if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
            {
                throwable = ((StreamzIncrements) (obj)).streamzLogger;
                obj = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
                boolean flag;
                if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    ((GcoreClearcutStreamzLogger) (throwable)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (throwable)).logSourceName).logAsync();
                }
                StreamzIncrements.incrementCounts = 0;
            }
            currentlyRunningJobs.remove(jobTag);
            job.getExtras().putStringArrayList("GrowthKitJobService.extra_history", history);
            jobFinished(job, true);
        }

        public final void onSuccess(Object obj)
        {
            obj = GrowthKitJobService.logger;
            obj = jobTag;
            Object obj1 = streamzIncrements;
            jobCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
                packageName, jobTag, "OK"
            }));
            StreamzIncrements.incrementCounts++;
            if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj1)).incrementsToFlush.get()).longValue())
            {
                obj = ((StreamzIncrements) (obj1)).streamzLogger;
                obj1 = new com.google.android.libraries.streamz.GcoreClearcutStreamzLogger.GcoreMessageProducer(((StreamzIncrements) (obj1)).metricFactory);
                boolean flag;
                if (((StreamzMessageProducer) (obj1)).incrementRequest.batch_.size() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    ((GcoreClearcutStreamzLogger) (obj)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj1))).setLogSourceName(((GcoreClearcutStreamzLogger) (obj)).logSourceName).logAsync();
                }
                StreamzIncrements.incrementCounts = 0;
            }
            currentlyRunningJobs.remove(jobTag);
            jobFinished(job, false);
        }

        _cls1()
        {
            this$0 = GrowthKitJobService.this;
            jobTag = s;
            job = jobparameters;
            history = arraylist;
            super();
        }
    }

}
