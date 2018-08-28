// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.support.v4.util.Pair;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.TimelineItem;

public final class chip
{

    public Pair asPair;
    public final Chip chip;
    public final Object partitionInfo;
    public final TimelineItem timelineItem;

    (Object obj, TimelineItem timelineitem, Chip chip1)
    {
        partitionInfo = obj;
        timelineItem = timelineitem;
        chip = chip1;
    }
}
