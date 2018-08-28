// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.TintAwareDrawable;

abstract class VectorDrawableCommon extends Drawable
    implements TintAwareDrawable
{

    public Drawable mDelegateDrawable;

    VectorDrawableCommon()
    {
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.applyTheme(theme);
        }
    }

    public void clearColorFilter()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.clearColorFilter();
            return;
        } else
        {
            super.clearColorFilter();
            return;
        }
    }

    public ColorFilter getColorFilter()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getColorFilter();
        } else
        {
            return null;
        }
    }

    public Drawable getCurrent()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getCurrent();
        } else
        {
            return super.getCurrent();
        }
    }

    public int getMinimumHeight()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getMinimumHeight();
        } else
        {
            return super.getMinimumHeight();
        }
    }

    public int getMinimumWidth()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getMinimumWidth();
        } else
        {
            return super.getMinimumWidth();
        }
    }

    public boolean getPadding(Rect rect)
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getPadding(rect);
        } else
        {
            return super.getPadding(rect);
        }
    }

    public int[] getState()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getState();
        } else
        {
            return super.getState();
        }
    }

    public Region getTransparentRegion()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getTransparentRegion();
        } else
        {
            return super.getTransparentRegion();
        }
    }

    public void jumpToCurrentState()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.jumpToCurrentState();
        }
    }

    protected void onBoundsChange(Rect rect)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setBounds(rect);
            return;
        } else
        {
            super.onBoundsChange(rect);
            return;
        }
    }

    protected boolean onLevelChange(int i)
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setLevel(i);
        } else
        {
            return super.onLevelChange(i);
        }
    }

    public void setChangingConfigurations(int i)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setChangingConfigurations(i);
            return;
        } else
        {
            super.setChangingConfigurations(i);
            return;
        }
    }

    public void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(i, mode);
            return;
        } else
        {
            super.setColorFilter(i, mode);
            return;
        }
    }

    public void setFilterBitmap(boolean flag)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setFilterBitmap(flag);
        }
    }

    public void setHotspot(float f, float f1)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setHotspot(f, f1);
        }
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setHotspotBounds(i, j, k, l);
        }
    }

    public boolean setState(int ai[])
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setState(ai);
        } else
        {
            return super.setState(ai);
        }
    }
}
