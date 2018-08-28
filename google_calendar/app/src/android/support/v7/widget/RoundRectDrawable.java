// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.widget:
//            RoundRectDrawableWithShadow

final class RoundRectDrawable extends Drawable
{

    private ColorStateList mBackground;
    private final RectF mBoundsF = new RectF();
    private final Rect mBoundsI = new Rect();
    public boolean mInsetForPadding;
    public boolean mInsetForRadius;
    public float mPadding;
    private final Paint mPaint = new Paint(5);
    public float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private android.graphics.PorterDuff.Mode mTintMode;

    RoundRectDrawable(ColorStateList colorstatelist, float f)
    {
        mInsetForPadding = false;
        mInsetForRadius = true;
        mTintMode = android.graphics.PorterDuff.Mode.SRC_IN;
        mRadius = f;
        setBackground(colorstatelist);
    }

    public final void draw(Canvas canvas)
    {
        Paint paint = mPaint;
        boolean flag;
        if (mTintFilter != null && paint.getColorFilter() == null)
        {
            paint.setColorFilter(mTintFilter);
            flag = true;
        } else
        {
            flag = false;
        }
        canvas.drawRoundRect(mBoundsF, mRadius, mRadius, paint);
        if (flag)
        {
            paint.setColorFilter(null);
        }
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void getOutline(Outline outline)
    {
        outline.setRoundRect(mBoundsI, mRadius);
    }

    public final boolean isStateful()
    {
        return mTint != null && mTint.isStateful() || mBackground != null && mBackground.isStateful() || super.isStateful();
    }

    protected final void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        updateBounds(rect);
    }

    protected final boolean onStateChange(int ai[])
    {
        int i = mBackground.getColorForState(ai, mBackground.getDefaultColor());
        boolean flag;
        boolean flag1;
        if (i != mPaint.getColor())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mPaint.setColor(i);
        }
        flag1 = flag;
        if (mTint != null)
        {
            flag1 = flag;
            if (mTintMode != null)
            {
                ai = mTint;
                android.graphics.PorterDuff.Mode mode = mTintMode;
                if (ai == null || mode == null)
                {
                    ai = null;
                } else
                {
                    ai = new PorterDuffColorFilter(ai.getColorForState(getState(), 0), mode);
                }
                mTintFilter = ai;
                flag1 = true;
            }
        }
        return flag1;
    }

    public final void setAlpha(int i)
    {
        mPaint.setAlpha(i);
    }

    final void setBackground(ColorStateList colorstatelist)
    {
        ColorStateList colorstatelist1 = colorstatelist;
        if (colorstatelist == null)
        {
            colorstatelist1 = ColorStateList.valueOf(0);
        }
        mBackground = colorstatelist1;
        mPaint.setColor(mBackground.getColorForState(getState(), mBackground.getDefaultColor()));
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        mTint = colorstatelist;
        colorstatelist = mTint;
        android.graphics.PorterDuff.Mode mode = mTintMode;
        if (colorstatelist == null || mode == null)
        {
            colorstatelist = null;
        } else
        {
            colorstatelist = new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), mode);
        }
        mTintFilter = colorstatelist;
        invalidateSelf();
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTintMode = mode;
        mode = mTint;
        android.graphics.PorterDuff.Mode mode1 = mTintMode;
        if (mode == null || mode1 == null)
        {
            mode = null;
        } else
        {
            mode = new PorterDuffColorFilter(mode.getColorForState(getState(), 0), mode1);
        }
        mTintFilter = mode;
        invalidateSelf();
    }

    final void updateBounds(Rect rect)
    {
        Rect rect1 = rect;
        if (rect == null)
        {
            rect1 = getBounds();
        }
        mBoundsF.set(rect1.left, rect1.top, rect1.right, rect1.bottom);
        mBoundsI.set(rect1);
        if (mInsetForPadding)
        {
            float f = RoundRectDrawableWithShadow.calculateVerticalPadding(mPadding, mRadius, mInsetForRadius);
            float f1 = RoundRectDrawableWithShadow.calculateHorizontalPadding(mPadding, mRadius, mInsetForRadius);
            mBoundsI.inset((int)Math.ceil(f1), (int)Math.ceil(f));
            mBoundsF.set(mBoundsI);
        }
    }
}
