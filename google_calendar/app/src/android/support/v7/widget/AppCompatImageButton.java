// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatImageHelper

public class AppCompatImageButton extends ImageButton
{

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatImageHelper mImageHelper;

    public AppCompatImageButton(Context context)
    {
        this(context, null);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100ae);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mImageHelper = new AppCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attributeset, i);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (mImageHelper != null)
        {
            mImageHelper.applySupportImageTint();
        }
    }

    public boolean hasOverlappingRendering()
    {
        boolean flag;
        if (mImageHelper.mView.getBackground() instanceof RippleDrawable)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        return flag && super.hasOverlappingRendering();
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if (mBackgroundTintHelper != null)
        {
            drawable = mBackgroundTintHelper;
            drawable.mBackgroundResId = -1;
            drawable.setInternalBackgroundTint(null);
            drawable.applySupportBackgroundTint();
        }
    }

    public void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        super.setImageBitmap(bitmap);
        if (mImageHelper != null)
        {
            mImageHelper.applySupportImageTint();
        }
    }

    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        if (mImageHelper != null)
        {
            mImageHelper.applySupportImageTint();
        }
    }

    public void setImageResource(int i)
    {
        mImageHelper.setImageResource(i);
    }

    public void setImageURI(Uri uri)
    {
        super.setImageURI(uri);
        if (mImageHelper != null)
        {
            mImageHelper.applySupportImageTint();
        }
    }
}
