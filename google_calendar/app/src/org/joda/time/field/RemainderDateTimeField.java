// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            DecoratedDateTimeField, ScaledDurationField, DividedDateTimeField, FieldUtils

public final class RemainderDateTimeField extends DecoratedDateTimeField
{

    private final int iDivisor;
    private final DurationField iRangeField;

    public RemainderDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype, int i)
    {
        super(datetimefield, datetimefieldtype);
        datetimefield = datetimefield.getDurationField();
        if (datetimefield == null)
        {
            iRangeField = null;
        } else
        {
            iRangeField = new ScaledDurationField(datetimefield, datetimefieldtype.getRangeDurationType(), 100);
        }
        iDivisor = 100;
    }

    public RemainderDateTimeField(DividedDateTimeField divideddatetimefield)
    {
        this(divideddatetimefield, divideddatetimefield.getType());
    }

    public RemainderDateTimeField(DividedDateTimeField divideddatetimefield, DateTimeFieldType datetimefieldtype)
    {
        super(((DecoratedDateTimeField) (divideddatetimefield)).iField, datetimefieldtype);
        iDivisor = divideddatetimefield.iDivisor;
        iRangeField = divideddatetimefield.iDurationField;
    }

    public final int get(long l)
    {
        int i = super.iField.get(l);
        if (i >= 0)
        {
            return i % iDivisor;
        } else
        {
            int j = iDivisor;
            return (i + 1) % iDivisor + (j - 1);
        }
    }

    public final int getMaximumValue()
    {
        return iDivisor - 1;
    }

    public final int getMinimumValue()
    {
        return 0;
    }

    public final DurationField getRangeDurationField()
    {
        return iRangeField;
    }

    public final long remainder(long l)
    {
        return super.iField.remainder(l);
    }

    public final long roundFloor(long l)
    {
        return super.iField.roundFloor(l);
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, 0, iDivisor - 1);
        int j = super.iField.get(l);
        if (j >= 0)
        {
            j /= iDivisor;
        } else
        {
            j = (j + 1) / iDivisor - 1;
        }
        return super.iField.set(l, j * iDivisor + i);
    }
}
