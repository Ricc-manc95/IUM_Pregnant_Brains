// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.activity.daybutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public final class DayOfMonthDrawable extends Drawable
{

    public String dayOfMonth;
    private final Paint paint = new Paint();
    private final Rect textBounds = new Rect();
    private final int topPadding;

    public DayOfMonthDrawable(Context context)
    {
        dayOfMonth = "1";
        context = context.getResources();
        paint.setColor(0xff000000);
        paint.setTextSize(context.getDimension(0x7f0e03ba));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setAntiAlias(true);
        topPadding = context.getDimensionPixelSize(0x7f0e03bb);
    }

    public final void draw(Canvas canvas)
    {
        paint.getTextBounds(dayOfMonth, 0, dayOfMonth.length(), textBounds);
        int i = textBounds.bottom;
        int j = textBounds.top;
        Rect rect = getBounds();
        String s = dayOfMonth;
        float f = rect.right / 2;
        float f1 = rect.bottom;
        canvas.drawText(s, f, ((float)(i - j) + f1 + (float)topPadding) / 2.0F, paint);
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void setAlpha(int i)
    {
        paint.setAlpha(i);
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }
}
