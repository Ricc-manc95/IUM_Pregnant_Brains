// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.common.util.concurrent.FutureCallback;

final class val.context
    implements FutureCallback
{

    private final Context val$context;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e("ObsoleteDataCleanerU", throwable, "Events data cleanup failed.", new Object[0]);
    }

    public final void onSuccess(Object obj)
    {
        obj = (com.google.android.syncadapters.calendar.aningResult)obj;
        Context context1 = val$context;
        if (((com.google.android.syncadapters.calendar.aningResult) (obj)).toStage > ((com.google.android.syncadapters.calendar.aningResult) (obj)).fromStage || ((com.google.android.syncadapters.calendar.aningResult) (obj)).eventsDeleted != 0 || ((com.google.android.syncadapters.calendar.aningResult) (obj)).habitNotificationsDeleted != 0)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            analyticslogger = (AnalyticsLogger)analyticslogger;
            int i = ((com.google.android.syncadapters.calendar.aningResult) (obj)).fromStage;
            int j = ((com.google.android.syncadapters.calendar.aningResult) (obj)).toStage;
            String s = (new StringBuilder(25)).append(i).append(" - ").append(j).toString();
            analyticslogger.setCustomDimension(context1, 48, s);
            analyticslogger.trackEvent(context1, "orphan_data_cleanup", "finished", "events", Long.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).eventsDeleted));
            analyticslogger.setCustomDimension(context1, 48, s);
            analyticslogger.trackEvent(context1, "orphan_data_cleanup", "finished", "habit_notifications", Long.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).habitNotificationsDeleted));
            LogUtils.v("ObsoleteDataCleanerU", "[Stage %d to %d] Cleanup has finished: %d events deleted, %d habit notifications deleted.", new Object[] {
                Integer.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).fromStage), Integer.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).toStage), Integer.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).eventsDeleted), Integer.valueOf(((com.google.android.syncadapters.calendar.aningResult) (obj)).habitNotificationsDeleted)
            });
        }
    }

    lder()
    {
        val$context = context1;
        super();
    }
}
