// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CalendarDateFormatFactory
{
    static abstract class CalendarDateFormat extends java.text.DateFormat
    {

        private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
        public static final long serialVersionUID = 0xc5d5256a44076c73L;
        private boolean lenient;
        private final String pattern;
        private TimeZone timeZone;

        public Object clone()
        {
            CalendarDateFormat calendardateformat = (CalendarDateFormat)CalendarDateFormatFactory.getInstance(pattern);
            calendardateformat.setTimeZone(getTimeZone());
            calendardateformat.setLenient(isLenient());
            return calendardateformat;
        }

        public boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                if (!super.equals(obj))
                {
                    return false;
                }
                obj = (CalendarDateFormat)obj;
                if (lenient != ((CalendarDateFormat) (obj)).lenient)
                {
                    return false;
                }
                if (!pattern.equals(((CalendarDateFormat) (obj)).pattern))
                {
                    return false;
                }
                if (!timeZone.equals(((CalendarDateFormat) (obj)).timeZone))
                {
                    return false;
                }
            }
            return true;
        }

        public Calendar getCalendar()
        {
            throw new UnsupportedOperationException();
        }

        public NumberFormat getNumberFormat()
        {
            throw new UnsupportedOperationException();
        }

        public TimeZone getTimeZone()
        {
            return timeZone;
        }

        public int hashCode()
        {
            int j = super.hashCode();
            int k = pattern.hashCode();
            int i;
            if (lenient)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return (i + (j * 31 + k) * 31) * 31 + timeZone.hashCode();
        }

        public boolean isLenient()
        {
            return lenient;
        }

        public void setCalendar(Calendar calendar)
        {
            throw new UnsupportedOperationException();
        }

        public void setLenient(boolean flag)
        {
            lenient = flag;
        }

        public void setNumberFormat(NumberFormat numberformat)
        {
            throw new UnsupportedOperationException();
        }

        public void setTimeZone(TimeZone timezone)
        {
            timeZone = timezone;
        }


        public CalendarDateFormat(String s)
        {
            lenient = true;
            timeZone = DEFAULT_TIME_ZONE;
            pattern = s;
        }
    }

    static final class DateFormat extends CalendarDateFormat
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

        public DateFormat(String s)
        {
            super(s);
        }
    }

    static final class DateTimeFormat extends CalendarDateFormat
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

        public DateTimeFormat(String s)
        {
            super(s);
            patternEndsWithZ = s.endsWith("'Z'");
        }
    }

    static final class TimeFormat extends CalendarDateFormat
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

        public TimeFormat(String s)
        {
            super(s);
            patternEndsWithZ = s.endsWith("'Z'");
        }
    }


    private static final Log LOG = LogFactory.getLog(net/fortuna/ical4j/model/CalendarDateFormatFactory);

    private CalendarDateFormatFactory()
    {
    }

    static void appendPadded(StringBuffer stringbuffer, int i, int j)
    {
        String s = Integer.toString(i);
        int k = s.length();
        for (i = 0; i < j - k; i++)
        {
            stringbuffer.append("0");
        }

        stringbuffer.append(s);
    }

    public static java.text.DateFormat getInstance(String s)
    {
        if (s.equals("yyyyMMdd'T'HHmmss") || s.equals("yyyyMMdd'T'HHmmss'Z'"))
        {
            return new DateTimeFormat(s);
        }
        if (s.equals("yyyyMMdd"))
        {
            return new DateFormat(s);
        }
        if (s.equals("HHmmss") || s.equals("HHmmss'Z'"))
        {
            return new TimeFormat(s);
        }
        if (LOG.isDebugEnabled())
        {
            Log log = LOG;
            String s1 = String.valueOf(s);
            if (s1.length() != 0)
            {
                s1 = "unexpected date format pattern: ".concat(s1);
            } else
            {
                s1 = new String("unexpected date format pattern: ");
            }
            log.debug(s1);
        }
        return new SimpleDateFormat(s);
    }

    static Calendar makeCalendar(boolean flag, TimeZone timezone, int i, int j, int k)
    {
        return makeCalendar(flag, timezone, i, j, k, 0, 0, 0);
    }

    static Calendar makeCalendar(boolean flag, TimeZone timezone, int i, int j, int k, int l, int i1, int j1)
    {
        timezone = new GregorianCalendar(timezone);
        timezone.setLenient(flag);
        timezone.set(i, j, k, l, i1, j1);
        timezone.set(14, 0);
        return timezone;
    }

}
