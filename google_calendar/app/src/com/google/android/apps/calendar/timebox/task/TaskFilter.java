// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.AutoValue_TimeRangeEntry;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskSet

final class TaskFilter
{

    static TimeRangeEntry newBundleWith(TimeRangeEntry timerangeentry, Iterable iterable)
    {
        iterable = ((TaskSet)timerangeentry.getValue()).toBuilder().setItems(ImmutableList.copyOf(iterable)).build();
        return new AutoValue_TimeRangeEntry(timerangeentry.getKey(), iterable, timerangeentry.getRange());
    }
}
