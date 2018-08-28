// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Time;

// Referenced classes of package net.fortuna.ical4j.util:
//            TimeZones

public final class Dates
{

    public static int getAbsMonthDay(Date date, int i)
    {
        if (i == 0 || i < -31 || i > 31)
        {
            throw new IllegalArgumentException(MessageFormat.format("Invalid month day [{0}]", new Object[] {
                new Integer(i)
            }));
        }
        if (i > 0)
        {
            return i;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int j = calendar.get(2);
        date = new ArrayList();
        calendar.set(5, 1);
        for (; calendar.get(2) == j; calendar.add(5, 1))
        {
            date.add(new Integer(calendar.get(5)));
        }

        return ((Integer)date.get(date.size() + i)).intValue();
    }

    public static Calendar getCalendarInstance(net.fortuna.ical4j.model.Date date)
    {
        if (date instanceof DateTime)
        {
            date = (DateTime)date;
            if (((DateTime) (date)).timezone != null)
            {
                return Calendar.getInstance(((DateTime) (date)).timezone);
            }
            if (((DateTime) (date)).time.utc)
            {
                return Calendar.getInstance(TimeZones.UTC_TIMEZONE);
            } else
            {
                return Calendar.getInstance();
            }
        } else
        {
            return Calendar.getInstance(TimeZones.getDateTimeZone());
        }
    }

    public static long round(long l, int i, TimeZone timezone)
    {
        if (i == 0 && l % 1000L == 0L)
        {
            return l;
        }
        timezone = Calendar.getInstance(timezone);
        timezone.setTimeInMillis(l);
        if (i != 1) goto _L2; else goto _L1
_L1:
        timezone.set(11, 0);
        timezone.clear(12);
        timezone.clear(13);
        timezone.clear(14);
_L4:
        return timezone.getTimeInMillis();
_L2:
        if (i == 0)
        {
            timezone.clear(14);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
