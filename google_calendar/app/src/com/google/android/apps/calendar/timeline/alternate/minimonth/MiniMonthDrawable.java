// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.google.android.apps.calendar.timeline.alternate.minimonth.data.MiniMonthDayData;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.material.Material;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthGeometry

final class MiniMonthDrawable extends Drawable
{

    private final int alternateNumberColorRegular = 0x8a000000;
    private final Paint alternateNumberPaint;
    private final Calendar calendar = Calendar.getInstance();
    private final Paint circlePaint = new Paint();
    public List dayDataList;
    private final int dotColorOnIllustrations = 0x99000000;
    private final float dotOffsetPx;
    private final float dotRadiusPx;
    private final float dotSpacingPx;
    public MiniMonthGeometry geometry;
    private final float highlightCircleRadiusPx;
    private final ObservableReference isRtl;
    private final int numberColorRegular;
    private final Paint numberPaint = new Paint();
    private final int overflowPlusColor;
    private final ObservableReference screenType;
    private final int selectedHighlightColor;
    public int selectedJulianDay;
    private final ObservableReference shouldShowMonthIllustrations;
    private final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    private final int todayHighlightColor;
    public int todayJulianDay;
    private final Paint weekNumberPaint;
    private final SimpleDateFormat weekdayFormat = new SimpleDateFormat("EEEEE", Locale.getDefault());
    private final Paint weekdayPaint;
    private final float weekdayTextOffsetPx;
    public YearMonth yearMonth;

    MiniMonthDrawable(Context context, DimensConverter dimensconverter, TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3)
    {
        boolean flag = false;
        super();
        timeUtils = timeutils;
        screenType = observablereference;
        shouldShowMonthIllustrations = observablereference1;
        isRtl = observablereference2;
        shouldShowWeekNumbers = observablereference3;
        weekdayFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        float f;
        int i;
        int j;
        if (observablereference.get() == ScreenType.LARGE_TABLET)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        weekdayTextOffsetPx = dimensconverter.dpToPx(2.0F);
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            if (i != 0)
            {
                j = 20;
            } else
            {
                j = 18;
            }
            f = j;
        } else
        {
            f = 16F;
        }
        highlightCircleRadiusPx = dimensconverter.dpToPx(f);
        dotRadiusPx = dimensconverter.dpToPx(2.0F);
        dotSpacingPx = dimensconverter.dpToPx(2.0F);
        dotOffsetPx = dimensconverter.dpToPx(6F) + dotRadiusPx;
        numberColorRegular = ContextCompat.getColor(context, 0x7f0d021e);
        todayHighlightColor = ContextCompat.getColor(context, 0x7f0d01d7);
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = -1;
        } else
        {
            j = 0xffd6d6d6;
        }
        selectedHighlightColor = j;
        overflowPlusColor = ContextCompat.getColor(context, 0x7f0d021a);
        numberPaint.setAntiAlias(true);
        timeutils = numberPaint;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            if (i != 0)
            {
                i = 16;
            } else
            {
                i = 14;
            }
            f = i;
        } else
        {
            f = 12F;
        }
        timeutils.setTextSize(dimensconverter.spToPx(f));
        numberPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        alternateNumberPaint = new Paint(numberPaint);
        alternateNumberPaint.setTextSize(dimensconverter.spToPx(8F));
        weekdayPaint = new Paint(numberPaint);
        observablereference1 = weekdayPaint;
        if (Material.robotoMedium != null)
        {
            timeutils = Material.robotoMedium;
        } else
        {
            timeutils = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = timeutils;
        }
        observablereference1.setTypeface(timeutils);
        timeutils = weekdayPaint;
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
            i = 0xff333333;
        } else
        {
            i = ContextCompat.getColor(context, 0x7f0d021a);
        }
        timeutils.setColor(i);
        weekNumberPaint = new Paint(numberPaint);
        timeutils = weekNumberPaint;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = ((flag) ? 1 : 0);
        if (i == 0)
        {
            j = 1;
        }
        if (j != 0)
        {
            i = 0x89000000;
        } else
        {
            i = ContextCompat.getColor(context, 0x7f0d021a);
        }
        timeutils.setColor(i);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(android.graphics.Paint.Style.FILL);
        circlePaint.setStrokeWidth(dimensconverter.dpToPx(1.0F));
    }

    public final void draw(Canvas canvas)
    {
        if (yearMonth == null)
        {
            throw new NullPointerException(String.valueOf("Data has not been set yet."));
        }
        geometry.updateDrawDimens(getBounds());
        timeUtils.initCalendar(calendar);
        calendar.set(7, ((Integer)timeUtils.firstDayOfWeek.get()).intValue());
        float f = geometry.weekdayHeaderHeightPx / 2.0F;
        float f4 = (weekdayPaint.getTextSize() - weekdayPaint.descent()) / 2.0F;
        float f7 = weekdayTextOffsetPx;
        for (int i = 0; i < 7; i++)
        {
            Calendar calendar1 = calendar;
            canvas.drawText(weekdayFormat.format(calendar1.getTime()).toUpperCase(Locale.getDefault()), geometry.getLeft(i), f7 + (f + f4), weekdayPaint);
            calendar.add(7, 1);
        }

        timeUtils.initCalendar(calendar);
        calendar.set(yearMonth.getYear(), yearMonth.getMonth(), 1);
        Object obj = timeUtils;
        long l4 = calendar.getTimeInMillis();
        int j3 = TimeUtils.getJulianDay(l4, TimeUnit.MILLISECONDS.toSeconds(((TimeZone)((TimeUtils) (obj)).timeZone.get()).getOffset(l4)));
        int k3 = todayJulianDay;
        int l3 = selectedJulianDay;
        int i4 = calendar.getActualMaximum(5);
        int j = 0;
        obj = geometry;
        int l = calendar.get(7);
        int l1 = ((Integer)((MiniMonthGeometry) (obj)).timeUtils.firstDayOfWeek.get()).intValue();
        int j1 = 1;
        l = ((l - l1) + 7) % 7;
        while (j1 <= i4) 
        {
            float f5;
            float f8;
            MiniMonthDayData minimonthdaydata;
            boolean flag;
            int k2;
            int l2;
            if (j1 == (k3 - j3) + 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (j1 == (l3 - j3) + 1)
            {
                k2 = 1;
            } else
            {
                k2 = 0;
            }
            minimonthdaydata = (MiniMonthDayData)dayDataList.get(j1 - 1);
            if (flag || k2 != 0)
            {
                Object obj2 = circlePaint;
                float f1;
                float f10;
                float f13;
                float f15;
                float f16;
                float f17;
                Object obj3;
                int j4;
                if (flag)
                {
                    l2 = todayHighlightColor;
                } else
                {
                    l2 = selectedHighlightColor;
                }
                ((Paint) (obj2)).setColor(l2);
                f1 = geometry.getLeft(l);
                obj2 = geometry;
                f5 = ((MiniMonthGeometry) (obj2)).weekdayHeaderHeightPx;
                f8 = ((MiniMonthGeometry) (obj2)).rowSizePx;
                f10 = j;
                canvas.drawCircle(f1, ((MiniMonthGeometry) (obj2)).rowSizePx / 2.0F + (f5 + f8 * f10), highlightCircleRadiusPx, circlePaint);
            }
            f5 = geometry.getLeft(l);
            obj2 = geometry;
            obj3 = numberPaint;
            f1 = ((MiniMonthGeometry) (obj2)).weekdayHeaderHeightPx;
            f8 = ((MiniMonthGeometry) (obj2)).rowSizePx;
            f10 = j;
            f13 = ((MiniMonthGeometry) (obj2)).rowSizePx / 2.0F;
            f8 = ((((Paint) (obj3)).getTextSize() - ((Paint) (obj3)).descent()) / 2.0F + (f1 + f8 * f10 + f13)) - ((MiniMonthGeometry) (obj2)).alternateLineHeightPx / 2.0F;
            if (!flag && k2 == 0)
            {
                float f11 = geometry.alternateLineHeightPx + f8 + dotOffsetPx;
                l2 = minimonthdaydata.getColors().size();
                if (minimonthdaydata.hasOverflow())
                {
                    k2 = 1;
                } else
                {
                    k2 = 0;
                }
                k2 += l2;
                if (k2 != 0)
                {
                    f13 = k2;
                    f15 = dotRadiusPx;
                    f16 = k2 - 1;
                    f17 = dotSpacingPx;
                    float f2;
                    if (((Boolean)isRtl.get()).booleanValue())
                    {
                        k2 = -1;
                    } else
                    {
                        k2 = 1;
                    }
                    f1 = k2;
                    f13 = -(f13 * (2.0F * f15) + f16 * f17) / 2.0F;
                    f15 = dotRadiusPx;
                    obj2 = (ImmutableList)minimonthdaydata.getColors();
                    j4 = ((ImmutableList) (obj2)).size();
                    k2 = 0;
                    obj3 = (UnmodifiableIterator)null;
                    f2 = f5 + f1 * (f13 + f15);
                    while (k2 < j4) 
                    {
                        int i3 = ((Integer)((ImmutableList) (obj2)).get(k2)).intValue();
                        obj3 = circlePaint;
                        if ((ScreenType)screenType.get() == ScreenType.PHONE)
                        {
                            l2 = 1;
                        } else
                        {
                            l2 = 0;
                        }
                        if (l2 == 0)
                        {
                            l2 = 1;
                        } else
                        {
                            l2 = 0;
                        }
                        if (l2 != 0 && ((Boolean)shouldShowMonthIllustrations.get()).booleanValue())
                        {
                            l2 = dotColorOnIllustrations;
                        } else
                        {
                            l2 = i3;
                        }
                        ((Paint) (obj3)).setColor(l2);
                        canvas.drawCircle(f2, f11, dotRadiusPx, circlePaint);
                        if (((Boolean)isRtl.get()).booleanValue())
                        {
                            l2 = -1;
                        } else
                        {
                            l2 = 1;
                        }
                        f13 = l2;
                        f15 = dotRadiusPx;
                        f16 = dotSpacingPx;
                        f17 = dotRadiusPx;
                        k2++;
                        f2 = f13 * (f15 + f16 + f17) + f2;
                    }
                    if (minimonthdaydata.hasOverflow())
                    {
                        circlePaint.setColor(overflowPlusColor);
                        canvas.drawLine(f2 - dotRadiusPx, f11, f2 + dotRadiusPx, f11, circlePaint);
                        canvas.drawLine(f2, f11 - dotRadiusPx, f2, f11 + dotRadiusPx, circlePaint);
                    }
                }
            }
            Paint paint = numberPaint;
            if (flag)
            {
                k2 = -1;
            } else
            {
                k2 = numberColorRegular;
            }
            paint.setColor(k2);
            canvas.drawText(minimonthdaydata.getName(), f5, f8, numberPaint);
            if (minimonthdaydata.getAlternateName() != null)
            {
                Paint paint1 = alternateNumberPaint;
                int i2;
                if (flag)
                {
                    i2 = -1;
                } else
                {
                    i2 = alternateNumberColorRegular;
                }
                paint1.setColor(i2);
                canvas.drawText(minimonthdaydata.getAlternateName(), f5, geometry.alternateLineHeightPx + f8, alternateNumberPaint);
            }
            l++;
            if (l == 7)
            {
                l = 0;
                j++;
            }
            j1++;
        }
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            timeUtils.initCalendar(calendar);
            calendar.set(yearMonth.getYear(), yearMonth.getMonth(), 1);
            calendar.getTimeInMillis();
            calendar.set(7, calendar.getFirstDayOfWeek());
            int k = 0;
            MiniMonthGeometry minimonthgeometry = geometry;
            float f3 = minimonthgeometry.toLeft(minimonthgeometry.sidePaddingPx + minimonthgeometry.columnWidthPx / 2.0F);
            do
            {
                int i1 = calendar.get(1);
                int k1 = calendar.get(2);
                Object obj1 = yearMonth;
                int j2 = ((YearMonth) (obj1)).getYear();
                if (i1 * 12 + k1 > ((YearMonth) (obj1)).getMonth() + j2 * 12)
                {
                    break;
                }
                obj1 = String.format(Locale.getDefault(), "%d", new Object[] {
                    Integer.valueOf(calendar.get(3))
                });
                MiniMonthGeometry minimonthgeometry1 = geometry;
                Paint paint2 = weekNumberPaint;
                float f6 = minimonthgeometry1.weekdayHeaderHeightPx;
                float f9 = minimonthgeometry1.rowSizePx;
                float f12 = k;
                float f14 = minimonthgeometry1.rowSizePx / 2.0F;
                canvas.drawText(((String) (obj1)), f3, ((paint2.getTextSize() - paint2.descent()) / 2.0F + (f6 + f9 * f12 + f14)) - minimonthgeometry1.alternateLineHeightPx / 2.0F, weekNumberPaint);
                calendar.add(3, 1);
                k++;
            } while (true);
        }
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
