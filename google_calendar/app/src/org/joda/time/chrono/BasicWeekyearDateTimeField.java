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

final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField
{

    private final BasicChronology iChronology;

    BasicWeekyearDateTimeField(BasicChronology basicchronology)
    {
        super(DateTimeFieldType.WEEKYEAR_TYPE, basicchronology.getAverageMillisPerYear());
        iChronology = basicchronology;
    }

    public final long add(long l, int i)
    {
        if (i == 0)
        {
            return l;
        } else
        {
            return set(l, get(l) + i);
        }
    }

    public final long add(long l, long l1)
    {
        return add(l, FieldUtils.safeToInt(l1));
    }

    public final int get(long l)
    {
        return iChronology.getWeekyear(l);
    }

    public final DurationField getLeapDurationField()
    {
        return iChronology.weeks();
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
        BasicChronology basicchronology = iChronology;
        int i = iChronology.getWeekyear(l);
        l = basicchronology.getFirstWeekOfYearMillis(i);
        return (int)((basicchronology.getFirstWeekOfYearMillis(i + 1) - l) / 0x240c8400L) > 52;
    }

    public final long remainder(long l)
    {
        return l - roundFloor(l);
    }

    public final long roundFloor(long l)
    {
        long l1 = iChronology.weekOfWeekyear().roundFloor(l);
        BasicChronology basicchronology = iChronology;
        int i = basicchronology.getWeekOfWeekyear(l1, basicchronology.getYear(l1));
        l = l1;
        if (i > 1)
        {
            l = l1 - (long)(i - 1) * 0x240c8400L;
        }
        return l;
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, Math.abs(i), iChronology.getMinYear(), iChronology.getMaxYear());
        int j = get(l);
        if (j == i)
        {
            return l;
        }
        int i1 = BasicChronology.getDayOfWeek(l);
        BasicChronology basicchronology = iChronology;
        long l1 = basicchronology.getFirstWeekOfYearMillis(j);
        j = (int)((basicchronology.getFirstWeekOfYearMillis(j + 1) - l1) / 0x240c8400L);
        basicchronology = iChronology;
        l1 = basicchronology.getFirstWeekOfYearMillis(i);
        int k = (int)((basicchronology.getFirstWeekOfYearMillis(i + 1) - l1) / 0x240c8400L);
        if (k < j)
        {
            j = k;
        }
        basicchronology = iChronology;
        k = basicchronology.getWeekOfWeekyear(l, basicchronology.getYear(l));
        if (k <= j)
        {
            j = k;
        }
        l1 = iChronology.setYear(l, i);
        k = get(l1);
        if (k >= i) goto _L2; else goto _L1
_L1:
        l = l1 + 0x240c8400L;
_L4:
        basicchronology = iChronology;
        l1 = j - basicchronology.getWeekOfWeekyear(l, basicchronology.getYear(l));
        return iChronology.dayOfWeek().set(l1 * 0x240c8400L + l, i1);
_L2:
        l = l1;
        if (k > i)
        {
            l = l1 - 0x240c8400L;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
