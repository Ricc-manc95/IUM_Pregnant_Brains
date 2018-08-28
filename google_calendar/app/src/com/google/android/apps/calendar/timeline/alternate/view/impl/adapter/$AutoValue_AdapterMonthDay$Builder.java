// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AutoValue_AdapterMonthDay, AdapterMonthDay

final class  extends 
{

    private ImmutableList events;
    private Boolean loaded;
    private Integer monthDayHeaderPosition;

    final AdapterMonthDay build()
    {
        String s2 = "";
        if (monthDayHeaderPosition == null)
        {
            s2 = String.valueOf("").concat(" monthDayHeaderPosition");
        }
        String s = s2;
        if (events == null)
        {
            s = String.valueOf(s2).concat(" events");
        }
        s2 = s;
        if (loaded == null)
        {
            s2 = String.valueOf(s).concat(" loaded");
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
            return new AutoValue_AdapterMonthDay(monthDayHeaderPosition.intValue(), events, loaded.booleanValue());
        }
    }

    final loaded setEvents(ImmutableList immutablelist)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null events");
        } else
        {
            events = immutablelist;
            return this;
        }
    }

    final events setLoaded(boolean flag)
    {
        loaded = Boolean.valueOf(flag);
        return this;
    }

    final loaded setMonthDayHeaderPosition(int i)
    {
        monthDayHeaderPosition = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }
}
