// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            WeekFragment, GridViewPagerAdapter, GridViewFrame, GridHourView, 
//            GridHourDrawable

final class staticHourView
    implements staticHourView
{

    private final GridHourView staticHourView;
    private final WeekFragment this$0;

    public final GridHourDrawable getCurrentHourDrawable()
    {
        boolean flag1 = true;
        if (staticHourView == null)
        {
            Object obj = GridViewPagerAdapter.getCurrentView(viewPager);
            boolean flag;
            if (obj != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            obj = (GridViewFrame)((View) (obj)).findViewById(0x7f100230);
            if (obj != null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            } else
            {
                return ((GridViewFrame) (obj)).gridHourDrawable;
            }
        } else
        {
            return staticHourView.gridHourDrawable;
        }
    }

    public final String getViewMode()
    {
        return "preferences_value_week_view";
    }

    public final void scrollHorizontally(int i)
    {
        int j = i;
        if (RtlUtils.isLayoutDirectionRtl(getContext()))
        {
            j = -i;
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + j);
    }

    public final void scrollVertically(int i)
    {
        boolean flag1 = true;
        Object obj = GridViewPagerAdapter.getCurrentView(viewPager);
        boolean flag;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        obj = (PagedScrollView)((View) (obj)).findViewById(0x7f10022f);
        if (obj != null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            ((PagedScrollView) (obj)).verticalScrollBy(i);
            return;
        }
    }

    public (GridHourView gridhourview)
    {
        this$0 = WeekFragment.this;
        super();
        staticHourView = gridhourview;
    }
}
