// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.TimeZones;
import org.apache.commons.lang.builder.EqualsBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Date, Time, Iso8601, TimeZone

public final class DateTime extends net.fortuna.ical4j.model.Date
{

    private static final DateFormatCache DEFAULT_FORMAT;
    private static final DateFormatCache LENIENT_DEFAULT_FORMAT = new DateFormatCache(new SimpleDateFormat("yyyyMMdd'T'HHmmss"));
    private static final DateFormatCache RELAXED_FORMAT;
    private static final DateFormatCache UTC_FORMAT;
    private static final DateFormatCache VCARD_FORMAT = new DateFormatCache(new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'"));
    public static final long serialVersionUID = 0xa714f106e577d1fdL;
    public Time time;
    public net.fortuna.ical4j.model.TimeZone timezone;

    public DateTime()
    {
        super(0, TimeZone.getDefault());
        time = new Time(getTime(), super.format.getTimeZone());
    }

    public DateTime(long l)
    {
        super(l, 0, TimeZone.getDefault());
        time = new Time(l, super.format.getTimeZone());
    }

    public DateTime(String s)
        throws ParseException
    {
        this(s, null);
    }

    public DateTime(String s, net.fortuna.ical4j.model.TimeZone timezone1)
        throws ParseException
    {
        Object obj;
        if (timezone1 != null)
        {
            obj = timezone1;
        } else
        {
            obj = TimeZone.getDefault();
        }
        super(0L, 0, ((TimeZone) (obj)));
        time = new Time(getTime(), super.format.getTimeZone());
        if (!s.endsWith("Z")) goto _L2; else goto _L1
_L1:
        obj = UTC_FORMAT.get();
        if (true)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        ((DateFormat) (obj)).setTimeZone(null);
        setTime(((DateFormat) (obj)).parse(s).getTime());
        setUtc(true);
_L6:
        return;
_L2:
        if (timezone1 == null) goto _L4; else goto _L3
_L3:
        Object obj1 = DEFAULT_FORMAT.get();
        if (timezone1 == null)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        ((DateFormat) (obj1)).setTimeZone(timezone1);
        setTime(((DateFormat) (obj1)).parse(s).getTime());
_L7:
        setTimeZone(timezone1);
        return;
        obj1;
        if (!CompatibilityHints.isHintEnabled("ical4j.compatibility.vcard"))
        {
            break MISSING_BLOCK_LABEL_251;
        }
        obj1 = VCARD_FORMAT.get();
        if (timezone1 == null)
        {
            break MISSING_BLOCK_LABEL_148;
        }
        ((DateFormat) (obj1)).setTimeZone(timezone1);
        setTime(((DateFormat) (obj1)).parse(s).getTime());
        setTimeZone(timezone1);
        return;
        obj1;
        if (!CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed")) goto _L6; else goto _L5
_L5:
        obj1 = RELAXED_FORMAT.get();
        if (timezone1 != null)
        {
            ((DateFormat) (obj1)).setTimeZone(timezone1);
        }
        setTime(((DateFormat) (obj1)).parse(s).getTime());
        setTimeZone(timezone1);
        return;
_L4:
        TimeZone timezone2;
        obj1 = LENIENT_DEFAULT_FORMAT.get();
        timezone2 = super.format.getTimeZone();
        if (timezone2 == null)
        {
            break MISSING_BLOCK_LABEL_236;
        }
        ((DateFormat) (obj1)).setTimeZone(timezone2);
        setTime(((DateFormat) (obj1)).parse(s).getTime());
          goto _L7
        if (CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
        {
            obj1 = RELAXED_FORMAT.get();
            if (timezone1 != null)
            {
                ((DateFormat) (obj1)).setTimeZone(timezone1);
            }
            setTime(((DateFormat) (obj1)).parse(s).getTime());
            setTimeZone(timezone1);
            return;
        } else
        {
            throw obj1;
        }
    }

    public DateTime(Date date)
    {
label0:
        {
            super(date.getTime(), 0, TimeZone.getDefault());
            time = new Time(date.getTime(), super.format.getTimeZone());
            if (date instanceof DateTime)
            {
                date = (DateTime)date;
                if (!((DateTime) (date)).time.utc)
                {
                    break label0;
                }
                setUtc(true);
            }
            return;
        }
        setTimeZone(((DateTime) (date)).timezone);
    }

    public DateTime(boolean flag)
    {
        this();
        setUtc(true);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof DateTime)
        {
            return (new EqualsBuilder()).append(time, ((DateTime)obj).time).isEquals;
        } else
        {
            return super.equals(obj);
        }
    }

    public final void setTime(long l)
    {
        super.setTime(l);
        if (time != null)
        {
            time.setTime(l);
        }
    }

    public final void setTimeZone(net.fortuna.ical4j.model.TimeZone timezone1)
    {
        timezone = timezone1;
        if (timezone1 != null)
        {
            super.format.setTimeZone(timezone1);
        } else
        {
            super.format.setTimeZone(TimeZone.getDefault());
        }
        time = new Time(time, super.format.getTimeZone(), false);
    }

    public final void setUtc(boolean flag)
    {
        timezone = null;
        if (flag)
        {
            super.format.setTimeZone(TimeZones.UTC_TIMEZONE);
        } else
        {
            super.format.setTimeZone(TimeZone.getDefault());
        }
        time = new Time(time, super.format.getTimeZone(), flag);
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append('T');
        stringbuffer.append(time.toString());
        return stringbuffer.toString();
    }

    static 
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpledateformat.setTimeZone(TimeZones.UTC_TIMEZONE);
        simpledateformat.setLenient(false);
        UTC_FORMAT = new DateFormatCache(simpledateformat);
        simpledateformat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        simpledateformat.setLenient(false);
        DEFAULT_FORMAT = new DateFormatCache(simpledateformat);
        simpledateformat = new SimpleDateFormat("yyyyMMdd");
        simpledateformat.setLenient(true);
        RELAXED_FORMAT = new DateFormatCache(simpledateformat);
    }

    private class DateFormatCache
    {

        private final DateFormat templateFormat;
        private final Map threadMap = new WeakHashMap();

        public final DateFormat get()
        {
            DateFormat dateformat1 = (DateFormat)threadMap.get(Thread.currentThread());
            DateFormat dateformat = dateformat1;
            if (dateformat1 == null)
            {
                dateformat = (DateFormat)templateFormat.clone();
                threadMap.put(Thread.currentThread(), dateformat);
            }
            return dateformat;
        }

        DateFormatCache(DateFormat dateformat)
        {
            templateFormat = dateformat;
        }
    }

}
