// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.support.v4.view.ViewPager;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthViewPagerAdapter, MiniMonthControllerImpl, MiniMonthViewPager

final class val.viewPager extends android.support.v4.view.geListener
{

    private final MiniMonthControllerImpl this$0;
    private final MiniMonthViewPager val$viewPager;

    public final void onPageSelected(int i)
    {
        i = val$viewPager.getCurrentItem();
        Object obj = MiniMonthViewPagerAdapter.MIN_MONTH;
        int j = ((YearMonth) (obj)).getYear();
        i += ((YearMonth) (obj)).getMonth() + j * 12;
        obj = new AutoValue_YearMonth(i / 12, i % 12);
        MiniMonthControllerImpl minimonthcontrollerimpl = MiniMonthControllerImpl.this;
        if (minimonthcontrollerimpl.onMonthChangedListener != null && !obj.equals(minimonthcontrollerimpl.lastMonth))
        {
            minimonthcontrollerimpl.onMonthChangedListener.onMonthChanged(((YearMonth) (obj)).getYear(), ((YearMonth) (obj)).getMonth());
            minimonthcontrollerimpl.lastMonth = ((YearMonth) (obj));
            minimonthcontrollerimpl.requestFocus();
        }
    }

    MonthChangedListener()
    {
        this$0 = final_minimonthcontrollerimpl;
        val$viewPager = MiniMonthViewPager.this;
        super();
    }
}
