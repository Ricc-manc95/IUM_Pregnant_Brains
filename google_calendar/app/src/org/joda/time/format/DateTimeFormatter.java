// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParserBucket, DateTimeParser, FormatUtils, DateTimePrinter

public final class DateTimeFormatter
{

    private final Chronology iChrono;
    private final int iDefaultYear;
    public final Locale iLocale;
    private final boolean iOffsetParsed;
    public final DateTimeParser iParser;
    private final Integer iPivotYear;
    public final DateTimePrinter iPrinter;
    public final DateTimeZone iZone;

    public DateTimeFormatter(DateTimePrinter datetimeprinter, DateTimeParser datetimeparser)
    {
        iPrinter = datetimeprinter;
        iParser = datetimeparser;
        iLocale = null;
        iOffsetParsed = false;
        iChrono = null;
        iZone = null;
        iPivotYear = null;
        iDefaultYear = 2000;
    }

    private DateTimeFormatter(DateTimePrinter datetimeprinter, DateTimeParser datetimeparser, Locale locale, boolean flag, Chronology chronology, DateTimeZone datetimezone, Integer integer, 
            int i)
    {
        iPrinter = datetimeprinter;
        iParser = datetimeparser;
        iLocale = locale;
        iOffsetParsed = flag;
        iChrono = chronology;
        iZone = datetimezone;
        iPivotYear = integer;
        iDefaultYear = i;
    }

    private final Chronology selectChronology(Chronology chronology)
    {
        chronology = DateTimeUtils.getChronology(chronology);
        if (iChrono != null)
        {
            chronology = iChrono;
        }
        Chronology chronology1 = chronology;
        if (iZone != null)
        {
            chronology1 = chronology.withZone(iZone);
        }
        return chronology1;
    }

    public final long parseMillis(String s)
    {
        DateTimeParser datetimeparser = iParser;
        if (datetimeparser == null)
        {
            throw new UnsupportedOperationException("Parsing not supported");
        }
        DateTimeParserBucket datetimeparserbucket = new DateTimeParserBucket(0L, selectChronology(iChrono), iLocale, iPivotYear, iDefaultYear);
        int j = datetimeparser.parseInto(datetimeparserbucket, s, 0);
        int i;
        if (j >= 0)
        {
            i = j;
            if (j >= s.length())
            {
                return datetimeparserbucket.computeMillis(true, s);
            }
        } else
        {
            i = ~j;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(s, i));
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology)
    {
        DateTimePrinter datetimeprinter = iPrinter;
        if (datetimeprinter == null)
        {
            throw new UnsupportedOperationException("Printing not supported");
        }
        Chronology chronology1 = selectChronology(chronology);
        DateTimeZone datetimezone = chronology1.getZone();
        int j = datetimezone.getOffset(l);
        long l2 = (long)j + l;
        long l1 = l2;
        int i = j;
        chronology = datetimezone;
        if ((l ^ l2) < 0L)
        {
            l1 = l2;
            i = j;
            chronology = datetimezone;
            if (((long)j ^ l) >= 0L)
            {
                chronology = DateTimeZone.UTC;
                i = 0;
                l1 = l;
            }
        }
        datetimeprinter.printTo(stringbuffer, l1, chronology1.withUTC(), i, chronology, iLocale);
    }

    public final DateTimeFormatter withChronology(Chronology chronology)
    {
        if (iChrono == chronology)
        {
            return this;
        } else
        {
            return new DateTimeFormatter(iPrinter, iParser, iLocale, iOffsetParsed, chronology, iZone, iPivotYear, iDefaultYear);
        }
    }

    public final DateTimeFormatter withZoneUTC()
    {
        DateTimeZone datetimezone = DateTimeZone.UTC;
        if (iZone == datetimezone)
        {
            return this;
        } else
        {
            return new DateTimeFormatter(iPrinter, iParser, iLocale, false, iChrono, datetimezone, iPivotYear, iDefaultYear);
        }
    }
}
