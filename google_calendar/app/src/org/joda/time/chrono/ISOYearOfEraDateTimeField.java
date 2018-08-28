// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.FieldUtils;

// Referenced classes of package org.joda.time.chrono:
//            GregorianChronology

final class ISOYearOfEraDateTimeField extends DecoratedDateTimeField
{

    public static final DateTimeField INSTANCE = new ISOYearOfEraDateTimeField();

    private ISOYearOfEraDateTimeField()
    {
        super(GregorianChronology.INSTANCE_UTC.year(), DateTimeFieldType.YEAR_OF_ERA_TYPE);
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
        if (j < 0)
        {
            i = -j;
        }
        return i;
    }

    public final int getMaximumValue()
    {
        return super.iField.getMaximumValue();
    }

    public final int getMinimumValue()
    {
        return 0;
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
        FieldUtils.verifyValueBounds(this, i, 0, getMaximumValue());
        int j = i;
        if (super.iField.get(l) < 0)
        {
            j = -i;
        }
        return super.set(l, j);
    }

}
