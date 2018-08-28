// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.text.format.Time;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.Duration;
import com.google.calendar.v2.client.service.api.time.Period;
import com.google.calendar.v2.client.service.api.time.TimeZone;
import com.google.calendar.v2.client.service.impl.time.BaseDateTimeImpl;

// Referenced classes of package com.google.android.calendar.time:
//            Time

public final class DateTimeImpl extends BaseDateTimeImpl
{

    public final com.google.android.calendar.time.Time time;
    private final TimeZone timeZone;

    public DateTimeImpl(long l, TimeZone timezone)
    {
        if (timezone == null)
        {
            throw new NullPointerException();
        } else
        {
            time = new com.google.android.calendar.time.Time(timezone.getId());
            com.google.android.calendar.time.Time time1 = time;
            time1.impl.timezone = time1.timezone;
            time1.impl.set(l);
            time1.impl.toMillis(true);
            time1.copyFieldsFromImpl();
            timeZone = timezone;
            return;
        }
    }

    private DateTimeImpl(com.google.android.calendar.time.Time time1, TimeZone timezone)
    {
        if (time1 == null)
        {
            throw new NullPointerException();
        }
        if (timezone == null)
        {
            throw new NullPointerException();
        }
        if (!time1.timezone.equals(timezone.getId()))
        {
            throw new IllegalArgumentException();
        } else
        {
            time = time1;
            timeZone = timezone;
            return;
        }
    }

    private final DateTime offsetPeriod(Period period, int i)
    {
        com.google.android.calendar.time.Time time1 = new com.google.android.calendar.time.Time(time);
        time1.year = time1.year + period.years * i;
        time1.month = time1.month + period.months * i;
        time1.monthDay = time1.monthDay + period.weeks * i * 7;
        time1.monthDay = time1.monthDay + period.days * i;
        time1.hour = time1.hour + period.hours * i;
        time1.minute = time1.minute + period.minutes * i;
        time1.second = (int)((long)time1.second + ((long)i * period.millis) / 1000L);
        time1.writeFieldsToImpl();
        time1.impl.normalize(true);
        time1.copyFieldsFromImpl();
        return new DateTimeImpl(time1, timeZone);
    }

    public final int getDayOfMonth()
    {
        return time.monthDay;
    }

    public final int getHourOfDay()
    {
        return time.hour;
    }

    public final int getJulianDay()
    {
        com.google.android.calendar.time.Time time1 = time;
        time1.writeFieldsToImpl();
        return Time.getJulianDay(time1.impl.toMillis(false), time.gmtoff);
    }

    public final long getMillis()
    {
        com.google.android.calendar.time.Time time1 = time;
        time1.writeFieldsToImpl();
        return time1.impl.toMillis(false);
    }

    public final int getMinuteOfHour()
    {
        return time.minute;
    }

    public final int getMonthOfYear()
    {
        return time.month + 1;
    }

    public final TimeZone getTimeZone()
    {
        return timeZone;
    }

    public final int getYear()
    {
        return time.year;
    }

    public final DateTime minusDuration(Duration duration)
    {
        com.google.android.calendar.time.Time time1 = time;
        time1.writeFieldsToImpl();
        return new DateTimeImpl(time1.impl.toMillis(false) - duration.millis, timeZone);
    }

    public final DateTime minusPeriod(Period period)
    {
        return offsetPeriod(period, -1);
    }

    public final DateTime plusPeriod(Period period)
    {
        return offsetPeriod(period, 1);
    }

    public final String toString()
    {
        return time.toString();
    }

    public final DateTime withDate(int i, int j, int k)
    {
        com.google.android.calendar.time.Time time1 = new com.google.android.calendar.time.Time(time);
        time1.year = i;
        time1.month = j - 1;
        time1.monthDay = k;
        time1.normalizeSafe();
        return new DateTimeImpl(time1, timeZone);
    }

    public final DateTime withMillisOfDay(int i)
    {
        com.google.android.calendar.time.Time time1 = new com.google.android.calendar.time.Time(time);
        time1.hour = 0;
        time1.minute = 0;
        time1.second = 0;
        time1.normalizeSafe();
        return new DateTimeImpl(time1, timeZone);
    }

    public final DateTime withTime(int i, int j, int k)
    {
        com.google.android.calendar.time.Time time1 = new com.google.android.calendar.time.Time(time);
        time1.hour = i;
        time1.minute = 0;
        time1.second = 0;
        time1.normalizeSafe();
        return new DateTimeImpl(time1, timeZone);
    }
}
