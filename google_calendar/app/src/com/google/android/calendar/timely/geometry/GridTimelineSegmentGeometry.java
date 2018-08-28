// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.geometry;

import android.content.Context;
import android.content.res.Resources;

// Referenced classes of package com.google.android.calendar.timely.geometry:
//            TimelineSegment

public final class GridTimelineSegmentGeometry
{

    public final int chipVerticalSpacing;
    public int gridHourCellHeight;
    public final int gridlineHeight;

    public GridTimelineSegmentGeometry(Context context, int i)
    {
        gridHourCellHeight = i;
        context = context.getResources();
        gridlineHeight = context.getDimensionPixelOffset(0x7f0e01dc);
        chipVerticalSpacing = context.getDimensionPixelSize(0x7f0e0096);
    }

    public final int computeHeight(long l)
    {
        return Math.max(Math.round(((float)l / 60F) * (float)(gridHourCellHeight + gridlineHeight)), Math.round((float)(gridHourCellHeight * 30) / 60F));
    }

    public final int computeItemBottom(TimelineSegment timelinesegment, int i, int j, int k)
    {
        if (timelinesegment.getEndDay() > i)
        {
            i = 1440;
        } else
        {
            i = timelinesegment.getEndTime();
        }
        return Math.max((Math.round((float)(gridHourCellHeight * 30) / 60F) - chipVerticalSpacing) + k, (Math.round((float)i * ((float)(gridHourCellHeight + gridlineHeight) / 60F)) + j) - chipVerticalSpacing);
    }

    public final int computeItemTop(TimelineSegment timelinesegment, int i, int j)
    {
        if (timelinesegment.getStartDay() < i)
        {
            i = 0;
        } else
        {
            i = timelinesegment.getStartTime();
        }
        return Math.min(Math.round((float)(gridHourCellHeight + gridlineHeight) * 24F - (float)(gridHourCellHeight * 30) / 60F), Math.round((float)i * ((float)(gridHourCellHeight + gridlineHeight) / 60F))) + j;
    }
}
