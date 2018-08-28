// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.google.android.calendar.timely.gridviews.GridHourDrawable;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            DualTimelineGridFragment, TimelyDayViewPager, PagedDayView, PagedScrollView

final class this._cls0
    implements com.google.android.calendar.timely.gridviews._E9__0A_
{

    private final DualTimelineGridFragment this$0;

    public final GridHourDrawable getCurrentHourDrawable()
    {
        Object obj = dayPager;
        obj = ((TimelyDayViewPager) (obj)).getChildAtPosition(((ViewPager) (obj)).getCurrentItem());
        if (((PagedDayView) (obj)).gridViewFrame == null)
        {
            return null;
        } else
        {
            return ((PagedDayView) (obj)).gridViewFrame.gridHourDrawable;
        }
    }

    public final String getViewMode()
    {
        return "preference_value_hourly_view";
    }

    public final void scrollHorizontally(int i)
    {
        int j = i;
        if (RtlUtils.isLayoutDirectionRtl(getContext()))
        {
            j = -i;
        }
        dayPager.setCurrentItem(dayPager.getCurrentItem() + j);
    }

    public final void scrollVertically(int i)
    {
        Object obj = dayPager;
        obj = ((TimelyDayViewPager) (obj)).getChildAtPosition(((ViewPager) (obj)).getCurrentItem()).pagedScrollView;
        this._cls0 _lcls0 = ((PagedScrollView) (obj)).scrollManager;
        i = ((PagedScrollView) (obj)).getVerticalScrollPositionFromBottom() + i;
        if (i != _lcls0.itionFromBottom)
        {
            _lcls0.itionFromBottom = i;
            _lcls0.itionFromBottom(null);
        }
    }

    ()
    {
        this$0 = DualTimelineGridFragment.this;
        super();
    }
}
