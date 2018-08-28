// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.res.Resources;
import android.graphics.Point;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.dnd.DragChipFactory;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.concurrent.TimeUnit;

public final class GridViewDndHelper
    implements com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener
{

    private final Recycler chipRecycler;
    private final TimelineItemCollection collection;
    private final Delegate _flddelegate;
    public int julianDay;
    private final GridTimelineSegmentGeometry timelineSegmentGeometry;

    public GridViewDndHelper(TimelineItemCollection timelineitemcollection, Delegate delegate1, GridTimelineSegmentGeometry gridtimelinesegmentgeometry, Recycler recycler)
    {
        if (timelineitemcollection == null)
        {
            throw new NullPointerException();
        }
        collection = (TimelineItemCollection)timelineitemcollection;
        if (delegate1 == null)
        {
            throw new NullPointerException();
        }
        _flddelegate = (Delegate)delegate1;
        if (gridtimelinesegmentgeometry == null)
        {
            throw new NullPointerException();
        }
        timelineSegmentGeometry = (GridTimelineSegmentGeometry)gridtimelinesegmentgeometry;
        if (recycler == null)
        {
            throw new NullPointerException();
        } else
        {
            chipRecycler = (Recycler)recycler;
            return;
        }
    }

    public final boolean onChipLongPress(Chip chip, Point point)
    {
        CreateNewEventView.removeSelectedTime();
        TimelineItem timelineitem = collection.getItemForChip(chip);
        Delegate delegate1 = _flddelegate;
        Object obj = timelineitem.getTimeRange();
        ChipViewModel chipviewmodel = chip.viewModel;
        int i;
        int j;
        if (((TimeRange) (obj)).getLocalEndMillis() - ((TimeRange) (obj)).getLocalStartMillis() <= 0x1b7740L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj = chipviewmodel.toBuilder().setOuterBorderColor(0).build();
        if (i == 0 && ((ChipViewModel) (obj)).getVerticalAlignType() != 0)
        {
            obj = ((ChipViewModel) (obj)).toBuilder().setVerticalAlignType(0).build();
        }
        obj = new DragChipFactory(chipRecycler, ((ChipViewModel) (obj)), chip.getResources().getDimensionPixelOffset(0x7f0e011a), chip.textIconScale);
        j = chip.getHeight();
        i = point.y;
        if (timelineitem.getStartDay() < julianDay)
        {
            long l = TimeUnit.MILLISECONDS.toMinutes(timelineitem.getEndMillis() - timelineitem.getStartMillis());
            i += timelineSegmentGeometry.computeHeight(l) - j;
        }
        if (delegate1.startDnd(chip, ((DragChipFactory) (obj)), timelineitem, i))
        {
            chip.performHapticFeedback(0);
        }
        return true;
    }

    private class Delegate
    {

        public abstract boolean startDnd(View view, DragChipFactory dragchipfactory, TimelineItem timelineitem, int i);
    }

}
