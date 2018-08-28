// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            PreciseDurationDateTimeField, FieldUtils

public class PreciseDateTimeField extends PreciseDurationDateTimeField
{

    private final int iRange;
    private final DurationField iRangeField;

    public PreciseDateTimeField(DateTimeFieldType datetimefieldtype, DurationField durationfield, DurationField durationfield1)
    {
        super(datetimefieldtype, durationfield);
        if (!durationfield1.isPrecise())
        {
            throw new IllegalArgumentException("Range duration field must be precise");
        }
        iRange = (int)(durationfield1.getUnitMillis() / super.iUnitMillis);
        if (iRange < 2)
        {
            throw new IllegalArgumentException("The effective range must be at least 2");
        } else
        {
            iRangeField = durationfield1;
            return;
        }
    }

    public final int get(long l)
    {
        if (l >= 0L)
        {
            return (int)((l / super.iUnitMillis) % (long)iRange);
        } else
        {
            return (iRange - 1) + (int)(((1L + l) / super.iUnitMillis) % (long)iRange);
        }
    }

    public final int getMaximumValue()
    {
        return iRange - 1;
    }

    public final DurationField getRangeDurationField()
    {
        return iRangeField;
    }

    public final long set(long l, int i)
    {
        FieldUtils.verifyValueBounds(this, i, getMinimumValue(), getMaximumValue());
        return (long)(i - get(l)) * iUnitMillis + l;
    }
}
