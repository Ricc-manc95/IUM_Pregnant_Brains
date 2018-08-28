// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class UnsupportedDurationField extends DurationField
    implements Serializable
{

    private static HashMap cCache;
    public static final long serialVersionUID = 0xa75116d2889f80d3L;
    private final DurationFieldType iType;

    private UnsupportedDurationField(DurationFieldType durationfieldtype)
    {
        iType = durationfieldtype;
    }

    public static UnsupportedDurationField getInstance(DurationFieldType durationfieldtype)
    {
        org/joda/time/field/UnsupportedDurationField;
        JVM INSTR monitorenter ;
        if (cCache != null) goto _L2; else goto _L1
_L1:
        cCache = new HashMap(7);
        UnsupportedDurationField unsupporteddurationfield = null;
_L4:
        UnsupportedDurationField unsupporteddurationfield1;
        unsupporteddurationfield1 = unsupporteddurationfield;
        if (unsupporteddurationfield != null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        unsupporteddurationfield1 = new UnsupportedDurationField(durationfieldtype);
        cCache.put(durationfieldtype, unsupporteddurationfield1);
        org/joda/time/field/UnsupportedDurationField;
        JVM INSTR monitorexit ;
        return unsupporteddurationfield1;
_L2:
        unsupporteddurationfield = (UnsupportedDurationField)cCache.get(durationfieldtype);
        if (true) goto _L4; else goto _L3
_L3:
        durationfieldtype;
        throw durationfieldtype;
    }

    private final Object readResolve()
    {
        return getInstance(iType);
    }

    private final UnsupportedOperationException unsupported()
    {
        String s = String.valueOf(iType);
        return new UnsupportedOperationException((new StringBuilder(String.valueOf(s).length() + 21)).append(s).append(" field is unsupported").toString());
    }

    public final long add(long l, int i)
    {
        throw unsupported();
    }

    public final long add(long l, long l1)
    {
        throw unsupported();
    }

    public final volatile int compareTo(Object obj)
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof UnsupportedDurationField)
            {
                obj = (UnsupportedDurationField)obj;
                if (((UnsupportedDurationField) (obj)).iType.iName == null)
                {
                    if (iType.iName != null)
                    {
                        return false;
                    }
                } else
                {
                    return ((UnsupportedDurationField) (obj)).iType.iName.equals(iType.iName);
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final DurationFieldType getType()
    {
        return iType;
    }

    public final long getUnitMillis()
    {
        return 0L;
    }

    public final int hashCode()
    {
        return iType.iName.hashCode();
    }

    public final boolean isPrecise()
    {
        return true;
    }

    public final boolean isSupported()
    {
        return false;
    }

    public final String toString()
    {
        String s = iType.iName;
        return (new StringBuilder(String.valueOf(s).length() + 26)).append("UnsupportedDurationField[").append(s).append(']').toString();
    }
}
