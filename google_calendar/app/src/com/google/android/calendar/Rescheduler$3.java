// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTask;

public final class eItemOperation extends TimelineItemOperation
{

    private final TimeRange val$timeRange;

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        timelineitem = String.valueOf(timelineitem);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(timelineitem).length() + 18)).append("Unable to update: ").append(timelineitem).toString());
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        timelineevent = (TimelineEvent)timelineevent.clone();
        timelineevent.timeRange = val$timeRange;
        return timelineevent;
    }

    public final Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        timelinetask = (TimelineTask)timelinetask.clone();
        timelinetask.timeRange = val$timeRange;
        return timelinetask;
    }

    public imeRange()
    {
        val$timeRange = timerange;
        super();
    }
}
