// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

// Referenced classes of package org.joda.time.field:
//            DecoratedDurationField, FieldUtils

public final class ScaledDurationField extends DecoratedDurationField
{

    public static final long serialVersionUID = 0xd384bef1064f7503L;
    private final int iScalar;

    public ScaledDurationField(DurationField durationfield, DurationFieldType durationfieldtype, int i)
    {
        super(durationfield, durationfieldtype);
        if (i == 0 || i == 1)
        {
            throw new IllegalArgumentException("The scalar must not be 0 or 1");
        } else
        {
            iScalar = i;
            return;
        }
    }

    public final long add(long l, int i)
    {
        long l1 = i;
        long l2 = iScalar;
        return super.iField.add(l, l1 * l2);
    }

    public final long add(long l, long l1)
    {
        l1 = FieldUtils.safeMultiply(l1, iScalar);
        return super.iField.add(l, l1);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof ScaledDurationField)
            {
                if (!super.iField.equals(((DecoratedDurationField) (obj = (ScaledDurationField)obj)).iField) || getType() != ((DurationField) (obj)).getType() || iScalar != ((ScaledDurationField) (obj)).iScalar)
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final long getUnitMillis()
    {
        return super.iField.getUnitMillis() * (long)iScalar;
    }

    public final int hashCode()
    {
        long l = iScalar;
        return (int)(l ^ l >>> 32) + getType().hashCode() + super.iField.hashCode();
    }
}
