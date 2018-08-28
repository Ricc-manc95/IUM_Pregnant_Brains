// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.material.Material;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport, ColumnDimens

public final class ColumnBackgroundDrawable extends Drawable
{

    private Rect bounds;
    private final Context context;
    private final ObservableReference currentTime;
    private final ColumnDimens dimens;
    private final DimensConverter dimensConverter;
    private final Point gridOffset;
    private final ObservableReference isPortrait;
    private final ObservableReference isRtl;
    private final LayoutDimens layoutDimens;
    private final Paint linePaint = new Paint();
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
    private final Paint monthPaint = new Paint();
    private final ObservableReference screenType;
    private final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    private final Paint todayPaint = new Paint();
    private final ColumnViewport viewport;
    private final Paint weekDividerPaint = new Paint();
    private final Paint weekNumberPaint = new Paint();

    public ColumnBackgroundDrawable(Context context1, DimensConverter dimensconverter, LayoutDimens layoutdimens, ColumnDimens columndimens, TimeUtils timeutils, ColumnViewport columnviewport, ObservableReference observablereference, 
            Point point, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, ObservableReference observablereference4)
    {
        bounds = new Rect();
        context = context1;
        dimensConverter = dimensconverter;
        layoutDimens = layoutdimens;
        dimens = columndimens;
        timeUtils = timeutils;
        viewport = columnviewport;
        gridOffset = point;
        currentTime = observablereference;
        monthFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        isPortrait = observablereference2;
        isRtl = observablereference3;
        screenType = observablereference1;
        shouldShowWeekNumbers = observablereference4;
        linePaint.setAntiAlias(true);
        linePaint.setStyle(android.graphics.Paint.Style.STROKE);
        linePaint.setColor(ContextCompat.getColor(context1, 0x7f0d0215));
        linePaint.setStrokeWidth(dimensconverter.dpToPx(0.5F));
        todayPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        todayPaint.setColor(0xfff9f9f9);
        weekNumberPaint.setAntiAlias(true);
        columndimens = weekNumberPaint;
        float f;
        int i;
        if (Material.robotoMedium != null)
        {
            layoutdimens = Material.robotoMedium;
        } else
        {
            layoutdimens = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = layoutdimens;
        }
        columndimens.setTypeface(layoutdimens);
        weekNumberPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        layoutdimens = weekNumberPaint;
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
            i = 0x89000000;
        } else
        {
            i = ContextCompat.getColor(context1, 0x7f0d021a);
        }
        layoutdimens.setColor(i);
        context1 = weekNumberPaint;
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
            f = 12F;
        } else
        {
            f = 10F;
        }
        context1.setTextSize(dimensconverter.spToPx(f));
        monthPaint.setAntiAlias(true);
        layoutdimens = weekNumberPaint;
        if (Material.robotoMedium != null)
        {
            context1 = Material.robotoMedium;
        } else
        {
            context1 = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context1;
        }
        layoutdimens.setTypeface(context1);
        monthPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        monthPaint.setColor(0xff303030);
        context1 = monthPaint;
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
            f = 14F;
        } else
        {
            f = 12F;
        }
        context1.setTextSize(dimensconverter.spToPx(f));
    }

    private final float startToLeft(float f)
    {
        float f1 = f;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            f1 = (float)bounds.width() - f;
        }
        return f1;
    }

    public final void draw(Canvas canvas)
    {
        bounds = getBounds();
        canvas.drawLine(bounds.left, gridOffset.y, bounds.right, gridOffset.y, linePaint);
        int i = (int)(viewport.startDayFp16 >> 16);
        int l = viewport.getRightVisibleJulianDay();
        int k1 = timeUtils.msToJulianDate(((Long)currentTime.get()).longValue());
        int k;
        if (viewport.snappedDays == 1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            if (k1 >= i && k1 <= l)
            {
                canvas.save();
                canvas.clipRect(gridOffset.x, gridOffset.y, bounds.right, bounds.height());
                float f = startToLeft(viewport.julianDayToGridStartPx(k1) + gridOffset.x);
                float f4 = startToLeft(viewport.julianDayToGridStartPx(k1 + 1) + gridOffset.x);
                canvas.drawRect(Math.round(Math.min(f, f4)), gridOffset.y, Math.round(Math.max(f, f4)), bounds.bottom, todayPaint);
                canvas.restore();
            }
            k = i;
            while (k <= l) 
            {
                if (((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + k) % 7 == 6)
                {
                    canvas.save();
                    float f1;
                    float f5;
                    if (((Boolean)isRtl.get()).booleanValue())
                    {
                        canvas.clipRect(0, 0, bounds.width() - gridOffset.x, bounds.height());
                    } else
                    {
                        canvas.clipRect(gridOffset.x, 0, bounds.right, bounds.height());
                    }
                    f1 = viewport.julianDayToGridStartPx(k + 1);
                    f5 = dimensConverter.dpToPx(16F);
                    f5 = startToLeft(((float)gridOffset.x + f1) - f5);
                    f1 = startToLeft(f1 + (float)gridOffset.x);
                    weekDividerPaint.setShader(new LinearGradient(f5, 0.0F, f1, 0.0F, 0, 0xa000000, android.graphics.Shader.TileMode.CLAMP));
                    canvas.drawRect(Math.min(f5, f1), 0.0F, Math.max(f5, f1), bounds.bottom, weekDividerPaint);
                    canvas.restore();
                }
                k++;
            }
            while (i <= l) 
            {
                k = viewport.julianDayToGridStartPx(i);
                if (k < 0 || k >= viewport.gridWidthPx)
                {
                    continue;
                }
                canvas.save();
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    canvas.clipRect(0, 0, bounds.width() - gridOffset.x, bounds.height());
                } else
                {
                    canvas.clipRect(gridOffset.x, 0, bounds.right, bounds.height());
                }
                k += gridOffset.x;
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    k = bounds.width() - k;
                }
                canvas.drawLine(k, 0.0F, k, bounds.height(), linePaint);
                canvas.restore();
                i++;
            }
        }
        if ((ScreenType)screenType.get() == ScreenType.PHONE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && !((Boolean)isPortrait.get()).booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            float f3;
            Object obj;
            int i1;
            int l1;
            int j2;
            int k2;
            int l2;
            int i3;
            if (viewport.snappedDays == 1)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k == 0)
            {
                k = timeUtils.getCalendarFieldForJulianDay((int)(viewport.startDayFp16 >> 16), 3);
                String s1 = String.format(Locale.getDefault(), "%d", new Object[] {
                    Integer.valueOf(k)
                });
                String s = s1;
                if (i != 0)
                {
                    s = context.getString(0x7f1304be, new Object[] {
                        s1
                    });
                }
                float f2 = dimensConverter.dpToPx(48F);
                canvas.drawText(s, startToLeft(gridOffset.x / 2), f2, weekNumberPaint);
            }
        }
        if (i != 0)
        {
            obj = new Date();
            ((Date) (obj)).setTime(timeUtils.julianDateToMs((int)(viewport.startDayFp16 >> 16)));
            obj = monthFormat.format(((Date) (obj)));
            f3 = dimensConverter.dpToPx(20F);
            canvas.drawText(((String) (obj)), startToLeft(gridOffset.x / 2), f3, monthPaint);
        }
        canvas.save();
        k = bounds.width();
        canvas.clipRect(bounds.left, gridOffset.y, bounds.right, bounds.bottom);
        if (viewport.snappedDays == 1 && viewport.oneDayRatio == 1.0F)
        {
            i = (int)(viewport.startDayFp16 >> 16);
            for (i1 = viewport.getRightVisibleJulianDay(); i <= i1; i++)
            {
                l1 = viewport.julianDayToGridStartPx(i);
                j2 = dimens.oneDayGridStartMarginPx;
                k2 = viewport.julianDayToGridStartPx(i + 1);
                l2 = layoutDimens.scheduleChipEndMargin();
                for (k = 1; k < 24; k++)
                {
                    i3 = gridOffset.y;
                    obj = viewport;
                    i3 += (int)(((((long)k << 16 << 16) / ((long)24 << 16) & 65535L) - ((ColumnViewport) (obj)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj)).gridFp32PerVerticalPixel);
                    canvas.drawLine(startToLeft(l1 + j2), i3, startToLeft(k2 - l2), i3, linePaint);
                }

            }

        } else
        {
            ColumnViewport columnviewport = viewport;
            int j = gridOffset.x;
            int j1 = dimens.oneDayGridStartMarginPx;
            j1 = Math.round(columnviewport.oneDayRatio * (float)(j1 - j) + (float)j);
            j = 1;
            while (j < 24) 
            {
                int i2 = gridOffset.y;
                ColumnViewport columnviewport1 = viewport;
                i2 += (int)(((((long)j << 16 << 16) / ((long)24 << 16) & 65535L) - columnviewport1.gridTopFp16OfDay << 16) / columnviewport1.gridFp32PerVerticalPixel);
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    canvas.drawLine(0.0F, i2, k - j1, i2, linePaint);
                } else
                {
                    canvas.drawLine(j1, i2, k, i2, linePaint);
                }
                j++;
            }
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
