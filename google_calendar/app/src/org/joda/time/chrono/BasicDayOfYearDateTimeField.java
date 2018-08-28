// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            BasicChronology

final class BasicDayOfYearDateTimeField extends PreciseDurationDateTimeField
{

    private final BasicChronology iChronology;

    BasicDayOfYearDateTimeField(BasicChronology basicchronology, DurationField durationfield)
    {
        super(DateTimeFieldType.DAY_OF_YEAR_TYPE, durationfield);
        iChronology = basicchronology;
    }

    public final int get(long l)
    {
        BasicChronology basicchronology = iChronology;
        return (int)((l - basicchronology.getYearMillis(basicchronology.getYear(l))) / 0x5265c00L) + 1;
    }

    public final int getMaximumValue()
    {
        return BasicChronology.getDaysInYearMax();
    }

    public final int getMaximumValue(long l)
    {
        int i = iChronology.getYear(l);
        return !iChronology.isLeapYear(i) ? 365 : 366;
    }

    protected final int getMaximumValueForSet(long l, int i)
    {
        int j = 365;
        BasicChronology.getDaysInYearMax();
        if (i > 365 || i <= 0)
        {
            j = getMaximumValue(l);
        }
        return j;
    }

    public final int getMinimumValue()
    {
        return 1;
    }

    public final DurationField getRangeDurationField()
    {
        return iChronology.years();
    }
}
