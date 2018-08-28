// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;

public final class TimeBoxItemAdapter
    implements ItemAdapter
{

    public TimeBoxItemAdapter()
    {
    }

    public final int compareType(Object obj, Object obj1)
    {
        obj = (TimeRangeEntry)obj;
        obj1 = (TimeRangeEntry)obj1;
        return ((Item)((TimeRangeEntry) (obj)).getValue()).getSortType().compareTo(((Item)((TimeRangeEntry) (obj1)).getValue()).getSortType());
    }

    public final int getEndDay(Object obj)
    {
        return ((TimeRangeEntry)obj).getRange().getEndDay();
    }

    public final Object getKey(Object obj)
    {
        return ((TimeRangeEntry)obj).getKey();
    }

    public final long getLocalEndMillis(Object obj)
    {
        return ((TimeRangeEntry)obj).getRange().getLocalEndMillis();
    }

    public final long getLocalStartMillis(Object obj)
    {
        return ((TimeRangeEntry)obj).getRange().getLocalStartMillis();
    }

    public final int getStartDay(Object obj)
    {
        return ((TimeRangeEntry)obj).getRange().getStartDay();
    }

    public final boolean isAllDay(Object obj)
    {
        return ((TimeRangeEntry)obj).getRange().isAllDay();
    }

    public final Object moveTime(Object obj, long l)
    {
        obj = (TimeRangeEntry)obj;
        TimeRange timerange = ((TimeRangeEntry) (obj)).getRange();
        boolean flag;
        if (!timerange.isAllDay())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Not yet supported"));
        } else
        {
            l -= timerange.getLocalStartMillis();
            timerange = TimeRange.newNonAllDay(timerange.getTimeZone(), timerange.getLocalStartMillis() + l, l + timerange.getLocalEndMillis());
            return new AutoValue_TimeRangeEntry(((TimeRangeEntry) (obj)).getKey(), (Item)((TimeRangeEntry) (obj)).getValue(), timerange);
        }
    }
}
