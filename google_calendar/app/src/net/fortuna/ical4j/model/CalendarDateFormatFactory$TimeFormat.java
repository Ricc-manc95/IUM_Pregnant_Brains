// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// Referenced classes of package net.fortuna.ical4j.model:
//            CalendarDateFormatFactory

static final class patternEndsWithZ extends eFormat
{

    public static final long serialVersionUID = 0xed0708aff8f9f8efL;
    private final boolean patternEndsWithZ;

    public final StringBuffer format(Date date, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        fieldposition = new GregorianCalendar(getTimeZone());
        fieldposition.setTimeInMillis(date.getTime());
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(11), 2);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(12), 2);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(13), 2);
        if (patternEndsWithZ)
        {
            stringbuffer.append("Z");
        }
        return stringbuffer;
    }

    public final Date parse(String s, ParsePosition parseposition)
    {
        if (patternEndsWithZ)
        {
            if (s.length() > 9 && !isLenient())
            {
                parseposition.setErrorIndex(9);
                return null;
            }
        } else
        if (s.length() > 6 && !isLenient())
        {
            parseposition.setErrorIndex(6);
            return null;
        }
        if (!patternEndsWithZ || s.charAt(6) == 'Z')
        {
            break MISSING_BLOCK_LABEL_81;
        }
        parseposition.setErrorIndex(6);
        return null;
        try
        {
            int i = Integer.parseInt(s.substring(0, 2));
            int j = Integer.parseInt(s.substring(2, 4));
            int k = Integer.parseInt(s.substring(4, 6));
            s = CalendarDateFormatFactory.makeCalendar(isLenient(), getTimeZone(), 1970, 0, 1, i, j, k).getTime();
            parseposition.setIndex(6);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    public eFormat(String s)
    {
        super(s);
        patternEndsWithZ = s.endsWith("'Z'");
    }
}
