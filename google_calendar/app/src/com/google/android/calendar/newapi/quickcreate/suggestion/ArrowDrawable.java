// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.google.android.calendar.utils.rtl.RtlUtils;

final class ArrowDrawable extends Drawable
{

    private final int headWidth;
    private final boolean isRtl;
    private final Paint paint = new Paint();
    private Path path;

    ArrowDrawable(Context context)
    {
        path = new Path();
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        headWidth = context.getResources().getDimensionPixelSize(0x7f0e033a);
        paint.setColor(ContextCompat.getColor(context, 0x7f0d02d8));
        paint.setStyle(android.graphics.Paint.Style.FILL);
    }

    public final void draw(Canvas canvas)
    {
        int i = canvas.getWidth();
        int j = canvas.getHeight();
        int k = headWidth;
        Path path1 = path;
        path1.reset();
        path1.moveTo(0.0F, j / 2);
        path1.lineTo(k, j);
        path1.lineTo(i, j);
        path1.lineTo(i, 0.0F);
        path1.lineTo(k, 0.0F);
        path1.close();
        if (isRtl)
        {
            canvas.scale(-1F, 1.0F, canvas.getWidth() / 2, 0.0F);
        }
        canvas.drawPath(path, paint);
    }

    public final int getOpacity()
    {
        return -2;
    }

    public final void setAlpha(int i)
    {
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }
}
