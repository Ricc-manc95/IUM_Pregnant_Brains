// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

public final class arg._cls1
    implements com.google.android.calendar.utils.collection.arg._cls1
{

    private final GridDayView arg$1;

    public final int onFold(Object obj, int i)
    {
        GridDayView griddayview = arg$1;
        obj = (Chip)obj;
        return Math.min(griddayview.timelineSegmentGeometry.computeItemTop(((com.google.android.calendar.timely.geometry.TimelineSegment) (obj)), griddayview.julianDay, 0), i);
    }

    public etry(GridDayView griddayview)
    {
        arg$1 = griddayview;
    }
}
