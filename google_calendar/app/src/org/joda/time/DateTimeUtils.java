// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.chrono.ISOChronology;

// Referenced classes of package org.joda.time:
//            DateTimeZone, ReadableInstant, Chronology

public final class DateTimeUtils
{

    private static final SystemMillisProvider SYSTEM_MILLIS_PROVIDER;
    public static volatile MillisProvider cMillisProvider;
    public static volatile Map cZoneNames;

    public static final Chronology getChronology(Chronology chronology)
    {
        Object obj = chronology;
        if (chronology == null)
        {
            obj = ISOChronology.getInstance();
        }
        return ((Chronology) (obj));
    }

    public static final DateFormatSymbols getDateFormatSymbols(Locale locale)
    {
        DateFormatSymbols dateformatsymbols;
        try
        {
            dateformatsymbols = (DateFormatSymbols)java/text/DateFormatSymbols.getMethod("getInstance", new Class[] {
                java/util/Locale
            }).invoke(null, new Object[] {
                locale
            });
        }
        catch (Exception exception)
        {
            return new DateFormatSymbols(locale);
        }
        return dateformatsymbols;
    }

    public static final Chronology getInstantChronology(ReadableInstant readableinstant)
    {
        if (readableinstant == null)
        {
            readableinstant = ISOChronology.getInstance();
        } else
        {
            Chronology chronology = readableinstant.getChronology();
            readableinstant = chronology;
            if (chronology == null)
            {
                return ISOChronology.getInstance();
            }
        }
        return readableinstant;
    }

    public static final long getInstantMillis(ReadableInstant readableinstant)
    {
        if (readableinstant == null)
        {
            return cMillisProvider.getMillis();
        } else
        {
            return readableinstant.getMillis();
        }
    }

    static 
    {
        Object obj = new SystemMillisProvider();
        SYSTEM_MILLIS_PROVIDER = ((SystemMillisProvider) (obj));
        cMillisProvider = ((MillisProvider) (obj));
        obj = new LinkedHashMap();
        ((Map) (obj)).put("UT", DateTimeZone.UTC);
        ((Map) (obj)).put("UTC", DateTimeZone.UTC);
        ((Map) (obj)).put("GMT", DateTimeZone.UTC);
        try
        {
            ((Map) (obj)).put("EST", DateTimeZone.forID("America/New_York"));
        }
        catch (RuntimeException runtimeexception7) { }
        try
        {
            ((Map) (obj)).put("EDT", DateTimeZone.forID("America/New_York"));
        }
        catch (RuntimeException runtimeexception6) { }
        try
        {
            ((Map) (obj)).put("CST", DateTimeZone.forID("America/Chicago"));
        }
        catch (RuntimeException runtimeexception5) { }
        try
        {
            ((Map) (obj)).put("CDT", DateTimeZone.forID("America/Chicago"));
        }
        catch (RuntimeException runtimeexception4) { }
        try
        {
            ((Map) (obj)).put("MST", DateTimeZone.forID("America/Denver"));
        }
        catch (RuntimeException runtimeexception3) { }
        try
        {
            ((Map) (obj)).put("MDT", DateTimeZone.forID("America/Denver"));
        }
        catch (RuntimeException runtimeexception2) { }
        try
        {
            ((Map) (obj)).put("PST", DateTimeZone.forID("America/Los_Angeles"));
        }
        catch (RuntimeException runtimeexception1) { }
        try
        {
            ((Map) (obj)).put("PDT", DateTimeZone.forID("America/Los_Angeles"));
        }
        catch (RuntimeException runtimeexception) { }
        cZoneNames = Collections.unmodifiableMap(((Map) (obj)));
    }

    private class MillisProvider
    {

        public abstract long getMillis();
    }


    private class SystemMillisProvider
        implements MillisProvider
    {

        public final long getMillis()
        {
            return System.currentTimeMillis();
        }

        SystemMillisProvider()
        {
        }
    }

}
