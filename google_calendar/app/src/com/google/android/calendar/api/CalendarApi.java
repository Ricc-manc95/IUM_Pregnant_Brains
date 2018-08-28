// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api;

import android.content.ContentResolver;
import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventNotificationClient;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;

// Referenced classes of package com.google.android.calendar.api:
//            CalendarApiFactory

public final class CalendarApi
{

    public static final CalendarListClient CalendarList;
    public static final CalendarListFactory CalendarListFactory;
    public static final EventFactory EventFactory;
    public static final EventNotificationClient EventNotifications;
    public static final EventPermissionsFactory EventPermissionsFactory;
    public static final EventClient Events;
    public static final HabitFactory HabitFactory;
    public static final HabitClient Habits;
    private static final Object INITIALIZATION_LOCK;
    public static final SettingsClient Settings;
    public static final SettingsFactory SettingsFactory;
    private static Context apiAppContext;
    private static ContentResolver apiContentResolver;
    public static ColorFactory colorFactory;
    private static final CalendarApiFactory factory;
    private static boolean initialized;

    public static Context getApiAppContext()
    {
        Object obj = INITIALIZATION_LOCK;
        obj;
        JVM INSTR monitorenter ;
        if (!initialized)
        {
            throw new IllegalStateException(String.valueOf("You have to call initialize(Context) first"));
        }
        break MISSING_BLOCK_LABEL_30;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Context context = apiAppContext;
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        throw new NullPointerException();
        context = (Context)context;
        obj;
        JVM INSTR monitorexit ;
        return context;
    }

    public static ContentResolver getApiContentResolver()
    {
        Object obj = INITIALIZATION_LOCK;
        obj;
        JVM INSTR monitorenter ;
        if (!initialized)
        {
            throw new IllegalStateException(String.valueOf("You have to call initialize(Context) first"));
        }
        break MISSING_BLOCK_LABEL_30;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        ContentResolver contentresolver = apiContentResolver;
        if (contentresolver != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        throw new NullPointerException();
        contentresolver = (ContentResolver)contentresolver;
        obj;
        JVM INSTR monitorexit ;
        return contentresolver;
    }

    public static ColorFactory getColorFactory()
    {
        boolean flag;
        if (colorFactory != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must initialize API first."));
        } else
        {
            return colorFactory;
        }
    }

    public static void initialize(Context context)
    {
        synchronized (INITIALIZATION_LOCK)
        {
            if (!initialized)
            {
                Context context1 = context.getApplicationContext();
                apiAppContext = context1;
                apiContentResolver = context1.getContentResolver();
                colorFactory = factory.getColorFactory(context);
                factory.initializeCaches(context);
                initialized = true;
                Settings.initialize(context);
                Habits.initialize(context);
                Events.initialize(context);
                EventNotifications.initialize(context);
                CalendarList.initialize(context);
            }
        }
        return;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    static 
    {
        CalendarApiFactory calendarapifactory = CalendarApiFactory.instance;
        if (calendarapifactory == null)
        {
            throw new NullPointerException(String.valueOf("CalendarApiFactory not set"));
        } else
        {
            calendarapifactory = (CalendarApiFactory)calendarapifactory;
            factory = calendarapifactory;
            Habits = calendarapifactory.getHabits();
            Events = factory.getEvents();
            EventNotifications = factory.getEventNotifications();
            CalendarList = factory.getCalendarList();
            Settings = factory.getSettings();
            HabitFactory = factory.getHabitFactory();
            EventFactory = factory.getEventFactory();
            CalendarListFactory = factory.getCalendarListFactory();
            SettingsFactory = factory.getSettingsFactory();
            factory.getEventDescriptorFactory();
            EventPermissionsFactory = factory.getEventPermissionsFactory();
            apiContentResolver = null;
            apiAppContext = null;
            initialized = false;
            INITIALIZATION_LOCK = new Object();
        }
    }
}
