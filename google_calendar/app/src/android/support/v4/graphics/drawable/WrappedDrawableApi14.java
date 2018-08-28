// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            TintAwareDrawable, WrappedDrawable

class WrappedDrawableApi14 extends Drawable
    implements android.graphics.drawable.Drawable.Callback, TintAwareDrawable, WrappedDrawable
{

    public static final android.graphics.PorterDuff.Mode DEFAULT_TINT_MODE;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private android.graphics.PorterDuff.Mode mCurrentMode;
    public Drawable mDrawable;
    private boolean mMutated;
    public DrawableWrapperState mState;

    WrappedDrawableApi14(Drawable drawable)
    {
        mState = mutateConstantState();
        setWrappedDrawable(drawable);
    }

    WrappedDrawableApi14(DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        mState = drawablewrapperstate;
        if (mState != null && mState.mDrawableState != null)
        {
            setWrappedDrawable(mState.mDrawableState.newDrawable(resources));
        }
    }

    private final boolean updateTint(int ai[])
    {
        if (isCompatTintEnabled())
        {
            ColorStateList colorstatelist = mState.mTint;
            android.graphics.PorterDuff.Mode mode = mState.mTintMode;
            if (colorstatelist != null && mode != null)
            {
                int i = colorstatelist.getColorForState(ai, colorstatelist.getDefaultColor());
                if (!mColorFilterSet || i != mCurrentColor || mode != mCurrentMode)
                {
                    setColorFilter(i, mode);
                    mCurrentColor = i;
                    mCurrentMode = mode;
                    mColorFilterSet = true;
                    return true;
                }
            } else
            {
                mColorFilterSet = false;
                clearColorFilter();
                return false;
            }
        }
        return false;
    }

    public void draw(Canvas canvas)
    {
        mDrawable.draw(canvas);
    }

    public int getChangingConfigurations()
    {
        int j = super.getChangingConfigurations();
        int i;
        if (mState != null)
        {
            i = mState.getChangingConfigurations();
        } else
        {
            i = 0;
        }
        return i | j | mDrawable.getChangingConfigurations();
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if (mState != null)
        {
            boolean flag;
            if (mState.mDrawableState != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                mState.mChangingConfigurations = getChangingConfigurations();
                return mState;
            }
        }
        return null;
    }

    public Drawable getCurrent()
    {
        return mDrawable.getCurrent();
    }

    public int getIntrinsicHeight()
    {
        return mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        return mDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight()
    {
        return mDrawable.getMinimumHeight();
    }

    public int getMinimumWidth()
    {
        return mDrawable.getMinimumWidth();
    }

    public int getOpacity()
    {
        return mDrawable.getOpacity();
    }

    public boolean getPadding(Rect rect)
    {
        return mDrawable.getPadding(rect);
    }

    public int[] getState()
    {
        return mDrawable.getState();
    }

    public Region getTransparentRegion()
    {
        return mDrawable.getTransparentRegion();
    }

    public final Drawable getWrappedDrawable()
    {
        return mDrawable;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        invalidateSelf();
    }

    public boolean isAutoMirrored()
    {
        return mDrawable.isAutoMirrored();
    }

    protected boolean isCompatTintEnabled()
    {
        return true;
    }

    public boolean isStateful()
    {
        ColorStateList colorstatelist;
        if (isCompatTintEnabled() && mState != null)
        {
            colorstatelist = mState.mTint;
        } else
        {
            colorstatelist = null;
        }
        return colorstatelist != null && colorstatelist.isStateful() || mDrawable.isStateful();
    }

    public void jumpToCurrentState()
    {
        mDrawable.jumpToCurrentState();
    }

    public Drawable mutate()
    {
        if (!mMutated && super.mutate() == this)
        {
            mState = mutateConstantState();
            if (mDrawable != null)
            {
                mDrawable.mutate();
            }
            if (mState != null)
            {
                DrawableWrapperState drawablewrapperstate = mState;
                android.graphics.drawable.Drawable.ConstantState constantstate;
                if (mDrawable != null)
                {
                    constantstate = mDrawable.getConstantState();
                } else
                {
                    constantstate = null;
                }
                drawablewrapperstate.mDrawableState = constantstate;
            }
            mMutated = true;
        }
        return this;
    }

    DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateBase(mState, null);
    }

    protected void onBoundsChange(Rect rect)
    {
        if (mDrawable != null)
        {
            mDrawable.setBounds(rect);
        }
    }

    protected boolean onLevelChange(int i)
    {
        return mDrawable.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        mDrawable.setAlpha(i);
    }

    public void setAutoMirrored(boolean flag)
    {
        mDrawable.setAutoMirrored(flag);
    }

    public void setChangingConfigurations(int i)
    {
        mDrawable.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mDrawable.setColorFilter(colorfilter);
    }

    public void setDither(boolean flag)
    {
        mDrawable.setDither(flag);
    }

    public void setFilterBitmap(boolean flag)
    {
        mDrawable.setFilterBitmap(flag);
    }

    public boolean setState(int ai[])
    {
        boolean flag = mDrawable.setState(ai);
        return updateTint(ai) || flag;
    }

    public void setTint(int i)
    {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mState.mTint = colorstatelist;
        updateTint(getState());
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mState.mTintMode = mode;
        updateTint(getState());
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        return super.setVisible(flag, flag1) || mDrawable.setVisible(flag, flag1);
    }

    public final void setWrappedDrawable(Drawable drawable)
    {
        if (mDrawable != null)
        {
            mDrawable.setCallback(null);
        }
        mDrawable = drawable;
        if (drawable != null)
        {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            if (mState != null)
            {
                mState.mDrawableState = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    static 
    {
        DEFAULT_TINT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
    }

    private class DrawableWrapperState extends android.graphics.drawable.Drawable.ConstantState
    {

        public int mChangingConfigurations;
        public android.graphics.drawable.Drawable.ConstantState mDrawableState;
        public ColorStateList mTint;
        public android.graphics.PorterDuff.Mode mTintMode;

        public int getChangingConfigurations()
        {
            int j = mChangingConfigurations;
            int i;
            if (mDrawableState != null)
            {
                i = mDrawableState.getChangingConfigurations();
            } else
            {
                i = 0;
            }
            return i | j;
        }

        public Drawable newDrawable()
        {
            return newDrawable(null);
        }

        public abstract Drawable newDrawable(Resources resources);

        DrawableWrapperState(DrawableWrapperState drawablewrapperstate)
        {
            mTint = null;
            mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;
            if (drawablewrapperstate != null)
            {
                mChangingConfigurations = drawablewrapperstate.mChangingConfigurations;
                mDrawableState = drawablewrapperstate.mDrawableState;
                mTint = drawablewrapperstate.mTint;
                mTintMode = drawablewrapperstate.mTintMode;
            }
        }
    }


    private class DrawableWrapperStateBase extends DrawableWrapperState
    {

        public final Drawable newDrawable(Resources resources)
        {
            return new WrappedDrawableApi14(this, resources);
        }

        DrawableWrapperStateBase(DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate);
        }
    }

}
