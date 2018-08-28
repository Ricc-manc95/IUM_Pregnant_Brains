// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterDay

class $AutoValue_AdapterDay extends AdapterDay
{

    private final ImmutableList allDayEvents;
    private final int allDayOverflowPosition;
    private final int cacheGeneration;
    private final int julianDay;
    private final boolean loaded;
    private final ImmutableList timedEvents;

    $AutoValue_AdapterDay(int i, int j, int k, boolean flag, ImmutableList immutablelist, ImmutableList immutablelist1)
    {
        cacheGeneration = i;
        julianDay = j;
        allDayOverflowPosition = k;
        loaded = flag;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null allDayEvents");
        }
        allDayEvents = immutablelist;
        if (immutablelist1 == null)
        {
            throw new NullPointerException("Null timedEvents");
        } else
        {
            timedEvents = immutablelist1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AdapterDay)
            {
                if (cacheGeneration != ((AdapterDay) (obj = (AdapterDay)obj)).getCacheGeneration() || julianDay != ((AdapterDay) (obj)).getJulianDay() || allDayOverflowPosition != ((AdapterDay) (obj)).getAllDayOverflowPosition() || loaded != ((AdapterDay) (obj)).getLoaded() || !allDayEvents.equals(((AdapterDay) (obj)).getAllDayEvents()) || !timedEvents.equals(((AdapterDay) (obj)).getTimedEvents()))
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

    public final ImmutableList getAllDayEvents()
    {
        return allDayEvents;
    }

    public final int getAllDayOverflowPosition()
    {
        return allDayOverflowPosition;
    }

    public final int getCacheGeneration()
    {
        return cacheGeneration;
    }

    public final int getJulianDay()
    {
        return julianDay;
    }

    public final boolean getLoaded()
    {
        return loaded;
    }

    public final ImmutableList getTimedEvents()
    {
        return timedEvents;
    }

    public int hashCode()
    {
        int i = cacheGeneration;
        int j = julianDay;
        int k = allDayOverflowPosition;
        char c;
        if (loaded)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return ((c ^ (((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243 ^ k) * 0xf4243) * 0xf4243 ^ allDayEvents.hashCode()) * 0xf4243 ^ timedEvents.hashCode();
    }

    public String toString()
    {
        int i = cacheGeneration;
        int j = julianDay;
        int k = allDayOverflowPosition;
        boolean flag = loaded;
        String s = String.valueOf(allDayEvents);
        String s1 = String.valueOf(timedEvents);
        return (new StringBuilder(String.valueOf(s).length() + 141 + String.valueOf(s1).length())).append("AdapterDay{cacheGeneration=").append(i).append(", julianDay=").append(j).append(", allDayOverflowPosition=").append(k).append(", loaded=").append(flag).append(", allDayEvents=").append(s).append(", timedEvents=").append(s1).append("}").toString();
    }
}
