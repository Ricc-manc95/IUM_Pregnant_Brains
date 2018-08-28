// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            TimeRange

class $AutoValue_TimeRange extends TimeRange
{

    private final boolean allDay;
    private final int endDay;
    private final int endMinute;
    private final int startDay;
    private final int startMinute;
    private final TimeZone timeZone;
    private final long utcEndMillis;
    private final long utcStartMillis;

    $AutoValue_TimeRange(TimeZone timezone, boolean flag, long l, long l1, int i, 
            int j, int k, int i1)
    {
        if (timezone == null)
        {
            throw new NullPointerException("Null timeZone");
        } else
        {
            timeZone = timezone;
            allDay = flag;
            utcStartMillis = l;
            utcEndMillis = l1;
            startDay = i;
            endDay = j;
            startMinute = k;
            endMinute = i1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TimeRange)
            {
                if (!timeZone.equals(((TimeRange) (obj = (TimeRange)obj)).getTimeZone()) || allDay != ((TimeRange) (obj)).isAllDay() || utcStartMillis != ((TimeRange) (obj)).getUtcStartMillis() || utcEndMillis != ((TimeRange) (obj)).getUtcEndMillis() || startDay != ((TimeRange) (obj)).getStartDay() || endDay != ((TimeRange) (obj)).getEndDay() || startMinute != ((TimeRange) (obj)).getStartMinute() || endMinute != ((TimeRange) (obj)).getEndMinute())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int getEndDay()
    {
        return endDay;
    }

    public final int getEndMinute()
    {
        return endMinute;
    }

    public final int getStartDay()
    {
        return startDay;
    }

    public final int getStartMinute()
    {
        return startMinute;
    }

    public final TimeZone getTimeZone()
    {
        return timeZone;
    }

    public final long getUtcEndMillis()
    {
        return utcEndMillis;
    }

    public final long getUtcStartMillis()
    {
        return utcStartMillis;
    }

    public int hashCode()
    {
        int i = timeZone.hashCode();
        char c;
        if (allDay)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return ((((((c ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ (int)(utcStartMillis >>> 32 ^ utcStartMillis)) * 0xf4243 ^ (int)(utcEndMillis >>> 32 ^ utcEndMillis)) * 0xf4243 ^ startDay) * 0xf4243 ^ endDay) * 0xf4243 ^ startMinute) * 0xf4243 ^ endMinute;
    }

    public final boolean isAllDay()
    {
        return allDay;
    }

    public String toString()
    {
        String s = String.valueOf(timeZone);
        boolean flag = allDay;
        long l1 = utcStartMillis;
        long l2 = utcEndMillis;
        int i = startDay;
        int j = endDay;
        int k = startMinute;
        int l = endMinute;
        return (new StringBuilder(String.valueOf(s).length() + 196)).append("TimeRange{timeZone=").append(s).append(", allDay=").append(flag).append(", utcStartMillis=").append(l1).append(", utcEndMillis=").append(l2).append(", startDay=").append(i).append(", endDay=").append(j).append(", startMinute=").append(k).append(", endMinute=").append(l).append("}").toString();
    }
}
