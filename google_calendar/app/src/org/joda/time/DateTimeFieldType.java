// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.Serializable;

// Referenced classes of package org.joda.time:
//            DurationFieldType, Chronology, DateTimeField

public abstract class DateTimeFieldType
    implements Serializable
{

    public static final DateTimeFieldType CENTURY_OF_ERA_TYPE;
    public static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE;
    public static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE;
    public static final DateTimeFieldType DAY_OF_MONTH_TYPE;
    public static final DateTimeFieldType DAY_OF_WEEK_TYPE;
    public static final DateTimeFieldType DAY_OF_YEAR_TYPE;
    public static final DateTimeFieldType ERA_TYPE;
    public static final DateTimeFieldType HALFDAY_OF_DAY_TYPE;
    public static final DateTimeFieldType HOUR_OF_DAY_TYPE;
    public static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE;
    public static final DateTimeFieldType MILLIS_OF_DAY_TYPE;
    public static final DateTimeFieldType MILLIS_OF_SECOND_TYPE;
    public static final DateTimeFieldType MINUTE_OF_DAY_TYPE;
    public static final DateTimeFieldType MINUTE_OF_HOUR_TYPE;
    public static final DateTimeFieldType MONTH_OF_YEAR_TYPE;
    public static final DateTimeFieldType SECOND_OF_DAY_TYPE;
    public static final DateTimeFieldType SECOND_OF_MINUTE_TYPE;
    public static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE;
    public static final DateTimeFieldType WEEKYEAR_TYPE;
    public static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE;
    public static final DateTimeFieldType YEAR_OF_CENTURY_TYPE;
    public static final DateTimeFieldType YEAR_OF_ERA_TYPE;
    public static final DateTimeFieldType YEAR_TYPE;
    public static final long serialVersionUID = 0xffffd93ddb047c1aL;
    public final String iName;

    protected DateTimeFieldType(String s)
    {
        iName = s;
    }

    public abstract DurationFieldType getDurationType();

    public abstract DateTimeField getField(Chronology chronology);

    public abstract DurationFieldType getRangeDurationType();

    public String toString()
    {
        return iName;
    }

    static 
    {
        ERA_TYPE = new StandardDateTimeFieldType("era", (byte)1, DurationFieldType.ERAS_TYPE, null);
        YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType("yearOfEra", (byte)2, DurationFieldType.YEARS_TYPE, DurationFieldType.ERAS_TYPE);
        CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType("centuryOfEra", (byte)3, DurationFieldType.CENTURIES_TYPE, DurationFieldType.ERAS_TYPE);
        YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("yearOfCentury", (byte)4, DurationFieldType.YEARS_TYPE, DurationFieldType.CENTURIES_TYPE);
        YEAR_TYPE = new StandardDateTimeFieldType("year", (byte)5, DurationFieldType.YEARS_TYPE, null);
        DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType("dayOfYear", (byte)6, DurationFieldType.DAYS_TYPE, DurationFieldType.YEARS_TYPE);
        MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType("monthOfYear", (byte)7, DurationFieldType.MONTHS_TYPE, DurationFieldType.YEARS_TYPE);
        DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType("dayOfMonth", (byte)8, DurationFieldType.DAYS_TYPE, DurationFieldType.MONTHS_TYPE);
        WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("weekyearOfCentury", (byte)9, DurationFieldType.WEEKYEARS_TYPE, DurationFieldType.CENTURIES_TYPE);
        WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekyear", (byte)10, DurationFieldType.WEEKYEARS_TYPE, null);
        WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekOfWeekyear", (byte)11, DurationFieldType.WEEKS_TYPE, DurationFieldType.WEEKYEARS_TYPE);
        DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType("dayOfWeek", (byte)12, DurationFieldType.DAYS_TYPE, DurationFieldType.WEEKS_TYPE);
        HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType("halfdayOfDay", (byte)13, DurationFieldType.HALFDAYS_TYPE, DurationFieldType.DAYS_TYPE);
        HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("hourOfHalfday", (byte)14, DurationFieldType.HOURS_TYPE, DurationFieldType.HALFDAYS_TYPE);
        CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("clockhourOfHalfday", (byte)15, DurationFieldType.HOURS_TYPE, DurationFieldType.HALFDAYS_TYPE);
        CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("clockhourOfDay", (byte)16, DurationFieldType.HOURS_TYPE, DurationFieldType.DAYS_TYPE);
        HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("hourOfDay", (byte)17, DurationFieldType.HOURS_TYPE, DurationFieldType.DAYS_TYPE);
        MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType("minuteOfDay", (byte)18, DurationFieldType.MINUTES_TYPE, DurationFieldType.DAYS_TYPE);
        MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType("minuteOfHour", (byte)19, DurationFieldType.MINUTES_TYPE, DurationFieldType.HOURS_TYPE);
        SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType("secondOfDay", (byte)20, DurationFieldType.SECONDS_TYPE, DurationFieldType.DAYS_TYPE);
        SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType("secondOfMinute", (byte)21, DurationFieldType.SECONDS_TYPE, DurationFieldType.MINUTES_TYPE);
        MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType("millisOfDay", (byte)22, DurationFieldType.MILLIS_TYPE, DurationFieldType.DAYS_TYPE);
        MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType("millisOfSecond", (byte)23, DurationFieldType.MILLIS_TYPE, DurationFieldType.SECONDS_TYPE);
    }

    private class StandardDateTimeFieldType extends DateTimeFieldType
    {

        public static final long serialVersionUID = 0xfffff6f623875386L;
        private final byte iOrdinal;
        private final transient DurationFieldType iRangeType;
        private final transient DurationFieldType iUnitType;

        private final Object readResolve()
        {
            switch (iOrdinal)
            {
            default:
                return this;

            case 1: // '\001'
                return DateTimeFieldType.ERA_TYPE;

            case 2: // '\002'
                return DateTimeFieldType.YEAR_OF_ERA_TYPE;

            case 3: // '\003'
                return DateTimeFieldType.CENTURY_OF_ERA_TYPE;

            case 4: // '\004'
                return DateTimeFieldType.YEAR_OF_CENTURY_TYPE;

            case 5: // '\005'
                return DateTimeFieldType.YEAR_TYPE;

            case 6: // '\006'
                return DateTimeFieldType.DAY_OF_YEAR_TYPE;

            case 7: // '\007'
                return DateTimeFieldType.MONTH_OF_YEAR_TYPE;

            case 8: // '\b'
                return DateTimeFieldType.DAY_OF_MONTH_TYPE;

            case 9: // '\t'
                return DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE;

            case 10: // '\n'
                return DateTimeFieldType.WEEKYEAR_TYPE;

            case 11: // '\013'
                return DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE;

            case 12: // '\f'
                return DateTimeFieldType.DAY_OF_WEEK_TYPE;

            case 13: // '\r'
                return DateTimeFieldType.HALFDAY_OF_DAY_TYPE;

            case 14: // '\016'
                return DateTimeFieldType.HOUR_OF_HALFDAY_TYPE;

            case 15: // '\017'
                return DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE;

            case 16: // '\020'
                return DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE;

            case 17: // '\021'
                return DateTimeFieldType.HOUR_OF_DAY_TYPE;

            case 18: // '\022'
                return DateTimeFieldType.MINUTE_OF_DAY_TYPE;

            case 19: // '\023'
                return DateTimeFieldType.MINUTE_OF_HOUR_TYPE;

            case 20: // '\024'
                return DateTimeFieldType.SECOND_OF_DAY_TYPE;

            case 21: // '\025'
                return DateTimeFieldType.SECOND_OF_MINUTE_TYPE;

            case 22: // '\026'
                return DateTimeFieldType.MILLIS_OF_DAY_TYPE;

            case 23: // '\027'
                return DateTimeFieldType.MILLIS_OF_SECOND_TYPE;
            }
        }

        public final boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj instanceof StandardDateTimeFieldType)
                {
                    if (iOrdinal != ((StandardDateTimeFieldType)obj).iOrdinal)
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

        public final DurationFieldType getDurationType()
        {
            return iUnitType;
        }

        public final DateTimeField getField(Chronology chronology)
        {
            chronology = DateTimeUtils.getChronology(chronology);
            switch (iOrdinal)
            {
            default:
                throw new InternalError();

            case 1: // '\001'
                return chronology.era();

            case 2: // '\002'
                return chronology.yearOfEra();

            case 3: // '\003'
                return chronology.centuryOfEra();

            case 4: // '\004'
                return chronology.yearOfCentury();

            case 5: // '\005'
                return chronology.year();

            case 6: // '\006'
                return chronology.dayOfYear();

            case 7: // '\007'
                return chronology.monthOfYear();

            case 8: // '\b'
                return chronology.dayOfMonth();

            case 9: // '\t'
                return chronology.weekyearOfCentury();

            case 10: // '\n'
                return chronology.weekyear();

            case 11: // '\013'
                return chronology.weekOfWeekyear();

            case 12: // '\f'
                return chronology.dayOfWeek();

            case 13: // '\r'
                return chronology.halfdayOfDay();

            case 14: // '\016'
                return chronology.hourOfHalfday();

            case 15: // '\017'
                return chronology.clockhourOfHalfday();

            case 16: // '\020'
                return chronology.clockhourOfDay();

            case 17: // '\021'
                return chronology.hourOfDay();

            case 18: // '\022'
                return chronology.minuteOfDay();

            case 19: // '\023'
                return chronology.minuteOfHour();

            case 20: // '\024'
                return chronology.secondOfDay();

            case 21: // '\025'
                return chronology.secondOfMinute();

            case 22: // '\026'
                return chronology.millisOfDay();

            case 23: // '\027'
                return chronology.millisOfSecond();
            }
        }

        public final DurationFieldType getRangeDurationType()
        {
            return iRangeType;
        }

        public final int hashCode()
        {
            return 1 << iOrdinal;
        }

        StandardDateTimeFieldType(String s, byte byte0, DurationFieldType durationfieldtype, DurationFieldType durationfieldtype1)
        {
            super(s);
            iOrdinal = byte0;
            iUnitType = durationfieldtype;
            iRangeType = durationfieldtype1;
        }
    }

}
