// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;

public class BackgroundImageView extends View
{

    private Paint bottomBackgroundPaint;
    public ListenableBitmapDrawable drawable;
    private int initialTranslationX;
    private int initialTranslationY;
    private Paint topBackgroundPaint;
    private float translationY;

    public BackgroundImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        topBackgroundPaint = new Paint();
        bottomBackgroundPaint = new Paint();
    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof BackgroundImageView))
        {
            obj = (BackgroundImageView)obj;
            if ((((BackgroundImageView) (obj)).drawable != null || drawable != null) && ((BasicBitmapDrawable) (((BackgroundImageView) (obj)).drawable)).mCurrKey.equals(((BasicBitmapDrawable) (drawable)).mCurrKey) && ((BackgroundImageView) (obj)).initialTranslationY == initialTranslationY && (float)((BackgroundImageView) (obj)).initialTranslationY + ((BackgroundImageView) (obj)).translationY == (float)initialTranslationY + translationY)
            {
                return true;
            }
        }
        return false;
    }

    public void onDraw(Canvas canvas)
    {
        if (drawable == null) goto _L2; else goto _L1
_L1:
        float f = (float)initialTranslationY + translationY;
        if (f <= 0.0F) goto _L4; else goto _L3
_L3:
        canvas.drawRect(0.0F, 0.0F, getWidth(), f, topBackgroundPaint);
_L6:
        canvas.translate(initialTranslationX, (float)initialTranslationY + translationY);
        super.onDraw(canvas);
        drawable.draw(canvas);
        canvas.translate(-(float)initialTranslationX, -((float)initialTranslationY + translationY));
_L2:
        return;
_L4:
        if (f < 0.0F)
        {
            canvas.drawRect(0.0F, (float)getHeight() + f, getWidth(), getHeight(), bottomBackgroundPaint);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setBottomBackgroundColor(int i)
    {
        bottomBackgroundPaint.setColor(i);
    }

    public void setClippingTranslationY(float f)
    {
        translationY = f;
        invalidate();
    }

    public void setInitialTranslationX(int i)
    {
        initialTranslationX = i;
    }

    public void setInitialTranslationY(int i)
    {
        initialTranslationY = i;
    }

    public void setTopBackgroundColor(int i)
    {
        topBackgroundPaint.setColor(i);
    }

    protected boolean verifyDrawable(Drawable drawable1)
    {
        return drawable1 == drawable || super.verifyDrawable(drawable1);
    }
}
