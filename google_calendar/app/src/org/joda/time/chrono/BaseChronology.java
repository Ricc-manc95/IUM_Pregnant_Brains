// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public abstract class BaseChronology extends Chronology
    implements Serializable
{

    public static final long serialVersionUID = 0x9a8a961c50f00a64L;

    public BaseChronology()
    {
    }

    public DurationField centuries()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.CENTURIES_TYPE);
    }

    public DateTimeField centuryOfEra()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.CENTURY_OF_ERA_TYPE, centuries());
    }

    public DateTimeField clockhourOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE, hours());
    }

    public DateTimeField clockhourOfHalfday()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE, hours());
    }

    public DateTimeField dayOfMonth()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.DAY_OF_MONTH_TYPE, days());
    }

    public DateTimeField dayOfWeek()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.DAY_OF_WEEK_TYPE, days());
    }

    public DateTimeField dayOfYear()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.DAY_OF_YEAR_TYPE, days());
    }

    public DurationField days()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.DAYS_TYPE);
    }

    public DateTimeField era()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.ERA_TYPE, eras());
    }

    public DurationField eras()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.ERAS_TYPE);
    }

    public DateTimeField halfdayOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.HALFDAY_OF_DAY_TYPE, halfdays());
    }

    public DurationField halfdays()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.HALFDAYS_TYPE);
    }

    public DateTimeField hourOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.HOUR_OF_DAY_TYPE, hours());
    }

    public DateTimeField hourOfHalfday()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.HOUR_OF_HALFDAY_TYPE, hours());
    }

    public DurationField hours()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.HOURS_TYPE);
    }

    public DurationField millis()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.MILLIS_TYPE);
    }

    public DateTimeField millisOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.MILLIS_OF_DAY_TYPE, millis());
    }

    public DateTimeField millisOfSecond()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.MILLIS_OF_SECOND_TYPE, millis());
    }

    public DateTimeField minuteOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.MINUTE_OF_DAY_TYPE, minutes());
    }

    public DateTimeField minuteOfHour()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, minutes());
    }

    public DurationField minutes()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.MINUTES_TYPE);
    }

    public DateTimeField monthOfYear()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.MONTH_OF_YEAR_TYPE, months());
    }

    public DurationField months()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.MONTHS_TYPE);
    }

    public DateTimeField secondOfDay()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.SECOND_OF_DAY_TYPE, seconds());
    }

    public DateTimeField secondOfMinute()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, seconds());
    }

    public DurationField seconds()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.SECONDS_TYPE);
    }

    public DateTimeField weekOfWeekyear()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, weeks());
    }

    public DurationField weeks()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.WEEKS_TYPE);
    }

    public DateTimeField weekyear()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.WEEKYEAR_TYPE, weekyears());
    }

    public DateTimeField weekyearOfCentury()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE, weekyears());
    }

    public DurationField weekyears()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.WEEKYEARS_TYPE);
    }

    public DateTimeField year()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.YEAR_TYPE, years());
    }

    public DateTimeField yearOfCentury()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.YEAR_OF_CENTURY_TYPE, years());
    }

    public DateTimeField yearOfEra()
    {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.YEAR_OF_ERA_TYPE, years());
    }

    public DurationField years()
    {
        return UnsupportedDurationField.getInstance(DurationFieldType.YEARS_TYPE);
    }
}
