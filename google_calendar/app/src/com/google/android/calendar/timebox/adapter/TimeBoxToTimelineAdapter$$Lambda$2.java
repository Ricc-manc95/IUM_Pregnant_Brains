// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox.adapter;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timebox.adapter:
//            TimeBoxToTimelineAdapter

final class arg._cls1
    implements Function
{

    private final TimeBoxToTimelineAdapter arg$1;

    public final Object apply(Object obj)
    {
        return arg$1.createTimelineItem((TimeRangeEntry)obj);
    }

    (TimeBoxToTimelineAdapter timeboxtotimelineadapter)
    {
        arg$1 = timeboxtotimelineadapter;
    }
}
