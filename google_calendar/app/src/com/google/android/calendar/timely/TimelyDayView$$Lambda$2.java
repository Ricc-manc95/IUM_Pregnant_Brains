// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.timeline.chip.Chip;
import java.util.Comparator;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView

final class arg._cls1
    implements Comparator
{

    private final Map arg$1;

    public final int compare(Object obj, Object obj1)
    {
        return TimelyDayView.lambda$loadTimelineItems$1$TimelyDayView(arg$1, (Chip)obj, (Chip)obj1);
    }

    (Map map)
    {
        arg$1 = map;
    }
}
