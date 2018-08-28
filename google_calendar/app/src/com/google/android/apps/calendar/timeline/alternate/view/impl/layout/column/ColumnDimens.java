// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport

final class ColumnDimens
{

    public final int allDayItemHeightPx;
    public final int allDayRowHeightPx;
    public final int allDayTopPx;
    public final int columnStartMarginPx;
    public final int dayHeaderTopMarginPx;
    public final int eventBottomPaddingPx;
    public final int eventStartPaddingPx;
    private final LayoutDimens layoutDimens;
    public final int multiDayGridStartMarginPx;
    public final int multiDayHeaderStartMarginPx;
    public final float nowLineRadius;
    public final int oneDayGridStartMarginPx;
    public final int oneDayHeaderStartMarginPx;

    ColumnDimens(ObservableReference observablereference, ObservableReference observablereference1, DimensConverter dimensconverter, LayoutDimens layoutdimens)
    {
        float f;
        int i;
        layoutDimens = layoutdimens;
        multiDayHeaderStartMarginPx = dimensconverter.getPixelOffset(8F);
        oneDayHeaderStartMarginPx = dimensconverter.getPixelOffset(19F);
        dayHeaderTopMarginPx = dimensconverter.getPixelSize(10F);
        eventBottomPaddingPx = dimensconverter.getPixelOffset(2.0F);
        if (((Boolean)observablereference1.get()).booleanValue())
        {
            int j;
            if ((ScreenType)observablereference.get() == ScreenType.PHONE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_349;
            }
        }
        f = 2.0F;
_L1:
        j = dimensconverter.getPixelOffset(f);
        allDayTopPx = Math.round(layoutdimens.converter.dpToPx(60F));
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f = 22F;
        } else
        {
            if (((Boolean)observablereference1.get()).booleanValue())
            {
                i = 26;
            } else
            {
                i = 21;
            }
            f = i;
        }
        allDayItemHeightPx = dimensconverter.getPixelSize(f);
        allDayRowHeightPx = allDayItemHeightPx + j;
        eventStartPaddingPx = dimensconverter.getPixelOffset(2.0F);
        columnStartMarginPx = dimensconverter.getPixelOffset(4F) - eventStartPaddingPx;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f = 48F;
        } else
        {
            f = 40F;
        }
        multiDayGridStartMarginPx = dimensconverter.getPixelOffset(f);
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f = 100F;
        } else
        {
            f = 72F;
        }
        oneDayGridStartMarginPx = dimensconverter.getPixelSize(f) - eventStartPaddingPx;
        nowLineRadius = layoutdimens.converter.dpToPx(5F);
        return;
        f = 3F;
          goto _L1
    }

    final int columnEndInset(ColumnViewport columnviewport)
    {
        int i = layoutDimens.scheduleChipEndMargin();
        float f = columnviewport.oneDayRatio;
        float f1 = i - 0;
        return Math.round((float)0 + f1 * f) + 0;
    }

    final int columnStartInset(ColumnViewport columnviewport, boolean flag)
    {
        int i = 0;
        int j = oneDayGridStartMarginPx;
        float f = columnviewport.oneDayRatio;
        j = Math.round((float)(j - 0) * f + (float)0);
        if (!flag)
        {
            i = columnStartMarginPx;
            float f1 = columnviewport.oneDayRatio;
            i = Math.round((float)(0 - i) * f1 + (float)i);
        }
        return i + (j + 0);
    }
}
