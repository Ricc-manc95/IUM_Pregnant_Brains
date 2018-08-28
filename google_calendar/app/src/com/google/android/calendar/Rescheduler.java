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
import com.google.android.calendar.timely.TimelineItemUtil;
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
//            Utils

public class Rescheduler
{
    public static final class RescheduleResult
    {

        public final String feedback;
        public final boolean successful;

        RescheduleResult(boolean flag, String s)
        {
            successful = flag;
            feedback = s;
        }
    }

    public final class TimelineItemRescheduleOperation extends TimelineItemOperation
    {

        private final com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestNotification;
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
                    return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(event);
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
            return (FluentFuture)AbstractCatchingFuture.create((FluentFuture)AbstractTransformFuture.create(event, .Lambda._cls5..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), java/lang/Exception, .Lambda._cls6..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }

        public final Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            timelineitem = withGenericMessage(false);
            if (timelineitem == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
        }

        public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
        {
            class .Lambda._cls0
                implements AsyncFunction
            {

                private final TimelineItemRescheduleOperation arg$1;

                public final ListenableFuture apply(Object obj)
                {
                    return arg$1.TimelineItemRescheduleOperation((Event)obj);
                }

                .Lambda._cls0()
                {
                    arg$1 = TimelineItemRescheduleOperation.this;
                }
            }

            class .Lambda._cls1
                implements Function
            {

                private final TimelineItemRescheduleOperation arg$1;

                public final Object apply(Object obj)
                {
                    return arg$1.withGenericMessage(((Boolean)obj).booleanValue());
                }

                .Lambda._cls1()
                {
                    arg$1 = TimelineItemRescheduleOperation.this;
                }
            }

            return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(rescheduledEvent, new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            class .Lambda._cls2
                implements AsyncFunction
            {

                private final TimelineItemRescheduleOperation arg$1;

                public final ListenableFuture apply(Object obj)
                {
                    return arg$1.TimelineItemRescheduleOperation((Event)obj);
                }

                .Lambda._cls2()
                {
                    arg$1 = TimelineItemRescheduleOperation.this;
                }
            }

            class .Lambda._cls3
                implements Function
            {

                private final TimelineItemRescheduleOperation arg$1;
                private final TimelineGroove arg$2;

                public final Object apply(Object obj)
                {
                    TimelineItemRescheduleOperation timelineitemrescheduleoperation = arg$1;
                    TimelineGroove timelinegroove1 = arg$2;
                    if (!((Boolean)obj).booleanValue())
                    {
                        return timelineitemrescheduleoperation.withGenericMessage(false);
                    } else
                    {
                        timelineitemrescheduleoperation._fld0.context.startService(HabitsIntentServiceHelper.createRefreshNotificationsIntent(timelineitemrescheduleoperation._fld0.context, timelinegroove1.descriptor));
                        return new RescheduleResult(true, timelineitemrescheduleoperation._fld0.context.getResources().getString(0x7f130254));
                    }
                }

                .Lambda._cls3(TimelineGroove timelinegroove)
                {
                    arg$1 = TimelineItemRescheduleOperation.this;
                    arg$2 = timelinegroove;
                }
            }

            return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(rescheduledEvent, new .Lambda._cls2(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls3(timelinegroove), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            class .Lambda._cls4
                implements Callable
            {

                private final TimelineItemRescheduleOperation arg$1;
                private final TimelineTask arg$2;

                public final Object call()
                {
                    return arg$1.rescheduleReminderBlocking(arg$2);
                }

                .Lambda._cls4(TimelineTask timelinetask)
                {
                    arg$1 = TimelineItemRescheduleOperation.this;
                    arg$2 = timelinetask;
                }
            }

            return (FluentFuture)CalendarExecutor.NET.submit(new .Lambda._cls4(timelinetask));
        }

        final RescheduleResult rescheduleReminderBlocking(TimelineTask timelinetask)
        {
            if (TaskDataFactory.instance == null)
            {
                TaskDataFactory.instance = new TaskDataFactory();
            }
            TaskConnection taskconnection = TaskDataFactory.instance.getTaskConnection();
            com.google.android.gms.reminders.model.Task task = taskconnection.loadTaskSynchronous(context, timelinetask.accountName, (String)timelinetask.id);
            com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task);
            Object obj = context;
            long l = newStartTime;
            boolean flag = TaskUtils.shouldDueDateBeAbsolute(task);
            obj = Calendar.getInstance(Utils.getTimeZone(((Context) (obj))));
            ((Calendar) (obj)).setTimeInMillis(l);
            com.google.android.gms.reminders.model.DateTime.Builder builder1 = new com.google.android.gms.reminders.model.DateTime.Builder();
            builder1.zzchT = Boolean.valueOf(false);
            builder1.zzchV = Integer.valueOf(((Calendar) (obj)).get(1));
            builder1.zzchW = Integer.valueOf(((Calendar) (obj)).get(2) + 1);
            builder1.zzchX = Integer.valueOf(((Calendar) (obj)).get(5));
            builder1.zzcic = Boolean.valueOf(false);
            if (flag)
            {
                builder1.zzcib = Long.valueOf(((Calendar) (obj)).getTimeInMillis());
            }
            com.google.android.gms.reminders.model.Time.Builder builder2 = new com.google.android.gms.reminders.model.Time.Builder();
            builder2.zzcjG = Integer.valueOf(((Calendar) (obj)).get(11));
            builder2.zzcjH = Integer.valueOf(((Calendar) (obj)).get(12));
            builder2.zzcjI = Integer.valueOf(0);
            obj = new zzaj(builder2.zzcjG, builder2.zzcjH, builder2.zzcjI);
            if (obj != null)
            {
                obj = (Time)((Time) (obj)).freeze();
            } else
            {
                obj = null;
            }
            builder1.zzchY = ((Time) (obj));
            obj = builder1.build();
            if (obj != null)
            {
                obj = (DateTime)((DateTime) (obj)).freeze();
            } else
            {
                obj = null;
            }
            builder.zzcjl = ((DateTime) (obj));
            builder.zzcjg = Boolean.valueOf(false);
            builder.zzcji = Boolean.valueOf(false);
            builder.zzcjj = Boolean.valueOf(true);
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            builder.zzcjk = Long.valueOf(l);
            obj = builder.build();
            flag = taskconnection.updateTask(context, timelinetask.accountName, task, ((com.google.android.gms.reminders.model.Task) (obj)));
            if (taskCache != null)
            {
                taskCache.invalidate();
            }
            return withGenericMessage(flag);
        }

        final RescheduleResult withGenericMessage(boolean flag)
        {
            String s;
            if (flag)
            {
                s = null;
            } else
            {
                s = context.getResources().getString(0x7f1303f3);
            }
            return new RescheduleResult(flag, s);
        }

        public TimelineItemRescheduleOperation(com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestnotification, long l)
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
                guestNotification = guestnotification;
                newStartTime = l;
                return;
            }
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/Rescheduler);
    public final Context context;
    public final FluentFuture rescheduledEvent;
    public final TimelineItem rescheduledItem;
    public final ListenableFutureCache taskCache;

    public Rescheduler(Context context1, TimelineItem timelineitem, ListenableFutureCache listenablefuturecache)
    {
        context = context1;
        taskCache = listenablefuturecache;
        rescheduledItem = timelineitem;
        context1 = TimelineItemUtil.resolveNewApiObject(rescheduledItem);
        if (context1 == null)
        {
            context1 = null;
        } else
        if (context1 instanceof FluentFuture)
        {
            context1 = (FluentFuture)context1;
        } else
        {
            context1 = new ForwardingFluentFuture(context1);
        }
        rescheduledEvent = context1;
    }

    public static String isReschedulable(final Resources resources, TimelineItem timelineitem)
    {
        return (String)timelineitem.perform(new _cls1(), new Void[0]);
    }

    public static String isReschedulableTo(final Resources resources, TimelineItem timelineitem, final long newStartTimeMillis)
    {
        String s = (String)timelineitem.perform(new _cls1(), new Void[0]);
        if (s != null)
        {
            return s;
        } else
        {
            return (String)timelineitem.perform(new _cls2(), new Void[0]);
        }
    }


    private class _cls1 extends TimelineItemOperation
    {

        private final Resources val$resources;

        public final Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            return resources.getString(0x7f130102);
        }

        public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
        {
label0:
            {
                boolean flag = false;
                if (timelineevent.getClass() != com/google/android/calendar/timely/TimelineEvent)
                {
                    return (String)super.onAnyEvent(timelineevent, new Void[0]);
                }
                if (timelineevent.isInstanceModifiable)
                {
                    aobj = timelineevent.calendarAccessLevel;
                    CalendarAccessLevel calendaraccesslevel = CalendarAccessLevel.WRITER;
                    if (calendaraccesslevel == null)
                    {
                        throw new NullPointerException();
                    }
                    if (((CalendarAccessLevel) (aobj)).level - calendaraccesslevel.level < 0)
                    {
                        flag = true;
                    }
                    if (!flag && TextUtils.equals(timelineevent.organizer, timelineevent.ownerAccount))
                    {
                        break label0;
                    }
                }
                return resources.getString(0x7f130102);
            }
            return null;
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            timelinegroove = ((TimelineEvent) (timelinegroove)).calendarAccessLevel;
            aobj = CalendarAccessLevel.WRITER;
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            boolean flag;
            if (((CalendarAccessLevel) (timelinegroove)).level - ((CalendarAccessLevel) (aobj)).level < 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return resources.getString(0x7f130102);
            } else
            {
                return null;
            }
        }

        public final volatile Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            return null;
        }

        _cls1()
        {
            resources = resources1;
            super();
        }
    }


    private class _cls2 extends TimelineItemOperation
    {

        private final long val$newStartTimeMillis;
        private final Resources val$resources;

        public final volatile Object onAny(TimelineItem timelineitem, Object aobj[])
        {
            return null;
        }

        public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
        {
            long l1 = newStartTimeMillis;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            if (l1 >= l)
            {
                return null;
            } else
            {
                return resources.getString(0x7f130366);
            }
        }

        _cls2()
        {
            newStartTimeMillis = l;
            resources = resources1;
            super();
        }
    }

}
