// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Iso8601;
import net.fortuna.ical4j.model.NumberList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.Time;
import net.fortuna.ical4j.model.UtcOffset;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.WeekDayList;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.DateListProperty;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.RDate;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.TzOffsetFrom;
import net.fortuna.ical4j.util.Dates;
import net.fortuna.ical4j.util.PropertyValidator;
import net.fortuna.ical4j.util.TimeZones;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Observance extends Component
{

    private static final DateFormat UTC_FORMAT;
    public static final long serialVersionUID = 0x2304ab9d3c2c846aL;
    private net.fortuna.ical4j.model.Date initialOnset;
    private net.fortuna.ical4j.model.Date onsetLimit;
    private DateTime onsetsDates[];
    private long onsetsMillisec[];

    protected Observance(String s)
    {
        super(s);
        new TreeMap();
        initialOnset = null;
    }

    protected Observance(String s, PropertyList propertylist)
    {
        super(s, propertylist);
        new TreeMap();
        initialOnset = null;
    }

    private static DateTime calculateOnset(String s)
        throws ParseException
    {
        long l;
        synchronized (UTC_FORMAT)
        {
            l = UTC_FORMAT.parse(s).getTime();
        }
        s = new DateTime(true);
        s.setTime(l);
        return s;
        s;
        dateformat;
        JVM INSTR monitorexit ;
        throw s;
    }

    public final net.fortuna.ical4j.model.Date getLatestOnset(net.fortuna.ical4j.model.Date date)
    {
        if (initialOnset == null)
        {
            try
            {
                DateTime datetime = calculateOnset(((DateProperty) ((DtStart)super.properties.getProperty("DTSTART"))).date.toString());
                DateTime datetime1 = new DateTime(true);
                datetime1.setTime(datetime.getTime() - ((TzOffsetFrom)super.properties.getProperty("TZOFFSETFROM")).offset.offset);
                initialOnset = datetime1;
            }
            // Misplaced declaration of an exception variable
            catch (net.fortuna.ical4j.model.Date date)
            {
                LogFactory.getLog(net/fortuna/ical4j/model/component/Observance).error("Unexpected error calculating initial onset", date);
                return null;
            }
        }
        if (!date.before(initialOnset)) goto _L2; else goto _L1
_L1:
        date = null;
_L41:
        return date;
_L2:
        Object obj2;
        DateList datelist9;
        DateTime datetime4;
        if (onsetsMillisec != null && (onsetLimit == null || date.before(onsetLimit)))
        {
            int i = Arrays.binarySearch(onsetsMillisec, date.getTime());
            if (i >= 0)
            {
                return onsetsDates[i];
            } else
            {
                i = -i;
                return onsetsDates[i - 1 - 1];
            }
        }
        obj2 = initialOnset;
        Object obj;
        Iterator iterator;
        Iterator iterator1;
        DateTime datetime3;
        boolean flag;
        try
        {
            datetime4 = calculateOnset(((DateProperty) ((DtStart)super.properties.getProperty("DTSTART"))).date.toString());
        }
        // Misplaced declaration of an exception variable
        catch (net.fortuna.ical4j.model.Date date)
        {
            LogFactory.getLog(net/fortuna/ical4j/model/component/Observance).error("Unexpected error calculating initial onset", date);
            return null;
        }
        datelist9 = new DateList();
        datelist9.setUtc(true);
        datelist9.add(initialOnset);
        iterator = super.properties.getProperties("RDATE").iterator();
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        iterator1 = ((DateListProperty) ((RDate)iterator.next())).dates.iterator();
        obj = obj2;
_L5:
        obj2 = obj;
        if (!iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_236;
        }
        datetime3 = calculateOnset(((net.fortuna.ical4j.model.Date)iterator1.next()).toString());
        obj2 = new DateTime(true);
        ((Iso8601) (obj2)).setTime(datetime3.getTime() - ((TzOffsetFrom)super.properties.getProperty("TZOFFSETFROM")).offset.offset);
        if (((DateTime) (obj2)).after(date))
        {
            break MISSING_BLOCK_LABEL_355;
        }
        flag = ((DateTime) (obj2)).after(((Date) (obj)));
        if (flag)
        {
            obj = obj2;
        }
        try
        {
            datelist9.add(((net.fortuna.ical4j.model.Date) (obj2)));
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Object obj2) { }
_L43:
        LogFactory.getLog(net/fortuna/ical4j/model/component/Observance).error("Unexpected error calculating onset", ((Throwable) (obj2)));
        if (true) goto _L5; else goto _L4
_L4:
        Object obj1;
        Iterator iterator9;
        iterator9 = super.properties.getProperties("RRULE").iterator();
        obj1 = obj2;
_L39:
        Recur recur;
        net.fortuna.ical4j.model.Date date1;
        Value value1;
        DateList datelist10;
        Calendar calendar6;
        int k;
        int l;
        if (!iterator9.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        RRule rrule = (RRule)iterator9.next();
        obj2 = Dates.getCalendarInstance(date);
        ((Calendar) (obj2)).setTime(date);
        ((Calendar) (obj2)).add(1, 10);
        obj2 = ((Calendar) (obj2)).getTime();
        Value value = Value.DATE_TIME;
        if (Value.DATE.equals(value))
        {
            obj2 = new net.fortuna.ical4j.model.Date(((Date) (obj2)));
        } else
        {
            obj2 = new DateTime(((Date) (obj2)));
        }
        onsetLimit = ((net.fortuna.ical4j.model.Date) (obj2));
        recur = rrule.recur;
        date1 = onsetLimit;
        value1 = Value.DATE_TIME;
        datelist10 = new DateList(value1);
        if (datetime4 instanceof DateTime)
        {
            if (((DateTime)datetime4).time.utc)
            {
                datelist10.setUtc(true);
            } else
            {
                datelist10.setTimeZone(((DateTime)datetime4).timezone);
            }
        }
        calendar6 = Dates.getCalendarInstance(datetime4);
        calendar6.setMinimalDaysInFirstWeek(4);
        calendar6.setFirstDayOfWeek(recur.calendarWeekStartDay);
        calendar6.setLenient(true);
        calendar6.setTime(datetime4);
        if (recur.count <= 0)
        {
            obj2 = (Calendar)calendar6.clone();
            while (((Calendar) (obj2)).getTime().before(datetime4)) 
            {
                calendar6.setTime(((Calendar) (obj2)).getTime());
                int j;
                if (recur.interval > 0)
                {
                    j = recur.interval;
                } else
                {
                    j = 1;
                }
                ((Calendar) (obj2)).add(recur.calIncField, j);
            }
        }
        k = 0;
        obj2 = null;
        l = 0;
_L35:
        Object obj3;
        Object obj5;
        obj3 = calendar6.getTime();
        Object obj4;
        if (Value.DATE.equals(value1))
        {
            obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
        } else
        {
            obj3 = new DateTime(((Date) (obj3)));
        }
        if (recur.until != null && obj2 != null && ((net.fortuna.ical4j.model.Date) (obj2)).after(recur.until) || date1 != null && obj2 != null && ((net.fortuna.ical4j.model.Date) (obj2)).after(date1) || recur.count > 0 && datelist10.size() + k >= recur.count) goto _L7; else goto _L6
_L6:
        if (obj3 instanceof DateTime)
        {
            if (datelist10.utc)
            {
                ((DateTime)obj3).setUtc(true);
            } else
            {
                ((DateTime)obj3).setTimeZone(datelist10.timeZone);
            }
        }
        obj5 = new DateList(value1);
        if (obj3 instanceof DateTime)
        {
            if (((DateTime)obj3).time.utc)
            {
                ((DateList) (obj5)).setUtc(true);
            } else
            {
                ((DateList) (obj5)).setTimeZone(((DateTime)obj3).timezone);
            }
        }
        ((DateList) (obj5)).add(((net.fortuna.ical4j.model.Date) (obj3)));
        if (recur.monthList == null)
        {
            recur.monthList = new NumberList(1, 12, false);
        }
        if (recur.monthList.isEmpty())
        {
            obj3 = obj5;
        } else
        {
            DateList datelist = Recur.getDateListInstance(((DateList) (obj5)));
            for (obj5 = ((DateList) (obj5)).iterator(); ((Iterator) (obj5)).hasNext();)
            {
                obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
                Calendar calendar = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
                calendar.setMinimalDaysInFirstWeek(4);
                calendar.setFirstDayOfWeek(recur.calendarWeekStartDay);
                calendar.setLenient(true);
                calendar.setTime(((Date) (obj3)));
                if (recur.monthList == null)
                {
                    recur.monthList = new NumberList(1, 12, false);
                }
                Iterator iterator3 = recur.monthList.iterator();
                while (iterator3.hasNext()) 
                {
                    calendar.roll(2, ((Integer)iterator3.next()).intValue() - 1 - calendar.get(2));
                    obj3 = calendar.getTime();
                    Value value2 = datelist.type;
                    if (Value.DATE.equals(value2))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }

            obj3 = datelist;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 32)).append("Dates after BYMONTH processing: ").append(((String) (obj5))).toString());
        }
        if (recur.weekNoList == null)
        {
            recur.weekNoList = new NumberList(1, 53, true);
        }
        if (!recur.weekNoList.isEmpty())
        {
            DateList datelist1 = Recur.getDateListInstance(((DateList) (obj3)));
            for (obj5 = ((DateList) (obj3)).iterator(); ((Iterator) (obj5)).hasNext();)
            {
                obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
                Calendar calendar1 = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
                calendar1.setMinimalDaysInFirstWeek(4);
                calendar1.setFirstDayOfWeek(recur.calendarWeekStartDay);
                calendar1.setLenient(true);
                calendar1.setTime(((Date) (obj3)));
                if (recur.weekNoList == null)
                {
                    recur.weekNoList = new NumberList(1, 53, true);
                }
                Iterator iterator4 = recur.weekNoList.iterator();
                while (iterator4.hasNext()) 
                {
                    Object obj7 = (Integer)iterator4.next();
                    obj3 = calendar1.getTime();
                    int i1 = ((Integer) (obj7)).intValue();
                    if (i1 == 0 || i1 < -53 || i1 > 53)
                    {
                        throw new IllegalArgumentException(MessageFormat.format("Invalid week number [{0}]", new Object[] {
                            new Integer(i1)
                        }));
                    }
                    if (i1 <= 0)
                    {
                        Calendar calendar7 = Calendar.getInstance();
                        calendar7.setTime(((Date) (obj3)));
                        int j2 = calendar7.get(1);
                        obj3 = new ArrayList();
                        calendar7.set(3, 1);
                        for (; calendar7.get(1) == j2; calendar7.add(3, 1))
                        {
                            ((List) (obj3)).add(new Integer(calendar7.get(3)));
                        }

                        i1 = ((Integer)((List) (obj3)).get(i1 + ((List) (obj3)).size())).intValue();
                    }
                    calendar1.set(3, i1);
                    obj3 = calendar1.getTime();
                    obj7 = datelist1.type;
                    if (Value.DATE.equals(obj7))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist1.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }

            obj3 = datelist1;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 33)).append("Dates after BYWEEKNO processing: ").append(((String) (obj5))).toString());
        }
        if (recur.yearDayList == null)
        {
            recur.yearDayList = new NumberList(1, 366, true);
        }
        if (!recur.yearDayList.isEmpty())
        {
            DateList datelist2 = Recur.getDateListInstance(((DateList) (obj3)));
            for (obj5 = ((DateList) (obj3)).iterator(); ((Iterator) (obj5)).hasNext();)
            {
                obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
                Calendar calendar2 = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
                calendar2.setMinimalDaysInFirstWeek(4);
                calendar2.setFirstDayOfWeek(recur.calendarWeekStartDay);
                calendar2.setLenient(true);
                calendar2.setTime(((Date) (obj3)));
                if (recur.yearDayList == null)
                {
                    recur.yearDayList = new NumberList(1, 366, true);
                }
                Iterator iterator5 = recur.yearDayList.iterator();
                while (iterator5.hasNext()) 
                {
                    Object obj8 = (Integer)iterator5.next();
                    obj3 = calendar2.getTime();
                    int j1 = ((Integer) (obj8)).intValue();
                    if (j1 == 0 || j1 < -366 || j1 > 366)
                    {
                        throw new IllegalArgumentException(MessageFormat.format("Invalid year day [{0}]", new Object[] {
                            new Integer(j1)
                        }));
                    }
                    if (j1 <= 0)
                    {
                        Calendar calendar8 = Calendar.getInstance();
                        calendar8.setTime(((Date) (obj3)));
                        int k2 = calendar8.get(1);
                        obj3 = new ArrayList();
                        calendar8.set(6, 1);
                        for (; calendar8.get(1) == k2; calendar8.add(6, 1))
                        {
                            ((List) (obj3)).add(new Integer(calendar8.get(6)));
                        }

                        j1 = ((Integer)((List) (obj3)).get(j1 + ((List) (obj3)).size())).intValue();
                    }
                    calendar2.set(6, j1);
                    obj3 = calendar2.getTime();
                    obj8 = datelist2.type;
                    if (Value.DATE.equals(obj8))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist2.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }

            obj3 = datelist2;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 34)).append("Dates after BYYEARDAY processing: ").append(((String) (obj5))).toString());
        }
        obj5 = recur.getMonthDayVariants(((DateList) (obj3)));
        if (recur.log.isDebugEnabled())
        {
            obj3 = recur.log;
            obj4 = String.valueOf(obj5);
            ((Log) (obj3)).debug((new StringBuilder(String.valueOf(obj4).length() + 35)).append("Dates after BYMONTHDAY processing: ").append(((String) (obj4))).toString());
        }
        if (recur.dayList == null)
        {
            recur.dayList = new WeekDayList();
        }
        if (!recur.dayList.isEmpty()) goto _L9; else goto _L8
_L8:
        obj3 = obj5;
_L28:
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 30)).append("Dates after BYDAY processing: ").append(((String) (obj5))).toString());
        }
        if (recur.hourList == null)
        {
            recur.hourList = new NumberList(0, 23, false);
        }
        DateList datelist3;
        DateList datelist8;
        Object obj6;
        Iterator iterator10;
        net.fortuna.ical4j.model.Date date2;
        Iterator iterator11;
        Value value6;
        Calendar calendar9;
        int k1;
        int l2;
        if (!recur.hourList.isEmpty())
        {
            DateList datelist4 = Recur.getDateListInstance(((DateList) (obj3)));
            for (obj5 = ((DateList) (obj3)).iterator(); ((Iterator) (obj5)).hasNext();)
            {
                obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
                Calendar calendar3 = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
                calendar3.setMinimalDaysInFirstWeek(4);
                calendar3.setFirstDayOfWeek(recur.calendarWeekStartDay);
                calendar3.setLenient(true);
                calendar3.setTime(((Date) (obj3)));
                if (recur.hourList == null)
                {
                    recur.hourList = new NumberList(0, 23, false);
                }
                Iterator iterator6 = recur.hourList.iterator();
                while (iterator6.hasNext()) 
                {
                    calendar3.set(11, ((Integer)iterator6.next()).intValue());
                    obj3 = calendar3.getTime();
                    Value value3 = datelist4.type;
                    if (Value.DATE.equals(value3))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist4.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }

            obj3 = datelist4;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 31)).append("Dates after BYHOUR processing: ").append(((String) (obj5))).toString());
        }
        if (recur.minuteList == null)
        {
            recur.minuteList = new NumberList(0, 59, false);
        }
        if (!recur.minuteList.isEmpty())
        {
            DateList datelist5 = Recur.getDateListInstance(((DateList) (obj3)));
            for (obj5 = ((DateList) (obj3)).iterator(); ((Iterator) (obj5)).hasNext();)
            {
                obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
                Calendar calendar4 = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
                calendar4.setMinimalDaysInFirstWeek(4);
                calendar4.setFirstDayOfWeek(recur.calendarWeekStartDay);
                calendar4.setLenient(true);
                calendar4.setTime(((Date) (obj3)));
                if (recur.minuteList == null)
                {
                    recur.minuteList = new NumberList(0, 59, false);
                }
                Iterator iterator7 = recur.minuteList.iterator();
                while (iterator7.hasNext()) 
                {
                    calendar4.set(12, ((Integer)iterator7.next()).intValue());
                    obj3 = calendar4.getTime();
                    Value value4 = datelist5.type;
                    if (Value.DATE.equals(value4))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist5.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }

            obj3 = datelist5;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 33)).append("Dates after BYMINUTE processing: ").append(((String) (obj5))).toString());
        }
        if (recur.secondList == null)
        {
            recur.secondList = new NumberList(0, 59, false);
        }
        if (!recur.secondList.isEmpty()) goto _L11; else goto _L10
_L10:
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 33)).append("Dates after BYSECOND processing: ").append(((String) (obj5))).toString());
        }
        if (recur.setPosList == null)
        {
            recur.setPosList = new NumberList(1, 366, true);
        }
        DateList datelist6;
        Calendar calendar5;
        Iterator iterator8;
        Value value5;
        if (!recur.setPosList.isEmpty())
        {
            Collections.sort(((List) (obj3)));
            DateList datelist7 = Recur.getDateListInstance(((DateList) (obj3)));
            int l1 = ((DateList) (obj3)).size();
            if (recur.setPosList == null)
            {
                recur.setPosList = new NumberList(1, 366, true);
            }
            Iterator iterator2 = recur.setPosList.iterator();
            do
            {
                if (!iterator2.hasNext())
                {
                    break;
                }
                int i3 = ((Integer)iterator2.next()).intValue();
                if (i3 > 0 && i3 <= l1)
                {
                    datelist7.add(((DateList) (obj3)).get(i3 - 1));
                } else
                if (i3 < 0 && i3 >= -l1)
                {
                    datelist7.add(((DateList) (obj3)).get(i3 + l1));
                }
            } while (true);
            obj3 = datelist7;
        }
        if (recur.log.isDebugEnabled())
        {
            obj4 = recur.log;
            obj5 = String.valueOf(obj3);
            ((Log) (obj4)).debug((new StringBuilder(String.valueOf(obj5).length() + 31)).append("Dates after SETPOS processing: ").append(((String) (obj5))).toString());
        }
        if (((DateList) (obj3)).isEmpty()) goto _L13; else goto _L12
_L12:
        Collections.sort(((List) (obj3)));
        obj4 = ((DateList) (obj3)).iterator();
_L17:
        if (!((Iterator) (obj4)).hasNext()) goto _L15; else goto _L14
_L14:
        obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj4)).next();
        obj2 = obj3;
        if (((net.fortuna.ical4j.model.Date) (obj3)).before(datetime4)) goto _L17; else goto _L16
_L16:
        if (!((net.fortuna.ical4j.model.Date) (obj3)).before(datetime4) && ((net.fortuna.ical4j.model.Date) (obj3)).before(date1)) goto _L19; else goto _L18
_L18:
        k++;
        obj2 = obj3;
          goto _L17
_L9:
        datelist8 = Recur.getDateListInstance(((DateList) (obj5)));
        iterator10 = ((DateList) (obj5)).iterator();
_L21:
        if (!iterator10.hasNext())
        {
            break MISSING_BLOCK_LABEL_3847;
        }
        date2 = (net.fortuna.ical4j.model.Date)iterator10.next();
        if (recur.dayList == null)
        {
            recur.dayList = new WeekDayList();
        }
        iterator11 = recur.dayList.iterator();
_L24:
        while (iterator11.hasNext()) 
        {
label0:
            {
                obj6 = (WeekDay)iterator11.next();
                if (recur.yearDayList == null)
                {
                    recur.yearDayList = new NumberList(1, 366, true);
                }
                if (recur.yearDayList.isEmpty())
                {
                    if (recur.monthDayList == null)
                    {
                        recur.monthDayList = new NumberList(1, 31, true);
                    }
                    if (recur.monthDayList.isEmpty())
                    {
                        break label0;
                    }
                }
                obj3 = Dates.getCalendarInstance(date2);
                ((Calendar) (obj3)).setMinimalDaysInFirstWeek(4);
                ((Calendar) (obj3)).setFirstDayOfWeek(recur.calendarWeekStartDay);
                ((Calendar) (obj3)).setLenient(true);
                ((Calendar) (obj3)).setTime(date2);
                if (((WeekDay) (obj6)).equals(new WeekDay(WeekDay.getDay(((Calendar) (obj3)).get(7)), 0)))
                {
                    datelist8.add(date2);
                }
            }
        }
        if (true) goto _L21; else goto _L20
_L20:
        value6 = ((DateList) (obj5)).type;
        calendar9 = Dates.getCalendarInstance(date2);
        calendar9.setMinimalDaysInFirstWeek(4);
        calendar9.setFirstDayOfWeek(recur.calendarWeekStartDay);
        calendar9.setLenient(true);
        calendar9.setTime(date2);
        datelist3 = new DateList(value6);
        if (date2 instanceof DateTime)
        {
            if (((DateTime)date2).time.utc)
            {
                datelist3.setUtc(true);
            } else
            {
                datelist3.setTimeZone(((DateTime)date2).timezone);
            }
        }
        k1 = WeekDay.getCalendarDay(((WeekDay) (obj6)));
        if (k1 != -1) goto _L23; else goto _L22
_L22:
        obj3 = datelist3;
_L25:
        datelist8.addAll(((java.util.Collection) (obj3)));
          goto _L24
_L23:
        if ("DAILY".equals(recur.frequency))
        {
            if (calendar9.get(7) == k1)
            {
                obj3 = calendar9.getTime();
                if (Value.DATE.equals(value6))
                {
                    obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                } else
                {
                    obj3 = new DateTime(((Date) (obj3)));
                }
                datelist3.add(((net.fortuna.ical4j.model.Date) (obj3)));
            }
        } else
        {
label1:
            {
                if (!"WEEKLY".equals(recur.frequency))
                {
                    if (recur.weekNoList == null)
                    {
                        recur.weekNoList = new NumberList(1, 53, true);
                    }
                    if (recur.weekNoList.isEmpty())
                    {
                        break label1;
                    }
                }
                l2 = calendar9.get(3);
                calendar9.set(7, calendar9.getFirstDayOfWeek());
                for (; calendar9.get(7) != k1; calendar9.add(7, 1)) { }
                if (calendar9.get(3) == l2)
                {
                    obj3 = calendar9.getTime();
                    if (Value.DATE.equals(value6))
                    {
                        obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
                    } else
                    {
                        obj3 = new DateTime(((Date) (obj3)));
                    }
                    datelist3.add(((net.fortuna.ical4j.model.Date) (obj3)));
                }
            }
        }
_L26:
        k1 = ((WeekDay) (obj6)).offset;
        if (k1 == 0)
        {
            obj3 = datelist3;
        } else
        {
            obj6 = Recur.getDateListInstance(datelist3);
            l2 = datelist3.size();
            if (k1 < 0 && k1 >= -l2)
            {
                ((List) (obj6)).add(datelist3.get(k1 + l2));
                obj3 = obj6;
            } else
            {
                obj3 = obj6;
                if (k1 > 0)
                {
                    obj3 = obj6;
                    if (k1 <= l2)
                    {
                        ((List) (obj6)).add(datelist3.get(k1 - 1));
                        obj3 = obj6;
                    }
                }
            }
        }
          goto _L25
        if (!"MONTHLY".equals(recur.frequency))
        {
            if (recur.monthList == null)
            {
                recur.monthList = new NumberList(1, 12, false);
            }
            if (recur.monthList.isEmpty())
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        l2 = calendar9.get(2);
        calendar9.set(5, 1);
        for (; calendar9.get(7) != k1; calendar9.add(5, 1)) { }
        while (calendar9.get(2) == l2) 
        {
            obj3 = calendar9.getTime();
            if (Value.DATE.equals(value6))
            {
                obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
            } else
            {
                obj3 = new DateTime(((Date) (obj3)));
            }
            datelist3.add(((net.fortuna.ical4j.model.Date) (obj3)));
            calendar9.add(5, 7);
        }
          goto _L26
        if (!"YEARLY".equals(recur.frequency)) goto _L26; else goto _L27
_L27:
        l2 = calendar9.get(1);
        calendar9.set(6, 1);
        for (; calendar9.get(7) != k1; calendar9.add(6, 1)) { }
        while (calendar9.get(1) == l2) 
        {
            obj3 = calendar9.getTime();
            if (Value.DATE.equals(value6))
            {
                obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
            } else
            {
                obj3 = new DateTime(((Date) (obj3)));
            }
            datelist3.add(((net.fortuna.ical4j.model.Date) (obj3)));
            calendar9.add(6, 7);
        }
          goto _L26
        obj3 = datelist8;
          goto _L28
_L11:
        datelist6 = Recur.getDateListInstance(((DateList) (obj3)));
        obj5 = ((DateList) (obj3)).iterator();
_L30:
        obj3 = datelist6;
        if (!((Iterator) (obj5)).hasNext()) goto _L10; else goto _L29
_L29:
        obj3 = (net.fortuna.ical4j.model.Date)((Iterator) (obj5)).next();
        calendar5 = Dates.getCalendarInstance(((net.fortuna.ical4j.model.Date) (obj3)));
        calendar5.setMinimalDaysInFirstWeek(4);
        calendar5.setFirstDayOfWeek(recur.calendarWeekStartDay);
        calendar5.setLenient(true);
        calendar5.setTime(((Date) (obj3)));
        if (recur.secondList == null)
        {
            recur.secondList = new NumberList(0, 59, false);
        }
        iterator8 = recur.secondList.iterator();
        while (iterator8.hasNext()) 
        {
            calendar5.set(13, ((Integer)iterator8.next()).intValue());
            obj3 = calendar5.getTime();
            value5 = datelist6.type;
            if (Value.DATE.equals(value5))
            {
                obj3 = new net.fortuna.ical4j.model.Date(((Date) (obj3)));
            } else
            {
                obj3 = new DateTime(((Date) (obj3)));
            }
            datelist6.add(((net.fortuna.ical4j.model.Date) (obj3)));
        }
          goto _L30
          goto _L10
_L19:
        if (recur.count <= 0) goto _L32; else goto _L31
_L31:
        obj2 = obj3;
        if (datelist10.size() + k >= recur.count) goto _L15; else goto _L32
_L32:
        if (recur.until == null) goto _L34; else goto _L33
_L33:
        obj2 = obj3;
        if (((net.fortuna.ical4j.model.Date) (obj3)).after(recur.until)) goto _L17; else goto _L34
_L34:
        datelist10.add(((net.fortuna.ical4j.model.Date) (obj3)));
        obj2 = obj3;
          goto _L17
_L15:
        int i2;
        l = 0;
        i2 = k;
        obj3 = obj2;
_L37:
        DateTime datetime2;
        int j3;
        if (recur.interval > 0)
        {
            k = recur.interval;
        } else
        {
            k = 1;
        }
        calendar6.add(recur.calIncField, k);
        obj2 = obj3;
        k = i2;
          goto _L35
_L13:
        j3 = l + 1;
        l = j3;
        obj3 = obj2;
        i2 = k;
        if (Recur.maxIncrementCount <= 0) goto _L37; else goto _L36
_L36:
        l = j3;
        obj3 = obj2;
        i2 = k;
        if (j3 <= Recur.maxIncrementCount) goto _L37; else goto _L7
_L7:
        Collections.sort(datelist10);
        obj3 = datelist10.iterator();
        while (((Iterator) (obj3)).hasNext()) 
        {
            datetime2 = (DateTime)((Iterator) (obj3)).next();
            obj2 = new DateTime(true);
            ((Iso8601) (obj2)).setTime(datetime2.getTime() - ((TzOffsetFrom)super.properties.getProperty("TZOFFSETFROM")).offset.offset);
            if (!((DateTime) (obj2)).after(date) && ((DateTime) (obj2)).after(((Date) (obj1))))
            {
                obj1 = obj2;
            }
            datelist9.add(((net.fortuna.ical4j.model.Date) (obj2)));
        }
        if (true) goto _L39; else goto _L38
_L38:
        Collections.sort(datelist9);
        onsetsMillisec = new long[datelist9.size()];
        onsetsDates = new DateTime[onsetsMillisec.length];
        k = 0;
_L42:
        date = ((net.fortuna.ical4j.model.Date) (obj1));
        if (k >= onsetsMillisec.length) goto _L41; else goto _L40
_L40:
        date = (DateTime)datelist9.get(k);
        onsetsMillisec[k] = date.getTime();
        onsetsDates[k] = date;
        k++;
          goto _L42
          goto _L41
        obj2;
          goto _L43
    }

    public final void validate(boolean flag)
        throws ValidationException
    {
        PropertyValidator.assertOne("TZOFFSETFROM", super.properties);
        PropertyValidator.assertOne("TZOFFSETTO", super.properties);
        PropertyValidator.assertOne("DTSTART", super.properties);
        if (flag)
        {
            validateProperties();
        }
    }

    static 
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        UTC_FORMAT = simpledateformat;
        simpledateformat.setTimeZone(TimeZones.UTC_TIMEZONE);
        UTC_FORMAT.setLenient(false);
    }
}
