// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.google.android.calendar.material.Material;

public class CircledStringDrawable extends Drawable
{

    private static Paint paint;
    private static final Rect recycleRect = new Rect();
    private final int background;
    private final int foreground;
    private final String string;
    public float textSize;

    public CircledStringDrawable(String s, int i, int j)
    {
        if (paint == null)
        {
            Paint paint1 = new Paint();
            paint = paint1;
            Typeface typeface;
            if (Material.robotoMedium != null)
            {
                typeface = Material.robotoMedium;
            } else
            {
                typeface = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = typeface;
            }
            paint1.setTypeface(typeface);
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            paint.setAntiAlias(true);
        }
        string = s;
        background = i;
        foreground = j;
    }

    public void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        if (!isVisible() || rect.isEmpty() || string == null)
        {
            return;
        } else
        {
            paint.setColor(background);
            int i = Math.min(rect.width(), rect.height());
            canvas.drawCircle(rect.centerX(), rect.centerY(), i / 2, paint);
            paint.setTextSize(textSize);
            paint.getTextBounds(string, 0, string.length(), recycleRect);
            paint.setColor(foreground);
            canvas.drawText(string, 0, string.length(), rect.centerX(), (float)rect.centerY() - recycleRect.exactCenterY(), paint);
            return;
        }
    }

    public int getOpacity()
    {
        return -3;
    }

    public void setAlpha(int i)
    {
        throw new UnsupportedOperationException();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        throw new UnsupportedOperationException();
    }

}
