// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.provider.sync.syncer;

import android.content.Context;
import android.provider.CalendarContract;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.ObservedUri;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.apps.xplat.logging.LoggingApi;
import com.google.apps.xplat.logging.XLogLevel;
import com.google.apps.xplat.logging.XLogger;
import com.google.calendar.v2a.android.util.job.FutureJobService;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TrustedListenableFutureTask;
import java.util.concurrent.Executor;

public class ProviderSyncJobService extends FutureJobService
{

    private static final String CP_WATCH_TAG = String.valueOf(com/google/calendar/v2a/android/provider/sync/syncer/ProviderSyncJobService.getSimpleName()).concat(":cp");
    private static final JobTrigger CP_WATCH_TRIGGER;
    private static final String SYNC_JOB_TAG = String.valueOf(com/google/calendar/v2a/android/provider/sync/syncer/ProviderSyncJobService.getSimpleName()).concat(":sync");
    private static final JobTrigger SYNC_JOB_TRIGGER = Trigger.executionWindow(5, 10);
    private static final String USS_WATCH_TAG = String.valueOf(com/google/calendar/v2a/android/provider/sync/syncer/ProviderSyncJobService.getSimpleName()).concat(":uss");
    private static final JobTrigger USS_WATCH_TRIGGER = Trigger.executionWindow(5, 10);
    private static int countRunningJobs = 0;
    private static final XLogger logger = new XLogger(com/google/calendar/v2a/android/provider/sync/syncer/ProviderSyncJobService);

    public ProviderSyncJobService()
    {
    }

    static final void lambda$requestSync$0$ProviderSyncJobService()
    {
    }

    public static void requestSync(Context context)
    {
        logger.getLoggingApi(XLogLevel.INFO).log("Requesting side-sync");
        throw new IllegalStateException();
    }

    protected final ListenableFuture createFuture(JobParameters jobparameters)
    {
        if (CP_WATCH_TAG.equals(jobparameters.getTag()) || USS_WATCH_TAG.equals(jobparameters.getTag()))
        {
            class .Lambda._cls1
                implements AsyncCallable
            {

                private final ProviderSyncJobService arg$1;

                public final ListenableFuture call()
                {
                    ProviderSyncJobService.requestSync(arg$1);
                    if (true)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                    }
                }

            .Lambda._cls1()
            {
                arg$1 = ProviderSyncJobService.this;
            }
            }

            Object obj = new com.google.calendar.v2a.android.util.job.FutureJobService..Lambda._cls2(this, SYNC_JOB_TAG, new .Lambda._cls1());
            jobparameters = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            obj = new TrustedListenableFutureTask(((AsyncCallable) (obj)));
            jobparameters.execute(((Runnable) (obj)));
            return ((ListenableFuture) (obj));
        }
        countRunningJobs++;
        jobparameters = new IllegalStateException("providerSyncerFactory is not set.");
        if (jobparameters == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(jobparameters);
        }
    }

    protected final com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus onFinishJob(JobParameters jobparameters, Object obj, Throwable throwable)
    {
        if (SYNC_JOB_TAG.equals(jobparameters.getTag()))
        {
            countRunningJobs--;
        }
        if (throwable != null)
        {
            return com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus.HARD_ERROR;
        } else
        {
            return com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus.SUCCESS;
        }
    }

    static 
    {
        CP_WATCH_TRIGGER = Trigger.contentUriTrigger(ImmutableList.of(new ObservedUri(CalendarContract.CONTENT_URI, 1)));
    }
}
