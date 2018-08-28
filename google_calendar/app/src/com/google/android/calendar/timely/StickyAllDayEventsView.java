// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.graphics.Rect;
import android.widget.FrameLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus;
import java.util.Map;

public final class StickyAllDayEventsView extends FrameLayout
{

    public int agendaYEnd;
    public int agendaYStart;
    public final TimelineAgendaGridAnimationStatus animationStatus;
    private final ChipScheduleGridAnimationHelper chipAnimationHelper;
    public int gridHeight;
    public int gridYStart;
    public int scrollOffset;

    public StickyAllDayEventsView(Context context, TimelineAgendaGridAnimationStatus timelineagendagridanimationstatus, ChipScheduleGridAnimationHelper chipschedulegridanimationhelper)
    {
        super(context);
        setBackgroundColor(0);
        animationStatus = timelineagendagridanimationstatus;
        chipAnimationHelper = chipschedulegridanimationhelper;
        setClipChildren(false);
    }

    protected final void onMeasure(int i, int j)
    {
        float f = animationStatus.getGridModeRatio();
        j = agendaYStart;
        int k = gridYStart;
        float f1 = j;
        j = (int)((float)(k - j) * f + f1);
        k = agendaYEnd;
        int l = gridYStart;
        int i1 = gridHeight;
        f1 = k;
        super.onMeasure(i, android.view.View.MeasureSpec.makeMeasureSpec((int)(f * (float)((l + i1) - k) + f1) - j, 0x40000000));
    }

    public final void setGridHeight(int i)
    {
        gridHeight = i;
    }

    public final void setGridOffset(int i)
    {
        gridYStart = i;
    }

    public final void setGridScrollPosition(int i)
    {
        scrollOffset = i;
        i = 0;
        while (i < getChildCount()) 
        {
            Object obj = chipAnimationHelper;
            Chip chip = (Chip)getChildAt(i);
            int j = -scrollOffset;
            if (!((ChipScheduleGridAnimationHelper) (obj)).chips.containsKey(chip))
            {
                LogUtils.e(ChipScheduleGridAnimationHelper.TAG, "setChipGridModeHorizontalOffset for chip not present.", new Object[0]);
            } else
            {
                obj = ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)((ChipScheduleGridAnimationHelper) (obj)).chips.get(chip)).gridCoordinates;
                obj.left = ((Rect) (obj)).left + j;
                obj.right = ((Rect) (obj)).right + j;
            }
            i++;
        }
    }
}
