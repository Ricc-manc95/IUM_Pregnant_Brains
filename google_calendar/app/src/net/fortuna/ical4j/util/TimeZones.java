// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.util.Properties;
import java.util.TimeZone;

// Referenced classes of package net.fortuna.ical4j.util:
//            Configurator

public final class TimeZones
{

    public static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("Etc/UTC");

    public static TimeZone getDateTimeZone()
    {
        String s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.timezone.date.floating");
        String s = s1;
        if (s1 == null)
        {
            s = System.getProperty("net.fortuna.ical4j.timezone.date.floating");
        }
        if ("true".equals(s))
        {
            return TimeZone.getDefault();
        } else
        {
            return UTC_TIMEZONE;
        }
    }

    public static boolean isUtc(TimeZone timezone)
    {
        return "Etc/UTC".equals(timezone.getID()) || "GMT".equals(timezone.getID());
    }

}
