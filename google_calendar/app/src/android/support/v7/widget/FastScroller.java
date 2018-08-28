// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

final class FastScroller extends RecyclerView.ItemDecoration
    implements RecyclerView.OnItemTouchListener
{

    private static final int EMPTY_STATE_SET[] = new int[0];
    private static final int PRESSED_STATE_SET[] = {
        0x10100a7
    };
    public int mAnimationState;
    private int mDragState;
    private final Runnable mHideRunnable = new _cls1();
    private float mHorizontalDragX;
    private final int mHorizontalRange[] = new int[2];
    public int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    public int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    public boolean mNeedHorizontalScrollbar;
    public boolean mNeedVerticalScrollbar;
    private final RecyclerView.OnScrollListener mOnScrollListener = new _cls2();
    public RecyclerView mRecyclerView;
    public int mRecyclerViewHeight;
    public int mRecyclerViewWidth;
    public final int mScrollbarMinimumRange;
    public final ValueAnimator mShowHideAnimator = ValueAnimator.ofFloat(new float[] {
        0.0F, 1.0F
    });
    public int mState;
    private float mVerticalDragY;
    private final int mVerticalRange[] = new int[2];
    public int mVerticalThumbCenterY;
    public final StateListDrawable mVerticalThumbDrawable;
    public int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    public final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    FastScroller(RecyclerView recyclerview, StateListDrawable statelistdrawable, Drawable drawable, StateListDrawable statelistdrawable1, Drawable drawable1, int i, int j, 
            int k)
    {
        mRecyclerViewWidth = 0;
        mRecyclerViewHeight = 0;
        mNeedVerticalScrollbar = false;
        mNeedHorizontalScrollbar = false;
        mState = 0;
        mDragState = 0;
        mAnimationState = 0;
        mVerticalThumbDrawable = statelistdrawable;
        mVerticalTrackDrawable = drawable;
        mHorizontalThumbDrawable = statelistdrawable1;
        mHorizontalTrackDrawable = drawable1;
        mVerticalThumbWidth = Math.max(i, statelistdrawable.getIntrinsicWidth());
        mVerticalTrackWidth = Math.max(i, drawable.getIntrinsicWidth());
        mHorizontalThumbHeight = Math.max(i, statelistdrawable1.getIntrinsicWidth());
        mHorizontalTrackHeight = Math.max(i, drawable1.getIntrinsicWidth());
        mScrollbarMinimumRange = j;
        mMargin = k;
        mVerticalThumbDrawable.setAlpha(255);
        mVerticalTrackDrawable.setAlpha(255);
        mShowHideAnimator.addListener(new AnimatorListener());
        mShowHideAnimator.addUpdateListener(new AnimatorUpdater());
        if (mRecyclerView != recyclerview)
        {
            if (mRecyclerView != null)
            {
                statelistdrawable = mRecyclerView;
                if (((RecyclerView) (statelistdrawable)).mLayout != null)
                {
                    ((RecyclerView) (statelistdrawable)).mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
                }
                ((RecyclerView) (statelistdrawable)).mItemDecorations.remove(this);
                if (((RecyclerView) (statelistdrawable)).mItemDecorations.isEmpty())
                {
                    boolean flag;
                    if (statelistdrawable.getOverScrollMode() == 2)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    statelistdrawable.setWillNotDraw(flag);
                }
                statelistdrawable.markItemDecorInsetsDirty();
                statelistdrawable.requestLayout();
                statelistdrawable = mRecyclerView;
                ((RecyclerView) (statelistdrawable)).mOnItemTouchListeners.remove(this);
                if (((RecyclerView) (statelistdrawable)).mActiveOnItemTouchListener == this)
                {
                    statelistdrawable.mActiveOnItemTouchListener = null;
                }
                statelistdrawable = mRecyclerView;
                drawable = mOnScrollListener;
                if (((RecyclerView) (statelistdrawable)).mScrollListeners != null)
                {
                    ((RecyclerView) (statelistdrawable)).mScrollListeners.remove(drawable);
                }
                mRecyclerView.removeCallbacks(mHideRunnable);
            }
            mRecyclerView = recyclerview;
            if (mRecyclerView != null)
            {
                recyclerview = mRecyclerView;
                if (recyclerview.mLayout != null)
                {
                    recyclerview.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
                }
                if (recyclerview.mItemDecorations.isEmpty())
                {
                    recyclerview.setWillNotDraw(false);
                }
                recyclerview.mItemDecorations.add(this);
                recyclerview.markItemDecorInsetsDirty();
                recyclerview.requestLayout();
                mRecyclerView.mOnItemTouchListeners.add(this);
                recyclerview = mRecyclerView;
                statelistdrawable = mOnScrollListener;
                if (recyclerview.mScrollListeners == null)
                {
                    recyclerview.mScrollListeners = new ArrayList();
                }
                recyclerview.mScrollListeners.add(statelistdrawable);
            }
        }
    }

    private final boolean isPointInsideHorizontalThumb(float f, float f1)
    {
        return f1 >= (float)(mRecyclerViewHeight - mHorizontalThumbHeight) && f >= (float)(mHorizontalThumbCenterX - mHorizontalThumbWidth / 2) && f <= (float)(mHorizontalThumbCenterX + mHorizontalThumbWidth / 2);
    }

    private final boolean isPointInsideVerticalThumb(float f, float f1)
    {
        return (ViewCompat.getLayoutDirection(mRecyclerView) != 1 ? f >= (float)(mRecyclerViewWidth - mVerticalThumbWidth) : f <= (float)(mVerticalThumbWidth / 2)) && (f1 >= (float)(mVerticalThumbCenterY - mVerticalThumbHeight / 2) && f1 <= (float)(mVerticalThumbCenterY + mVerticalThumbHeight / 2));
    }

    private static int scrollTo(float f, float f1, int ai[], int i, int j, int k)
    {
        int l = ai[1] - ai[0];
        if (l != 0)
        {
            f = (f1 - f) / (float)l;
            i -= k;
            k = (int)(f * (float)i);
            j += k;
            if (j < i && j >= 0)
            {
                return k;
            }
        }
        return 0;
    }

    private final void show()
    {
        switch (mAnimationState)
        {
        case 1: // '\001'
        case 2: // '\002'
        default:
            return;

        case 3: // '\003'
            mShowHideAnimator.cancel();
            // fall through

        case 0: // '\0'
            mAnimationState = 1;
            break;
        }
        mShowHideAnimator.setFloatValues(new float[] {
            ((Float)mShowHideAnimator.getAnimatedValue()).floatValue(), 1.0F
        });
        mShowHideAnimator.setDuration(500L);
        mShowHideAnimator.setStartDelay(0L);
        mShowHideAnimator.start();
    }

    public final void onDrawOver$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTIILG_0(Canvas canvas, RecyclerView recyclerview)
    {
        if (mRecyclerViewWidth != mRecyclerView.getWidth() || mRecyclerViewHeight != mRecyclerView.getHeight())
        {
            mRecyclerViewWidth = mRecyclerView.getWidth();
            mRecyclerViewHeight = mRecyclerView.getHeight();
            setState(0);
        } else
        if (mAnimationState != 0)
        {
            if (mNeedVerticalScrollbar)
            {
                int i = mRecyclerViewWidth - mVerticalThumbWidth;
                int j = mVerticalThumbCenterY - mVerticalThumbHeight / 2;
                mVerticalThumbDrawable.setBounds(0, 0, mVerticalThumbWidth, mVerticalThumbHeight);
                mVerticalTrackDrawable.setBounds(0, 0, mVerticalTrackWidth, mRecyclerViewHeight);
                if (ViewCompat.getLayoutDirection(mRecyclerView) == 1)
                {
                    mVerticalTrackDrawable.draw(canvas);
                    canvas.translate(mVerticalThumbWidth, j);
                    canvas.scale(-1F, 1.0F);
                    mVerticalThumbDrawable.draw(canvas);
                    canvas.scale(1.0F, 1.0F);
                    canvas.translate(-mVerticalThumbWidth, -j);
                } else
                {
                    canvas.translate(i, 0.0F);
                    mVerticalTrackDrawable.draw(canvas);
                    canvas.translate(0.0F, j);
                    mVerticalThumbDrawable.draw(canvas);
                    canvas.translate(-i, -j);
                }
            }
            if (mNeedHorizontalScrollbar)
            {
                i = mRecyclerViewHeight - mHorizontalThumbHeight;
                j = mHorizontalThumbCenterX - mHorizontalThumbWidth / 2;
                mHorizontalThumbDrawable.setBounds(0, 0, mHorizontalThumbWidth, mHorizontalThumbHeight);
                mHorizontalTrackDrawable.setBounds(0, 0, mRecyclerViewWidth, mHorizontalTrackHeight);
                canvas.translate(0.0F, i);
                mHorizontalTrackDrawable.draw(canvas);
                canvas.translate(j, 0.0F);
                mHorizontalThumbDrawable.draw(canvas);
                canvas.translate(-j, -i);
                return;
            }
        }
    }

    public final boolean onInterceptTouchEvent$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIIMG_0(MotionEvent motionevent)
    {
        if (mState != 1) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        flag = isPointInsideVerticalThumb(motionevent.getX(), motionevent.getY());
        flag1 = isPointInsideHorizontalThumb(motionevent.getX(), motionevent.getY());
        if (motionevent.getAction() != 0 || !flag && !flag1) goto _L4; else goto _L3
_L3:
        if (!flag1) goto _L6; else goto _L5
_L5:
        mDragState = 1;
        mHorizontalDragX = (int)motionevent.getX();
_L10:
        setState(2);
_L8:
        return true;
_L6:
        if (flag)
        {
            mDragState = 2;
            mVerticalDragY = (int)motionevent.getY();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        return false;
_L2:
        if (mState == 2) goto _L8; else goto _L7
_L7:
        return false;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void onRequestDisallowInterceptTouchEvent$51D2ILG_0()
    {
    }

    public final void onTouchEvent$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0(MotionEvent motionevent)
    {
        if (mState != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (motionevent.getAction() != 0) goto _L4; else goto _L3
_L3:
        boolean flag;
        boolean flag1;
        flag = isPointInsideVerticalThumb(motionevent.getX(), motionevent.getY());
        flag1 = isPointInsideHorizontalThumb(motionevent.getX(), motionevent.getY());
        if (!flag && !flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!flag1) goto _L6; else goto _L5
_L5:
        mDragState = 1;
        mHorizontalDragX = (int)motionevent.getX();
_L7:
        setState(2);
        return;
_L6:
        if (flag)
        {
            mDragState = 2;
            mVerticalDragY = (int)motionevent.getY();
        }
        if (true) goto _L7; else goto _L4
_L4:
        if (motionevent.getAction() == 1 && mState == 2)
        {
            mVerticalDragY = 0.0F;
            mHorizontalDragX = 0.0F;
            setState(1);
            mDragState = 0;
            return;
        }
        if (motionevent.getAction() == 2 && mState == 2)
        {
            show();
            if (mDragState == 1)
            {
                float f = motionevent.getX();
                mHorizontalRange[0] = mMargin;
                mHorizontalRange[1] = mRecyclerViewWidth - mMargin;
                int ai[] = mHorizontalRange;
                f = Math.max(ai[0], Math.min(ai[1], f));
                if (Math.abs((float)mHorizontalThumbCenterX - f) >= 2.0F)
                {
                    int i = scrollTo(mHorizontalDragX, f, ai, mRecyclerView.computeHorizontalScrollRange(), mRecyclerView.computeHorizontalScrollOffset(), mRecyclerViewWidth);
                    if (i != 0)
                    {
                        mRecyclerView.scrollBy(i, 0);
                    }
                    mHorizontalDragX = f;
                }
            }
            if (mDragState == 2)
            {
                float f1 = motionevent.getY();
                mVerticalRange[0] = mMargin;
                mVerticalRange[1] = mRecyclerViewHeight - mMargin;
                motionevent = mVerticalRange;
                f1 = Math.max(motionevent[0], Math.min(motionevent[1], f1));
                if (Math.abs((float)mVerticalThumbCenterY - f1) >= 2.0F)
                {
                    int j = scrollTo(mVerticalDragY, f1, motionevent, mRecyclerView.computeVerticalScrollRange(), mRecyclerView.computeVerticalScrollOffset(), mRecyclerViewHeight);
                    if (j != 0)
                    {
                        mRecyclerView.scrollBy(0, j);
                    }
                    mVerticalDragY = f1;
                    return;
                }
            }
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    final void setState(int i)
    {
        if (i == 2 && mState != 2)
        {
            mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            mRecyclerView.removeCallbacks(mHideRunnable);
        }
        if (i == 0)
        {
            mRecyclerView.invalidate();
        } else
        {
            show();
        }
        if (mState != 2 || i == 2) goto _L2; else goto _L1
_L1:
        mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
        mRecyclerView.removeCallbacks(mHideRunnable);
        mRecyclerView.postDelayed(mHideRunnable, 1200);
_L4:
        mState = i;
        return;
_L2:
        if (i == 1)
        {
            mRecyclerView.removeCallbacks(mHideRunnable);
            mRecyclerView.postDelayed(mHideRunnable, 1500);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }


    private class _cls1
        implements Runnable
    {

        private final FastScroller this$0;

        public final void run()
        {
            FastScroller fastscroller = FastScroller.this;
            switch (fastscroller.mAnimationState)
            {
            default:
                return;

            case 1: // '\001'
                fastscroller.mShowHideAnimator.cancel();
                // fall through

            case 2: // '\002'
                fastscroller.mAnimationState = 3;
                break;
            }
            fastscroller.mShowHideAnimator.setFloatValues(new float[] {
                ((Float)fastscroller.mShowHideAnimator.getAnimatedValue()).floatValue(), 0.0F
            });
            fastscroller.mShowHideAnimator.setDuration(500L);
            fastscroller.mShowHideAnimator.start();
        }

        _cls1()
        {
            this$0 = FastScroller.this;
            super();
        }
    }


    private class _cls2 extends RecyclerView.OnScrollListener
    {

        private final FastScroller this$0;

        public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
        {
            FastScroller fastscroller = FastScroller.this;
            i = recyclerview.computeHorizontalScrollOffset();
            int j = recyclerview.computeVerticalScrollOffset();
            int k = fastscroller.mRecyclerView.computeVerticalScrollRange();
            int l = fastscroller.mRecyclerViewHeight;
            int i1;
            int j1;
            boolean flag;
            if (k - l > 0 && fastscroller.mRecyclerViewHeight >= fastscroller.mScrollbarMinimumRange)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            fastscroller.mNeedVerticalScrollbar = flag;
            i1 = fastscroller.mRecyclerView.computeHorizontalScrollRange();
            j1 = fastscroller.mRecyclerViewWidth;
            if (i1 - j1 > 0 && fastscroller.mRecyclerViewWidth >= fastscroller.mScrollbarMinimumRange)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            fastscroller.mNeedHorizontalScrollbar = flag;
            if (!fastscroller.mNeedVerticalScrollbar && !fastscroller.mNeedHorizontalScrollbar)
            {
                if (fastscroller.mState != 0)
                {
                    fastscroller.setState(0);
                }
            } else
            {
                if (fastscroller.mNeedVerticalScrollbar)
                {
                    fastscroller.mVerticalThumbCenterY = (int)((((float)j + (float)l / 2.0F) * (float)l) / (float)k);
                    fastscroller.mVerticalThumbHeight = Math.min(l, (l * l) / k);
                }
                if (fastscroller.mNeedHorizontalScrollbar)
                {
                    fastscroller.mHorizontalThumbCenterX = (int)((((float)i + (float)j1 / 2.0F) * (float)j1) / (float)i1);
                    fastscroller.mHorizontalThumbWidth = Math.min(j1, (j1 * j1) / i1);
                }
                if (fastscroller.mState == 0 || fastscroller.mState == 1)
                {
                    fastscroller.setState(1);
                    return;
                }
            }
        }

        _cls2()
        {
            this$0 = FastScroller.this;
            super();
        }
    }


    private class AnimatorListener extends AnimatorListenerAdapter
    {

        private boolean mCanceled;
        private final FastScroller this$0;

        public final void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public final void onAnimationEnd(Animator animator)
        {
            if (mCanceled)
            {
                mCanceled = false;
                return;
            }
            if (((Float)mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0F)
            {
                mAnimationState = 0;
                setState(0);
                return;
            } else
            {
                mAnimationState = 2;
                mRecyclerView.invalidate();
                return;
            }
        }

        AnimatorListener()
        {
            this$0 = FastScroller.this;
            super();
            mCanceled = false;
        }
    }


    private class AnimatorUpdater
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final FastScroller this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            int i = (int)(((Float)valueanimator.getAnimatedValue()).floatValue() * 255F);
            mVerticalThumbDrawable.setAlpha(i);
            mVerticalTrackDrawable.setAlpha(i);
            mRecyclerView.invalidate();
        }

        AnimatorUpdater()
        {
            this$0 = FastScroller.this;
            super();
        }
    }

}
