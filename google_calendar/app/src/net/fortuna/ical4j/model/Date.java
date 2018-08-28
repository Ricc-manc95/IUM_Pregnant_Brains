// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.TimeZones;

// Referenced classes of package net.fortuna.ical4j.model:
//            Iso8601

public class Date extends Iso8601
{

    public static final long serialVersionUID = 0x63086c017885f5c5L;

    public Date()
    {
        super("yyyyMMdd", 1, TimeZones.getDateTimeZone());
    }

    protected Date(int i, TimeZone timezone)
    {
        super("yyyyMMdd", 0, timezone);
    }

    public Date(long l)
    {
        super(l, "yyyyMMdd", 1, TimeZones.getDateTimeZone());
    }

    protected Date(long l, int i, TimeZone timezone)
    {
        super(l, "yyyyMMdd", i, timezone);
    }

    public Date(String s)
        throws ParseException
    {
        this();
        try
        {
            setTime(super.format.parse(s).getTime());
            return;
        }
        catch (Object obj)
        {
            if (CompatibilityHints.isHintEnabled("ical4j.compatibility.vcard"))
            {
                obj = new SimpleDateFormat("yyyy'-'MM'-'dd");
                ((DateFormat) (obj)).setTimeZone(TimeZones.getDateTimeZone());
                setTime(((DateFormat) (obj)).parse(s).getTime());
                return;
            } else
            {
                throw obj;
            }
        }
    }

    public Date(java.util.Date date)
    {
        this(date.getTime(), 1, TimeZones.getDateTimeZone());
    }
}
