// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.interaction.swipe.SwipeUtils;

final class ConfigProvider
    implements ConfigProvider
{

    public final int getSupportedSwipeDirections(TimelineItem timelineitem)
    {
        return SwipeUtils.getSupportedSwipeDirections(timelineitem);
    }

    public final Integer getSwipeIcon(TimelineItem timelineitem, int i)
    {
        return SwipeUtils.getSwipeIcon(timelineitem, i);
    }

    ConfigProvider()
    {
    }
}
