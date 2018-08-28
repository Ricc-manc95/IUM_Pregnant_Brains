// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.geometry;


// Referenced classes of package com.google.android.calendar.timely.geometry:
//            PartitionItem, TimelineSegment

public class SimplePartitionItem
    implements PartitionItem
{

    public final int endDay;
    private final long endMillis;
    private final int endTime;
    private final boolean isAllDay;
    private int maxPartitions;
    public int partitionIndex;
    public final boolean spansAtLeastOneDay;
    public final int startDay;
    private final long startMillis;
    private final int startTime;

    public SimplePartitionItem(TimelineSegment timelinesegment)
    {
        this(timelinesegment.isAllDay(), timelinesegment.spansAtLeastOneDay(), timelinesegment.getStartDay(), timelinesegment.getEndDay(), timelinesegment.getStartTime(), timelinesegment.getEndTime(), timelinesegment.getStartMillis(), timelinesegment.getEndMillis());
    }

    public SimplePartitionItem(boolean flag, boolean flag1, int i, int j, int k, int l, long l1, long l2)
    {
        isAllDay = flag;
        spansAtLeastOneDay = flag1;
        startDay = i;
        endDay = j;
        startTime = k;
        endTime = l;
        startMillis = l1;
        endMillis = l2;
    }

    public final int getEndDay()
    {
        return endDay;
    }

    public final long getEndMillis()
    {
        return endMillis;
    }

    public final int getEndTime()
    {
        return endTime;
    }

    public final int getMaxPartitions()
    {
        return maxPartitions;
    }

    public final int getPartition()
    {
        return partitionIndex;
    }

    public final int getStartDay()
    {
        return startDay;
    }

    public final long getStartMillis()
    {
        return startMillis;
    }

    public final int getStartTime()
    {
        return startTime;
    }

    public final boolean isAllDay()
    {
        return isAllDay;
    }

    public void setMaxPartitions(int i)
    {
        maxPartitions = i;
    }

    public void setPartition(int i)
    {
        partitionIndex = i;
    }

    public final boolean spansAtLeastOneDay()
    {
        return spansAtLeastOneDay;
    }
}
