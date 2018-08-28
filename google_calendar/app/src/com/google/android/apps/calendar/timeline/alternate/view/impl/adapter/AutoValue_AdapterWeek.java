// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterWeek

final class AutoValue_AdapterWeek extends AdapterWeek
{

    private final long cacheGeneration;
    private final ImmutableList days;
    private final int julianWeek;

    AutoValue_AdapterWeek(long l, int i, ImmutableList immutablelist)
    {
        cacheGeneration = l;
        julianWeek = i;
        days = immutablelist;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AdapterWeek)
            {
                if (cacheGeneration != ((AdapterWeek) (obj = (AdapterWeek)obj)).getCacheGeneration() || julianWeek != ((AdapterWeek) (obj)).getJulianWeek() || !days.equals(((AdapterWeek) (obj)).getDays()))
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

    public final long getCacheGeneration()
    {
        return cacheGeneration;
    }

    public final ImmutableList getDays()
    {
        return days;
    }

    public final int getJulianWeek()
    {
        return julianWeek;
    }

    public final int hashCode()
    {
        return (((int)(cacheGeneration >>> 32 ^ cacheGeneration) ^ 0xf4243) * 0xf4243 ^ julianWeek) * 0xf4243 ^ days.hashCode();
    }

    public final String toString()
    {
        long l = cacheGeneration;
        int i = julianWeek;
        String s = String.valueOf(days);
        return (new StringBuilder(String.valueOf(s).length() + 80)).append("AdapterWeek{cacheGeneration=").append(l).append(", julianWeek=").append(i).append(", days=").append(s).append("}").toString();
    }
}
