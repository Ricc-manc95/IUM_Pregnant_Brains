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

    private final char iValue;

    public final int estimateParsedLength()
    {
        return 1;
    }

    public final int estimatePrintedLength()
    {
        return 1;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        if (i >= s.length())
        {
            return ~i;
        }
        char c1 = s.charAt(i);
        char c = iValue;
        if (c1 != c)
        {
            c1 = Character.toUpperCase(c1);
            c = Character.toUpperCase(c);
            if (c1 != c && Character.toLowerCase(c1) != Character.toLowerCase(c))
            {
                return ~i;
            }
        }
        return i + 1;
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        stringbuffer.append(iValue);
    }

    (char c)
    {
        iValue = c;
    }
}
