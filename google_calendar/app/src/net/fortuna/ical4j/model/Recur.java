// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.Configurator;
import net.fortuna.ical4j.util.Dates;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package net.fortuna.ical4j.model:
//            DateTime, Date, NumberList, WeekDayList, 
//            WeekDay, DateList, Parameter

public class Recur
    implements Serializable
{

    public static int maxIncrementCount = 0;
    public static final long serialVersionUID = 0x9a3b25449342b25aL;
    public int calIncField;
    public int calendarWeekStartDay;
    public int count;
    public WeekDayList dayList;
    private Map experimentalValues;
    public String frequency;
    public NumberList hourList;
    public int interval;
    public transient Log log;
    public NumberList minuteList;
    public NumberList monthDayList;
    public NumberList monthList;
    public NumberList secondList;
    public NumberList setPosList;
    public Date until;
    public NumberList weekNoList;
    private String weekStartDay;
    public NumberList yearDayList;

    public Recur()
    {
        log = LogFactory.getLog(net/fortuna/ical4j/model/Recur);
        count = -1;
        interval = -1;
        experimentalValues = new HashMap();
        calendarWeekStartDay = 2;
    }

    public Recur(String s)
        throws ParseException
    {
        log = LogFactory.getLog(net/fortuna/ical4j/model/Recur);
        count = -1;
        interval = -1;
        experimentalValues = new HashMap();
        calendarWeekStartDay = 2;
        for (s = new StringTokenizer(s, ";="); s.hasMoreTokens();)
        {
            String s1 = s.nextToken();
            if ("FREQ".equals(s1))
            {
                frequency = nextToken(s, s1);
            } else
            if ("UNTIL".equals(s1))
            {
                s1 = nextToken(s, s1);
                if (s1 != null && s1.indexOf("T") >= 0)
                {
                    until = new DateTime(s1);
                    ((DateTime)until).setUtc(true);
                } else
                {
                    until = new Date(s1);
                }
            } else
            if ("COUNT".equals(s1))
            {
                count = Integer.parseInt(nextToken(s, s1));
            } else
            if ("INTERVAL".equals(s1))
            {
                interval = Integer.parseInt(nextToken(s, s1));
            } else
            if ("BYSECOND".equals(s1))
            {
                secondList = new NumberList(nextToken(s, s1), 0, 59, false);
            } else
            if ("BYMINUTE".equals(s1))
            {
                minuteList = new NumberList(nextToken(s, s1), 0, 59, false);
            } else
            if ("BYHOUR".equals(s1))
            {
                hourList = new NumberList(nextToken(s, s1), 0, 23, false);
            } else
            if ("BYDAY".equals(s1))
            {
                dayList = new WeekDayList(nextToken(s, s1));
            } else
            if ("BYMONTHDAY".equals(s1))
            {
                monthDayList = new NumberList(nextToken(s, s1), 1, 31, true);
            } else
            if ("BYYEARDAY".equals(s1))
            {
                yearDayList = new NumberList(nextToken(s, s1), 1, 366, true);
            } else
            if ("BYWEEKNO".equals(s1))
            {
                weekNoList = new NumberList(nextToken(s, s1), 1, 53, true);
            } else
            if ("BYMONTH".equals(s1))
            {
                monthList = new NumberList(nextToken(s, s1), 1, 12, false);
            } else
            if ("BYSETPOS".equals(s1))
            {
                setPosList = new NumberList(nextToken(s, s1), 1, 366, true);
            } else
            if ("WKST".equals(s1))
            {
                weekStartDay = nextToken(s, s1);
                calendarWeekStartDay = WeekDay.getCalendarDay(new WeekDay(weekStartDay));
            } else
            if (CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
            {
                experimentalValues.put(s1, nextToken(s, s1));
            } else
            {
                s = nextToken(s, s1);
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s1).length() + 31 + String.valueOf(s).length())).append("Invalid recurrence rule part: ").append(s1).append("=").append(s).toString());
            }
        }

        validateFrequency();
    }

    public Recur(String s, int i)
    {
        log = LogFactory.getLog(net/fortuna/ical4j/model/Recur);
        count = -1;
        interval = -1;
        experimentalValues = new HashMap();
        calendarWeekStartDay = 2;
        frequency = s;
        count = 1;
        validateFrequency();
    }

    public static DateList getDateListInstance(DateList datelist)
    {
        DateList datelist1 = new DateList(datelist.type);
        if (datelist.utc)
        {
            datelist1.setUtc(true);
            return datelist1;
        } else
        {
            datelist1.setTimeZone(datelist.timeZone);
            return datelist1;
        }
    }

    private static String nextToken(StringTokenizer stringtokenizer, String s)
    {
        try
        {
            stringtokenizer = stringtokenizer.nextToken();
        }
        // Misplaced declaration of an exception variable
        catch (StringTokenizer stringtokenizer)
        {
            stringtokenizer = String.valueOf(s);
            if (stringtokenizer.length() != 0)
            {
                stringtokenizer = "Missing expected token, last token: ".concat(stringtokenizer);
            } else
            {
                stringtokenizer = new String("Missing expected token, last token: ");
            }
            throw new IllegalArgumentException(stringtokenizer);
        }
        return stringtokenizer;
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        log = LogFactory.getLog(net/fortuna/ical4j/model/Recur);
    }

    private final void validateFrequency()
    {
        if (frequency == null)
        {
            throw new IllegalArgumentException("A recurrence rule MUST contain a FREQ rule part.");
        }
        if ("SECONDLY".equals(frequency))
        {
            calIncField = 13;
            return;
        }
        if ("MINUTELY".equals(frequency))
        {
            calIncField = 12;
            return;
        }
        if ("HOURLY".equals(frequency))
        {
            calIncField = 11;
            return;
        }
        if ("DAILY".equals(frequency))
        {
            calIncField = 6;
            return;
        }
        if ("WEEKLY".equals(frequency))
        {
            calIncField = 3;
            return;
        }
        if ("MONTHLY".equals(frequency))
        {
            calIncField = 2;
            return;
        }
        if ("YEARLY".equals(frequency))
        {
            calIncField = 1;
            return;
        } else
        {
            String s = frequency;
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 44)).append("Invalid FREQ rule part '").append(s).append("' in recurrence rule").toString());
        }
    }

    public final DateList getMonthDayVariants(DateList datelist)
    {
        DateList datelist1;
        Iterator iterator;
        if (monthDayList == null)
        {
            monthDayList = new NumberList(1, 31, true);
        }
        if (monthDayList.isEmpty())
        {
            return datelist;
        }
        datelist1 = getDateListInstance(datelist);
        iterator = datelist.iterator();
_L2:
        Calendar calendar;
        Iterator iterator1;
        Integer integer;
        if (iterator.hasNext())
        {
            datelist = (Date)iterator.next();
            calendar = Dates.getCalendarInstance(datelist);
            calendar.setMinimalDaysInFirstWeek(4);
            calendar.setFirstDayOfWeek(calendarWeekStartDay);
            calendar.setLenient(false);
            calendar.setTime(datelist);
            if (monthDayList == null)
            {
                monthDayList = new NumberList(1, 31, true);
            }
            iterator1 = monthDayList.iterator();
        } else
        {
            return datelist1;
        }
_L3:
        if (!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        integer = (Integer)iterator1.next();
        calendar.set(5, Dates.getAbsMonthDay(calendar.getTime(), integer.intValue()));
        datelist = calendar.getTime();
        Value value = datelist1.type;
        if (!Value.DATE.equals(value))
        {
            break MISSING_BLOCK_LABEL_274;
        }
        datelist = new Date(datelist);
_L4:
        datelist1.add(datelist);
          goto _L3
        datelist;
        if (log.isTraceEnabled())
        {
            datelist = log;
            int i = Dates.getAbsMonthDay(calendar.getTime(), integer.intValue());
            datelist.trace((new StringBuilder(33)).append("Invalid day of month: ").append(i).toString());
        }
          goto _L3
        datelist = new DateTime(datelist);
          goto _L4
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("FREQ");
        stringbuffer.append('=');
        stringbuffer.append(frequency);
        if (weekStartDay != null)
        {
            stringbuffer.append(';');
            stringbuffer.append("WKST");
            stringbuffer.append('=');
            stringbuffer.append(weekStartDay);
        }
        if (interval > 0)
        {
            stringbuffer.append(';');
            stringbuffer.append("INTERVAL");
            stringbuffer.append('=');
            stringbuffer.append(interval);
        }
        if (until != null)
        {
            stringbuffer.append(';');
            stringbuffer.append("UNTIL");
            stringbuffer.append('=');
            stringbuffer.append(until);
        }
        if (count > 0)
        {
            stringbuffer.append(';');
            stringbuffer.append("COUNT");
            stringbuffer.append('=');
            stringbuffer.append(count);
        }
        if (monthList == null)
        {
            monthList = new NumberList(1, 12, false);
        }
        if (!monthList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYMONTH");
            stringbuffer.append('=');
            stringbuffer.append(monthList);
        }
        if (weekNoList == null)
        {
            weekNoList = new NumberList(1, 53, true);
        }
        if (!weekNoList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYWEEKNO");
            stringbuffer.append('=');
            stringbuffer.append(weekNoList);
        }
        if (yearDayList == null)
        {
            yearDayList = new NumberList(1, 366, true);
        }
        if (!yearDayList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYYEARDAY");
            stringbuffer.append('=');
            stringbuffer.append(yearDayList);
        }
        if (monthDayList == null)
        {
            monthDayList = new NumberList(1, 31, true);
        }
        if (!monthDayList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYMONTHDAY");
            stringbuffer.append('=');
            stringbuffer.append(monthDayList);
        }
        if (dayList == null)
        {
            dayList = new WeekDayList();
        }
        if (!dayList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYDAY");
            stringbuffer.append('=');
            stringbuffer.append(dayList);
        }
        if (hourList == null)
        {
            hourList = new NumberList(0, 23, false);
        }
        if (!hourList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYHOUR");
            stringbuffer.append('=');
            stringbuffer.append(hourList);
        }
        if (minuteList == null)
        {
            minuteList = new NumberList(0, 59, false);
        }
        if (!minuteList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYMINUTE");
            stringbuffer.append('=');
            stringbuffer.append(minuteList);
        }
        if (secondList == null)
        {
            secondList = new NumberList(0, 59, false);
        }
        if (!secondList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYSECOND");
            stringbuffer.append('=');
            stringbuffer.append(secondList);
        }
        if (setPosList == null)
        {
            setPosList = new NumberList(1, 366, true);
        }
        if (!setPosList.isEmpty())
        {
            stringbuffer.append(';');
            stringbuffer.append("BYSETPOS");
            stringbuffer.append('=');
            stringbuffer.append(setPosList);
        }
        return stringbuffer.toString();
    }

    static 
    {
        String s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.recur.maxincrementcount");
        String s = s1;
        if (s1 == null)
        {
            s = System.getProperty("net.fortuna.ical4j.recur.maxincrementcount");
        }
        if (s != null && s.length() > 0)
        {
            maxIncrementCount = Integer.parseInt(s);
        } else
        {
            maxIncrementCount = 1000;
        }
    }
}
