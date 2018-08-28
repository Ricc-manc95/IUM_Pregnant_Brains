// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarDay, AutoValue_CalendarDay

final class items extends items
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

    public final items setCacheGeneration(int i)
    {
        cacheGeneration = Integer.valueOf(i);
        return this;
    }

    public final cacheGeneration setItems(ImmutableSet immutableset)
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

    public final items setJulianDate(int i)
    {
        julianDate = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }

    (CalendarDay calendarday)
    {
        cacheGeneration = Integer.valueOf(calendarday.getCacheGeneration());
        julianDate = Integer.valueOf(calendarday.getJulianDate());
        items = calendarday.getItems();
    }
}
