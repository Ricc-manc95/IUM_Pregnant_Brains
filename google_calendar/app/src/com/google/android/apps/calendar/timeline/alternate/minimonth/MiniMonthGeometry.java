// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;

final class MiniMonthGeometry
{

    private final AlternateCalendarHelper alternateCalendarHelper;
    public float alternateLineHeightPx;
    public final float bottomPaddingPx;
    public Rect bounds;
    public float columnSpacingPx;
    public final float columnWidthPx;
    private final DimensConverter dimensConverter;
    private final ObservableReference isPortrait;
    private final ObservableReference isRtl;
    public float rowSizePx;
    private final ObservableReference screenType;
    private final ObservableReference shouldShowWeekNumbers;
    public float sidePaddingPx;
    public final TimeUtils timeUtils;
    public float weekNumPaddingPx;
    public final float weekdayHeaderHeightPx;

    MiniMonthGeometry(Context context, DimensConverter dimensconverter, TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, 
            AlternateCalendarHelper alternatecalendarhelper)
    {
        byte byte0 = 36;
        super();
        dimensConverter = dimensconverter;
        timeUtils = timeutils;
        isRtl = observablereference;
        screenType = observablereference1;
        isPortrait = observablereference2;
        shouldShowWeekNumbers = observablereference3;
        alternateCalendarHelper = alternatecalendarhelper;
        computeRowDimens(context);
        weekdayHeaderHeightPx = dimensconverter.dpToPx(36F);
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
            if (observablereference1.get() == ScreenType.LARGE_TABLET)
            {
                i = 40;
            } else
            {
                i = 36;
            }
            f = i;
        } else
        {
            f = 32F;
        }
        columnWidthPx = dimensconverter.dpToPx(f);
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
            if (((Boolean)observablereference2.get()).booleanValue())
            {
                i = byte0;
            } else
            {
                i = 1;
            }
            f = i;
        } else
        {
            f = 16F;
        }
        bottomPaddingPx = dimensconverter.dpToPx(f);
    }

    final void computeRowDimens(Context context)
    {
        float f;
        boolean flag;
        if (alternateCalendarHelper.isEnabled())
        {
            f = dimensConverter.dpToPx(12F);
        } else
        {
            f = 0.0F;
        }
        alternateLineHeightPx = f;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            context = dimensConverter;
            float f1;
            if (screenType.get() == ScreenType.LARGE_TABLET)
            {
                f = 40F;
            } else
            {
                f = 36F;
            }
            f1 = context.dpToPx(f);
            context = dimensConverter;
            if (((Boolean)isPortrait.get()).booleanValue())
            {
                f = 24F;
            } else
            {
                f = 12F;
            }
            rowSizePx = context.dpToPx(f) + f1;
            return;
        } else
        {
            rowSizePx = Math.max(alternateLineHeightPx + dimensConverter.dpToPx(36F), context.getResources().getDisplayMetrics().heightPixels / 20);
            return;
        }
    }

    final float getLeft(int i)
    {
        return toLeft(sidePaddingPx + weekNumPaddingPx + (columnWidthPx + columnSpacingPx) * (float)i + columnWidthPx / 2.0F);
    }

    final float toLeft(float f)
    {
        float f1 = f;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            f1 = (float)bounds.width() - f;
        }
        return f1;
    }

    final void updateDrawDimens(Rect rect)
    {
        float f = 0.0F;
        float f1 = 0.0F;
        bounds = rect;
        boolean flag;
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            DimensConverter dimensconverter = dimensConverter;
            if (((Boolean)isPortrait.get()).booleanValue())
            {
                f = 32F;
            } else
            {
                f = 8F;
            }
            columnSpacingPx = dimensconverter.dpToPx(f);
            if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
            {
                f = columnWidthPx + columnSpacingPx;
            } else
            {
                f = 0.0F;
            }
            weekNumPaddingPx = f;
            if (((Boolean)isPortrait.get()).booleanValue())
            {
                f = weekNumPaddingPx;
                f1 = columnWidthPx;
                float f2 = columnSpacingPx;
                sidePaddingPx = ((float)rect.width() - (f + f1 * 7F + f2 * 6F)) / 2.0F;
                return;
            }
            rect = dimensConverter;
            f = f1;
            if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
            {
                f = 12F;
            }
            sidePaddingPx = rect.dpToPx(f);
            return;
        }
        int i;
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i += 7;
        sidePaddingPx = dimensConverter.dpToPx(12F);
        columnSpacingPx = ((float)rect.width() - sidePaddingPx * 2.0F - columnWidthPx * (float)i) / (float)(i - 1);
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            f = columnWidthPx;
            f = columnSpacingPx + f;
        }
        weekNumPaddingPx = f;
    }
}
