// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.zzaj;
import com.google.calendar.v2.client.service.api.time.DateTime;
import java.util.TimeZone;

public final class ArpTaskDateTimeInCalendar
{

    public final boolean allDay;
    public final Long originalStartMillis;
    public final long startMillis;
    public final boolean unscheduled;

    public ArpTaskDateTimeInCalendar(Task task, DateTimeService datetimeservice)
    {
        Time time;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, datetimeservice.calendarTimeZone)).withTime(0, 0, 0)).time;
        time.writeFieldsToImpl();
        this(task, datetimeservice, time.impl.toMillis(false));
    }

    public ArpTaskDateTimeInCalendar(Task task, DateTimeService datetimeservice, long l)
    {
        Long long1;
        if (task.getArchived().booleanValue() || task.getDueDate() == null)
        {
            long1 = null;
        } else
        {
            long1 = Long.valueOf(getDueTimeMillis(task.getDueDate(), datetimeservice));
        }
        originalStartMillis = long1;
        if (task.getArchived().booleanValue())
        {
            allDay = true;
            if (task.getArchivedTimeMs() != null)
            {
                task = task.getArchivedTimeMs();
            } else
            {
                task = task.getCreatedTimeMillis();
            }
            startMillis = datetimeservice.convertLocalToAllDayLocal(task.longValue());
            unscheduled = false;
        } else
        if (task.getDueDate() != null && originalStartMillis.longValue() >= l)
        {
            allDay = isDueAllDay(task.getDueDate());
            startMillis = originalStartMillis.longValue();
            boolean flag;
            if (allDay && startMillis == l)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            unscheduled = flag;
        } else
        {
            allDay = true;
            startMillis = l;
            unscheduled = true;
        }
        l = startMillis;
        datetimeservice.recycle.timezone = datetimeservice.calendarTimeZone.timeZone.getID();
        task = datetimeservice.recycle;
        ((Time) (task)).impl.timezone = ((Time) (task)).timezone;
        ((Time) (task)).impl.set(l);
        ((Time) (task)).impl.toMillis(true);
        task.copyFieldsFromImpl();
        task = datetimeservice.recycle;
        android.text.format.Time.getJulianDay(startMillis, ((Time) (task)).gmtoff);
    }

    public static com.google.android.gms.reminders.model.DateTime fromCalendarDateTime(DateTime datetime, boolean flag, boolean flag1)
    {
        com.google.android.gms.reminders.model.DateTime.Builder builder = new com.google.android.gms.reminders.model.DateTime.Builder();
        builder.zzchT = Boolean.valueOf(flag);
        builder.zzchV = Integer.valueOf(datetime.getYear());
        builder.zzchW = Integer.valueOf(datetime.getMonthOfYear());
        builder.zzchX = Integer.valueOf(datetime.getDayOfMonth());
        builder.zzcic = Boolean.valueOf(false);
        DateTime datetime1 = datetime;
        if (flag)
        {
            datetime1 = datetime.withTime(getHourFromPeriod(1), 0, 0);
        }
        if (flag1)
        {
            builder.zzcib = Long.valueOf(datetime1.getMillis());
        }
        datetime = new com.google.android.gms.reminders.model.Time.Builder();
        datetime.zzcjG = Integer.valueOf(datetime1.getHourOfDay());
        datetime.zzcjH = Integer.valueOf(datetime1.getMinuteOfHour());
        datetime.zzcjI = Integer.valueOf(0);
        datetime = new zzaj(((com.google.android.gms.reminders.model.Time.Builder) (datetime)).zzcjG, ((com.google.android.gms.reminders.model.Time.Builder) (datetime)).zzcjH, ((com.google.android.gms.reminders.model.Time.Builder) (datetime)).zzcjI);
        if (datetime != null)
        {
            datetime = (com.google.android.gms.reminders.model.Time)datetime.freeze();
        } else
        {
            datetime = null;
        }
        builder.zzchY = datetime;
        return builder.build();
    }

    public static long getDueTimeMillis(com.google.android.gms.reminders.model.DateTime datetime, DateTimeService datetimeservice)
    {
        int j = 0;
        boolean flag = isDueAllDay(datetime);
        Long long1 = datetime.getAbsoluteTimeMs();
        if (long1 != null)
        {
            if (flag)
            {
                return datetimeservice.convertLocalToAllDayLocal(long1.longValue());
            } else
            {
                return long1.longValue();
            }
        }
        int i;
        if (!flag)
        {
            if (datetime.getTime() != null)
            {
                com.google.android.gms.reminders.model.Time time = datetime.getTime();
                i = time.getHour().intValue();
                j = time.getMinute().intValue();
            } else
            {
                i = getHourFromPeriod(datetime.getPeriod().intValue());
            }
        } else
        {
            i = 0;
        }
        return datetimeservice.getMillis(datetime.getYear().intValue(), datetime.getMonth().intValue() - 1, datetime.getDay().intValue(), i, j);
    }

    private static int getHourFromPeriod(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 1: // '\001'
            return 8;

        case 2: // '\002'
            return 13;

        case 3: // '\003'
            return 18;

        case 4: // '\004'
            return 20;
        }
    }

    public static boolean isDueAllDay(com.google.android.gms.reminders.model.DateTime datetime)
    {
        return datetime.getAllDay().booleanValue() || datetime.getTime() == null && datetime.getPeriod() == null;
    }
}
