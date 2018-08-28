// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

public class DrawerArrowDrawable extends Drawable
{

    private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45D);
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    public boolean mVerticalMirror;

    public DrawerArrowDrawable(Context context)
    {
        mVerticalMirror = false;
        mDirection = 2;
        mPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPaint.setStrokeJoin(android.graphics.Paint.Join.MITER);
        mPaint.setStrokeCap(android.graphics.Paint.Cap.BUTT);
        mPaint.setAntiAlias(true);
        context = context.getTheme().obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.DrawerArrowToggle, 0x7f01000a, 0x7f1400a7);
        int i = context.getColor(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_color, 0);
        if (i != mPaint.getColor())
        {
            mPaint.setColor(i);
            invalidateSelf();
        }
        float f = context.getDimension(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_thickness, 0.0F);
        if (mPaint.getStrokeWidth() != f)
        {
            mPaint.setStrokeWidth(f);
            mMaxCutForBarSize = (float)((double)(f / 2.0F) * Math.cos(ARROW_HEAD_ANGLE));
            invalidateSelf();
        }
        boolean flag = context.getBoolean(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_spinBars, true);
        if (mSpin != flag)
        {
            mSpin = flag;
            invalidateSelf();
        }
        f = Math.round(context.getDimension(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F));
        if (f != mBarGap)
        {
            mBarGap = f;
            invalidateSelf();
        }
        mSize = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_drawableSize, 0);
        mBarLength = Math.round(context.getDimension(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_barLength, 0.0F));
        mArrowHeadLength = Math.round(context.getDimension(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
        mArrowShaftLength = context.getDimension(android.support.v7.appcompat.R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
        context.recycle();
    }

    public void draw(Canvas canvas)
    {
label0:
        {
label1:
            {
label2:
                {
                    float f;
                    float f1;
                    int i;
                    {
                        Rect rect = getBounds();
                        float f2;
                        float f3;
                        float f4;
                        float f5;
                        float f6;
                        float f7;
                        float f8;
                        switch (mDirection)
                        {
                        case 2: // '\002'
                        default:
                            if (DrawableCompat.getLayoutDirection(this) == 1)
                            {
                                i = 1;
                            } else
                            {
                                i = 0;
                            }
                            break;

                        case 0: // '\0'
                            break label2;

                        case 1: // '\001'
                            break label1;

                        case 3: // '\003'
                            break label0;
                        }
                    }
                    f = (float)Math.sqrt(mArrowHeadLength * mArrowHeadLength * 2.0F);
                    f1 = mBarLength;
                    f5 = f1 + (f - f1) * mProgress;
                    f = mBarLength;
                    f2 = f + (mArrowShaftLength - f) * mProgress;
                    f3 = Math.round(0.0F + (mMaxCutForBarSize - 0.0F) * mProgress);
                    f6 = 0.0F + (ARROW_HEAD_ANGLE - 0.0F) * mProgress;
                    if (i != 0)
                    {
                        f = 0.0F;
                    } else
                    {
                        f = -180F;
                    }
                    if (i != 0)
                    {
                        f1 = 180F;
                    } else
                    {
                        f1 = 0.0F;
                    }
                    f4 = mProgress;
                    f7 = Math.round((double)f5 * Math.cos(f6));
                    f5 = Math.round((double)f5 * Math.sin(f6));
                    mPath.rewind();
                    f6 = mBarGap + mPaint.getStrokeWidth();
                    f6 += (-mMaxCutForBarSize - f6) * mProgress;
                    f8 = -f2 / 2.0F;
                    mPath.moveTo(f8 + f3, 0.0F);
                    mPath.rLineTo(f2 - f3 * 2.0F, 0.0F);
                    mPath.moveTo(f8, f6);
                    mPath.rLineTo(f7, f5);
                    mPath.moveTo(f8, -f6);
                    mPath.rLineTo(f7, -f5);
                    mPath.close();
                    canvas.save();
                    f2 = mPaint.getStrokeWidth();
                    f3 = (int)((float)rect.height() - 3F * f2 - mBarGap * 2.0F) / 4 << 1;
                    f5 = mBarGap;
                    canvas.translate(rect.centerX(), f2 * 1.5F + f5 + f3);
                    if (mSpin)
                    {
                        if ((i ^ mVerticalMirror) != 0)
                        {
                            i = -1;
                        } else
                        {
                            i = 1;
                        }
                        canvas.rotate((float)i * ((f1 - f) * f4 + f));
                    } else
                    if (i != 0)
                    {
                        canvas.rotate(180F);
                    }
                    canvas.drawPath(mPath, mPaint);
                    canvas.restore();
                    return;
                }
                i = 0;
                break MISSING_BLOCK_LABEL_51;
            }
            i = 1;
            break MISSING_BLOCK_LABEL_51;
        }
        if (DrawableCompat.getLayoutDirection(this) == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        break MISSING_BLOCK_LABEL_51;
    }

    public int getIntrinsicHeight()
    {
        return mSize;
    }

    public int getIntrinsicWidth()
    {
        return mSize;
    }

    public int getOpacity()
    {
        return -3;
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

    public void setProgress(float f)
    {
        if (mProgress != f)
        {
            mProgress = f;
            invalidateSelf();
        }
    }

}
