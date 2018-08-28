// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package net.fortuna.ical4j.model:
//            CalendarDateFormatFactory

static abstract class pattern extends DateFormat
{

    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
    public static final long serialVersionUID = 0xc5d5256a44076c73L;
    private boolean lenient;
    private final String pattern;
    private TimeZone timeZone;

    public Object clone()
    {
        pattern pattern1 = (pattern)CalendarDateFormatFactory.getInstance(pattern);
        pattern1.setTimeZone(getTimeZone());
        pattern1.setLenient(isLenient());
        return pattern1;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            if (!super.equals(obj))
            {
                return false;
            }
            obj = (isLenient)obj;
            if (lenient != ((lenient) (obj)).lenient)
            {
                return false;
            }
            if (!pattern.equals(((pattern) (obj)).pattern))
            {
                return false;
            }
            if (!timeZone.equals(((timeZone) (obj)).timeZone))
            {
                return false;
            }
        }
        return true;
    }

    public Calendar getCalendar()
    {
        throw new UnsupportedOperationException();
    }

    public NumberFormat getNumberFormat()
    {
        throw new UnsupportedOperationException();
    }

    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    public int hashCode()
    {
        int j = super.hashCode();
        int k = pattern.hashCode();
        int i;
        if (lenient)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return (i + (j * 31 + k) * 31) * 31 + timeZone.hashCode();
    }

    public boolean isLenient()
    {
        return lenient;
    }

    public void setCalendar(Calendar calendar)
    {
        throw new UnsupportedOperationException();
    }

    public void setLenient(boolean flag)
    {
        lenient = flag;
    }

    public void setNumberFormat(NumberFormat numberformat)
    {
        throw new UnsupportedOperationException();
    }

    public void setTimeZone(TimeZone timezone)
    {
        timeZone = timezone;
    }


    public (String s)
    {
        lenient = true;
        timeZone = DEFAULT_TIME_ZONE;
        pattern = s;
    }
}
