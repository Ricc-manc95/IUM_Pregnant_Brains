// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class DrawableWrapper extends Drawable
{

    public final Drawable wrappedDrawable;
    private final android.graphics.drawable.Drawable.Callback wrappedDrawableCallback = new _cls1();

    public DrawableWrapper(Drawable drawable)
    {
        wrappedDrawable = drawable;
        wrappedDrawable.setCallback(wrappedDrawableCallback);
    }

    public void draw(Canvas canvas)
    {
        wrappedDrawable.draw(canvas);
    }

    public int getChangingConfigurations()
    {
        return wrappedDrawable.getChangingConfigurations();
    }

    public Drawable getCurrent()
    {
        return wrappedDrawable.getCurrent();
    }

    public int getIntrinsicHeight()
    {
        return wrappedDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        return wrappedDrawable.getIntrinsicWidth();
    }

    public int getOpacity()
    {
        return wrappedDrawable.getOpacity();
    }

    public boolean getPadding(Rect rect)
    {
        return wrappedDrawable.getPadding(rect);
    }

    public boolean isAutoMirrored()
    {
        return wrappedDrawable.isAutoMirrored();
    }

    public boolean isStateful()
    {
        return wrappedDrawable.isStateful();
    }

    public void jumpToCurrentState()
    {
        wrappedDrawable.jumpToCurrentState();
    }

    public Drawable mutate()
    {
        wrappedDrawable.mutate();
        return super.mutate();
    }

    protected void onBoundsChange(Rect rect)
    {
        wrappedDrawable.setBounds(rect);
    }

    protected boolean onLevelChange(int i)
    {
        return wrappedDrawable.setLevel(i);
    }

    protected boolean onStateChange(int ai[])
    {
        return wrappedDrawable.setState(ai);
    }

    public void setAlpha(int i)
    {
        wrappedDrawable.setAlpha(i);
    }

    public void setAutoMirrored(boolean flag)
    {
        wrappedDrawable.setAutoMirrored(flag);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        wrappedDrawable.setColorFilter(colorfilter);
    }

    public void setDither(boolean flag)
    {
        wrappedDrawable.setDither(flag);
    }

    public void setHotspot(float f, float f1)
    {
        wrappedDrawable.setHotspot(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        wrappedDrawable.setHotspotBounds(i, j, k, l);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        wrappedDrawable.setTintList(colorstatelist);
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        wrappedDrawable.setTintMode(mode);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        wrappedDrawable.setVisible(flag, flag1);
        return super.setVisible(flag, flag1);
    }

    private class _cls1
        implements android.graphics.drawable.Drawable.Callback
    {

        private final DrawableWrapper this$0;

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
            this$0 = DrawableWrapper.this;
            super();
        }
    }

}
