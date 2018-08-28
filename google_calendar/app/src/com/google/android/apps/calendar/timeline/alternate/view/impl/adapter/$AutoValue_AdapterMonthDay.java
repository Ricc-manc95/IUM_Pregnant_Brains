// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterMonthDay

class $AutoValue_AdapterMonthDay extends AdapterMonthDay
{

    private final ImmutableList events;
    private final boolean loaded;
    private final int monthDayHeaderPosition;

    $AutoValue_AdapterMonthDay(int i, ImmutableList immutablelist, boolean flag)
    {
        monthDayHeaderPosition = i;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null events");
        } else
        {
            events = immutablelist;
            loaded = flag;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AdapterMonthDay)
            {
                if (monthDayHeaderPosition != ((AdapterMonthDay) (obj = (AdapterMonthDay)obj)).getMonthDayHeaderPosition() || !events.equals(((AdapterMonthDay) (obj)).getEvents()) || loaded != ((AdapterMonthDay) (obj)).getLoaded())
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

    public final ImmutableList getEvents()
    {
        return events;
    }

    public final boolean getLoaded()
    {
        return loaded;
    }

    public final int getMonthDayHeaderPosition()
    {
        return monthDayHeaderPosition;
    }

    public int hashCode()
    {
        int i = monthDayHeaderPosition;
        int j = events.hashCode();
        char c;
        if (loaded)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243;
    }

    public String toString()
    {
        int i = monthDayHeaderPosition;
        String s = String.valueOf(events);
        boolean flag = loaded;
        return (new StringBuilder(String.valueOf(s).length() + 74)).append("AdapterMonthDay{monthDayHeaderPosition=").append(i).append(", events=").append(s).append(", loaded=").append(flag).append("}").toString();
    }
}
