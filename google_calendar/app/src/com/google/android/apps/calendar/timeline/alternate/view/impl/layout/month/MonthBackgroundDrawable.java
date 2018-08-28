// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import com.google.android.apps.calendar.timeline.alternate.util.AutoValue_YearMonth;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.YearMonthHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.material.Material;
import com.google.common.collect.Range;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month:
//            MonthViewport, WeekdayNames

public final class MonthBackgroundDrawable extends Drawable
{

    private final Paint borderPaint = new Paint();
    private final Calendar calendar;
    private final ObservableReference currentTime;
    private final LayoutDimens dimens;
    private final Paint highlightedWeekdaysPaint;
    private final ObservableReference isRtl;
    private final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    private final MonthViewport viewport;
    private final Paint weekNumberPaint = new Paint();
    private final SparseArray weekNumberStrings = new SparseArray();
    private final WeekdayNames weekdayNames;
    private final int weekdayOffsetVertical;
    private final int weekdayPaddingHorizontal;
    private final Paint weekdaysPaint = new Paint();
    private final int weeksInMonth;
    private final YearMonthHelper yearMonthHelper;

    public MonthBackgroundDrawable(Context context, TimeUtils timeutils, LayoutDimens layoutdimens, MonthViewport monthviewport, YearMonthHelper yearmonthhelper, WeekdayNames weekdaynames, ObservableReference observablereference, 
            int i, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, ObservableReference observablereference4, DimensConverter dimensconverter)
    {
        viewport = monthviewport;
        timeUtils = timeutils;
        dimens = layoutdimens;
        yearMonthHelper = yearmonthhelper;
        weekdayNames = weekdaynames;
        currentTime = observablereference;
        calendar = Calendar.getInstance((TimeZone)timeutils.timeZone.get());
        weeksInMonth = i;
        isRtl = observablereference1;
        shouldShowWeekNumbers = observablereference4;
        float f;
        if ((ScreenType)observablereference2.get() == ScreenType.PHONE)
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
        if (((Boolean)observablereference3.get()).booleanValue())
        {
            int j;
            if (i != 0)
            {
                j = 9;
            } else
            {
                j = 4;
            }
            f = j;
        } else
        {
            f = 12F;
        }
        weekdayPaddingHorizontal = dimensconverter.getPixelOffset(f);
        if (i != 0)
        {
            f = 0.0F;
        } else
        {
            f = 4F;
        }
        weekdayOffsetVertical = dimensconverter.getPixelSize(f);
        weekdaysPaint.setAntiAlias(true);
        weekdaysPaint.setFakeBoldText(true);
        weekdaysPaint.setTextSize(dimensconverter.spToPx(12F));
        weekdaysPaint.setColor(ContextCompat.getColor(context, 0x7f0d021a));
        layoutdimens = weekdaysPaint;
        if (((Boolean)observablereference1.get()).booleanValue())
        {
            timeutils = android.graphics.Paint.Align.RIGHT;
        } else
        {
            timeutils = android.graphics.Paint.Align.LEFT;
        }
        layoutdimens.setTextAlign(timeutils);
        highlightedWeekdaysPaint = new Paint(weekdaysPaint);
        highlightedWeekdaysPaint.setColor(ContextCompat.getColor(context, 0x7f0d01d7));
        layoutdimens = highlightedWeekdaysPaint;
        if (Material.robotoMedium != null)
        {
            timeutils = Material.robotoMedium;
        } else
        {
            timeutils = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = timeutils;
        }
        layoutdimens.setTypeface(timeutils);
        weekNumberPaint.setAntiAlias(true);
        weekNumberPaint.setColor(ContextCompat.getColor(context, 0x7f0d021a));
        timeutils = weekNumberPaint;
        if ((ScreenType)observablereference2.get() == ScreenType.PHONE)
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
            f = 12F;
        } else
        {
            f = 10F;
        }
        timeutils.setTextSize(dimensconverter.spToPx(f));
        weekNumberPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(ContextCompat.getColor(context, 0x7f0d0215));
    }

    public final void draw(Canvas canvas)
    {
        Object obj = getBounds();
        canvas.save();
        canvas.clipRect(((Rect) (obj)).left, ((Rect) (obj)).top, ((Rect) (obj)).right, ((Rect) (obj)).bottom);
        obj = viewport.start;
        int i = ((YearMonth) (obj)).getYear();
        int j = ((YearMonth) (obj)).getMonth();
        obj = viewport.end;
        int k1 = ((YearMonth) (obj)).getYear();
        int l1 = ((YearMonth) (obj)).getMonth();
label0:
        for (i = j + i * 12; i <= k1 * 12 + l1; i++)
        {
            AutoValue_YearMonth autovalue_yearmonth = new AutoValue_YearMonth(i / 12, i % 12);
            Rect rect = getBounds();
            Object obj1 = new Rect();
            viewport.projectYearMonth(autovalue_yearmonth, ((Rect) (obj1)));
            if (((Rect) (obj1)).isEmpty())
            {
                continue;
            }
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            int k;
            int l;
            int i1;
            int i2;
            long l2;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                k = rect.width() - ((Rect) (obj1)).right;
            } else
            {
                k = ((Rect) (obj1)).left;
            }
            i2 = ((Rect) (obj1)).top;
            f5 = (float)((Rect) (obj1)).height() / (float)weeksInMonth;
            f4 = (float)((Rect) (obj1)).width() / 7F;
            i1 = ((Rect) (obj1)).top;
            l2 = ((Long)currentTime.get()).longValue();
            l = timeUtils.msToJulianDate(l2);
            if (!yearMonthHelper.getMonthRange(autovalue_yearmonth).contains(Integer.valueOf(l)))
            {
                l = -1;
            } else
            {
                calendar.setTimeInMillis(l2);
                l = ((calendar.get(7) - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + 7) % 7;
            }
            f6 = i1 / 2;
            f7 = weekdaysPaint.getTextSize() / 2.0F;
            f8 = weekdayOffsetVertical;
            i1 = 0;
            while (i1 < 7) 
            {
                String s2 = weekdayNames.get(((((Integer)timeUtils.firstDayOfWeek.get()).intValue() + i1) - 1) % 7 + 1);
                int j2 = weekdayPaddingHorizontal;
                j2 = Math.round((float)i1 * f4) + (j2 + k);
                float f;
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    f = rect.width() - j2;
                } else
                {
                    f = j2;
                }
                if (l == i1)
                {
                    obj1 = highlightedWeekdaysPaint;
                } else
                {
                    obj1 = weekdaysPaint;
                }
                canvas.drawText(s2, f, f6 + f7 + f8, ((Paint) (obj1)));
                i1++;
            }
            for (l = 1; l < weeksInMonth; l++)
            {
                float f1 = (float)(Math.round((float)l * f5) + i2) + 0.5F;
                canvas.drawLine(rect.left, f1, rect.right, f1, borderPaint);
            }

            if (!((Boolean)shouldShowWeekNumbers.get()).booleanValue())
            {
                continue;
            }
            l = dimens.monthDayRowHeight(Math.round(f5));
            float f2 = weekNumberPaint.getTextSize();
            f4 = weekNumberPaint.descent();
            f6 = ((float)l + (f2 - f4)) / 2.0F;
            f4 = (float)k - dimens.monthWeekNumberColumnWidth() / 2.0F;
            k = yearMonthHelper.getFirstVisibleJulianDay(autovalue_yearmonth);
            l = 0;
            do
            {
                if (l >= weeksInMonth)
                {
                    continue label0;
                }
                int j1 = timeUtils.getCalendarFieldForJulianDay(k, 3);
                int k2 = Math.round((float)l * f5);
                String s1 = (String)weekNumberStrings.get(j1);
                String s = s1;
                if (s1 == null)
                {
                    s = String.format(Locale.getDefault(), "%d", new Object[] {
                        Integer.valueOf(j1)
                    });
                    weekNumberStrings.put(j1, s);
                }
                float f3;
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    f3 = (float)rect.width() - f4;
                } else
                {
                    f3 = f4;
                }
                canvas.drawText(s, f3, (float)(i2 + k2) + f6, weekNumberPaint);
                l++;
                k += 7;
            } while (true);
        }

        canvas.restore();
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void setAlpha(int i)
    {
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }
}
