// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            DecoratedDateTimeField, FieldUtils

public final class ZeroIsMaxDateTimeField extends DecoratedDateTimeField
{

    public ZeroIsMaxDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype)
    {
        super(datetimefield, datetimefieldtype);
        if (datetimefield.getMinimumValue() != 0)
        {
            throw new IllegalArgumentException("Wrapped field's minumum value must be zero");
        } else
        {
            return;
        }
    }

    public final long add(long l, int i)
    {
        return super.iField.add(l, i);
    }

    public final long add(long l, long l1)
    {
        return super.iField.add(l, l1);
    }

    public final int get(long l)
    {
        int j = super.iField.get(l);
        int i = j;
        if (j == 0)
        {
            i = getMaximumValue();
        }
        return i;
    }

    public final DurationField getLeapDurationField()
    {
        return super.iField.getLeapDurationField();
    }

    public final int getMaximumValue()
    {
        return super.iField.getMaximumValue() + 1;
    }

    public final int getMaximumValue(long l)
    {
        return super.iField.getMaximumValue(l) + 1;
    }

    public final int getMinimumValue()
    {
        return 1;
    }

    public final boolean isLeap(long l)
    {
        return super.iField.isLeap(l);
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
        int k = getMaximumValue();
        FieldUtils.verifyValueBounds(this, i, 1, k);
        int j = i;
        if (i == k)
        {
            j = 0;
        }
        return super.iField.set(l, j);
    }
}
