// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.bitmap.drawable.BasicBitmapDrawable;

public class BitmapDrawableImageView extends ImageView
{

    private static final boolean HAS_TRANSIENT_STATE_SUPPORTED = true;
    private boolean attachedToWindow;
    private BasicBitmapDrawable drawable;
    private boolean shouldUnbindOnDetachFromWindow;

    public BitmapDrawableImageView(Context context)
    {
        this(context, null);
    }

    public BitmapDrawableImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public BitmapDrawableImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        shouldUnbindOnDetachFromWindow = true;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        attachedToWindow = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        attachedToWindow = false;
        if (HAS_TRANSIENT_STATE_SUPPORTED && !hasTransientState())
        {
            if (!shouldUnbindOnDetachFromWindow);
        }
    }

    public void setHasTransientState(boolean flag)
    {
        super.setHasTransientState(flag);
        if (!flag && !attachedToWindow)
        {
            if (!shouldUnbindOnDetachFromWindow);
        }
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        super.setImageBitmap(bitmap);
        drawable = null;
    }

    public void setImageDrawable(Drawable drawable1)
    {
        super.setImageDrawable(drawable1);
        drawable = null;
    }

    public void setImageResource(int i)
    {
        super.setImageResource(i);
        drawable = null;
    }

    public void setImageURI(Uri uri)
    {
        super.setImageURI(uri);
        drawable = null;
    }

}
