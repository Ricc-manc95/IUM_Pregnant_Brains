// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.alerts.AlarmManagerInterface;
import com.google.android.calendar.alerts.AlertUtils;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.time.clock.Clock;
import java.util.HashSet;
import java.util.Set;

public class GrooveSyncTracker
{
    public static interface HabitSyncListener
    {

        public abstract void onHabitInstancesSynced();
    }


    public static GrooveSyncTracker instance;
    public SimpleArrayMap deferredEvents;
    public SimpleArrayMap habitCreationListeners;
    public SimpleArrayMap habitDescriptors;
    public Set habitInstancesSyncScheduled;
    public Set habitOperations;

    public GrooveSyncTracker()
    {
        habitCreationListeners = new SimpleArrayMap();
        habitDescriptors = new SimpleArrayMap();
        habitInstancesSyncScheduled = new HashSet();
        habitOperations = new HashSet();
        deferredEvents = new SimpleArrayMap();
    }

    public static void cancelForceInstancesSyncAlarm(Context context, String s, String s1, int i)
    {
        AlertUtils.createAlarmManager(context).cancel(getForceSyncPendingIntent(s, s1, context, null, false, i));
    }

    private static PendingIntent getForceSyncPendingIntent(String s, String s1, Context context, HabitDescriptor habitdescriptor, boolean flag, int i)
    {
        if (s1 != null)
        {
            s = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("-").append(s1).toString();
        }
        s1 = HabitsIntentServiceHelper.createForceSyncIntent(context, s, habitdescriptor, s1, flag, i);
        return PendingIntent.getBroadcast(context, s.hashCode(), s1, 0x8000000);
    }

    public static void scheduleForceInstancesSyncAlarm(Context context, HabitDescriptor habitdescriptor, int i, int j, String s)
    {
        AlarmManagerInterface alarmmanagerinterface = AlertUtils.createAlarmManager(context);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        alarmmanagerinterface.setExactAndAllowWhileIdle(0, l + (long)i, getForceSyncPendingIntent(habitdescriptor.habitId, s, context, habitdescriptor, true, j));
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/groove/GrooveSyncTracker);
    }
}
