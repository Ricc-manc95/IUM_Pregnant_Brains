// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.MotionEvent;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, DayViewConfig, TimelyDayViewResources, TimelyDayHeaderView, 
//            OnTimelineGestureListener

final class this._cls0 extends android.view.r
{

    private final TimelyDayView this$0;

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        int j = (int)motionevent.getY();
        if (j >= startOfGrid && dayViewConfig.inGridMode())
        {
            int i = startOfGrid;
            motionevent = timelineSegmentGeometry;
            int k = ((GridTimelineSegmentGeometry) (motionevent)).gridHourCellHeight;
            i = (j - i) / (int)(float)(((GridTimelineSegmentGeometry) (motionevent)).gridlineHeight + k);
            long l1 = getSelectedTimeInMillis(i);
            CreateNewEventView.setSelectedTime(getContext(), julianDay, i);
            if (createNewEventView != null)
            {
                createNewEventView.setStartTime(l1);
                installCreateEventView();
            }
        }
        int l = getMonthHeaderSize();
        int i1 = resources.weekHeaderHeight;
        if (dayViewConfig.inListView())
        {
            motionevent = TimelyDayView.this;
            boolean flag;
            if (((TimelyDayView) (motionevent)).dayViewConfig.shouldDrawExtraHeaders() && ((TimelyDayView) (motionevent)).dayHeaderView.isFirstDayOfWeek)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && j > l && j < l + i1)
            {
                timelineGestureListener.onWeekDividerTap();
            }
        }
        requestLayout();
        return true;
    }

    ()
    {
        this$0 = TimelyDayView.this;
        super();
    }
}
