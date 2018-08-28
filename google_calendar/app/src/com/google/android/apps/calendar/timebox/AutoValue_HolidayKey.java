// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            HolidayKey

public final class AutoValue_HolidayKey extends HolidayKey
{

    private final int endJulianDay;
    private final int startJulianDay;
    private final String title;

    public AutoValue_HolidayKey(int i, int j, String s)
    {
        startJulianDay = i;
        endJulianDay = j;
        if (s == null)
        {
            throw new NullPointerException("Null title");
        } else
        {
            title = s;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof HolidayKey)
            {
                if (startJulianDay != ((HolidayKey) (obj = (HolidayKey)obj)).getStartJulianDay() || endJulianDay != ((HolidayKey) (obj)).getEndJulianDay() || !title.equals(((HolidayKey) (obj)).getTitle()))
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

    final int getEndJulianDay()
    {
        return endJulianDay;
    }

    final int getStartJulianDay()
    {
        return startJulianDay;
    }

    final String getTitle()
    {
        return title;
    }

    public final int hashCode()
    {
        return ((startJulianDay ^ 0xf4243) * 0xf4243 ^ endJulianDay) * 0xf4243 ^ title.hashCode();
    }

    public final String toString()
    {
        int i = startJulianDay;
        int j = endJulianDay;
        String s = title;
        return (new StringBuilder(String.valueOf(s).length() + 72)).append("HolidayKey{startJulianDay=").append(i).append(", endJulianDay=").append(j).append(", title=").append(s).append("}").toString();
    }
}
