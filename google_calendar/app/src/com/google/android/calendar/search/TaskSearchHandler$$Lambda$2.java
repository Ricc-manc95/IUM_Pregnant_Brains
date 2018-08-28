// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final TimeBoxToTimelineAdapter arg$1;

    public final Object apply(Object obj)
    {
        return arg$1.createTimelineItem((TimeRangeEntry)obj);
    }

    apter(TimeBoxToTimelineAdapter timeboxtotimelineadapter)
    {
        arg$1 = timeboxtotimelineadapter;
    }
}
