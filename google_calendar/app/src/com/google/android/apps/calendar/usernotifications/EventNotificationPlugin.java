// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.NewNotificationsFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.usernotificationsframework.contracts.AutoValue_UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationPlugin;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotificationState;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.ListenableFuturePendingResult;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.EventNotificationClient;
import com.google.android.calendar.api.event.NotificationInfo;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.utils.notification.NotificationPrefs;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Pair;
import com.google.common.base.Present;
import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.TimeUnit;

public final class EventNotificationPlugin
    implements UserNotificationPlugin
{

    public static final long MIN_SOUND_INTERVAL_MILLIS;
    private static final long NOTIFICATION_RELEVANCE_INTERVAL;
    public final EventNotificationPresenter eventNotificationPresenter;
    public final HabitNotificationPresenter habitNotificationPresenter;
    public Long lastPlayedSoundMillis;
    public final NotificationPrefs notificationPrefs;
    private final SharedPreferences sharedPreferences;

    public EventNotificationPlugin(Context context, EventNotificationPresenter eventnotificationpresenter, HabitNotificationPresenter habitnotificationpresenter)
    {
        eventNotificationPresenter = eventnotificationpresenter;
        habitNotificationPresenter = habitnotificationpresenter;
        sharedPreferences = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
        notificationPrefs = new NotificationPrefs(context, sharedPreferences);
    }

    static final UserNotification bridge$lambda$0$EventNotificationPlugin(NotificationInfo notificationinfo)
    {
        EventKey eventkey = notificationinfo.getEventKey();
        StringBuilder stringbuilder = (new StringBuilder(eventkey.getClass().getSimpleName())).append('|');
        eventkey.serializeInternal(stringbuilder);
        return new AutoValue_UserNotification(1, stringbuilder.toString(), notificationinfo.getType().ordinal(), notificationinfo.getTriggerMillis(), notificationinfo.getExpirationMillis());
    }

    static final List lambda$getRelevantNotifications$0$EventNotificationPlugin(List list)
    {
        class .Lambda._cls7
            implements Function
        {

            public static final Function $instance = new .Lambda._cls7();

            public final Object apply(Object obj)
            {
                return EventNotificationPlugin.bridge$lambda$0$EventNotificationPlugin((NotificationInfo)obj);
            }


            private .Lambda._cls7()
            {
            }
        }

        Function function = .Lambda._cls7..instance;
        if (list instanceof RandomAccess)
        {
            return new com.google.common.collect.Lists.TransformingRandomAccessList(list, function);
        } else
        {
            return new com.google.common.collect.Lists.TransformingSequentialList(list, function);
        }
    }

    static final ListenableFuture lambda$getRelevantNotifications$1$EventNotificationPlugin(long l, long l1, CalendarListEntry acalendarlistentry[])
        throws Exception
    {
        acalendarlistentry = Arrays.asList(acalendarlistentry);
        class .Lambda._cls5
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls5();

            public final boolean apply(Object obj)
            {
                return ((CalendarListEntry)obj).isVisible();
            }


            private .Lambda._cls5()
            {
            }
        }

        Predicate predicate;
        if (acalendarlistentry instanceof FluentIterable)
        {
            acalendarlistentry = (FluentIterable)acalendarlistentry;
        } else
        {
            acalendarlistentry = new com.google.common.collect.FluentIterable._cls1(acalendarlistentry, acalendarlistentry);
        }
        predicate = .Lambda._cls5..instance;
        acalendarlistentry = (Iterable)((FluentIterable) (acalendarlistentry)).iterableDelegate.or(acalendarlistentry);
        if (acalendarlistentry == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        acalendarlistentry = new com.google.common.collect.Iterables._cls4(acalendarlistentry, predicate);
        class .Lambda._cls6
            implements Function
        {

            public static final Function $instance = new .Lambda._cls6();

            public final Object apply(Object obj)
            {
                return EventNotificationPlugin.lambda$getRelevantNotifications$0$EventNotificationPlugin((List)obj);
            }


            private .Lambda._cls6()
            {
            }
        }

        if (acalendarlistentry instanceof FluentIterable)
        {
            acalendarlistentry = (FluentIterable)acalendarlistentry;
        } else
        {
            acalendarlistentry = new com.google.common.collect.FluentIterable._cls1(acalendarlistentry, acalendarlistentry);
        }
        return AbstractTransformFuture.create(CalendarApi.EventNotifications.getNotifications(acalendarlistentry, l, l1), .Lambda._cls6..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    static final Pair lambda$show$3$EventNotificationPlugin(Event event, com.google.android.calendar.api.habit.HabitClient.ReadResult readresult)
    {
        return new Pair(event, readresult.getHabit());
    }

    static final ListenableFuture lambda$show$4$EventNotificationPlugin(Event event)
        throws Exception
    {
        com.google.android.gms.common.api.PendingResult pendingresult = CalendarApi.Habits.read(((HabitInstance)event.getHabitInstance().getValue()).getHabitParentDescriptor());
        class .Lambda._cls4
            implements Function
        {

            private final Event arg$1;

            public final Object apply(Object obj)
            {
                return EventNotificationPlugin.lambda$show$3$EventNotificationPlugin(arg$1, (com.google.android.calendar.api.habit.HabitClient.ReadResult)obj);
            }

            .Lambda._cls4(Event event)
            {
                arg$1 = event;
            }
        }

        .Lambda._cls4 _lcls4 = new .Lambda._cls4(event);
        if (pendingresult instanceof ListenableFuturePendingResult)
        {
            event = ((ListenableFuturePendingResult)pendingresult).getFuture();
        } else
        {
            event = new com.google.android.apps.calendar.util.gms.GmsFutures.PendingResultFuture(pendingresult);
        }
        return AbstractTransformFuture.create(event, new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(_lcls4), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final int getId()
    {
        return 1;
    }

    public final ListenableFuture getRelevantNotifications(long l, long l1)
    {
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final long arg$1;
            private final long arg$2;

            public final ListenableFuture apply(Object obj)
            {
                return EventNotificationPlugin.lambda$getRelevantNotifications$1$EventNotificationPlugin(arg$1, arg$2, (CalendarListEntry[])obj);
            }

            .Lambda._cls0(long l, long l1)
            {
                arg$1 = l;
                arg$2 = l1;
            }
        }

        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            return AbstractTransformFuture.create(((ListenableFutureCache)listenablefuturecache).getValueAsync(), new .Lambda._cls0(l, l1), CalendarExecutor.BACKGROUND);
        }
    }

    public final boolean onNotificationStateUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELPMASJEDTQ6IPJ9CDGN8QBFDPPMCSJ1DLINERRIDCNM6RREEHP62ORKECNLASR5E976UT39CPKM6OBKD5NMSEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UTBJCLP6SRRKD5J6IOR1EHKMURJJCPP62RB5ETNN4QPFCDNMST3IC5HN8SPFALPMASIEDTQ6IPJ9CDGN8QBFDP9N8OBKCKTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQN6PBIDPNN8QB6D5HM2T39DTN76PJIC5MMATRFE9LIUORFDPQ74OB3EHPIULBJCLP4SRRKD5J6IOR1EHKMURIJEHGN8P9R9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQFE1Q6IRREC5M3MAAQ0(UserNotification usernotification, UserNotificationState usernotificationstate, UserNotificationState usernotificationstate1)
    {
        com.google.android.calendar.api.event.NotificationInfo.NotificationType notificationtype;
        notificationtype = com.google.android.calendar.api.event.NotificationInfo.NotificationType.fromInteger(usernotification.getType());
        if (notificationtype == com.google.android.calendar.api.event.NotificationInfo.NotificationType.UNKNOWN)
        {
            LogUtils.wtf(TAG, "Unknown notification type.", new Object[0]);
            return false;
        }
        if (!sharedPreferences.getBoolean("preferences_alerts", true))
        {
            LogUtils.i(TAG, "Notifications are disabled on this device.", new Object[0]);
            return false;
        }
        if (notificationtype != com.google.android.calendar.api.event.NotificationInfo.NotificationType.EVENT && !RemoteFeatureConfig.NEW_NOTIFICATIONS.getSupportsHabits().booleanValue())
        {
            LogUtils.w(TAG, "Processing habit notification when such notifications are not supported.", new Object[0]);
            return true;
        }
        usernotificationstate1.ordinal();
        JVM INSTR tableswitch 0 6: default 148
    //                   0 440
    //                   1 440
    //                   2 150
    //                   3 364
    //                   4 148
    //                   5 406
    //                   6 406;
           goto _L1 _L2 _L2 _L3 _L4 _L1 _L5 _L5
_L6:
        return true;
_L3:
        usernotificationstate1 = EventKey.deserialize(usernotification.getEntityFingerprint());
        usernotificationstate1 = CalendarApi.Events.read(usernotificationstate1);
        switch (notificationtype.ordinal())
        {
        case 0: // '\0'
            LogUtils.wtf(TAG, "Unexpected notification type.", new Object[0]);
            break;

        case 1: // '\001'
            class .Lambda._cls1
                implements Consumer
            {

                private final EventNotificationPlugin arg$1;
                private final UserNotification arg$2;
                private final UserNotificationState arg$3;

                public final void accept(Object obj)
                {
                    EventNotificationPlugin eventnotificationplugin;
                    UserNotification usernotification1;
                    UserNotificationState usernotificationstate2;
                    EventNotificationPresenter eventnotificationpresenter;
                    boolean flag;
                    boolean flag1;
                    flag = false;
                    flag1 = false;
                    eventnotificationplugin = arg$1;
                    usernotification1 = arg$2;
                    usernotificationstate2 = arg$3;
                    obj = (Event)obj;
                    eventnotificationpresenter = eventnotificationplugin.eventNotificationPresenter;
                    if (usernotificationstate2 == UserNotificationState.NOT_FIRED) goto _L2; else goto _L1
_L1:
                    eventnotificationpresenter.show(((Event) (obj)), usernotification1, flag1, eventnotificationplugin.notificationPrefs);
                    return;
_L2:
                    long l = SystemClock.elapsedRealtime();
                    if (eventnotificationplugin.lastPlayedSoundMillis == null || l > eventnotificationplugin.lastPlayedSoundMillis.longValue() + EventNotificationPlugin.MIN_SOUND_INTERVAL_MILLIS)
                    {
                        flag = true;
                    }
                    flag1 = flag;
                    if (flag)
                    {
                        eventnotificationplugin.lastPlayedSoundMillis = Long.valueOf(l);
                        NotificationPrefs notificationprefs = eventnotificationplugin.notificationPrefs;
                        notificationprefs.defaultVibrate = null;
                        notificationprefs.ringtone = null;
                        flag1 = flag;
                    }
                    if (true) goto _L1; else goto _L3
_L3:
                }

            .Lambda._cls1(UserNotification usernotification, UserNotificationState usernotificationstate)
            {
                arg$1 = EventNotificationPlugin.this;
                arg$2 = usernotification;
                arg$3 = usernotificationstate;
            }
            }

            usernotification = LogUtils.newFailureLoggingCallback(new .Lambda._cls1(usernotification, usernotificationstate), TAG, "Failed to load event and show notification.", new Object[0]);
            usernotificationstate = CalendarExecutor.BACKGROUND;
            if (usernotification == null)
            {
                throw new NullPointerException();
            }
            usernotificationstate1.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(usernotificationstate1, usernotification), usernotificationstate);
            break;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            class .Lambda._cls2
                implements AsyncFunction
            {

                public static final AsyncFunction $instance = new .Lambda._cls2();

                public final ListenableFuture apply(Object obj)
                {
                    return EventNotificationPlugin.lambda$show$4$EventNotificationPlugin((Event)obj);
                }


            private .Lambda._cls2()
            {
            }
            }

            usernotificationstate = AbstractTransformFuture.create(usernotificationstate1, .Lambda._cls2..instance, CalendarExecutor.BACKGROUND);
            class .Lambda._cls3
                implements Consumer
            {

                private final EventNotificationPlugin arg$1;
                private final UserNotification arg$2;

                public final void accept(Object obj)
                {
                    EventNotificationPlugin eventnotificationplugin = arg$1;
                    UserNotification usernotification1 = arg$2;
                    obj = (Pair)obj;
                    eventnotificationplugin.habitNotificationPresenter.show((Habit)((Pair) (obj)).second, (Event)((Pair) (obj)).first, usernotification1);
                }

            .Lambda._cls3(UserNotification usernotification)
            {
                arg$1 = EventNotificationPlugin.this;
                arg$2 = usernotification;
            }
            }

            usernotification = LogUtils.newFailureLoggingCallback(new .Lambda._cls3(usernotification), TAG, "Failed to load event or habit and show notification.", new Object[0]);
            usernotificationstate1 = CalendarExecutor.BACKGROUND;
            if (usernotification == null)
            {
                throw new NullPointerException();
            }
            usernotificationstate.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(usernotificationstate, usernotification), usernotificationstate1);
            break;
        }
_L1:
        if (false)
        {
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (notificationtype != com.google.android.calendar.api.event.NotificationInfo.NotificationType.EVENT)
        {
            if (notificationtype == com.google.android.calendar.api.event.NotificationInfo.NotificationType.EVENT)
            {
                eventNotificationPresenter.hide(usernotification);
            } else
            {
                habitNotificationPresenter.hide(usernotification);
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (notificationtype == com.google.android.calendar.api.event.NotificationInfo.NotificationType.EVENT)
        {
            eventNotificationPresenter.hide(usernotification);
        } else
        {
            habitNotificationPresenter.hide(usernotification);
        }
        if (true) goto _L6; else goto _L2
_L2:
        throw new IllegalStateException("Illegal target state.");
    }

    public final Optional relevanceIntervalMillis(UserNotification usernotification)
    {
        if (usernotification.getType() == com.google.android.calendar.api.event.NotificationInfo.NotificationType.HABIT_FIT_CHECK.value || usernotification.getType() == com.google.android.calendar.api.event.NotificationInfo.NotificationType.HABIT_FOLLOWUP.value)
        {
            return Absent.INSTANCE;
        }
        usernotification = Long.valueOf(NOTIFICATION_RELEVANCE_INTERVAL);
        if (usernotification == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(usernotification);
        }
    }

    public final boolean shouldReshowNotification(UserNotification usernotification, UserNotificationState usernotificationstate)
    {
        while (usernotification.getType() == com.google.android.calendar.api.event.NotificationInfo.NotificationType.HABIT_RESOLVED_BY_FIT.value || usernotification.getType() == com.google.android.calendar.api.event.NotificationInfo.NotificationType.HABIT_FIT_CHECK.value || usernotificationstate != UserNotificationState.FIRED) 
        {
            return false;
        }
        return true;
    }

    static 
    {
        MIN_SOUND_INTERVAL_MILLIS = TimeUnit.SECONDS.toMillis(15L);
        NOTIFICATION_RELEVANCE_INTERVAL = TimeUnit.HOURS.toMillis(2L);
    }

    private class EventNotificationPresenter
    {

        public abstract void hide(UserNotification usernotification);

        public abstract void show(Event event, UserNotification usernotification, boolean flag, NotificationPrefs notificationprefs);
    }


    private class HabitNotificationPresenter
    {

        public abstract void hide(UserNotification usernotification);

        public abstract void show(Habit habit, Event event, UserNotification usernotification);
    }

}
