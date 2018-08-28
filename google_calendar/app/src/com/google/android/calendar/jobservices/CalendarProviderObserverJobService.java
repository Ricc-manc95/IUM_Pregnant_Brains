// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.jobservices;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.intent.IntentUtils;

// Referenced classes of package com.google.android.calendar.jobservices:
//            JobServiceUtils

public class CalendarProviderObserverJobService extends JobService
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/jobservices/CalendarProviderObserverJobService);

    public CalendarProviderObserverJobService()
    {
    }

    public static void scheduleJob(Context context)
    {
        JobServiceUtils.scheduleJob(context, (new android.app.job.JobInfo.Builder(1, new ComponentName(context, com/google/android/calendar/jobservices/CalendarProviderObserverJobService))).addTriggerContentUri(new android.app.job.JobInfo.TriggerContentUri(CalendarContract.CONTENT_URI, 1)).setTriggerContentUpdateDelay(5000L).build());
    }

    public boolean onStartJob(JobParameters jobparameters)
    {
        LogUtils.d(TAG, "onStartJob", new Object[0]);
        jobparameters = (Intent)IntentUtils.createProviderChangedIntent().clone();
        jobparameters.setPackage(getPackageName());
        sendBroadcast(jobparameters);
        JobServiceUtils.scheduleJob(this, (new android.app.job.JobInfo.Builder(1, new ComponentName(this, com/google/android/calendar/jobservices/CalendarProviderObserverJobService))).addTriggerContentUri(new android.app.job.JobInfo.TriggerContentUri(CalendarContract.CONTENT_URI, 1)).setTriggerContentUpdateDelay(5000L).build());
        return true;
    }

    public boolean onStopJob(JobParameters jobparameters)
    {
        return true;
    }

}
