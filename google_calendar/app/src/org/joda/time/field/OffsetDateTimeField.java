// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            DecoratedDateTimeField, FieldUtils

public final class OffsetDateTimeField extends DecoratedDateTimeField
{

    private final int iMax;
    private final int iMin;
    private final int iOffset;

    public OffsetDateTimeField(DateTimeField datetimefield, int i)
    {
        DateTimeFieldType datetimefieldtype;
        if (datetimefield == null)
        {
            datetimefieldtype = null;
        } else
        {
            datetimefieldtype = datetimefield.getType();
        }
        this(datetimefield, datetimefieldtype, i, 0x80000000, 0x7fffffff);
    }

    public OffsetDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype, int i)
    {
        this(datetimefield, datetimefieldtype, 1, 0x80000000, 0x7fffffff);
    }

    private OffsetDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype, int i, int j, int k)
    {
        super(datetimefield, datetimefieldtype);
        if (i == 0)
        {
            throw new IllegalArgumentException("The offset cannot be zero");
        }
        iOffset = i;
        if (0x80000000 < datetimefield.getMinimumValue() + i)
        {
            iMin = datetimefield.getMinimumValue() + i;
        } else
        {
            iMin = 0x80000000;
        }
        if (0x7fffffff > datetimefield.getMaximumValue() + i)
        {
            iMax = datetimefield.getMaximumValue() + i;
            return;
        } else
        {
            iMax = 0x7fffffff;
            return;
        }
    }

    public final long add(long l, int i)
    {
        l = super.add(l, i);
        FieldUtils.verifyValueBounds(this, get(l), iMin, iMax);
        return l;
    }

    public final long add(long l, long l1)
    {
        l = super.add(l, l1);
        FieldUtils.verifyValueBounds(this, get(l), iMin, iMax);
        return l;
    }

    public final int get(long l)
    {
        return super.get(l) + iOffset;
    }

    public final DurationField getLeapDurationField()
    {
        return super.iField.getLeapDurationField();
    }

    public final int getMaximumValue()
    {
        return iMax;
    }

    public final int getMinimumValue()
    {
        return iMin;
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
        FieldUtils.verifyValueBounds(this, i, iMin, iMax);
        return super.set(l, i - iOffset);
    }
}
