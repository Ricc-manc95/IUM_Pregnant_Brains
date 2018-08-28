// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import android.os.Bundle;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.util.concurrent.FutureCallback;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl:
//            GrowthKitJobService

final class val.history
    implements FutureCallback
{

    private final GrowthKitJobService this$0;
    private final ArrayList val$history;
    private final JobParameters val$job;
    private final String val$jobTag;

    public final void onFailure(Throwable throwable)
    {
        GrowthKitJobService.logger.e("job %s failed", new Object[] {
            val$jobTag
        });
        Object obj = streamzIncrements;
        jobCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            packageName, val$jobTag, "ERROR"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj)).incrementsToFlush.get()).longValue())
        {
            throwable = ((StreamzIncrements) (obj)).streamzLogger;
            obj = new com.google.android.libraries.streamz.gger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
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
        currentlyRunningJobs.remove(val$jobTag);
        val$job.getExtras().putStringArrayList("GrowthKitJobService.extra_history", val$history);
        jobFinished(val$job, true);
    }

    public final void onSuccess(Object obj)
    {
        obj = GrowthKitJobService.logger;
        obj = val$jobTag;
        Object obj1 = streamzIncrements;
        jobCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            packageName, val$jobTag, "OK"
        }));
        StreamzIncrements.incrementCounts++;
        if ((long)StreamzIncrements.incrementCounts >= ((Long)((StreamzIncrements) (obj1)).incrementsToFlush.get()).longValue())
        {
            obj = ((StreamzIncrements) (obj1)).streamzLogger;
            obj1 = new com.google.android.libraries.streamz.gger.GcoreMessageProducer(((StreamzIncrements) (obj1)).metricFactory);
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
        currentlyRunningJobs.remove(val$jobTag);
        jobFinished(val$job, false);
    }

    ()
    {
        this$0 = final_growthkitjobservice;
        val$jobTag = s;
        val$job = jobparameters;
        val$history = ArrayList.this;
        super();
    }
}
