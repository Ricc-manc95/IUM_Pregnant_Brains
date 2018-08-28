// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.field.BaseDurationField;

final class iZone extends BaseDurationField
{

    public static final long serialVersionUID = 0xf943b502d87d9ea2L;
    private final DurationField iField;
    private final boolean iTimeField;
    private final DateTimeZone iZone;

    private final int getOffsetFromLocalToSubtract(long l)
    {
        int i = iZone.getOffsetFromLocal(l);
        if ((l - (long)i ^ l) < 0L && ((long)i ^ l) < 0L)
        {
            throw new ArithmeticException("Subtracting time zone offset caused overflow");
        } else
        {
            return i;
        }
    }

    private final int getOffsetToAdd(long l)
    {
        int i = iZone.getOffset(l);
        if (((long)i + l ^ l) < 0L && ((long)i ^ l) >= 0L)
        {
            throw new ArithmeticException("Adding time zone offset caused overflow");
        } else
        {
            return i;
        }
    }

    public final long add(long l, int i)
    {
        int j = getOffsetToAdd(l);
        l = iField.add((long)j + l, i);
        if (iTimeField)
        {
            i = j;
        } else
        {
            i = getOffsetFromLocalToSubtract(l);
        }
        return l - (long)i;
    }

    public final long add(long l, long l1)
    {
        int i = getOffsetToAdd(l);
        l = iField.add((long)i + l, l1);
        if (!iTimeField)
        {
            i = getOffsetFromLocalToSubtract(l);
        }
        return l - (long)i;
    }

    public final long getUnitMillis()
    {
        return iField.getUnitMillis();
    }

    public final boolean isPrecise()
    {
        if (iTimeField)
        {
            return iField.isPrecise();
        }
        return iField.isPrecise() && iZone.isFixed();
    }

    (DurationField durationfield, DateTimeZone datetimezone)
    {
        super(durationfield.getType());
        if (!durationfield.isSupported())
        {
            throw new IllegalArgumentException();
        }
        iField = durationfield;
        boolean flag;
        if (durationfield != null && durationfield.getUnitMillis() < 0x2932e00L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        iTimeField = flag;
        iZone = datetimezone;
    }
}
