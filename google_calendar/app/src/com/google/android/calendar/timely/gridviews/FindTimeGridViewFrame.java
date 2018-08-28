// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridViewFrame, FindTimeGridDayView

public class FindTimeGridViewFrame extends GridViewFrame
{

    public FindTimeGridDayView suggestionGridDayView;

    public FindTimeGridViewFrame(Context context, AttributeSet attributeset)
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
        suggestionGridDayView = (FindTimeGridDayView)findViewById(0x7f1001a0);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        suggestionGridDayView.layout(i, j, k, l);
        super.onLayout(flag, i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        suggestionGridDayView.measure(i, j);
        super.onMeasure(i, j);
    }
}
