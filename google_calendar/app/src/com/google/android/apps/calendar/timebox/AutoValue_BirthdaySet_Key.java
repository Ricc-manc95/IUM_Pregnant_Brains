// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


public final class AutoValue_BirthdaySet_Key extends BirthdaySet.Key
{

    private final int julianDay;

    public AutoValue_BirthdaySet_Key(int i)
    {
        julianDay = i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof BirthdaySet.Key)
            {
                if (julianDay != ((BirthdaySet.Key) (obj = (BirthdaySet.Key)obj)).getJulianDay())
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

    final int getJulianDay()
    {
        return julianDay;
    }

    public final int hashCode()
    {
        return 0xf4243 ^ julianDay;
    }

    public final String toString()
    {
        int i = julianDay;
        return (new StringBuilder(26)).append("Key{julianDay=").append(i).append("}").toString();
    }
}
