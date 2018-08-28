// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarDay

final class AutoValue_CalendarDay extends CalendarDay
{

    private final int cacheGeneration;
    private final ImmutableSet items;
    private final int julianDate;

    AutoValue_CalendarDay(int i, int j, ImmutableSet immutableset)
    {
        cacheGeneration = i;
        julianDate = j;
        items = immutableset;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CalendarDay)
            {
                if (cacheGeneration != ((CalendarDay) (obj = (CalendarDay)obj)).getCacheGeneration() || julianDate != ((CalendarDay) (obj)).getJulianDate() || !items.equals(((CalendarDay) (obj)).getItems()))
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

    public final int getCacheGeneration()
    {
        return cacheGeneration;
    }

    public final ImmutableSet getItems()
    {
        return items;
    }

    public final int getJulianDate()
    {
        return julianDate;
    }

    public final int hashCode()
    {
        return ((cacheGeneration ^ 0xf4243) * 0xf4243 ^ julianDate) * 0xf4243 ^ items.hashCode();
    }

    public final CalendarDay.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        int i = cacheGeneration;
        int j = julianDate;
        String s = String.valueOf(items);
        return (new StringBuilder(String.valueOf(s).length() + 72)).append("CalendarDay{cacheGeneration=").append(i).append(", julianDate=").append(j).append(", items=").append(s).append("}").toString();
    }

    private class Builder extends CalendarDay.Builder
    {

        private Integer cacheGeneration;
        private ImmutableSet items;
        private Integer julianDate;

        public final CalendarDay build()
        {
            String s2 = "";
            if (cacheGeneration == null)
            {
                s2 = String.valueOf("").concat(" cacheGeneration");
            }
            String s = s2;
            if (julianDate == null)
            {
                s = String.valueOf(s2).concat(" julianDate");
            }
            s2 = s;
            if (items == null)
            {
                s2 = String.valueOf(s).concat(" items");
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
                return new AutoValue_CalendarDay(cacheGeneration.intValue(), julianDate.intValue(), items);
            }
        }

        public final CalendarDay.Builder setCacheGeneration(int i)
        {
            cacheGeneration = Integer.valueOf(i);
            return this;
        }

        public final CalendarDay.Builder setItems(ImmutableSet immutableset)
        {
            if (immutableset == null)
            {
                throw new NullPointerException("Null items");
            } else
            {
                items = immutableset;
                return this;
            }
        }

        public final CalendarDay.Builder setJulianDate(int i)
        {
            julianDate = Integer.valueOf(i);
            return this;
        }

        Builder()
        {
        }

        Builder(CalendarDay calendarday)
        {
            cacheGeneration = Integer.valueOf(calendarday.getCacheGeneration());
            julianDate = Integer.valueOf(calendarday.getJulianDate());
            items = calendarday.getItems();
        }
    }

}
