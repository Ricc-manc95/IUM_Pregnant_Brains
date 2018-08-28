// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;
import org.joda.time.field.BaseDateTimeField;

final class iLeapDurationField extends BaseDateTimeField
{

    private final DurationField iDurationField;
    private final DateTimeField iField;
    private final DurationField iLeapDurationField;
    private final DurationField iRangeDurationField;
    private final boolean iTimeField;
    private final DateTimeZone iZone;

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
        if (iTimeField)
        {
            int j = getOffsetToAdd(l);
            return iField.add((long)j + l, i) - (long)j;
        } else
        {
            long l1 = iZone.convertUTCToLocal(l);
            l1 = iField.add(l1, i);
            return iZone.convertLocalToUTC(l1, false, l);
        }
    }

    public final long add(long l, long l1)
    {
        if (iTimeField)
        {
            int i = getOffsetToAdd(l);
            return iField.add((long)i + l, l1) - (long)i;
        } else
        {
            long l2 = iZone.convertUTCToLocal(l);
            l1 = iField.add(l2, l1);
            return iZone.convertLocalToUTC(l1, false, l);
        }
    }

    public final int get(long l)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.get(l);
    }

    public final String getAsShortText(int i, Locale locale)
    {
        return iField.getAsShortText(i, locale);
    }

    public final String getAsShortText(long l, Locale locale)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.getAsShortText(l, locale);
    }

    public final String getAsText(int i, Locale locale)
    {
        return iField.getAsText(i, locale);
    }

    public final String getAsText(long l, Locale locale)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.getAsText(l, locale);
    }

    public final DurationField getDurationField()
    {
        return iDurationField;
    }

    public final DurationField getLeapDurationField()
    {
        return iLeapDurationField;
    }

    public final int getMaximumTextLength(Locale locale)
    {
        return iField.getMaximumTextLength(locale);
    }

    public final int getMaximumValue()
    {
        return iField.getMaximumValue();
    }

    public final int getMaximumValue(long l)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.getMaximumValue(l);
    }

    public final int getMinimumValue()
    {
        return iField.getMinimumValue();
    }

    public final DurationField getRangeDurationField()
    {
        return iRangeDurationField;
    }

    public final boolean isLeap(long l)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.isLeap(l);
    }

    public final long remainder(long l)
    {
        l = iZone.convertUTCToLocal(l);
        return iField.remainder(l);
    }

    public final long roundFloor(long l)
    {
        if (iTimeField)
        {
            int i = getOffsetToAdd(l);
            return iField.roundFloor((long)i + l) - (long)i;
        } else
        {
            long l1 = iZone.convertUTCToLocal(l);
            l1 = iField.roundFloor(l1);
            return iZone.convertLocalToUTC(l1, false, l);
        }
    }

    public final long set(long l, int i)
    {
        long l1 = iZone.convertUTCToLocal(l);
        l1 = iField.set(l1, i);
        l = iZone.convertLocalToUTC(l1, false, l);
        if (get(l) != i)
        {
            IllegalInstantException illegalinstantexception = new IllegalInstantException(l1, iZone.iID);
            IllegalFieldValueException illegalfieldvalueexception = new IllegalFieldValueException(iField.getType(), Integer.valueOf(i), illegalinstantexception.getMessage());
            illegalfieldvalueexception.initCause(illegalinstantexception);
            throw illegalfieldvalueexception;
        } else
        {
            return l;
        }
    }

    public final long set(long l, String s, Locale locale)
    {
        long l1 = iZone.convertUTCToLocal(l);
        l1 = iField.set(l1, s, locale);
        return iZone.convertLocalToUTC(l1, false, l);
    }

    (DateTimeField datetimefield, DateTimeZone datetimezone, DurationField durationfield, DurationField durationfield1, DurationField durationfield2)
    {
        super(datetimefield.getType());
        if (!datetimefield.isSupported())
        {
            throw new IllegalArgumentException();
        }
        iField = datetimefield;
        iZone = datetimezone;
        iDurationField = durationfield;
        boolean flag;
        if (durationfield != null && durationfield.getUnitMillis() < 0x2932e00L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        iTimeField = flag;
        iRangeDurationField = durationfield1;
        iLeapDurationField = durationfield2;
    }
}
