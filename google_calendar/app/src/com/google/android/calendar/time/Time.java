// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import com.android.calendarcommon2.LogUtils;

public final class Time
{

    public boolean allDay;
    public long gmtoff;
    public int hour;
    public final android.text.format.Time impl;
    public int isDst;
    public int minute;
    public int month;
    public int monthDay;
    public int second;
    public String timezone;
    private android.text.format.Time utcTemp;
    public int weekDay;
    public int year;
    public int yearDay;

    public Time()
    {
        this(new android.text.format.Time(android.text.format.Time.getCurrentTimezone()));
    }

    private Time(android.text.format.Time time)
    {
        isDst = -1;
        impl = time;
        copyFieldsFromImpl();
    }

    public Time(Time time)
    {
        this(new android.text.format.Time(time.impl));
    }

    public Time(String s)
    {
        this(new android.text.format.Time(s));
    }

    public static long calculateDayHourMinuteMillis(int i, int j, int k, String s, Time time)
    {
        Time time1 = time;
        if (time == null)
        {
            time1 = new Time(s);
        }
        time1.timezone = s;
        time1.setJulianDaySafe(i);
        time1.hour = j;
        time1.minute = k;
        time1.second = 0;
        return time1.toMillisWithFallback();
    }

    private final void copyTimeFieldsFrom(android.text.format.Time time)
    {
        year = time.year;
        month = time.month;
        monthDay = time.monthDay;
        hour = time.hour;
        minute = time.minute;
        second = time.second;
    }

    private static void fixAllDay(android.text.format.Time time)
    {
        if (time.allDay && (time.hour != 0 || time.minute != 0 || time.second != 0))
        {
            LogUtils.d("CalTime", "Bad allDay for %04d-%02d-%02d %02d:%02d:%02d", new Object[] {
                Integer.valueOf(time.year), Integer.valueOf(time.month), Integer.valueOf(time.monthDay), Integer.valueOf(time.hour), Integer.valueOf(time.minute), Integer.valueOf(time.second)
            });
            time.allDay = false;
        }
    }

    private final void writeTimeFieldsTo(android.text.format.Time time)
    {
        time.year = year;
        time.month = month;
        time.monthDay = monthDay;
        time.hour = hour;
        time.minute = minute;
        time.second = second;
    }

    public final void copyFieldsFromImpl()
    {
        copyTimeFieldsFrom(impl);
        allDay = impl.allDay;
        timezone = impl.timezone;
        yearDay = impl.yearDay;
        weekDay = impl.weekDay;
        gmtoff = impl.gmtoff;
        isDst = impl.isDst;
    }

    public final void normalizeSafe()
    {
        writeFieldsToImpl();
        long l = impl.normalize(true);
        fixAllDay(impl);
        if (l == -1L)
        {
            if (utcTemp == null)
            {
                utcTemp = new android.text.format.Time("UTC");
            }
            android.text.format.Time time = utcTemp;
            writeTimeFieldsTo(time);
            time.allDay = allDay;
            time.normalize(true);
            copyTimeFieldsFrom(time);
            writeFieldsToImpl();
            android.text.format.Time time1 = impl;
            time1.hour = 12;
            time1.minute = 0;
            time1.second = 0;
            time1.allDay = false;
            impl.normalize(true);
            copyFieldsFromImpl();
            copyTimeFieldsFrom(time);
            allDay = time.allDay;
            writeFieldsToImpl();
            fixAllDay(impl);
            allDay = impl.allDay;
            return;
        } else
        {
            copyFieldsFromImpl();
            return;
        }
    }

    public final void set(long l)
    {
        impl.timezone = timezone;
        impl.set(l);
        impl.toMillis(true);
        copyFieldsFromImpl();
    }

    public final void set(Time time)
    {
        timezone = time.timezone;
        impl.timezone = timezone;
        impl.set(time.impl);
        copyFieldsFromImpl();
    }

    public final void setJulianDaySafe(int i)
    {
        writeFieldsToImpl();
        if (Math.abs(android.text.format.Time.getJulianDay(impl.setJulianDay(i), gmtoff) - i) > 1)
        {
            impl.setJulianDay(i + 1);
            impl.set((impl.toMillis(false) - 0x5265c00L) + 0x36ee80L);
            impl.normalize(false);
        }
        copyFieldsFromImpl();
    }

    public final long toMillisWithFallback()
    {
        writeFieldsToImpl();
        long l = impl.toMillis(true);
        if (l != -1L)
        {
            return l;
        }
        if (utcTemp == null)
        {
            utcTemp = new android.text.format.Time("UTC");
        }
        android.text.format.Time time = utcTemp;
        writeTimeFieldsTo(time);
        l = time.toMillis(true);
        time.hour = 12;
        time.minute = 0;
        time.second = 0;
        time.allDay = false;
        long l1 = time.toMillis(true);
        writeFieldsToImpl();
        time = impl;
        time.hour = 12;
        time.minute = 0;
        time.second = 0;
        time.allDay = false;
        long l2 = impl.normalize(true);
        boolean flag;
        if (l2 != -1L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            writeFieldsToImpl();
            return l2 + (l - l1);
        }
    }

    public final String toString()
    {
        return String.format("Time:%04d-%02d-%02d %02d:%02d:%02d ad:%s dst:%s tz:%s", new Object[] {
            Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(monthDay), Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second), Boolean.valueOf(allDay), Integer.valueOf(isDst), timezone
        });
    }

    public final void writeFieldsToImpl()
    {
        writeTimeFieldsTo(impl);
        impl.allDay = allDay;
        impl.timezone = timezone;
        impl.yearDay = yearDay;
        impl.weekDay = weekDay;
        impl.gmtoff = gmtoff;
        impl.isDst = isDst;
        fixAllDay(impl);
    }
}
