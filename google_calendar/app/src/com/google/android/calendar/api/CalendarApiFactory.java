// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptorFactory;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventNotificationClient;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;

public abstract class CalendarApiFactory
{

    public static CalendarApiFactory instance;

    public CalendarApiFactory()
    {
    }

    public abstract CalendarListClient getCalendarList();

    public abstract CalendarListFactory getCalendarListFactory();

    public abstract ColorFactory getColorFactory(Context context);

    public abstract EventDescriptorFactory getEventDescriptorFactory();

    public abstract EventFactory getEventFactory();

    public abstract EventNotificationClient getEventNotifications();

    public abstract EventPermissionsFactory getEventPermissionsFactory();

    public abstract EventClient getEvents();

    public abstract HabitFactory getHabitFactory();

    public abstract HabitClient getHabits();

    public abstract SettingsClient getSettings();

    public abstract SettingsFactory getSettingsFactory();

    public abstract void initializeCaches(Context context);
}
