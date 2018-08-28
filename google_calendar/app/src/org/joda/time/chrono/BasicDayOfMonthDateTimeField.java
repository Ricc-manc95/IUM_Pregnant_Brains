// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            BasicChronology

final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField
{

    private final BasicChronology iChronology;

    BasicDayOfMonthDateTimeField(BasicChronology basicchronology, DurationField durationfield)
    {
        super(DateTimeFieldType.DAY_OF_MONTH_TYPE, durationfield);
        iChronology = basicchronology;
    }

    public final int get(long l)
    {
        BasicChronology basicchronology = iChronology;
        int i = basicchronology.getYear(l);
        int j = basicchronology.getMonthOfYear(l, i);
        long l1 = basicchronology.getYearMillis(i);
        return (int)((l - (basicchronology.getTotalMillisByYearMonth(i, j) + l1)) / 0x5265c00L) + 1;
    }

    public final int getMaximumValue()
    {
        return BasicChronology.getDaysInMonthMax();
    }

    public final int getMaximumValue(long l)
    {
        BasicChronology basicchronology = iChronology;
        int i = basicchronology.getYear(l);
        return basicchronology.getDaysInYearMonth(i, basicchronology.getMonthOfYear(l, i));
    }

    protected final int getMaximumValueForSet(long l, int i)
    {
        return iChronology.getDaysInMonthMaxForSet(l, i);
    }

    public final int getMinimumValue()
    {
        return 1;
    }

    public final DurationField getRangeDurationField()
    {
        return iChronology.months();
    }
}
