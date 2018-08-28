// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.calendar.common.drawable:
//            ColorCircle

public final class CircledDrawable extends Drawable
{

    private final ColorCircle background;
    private final int backgroundDiameter;
    private final android.graphics.drawable.Drawable.Callback callback = new _cls1();
    private final Drawable icon;

    public CircledDrawable(Resources resources, Drawable drawable)
    {
        backgroundDiameter = resources.getDimensionPixelSize(0x7f0e0064);
        background = new ColorCircle(resources, 0x7f0e0064);
        ColorCircle colorcircle = background;
        int i = resources.getColor(0x7f0d0022);
        colorcircle.getPaint().setColor(i);
        colorcircle.getPaint().setStyle(android.graphics.Paint.Style.FILL);
        background.setBounds(0, 0, backgroundDiameter, backgroundDiameter);
        background.setCallback(callback);
        icon = drawable;
        resources = new Rect(0, 0, backgroundDiameter, backgroundDiameter);
        if (drawable.getIntrinsicWidth() > 0)
        {
            int j = (backgroundDiameter - drawable.getIntrinsicWidth()) / 2;
            resources.left = j;
            resources.right = backgroundDiameter - j;
        }
        if (drawable.getIntrinsicHeight() > 0)
        {
            int k = (backgroundDiameter - drawable.getIntrinsicHeight()) / 2;
            resources.top = k;
            resources.bottom = backgroundDiameter - k;
        }
        icon.setBounds(resources);
        icon.setCallback(callback);
    }

    public final void draw(Canvas canvas)
    {
        background.draw(canvas);
        icon.draw(canvas);
    }

    public final int getIntrinsicHeight()
    {
        return backgroundDiameter;
    }

    public final int getIntrinsicWidth()
    {
        return backgroundDiameter;
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void setAlpha(int i)
    {
        throw new UnsupportedOperationException();
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        throw new UnsupportedOperationException();
    }

    private class _cls1
        implements android.graphics.drawable.Drawable.Callback
    {

        private final CircledDrawable this$0;

        public final void invalidateDrawable(Drawable drawable)
        {
            invalidateSelf();
        }

        public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
        {
            scheduleSelf(runnable, l);
        }

        public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
        {
            unscheduleSelf(runnable);
        }

        _cls1()
        {
            this$0 = CircledDrawable.this;
            super();
        }
    }

}
