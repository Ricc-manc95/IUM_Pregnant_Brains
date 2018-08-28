// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;

// Referenced classes of package android.support.v4.widget:
//            CircleImageView, CircularProgressDrawable

public class SwipeRefreshLayout extends ViewGroup
{
    public static interface OnRefreshListener
    {
    }


    private static final int LAYOUT_ATTRS[] = {
        0x101000e
    };
    private static final String LOG_TAG = android/support/v4/widget/SwipeRefreshLayout.getSimpleName();
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleDiameter;
    public CircleImageView mCircleView;
    private int mCircleViewIndex;
    public int mCurrentTargetOffsetTop;
    private int mCustomSlingshotDistance;
    private final DecelerateInterpolator mDecelerateInterpolator;
    public int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    public OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    public boolean mNotify;
    public int mOriginalOffsetTop;
    private final int mParentOffsetInWindow[];
    private final int mParentScrollConsumed[];
    public CircularProgressDrawable mProgress;
    private android.view.animation.Animation.AnimationListener mRefreshListener;
    public boolean mRefreshing;
    public boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    public int mSpinnerOffsetEnd;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    public boolean mUsingCustomStart;

    public SwipeRefreshLayout(Context context)
    {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mRefreshing = false;
        mTotalDragDistance = -1F;
        mParentScrollConsumed = new int[2];
        mParentOffsetInWindow = new int[2];
        mActivePointerId = -1;
        mCircleViewIndex = -1;
        mRefreshListener = new _cls1();
        mAnimateToCorrectPosition = new _cls6();
        mAnimateToStartPosition = new _cls7();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMediumAnimationDuration = getResources().getInteger(0x10e0001);
        setWillNotDraw(false);
        mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        mCircleDiameter = (int)(40F * displaymetrics.density);
        mCircleView = new CircleImageView(getContext(), 0xfffafafa);
        mProgress = new CircularProgressDrawable(getContext());
        mProgress.setStyle(1);
        mCircleView.setImageDrawable(mProgress);
        mCircleView.setVisibility(8);
        addView(mCircleView);
        setChildrenDrawingOrderEnabled(true);
        mSpinnerOffsetEnd = (int)(displaymetrics.density * 64F);
        mTotalDragDistance = mSpinnerOffsetEnd;
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i = -mCircleDiameter;
        mCurrentTargetOffsetTop = i;
        mOriginalOffsetTop = i;
        moveToStart(1.0F);
        context = context.obtainStyledAttributes(attributeset, LAYOUT_ATTRS);
        setEnabled(context.getBoolean(0, true));
        context.recycle();
    }

    private final boolean canChildScrollUp()
    {
        if (mTarget instanceof ListView)
        {
            return ((ListView)mTarget).canScrollList(-1);
        } else
        {
            return mTarget.canScrollVertically(-1);
        }
    }

    private final void ensureTarget()
    {
        if (mTarget != null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        if (i >= getChildCount()) goto _L2; else goto _L3
_L3:
        View view = getChildAt(i);
        if (view.equals(mCircleView)) goto _L5; else goto _L4
_L4:
        mTarget = view;
_L2:
        return;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private final void finishSpinner(float f)
    {
        if (f > mTotalDragDistance)
        {
            setRefreshing(true, true);
            return;
        }
        mRefreshing = false;
        mProgress.setStartEndTrim(0.0F, 0.0F);
        Object obj = new _cls5();
        mFrom = mCurrentTargetOffsetTop;
        mAnimateToStartPosition.reset();
        mAnimateToStartPosition.setDuration(200L);
        mAnimateToStartPosition.setInterpolator(mDecelerateInterpolator);
        if (obj != null)
        {
            mCircleView.mListener = ((android.view.animation.Animation.AnimationListener) (obj));
        }
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mAnimateToStartPosition);
        obj = mProgress;
        CircularProgressDrawable.Ring ring = ((CircularProgressDrawable) (obj)).mRing;
        if (ring.mShowArrow)
        {
            ring.mShowArrow = false;
        }
        ((CircularProgressDrawable) (obj)).invalidateSelf();
    }

    private final void moveSpinner(float f)
    {
        Object obj = mProgress;
        CircularProgressDrawable.Ring ring = ((CircularProgressDrawable) (obj)).mRing;
        if (!ring.mShowArrow)
        {
            ring.mShowArrow = true;
        }
        ((CircularProgressDrawable) (obj)).invalidateSelf();
        float f3 = Math.min(1.0F, Math.abs(f / mTotalDragDistance));
        float f2 = ((float)Math.max((double)f3 - 0.40000000000000002D, 0.0D) * 5F) / 3F;
        float f4 = Math.abs(f);
        float f5 = mTotalDragDistance;
        float f1;
        int j;
        int k;
        if (mCustomSlingshotDistance > 0)
        {
            f1 = mCustomSlingshotDistance;
        } else
        {
            f1 = mSpinnerOffsetEnd;
        }
        f4 = Math.max(0.0F, Math.min(f4 - f5, 2.0F * f1) / f1);
        f4 = (float)((double)(f4 / 4F) - Math.pow(f4 / 4F, 2D)) * 2.0F;
        j = mOriginalOffsetTop;
        k = (int)(f1 * f3 + f1 * f4 * 2.0F);
        if (mCircleView.getVisibility() != 0)
        {
            mCircleView.setVisibility(0);
        }
        mCircleView.setScaleX(1.0F);
        mCircleView.setScaleY(1.0F);
        if (f >= mTotalDragDistance) goto _L2; else goto _L1
_L1:
        if (mProgress.getAlpha() > 76)
        {
            obj = mAlphaStartAnimation;
            int i;
            if (obj != null && ((Animation) (obj)).hasStarted() && !((Animation) (obj)).hasEnded())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                mAlphaStartAnimation = startAlphaAnimation(mProgress.getAlpha(), 76);
            }
        }
_L4:
        mProgress.setStartEndTrim(0.0F, Math.min(0.8F, 0.8F * f2));
        obj = mProgress;
        f = Math.min(1.0F, f2);
        ring = ((CircularProgressDrawable) (obj)).mRing;
        if (f != ring.mArrowScale)
        {
            ring.mArrowScale = f;
        }
        ((CircularProgressDrawable) (obj)).invalidateSelf();
        obj = mProgress;
        ((CircularProgressDrawable) (obj)).mRing.mRotation = (-0.25F + f2 * 0.4F + 2.0F * f4) * 0.5F;
        ((CircularProgressDrawable) (obj)).invalidateSelf();
        i = mCurrentTargetOffsetTop;
        mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(mCircleView, (j + k) - i);
        mCurrentTargetOffsetTop = mCircleView.getTop();
        return;
_L2:
        if (mProgress.getAlpha() < 255)
        {
            Animation animation = mAlphaMaxAnimation;
            boolean flag;
            if (animation != null && animation.hasStarted() && !animation.hasEnded())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                mAlphaMaxAnimation = startAlphaAnimation(mProgress.getAlpha(), 255);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if (motionevent.getPointerId(i) == mActivePointerId)
        {
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            mActivePointerId = motionevent.getPointerId(i);
        }
    }

    private final void setRefreshing(boolean flag, boolean flag1)
    {
label0:
        {
            if (mRefreshing != flag)
            {
                mNotify = flag1;
                ensureTarget();
                mRefreshing = flag;
                if (!mRefreshing)
                {
                    break label0;
                }
                int i = mCurrentTargetOffsetTop;
                android.view.animation.Animation.AnimationListener animationlistener = mRefreshListener;
                mFrom = i;
                mAnimateToCorrectPosition.reset();
                mAnimateToCorrectPosition.setDuration(200L);
                mAnimateToCorrectPosition.setInterpolator(mDecelerateInterpolator);
                if (animationlistener != null)
                {
                    mCircleView.mListener = animationlistener;
                }
                mCircleView.clearAnimation();
                mCircleView.startAnimation(mAnimateToCorrectPosition);
            }
            return;
        }
        startScaleDownAnimation(mRefreshListener);
    }

    private final Animation startAlphaAnimation(final int startingAlpha, final int endingAlpha)
    {
        _cls4 _lcls4 = new _cls4();
        _lcls4.setDuration(300L);
        mCircleView.mListener = null;
        mCircleView.clearAnimation();
        mCircleView.startAnimation(_lcls4);
        return _lcls4;
    }

    private final void startDragging(float f)
    {
        if (f - mInitialDownY > (float)mTouchSlop && !mIsBeingDragged)
        {
            mInitialMotionY = mInitialDownY + (float)mTouchSlop;
            mIsBeingDragged = true;
            mProgress.setAlpha(76);
        }
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        return mNestedScrollingChildHelper.dispatchNestedFling(f, f1, flag);
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(f, f1);
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(i, j, ai, ai1, 0);
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        return mNestedScrollingChildHelper.dispatchNestedScroll(i, j, k, l, ai, 0);
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (mCircleViewIndex >= 0)
        {
            if (j == i - 1)
            {
                return mCircleViewIndex;
            }
            if (j >= mCircleViewIndex)
            {
                return j + 1;
            }
        }
        return j;
    }

    public int getNestedScrollAxes()
    {
        return mNestedScrollingParentHelper.mNestedScrollAxes;
    }

    public boolean hasNestedScrollingParent()
    {
        boolean flag = false;
        if (mNestedScrollingChildHelper.getNestedScrollingParentForType(0) != null)
        {
            flag = true;
        }
        return flag;
    }

    public boolean isNestedScrollingEnabled()
    {
        return mNestedScrollingChildHelper.mIsNestedScrollingEnabled;
    }

    final void moveToStart(float f)
    {
        int i = mFrom;
        int j = (int)((float)(mOriginalOffsetTop - mFrom) * f);
        int k = mCircleView.getTop();
        mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(mCircleView, (i + j) - k);
        mCurrentTargetOffsetTop = mCircleView.getTop();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        reset();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        ensureTarget();
        i = motionevent.getActionMasked();
        if (isEnabled() && !canChildScrollUp() && !mRefreshing && !mNestedScrollInProgress) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        i;
        JVM INSTR tableswitch 0 6: default 84
    //                   0 89
    //                   1 222
    //                   2 169
    //                   3 222
    //                   4 84
    //                   5 84
    //                   6 214;
           goto _L3 _L4 _L5 _L6 _L5 _L3 _L3 _L7
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_222;
_L9:
        return mIsBeingDragged;
_L4:
        i = mOriginalOffsetTop;
        int j = mCircleView.getTop();
        mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(mCircleView, i - j);
        mCurrentTargetOffsetTop = mCircleView.getTop();
        mActivePointerId = motionevent.getPointerId(0);
        mIsBeingDragged = false;
        i = motionevent.findPointerIndex(mActivePointerId);
        if (i < 0) goto _L1; else goto _L8
_L8:
        mInitialDownY = motionevent.getY(i);
          goto _L9
_L6:
        if (mActivePointerId == -1)
        {
            Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
            return false;
        }
        i = motionevent.findPointerIndex(mActivePointerId);
        if (i < 0) goto _L1; else goto _L10
_L10:
        startDragging(motionevent.getY(i));
          goto _L9
_L7:
        onSecondaryPointerUp(motionevent);
          goto _L9
        mIsBeingDragged = false;
        mActivePointerId = -1;
          goto _L9
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        i = getMeasuredWidth();
        j = getMeasuredHeight();
        if (getChildCount() != 0)
        {
            if (mTarget == null)
            {
                ensureTarget();
            }
            if (mTarget != null)
            {
                View view = mTarget;
                k = getPaddingLeft();
                l = getPaddingTop();
                view.layout(k, l, (i - getPaddingLeft() - getPaddingRight()) + k, (j - getPaddingTop() - getPaddingBottom()) + l);
                j = mCircleView.getMeasuredWidth();
                k = mCircleView.getMeasuredHeight();
                mCircleView.layout(i / 2 - j / 2, mCurrentTargetOffsetTop, i / 2 + j / 2, mCurrentTargetOffsetTop + k);
                return;
            }
        }
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (mTarget == null)
        {
            ensureTarget();
        }
        if (mTarget != null)
        {
            mTarget.measure(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 0x40000000));
            mCircleView.measure(android.view.View.MeasureSpec.makeMeasureSpec(mCircleDiameter, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(mCircleDiameter, 0x40000000));
            mCircleViewIndex = -1;
            i = 0;
            while (i < getChildCount()) 
            {
                if (getChildAt(i) == mCircleView)
                {
                    mCircleViewIndex = i;
                    return;
                }
                i++;
            }
        }
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        return dispatchNestedFling(f, f1, flag);
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        return dispatchNestedPreFling(f, f1);
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        if (j > 0 && mTotalUnconsumed > 0.0F)
        {
            if ((float)j > mTotalUnconsumed)
            {
                ai[1] = j - (int)mTotalUnconsumed;
                mTotalUnconsumed = 0.0F;
            } else
            {
                mTotalUnconsumed = mTotalUnconsumed - (float)j;
                ai[1] = j;
            }
            moveSpinner(mTotalUnconsumed);
        }
        view = mParentScrollConsumed;
        if (dispatchNestedPreScroll(i - ai[0], j - ai[1], view, null))
        {
            ai[0] = ai[0] + view[0];
            i = ai[1];
            ai[1] = view[1] + i;
        }
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        dispatchNestedScroll(i, j, k, l, mParentOffsetInWindow);
        i = mParentOffsetInWindow[1] + l;
        if (i < 0 && !canChildScrollUp())
        {
            float f = mTotalUnconsumed;
            mTotalUnconsumed = (float)Math.abs(i) + f;
            moveSpinner(mTotalUnconsumed);
        }
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        mNestedScrollingParentHelper.mNestedScrollAxes = i;
        startNestedScroll(i & 2);
        mTotalUnconsumed = 0.0F;
        mNestedScrollInProgress = true;
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        return isEnabled() && !mRefreshing && (i & 2) != 0;
    }

    public void onStopNestedScroll(View view)
    {
        mNestedScrollingParentHelper.mNestedScrollAxes = 0;
        mNestedScrollInProgress = false;
        if (mTotalUnconsumed > 0.0F)
        {
            finishSpinner(mTotalUnconsumed);
            mTotalUnconsumed = 0.0F;
        }
        stopNestedScroll();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (isEnabled() && !canChildScrollUp() && !mRefreshing && !mNestedScrollInProgress) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        i;
        JVM INSTR tableswitch 0 6: default 80
    //                   0 82
    //                   1 214
    //                   2 99
    //                   3 34
    //                   4 80
    //                   5 170
    //                   6 206;
           goto _L3 _L4 _L5 _L6 _L1 _L3 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_206;
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_214;
_L9:
        return true;
_L4:
        mActivePointerId = motionevent.getPointerId(0);
        mIsBeingDragged = false;
          goto _L9
_L6:
        float f;
        int j = motionevent.findPointerIndex(mActivePointerId);
        if (j < 0)
        {
            Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
            return false;
        }
        f = motionevent.getY(j);
        startDragging(f);
        if (!mIsBeingDragged) goto _L9; else goto _L10
_L10:
        f = (f - mInitialMotionY) * 0.5F;
        if (f <= 0.0F) goto _L1; else goto _L11
_L11:
        moveSpinner(f);
          goto _L9
_L7:
        int k = motionevent.getActionIndex();
        if (k < 0)
        {
            Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
            return false;
        }
        mActivePointerId = motionevent.getPointerId(k);
          goto _L9
        onSecondaryPointerUp(motionevent);
          goto _L9
        int l = motionevent.findPointerIndex(mActivePointerId);
        if (l < 0)
        {
            Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
            return false;
        }
        if (mIsBeingDragged)
        {
            float f1 = motionevent.getY(l);
            float f2 = mInitialMotionY;
            mIsBeingDragged = false;
            finishSpinner((f1 - f2) * 0.5F);
        }
        mActivePointerId = -1;
        return false;
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        if (mTarget == null || ViewCompat.isNestedScrollingEnabled(mTarget))
        {
            super.requestDisallowInterceptTouchEvent(flag);
        }
    }

    final void reset()
    {
        mCircleView.clearAnimation();
        mProgress.stop();
        mCircleView.setVisibility(8);
        mCircleView.getBackground().setAlpha(255);
        mProgress.setAlpha(255);
        int i = mOriginalOffsetTop;
        int j = mCurrentTargetOffsetTop;
        mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(mCircleView, i - j);
        mCurrentTargetOffsetTop = mCircleView.getTop();
        mCurrentTargetOffsetTop = mCircleView.getTop();
    }

    public final transient void setColorSchemeResources(int ai[])
    {
        Object obj = getContext();
        int ai1[] = new int[ai.length];
        for (int i = 0; i < ai.length; i++)
        {
            ai1[i] = ContextCompat.getColor(((Context) (obj)), ai[i]);
        }

        ensureTarget();
        ai = mProgress;
        obj = ((CircularProgressDrawable) (ai)).mRing;
        obj.mColors = ai1;
        obj.mColorIndex = 0;
        obj.mCurrentColor = ((CircularProgressDrawable.Ring) (obj)).mColors[((CircularProgressDrawable.Ring) (obj)).mColorIndex];
        CircularProgressDrawable.Ring ring = ((CircularProgressDrawable) (ai)).mRing;
        ring.mColorIndex = 0;
        ring.mCurrentColor = ring.mColors[ring.mColorIndex];
        ai.invalidateSelf();
    }

    public void setDistanceToTriggerSync(int i)
    {
        mTotalDragDistance = i;
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        if (!flag)
        {
            reset();
        }
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        NestedScrollingChildHelper nestedscrollingchildhelper = mNestedScrollingChildHelper;
        if (nestedscrollingchildhelper.mIsNestedScrollingEnabled)
        {
            ViewCompat.stopNestedScroll(nestedscrollingchildhelper.mView);
        }
        nestedscrollingchildhelper.mIsNestedScrollingEnabled = flag;
    }

    public void setProgressBackgroundColor(int i)
    {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i)
    {
        mCircleView.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i)
    {
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), i));
    }

    public final void setRefreshing(boolean flag)
    {
        if (flag && mRefreshing != flag)
        {
            mRefreshing = flag;
            int i = mSpinnerOffsetEnd;
            int j = mOriginalOffsetTop;
            int k = mCurrentTargetOffsetTop;
            mCircleView.bringToFront();
            ViewCompat.offsetTopAndBottom(mCircleView, (i + j) - k);
            mCurrentTargetOffsetTop = mCircleView.getTop();
            mNotify = false;
            android.view.animation.Animation.AnimationListener animationlistener = mRefreshListener;
            mCircleView.setVisibility(0);
            mProgress.setAlpha(255);
            mScaleAnimation = new _cls2();
            mScaleAnimation.setDuration(mMediumAnimationDuration);
            if (animationlistener != null)
            {
                mCircleView.mListener = animationlistener;
            }
            mCircleView.clearAnimation();
            mCircleView.startAnimation(mScaleAnimation);
            return;
        } else
        {
            setRefreshing(flag, false);
            return;
        }
    }

    public void setSize(int i)
    {
        if (i != 0 && i != 1)
        {
            return;
        }
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        if (i == 0)
        {
            mCircleDiameter = (int)(displaymetrics.density * 56F);
        } else
        {
            mCircleDiameter = (int)(displaymetrics.density * 40F);
        }
        mCircleView.setImageDrawable(null);
        mProgress.setStyle(i);
        mCircleView.setImageDrawable(mProgress);
    }

    public void setSlingshotDistance(int i)
    {
        mCustomSlingshotDistance = i;
    }

    public boolean startNestedScroll(int i)
    {
        return mNestedScrollingChildHelper.startNestedScroll(i, 0);
    }

    final void startScaleDownAnimation(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mScaleDownAnimation = new _cls3();
        mScaleDownAnimation.setDuration(150L);
        mCircleView.mListener = animationlistener;
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleDownAnimation);
    }

    public void stopNestedScroll()
    {
        NestedScrollingChildHelper nestedscrollingchildhelper = mNestedScrollingChildHelper;
        android.view.ViewParent viewparent = nestedscrollingchildhelper.getNestedScrollingParentForType(0);
        if (viewparent != null)
        {
            ViewParentCompat.onStopNestedScroll(viewparent, nestedscrollingchildhelper.mView, 0);
            nestedscrollingchildhelper.setNestedScrollingParentForType(0, null);
        }
    }


    private class _cls1
        implements android.view.animation.Animation.AnimationListener
    {

        private final SwipeRefreshLayout this$0;

        public final void onAnimationEnd(Animation animation)
        {
            if (mRefreshing)
            {
                mProgress.setAlpha(255);
                mProgress.start();
                if (mNotify)
                {
                    animation = mListener;
                }
                mCurrentTargetOffsetTop = mCircleView.getTop();
                return;
            } else
            {
                reset();
                return;
            }
        }

        public final void onAnimationRepeat(Animation animation)
        {
        }

        public final void onAnimationStart(Animation animation)
        {
        }

        _cls1()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }


    private class _cls6 extends Animation
    {

        private final SwipeRefreshLayout this$0;

        public final void applyTransformation(float f, Transformation transformation)
        {
            boolean flag = mUsingCustomStart;
            int j = mSpinnerOffsetEnd;
            int k = Math.abs(mOriginalOffsetTop);
            int i = mFrom;
            j = (int)((float)(j - k - mFrom) * f);
            k = mCircleView.getTop();
            transformation = SwipeRefreshLayout.this;
            ((SwipeRefreshLayout) (transformation)).mCircleView.bringToFront();
            ViewCompat.offsetTopAndBottom(((SwipeRefreshLayout) (transformation)).mCircleView, (j + i) - k);
            transformation.mCurrentTargetOffsetTop = ((SwipeRefreshLayout) (transformation)).mCircleView.getTop();
            transformation = mProgress;
            f = 1.0F - f;
            CircularProgressDrawable.Ring ring = ((CircularProgressDrawable) (transformation)).mRing;
            if (f != ring.mArrowScale)
            {
                ring.mArrowScale = f;
            }
            transformation.invalidateSelf();
        }

        _cls6()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }


    private class _cls7 extends Animation
    {

        private final SwipeRefreshLayout this$0;

        public final void applyTransformation(float f, Transformation transformation)
        {
            moveToStart(f);
        }

        _cls7()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }


    private class _cls5
        implements android.view.animation.Animation.AnimationListener
    {

        private final SwipeRefreshLayout this$0;

        public final void onAnimationEnd(Animation animation)
        {
            boolean flag = mScale;
            startScaleDownAnimation(null);
        }

        public final void onAnimationRepeat(Animation animation)
        {
        }

        public final void onAnimationStart(Animation animation)
        {
        }

        _cls5()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }


    private class _cls4 extends Animation
    {

        private final SwipeRefreshLayout this$0;
        private final int val$endingAlpha;
        private final int val$startingAlpha;

        public final void applyTransformation(float f, Transformation transformation)
        {
            mProgress.setAlpha((int)((float)startingAlpha + (float)(endingAlpha - startingAlpha) * f));
        }

        _cls4()
        {
            this$0 = SwipeRefreshLayout.this;
            startingAlpha = i;
            endingAlpha = j;
            super();
        }
    }


    private class _cls2 extends Animation
    {

        private final SwipeRefreshLayout this$0;

        public final void applyTransformation(float f, Transformation transformation)
        {
            transformation = SwipeRefreshLayout.this;
            ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleX(f);
            ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleY(f);
        }

        _cls2()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }


    private class _cls3 extends Animation
    {

        private final SwipeRefreshLayout this$0;

        public final void applyTransformation(float f, Transformation transformation)
        {
            transformation = SwipeRefreshLayout.this;
            f = 1.0F - f;
            ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleX(f);
            ((SwipeRefreshLayout) (transformation)).mCircleView.setScaleY(f);
        }

        _cls3()
        {
            this$0 = SwipeRefreshLayout.this;
            super();
        }
    }

}
