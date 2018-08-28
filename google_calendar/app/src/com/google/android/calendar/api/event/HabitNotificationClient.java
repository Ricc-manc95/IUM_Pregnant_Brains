// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.NewNotificationsFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.api.event:
//            AutoValue_NotificationInfo, EventKey

class HabitNotificationClient
{
    static abstract class HabitInstance
    {

        public abstract HabitDescriptor getDescriptor();

        public abstract long getEndMillis();

        public abstract EventKey getEventKey();

        public abstract boolean getResolvedByFit();

        public abstract long getStartMillis();

        HabitInstance()
        {
        }
    }


    public static final long HABIT_NOTIFICATION_EXPIRATION_INTERVAL;
    public static final long MAX_FOLLOW_UP_NOTIFICATION_INTERVAL;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/HabitNotificationClient);
    public final Context context;

    HabitNotificationClient(Context context1)
    {
        context = context1;
    }

    static boolean areHabitNotificationsSupported()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).new_notifications();
        return RemoteFeatureConfig.NEW_NOTIFICATIONS.enabled() && RemoteFeatureConfig.NEW_NOTIFICATIONS.getSupportsHabits().booleanValue();
    }

    static final HabitDescriptor lambda$getHabitNotificationsAsync$0$HabitNotificationClient(HabitInstance habitinstance)
    {
        return habitinstance.getDescriptor();
    }

    static final Map lambda$getHabitNotificationsAsync$1$HabitNotificationClient(com.google.android.calendar.api.habit.HabitClient.BulkReadResult bulkreadresult)
    {
        HashMap hashmap = new HashMap();
        bulkreadresult = bulkreadresult.getHabits();
        int j = bulkreadresult.length;
        for (int i = 0; i < j; i++)
        {
            Habit habit = bulkreadresult[i];
            hashmap.put(habit.getDescriptor().habitId, habit);
        }

        return hashmap;
    }

    static void maybeAddNotification(EventKey eventkey, NotificationInfo.NotificationType notificationtype, long l, long l1, long l2, 
            long l3, com.google.common.collect.ImmutableList.Builder builder)
    {
        if (l < l3 && l1 > l2)
        {
            eventkey = (com.google.common.collect.ImmutableList.Builder)builder.add(new AutoValue_NotificationInfo(eventkey, notificationtype, l, l1));
        }
    }

    static 
    {
        MAX_FOLLOW_UP_NOTIFICATION_INTERVAL = TimeUnit.MINUTES.toMillis(15L);
        HABIT_NOTIFICATION_EXPIRATION_INTERVAL = TimeUnit.HOURS.toMillis(12L);
    }
}
