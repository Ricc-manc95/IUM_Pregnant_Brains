// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.android.calendar.timely.gridviews.GridHourDrawable;

// Referenced classes of package com.google.android.calendar.timely:
//            ThreeDayViewFragment, WeekRecyclerAdapter, PagedScrollView

final class hourDrawable
    implements com.google.android.calendar.timely.gridviews.le
{

    private final GridHourDrawable hourDrawable;
    private final ThreeDayViewFragment this$0;

    public final GridHourDrawable getCurrentHourDrawable()
    {
        return hourDrawable;
    }

    public final String getViewMode()
    {
        return "preference_value_3_day_view";
    }

    public final void scrollHorizontally(int i)
    {
        recyclerView.smoothScrollBy((recyclerAdapter.pageWidth / 7) * i, 0);
    }

    public final void scrollVertically(int i)
    {
        boolean flag;
        if (layoutManager.() > 0)
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
        View view = layoutManager.(0).findViewById(0x7f10022f);
        if (!(view instanceof PagedScrollView))
        {
            throw new IllegalStateException();
        } else
        {
            ((PagedScrollView)view).verticalScrollBy(i);
            return;
        }
    }

    public (GridHourDrawable gridhourdrawable)
    {
        this$0 = ThreeDayViewFragment.this;
        super();
        hourDrawable = gridhourdrawable;
    }
}
