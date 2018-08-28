// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.text.format.Time;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class TimeBoxUtil
{

    private static final ThreadLocal CALENDAR_LOCAL = new _cls1();

    static boolean isMidnight(TimeZone timezone, long l)
    {
        long l1 = (long)(Time.getJulianDay(l, timezone.getOffset(l) / 1000) - 0x253d8c) * 0x5265c00L;
        return l1 - (long)timezone.getOffset(l1) == l;
    }

    public static long julianDayToMs(TimeZone timezone, int i)
    {
        long l = (long)(i - 0x253d8c) * 0x5265c00L;
        return l - (long)timezone.getOffset(l);
    }

    public static int msToJulianDay(TimeZone timezone, long l)
    {
        return Time.getJulianDay(l, timezone.getOffset(l) / 1000);
    }

    static int msToMinuteOfDay(TimeZone timezone, long l)
    {
        Calendar calendar = (Calendar)CALENDAR_LOCAL.get();
        calendar.setTimeZone(timezone);
        calendar.setTimeInMillis(l);
        l = TimeUnit.MINUTES.convert(calendar.get(11), TimeUnit.HOURS);
        return (int)((long)calendar.get(12) + l);
    }

    public static int msToUtcJulianDay(long l)
    {
        return Time.getJulianDay(l, 0L);
    }

    public static long utcJulianDayToMs(int i)
    {
        return (long)(i - 0x253d8c) * 0x5265c00L;
    }


    private class _cls1 extends ThreadLocal
    {

        protected final Object initialValue()
        {
            return Calendar.getInstance();
        }

        _cls1()
        {
        }
    }

}
