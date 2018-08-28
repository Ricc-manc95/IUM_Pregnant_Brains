// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public class RoundedBitmapDrawable extends Drawable
{

    public boolean mApplyGravity;
    private final Bitmap mBitmap;
    public int mBitmapHeight;
    public final BitmapShader mBitmapShader;
    public int mBitmapWidth;
    public float mCornerRadius;
    public final Rect mDstRect = new Rect();
    private final RectF mDstRectF = new RectF();
    private int mGravity;
    public boolean mIsCircular;
    public final Paint mPaint = new Paint(3);
    private final Matrix mShaderMatrix = new Matrix();
    private int mTargetDensity;

    RoundedBitmapDrawable(Resources resources, Bitmap bitmap)
    {
        mTargetDensity = 160;
        mGravity = 119;
        mApplyGravity = true;
        if (resources != null)
        {
            mTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        mBitmap = bitmap;
        if (mBitmap != null)
        {
            mBitmapWidth = mBitmap.getScaledWidth(mTargetDensity);
            mBitmapHeight = mBitmap.getScaledHeight(mTargetDensity);
            mBitmapShader = new BitmapShader(mBitmap, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
            return;
        } else
        {
            mBitmapHeight = -1;
            mBitmapWidth = -1;
            mBitmapShader = null;
            return;
        }
    }

    public void draw(Canvas canvas)
    {
        Bitmap bitmap = mBitmap;
        if (bitmap == null)
        {
            return;
        }
        updateDstRect();
        if (mPaint.getShader() == null)
        {
            canvas.drawBitmap(bitmap, null, mDstRect, mPaint);
            return;
        } else
        {
            canvas.drawRoundRect(mDstRectF, mCornerRadius, mCornerRadius, mPaint);
            return;
        }
    }

    public int getAlpha()
    {
        return mPaint.getAlpha();
    }

    public ColorFilter getColorFilter()
    {
        return mPaint.getColorFilter();
    }

    public int getIntrinsicHeight()
    {
        return mBitmapHeight;
    }

    public int getIntrinsicWidth()
    {
        return mBitmapWidth;
    }

    public int getOpacity()
    {
        Bitmap bitmap;
        if (mGravity == 119 && !mIsCircular)
        {
            if ((bitmap = mBitmap) != null && !bitmap.hasAlpha() && mPaint.getAlpha() >= 255)
            {
                boolean flag;
                if (mCornerRadius > 0.05F)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    return -1;
                }
            }
        }
        return -3;
    }

    void gravityCompatApply(int i, int j, int k, Rect rect, Rect rect1)
    {
        throw new UnsupportedOperationException();
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        if (mIsCircular)
        {
            mCornerRadius = Math.min(mBitmapHeight, mBitmapWidth) / 2;
        }
        mApplyGravity = true;
    }

    public void setAlpha(int i)
    {
        if (i != mPaint.getAlpha())
        {
            mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public void setDither(boolean flag)
    {
        mPaint.setDither(flag);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean flag)
    {
        mPaint.setFilterBitmap(flag);
        invalidateSelf();
    }

    final void updateDstRect()
    {
        if (mApplyGravity)
        {
            if (mIsCircular)
            {
                int i = Math.min(mBitmapWidth, mBitmapHeight);
                gravityCompatApply(mGravity, i, i, getBounds(), mDstRect);
                i = Math.min(mDstRect.width(), mDstRect.height());
                int j = Math.max(0, (mDstRect.width() - i) / 2);
                int k = Math.max(0, (mDstRect.height() - i) / 2);
                mDstRect.inset(j, k);
                mCornerRadius = (float)i * 0.5F;
            } else
            {
                gravityCompatApply(mGravity, mBitmapWidth, mBitmapHeight, getBounds(), mDstRect);
            }
            mDstRectF.set(mDstRect);
            if (mBitmapShader != null)
            {
                mShaderMatrix.setTranslate(mDstRectF.left, mDstRectF.top);
                mShaderMatrix.preScale(mDstRectF.width() / (float)mBitmap.getWidth(), mDstRectF.height() / (float)mBitmap.getHeight());
                mBitmapShader.setLocalMatrix(mShaderMatrix);
                mPaint.setShader(mBitmapShader);
            }
            mApplyGravity = false;
        }
    }
}
