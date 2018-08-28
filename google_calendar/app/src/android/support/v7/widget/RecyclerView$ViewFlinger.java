// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, GapWorker

final class rpolator
    implements Runnable
{

    private boolean mEatRunOnAnimationRequest;
    private Interpolator mInterpolator;
    public int mLastFlingX;
    public int mLastFlingY;
    private boolean mReSchedulePostAnimationCallback;
    public OverScroller mScroller;
    public final RecyclerView this$0;

    final int computeScrollDuration(int i, int j, int k, int l)
    {
        int i1 = Math.abs(i);
        int j1 = Math.abs(j);
        float f;
        float f1;
        float f2;
        boolean flag;
        if (i1 > j1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        k = (int)Math.sqrt(k * k + l * l);
        j = (int)Math.sqrt(i * i + j * j);
        if (flag)
        {
            i = getWidth();
        } else
        {
            i = getHeight();
        }
        l = i / 2;
        f2 = Math.min(1.0F, ((float)j * 1.0F) / (float)i);
        f = l;
        f1 = l;
        f2 = (float)Math.sin((f2 - 0.5F) * 0.4712389F);
        if (k > 0)
        {
            i = Math.round(1000F * Math.abs((f2 * f1 + f) / (float)k)) * 4;
        } else
        {
            if (flag)
            {
                j = i1;
            } else
            {
                j = j1;
            }
            i = (int)(((float)j / (float)i + 1.0F) * 300F);
        }
        return Math.min(i, 2000);
    }

    final void postOnAnimation()
    {
        if (mEatRunOnAnimationRequest)
        {
            mReSchedulePostAnimationCallback = true;
            return;
        } else
        {
            removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
            return;
        }
    }

    public final void run()
    {
        if (mLayout != null) goto _L2; else goto _L1
_L1:
        removeCallbacks(this);
        mScroller.abortAnimation();
_L4:
        return;
_L2:
        mReSchedulePostAnimationCallback = false;
        mEatRunOnAnimationRequest = true;
        consumePendingUpdateOperations();
        Object obj = mScroller;
        r r = mLayout;
        if (((OverScroller) (obj)).computeScrollOffset())
        {
            int ai[] = mScrollConsumed;
            int k2 = ((OverScroller) (obj)).getCurrX();
            int l2 = ((OverScroller) (obj)).getCurrY();
            int l = k2 - mLastFlingX;
            int k = l2 - mLastFlingY;
            mLastFlingX = k2;
            mLastFlingY = l2;
            if (dispatchNestedPreScroll(l, k, ai, null, 1))
            {
                int i = ai[0];
                k -= ai[1];
                l -= i;
            }
            int i1;
            int j1;
            int k1;
            int l1;
            if (mAdapter != null)
            {
                scrollStep(l, k, mScrollStepConsumed);
                i1 = mScrollStepConsumed[0];
                j1 = mScrollStepConsumed[1];
                k1 = l - i1;
                l1 = k - j1;
            } else
            {
                l1 = 0;
                k1 = 0;
                j1 = 0;
                i1 = 0;
            }
            if (!mItemDecorations.isEmpty())
            {
                invalidate();
            }
            if (getOverScrollMode() != 2)
            {
                considerReleasingGlowsOnScroll(l, k);
            }
            if (!dispatchNestedScroll(i1, j1, k1, l1, null, 1) && (k1 != 0 || l1 != 0))
            {
                int i2 = (int)((OverScroller) (obj)).getCurrVelocity();
                int j;
                int j2;
                if (k1 != k2)
                {
                    if (k1 < 0)
                    {
                        j = -i2;
                    } else
                    if (k1 > 0)
                    {
                        j = i2;
                    } else
                    {
                        j = 0;
                    }
                    j2 = j;
                } else
                {
                    j2 = 0;
                }
                if (l1 != l2)
                {
                    if (l1 < 0)
                    {
                        j = -i2;
                    } else
                    {
                        j = i2;
                        if (l1 <= 0)
                        {
                            j = 0;
                        }
                    }
                } else
                {
                    j = 0;
                }
                if (getOverScrollMode() != 2)
                {
                    RecyclerView recyclerview = RecyclerView.this;
                    if (j2 < 0)
                    {
                        recyclerview.ensureLeftGlow();
                        recyclerview.mLeftGlow.onAbsorb(-j2);
                    } else
                    if (j2 > 0)
                    {
                        recyclerview.ensureRightGlow();
                        recyclerview.mRightGlow.onAbsorb(j2);
                    }
                    if (j < 0)
                    {
                        recyclerview.ensureTopGlow();
                        recyclerview.mTopGlow.onAbsorb(-j);
                    } else
                    if (j > 0)
                    {
                        recyclerview.ensureBottomGlow();
                        recyclerview.mBottomGlow.onAbsorb(j);
                    }
                    if (j2 != 0 || j != 0)
                    {
                        ViewCompat.postInvalidateOnAnimation(recyclerview);
                    }
                }
                if ((j2 != 0 || k1 == k2 || ((OverScroller) (obj)).getFinalX() == 0) && (j != 0 || l1 == l2 || ((OverScroller) (obj)).getFinalY() == 0))
                {
                    ((OverScroller) (obj)).abortAnimation();
                }
            }
            if (i1 != 0 || j1 != 0)
            {
                dispatchOnScrolled(i1, j1);
            }
            if (!RecyclerView.access$900(RecyclerView.this))
            {
                invalidate();
            }
            if (k != 0 && mLayout.canScrollVertically() && j1 == k)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (l != 0 && mLayout.canScrollHorizontally() && i1 == l)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (l == 0 && k == 0 || i1 != 0 || j != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (!((OverScroller) (obj)).isFinished())
            {
                if (j != 0)
                {
                    break; /* Loop/switch isn't completed */
                }
                obj = RecyclerView.this;
                if (((RecyclerView) (obj)).mScrollingChildHelper == null)
                {
                    obj.mScrollingChildHelper = new NestedScrollingChildHelper(((android.view.View) (obj)));
                }
                if (((RecyclerView) (obj)).mScrollingChildHelper.getNestedScrollingParentForType(1) != null)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    break; /* Loop/switch isn't completed */
                }
            }
            setScrollState(0);
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                obj = mPrefetchRegistry;
                if (((egistryImpl) (obj)).mPrefetchArray != null)
                {
                    Arrays.fill(((egistryImpl) (obj)).mPrefetchArray, -1);
                }
                obj.mCount = 0;
            }
            obj = RecyclerView.this;
            if (((RecyclerView) (obj)).mScrollingChildHelper == null)
            {
                obj.mScrollingChildHelper = new NestedScrollingChildHelper(((android.view.View) (obj)));
            }
            ((RecyclerView) (obj)).mScrollingChildHelper.stopNestedScroll(1);
        }
        mEatRunOnAnimationRequest = false;
        if (mReSchedulePostAnimationCallback)
        {
            postOnAnimation();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        postOnAnimation();
        if (mGapWorker != null)
        {
            mGapWorker.postFromTraversal(RecyclerView.this, l, k);
        }
        break MISSING_BLOCK_LABEL_673;
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final void smoothScrollBy(int i, int j, int k, Interpolator interpolator)
    {
        if (mInterpolator != interpolator)
        {
            mInterpolator = interpolator;
            mScroller = new OverScroller(getContext(), interpolator);
        }
        setScrollState(2);
        mLastFlingY = 0;
        mLastFlingX = 0;
        mScroller.startScroll(0, 0, i, j, k);
        if (android.os.oll < 23)
        {
            mScroller.computeScrollOffset();
        }
        postOnAnimation();
    }

    egistryImpl()
    {
        this$0 = RecyclerView.this;
        super();
        mInterpolator = RecyclerView.sQuinticInterpolator;
        mEatRunOnAnimationRequest = false;
        mReSchedulePostAnimationCallback = false;
        mScroller = new OverScroller(getContext(), RecyclerView.sQuinticInterpolator);
    }
}
