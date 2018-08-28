// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public abstract class AutoScrollHelper
    implements android.view.View.OnTouchListener
{

    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    public boolean mAnimating;
    private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    private int mEdgeType;
    private boolean mEnabled;
    private float mMaximumEdges[] = {
        3.402823E+38F, 3.402823E+38F
    };
    private float mMaximumVelocity[] = {
        3.402823E+38F, 3.402823E+38F
    };
    private float mMinimumVelocity[] = {
        0.0F, 0.0F
    };
    public boolean mNeedsCancel;
    public boolean mNeedsReset;
    private float mRelativeEdges[] = {
        0.0F, 0.0F
    };
    private float mRelativeVelocity[] = {
        0.0F, 0.0F
    };
    private Runnable mRunnable;
    public final ClampedScroller mScroller = new ClampedScroller();
    public final View mTarget;

    public AutoScrollHelper(View view)
    {
        mTarget = view;
        view = Resources.getSystem().getDisplayMetrics();
        int i = (int)(1575F * ((DisplayMetrics) (view)).density + 0.5F);
        int j = (int)(((DisplayMetrics) (view)).density * 315F + 0.5F);
        float f = i;
        float f1 = i;
        mMaximumVelocity[0] = f / 1000F;
        mMaximumVelocity[1] = f1 / 1000F;
        f = j;
        f1 = j;
        mMinimumVelocity[0] = f / 1000F;
        mMinimumVelocity[1] = f1 / 1000F;
        mEdgeType = 1;
        mMaximumEdges[0] = 3.402823E+38F;
        mMaximumEdges[1] = 3.402823E+38F;
        mRelativeEdges[0] = 0.2F;
        mRelativeEdges[1] = 0.2F;
        mRelativeVelocity[0] = 0.001F;
        mRelativeVelocity[1] = 0.001F;
        mActivationDelay = DEFAULT_ACTIVATION_DELAY;
        mScroller.mRampUpDuration = 500;
        mScroller.mRampDownDuration = 500;
    }

    private final float computeTargetVelocity(int i, float f, float f1, float f2)
    {
        float f5 = 0.0F;
        float f7 = mRelativeEdges[i];
        float f3 = mMaximumEdges[i];
        f7 *= f1;
        if (f7 <= f3)
        {
            if (f7 < 0.0F)
            {
                f3 = 0.0F;
            } else
            {
                f3 = f7;
            }
        }
        f7 = constrainEdgeValue(f, f3);
        f = constrainEdgeValue(f1 - f, f3) - f7;
        if (f >= 0.0F) goto _L2; else goto _L1
_L1:
        f1 = -mEdgeInterpolator.getInterpolation(-f);
_L9:
        if (f1 > 1.0F)
        {
            f = 1.0F;
        } else
        {
            f = f1;
            if (f1 < -1F)
            {
                f = -1F;
            }
        }
_L3:
        float f4;
        if (f == 0.0F)
        {
            f = f5;
        } else
        {
label0:
            {
                float f6 = mRelativeVelocity[i];
                f1 = mMinimumVelocity[i];
                f4 = mMaximumVelocity[i];
                f2 = f6 * f2;
                if (f <= 0.0F)
                {
                    break label0;
                }
                f2 = f * f2;
                if (f2 > f4)
                {
                    return f4;
                }
                f = f2;
                if (f2 < f1)
                {
                    return f1;
                }
            }
        }
        return f;
_L2:
        if (f > 0.0F)
        {
            f1 = mEdgeInterpolator.getInterpolation(f);
            continue; /* Loop/switch isn't completed */
        }
        f = 0.0F;
          goto _L3
        f = -f * f2;
        if (f <= f4) goto _L5; else goto _L4
_L4:
        f = f4;
_L7:
        return -f;
_L5:
        if (f < f1)
        {
            f = f1;
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L9; else goto _L8
_L8:
    }

    static float constrain(float f, float f1, float f2)
    {
        if (f > f2)
        {
            return f2;
        }
        if (f < f1)
        {
            return f1;
        } else
        {
            return f;
        }
    }

    private final float constrainEdgeValue(float f, float f1)
    {
        if (f1 != 0.0F) goto _L2; else goto _L1
_L1:
        return 0.0F;
_L2:
        mEdgeType;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 42
    //                   1 42
    //                   2 77;
           goto _L3 _L4 _L4 _L5
_L5:
        continue; /* Loop/switch isn't completed */
_L3:
        return 0.0F;
_L4:
        if (f >= f1) goto _L1; else goto _L6
_L6:
        if (f >= 0.0F)
        {
            return 1.0F - f / f1;
        }
        if (!mAnimating || mEdgeType != 1) goto _L1; else goto _L7
_L7:
        return 1.0F;
        if (f >= 0.0F) goto _L1; else goto _L8
_L8:
        return f / -f1;
    }

    public abstract boolean canTargetScrollHorizontally$514IIMG_0();

    public abstract boolean canTargetScrollVertically(int i);

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (mEnabled) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        int j;
        long l;
        switch (motionevent.getActionMasked())
        {
        default:
            return false;

        case 0: // '\0'
            mNeedsCancel = true;
            mAlreadyDelayed = false;
            // fall through

        case 2: // '\002'
            float f = computeTargetVelocity(0, motionevent.getX(), view.getWidth(), mTarget.getWidth());
            float f1 = computeTargetVelocity(1, motionevent.getY(), view.getHeight(), mTarget.getHeight());
            view = mScroller;
            view.mTargetVelocityX = f;
            view.mTargetVelocityY = f1;
            if (!mAnimating && shouldAnimate())
            {
                if (mRunnable == null)
                {
                    mRunnable = new ScrollAnimationRunnable();
                }
                mAnimating = true;
                mNeedsReset = true;
                if (!mAlreadyDelayed && mActivationDelay > 0)
                {
                    ViewCompat.postOnAnimationDelayed(mTarget, mRunnable, mActivationDelay);
                } else
                {
                    mRunnable.run();
                }
                mAlreadyDelayed = true;
                return false;
            }
            break;

        case 1: // '\001'
        case 3: // '\003'
            if (mNeedsReset)
            {
                mAnimating = false;
                return false;
            }
            view = mScroller;
            l = AnimationUtils.currentAnimationTimeMillis();
            i = (int)(l - ((ClampedScroller) (view)).mStartTime);
            j = ((ClampedScroller) (view)).mRampDownDuration;
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (i <= j) goto _L5; else goto _L4
_L4:
        i = j;
_L7:
        view.mEffectiveRampDown = i;
        view.mStopValue = view.getValueAt(l);
        view.mStopTime = l;
        return false;
_L5:
        if (i < 0)
        {
            i = 0;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public abstract void scrollTargetBy$514KIAAM0(int i);

    public final AutoScrollHelper setEnabled(boolean flag)
    {
        if (!mEnabled || flag) goto _L2; else goto _L1
_L1:
        if (!mNeedsReset) goto _L4; else goto _L3
_L3:
        mAnimating = false;
_L2:
        mEnabled = flag;
        return this;
_L4:
        ClampedScroller clampedscroller;
        int i;
        long l;
        clampedscroller = mScroller;
        l = AnimationUtils.currentAnimationTimeMillis();
        i = (int)(l - clampedscroller.mStartTime);
        int j = clampedscroller.mRampDownDuration;
        if (i <= j)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j;
_L7:
        clampedscroller.mEffectiveRampDown = i;
        clampedscroller.mStopValue = clampedscroller.getValueAt(l);
        clampedscroller.mStopTime = l;
        if (true) goto _L2; else goto _L5
_L5:
        if (i >= 0) goto _L7; else goto _L6
_L6:
        i = 0;
          goto _L7
    }

    final boolean shouldAnimate()
    {
        ClampedScroller clampedscroller = mScroller;
        int i = (int)(clampedscroller.mTargetVelocityY / Math.abs(clampedscroller.mTargetVelocityY));
        int j = (int)(clampedscroller.mTargetVelocityX / Math.abs(clampedscroller.mTargetVelocityX));
        if (i == 0 || !canTargetScrollVertically(i))
        {
            if (j != 0)
            {
                canTargetScrollHorizontally$514IIMG_0();
            }
            return false;
        } else
        {
            return true;
        }
    }


    private class ClampedScroller
    {

        public long mDeltaTime;
        public int mDeltaX;
        public int mDeltaY;
        public int mEffectiveRampDown;
        public int mRampDownDuration;
        public int mRampUpDuration;
        public long mStartTime;
        public long mStopTime;
        public float mStopValue;
        public float mTargetVelocityX;
        public float mTargetVelocityY;

        final float getValueAt(long l)
        {
            if (l < mStartTime)
            {
                return 0.0F;
            }
            if (mStopTime < 0L || l < mStopTime)
            {
                return AutoScrollHelper.constrain((float)(l - mStartTime) / (float)mRampUpDuration, 0.0F, 1.0F) * 0.5F;
            } else
            {
                long l1 = mStopTime;
                float f = mStopValue;
                float f1 = mStopValue;
                return AutoScrollHelper.constrain((float)(l - l1) / (float)mEffectiveRampDown, 0.0F, 1.0F) * f1 + (1.0F - f);
            }
        }

        ClampedScroller()
        {
            mStartTime = 0x8000000000000000L;
            mStopTime = -1L;
            mDeltaTime = 0L;
            mDeltaX = 0;
            mDeltaY = 0;
        }
    }


    private class ScrollAnimationRunnable
        implements Runnable
    {

        private final AutoScrollHelper this$0;

        public final void run()
        {
            if (!mAnimating)
            {
                return;
            }
            if (mNeedsReset)
            {
                mNeedsReset = false;
                ClampedScroller clampedscroller = mScroller;
                clampedscroller.mStartTime = AnimationUtils.currentAnimationTimeMillis();
                clampedscroller.mStopTime = -1L;
                clampedscroller.mDeltaTime = clampedscroller.mStartTime;
                clampedscroller.mStopValue = 0.5F;
                clampedscroller.mDeltaX = 0;
                clampedscroller.mDeltaY = 0;
            }
            ClampedScroller clampedscroller1 = mScroller;
            boolean flag;
            if (clampedscroller1.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > clampedscroller1.mStopTime + (long)clampedscroller1.mEffectiveRampDown)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || !shouldAnimate())
            {
                mAnimating = false;
                return;
            }
            if (mNeedsCancel)
            {
                mNeedsCancel = false;
                AutoScrollHelper autoscrollhelper = AutoScrollHelper.this;
                long l = SystemClock.uptimeMillis();
                MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                autoscrollhelper.mTarget.onTouchEvent(motionevent);
                motionevent.recycle();
            }
            if (clampedscroller1.mDeltaTime == 0L)
            {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            } else
            {
                long l1 = AnimationUtils.currentAnimationTimeMillis();
                float f = clampedscroller1.getValueAt(l1);
                f = f * 4F + -4F * f * f;
                long l2 = l1 - clampedscroller1.mDeltaTime;
                clampedscroller1.mDeltaTime = l1;
                clampedscroller1.mDeltaX = (int)((float)l2 * f * clampedscroller1.mTargetVelocityX);
                clampedscroller1.mDeltaY = (int)((float)l2 * f * clampedscroller1.mTargetVelocityY);
                int i = clampedscroller1.mDeltaY;
                scrollTargetBy$514KIAAM0(i);
                ViewCompat.postOnAnimation(mTarget, this);
                return;
            }
        }

        ScrollAnimationRunnable()
        {
            this$0 = AutoScrollHelper.this;
            super();
        }
    }

}
