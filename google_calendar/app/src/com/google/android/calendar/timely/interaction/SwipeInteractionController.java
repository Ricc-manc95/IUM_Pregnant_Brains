// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.animation.Animator;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.DayViewConfig;
import com.google.android.calendar.timely.DualTimelineGridFragment;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineRecyclerView;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelineTaskType;
import com.google.android.calendar.timely.TimelyDayView;
import com.google.android.calendar.timely.interaction.swipe.SwipeUtils;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.animation.AnimationUtils;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            InteractionTracker, SwipeTaskUtils

public class SwipeInteractionController
    implements com.google.android.calendar.timely.TimelyDayView.SwipeGestureDelegate
{
    static final class CloneAsReminderBundle extends TimelineItemOperation
    {

        public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
        {
            return (TimelineTaskBundle)timelinetaskbundle.clone();
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            return new TimelineTaskBundle(timelinetask);
        }

        CloneAsReminderBundle()
        {
        }
    }

    static final class GetAllReminders extends TimelineItemOperation
    {

        public final Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            return Collections.emptyList();
        }

        public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
        {
            return timelinetaskbundle.timelineTaskList;
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            return Collections.singletonList(timelinetask);
        }

        GetAllReminders()
        {
        }
    }


    private static final ActivitySingletonCache instances = new _cls1();
    public TimelineRecyclerView scheduleView;

    public SwipeInteractionController()
    {
    }

    public static SwipeInteractionController getInstance(Activity activity)
    {
        CalendarExecutor.MAIN.checkOnThread();
        return (SwipeInteractionController)instances.getInstance(activity);
    }

    static void logUserAction(TimelyDayView timelydayview, String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(timelydayview.getContext(), "event", s);
            return;
        }
    }

    private final boolean performSwipe(final TimelyDayView view, TimelineItem timelineitem, final boolean isInteractive)
    {
        InteractionTracker.getInstance().trackInteractionStart(this, timelineitem);
        return ((Boolean)timelineitem.perform(new _cls2(), new Void[0])).booleanValue();
    }

    public final int getSupportedSwipeDirections(TimelineItem timelineitem)
    {
        return SwipeUtils.getSupportedSwipeDirections(timelineitem);
    }

    public final Integer getSwipeIcon(TimelineItem timelineitem, int i)
    {
        return SwipeUtils.getSwipeIcon(timelineitem, i);
    }

    public final boolean isSwipeEnabled(TimelyDayView timelydayview)
    {
        if (!AccessibilityUtils.isAccessibilityEnabled(timelydayview.getContext()))
        {
            if ((timelydayview = timelydayview.dayViewConfig) != null && timelydayview.supportsSwipe())
            {
                return true;
            }
        }
        return false;
    }

    public final boolean onDismissActionComplete$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(TimelyDayView timelydayview, TimelineItem timelineitem)
    {
        return performSwipe(timelydayview, timelineitem, false);
    }

    final boolean onMarkReminderAsDone(TimelyDayView timelydayview, TimelineTaskType timelinetasktype, boolean flag)
    {
        Object obj;
        Object obj1;
        InteractionTracker.EndInteractionCountdown endinteractioncountdown = new InteractionTracker.EndInteractionCountdown(this, timelinetasktype, 2);
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj5)
            {
                return (String)((TimelineTask)obj5).id;
            }


            private .Lambda._cls0()
            {
            }
        }

        Animator animator;
        if (scheduleView == null)
        {
            obj = null;
        } else
        {
            obj = scheduleView;
            obj1 = ((TimelineRecyclerView) (obj)).recycleTime;
            int i;
            int i1;
            int j1;
            long l1;
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            ((Time) (obj1)).set(l1);
            j1 = TimelineRecyclerView.getPosition(((TimelineRecyclerView) (obj)).recycleTime);
            if (((TimelineRecyclerView) (obj)).getChildCount() > 0)
            {
                i = ((TimelineRecyclerView) (obj)).layoutManager.findFirstVisibleItemPosition();
            } else
            {
                i = -1;
            }
            if (((TimelineRecyclerView) (obj)).getChildCount() > 0)
            {
                i1 = ((TimelineRecyclerView) (obj)).layoutManager.findLastVisibleItemPosition();
            } else
            {
                i1 = -1;
            }
            if (j1 < i || j1 > i1)
            {
                obj = null;
            } else
            {
                int j;
                if (((TimelineRecyclerView) (obj)).getChildCount() > 0)
                {
                    j = LinearLayoutManager.getPosition(((TimelineRecyclerView) (obj)).getChildAt(0));
                } else
                {
                    j = -1;
                }
                j = j1 - j;
                if (j < 0 || j >= ((TimelineRecyclerView) (obj)).getChildCount())
                {
                    obj = null;
                } else
                {
                    obj = (TimelyDayView)((TimelineRecyclerView) (obj)).getChildAt(j);
                }
            }
        }
_L11:
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj1 = null;
_L9:
        animator = timelydayview.excludeItemAnimated(timelinetasktype);
        if (TimelineItemUtil.isReminderBundle(timelinetasktype))
        {
            obj = null;
        } else
        {
            if (scheduleView == null)
            {
                obj = null;
            } else
            {
                obj = scheduleView;
                int l = DualTimelineGridFragment.getPositionFromJulianDay(timelinetasktype.getStartDay());
                obj = (TimelyDayView)((RecyclerView) (obj)).mLayout.findViewByPosition(l);
            }
            if (obj == null || obj == timelydayview)
            {
                obj = null;
            } else
            if (((TimelyDayView) (obj)).getChipForItem(timelinetasktype) != null)
            {
                obj = ((TimelyDayView) (obj)).excludeItemAnimated(timelinetasktype);
            } else
            {
                Object obj4 = (TimelineTask)timelinetasktype;
                obj4 = (TimelineTaskBundle)((TimelyDayView) (obj)).items.find(new com.google.android.calendar.timely.TimelyDayView._cls1(((TimelineTask) (obj4))));
                if (obj4 == null)
                {
                    obj = null;
                } else
                {
                    TimelineTaskBundle timelinetaskbundle = (TimelineTaskBundle)((TimelineTaskBundle) (obj4)).clone();
                    TimelineTask timelinetask1 = (TimelineTask)timelinetasktype;
                    timelinetaskbundle.timelineTaskList.remove(timelinetask1);
                    if (timelinetaskbundle.timelineTaskList.isEmpty())
                    {
                        timelinetaskbundle.timeRange = TimeRange.newAllDayFromJulianDay(timelinetaskbundle.timeRange.getTimeZone(), timelinetaskbundle.timeRange.getStartDay(), timelinetaskbundle.timeRange.getStartDay());
                    } else
                    {
                        timelinetaskbundle.timeRange = TimeRange.span(new com.google.common.collect.Collections2.TransformedCollection(timelinetaskbundle.timelineTaskList, com.google.android.calendar.timely.TimelineTaskBundle..Lambda._cls0.$instance), timelinetaskbundle.timeRange.getTimeZone());
                    }
                    timelinetaskbundle.updateFinished = false;
                    timelinetaskbundle.updateTitles(((TimelyDayView) (obj)).getContext());
                    if (timelinetaskbundle.timelineTaskList.size() == 1)
                    {
                        obj = ((TimelyDayView) (obj)).updateItemAnimated(((TimelineItem) (obj4)), (TimelineItem)timelinetaskbundle.timelineTaskList.get(0));
                    } else
                    {
                        obj = ((TimelyDayView) (obj)).updateItemAnimated(((TimelineItem) (obj4)), timelinetaskbundle);
                    }
                }
            }
        }
        AnimationUtils.animateThenRun(AnimationUtils.playTogether(animator, new Animator[] {
            obj1, obj
        }), endinteractioncountdown);
        obj1 = timelydayview.getContext();
        if (timelinetasktype instanceof TimelineTaskBundle)
        {
            obj = new com.google.common.collect.Collections2.TransformedCollection(((TimelineTaskBundle)timelinetasktype).timelineTaskList, .Lambda._cls0..instance);
            Object obj2;
            Object obj3;
            TimelineTask timelinetask;
            int k;
            if (obj instanceof Collection)
            {
                obj = new HashSet((Collection)obj);
            } else
            {
                Iterator iterator = ((Iterable) (obj)).iterator();
                obj = new HashSet();
                Iterators.addAll(((Collection) (obj)), iterator);
            }
        } else
        {
            obj = Collections.singleton((String)((TimelineTask)timelinetasktype).id);
        }
        SwipeTaskUtils.updateTaskDoneAsync(((Context) (obj1)), timelinetasktype.getSourceAccountName(), ((java.util.Set) (obj))).addListener(endinteractioncountdown, CalendarExecutor.MAIN);
        if (!flag || !TimelineItemUtil.isReminder(timelinetasktype))
        {
            break MISSING_BLOCK_LABEL_1006;
        }
        timelinetasktype = AnalyticsLoggerHolder.instance;
        if (timelinetasktype == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break MISSING_BLOCK_LABEL_988;
_L2:
        obj1 = timelinetasktype.getSourceAccountName();
        obj1 = (TimelineTaskType)((TimelyDayView) (obj)).items.find(new com.google.android.calendar.timely.TimelyDayView._cls2(((String) (obj1))));
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        obj2 = (TimelineTaskType)timelinetasktype.clone();
        ((TimelineTaskType) (obj2)).setTransientDoneState(Utils.getTodayJulianDay(((TimelyDayView) (obj)).getContext()));
        obj = ((TimelyDayView) (obj)).items;
        if (obj2 == null) goto _L6; else goto _L5
_L5:
        if (((TimelineItemCollection) (obj)).animatorProvider != null) goto _L8; else goto _L7
_L7:
        ((TimelineItemCollection) (obj)).include(((TimelineItem) (obj2)), null);
_L6:
        obj1 = null;
          goto _L9
_L8:
        obj1 = ((TimelineItemCollection) (obj)).animatorProvider.createBeforeIncludeItemAnimator(((TimelineItem) (obj2)));
        if (!((TimelineItemCollection) (obj)).include(((TimelineItem) (obj2)), null)) goto _L6; else goto _L9
_L4:
        obj3 = (List)timelinetasktype.perform(new GetAllReminders(), new Void[0]);
        obj2 = (TimelineTaskBundle)((TimelineTaskType) (obj1)).perform(new CloneAsReminderBundle(), new Void[0]);
        for (obj3 = ((List) (obj3)).iterator(); ((Iterator) (obj3)).hasNext(); ((TimelineTaskBundle) (obj2)).addTimelineTask(timelinetask))
        {
            timelinetask = (TimelineTask)((TimelineTask)((Iterator) (obj3)).next()).clone();
            k = Utils.getTodayJulianDay(((TimelyDayView) (obj)).getContext());
            timelinetask.isDone = true;
            timelinetask.timeRange = TimeRange.newAllDayFromJulianDay(timelinetask.timeRange.getTimeZone(), k, k);
        }

        ((TimelineTaskBundle) (obj2)).sort();
        ((TimelineTaskBundle) (obj2)).updateTitles(((TimelyDayView) (obj)).getContext());
        obj1 = ((TimelyDayView) (obj)).updateItemAnimated(((TimelineItem) (obj1)), ((TimelineItem) (obj2)));
          goto _L9
        ((AnalyticsLogger)timelinetasktype).trackEvent(timelydayview.getContext(), "event", "done_swipe");
        return true;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final boolean onSwipeGestureComplete$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(TimelyDayView timelydayview, TimelineItem timelineitem)
    {
        return performSwipe(timelydayview, timelineitem, true);
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/timely/interaction/SwipeInteractionController);
    }

    private class _cls2 extends TimelineItemOperation
    {

        private final SwipeInteractionController this$0;
        private final boolean val$isInteractive;
        private final TimelyDayView val$view;

        public final Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            InteractionTracker.getInstance().trackInteractionEnd(SwipeInteractionController.this, timelineitem);
            return Boolean.valueOf(false);
        }

        public final Object onAnyEvent(final TimelineEvent item, Object aobj[])
        {
            Object obj = SwipeInteractionController.this;
            final TimelyDayView view = view;
            final boolean isInteractive = isInteractive;
            final InteractionTracker.EndInteractionCountdown endInteraction = new InteractionTracker.EndInteractionCountdown(obj, item, 1);
            aobj = DeleteEventInteractiveHelper.deleteEvent(view.getContext(), (EventKey)item.eventKey);
            item = ((_cls4) (obj)). new _cls4();
            obj = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (item == null)
            {
                throw new NullPointerException();
            } else
            {
                ((ListenableFuture) (aobj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (aobj)), item), ((java.util.concurrent.Executor) (obj)));
                return Boolean.valueOf(true);
            }
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            aobj = SwipeInteractionController.this;
            TimelyDayView timelydayview = view;
            boolean flag = isInteractive;
            InteractionTracker.EndInteractionCountdown endinteractioncountdown = new InteractionTracker.EndInteractionCountdown(((Object) (aobj)), timelinegroove, 2);
            aobj = (TimelineGroove)(TimelineEvent)timelinegroove.clone();
            aobj.completed = true;
            AnimationUtils.animateThenRun(timelydayview.updateItemAnimated(timelinegroove, ((TimelineItem) (aobj))), endinteractioncountdown);
            Context context;
            com.google.android.calendar.api.habit.HabitDescriptor habitdescriptor;
            EventKey eventkey;
            if (flag)
            {
                aobj = "swipe_from_timeline";
            } else
            {
                aobj = "";
            }
            context = timelydayview.getContext();
            habitdescriptor = timelinegroove.descriptor;
            eventkey = ((TimelineEvent) (timelinegroove)).eventKey;
            if (!timelinegroove.completed)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            timelinegroove = HabitsIntentServiceHelper.createCompleteIntent(context, habitdescriptor, eventkey, flag, ((String) (aobj)));
            timelydayview.getContext().startService(timelinegroove);
            endinteractioncountdown.run();
            return Boolean.valueOf(true);
        }

        public final Object onReminderBundle(final TimelineTaskBundle item, Object aobj[])
        {
            Object obj = SwipeInteractionController.this;
            final TimelyDayView view = view;
            final boolean isInteractive = isInteractive;
            if (!isInteractive)
            {
                isInteractive = ((SwipeInteractionController) (obj)).onMarkReminderAsDone(view, item, isInteractive);
            } else
            {
                final InteractionTracker.EndInteractionCountdown endInteraction = new InteractionTracker.EndInteractionCountdown(obj, item, 1);
                aobj = SwipeTaskUtils.showMarkTaskBundleDoneDialog(view.getContext(), item.timelineTaskList.size());
                item = ((_cls3) (obj)). new _cls3();
                obj = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
                if (item == null)
                {
                    throw new NullPointerException();
                }
                ((ListenableFuture) (aobj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (aobj)), item), ((java.util.concurrent.Executor) (obj)));
                isInteractive = true;
            }
            return Boolean.valueOf(isInteractive);
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            return Boolean.valueOf(onMarkReminderAsDone(view, timelinetask, isInteractive));
        }

        _cls2()
        {
            this$0 = SwipeInteractionController.this;
            view = timelydayview;
            isInteractive = flag;
            super();
        }

        private class _cls4
            implements FutureCallback
        {

            private final SwipeInteractionController this$0;
            private final Runnable val$endInteraction;
            private final boolean val$isInteractive;
            private final TimelineEvent val$item;
            private final TimelyDayView val$view;

            public final void onFailure(Throwable throwable)
            {
                AnimationUtils.animateThenRun(view.createRestoreAnimator(item), endInteraction);
            }

            public final void onSuccess(Object obj)
            {
                if (isInteractive)
                {
                    SwipeInteractionController.logUserAction(view, "delete_swipe");
                }
                AnimationUtils.animateThenRun(view.excludeItemAnimated(item), endInteraction);
            }

            _cls4()
            {
                this$0 = SwipeInteractionController.this;
                isInteractive = flag;
                view = timelydayview;
                item = timelineevent;
                endInteraction = runnable;
                super();
            }
        }


        private class _cls3
            implements FutureCallback
        {

            private final SwipeInteractionController this$0;
            private final Runnable val$endInteraction;
            private final boolean val$isInteractive;
            private final TimelineTaskBundle val$item;
            private final TimelyDayView val$view;

            public final void onFailure(Throwable throwable)
            {
                AnimationUtils.animateThenRun(view.createRestoreAnimator(item), endInteraction);
            }

            public final void onSuccess(Object obj)
            {
                onMarkReminderAsDone(view, item, isInteractive);
                if (isInteractive)
                {
                    SwipeInteractionController.logUserAction(view, "done_all_swipe");
                }
            }

            _cls3()
            {
                this$0 = SwipeInteractionController.this;
                view = timelydayview;
                item = timelinetaskbundle;
                isInteractive = flag;
                endInteraction = runnable;
                super();
            }
        }

    }


    private class _cls1 extends ActivitySingletonCache
    {

        protected final Object createInstance(Activity activity)
        {
            return new SwipeInteractionController();
        }

        _cls1()
        {
        }
    }

}
