// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.fortuna.ical4j.util.TimeZones;

// Referenced classes of package net.fortuna.ical4j.model:
//            Iso8601

public final class Time extends Iso8601
{

    public static final long serialVersionUID = 0x8b699d1f9d5cb3e4L;
    public boolean utc;

    public Time(long l, TimeZone timezone)
    {
        this(l, timezone, TimeZones.isUtc(timezone));
    }

    private Time(long l, TimeZone timezone, boolean flag)
    {
        String s;
        if (flag)
        {
            s = "HHmmss'Z'";
        } else
        {
            s = "HHmmss";
        }
        super(l, s, 0, timezone);
        utc = false;
        super.format.setTimeZone(timezone);
        utc = flag;
    }

    public Time(Date date, TimeZone timezone, boolean flag)
    {
        long l = date.getTime();
        if (flag)
        {
            date = "HHmmss'Z'";
        } else
        {
            date = "HHmmss";
        }
        super(l, date, 0, timezone);
        utc = false;
        super.format.setTimeZone(timezone);
        utc = flag;
    }
}
