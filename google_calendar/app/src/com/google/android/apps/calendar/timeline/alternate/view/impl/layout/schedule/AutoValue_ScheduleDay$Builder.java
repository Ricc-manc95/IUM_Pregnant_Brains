// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            AutoValue_ScheduleDay, ScheduleDay

final class q extends q
{

    private Integer cacheGeneration;
    private Integer heightPx;
    private List layout;
    private Boolean loaded;

    final ScheduleDay build()
    {
        String s1 = "";
        if (cacheGeneration == null)
        {
            s1 = String.valueOf("").concat(" cacheGeneration");
        }
        String s = s1;
        if (layout == null)
        {
            s = String.valueOf(s1).concat(" layout");
        }
        s1 = s;
        if (heightPx == null)
        {
            s1 = String.valueOf(s).concat(" heightPx");
        }
        s = s1;
        if (loaded == null)
        {
            s = String.valueOf(s1).concat(" loaded");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_ScheduleDay(cacheGeneration.intValue(), layout, heightPx.intValue(), loaded.booleanValue());
        }
    }

    final loaded setCacheGeneration(int i)
    {
        cacheGeneration = Integer.valueOf(i);
        return this;
    }

    final cacheGeneration setHeightPx(int i)
    {
        heightPx = Integer.valueOf(i);
        return this;
    }

    final heightPx setLayout(List list)
    {
        layout = list;
        return this;
    }

    final layout setLoaded(boolean flag)
    {
        loaded = Boolean.valueOf(flag);
        return this;
    }

    q()
    {
    }
}
