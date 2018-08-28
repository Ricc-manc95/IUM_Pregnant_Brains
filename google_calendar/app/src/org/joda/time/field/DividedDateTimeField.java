// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            DecoratedDateTimeField, ScaledDurationField, FieldUtils

public final class DividedDateTimeField extends DecoratedDateTimeField
{

    public final int iDivisor = 100;
    public final DurationField iDurationField;
    private final int iMax;
    private final int iMin;

    public DividedDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype, int i)
    {
        super(datetimefield, datetimefieldtype);
        DurationField durationfield = datetimefield.getDurationField();
        int j;
        if (durationfield == null)
        {
            iDurationField = null;
        } else
        {
            iDurationField = new ScaledDurationField(durationfield, datetimefieldtype.getDurationType(), 100);
        }
        i = datetimefield.getMinimumValue();
        if (i >= 0)
        {
            i /= 100;
        } else
        {
            i = (i + 1) / 100 - 1;
        }
        j = datetimefield.getMaximumValue();
        if (j >= 0)
        {
            j /= 100;
        } else
        {
            j = (j + 1) / 100 - 1;
        }
        iMin = i;
        iMax = j;
    }

    public final long add(long l, int i)
    {
        return super.iField.add(l, iDivisor * i);
    }

    public final long add(long l, long l1)
    {
        return super.iField.add(l, (long)iDivisor * l1);
    }

    public final int get(long l)
    {
        int i = super.iField.get(l);
        if (i >= 0)
        {
            return i / iDivisor;
        } else
        {
            return (i + 1) / iDivisor - 1;
        }
    }

    public final DurationField getDurationField()
    {
        return iDurationField;
    }

    public final int getMaximumValue()
    {
        return iMax;
    }

    public final int getMinimumValue()
    {
        return iMin;
    }

    public final long remainder(long l)
    {
        return set(l, get(super.iField.remainder(l)));
    }

    public final long roundFloor(long l)
    {
        DateTimeField datetimefield = super.iField;
        return datetimefield.roundFloor(datetimefield.set(l, get(l) * iDivisor));
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, iMin, iMax);
        int j = super.iField.get(l);
        if (j >= 0)
        {
            j %= iDivisor;
        } else
        {
            int k = iDivisor;
            j = (j + 1) % iDivisor + (k - 1);
        }
        return super.iField.set(l, j + iDivisor * i);
    }
}
