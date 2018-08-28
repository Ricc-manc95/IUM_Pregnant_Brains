// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager, TintInfo, TintTypedArray, DrawableUtils

final class AppCompatBackgroundHelper
{

    public int mBackgroundResId;
    public TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view)
    {
        mBackgroundResId = -1;
        mView = view;
        if (AppCompatDrawableManager.INSTANCE == null)
        {
            view = new AppCompatDrawableManager();
            AppCompatDrawableManager.INSTANCE = view;
            AppCompatDrawableManager.installDefaultInflateDelegates(view);
        }
        mDrawableManager = AppCompatDrawableManager.INSTANCE;
    }

    final void applySupportBackgroundTint()
    {
        android.graphics.drawable.Drawable drawable;
        boolean flag;
        flag = false;
        drawable = mView.getBackground();
        if (drawable == null) goto _L2; else goto _L1
_L1:
        int i = android.os.Build.VERSION.SDK_INT;
        TintInfo tintinfo;
        Object obj;
        if (i > 21)
        {
            if (mInternalBackgroundTint != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        if (i == 21)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L4; else goto _L3
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
            obj = ViewCompat.getBackgroundTintList(mView);
            if (obj != null)
            {
                tintinfo.mHasTintList = true;
                tintinfo.mTintList = ((ColorStateList) (obj));
            }
            obj = ViewCompat.getBackgroundTintMode(mView);
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
        if (i == 0) goto _L4; else goto _L2
_L2:
        return;
_L4:
        if (mBackgroundTint != null)
        {
            AppCompatDrawableManager.tintDrawable(drawable, mBackgroundTint, mView.getDrawableState());
            return;
        }
        if (mInternalBackgroundTint != null)
        {
            AppCompatDrawableManager.tintDrawable(drawable, mInternalBackgroundTint, mView.getDrawableState());
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    final void loadFromAttributes(AttributeSet attributeset, int i)
    {
        Context context = mView.getContext();
        attributeset = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ViewBackgroundHelper, i, 0));
        ColorStateList colorstatelist;
        i = android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background;
        if (!((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            break MISSING_BLOCK_LABEL_87;
        }
        i = android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_android_background;
        mBackgroundResId = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(i, -1);
        colorstatelist = mDrawableManager.getTintList(mView.getContext(), mBackgroundResId);
        if (colorstatelist == null)
        {
            break MISSING_BLOCK_LABEL_87;
        }
        setInternalBackgroundTint(colorstatelist);
        i = android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            ViewCompat.setBackgroundTintList(mView, attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTint));
        }
        i = android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            View view = mView;
            i = android.support.v7.appcompat.R.styleable.ViewBackgroundHelper_backgroundTintMode;
            ViewCompat.setBackgroundTintMode(view, DrawableUtils.parseTintMode(((TintTypedArray) (attributeset)).mWrapped.getInt(i, -1), null));
        }
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        return;
        Exception exception;
        exception;
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        throw exception;
    }

    final void onSetBackgroundResource(int i)
    {
        mBackgroundResId = i;
        ColorStateList colorstatelist;
        if (mDrawableManager != null)
        {
            colorstatelist = mDrawableManager.getTintList(mView.getContext(), i);
        } else
        {
            colorstatelist = null;
        }
        setInternalBackgroundTint(colorstatelist);
        applySupportBackgroundTint();
    }

    final void setInternalBackgroundTint(ColorStateList colorstatelist)
    {
        if (colorstatelist != null)
        {
            if (mInternalBackgroundTint == null)
            {
                mInternalBackgroundTint = new TintInfo();
            }
            mInternalBackgroundTint.mTintList = colorstatelist;
            mInternalBackgroundTint.mHasTintList = true;
        } else
        {
            mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    final void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintList = colorstatelist;
        mBackgroundTint.mHasTintList = true;
        applySupportBackgroundTint();
    }

    final void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mBackgroundTint == null)
        {
            mBackgroundTint = new TintInfo();
        }
        mBackgroundTint.mTintMode = mode;
        mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }
}
