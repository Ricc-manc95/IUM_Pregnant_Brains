// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class BaseDurationField extends DurationField
    implements Serializable
{

    public static final long serialVersionUID = 0xdc8d7f9b8cda387eL;
    private final DurationFieldType iType;

    public BaseDurationField(DurationFieldType durationfieldtype)
    {
        if (durationfieldtype == null)
        {
            throw new IllegalArgumentException("The type must not be null");
        } else
        {
            iType = durationfieldtype;
            return;
        }
    }

    public int compareTo(Object obj)
    {
        long l = ((DurationField)obj).getUnitMillis();
        long l1 = getUnitMillis();
        if (l1 == l)
        {
            return 0;
        }
        return l1 >= l ? 1 : -1;
    }

    public final DurationFieldType getType()
    {
        return iType;
    }

    public final boolean isSupported()
    {
        return true;
    }

    public String toString()
    {
        String s = iType.iName;
        return (new StringBuilder(String.valueOf(s).length() + 15)).append("DurationField[").append(s).append(']').toString();
    }
}
