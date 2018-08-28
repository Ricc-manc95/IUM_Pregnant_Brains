// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AutoValue_AdapterWeek, AdapterWeek

final class i extends i
{

    private Long cacheGeneration;
    private ImmutableList days;
    private Integer julianWeek;

    public final AdapterWeek build()
    {
        String s2 = "";
        if (cacheGeneration == null)
        {
            s2 = String.valueOf("").concat(" cacheGeneration");
        }
        String s = s2;
        if (julianWeek == null)
        {
            s = String.valueOf(s2).concat(" julianWeek");
        }
        s2 = s;
        if (days == null)
        {
            s2 = String.valueOf(s).concat(" days");
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
            return new AutoValue_AdapterWeek(cacheGeneration.longValue(), julianWeek.intValue(), days);
        }
    }

    public final days setCacheGeneration(long l)
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

    i()
    {
    }
}
