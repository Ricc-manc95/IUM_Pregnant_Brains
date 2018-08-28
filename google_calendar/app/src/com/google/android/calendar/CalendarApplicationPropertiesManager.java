// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.Time;
import android.view.accessibility.AccessibilityManager;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.time.TimeUtils;
import java.util.List;
import java.util.TimeZone;

final class CalendarApplicationPropertiesManager
{

    private final Application application;
    public final ObservableReference defaultCalendarColor = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Integer.valueOf(0));
    public final ObservableReference defaultDuration = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Long.valueOf(0L));
    public final ObservableReference firstDayOfWeek = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Integer.valueOf(0));
    public final ObservableReference hideDeclinedEvents = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    public final ObservableReference isA11yEnabled = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    public final ObservableReference isTalkBackEnabled = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    private final android.content.SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new .Lambda._cls1();
    public final ObservableReference shouldShowMonthIllustrations = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    public final ObservableReference shouldShowWeekNumbers = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    public final ObservableReference timeZone;

    CalendarApplicationPropertiesManager(Application application1)
    {
        application = application1;
        Object obj = TimeUtils.tZUtils;
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(application1, null, false);
        }
        class .Lambda._cls0
            implements Runnable
        {

            private final CalendarApplicationPropertiesManager arg$1;

            public final void run()
            {
                arg$1.updateTimeZone();
            }

            .Lambda._cls0()
            {
                arg$1 = CalendarApplicationPropertiesManager.this;
            }
        }

        class .Lambda._cls2
            implements android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        {

            private final CalendarApplicationPropertiesManager arg$1;

            public final void onAccessibilityStateChanged(boolean flag)
            {
                Object obj1 = arg$1;
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                class .Lambda._cls5
                    implements Runnable
                {

                    private final CalendarApplicationPropertiesManager arg$1;

                    public final void run()
                    {
                        arg$1.updateA11yProperties();
                    }

                        .Lambda._cls5()
                        {
                            arg$1 = CalendarApplicationPropertiesManager.this;
                        }
                }

                obj1 = ((.Lambda._cls5) (obj1)). new .Lambda._cls5();
                TimeUnit timeunit = TimeUnit.SECONDS;
                calendarexecutor.getDelegate().schedule(((Runnable) (obj1)), 1L, timeunit);
            }

            .Lambda._cls2()
            {
                arg$1 = CalendarApplicationPropertiesManager.this;
            }
        }

        class .Lambda._cls1
            implements android.content.SharedPreferences.OnSharedPreferenceChangeListener
        {

            private final CalendarApplicationPropertiesManager arg$1;

            public final void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
            {
                arg$1.updateSharedPreferencesValues();
            }

            .Lambda._cls1()
            {
                arg$1 = CalendarApplicationPropertiesManager.this;
            }
        }

        AccessibilityManager accessibilitymanager;
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            obj = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
        } else
        {
            obj = Time.getCurrentTimezone();
        }
        timeZone = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(TimeZone.getTimeZone(((String) (obj))));
        TimeUtils.subscribeToTimeChanges(application1, new .Lambda._cls0());
        obj = new .Lambda._cls2();
        accessibilitymanager = (AccessibilityManager)application.getSystemService("accessibility");
        if (accessibilitymanager != null)
        {
            accessibilitymanager.addAccessibilityStateChangeListener(((android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener) (obj)));
        }
        updateA11yProperties();
        application1.getSharedPreferences("com.google.android.calendar_preferences", 0).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        updateSharedPreferencesValues();
    }

    final void updateA11yProperties()
    {
        boolean flag1 = true;
        Object obj = (AccessibilityManager)application.getSystemService("accessibility");
        ObservableReference observablereference = isA11yEnabled;
        Boolean boolean1;
        boolean flag;
        if (obj != null && ((AccessibilityManager) (obj)).isEnabled())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        boolean1 = Boolean.valueOf(flag);
        if (!observablereference.get().equals(boolean1))
        {
            observablereference.set(boolean1);
        }
        observablereference = isTalkBackEnabled;
        if (((Boolean)isA11yEnabled.get()).booleanValue() && !((AccessibilityManager) (obj)).getEnabledAccessibilityServiceList(1).isEmpty())
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        obj = Boolean.valueOf(flag);
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
    }

    final void updateSharedPreferencesValues()
    {
        ObservableReference observablereference = firstDayOfWeek;
        Object obj = Integer.valueOf(PreferenceUtils.getFirstDayOfWeekAsCalendar(application));
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
        observablereference = shouldShowWeekNumbers;
        obj = Boolean.valueOf(application.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_show_week_num", false));
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
        observablereference = shouldShowMonthIllustrations;
        boolean flag;
        if (!application.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = Boolean.valueOf(flag);
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
        observablereference = hideDeclinedEvents;
        obj = Boolean.valueOf(application.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_hide_declined", false));
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
        updateTimeZone();
    }

    final void updateTimeZone()
    {
        Object obj = application;
        Object obj1 = TimeUtils.tZUtils;
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(((Context) (obj)), null, false);
        }
        String s;
        boolean flag;
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            obj = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
        } else
        {
            obj = Time.getCurrentTimezone();
        }
        obj = TimeZone.getTimeZone(((String) (obj)));
        obj1 = ((TimeZone)timeZone.get()).getID();
        s = ((TimeZone) (obj)).getID();
        if (obj1 == s || obj1 != null && obj1.equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            timeZone.set(obj);
        }
    }
}
