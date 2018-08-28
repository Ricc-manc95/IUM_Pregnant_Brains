// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class TileDrawable extends Drawable
    implements android.graphics.drawable.Drawable.Callback
{

    public int alpha;
    public int fadeAlpha;
    public final Drawable inner;
    private final int innerHeight;
    private final int innerWidth;
    public final ValueAnimator mFadeOutAnimator;
    private final ExtendedBitmapDrawable.ExtendedOptions opts;
    private final Paint paint = new Paint();

    public TileDrawable(Drawable drawable, int i, int j, int k, ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        alpha = 255;
        fadeAlpha = 255;
        opts = extendedoptions;
        if (drawable != null)
        {
            extendedoptions = drawable.mutate();
        } else
        {
            extendedoptions = null;
        }
        inner = extendedoptions;
        innerWidth = i;
        innerHeight = j;
        if (drawable != null)
        {
            inner.setCallback(this);
        }
        mFadeOutAnimator = ValueAnimator.ofInt(new int[] {
            255, 0
        }).setDuration(k);
        mFadeOutAnimator.addUpdateListener(new _cls1());
        alpha = 0;
        fadeAlpha = 0;
        setVisible(false);
        invalidateSelf();
    }

    public void draw(Canvas canvas)
    {
        int i = (int)((float)alpha * ((float)fadeAlpha / 255F));
        if (isVisible() || i != 0)
        {
            paint.setColor(opts.backgroundColor);
            paint.setAlpha(i);
            canvas.drawRect(getBounds(), paint);
            if (inner != null)
            {
                i = (int)((float)i * ((float)getInnerAlpha() / 255F));
                inner.setAlpha(i);
                inner.draw(canvas);
                return;
            }
        }
    }

    public int getAlpha()
    {
        return alpha;
    }

    protected int getInnerAlpha()
    {
        return 255;
    }

    public int getOpacity()
    {
        return 0;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        invalidateSelf();
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        if (inner == null)
        {
            return;
        }
        if (rect.isEmpty())
        {
            inner.setBounds(0, 0, 0, 0);
            return;
        } else
        {
            int i = (rect.left + rect.width() / 2) - innerWidth / 2;
            int j = (rect.top + rect.height() / 2) - innerHeight / 2;
            inner.setBounds(i, j, innerWidth + i, innerHeight + j);
            return;
        }
    }

    protected boolean onLevelChange(int i)
    {
        if (inner != null)
        {
            return inner.setLevel(i);
        } else
        {
            return super.onLevelChange(i);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        int j = alpha;
        alpha = i;
        if (i != j)
        {
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        paint.setColorFilter(colorfilter);
        if (inner != null)
        {
            inner.setColorFilter(colorfilter);
        }
    }

    public boolean setVisible(boolean flag)
    {
        return setVisible(flag, true);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if (inner != null)
        {
            inner.setVisible(flag, flag1);
        }
        flag = super.setVisible(flag, flag1);
        if (flag)
        {
            if (isVisible())
            {
                mFadeOutAnimator.cancel();
                fadeAlpha = 255;
            } else
            if (fadeAlpha == 255)
            {
                mFadeOutAnimator.start();
                return flag;
            }
        }
        return flag;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    private class _cls1
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final TileDrawable this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            fadeAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
            invalidateSelf();
        }

        _cls1()
        {
            this$0 = TileDrawable.this;
            super();
        }
    }

}
