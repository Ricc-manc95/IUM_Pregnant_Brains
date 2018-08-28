// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public final class UnsupportedDateTimeField extends DateTimeField
    implements Serializable
{

    private static HashMap cCache;
    public static final long serialVersionUID = 0xe526dad19bd069d1L;
    private final DurationField iDurationField;
    private final DateTimeFieldType iType;

    private UnsupportedDateTimeField(DateTimeFieldType datetimefieldtype, DurationField durationfield)
    {
        if (datetimefieldtype == null || durationfield == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            iType = datetimefieldtype;
            iDurationField = durationfield;
            return;
        }
    }

    public static UnsupportedDateTimeField getInstance(DateTimeFieldType datetimefieldtype, DurationField durationfield)
    {
        org/joda/time/field/UnsupportedDateTimeField;
        JVM INSTR monitorenter ;
        if (cCache != null) goto _L2; else goto _L1
_L1:
        cCache = new HashMap(7);
        UnsupportedDateTimeField unsupporteddatetimefield = null;
_L4:
        UnsupportedDateTimeField unsupporteddatetimefield1;
        unsupporteddatetimefield1 = unsupporteddatetimefield;
        if (unsupporteddatetimefield != null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        unsupporteddatetimefield1 = new UnsupportedDateTimeField(datetimefieldtype, durationfield);
        cCache.put(datetimefieldtype, unsupporteddatetimefield1);
        org/joda/time/field/UnsupportedDateTimeField;
        JVM INSTR monitorexit ;
        return unsupporteddatetimefield1;
_L2:
        unsupporteddatetimefield1 = (UnsupportedDateTimeField)cCache.get(datetimefieldtype);
        unsupporteddatetimefield = unsupporteddatetimefield1;
        if (unsupporteddatetimefield1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        DurationField durationfield1 = unsupporteddatetimefield1.getDurationField();
        unsupporteddatetimefield = unsupporteddatetimefield1;
        if (durationfield1 != durationfield)
        {
            unsupporteddatetimefield = null;
        }
        if (true) goto _L4; else goto _L3
_L3:
        datetimefieldtype;
        throw datetimefieldtype;
    }

    private final Object readResolve()
    {
        return getInstance(iType, iDurationField);
    }

    private final UnsupportedOperationException unsupported()
    {
        String s = String.valueOf(iType);
        return new UnsupportedOperationException((new StringBuilder(String.valueOf(s).length() + 21)).append(s).append(" field is unsupported").toString());
    }

    public final long add(long l, int i)
    {
        return getDurationField().add(l, i);
    }

    public final long add(long l, long l1)
    {
        return getDurationField().add(l, l1);
    }

    public final int get(long l)
    {
        throw unsupported();
    }

    public final String getAsShortText(int i, Locale locale)
    {
        throw unsupported();
    }

    public final String getAsShortText(long l, Locale locale)
    {
        throw unsupported();
    }

    public final String getAsText(int i, Locale locale)
    {
        throw unsupported();
    }

    public final String getAsText(long l, Locale locale)
    {
        throw unsupported();
    }

    public final DurationField getDurationField()
    {
        return iDurationField;
    }

    public final DurationField getLeapDurationField()
    {
        return null;
    }

    public final int getMaximumTextLength(Locale locale)
    {
        throw unsupported();
    }

    public final int getMaximumValue()
    {
        throw unsupported();
    }

    public final int getMaximumValue(long l)
    {
        throw unsupported();
    }

    public final int getMinimumValue()
    {
        throw unsupported();
    }

    public final String getName()
    {
        return iType.iName;
    }

    public final DurationField getRangeDurationField()
    {
        return null;
    }

    public final DateTimeFieldType getType()
    {
        return iType;
    }

    public final boolean isLeap(long l)
    {
        throw unsupported();
    }

    public final boolean isSupported()
    {
        return false;
    }

    public final long remainder(long l)
    {
        throw unsupported();
    }

    public final long roundFloor(long l)
    {
        throw unsupported();
    }

    public final long set(long l, int i)
    {
        throw unsupported();
    }

    public final long set(long l, String s, Locale locale)
    {
        throw unsupported();
    }

    public final String toString()
    {
        return "UnsupportedDateTimeField";
    }
}
