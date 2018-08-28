// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

// Referenced classes of package org.joda.time.field:
//            BaseDurationField

public class DecoratedDurationField extends BaseDurationField
{

    public static final long serialVersionUID = 0x6f4cb35dbe61c66fL;
    public final DurationField iField;

    public DecoratedDurationField(DurationField durationfield, DurationFieldType durationfieldtype)
    {
        super(durationfieldtype);
        if (durationfield == null)
        {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!durationfield.isSupported())
        {
            throw new IllegalArgumentException("The field must be supported");
        } else
        {
            iField = durationfield;
            return;
        }
    }

    public long add(long l, int i)
    {
        return iField.add(l, i);
    }

    public long add(long l, long l1)
    {
        return iField.add(l, l1);
    }

    public long getUnitMillis()
    {
        return iField.getUnitMillis();
    }

    public final boolean isPrecise()
    {
        return iField.isPrecise();
    }
}
