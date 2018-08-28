// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskSet

final class arg._cls1
    implements Predicate
{

    private final int arg$1;

    public final boolean apply(Object obj)
    {
        int i = arg$1;
        obj = (TimeRangeEntry)obj;
        return ((TimeRangeEntry) (obj)).getRange().getStartDay() == i && ((TimeRangeEntry) (obj)).getRange().isAllDay() && !((TaskSet)((TimeRangeEntry) (obj)).getValue()).isDone();
    }

    (int i)
    {
        arg$1 = i;
    }
}
