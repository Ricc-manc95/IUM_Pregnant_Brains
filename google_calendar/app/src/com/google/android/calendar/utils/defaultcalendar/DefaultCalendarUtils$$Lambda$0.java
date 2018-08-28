// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.defaultcalendar;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Present;

public final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        boolean flag1 = false;
        obj = (CalendarListEntry[])obj;
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = CalendarProperties.getDefaultCalendarId();
        int i1 = obj.length;
        for (int i = 0; i < i1; i++)
        {
            CalendarListEntry calendarlistentry2 = obj[i];
            com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor1 = calendarlistentry2.getDescriptor();
            boolean flag;
            if (calendardescriptor == calendardescriptor1 || calendardescriptor != null && calendardescriptor.equals(calendardescriptor1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (calendarlistentry2 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(calendarlistentry2);
                }
            }
        }

        int l = obj.length;
        for (int j = 0; j < l; j++)
        {
            CalendarListEntry calendarlistentry = obj[j];
            if (calendarlistentry.isPrimary())
            {
                if (calendarlistentry == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(calendarlistentry);
                }
            }
        }

        l = obj.length;
        for (int k = ((flag1) ? 1 : 0); k < l; k++)
        {
            CalendarListEntry calendarlistentry1 = obj[k];
            if (calendarlistentry1.getAccessLevel().isGreaterOrEqual(CalendarAccessLevel.WRITER))
            {
                if (calendarlistentry1 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(calendarlistentry1);
                }
            }
        }

        return Absent.INSTANCE;
    }


    private ()
    {
    }
}
