// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.NewNotificationsFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.calendar.groove.category.GrooveCategories;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.notification.NotificationChannels;
import com.google.android.calendar.utils.notification.NotificationPrefs;
import com.google.android.calendar.utils.notification.NotificationUtil;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper, AlertUtils

public class HabitsAlerts
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/HabitsAlerts);

    private HabitsAlerts()
    {
    }

    private static void addCompleteAction(Context context, HabitDescriptor habitdescriptor, EventKey eventkey, android.support.v4.app.NotificationCompat.Builder builder, UserNotification usernotification, int i, String s, String s1)
    {
        s = s.toUpperCase();
        context = PendingIntent.getService(context, i, HabitsIntentServiceHelper.createCompleteIntent(context, habitdescriptor, eventkey, usernotification, i, true, s1), 0x8000000);
        builder.mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201e3, s, context));
    }

    private static void addDeferAction(Context context, HabitDescriptor habitdescriptor, EventKey eventkey, android.support.v4.app.NotificationCompat.Builder builder, UserNotification usernotification, int i, String s)
    {
        String s1 = context.getResources().getString(0x7f130085).toUpperCase();
        context = PendingIntent.getService(context, i, HabitsIntentServiceHelper.createDeferIntent(context, habitdescriptor, eventkey, usernotification, i, s), 0x8000000);
        builder.mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f02022e, s1, context));
    }

    public static boolean areNewHabitNotificationsEnabled()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).new_notifications();
        return RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled() && RemoteFeatureConfig.NEW_NOTIFICATIONS.getSupportsHabits().booleanValue();
    }

    public static void cancel(Context context, int i)
    {
        ((NotificationManager)context.getSystemService("notification")).cancel("Habits", i);
    }

    public static void cancelLegacy(Context context, String s)
    {
        if (areNewHabitNotificationsEnabled())
        {
            LogUtils.i(TAG, "Cancelling notification is ignored.", new Object[0]);
            return;
        } else
        {
            int i = s.hashCode();
            ((NotificationManager)context.getSystemService("notification")).cancel("Habits", i);
            return;
        }
    }

    private static android.support.v4.app.NotificationCompat.Builder createBuilder(Context context, HabitDescriptor habitdescriptor, EventKey eventkey, UserNotification usernotification, int i)
    {
        android.support.v4.app.NotificationCompat.Builder builder = null;
        boolean flag = false;
        Object obj1 = new NotificationPrefs(context, context.getSharedPreferences("com.google.android.calendar_preferences", 0));
        if (((NotificationPrefs) (obj1)).silenced)
        {
            flag = true;
        }
        Object obj;
        int j;
        if (flag)
        {
            obj = null;
        } else
        {
            if (((NotificationPrefs) (obj1)).ringtone == null)
            {
                obj1.ringtone = PreferencesUtils.getRingtonePreference(((NotificationPrefs) (obj1)).context);
            }
            obj = ((NotificationPrefs) (obj1)).ringtone;
        }
        obj1.silenced = true;
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = builder;
        } else
        {
            obj = Uri.parse(((String) (obj)));
        }
        builder = new android.support.v4.app.NotificationCompat.Builder(context);
        j = AlertUtils.getNotificationDefaults(((NotificationPrefs) (obj1)));
        builder.mNotification.defaults = j;
        if ((j & 4) != 0)
        {
            obj1 = builder.mNotification;
            obj1.flags = ((Notification) (obj1)).flags | 1;
        }
        builder.mNotification.icon = 0x7f020133;
        builder.mColor = context.getResources().getColor(0x7f0d01d7);
        builder.mContentIntent = PendingIntent.getService(context, i, HabitsIntentServiceHelper.createViewIntent(context, habitdescriptor, eventkey, usernotification, i), 0x8000000);
        context = PendingIntent.getService(context, i, HabitsIntentServiceHelper.createDismissIntent(context, habitdescriptor, eventkey, usernotification, i), 0x8000000);
        builder.mNotification.deleteIntent = context;
        context = builder.setAutoCancel(true);
        if (obj != null)
        {
            context.setSound(((Uri) (obj)));
        }
        return context;
    }

    public static void showBelongNotification(Context context, Habit habit, EventKey eventkey, UserNotification usernotification, int i)
    {
        Object obj = context.getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(((Resources) (obj)));
        }
        obj = context.getString(0x7f1300dd, new Object[] {
            GrooveCategories.getName(habit)
        });
        String s = context.getString(0x7f1300dc);
        eventkey = createBuilder(context, habit.getDescriptor(), eventkey, usernotification, -2).setContentTitle(((CharSequence) (obj))).setContentText(s).setStyle((new android.support.v4.app.NotificationCompat.BigTextStyle()).bigText(s));
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(context, NotificationChannels.channelsCreated);
            eventkey.mChannelId = "REMINDERS";
        }
        eventkey = eventkey.build();
        habit = habit.getDescriptor();
        if (!areNewHabitNotificationsEnabled())
        {
            i = ((HabitDescriptor) (habit)).habitId.hashCode();
        }
        showNotification(context, eventkey, i);
    }

    public static void showFollowupNotification(Context context, Habit habit, EventKey eventkey, UserNotification usernotification, int i)
    {
        android.support.v4.app.NotificationCompat.Builder builder = createBuilder(context, habit.getDescriptor(), eventkey, usernotification, i);
        Resources resources = context.getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(resources);
        }
        builder = builder.setContentTitle(GrooveCategories.getName(habit)).setContentText(context.getResources().getString(0x7f1302af));
        addDeferAction(context, habit.getDescriptor(), eventkey, builder, usernotification, i, "notification_followup");
        addCompleteAction(context, habit.getDescriptor(), eventkey, builder, usernotification, i, context.getResources().getString(0x7f130082), "notification_followup");
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(context, NotificationChannels.channelsCreated);
            builder.mChannelId = "REMINDERS";
        }
        eventkey = builder.build();
        habit = habit.getDescriptor();
        if (!areNewHabitNotificationsEnabled())
        {
            i = ((HabitDescriptor) (habit)).habitId.hashCode();
        }
        showNotification(context, eventkey, i);
    }

    private static void showNotification(Context context, Notification notification, int i)
    {
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService("notification");
        if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_alerts", true))
        {
            LogUtils.d(TAG, "Notifications are disabled.", new Object[0]);
            notificationmanager.cancelAll();
            return;
        } else
        {
            NotificationUtil.notify(notificationmanager, "Habits", i, notification);
            return;
        }
    }

    public static void showPreinstanceNotification(Context context, Habit habit, EventKey eventkey, long l, long l1, UserNotification usernotification, 
            int i)
    {
        android.support.v4.app.NotificationCompat.Builder builder = createBuilder(context, habit.getDescriptor(), eventkey, usernotification, i);
        builder.mCategory = "event";
        Resources resources = context.getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(resources);
        }
        builder = builder.setContentTitle(GrooveCategories.getName(habit));
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        builder = builder.setContentText(Utils.getDisplayedDatetime(l, l1, l2, Utils.getTimeZoneId(context, null), false, true, context));
        addDeferAction(context, habit.getDescriptor(), eventkey, builder, usernotification, i, "notification_preinstance");
        if (!habit.getReminders().enableFollowup)
        {
            addCompleteAction(context, habit.getDescriptor(), eventkey, builder, usernotification, i, context.getResources().getString(0x7f130082), "notification_preinstance");
        }
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(context, NotificationChannels.channelsCreated);
            builder.mChannelId = "REMINDERS";
        }
        eventkey = builder.build();
        habit = habit.getDescriptor();
        if (!areNewHabitNotificationsEnabled())
        {
            i = ((HabitDescriptor) (habit)).habitId.hashCode();
        }
        showNotification(context, eventkey, i);
    }

    public static void showRecommitNotification(Context context, Habit habit, EventKey eventkey, long l, long l1, UserNotification usernotification, 
            int i)
    {
        Object obj;
        android.support.v4.app.NotificationCompat.Builder builder;
        Resources resources;
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        obj = Utils.getDisplayedDatetime(l, l1, l2, Utils.getTimeZoneId(context, null), false, true, context);
        obj = context.getResources().getString(0x7f1302d1, new Object[] {
            obj
        });
        builder = createBuilder(context, habit.getDescriptor(), eventkey, usernotification, i);
        resources = context.getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(resources);
        }
        obj = builder.setContentTitle(GrooveCategories.getName(habit)).setContentText(((CharSequence) (obj))).setStyle((new android.support.v4.app.NotificationCompat.BigTextStyle()).bigText(((CharSequence) (obj))));
        addDeferAction(context, habit.getDescriptor(), eventkey, ((android.support.v4.app.NotificationCompat.Builder) (obj)), usernotification, i, "notification_recommit");
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(context, NotificationChannels.channelsCreated);
            obj.mChannelId = "REMINDERS";
        }
        eventkey = ((android.support.v4.app.NotificationCompat.Builder) (obj)).build();
        habit = habit.getDescriptor();
        if (!areNewHabitNotificationsEnabled())
        {
            i = ((HabitDescriptor) (habit)).habitId.hashCode();
        }
        showNotification(context, eventkey, i);
    }

}
