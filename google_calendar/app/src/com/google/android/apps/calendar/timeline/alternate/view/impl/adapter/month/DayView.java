// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import javax.inject.Provider;

public final class DayView extends View
{

    private static final int RIPPLE_DRAWABLE_PROJECTION[] = {
        0x101030e
    };
    public final DayDrawable drawable;

    public DayView(Context context, Provider provider)
    {
        super(context);
        drawable = (DayDrawable)provider.get();
        context = drawable;
        provider = getContext().getTheme().obtainStyledAttributes(RIPPLE_DRAWABLE_PROJECTION);
        Drawable drawable1 = provider.getDrawable(0);
        provider.recycle();
        setBackground(new LayerDrawable(new Drawable[] {
            drawable1, context
        }));
    }


    private class DayDrawable extends Drawable
    {

        public String alternateText;
        public String dayText;
        private final LayoutDimens dimens;
        private final int eventRowHeight;
        private final ObservableReference eventsPerDay;
        public final Paint hiddenEventsPaint;
        public String hiddenEventsText;
        private final Paint highlightPaint = new Paint();
        private final Paint highlightedAlternateTextPaint;
        public boolean indicateHiddenEvents;
        public boolean isHighlighted;
        private final ObservableReference isRtl;
        private final boolean isTablet;
        private final float overflowDotRadius;
        private final float overflowDotSpacing;
        private final Paint overflowPaint = new Paint();
        private final int paddingHorizontal;
        public final Paint textPaint = new Paint();
        private final float textSize;
        private final float todayCircleRadius;
        private final boolean useOverflowDots;

        public final void draw(Canvas canvas)
        {
            Object obj1 = textPaint;
            float f;
            float f1;
            float f4;
            Object obj;
            int k;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            ((Paint) (obj1)).setTextAlign(((android.graphics.Paint.Align) (obj)));
            obj1 = highlightedAlternateTextPaint;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            ((Paint) (obj1)).setTextAlign(((android.graphics.Paint.Align) (obj)));
            obj1 = hiddenEventsPaint;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            ((Paint) (obj1)).setTextAlign(((android.graphics.Paint.Align) (obj)));
            obj1 = getBounds();
            f = ((Rect) (obj1)).left + paddingHorizontal;
            k = dimens.monthDayRowHeight(((Rect) (obj1)).height());
            f4 = textPaint.getTextSize() - textPaint.descent();
            float f3;
            int j;
            if (isHighlighted)
            {
                f1 = textPaint.measureText(dayText);
                if (f1 / 2.0F + f < todayCircleRadius)
                {
                    f += todayCircleRadius - f - f1 / 2.0F;
                }
                f1 = f + f1 / 2.0F;
                float f2 = f1;
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    f2 = (float)((Rect) (obj1)).right - f1;
                }
                canvas.drawCircle(f2, k / 2, todayCircleRadius, highlightPaint);
                f1 = f;
                if (alternateText != null)
                {
                    float f5;
                    int i;
                    if (((Boolean)isRtl.get()).booleanValue())
                    {
                        i = -1;
                    } else
                    {
                        i = 1;
                    }
                    f1 = i;
                    f5 = todayCircleRadius;
                    canvas.drawText(alternateText, f1 * f5 + f2, (float)(k / 2) + f4 / 2.0F, highlightedAlternateTextPaint);
                    f1 = f;
                }
            } else
            {
                f1 = f;
            }
            if (alternateText == null || isHighlighted)
            {
                obj = dayText;
            } else
            {
                obj = dayText;
                String s = alternateText;
                obj = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(s).length())).append(((String) (obj))).append(" ").append(s).toString();
            }
            f = f1;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                f = (float)((Rect) (obj1)).right - f1;
            }
            canvas.drawText(((String) (obj)), f, (float)(k / 2) + f4 / 2.0F, textPaint);
            if (indicateHiddenEvents && ((Integer)eventsPerDay.get()).intValue() != 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if (!useOverflowDots)
            {
                break; /* Loop/switch isn't completed */
            }
            f3 = ((Rect) (obj1)).bottom - eventRowHeight / 2;
            f = paddingHorizontal;
            f = overflowDotRadius + f;
            j = 0;
            while (j < 3) 
            {
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    f1 = (float)((Rect) (obj1)).right - f;
                } else
                {
                    f1 = f;
                }
                canvas.drawCircle(f1, f3, overflowDotRadius, overflowPaint);
                f += overflowDotRadius * 2.0F + overflowDotSpacing;
                j++;
            }
            if (true) goto _L1; else goto _L3
_L3:
            f = ((Rect) (obj1)).left + paddingHorizontal;
            f3 = hiddenEventsPaint.getTextSize();
            f4 = hiddenEventsPaint.descent();
            f1 = ((Rect) (obj1)).bottom - eventRowHeight / 2;
            f3 = (f3 - f4) / 2.0F;
            obj = hiddenEventsText;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                f = (float)((Rect) (obj1)).right - f;
            }
            canvas.drawText(((String) (obj)), f, f1 + f3, hiddenEventsPaint);
            return;
        }

        public final int getOpacity()
        {
            return -1;
        }

        public final void setAlpha(int i)
        {
        }

        public final void setColorFilter(ColorFilter colorfilter)
        {
        }

        DayDrawable(Context context, ObservableReference observablereference, ObservableReference observablereference1, ObservableReference observablereference2, ObservableReference observablereference3, LayoutDimens layoutdimens, DimensConverter dimensconverter)
        {
            float f1 = 10F;
            boolean flag1 = false;
            super();
            eventsPerDay = observablereference;
            isRtl = observablereference1;
            float f;
            int i;
            boolean flag;
            if ((ScreenType)observablereference2.get() == ScreenType.PHONE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isTablet = flag;
            flag = flag1;
            if ((ScreenType)observablereference2.get() == ScreenType.PHONE)
            {
                flag = true;
            }
            useOverflowDots = flag;
            dimens = layoutdimens;
            if (isTablet)
            {
                f = 12F;
            } else
            {
                f = 10F;
            }
            textSize = dimensconverter.spToPx(f);
            if (((Boolean)observablereference3.get()).booleanValue())
            {
                if (isTablet)
                {
                    i = 9;
                } else
                {
                    i = 4;
                }
                f = i;
            } else
            {
                f = 12F;
            }
            paddingHorizontal = dimensconverter.getPixelOffset(f);
            if (isTablet)
            {
                f = 22F;
            } else
            {
                f = 14F;
            }
            eventRowHeight = dimensconverter.getPixelSize(f);
            overflowDotRadius = dimensconverter.dpToPx(1.5F);
            overflowDotSpacing = dimensconverter.dpToPx(2.0F);
            if (isTablet)
            {
                f = f1;
            } else
            {
                f = 8F;
            }
            todayCircleRadius = dimensconverter.dpToPx(f);
            textPaint.setTextSize(textSize);
            textPaint.setAntiAlias(true);
            highlightedAlternateTextPaint = new Paint(textPaint);
            highlightedAlternateTextPaint.setColor(ContextCompat.getColor(context, 0x7f0d01d7));
            hiddenEventsPaint = new Paint(textPaint);
            hiddenEventsPaint.setColor(ContextCompat.getColor(context, 0x7f0d021a));
            hiddenEventsPaint.setTextSize(dimensconverter.spToPx(12F));
            overflowPaint.setAntiAlias(true);
            overflowPaint.setStyle(android.graphics.Paint.Style.FILL);
            overflowPaint.setColor(ContextCompat.getColor(context, 0x7f0d021a));
            highlightPaint.setAntiAlias(true);
            highlightPaint.setStyle(android.graphics.Paint.Style.FILL);
            highlightPaint.setColor(ContextCompat.getColor(context, 0x7f0d01d7));
        }
    }

}
