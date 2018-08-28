// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCompat

final class mAutoMirrored extends android.graphics.drawable.atState
{

    public boolean mAutoMirrored;
    public boolean mCacheDirty;
    public boolean mCachedAutoMirrored;
    public Bitmap mCachedBitmap;
    public int mCachedRootAlpha;
    public ColorStateList mCachedTint;
    public android.graphics.wableCompatState mCachedTintMode;
    public int mChangingConfigurations;
    public Paint mTempPaint;
    public ColorStateList mTint;
    public android.graphics.wableCompatState mTintMode;
    public mChangingConfigurations mVPathRenderer;

    public final int getChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public final Drawable newDrawable()
    {
        return new VectorDrawableCompat(this);
    }

    public final Drawable newDrawable(Resources resources)
    {
        return new VectorDrawableCompat(this);
    }

    public ()
    {
        mTint = null;
        mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
        mVPathRenderer = new mVPathRenderer();
    }

    public mVPathRenderer(mVPathRenderer mvpathrenderer)
    {
        mTint = null;
        mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
        if (mvpathrenderer != null)
        {
            mChangingConfigurations = mvpathrenderer.mChangingConfigurations;
            mVPathRenderer = new mVPathRenderer(mvpathrenderer.mVPathRenderer);
            if (mvpathrenderer.mVPathRenderer.mVPathRenderer != null)
            {
                mVPathRenderer.mVPathRenderer = new Paint(mvpathrenderer.mVPathRenderer.mVPathRenderer);
            }
            if (mvpathrenderer.mVPathRenderer. != null)
            {
                mVPathRenderer. = new Paint(mvpathrenderer.mVPathRenderer.);
            }
            mTint = mvpathrenderer.mTint;
            mTintMode = mvpathrenderer.mTintMode;
            mAutoMirrored = mvpathrenderer.mAutoMirrored;
        }
    }
}
