// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPager;

// Referenced classes of package com.google.android.calendar.timely:
//            BaseCalendarFragment, TimelyMonthPagerAdapter, TimelyMonthViewPager

final class this._cls0
    implements android.support.v4.view.istener
{

    private final BaseCalendarFragment this$0;
    private boolean wasDragging;

    public final void onPageScrollStateChanged(int i)
    {
        if (i == 1)
        {
            wasDragging = true;
        } else
        if (i == 0 && wasDragging)
        {
            wasDragging = false;
            com.android.datetimepicker.date.y y = TimelyMonthPagerAdapter.getDayFromPosition(mTimelyMonthViewPager.getCurrentItem());
            com.android.datetimepicker.date.y y1 = mTimelyMonthViewPager.currentDay;
            if (y1.month == y.month && y1.year == y.year)
            {
                y = y1;
            }
            onDayOfMonthSelected(y);
            return;
        }
    }

    public final void onPageScrolled(int i, float f, int j)
    {
    }

    public final void onPageSelected(int i)
    {
    }

    ()
    {
        this$0 = BaseCalendarFragment.this;
        super();
    }
}
