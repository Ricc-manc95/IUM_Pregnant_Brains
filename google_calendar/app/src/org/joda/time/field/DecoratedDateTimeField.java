// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

// Referenced classes of package org.joda.time.field:
//            BaseDateTimeField

public class DecoratedDateTimeField extends BaseDateTimeField
{

    public final DateTimeField iField;

    public DecoratedDateTimeField(DateTimeField datetimefield, DateTimeFieldType datetimefieldtype)
    {
        super(datetimefieldtype);
        if (datetimefield == null)
        {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!datetimefield.isSupported())
        {
            throw new IllegalArgumentException("The field must be supported");
        } else
        {
            iField = datetimefield;
            return;
        }
    }

    public int get(long l)
    {
        return iField.get(l);
    }

    public DurationField getDurationField()
    {
        return iField.getDurationField();
    }

    public int getMaximumValue()
    {
        return iField.getMaximumValue();
    }

    public int getMinimumValue()
    {
        return iField.getMinimumValue();
    }

    public DurationField getRangeDurationField()
    {
        return iField.getRangeDurationField();
    }

    public long roundFloor(long l)
    {
        return iField.roundFloor(l);
    }

    public long set(long l, int i)
    {
        return iField.set(l, i);
    }
}
