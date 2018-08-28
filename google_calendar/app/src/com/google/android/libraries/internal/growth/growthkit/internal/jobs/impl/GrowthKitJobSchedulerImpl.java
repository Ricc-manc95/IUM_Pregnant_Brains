// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJob;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJobScheduler;
import com.google.common.base.Strings;
import dagger.Lazy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl:
//            GrowthKitJobService

final class GrowthKitJobSchedulerImpl
    implements GrowthKitJobScheduler
{

    private static final Logger logger = new Logger();
    private final FirebaseJobDispatcher firebaseJobDispatcher;
    private final Lazy jobs;

    GrowthKitJobSchedulerImpl(FirebaseJobDispatcher firebasejobdispatcher, Lazy lazy)
    {
        firebaseJobDispatcher = firebasejobdispatcher;
        jobs = lazy;
    }

    public final void autoScheduleJobs()
    {
        Iterator iterator = ((Map)jobs.get()).entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj1 = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj1)).getKey();
            GrowthKitJob growthkitjob = (GrowthKitJob)((Provider)((java.util.Map.Entry) (obj1)).getValue()).get();
            obj1 = growthkitjob.getJobBuilder();
            String s1 = ((com.firebase.jobdispatcher.Job.Builder) (obj1)).tag;
            if (!TextUtils.equals(s, s1))
            {
                throw new IllegalArgumentException(Strings.lenientFormat("Job key %s must match Job Tag %s!", new Object[] {
                    s, s1
                }));
            }
            if (growthkitjob.autoSchedule())
            {
                Object obj = logger;
                obj = ((com.firebase.jobdispatcher.Job.Builder) (obj1)).extras;
                int i;
                if (obj == null)
                {
                    obj = new Bundle();
                }
                obj1.extras = ((Bundle) (obj));
                obj1.serviceClassName = com/google/android/libraries/internal/growth/growthkit/internal/jobs/impl/GrowthKitJobService.getName();
                obj = ((com.firebase.jobdispatcher.Job.Builder) (obj1)).build();
                obj1 = firebaseJobDispatcher;
                ((FirebaseJobDispatcher) (obj1)).driver.isAvailable();
                i = ((FirebaseJobDispatcher) (obj1)).driver.schedule(((Job) (obj)));
                if (i != 0)
                {
                    logger.e("Failed to schedule job %s with error %d", new Object[] {
                        ((Job) (obj)).tag, Integer.valueOf(i)
                    });
                }
            }
        } while (true);
    }

}
