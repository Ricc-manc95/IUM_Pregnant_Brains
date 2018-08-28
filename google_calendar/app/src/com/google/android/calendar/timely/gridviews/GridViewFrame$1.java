// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timely.PagedScrollView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewFrame, GridDrawable, GridDayView

public final class this._cls0
    implements Runnable
{

    private final GridViewFrame this$0;

    public final void run()
    {
        if (todayIndex < 0) goto _L2; else goto _L1
_L1:
        GridViewFrame gridviewframe;
        int i;
        i = todayIndex;
        gridviewframe = GridViewFrame.this;
        if (i >= gridviewframe.getChildCount() - gridviewframe.getChildrenBeforeGridDayViews()) goto _L2; else goto _L3
_L3:
        float f = getNowLinePosition();
        GridDrawable griddrawable = gridDrawable;
        i = griddrawable.intervalHeight;
        i = (int)(f - (float)(griddrawable.gridLineHeight + i));
_L5:
        ((PagedScrollView)getParent()).scrollTo(0, i);
        return;
_L2:
        int j = 0;
        i = 0x7fffffff;
        do
        {
            GridViewFrame gridviewframe1 = GridViewFrame.this;
            if (j >= gridviewframe1.getChildCount() - gridviewframe1.getChildrenBeforeGridDayViews())
            {
                break;
            }
            gridviewframe1 = GridViewFrame.this;
            i = Math.min(((GridDayView)gridviewframe1.getChildAt(gridviewframe1.getChildrenBeforeGridDayViews() + j)).getFirstChipTopCoordinate(), i);
            j++;
        } while (true);
        if (i == 0x7fffffff)
        {
            GridDrawable griddrawable1 = gridDrawable;
            i = griddrawable1.intervalHeight;
            i = (int)(8F * (float)(griddrawable1.gridLineHeight + i));
        } else
        {
            GridDrawable griddrawable2 = gridDrawable;
            int k = griddrawable2.intervalHeight;
            i -= (int)(float)(griddrawable2.gridLineHeight + k);
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public ()
    {
        this$0 = GridViewFrame.this;
        super();
    }
}
