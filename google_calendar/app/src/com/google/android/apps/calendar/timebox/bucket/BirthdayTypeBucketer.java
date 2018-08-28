// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.AutoValue_BirthdaySet_Key;
import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

final class BirthdayTypeBucketer extends TypeBucketer
{

    BirthdayTypeBucketer()
    {
    }

    final void addToBucket(Object obj, TimeRangeEntry timerangeentry)
    {
        obj = (com.google.common.collect.ImmutableSet.Builder)((com.google.android.apps.calendar.timebox.BirthdaySet.Builder)((TimeRangeEntry)obj).getValue()).itemsBuilder().add((EventItem)timerangeentry.getValue());
    }

    final TimeRangeEntry finalizeBucket(Object obj)
    {
        obj = (TimeRangeEntry)obj;
        return new AutoValue_TimeRangeEntry(((TimeRangeEntry) (obj)).getKey(), ((com.google.android.apps.calendar.timebox.BirthdaySet.Builder)((TimeRangeEntry) (obj)).getValue()).build(), ((TimeRangeEntry) (obj)).getRange());
    }

    final int hashEntry(TimeRangeEntry timerangeentry)
    {
        return timerangeentry.getRange().getStartDay();
    }

    final Object newBucket(TimeRangeEntry timerangeentry)
    {
        com.google.android.apps.calendar.timebox.AutoValue_BirthdaySet.Builder builder = new com.google.android.apps.calendar.timebox.AutoValue_BirthdaySet.Builder();
        AutoValue_BirthdaySet_Key autovalue_birthdayset_key = new AutoValue_BirthdaySet_Key(timerangeentry.getRange().getStartDay());
        com.google.common.collect.ImmutableSet.Builder builder1 = (com.google.common.collect.ImmutableSet.Builder)builder.itemsBuilder().add((EventItem)timerangeentry.getValue());
        return new AutoValue_TimeRangeEntry(autovalue_birthdayset_key, builder, timerangeentry.getRange());
    }
}
