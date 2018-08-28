// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleDay

final class AutoValue_ScheduleDay extends ScheduleDay
{

    private final int cacheGeneration;
    private final int heightPx;
    private final List layout;
    private final boolean loaded;

    AutoValue_ScheduleDay(int i, List list, int j, boolean flag)
    {
        cacheGeneration = i;
        layout = list;
        heightPx = j;
        loaded = flag;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ScheduleDay)
            {
                if (cacheGeneration != ((ScheduleDay) (obj = (ScheduleDay)obj)).getCacheGeneration() || !layout.equals(((ScheduleDay) (obj)).getLayout()) || heightPx != ((ScheduleDay) (obj)).getHeightPx() || loaded != ((ScheduleDay) (obj)).getLoaded())
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

    final int getCacheGeneration()
    {
        return cacheGeneration;
    }

    final int getHeightPx()
    {
        return heightPx;
    }

    final List getLayout()
    {
        return layout;
    }

    final boolean getLoaded()
    {
        return loaded;
    }

    public final int hashCode()
    {
        int i = cacheGeneration;
        int j = layout.hashCode();
        int k = heightPx;
        char c;
        if (loaded)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ (((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243 ^ k) * 0xf4243;
    }

    public final String toString()
    {
        int i = cacheGeneration;
        String s = String.valueOf(layout);
        int j = heightPx;
        boolean flag = loaded;
        return (new StringBuilder(String.valueOf(s).length() + 85)).append("ScheduleDay{cacheGeneration=").append(i).append(", layout=").append(s).append(", heightPx=").append(j).append(", loaded=").append(flag).append("}").toString();
    }
}
