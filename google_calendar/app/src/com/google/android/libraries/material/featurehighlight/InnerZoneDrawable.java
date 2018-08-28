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
import android.graphics.drawable.Drawable;

public final class InnerZoneDrawable extends Drawable
{

    public float centerX;
    public float centerY;
    public int finalAlpha;
    public final int margin;
    public final int minRadius;
    public final int padding;
    public final Paint paint = new Paint();
    private float pulseAlpha;
    private final int pulseBaseAlpha;
    public final Paint pulsePaint = new Paint();
    private float pulseScale;
    public float radius;
    private float scale;

    public InnerZoneDrawable(Context context)
    {
        scale = 1.0F;
        context = context.getResources();
        minRadius = context.getDimensionPixelSize(0x7f0e0287);
        padding = context.getDimensionPixelOffset(0x7f0e0286);
        margin = context.getDimensionPixelOffset(0x7f0e0285);
        pulseBaseAlpha = context.getInteger(0x7f110024);
        paint.setAntiAlias(true);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        pulsePaint.setAntiAlias(true);
        pulsePaint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(-1);
        finalAlpha = paint.getAlpha();
        pulsePaint.setColor(-1);
        invalidateSelf();
    }

    public final Animator createDismissAnimation()
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofFloat("scale", new float[] {
                getScale(), 0.0F
            }), PropertyValuesHolder.ofInt("alpha", new int[] {
                getAlpha(), 0
            }), PropertyValuesHolder.ofFloat("pulseScale", new float[] {
                getPulseScale(), 0.0F
            }), PropertyValuesHolder.ofFloat("pulseAlpha", new float[] {
                getPulseAlpha(), 0.0F
            })
        });
        objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
        return objectanimator.setDuration(200L);
    }

    public final Animator createShowAnimation(float f)
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofFloat("scale", new float[] {
                f, 1.0F
            }), PropertyValuesHolder.ofInt("alpha", new int[] {
                (int)((float)finalAlpha * f), finalAlpha
            })
        });
        objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.linearOutSlowIn);
        long l;
        if (f == 0.0F)
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
        if (pulseAlpha > 0.0F)
        {
            float f = radius;
            float f1 = pulseScale;
            pulsePaint.setAlpha((int)((float)pulseBaseAlpha * pulseAlpha));
            canvas.drawCircle(centerX, centerY, f * f1, pulsePaint);
        }
        canvas.drawCircle(centerX, centerY, radius * scale, paint);
    }

    public final int getAlpha()
    {
        return paint.getAlpha();
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final float getPulseAlpha()
    {
        return pulseAlpha;
    }

    public final float getPulseScale()
    {
        return pulseScale;
    }

    public final float getScale()
    {
        return scale;
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

    public final void setPulseAlpha(float f)
    {
        pulseAlpha = f;
        invalidateSelf();
    }

    public final void setPulseScale(float f)
    {
        pulseScale = f;
        invalidateSelf();
    }

    public final void setScale(float f)
    {
        scale = f;
        invalidateSelf();
    }
}
