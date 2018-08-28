// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.AutoValue_HolidayKey;
import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import java.util.Arrays;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

final class HolidayTypeBucketer extends TypeBucketer
{

    HolidayTypeBucketer()
    {
    }

    final volatile void addToBucket(Object obj, TimeRangeEntry timerangeentry)
    {
    }

    final TimeRangeEntry finalizeBucket(Object obj)
    {
        return (TimeRangeEntry)obj;
    }

    final int hashEntry(TimeRangeEntry timerangeentry)
    {
        String s = ((EventItem)timerangeentry.getValue()).getEvent().getTitle();
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(timerangeentry.getRange().getStartDay()), Integer.valueOf(timerangeentry.getRange().getEndDay()), s
        });
    }

    final Object newBucket(TimeRangeEntry timerangeentry)
    {
        TimeRange timerange = timerangeentry.getRange();
        String s = ((EventItem)timerangeentry.getValue()).getEvent().getTitle();
        return new AutoValue_TimeRangeEntry(new AutoValue_HolidayKey(timerange.getStartDay(), timerange.getEndDay(), s), (Item)timerangeentry.getValue(), timerangeentry.getRange());
    }
}
