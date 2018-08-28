// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

public final class ShadowDrawableWrapper extends DrawableWrapper
{

    private static final double COS_45 = Math.cos(Math.toRadians(45D));
    public boolean addPaddingForCorners;
    private final RectF contentBounds = new RectF();
    private float cornerRadius;
    private final Paint cornerShadowPaint = new Paint(5);
    private Path cornerShadowPath;
    private boolean dirty;
    private final Paint edgeShadowPaint;
    private boolean printedShadowClipWarning;
    public float rawMaxShadowSize;
    public float rawShadowSize;
    public float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    private float shadowSize;
    private final int shadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f, float f1, float f2)
    {
        super(drawable);
        dirty = true;
        addPaddingForCorners = true;
        printedShadowClipWarning = false;
        shadowStartColor = ContextCompat.getColor(context, 0x7f0d008f);
        shadowMiddleColor = ContextCompat.getColor(context, 0x7f0d008e);
        shadowEndColor = ContextCompat.getColor(context, 0x7f0d008d);
        cornerShadowPaint.setStyle(android.graphics.Paint.Style.FILL);
        cornerRadius = Math.round(f);
        edgeShadowPaint = new Paint(cornerShadowPaint);
        edgeShadowPaint.setAntiAlias(false);
        setShadowSize(f1, f2);
    }

    public static float calculateHorizontalPadding(float f, float f1, boolean flag)
    {
        float f2 = f;
        if (flag)
        {
            f2 = (float)((double)f + (1.0D - COS_45) * (double)f1);
        }
        return f2;
    }

    public static float calculateVerticalPadding(float f, float f1, boolean flag)
    {
        if (flag)
        {
            return (float)((double)(1.5F * f) + (1.0D - COS_45) * (double)f1);
        } else
        {
            return 1.5F * f;
        }
    }

    public final void draw(Canvas canvas)
    {
        int j;
        boolean flag;
        if (dirty)
        {
            Object obj = getBounds();
            float f = rawMaxShadowSize * 1.5F;
            contentBounds.set((float)((Rect) (obj)).left + rawMaxShadowSize, (float)((Rect) (obj)).top + f, (float)((Rect) (obj)).right - rawMaxShadowSize, (float)((Rect) (obj)).bottom - f);
            super.mDrawable.setBounds((int)contentBounds.left, (int)contentBounds.top, (int)contentBounds.right, (int)contentBounds.bottom);
            obj = new RectF(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius);
            RectF rectf = new RectF(((RectF) (obj)));
            rectf.inset(-shadowSize, -shadowSize);
            float f2;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            Paint paint1;
            int l;
            int j1;
            int k1;
            if (cornerShadowPath == null)
            {
                cornerShadowPath = new Path();
            } else
            {
                cornerShadowPath.reset();
            }
            cornerShadowPath.setFillType(android.graphics.Path.FillType.EVEN_ODD);
            cornerShadowPath.moveTo(-cornerRadius, 0.0F);
            cornerShadowPath.rLineTo(-shadowSize, 0.0F);
            cornerShadowPath.arcTo(rectf, 180F, 90F, false);
            cornerShadowPath.arcTo(((RectF) (obj)), 270F, -90F, false);
            cornerShadowPath.close();
            f = -rectf.top;
            if (f > 0.0F)
            {
                float f1 = cornerRadius / f;
                float f3 = (1.0F - f1) / 2.0F;
                Paint paint = cornerShadowPaint;
                int i = shadowStartColor;
                int k = shadowMiddleColor;
                int i1 = shadowEndColor;
                android.graphics.Shader.TileMode tilemode = android.graphics.Shader.TileMode.CLAMP;
                paint.setShader(new RadialGradient(0.0F, 0.0F, f, new int[] {
                    0, i, k, i1
                }, new float[] {
                    0.0F, f1, f1 + f3, 1.0F
                }, tilemode));
            }
            paint1 = edgeShadowPaint;
            f = ((RectF) (obj)).top;
            f2 = rectf.top;
            j = shadowStartColor;
            l = shadowMiddleColor;
            j1 = shadowEndColor;
            obj = android.graphics.Shader.TileMode.CLAMP;
            paint1.setShader(new LinearGradient(0.0F, f, 0.0F, f2, new int[] {
                j, l, j1
            }, new float[] {
                0.0F, 0.5F, 1.0F
            }, ((android.graphics.Shader.TileMode) (obj))));
            edgeShadowPaint.setAntiAlias(false);
            dirty = false;
        }
        j1 = canvas.save();
        canvas.rotate(rotation, contentBounds.centerX(), contentBounds.centerY());
        f = -cornerRadius - shadowSize;
        f2 = cornerRadius;
        if (contentBounds.width() - 2.0F * f2 > 0.0F)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (contentBounds.height() - 2.0F * f2 > 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        f7 = rawShadowSize;
        f8 = rawShadowSize;
        f4 = rawShadowSize;
        f9 = rawShadowSize;
        f5 = rawShadowSize;
        f6 = rawShadowSize;
        f4 = f2 / ((f4 - f9 * 0.5F) + f2);
        f7 = f2 / ((f7 - f8 * 0.25F) + f2);
        f5 = f2 / (f2 + (f5 - f6));
        k1 = canvas.save();
        canvas.translate(contentBounds.left + f2, contentBounds.top + f2);
        canvas.scale(f4, f7);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (j != 0)
        {
            canvas.scale(1.0F / f4, 1.0F);
            canvas.drawRect(0.0F, f, contentBounds.width() - 2.0F * f2, -cornerRadius, edgeShadowPaint);
        }
        canvas.restoreToCount(k1);
        k1 = canvas.save();
        canvas.translate(contentBounds.right - f2, contentBounds.bottom - f2);
        canvas.scale(f4, f5);
        canvas.rotate(180F);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (j != 0)
        {
            canvas.scale(1.0F / f4, 1.0F);
            f6 = contentBounds.width();
            f8 = -cornerRadius;
            canvas.drawRect(0.0F, f, f6 - 2.0F * f2, shadowSize + f8, edgeShadowPaint);
        }
        canvas.restoreToCount(k1);
        j = canvas.save();
        canvas.translate(contentBounds.left + f2, contentBounds.bottom - f2);
        canvas.scale(f4, f5);
        canvas.rotate(270F);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (flag)
        {
            canvas.scale(1.0F / f5, 1.0F);
            canvas.drawRect(0.0F, f, contentBounds.height() - 2.0F * f2, -cornerRadius, edgeShadowPaint);
        }
        canvas.restoreToCount(j);
        j = canvas.save();
        canvas.translate(contentBounds.right - f2, contentBounds.top + f2);
        canvas.scale(f4, f7);
        canvas.rotate(90F);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (flag)
        {
            canvas.scale(1.0F / f7, 1.0F);
            canvas.drawRect(0.0F, f, contentBounds.height() - 2.0F * f2, -cornerRadius, edgeShadowPaint);
        }
        canvas.restoreToCount(j);
        canvas.restoreToCount(j1);
        super.draw(canvas);
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final boolean getPadding(Rect rect)
    {
        float f = rawMaxShadowSize;
        float f1 = cornerRadius;
        float f2;
        int i;
        int j;
        if (addPaddingForCorners)
        {
            double d = f * 1.5F;
            double d2 = COS_45;
            f = (float)((double)f1 * (1.0D - d2) + d);
        } else
        {
            f *= 1.5F;
        }
        i = (int)Math.ceil(f);
        f1 = rawMaxShadowSize;
        f2 = cornerRadius;
        f = f1;
        if (addPaddingForCorners)
        {
            double d1 = f1;
            double d3 = COS_45;
            f = (float)((double)f2 * (1.0D - d3) + d1);
        }
        j = (int)Math.ceil(f);
        rect.set(j, i, j, i);
        return true;
    }

    protected final void onBoundsChange(Rect rect)
    {
        dirty = true;
    }

    public final void setAlpha(int i)
    {
        super.setAlpha(i);
        cornerShadowPaint.setAlpha(i);
        edgeShadowPaint.setAlpha(i);
    }

    public final void setShadowSize(float f, float f1)
    {
        if (f < 0.0F || f1 < 0.0F)
        {
            throw new IllegalArgumentException("invalid shadow size");
        }
        int j = Math.round(f);
        int i = j;
        if (j % 2 == 1)
        {
            i = j - 1;
        }
        float f2 = i;
        j = Math.round(f1);
        i = j;
        if (j % 2 == 1)
        {
            i = j - 1;
        }
        f1 = i;
        f = f2;
        if (f2 > f1)
        {
            if (!printedShadowClipWarning)
            {
                printedShadowClipWarning = true;
            }
            f = f1;
        }
        if (rawShadowSize == f && rawMaxShadowSize == f1)
        {
            return;
        } else
        {
            rawShadowSize = f;
            rawMaxShadowSize = f1;
            shadowSize = Math.round(f * 1.5F);
            dirty = true;
            invalidateSelf();
            return;
        }
    }

}
