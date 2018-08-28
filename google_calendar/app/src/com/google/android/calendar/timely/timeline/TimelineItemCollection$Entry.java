// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.timeline;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.TimelineItem;

// Referenced classes of package com.google.android.calendar.timely.timeline:
//            TimelineItemCollection

public static final class timelineItemCopy
{

    public final Chip chip;
    public final TimelineItem timelineItem;
    public final TimelineItem timelineItemCopy;

    public (TimelineItem timelineitem, Chip chip1)
    {
        if (timelineitem == null)
        {
            throw new NullPointerException();
        }
        timelineItem = (TimelineItem)timelineitem;
        chip = chip1;
        timelineItemCopy = timelineitem.clone();
        if (!timelineitem.isIdentical(timelineItemCopy))
        {
            LogUtils.wtf(TimelineItemCollection.TAG, "Cloned item is not identical: %s => %s", new Object[] {
                timelineitem, timelineItemCopy
            });
        }
    }
}
