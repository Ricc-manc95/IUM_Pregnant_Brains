// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport.firebase;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.calendar.v2a.android.util.job.FutureJobService;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.config.phenotypesupport.firebase:
//            PhenotypeCommitOperation

public class PhenotypeCommitFirebaseService extends FutureJobService
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/config/phenotypesupport/firebase/PhenotypeCommitFirebaseService);

    public PhenotypeCommitFirebaseService()
    {
    }

    static void scheduleJob(Context context)
    {
        context = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Object obj = new com.firebase.jobdispatcher.Job.Builder(((FirebaseJobDispatcher) (context)).validator);
        obj.serviceClassName = com/google/android/apps/calendar/config/phenotypesupport/firebase/PhenotypeCommitFirebaseService.getName();
        obj.tag = TAG;
        obj.lifetime = 2;
        obj.trigger = Trigger.NOW;
        obj = ((com.firebase.jobdispatcher.Job.Builder) (obj)).build();
        Runnable runnable = com.google.calendar.v2a.android.util.job.FutureJobService..Lambda._cls0.$instance;
        (new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN)).execute(new com.google.calendar.v2a.android.util.job.FutureJobService..Lambda._cls1(context, ((com.firebase.jobdispatcher.Job) (obj)), runnable));
    }

    protected final ListenableFuture createFuture(JobParameters jobparameters)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final PhenotypeCommitFirebaseService arg$1;

            public final Object call()
            {
                GoogleApiClient googleapiclient;
                Object obj;
label0:
                {
                    obj = arg$1;
                    googleapiclient = (new com.google.android.gms.common.api.GoogleApiClient.Builder(((Context) (obj)))).addApi(Phenotype.API).build();
                    ConnectionResult connectionresult = googleapiclient.blockingConnect(10L, TimeUnit.SECONDS);
                    if (connectionresult != null)
                    {
                        boolean flag;
                        if (connectionresult.zzaEP == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            break label0;
                        }
                    }
                    if (connectionresult == null)
                    {
                        throw new RuntimeException("Failed to commit to GoogleApiClient - null result.");
                    } else
                    {
                        throw new RuntimeException(String.format(null, "Failed to connect to GoogleApiClient. Error #%d: %s", new Object[] {
                            Integer.valueOf(connectionresult.zzaEP), connectionresult.zzaIk
                        }));
                    }
                }
                obj = new PhenotypeSharedPrefsCommitter(((Context) (obj)), googleapiclient);
                if ("" == null)
                {
                    throw new NullPointerException("null reference");
                } else
                {
                    ((PhenotypeFlagCommitter) (obj)).zzF("", 3);
                    googleapiclient.disconnect();
                    return null;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = PhenotypeCommitFirebaseService.this;
            }
        }

        jobparameters = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0());
        PhenotypeCommitOperation phenotypecommitoperation = PhenotypeCommitOperation.COMMIT;
        return MetricUtils.withMetrics(new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS), jobparameters, phenotypecommitoperation);
    }

    protected final com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus onFinishJob(JobParameters jobparameters, Object obj, Throwable throwable)
    {
        if (throwable == null)
        {
            return com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus.SUCCESS;
        } else
        {
            return com.google.calendar.v2a.android.util.job.FutureJobService.JobStatus.SOFT_ERROR;
        }
    }

}
