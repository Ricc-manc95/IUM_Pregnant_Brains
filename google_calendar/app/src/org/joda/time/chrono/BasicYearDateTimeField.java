// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

// Referenced classes of package org.joda.time.chrono:
//            BasicChronology

final class BasicYearDateTimeField extends ImpreciseDateTimeField
{

    private final BasicChronology iChronology;

    BasicYearDateTimeField(BasicChronology basicchronology)
    {
        super(DateTimeFieldType.YEAR_TYPE, basicchronology.getAverageMillisPerYear());
        iChronology = basicchronology;
    }

    public final long add(long l, int i)
    {
        if (i == 0)
        {
            return l;
        }
        int j = get(l);
        int k = j + i;
        if ((j ^ k) < 0 && (j ^ i) >= 0)
        {
            throw new ArithmeticException((new StringBuilder(61)).append("The calculation caused an overflow: ").append(j).append(" + ").append(i).toString());
        } else
        {
            return set(l, k);
        }
    }

    public final long add(long l, long l1)
    {
        return add(l, FieldUtils.safeToInt(l1));
    }

    public final int get(long l)
    {
        return iChronology.getYear(l);
    }

    public final DurationField getLeapDurationField()
    {
        return iChronology.days();
    }

    public final int getMaximumValue()
    {
        return iChronology.getMaxYear();
    }

    public final int getMinimumValue()
    {
        return iChronology.getMinYear();
    }

    public final DurationField getRangeDurationField()
    {
        return null;
    }

    public final boolean isLeap(long l)
    {
        return iChronology.isLeapYear(get(l));
    }

    public final long remainder(long l)
    {
        return l - roundFloor(l);
    }

    public final long roundFloor(long l)
    {
        return iChronology.getYearMillis(get(l));
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, iChronology.getMinYear(), iChronology.getMaxYear());
        return iChronology.setYear(l, i);
    }
}
