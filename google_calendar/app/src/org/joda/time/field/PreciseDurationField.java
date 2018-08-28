// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

// Referenced classes of package org.joda.time.field:
//            BaseDurationField, FieldUtils

public final class PreciseDurationField extends BaseDurationField
{

    public static final long serialVersionUID = 0x8c2c82ce195505fbL;
    private final long iUnitMillis;

    public PreciseDurationField(DurationFieldType durationfieldtype, long l)
    {
        super(durationfieldtype);
        iUnitMillis = l;
    }

    public final long add(long l, int i)
    {
        return FieldUtils.safeAdd(l, (long)i * iUnitMillis);
    }

    public final long add(long l, long l1)
    {
        long l2 = iUnitMillis;
        if (l2 != 1L)
        {
            if (l1 == 1L)
            {
                l1 = l2;
            } else
            if (l1 == 0L || l2 == 0L)
            {
                l1 = 0L;
            } else
            {
                long l3 = l1 * l2;
                if (l3 / l2 != l1 || l1 == 0x8000000000000000L && l2 == -1L || l2 == 0x8000000000000000L && l1 == -1L)
                {
                    throw new ArithmeticException((new StringBuilder(76)).append("Multiplication overflows a long: ").append(l1).append(" * ").append(l2).toString());
                }
                l1 = l3;
            }
        }
        return FieldUtils.safeAdd(l, l1);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof PreciseDurationField)
            {
                if (getType() != ((DurationField) (obj = (PreciseDurationField)obj)).getType() || iUnitMillis != ((PreciseDurationField) (obj)).iUnitMillis)
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
        return iUnitMillis;
    }

    public final int hashCode()
    {
        long l = iUnitMillis;
        return (int)(l ^ l >>> 32) + getType().hashCode();
    }

    public final boolean isPrecise()
    {
        return true;
    }
}
