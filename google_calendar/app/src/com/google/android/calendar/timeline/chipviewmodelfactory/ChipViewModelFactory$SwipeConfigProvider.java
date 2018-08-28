// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineItem;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipViewModelFactory

public static interface 
{

    public abstract int getSupportedSwipeDirections(TimelineItem timelineitem);

    public abstract Integer getSwipeIcon(TimelineItem timelineitem, int i);
}
