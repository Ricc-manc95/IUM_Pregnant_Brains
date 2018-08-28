// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;

class GAnalytics
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/controller/GAnalytics);

    GAnalytics()
    {
    }

    private static String getScreenLabel(int i)
    {
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Invalid screen to log!", new Object[0]);
            return "";

        case 0: // '\0'
            return "main_screen";

        case 1: // '\001'
            return "filter";

        case 2: // '\002'
            return "search_screen";

        case 3: // '\003'
            return "offline_screen";

        case 4: // '\004'
            return "empty_screen";

        case 5: // '\005'
            return "next_page";

        case 6: // '\006'
            return "hierarchy_screen";

        case 7: // '\007'
            return "error_screen";
        }
    }

    static void logApplyFilter(Context context, RoomBookingFilterParams roombookingfilterparams, boolean flag)
    {
        String s;
        String s1;
        AnalyticsLogger analyticslogger;
        if (flag)
        {
            s1 = "apply_initial_filter";
        } else
        {
            s1 = "apply_filter";
        }
        if (roombookingfilterparams.showOnlyAvailable())
        {
            s = "show_available_true";
        } else
        {
            s = "show_available_false";
        }
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(context, "room_booking", s1, s, null);
        s = "show_rooms_for_null";
        if (roombookingfilterparams.getRecurrenceOption() == null)
        {
            s = "show_rooms_for_null";
        } else
        if (roombookingfilterparams.getRecurrenceOption().intValue() == 2)
        {
            s = "show_rooms_for_this";
        } else
        if (roombookingfilterparams.getRecurrenceOption().intValue() == 1)
        {
            s = "show_rooms_for_future";
        }
        roombookingfilterparams = AnalyticsLoggerHolder.instance;
        if (roombookingfilterparams == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)roombookingfilterparams).trackEvent(context, "room_booking", s1, s, null);
            return;
        }
    }

    static void logBack(Context context, int i)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "room_booking", "back", getScreenLabel(i), null);
            return;
        }
    }

    static void logRemovedRoom(Context context)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "room_booking", "removed", "room", null);
            return;
        }
    }

    static void logScreenShown(Context context, int i)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "room_booking", "shown", getScreenLabel(i), null);
            return;
        }
    }

    static void logSelected(Context context, String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "room_booking", "selected", s, null);
            return;
        }
    }

}
