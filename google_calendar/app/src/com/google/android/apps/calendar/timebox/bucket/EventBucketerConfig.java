// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import android.util.SparseArray;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timebox.Calendar;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            BirthdayTypeBucketer, HolidayTypeBucketer, IgnoringBucketer, TypeBucketer

public final class EventBucketerConfig
    implements Bucketer.BucketerConfig
{

    private final SparseArray bucketers = new SparseArray(2);

    public EventBucketerConfig()
    {
        bucketers.put(1, new BirthdayTypeBucketer());
        bucketers.put(2, new HolidayTypeBucketer());
        bucketers.put(3, new IgnoringBucketer());
    }

    public final TypeBucketer getBucketer(TimeRangeEntry timerangeentry)
    {
        timerangeentry = ((EventItem)timerangeentry.getValue()).getEvent().getCalendar().getOwnerAccount();
        return (TypeBucketer)bucketers.get(CalendarType.calculateType(timerangeentry));
    }

    public final List getBucketers()
    {
        ArrayList arraylist = new ArrayList(bucketers.size());
        for (int i = 0; i < bucketers.size(); i++)
        {
            arraylist.add((TypeBucketer)bucketers.valueAt(i));
        }

        return arraylist;
    }
}
