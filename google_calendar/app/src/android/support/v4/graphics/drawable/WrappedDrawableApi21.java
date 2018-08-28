// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Log;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.graphics.drawable:
//            WrappedDrawableApi14

public final class WrappedDrawableApi21 extends WrappedDrawableApi14
{

    private static Method sIsProjectedDrawableMethod;

    public WrappedDrawableApi21(Drawable drawable)
    {
        super(drawable);
        findAndCacheIsProjectedDrawableMethod();
    }

    WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        super(drawablewrapperstate, resources);
        findAndCacheIsProjectedDrawableMethod();
    }

    private static void findAndCacheIsProjectedDrawableMethod()
    {
        if (sIsProjectedDrawableMethod != null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        sIsProjectedDrawableMethod = android/graphics/drawable/Drawable.getDeclaredMethod("isProjected", new Class[0]);
        return;
        Exception exception;
        exception;
        Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", exception);
        return;
    }

    public final Rect getDirtyBounds()
    {
        return mDrawable.getDirtyBounds();
    }

    public final void getOutline(Outline outline)
    {
        mDrawable.getOutline(outline);
    }

    protected final boolean isCompatTintEnabled()
    {
        boolean flag;
label0:
        {
            boolean flag1 = false;
            flag = flag1;
            if (android.os.Build.VERSION.SDK_INT != 21)
            {
                break label0;
            }
            Drawable drawable = mDrawable;
            if (!(drawable instanceof GradientDrawable) && !(drawable instanceof DrawableContainer) && !(drawable instanceof InsetDrawable))
            {
                flag = flag1;
                if (!(drawable instanceof RippleDrawable))
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    final WrappedDrawableApi14.DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateLollipop(mState, null);
    }

    public final void setHotspot(float f, float f1)
    {
        mDrawable.setHotspot(f, f1);
    }

    public final void setHotspotBounds(int i, int j, int k, int l)
    {
        mDrawable.setHotspotBounds(i, j, k, l);
    }

    public final boolean setState(int ai[])
    {
        if (super.setState(ai))
        {
            invalidateSelf();
            return true;
        } else
        {
            return false;
        }
    }

    public final void setTint(int i)
    {
        if (isCompatTintEnabled())
        {
            super.setTint(i);
            return;
        } else
        {
            mDrawable.setTint(i);
            return;
        }
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        if (isCompatTintEnabled())
        {
            super.setTintList(colorstatelist);
            return;
        } else
        {
            mDrawable.setTintList(colorstatelist);
            return;
        }
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (isCompatTintEnabled())
        {
            super.setTintMode(mode);
            return;
        } else
        {
            mDrawable.setTintMode(mode);
            return;
        }
    }

    private class DrawableWrapperStateLollipop extends WrappedDrawableApi14.DrawableWrapperState
    {

        public final Drawable newDrawable(Resources resources)
        {
            return new WrappedDrawableApi21(this, resources);
        }

        DrawableWrapperStateLollipop(WrappedDrawableApi14.DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate);
        }
    }

}
