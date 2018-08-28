// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Predicate;

final class arg._cls2
    implements Predicate
{

    private final int arg$1;
    private final int arg$2;

    public final boolean apply(Object obj)
    {
        int i = arg$1;
        int j = arg$2;
        obj = (TimeRangeEntry)obj;
        return ((TimeRangeEntry) (obj)).getRange().getEndDay() >= i || ((TimeRangeEntry) (obj)).getRange().getStartDay() <= j;
    }

    (int i, int j)
    {
        arg$1 = i;
        arg$2 = j;
    }
}
