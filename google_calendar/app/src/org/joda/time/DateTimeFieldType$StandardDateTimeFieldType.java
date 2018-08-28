// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;


// Referenced classes of package org.joda.time:
//            DateTimeFieldType, DateTimeUtils, Chronology, DurationFieldType, 
//            DateTimeField

final class iRangeType extends DateTimeFieldType
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
            if (obj instanceof iOrdinal)
            {
                if (iOrdinal != ((iOrdinal)obj).iOrdinal)
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

    (String s, byte byte0, DurationFieldType durationfieldtype, DurationFieldType durationfieldtype1)
    {
        super(s);
        iOrdinal = byte0;
        iUnitType = durationfieldtype;
        iRangeType = durationfieldtype1;
    }
}
