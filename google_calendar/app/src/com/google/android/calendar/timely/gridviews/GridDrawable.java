// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.rtl.RtlUtils;

public final class GridDrawable extends Drawable
{

    public int columnCount;
    private final Context context;
    public boolean drawStartVerticalLine;
    public final int gridLineHeight;
    private final int highlightColor;
    public int highlightColumn;
    public int intervalHeight;
    private final int lineColor;
    private final Paint paint = new Paint();
    public final Rect rect = new Rect();

    public GridDrawable(Context context1)
    {
        drawStartVerticalLine = true;
        context = context1;
        context1 = context1.getResources();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        intervalHeight = CalendarProperties.getIntProperty(10).intValue();
        highlightColor = context1.getColor(0x7f0d0332);
        lineColor = context1.getColor(0x7f0d011a);
        gridLineHeight = context1.getDimensionPixelOffset(0x7f0e01dc);
    }

    public final void draw(Canvas canvas)
    {
        boolean flag = RtlUtils.isLayoutDirectionRtl(context);
        int j1 = rect.width();
        int k1 = rect.height();
        canvas.save();
        canvas.translate(rect.left, rect.top);
        if (highlightColumn >= 0 && highlightColumn < columnCount)
        {
            paint.setColor(highlightColor);
            int i = (int)(((float)highlightColumn / (float)columnCount) * (float)j1);
            int k = (int)(((float)(highlightColumn + 1) / (float)columnCount) * (float)j1);
            float f;
            float f1;
            if (flag)
            {
                f = j1 - i;
            } else
            {
                f = i;
            }
            if (flag)
            {
                f1 = j1 - k;
            } else
            {
                f1 = k;
            }
            canvas.drawRect(f, 0.0F, f1, k1, paint);
        }
        paint.setStrokeWidth(gridLineHeight);
        for (i = 1; i < 24; i++)
        {
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setAntiAlias(false);
            paint.setColor(lineColor);
            f = (intervalHeight + gridLineHeight) * i;
            canvas.drawLine(0.0F, f, j1, f, paint);
        }

        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setAntiAlias(false);
        paint.setColor(lineColor);
        int j;
        if (drawStartVerticalLine)
        {
            j = 0;
        } else
        {
            j = 1;
        }
        for (; j < columnCount; j++)
        {
            int i1 = (int)(((float)j / (float)columnCount) * (float)j1);
            int l = i1;
            if (flag)
            {
                l = j1 - 1 - i1;
            }
            canvas.drawLine(l, 0.0F, l, k1, paint);
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
