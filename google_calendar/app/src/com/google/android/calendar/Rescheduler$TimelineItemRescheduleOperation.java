// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactoryImpl;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzaj;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar:
//            Rescheduler, Utils

public final class newStartTime extends TimelineItemOperation
{

    private final com.google.android.calendar.api.event.cMessage guestNotification;
    private final long newStartTime;
    public final Rescheduler this$0;

    final ListenableFuture bridge$lambda$0$Rescheduler$TimelineItemRescheduleOperation(Event event)
    {
        boolean flag;
        if (event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (!(new EventPermissionsFactoryImpl()).create(event).canModifyStartTime())
        {
            LogUtils.w(Rescheduler.TAG, "No permission to change start time of the event.", new Object[0]);
            event = null;
        } else
        {
            EventModifications eventmodifications = CalendarApi.EventFactory.modifyEvent(event);
            eventmodifications.setStartMillis(newStartTime);
            eventmodifications.setEndMillis((event.getEndMillis() - event.getStartMillis()) + newStartTime);
            event = eventmodifications;
        }
        if (event == null)
        {
            LogUtils.wtf(Rescheduler.TAG, "Failed to create EventModifications.", new Object[0]);
            event = Boolean.valueOf(false);
            if (event == null)
            {
                return com.google.common.util.concurrent.LL;
            } else
            {
                return new com.google.common.util.concurrent.nit>(event);
            }
        }
        event = CalendarApi.Events.update(event, 0, guestNotification);
        class .Lambda._cls5
            implements Function
        {

            public static final Function $instance = new .Lambda._cls5();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(true);
            }


            private .Lambda._cls5()
            {
            }
        }

        class .Lambda._cls6
            implements Function
        {

            public static final Function $instance = new .Lambda._cls6();

            public final Object apply(Object obj)
            {
                obj = (Exception)obj;
                LogUtils.wtf(Rescheduler.TAG, ((Throwable) (obj)), "Failed to reschedule the event.", new Object[0]);
                return Boolean.valueOf(false);
            }


            private .Lambda._cls6()
            {
            }
        }

        if (event instanceof FluentFuture)
        {
            event = (FluentFuture)event;
        } else
        {
            event = new ForwardingFluentFuture(event);
        }
        return (FluentFuture)AbstractCatchingFuture.create((FluentFuture)AbstractTransformFuture.create(event, .Lambda._cls5..instance, com.google.common.util.concurrent.a._cls5..instance), java/lang/Exception, .Lambda._cls6..instance, com.google.common.util.concurrent.a._cls6..instance);
    }

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        timelineitem = withGenericMessage(false);
        if (timelineitem == null)
        {
            return com.google.common.util.concurrent.LL;
        } else
        {
            return new com.google.common.util.concurrent.nit>(timelineitem);
        }
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final Rescheduler.TimelineItemRescheduleOperation arg$1;

            public final ListenableFuture apply(Object obj)
            {
                return arg$1.bridge$lambda$0$Rescheduler$TimelineItemRescheduleOperation((Event)obj);
            }

            .Lambda._cls0()
            {
                arg$1 = Rescheduler.TimelineItemRescheduleOperation.this;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final Rescheduler.TimelineItemRescheduleOperation arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.withGenericMessage(((Boolean)obj).booleanValue());
            }

            .Lambda._cls1()
            {
                arg$1 = Rescheduler.TimelineItemRescheduleOperation.this;
            }
        }

        return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(rescheduledEvent, new .Lambda._cls0(), com.google.common.util.concurrent.a._fld0), new .Lambda._cls1(), com.google.common.util.concurrent.a._fld1);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        class .Lambda._cls2
            implements AsyncFunction
        {

            private final Rescheduler.TimelineItemRescheduleOperation arg$1;

            public final ListenableFuture apply(Object obj)
            {
                return arg$1.bridge$lambda$0$Rescheduler$TimelineItemRescheduleOperation((Event)obj);
            }

            .Lambda._cls2()
            {
                arg$1 = Rescheduler.TimelineItemRescheduleOperation.this;
            }
        }

        class .Lambda._cls3
            implements Function
        {

            private final Rescheduler.TimelineItemRescheduleOperation arg$1;
            private final TimelineGroove arg$2;

            public final Object apply(Object obj)
            {
                Rescheduler.TimelineItemRescheduleOperation timelineitemrescheduleoperation = arg$1;
                TimelineGroove timelinegroove1 = arg$2;
                if (!((Boolean)obj).booleanValue())
                {
                    return timelineitemrescheduleoperation.withGenericMessage(false);
                } else
                {
                    timelineitemrescheduleoperation.this$0.context.startService(HabitsIntentServiceHelper.createRefreshNotificationsIntent(timelineitemrescheduleoperation.this$0.context, timelinegroove1.descriptor));
                    return new Rescheduler.RescheduleResult(true, timelineitemrescheduleoperation.this$0.context.getResources().getString(0x7f130254));
                }
            }

            .Lambda._cls3(TimelineGroove timelinegroove)
            {
                arg$1 = Rescheduler.TimelineItemRescheduleOperation.this;
                arg$2 = timelinegroove;
            }
        }

        return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(rescheduledEvent, new .Lambda._cls2(), com.google.common.util.concurrent.a._fld2), new .Lambda._cls3(timelinegroove), com.google.common.util.concurrent.a._fld3);
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        class .Lambda._cls4
            implements Callable
        {

            private final Rescheduler.TimelineItemRescheduleOperation arg$1;
            private final TimelineTask arg$2;

            public final Object call()
            {
                return arg$1.rescheduleReminderBlocking(arg$2);
            }

            .Lambda._cls4(TimelineTask timelinetask)
            {
                arg$1 = Rescheduler.TimelineItemRescheduleOperation.this;
                arg$2 = timelinetask;
            }
        }

        return (FluentFuture)CalendarExecutor.NET.submit(new .Lambda._cls4(timelinetask));
    }

    final .Lambda._cls4 rescheduleReminderBlocking(TimelineTask timelinetask)
    {
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskConnection taskconnection = TaskDataFactory.instance.getTaskConnection();
        com.google.android.gms.reminders.model.Task task = taskconnection.loadTaskSynchronous(context, timelinetask.accountName, (String)timelinetask.id);
        com.google.android.gms.reminders.model.init> init> = new com.google.android.gms.reminders.model.init>(task);
        Object obj = context;
        long l = newStartTime;
        boolean flag = TaskUtils.shouldDueDateBeAbsolute(task);
        obj = Calendar.getInstance(Utils.getTimeZone(((Context) (obj))));
        ((Calendar) (obj)).setTimeInMillis(l);
        com.google.android.gms.reminders.model.init> init>1 = new com.google.android.gms.reminders.model.e();
        init>1.e = Boolean.valueOf(false);
        init>1.e = Integer.valueOf(((Calendar) (obj)).get(1));
        init>1.e = Integer.valueOf(((Calendar) (obj)).get(2) + 1);
        init>1.e = Integer.valueOf(((Calendar) (obj)).get(5));
        init>1.e = Boolean.valueOf(false);
        if (flag)
        {
            init>1.e = Long.valueOf(((Calendar) (obj)).getTimeInMillis());
        }
        com.google.android.gms.reminders.model.init> init>2 = new com.google.android.gms.reminders.model.e();
        init>2.e = Integer.valueOf(((Calendar) (obj)).get(11));
        init>2.e = Integer.valueOf(((Calendar) (obj)).get(12));
        init>2.e = Integer.valueOf(0);
        obj = new zzaj(init>2.e, init>2.e, init>2.e);
        if (obj != null)
        {
            obj = (Time)((Time) (obj)).freeze();
        } else
        {
            obj = null;
        }
        init>1.e = ((Time) (obj));
        obj = init>1.e();
        if (obj != null)
        {
            obj = (DateTime)((DateTime) (obj)).freeze();
        } else
        {
            obj = null;
        }
        init>.e = ((DateTime) (obj));
        init>.e = Boolean.valueOf(false);
        init>.e = Boolean.valueOf(false);
        init>.e = Boolean.valueOf(true);
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        init>.e = Long.valueOf(l);
        obj = init>.e();
        flag = taskconnection.updateTask(context, timelinetask.accountName, task, ((com.google.android.gms.reminders.model.Task) (obj)));
        if (taskCache != null)
        {
            taskCache.invalidate();
        }
        return withGenericMessage(flag);
    }

    final withGenericMessage withGenericMessage(boolean flag)
    {
        String s;
        if (flag)
        {
            s = null;
        } else
        {
            s = context.getResources().getString(0x7f1303f3);
        }
        return new withGenericMessage(flag, s);
    }

    public n(com.google.android.calendar.api.event. , long l)
    {
        this$0 = Rescheduler.this;
        super();
        boolean flag;
        if (l > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            guestNotification = ;
            newStartTime = l;
            return;
        }
    }
}
