// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            ThreeDayViewFragment, WeekRecyclerView, BaseCalendarFragment

final class this._cls0 extends android.support.v7.widget.Listener
{

    private final ThreeDayViewFragment this$0;

    public final void onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(int i)
    {
        if (i == 0)
        {
            WeekRecyclerView weekrecyclerview = recyclerView;
            boolean flag = RtlUtils.isLayoutDirectionRtl(weekrecyclerview.getContext());
            i = 0x7fffffff;
            int ai[] = new int[2];
            weekrecyclerview.getLocationOnScreen(ai);
            int j;
            int j1;
            if (flag)
            {
                j = ai[0] + weekrecyclerview.getWidth();
            } else
            {
                j = ai[0];
            }
            j1 = weekrecyclerview.getChildCount();
            for (int k = 0; k < j1; k++)
            {
                GridViewFrame gridviewframe = ((_cls6R35E9B6IPBN7D4IILG_0)weekrecyclerview.getChildViewHolder(weekrecyclerview.getChildAt(k))).tent;
                int k1 = gridviewframe.getChildCount();
                int l1 = gridviewframe.getChildrenBeforeGridDayViews();
                int l = 0;
                while (l < k1 - l1) 
                {
                    GridDayView griddayview = (GridDayView)gridviewframe.getChildAt(gridviewframe.getChildrenBeforeGridDayViews() + l);
                    griddayview.getLocationOnScreen(ai);
                    int i1;
                    if (flag)
                    {
                        i1 = ai[0];
                        i1 = (griddayview.getWidth() + i1) - 1;
                    } else
                    {
                        i1 = ai[0] + 1;
                    }
                    if (Math.abs(i1 - j) < Math.abs(i))
                    {
                        i = i1 - j;
                    }
                    l++;
                }
            }

            if (i == 0x7fffffff)
            {
                i = 0;
            }
            if (i != 0)
            {
                weekrecyclerview.smoothScrollBy(i, 0);
            }
            updateAccessibility();
        }
    }

    public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
    {
        if (recyclerView.getChildCount() != 0)
        {
            if ((i = getCurrentJulianDay()) != currentJulianDay)
            {
                currentJulianDay = i;
                mLastSelectedTime.setJulianDaySafe(i);
                CalendarController calendarcontroller = mController;
                Time time = mLastSelectedTime;
                time.writeFieldsToImpl();
                calendarcontroller.setTime(time.impl.toMillis(true));
                allDayArrowView.setJulianDay(i);
                updateMiniMonth(i);
                updateTitle();
                if (recyclerview.getScrollState() == 0)
                {
                    updateAccessibility();
                    return;
                }
            }
        }
    }

    ()
    {
        this$0 = ThreeDayViewFragment.this;
        super();
    }
}
