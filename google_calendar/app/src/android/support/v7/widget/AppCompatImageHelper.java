// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            DrawableUtils, TintInfo, AppCompatDrawableManager, TintTypedArray

public final class AppCompatImageHelper
{

    public TintInfo mImageTint;
    private TintInfo mTmpInfo;
    public final ImageView mView;

    public AppCompatImageHelper(ImageView imageview)
    {
        mView = imageview;
    }

    final void applySupportImageTint()
    {
        Drawable drawable;
        boolean flag;
        flag = false;
        drawable = mView.getDrawable();
        if (drawable != null)
        {
            DrawableUtils.fixDrawable(drawable);
        }
        if (drawable == null) goto _L2; else goto _L1
_L1:
        int i = android.os.Build.VERSION.SDK_INT;
        TintInfo tintinfo;
        Object obj;
        if (i <= 21 && i == 21)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i) goto _L4; else goto _L3
_L3:
label0:
        {
            if (mTmpInfo == null)
            {
                mTmpInfo = new TintInfo();
            }
            tintinfo = mTmpInfo;
            tintinfo.mTintList = null;
            tintinfo.mHasTintList = false;
            tintinfo.mTintMode = null;
            tintinfo.mHasTintMode = false;
            obj = mView.getImageTintList();
            if (obj != null)
            {
                tintinfo.mHasTintList = true;
                tintinfo.mTintList = ((android.content.res.ColorStateList) (obj));
            }
            obj = mView.getImageTintMode();
            if (obj != null)
            {
                tintinfo.mHasTintMode = true;
                tintinfo.mTintMode = ((android.graphics.PorterDuff.Mode) (obj));
            }
            if (!tintinfo.mHasTintList)
            {
                i = ((flag) ? 1 : 0);
                if (!tintinfo.mHasTintMode)
                {
                    break label0;
                }
            }
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
            i = 1;
        }
        if (!i) goto _L4; else goto _L2
_L2:
        return;
_L4:
        if (mImageTint != null)
        {
            AppCompatDrawableManager.tintDrawable(drawable, mImageTint, mView.getDrawableState());
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final void loadFromAttributes(AttributeSet attributeset, int i)
    {
        TintTypedArray tinttypedarray;
        boolean flag;
        flag = true;
        Context context = mView.getContext();
        tinttypedarray = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatImageView, i, 0));
        Drawable drawable = mView.getDrawable();
        attributeset = drawable;
        if (drawable != null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        i = android.support.v7.appcompat.R.styleable.AppCompatImageView_srcCompat;
        i = tinttypedarray.mWrapped.getResourceId(i, -1);
        attributeset = drawable;
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        drawable = AppCompatResources.getDrawable(mView.getContext(), i);
        attributeset = drawable;
        if (drawable == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        mView.setImageDrawable(drawable);
        attributeset = drawable;
        if (attributeset == null)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        DrawableUtils.fixDrawable(attributeset);
        i = android.support.v7.appcompat.R.styleable.AppCompatImageView_tint;
        if (!tinttypedarray.mWrapped.hasValue(i))
        {
            break MISSING_BLOCK_LABEL_194;
        }
        attributeset = mView;
        attributeset.setImageTintList(tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.AppCompatImageView_tint));
        if (android.os.Build.VERSION.SDK_INT != 21)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        drawable = attributeset.getDrawable();
        if (attributeset.getImageTintList() != null && attributeset.getImageTintMode() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (drawable == null || i == 0)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        if (drawable.isStateful())
        {
            drawable.setState(attributeset.getDrawableState());
        }
        attributeset.setImageDrawable(drawable);
        i = android.support.v7.appcompat.R.styleable.AppCompatImageView_tintMode;
        if (!tinttypedarray.mWrapped.hasValue(i))
        {
            break MISSING_BLOCK_LABEL_296;
        }
        attributeset = mView;
        i = android.support.v7.appcompat.R.styleable.AppCompatImageView_tintMode;
        attributeset.setImageTintMode(DrawableUtils.parseTintMode(tinttypedarray.mWrapped.getInt(i, -1), null));
        if (android.os.Build.VERSION.SDK_INT != 21)
        {
            break MISSING_BLOCK_LABEL_296;
        }
        drawable = attributeset.getDrawable();
        if (attributeset.getImageTintList() != null && attributeset.getImageTintMode() != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (drawable == null || i == 0)
        {
            break MISSING_BLOCK_LABEL_296;
        }
        if (drawable.isStateful())
        {
            drawable.setState(attributeset.getDrawableState());
        }
        attributeset.setImageDrawable(drawable);
        tinttypedarray.mWrapped.recycle();
        return;
        attributeset;
        tinttypedarray.mWrapped.recycle();
        throw attributeset;
    }

    public final void setImageResource(int i)
    {
        if (i != 0)
        {
            Drawable drawable = AppCompatResources.getDrawable(mView.getContext(), i);
            if (drawable != null)
            {
                DrawableUtils.fixDrawable(drawable);
            }
            mView.setImageDrawable(drawable);
        } else
        {
            mView.setImageDrawable(null);
        }
        applySupportImageTint();
    }
}
