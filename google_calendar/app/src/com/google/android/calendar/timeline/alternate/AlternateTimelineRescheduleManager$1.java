// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.analytics.dnd.DndAnalytics;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            AlternateTimelineRescheduleManager

final class val.newStartTimeMs
    implements com.google.android.calendar.timely.dnd.
{

    private final AlternateTimelineRescheduleManager this$0;
    private final TimelineItem val$event;
    private final SettableFuture val$future;
    private final Optional val$newStartTimeMs;

    public final void onCancel()
    {
        val$future.set(new Object());
        analytics.logFailureDroppedUndo();
    }

    public final void onFailure()
    {
        android.content.Context context = analytics.context;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "dnd", "dnd_drop_failed", "dnd_drop_failed_other", Long.valueOf(0L));
            val$future.setException(new RuntimeException());
            return;
        }
    }

    public final void onSuccess()
    {
        TimeRange timerange = TimeRange.newNonAllDay(val$event.getTimeRange().getTimeZone(), ((Long)val$newStartTimeMs.get()).longValue(), ((Long)val$newStartTimeMs.get()).longValue());
        analytics.logSuccess(timerange);
        val$future.set(new Object());
    }

    Y()
    {
        this$0 = final_alternatetimelinereschedulemanager;
        val$future = settablefuture;
        val$event = timelineitem;
        val$newStartTimeMs = Optional.this;
        super();
    }
}
