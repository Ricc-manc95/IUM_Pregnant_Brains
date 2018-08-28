// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

public final class ApiUtils
{

    public static void setDefaultCalendar(CalendarListEntry calendarlistentry)
    {
        if (calendarlistentry.getDescriptor().calendarKey != null)
        {
            calendarlistentry = calendarlistentry.getDescriptor();
            CalendarProperties calendarproperties = CalendarProperties.instance;
            if (calendarproperties == null)
            {
                throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
            }
            ((CalendarProperties)calendarproperties).setDefaultCalendarIdValue(calendarlistentry, true);
        }
    }
}
