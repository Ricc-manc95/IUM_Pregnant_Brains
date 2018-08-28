// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid.views;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.timely.gridviews.GridViewFrame;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.grid.views:
//            ProposeNewTimeGridDayView

public class ProposeNewTimeGridViewFrame extends GridViewFrame
{

    public ProposeNewTimeGridDayView proposalView;

    public ProposeNewTimeGridViewFrame(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final int getChildrenBeforeGridDayViews()
    {
        return 1;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        proposalView = (ProposeNewTimeGridDayView)findViewById(0x7f100030);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        proposalView.layout(i, j, k, l);
        super.onLayout(flag, i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        proposalView.measure(i, j);
        super.onMeasure(i, j);
    }
}
