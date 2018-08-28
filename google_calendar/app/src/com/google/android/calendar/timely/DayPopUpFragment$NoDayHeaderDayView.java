// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, TimelyDayViewResources, NowLineDrawable, DayViewConfig

final class chipMarginHorizontal extends chipMarginHorizontal
{

    private final int chipMarginHorizontal;

    protected final int getBottomPadding()
    {
        if (super.hasItems)
        {
            return super.resources.defaultMargin - super.resources.chipVerticalSpacing;
        } else
        {
            return 0;
        }
    }

    public final int getEventLayoutEndX()
    {
        return super.viewWidth - chipMarginHorizontal;
    }

    public final int getEventLayoutStartX()
    {
        return chipMarginHorizontal;
    }

    protected final int getNowLineMarginStart()
    {
        return chipMarginHorizontal - (int)super.nowLineDrawable.radius;
    }

    public (Context context, Recycler recycler, DayViewConfig dayviewconfig, OverlayFragment overlayfragment)
    {
        super(context, recycler, dayviewconfig, overlayfragment);
        chipMarginHorizontal = context.getResources().getDimensionPixelSize(0x7f0e00da);
    }
}
