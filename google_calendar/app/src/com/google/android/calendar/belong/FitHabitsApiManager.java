// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.gms.common.api.PendingResult;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FitHabitsApiManager
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitHabitsApiManager);
    public final Account account;
    private final CalendarListClient calendarListClient;
    public final Context context;
    private final EventClient eventClient;
    public final HabitClient habitClient;

    FitHabitsApiManager(Context context1, Account account1)
    {
        this(context1, account1, CalendarApi.Habits, CalendarApi.Events, CalendarApi.CalendarList);
    }

    private FitHabitsApiManager(Context context1, Account account1, HabitClient habitclient, EventClient eventclient, CalendarListClient calendarlistclient)
    {
        context = context1;
        account = account1;
        habitClient = habitclient;
        eventClient = eventclient;
        calendarListClient = calendarlistclient;
    }

    static final List lambda$getInstancesForHabits$1$FitHabitsApiManager$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR8C5H6IT1F91GM4QBK7D66KOBMC4NNAT39DGNKOQBJEGTKOQJ1EPGIUTBKD5M2UJ39EDQ3MAACD9GNCO9FELQ6IR1F9HKN6T1R0(List list, List list1)
    {
        list1.addAll(list);
        return list1;
    }

    final Event createNewInstanceImpl(Habit habit, long l)
    {
        Object obj;
        try
        {
            obj = (CalendarListEntry)calendarListClient.read(habit.getDescriptor().calendar).get();
        }
        // Misplaced declaration of an exception variable
        catch (Habit habit)
        {
            LogUtils.wtf(TAG, "Failed to retrieve the calendar.", new Object[0]);
            return null;
        }
        obj = CalendarApi.EventFactory.newHabitInstance(habit, ((CalendarListEntry) (obj)));
        ((EventModifications) (obj)).setToTimedWithTimes(l, TimeUnit.MINUTES.toMillis(habit.getContract().getDurationMinutes()) + l);
        ((HabitInstanceModifications)((EventModifications) (obj)).getHabitInstanceModifications().getValue()).setStatus(3, true);
        habit = (Event)eventClient.create(((EventModifications) (obj)), com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED).get();
        obj = Features.instance;
        if (obj == null)
        {
            try
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            // Misplaced declaration of an exception variable
            catch (Habit habit)
            {
                LogUtils.e(TAG, habit, "Failed to create the instance.", new Object[0]);
            }
            return null;
        }
        ((FeatureConfig)obj).dogfood_features();
        return habit;
    }

    final boolean hasHabitsWithEnabledIntegration(long l)
    {
        HabitClient habitclient = habitClient;
        HabitFilterOptions habitfilteroptions = (new HabitFilterOptions(account.name)).setFitIntegrationStatus(Integer.valueOf(20));
        habitfilteroptions.activeAfterFilter = Long.valueOf(l);
        return ((com.google.android.calendar.api.habit.HabitClient.GenericResult)habitclient.count(habitfilteroptions).await()).getCount() > 0;
    }

    final Event moveAndMarkAsDone(EventDescriptor eventdescriptor, long l)
    {
        Feature feature;
        long l1;
        long l2;
        try
        {
            eventdescriptor = (Event)eventClient.read(eventdescriptor).get();
        }
        // Misplaced declaration of an exception variable
        catch (EventDescriptor eventdescriptor)
        {
            LogUtils.e(TAG, eventdescriptor, "Failed to read the instance.", new Object[0]);
            return null;
        }
        l1 = eventdescriptor.getEndMillis();
        l2 = eventdescriptor.getStartMillis();
        eventdescriptor = CalendarApi.EventFactory.modifyEvent(eventdescriptor);
        eventdescriptor.setStartMillis(l);
        eventdescriptor.setEndMillis((l1 - l2) + l);
        feature = eventdescriptor.getHabitInstanceModifications();
        if (!feature.isSupported() || feature.getValue() == null)
        {
            eventdescriptor = null;
        } else
        {
            ((HabitInstanceModifications)feature.getValue()).setStatus(3, true);
        }
        if (eventdescriptor == null)
        {
            LogUtils.e(TAG, "Failed to modify the instance.", new Object[0]);
            return null;
        }
        try
        {
            eventdescriptor = (Event)eventClient.update(eventdescriptor, 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED).get();
        }
        // Misplaced declaration of an exception variable
        catch (EventDescriptor eventdescriptor)
        {
            LogUtils.e(TAG, eventdescriptor, "Failed to update the instance.", new Object[0]);
            return null;
        }
        return eventdescriptor;
    }

}
