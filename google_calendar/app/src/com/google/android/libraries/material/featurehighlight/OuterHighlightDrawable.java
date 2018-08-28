// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.util.TypedValue;

public final class OuterHighlightDrawable extends Drawable
{

    public int centerThresholdPx;
    public float centerX;
    public float centerY;
    public int finalAlpha;
    public int offsetHorizontalOffsetPx;
    public int offsetVerticalOffsetPx;
    public int outerVisualPadding;
    public final Paint paint = new Paint();
    public float radius;
    private float scale;
    public final Rect targetBounds = new Rect();
    public final Rect textBounds = new Rect();
    private float translationX;
    private float translationY;

    public OuterHighlightDrawable(Context context)
    {
        scale = 1.0F;
        translationX = 0.0F;
        translationY = 0.0F;
        finalAlpha = 244;
        TypedValue typedvalue = new TypedValue();
        int i;
        if (context.getTheme().resolveAttribute(0x1010433, typedvalue, true))
        {
            i = typedvalue.data;
        } else
        {
            i = context.getResources().getColor(0x7f0d01de);
        }
        i = ColorUtils.setAlphaComponent(i, 244);
        paint.setColor(i);
        finalAlpha = paint.getAlpha();
        invalidateSelf();
        paint.setAntiAlias(true);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        context = context.getResources();
        centerThresholdPx = context.getDimensionPixelSize(0x7f0e0282);
        offsetHorizontalOffsetPx = context.getDimensionPixelSize(0x7f0e0281);
        offsetVerticalOffsetPx = context.getDimensionPixelSize(0x7f0e0283);
        outerVisualPadding = context.getDimensionPixelSize(0x7f0e0289);
    }

    public final Animator createShowAnimation(float f, float f1, float f2)
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofFloat("scale", new float[] {
                f2, 1.0F
            }), PropertyValuesHolder.ofFloat("translationX", new float[] {
                (1.0F - f2) * f, 0.0F
            }), PropertyValuesHolder.ofFloat("translationY", new float[] {
                (1.0F - f2) * f1, 0.0F
            }), PropertyValuesHolder.ofInt("alpha", new int[] {
                (int)((float)finalAlpha * f2), finalAlpha
            })
        });
        objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.linearOutSlowIn);
        long l;
        if (f2 == 0.0F)
        {
            l = 350L;
        } else
        {
            l = 150L;
        }
        return objectanimator.setDuration(l);
    }

    public final void draw(Canvas canvas)
    {
        canvas.drawCircle(centerX + translationX, centerY + translationY, radius * scale, paint);
    }

    public final int getAlpha()
    {
        return paint.getAlpha();
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final float getScale()
    {
        return scale;
    }

    public final float getTranslationX()
    {
        return translationX;
    }

    public final float getTranslationY()
    {
        return translationY;
    }

    public final void setAlpha(int i)
    {
        paint.setAlpha(i);
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        paint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public final void setScale(float f)
    {
        scale = f;
        invalidateSelf();
    }

    public final void setTranslationX(float f)
    {
        translationX = f;
        invalidateSelf();
    }

    public final void setTranslationY(float f)
    {
        translationY = f;
        invalidateSelf();
    }
}
