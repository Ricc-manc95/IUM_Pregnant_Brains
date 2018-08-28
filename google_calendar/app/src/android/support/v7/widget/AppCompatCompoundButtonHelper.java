// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

// Referenced classes of package android.support.v7.widget:
//            DrawableUtils

final class AppCompatCompoundButtonHelper
{

    public ColorStateList mButtonTintList;
    public android.graphics.PorterDuff.Mode mButtonTintMode;
    public boolean mHasButtonTint;
    public boolean mHasButtonTintMode;
    public boolean mSkipNextApply;
    private final CompoundButton mView;

    AppCompatCompoundButtonHelper(CompoundButton compoundbutton)
    {
        mButtonTintList = null;
        mButtonTintMode = null;
        mHasButtonTint = false;
        mHasButtonTintMode = false;
        mView = compoundbutton;
    }

    final void applyButtonTint()
    {
        Object obj = CompoundButtonCompat.getButtonDrawable(mView);
        if (obj != null && (mHasButtonTint || mHasButtonTintMode))
        {
            if (android.os.Build.VERSION.SDK_INT < 23 && !(obj instanceof TintAwareDrawable))
            {
                obj = new WrappedDrawableApi21(((Drawable) (obj)));
            }
            obj = ((Drawable) (obj)).mutate();
            if (mHasButtonTint)
            {
                ((Drawable) (obj)).setTintList(mButtonTintList);
            }
            if (mHasButtonTintMode)
            {
                ((Drawable) (obj)).setTintMode(mButtonTintMode);
            }
            if (((Drawable) (obj)).isStateful())
            {
                ((Drawable) (obj)).setState(mView.getDrawableState());
            }
            mView.setButtonDrawable(((Drawable) (obj)));
        }
    }

    final void loadFromAttributes(AttributeSet attributeset, int i)
    {
        attributeset = mView.getContext().obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.CompoundButton, i, 0);
        if (!attributeset.hasValue(android.support.v7.appcompat.R.styleable.CompoundButton_android_button))
        {
            break MISSING_BLOCK_LABEL_58;
        }
        i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.CompoundButton_android_button, 0);
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        mView.setButtonDrawable(AppCompatResources.getDrawable(mView.getContext(), i));
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.CompoundButton_buttonTint))
        {
            mView.setButtonTintList(attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.CompoundButton_buttonTint));
        }
        if (attributeset.hasValue(android.support.v7.appcompat.R.styleable.CompoundButton_buttonTintMode))
        {
            mView.setButtonTintMode(DrawableUtils.parseTintMode(attributeset.getInt(android.support.v7.appcompat.R.styleable.CompoundButton_buttonTintMode, -1), null));
        }
        attributeset.recycle();
        return;
        Exception exception;
        exception;
        attributeset.recycle();
        throw exception;
    }
}
