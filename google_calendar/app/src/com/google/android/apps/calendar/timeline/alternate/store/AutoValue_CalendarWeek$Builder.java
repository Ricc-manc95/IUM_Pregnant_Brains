// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarWeek, AutoValue_CalendarWeek

final class julianWeek extends julianWeek
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

    public final julianWeek setCacheGeneration(long l)
    {
        cacheGeneration = Long.valueOf(l);
        return this;
    }

    public final cacheGeneration setDays(ImmutableList immutablelist)
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

    public final days setJulianWeek(int i)
    {
        julianWeek = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }

    (CalendarWeek calendarweek)
    {
        cacheGeneration = Long.valueOf(calendarweek.getCacheGeneration());
        days = calendarweek.getDays();
        julianWeek = Integer.valueOf(calendarweek.getJulianWeek());
    }
}
