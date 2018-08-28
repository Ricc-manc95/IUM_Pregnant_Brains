// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class iValue
    implements DateTimeParser, DateTimePrinter
{

    private final String iValue;

    public final int estimateParsedLength()
    {
        return iValue.length();
    }

    public final int estimatePrintedLength()
    {
        return iValue.length();
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        if (s.regionMatches(true, i, iValue, 0, iValue.length()))
        {
            return iValue.length() + i;
        } else
        {
            return ~i;
        }
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        stringbuffer.append(iValue);
    }

    A(String s)
    {
        iValue = s;
    }
}
