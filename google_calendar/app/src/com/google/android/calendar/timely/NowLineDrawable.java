// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public final class NowLineDrawable extends Drawable
{

    public int endX;
    public final int oneDayStartPadding;
    public final Paint paint = new Paint();
    public final float radius;
    public int startX;
    public int y;

    public NowLineDrawable(android.graphics.Paint.Style style, int i, int j, float f, int k)
    {
        paint.setStyle(style);
        paint.setColor(i);
        paint.setStrokeWidth(j);
        radius = f;
        oneDayStartPadding = k;
    }

    public static NowLineDrawable createAgendaStyle(Resources resources, boolean flag)
    {
        float f;
        android.graphics.Paint.Style style;
        int i;
        int j;
        int k;
        if (flag)
        {
            i = 0x7f0e038d;
        } else
        {
            i = 0x7f0e02f4;
        }
        f = (float)resources.getDimensionPixelOffset(i) / 2.0F;
        if (flag)
        {
            i = 0x7f0e0307;
        } else
        {
            i = 0x7f0e03b3;
        }
        i = resources.getDimensionPixelSize(i);
        j = resources.getDimensionPixelSize(0x7f0e02f7);
        if (!flag)
        {
            float f1 = i;
            i = (int)((float)(j / 2) + (f1 + f));
        }
        if (flag)
        {
            style = android.graphics.Paint.Style.FILL;
        } else
        {
            style = android.graphics.Paint.Style.FILL_AND_STROKE;
        }
        k = resources.getColor(0x7f0d01d7);
        if (flag)
        {
            j = 0x7f0e038e;
        } else
        {
            j = 0x7f0e02f8;
        }
        return new NowLineDrawable(style, k, resources.getDimensionPixelOffset(j), f, i);
    }

    public final void draw(Canvas canvas)
    {
        paint.setAntiAlias(true);
        canvas.drawCircle(startX, y, radius, paint);
        paint.setAntiAlias(false);
        canvas.drawLine(startX, y, endX, y, paint);
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
