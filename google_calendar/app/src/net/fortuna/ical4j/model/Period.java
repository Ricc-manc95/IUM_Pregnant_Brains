// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.ParseException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            DateRange, DateTime, Time, Dur

public class Period extends DateRange
    implements Comparable
{

    public static final long serialVersionUID = 0x6599bcf36a2a304aL;
    private Dur duration;

    public Period(String s)
        throws ParseException
    {
        super(new DateTime(s.substring(0, s.indexOf('/'))), parseEndDate(s, true));
        try
        {
            parseEndDate(s, false);
        }
        catch (ParseException parseexception)
        {
            duration = new Dur(s.substring(s.indexOf('/') + 1));
        }
        if (((DateTime)super.rangeStart).time.utc)
        {
            ((DateTime)super.rangeEnd).setUtc(true);
            return;
        } else
        {
            ((DateTime)super.rangeEnd).setTimeZone(((DateTime)super.rangeStart).timezone);
            return;
        }
    }

    private final Dur getDuration()
    {
        if (duration == null)
        {
            return new Dur((DateTime)super.rangeStart, (DateTime)super.rangeEnd);
        } else
        {
            return duration;
        }
    }

    private static DateTime parseEndDate(String s, boolean flag)
        throws ParseException
    {
        DateTime datetime;
        try
        {
            datetime = new DateTime(s.substring(s.indexOf('/') + 1));
        }
        catch (ParseException parseexception)
        {
            if (flag)
            {
                return new DateTime((new Dur(s.substring(s.indexOf('/') + 1))).getTime(new DateTime(s.substring(0, s.indexOf('/')))));
            } else
            {
                throw parseexception;
            }
        }
        return datetime;
    }

    public final int compareTo(Object obj)
    {
        int i;
        obj = (Period)obj;
        if (obj == null)
        {
            throw new ClassCastException("Cannot compare this object to null");
        }
        i = ((DateTime)super.rangeStart).compareTo((DateTime)((DateRange) (obj)).rangeStart);
        if (i == 0) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        int j;
        if (duration != null)
        {
            break; /* Loop/switch isn't completed */
        }
        j = ((DateTime)super.rangeEnd).compareTo((DateTime)((DateRange) (obj)).rangeEnd);
        i = j;
        if (j != 0) goto _L1; else goto _L3
_L3:
        return getDuration().compareTo(((Period) (obj)).getDuration());
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Period))
        {
            return false;
        } else
        {
            obj = (Period)obj;
            return (new EqualsBuilder()).append((DateTime)super.rangeStart, (DateTime)((DateRange) (obj)).rangeStart).append((DateTime)super.rangeEnd, (DateTime)((DateRange) (obj)).rangeEnd).isEquals;
        }
    }

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = (new HashCodeBuilder()).append((DateTime)super.rangeStart);
        Object obj;
        if (duration == null)
        {
            obj = (DateTime)super.rangeEnd;
        } else
        {
            obj = duration;
        }
        return hashcodebuilder.append(obj).iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((DateTime)super.rangeStart);
        stringbuffer.append('/');
        if (duration == null)
        {
            stringbuffer.append((DateTime)super.rangeEnd);
        } else
        {
            stringbuffer.append(duration);
        }
        return stringbuffer.toString();
    }
}
