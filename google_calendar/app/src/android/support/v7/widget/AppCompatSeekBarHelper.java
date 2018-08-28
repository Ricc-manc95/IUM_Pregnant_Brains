// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatProgressBarHelper, TintTypedArray, DrawableUtils

final class AppCompatSeekBarHelper extends AppCompatProgressBarHelper
{

    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    public Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private android.graphics.PorterDuff.Mode mTickMarkTintMode;
    public final SeekBar mView;

    AppCompatSeekBarHelper(SeekBar seekbar)
    {
        super(seekbar);
        mTickMarkTintList = null;
        mTickMarkTintMode = null;
        mHasTickMarkTint = false;
        mHasTickMarkTintMode = false;
        mView = seekbar;
    }

    private final void applyTickMarkTint()
    {
        if (mTickMark != null && (mHasTickMarkTint || mHasTickMarkTintMode))
        {
            Object obj = mTickMark.mutate();
            if (android.os.Build.VERSION.SDK_INT < 23 && !(obj instanceof TintAwareDrawable))
            {
                obj = new WrappedDrawableApi21(((Drawable) (obj)));
            }
            mTickMark = ((Drawable) (obj));
            if (mHasTickMarkTint)
            {
                mTickMark.setTintList(mTickMarkTintList);
            }
            if (mHasTickMarkTintMode)
            {
                mTickMark.setTintMode(mTickMarkTintMode);
            }
            if (mTickMark.isStateful())
            {
                mTickMark.setState(mView.getDrawableState());
            }
        }
    }

    final void loadFromAttributes(AttributeSet attributeset, int i)
    {
        super.loadFromAttributes(attributeset, i);
        Object obj = mView.getContext();
        attributeset = new TintTypedArray(((Context) (obj)), ((Context) (obj)).obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatSeekBar, i, 0));
        obj = attributeset.getDrawableIfKnown(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_android_thumb);
        if (obj != null)
        {
            mView.setThumb(((Drawable) (obj)));
        }
        obj = attributeset.getDrawable(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMark);
        if (mTickMark != null)
        {
            mTickMark.setCallback(null);
        }
        mTickMark = ((Drawable) (obj));
        if (obj != null)
        {
            ((Drawable) (obj)).setCallback(mView);
            DrawableCompat.setLayoutDirection(((Drawable) (obj)), ViewCompat.getLayoutDirection(mView));
            if (((Drawable) (obj)).isStateful())
            {
                ((Drawable) (obj)).setState(mView.getDrawableState());
            }
            applyTickMarkTint();
        }
        mView.invalidate();
        i = android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTintMode;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            i = android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTintMode;
            mTickMarkTintMode = DrawableUtils.parseTintMode(((TintTypedArray) (attributeset)).mWrapped.getInt(i, -1), mTickMarkTintMode);
            mHasTickMarkTintMode = true;
        }
        i = android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTint;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            mTickMarkTintList = attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTint);
            mHasTickMarkTint = true;
        }
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        applyTickMarkTint();
    }
}
