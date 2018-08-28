// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.util:
//            YearMonth

public final class AutoValue_YearMonth extends YearMonth
{

    private final int month;
    private final int year;

    public AutoValue_YearMonth(int i, int j)
    {
        year = i;
        month = j;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof YearMonth)
            {
                if (year != ((YearMonth) (obj = (YearMonth)obj)).getYear() || month != ((YearMonth) (obj)).getMonth())
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

    public final int getMonth()
    {
        return month;
    }

    public final int getYear()
    {
        return year;
    }

    public final int hashCode()
    {
        return (year ^ 0xf4243) * 0xf4243 ^ month;
    }

    public final String toString()
    {
        int i = year;
        int j = month;
        return (new StringBuilder(46)).append("YearMonth{year=").append(i).append(", month=").append(j).append("}").toString();
    }
}
