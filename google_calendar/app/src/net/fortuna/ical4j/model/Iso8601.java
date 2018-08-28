// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.Dates;

// Referenced classes of package net.fortuna.ical4j.model:
//            CalendarDateFormatFactory, TimeZone

public class Iso8601 extends Date
{

    public static final long serialVersionUID = 0xc474459caf999745L;
    public DateFormat format;
    private DateFormat gmtFormat;
    private int precision;

    public Iso8601(long l, String s, int i, TimeZone timezone)
    {
        super(Dates.round(l, i, timezone));
        format = CalendarDateFormatFactory.getInstance(s);
        format.setTimeZone(timezone);
        format.setLenient(CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"));
        precision = i;
    }

    public Iso8601(String s, int i, TimeZone timezone)
    {
        this(1000L * (long)Math.floor((double)System.currentTimeMillis() / 1000D), s, i, timezone);
    }

    public void setTime(long l)
    {
        if (format != null)
        {
            super.setTime(Dates.round(l, precision, format.getTimeZone()));
            return;
        } else
        {
            super.setTime(l);
            return;
        }
    }

    public String toString()
    {
        TimeZone timezone = format.getTimeZone();
        if (!(timezone instanceof net.fortuna.ical4j.model.TimeZone))
        {
            if (gmtFormat == null)
            {
                gmtFormat = (DateFormat)format.clone();
                gmtFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));
            }
            if (timezone.inDaylightTime(this) && timezone.inDaylightTime(new Date(getTime() - 1L)))
            {
                return gmtFormat.format(new Date(getTime() + (long)timezone.getRawOffset() + (long)timezone.getDSTSavings()));
            } else
            {
                return gmtFormat.format(new Date(getTime() + (long)timezone.getRawOffset()));
            }
        } else
        {
            return format.format(this);
        }
    }
}
