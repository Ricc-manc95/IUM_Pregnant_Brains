// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.PreciseDateTimeField;
import org.joda.time.field.PreciseDurationField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.ZeroIsMaxDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            AssembledChronology, BasicYearDateTimeField, GJYearOfEraDateTimeField, GJEraDateTimeField, 
//            GJDayOfWeekDateTimeField, BasicDayOfMonthDateTimeField, BasicDayOfYearDateTimeField, GJMonthOfYearDateTimeField, 
//            BasicWeekyearDateTimeField, BasicWeekOfWeekyearDateTimeField

abstract class BasicChronology extends AssembledChronology
{

    private static final DateTimeField cClockhourOfDayField;
    private static final DateTimeField cClockhourOfHalfdayField;
    public static final DurationField cDaysField;
    private static final DateTimeField cHalfdayOfDayField = new HalfdayField();
    public static final DurationField cHalfdaysField;
    private static final DateTimeField cHourOfDayField;
    private static final DateTimeField cHourOfHalfdayField;
    private static final DurationField cHoursField;
    private static final DurationField cMillisField;
    private static final DateTimeField cMillisOfDayField;
    private static final DateTimeField cMillisOfSecondField;
    private static final DateTimeField cMinuteOfDayField;
    private static final DateTimeField cMinuteOfHourField;
    private static final DurationField cMinutesField;
    private static final DateTimeField cSecondOfDayField;
    private static final DateTimeField cSecondOfMinuteField;
    private static final DurationField cSecondsField;
    private static final DurationField cWeeksField;
    public static final long serialVersionUID = 0x72f3ed8da0b42f1fL;
    private final int iMinDaysInFirstWeek;
    private final transient YearInfo iYearInfoCache[] = new YearInfo[1024];

    BasicChronology(Chronology chronology, Object obj, int i)
    {
        super(chronology, obj);
        if (i <= 0 || i > 7)
        {
            throw new IllegalArgumentException((new StringBuilder(43)).append("Invalid min days in first week: ").append(i).toString());
        } else
        {
            iMinDaysInFirstWeek = i;
            return;
        }
    }

    static int getDayOfWeek(long l)
    {
        if (l >= 0L)
        {
            l /= 0x5265c00L;
        } else
        {
            long l1 = (l - 0x5265bffL) / 0x5265c00L;
            l = l1;
            if (l1 < -3L)
            {
                return (int)((l1 + 4L) % 7L) + 7;
            }
        }
        return (int)((l + 3L) % 7L) + 1;
    }

    static int getDaysInMonthMax()
    {
        return 31;
    }

    static int getDaysInYearMax()
    {
        return 366;
    }

    static int getMaxMonth()
    {
        return 12;
    }

    static int getMillisOfDay(long l)
    {
        if (l >= 0L)
        {
            return (int)(l % 0x5265c00L);
        } else
        {
            return 0x5265bff + (int)((1L + l) % 0x5265c00L);
        }
    }

    protected void assemble(AssembledChronology.Fields fields)
    {
        fields.millis = cMillisField;
        fields.seconds = cSecondsField;
        fields.minutes = cMinutesField;
        fields.hours = cHoursField;
        fields.halfdays = cHalfdaysField;
        fields.days = cDaysField;
        fields.weeks = cWeeksField;
        fields.millisOfSecond = cMillisOfSecondField;
        fields.millisOfDay = cMillisOfDayField;
        fields.secondOfMinute = cSecondOfMinuteField;
        fields.secondOfDay = cSecondOfDayField;
        fields.minuteOfHour = cMinuteOfHourField;
        fields.minuteOfDay = cMinuteOfDayField;
        fields.hourOfDay = cHourOfDayField;
        fields.hourOfHalfday = cHourOfHalfdayField;
        fields.clockhourOfDay = cClockhourOfDayField;
        fields.clockhourOfHalfday = cClockhourOfHalfdayField;
        fields.halfdayOfDay = cHalfdayOfDayField;
        fields.year = new BasicYearDateTimeField(this);
        fields.yearOfEra = new GJYearOfEraDateTimeField(fields.year, this);
        fields.centuryOfEra = new DividedDateTimeField(new OffsetDateTimeField(fields.yearOfEra, 99), DateTimeFieldType.CENTURY_OF_ERA_TYPE, 100);
        fields.yearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra), DateTimeFieldType.YEAR_OF_CENTURY_TYPE, 1);
        fields.era = new GJEraDateTimeField(this);
        fields.dayOfWeek = new GJDayOfWeekDateTimeField(this, fields.days);
        fields.dayOfMonth = new BasicDayOfMonthDateTimeField(this, fields.days);
        fields.dayOfYear = new BasicDayOfYearDateTimeField(this, fields.days);
        fields.monthOfYear = new GJMonthOfYearDateTimeField(this);
        fields.weekyear = new BasicWeekyearDateTimeField(this);
        fields.weekOfWeekyear = new BasicWeekOfWeekyearDateTimeField(this, fields.weeks);
        fields.weekyearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField(fields.weekyear, DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE, 100), DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE, 1);
        fields.years = fields.year.getDurationField();
        fields.centuries = fields.centuryOfEra.getDurationField();
        fields.months = fields.monthOfYear.getDurationField();
        fields.weekyears = fields.weekyear.getDurationField();
    }

    abstract long calculateFirstDayOfYearMillis(int i);

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj != null && getClass() == obj.getClass())
            {
                if (getMinimumDaysInFirstWeek() != ((BasicChronology) (obj = (BasicChronology)obj)).getMinimumDaysInFirstWeek() || !getZone().equals(((Chronology) (obj)).getZone()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    abstract long getApproxMillisAtEpochDividedByTwo();

    abstract long getAverageMillisPerMonth();

    abstract long getAverageMillisPerYear();

    abstract long getAverageMillisPerYearDividedByTwo();

    int getDaysInMonthMaxForSet(long l, int i)
    {
        i = getYear(l);
        return getDaysInYearMonth(i, getMonthOfYear(l, i));
    }

    abstract int getDaysInYearMonth(int i, int j);

    final long getFirstWeekOfYearMillis(int i)
    {
        YearInfo yearinfo;
label0:
        {
            YearInfo yearinfo1 = iYearInfoCache[i & 0x3ff];
            if (yearinfo1 != null)
            {
                yearinfo = yearinfo1;
                if (yearinfo1.iYear == i)
                {
                    break label0;
                }
            }
            yearinfo = new YearInfo(i, calculateFirstDayOfYearMillis(i));
            iYearInfoCache[i & 0x3ff] = yearinfo;
        }
        long l = yearinfo.iFirstDayMillis;
        i = getDayOfWeek(l);
        if (i > 8 - iMinDaysInFirstWeek)
        {
            return l + (long)(8 - i) * 0x5265c00L;
        } else
        {
            return l - (long)(i - 1) * 0x5265c00L;
        }
    }

    abstract int getMaxYear();

    abstract int getMinYear();

    public int getMinimumDaysInFirstWeek()
    {
        return iMinDaysInFirstWeek;
    }

    abstract int getMonthOfYear(long l, int i);

    abstract long getTotalMillisByYearMonth(int i, int j);

    final int getWeekOfWeekyear(long l, int i)
    {
        long l1 = getFirstWeekOfYearMillis(i);
        if (l < l1)
        {
            i--;
            l = getFirstWeekOfYearMillis(i);
            return (int)((getFirstWeekOfYearMillis(i + 1) - l) / 0x240c8400L);
        }
        if (l >= getFirstWeekOfYearMillis(i + 1))
        {
            return 1;
        } else
        {
            return (int)((l - l1) / 0x240c8400L) + 1;
        }
    }

    final int getWeekyear(long l)
    {
        int i = getYear(l);
        int j = getWeekOfWeekyear(l, i);
        if (j == 1)
        {
            i = getYear(0x240c8400L + l);
        } else
        if (j > 51)
        {
            return getYear(l - 0x48190800L);
        }
        return i;
    }

    final int getYear(long l)
    {
        long l3 = 0x757b12c00L;
        long l5 = getAverageMillisPerYearDividedByTwo();
        long l4 = (l >> 1) + getApproxMillisAtEpochDividedByTwo();
        long l1 = l4;
        if (l4 < 0L)
        {
            l1 = (l4 - l5) + 1L;
        }
        int j = (int)(l1 / l5);
        l4 = getYearMillis(j);
        l1 = l - l4;
        int i;
        if (l1 < 0L)
        {
            i = j - 1;
        } else
        {
            i = j;
            if (l1 >= 0x757b12c00L)
            {
                long l2 = l3;
                if (isLeapYear(j))
                {
                    l2 = 0x75cd78800L;
                }
                i = j;
                if (l2 + l4 <= l)
                {
                    return j + 1;
                }
            }
        }
        return i;
    }

    final long getYearMillis(int i)
    {
        YearInfo yearinfo;
label0:
        {
            YearInfo yearinfo1 = iYearInfoCache[i & 0x3ff];
            if (yearinfo1 != null)
            {
                yearinfo = yearinfo1;
                if (yearinfo1.iYear == i)
                {
                    break label0;
                }
            }
            yearinfo = new YearInfo(i, calculateFirstDayOfYearMillis(i));
            iYearInfoCache[i & 0x3ff] = yearinfo;
        }
        return yearinfo.iFirstDayMillis;
    }

    public DateTimeZone getZone()
    {
        Chronology chronology = super.iBase;
        if (chronology != null)
        {
            return chronology.getZone();
        } else
        {
            return DateTimeZone.UTC;
        }
    }

    public int hashCode()
    {
        return getClass().getName().hashCode() * 11 + getZone().hashCode() + getMinimumDaysInFirstWeek();
    }

    abstract boolean isLeapYear(int i);

    abstract long setYear(long l, int i);

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(60);
        String s = getClass().getName();
        int i = s.lastIndexOf('.');
        Object obj = s;
        if (i >= 0)
        {
            obj = s.substring(i + 1);
        }
        stringbuilder.append(((String) (obj)));
        stringbuilder.append('[');
        obj = getZone();
        if (obj != null)
        {
            stringbuilder.append(((DateTimeZone) (obj)).iID);
        }
        if (getMinimumDaysInFirstWeek() != 4)
        {
            stringbuilder.append(",mdfw=");
            stringbuilder.append(getMinimumDaysInFirstWeek());
        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    static 
    {
        cMillisField = MillisDurationField.INSTANCE;
        cSecondsField = new PreciseDurationField(DurationFieldType.SECONDS_TYPE, 1000L);
        cMinutesField = new PreciseDurationField(DurationFieldType.MINUTES_TYPE, 60000L);
        cHoursField = new PreciseDurationField(DurationFieldType.HOURS_TYPE, 0x36ee80L);
        cHalfdaysField = new PreciseDurationField(DurationFieldType.HALFDAYS_TYPE, 0x2932e00L);
        cDaysField = new PreciseDurationField(DurationFieldType.DAYS_TYPE, 0x5265c00L);
        cWeeksField = new PreciseDurationField(DurationFieldType.WEEKS_TYPE, 0x240c8400L);
        cMillisOfSecondField = new PreciseDateTimeField(DateTimeFieldType.MILLIS_OF_SECOND_TYPE, cMillisField, cSecondsField);
        cMillisOfDayField = new PreciseDateTimeField(DateTimeFieldType.MILLIS_OF_DAY_TYPE, cMillisField, cDaysField);
        cSecondOfMinuteField = new PreciseDateTimeField(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, cSecondsField, cMinutesField);
        cSecondOfDayField = new PreciseDateTimeField(DateTimeFieldType.SECOND_OF_DAY_TYPE, cSecondsField, cDaysField);
        cMinuteOfHourField = new PreciseDateTimeField(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, cMinutesField, cHoursField);
        cMinuteOfDayField = new PreciseDateTimeField(DateTimeFieldType.MINUTE_OF_DAY_TYPE, cMinutesField, cDaysField);
        cHourOfDayField = new PreciseDateTimeField(DateTimeFieldType.HOUR_OF_DAY_TYPE, cHoursField, cDaysField);
        cHourOfHalfdayField = new PreciseDateTimeField(DateTimeFieldType.HOUR_OF_HALFDAY_TYPE, cHoursField, cHalfdaysField);
        cClockhourOfDayField = new ZeroIsMaxDateTimeField(cHourOfDayField, DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE);
        cClockhourOfHalfdayField = new ZeroIsMaxDateTimeField(cHourOfHalfdayField, DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE);
    }

    private class YearInfo
    {

        public final long iFirstDayMillis;
        public final int iYear;

        YearInfo(int i, long l)
        {
            iYear = i;
            iFirstDayMillis = l;
        }
    }


    private class HalfdayField extends PreciseDateTimeField
    {

        public final String getAsText(int i, Locale locale)
        {
            return GJLocaleSymbols.forLocale(locale).iHalfday[i];
        }

        public final int getMaximumTextLength(Locale locale)
        {
            return GJLocaleSymbols.forLocale(locale).iMaxHalfdayLength;
        }

        public final long set(long l, String s, Locale locale)
        {
            locale = GJLocaleSymbols.forLocale(locale).iHalfday;
            int i = locale.length;
            do
            {
                int j = i - 1;
                if (j >= 0)
                {
                    i = j;
                    if (locale[j].equalsIgnoreCase(s))
                    {
                        return set(l, j);
                    }
                } else
                {
                    throw new IllegalFieldValueException(DateTimeFieldType.HALFDAY_OF_DAY_TYPE, s);
                }
            } while (true);
        }

        HalfdayField()
        {
            super(DateTimeFieldType.HALFDAY_OF_DAY_TYPE, BasicChronology.cHalfdaysField, BasicChronology.cDaysField);
        }
    }

}
