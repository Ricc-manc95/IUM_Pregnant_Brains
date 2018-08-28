// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper, OrientationHelper, ViewBoundsCheck, 
//            ViewInfoStore

public class LinearLayoutManager extends RecyclerView.LayoutManager
{

    private final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    private int mOrientation;
    private OrientationHelper mOrientationHelper;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mReverseLayout;
    private boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    public LinearLayoutManager(int i, boolean flag)
    {
        mOrientation = 1;
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mStackFromEnd = false;
        mSmoothScrollbarEnabled = true;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mPendingSavedState = null;
        mAnchorInfo = new AnchorInfo();
        mLayoutChunkResult = new LayoutChunkResult();
        mInitialPrefetchItemCount = 2;
        setOrientation(i);
        assertNotInLayoutOrScroll(null);
        if (flag != mReverseLayout)
        {
            mReverseLayout = flag;
            if (super.mRecyclerView != null)
            {
                super.mRecyclerView.requestLayout();
            }
        }
    }

    public LinearLayoutManager(Context context)
    {
        this(1, false);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeset, int i, int j)
    {
        mOrientation = 1;
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mStackFromEnd = false;
        mSmoothScrollbarEnabled = true;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mPendingSavedState = null;
        mAnchorInfo = new AnchorInfo();
        mLayoutChunkResult = new LayoutChunkResult();
        mInitialPrefetchItemCount = 2;
        RecyclerView.LayoutManager.Properties properties = new RecyclerView.LayoutManager.Properties();
        context = context.obtainStyledAttributes(attributeset, android.support.v7.recyclerview.R.styleable.RecyclerView, i, j);
        properties.orientation = context.getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_android_orientation, 1);
        context.getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_spanCount, 1);
        properties.reverseLayout = context.getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_reverseLayout, false);
        properties.stackFromEnd = context.getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_stackFromEnd, false);
        context.recycle();
        setOrientation(properties.orientation);
        boolean flag = properties.reverseLayout;
        assertNotInLayoutOrScroll(null);
        if (flag != mReverseLayout)
        {
            mReverseLayout = flag;
            if (super.mRecyclerView != null)
            {
                super.mRecyclerView.requestLayout();
            }
        }
        flag = properties.stackFromEnd;
        assertNotInLayoutOrScroll(null);
        if (mStackFromEnd != flag)
        {
            mStackFromEnd = flag;
            if (super.mRecyclerView != null)
            {
                super.mRecyclerView.requestLayout();
            }
        }
    }

    private final int computeScrollExtent(RecyclerView.State state)
    {
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            if (mLayoutState == null)
            {
                mLayoutState = new LayoutState();
            }
            OrientationHelper orientationhelper = mOrientationHelper;
            View view;
            View view1;
            int j;
            boolean flag;
            if (!mSmoothScrollbarEnabled)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view1 = findFirstVisibleChildClosestToStart(flag, true);
            if (!mSmoothScrollbarEnabled)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view = findFirstVisibleChildClosestToEnd(flag, true);
            flag = mSmoothScrollbarEnabled;
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper1 = super.mChildHelper;
                j = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                if (state.mInPreLayout)
                {
                    j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    j = state.mItemCount;
                }
                if (j != 0 && view1 != null && view != null)
                {
                    if (!flag)
                    {
                        state = ((RecyclerView.LayoutParams)view1.getLayoutParams()).mViewHolder;
                        int l;
                        if (((RecyclerView.ViewHolder) (state)).mPreLayoutPosition == -1)
                        {
                            j = ((RecyclerView.ViewHolder) (state)).mPosition;
                        } else
                        {
                            j = ((RecyclerView.ViewHolder) (state)).mPreLayoutPosition;
                        }
                        state = ((RecyclerView.LayoutParams)view.getLayoutParams()).mViewHolder;
                        if (((RecyclerView.ViewHolder) (state)).mPreLayoutPosition == -1)
                        {
                            l = ((RecyclerView.ViewHolder) (state)).mPosition;
                        } else
                        {
                            l = ((RecyclerView.ViewHolder) (state)).mPreLayoutPosition;
                        }
                        return Math.abs(j - l) + 1;
                    } else
                    {
                        int k = orientationhelper.getDecoratedEnd(view);
                        int i1 = orientationhelper.getDecoratedStart(view1);
                        return Math.min(orientationhelper.getTotalSpace(), k - i1);
                    }
                }
            }
        }
        return 0;
    }

    private final int computeScrollOffset(RecyclerView.State state)
    {
        boolean flag = false;
        int i;
        int k;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            k = ((flag) ? 1 : 0);
        } else
        {
            if (mLayoutState == null)
            {
                mLayoutState = new LayoutState();
            }
            OrientationHelper orientationhelper = mOrientationHelper;
            View view;
            View view1;
            int j;
            boolean flag1;
            boolean flag2;
            if (!mSmoothScrollbarEnabled)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            view = findFirstVisibleChildClosestToStart(flag1, true);
            if (!mSmoothScrollbarEnabled)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            view1 = findFirstVisibleChildClosestToEnd(flag1, true);
            flag1 = mSmoothScrollbarEnabled;
            flag2 = mShouldReverseLayout;
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper1 = super.mChildHelper;
                j = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
            } else
            {
                j = 0;
            }
            k = ((flag) ? 1 : 0);
            if (j != 0)
            {
                if (state.mInPreLayout)
                {
                    j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    j = state.mItemCount;
                }
                k = ((flag) ? 1 : 0);
                if (j != 0)
                {
                    k = ((flag) ? 1 : 0);
                    if (view != null)
                    {
                        k = ((flag) ? 1 : 0);
                        if (view1 != null)
                        {
                            RecyclerView.ViewHolder viewholder = ((RecyclerView.LayoutParams)view.getLayoutParams()).mViewHolder;
                            int l;
                            if (viewholder.mPreLayoutPosition == -1)
                            {
                                j = viewholder.mPosition;
                            } else
                            {
                                j = viewholder.mPreLayoutPosition;
                            }
                            viewholder = ((RecyclerView.LayoutParams)view1.getLayoutParams()).mViewHolder;
                            if (viewholder.mPreLayoutPosition == -1)
                            {
                                k = viewholder.mPosition;
                            } else
                            {
                                k = viewholder.mPreLayoutPosition;
                            }
                            l = Math.min(j, k);
                            viewholder = ((RecyclerView.LayoutParams)view.getLayoutParams()).mViewHolder;
                            if (viewholder.mPreLayoutPosition == -1)
                            {
                                j = viewholder.mPosition;
                            } else
                            {
                                j = viewholder.mPreLayoutPosition;
                            }
                            viewholder = ((RecyclerView.LayoutParams)view1.getLayoutParams()).mViewHolder;
                            if (viewholder.mPreLayoutPosition == -1)
                            {
                                k = viewholder.mPosition;
                            } else
                            {
                                k = viewholder.mPreLayoutPosition;
                            }
                            k = Math.max(j, k);
                            if (flag2)
                            {
                                int i1;
                                if (state.mInPreLayout)
                                {
                                    j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                                } else
                                {
                                    j = state.mItemCount;
                                }
                                j = Math.max(0, j - k - 1);
                            } else
                            {
                                j = Math.max(0, l);
                            }
                            k = j;
                            if (flag1)
                            {
                                i1 = Math.abs(orientationhelper.getDecoratedEnd(view1) - orientationhelper.getDecoratedStart(view));
                                state = ((RecyclerView.LayoutParams)view.getLayoutParams()).mViewHolder;
                                if (((RecyclerView.ViewHolder) (state)).mPreLayoutPosition == -1)
                                {
                                    k = ((RecyclerView.ViewHolder) (state)).mPosition;
                                } else
                                {
                                    k = ((RecyclerView.ViewHolder) (state)).mPreLayoutPosition;
                                }
                                state = ((RecyclerView.LayoutParams)view1.getLayoutParams()).mViewHolder;
                                if (((RecyclerView.ViewHolder) (state)).mPreLayoutPosition == -1)
                                {
                                    l = ((RecyclerView.ViewHolder) (state)).mPosition;
                                } else
                                {
                                    l = ((RecyclerView.ViewHolder) (state)).mPreLayoutPosition;
                                }
                                k = Math.abs(k - l);
                                return Math.round(((float)i1 / (float)(k + 1)) * (float)j + (float)(orientationhelper.getStartAfterPadding() - orientationhelper.getDecoratedStart(view)));
                            }
                        }
                    }
                }
            }
        }
        return k;
    }

    private final int computeScrollRange(RecyclerView.State state)
    {
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        return 0;
_L2:
        Object obj;
        Object obj1;
        View view;
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        obj1 = mOrientationHelper;
        boolean flag;
        if (!mSmoothScrollbarEnabled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = findFirstVisibleChildClosestToStart(flag, true);
        if (!mSmoothScrollbarEnabled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = findFirstVisibleChildClosestToEnd(flag, true);
        flag = mSmoothScrollbarEnabled;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper1 = super.mChildHelper;
            i = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L1; else goto _L3
_L3:
        if (state.mInPreLayout)
        {
            i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            i = state.mItemCount;
        }
        if (i == 0 || view == null || obj == null) goto _L1; else goto _L4
_L4:
        if (flag) goto _L6; else goto _L5
_L5:
        if (!state.mInPreLayout) goto _L8; else goto _L7
_L7:
        i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
_L10:
        return i;
_L8:
        return state.mItemCount;
_L6:
        int k = ((OrientationHelper) (obj1)).getDecoratedEnd(((View) (obj)));
        int l = ((OrientationHelper) (obj1)).getDecoratedStart(view);
        obj1 = ((RecyclerView.LayoutParams)view.getLayoutParams()).mViewHolder;
        float f;
        int j;
        if (((RecyclerView.ViewHolder) (obj1)).mPreLayoutPosition == -1)
        {
            i = ((RecyclerView.ViewHolder) (obj1)).mPosition;
        } else
        {
            i = ((RecyclerView.ViewHolder) (obj1)).mPreLayoutPosition;
        }
        obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
        {
            j = ((RecyclerView.ViewHolder) (obj)).mPosition;
        } else
        {
            j = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
        }
        i = Math.abs(i - j);
        f = (float)(k - l) / (float)(i + 1);
        if (state.mInPreLayout)
        {
            i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            i = state.mItemCount;
        }
        i = (int)((float)i * f);
        if (true) goto _L10; else goto _L9
_L9:
    }

    private final int fill(RecyclerView.Recycler recycler, LayoutState layoutstate, RecyclerView.State state, boolean flag)
    {
        LayoutChunkResult layoutchunkresult;
        int i2;
        int l3;
        l3 = layoutstate.mAvailable;
        if (layoutstate.mScrollingOffset != 0x80000000)
        {
            if (layoutstate.mAvailable < 0)
            {
                layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutstate.mAvailable;
            }
            recycleByLayoutState(recycler, layoutstate);
        }
        int i = layoutstate.mAvailable;
        int k = layoutstate.mExtra;
        layoutchunkresult = mLayoutChunkResult;
        i2 = i + k;
_L13:
        if (!layoutstate.mInfinite && i2 <= 0) goto _L2; else goto _L1
_L1:
        if (layoutstate.mCurrentPosition < 0) goto _L4; else goto _L3
_L3:
        View view;
        int j;
        int l = layoutstate.mCurrentPosition;
        RecyclerView.LayoutParams layoutparams;
        int k1;
        int j2;
        if (state.mInPreLayout)
        {
            j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            j = state.mItemCount;
        }
        if (l >= j) goto _L4; else goto _L5
_L5:
        j = 1;
_L9:
        if (j == 0) goto _L2; else goto _L6
_L6:
        layoutchunkresult.mConsumed = 0;
        layoutchunkresult.mFinished = false;
        layoutchunkresult.mIgnoreConsumed = false;
        layoutchunkresult.mFocusable = false;
        if (layoutstate.mScrapList == null)
        {
            break MISSING_BLOCK_LABEL_472;
        }
        k1 = layoutstate.mScrapList.size();
        j = 0;
_L10:
        if (j >= k1) goto _L8; else goto _L7
_L7:
        view = ((RecyclerView.ViewHolder)layoutstate.mScrapList.get(j)).itemView;
        layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
        RecyclerView.ViewHolder viewholder;
        int i1;
        if ((layoutparams.mViewHolder.mFlags & 8) != 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        j2 = layoutstate.mCurrentPosition;
        viewholder = layoutparams.mViewHolder;
        if (viewholder.mPreLayoutPosition == -1)
        {
            i1 = viewholder.mPosition;
        } else
        {
            i1 = viewholder.mPreLayoutPosition;
        }
        if (j2 != i1)
        {
            continue; /* Loop/switch isn't completed */
        }
        layoutstate.assignPositionFromScrapList(view);
_L11:
        if (view == null)
        {
            layoutchunkresult.mFinished = true;
        } else
        {
label0:
            {
                RecyclerView.LayoutParams layoutparams1 = (RecyclerView.LayoutParams)view.getLayoutParams();
                int j1;
                int l1;
                int k2;
                if (layoutstate.mScrapList == null)
                {
                    boolean flag3 = mShouldReverseLayout;
                    RecyclerView.LayoutParams layoutparams2;
                    Rect rect;
                    int l2;
                    boolean flag1;
                    if (layoutstate.mLayoutDirection == -1)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag3 == flag1)
                    {
                        super.addViewInt(view, -1, false);
                    } else
                    {
                        super.addViewInt(view, 0, false);
                    }
                } else
                {
                    boolean flag4 = mShouldReverseLayout;
                    boolean flag2;
                    if (layoutstate.mLayoutDirection == -1)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (flag4 == flag2)
                    {
                        super.addViewInt(view, -1, true);
                    } else
                    {
                        super.addViewInt(view, 0, true);
                    }
                }
                measureChildWithMargins(view, 0, 0);
                layoutchunkresult.mConsumed = mOrientationHelper.getDecoratedMeasurement(view);
                if (mOrientation == 1)
                {
                    if (ViewCompat.getLayoutDirection(super.mRecyclerView) == 1)
                    {
                        j1 = super.mWidth;
                        if (super.mRecyclerView != null)
                        {
                            j = super.mRecyclerView.getPaddingRight();
                        } else
                        {
                            j = 0;
                        }
                        j = j1 - j;
                        j1 = j - mOrientationHelper.getDecoratedMeasurementInOther(view);
                    } else
                    {
                        if (super.mRecyclerView != null)
                        {
                            j = super.mRecyclerView.getPaddingLeft();
                        } else
                        {
                            j = 0;
                        }
                        l1 = mOrientationHelper.getDecoratedMeasurementInOther(view);
                        j1 = j;
                        j = l1 + j;
                    }
                    if (layoutstate.mLayoutDirection == -1)
                    {
                        k2 = layoutstate.mOffset;
                        l2 = layoutstate.mOffset - layoutchunkresult.mConsumed;
                        l1 = j1;
                        j1 = l2;
                    } else
                    {
                        int i3 = layoutstate.mOffset;
                        k2 = layoutstate.mOffset;
                        int i4 = layoutchunkresult.mConsumed;
                        l1 = j1;
                        k2 += i4;
                        j1 = i3;
                    }
                } else
                {
                    if (super.mRecyclerView != null)
                    {
                        j = super.mRecyclerView.getPaddingTop();
                    } else
                    {
                        j = 0;
                    }
                    k2 = mOrientationHelper.getDecoratedMeasurementInOther(view) + j;
                    if (layoutstate.mLayoutDirection == -1)
                    {
                        int j3 = layoutstate.mOffset;
                        l1 = layoutstate.mOffset - layoutchunkresult.mConsumed;
                        j1 = j;
                        j = j3;
                    } else
                    {
                        l1 = layoutstate.mOffset;
                        int k3 = layoutstate.mOffset + layoutchunkresult.mConsumed;
                        j1 = j;
                        j = k3;
                    }
                }
                layoutparams2 = (RecyclerView.LayoutParams)view.getLayoutParams();
                rect = layoutparams2.mDecorInsets;
                view.layout(l1 + rect.left + layoutparams2.leftMargin, j1 + rect.top + layoutparams2.topMargin, j - rect.right - layoutparams2.rightMargin, k2 - rect.bottom - layoutparams2.bottomMargin);
                if ((layoutparams1.mViewHolder.mFlags & 8) != 0)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0)
                {
                    if ((layoutparams1.mViewHolder.mFlags & 2) != 0)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j == 0)
                    {
                        break label0;
                    }
                }
                layoutchunkresult.mIgnoreConsumed = true;
            }
            layoutchunkresult.mFocusable = view.hasFocusable();
        }
        if (!layoutchunkresult.mFinished)
        {
            layoutstate.mOffset = layoutstate.mOffset + layoutchunkresult.mConsumed * layoutstate.mLayoutDirection;
            if (!layoutchunkresult.mIgnoreConsumed || mLayoutState.mScrapList != null || !state.mInPreLayout)
            {
                layoutstate.mAvailable = layoutstate.mAvailable - layoutchunkresult.mConsumed;
                j = i2 - layoutchunkresult.mConsumed;
            } else
            {
                j = i2;
            }
            if (layoutstate.mScrollingOffset != 0x80000000)
            {
                layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutchunkresult.mConsumed;
                if (layoutstate.mAvailable < 0)
                {
                    layoutstate.mScrollingOffset = layoutstate.mScrollingOffset + layoutstate.mAvailable;
                }
                recycleByLayoutState(recycler, layoutstate);
            }
            if (!flag || !layoutchunkresult.mFocusable)
            {
                break MISSING_BLOCK_LABEL_1085;
            }
        }
_L2:
        return l3 - layoutstate.mAvailable;
_L4:
        j = 0;
          goto _L9
        j++;
          goto _L10
_L8:
        view = null;
          goto _L11
        view = recycler.tryGetViewHolderForPositionByDeadline(layoutstate.mCurrentPosition, false, 0x7fffffffffffffffL).itemView;
        layoutstate.mCurrentPosition = layoutstate.mCurrentPosition + layoutstate.mItemDirection;
          goto _L11
        i2 = j;
        if (true) goto _L13; else goto _L12
_L12:
    }

    private final View findFirstVisibleChildClosestToEnd(boolean flag, boolean flag1)
    {
        int i = 0;
        if (mShouldReverseLayout)
        {
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper = super.mChildHelper;
                i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            } else
            {
                i = 0;
            }
            return findOneVisibleChild(0, i, flag, true);
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper1 = super.mChildHelper;
            i = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
        }
        return findOneVisibleChild(i - 1, -1, flag, true);
    }

    private final View findFirstVisibleChildClosestToStart(boolean flag, boolean flag1)
    {
        int i = 0;
        if (mShouldReverseLayout)
        {
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper = super.mChildHelper;
                i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            }
            return findOneVisibleChild(i - 1, -1, flag, true);
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper1 = super.mChildHelper;
            i = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        return findOneVisibleChild(0, i, flag, true);
    }

    private final View findOnePartiallyOrCompletelyInvisibleChild(int i, int j)
    {
        Object obj = null;
        OrientationHelper orientationhelper = null;
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        int k;
        if (j > i)
        {
            k = 1;
        } else
        if (j < i)
        {
            k = -1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            obj = orientationhelper;
            if (super.mChildHelper != null)
            {
                obj = super.mChildHelper;
                i = ((ChildHelper) (obj)).getOffset(i);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(i);
            }
            return ((View) (obj));
        }
        orientationhelper = mOrientationHelper;
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            k = ((ChildHelper) (obj)).getOffset(i);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(k);
        }
        char c;
        if (orientationhelper.getDecoratedStart(((View) (obj))) < mOrientationHelper.getStartAfterPadding())
        {
            c = '\u4104';
            k = 16388;
        } else
        {
            c = '\u1041';
            k = 4097;
        }
        if (mOrientation == 0)
        {
            return mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, j, c, k);
        } else
        {
            return mVerticalBoundCheck.findOneViewWithinBoundFlags(i, j, c, k);
        }
    }

    private final View findOneVisibleChild(int i, int j, boolean flag, boolean flag1)
    {
        char c1 = '\u0140';
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        char c;
        if (flag)
        {
            c = '\u6003';
        } else
        {
            c = '\u0140';
        }
        if (!flag1)
        {
            c1 = '\0';
        }
        if (mOrientation == 0)
        {
            return mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, j, c, c1);
        } else
        {
            return mVerticalBoundCheck.findOneViewWithinBoundFlags(i, j, c, c1);
        }
    }

    private final View findReferenceChild$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357D4KII999HGMSP3IDTKM8BRMD5INEBQMD5INEEO_0(int i, int j, int k)
    {
        View view;
        Object obj;
        View view1;
        int k1;
        int l1;
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        k1 = mOrientationHelper.getStartAfterPadding();
        l1 = mOrientationHelper.getEndAfterPadding();
        int l;
        int i1;
        if (j > i)
        {
            l = 1;
        } else
        {
            l = -1;
        }
        view = null;
        view1 = null;
        if (i == j) goto _L2; else goto _L1
_L1:
        RecyclerView.ViewHolder viewholder;
        int j1;
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            i1 = ((ChildHelper) (obj)).getOffset(i);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(i1);
        } else
        {
            obj = null;
        }
        viewholder = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (viewholder.mPreLayoutPosition == -1)
        {
            j1 = viewholder.mPosition;
        } else
        {
            j1 = viewholder.mPreLayoutPosition;
        }
        if (j1 < 0 || j1 >= k)
        {
            break MISSING_BLOCK_LABEL_266;
        }
        if ((((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder.mFlags & 8) != 0)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        if (j1 == 0) goto _L4; else goto _L3
_L3:
        if (view1 != null)
        {
            break MISSING_BLOCK_LABEL_266;
        }
_L11:
        i += l;
        view1 = ((View) (obj));
        break MISSING_BLOCK_LABEL_50;
_L4:
        if (mOrientationHelper.getDecoratedStart(((View) (obj))) >= l1) goto _L6; else goto _L5
_L5:
        View view2 = ((View) (obj));
        if (mOrientationHelper.getDecoratedEnd(((View) (obj))) >= k1) goto _L7; else goto _L6
_L6:
        if (view != null)
        {
            break MISSING_BLOCK_LABEL_266;
        }
        view = ((View) (obj));
        obj = view1;
        continue; /* Loop/switch isn't completed */
_L2:
        if (view == null) goto _L9; else goto _L8
_L8:
        view2 = view;
_L7:
        return view2;
_L9:
        return view1;
        obj = view1;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private final int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int j = mOrientationHelper.getEndAfterPadding() - i;
        if (j > 0)
        {
            int k = -scrollBy(-j, recycler, state);
            j = k;
            if (flag)
            {
                i = mOrientationHelper.getEndAfterPadding() - (i + k);
                j = k;
                if (i > 0)
                {
                    mOrientationHelper.offsetChildren(i);
                    j = k + i;
                }
            }
            return j;
        } else
        {
            return 0;
        }
    }

    private final int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int j = i - mOrientationHelper.getStartAfterPadding();
        if (j > 0)
        {
            int k = -scrollBy(j, recycler, state);
            j = k;
            if (flag)
            {
                i = (i + k) - mOrientationHelper.getStartAfterPadding();
                j = k;
                if (i > 0)
                {
                    mOrientationHelper.offsetChildren(-i);
                    j = k - i;
                }
            }
            return j;
        } else
        {
            return 0;
        }
    }

    private final View getChildClosestToEnd()
    {
        boolean flag = false;
        int i = 0;
        if (!mShouldReverseLayout)
        {
            i = ((flag) ? 1 : 0);
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper1 = super.mChildHelper;
                i = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
            }
            i--;
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.getOffset(i);
            return childhelper.mCallback.getChildAt(i);
        } else
        {
            return null;
        }
    }

    private final View getChildClosestToStart()
    {
        int i = 0;
        boolean flag = false;
        if (mShouldReverseLayout)
        {
            i = ((flag) ? 1 : 0);
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper = super.mChildHelper;
                i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            }
            i--;
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper1 = super.mChildHelper;
            i = childhelper1.getOffset(i);
            return childhelper1.mCallback.getChildAt(i);
        } else
        {
            return null;
        }
    }

    private final void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutstate)
    {
        if (layoutstate.mRecycle && !layoutstate.mInfinite) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (layoutstate.mLayoutDirection != -1)
        {
            break; /* Loop/switch isn't completed */
        }
        int k = layoutstate.mScrollingOffset;
        int i;
        if (super.mChildHelper != null)
        {
            layoutstate = super.mChildHelper;
            i = ((ChildHelper) (layoutstate)).mCallback.getChildCount() - ((ChildHelper) (layoutstate)).mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (k >= 0)
        {
            int l1 = mOrientationHelper.getEnd() - k;
            if (mShouldReverseLayout)
            {
                int l = 0;
                while (l < i) 
                {
                    if (super.mChildHelper != null)
                    {
                        layoutstate = super.mChildHelper;
                        int j2 = layoutstate.getOffset(l);
                        layoutstate = ((ChildHelper) (layoutstate)).mCallback.getChildAt(j2);
                    } else
                    {
                        layoutstate = null;
                    }
                    if (mOrientationHelper.getDecoratedStart(layoutstate) < l1 || mOrientationHelper.getTransformedStartWithDecoration(layoutstate) < l1)
                    {
                        recycleChildren(recycler, 0, l);
                        return;
                    }
                    l++;
                }
            } else
            {
                int i1 = i - 1;
                while (i1 >= 0) 
                {
                    if (super.mChildHelper != null)
                    {
                        layoutstate = super.mChildHelper;
                        int k2 = layoutstate.getOffset(i1);
                        layoutstate = ((ChildHelper) (layoutstate)).mCallback.getChildAt(k2);
                    } else
                    {
                        layoutstate = null;
                    }
                    if (mOrientationHelper.getDecoratedStart(layoutstate) < l1 || mOrientationHelper.getTransformedStartWithDecoration(layoutstate) < l1)
                    {
                        recycleChildren(recycler, i - 1, i1);
                        return;
                    }
                    i1--;
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j;
        int i2;
        i2 = layoutstate.mScrollingOffset;
        if (i2 < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j1;
        if (super.mChildHelper != null)
        {
            layoutstate = super.mChildHelper;
            j = ((ChildHelper) (layoutstate)).mCallback.getChildCount() - ((ChildHelper) (layoutstate)).mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (!mShouldReverseLayout)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = j - 1;
        while (j1 >= 0) 
        {
            if (super.mChildHelper != null)
            {
                layoutstate = super.mChildHelper;
                int l2 = layoutstate.getOffset(j1);
                layoutstate = ((ChildHelper) (layoutstate)).mCallback.getChildAt(l2);
            } else
            {
                layoutstate = null;
            }
            if (mOrientationHelper.getDecoratedEnd(layoutstate) > i2 || mOrientationHelper.getTransformedEndWithDecoration(layoutstate) > i2)
            {
                recycleChildren(recycler, j - 1, j1);
                return;
            }
            j1--;
        }
        if (true) goto _L1; else goto _L4
_L4:
        int k1 = 0;
        while (k1 < j) 
        {
            if (super.mChildHelper != null)
            {
                layoutstate = super.mChildHelper;
                int i3 = layoutstate.getOffset(k1);
                layoutstate = ((ChildHelper) (layoutstate)).mCallback.getChildAt(i3);
            } else
            {
                layoutstate = null;
            }
            if (mOrientationHelper.getDecoratedEnd(layoutstate) > i2 || mOrientationHelper.getTransformedEndWithDecoration(layoutstate) > i2)
            {
                recycleChildren(recycler, 0, k1);
                return;
            }
            k1++;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    private final void recycleChildren(RecyclerView.Recycler recycler, int i, int j)
    {
        int k;
        if (i != j)
        {
            k = i;
            if (j <= i)
            {
                continue; /* Loop/switch isn't completed */
            }
            j--;
            while (j >= i) 
            {
                Object obj;
                Object obj2;
                if (super.mChildHelper != null)
                {
                    obj = super.mChildHelper;
                    k = ((ChildHelper) (obj)).getOffset(j);
                    obj = ((ChildHelper) (obj)).mCallback.getChildAt(k);
                } else
                {
                    obj = null;
                }
                if (super.mChildHelper != null)
                {
                    obj2 = super.mChildHelper;
                    k = ((ChildHelper) (obj2)).getOffset(j);
                    obj2 = ((ChildHelper) (obj2)).mCallback.getChildAt(k);
                } else
                {
                    obj2 = null;
                }
                if (obj2 != null)
                {
                    obj2 = super.mChildHelper;
                    k = ((ChildHelper) (obj2)).getOffset(j);
                    View view = ((ChildHelper) (obj2)).mCallback.getChildAt(k);
                    if (view != null)
                    {
                        if (((ChildHelper) (obj2)).mBucket.remove(k) && ((ChildHelper) (obj2)).mHiddenViews.remove(view))
                        {
                            ((ChildHelper) (obj2)).mCallback.onLeftHiddenState(view);
                        }
                        ((ChildHelper) (obj2)).mCallback.removeViewAt(k);
                    }
                }
                recycler.recycleView(((View) (obj)));
                j--;
            }
        }
_L2:
        return;
_L4:
        Object obj3 = null;
_L5:
        Object obj1;
        if (obj3 != null)
        {
            obj3 = super.mChildHelper;
            i = ((ChildHelper) (obj3)).getOffset(k);
            View view1 = ((ChildHelper) (obj3)).mCallback.getChildAt(i);
            if (view1 != null)
            {
                if (((ChildHelper) (obj3)).mBucket.remove(i) && ((ChildHelper) (obj3)).mHiddenViews.remove(view1))
                {
                    ((ChildHelper) (obj3)).mCallback.onLeftHiddenState(view1);
                }
                ((ChildHelper) (obj3)).mCallback.removeViewAt(i);
            }
        }
        recycler.recycleView(((View) (obj1)));
        k--;
        if (k <= j) goto _L2; else goto _L1
_L1:
        if (super.mChildHelper != null)
        {
            obj1 = super.mChildHelper;
            i = ((ChildHelper) (obj1)).getOffset(k);
            obj1 = ((ChildHelper) (obj1)).mCallback.getChildAt(i);
        } else
        {
            obj1 = null;
        }
        if (super.mChildHelper == null) goto _L4; else goto _L3
_L3:
        obj3 = super.mChildHelper;
        i = ((ChildHelper) (obj3)).getOffset(k);
        obj3 = ((ChildHelper) (obj3)).mCallback.getChildAt(i);
          goto _L5
    }

    private final void resolveShouldLayoutReverse()
    {
        boolean flag1 = true;
        if (mOrientation == 1) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (ViewCompat.getLayoutDirection(super.mRecyclerView) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        flag1 = mReverseLayout;
_L5:
        mShouldReverseLayout = flag1;
        return;
_L3:
        if (mReverseLayout)
        {
            flag1 = false;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        int j;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j == 0 || i == 0)
        {
            return 0;
        }
        mLayoutState.mRecycle = true;
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        int k;
        int l;
        if (i > 0)
        {
            j = 1;
        } else
        {
            j = -1;
        }
        k = Math.abs(i);
        updateLayoutState(j, k, true, state);
        l = mLayoutState.mScrollingOffset + fill(recycler, mLayoutState, state, false);
        if (l < 0)
        {
            return 0;
        }
        if (k > l)
        {
            i = j * l;
        }
        mOrientationHelper.offsetChildren(-i);
        mLayoutState.mLastScrollDelta = i;
        return i;
    }

    private final void setOrientation(int i)
    {
        if (i != 0 && i != 1)
        {
            throw new IllegalArgumentException((new StringBuilder("invalid orientation:")).append(i).toString());
        }
        assertNotInLayoutOrScroll(null);
        if (i == mOrientation && mOrientationHelper != null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 1: default 76
    //                   0 87
    //                   1 132;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalArgumentException("invalid orientation");
_L4:
        Object obj = new OrientationHelper._cls1(this);
_L7:
        mOrientationHelper = ((OrientationHelper) (obj));
        mAnchorInfo.mOrientationHelper = mOrientationHelper;
        mOrientation = i;
        if (super.mRecyclerView != null)
        {
            super.mRecyclerView.requestLayout();
        }
_L2:
        return;
_L5:
        obj = new OrientationHelper._cls2(this);
        if (true) goto _L7; else goto _L6
_L6:
    }

    private final void updateLayoutState(int i, int j, boolean flag, RecyclerView.State state)
    {
        LayoutState layoutstate = null;
        boolean flag2 = true;
        boolean flag1 = true;
        boolean flag5 = false;
        boolean flag3 = false;
        boolean flag6 = false;
        boolean flag4 = false;
        LayoutState layoutstate1 = mLayoutState;
        int k;
        boolean flag7;
        if (mOrientationHelper.getMode() == 0 && mOrientationHelper.getEnd() == 0)
        {
            flag7 = true;
        } else
        {
            flag7 = false;
        }
        layoutstate1.mInfinite = flag7;
        layoutstate1 = mLayoutState;
        if (state.mTargetPosition != -1)
        {
            k = mOrientationHelper.getTotalSpace();
        } else
        {
            k = 0;
        }
        layoutstate1.mExtra = k;
        mLayoutState.mLayoutDirection = i;
        if (i == 1)
        {
            state = mLayoutState;
            state.mExtra = ((LayoutState) (state)).mExtra + mOrientationHelper.getEndPadding();
            RecyclerView.ViewHolder viewholder;
            if (mShouldReverseLayout)
            {
                i = ((flag4) ? 1 : 0);
            } else
            {
                i = ((flag5) ? 1 : 0);
                if (super.mChildHelper != null)
                {
                    state = super.mChildHelper;
                    i = ((ChildHelper) (state)).mCallback.getChildCount() - ((ChildHelper) (state)).mHiddenViews.size();
                }
                i--;
            }
            if (super.mChildHelper != null)
            {
                state = super.mChildHelper;
                i = state.getOffset(i);
                state = ((ChildHelper) (state)).mCallback.getChildAt(i);
            } else
            {
                state = null;
            }
            layoutstate = mLayoutState;
            i = ((flag1) ? 1 : 0);
            if (mShouldReverseLayout)
            {
                i = -1;
            }
            layoutstate.mItemDirection = i;
            layoutstate = mLayoutState;
            viewholder = ((RecyclerView.LayoutParams)state.getLayoutParams()).mViewHolder;
            if (viewholder.mPreLayoutPosition == -1)
            {
                i = viewholder.mPosition;
            } else
            {
                i = viewholder.mPreLayoutPosition;
            }
            layoutstate.mCurrentPosition = i + mLayoutState.mItemDirection;
            mLayoutState.mOffset = mOrientationHelper.getDecoratedEnd(state);
            i = mOrientationHelper.getDecoratedEnd(state) - mOrientationHelper.getEndAfterPadding();
        } else
        {
            i = ((flag6) ? 1 : 0);
            if (mShouldReverseLayout)
            {
                i = ((flag3) ? 1 : 0);
                if (super.mChildHelper != null)
                {
                    state = super.mChildHelper;
                    i = ((ChildHelper) (state)).mCallback.getChildCount() - ((ChildHelper) (state)).mHiddenViews.size();
                }
                i--;
            }
            state = layoutstate;
            if (super.mChildHelper != null)
            {
                state = super.mChildHelper;
                i = state.getOffset(i);
                state = ((ChildHelper) (state)).mCallback.getChildAt(i);
            }
            layoutstate = mLayoutState;
            layoutstate.mExtra = layoutstate.mExtra + mOrientationHelper.getStartAfterPadding();
            layoutstate = mLayoutState;
            RecyclerView.ViewHolder viewholder1;
            if (mShouldReverseLayout)
            {
                i = ((flag2) ? 1 : 0);
            } else
            {
                i = -1;
            }
            layoutstate.mItemDirection = i;
            layoutstate = mLayoutState;
            viewholder1 = ((RecyclerView.LayoutParams)state.getLayoutParams()).mViewHolder;
            if (viewholder1.mPreLayoutPosition == -1)
            {
                i = viewholder1.mPosition;
            } else
            {
                i = viewholder1.mPreLayoutPosition;
            }
            layoutstate.mCurrentPosition = i + mLayoutState.mItemDirection;
            mLayoutState.mOffset = mOrientationHelper.getDecoratedStart(state);
            i = -mOrientationHelper.getDecoratedStart(state) + mOrientationHelper.getStartAfterPadding();
        }
        mLayoutState.mAvailable = j;
        if (flag)
        {
            state = mLayoutState;
            state.mAvailable = ((LayoutState) (state)).mAvailable - i;
        }
        mLayoutState.mScrollingOffset = i;
    }

    private final void updateLayoutStateToFillEnd(int i, int j)
    {
        mLayoutState.mAvailable = mOrientationHelper.getEndAfterPadding() - j;
        LayoutState layoutstate = mLayoutState;
        int k;
        if (mShouldReverseLayout)
        {
            k = -1;
        } else
        {
            k = 1;
        }
        layoutstate.mItemDirection = k;
        mLayoutState.mCurrentPosition = i;
        mLayoutState.mLayoutDirection = 1;
        mLayoutState.mOffset = j;
        mLayoutState.mScrollingOffset = 0x80000000;
    }

    private final void updateLayoutStateToFillStart(int i, int j)
    {
        mLayoutState.mAvailable = j - mOrientationHelper.getStartAfterPadding();
        mLayoutState.mCurrentPosition = i;
        LayoutState layoutstate = mLayoutState;
        if (mShouldReverseLayout)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        layoutstate.mItemDirection = i;
        mLayoutState.mLayoutDirection = -1;
        mLayoutState.mOffset = j;
        mLayoutState.mScrollingOffset = 0x80000000;
    }

    public final void assertNotInLayoutOrScroll(String s)
    {
        if (mPendingSavedState == null)
        {
            super.assertNotInLayoutOrScroll(s);
        }
    }

    public final boolean canScrollHorizontally()
    {
        return mOrientation == 0;
    }

    public final boolean canScrollVertically()
    {
        return mOrientation == 1;
    }

    public final void collectAdjacentPrefetchPositions(int i, int j, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        if (mOrientation != 0)
        {
            i = j;
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j != 0 && i != 0)
        {
            if (mLayoutState == null)
            {
                mLayoutState = new LayoutState();
            }
            LayoutState layoutstate;
            if (i > 0)
            {
                j = 1;
            } else
            {
                j = -1;
            }
            updateLayoutState(j, Math.abs(i), true, state);
            layoutstate = mLayoutState;
            j = layoutstate.mCurrentPosition;
            if (j >= 0)
            {
                if (state.mInPreLayout)
                {
                    i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    i = state.mItemCount;
                }
                if (j < i)
                {
                    layoutprefetchregistry.addPosition(j, Math.max(0, layoutstate.mScrollingOffset));
                    return;
                }
            }
        }
    }

    public final void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        int j;
        byte byte0;
        boolean flag1;
        if (mPendingSavedState == null)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        int k;
        boolean flag;
        if (mPendingSavedState.mAnchorPosition >= 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        flag1 = mPendingSavedState.mAnchorLayoutFromEnd;
        j = mPendingSavedState.mAnchorPosition;
_L1:
        if (flag1)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        flag = false;
        k = j;
        for (j = ((flag) ? 1 : 0); j < mInitialPrefetchItemCount && k >= 0 && k < i; j++)
        {
            layoutprefetchregistry.addPosition(k, 0);
            k += byte0;
        }

        break MISSING_BLOCK_LABEL_153;
        resolveShouldLayoutReverse();
        flag1 = mShouldReverseLayout;
        if (mPendingScrollPosition == -1)
        {
            if (flag1)
            {
                j = i - 1;
            } else
            {
                j = 0;
            }
        } else
        {
            j = mPendingScrollPosition;
        }
          goto _L1
    }

    public final int computeHorizontalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public final int computeHorizontalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public final int computeHorizontalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    public final int computeVerticalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public final int computeVerticalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public final int computeVerticalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    public final int findFirstVisibleItemPosition()
    {
        Object obj;
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        obj = findOneVisibleChild(0, i, false, true);
        if (obj == null)
        {
            return -1;
        }
        obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
        {
            return ((RecyclerView.ViewHolder) (obj)).mPosition;
        } else
        {
            return ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
        }
    }

    public final int findLastVisibleItemPosition()
    {
        Object obj;
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        obj = findOneVisibleChild(i - 1, -1, false, true);
        if (obj == null)
        {
            return -1;
        }
        obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
        {
            return ((RecyclerView.ViewHolder) (obj)).mPosition;
        } else
        {
            return ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
        }
    }

    public final View findViewByPosition(int i)
    {
        Object obj;
        RecyclerView.ViewHolder viewholder;
        int j;
        viewholder = null;
        obj = null;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L2; else goto _L1
_L1:
        return ((View) (obj));
_L2:
        int l;
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            int k = ((ChildHelper) (obj)).getOffset(0);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(k);
        } else
        {
            obj = null;
        }
        obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
        {
            l = ((RecyclerView.ViewHolder) (obj)).mPosition;
        } else
        {
            l = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
        }
        l = i - l;
        if (l < 0 || l >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = viewholder;
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            j = ((ChildHelper) (obj)).getOffset(l);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
        }
        viewholder = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (viewholder.mPreLayoutPosition == -1)
        {
            j = viewholder.mPosition;
        } else
        {
            j = viewholder.mPreLayoutPosition;
        }
        if (j == i) goto _L1; else goto _L3
_L3:
        return super.findViewByPosition(i);
    }

    public final RecyclerView.LayoutParams generateDefaultLayoutParams()
    {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public final boolean isAutoMeasureEnabled()
    {
        return true;
    }

    public final View onFocusSearchFailed$51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357CKKOOBECHP6UQB45TR6IPBN5TB6IPBN7C______0(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        int j1;
        boolean flag;
        boolean flag2;
        j1 = 0;
        flag2 = false;
        flag = false;
        resolveShouldLayoutReverse();
        int j;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L2; else goto _L1
_L1:
        recycler = null;
_L13:
        return recycler;
_L2:
        i;
        JVM INSTR lookupswitch 6: default 124
    //                   1: 135
    //                   2: 169
    //                   17: 241
    //                   33: 203
    //                   66: 259
    //                   130: 222;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        i = 0x80000000;
_L11:
        if (i == 0x80000000)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (mOrientation == 1)
        {
            i = -1;
        } else
        if (ViewCompat.getLayoutDirection(super.mRecyclerView) == 1)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (mOrientation == 1)
        {
            i = 1;
        } else
        if (ViewCompat.getLayoutDirection(super.mRecyclerView) == 1)
        {
            i = -1;
        } else
        {
            i = 1;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (mOrientation == 1)
        {
            i = -1;
        } else
        {
            i = 0x80000000;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (mOrientation == 1)
        {
            i = 1;
        } else
        {
            i = 0x80000000;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (mOrientation == 0)
        {
            i = -1;
        } else
        {
            i = 0x80000000;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (mOrientation == 0)
        {
            i = 1;
        } else
        {
            i = 0x80000000;
        }
        if (true) goto _L11; else goto _L10
_L10:
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        updateLayoutState(i, (int)(0.3333333F * (float)mOrientationHelper.getTotalSpace()), false, state);
        mLayoutState.mScrollingOffset = 0x80000000;
        mLayoutState.mRecycle = false;
        fill(recycler, mLayoutState, state, true);
        if (i == -1)
        {
            int k;
            if (mShouldReverseLayout)
            {
                k = ((flag) ? 1 : 0);
                if (super.mChildHelper != null)
                {
                    recycler = super.mChildHelper;
                    k = ((ChildHelper) (recycler)).mCallback.getChildCount() - ((ChildHelper) (recycler)).mHiddenViews.size();
                }
                j1 = k - 1;
                k = -1;
            } else
            if (super.mChildHelper != null)
            {
                recycler = super.mChildHelper;
                k = ((ChildHelper) (recycler)).mCallback.getChildCount() - ((ChildHelper) (recycler)).mHiddenViews.size();
                j1 = 0;
            } else
            {
                boolean flag1 = false;
                k = j1;
                j1 = ((flag1) ? 1 : 0);
            }
            state = findOnePartiallyOrCompletelyInvisibleChild(j1, k);
        } else
        {
            if (mShouldReverseLayout)
            {
                int l;
                if (super.mChildHelper != null)
                {
                    recycler = super.mChildHelper;
                    l = ((ChildHelper) (recycler)).mCallback.getChildCount() - ((ChildHelper) (recycler)).mHiddenViews.size();
                } else
                {
                    l = 0;
                }
                recycler = findOnePartiallyOrCompletelyInvisibleChild(0, l);
            } else
            {
                int i1 = ((flag2) ? 1 : 0);
                if (super.mChildHelper != null)
                {
                    recycler = super.mChildHelper;
                    i1 = ((ChildHelper) (recycler)).mCallback.getChildCount() - ((ChildHelper) (recycler)).mHiddenViews.size();
                }
                recycler = findOnePartiallyOrCompletelyInvisibleChild(i1 - 1, -1);
            }
            state = recycler;
        }
        if (i == -1)
        {
            recycler = getChildClosestToStart();
        } else
        {
            recycler = getChildClosestToEnd();
        }
        if (recycler.hasFocusable())
        {
            if (state == null)
            {
                return null;
            }
        } else
        {
            return state;
        }
        if (true) goto _L13; else goto _L12
_L12:
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        byte byte0 = -1;
        super.onInitializeAccessibilityEvent(accessibilityevent);
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i > 0)
        {
            Object obj;
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper1 = super.mChildHelper;
                i = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
            } else
            {
                i = 0;
            }
            obj = findOneVisibleChild(0, i, false, true);
            if (obj == null)
            {
                i = -1;
            } else
            {
                obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPosition;
                } else
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
                }
            }
            accessibilityevent.setFromIndex(i);
            if (super.mChildHelper != null)
            {
                obj = super.mChildHelper;
                i = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
            } else
            {
                i = 0;
            }
            obj = findOneVisibleChild(i - 1, -1, false, true);
            if (obj == null)
            {
                i = byte0;
            } else
            {
                obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPosition;
                } else
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
                }
            }
            accessibilityevent.setToIndex(i);
        }
    }

    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        Object obj;
        Object obj4;
        Object obj5;
        int j;
        int k;
        int l;
        int k1;
        if (mPendingSavedState != null || mPendingScrollPosition != -1)
        {
            int i;
            if (state.mInPreLayout)
            {
                i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
            } else
            {
                i = state.mItemCount;
            }
            if (i == 0)
            {
                removeAndRecycleAllViews(recycler);
                return;
            }
        }
        if (mPendingSavedState != null)
        {
            if (mPendingSavedState.mAnchorPosition >= 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                mPendingScrollPosition = mPendingSavedState.mAnchorPosition;
            }
        }
        if (mLayoutState == null)
        {
            mLayoutState = new LayoutState();
        }
        mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        obj = getFocusedChild();
        if (mAnchorInfo.mValid && mPendingScrollPosition == -1 && mPendingSavedState == null) goto _L2; else goto _L1
_L1:
        obj = mAnchorInfo;
        obj.mPosition = -1;
        obj.mCoordinate = 0x80000000;
        obj.mLayoutFromEnd = false;
        obj.mValid = false;
        mAnchorInfo.mLayoutFromEnd = mShouldReverseLayout ^ mStackFromEnd;
        obj4 = mAnchorInfo;
        if (!state.mInPreLayout && mPendingScrollPosition != -1) goto _L4; else goto _L3
_L3:
        j = 0;
_L38:
        if (j != 0) goto _L6; else goto _L5
_L5:
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L8; else goto _L7
_L7:
        obj = getFocusedChild();
        if (obj == null) goto _L10; else goto _L9
_L9:
        obj5 = (RecyclerView.LayoutParams)((View) (obj)).getLayoutParams();
        boolean flag;
        if ((((RecyclerView.LayoutParams) (obj5)).mViewHolder.mFlags & 8) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L12; else goto _L11
_L11:
        RecyclerView.ViewHolder viewholder = ((RecyclerView.LayoutParams) (obj5)).mViewHolder;
        if (viewholder.mPreLayoutPosition == -1)
        {
            j = viewholder.mPosition;
        } else
        {
            j = viewholder.mPreLayoutPosition;
        }
        if (j < 0) goto _L12; else goto _L13
_L13:
        obj5 = ((RecyclerView.LayoutParams) (obj5)).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition == -1)
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPosition;
        } else
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition;
        }
        if (state.mInPreLayout)
        {
            k = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            k = state.mItemCount;
        }
        if (j >= k) goto _L12; else goto _L14
_L14:
        j = 1;
_L32:
        if (j == 0) goto _L10; else goto _L15
_L15:
        obj5 = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition == -1)
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPosition;
        } else
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition;
        }
        ((AnchorInfo) (obj4)).assignFromViewAndKeepVisibleRect(((View) (obj)), j);
        j = 1;
_L35:
        if (j == 0)
        {
            if (((AnchorInfo) (obj4)).mLayoutFromEnd)
            {
                j = ((AnchorInfo) (obj4)).mOrientationHelper.getEndAfterPadding();
            } else
            {
                j = ((AnchorInfo) (obj4)).mOrientationHelper.getStartAfterPadding();
            }
            obj4.mCoordinate = j;
            if (mStackFromEnd)
            {
                if (state.mInPreLayout)
                {
                    j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    j = state.mItemCount;
                }
                j--;
            } else
            {
                j = 0;
            }
            obj4.mPosition = j;
        }
_L6:
        mAnchorInfo.mValid = true;
_L36:
        if (state.mTargetPosition != -1)
        {
            j = mOrientationHelper.getTotalSpace();
        } else
        {
            j = 0;
        }
        if (mLayoutState.mLastScrollDelta >= 0)
        {
            k = 0;
        } else
        {
            k = j;
            j = 0;
        }
        k += mOrientationHelper.getStartAfterPadding();
        j += mOrientationHelper.getEndPadding();
        if (state.mInPreLayout && mPendingScrollPosition != -1 && mPendingScrollPositionOffset != 0x80000000)
        {
            obj = findViewByPosition(mPendingScrollPosition);
            if (obj != null)
            {
                if (mShouldReverseLayout)
                {
                    l = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(((View) (obj))) - mPendingScrollPositionOffset;
                } else
                {
                    l = mOrientationHelper.getDecoratedStart(((View) (obj)));
                    k1 = mOrientationHelper.getStartAfterPadding();
                    l = mPendingScrollPositionOffset - (l - k1);
                }
                if (l > 0)
                {
                    k += l;
                } else
                {
                    j -= l;
                }
            }
        }
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            l = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
        } else
        {
            l = 0;
        }
        l--;
_L23:
        if (l < 0) goto _L17; else goto _L16
_L16:
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            k1 = ((ChildHelper) (obj)).getOffset(l);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(k1);
        } else
        {
            obj = null;
        }
        obj4 = RecyclerView.getChildViewHolderInt(((View) (obj)));
        if ((((RecyclerView.ViewHolder) (obj4)).mFlags & 0x80) != 0)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 != 0) goto _L19; else goto _L18
_L18:
        if ((((RecyclerView.ViewHolder) (obj4)).mFlags & 4) != 0)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 == 0) goto _L21; else goto _L20
_L20:
        if ((((RecyclerView.ViewHolder) (obj4)).mFlags & 8) != 0)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 != 0 || super.mRecyclerView.mAdapter.mHasStableIds) goto _L21; else goto _L22
_L22:
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            k1 = ((ChildHelper) (obj)).getOffset(l);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(k1);
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            obj = super.mChildHelper;
            k1 = ((ChildHelper) (obj)).getOffset(l);
            obj5 = ((ChildHelper) (obj)).mCallback.getChildAt(k1);
            if (obj5 != null)
            {
                if (((ChildHelper) (obj)).mBucket.remove(k1) && ((ChildHelper) (obj)).mHiddenViews.remove(obj5))
                {
                    ((ChildHelper) (obj)).mCallback.onLeftHiddenState(((View) (obj5)));
                }
                ((ChildHelper) (obj)).mCallback.removeViewAt(k1);
            }
        }
        recycler.recycleViewHolderInternal(((RecyclerView.ViewHolder) (obj4)));
_L19:
        l--;
          goto _L23
_L4:
label0:
        {
            if (mPendingScrollPosition >= 0)
            {
                k = mPendingScrollPosition;
                if (state.mInPreLayout)
                {
                    j = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    j = state.mItemCount;
                }
                if (k < j)
                {
                    break label0;
                }
            }
            mPendingScrollPosition = -1;
            mPendingScrollPositionOffset = 0x80000000;
            j = 0;
            continue; /* Loop/switch isn't completed */
        }
        obj4.mPosition = mPendingScrollPosition;
        if (mPendingSavedState != null)
        {
            if (mPendingSavedState.mAnchorPosition >= 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                obj4.mLayoutFromEnd = mPendingSavedState.mAnchorLayoutFromEnd;
                if (((AnchorInfo) (obj4)).mLayoutFromEnd)
                {
                    obj4.mCoordinate = mOrientationHelper.getEndAfterPadding() - mPendingSavedState.mAnchorOffset;
                } else
                {
                    obj4.mCoordinate = mOrientationHelper.getStartAfterPadding() + mPendingSavedState.mAnchorOffset;
                }
                j = 1;
                continue; /* Loop/switch isn't completed */
            }
        }
        if (mPendingScrollPositionOffset != 0x80000000) goto _L25; else goto _L24
_L24:
        obj = findViewByPosition(mPendingScrollPosition);
        if (obj == null) goto _L27; else goto _L26
_L26:
        if (mOrientationHelper.getDecoratedMeasurement(((View) (obj))) <= mOrientationHelper.getTotalSpace()) goto _L29; else goto _L28
_L28:
        if (((AnchorInfo) (obj4)).mLayoutFromEnd)
        {
            j = ((AnchorInfo) (obj4)).mOrientationHelper.getEndAfterPadding();
        } else
        {
            j = ((AnchorInfo) (obj4)).mOrientationHelper.getStartAfterPadding();
        }
        obj4.mCoordinate = j;
_L31:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L29:
        if (mOrientationHelper.getDecoratedStart(((View) (obj))) - mOrientationHelper.getStartAfterPadding() < 0)
        {
            obj4.mCoordinate = mOrientationHelper.getStartAfterPadding();
            obj4.mLayoutFromEnd = false;
            continue; /* Loop/switch isn't completed */
        }
        if (mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(((View) (obj))) < 0)
        {
            obj4.mCoordinate = mOrientationHelper.getEndAfterPadding();
            obj4.mLayoutFromEnd = true;
            continue; /* Loop/switch isn't completed */
        }
        if (((AnchorInfo) (obj4)).mLayoutFromEnd)
        {
            k = mOrientationHelper.getDecoratedEnd(((View) (obj)));
            obj = mOrientationHelper;
            if (0x80000000 == ((OrientationHelper) (obj)).mLastTotalSpace)
            {
                j = 0;
            } else
            {
                j = ((OrientationHelper) (obj)).getTotalSpace() - ((OrientationHelper) (obj)).mLastTotalSpace;
            }
            j += k;
        } else
        {
            j = mOrientationHelper.getDecoratedStart(((View) (obj)));
        }
        obj4.mCoordinate = j;
_L30:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L27:
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
        } else
        {
            j = 0;
        }
        if (j > 0)
        {
            if (super.mChildHelper != null)
            {
                obj = super.mChildHelper;
                j = ((ChildHelper) (obj)).getOffset(0);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
            } else
            {
                obj = null;
            }
            obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
            {
                j = ((RecyclerView.ViewHolder) (obj)).mPosition;
            } else
            {
                j = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
            }
            if (mPendingScrollPosition < j)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag == mShouldReverseLayout)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj4.mLayoutFromEnd = flag;
        }
        if (((AnchorInfo) (obj4)).mLayoutFromEnd)
        {
            j = ((AnchorInfo) (obj4)).mOrientationHelper.getEndAfterPadding();
        } else
        {
            j = ((AnchorInfo) (obj4)).mOrientationHelper.getStartAfterPadding();
        }
        obj4.mCoordinate = j;
        if (true) goto _L30; else goto _L25
_L25:
        obj4.mLayoutFromEnd = mShouldReverseLayout;
        if (mShouldReverseLayout)
        {
            obj4.mCoordinate = mOrientationHelper.getEndAfterPadding() - mPendingScrollPositionOffset;
        } else
        {
            obj4.mCoordinate = mOrientationHelper.getStartAfterPadding() + mPendingScrollPositionOffset;
        }
        if (true) goto _L31; else goto _L12
_L12:
        j = 0;
          goto _L32
_L10:
        if (mLastStackFromEnd != mStackFromEnd) goto _L8; else goto _L33
_L33:
        if (((AnchorInfo) (obj4)).mLayoutFromEnd)
        {
            if (mShouldReverseLayout)
            {
                if (super.mChildHelper != null)
                {
                    obj = super.mChildHelper;
                    j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
                } else
                {
                    j = 0;
                }
                if (state.mInPreLayout)
                {
                    k = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    k = state.mItemCount;
                }
                obj = findReferenceChild$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357D4KII999HGMSP3IDTKM8BRMD5INEBQMD5INEEO_0(0, j, k);
            } else
            {
                if (super.mChildHelper != null)
                {
                    obj = super.mChildHelper;
                    j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
                } else
                {
                    j = 0;
                }
                if (state.mInPreLayout)
                {
                    k = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    k = state.mItemCount;
                }
                obj = findReferenceChild$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357D4KII999HGMSP3IDTKM8BRMD5INEBQMD5INEEO_0(j - 1, -1, k);
            }
        } else
        if (mShouldReverseLayout)
        {
            if (super.mChildHelper != null)
            {
                obj = super.mChildHelper;
                j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
            } else
            {
                j = 0;
            }
            if (state.mInPreLayout)
            {
                k = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
            } else
            {
                k = state.mItemCount;
            }
            obj = findReferenceChild$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357D4KII999HGMSP3IDTKM8BRMD5INEBQMD5INEEO_0(j - 1, -1, k);
        } else
        {
            if (super.mChildHelper != null)
            {
                obj = super.mChildHelper;
                j = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
            } else
            {
                j = 0;
            }
            if (state.mInPreLayout)
            {
                k = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
            } else
            {
                k = state.mItemCount;
            }
            obj = findReferenceChild$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357D4KII999HGMSP3IDTKM8BRMD5INEBQMD5INEEO_0(0, j, k);
        }
        if (obj == null) goto _L8; else goto _L34
_L34:
        obj5 = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        if (((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition == -1)
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPosition;
        } else
        {
            j = ((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition;
        }
        ((AnchorInfo) (obj4)).assignFromView(((View) (obj)), j);
        if (!state.mInPreLayout && supportsPredictiveItemAnimations())
        {
            if (mOrientationHelper.getDecoratedStart(((View) (obj))) >= mOrientationHelper.getEndAfterPadding() || mOrientationHelper.getDecoratedEnd(((View) (obj))) < mOrientationHelper.getStartAfterPadding())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                if (((AnchorInfo) (obj4)).mLayoutFromEnd)
                {
                    j = mOrientationHelper.getEndAfterPadding();
                } else
                {
                    j = mOrientationHelper.getStartAfterPadding();
                }
                obj4.mCoordinate = j;
            }
        }
        j = 1;
          goto _L35
_L8:
        j = 0;
          goto _L35
_L2:
        if (obj != null && (mOrientationHelper.getDecoratedStart(((View) (obj))) >= mOrientationHelper.getEndAfterPadding() || mOrientationHelper.getDecoratedEnd(((View) (obj))) <= mOrientationHelper.getStartAfterPadding()))
        {
            obj4 = mAnchorInfo;
            obj5 = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            if (((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition == -1)
            {
                j = ((RecyclerView.ViewHolder) (obj5)).mPosition;
            } else
            {
                j = ((RecyclerView.ViewHolder) (obj5)).mPreLayoutPosition;
            }
            ((AnchorInfo) (obj4)).assignFromViewAndKeepVisibleRect(((View) (obj)), j);
        }
          goto _L36
_L21:
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper2 = super.mChildHelper;
            int l1 = childhelper2.getOffset(l);
            childhelper2.mCallback.getChildAt(l1);
        }
        ChildHelper childhelper3 = super.mChildHelper;
        int i2 = childhelper3.getOffset(l);
        childhelper3.mBucket.remove(i2);
        childhelper3.mCallback.detachViewFromParent(i2);
        recycler.scrapView(((View) (obj)));
        obj = (ViewInfoStore.InfoRecord)super.mRecyclerView.mViewInfoStore.mLayoutHolderMap.get(obj4);
        if (obj != null)
        {
            obj.flags = ((ViewInfoStore.InfoRecord) (obj)).flags & -2;
        }
          goto _L19
_L17:
        LayoutState layoutstate = mLayoutState;
        int j1;
        int k2;
        boolean flag1;
        if (mOrientationHelper.getMode() == 0 && mOrientationHelper.getEnd() == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        layoutstate.mInfinite = flag1;
        if (mAnchorInfo.mLayoutFromEnd)
        {
            Object obj1 = mAnchorInfo;
            updateLayoutStateToFillStart(((AnchorInfo) (obj1)).mPosition, ((AnchorInfo) (obj1)).mCoordinate);
            mLayoutState.mExtra = k;
            fill(recycler, mLayoutState, state, false);
            int i1 = mLayoutState.mOffset;
            int j2 = mLayoutState.mCurrentPosition;
            k = j;
            if (mLayoutState.mAvailable > 0)
            {
                k = j + mLayoutState.mAvailable;
            }
            obj1 = mAnchorInfo;
            updateLayoutStateToFillEnd(((AnchorInfo) (obj1)).mPosition, ((AnchorInfo) (obj1)).mCoordinate);
            mLayoutState.mExtra = k;
            obj1 = mLayoutState;
            obj1.mCurrentPosition = ((LayoutState) (obj1)).mCurrentPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state, false);
            k = mLayoutState.mOffset;
            j = i1;
            if (mLayoutState.mAvailable > 0)
            {
                j = mLayoutState.mAvailable;
                updateLayoutStateToFillStart(j2, i1);
                mLayoutState.mExtra = j;
                fill(recycler, mLayoutState, state, false);
                j = mLayoutState.mOffset;
            }
            i1 = k;
            k = j;
            j = i1;
        } else
        {
            Object obj2 = mAnchorInfo;
            updateLayoutStateToFillEnd(((AnchorInfo) (obj2)).mPosition, ((AnchorInfo) (obj2)).mCoordinate);
            mLayoutState.mExtra = j;
            fill(recycler, mLayoutState, state, false);
            j1 = mLayoutState.mOffset;
            int l2 = mLayoutState.mCurrentPosition;
            j = k;
            if (mLayoutState.mAvailable > 0)
            {
                j = k + mLayoutState.mAvailable;
            }
            obj2 = mAnchorInfo;
            updateLayoutStateToFillStart(((AnchorInfo) (obj2)).mPosition, ((AnchorInfo) (obj2)).mCoordinate);
            mLayoutState.mExtra = j;
            obj2 = mLayoutState;
            obj2.mCurrentPosition = ((LayoutState) (obj2)).mCurrentPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state, false);
            k2 = mLayoutState.mOffset;
            j = j1;
            k = k2;
            if (mLayoutState.mAvailable > 0)
            {
                j = mLayoutState.mAvailable;
                updateLayoutStateToFillEnd(l2, j1);
                mLayoutState.mExtra = j;
                fill(recycler, mLayoutState, state, false);
                j = mLayoutState.mOffset;
                k = k2;
            }
        }
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            j1 = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            j1 = 0;
        }
        if (j1 > 0)
        {
            if (mShouldReverseLayout ^ mStackFromEnd)
            {
                j1 = fixLayoutEndGap(j, recycler, state, true);
                k2 = k + j1;
                k = fixLayoutStartGap(k2, recycler, state, false);
                k2 += k;
                j1 = j + j1 + k;
            } else
            {
                j1 = fixLayoutStartGap(k, recycler, state, true);
                j += j1;
                int i3 = fixLayoutEndGap(j, recycler, state, false);
                k2 = k + j1 + i3;
                j1 = j + i3;
            }
        } else
        {
            j1 = j;
            k2 = k;
        }
        if (state.mRunPredictiveAnimations)
        {
            if (super.mChildHelper != null)
            {
                ChildHelper childhelper1 = super.mChildHelper;
                j = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
            } else
            {
                j = 0;
            }
            if (j != 0 && !state.mInPreLayout && supportsPredictiveItemAnimations())
            {
                k = 0;
                j = 0;
                List list = recycler.mUnmodifiableAttachedScrap;
                int k4 = list.size();
                Object obj3;
                int k3;
                int i4;
                if (super.mChildHelper != null)
                {
                    obj3 = super.mChildHelper;
                    int j3 = ((ChildHelper) (obj3)).getOffset(0);
                    obj3 = ((ChildHelper) (obj3)).mCallback.getChildAt(j3);
                } else
                {
                    obj3 = null;
                }
                obj3 = ((RecyclerView.LayoutParams)((View) (obj3)).getLayoutParams()).mViewHolder;
                if (((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition == -1)
                {
                    k3 = ((RecyclerView.ViewHolder) (obj3)).mPosition;
                } else
                {
                    k3 = ((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition;
                }
                i4 = 0;
                while (i4 < k4) 
                {
                    obj3 = (RecyclerView.ViewHolder)list.get(i4);
                    int j4;
                    if ((((RecyclerView.ViewHolder) (obj3)).mFlags & 8) != 0)
                    {
                        j4 = 1;
                    } else
                    {
                        j4 = 0;
                    }
                    if (j4 == 0)
                    {
                        boolean flag2;
                        if (((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition == -1)
                        {
                            j4 = ((RecyclerView.ViewHolder) (obj3)).mPosition;
                        } else
                        {
                            j4 = ((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition;
                        }
                        if (j4 < k3)
                        {
                            flag2 = true;
                        } else
                        {
                            flag2 = false;
                        }
                        if (flag2 != mShouldReverseLayout)
                        {
                            j4 = -1;
                        } else
                        {
                            j4 = 1;
                        }
                        if (j4 == -1)
                        {
                            k = mOrientationHelper.getDecoratedMeasurement(((RecyclerView.ViewHolder) (obj3)).itemView) + k;
                        } else
                        {
                            j = mOrientationHelper.getDecoratedMeasurement(((RecyclerView.ViewHolder) (obj3)).itemView) + j;
                        }
                    }
                    i4++;
                }
                mLayoutState.mScrapList = list;
                if (k > 0)
                {
                    int l3;
                    if (mShouldReverseLayout)
                    {
                        if (super.mChildHelper != null)
                        {
                            obj3 = super.mChildHelper;
                            l3 = ((ChildHelper) (obj3)).mCallback.getChildCount() - ((ChildHelper) (obj3)).mHiddenViews.size();
                        } else
                        {
                            l3 = 0;
                        }
                        l3--;
                    } else
                    {
                        l3 = 0;
                    }
                    if (super.mChildHelper != null)
                    {
                        obj3 = super.mChildHelper;
                        l3 = ((ChildHelper) (obj3)).getOffset(l3);
                        obj3 = ((ChildHelper) (obj3)).mCallback.getChildAt(l3);
                    } else
                    {
                        obj3 = null;
                    }
                    obj3 = ((RecyclerView.LayoutParams)((View) (obj3)).getLayoutParams()).mViewHolder;
                    if (((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition == -1)
                    {
                        l3 = ((RecyclerView.ViewHolder) (obj3)).mPosition;
                    } else
                    {
                        l3 = ((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition;
                    }
                    updateLayoutStateToFillStart(l3, k2);
                    mLayoutState.mExtra = k;
                    mLayoutState.mAvailable = 0;
                    mLayoutState.assignPositionFromScrapList(null);
                    fill(recycler, mLayoutState, state, false);
                }
                if (j > 0)
                {
                    if (mShouldReverseLayout)
                    {
                        k = 0;
                    } else
                    {
                        if (super.mChildHelper != null)
                        {
                            obj3 = super.mChildHelper;
                            k = ((ChildHelper) (obj3)).mCallback.getChildCount() - ((ChildHelper) (obj3)).mHiddenViews.size();
                        } else
                        {
                            k = 0;
                        }
                        k--;
                    }
                    if (super.mChildHelper != null)
                    {
                        obj3 = super.mChildHelper;
                        k = ((ChildHelper) (obj3)).getOffset(k);
                        obj3 = ((ChildHelper) (obj3)).mCallback.getChildAt(k);
                    } else
                    {
                        obj3 = null;
                    }
                    obj3 = ((RecyclerView.LayoutParams)((View) (obj3)).getLayoutParams()).mViewHolder;
                    if (((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition == -1)
                    {
                        k = ((RecyclerView.ViewHolder) (obj3)).mPosition;
                    } else
                    {
                        k = ((RecyclerView.ViewHolder) (obj3)).mPreLayoutPosition;
                    }
                    updateLayoutStateToFillEnd(k, j1);
                    mLayoutState.mExtra = j;
                    mLayoutState.mAvailable = 0;
                    mLayoutState.assignPositionFromScrapList(null);
                    fill(recycler, mLayoutState, state, false);
                }
                mLayoutState.mScrapList = null;
            }
        }
        if (!state.mInPreLayout)
        {
            recycler = mOrientationHelper;
            recycler.mLastTotalSpace = recycler.getTotalSpace();
        } else
        {
            recycler = mAnchorInfo;
            recycler.mPosition = -1;
            recycler.mCoordinate = 0x80000000;
            recycler.mLayoutFromEnd = false;
            recycler.mValid = false;
        }
        mLastStackFromEnd = mStackFromEnd;
        return;
        if (true) goto _L38; else goto _L37
_L37:
    }

    public final void onLayoutCompleted(RecyclerView.State state)
    {
        super.onLayoutCompleted(state);
        mPendingSavedState = null;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        state = mAnchorInfo;
        state.mPosition = -1;
        state.mCoordinate = 0x80000000;
        state.mLayoutFromEnd = false;
        state.mValid = false;
    }

    public final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable instanceof SavedState)
        {
            mPendingSavedState = (SavedState)parcelable;
            if (super.mRecyclerView != null)
            {
                super.mRecyclerView.requestLayout();
            }
        }
    }

    public final Parcelable onSaveInstanceState()
    {
        Object obj = null;
        boolean flag2 = false;
        boolean flag = false;
        boolean flag3 = false;
        boolean flag1 = false;
        if (mPendingSavedState != null)
        {
            return new SavedState(mPendingSavedState);
        }
        SavedState savedstate = new SavedState();
        int i;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        if (i > 0)
        {
            if (mLayoutState == null)
            {
                mLayoutState = new LayoutState();
            }
            boolean flag4 = mLastStackFromEnd ^ mShouldReverseLayout;
            savedstate.mAnchorLayoutFromEnd = flag4;
            if (flag4)
            {
                if (mShouldReverseLayout)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = ((flag2) ? 1 : 0);
                    if (super.mChildHelper != null)
                    {
                        obj = super.mChildHelper;
                        i = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size();
                    }
                    i--;
                }
                if (super.mChildHelper != null)
                {
                    obj = super.mChildHelper;
                    i = ((ChildHelper) (obj)).getOffset(i);
                    obj = ((ChildHelper) (obj)).mCallback.getChildAt(i);
                } else
                {
                    obj = null;
                }
                savedstate.mAnchorOffset = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(((View) (obj)));
                obj = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPosition;
                } else
                {
                    i = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
                }
                savedstate.mAnchorPosition = i;
            } else
            {
                int j = ((flag3) ? 1 : 0);
                if (mShouldReverseLayout)
                {
                    j = ((flag) ? 1 : 0);
                    if (super.mChildHelper != null)
                    {
                        ChildHelper childhelper1 = super.mChildHelper;
                        j = childhelper1.mCallback.getChildCount() - childhelper1.mHiddenViews.size();
                    }
                    j--;
                }
                if (super.mChildHelper != null)
                {
                    obj = super.mChildHelper;
                    j = ((ChildHelper) (obj)).getOffset(j);
                    obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
                }
                RecyclerView.ViewHolder viewholder = ((RecyclerView.LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                if (viewholder.mPreLayoutPosition == -1)
                {
                    j = viewholder.mPosition;
                } else
                {
                    j = viewholder.mPreLayoutPosition;
                }
                savedstate.mAnchorPosition = j;
                savedstate.mAnchorOffset = mOrientationHelper.getDecoratedStart(((View) (obj))) - mOrientationHelper.getStartAfterPadding();
            }
        } else
        {
            savedstate.mAnchorPosition = -1;
        }
        return savedstate;
    }

    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 1)
        {
            return 0;
        } else
        {
            return scrollBy(i, recycler, state);
        }
    }

    public final void scrollToPosition(int i)
    {
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = 0x80000000;
        if (mPendingSavedState != null)
        {
            mPendingSavedState.mAnchorPosition = -1;
        }
        if (super.mRecyclerView != null)
        {
            super.mRecyclerView.requestLayout();
        }
    }

    public final void scrollToPositionWithOffset(int i, int j)
    {
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = j;
        if (mPendingSavedState != null)
        {
            mPendingSavedState.mAnchorPosition = -1;
        }
        if (super.mRecyclerView != null)
        {
            super.mRecyclerView.requestLayout();
        }
    }

    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 0)
        {
            return 0;
        } else
        {
            return scrollBy(i, recycler, state);
        }
    }

    final boolean shouldMeasureTwice()
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        flag = flag1;
        if (super.mHeightMode == 0x40000000) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (super.mWidthMode == 0x40000000) goto _L2; else goto _L3
_L3:
        Object obj;
        int i;
        int j;
        int k;
        if (super.mChildHelper != null)
        {
            ChildHelper childhelper = super.mChildHelper;
            i = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        } else
        {
            i = 0;
        }
        j = 0;
_L6:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        if (super.mChildHelper != null)
        {
            obj = super.mChildHelper;
            k = ((ChildHelper) (obj)).getOffset(j);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(k);
        } else
        {
            obj = null;
        }
        obj = ((View) (obj)).getLayoutParams();
        if (((android.view.ViewGroup.LayoutParams) (obj)).width >= 0 || ((android.view.ViewGroup.LayoutParams) (obj)).height >= 0) goto _L5; else goto _L4
_L4:
        i = 1;
_L7:
        flag = flag1;
        if (i != 0)
        {
            flag = true;
        }
_L2:
        return flag;
_L5:
        j++;
          goto _L6
        i = 0;
          goto _L7
    }

    public final boolean supportsPredictiveItemAnimations()
    {
        return mPendingSavedState == null && mLastStackFromEnd == mStackFromEnd;
    }

    private class AnchorInfo
    {

        public int mCoordinate;
        public boolean mLayoutFromEnd;
        public OrientationHelper mOrientationHelper;
        public int mPosition;
        public boolean mValid;

        public final void assignFromView(View view, int i)
        {
            if (mLayoutFromEnd)
            {
                int k = mOrientationHelper.getDecoratedEnd(view);
                view = mOrientationHelper;
                int j;
                if (0x80000000 == ((OrientationHelper) (view)).mLastTotalSpace)
                {
                    j = 0;
                } else
                {
                    j = view.getTotalSpace() - ((OrientationHelper) (view)).mLastTotalSpace;
                }
                mCoordinate = j + k;
            } else
            {
                mCoordinate = mOrientationHelper.getDecoratedStart(view);
            }
            mPosition = i;
        }

        public final void assignFromViewAndKeepVisibleRect(View view, int i)
        {
            int j;
            OrientationHelper orientationhelper = mOrientationHelper;
            if (0x80000000 == orientationhelper.mLastTotalSpace)
            {
                j = 0;
            } else
            {
                j = orientationhelper.getTotalSpace() - orientationhelper.mLastTotalSpace;
            }
            if (j < 0) goto _L2; else goto _L1
_L1:
            assignFromView(view, i);
_L4:
            return;
_L2:
            mPosition = i;
            if (!mLayoutFromEnd)
            {
                break; /* Loop/switch isn't completed */
            }
            i = mOrientationHelper.getEndAfterPadding() - j - mOrientationHelper.getDecoratedEnd(view);
            mCoordinate = mOrientationHelper.getEndAfterPadding() - i;
            if (i > 0)
            {
                j = mOrientationHelper.getDecoratedMeasurement(view);
                int k = mCoordinate;
                int j1 = mOrientationHelper.getStartAfterPadding();
                j = k - j - (Math.min(mOrientationHelper.getDecoratedStart(view) - j1, 0) + j1);
                if (j < 0)
                {
                    int l = mCoordinate;
                    mCoordinate = Math.min(i, -j) + l;
                    return;
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
            int i1 = mOrientationHelper.getDecoratedStart(view);
            i = i1 - mOrientationHelper.getStartAfterPadding();
            mCoordinate = i1;
            if (i > 0)
            {
                int k1 = mOrientationHelper.getDecoratedMeasurement(view);
                int l1 = mOrientationHelper.getEndAfterPadding();
                int i2 = mOrientationHelper.getDecoratedEnd(view);
                j = mOrientationHelper.getEndAfterPadding() - Math.min(0, l1 - j - i2) - (i1 + k1);
                if (j < 0)
                {
                    mCoordinate = mCoordinate - Math.min(i, -j);
                    return;
                }
            }
            if (true) goto _L4; else goto _L5
_L5:
        }

        public final String toString()
        {
            return (new StringBuilder("AnchorInfo{mPosition=")).append(mPosition).append(", mCoordinate=").append(mCoordinate).append(", mLayoutFromEnd=").append(mLayoutFromEnd).append(", mValid=").append(mValid).append('}').toString();
        }

        AnchorInfo()
        {
            mPosition = -1;
            mCoordinate = 0x80000000;
            mLayoutFromEnd = false;
            mValid = false;
        }
    }


    private class LayoutChunkResult
    {

        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult()
        {
        }
    }


    private class LayoutState
    {

        public int mAvailable;
        public int mCurrentPosition;
        public int mExtra;
        public boolean mInfinite;
        public int mItemDirection;
        public int mLastScrollDelta;
        public int mLayoutDirection;
        public int mOffset;
        public boolean mRecycle;
        public List mScrapList;
        public int mScrollingOffset;

        public final void assignPositionFromScrapList(View view)
        {
            int l = mScrapList.size();
            View view1 = null;
            int i = 0x7fffffff;
            int j = 0;
            while (j < l) 
            {
                View view2 = ((RecyclerView.ViewHolder)mScrapList.get(j)).itemView;
                Object obj = (RecyclerView.LayoutParams)view2.getLayoutParams();
                if (view2 == view)
                {
                    continue;
                }
                int k;
                if ((((RecyclerView.LayoutParams) (obj)).mViewHolder.mFlags & 8) != 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    continue;
                }
                obj = ((RecyclerView.LayoutParams) (obj)).mViewHolder;
                if (((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition == -1)
                {
                    k = ((RecyclerView.ViewHolder) (obj)).mPosition;
                } else
                {
                    k = ((RecyclerView.ViewHolder) (obj)).mPreLayoutPosition;
                }
                k = (k - mCurrentPosition) * mItemDirection;
                if (k < 0 || k >= i)
                {
                    continue;
                }
                view1 = view2;
                if (k == 0)
                {
                    break;
                }
                view1 = view2;
                i = k;
                j++;
            }
            if (view1 == null)
            {
                i = -1;
            } else
            {
                view = ((RecyclerView.LayoutParams)view1.getLayoutParams()).mViewHolder;
                if (((RecyclerView.ViewHolder) (view)).mPreLayoutPosition == -1)
                {
                    i = ((RecyclerView.ViewHolder) (view)).mPosition;
                } else
                {
                    i = ((RecyclerView.ViewHolder) (view)).mPreLayoutPosition;
                }
            }
            mCurrentPosition = i;
        }

        LayoutState()
        {
            mRecycle = true;
            mExtra = 0;
            mScrapList = null;
        }
    }


    private class SavedState
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public boolean mAnchorLayoutFromEnd;
        public int mAnchorOffset;
        public int mAnchorPosition;

        public final int describeContents()
        {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mAnchorPosition);
            parcel.writeInt(mAnchorOffset);
            if (mAnchorLayoutFromEnd)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }


        public SavedState()
        {
        }

        SavedState(Parcel parcel)
        {
            boolean flag = true;
            super();
            mAnchorPosition = parcel.readInt();
            mAnchorOffset = parcel.readInt();
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            mAnchorLayoutFromEnd = flag;
        }

        public SavedState(SavedState savedstate)
        {
            mAnchorPosition = savedstate.mAnchorPosition;
            mAnchorOffset = savedstate.mAnchorOffset;
            mAnchorLayoutFromEnd = savedstate.mAnchorLayoutFromEnd;
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }

}
