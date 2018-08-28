// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.geometry;


// Referenced classes of package com.google.android.calendar.timely.geometry:
//            TimelineSegment

public interface PartitionItem
    extends TimelineSegment
{

    public abstract int getMaxPartitions();

    public abstract int getPartition();

    public abstract void setMaxPartitions(int i);

    public abstract void setPartition(int i);
}
