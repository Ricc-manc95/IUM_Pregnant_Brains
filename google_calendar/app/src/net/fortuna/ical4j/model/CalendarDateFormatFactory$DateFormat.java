// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package net.fortuna.ical4j.model:
//            CalendarDateFormatFactory

static final class eFormat extends eFormat
{

    public static final long serialVersionUID = 0x962abac42cecf05dL;

    public final StringBuffer format(Date date, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        fieldposition = Calendar.getInstance(getTimeZone());
        fieldposition.setTimeInMillis(date.getTime());
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(1), 4);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(2) + 1, 2);
        CalendarDateFormatFactory.appendPadded(stringbuffer, fieldposition.get(5), 2);
        return stringbuffer;
    }

    public final Date parse(String s, ParsePosition parseposition)
    {
        if (s.length() > 8 && !isLenient())
        {
            parseposition.setErrorIndex(8);
            return null;
        }
        try
        {
            int i = Integer.parseInt(s.substring(0, 4));
            int j = Integer.parseInt(s.substring(4, 6));
            int k = Integer.parseInt(s.substring(6, 8));
            s = CalendarDateFormatFactory.makeCalendar(isLenient(), getTimeZone(), i, j - 1, k).getTime();
            parseposition.setIndex(8);
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
    }
}
