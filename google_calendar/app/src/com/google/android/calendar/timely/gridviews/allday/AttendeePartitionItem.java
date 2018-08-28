// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;

final class AttendeePartitionItem extends SimplePartitionItem
{

    public final int attendeeIndex;

    AttendeePartitionItem(TimelineItem timelineitem, int i)
    {
        super(timelineitem);
        attendeeIndex = i;
    }
}
