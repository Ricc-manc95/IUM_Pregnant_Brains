// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

// Referenced classes of package org.joda.time.field:
//            FieldUtils

public final class MillisDurationField extends DurationField
    implements Serializable
{

    public static final DurationField INSTANCE = new MillisDurationField();
    public static final long serialVersionUID = 0x24de85b89b81f517L;

    private MillisDurationField()
    {
    }

    private final Object readResolve()
    {
        return INSTANCE;
    }

    public final long add(long l, int i)
    {
        return FieldUtils.safeAdd(l, i);
    }

    public final long add(long l, long l1)
    {
        return FieldUtils.safeAdd(l, l1);
    }

    public final int compareTo(Object obj)
    {
        long l = ((DurationField)obj).getUnitMillis();
        if (1L == l)
        {
            return 0;
        }
        return 1L >= l ? 1 : -1;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof MillisDurationField)
        {
            flag = flag1;
            if (1L == 1L)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final DurationFieldType getType()
    {
        return DurationFieldType.MILLIS_TYPE;
    }

    public final long getUnitMillis()
    {
        return 1L;
    }

    public final int hashCode()
    {
        return 1;
    }

    public final boolean isPrecise()
    {
        return true;
    }

    public final boolean isSupported()
    {
        return true;
    }

    public final String toString()
    {
        return "DurationField[millis]";
    }

}
