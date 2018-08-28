// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;

public abstract class BaseDateTimeField extends DateTimeField
{

    private final DateTimeFieldType iType;

    public BaseDateTimeField(DateTimeFieldType datetimefieldtype)
    {
        if (datetimefieldtype == null)
        {
            throw new IllegalArgumentException("The type must not be null");
        } else
        {
            iType = datetimefieldtype;
            return;
        }
    }

    public long add(long l, int i)
    {
        return getDurationField().add(l, i);
    }

    public long add(long l, long l1)
    {
        return getDurationField().add(l, l1);
    }

    public int convertText(String s, Locale locale)
    {
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch (Locale locale)
        {
            throw new IllegalFieldValueException(getType(), s);
        }
        return i;
    }

    public String getAsShortText(int i, Locale locale)
    {
        return getAsText(i, locale);
    }

    public String getAsShortText(long l, Locale locale)
    {
        return getAsShortText(get(l), locale);
    }

    public String getAsText(int i, Locale locale)
    {
        return Integer.toString(i);
    }

    public String getAsText(long l, Locale locale)
    {
        return getAsText(get(l), locale);
    }

    public DurationField getLeapDurationField()
    {
        return null;
    }

    public int getMaximumTextLength(Locale locale)
    {
        int i = getMaximumValue();
        if (i >= 0)
        {
            if (i < 10)
            {
                return 1;
            }
            if (i < 100)
            {
                return 2;
            }
            if (i < 1000)
            {
                return 3;
            }
        }
        return Integer.toString(i).length();
    }

    public int getMaximumValue(long l)
    {
        return getMaximumValue();
    }

    public final String getName()
    {
        return iType.iName;
    }

    public final DateTimeFieldType getType()
    {
        return iType;
    }

    public boolean isLeap(long l)
    {
        return false;
    }

    public final boolean isSupported()
    {
        return true;
    }

    public long remainder(long l)
    {
        return l - roundFloor(l);
    }

    public long set(long l, String s, Locale locale)
    {
        return set(l, convertText(s, locale));
    }

    public String toString()
    {
        String s = getName();
        return (new StringBuilder(String.valueOf(s).length() + 15)).append("DateTimeField[").append(s).append(']').toString();
    }
}
