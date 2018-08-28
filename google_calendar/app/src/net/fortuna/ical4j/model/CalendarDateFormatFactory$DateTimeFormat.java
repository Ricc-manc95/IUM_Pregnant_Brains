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

static final class patternEndsWithZ extends mat
{

    public static final long serialVersionUID = 0x29b6d54702049e1aL;
    private final boolean patternEndsWithZ;

    public final StringBuffer format(Date date, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        fieldposition = new GregorianCalendar(getTimeZone());
        fieldposition.setTimeInMillis(date.getTime());
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(1), 4);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(2) + 1, 2);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(5), 2);
        stringbuffer.append("T");
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
            if (s.length() > 20 && !isLenient())
            {
                parseposition.setErrorIndex(20);
                return null;
            }
        } else
        if (s.length() > 17 && !isLenient())
        {
            parseposition.setErrorIndex(17);
            return null;
        }
        if (s.charAt(8) == 'T')
        {
            break MISSING_BLOCK_LABEL_74;
        }
        parseposition.setErrorIndex(8);
        return null;
        if (!patternEndsWithZ || s.charAt(15) == 'Z')
        {
            break MISSING_BLOCK_LABEL_100;
        }
        parseposition.setErrorIndex(15);
        return null;
        try
        {
            int i = Integer.parseInt(s.substring(0, 4));
            int j = Integer.parseInt(s.substring(4, 6));
            int k = Integer.parseInt(s.substring(6, 8));
            int l = Integer.parseInt(s.substring(9, 11));
            int i1 = Integer.parseInt(s.substring(11, 13));
            int j1 = Integer.parseInt(s.substring(13, 15));
            s = CalendarDateFormatFactory.makeCalendar(isLenient(), getTimeZone(), i, j - 1, k, l, i1, j1).getTime();
            parseposition.setIndex(15);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    public mat(String s)
    {
        super(s);
        patternEndsWithZ = s.endsWith("'Z'");
    }
}
