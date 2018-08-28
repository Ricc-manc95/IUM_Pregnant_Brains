// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.jobservices;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;

public class JobServiceUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/jobservices/JobServiceUtils);

    public JobServiceUtils()
    {
    }

    public static void scheduleJob(Context context, JobInfo jobinfo)
    {
        if (((JobScheduler)context.getSystemService("jobscheduler")).schedule(jobinfo) == 1)
        {
            LogUtils.d(TAG, "Job with id %d scheduled successfully on behalf of %s.", new Object[] {
                Integer.valueOf(jobinfo.getId()), jobinfo.getService().getShortClassName()
            });
            return;
        } else
        {
            LogUtils.e(TAG, "Failed to schedule job with id %d on behalf of %s.", new Object[] {
                Integer.valueOf(jobinfo.getId()), jobinfo.getService().getShortClassName()
            });
            return;
        }
    }

}
