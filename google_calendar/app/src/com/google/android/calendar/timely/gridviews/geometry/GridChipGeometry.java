// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.android.calendar.utils.Holder;
import com.google.android.calendar.utils.NumberUtils;
import com.google.common.collect.Lists;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            PositionOnGrid, HorizontalChipGeometry

public final class GridChipGeometry
{

    private final int chipHorizontalSpacing;
    private final HorizontalChipGeometry horizontalGeometry;
    public int julianDay;
    private final int startPadding;
    private final GridTimelineSegmentGeometry verticalGeometry;

    public GridChipGeometry(Context context, GridTimelineSegmentGeometry gridtimelinesegmentgeometry, HorizontalChipGeometry horizontalchipgeometry)
    {
        verticalGeometry = gridtimelinesegmentgeometry;
        horizontalGeometry = horizontalchipgeometry;
        context = context.getResources();
        startPadding = context.getDimensionPixelOffset(0x7f0e01db);
        chipHorizontalSpacing = context.getDimensionPixelOffset(0x7f0e0094);
    }

    public final boolean getChipFrame(View view, int i, boolean flag, Rect rect)
    {
        if (((TimelineSegment)view).getStartDay() > julianDay || ((TimelineSegment)view).getEndDay() < julianDay)
        {
            return false;
        }
        PositionOnGrid positionongrid = (PositionOnGrid)((Holder)view.getLayoutParams()).get();
        int i1 = chipHorizontalSpacing;
        int j = startPadding - i1;
        int j1 = i - j;
        int k = verticalGeometry.computeItemTop((TimelineSegment)view, julianDay, 0);
        int l = verticalGeometry.computeItemBottom((TimelineSegment)view, julianDay, 0, k);
        j = j + i1 + Math.round((float)j1 * positionongrid.startFraction);
        float f = j1;
        i1 = Math.round((positionongrid.endFraction - positionongrid.startFraction) * f) - i1;
        if (flag)
        {
            j = i - j - i1;
        }
        rect.set(NumberUtils.clamp(j, 0, i), k, NumberUtils.clamp(j + i1, 0, i), l);
        return true;
    }

    public final void updateChipsLayoutParams(Iterable iterable)
    {
        for (Iterator iterator = iterable.iterator(); iterator.hasNext();)
        {
            Object obj = (View)iterator.next();
            PositionOnGrid positionongrid = (PositionOnGrid)((Holder)((View) (obj)).getLayoutParams()).get();
            TimelineSegment timelinesegment = (TimelineSegment)obj;
            int i = Math.min(timelinesegment.getStartTime(), 1410);
            if (timelinesegment.getStartDay() < julianDay)
            {
                i = 0;
            }
            positionongrid.topMinutes = i;
            obj = (TimelineSegment)obj;
            int j = ((TimelineSegment) (obj)).getEndTime();
            i = Math.min(((TimelineSegment) (obj)).getStartTime(), 1410);
            if (((TimelineSegment) (obj)).getStartDay() < julianDay)
            {
                i = 0;
            }
            i = NumberUtils.clamp(j, i + 30, 1440);
            if (((TimelineSegment) (obj)).getEndDay() > julianDay)
            {
                i = 1440;
            }
            positionongrid.bottomMinutes = i;
        }

        horizontalGeometry.layoutChipsHorizontally(Lists.newArrayList(iterable));
    }
}
