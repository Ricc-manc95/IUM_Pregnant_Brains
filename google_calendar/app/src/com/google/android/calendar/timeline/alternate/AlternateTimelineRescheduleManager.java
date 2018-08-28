// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.app.Activity;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.Rescheduler;
import com.google.android.calendar.analytics.dnd.DndAnalytics;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.dnd.InteractiveRescheduleManager;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.HashSet;
import java.util.Set;

public final class AlternateTimelineRescheduleManager
    implements com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.RescheduleManager
{

    private final Activity activity;
    public DndAnalytics analytics;
    public final Set pendingReschedules = new HashSet();
    private final ListenableFutureCache taskCache;

    public AlternateTimelineRescheduleManager(Activity activity1, ListenableFutureCache listenablefuturecache)
    {
        activity = activity1;
        taskCache = listenablefuturecache;
    }

    private final Optional start(TimeRangeEntry timerangeentry, int i)
    {
        TimelineItem timelineitem;
label0:
        {
            obj = timerangeentry.getKey();
            timelineitem = (new TimeBoxToTimelineAdapter(activity)).createTimelineItem(timerangeentry);
            analytics = new DndAnalytics(activity, i, InteractiveRescheduleManager.getDndAnalyticsType(timelineitem), timelineitem.getTimeRange());
            synchronized (pendingReschedules)
            {
                if (!pendingReschedules.contains(obj))
                {
                    break label0;
                }
                DndAnalytics.logDndFailedStartWrongView(activity, "long_press_timeline_chip_reschedule_pending");
                obj = Absent.INSTANCE;
            }
            return ((Optional) (obj));
        }
        A11yHelper.getInstance();
        if (!A11yHelper.isAccessibilityEnabled(activity))
        {
            break MISSING_BLOCK_LABEL_114;
        }
        obj = Absent.INSTANCE;
        timerangeentry;
        JVM INSTR monitorexit ;
        return ((Optional) (obj));
        obj;
        timerangeentry;
        JVM INSTR monitorexit ;
        throw obj;
        if (InteractiveRescheduleManager.checkReschedulableWithFeedback(activity, timelineitem))
        {
            break MISSING_BLOCK_LABEL_143;
        }
        DndAnalytics.logDndFailedStartWrongView(activity, "long_press_timeline_chip_grid_not_reschedulable");
        obj = Absent.INSTANCE;
        timerangeentry;
        JVM INSTR monitorexit ;
        return ((Optional) (obj));
        pendingReschedules.add(obj);
        timerangeentry;
        JVM INSTR monitorexit ;
        timerangeentry = activity;
        class .Lambda._cls0
            implements Function
        {

            private final AlternateTimelineRescheduleManager arg$1;
            private final InteractiveRescheduleManager arg$2;
            private final TimelineItem arg$3;
            private final Object arg$4;

            public final Object apply(final Object newStartTimeMs)
            {
                AlternateTimelineRescheduleManager alternatetimelinereschedulemanager = arg$1;
                InteractiveRescheduleManager interactivereschedulemanager = arg$2;
                final TimelineItem event = arg$3;
                Object obj1 = arg$4;
                newStartTimeMs = (Optional)newStartTimeMs;
                final SettableFuture future = new SettableFuture();
                class .Lambda._cls1
                    implements Runnable
                {

                    private final AlternateTimelineRescheduleManager arg$1;
                    private final Object arg$2;

                    public final void run()
                    {
                        AlternateTimelineRescheduleManager alternatetimelinereschedulemanager1 = arg$1;
                        Object obj2 = arg$2;
                        synchronized (alternatetimelinereschedulemanager1.pendingReschedules)
                        {
                            alternatetimelinereschedulemanager1.pendingReschedules.remove(obj2);
                        }
                        return;
                        exception;
                        set;
                        JVM INSTR monitorexit ;
                        throw exception;
                    }

                        .Lambda._cls1(Object obj)
                        {
                            arg$1 = AlternateTimelineRescheduleManager.this;
                            arg$2 = obj;
                        }
                }

                if (!((Optional) (newStartTimeMs)).isPresent())
                {
                    future.set(new Object());
                } else
                {
                    interactivereschedulemanager.reschedule(((Long)((Optional) (newStartTimeMs)).get()).longValue(), alternatetimelinereschedulemanager. new _cls1());
                }
                future.addListener(alternatetimelinereschedulemanager. new .Lambda._cls1(obj1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return future;
            }

            .Lambda._cls0(InteractiveRescheduleManager interactivereschedulemanager, TimelineItem timelineitem, Object obj)
            {
                arg$1 = AlternateTimelineRescheduleManager.this;
                arg$2 = interactivereschedulemanager;
                arg$3 = timelineitem;
                arg$4 = obj;
            }

            private class _cls1
                implements com.google.android.calendar.timely.dnd.InteractiveRescheduleManager.Callback
            {

                private final AlternateTimelineRescheduleManager this$0;
                private final TimelineItem val$event;
                private final SettableFuture val$future;
                private final Optional val$newStartTimeMs;

                public final void onCancel()
                {
                    future.set(new Object());
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
                        future.setException(new RuntimeException());
                        return;
                    }
                }

                public final void onSuccess()
                {
                    TimeRange timerange = TimeRange.newNonAllDay(event.getTimeRange().getTimeZone(), ((Long)newStartTimeMs.get()).longValue(), ((Long)newStartTimeMs.get()).longValue());
                    analytics.logSuccess(timerange);
                    future.set(new Object());
                }

                _cls1()
                {
                    this$0 = AlternateTimelineRescheduleManager.this;
                    future = settablefuture;
                    event = timelineitem;
                    newStartTimeMs = optional;
                    super();
                }
            }

        }

        timerangeentry = new .Lambda._cls0(new InteractiveRescheduleManager(timerangeentry, new Rescheduler(timerangeentry, timelineitem, taskCache), timelineitem), timelineitem, obj);
        if (timerangeentry == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(timerangeentry);
        }
    }

    public final boolean isPendingReschedule(Object obj)
    {
        boolean flag;
        synchronized (pendingReschedules)
        {
            flag = pendingReschedules.contains(obj);
        }
        return flag;
        obj;
        set;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public final volatile Optional start(Object obj, int i)
    {
        return start((TimeRangeEntry)obj, i);
    }
}
