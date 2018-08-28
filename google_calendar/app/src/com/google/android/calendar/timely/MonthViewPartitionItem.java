// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.timely.geometry.SimplePartitionItem;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem

public final class MonthViewPartitionItem extends SimplePartitionItem
{

    public final TimelineItem item;

    public MonthViewPartitionItem()
    {
        super(false, false, 0, 0, 0, 0, 0L, 0L);
        item = null;
    }

    public MonthViewPartitionItem(TimelineItem timelineitem)
    {
        super(timelineitem);
        item = timelineitem;
    }
}
