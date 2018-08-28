// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

final class RoundRectDrawableWithShadow extends Drawable
{

    private static final double COS_45 = Math.cos(Math.toRadians(45D));
    public static RoundRectHelper sRoundRectHelper;
    public boolean mAddPaddingForCorners;
    private ColorStateList mBackground;
    private final RectF mCardBounds;
    public float mCornerRadius;
    private Paint mCornerShadowPaint;
    private Path mCornerShadowPath;
    public boolean mDirty;
    private Paint mEdgeShadowPaint;
    public final int mInsetShadow;
    private Paint mPaint;
    public float mRawMaxShadowSize;
    public float mRawShadowSize;
    private final int mShadowEndColor;
    private float mShadowSize;
    private final int mShadowStartColor;

    static float calculateHorizontalPadding(float f, float f1, boolean flag)
    {
        float f2 = f;
        if (flag)
        {
            f2 = (float)((double)f + (1.0D - COS_45) * (double)f1);
        }
        return f2;
    }

    static float calculateVerticalPadding(float f, float f1, boolean flag)
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
        boolean flag = true;
        int i;
        if (mDirty)
        {
            Object obj = getBounds();
            float f = mRawMaxShadowSize * 1.5F;
            mCardBounds.set((float)((Rect) (obj)).left + mRawMaxShadowSize, (float)((Rect) (obj)).top + f, (float)((Rect) (obj)).right - mRawMaxShadowSize, (float)((Rect) (obj)).bottom - f);
            obj = new RectF(-mCornerRadius, -mCornerRadius, mCornerRadius, mCornerRadius);
            Object obj1 = new RectF(((RectF) (obj)));
            ((RectF) (obj1)).inset(-mShadowSize, -mShadowSize);
            float f1;
            float f2;
            float f3;
            int j;
            int k;
            if (mCornerShadowPath == null)
            {
                mCornerShadowPath = new Path();
            } else
            {
                mCornerShadowPath.reset();
            }
            mCornerShadowPath.setFillType(android.graphics.Path.FillType.EVEN_ODD);
            mCornerShadowPath.moveTo(-mCornerRadius, 0.0F);
            mCornerShadowPath.rLineTo(-mShadowSize, 0.0F);
            mCornerShadowPath.arcTo(((RectF) (obj1)), 180F, 90F, false);
            mCornerShadowPath.arcTo(((RectF) (obj)), 270F, -90F, false);
            mCornerShadowPath.close();
            f = mCornerRadius / (mCornerRadius + mShadowSize);
            obj = mCornerShadowPaint;
            f1 = mCornerRadius;
            f2 = mShadowSize;
            i = mShadowStartColor;
            j = mShadowStartColor;
            k = mShadowEndColor;
            obj1 = android.graphics.Shader.TileMode.CLAMP;
            ((Paint) (obj)).setShader(new RadialGradient(0.0F, 0.0F, f1 + f2, new int[] {
                i, j, k
            }, new float[] {
                0.0F, f, 1.0F
            }, ((android.graphics.Shader.TileMode) (obj1))));
            obj = mEdgeShadowPaint;
            f = -mCornerRadius;
            f1 = mShadowSize;
            f2 = -mCornerRadius;
            f3 = mShadowSize;
            i = mShadowStartColor;
            j = mShadowStartColor;
            k = mShadowEndColor;
            obj1 = android.graphics.Shader.TileMode.CLAMP;
            ((Paint) (obj)).setShader(new LinearGradient(0.0F, f + f1, 0.0F, f2 - f3, new int[] {
                i, j, k
            }, new float[] {
                0.0F, 0.5F, 1.0F
            }, ((android.graphics.Shader.TileMode) (obj1))));
            mEdgeShadowPaint.setAntiAlias(false);
            mDirty = false;
        }
        canvas.translate(0.0F, mRawShadowSize / 2.0F);
        f = -mCornerRadius - mShadowSize;
        f1 = mCornerRadius + (float)mInsetShadow + mRawShadowSize / 2.0F;
        if (mCardBounds.width() - f1 * 2.0F > 0.0F)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (mCardBounds.height() - f1 * 2.0F <= 0.0F)
        {
            flag = false;
        }
        j = canvas.save();
        canvas.translate(mCardBounds.left + f1, mCardBounds.top + f1);
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
        if (i != 0)
        {
            canvas.drawRect(0.0F, f, mCardBounds.width() - f1 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
        }
        canvas.restoreToCount(j);
        j = canvas.save();
        canvas.translate(mCardBounds.right - f1, mCardBounds.bottom - f1);
        canvas.rotate(180F);
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
        if (i != 0)
        {
            f2 = mCardBounds.width();
            f3 = -mCornerRadius;
            canvas.drawRect(0.0F, f, f2 - f1 * 2.0F, mShadowSize + f3, mEdgeShadowPaint);
        }
        canvas.restoreToCount(j);
        i = canvas.save();
        canvas.translate(mCardBounds.left + f1, mCardBounds.bottom - f1);
        canvas.rotate(270F);
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
        if (flag)
        {
            canvas.drawRect(0.0F, f, mCardBounds.height() - f1 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
        }
        canvas.restoreToCount(i);
        i = canvas.save();
        canvas.translate(mCardBounds.right - f1, mCardBounds.top + f1);
        canvas.rotate(90F);
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
        if (flag)
        {
            canvas.drawRect(0.0F, f, mCardBounds.height() - f1 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
        }
        canvas.restoreToCount(i);
        canvas.translate(0.0F, -mRawShadowSize / 2.0F);
        sRoundRectHelper.drawRoundRect(canvas, mCardBounds, mCornerRadius, mPaint);
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final boolean getPadding(Rect rect)
    {
        float f = mRawMaxShadowSize;
        float f1 = mCornerRadius;
        float f2;
        int i;
        int j;
        if (mAddPaddingForCorners)
        {
            double d = f * 1.5F;
            double d2 = COS_45;
            f = (float)((double)f1 * (1.0D - d2) + d);
        } else
        {
            f *= 1.5F;
        }
        i = (int)Math.ceil(f);
        f1 = mRawMaxShadowSize;
        f2 = mCornerRadius;
        f = f1;
        if (mAddPaddingForCorners)
        {
            double d1 = f1;
            double d3 = COS_45;
            f = (float)((double)f2 * (1.0D - d3) + d1);
        }
        j = (int)Math.ceil(f);
        rect.set(j, i, j, i);
        return true;
    }

    public final boolean isStateful()
    {
        return mBackground != null && mBackground.isStateful() || super.isStateful();
    }

    protected final void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        mDirty = true;
    }

    protected final boolean onStateChange(int ai[])
    {
        int i = mBackground.getColorForState(ai, mBackground.getDefaultColor());
        if (mPaint.getColor() == i)
        {
            return false;
        } else
        {
            mPaint.setColor(i);
            mDirty = true;
            invalidateSelf();
            return true;
        }
    }

    public final void setAlpha(int i)
    {
        mPaint.setAlpha(i);
        mCornerShadowPaint.setAlpha(i);
        mEdgeShadowPaint.setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
    }


    private class RoundRectHelper
    {

        public abstract void drawRoundRect(Canvas canvas, RectF rectf, float f, Paint paint);
    }

}
