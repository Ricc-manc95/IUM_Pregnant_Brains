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

class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField
{

    private final BasicChronology iChronology;
    private final int iLeapMonth = 2;
    private final int iMax = BasicChronology.getMaxMonth();

    BasicMonthOfYearDateTimeField(BasicChronology basicchronology, int i)
    {
        super(DateTimeFieldType.MONTH_OF_YEAR_TYPE, basicchronology.getAverageMillisPerMonth());
        iChronology = basicchronology;
    }

    public final long add(long l, int i)
    {
        int j;
        int l1;
        int i2;
        long l2;
        if (i == 0)
        {
            return l;
        }
        l2 = BasicChronology.getMillisOfDay(l);
        l1 = iChronology.getYear(l);
        i2 = iChronology.getMonthOfYear(l, l1);
        j = (i2 - 1) + i;
        if (j < 0) goto _L2; else goto _L1
_L1:
        i = j / iMax + l1;
        j = j % iMax + 1;
_L4:
        BasicChronology basicchronology = iChronology;
        long l3 = basicchronology.getYearMillis(l1);
        int k = (int)((l - (basicchronology.getTotalMillisByYearMonth(l1, i2) + l3)) / 0x5265c00L) + 1;
        int j1 = iChronology.getDaysInYearMonth(i, j);
        if (k > j1)
        {
            k = j1;
        }
        basicchronology = iChronology;
        l = basicchronology.getYearMillis(i);
        return basicchronology.getTotalMillisByYearMonth(i, j) + l + (long)(k - 1) * 0x5265c00L + l2;
_L2:
        int i1 = (j / iMax + l1) - 1;
        j = Math.abs(j) % iMax;
        i = j;
        if (j == 0)
        {
            i = iMax;
        }
        int k1 = (iMax - i) + 1;
        j = k1;
        i = i1;
        if (k1 == 1)
        {
            i = i1 + 1;
            j = k1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final long add(long l, long l1)
    {
        int j2;
        int k2;
        long l3;
        long l6;
        int i = (int)l1;
        if ((long)i == l1)
        {
            return add(l, i);
        }
        l6 = BasicChronology.getMillisOfDay(l);
        j2 = iChronology.getYear(l);
        k2 = iChronology.getMonthOfYear(l, j2);
        l3 = (long)(k2 - 1) + l1;
        if (l3 < 0L) goto _L2; else goto _L1
_L1:
        long l2;
        l2 = (long)j2 + l3 / (long)iMax;
        l3 = l3 % (long)iMax + 1L;
_L4:
        if (l2 < (long)iChronology.getMinYear() || l2 > (long)iChronology.getMaxYear())
        {
            throw new IllegalArgumentException((new StringBuilder(58)).append("Magnitude of add amount is too large: ").append(l1).toString());
        }
        break; /* Loop/switch isn't completed */
_L2:
        long l4 = ((long)j2 + l3 / (long)iMax) - 1L;
        int i1 = (int)(Math.abs(l3) % (long)iMax);
        int j = i1;
        if (i1 == 0)
        {
            j = iMax;
        }
        long l5 = (iMax - j) + 1;
        l3 = l5;
        l2 = l4;
        if (l5 == 1L)
        {
            l2 = l4 + 1L;
            l3 = l5;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int k1 = (int)l2;
        int i2 = (int)l3;
        BasicChronology basicchronology = iChronology;
        l1 = basicchronology.getYearMillis(j2);
        int k = (int)((l - (basicchronology.getTotalMillisByYearMonth(j2, k2) + l1)) / 0x5265c00L) + 1;
        int j1 = iChronology.getDaysInYearMonth(k1, i2);
        if (k > j1)
        {
            k = j1;
        }
        basicchronology = iChronology;
        l = basicchronology.getYearMillis(k1);
        l1 = basicchronology.getTotalMillisByYearMonth(k1, i2);
        return (long)(k - 1) * 0x5265c00L + (l1 + l) + l6;
    }

    public final int get(long l)
    {
        BasicChronology basicchronology = iChronology;
        return basicchronology.getMonthOfYear(l, basicchronology.getYear(l));
    }

    public final DurationField getLeapDurationField()
    {
        return iChronology.days();
    }

    public final int getMaximumValue()
    {
        return iMax;
    }

    public final int getMinimumValue()
    {
        return 1;
    }

    public final DurationField getRangeDurationField()
    {
        return iChronology.years();
    }

    public final boolean isLeap(long l)
    {
        boolean flag1 = false;
        int i = iChronology.getYear(l);
        boolean flag = flag1;
        if (iChronology.isLeapYear(i))
        {
            flag = flag1;
            if (iChronology.getMonthOfYear(l, i) == iLeapMonth)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final long remainder(long l)
    {
        return l - roundFloor(l);
    }

    public final long roundFloor(long l)
    {
        int i = iChronology.getYear(l);
        int j = iChronology.getMonthOfYear(l, i);
        BasicChronology basicchronology = iChronology;
        l = basicchronology.getYearMillis(i);
        return basicchronology.getTotalMillisByYearMonth(i, j) + l;
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, 1, iMax);
        int i1 = iChronology.getYear(l);
        BasicChronology basicchronology = iChronology;
        int j = basicchronology.getMonthOfYear(l, i1);
        long l1 = basicchronology.getYearMillis(i1);
        j = (int)((l - (basicchronology.getTotalMillisByYearMonth(i1, j) + l1)) / 0x5265c00L) + 1;
        int k = iChronology.getDaysInYearMonth(i1, i);
        if (j > k)
        {
            j = k;
        }
        basicchronology = iChronology;
        l1 = basicchronology.getYearMillis(i1);
        long l2 = basicchronology.getTotalMillisByYearMonth(i1, i);
        return (long)(j - 1) * 0x5265c00L + (l2 + l1) + (long)BasicChronology.getMillisOfDay(l);
    }
}
