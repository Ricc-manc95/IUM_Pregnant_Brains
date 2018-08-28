// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.android.calendar.widget.CalendarAppWidgetProvider;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;

public class AnalyticsUtils
{

    private static final Executor POST_EXECUTOR;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/AnalyticsUtils);
    private static final ImmutableMap VIEW_TO_ANALYTICS_VIEW_MODE_MAP = (new com.google.common.collect.ImmutableMap.Builder()).put(Integer.valueOf(0x7f100004), "schedule").put(Integer.valueOf(0x7f100022), "day").put(Integer.valueOf(0x7f100026), "nDay").put(Integer.valueOf(0x7f100050), "week").put(Integer.valueOf(0x7f100027), "month").build();
    public static boolean haveLoggedAllDataReady = false;
    private static boolean haveLoggedInfrequentAnalytics = false;

    public AnalyticsUtils()
    {
    }

    public static String convertNumToDimensionValue(int i, int j)
    {
        if (i <= j)
        {
            return Integer.toString(i);
        } else
        {
            return String.valueOf(Integer.toString(j + 1)).concat("+");
        }
    }

    static final void lambda$postAppOpenAnalytics$0$AnalyticsUtils(Context context, Intent intent, boolean flag, CalendarListEntry acalendarlistentry[])
    {
        if (acalendarlistentry == null)
        {
            throw new NullPointerException();
        }
        int i = acalendarlistentry.length;
        CollectPreconditions.checkNonnegative(i, "arraySize");
        long l1 = i;
        l1 = (long)(i / 10) + (5L + l1);
        ArrayList arraylist;
        Resources resources;
        if (l1 > 0x7fffffffL)
        {
            i = 0x7fffffff;
        } else
        if (l1 < 0xffffffff80000000L)
        {
            i = 0x80000000;
        } else
        {
            i = (int)l1;
        }
        arraylist = new ArrayList(i);
        Collections.addAll(arraylist, acalendarlistentry);
        resources = context.getResources();
        if (intent != null)
        {
            intent = intent.getExtras();
        } else
        {
            intent = null;
        }
        if (intent != null)
        {
            intent = intent.getString("intent_source", "unknown");
        } else
        {
            intent = "unknown";
        }
        if (!haveLoggedInfrequentAnalytics)
        {
            haveLoggedInfrequentAnalytics = true;
            acalendarlistentry = AnalyticsLoggerHolder.instance;
            if (acalendarlistentry == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            acalendarlistentry = (AnalyticsLogger)acalendarlistentry;
            Configuration configuration = context.getResources().getConfiguration();
            if (configuration.smallestScreenWidthDp != 0)
            {
                acalendarlistentry.trackTiming(context, "device_info", configuration.smallestScreenWidthDp * 1000, "swdp", null);
            }
            acalendarlistentry.trackTiming(context, "device_info", configuration.densityDpi * 1000, "dpi", null);
        }
        SharedPreferences sharedpreferences = context.getSharedPreferences("analytics_utils_prefs.xml", 0);
        acalendarlistentry = String.valueOf("app_open");
        boolean flag1;
        if (acalendarlistentry.length() != 0)
        {
            acalendarlistentry = "throttle_time_".concat(acalendarlistentry);
        } else
        {
            acalendarlistentry = new String("throttle_time_");
        }
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        if (sharedpreferences.getLong(acalendarlistentry, -1L) + 0x5265c00L < l1)
        {
            sharedpreferences.edit().putLong(acalendarlistentry, l1).apply();
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            acalendarlistentry = AnalyticsLoggerHolder.instance;
            if (acalendarlistentry == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            AnalyticsLogger analyticslogger = (AnalyticsLogger)acalendarlistentry;
            int l = resources.getDimensionPixelSize(0x7f0e01d5);
            int j = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_p", l);
            l = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_l", l);
            float f = resources.getDisplayMetrics().density;
            j = Math.round((float)j / f);
            l = Math.round((float)l / f);
            analyticslogger.trackTiming(context, "preferences", j * 1000, "cell_height_portrait", null);
            analyticslogger.trackTiming(context, "preferences", l * 1000, "cell_height_landscape", null);
            j = PreferencesUtils.getLastUsedView(context, flag);
            acalendarlistentry = (String)VIEW_TO_ANALYTICS_VIEW_MODE_MAP.get(Integer.valueOf(j));
            if (acalendarlistentry != null)
            {
                analyticslogger.setCustomDimension(context, 1, acalendarlistentry);
            }
            if (!flag && j == 0x7f100027)
            {
                int k;
                if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_key_grid_mode", false))
                {
                    acalendarlistentry = "day";
                } else
                {
                    acalendarlistentry = "schedule";
                }
                analyticslogger.setCustomDimension(context, 26, acalendarlistentry);
            }
            if (context.getResources().getConfiguration().orientation == 1)
            {
                acalendarlistentry = "portrait";
            } else
            {
                acalendarlistentry = "landscape";
            }
            analyticslogger.setCustomDimension(context, 2, acalendarlistentry);
            k = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, com/android/calendar/widget/CalendarAppWidgetProvider)).length;
            if (k <= 10)
            {
                acalendarlistentry = Integer.toString(k);
            } else
            {
                acalendarlistentry = String.valueOf(Integer.toString(11)).concat("+");
            }
            analyticslogger.setCustomDimension(context, 4, acalendarlistentry);
            if (flag)
            {
                acalendarlistentry = "tablet";
            } else
            {
                acalendarlistentry = "phone";
            }
            analyticslogger.setCustomDimension(context, 10, acalendarlistentry);
            if (flag)
            {
                if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                analyticslogger.setCustomDimension(context, 5, Boolean.toString(flag));
            }
            analyticslogger.setAdditionalDimensionsForApplicationOpenEvent(context, arraylist);
            analyticslogger.trackEvent(context, "app_open_daily", intent);
            analyticslogger.sendAdditionalEventsOnApplicationOpen(context, arraylist);
        }
    }

    static void postAppOpenAnalytics(Context context, Intent intent, boolean flag)
    {
        Object obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = ((ListenableFutureCache)obj).getValueAsync();
        class .Lambda._cls0
            implements Consumer
        {

            private final Context arg$1;
            private final Intent arg$2;
            private final boolean arg$3;

            public final void accept(Object obj1)
            {
                AnalyticsUtils.lambda$postAppOpenAnalytics$0$AnalyticsUtils(arg$1, arg$2, arg$3, (CalendarListEntry[])obj1);
            }

            .Lambda._cls0(Context context, Intent intent, boolean flag)
            {
                arg$1 = context;
                arg$2 = intent;
                arg$3 = flag;
            }
        }

        context = LogUtils.newFailureLoggingCallback(new .Lambda._cls0(context, intent, flag), TAG, "Unable to load calendars", new Object[0]);
        intent = POST_EXECUTOR;
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), context), intent);
            return;
        }
    }

    static 
    {
        POST_EXECUTOR = CalendarExecutors.serialExecutor(CalendarExecutor.NET);
    }
}
