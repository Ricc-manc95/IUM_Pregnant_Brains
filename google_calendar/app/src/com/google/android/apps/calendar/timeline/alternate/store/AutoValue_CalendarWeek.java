// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarWeek

final class AutoValue_CalendarWeek extends CalendarWeek
{

    private final long cacheGeneration;
    private final ImmutableList days;
    private final int julianWeek;

    AutoValue_CalendarWeek(long l, ImmutableList immutablelist, int i)
    {
        cacheGeneration = l;
        days = immutablelist;
        julianWeek = i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CalendarWeek)
            {
                if (cacheGeneration != ((CalendarWeek) (obj = (CalendarWeek)obj)).getCacheGeneration() || !days.equals(((CalendarWeek) (obj)).getDays()) || julianWeek != ((CalendarWeek) (obj)).getJulianWeek())
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
        return (((int)(cacheGeneration >>> 32 ^ cacheGeneration) ^ 0xf4243) * 0xf4243 ^ days.hashCode()) * 0xf4243 ^ julianWeek;
    }

    public final CalendarWeek.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        long l = cacheGeneration;
        String s = String.valueOf(days);
        int i = julianWeek;
        return (new StringBuilder(String.valueOf(s).length() + 81)).append("CalendarWeek{cacheGeneration=").append(l).append(", days=").append(s).append(", julianWeek=").append(i).append("}").toString();
    }

    private class Builder extends CalendarWeek.Builder
    {

        private Long cacheGeneration;
        private ImmutableList days;
        private Integer julianWeek;

        public final CalendarWeek build()
        {
            String s2 = "";
            if (cacheGeneration == null)
            {
                s2 = String.valueOf("").concat(" cacheGeneration");
            }
            String s = s2;
            if (days == null)
            {
                s = String.valueOf(s2).concat(" days");
            }
            s2 = s;
            if (julianWeek == null)
            {
                s2 = String.valueOf(s).concat(" julianWeek");
            }
            if (!s2.isEmpty())
            {
                String s1 = String.valueOf(s2);
                if (s1.length() != 0)
                {
                    s1 = "Missing required properties:".concat(s1);
                } else
                {
                    s1 = new String("Missing required properties:");
                }
                throw new IllegalStateException(s1);
            } else
            {
                return new AutoValue_CalendarWeek(cacheGeneration.longValue(), days, julianWeek.intValue());
            }
        }

        public final CalendarWeek.Builder setCacheGeneration(long l)
        {
            cacheGeneration = Long.valueOf(l);
            return this;
        }

        public final CalendarWeek.Builder setDays(ImmutableList immutablelist)
        {
            if (immutablelist == null)
            {
                throw new NullPointerException("Null days");
            } else
            {
                days = immutablelist;
                return this;
            }
        }

        public final CalendarWeek.Builder setJulianWeek(int i)
        {
            julianWeek = Integer.valueOf(i);
            return this;
        }

        Builder()
        {
        }

        Builder(CalendarWeek calendarweek)
        {
            cacheGeneration = Long.valueOf(calendarweek.getCacheGeneration());
            days = calendarweek.getDays();
            julianWeek = Integer.valueOf(calendarweek.getJulianWeek());
        }
    }

}
