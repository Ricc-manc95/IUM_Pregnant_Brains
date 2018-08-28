// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class TimeUtils
{

    public static final int DAY_MS;
    public static final long DAY_MS_FP16;
    public static final long HOUR_FP16 = ((long)1 << 16 << 16) / ((long)24 << 16);
    private final Calendar calendar = Calendar.getInstance();
    public final ObservableReference firstDayOfWeek;
    public final ObservableReference timeZone;

    public TimeUtils(ObservableReference observablereference, ObservableReference observablereference1)
    {
        timeZone = observablereference;
        firstDayOfWeek = observablereference1;
    }

    public static int fp16ToDisplayMsOfDay(long l, boolean flag)
    {
        l -= 0xffffffffffff0000L & l;
        if (l == 0L)
        {
            if (flag)
            {
                return DAY_MS;
            } else
            {
                return 0;
            }
        } else
        {
            return (int)((l * DAY_MS_FP16 >> 16) + 32768L >> 16);
        }
    }

    public static int getJulianDay(long l, long l1)
    {
        return (int)((1000L * l1 + l) / (long)DAY_MS) + 0x253d8c;
    }

    public static long utcJulianDayToMs(int i)
    {
        return TimeUnit.DAYS.toMillis(i - 0x253d8c);
    }

    public final long fp16ToMs(long l)
    {
        int i = (int)(l >> 16);
        long l1 = TimeUnit.DAYS.toMillis(i - 0x253d8c);
        long l2 = ((TimeZone)timeZone.get()).getOffset(l1);
        l -= 0xffffffffffff0000L & l;
        i = (int)((((long)24 << 16) * l >> 16) + 32768L >> 16);
        int j = (int)(((l - ((long)i << 16 << 16) / ((long)24 << 16)) * ((long)1440 << 16) >> 16) + 32768L >> 16);
        initCalendar(calendar);
        calendar.setTimeInMillis(l1 - l2);
        calendar.set(10, i);
        calendar.set(12, j);
        return calendar.getTimeInMillis();
    }

    public final int getCalendarFieldForJulianDay(int i, int j)
    {
        initCalendar(calendar);
        calendar.setTimeInMillis(julianDateToMs(i) + TimeUnit.HOURS.toMillis(12L));
        return calendar.get(j);
    }

    public final void initCalendar(Calendar calendar1)
    {
        calendar1.clear();
        calendar1.setTimeZone((TimeZone)timeZone.get());
        calendar1.setFirstDayOfWeek(((Integer)firstDayOfWeek.get()).intValue());
    }

    public final int[] julianDateToDateInfo(int i)
    {
        initCalendar(calendar);
        calendar.setTimeInMillis(julianDateToMs(i));
        return (new int[] {
            calendar.get(1), calendar.get(2), calendar.get(5)
        });
    }

    public final long julianDateToMs(int i)
    {
        long l = TimeUnit.DAYS.toMillis(i - 0x253d8c);
        return l - (long)((TimeZone)timeZone.get()).getOffset(l);
    }

    public final long msToFp16(long l)
    {
        long l1 = msToJulianDate(l);
        initCalendar(calendar);
        calendar.setTimeInMillis(l);
        return (l1 << 16) + ((long)(int)(TimeUnit.HOURS.toMillis(calendar.get(11)) + TimeUnit.MINUTES.toMillis(calendar.get(12))) << 16 << 16) / DAY_MS_FP16;
    }

    public final int msToJulianDate(long l)
    {
        return (int)((TimeUnit.MILLISECONDS.toSeconds(((TimeZone)timeZone.get()).getOffset(l)) * 1000L + l) / (long)DAY_MS) + 0x253d8c;
    }

    static 
    {
        int i = (int)TimeUnit.DAYS.toMillis(1L);
        DAY_MS = i;
        DAY_MS_FP16 = (long)i << 16;
        TimeUnit.HOURS.toMillis(1L);
    }
}
