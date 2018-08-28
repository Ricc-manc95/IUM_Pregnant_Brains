// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.AnalyticsUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;

final class MetricsUtils
{

    static String getCalendarType(CalendarListEntry calendarlistentry)
    {
        if (calendarlistentry == null)
        {
            return "unknown";
        }
        if (!"com.google".equals(calendarlistentry.getDescriptor().account.type))
        {
            return "nonGoogle";
        }
        if (calendarlistentry.isPrimary())
        {
            return "primary";
        }
        switch (CalendarType.calculateType(calendarlistentry.getDescriptor().calendarId))
        {
        case 5: // '\005'
        default:
            return "other";

        case 6: // '\006'
            return "individual";

        case 4: // '\004'
            break;
        }
        if (CalendarAccessLevel.OWNER.equals(calendarlistentry.getAccessLevel()))
        {
            return "secondary";
        } else
        {
            return "group";
        }
    }

    static void logSaveCustomDimensions(Context context, String s, String s1, int i, boolean flag, boolean flag1, int j, String s2, 
            String s3, String s4, String s5, String s6)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger = (AnalyticsLogger)analyticslogger;
        if (s != null)
        {
            analyticslogger.setCustomDimension(context, 23, s);
        }
        if (s1 != null)
        {
            analyticslogger.setCustomDimension(context, 34, s1);
        }
        s = AnalyticsUtils.convertNumToDimensionValue(i, 50);
        if (s != null)
        {
            analyticslogger.setCustomDimension(context, 24, s);
        }
        s = String.valueOf(flag);
        if (s != null)
        {
            analyticslogger.setCustomDimension(context, 36, s);
        }
        s = String.valueOf(flag1);
        if (s != null)
        {
            analyticslogger.setCustomDimension(context, 37, s);
        }
        s = AnalyticsUtils.convertNumToDimensionValue(j, 50);
        if (s != null)
        {
            analyticslogger.setCustomDimension(context, 38, s);
        }
        if (s2 != null)
        {
            analyticslogger.setCustomDimension(context, 14, s2);
        }
        if (s3 != null)
        {
            analyticslogger.setCustomDimension(context, 15, s3);
        }
        if (s4 != null)
        {
            analyticslogger.setCustomDimension(context, 16, s4);
        }
        if (s5 != null)
        {
            analyticslogger.setCustomDimension(context, 22, s5);
        }
        if (s6 != null)
        {
            analyticslogger.setCustomDimension(context, 25, s6);
        }
    }
}
