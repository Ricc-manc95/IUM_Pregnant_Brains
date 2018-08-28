// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.Locale;
import java.util.TreeMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.PreciseDurationDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            GJLocaleSymbols, BasicChronology

final class GJDayOfWeekDateTimeField extends PreciseDurationDateTimeField
{

    private final BasicChronology iChronology;

    GJDayOfWeekDateTimeField(BasicChronology basicchronology, DurationField durationfield)
    {
        super(DateTimeFieldType.DAY_OF_WEEK_TYPE, durationfield);
        iChronology = basicchronology;
    }

    protected final int convertText(String s, Locale locale)
    {
        locale = (Integer)GJLocaleSymbols.forLocale(locale).iParseDaysOfWeek.get(s);
        if (locale != null)
        {
            return locale.intValue();
        } else
        {
            throw new IllegalFieldValueException(DateTimeFieldType.DAY_OF_WEEK_TYPE, s);
        }
    }

    public final int get(long l)
    {
        return BasicChronology.getDayOfWeek(l);
    }

    public final String getAsShortText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iShortDaysOfWeek[i];
    }

    public final String getAsText(int i, Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iDaysOfWeek[i];
    }

    public final int getMaximumTextLength(Locale locale)
    {
        return GJLocaleSymbols.forLocale(locale).iMaxDayOfWeekLength;
    }

    public final int getMaximumValue()
    {
        return 7;
    }

    public final int getMinimumValue()
    {
        return 1;
    }

    public final DurationField getRangeDurationField()
    {
        return iChronology.weeks();
    }
}
