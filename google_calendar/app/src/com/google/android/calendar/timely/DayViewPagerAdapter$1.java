// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.ViewTreeObserver;
import com.google.android.calendar.timely.gridviews.GridDayView;

// Referenced classes of package com.google.android.calendar.timely:
//            PagedDayView, DayViewPagerAdapter, PagedScrollView

final class val.view
    implements android.view.DrawListener
{

    private final DayViewPagerAdapter this$0;
    private final PagedDayView val$view;

    public final boolean onPreDraw()
    {
label0:
        {
            int i;
            if (!val$view.gridDayView.isLayoutRequested())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                val$view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (!setNextScrollPositionManually)
                {
                    break label0;
                }
                Object obj = DayViewPagerAdapter.this;
                PagedDayView pageddayview = val$view;
                i = pageddayview.getGridHourStartOffset();
                i = pageddayview.pagedScrollView.computeScrollPositionFromBottom(i * -1);
                Manager manager1 = ((DayViewPagerAdapter) (obj)).scrollManager;
                PagedScrollView pagedscrollview = pageddayview.pagedScrollView;
                if (i != manager1.verticalScrollPositionFromBottom)
                {
                    manager1.verticalScrollPositionFromBottom = i;
                    manager1.notifyListeners(pagedscrollview);
                }
                obj = ((DayViewPagerAdapter) (obj)).scrollManager;
                pageddayview.pagedScrollView.setVerticalScrollPositionFromBottom(((Manager) (obj)).verticalScrollPositionFromBottom, false);
                setNextScrollPositionManually = false;
            }
            return true;
        }
        if (val$view.position == manualScrollPosition)
        {
            manuallySetScrollPosition(val$view, manualScrollPositionTime);
            DayViewPagerAdapter dayviewpageradapter = DayViewPagerAdapter.this;
            com.google.android.calendar.time.Time time = manualScrollPositionTime;
            dayviewpageradapter.manualScrollPosition = -1;
            dayviewpageradapter.manualScrollPositionTime = time;
            return true;
        } else
        {
            Manager manager = scrollManager;
            val$view.pagedScrollView.setVerticalScrollPositionFromBottom(manager.verticalScrollPositionFromBottom, false);
            return true;
        }
    }

    Manager()
    {
        this$0 = final_dayviewpageradapter;
        val$view = PagedDayView.this;
        super();
    }
}
