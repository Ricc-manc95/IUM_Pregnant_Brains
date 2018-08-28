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

final class BasicWeekOfWeekyearDateTimeField extends PreciseDurationDateTimeField
{

    private final BasicChronology iChronology;

    BasicWeekOfWeekyearDateTimeField(BasicChronology basicchronology, DurationField durationfield)
    {
        super(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, durationfield);
        iChronology = basicchronology;
    }

    public final int get(long l)
    {
        BasicChronology basicchronology = iChronology;
        return basicchronology.getWeekOfWeekyear(l, basicchronology.getYear(l));
    }

    public final int getMaximumValue()
    {
        return 53;
    }

    public final int getMaximumValue(long l)
    {
        int i = iChronology.getWeekyear(l);
        BasicChronology basicchronology = iChronology;
        l = basicchronology.getFirstWeekOfYearMillis(i);
        return (int)((basicchronology.getFirstWeekOfYearMillis(i + 1) - l) / 0x240c8400L);
    }

    protected final int getMaximumValueForSet(long l, int i)
    {
        int j = 52;
        if (i > 52)
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
        return iChronology.weekyears();
    }

    public final long remainder(long l)
    {
        return super.remainder(0xf731400L + l);
    }

    public final long roundFloor(long l)
    {
        return super.roundFloor(l + 0xf731400L) - 0xf731400L;
    }
}
