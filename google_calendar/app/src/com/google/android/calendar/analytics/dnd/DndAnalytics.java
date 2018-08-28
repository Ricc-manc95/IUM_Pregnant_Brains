// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.analytics.dnd;

import android.content.Context;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;

public final class DndAnalytics
{

    private final String action;
    public final Context context;
    public boolean hasPaged;
    private final String itemType;
    private final String label;
    private final TimeRange originTime;

    public DndAnalytics(Context context1, int i, String s, TimeRange timerange)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   1: 65
    //                   3: 80
    //                   7: 87;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException((new StringBuilder(35)).append("Invalid number of days: ").append(i).toString());
_L2:
        String s1 = "preference_value_hourly_view";
_L6:
        this(context1, s1, s, timerange);
        return;
_L3:
        s1 = "preference_value_3_day_view";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "preferences_value_week_view";
        if (true) goto _L6; else goto _L5
_L5:
    }

    public DndAnalytics(Context context1, String s, String s1, TimeRange timerange)
    {
        byte byte0;
        byte0 = 0;
        super();
        hasPaged = false;
        context = context1;
        action = "dnd_reschedule";
        s.hashCode();
        JVM INSTR lookupswitch 3: default 60
    //                   -430651802: 132
    //                   908217217: 120
    //                   913482619: 147;
           goto _L1 _L2 _L3 _L4
_L1:
        byte0 = -1;
_L9:
        byte0;
        JVM INSTR tableswitch 0 2: default 92
    //                   0 162
    //                   1 182
    //                   2 188;
           goto _L5 _L6 _L7 _L8
_L5:
        context1 = String.valueOf(s);
        if (context1.length() != 0)
        {
            context1 = "Invalid ViewMode for DnD ".concat(context1);
        } else
        {
            context1 = new String("Invalid ViewMode for DnD ");
        }
        throw new IllegalArgumentException(context1);
_L3:
        if (!s.equals("preference_value_hourly_view")) goto _L1; else goto _L9
_L2:
        if (!s.equals("preference_value_3_day_view")) goto _L1; else goto _L10
_L10:
        byte0 = 1;
          goto _L9
_L4:
        if (!s.equals("preferences_value_week_view")) goto _L1; else goto _L11
_L11:
        byte0 = 2;
          goto _L9
_L6:
        context1 = "dnd_reschedule_1day";
_L13:
        label = context1;
        itemType = s1;
        originTime = timerange;
        return;
_L7:
        context1 = "dnd_reschedule_3day";
        continue; /* Loop/switch isn't completed */
_L8:
        context1 = "dnd_reschedule_week";
        if (true) goto _L13; else goto _L12
_L12:
    }

    public static void logDndFailedStartWrongView(Context context1, String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context1, "dnd", "dnd_pickup_failed", s, Long.valueOf(0L));
            return;
        }
    }

    public final void logFailureDroppedUndo()
    {
        Context context1 = context;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context1, "dnd", "dnd_drop_failed", "dnd_drop_undone", Long.valueOf(0L));
            return;
        }
    }

    public final void logSuccess(TimeRange timerange)
    {
        Object obj = context;
        Object obj1 = itemType;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).setCustomDimension(((Context) (obj)), 43, ((String) (obj1)));
        obj = context;
        obj1 = originTime;
        if (timerange.getLocalStartMillis() < ((TimeRange) (obj1)).getLocalStartMillis())
        {
            timerange = "past";
        } else
        if (timerange.getStartDay() == ((TimeRange) (obj1)).getStartDay())
        {
            int i = timerange.getStartMinute() - ((TimeRange) (obj1)).getStartMinute();
            if (i <= 30)
            {
                timerange = "next_30_min";
            } else
            if (i <= 60)
            {
                timerange = "next_60_min";
            } else
            {
                timerange = "same_day";
            }
        } else
        {
            int j = timerange.getStartDay() - ((TimeRange) (obj1)).getStartDay();
            if (j == 1)
            {
                timerange = "next_day";
            } else
            if (j < 7)
            {
                timerange = "same_week";
            } else
            if (j < 14)
            {
                timerange = "next_week";
            } else
            {
                timerange = "after_next_week";
            }
        }
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj1).setCustomDimension(((Context) (obj)), 44, timerange);
        obj = context;
        if (hasPaged)
        {
            timerange = "yes";
        } else
        {
            timerange = "no";
        }
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj1).setCustomDimension(((Context) (obj)), 45, timerange);
        timerange = context;
        obj = action;
        obj1 = label;
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(timerange, "dnd", ((String) (obj)), ((String) (obj1)), Long.valueOf(0L));
            return;
        }
    }
}
