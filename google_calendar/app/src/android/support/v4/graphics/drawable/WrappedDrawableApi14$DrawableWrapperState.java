// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            WrappedDrawableApi14

public abstract class mTintMode extends android.graphics.drawable.apperState
{

    public int mChangingConfigurations;
    public android.graphics.drawable.apperState mDrawableState;
    public ColorStateList mTint;
    public android.graphics.rawableWrapperState mTintMode;

    public int getChangingConfigurations()
    {
        int j = mChangingConfigurations;
        int i;
        if (mDrawableState != null)
        {
            i = mDrawableState.ations();
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

    ( )
    {
        mTint = null;
        mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;
        if ( != null)
        {
            mChangingConfigurations = .mChangingConfigurations;
            mDrawableState = .mDrawableState;
            mTint = .mTint;
            mTintMode = .mTintMode;
        }
    }
}
