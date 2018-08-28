// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public final class CircularProgressDrawable extends Drawable
    implements Animatable
{

    private static final int COLORS[] = {
        0xff000000
    };
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private Animator mAnimator;
    public boolean mFinishing;
    private Resources mResources;
    public final Ring mRing;
    public float mRotation;
    public float mRotationCount;

    public CircularProgressDrawable(final Context ring)
    {
        if (ring == null)
        {
            throw new NullPointerException();
        } else
        {
            mResources = ((Context)ring).getResources();
            mRing = new Ring();
            ring = mRing;
            ring.mColors = COLORS;
            ring.mColorIndex = 0;
            ring.mCurrentColor = ((Ring) (ring)).mColors[((Ring) (ring)).mColorIndex];
            ring = mRing;
            ring.mStrokeWidth = 2.5F;
            ((Ring) (ring)).mPaint.setStrokeWidth(2.5F);
            invalidateSelf();
            ring = mRing;
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            valueanimator.addUpdateListener(new _cls1());
            valueanimator.setRepeatCount(-1);
            valueanimator.setRepeatMode(1);
            valueanimator.setInterpolator(LINEAR_INTERPOLATOR);
            valueanimator.addListener(new _cls2());
            mAnimator = valueanimator;
            return;
        }
    }

    private final void setSizeParameters(float f, float f1, float f2, float f3)
    {
        Ring ring = mRing;
        float f4 = mResources.getDisplayMetrics().density;
        f1 *= f4;
        ring.mStrokeWidth = f1;
        ring.mPaint.setStrokeWidth(f1);
        ring.mRingCenterRadius = f * f4;
        ring.mColorIndex = 0;
        ring.mCurrentColor = ring.mColors[ring.mColorIndex];
        ring.mArrowWidth = (int)(f2 * f4);
        ring.mArrowHeight = (int)(f4 * f3);
    }

    public final void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        canvas.save();
        canvas.rotate(mRotation, rect.exactCenterX(), rect.exactCenterY());
        Ring ring = mRing;
        RectF rectf = ring.mTempBounds;
        float f = ring.mRingCenterRadius + ring.mStrokeWidth / 2.0F;
        if (ring.mRingCenterRadius <= 0.0F)
        {
            f = (float)Math.min(rect.width(), rect.height()) / 2.0F - Math.max(((float)ring.mArrowWidth * ring.mArrowScale) / 2.0F, ring.mStrokeWidth / 2.0F);
        }
        rectf.set((float)rect.centerX() - f, (float)rect.centerY() - f, (float)rect.centerX() + f, f + (float)rect.centerY());
        f = (ring.mStartTrim + ring.mRotation) * 360F;
        float f1 = (ring.mEndTrim + ring.mRotation) * 360F - f;
        ring.mPaint.setColor(ring.mCurrentColor);
        ring.mPaint.setAlpha(ring.mAlpha);
        float f2 = ring.mStrokeWidth / 2.0F;
        rectf.inset(f2, f2);
        canvas.drawCircle(rectf.centerX(), rectf.centerY(), rectf.width() / 2.0F, ring.mCirclePaint);
        rectf.inset(-f2, -f2);
        canvas.drawArc(rectf, f, f1, false, ring.mPaint);
        if (ring.mShowArrow)
        {
            float f3;
            float f4;
            if (ring.mArrow == null)
            {
                ring.mArrow = new Path();
                ring.mArrow.setFillType(android.graphics.Path.FillType.EVEN_ODD);
            } else
            {
                ring.mArrow.reset();
            }
            f3 = Math.min(rectf.width(), rectf.height()) / 2.0F;
            f4 = ((float)ring.mArrowWidth * ring.mArrowScale) / 2.0F;
            ring.mArrow.moveTo(0.0F, 0.0F);
            ring.mArrow.lineTo((float)ring.mArrowWidth * ring.mArrowScale, 0.0F);
            ring.mArrow.lineTo(((float)ring.mArrowWidth * ring.mArrowScale) / 2.0F, (float)ring.mArrowHeight * ring.mArrowScale);
            ring.mArrow.offset((f3 + rectf.centerX()) - f4, rectf.centerY() + ring.mStrokeWidth / 2.0F);
            ring.mArrow.close();
            ring.mArrowPaint.setColor(ring.mCurrentColor);
            ring.mArrowPaint.setAlpha(ring.mAlpha);
            canvas.save();
            canvas.rotate(f + f1, rectf.centerX(), rectf.centerY());
            canvas.drawPath(ring.mArrow, ring.mArrowPaint);
            canvas.restore();
        }
        canvas.restore();
    }

    public final int getAlpha()
    {
        return mRing.mAlpha;
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final boolean isRunning()
    {
        return mAnimator.isRunning();
    }

    public final void setAlpha(int i)
    {
        mRing.mAlpha = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        mRing.mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public final void setStartEndTrim(float f, float f1)
    {
        mRing.mStartTrim = 0.0F;
        mRing.mEndTrim = f1;
        invalidateSelf();
    }

    public final void setStyle(int i)
    {
        if (i == 0)
        {
            setSizeParameters(11F, 3F, 12F, 6F);
        } else
        {
            setSizeParameters(7.5F, 2.5F, 10F, 5F);
        }
        invalidateSelf();
    }

    public final void start()
    {
        mAnimator.cancel();
        Ring ring = mRing;
        ring.mStartingStartTrim = ring.mStartTrim;
        ring.mStartingEndTrim = ring.mEndTrim;
        ring.mStartingRotation = ring.mRotation;
        if (mRing.mEndTrim != mRing.mStartTrim)
        {
            mFinishing = true;
            mAnimator.setDuration(666L);
            mAnimator.start();
            return;
        } else
        {
            Ring ring1 = mRing;
            ring1.mColorIndex = 0;
            ring1.mCurrentColor = ring1.mColors[ring1.mColorIndex];
            ring1 = mRing;
            ring1.mStartingStartTrim = 0.0F;
            ring1.mStartingEndTrim = 0.0F;
            ring1.mStartingRotation = 0.0F;
            ring1.mStartTrim = 0.0F;
            ring1.mEndTrim = 0.0F;
            ring1.mRotation = 0.0F;
            mAnimator.setDuration(1332L);
            mAnimator.start();
            return;
        }
    }

    public final void stop()
    {
        mAnimator.cancel();
        mRotation = 0.0F;
        Ring ring = mRing;
        if (ring.mShowArrow)
        {
            ring.mShowArrow = false;
        }
        ring = mRing;
        ring.mColorIndex = 0;
        ring.mCurrentColor = ring.mColors[ring.mColorIndex];
        ring = mRing;
        ring.mStartingStartTrim = 0.0F;
        ring.mStartingEndTrim = 0.0F;
        ring.mStartingRotation = 0.0F;
        ring.mStartTrim = 0.0F;
        ring.mEndTrim = 0.0F;
        ring.mRotation = 0.0F;
        invalidateSelf();
    }

    final void updateRingColor(float f, Ring ring)
    {
        if (f > 0.75F)
        {
            f = (f - 0.75F) / 0.25F;
            int i1 = ring.mColors[ring.mColorIndex];
            int i = ring.mColors[(ring.mColorIndex + 1) % ring.mColors.length];
            int j = i1 >>> 24;
            int k = i1 >> 16 & 0xff;
            int l = i1 >> 8 & 0xff;
            i1 &= 0xff;
            int j1 = (int)((float)((i >>> 24) - j) * f);
            int k1 = (int)((float)((i >> 16 & 0xff) - k) * f);
            int l1 = (int)((float)((i >> 8 & 0xff) - l) * f);
            ring.mCurrentColor = (int)(f * (float)((i & 0xff) - i1)) + i1 | (j + j1 << 24 | k + k1 << 16 | l1 + l << 8);
            return;
        } else
        {
            ring.mCurrentColor = ring.mColors[ring.mColorIndex];
            return;
        }
    }


    private class Ring
    {

        public int mAlpha;
        public Path mArrow;
        public int mArrowHeight;
        public final Paint mArrowPaint = new Paint();
        public float mArrowScale;
        public int mArrowWidth;
        public final Paint mCirclePaint = new Paint();
        public int mColorIndex;
        public int mColors[];
        public int mCurrentColor;
        public float mEndTrim;
        public final Paint mPaint = new Paint();
        public float mRingCenterRadius;
        public float mRotation;
        public boolean mShowArrow;
        public float mStartTrim;
        public float mStartingEndTrim;
        public float mStartingRotation;
        public float mStartingStartTrim;
        public float mStrokeWidth;
        public final RectF mTempBounds = new RectF();

        Ring()
        {
            mStartTrim = 0.0F;
            mEndTrim = 0.0F;
            mRotation = 0.0F;
            mStrokeWidth = 5F;
            mArrowScale = 1.0F;
            mAlpha = 255;
            mPaint.setStrokeCap(android.graphics.Paint.Cap.SQUARE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mArrowPaint.setStyle(android.graphics.Paint.Style.FILL);
            mArrowPaint.setAntiAlias(true);
            mCirclePaint.setColor(0);
        }
    }


    private class _cls1
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final CircularProgressDrawable this$0;
        private final Ring val$ring;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            float f4;
            Ring ring1;
            f4 = ((Float)valueanimator.getAnimatedValue()).floatValue();
            updateRingColor(f4, ring);
            valueanimator = CircularProgressDrawable.this;
            ring1 = ring;
            if (!((CircularProgressDrawable) (valueanimator)).mFinishing) goto _L2; else goto _L1
_L1:
            valueanimator.updateRingColor(f4, ring1);
            float f = (float)(Math.floor(ring1.mStartingRotation / 0.8F) + 1.0D);
            ring1.mStartTrim = ring1.mStartingStartTrim + (ring1.mStartingEndTrim - 0.01F - ring1.mStartingStartTrim) * f4;
            ring1.mEndTrim = ring1.mStartingEndTrim;
            float f2 = ring1.mStartingRotation;
            ring1.mRotation = (f - ring1.mStartingRotation) * f4 + f2;
_L4:
            invalidateSelf();
            return;
_L2:
            if (f4 != 1.0F)
            {
                float f5 = ring1.mStartingRotation;
                float f1;
                float f3;
                float f6;
                if (f4 < 0.5F)
                {
                    f1 = f4 / 0.5F;
                    f3 = ring1.mStartingStartTrim;
                    f1 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F + f3;
                } else
                {
                    f3 = (f4 - 0.5F) / 0.5F;
                    f1 = ring1.mStartingStartTrim + 0.79F;
                    f3 = f1 - ((1.0F - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f3)) * 0.79F + 0.01F);
                }
                f6 = ((CircularProgressDrawable) (valueanimator)).mRotationCount;
                ring1.mStartTrim = f3;
                ring1.mEndTrim = f1;
                ring1.mRotation = f5 + 0.21F * f4;
                valueanimator.mRotation = (f4 + f6) * 216F;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        _cls1()
        {
            this$0 = CircularProgressDrawable.this;
            ring = ring1;
            super();
        }
    }


    private class _cls2
        implements android.animation.Animator.AnimatorListener
    {

        private final CircularProgressDrawable this$0;
        private final Ring val$ring;

        public final void onAnimationCancel(Animator animator)
        {
        }

        public final void onAnimationEnd(Animator animator)
        {
        }

        public final void onAnimationRepeat(Animator animator)
        {
            Object obj = CircularProgressDrawable.this;
            Ring ring1 = ring;
            if (((CircularProgressDrawable) (obj)).mFinishing)
            {
                ((CircularProgressDrawable) (obj)).updateRingColor(1.0F, ring1);
                float f = (float)(Math.floor(ring1.mStartingRotation / 0.8F) + 1.0D);
                ring1.mStartTrim = ring1.mStartingStartTrim + (ring1.mStartingEndTrim - 0.01F - ring1.mStartingStartTrim) * 1.0F;
                ring1.mEndTrim = ring1.mStartingEndTrim;
                float f2 = ring1.mStartingRotation;
                ring1.mRotation = (f - ring1.mStartingRotation) * 1.0F + f2;
            } else
            {
                if (1.0F != 1.0F);
                float f4 = ring1.mStartingRotation;
                float f1;
                float f3;
                float f5;
                if (1.0F < 0.5F)
                {
                    f1 = 1.0F / 0.5F;
                    f3 = ring1.mStartingStartTrim;
                    f1 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F + f3;
                } else
                {
                    f3 = (1.0F - 0.5F) / 0.5F;
                    f1 = ring1.mStartingStartTrim + 0.79F;
                    f3 = f1 - ((1.0F - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f3)) * 0.79F + 0.01F);
                }
                f5 = ((CircularProgressDrawable) (obj)).mRotationCount;
                ring1.mStartTrim = f3;
                ring1.mEndTrim = f1;
                ring1.mRotation = f4 + 0.21F * 1.0F;
                obj.mRotation = 216F * (f5 + 1.0F);
            }
            obj = ring;
            obj.mStartingStartTrim = ((Ring) (obj)).mStartTrim;
            obj.mStartingEndTrim = ((Ring) (obj)).mEndTrim;
            obj.mStartingRotation = ((Ring) (obj)).mRotation;
            obj = ring;
            obj.mColorIndex = (((Ring) (obj)).mColorIndex + 1) % ((Ring) (obj)).mColors.length;
            obj.mCurrentColor = ((Ring) (obj)).mColors[((Ring) (obj)).mColorIndex];
            if (mFinishing)
            {
                mFinishing = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                animator = ring;
                if (((Ring) (animator)).mShowArrow)
                {
                    animator.mShowArrow = false;
                }
                return;
            } else
            {
                mRotationCount = mRotationCount + 1.0F;
                return;
            }
        }

        public final void onAnimationStart(Animator animator)
        {
            mRotationCount = 0.0F;
        }

        _cls2()
        {
            this$0 = CircularProgressDrawable.this;
            ring = ring1;
            super();
        }
    }

}
