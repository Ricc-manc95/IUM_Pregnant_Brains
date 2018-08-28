// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;

public class CircularBorderDrawable extends Drawable
{

    private ColorStateList borderTint;
    public float borderWidth;
    public int bottomInnerStrokeColor;
    public int bottomOuterStrokeColor;
    private int currentBorderTintColor;
    public boolean invalidateShader;
    public final Paint paint = new Paint(1);
    public final Rect rect = new Rect();
    private final RectF rectF = new RectF();
    public float rotation;
    public int topInnerStrokeColor;
    public int topOuterStrokeColor;

    public CircularBorderDrawable()
    {
        invalidateShader = true;
        paint.setStyle(android.graphics.Paint.Style.STROKE);
    }

    public void draw(Canvas canvas)
    {
        if (invalidateShader)
        {
            Paint paint1 = paint;
            Object obj = rect;
            copyBounds(((Rect) (obj)));
            float f = borderWidth / (float)((Rect) (obj)).height();
            int i = ColorUtils.compositeColors(topOuterStrokeColor, currentBorderTintColor);
            int j = ColorUtils.compositeColors(topInnerStrokeColor, currentBorderTintColor);
            int k = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(topInnerStrokeColor, 0), currentBorderTintColor);
            int l = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(bottomInnerStrokeColor, 0), currentBorderTintColor);
            int i1 = ColorUtils.compositeColors(bottomInnerStrokeColor, currentBorderTintColor);
            int j1 = ColorUtils.compositeColors(bottomOuterStrokeColor, currentBorderTintColor);
            float f2 = ((Rect) (obj)).top;
            float f3 = ((Rect) (obj)).bottom;
            obj = android.graphics.Shader.TileMode.CLAMP;
            paint1.setShader(new LinearGradient(0.0F, f2, 0.0F, f3, new int[] {
                i, j, k, l, i1, j1
            }, new float[] {
                0.0F, f, 0.5F, 0.5F, 1.0F - f, 1.0F
            }, ((android.graphics.Shader.TileMode) (obj))));
            invalidateShader = false;
        }
        float f1 = paint.getStrokeWidth() / 2.0F;
        RectF rectf = rectF;
        copyBounds(rect);
        rectf.set(rect);
        rectf.left = rectf.left + f1;
        rectf.top = rectf.top + f1;
        rectf.right = rectf.right - f1;
        rectf.bottom = rectf.bottom - f1;
        canvas.save();
        canvas.rotate(rotation, rectf.centerX(), rectf.centerY());
        canvas.drawOval(rectf, paint);
        canvas.restore();
    }

    public int getOpacity()
    {
        return borderWidth <= 0.0F ? -2 : -3;
    }

    public boolean getPadding(Rect rect1)
    {
        int i = Math.round(borderWidth);
        rect1.set(i, i, i, i);
        return true;
    }

    public boolean isStateful()
    {
        return borderTint != null && borderTint.isStateful() || super.isStateful();
    }

    protected void onBoundsChange(Rect rect1)
    {
        invalidateShader = true;
    }

    protected boolean onStateChange(int ai[])
    {
        if (borderTint != null)
        {
            int i = borderTint.getColorForState(ai, currentBorderTintColor);
            if (i != currentBorderTintColor)
            {
                invalidateShader = true;
                currentBorderTintColor = i;
            }
        }
        if (invalidateShader)
        {
            invalidateSelf();
        }
        return invalidateShader;
    }

    public void setAlpha(int i)
    {
        paint.setAlpha(i);
        invalidateSelf();
    }

    public final void setBorderTint(ColorStateList colorstatelist)
    {
        if (colorstatelist != null)
        {
            currentBorderTintColor = colorstatelist.getColorForState(getState(), currentBorderTintColor);
        }
        borderTint = colorstatelist;
        invalidateShader = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        paint.setColorFilter(colorfilter);
        invalidateSelf();
    }
}
