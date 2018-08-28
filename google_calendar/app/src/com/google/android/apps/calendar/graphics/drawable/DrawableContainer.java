// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public final class DrawableContainer extends Drawable
{

    private int alpha;
    public android.graphics.drawable.Drawable.Callback callback;
    private ColorFilter colorFilter;
    private boolean dither;
    public Drawable drawable;
    private int hotspotBottom;
    private boolean hotspotBoundsNeedsUpdate;
    private int hotspotLeft;
    private int hotspotRight;
    private int hotspotTop;
    private float hotspotX;
    private boolean hotspotXyNeedsUpdate;
    private float hotspotY;
    private boolean isAutoMirrored;
    private boolean isVisible;
    private boolean shouldRestart;
    private ColorStateList tintList;
    private android.graphics.PorterDuff.Mode tintMode;

    public DrawableContainer()
    {
        drawable = new ColorDrawable(0);
        alpha = 255;
        isVisible = true;
        updateDrawable();
    }

    private final void updateDrawableHotspot()
    {
        if (hotspotXyNeedsUpdate)
        {
            drawable.setHotspot(hotspotX, hotspotY);
            hotspotXyNeedsUpdate = false;
        }
        if (hotspotBoundsNeedsUpdate)
        {
            drawable.setHotspotBounds(hotspotLeft, hotspotTop, hotspotRight, hotspotBottom);
            hotspotBoundsNeedsUpdate = false;
        }
    }

    public final void draw(Canvas canvas)
    {
        drawable.draw(canvas);
    }

    public final int getAlpha()
    {
        return alpha;
    }

    public final int getChangingConfigurations()
    {
        return drawable.getChangingConfigurations();
    }

    public final ColorFilter getColorFilter()
    {
        return colorFilter;
    }

    public final Drawable getCurrent()
    {
        return drawable.getCurrent();
    }

    public final int getIntrinsicHeight()
    {
        return drawable.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth()
    {
        return drawable.getIntrinsicWidth();
    }

    public final int getOpacity()
    {
        return drawable.getOpacity();
    }

    public final boolean getPadding(Rect rect)
    {
        return drawable.getPadding(rect);
    }

    public final boolean isAutoMirrored()
    {
        return isAutoMirrored;
    }

    public final boolean isStateful()
    {
        return drawable.isStateful();
    }

    public final void jumpToCurrentState()
    {
        drawable.jumpToCurrentState();
    }

    public final Drawable mutate()
    {
        drawable.mutate();
        return super.mutate();
    }

    protected final void onBoundsChange(Rect rect)
    {
        drawable.setBounds(getBounds());
    }

    protected final boolean onLevelChange(int i)
    {
        return drawable.setLevel(getLevel());
    }

    protected final boolean onStateChange(int ai[])
    {
        return drawable.setState(getState());
    }

    public final void setAlpha(int i)
    {
        alpha = i;
        drawable.setAlpha(alpha);
    }

    public final void setAutoMirrored(boolean flag)
    {
        isAutoMirrored = flag;
        drawable.setAutoMirrored(isAutoMirrored);
        drawable.setAutoMirrored(flag);
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        colorFilter = colorfilter;
        drawable.setColorFilter(colorFilter);
    }

    public final void setDither(boolean flag)
    {
        dither = flag;
        drawable.setDither(dither);
    }

    public final void setHotspot(float f, float f1)
    {
        hotspotXyNeedsUpdate = true;
        hotspotBoundsNeedsUpdate = false;
        hotspotX = f;
        hotspotY = f1;
        updateDrawableHotspot();
    }

    public final void setHotspotBounds(int i, int j, int k, int l)
    {
        hotspotBoundsNeedsUpdate = true;
        hotspotXyNeedsUpdate = false;
        hotspotLeft = i;
        hotspotTop = j;
        hotspotRight = k;
        hotspotBottom = l;
        updateDrawableHotspot();
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        tintList = colorstatelist;
        drawable.setTintList(tintList);
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        tintMode = mode;
        drawable.setTintMode(tintMode);
    }

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        isVisible = flag;
        shouldRestart = flag1;
        return drawable.setVisible(isVisible, shouldRestart);
    }

    public final void updateDrawable()
    {
        callback = new _cls1();
        drawable.setCallback(callback);
        drawable.setBounds(getBounds());
        drawable.setAlpha(alpha);
        drawable.setColorFilter(colorFilter);
        drawable.setTintList(tintList);
        drawable.setTintMode(tintMode);
        drawable.setDither(dither);
        drawable.setAutoMirrored(isAutoMirrored);
        drawable.setVisible(isVisible, shouldRestart);
        drawable.setLevel(getLevel());
        drawable.setState(getState());
    }

    private class _cls1
        implements android.graphics.drawable.Drawable.Callback
    {

        private final DrawableContainer this$0;

        public final void invalidateDrawable(Drawable drawable1)
        {
            if (callback == this)
            {
                invalidateSelf();
            }
        }

        public final void scheduleDrawable(Drawable drawable1, Runnable runnable, long l)
        {
            if (callback == this)
            {
                scheduleSelf(runnable, l);
            }
        }

        public final void unscheduleDrawable(Drawable drawable1, Runnable runnable)
        {
            if (callback == this)
            {
                unscheduleSelf(runnable);
            }
        }

        _cls1()
        {
            this$0 = DrawableContainer.this;
            super();
        }
    }

}
