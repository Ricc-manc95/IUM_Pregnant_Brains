// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.content.Context;
import android.text.format.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.Period;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.time:
//            TimeUtils, TimeZoneImpl, DateTimeImpl

public final class DateTimeUtils
{

    public static DateTime getNowDateTime(Context context)
    {
        TimeUtils.TimeZoneUtils timezoneutils = TimeUtils.tZUtils;
        if (TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            TimeUtils.TimeZoneUtils.getTimeZone(context, null, false);
        }
        if (TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            context = TimeUtils.TimeZoneUtils.homeTZ;
        } else
        {
            context = Time.getCurrentTimezone();
        }
        return getNowDateTime(((String) (context)));
    }

    public static DateTime getNowDateTime(String s)
    {
        s = new TimeZoneImpl(s);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return new DateTimeImpl(l, s);
    }

    public static boolean isValidEventTime(long l, TimeZone timezone, long l1, TimeZone timezone1, boolean flag)
    {
        boolean flag1;
label0:
        {
label1:
            {
                boolean flag2 = false;
                if (l1 - l >= 0L)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag)
                {
                    break label0;
                }
                if (!flag1)
                {
                    flag = flag2;
                    if (Time.getJulianDay(l, (long)timezone.getOffset(l) / 1000L) != Time.getJulianDay(l1, (long)timezone1.getOffset(l1) / 1000L))
                    {
                        break label1;
                    }
                }
                flag = true;
            }
            return flag;
        }
        return flag1;
    }

    public static DateTime roundUpToMidnight(DateTime datetime)
    {
        if (datetime == null)
        {
            return null;
        } else
        {
            return datetime.plusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L)).withMillisOfDay(0);
        }
    }
}
