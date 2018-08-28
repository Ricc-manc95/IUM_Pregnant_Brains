// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import android.graphics.Rect;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

final class MonthViewport
{

    public final ObservableReference changeObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(this);
    public YearMonth end;
    private final int headerHeight;
    public int height;
    private final int horizontalPadding;
    public final boolean isRtl;
    private final ObservableReference shouldShowWeekNumbers;
    public YearMonth start;
    public double startFraction;
    private final float weekNumberColumnWidth;
    public int width;
    public final YearMonthHelper yearMonthHelper;

    MonthViewport(YearMonthHelper yearmonthhelper, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, LayoutDimens layoutdimens, DimensConverter dimensconverter)
    {
        yearMonthHelper = yearmonthhelper;
        isRtl = ((Boolean)observablereference.get()).booleanValue();
        shouldShowWeekNumbers = observablereference2;
        float f;
        int i;
        if ((ScreenType)observablereference1.get() == ScreenType.PHONE)
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
            f = 32F;
        } else
        {
            f = 24F;
        }
        headerHeight = dimensconverter.getPixelSize(f);
        horizontalPadding = dimensconverter.getPixelOffset(14F);
        weekNumberColumnWidth = layoutdimens.monthWeekNumberColumnWidth();
        yearmonthhelper = yearmonthhelper.createForMs(((Long)observablereference3.get()).longValue());
        i = yearmonthhelper.getYear();
        startFraction = yearmonthhelper.getMonth() + i * 12;
        i = (int)Math.floor(startFraction);
        start = new AutoValue_YearMonth(i / 12, i % 12);
        i = (int)Math.ceil(startFraction);
        end = new AutoValue_YearMonth(i / 12, i % 12);
    }

    final float projectSnapDistance(int i)
    {
        boolean flag = true;
        double d;
        boolean flag1;
        if (i > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 != isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            d = Math.ceil(startFraction);
        } else
        {
            d = Math.floor(startFraction);
        }
        return (float)((double)(int)d - startFraction);
    }

    final void projectYearMonth(YearMonth yearmonth, Rect rect)
    {
label0:
        {
            int i = yearmonth.getYear();
            int l = yearmonth.getMonth();
            YearMonth yearmonth1 = start;
            int k1 = yearmonth1.getYear();
            if (i * 12 + l >= yearmonth1.getMonth() + k1 * 12)
            {
                int j = yearmonth.getYear();
                int i1 = yearmonth.getMonth();
                YearMonth yearmonth2 = end;
                int l1 = yearmonth2.getYear();
                if (j * 12 + i1 <= yearmonth2.getMonth() + l1 * 12)
                {
                    break label0;
                }
            }
            rect.set(0, 0, 0, 0);
            return;
        }
        int j1 = Math.round((float)((double)(yearmonth.getYear() * 12 + yearmonth.getMonth()) - startFraction) * (float)width);
        int k;
        int i2;
        int j2;
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            k = (int)weekNumberColumnWidth;
        } else
        {
            k = 0;
        }
        k += horizontalPadding;
        i2 = horizontalPadding;
        j2 = headerHeight;
        if (isRtl)
        {
            rect.set(i2 + -j1, j2, width - j1 - k, height);
            return;
        } else
        {
            rect.set(k + j1, j2, (j1 + width) - i2, height);
            return;
        }
    }

    final void setFraction(double d)
    {
        startFraction = d;
        int i = (int)Math.floor(d);
        start = new AutoValue_YearMonth(i / 12, i % 12);
        i = (int)Math.ceil(d);
        end = new AutoValue_YearMonth(i / 12, i % 12);
        changeObservable.set(this);
    }
}
