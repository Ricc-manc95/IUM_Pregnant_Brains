// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.DeleteEventInteractiveHelper;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelyDayView;
import com.google.android.calendar.utils.animation.AnimationUtils;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            InteractionTracker, SwipeInteractionController, SwipeTaskUtils

final class val.isInteractive extends TimelineItemOperation
{

    private final SwipeInteractionController this$0;
    private final boolean val$isInteractive;
    private final TimelyDayView val$view;

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        InteractionTracker.getInstance().trackInteractionEnd(SwipeInteractionController.this, timelineitem);
        return Boolean.valueOf(false);
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        Object obj = SwipeInteractionController.this;
        TimelyDayView timelydayview = val$view;
        boolean flag = val$isInteractive;
        tionCountdown tioncountdown = new tionCountdown(obj, timelineevent, 1);
        aobj = DeleteEventInteractiveHelper.deleteEvent(timelydayview.getContext(), (EventKey)timelineevent.eventKey);
        timelineevent = new <init>(((SwipeInteractionController) (obj)), flag, timelydayview, timelineevent, tioncountdown);
        obj = new com.google.android.apps.calendar.util.concurrent.nit>(CalendarExecutor.MAIN);
        if (timelineevent == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (aobj)).addListener(new com.google.common.util.concurrent.t>(((java.util.concurrent.Future) (aobj)), timelineevent), ((java.util.concurrent.Executor) (obj)));
            return Boolean.valueOf(true);
        }
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        aobj = SwipeInteractionController.this;
        TimelyDayView timelydayview = val$view;
        boolean flag = val$isInteractive;
        tionCountdown tioncountdown = new tionCountdown(((Object) (aobj)), timelinegroove, 2);
        aobj = (TimelineGroove)(TimelineEvent)timelinegroove.clone();
        aobj.completed = true;
        AnimationUtils.animateThenRun(timelydayview.updateItemAnimated(timelinegroove, ((TimelineItem) (aobj))), tioncountdown);
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
        tioncountdown.run();
        return Boolean.valueOf(true);
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        Object obj = SwipeInteractionController.this;
        TimelyDayView timelydayview = val$view;
        boolean flag = val$isInteractive;
        if (!flag)
        {
            flag = ((SwipeInteractionController) (obj)).onMarkReminderAsDone(timelydayview, timelinetaskbundle, flag);
        } else
        {
            tionCountdown tioncountdown = new tionCountdown(obj, timelinetaskbundle, 1);
            aobj = SwipeTaskUtils.showMarkTaskBundleDoneDialog(timelydayview.getContext(), timelinetaskbundle.timelineTaskList.size());
            timelinetaskbundle = new <init>(((SwipeInteractionController) (obj)), timelydayview, timelinetaskbundle, flag, tioncountdown);
            obj = new com.google.android.apps.calendar.util.concurrent.nit>(CalendarExecutor.MAIN);
            if (timelinetaskbundle == null)
            {
                throw new NullPointerException();
            }
            ((ListenableFuture) (aobj)).addListener(new com.google.common.util.concurrent.t>(((java.util.concurrent.Future) (aobj)), timelinetaskbundle), ((java.util.concurrent.Executor) (obj)));
            flag = true;
        }
        return Boolean.valueOf(flag);
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return Boolean.valueOf(onMarkReminderAsDone(val$view, timelinetask, val$isInteractive));
    }

    tionCountdown()
    {
        this$0 = final_swipeinteractioncontroller;
        val$view = timelydayview;
        val$isInteractive = Z.this;
        super();
    }
}
