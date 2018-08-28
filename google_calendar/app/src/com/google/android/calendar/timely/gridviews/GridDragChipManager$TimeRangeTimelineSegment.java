// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.geometry.TimelineSegment;

final class 
    implements TimelineSegment
{

    public TimeRange timeRange;

    public final int getEndDay()
    {
        return timeRange.getEndDay();
    }

    public final long getEndMillis()
    {
        return timeRange.getUtcEndMillis();
    }

    public final int getEndTime()
    {
        return timeRange.getEndMinute();
    }

    public final int getStartDay()
    {
        return timeRange.getStartDay();
    }

    public final long getStartMillis()
    {
        return timeRange.getUtcStartMillis();
    }

    public final int getStartTime()
    {
        return timeRange.getStartMinute();
    }

    public final boolean isAllDay()
    {
        return false;
    }

    public final boolean spansAtLeastOneDay()
    {
        return false;
    }

    ()
    {
    }
}
