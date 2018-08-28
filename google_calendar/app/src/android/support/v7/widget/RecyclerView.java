// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            ViewInfoStore, DefaultItemAnimator, AdapterHelper, ChildHelper, 
//            RecyclerViewAccessibilityDelegate, FastScroller, GapWorker, ViewBoundsCheck

public class RecyclerView extends ViewGroup
{
    public static abstract class Adapter
    {

        public boolean mHasStableIds;
        public final AdapterDataObservable mObservable = new AdapterDataObservable();

        public final ViewHolder createViewHolder(ViewGroup viewgroup, int i)
        {
            Trace.beginSection("RV CreateView");
            viewgroup = onCreateViewHolder(viewgroup, i);
            if (((ViewHolder) (viewgroup)).itemView.getParent() != null)
            {
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            }
            break MISSING_BLOCK_LABEL_38;
            viewgroup;
            Trace.endSection();
            throw viewgroup;
            viewgroup.mItemViewType = i;
            Trace.endSection();
            return viewgroup;
        }

        public abstract int getItemCount();

        public long getItemId(int i)
        {
            return -1L;
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerview)
        {
        }

        public abstract void onBindViewHolder(ViewHolder viewholder, int i);

        public void onBindViewHolder(ViewHolder viewholder, int i, List list)
        {
            onBindViewHolder(viewholder, i);
        }

        public abstract ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerview)
        {
        }

        public void onViewAttachedToWindow(ViewHolder viewholder)
        {
        }

        public void onViewDetachedFromWindow(ViewHolder viewholder)
        {
        }

        public void onViewRecycled(ViewHolder viewholder)
        {
        }

        public Adapter()
        {
            mHasStableIds = false;
        }
    }

    public static final class AdapterDataObservable extends Observable
    {

        public final boolean hasObservers()
        {
            return !mObservers.isEmpty();
        }

        public final void notifyChanged()
        {
            for (int i = mObservers.size() - 1; i >= 0; i--)
            {
                ((AdapterDataObserver)mObservers.get(i)).onChanged();
            }

        }

        public final void notifyItemRangeChanged(int i, int j, Object obj)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeChanged(i, j, obj);
            }

        }

        public final void notifyItemRangeInserted(int i, int j)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeInserted(i, j);
            }

        }

        public final void notifyItemRangeRemoved(int i, int j)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeRemoved(i, j);
            }

        }

        AdapterDataObservable()
        {
        }
    }

    public static class AdapterDataObserver
    {

        public void onChanged()
        {
        }

        public void onItemRangeChanged(int i, int j, Object obj)
        {
        }

        public void onItemRangeInserted(int i, int j)
        {
        }

        public void onItemRangeRemoved(int i, int j)
        {
        }

        public AdapterDataObserver()
        {
        }
    }

    public static interface ChildDrawingOrderCallback
    {

        public abstract int onGetChildDrawingOrder(int i, int j);
    }

    public static final class EdgeEffectFactory
    {

        public EdgeEffectFactory()
        {
        }
    }

    public static abstract class ItemAnimator
    {

        public long mAddDuration;
        public long mChangeDuration;
        private ArrayList mFinishedListeners;
        public ItemAnimatorListener mListener;
        public long mMoveDuration;
        public long mRemoveDuration;

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewholder)
        {
            int k = viewholder.mFlags & 0xe;
            int i;
            if ((viewholder.mFlags & 4) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 4;
            } else
            {
                i = k;
                if ((k & 4) == 0)
                {
                    int l = viewholder.mOldPosition;
                    int j;
                    if (viewholder.mOwnerRecyclerView == null)
                    {
                        j = -1;
                    } else
                    {
                        j = viewholder.mOwnerRecyclerView.getAdapterPositionFor(viewholder);
                    }
                    i = k;
                    if (l != -1)
                    {
                        i = k;
                        if (j != -1)
                        {
                            i = k;
                            if (l != j)
                            {
                                return k | 0x800;
                            }
                        }
                    }
                }
            }
            return i;
        }

        public abstract boolean animateAppearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateChange(ViewHolder viewholder, ViewHolder viewholder1, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateDisappearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animatePersistence(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder)
        {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder, List list)
        {
            return canReuseUpdatedViewHolder(viewholder);
        }

        public final void dispatchAnimationsFinished()
        {
            int j = mFinishedListeners.size();
            for (int i = 0; i < j; i++)
            {
                class ItemAnimatorFinishedListener
                {

                    public abstract void onAnimationsFinished();
                }

                ((ItemAnimatorFinishedListener)mFinishedListeners.get(i)).onAnimationsFinished();
            }

            mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewholder);

        public abstract void endAnimations();

        public abstract boolean isRunning();

        public final ItemHolderInfo recordPreLayoutInformation$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNEQB4CTIN8BQICLHNIORCCLP5CQB5ESI5CQB5ET46UR34CLP3MIACD9GNCO9FELQ6IR1F9HKN6T1R55662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H4N8PBD85N6IRB1EHNN4929EHIMQI3FDHI6ASI9DPJ6UEO_0(ViewHolder viewholder)
        {
            class ItemHolderInfo
            {

                public int left;
                public int top;

                public ItemHolderInfo()
                {
                }
            }

            ItemHolderInfo itemholderinfo = new ItemHolderInfo();
            viewholder = viewholder.itemView;
            itemholderinfo.left = viewholder.getLeft();
            itemholderinfo.top = viewholder.getTop();
            viewholder.getRight();
            viewholder.getBottom();
            return itemholderinfo;
        }

        public abstract void runPendingAnimations();

        public ItemAnimator()
        {
            mListener = null;
            mFinishedListeners = new ArrayList();
            mAddDuration = 120L;
            mRemoveDuration = 120L;
            mMoveDuration = 250L;
            mChangeDuration = 250L;
        }
    }

    final class ItemAnimatorRestoreListener
        implements ItemAnimator.ItemAnimatorListener
    {

        private final RecyclerView this$0;

        public final void onAnimationFinished(ViewHolder viewholder)
        {
            ViewHolder viewholder1 = null;
            boolean flag = true;
            viewholder.setIsRecyclable(true);
            if (viewholder.mShadowedHolder != null && viewholder.mShadowingHolder == null)
            {
                viewholder.mShadowedHolder = null;
            }
            viewholder.mShadowingHolder = null;
            int i;
            if ((viewholder.mFlags & 0x10) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                RecyclerView recyclerview = RecyclerView.this;
                View view = viewholder.itemView;
                recyclerview.mInterceptRequestLayoutDepth = recyclerview.mInterceptRequestLayoutDepth + 1;
                if (recyclerview.mInterceptRequestLayoutDepth == 1 && !recyclerview.mLayoutFrozen)
                {
                    recyclerview.mLayoutWasDefered = false;
                }
                ChildHelper childhelper = recyclerview.mChildHelper;
                i = childhelper.mCallback.indexOfChild(view);
                boolean flag1;
                if (i == -1)
                {
                    if (childhelper.mHiddenViews.remove(view))
                    {
                        childhelper.mCallback.onLeftHiddenState(view);
                    }
                    i = 1;
                } else
                if (childhelper.mBucket.get(i))
                {
                    childhelper.mBucket.remove(i);
                    if (childhelper.mHiddenViews.remove(view))
                    {
                        childhelper.mCallback.onLeftHiddenState(view);
                    }
                    childhelper.mCallback.removeViewAt(i);
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    if (view != null)
                    {
                        viewholder1 = ((LayoutParams)view.getLayoutParams()).mViewHolder;
                    }
                    recyclerview.mRecycler.unscrapView(viewholder1);
                    recyclerview.mRecycler.recycleViewHolderInternal(viewholder1);
                }
                if (i == 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                recyclerview.stopInterceptRequestLayout(flag1);
                if (i == 0)
                {
                    if ((viewholder.mFlags & 0x100) != 0)
                    {
                        i = ((flag) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        removeDetachedView(viewholder.itemView, false);
                    }
                }
            }
        }

        ItemAnimatorRestoreListener()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static class ItemDecoration
    {

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, State state)
        {
            view = ((LayoutParams)view.getLayoutParams()).mViewHolder;
            int i;
            if (((ViewHolder) (view)).mPreLayoutPosition == -1)
            {
                i = ((ViewHolder) (view)).mPosition;
            } else
            {
                i = ((ViewHolder) (view)).mPreLayoutPosition;
            }
            rect.set(0, 0, 0, 0);
        }

        public void onDrawOver$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTIILG_0(Canvas canvas, RecyclerView recyclerview)
        {
        }

        public ItemDecoration()
        {
        }
    }

    public static abstract class LayoutManager
    {

        public boolean mAutoMeasure;
        public ChildHelper mChildHelper;
        public int mHeight;
        public int mHeightMode;
        public ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new _cls1();
        public boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        public int mPrefetchMaxCountObserved;
        public boolean mPrefetchMaxObservedInInitialPrefetch;
        public RecyclerView mRecyclerView;
        public boolean mRequestedSimpleAnimations;
        public ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new _cls2();
        public int mWidth;
        public int mWidthMode;

        public static int chooseSize(int i, int j, int k)
        {
            int i1 = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getSize(i);
            i = l;
            switch (i1)
            {
            default:
                i = Math.max(j, k);
                // fall through

            case 1073741824: 
                return i;

            case -2147483648: 
                return Math.min(l, Math.max(j, k));
            }
        }

        private static int getChildMeasureSpec(int i, int j, int k, int l, boolean flag)
        {
            boolean flag1;
            int i1;
            flag1 = false;
            i1 = Math.max(0, i - k);
            if (!flag) goto _L2; else goto _L1
_L1:
            if (l < 0) goto _L4; else goto _L3
_L3:
            k = 0x40000000;
            i = l;
_L7:
            return android.view.View.MeasureSpec.makeMeasureSpec(i, k);
_L4:
            if (l == -1)
            {
                switch (j)
                {
                case 0: // '\0'
                default:
                    i = 0;
                    k = ((flag1) ? 1 : 0);
                    break;

                case -2147483648: 
                case 1073741824: 
                    i = i1;
                    k = j;
                    break;
                }
                continue; /* Loop/switch isn't completed */
            }
            if (l == -2)
            {
                i = 0;
                k = ((flag1) ? 1 : 0);
                continue; /* Loop/switch isn't completed */
            }
              goto _L5
_L2:
            if (l >= 0)
            {
                k = 0x40000000;
                i = l;
                continue; /* Loop/switch isn't completed */
            }
            if (l == -1)
            {
                i = i1;
                k = j;
                continue; /* Loop/switch isn't completed */
            }
            if (l == -2)
            {
                if (j != 0x80000000)
                {
                    i = i1;
                    k = ((flag1) ? 1 : 0);
                    if (j != 0x40000000)
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                }
                k = 0x80000000;
                i = i1;
                continue; /* Loop/switch isn't completed */
            }
_L5:
            i = 0;
            k = ((flag1) ? 1 : 0);
            if (true) goto _L7; else goto _L6
_L6:
        }

        public static int getChildMeasureSpec(int i, int j, int k, boolean flag)
        {
            int l;
            int i1;
            i1 = 0x40000000;
            l = Math.max(0, i - j);
            if (!flag) goto _L2; else goto _L1
_L1:
            if (k >= 0)
            {
                i = k;
                j = i1;
            } else
            {
                j = 0;
                i = 0;
            }
_L4:
            return android.view.View.MeasureSpec.makeMeasureSpec(i, j);
_L2:
            j = i1;
            i = k;
            if (k < 0)
            {
                if (k == -1)
                {
                    i = l;
                    j = i1;
                } else
                if (k == -2)
                {
                    j = 0x80000000;
                    i = l;
                } else
                {
                    j = 0;
                    i = 0;
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public static int getPosition(View view)
        {
            view = ((LayoutParams)view.getLayoutParams()).mViewHolder;
            if (((ViewHolder) (view)).mPreLayoutPosition == -1)
            {
                return ((ViewHolder) (view)).mPosition;
            } else
            {
                return ((ViewHolder) (view)).mPreLayoutPosition;
            }
        }

        private static boolean isMeasurementUpToDate(int i, int j, int k)
        {
            int l;
            l = android.view.View.MeasureSpec.getMode(j);
            j = android.view.View.MeasureSpec.getSize(j);
            if (k <= 0 || i == k) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            switch (l)
            {
            default:
                return false;

            case 1073741824: 
                continue; /* Loop/switch isn't completed */

            case -2147483648: 
                if (j >= i)
                {
                    return true;
                }
                break;

            case 0: // '\0'
                return true;
            }
            if (true) goto _L1; else goto _L3
_L3:
            if (j != i) goto _L1; else goto _L4
_L4:
            return true;
        }

        public static void layoutDecorated(View view, int i, int j, int k, int l)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            view.layout(rect.left + i, rect.top + j, k - rect.right, l - rect.bottom);
        }

        private final void removeViewAt(int i)
        {
            Object obj;
            if (mChildHelper != null)
            {
                obj = mChildHelper;
                int j = ((ChildHelper) (obj)).getOffset(i);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
            } else
            {
                obj = null;
            }
            if (obj != null)
            {
                obj = mChildHelper;
                i = ((ChildHelper) (obj)).getOffset(i);
                View view = ((ChildHelper) (obj)).mCallback.getChildAt(i);
                if (view != null)
                {
                    if (((ChildHelper) (obj)).mBucket.remove(i) && ((ChildHelper) (obj)).mHiddenViews.remove(view))
                    {
                        ((ChildHelper) (obj)).mCallback.onLeftHiddenState(view);
                    }
                    ((ChildHelper) (obj)).mCallback.removeViewAt(i);
                }
            }
        }

        public final void addViewInt(View view, int i, boolean flag)
        {
            ViewHolder viewholder;
            boolean flag2;
            flag2 = true;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            if (flag) goto _L2; else goto _L1
_L1:
            Object obj;
            boolean flag1;
            if ((viewholder.mFlags & 8) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1) goto _L3; else goto _L2
_L2:
            mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder);
_L4:
            obj = (LayoutParams)view.getLayoutParams();
            if ((viewholder.mFlags & 0x20) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                if (viewholder.mScrapContainer != null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    break MISSING_BLOCK_LABEL_233;
                }
            }
            if (viewholder.mScrapContainer != null)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                viewholder.mScrapContainer.unscrapView(viewholder);
            } else
            {
                viewholder.mFlags = viewholder.mFlags & 0xffffffdf;
            }
            mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
_L5:
            if (((LayoutParams) (obj)).mPendingInvalidate)
            {
                viewholder.itemView.invalidate();
                obj.mPendingInvalidate = false;
            }
            return;
_L3:
            obj = (ViewInfoStore.InfoRecord)mRecyclerView.mViewInfoStore.mLayoutHolderMap.get(viewholder);
            if (obj != null)
            {
                obj.flags = ((ViewInfoStore.InfoRecord) (obj)).flags & -2;
            }
              goto _L4
            if (view.getParent() == mRecyclerView)
            {
                int k = mChildHelper.indexOfChild(view);
                int j = i;
                if (i == -1)
                {
                    ChildHelper childhelper = mChildHelper;
                    j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
                }
                if (k == -1)
                {
                    throw new IllegalStateException((new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:")).append(mRecyclerView.indexOfChild(view)).append(mRecyclerView.exceptionLabel()).toString());
                }
                if (k != j)
                {
                    Object obj1 = mRecyclerView.mLayout;
                    if (((LayoutManager) (obj1)).mChildHelper != null)
                    {
                        view = ((LayoutManager) (obj1)).mChildHelper;
                        i = view.getOffset(k);
                        view = ((ChildHelper) (view)).mCallback.getChildAt(i);
                    } else
                    {
                        view = null;
                    }
                    if (view == null)
                    {
                        throw new IllegalArgumentException((new StringBuilder("Cannot move a child from non-existing index:")).append(k).append(((LayoutManager) (obj1)).mRecyclerView.toString()).toString());
                    }
                    if (((LayoutManager) (obj1)).mChildHelper != null)
                    {
                        ChildHelper childhelper1 = ((LayoutManager) (obj1)).mChildHelper;
                        i = childhelper1.getOffset(k);
                        childhelper1.mCallback.getChildAt(i);
                    }
                    Object obj2 = ((LayoutManager) (obj1)).mChildHelper;
                    i = ((ChildHelper) (obj2)).getOffset(k);
                    ((ChildHelper) (obj2)).mBucket.remove(i);
                    ((ChildHelper) (obj2)).mCallback.detachViewFromParent(i);
                    obj2 = (LayoutParams)view.getLayoutParams();
                    ViewHolder viewholder1 = RecyclerView.getChildViewHolderInt(view);
                    if ((viewholder1.mFlags & 8) != 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        ((LayoutManager) (obj1)).mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder1);
                    } else
                    {
                        ViewInfoStore.InfoRecord inforecord = (ViewInfoStore.InfoRecord)((LayoutManager) (obj1)).mRecyclerView.mViewInfoStore.mLayoutHolderMap.get(viewholder1);
                        if (inforecord != null)
                        {
                            inforecord.flags = inforecord.flags & -2;
                        }
                    }
                    obj1 = ((LayoutManager) (obj1)).mChildHelper;
                    if ((viewholder1.mFlags & 8) != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    ((ChildHelper) (obj1)).attachViewToParent(view, j, ((android.view.ViewGroup.LayoutParams) (obj2)), flag);
                }
            } else
            {
                mChildHelper.addView(view, i, false);
                obj.mInsetsDirty = true;
            }
              goto _L5
        }

        public void assertNotInLayoutOrScroll(String s)
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.assertNotInLayoutOrScroll(s);
            }
        }

        public boolean canScrollHorizontally()
        {
            return false;
        }

        public boolean canScrollVertically()
        {
            return false;
        }

        public void collectAdjacentPrefetchPositions(int i, int j, State state, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public int computeHorizontalScrollExtent(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollRange(State state)
        {
            return 0;
        }

        public int computeVerticalScrollExtent(State state)
        {
            return 0;
        }

        public int computeVerticalScrollOffset(State state)
        {
            return 0;
        }

        public int computeVerticalScrollRange(State state)
        {
            return 0;
        }

        public final void detachView(View view)
        {
            int i = mChildHelper.indexOfChild(view);
            if (i >= 0)
            {
                view = mChildHelper;
                i = view.getOffset(i);
                ((ChildHelper) (view)).mBucket.remove(i);
                ((ChildHelper) (view)).mCallback.detachViewFromParent(i);
            }
        }

        public View findViewByPosition(int i)
        {
            int j;
            int k;
            if (mChildHelper != null)
            {
                ChildHelper childhelper = mChildHelper;
                j = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            } else
            {
                j = 0;
            }
            for (k = 0; k < j; k++)
            {
                Object obj;
                ViewHolder viewholder;
                int i1;
                if (mChildHelper != null)
                {
                    obj = mChildHelper;
                    int l = ((ChildHelper) (obj)).getOffset(k);
                    obj = ((ChildHelper) (obj)).mCallback.getChildAt(l);
                } else
                {
                    obj = null;
                }
                viewholder = RecyclerView.getChildViewHolderInt(((View) (obj)));
                if (viewholder == null)
                {
                    continue;
                }
                if (viewholder.mPreLayoutPosition == -1)
                {
                    i1 = viewholder.mPosition;
                } else
                {
                    i1 = viewholder.mPreLayoutPosition;
                }
                if (i1 != i)
                {
                    continue;
                }
                if ((viewholder.mFlags & 0x80) != 0)
                {
                    i1 = 1;
                } else
                {
                    i1 = 0;
                }
                if (i1 != 0)
                {
                    continue;
                }
                if (!mRecyclerView.mState.mInPreLayout)
                {
                    if ((viewholder.mFlags & 8) != 0)
                    {
                        i1 = 1;
                    } else
                    {
                        i1 = 0;
                    }
                    if (i1 != 0)
                    {
                        continue;
                    }
                }
                return ((View) (obj));
            }

            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public final View getChildAt(int i)
        {
            if (mChildHelper != null)
            {
                ChildHelper childhelper = mChildHelper;
                i = childhelper.getOffset(i);
                return childhelper.mCallback.getChildAt(i);
            } else
            {
                return null;
            }
        }

        public final int getChildCount()
        {
            if (mChildHelper != null)
            {
                ChildHelper childhelper = mChildHelper;
                return childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            } else
            {
                return 0;
            }
        }

        public final View getFocusedChild()
        {
            View view;
            if (mRecyclerView != null)
            {
                if ((view = mRecyclerView.getFocusedChild()) != null && !mChildHelper.mHiddenViews.contains(view))
                {
                    return view;
                }
            }
            return null;
        }

        public final void getTransformedBoundingBox(View view, boolean flag, Rect rect)
        {
            Rect rect1 = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            int i = -rect1.left;
            int j = -rect1.top;
            int k = view.getWidth();
            int l = rect1.right;
            int i1 = view.getHeight();
            rect.set(i, j, k + l, rect1.bottom + i1);
            if (mRecyclerView != null)
            {
                Matrix matrix = view.getMatrix();
                if (matrix != null && !matrix.isIdentity())
                {
                    RectF rectf = mRecyclerView.mTempRectF;
                    rectf.set(rect);
                    matrix.mapRect(rectf);
                    rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean isAutoMeasureEnabled()
        {
            return mAutoMeasure;
        }

        public void measureChildWithMargins(View view, int i, int j)
        {
label0:
            {
                boolean flag = false;
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                Rect rect = mRecyclerView.getItemDecorInsetsForChild(view);
                int k1 = rect.left;
                int l1 = rect.right;
                int i1 = rect.top;
                int j1 = rect.bottom;
                int i2 = mWidth;
                int j2 = mWidthMode;
                int k;
                int l;
                if (mRecyclerView != null)
                {
                    k = mRecyclerView.getPaddingLeft();
                } else
                {
                    k = 0;
                }
                if (mRecyclerView != null)
                {
                    l = mRecyclerView.getPaddingRight();
                } else
                {
                    l = 0;
                }
                l = getChildMeasureSpec(i2, j2, k + l + layoutparams.leftMargin + layoutparams.rightMargin + (i + (k1 + l1)), layoutparams.width, canScrollHorizontally());
                k1 = mHeight;
                l1 = mHeightMode;
                if (mRecyclerView != null)
                {
                    i = mRecyclerView.getPaddingTop();
                } else
                {
                    i = 0;
                }
                if (mRecyclerView != null)
                {
                    k = mRecyclerView.getPaddingBottom();
                } else
                {
                    k = 0;
                }
                j = getChildMeasureSpec(k1, l1, i + k + layoutparams.topMargin + layoutparams.bottomMargin + (j + (j1 + i1)), layoutparams.height, canScrollVertically());
                if (!view.isLayoutRequested() && mMeasurementCacheEnabled && isMeasurementUpToDate(view.getWidth(), l, layoutparams.width))
                {
                    i = ((flag) ? 1 : 0);
                    if (isMeasurementUpToDate(view.getHeight(), j, layoutparams.height))
                    {
                        break label0;
                    }
                }
                i = 1;
            }
            if (i != 0)
            {
                view.measure(l, j);
            }
        }

        public void onDetachedFromWindow(RecyclerView recyclerview, Recycler recycler)
        {
        }

        public View onFocusSearchFailed$51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357CKKOOBECHP6UQB45TR6IPBN5TB6IPBN7C______0(int i, Recycler recycler, State state)
        {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            boolean flag1 = true;
            Object obj = mRecyclerView.mRecycler;
            obj = mRecyclerView.mState;
            if (mRecyclerView != null && accessibilityevent != null)
            {
                boolean flag = flag1;
                if (!mRecyclerView.canScrollVertically(1))
                {
                    flag = flag1;
                    if (!mRecyclerView.canScrollVertically(-1))
                    {
                        flag = flag1;
                        if (!mRecyclerView.canScrollHorizontally(-1))
                        {
                            if (mRecyclerView.canScrollHorizontally(1))
                            {
                                flag = flag1;
                            } else
                            {
                                flag = false;
                            }
                        }
                    }
                }
                accessibilityevent.setScrollable(flag);
                if (mRecyclerView.mAdapter != null)
                {
                    accessibilityevent.setItemCount(mRecyclerView.mAdapter.getItemCount());
                    return;
                }
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            boolean flag = true;
            if (mRecyclerView.canScrollVertically(-1) || mRecyclerView.canScrollHorizontally(-1))
            {
                accessibilitynodeinfocompat.mInfo.addAction(8192);
                accessibilitynodeinfocompat.mInfo.setScrollable(true);
            }
            if (mRecyclerView.canScrollVertically(1) || mRecyclerView.canScrollHorizontally(1))
            {
                accessibilitynodeinfocompat.mInfo.addAction(4096);
                accessibilitynodeinfocompat.mInfo.setScrollable(true);
            }
            int i;
            int j;
            if (mRecyclerView == null || mRecyclerView.mAdapter == null)
            {
                i = 1;
            } else
            if (canScrollVertically())
            {
                i = mRecyclerView.mAdapter.getItemCount();
            } else
            {
                i = 1;
            }
            j = ((flag) ? 1 : 0);
            if (mRecyclerView != null)
            {
                if (mRecyclerView.mAdapter == null)
                {
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j = ((flag) ? 1 : 0);
                    if (canScrollHorizontally())
                    {
                        j = mRecyclerView.mAdapter.getItemCount();
                    }
                }
            }
            recycler = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, false, 0));
            state = accessibilitynodeinfocompat.mInfo;
            if (recycler == null)
            {
                recycler = null;
            } else
            {
                recycler = (android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat)recycler).mInfo;
            }
            state.setCollectionInfo(recycler);
        }

        public void onLayoutChildren(Recycler recycler, State state)
        {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state)
        {
        }

        public void onRestoreInstanceState(Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState()
        {
            return null;
        }

        public void onScrollStateChanged(int i)
        {
        }

        public final void removeAndRecycleAllViews(Recycler recycler)
        {
            int i = getChildCount() - 1;
            while (i >= 0) 
            {
                boolean flag;
                if ((RecyclerView.getChildViewHolderInt(getChildAt(i)).mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    removeAndRecycleViewAt(i, recycler);
                }
                i--;
            }
        }

        final void removeAndRecycleScrapInt(Recycler recycler)
        {
            int j = recycler.mAttachedScrap.size();
            int i = j - 1;
            while (i >= 0) 
            {
                Object obj = ((ViewHolder)recycler.mAttachedScrap.get(i)).itemView;
                ViewHolder viewholder = RecyclerView.getChildViewHolderInt(((View) (obj)));
                boolean flag;
                if ((viewholder.mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    continue;
                }
                viewholder.setIsRecyclable(false);
                if ((viewholder.mFlags & 0x100) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    mRecyclerView.removeDetachedView(((View) (obj)), false);
                }
                if (mRecyclerView.mItemAnimator != null)
                {
                    mRecyclerView.mItemAnimator.endAnimation(viewholder);
                }
                viewholder.setIsRecyclable(true);
                obj = RecyclerView.getChildViewHolderInt(((View) (obj)));
                obj.mScrapContainer = null;
                obj.mInChangeScrap = false;
                obj.mFlags = ((ViewHolder) (obj)).mFlags & 0xffffffdf;
                recycler.recycleViewHolderInternal(((ViewHolder) (obj)));
                i--;
            }
            recycler.mAttachedScrap.clear();
            if (recycler.mChangedScrap != null)
            {
                recycler.mChangedScrap.clear();
            }
            if (j > 0)
            {
                mRecyclerView.invalidate();
            }
        }

        public final void removeAndRecycleViewAt(int i, Recycler recycler)
        {
            Object obj;
            if (mChildHelper != null)
            {
                obj = mChildHelper;
                int j = ((ChildHelper) (obj)).getOffset(i);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
            } else
            {
                obj = null;
            }
            removeViewAt(i);
            recycler.recycleView(((View) (obj)));
        }

        public final boolean requestChildRectangleOnScreen(RecyclerView recyclerview, View view, Rect rect, boolean flag, boolean flag1)
        {
label0:
            {
                int ai[] = new int[2];
                int i;
                int j;
                int l;
                int j1;
                int l1;
                int i2;
                int j2;
                int l2;
                int j3;
                int k3;
                int l3;
                int i4;
                if (mRecyclerView != null)
                {
                    i = mRecyclerView.getPaddingLeft();
                } else
                {
                    i = 0;
                }
                if (mRecyclerView != null)
                {
                    j = mRecyclerView.getPaddingTop();
                } else
                {
                    j = 0;
                }
                j1 = mWidth;
                if (mRecyclerView != null)
                {
                    l = mRecyclerView.getPaddingRight();
                } else
                {
                    l = 0;
                }
                l2 = j1 - l;
                j3 = mHeight;
                if (mRecyclerView != null)
                {
                    l = mRecyclerView.getPaddingBottom();
                } else
                {
                    l = 0;
                }
                k3 = (view.getLeft() + rect.left) - view.getScrollX();
                j2 = (view.getTop() + rect.top) - view.getScrollY();
                l3 = k3 + rect.width();
                i4 = rect.height();
                l1 = Math.min(0, k3 - i);
                j1 = Math.min(0, j2 - j);
                i2 = Math.max(0, l3 - l2);
                l = Math.max(0, (j2 + i4) - (j3 - l));
                if (ViewCompat.getLayoutDirection(mRecyclerView) == 1)
                {
                    if (i2 != 0)
                    {
                        i = i2;
                    } else
                    {
                        i = Math.max(l1, l3 - l2);
                    }
                } else
                if (l1 != 0)
                {
                    i = l1;
                } else
                {
                    i = Math.min(k3 - i, i2);
                }
                if (j1 != 0)
                {
                    j = j1;
                } else
                {
                    j = Math.min(j2 - j, l);
                }
                ai[0] = i;
                ai[1] = j;
                l1 = ai[0];
                i2 = ai[1];
                if (flag1)
                {
                    view = recyclerview.getFocusedChild();
                    if (view == null)
                    {
                        i = 0;
                    } else
                    {
                        int k;
                        int i1;
                        int k1;
                        int k2;
                        int i3;
                        if (mRecyclerView != null)
                        {
                            i = mRecyclerView.getPaddingLeft();
                        } else
                        {
                            i = 0;
                        }
                        if (mRecyclerView != null)
                        {
                            k = mRecyclerView.getPaddingTop();
                        } else
                        {
                            k = 0;
                        }
                        k2 = mWidth;
                        if (mRecyclerView != null)
                        {
                            i1 = mRecyclerView.getPaddingRight();
                        } else
                        {
                            i1 = 0;
                        }
                        i3 = mHeight;
                        if (mRecyclerView != null)
                        {
                            k1 = mRecyclerView.getPaddingBottom();
                        } else
                        {
                            k1 = 0;
                        }
                        rect = mRecyclerView.mTempRect;
                        RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
                        if (rect.left - l1 >= k2 - i1 || rect.right - l1 <= i || rect.top - i2 >= i3 - k1 || rect.bottom - i2 <= k)
                        {
                            i = 0;
                        } else
                        {
                            i = 1;
                        }
                    }
                    if (i == 0)
                    {
                        break label0;
                    }
                }
                if (l1 != 0 || i2 != 0)
                {
                    if (flag)
                    {
                        recyclerview.scrollBy(l1, i2);
                    } else
                    {
                        recyclerview.smoothScrollBy(l1, i2);
                    }
                    return true;
                }
            }
            return false;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        public void scrollToPosition(int i)
        {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        final void setMeasureSpecs(int i, int j)
        {
            mWidth = android.view.View.MeasureSpec.getSize(i);
            mWidthMode = android.view.View.MeasureSpec.getMode(i);
            if (mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
            {
                mWidth = 0;
            }
            mHeight = android.view.View.MeasureSpec.getSize(j);
            mHeightMode = android.view.View.MeasureSpec.getMode(j);
            if (mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
            {
                mHeight = 0;
            }
        }

        final void setMeasuredDimensionFromChildren(int i, int j)
        {
            int i1 = 0x7fffffff;
            int k = 0x80000000;
            boolean flag = false;
            int k1;
            if (mChildHelper != null)
            {
                ChildHelper childhelper = mChildHelper;
                k1 = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
            } else
            {
                k1 = 0;
            }
            if (k1 == 0)
            {
                mRecyclerView.defaultOnMeasure(i, j);
                return;
            }
            int l1 = 0;
            int j1 = 0x80000000;
            int l = 0x7fffffff;
            while (l1 < k1) 
            {
                Object obj;
                Rect rect1;
                if (mChildHelper != null)
                {
                    obj = mChildHelper;
                    int i2 = ((ChildHelper) (obj)).getOffset(l1);
                    obj = ((ChildHelper) (obj)).mCallback.getChildAt(i2);
                } else
                {
                    obj = null;
                }
                rect1 = mRecyclerView.mTempRect;
                RecyclerView.getDecoratedBoundsWithMarginsInt(((View) (obj)), rect1);
                if (rect1.left < l)
                {
                    l = rect1.left;
                }
                if (rect1.right > j1)
                {
                    j1 = rect1.right;
                }
                if (rect1.top < i1)
                {
                    i1 = rect1.top;
                }
                if (rect1.bottom > k)
                {
                    k = rect1.bottom;
                }
                l1++;
            }
            mRecyclerView.mTempRect.set(l, i1, j1, k);
            Rect rect = mRecyclerView.mTempRect;
            k1 = rect.width();
            if (mRecyclerView != null)
            {
                k = mRecyclerView.getPaddingLeft();
            } else
            {
                k = 0;
            }
            if (mRecyclerView != null)
            {
                l = mRecyclerView.getPaddingRight();
            } else
            {
                l = 0;
            }
            l1 = rect.height();
            if (mRecyclerView != null)
            {
                i1 = mRecyclerView.getPaddingTop();
            } else
            {
                i1 = 0;
            }
            j1 = ((flag) ? 1 : 0);
            if (mRecyclerView != null)
            {
                j1 = mRecyclerView.getPaddingBottom();
            }
            i = chooseSize(i, k1 + k + l, ViewCompat.getMinimumWidth(mRecyclerView));
            j = chooseSize(j, i1 + l1 + j1, ViewCompat.getMinimumHeight(mRecyclerView));
            mRecyclerView.setMeasuredDimension(i, j);
        }

        final void setRecyclerView(RecyclerView recyclerview)
        {
            if (recyclerview == null)
            {
                mRecyclerView = null;
                mChildHelper = null;
                mWidth = 0;
                mHeight = 0;
            } else
            {
                mRecyclerView = recyclerview;
                mChildHelper = recyclerview.mChildHelper;
                mWidth = recyclerview.getWidth();
                mHeight = recyclerview.getHeight();
            }
            mWidthMode = 0x40000000;
            mHeightMode = 0x40000000;
        }

        boolean shouldMeasureTwice()
        {
            return false;
        }

        public boolean supportsPredictiveItemAnimations()
        {
            return false;
        }

        public LayoutManager()
        {
            class _cls1
                implements ViewBoundsCheck.Callback
            {

                private final LayoutManager this$0;

                public final View getChildAt(int i)
                {
                    return LayoutManager.this.getChildAt(i);
                }

                public final int getChildEnd(View view)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    LayoutManager layoutmanager = LayoutManager.this;
                    int i = view.getRight();
                    int j = ((LayoutParams)view.getLayoutParams()).mDecorInsets.right;
                    return layoutparams.rightMargin + (j + i);
                }

                public final int getChildStart(View view)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    LayoutManager layoutmanager = LayoutManager.this;
                    return view.getLeft() - ((LayoutParams)view.getLayoutParams()).mDecorInsets.left - layoutparams.leftMargin;
                }

                public final int getParentEnd()
                {
                    int j = mWidth;
                    LayoutManager layoutmanager = LayoutManager.this;
                    int i;
                    if (layoutmanager.mRecyclerView != null)
                    {
                        i = layoutmanager.mRecyclerView.getPaddingRight();
                    } else
                    {
                        i = 0;
                    }
                    return j - i;
                }

                public final int getParentStart()
                {
                    LayoutManager layoutmanager = LayoutManager.this;
                    if (layoutmanager.mRecyclerView != null)
                    {
                        return layoutmanager.mRecyclerView.getPaddingLeft();
                    } else
                    {
                        return 0;
                    }
                }

                _cls1()
                {
                    this$0 = LayoutManager.this;
                    super();
                }
            }

            class _cls2
                implements ViewBoundsCheck.Callback
            {

                private final LayoutManager this$0;

                public final View getChildAt(int i)
                {
                    return LayoutManager.this.getChildAt(i);
                }

                public final int getChildEnd(View view)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    LayoutManager layoutmanager = LayoutManager.this;
                    int i = view.getBottom();
                    int j = ((LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
                    return layoutparams.bottomMargin + (j + i);
                }

                public final int getChildStart(View view)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    LayoutManager layoutmanager = LayoutManager.this;
                    return view.getTop() - ((LayoutParams)view.getLayoutParams()).mDecorInsets.top - layoutparams.topMargin;
                }

                public final int getParentEnd()
                {
                    int j = mHeight;
                    LayoutManager layoutmanager = LayoutManager.this;
                    int i;
                    if (layoutmanager.mRecyclerView != null)
                    {
                        i = layoutmanager.mRecyclerView.getPaddingBottom();
                    } else
                    {
                        i = 0;
                    }
                    return j - i;
                }

                public final int getParentStart()
                {
                    LayoutManager layoutmanager = LayoutManager.this;
                    if (layoutmanager.mRecyclerView != null)
                    {
                        return layoutmanager.mRecyclerView.getPaddingTop();
                    } else
                    {
                        return 0;
                    }
                }

                _cls2()
                {
                    this$0 = LayoutManager.this;
                    super();
                }
            }

            mHorizontalBoundCheck = new ViewBoundsCheck(mHorizontalBoundCheckCallback);
            mVerticalBoundCheck = new ViewBoundsCheck(mVerticalBoundCheckCallback);
            mRequestedSimpleAnimations = false;
            mAutoMeasure = false;
            mMeasurementCacheEnabled = true;
            mItemPrefetchEnabled = true;
        }
    }

    public static interface LayoutManager.LayoutPrefetchRegistry
    {

        public abstract void addPosition(int i, int j);
    }

    public static final class LayoutManager.Properties
    {

        public int orientation;
        public boolean reverseLayout;
        public boolean stackFromEnd;

        public LayoutManager.Properties()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public final Rect mDecorInsets;
        public boolean mInsetsDirty;
        public boolean mPendingInvalidate;
        public ViewHolder mViewHolder;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }
    }

    public static abstract class OnFlingListener
    {

        public abstract boolean onFling(int i, int j);

        public OnFlingListener()
        {
        }
    }

    public static interface OnItemTouchListener
    {

        public abstract boolean onInterceptTouchEvent$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIIMG_0(MotionEvent motionevent);

        public abstract void onRequestDisallowInterceptTouchEvent$51D2ILG_0();

        public abstract void onTouchEvent$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0(MotionEvent motionevent);
    }

    public static class OnScrollListener
    {

        public void onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(int i)
        {
        }

        public void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
        {
        }

        public OnScrollListener()
        {
        }
    }

    public static final class RecycledViewPool
    {

        public int mAttachCount;
        public SparseArray mScrap;

        final ScrapData getScrapDataForType(int i)
        {
            class ScrapData
            {

                public long mBindRunningAverageNs;
                public long mCreateRunningAverageNs;
                public int mMaxScrap;
                public final ArrayList mScrapHeap = new ArrayList();

                ScrapData()
                {
                    mMaxScrap = 5;
                    mCreateRunningAverageNs = 0L;
                    mBindRunningAverageNs = 0L;
                }
            }

            ScrapData scrapdata1 = (ScrapData)mScrap.get(i);
            ScrapData scrapdata = scrapdata1;
            if (scrapdata1 == null)
            {
                scrapdata = new ScrapData();
                mScrap.put(i, scrapdata);
            }
            return scrapdata;
        }

        public final void setMaxRecycledViews(int i, int j)
        {
            Object obj = getScrapDataForType(i);
            obj.mMaxScrap = j;
            for (obj = ((ScrapData) (obj)).mScrapHeap; ((ArrayList) (obj)).size() > j; ((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1)) { }
        }

        public RecycledViewPool()
        {
            mScrap = new SparseArray();
            mAttachCount = 0;
        }
    }

    public final class Recycler
    {

        public final ArrayList mAttachedScrap = new ArrayList();
        public final ArrayList mCachedViews = new ArrayList();
        public ArrayList mChangedScrap;
        public RecycledViewPool mRecyclerPool;
        public int mRequestedCacheMax;
        public final List mUnmodifiableAttachedScrap;
        private int mViewCacheMax;
        public final RecyclerView this$0;

        final void addViewHolderToRecycledViewPool(ViewHolder viewholder, boolean flag)
        {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewholder);
            RecycledViewPool recycledviewpool;
            ArrayList arraylist;
            int i;
            if ((0x4000 & viewholder.mFlags) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                viewholder.mFlags = viewholder.mFlags & 0xffffbfff | 0;
                ViewCompat.setAccessibilityDelegate(viewholder.itemView, null);
            }
            if (flag)
            {
                if (mRecyclerListener != null)
                {
                    mRecyclerListener.onViewRecycled(viewholder);
                }
                if (mAdapter != null)
                {
                    mAdapter.onViewRecycled(viewholder);
                }
                if (mState != null)
                {
                    mViewInfoStore.removeViewHolder(viewholder);
                }
            }
            viewholder.mOwnerRecyclerView = null;
            if (mRecyclerPool == null)
            {
                mRecyclerPool = new RecycledViewPool();
            }
            recycledviewpool = mRecyclerPool;
            i = viewholder.mItemViewType;
            arraylist = recycledviewpool.getScrapDataForType(i).mScrapHeap;
            if (((RecycledViewPool.ScrapData)recycledviewpool.mScrap.get(i)).mMaxScrap > arraylist.size())
            {
                viewholder.resetInternal();
                arraylist.add(viewholder);
            }
        }

        public final void recycleAndClearCachedViews()
        {
            for (int i = mCachedViews.size() - 1; i >= 0; i--)
            {
                addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(i), true);
                mCachedViews.remove(i);
            }

            mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                GapWorker.LayoutPrefetchRegistryImpl layoutprefetchregistryimpl = mPrefetchRegistry;
                if (layoutprefetchregistryimpl.mPrefetchArray != null)
                {
                    Arrays.fill(layoutprefetchregistryimpl.mPrefetchArray, -1);
                }
                layoutprefetchregistryimpl.mCount = 0;
            }
        }

        public final void recycleView(View view)
        {
            ViewHolder viewholder;
            boolean flag2;
            flag2 = true;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            boolean flag;
            if ((viewholder.mFlags & 0x100) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                removeDetachedView(view, false);
            }
            if (viewholder.mScrapContainer != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L2; else goto _L1
_L1:
            viewholder.mScrapContainer.unscrapView(viewholder);
_L4:
            recycleViewHolderInternal(viewholder);
            return;
_L2:
            boolean flag1;
            if ((viewholder.mFlags & 0x20) != 0)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                viewholder.mFlags = viewholder.mFlags & 0xffffffdf;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        final void recycleViewHolderInternal(ViewHolder viewholder)
        {
            int i;
            boolean flag2;
            boolean flag4;
            flag4 = false;
            boolean flag6 = true;
            boolean flag;
            if (viewholder.mScrapContainer != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || viewholder.itemView.getParent() != null)
            {
                StringBuilder stringbuilder = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
                boolean flag5;
                if (viewholder.mScrapContainer != null)
                {
                    flag5 = true;
                } else
                {
                    flag5 = false;
                }
                stringbuilder = stringbuilder.append(flag5).append(" isAttached:");
                if (viewholder.itemView.getParent() != null)
                {
                    flag5 = flag6;
                } else
                {
                    flag5 = false;
                }
                throw new IllegalArgumentException(stringbuilder.append(flag5).append(exceptionLabel()).toString());
            }
            if ((viewholder.mFlags & 0x100) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                throw new IllegalArgumentException((new StringBuilder("Tmp detached view should be removed from RecyclerView before it can be recycled: ")).append(viewholder).append(exceptionLabel()).toString());
            }
            if ((viewholder.mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                throw new IllegalArgumentException((new StringBuilder("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.")).append(exceptionLabel()).toString());
            }
            Adapter adapter;
            if ((viewholder.mFlags & 0x10) == 0 && ViewCompat.hasTransientState(viewholder.itemView))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (mAdapter != null && flag2)
            {
                adapter = mAdapter;
            }
            if ((viewholder.mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(viewholder.itemView))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_546;
            }
            if (mViewCacheMax <= 0) goto _L2; else goto _L1
_L1:
            if ((0x20e & viewholder.mFlags) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0) goto _L2; else goto _L3
_L3:
            boolean flag1;
            i = mCachedViews.size();
            if (i >= mViewCacheMax && i > 0)
            {
                addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(0), true);
                mCachedViews.remove(0);
                i--;
            }
            int j = i;
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                j = i;
                if (i > 0)
                {
                    j = i;
                    if (!mPrefetchRegistry.lastPrefetchIncludedPosition(viewholder.mPosition))
                    {
                        i--;
                        do
                        {
                            if (i < 0)
                            {
                                break;
                            }
                            j = ((ViewHolder)mCachedViews.get(i)).mPosition;
                            if (!mPrefetchRegistry.lastPrefetchIncludedPosition(j))
                            {
                                break;
                            }
                            i--;
                        } while (true);
                        j = i + 1;
                    }
                }
            }
            mCachedViews.add(j, viewholder);
            flag1 = true;
_L4:
            boolean flag3;
            flag3 = flag1;
            if (!flag1)
            {
                addViewHolderToRecycledViewPool(viewholder, true);
                flag4 = true;
                flag3 = flag1;
            }
_L5:
            mViewInfoStore.removeViewHolder(viewholder);
            if (!flag3 && !flag4 && flag2)
            {
                viewholder.mOwnerRecyclerView = null;
            }
            return;
_L2:
            flag1 = false;
              goto _L4
            flag3 = false;
              goto _L5
        }

        final void scrapView(View view)
        {
label0:
            {
                boolean flag1 = true;
                view = RecyclerView.getChildViewHolderInt(view);
                boolean flag;
                if ((0xc & ((ViewHolder) (view)).mFlags) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    if ((((ViewHolder) (view)).mFlags & 2) != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        RecyclerView recyclerview = RecyclerView.this;
                        if (recyclerview.mItemAnimator == null || recyclerview.mItemAnimator.canReuseUpdatedViewHolder(view, view.getUnmodifiedPayloads()))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            break label0;
                        }
                    }
                }
                if ((((ViewHolder) (view)).mFlags & 4) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if ((((ViewHolder) (view)).mFlags & 8) != 0)
                    {
                        flag = flag1;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag && !mAdapter.mHasStableIds)
                    {
                        throw new IllegalArgumentException((new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")).append(exceptionLabel()).toString());
                    }
                }
                view.mScrapContainer = this;
                view.mInChangeScrap = false;
                mAttachedScrap.add(view);
                return;
            }
            if (mChangedScrap == null)
            {
                mChangedScrap = new ArrayList();
            }
            view.mScrapContainer = this;
            view.mInChangeScrap = true;
            mChangedScrap.add(view);
        }

        public final ViewHolder tryGetViewHolderForPositionByDeadline(int i, boolean flag, long l)
        {
            Object obj2;
            int i1;
label0:
            {
                State state;
                if (i >= 0)
                {
                    Object obj = mState;
                    int j;
                    if (((State) (obj)).mInPreLayout)
                    {
                        j = ((State) (obj)).mPreviousLayoutItemCount - ((State) (obj)).mDeletedInvisibleItemCountSincePreviousLayout;
                    } else
                    {
                        j = ((State) (obj)).mItemCount;
                    }
                    if (i < j)
                    {
                        break label0;
                    }
                }
                obj = (new StringBuilder("Invalid item position ")).append(i).append("(").append(i).append("). Item count:");
                state = mState;
                if (state.mInPreLayout)
                {
                    i = state.mPreviousLayoutItemCount - state.mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    i = state.mItemCount;
                }
                throw new IndexOutOfBoundsException(((StringBuilder) (obj)).append(i).append(exceptionLabel()).toString());
            }
            i1 = 0;
            obj2 = null;
            if (!mState.mInPreLayout) goto _L2; else goto _L1
_L1:
            if (mChangedScrap == null) goto _L4; else goto _L3
_L3:
            int j1 = mChangedScrap.size();
            if (j1 != 0) goto _L5; else goto _L4
_L4:
            Object obj1 = null;
_L12:
            int k;
            int k2;
            boolean flag1;
            int l1;
            long l2;
            if (obj1 != null)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            obj2 = obj1;
            i1 = k;
_L2:
            k = i1;
            obj1 = obj2;
            if (obj2 != null) goto _L7; else goto _L6
_L6:
            l1 = mAttachedScrap.size();
            k = 0;
_L15:
            if (k >= l1) goto _L9; else goto _L8
_L8:
            obj2 = (ViewHolder)mAttachedScrap.get(k);
            if ((((ViewHolder) (obj2)).mFlags & 0x20) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (((ViewHolder) (obj2)).mPreLayoutPosition == -1)
            {
                j1 = ((ViewHolder) (obj2)).mPosition;
            } else
            {
                j1 = ((ViewHolder) (obj2)).mPreLayoutPosition;
            }
            if (j1 != i)
            {
                continue; /* Loop/switch isn't completed */
            }
            if ((((ViewHolder) (obj2)).mFlags & 4) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!mState.mInPreLayout)
            {
                if ((((ViewHolder) (obj2)).mFlags & 8) != 0)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                if (j1 != 0)
                {
                    continue; /* Loop/switch isn't completed */
                }
            }
            obj2.mFlags = 0x20 | ((ViewHolder) (obj2)).mFlags;
_L20:
            k = i1;
            obj1 = obj2;
            if (obj2 != null)
            {
                boolean flag3;
                if ((((ViewHolder) (obj2)).mFlags & 8) != 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    flag3 = mState.mInPreLayout;
                } else
                {
                    if (((ViewHolder) (obj2)).mPosition < 0 || ((ViewHolder) (obj2)).mPosition >= mAdapter.getItemCount())
                    {
                        throw new IndexOutOfBoundsException((new StringBuilder("Inconsistency detected. Invalid view holder adapter position")).append(obj2).append(exceptionLabel()).toString());
                    }
                    if (!mState.mInPreLayout && mAdapter.getItemViewType(((ViewHolder) (obj2)).mPosition) != ((ViewHolder) (obj2)).mItemViewType)
                    {
                        flag3 = false;
                    } else
                    if (mAdapter.mHasStableIds)
                    {
                        if (((ViewHolder) (obj2)).mItemId == mAdapter.getItemId(((ViewHolder) (obj2)).mPosition))
                        {
                            flag3 = true;
                        } else
                        {
                            flag3 = false;
                        }
                    } else
                    {
                        flag3 = true;
                    }
                }
                if (!flag3)
                {
                    if (!flag)
                    {
                        obj2.mFlags = 4 | ((ViewHolder) (obj2)).mFlags;
                        Object obj3;
                        int i2;
                        if (((ViewHolder) (obj2)).mScrapContainer != null)
                        {
                            k = 1;
                        } else
                        {
                            k = 0;
                        }
                        if (k != 0)
                        {
                            removeDetachedView(((ViewHolder) (obj2)).itemView, false);
                            ((ViewHolder) (obj2)).mScrapContainer.unscrapView(((ViewHolder) (obj2)));
                        } else
                        {
                            if ((((ViewHolder) (obj2)).mFlags & 0x20) != 0)
                            {
                                k = 1;
                            } else
                            {
                                k = 0;
                            }
                            if (k != 0)
                            {
                                obj2.mFlags = ((ViewHolder) (obj2)).mFlags & 0xffffffdf;
                            }
                        }
                        recycleViewHolderInternal(((ViewHolder) (obj2)));
                    }
                    obj1 = null;
                    k = i1;
                } else
                {
                    k = 1;
                    obj1 = obj2;
                }
            }
_L7:
            if (obj1 != null)
            {
                break MISSING_BLOCK_LABEL_3227;
            }
            k2 = mAdapterHelper.findPositionOffset(i, 0);
            if (k2 < 0 || k2 >= mAdapter.getItemCount())
            {
                obj1 = (new StringBuilder("Inconsistency detected. Invalid item position ")).append(i).append("(offset:").append(k2).append(").state:");
                obj2 = mState;
                if (((State) (obj2)).mInPreLayout)
                {
                    i = ((State) (obj2)).mPreviousLayoutItemCount - ((State) (obj2)).mDeletedInvisibleItemCountSincePreviousLayout;
                } else
                {
                    i = ((State) (obj2)).mItemCount;
                }
                throw new IndexOutOfBoundsException(((StringBuilder) (obj1)).append(i).append(exceptionLabel()).toString());
            }
            break MISSING_BLOCK_LABEL_1678;
_L5:
            k = 0;
_L13:
            if (k >= j1) goto _L11; else goto _L10
_L10:
            obj1 = (ViewHolder)mChangedScrap.get(k);
            if ((((ViewHolder) (obj1)).mFlags & 0x20) != 0)
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
            if (((ViewHolder) (obj1)).mPreLayoutPosition == -1)
            {
                i1 = ((ViewHolder) (obj1)).mPosition;
            } else
            {
                i1 = ((ViewHolder) (obj1)).mPreLayoutPosition;
            }
            if (i1 != i)
            {
                continue; /* Loop/switch isn't completed */
            }
            obj1.mFlags = 0x20 | ((ViewHolder) (obj1)).mFlags;
              goto _L12
            k++;
              goto _L13
_L11:
            if (!mAdapter.mHasStableIds)
            {
                break MISSING_BLOCK_LABEL_853;
            }
            k = mAdapterHelper.findPositionOffset(i, 0);
            if (k <= 0 || k >= mAdapter.getItemCount())
            {
                break MISSING_BLOCK_LABEL_853;
            }
            l2 = mAdapter.getItemId(k);
            k = 0;
_L14:
label1:
            {
                if (k >= j1)
                {
                    break MISSING_BLOCK_LABEL_853;
                }
                obj1 = (ViewHolder)mChangedScrap.get(k);
                if ((((ViewHolder) (obj1)).mFlags & 0x20) != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 || ((ViewHolder) (obj1)).mItemId != l2)
                {
                    break label1;
                }
                obj1.mFlags = 0x20 | ((ViewHolder) (obj1)).mFlags;
            }
              goto _L12
            k++;
              goto _L14
            obj1 = null;
              goto _L12
            k++;
              goto _L15
_L9:
            if (flag)
            {
                break MISSING_BLOCK_LABEL_1326;
            }
            obj2 = mChildHelper;
            i2 = ((ChildHelper) (obj2)).mHiddenViews.size();
            k = 0;
_L18:
            if (k >= i2) goto _L17; else goto _L16
_L16:
            obj1 = (View)((ChildHelper) (obj2)).mHiddenViews.get(k);
            obj3 = ((ChildHelper) (obj2)).mCallback.getChildViewHolder(((View) (obj1)));
            if (((ViewHolder) (obj3)).mPreLayoutPosition == -1)
            {
                j1 = ((ViewHolder) (obj3)).mPosition;
            } else
            {
                j1 = ((ViewHolder) (obj3)).mPreLayoutPosition;
            }
            if (j1 != i)
            {
                continue; /* Loop/switch isn't completed */
            }
            if ((((ViewHolder) (obj3)).mFlags & 4) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            if ((((ViewHolder) (obj3)).mFlags & 8) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
_L19:
            if (obj1 == null)
            {
                break MISSING_BLOCK_LABEL_1326;
            }
            obj2 = RecyclerView.getChildViewHolderInt(((View) (obj1)));
            obj3 = mChildHelper;
            k = ((ChildHelper) (obj3)).mCallback.indexOfChild(((View) (obj1)));
            if (k < 0)
            {
                throw new IllegalArgumentException((new StringBuilder("view is not a child, cannot hide ")).append(obj1).toString());
            }
            break MISSING_BLOCK_LABEL_1130;
            k++;
              goto _L18
_L17:
            obj1 = null;
              goto _L19
            if (!((ChildHelper) (obj3)).mBucket.get(k))
            {
                throw new RuntimeException((new StringBuilder("trying to unhide a view that was not hidden")).append(obj1).toString());
            }
            ((ChildHelper) (obj3)).mBucket.clear(k);
            if (((ChildHelper) (obj3)).mHiddenViews.remove(obj1))
            {
                ((ChildHelper) (obj3)).mCallback.onLeftHiddenState(((View) (obj1)));
            }
            k = mChildHelper.indexOfChild(((View) (obj1)));
            if (k == -1)
            {
                throw new IllegalStateException((new StringBuilder("layout index should not be -1 after unhiding a view:")).append(obj2).append(exceptionLabel()).toString());
            }
            obj3 = mChildHelper;
            k = ((ChildHelper) (obj3)).getOffset(k);
            ((ChildHelper) (obj3)).mBucket.remove(k);
            ((ChildHelper) (obj3)).mCallback.detachViewFromParent(k);
            scrapView(((View) (obj1)));
            obj2.mFlags = 0x2020 | ((ViewHolder) (obj2)).mFlags;
              goto _L20
            i2 = mCachedViews.size();
            k = 0;
_L23:
            if (k >= i2) goto _L22; else goto _L21
_L21:
            obj2 = (ViewHolder)mCachedViews.get(k);
            if ((((ViewHolder) (obj2)).mFlags & 4) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (j1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (((ViewHolder) (obj2)).mPreLayoutPosition == -1)
            {
                j1 = ((ViewHolder) (obj2)).mPosition;
            } else
            {
                j1 = ((ViewHolder) (obj2)).mPreLayoutPosition;
            }
            if (j1 != i)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!flag)
            {
                mCachedViews.remove(k);
            }
              goto _L20
            k++;
              goto _L23
_L22:
            obj2 = null;
              goto _L20
            int j2;
            j2 = mAdapter.getItemViewType(k2);
            obj2 = obj1;
            if (!mAdapter.mHasStableIds) goto _L25; else goto _L24
_L24:
            long l3;
            l3 = mAdapter.getItemId(k2);
            flag1 = mAttachedScrap.size() - 1;
_L32:
            if (flag1 < 0) goto _L27; else goto _L26
_L26:
            obj1 = (ViewHolder)mAttachedScrap.get(flag1);
            if (((ViewHolder) (obj1)).mItemId != l3)
            {
                continue; /* Loop/switch isn't completed */
            }
            boolean flag2;
            long l4;
            if ((((ViewHolder) (obj1)).mFlags & 0x20) != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (j2 != ((ViewHolder) (obj1)).mItemViewType) goto _L29; else goto _L28
_L28:
            obj1.mFlags = 0x20 | ((ViewHolder) (obj1)).mFlags;
            if ((((ViewHolder) (obj1)).mFlags & 8) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 != 0 && !mState.mInPreLayout)
            {
                obj1.mFlags = ((ViewHolder) (obj1)).mFlags & 0xfffffff1 | 2;
            }
_L33:
            obj2 = obj1;
            if (obj1 == null) goto _L25; else goto _L30
_L30:
            obj1.mPosition = k2;
            k = 1;
_L38:
            obj2 = obj1;
            if (obj1 == null)
            {
                if (mRecyclerPool == null)
                {
                    mRecyclerPool = new RecycledViewPool();
                }
                obj1 = (RecycledViewPool.ScrapData)mRecyclerPool.mScrap.get(j2);
                if (obj1 != null && !((RecycledViewPool.ScrapData) (obj1)).mScrapHeap.isEmpty())
                {
                    obj1 = ((RecycledViewPool.ScrapData) (obj1)).mScrapHeap;
                    obj1 = (ViewHolder)((ArrayList) (obj1)).remove(((ArrayList) (obj1)).size() - 1);
                } else
                {
                    obj1 = null;
                }
                obj2 = obj1;
                if (obj1 != null)
                {
                    ((ViewHolder) (obj1)).resetInternal();
                    obj2 = obj1;
                }
            }
            obj1 = obj2;
            flag1 = k;
            if (obj2 == null)
            {
                if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                {
                    l3 = System.nanoTime();
                } else
                {
                    l3 = 0L;
                }
                if (l != 0x7fffffffffffffffL)
                {
                    l4 = mRecyclerPool.getScrapDataForType(j2).mCreateRunningAverageNs;
                    if (l4 == 0L || l4 + l3 < l)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 == 0)
                    {
                        return null;
                    }
                }
                obj1 = mAdapter.createViewHolder(RecyclerView.this, j2);
                if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                {
                    obj2 = RecyclerView.findNestedRecyclerView(((ViewHolder) (obj1)).itemView);
                    if (obj2 != null)
                    {
                        obj1.mNestedRecyclerView = new WeakReference(obj2);
                    }
                }
                State state1;
                long l5;
                if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                {
                    l5 = System.nanoTime();
                } else
                {
                    l5 = 0L;
                }
                obj2 = mRecyclerPool;
                l3 = l5 - l3;
                obj2 = ((RecycledViewPool) (obj2)).getScrapDataForType(j2);
                l5 = ((RecycledViewPool.ScrapData) (obj2)).mCreateRunningAverageNs;
                if (l5 != 0L)
                {
                    l5 /= 4L;
                    l3 = l3 / 4L + l5 * 3L;
                }
                obj2.mCreateRunningAverageNs = l3;
                flag1 = k;
            }
              goto _L31
_L29:
            if (!flag)
            {
                mAttachedScrap.remove(flag1);
                removeDetachedView(((ViewHolder) (obj1)).itemView, false);
                obj1 = RecyclerView.getChildViewHolderInt(((ViewHolder) (obj1)).itemView);
                obj1.mScrapContainer = null;
                obj1.mInChangeScrap = false;
                obj1.mFlags = ((ViewHolder) (obj1)).mFlags & 0xffffffdf;
                recycleViewHolderInternal(((ViewHolder) (obj1)));
            }
            flag1--;
              goto _L32
_L27:
label2:
            {
                for (flag1 = mCachedViews.size() - 1; flag1 < 0; flag1--)
                {
                    break MISSING_BLOCK_LABEL_2235;
                }

                obj1 = (ViewHolder)mCachedViews.get(flag1);
                if (((ViewHolder) (obj1)).mItemId != l3)
                {
                    break MISSING_BLOCK_LABEL_2241;
                }
                if (j2 != ((ViewHolder) (obj1)).mItemViewType)
                {
                    break label2;
                }
                if (!flag)
                {
                    mCachedViews.remove(flag1);
                }
            }
              goto _L33
            if (flag)
            {
                break MISSING_BLOCK_LABEL_2241;
            }
            addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(flag1), true);
            mCachedViews.remove(flag1);
            obj1 = null;
              goto _L33
_L31:
            if (flag1 != 0 && !mState.mInPreLayout)
            {
                if ((0x2000 & ((ViewHolder) (obj1)).mFlags) != 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    obj1.mFlags = ((ViewHolder) (obj1)).mFlags & 0xffffdfff | 0;
                    if (mState.mRunSimpleAnimations)
                    {
                        ItemAnimator.buildAdapterChangeFlagsForAnimations(((ViewHolder) (obj1)));
                        obj2 = mItemAnimator;
                        state1 = mState;
                        ((ViewHolder) (obj1)).getUnmodifiedPayloads();
                        obj2 = ((ItemAnimator) (obj2))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNEQB4CTIN8BQICLHNIORCCLP5CQB5ESI5CQB5ET46UR34CLP3MIACD9GNCO9FELQ6IR1F9HKN6T1R55662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H4N8PBD85N6IRB1EHNN4929EHIMQI3FDHI6ASI9DPJ6UEO_0(((ViewHolder) (obj1)));
                        recordAnimationInfoIfBouncedHiddenView(((ViewHolder) (obj1)), ((ItemAnimator.ItemHolderInfo) (obj2)));
                    }
                }
            }
            if (!mState.mInPreLayout) goto _L35; else goto _L34
_L34:
            if ((((ViewHolder) (obj1)).mFlags & 1) != 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k == 0) goto _L35; else goto _L36
_L36:
            obj1.mPreLayoutPosition = i;
            i = 0;
_L37:
            obj2 = ((ViewHolder) (obj1)).itemView.getLayoutParams();
            int k1;
            long l6;
            if (obj2 == null)
            {
                obj2 = (LayoutParams)generateDefaultLayoutParams();
                ((ViewHolder) (obj1)).itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj2)));
            } else
            if (!checkLayoutParams(((android.view.ViewGroup.LayoutParams) (obj2))))
            {
                obj2 = (LayoutParams)generateLayoutParams(((android.view.ViewGroup.LayoutParams) (obj2)));
                ((ViewHolder) (obj1)).itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj2)));
            } else
            {
                obj2 = (LayoutParams)obj2;
            }
            obj2.mViewHolder = ((ViewHolder) (obj1));
            if (flag1 != 0 && i != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj2.mPendingInvalidate = flag;
            return ((ViewHolder) (obj1));
_L35:
label3:
            {
                if ((((ViewHolder) (obj1)).mFlags & 1) != 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    if ((((ViewHolder) (obj1)).mFlags & 2) != 0)
                    {
                        k = 1;
                    } else
                    {
                        k = 0;
                    }
                    if (k == 0)
                    {
                        if ((((ViewHolder) (obj1)).mFlags & 4) != 0)
                        {
                            k = 1;
                        } else
                        {
                            k = 0;
                        }
                        if (k == 0)
                        {
                            break label3;
                        }
                    }
                }
                k1 = mAdapterHelper.findPositionOffset(i, 0);
                obj1.mOwnerRecyclerView = RecyclerView.this;
                k = ((ViewHolder) (obj1)).mItemViewType;
                if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                {
                    l3 = System.nanoTime();
                } else
                {
                    l3 = 0L;
                }
                if (l != 0x7fffffffffffffffL)
                {
                    l6 = mRecyclerPool.getScrapDataForType(k).mBindRunningAverageNs;
                    if (l6 == 0L || l6 + l3 < l)
                    {
                        k = 1;
                    } else
                    {
                        k = 0;
                    }
                    if (k == 0)
                    {
                        i = 0;
                        continue; /* Loop/switch isn't completed */
                    }
                }
                obj2 = mAdapter;
                obj1.mPosition = k1;
                if (((Adapter) (obj2)).mHasStableIds)
                {
                    obj1.mItemId = ((Adapter) (obj2)).getItemId(k1);
                }
                obj1.mFlags = ((ViewHolder) (obj1)).mFlags & 0xfffffdf8 | 1;
                Trace.beginSection("RV OnBindView");
                ((Adapter) (obj2)).onBindViewHolder(((ViewHolder) (obj1)), k1, ((ViewHolder) (obj1)).getUnmodifiedPayloads());
                if (((ViewHolder) (obj1)).mPayloads != null)
                {
                    ((ViewHolder) (obj1)).mPayloads.clear();
                }
                obj1.mFlags = ((ViewHolder) (obj1)).mFlags & 0xfffffbff;
                obj2 = ((ViewHolder) (obj1)).itemView.getLayoutParams();
                if (obj2 instanceof LayoutParams)
                {
                    ((LayoutParams)obj2).mInsetsDirty = true;
                }
                Trace.endSection();
                if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                {
                    l = System.nanoTime();
                } else
                {
                    l = 0L;
                }
                obj2 = mRecyclerPool;
                k = ((ViewHolder) (obj1)).mItemViewType;
                l -= l3;
                obj2 = ((RecycledViewPool) (obj2)).getScrapDataForType(k);
                l3 = ((RecycledViewPool.ScrapData) (obj2)).mBindRunningAverageNs;
                if (l3 != 0L)
                {
                    l3 /= 4L;
                    l = l / 4L + l3 * 3L;
                }
                obj2.mBindRunningAverageNs = l;
                obj2 = RecyclerView.this;
                if (((RecyclerView) (obj2)).mAccessibilityManager != null && ((RecyclerView) (obj2)).mAccessibilityManager.isEnabled())
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    obj2 = ((ViewHolder) (obj1)).itemView;
                    if (ViewCompat.getImportantForAccessibility(((View) (obj2))) == 0)
                    {
                        ViewCompat.setImportantForAccessibility(((View) (obj2)), 1);
                    }
                    if (!ViewCompat.hasAccessibilityDelegate(((View) (obj2))))
                    {
                        obj1.mFlags = 0x4000 | ((ViewHolder) (obj1)).mFlags;
                        ViewCompat.setAccessibilityDelegate(((View) (obj2)), mAccessibilityDelegate.getItemDelegate());
                    }
                }
                if (mState.mInPreLayout)
                {
                    obj1.mPreLayoutPosition = i;
                }
                i = 1;
                continue; /* Loop/switch isn't completed */
            }
            i = 0;
            if (true) goto _L37; else goto _L25
_L25:
            obj1 = obj2;
              goto _L38
            flag1 = k;
              goto _L31
        }

        final void unscrapView(ViewHolder viewholder)
        {
            if (viewholder.mInChangeScrap)
            {
                mChangedScrap.remove(viewholder);
            } else
            {
                mAttachedScrap.remove(viewholder);
            }
            viewholder.mScrapContainer = null;
            viewholder.mInChangeScrap = false;
            viewholder.mFlags = viewholder.mFlags & 0xffffffdf;
        }

        final void updateViewCacheSize()
        {
            int i;
            if (mLayout != null)
            {
                i = mLayout.mPrefetchMaxCountObserved;
            } else
            {
                i = 0;
            }
            mViewCacheMax = i + mRequestedCacheMax;
            for (i = mCachedViews.size() - 1; i >= 0 && mCachedViews.size() > mViewCacheMax; i--)
            {
                addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(i), true);
                mCachedViews.remove(i);
            }

        }

        public Recycler()
        {
            this$0 = RecyclerView.this;
            super();
            mChangedScrap = null;
            mUnmodifiableAttachedScrap = Collections.unmodifiableList(mAttachedScrap);
            mRequestedCacheMax = 2;
            mViewCacheMax = 2;
        }
    }

    public static interface RecyclerListener
    {

        public abstract void onViewRecycled(ViewHolder viewholder);
    }

    final class RecyclerViewDataObserver extends AdapterDataObserver
    {

        private final RecyclerView this$0;

        private final void triggerUpdateProcessor()
        {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached)
            {
                ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
                return;
            } else
            {
                mAdapterUpdateDuringMeasure = true;
                requestLayout();
                return;
            }
        }

        public final void onChanged()
        {
            boolean flag = true;
            assertNotInLayoutOrScroll(null);
            mState.mStructureChanged = true;
            processDataSetCompletelyChanged(true);
            if (mAdapterHelper.mPendingUpdates.size() <= 0)
            {
                flag = false;
            }
            if (!flag)
            {
                requestLayout();
            }
        }

        public final void onItemRangeChanged(int i, int j, Object obj)
        {
            AdapterHelper adapterhelper;
            boolean flag;
            flag = true;
            assertNotInLayoutOrScroll(null);
            adapterhelper = mAdapterHelper;
            if (j > 0) goto _L2; else goto _L1
_L1:
            i = 0;
_L4:
            if (i != 0)
            {
                triggerUpdateProcessor();
            }
            return;
_L2:
            adapterhelper.mPendingUpdates.add(adapterhelper.obtainUpdateOp(4, i, j, obj));
            adapterhelper.mExistingUpdateTypes = adapterhelper.mExistingUpdateTypes | 4;
            if (adapterhelper.mPendingUpdates.size() != 1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ((flag) ? 1 : 0);
            if (true) goto _L4; else goto _L3
_L3:
            if (true) goto _L1; else goto _L5
_L5:
        }

        public final void onItemRangeInserted(int i, int j)
        {
            AdapterHelper adapterhelper;
            boolean flag;
            flag = true;
            assertNotInLayoutOrScroll(null);
            adapterhelper = mAdapterHelper;
            if (j > 0) goto _L2; else goto _L1
_L1:
            i = 0;
_L4:
            if (i != 0)
            {
                triggerUpdateProcessor();
            }
            return;
_L2:
            adapterhelper.mPendingUpdates.add(adapterhelper.obtainUpdateOp(1, i, j, null));
            adapterhelper.mExistingUpdateTypes = adapterhelper.mExistingUpdateTypes | 1;
            if (adapterhelper.mPendingUpdates.size() != 1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ((flag) ? 1 : 0);
            if (true) goto _L4; else goto _L3
_L3:
            if (true) goto _L1; else goto _L5
_L5:
        }

        public final void onItemRangeRemoved(int i, int j)
        {
            AdapterHelper adapterhelper;
            boolean flag;
            flag = true;
            assertNotInLayoutOrScroll(null);
            adapterhelper = mAdapterHelper;
            if (j > 0) goto _L2; else goto _L1
_L1:
            i = 0;
_L4:
            if (i != 0)
            {
                triggerUpdateProcessor();
            }
            return;
_L2:
            adapterhelper.mPendingUpdates.add(adapterhelper.obtainUpdateOp(2, i, j, null));
            adapterhelper.mExistingUpdateTypes = adapterhelper.mExistingUpdateTypes | 2;
            if (adapterhelper.mPendingUpdates.size() != 1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ((flag) ? 1 : 0);
            if (true) goto _L4; else goto _L3
_L3:
            if (true) goto _L1; else goto _L5
_L5:
        }

        RecyclerViewDataObserver()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public Parcelable mLayoutState;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(mLayoutState, 0);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            if (classloader == null)
            {
                classloader = android/support/v7/widget/RecyclerView$LayoutManager.getClassLoader();
            }
            mLayoutState = parcel.readParcelable(classloader);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
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

    public static final class State
    {

        public int mDeletedInvisibleItemCountSincePreviousLayout;
        public long mFocusedItemId;
        public int mFocusedItemPosition;
        public int mFocusedSubChildId;
        public boolean mInPreLayout;
        public boolean mIsMeasuring;
        public int mItemCount;
        public int mLayoutStep;
        public int mPreviousLayoutItemCount;
        public boolean mRunPredictiveAnimations;
        public boolean mRunSimpleAnimations;
        public boolean mStructureChanged;
        public int mTargetPosition;
        public boolean mTrackOldChangeHolders;

        final void assertLayoutStep(int i)
        {
            if ((mLayoutStep & i) == 0)
            {
                throw new IllegalStateException((new StringBuilder("Layout state should be one of ")).append(Integer.toBinaryString(i)).append(" but it is ").append(Integer.toBinaryString(mLayoutStep)).toString());
            } else
            {
                return;
            }
        }

        public final String toString()
        {
            return (new StringBuilder("State{mTargetPosition=")).append(mTargetPosition).append(", mData=").append(null).append(", mItemCount=").append(mItemCount).append(", mIsMeasuring=").append(mIsMeasuring).append(", mPreviousLayoutItemCount=").append(mPreviousLayoutItemCount).append(", mDeletedInvisibleItemCountSincePreviousLayout=").append(mDeletedInvisibleItemCountSincePreviousLayout).append(", mStructureChanged=").append(mStructureChanged).append(", mInPreLayout=").append(mInPreLayout).append(", mRunSimpleAnimations=").append(mRunSimpleAnimations).append(", mRunPredictiveAnimations=").append(mRunPredictiveAnimations).append('}').toString();
        }

        public State()
        {
            mTargetPosition = -1;
            mPreviousLayoutItemCount = 0;
            mDeletedInvisibleItemCountSincePreviousLayout = 0;
            mLayoutStep = 1;
            mItemCount = 0;
            mStructureChanged = false;
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
            mRunSimpleAnimations = false;
            mRunPredictiveAnimations = false;
        }
    }

    final class ViewFlinger
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
            LayoutManager layoutmanager = mLayout;
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
                if (!awakenScrollBars())
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
                        obj.mScrollingChildHelper = new NestedScrollingChildHelper(((View) (obj)));
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
                    if (((GapWorker.LayoutPrefetchRegistryImpl) (obj)).mPrefetchArray != null)
                    {
                        Arrays.fill(((GapWorker.LayoutPrefetchRegistryImpl) (obj)).mPrefetchArray, -1);
                    }
                    obj.mCount = 0;
                }
                obj = RecyclerView.this;
                if (((RecyclerView) (obj)).mScrollingChildHelper == null)
                {
                    obj.mScrollingChildHelper = new NestedScrollingChildHelper(((View) (obj)));
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
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                mScroller.computeScrollOffset();
            }
            postOnAnimation();
        }

        ViewFlinger()
        {
            this$0 = RecyclerView.this;
            super();
            mInterpolator = RecyclerView.sQuinticInterpolator;
            mEatRunOnAnimationRequest = false;
            mReSchedulePostAnimationCallback = false;
            mScroller = new OverScroller(getContext(), RecyclerView.sQuinticInterpolator);
        }
    }

    public static class ViewHolder
    {

        private static final List FULLUPDATE_PAYLOADS;
        public final View itemView;
        public int mFlags;
        public boolean mInChangeScrap;
        private int mIsRecyclableCount;
        public long mItemId;
        public int mItemViewType;
        public WeakReference mNestedRecyclerView;
        public int mOldPosition;
        public RecyclerView mOwnerRecyclerView;
        public List mPayloads;
        public int mPendingAccessibilityState;
        public int mPosition;
        public int mPreLayoutPosition;
        public Recycler mScrapContainer;
        public ViewHolder mShadowedHolder;
        public ViewHolder mShadowingHolder;
        private List mUnmodifiedPayloads;
        public int mWasImportantForAccessibilityBeforeHidden;

        public final void addChangePayload(Object obj)
        {
            if (obj == null)
            {
                mFlags = 0x400 | mFlags;
            } else
            if ((mFlags & 0x400) == 0)
            {
                if (mPayloads == null)
                {
                    mPayloads = new ArrayList();
                    mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
                }
                mPayloads.add(obj);
                return;
            }
        }

        final List getUnmodifiedPayloads()
        {
            if ((mFlags & 0x400) == 0)
            {
                if (mPayloads == null || mPayloads.size() == 0)
                {
                    return FULLUPDATE_PAYLOADS;
                } else
                {
                    return mUnmodifiedPayloads;
                }
            } else
            {
                return FULLUPDATE_PAYLOADS;
            }
        }

        final void offsetPosition(int i, boolean flag)
        {
            if (mOldPosition == -1)
            {
                mOldPosition = mPosition;
            }
            if (mPreLayoutPosition == -1)
            {
                mPreLayoutPosition = mPosition;
            }
            if (flag)
            {
                mPreLayoutPosition = mPreLayoutPosition + i;
            }
            mPosition = mPosition + i;
            if (itemView.getLayoutParams() != null)
            {
                ((LayoutParams)itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        final void resetInternal()
        {
            mFlags = 0;
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mPreLayoutPosition = -1;
            mIsRecyclableCount = 0;
            mShadowedHolder = null;
            mShadowingHolder = null;
            if (mPayloads != null)
            {
                mPayloads.clear();
            }
            mFlags = mFlags & 0xfffffbff;
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public final void setIsRecyclable(boolean flag)
        {
            int i;
            if (flag)
            {
                i = mIsRecyclableCount - 1;
            } else
            {
                i = mIsRecyclableCount + 1;
            }
            mIsRecyclableCount = i;
            if (mIsRecyclableCount < 0)
            {
                mIsRecyclableCount = 0;
                Log.e("View", (new StringBuilder("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ")).append(this).toString());
            } else
            {
                if (!flag && mIsRecyclableCount == 1)
                {
                    mFlags = mFlags | 0x10;
                    return;
                }
                if (flag && mIsRecyclableCount == 0)
                {
                    mFlags = mFlags & 0xffffffef;
                    return;
                }
            }
        }

        public String toString()
        {
            boolean flag;
            StringBuilder stringbuilder = new StringBuilder((new StringBuilder("ViewHolder{")).append(Integer.toHexString(hashCode())).append(" position=").append(mPosition).append(" id=").append(mItemId).append(", oldPos=").append(mOldPosition).append(", pLpos:").append(mPreLayoutPosition).toString());
            if (mScrapContainer != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                StringBuilder stringbuilder1 = stringbuilder.append(" scrap ");
                String s;
                if (mInChangeScrap)
                {
                    s = "[changeScrap]";
                } else
                {
                    s = "[attachedScrap]";
                }
                stringbuilder1.append(s);
            }
            if ((mFlags & 4) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                stringbuilder.append(" invalid");
            }
            if ((mFlags & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                stringbuilder.append(" unbound");
            }
            if ((mFlags & 2) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                stringbuilder.append(" update");
            }
            if ((mFlags & 8) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                stringbuilder.append(" removed");
            }
            if ((mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                stringbuilder.append(" ignored");
            }
            if ((mFlags & 0x100) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                stringbuilder.append(" tmpDetached");
            }
            if ((mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(itemView))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                stringbuilder.append((new StringBuilder(" not recyclable(")).append(mIsRecyclableCount).append(")").toString());
            }
            if ((mFlags & 0x200) != 0) goto _L2; else goto _L1
_L1:
            if ((mFlags & 4) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L3; else goto _L2
_L2:
            flag = true;
_L5:
            if (flag)
            {
                stringbuilder.append(" undefined adapter position");
            }
            if (itemView.getParent() == null)
            {
                stringbuilder.append(" no parent");
            }
            stringbuilder.append("}");
            return stringbuilder.toString();
_L3:
            flag = false;
            if (true) goto _L5; else goto _L4
_L4:
        }

        static 
        {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }

        public ViewHolder(View view)
        {
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mItemViewType = -1;
            mPreLayoutPosition = -1;
            mShadowedHolder = null;
            mShadowingHolder = null;
            mPayloads = null;
            mUnmodifiedPayloads = null;
            mIsRecyclableCount = 0;
            mScrapContainer = null;
            mInChangeScrap = false;
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            if (view == null)
            {
                throw new IllegalArgumentException("itemView may not be null");
            } else
            {
                itemView = view;
                return;
            }
        }
    }


    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    public static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final int CLIP_TO_PADDING_ATTR[] = {
        0x10100eb
    };
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    public static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    private static final Class LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE[];
    private static final int NESTED_SCROLLING_ATTRS[] = {
        0x1010436
    };
    public static final boolean POST_UPDATES_ON_ANIMATION = true;
    public static final Interpolator sQuinticInterpolator = new _cls3();
    public RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public OnItemTouchListener mActiveOnItemTouchListener;
    public Adapter mAdapter;
    public AdapterHelper mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    public ChildHelper mChildHelper;
    private boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    public boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private EdgeEffectFactory mEdgeEffectFactory;
    private boolean mEnableFastScroller;
    public boolean mFirstLayoutComplete;
    public GapWorker mGapWorker;
    public boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    public int mInterceptRequestLayoutDepth;
    public boolean mIsAttached;
    public ItemAnimator mItemAnimator;
    public ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    public Runnable mItemAnimatorRunner;
    public final ArrayList mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    public LayoutManager mLayout;
    public boolean mLayoutFrozen;
    public int mLayoutOrScrollCounter;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int mMinMaxLayoutPositions[];
    private final int mNestedOffsets[];
    public final RecyclerViewDataObserver mObserver;
    public OnFlingListener mOnFlingListener;
    public final ArrayList mOnItemTouchListeners;
    private final List mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    public boolean mPostedAnimatorRunner;
    public GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    public RecyclerListener mRecyclerListener;
    public EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    public final int mScrollConsumed[];
    private OnScrollListener mScrollListener;
    public List mScrollListeners;
    private final int mScrollOffset[];
    private int mScrollPointerId;
    private int mScrollState;
    public final int mScrollStepConsumed[];
    public NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    public final Rect mTempRect;
    private final Rect mTempRect2;
    public final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    private int mTouchSlop;
    public final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    public final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    public final ViewInfoStore mViewInfoStore;

    public RecyclerView(Context context)
    {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeset, int i)
    {
        Object obj1;
        boolean flag;
        super(context, attributeset, i);
        mObserver = new RecyclerViewDataObserver();
        mRecycler = new Recycler();
        mViewInfoStore = new ViewInfoStore();
        mUpdateChildViewsRunnable = new _cls1();
        mTempRect = new Rect();
        mTempRect2 = new Rect();
        mTempRectF = new RectF();
        mItemDecorations = new ArrayList();
        mOnItemTouchListeners = new ArrayList();
        mInterceptRequestLayoutDepth = 0;
        mDataSetHasChangedAfterLayout = false;
        mDispatchItemsChangedEvent = false;
        mLayoutOrScrollCounter = 0;
        mDispatchScrollCounter = 0;
        mEdgeEffectFactory = new EdgeEffectFactory();
        mItemAnimator = new DefaultItemAnimator();
        mScrollState = 0;
        mScrollPointerId = -1;
        mScaledHorizontalScrollFactor = 1.401298E-45F;
        mScaledVerticalScrollFactor = 1.401298E-45F;
        mPreserveFocusAfterLayout = true;
        mViewFlinger = new ViewFlinger();
        Object obj;
        if (ALLOW_THREAD_GAP_WORK)
        {
            obj = new GapWorker.LayoutPrefetchRegistryImpl();
        } else
        {
            obj = null;
        }
        mPrefetchRegistry = ((GapWorker.LayoutPrefetchRegistryImpl) (obj));
        mState = new State();
        mItemsAddedOrRemoved = false;
        mItemsChanged = false;
        mItemAnimatorListener = new ItemAnimatorRestoreListener();
        mPostedAnimatorRunner = false;
        mMinMaxLayoutPositions = new int[2];
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mNestedOffsets = new int[2];
        mScrollStepConsumed = new int[2];
        mPendingAccessibilityImportanceChange = new ArrayList();
        mItemAnimatorRunner = new _cls2();
        mViewInfoProcessCallback = new _cls4();
        if (attributeset != null)
        {
            obj = context.obtainStyledAttributes(attributeset, CLIP_TO_PADDING_ATTR, i, 0);
            mClipToPadding = ((TypedArray) (obj)).getBoolean(0, true);
            ((TypedArray) (obj)).recycle();
        } else
        {
            mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        obj = ViewConfiguration.get(context);
        mTouchSlop = ((ViewConfiguration) (obj)).getScaledTouchSlop();
        mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(((ViewConfiguration) (obj)), context);
        mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(((ViewConfiguration) (obj)), context);
        mMinFlingVelocity = ((ViewConfiguration) (obj)).getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = ((ViewConfiguration) (obj)).getScaledMaximumFlingVelocity();
        if (getOverScrollMode() == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setWillNotDraw(flag);
        mItemAnimator.mListener = mItemAnimatorListener;
        mAdapterHelper = new AdapterHelper(new _cls6());
        mChildHelper = new ChildHelper(new _cls5());
        if (ViewCompat.getImportantForAutofill(this) == 0)
        {
            ViewCompat.setImportantForAutofill(this, 8);
        }
        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        mAccessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
        mAccessibilityDelegate = new RecyclerViewAccessibilityDelegate(this);
        ViewCompat.setAccessibilityDelegate(this, mAccessibilityDelegate);
        flag = true;
        if (attributeset == null)
        {
            break MISSING_BLOCK_LABEL_1289;
        }
        obj = context.obtainStyledAttributes(attributeset, android.support.v7.recyclerview.R.styleable.RecyclerView, i, 0);
        obj1 = ((TypedArray) (obj)).getString(android.support.v7.recyclerview.R.styleable.RecyclerView_layoutManager);
        if (((TypedArray) (obj)).getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_android_descendantFocusability, -1) == -1)
        {
            setDescendantFocusability(0x40000);
        }
        mEnableFastScroller = ((TypedArray) (obj)).getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollEnabled, false);
        if (mEnableFastScroller)
        {
            StateListDrawable statelistdrawable = (StateListDrawable)((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollVerticalThumbDrawable);
            android.graphics.drawable.Drawable drawable = ((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollVerticalTrackDrawable);
            StateListDrawable statelistdrawable1 = (StateListDrawable)((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable);
            android.graphics.drawable.Drawable drawable1 = ((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable);
            if (statelistdrawable == null || drawable == null || statelistdrawable1 == null || drawable1 == null)
            {
                throw new IllegalArgumentException((new StringBuilder("Trying to set fast scroller without both required drawables.")).append(exceptionLabel()).toString());
            }
            Resources resources = getContext().getResources();
            new FastScroller(this, statelistdrawable, drawable, statelistdrawable1, drawable1, resources.getDimensionPixelSize(0x7f0e015f), resources.getDimensionPixelSize(0x7f0e0161), resources.getDimensionPixelOffset(0x7f0e0160));
        }
        ((TypedArray) (obj)).recycle();
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        String s = ((String) (obj1)).trim();
        if (s.isEmpty()) goto _L2; else goto _L3
_L3:
        Constructor constructor;
        Class class1;
        if (s.charAt(0) == '.')
        {
            s = (new StringBuilder()).append(context.getPackageName()).append(s).toString();
        } else
        if (!s.contains("."))
        {
            s = (new StringBuilder()).append(android/support/v7/widget/RecyclerView.getPackage().getName()).append('.').append(s).toString();
        }
        if (!isInEditMode()) goto _L5; else goto _L4
_L4:
        obj1 = getClass().getClassLoader();
_L6:
        class1 = ((ClassLoader) (obj1)).loadClass(s).asSubclass(android/support/v7/widget/RecyclerView$LayoutManager);
        constructor = class1.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
        obj1 = ((Object) (new Object[4]));
        obj1[0] = context;
        obj1[1] = attributeset;
        obj1[2] = Integer.valueOf(i);
        obj1[3] = Integer.valueOf(0);
_L7:
        constructor.setAccessible(true);
        setLayoutManager((LayoutManager)constructor.newInstance(((Object []) (obj1))));
_L2:
        context = context.obtainStyledAttributes(attributeset, NESTED_SCROLLING_ATTRS, i, 0);
        flag = context.getBoolean(0, true);
        context.recycle();
_L8:
        setNestedScrollingEnabled(flag);
        return;
_L5:
        NoSuchMethodException nosuchmethodexception;
        try
        {
            obj1 = context.getClassLoader();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Unable to find LayoutManager ").append(s).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Cannot access non-public constructor ").append(s).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Class is not a LayoutManager ").append(s).toString(), context);
        }
          goto _L6
        nosuchmethodexception;
        constructor = class1.getConstructor(new Class[0]);
        nosuchmethodexception = null;
          goto _L7
        context;
        context.initCause(nosuchmethodexception);
        throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error creating LayoutManager ").append(s).toString(), context);
        setDescendantFocusability(0x40000);
          goto _L8
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder viewholder)
    {
        if (viewholder.mNestedRecyclerView == null) goto _L2; else goto _L1
_L1:
        Object obj = (View)viewholder.mNestedRecyclerView.get();
_L5:
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != viewholder.itemView) goto _L3; else goto _L2
_L2:
        return;
_L3:
        obj = ((View) (obj)).getParent();
        if (obj instanceof View)
        {
            obj = (View)obj;
        } else
        {
            obj = null;
        }
        if (true) goto _L5; else goto _L4
_L4:
        viewholder.mNestedRecyclerView = null;
        return;
    }

    private final void clearOldPositions()
    {
        boolean flag1 = false;
        int k1 = mChildHelper.mCallback.getChildCount();
        int i = 0;
        while (i < k1) 
        {
            Object obj = mChildHelper.mCallback.getChildAt(i);
            boolean flag;
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj.mOldPosition = -1;
                obj.mPreLayoutPosition = -1;
            }
            i++;
        }
        Recycler recycler = mRecycler;
        int i1 = recycler.mCachedViews.size();
        for (int j = 0; j < i1; j++)
        {
            ViewHolder viewholder = (ViewHolder)recycler.mCachedViews.get(j);
            viewholder.mOldPosition = -1;
            viewholder.mPreLayoutPosition = -1;
        }

        i1 = recycler.mAttachedScrap.size();
        for (int k = 0; k < i1; k++)
        {
            ViewHolder viewholder1 = (ViewHolder)recycler.mAttachedScrap.get(k);
            viewholder1.mOldPosition = -1;
            viewholder1.mPreLayoutPosition = -1;
        }

        if (recycler.mChangedScrap != null)
        {
            int j1 = recycler.mChangedScrap.size();
            for (int l = ((flag1) ? 1 : 0); l < j1; l++)
            {
                ViewHolder viewholder2 = (ViewHolder)recycler.mChangedScrap.get(l);
                viewholder2.mOldPosition = -1;
                viewholder2.mPreLayoutPosition = -1;
            }

        }
    }

    private final void dispatchLayout()
    {
        Object obj;
        Object obj1;
        Object obj2;
        int i1;
        boolean flag2;
        obj2 = null;
        flag2 = false;
        if (mAdapter == null)
        {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (mLayout == null)
        {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        mState.mIsMeasuring = false;
        View view;
        ViewInfoStore.InfoRecord inforecord;
        int i;
        if (mState.mLayoutStep == 1)
        {
            dispatchLayoutStep1();
            mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getHeight(), 0x40000000));
            dispatchLayoutStep2();
        } else
        {
            obj = mAdapterHelper;
            boolean flag;
            if (!((AdapterHelper) (obj)).mPostponedList.isEmpty() && !((AdapterHelper) (obj)).mPendingUpdates.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || mLayout.mWidth != getWidth() || mLayout.mHeight != getHeight())
            {
                mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getHeight(), 0x40000000));
                dispatchLayoutStep2();
            } else
            {
                mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getHeight(), 0x40000000));
            }
        }
        mState.assertLayoutStep(4);
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
        mState.mLayoutStep = 1;
        if (!mState.mRunSimpleAnimations)
        {
            break MISSING_BLOCK_LABEL_1344;
        }
        obj = mChildHelper;
        i = ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size() - 1;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_1067;
        }
        obj = mChildHelper;
        i1 = ((ChildHelper) (obj)).getOffset(i);
        obj = ((ChildHelper) (obj)).mCallback.getChildAt(i1);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        }
        if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 == 0)
        {
            ViewHolder viewholder;
            int j1;
            long l2;
            if (mAdapter.mHasStableIds)
            {
                l2 = ((ViewHolder) (obj)).mItemId;
            } else
            {
                l2 = ((ViewHolder) (obj)).mPosition;
            }
            obj1 = mItemAnimator;
            obj1 = new ItemAnimator.ItemHolderInfo();
            view = ((ViewHolder) (obj)).itemView;
            obj1.left = view.getLeft();
            obj1.top = view.getTop();
            view.getRight();
            view.getBottom();
            viewholder = (ViewHolder)mViewInfoStore.mOldChangedHolders.get(l2);
            if (viewholder == null)
            {
                break MISSING_BLOCK_LABEL_1055;
            }
            if ((viewholder.mFlags & 0x80) != 0)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (i1 != 0)
            {
                break MISSING_BLOCK_LABEL_1055;
            }
            inforecord = (ViewInfoStore.InfoRecord)mViewInfoStore.mLayoutHolderMap.get(viewholder);
            if (inforecord != null && (inforecord.flags & 1) != 0)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            inforecord = (ViewInfoStore.InfoRecord)mViewInfoStore.mLayoutHolderMap.get(obj);
            if (inforecord != null && (inforecord.flags & 1) != 0)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            if (i1 != 0 && viewholder == obj)
            {
                mViewInfoStore.addToPostLayout(((ViewHolder) (obj)), ((ItemAnimator.ItemHolderInfo) (obj1)));
            } else
            {
                ItemAnimator.ItemHolderInfo itemholderinfo = mViewInfoStore.popFromLayoutStep(viewholder, 4);
                mViewInfoStore.addToPostLayout(((ViewHolder) (obj)), ((ItemAnimator.ItemHolderInfo) (obj1)));
                obj1 = mViewInfoStore.popFromLayoutStep(((ViewHolder) (obj)), 8);
                if (itemholderinfo == null)
                {
                    obj1 = mChildHelper;
                    j1 = ((ChildHelper) (obj1)).mCallback.getChildCount();
                    int l1 = ((ChildHelper) (obj1)).mHiddenViews.size();
                    for (i1 = 0; i1 < j1 - l1; i1++)
                    {
                        obj1 = mChildHelper;
                        int i2 = ((ChildHelper) (obj1)).getOffset(i1);
                        obj1 = ((ChildHelper) (obj1)).mCallback.getChildAt(i2);
                        long l4;
                        if (obj1 == null)
                        {
                            obj1 = null;
                        } else
                        {
                            obj1 = ((LayoutParams)((View) (obj1)).getLayoutParams()).mViewHolder;
                        }
                        if (obj1 == obj)
                        {
                            continue;
                        }
                        if (mAdapter.mHasStableIds)
                        {
                            l4 = ((ViewHolder) (obj1)).mItemId;
                        } else
                        {
                            l4 = ((ViewHolder) (obj1)).mPosition;
                        }
                        if (l4 != l2)
                        {
                            continue;
                        }
                        if (mAdapter != null && mAdapter.mHasStableIds)
                        {
                            throw new IllegalStateException((new StringBuilder("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:")).append(obj1).append(" \n View Holder 2:").append(obj).append(exceptionLabel()).toString());
                        } else
                        {
                            throw new IllegalStateException((new StringBuilder("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:")).append(obj1).append(" \n View Holder 2:").append(obj).append(exceptionLabel()).toString());
                        }
                    }

                    Log.e("RecyclerView", (new StringBuilder("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ")).append(viewholder).append(" cannot be found but it is necessary for ").append(obj).append(exceptionLabel()).toString());
                } else
                {
                    viewholder.setIsRecyclable(false);
                    if (i1 != 0)
                    {
                        addAnimatingView(viewholder);
                    }
                    if (viewholder != obj)
                    {
                        if (j1 != 0)
                        {
                            addAnimatingView(((ViewHolder) (obj)));
                        }
                        viewholder.mShadowedHolder = ((ViewHolder) (obj));
                        addAnimatingView(viewholder);
                        mRecycler.unscrapView(viewholder);
                        ((ViewHolder) (obj)).setIsRecyclable(false);
                        obj.mShadowingHolder = viewholder;
                    }
                    if (mItemAnimator.animateChange(viewholder, ((ViewHolder) (obj)), itemholderinfo, ((ItemAnimator.ItemHolderInfo) (obj1))) && !mPostedAnimatorRunner && mIsAttached)
                    {
                        ViewCompat.postOnAnimation(this, mItemAnimatorRunner);
                        mPostedAnimatorRunner = true;
                    }
                }
            }
        }
_L2:
        i--;
        break MISSING_BLOCK_LABEL_189;
        mViewInfoStore.addToPostLayout(((ViewHolder) (obj)), ((ItemAnimator.ItemHolderInfo) (obj1)));
        if (true) goto _L2; else goto _L1
_L1:
        obj = mViewInfoStore;
        obj1 = mViewInfoProcessCallback;
        int j = ((ViewInfoStore) (obj)).mLayoutHolderMap.size() - 1;
        while (j >= 0) 
        {
            ViewHolder viewholder1 = (ViewHolder)((SimpleArrayMap) (((ViewInfoStore) (obj)).mLayoutHolderMap)).mArray[j << 1];
            ViewInfoStore.InfoRecord inforecord1 = (ViewInfoStore.InfoRecord)((ViewInfoStore) (obj)).mLayoutHolderMap.removeAt(j);
            if ((inforecord1.flags & 3) == 3)
            {
                ((ViewInfoStore.ProcessCallback) (obj1)).unused(viewholder1);
            } else
            if ((inforecord1.flags & 1) != 0)
            {
                if (inforecord1.preInfo == null)
                {
                    ((ViewInfoStore.ProcessCallback) (obj1)).unused(viewholder1);
                } else
                {
                    ((ViewInfoStore.ProcessCallback) (obj1)).processDisappeared(viewholder1, inforecord1.preInfo, inforecord1.postInfo);
                }
            } else
            if ((inforecord1.flags & 0xe) == 14)
            {
                ((ViewInfoStore.ProcessCallback) (obj1)).processAppeared(viewholder1, inforecord1.preInfo, inforecord1.postInfo);
            } else
            if ((inforecord1.flags & 0xc) == 12)
            {
                ((ViewInfoStore.ProcessCallback) (obj1)).processPersistent(viewholder1, inforecord1.preInfo, inforecord1.postInfo);
            } else
            if ((inforecord1.flags & 4) != 0)
            {
                ((ViewInfoStore.ProcessCallback) (obj1)).processDisappeared(viewholder1, inforecord1.preInfo, null);
            } else
            if ((inforecord1.flags & 8) != 0)
            {
                ((ViewInfoStore.ProcessCallback) (obj1)).processAppeared(viewholder1, inforecord1.preInfo, inforecord1.postInfo);
            } else
            {
                i1 = inforecord1.flags;
            }
            ViewInfoStore.InfoRecord.recycle(inforecord1);
            j--;
        }
        mLayout.removeAndRecycleScrapInt(mRecycler);
        mState.mPreviousLayoutItemCount = mState.mItemCount;
        mDataSetHasChangedAfterLayout = false;
        mDispatchItemsChangedEvent = false;
        mState.mRunSimpleAnimations = false;
        mState.mRunPredictiveAnimations = false;
        mLayout.mRequestedSimpleAnimations = false;
        if (mRecycler.mChangedScrap != null)
        {
            mRecycler.mChangedScrap.clear();
        }
        if (mLayout.mPrefetchMaxObservedInInitialPrefetch)
        {
            mLayout.mPrefetchMaxCountObserved = 0;
            mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            mRecycler.updateViewCacheSize();
        }
        mLayout.onLayoutCompleted(mState);
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        obj = mViewInfoStore;
        ((ViewInfoStore) (obj)).mLayoutHolderMap.clear();
        ((ViewInfoStore) (obj)).mOldChangedHolders.clear();
        int k = mMinMaxLayoutPositions[0];
        i1 = mMinMaxLayoutPositions[1];
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        boolean flag1;
        if (mMinMaxLayoutPositions[0] != k || mMinMaxLayoutPositions[1] != i1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            dispatchOnScrolled(0, 0);
        }
        if (mPreserveFocusAfterLayout && mAdapter != null && hasFocus() && getDescendantFocusability() != 0x60000 && (getDescendantFocusability() != 0x20000 || !isFocused())) goto _L4; else goto _L3
_L3:
        mState.mFocusedItemId = -1L;
        mState.mFocusedItemPosition = -1;
        mState.mFocusedSubChildId = -1;
        return;
_L4:
        if (isFocused()) goto _L6; else goto _L5
_L5:
        obj = getFocusedChild();
        if (!mChildHelper.mHiddenViews.contains(obj)) goto _L3; else goto _L6
_L6:
        long l3;
        if (mState.mFocusedItemId == -1L || !mAdapter.mHasStableIds)
        {
            break MISSING_BLOCK_LABEL_2158;
        }
        l3 = mState.mFocusedItemId;
        if (mAdapter != null && mAdapter.mHasStableIds) goto _L8; else goto _L7
_L7:
        obj = null;
_L20:
        if (obj == null) goto _L10; else goto _L9
_L9:
        View view1;
        obj1 = mChildHelper;
        view1 = ((ViewHolder) (obj)).itemView;
        if (!((ChildHelper) (obj1)).mHiddenViews.contains(view1) && ((ViewHolder) (obj)).itemView.hasFocusable()) goto _L11; else goto _L10
_L10:
        obj = mChildHelper;
        if (((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size() <= 0) goto _L13; else goto _L12
_L12:
        int l;
        int k1;
        l = ((flag2) ? 1 : 0);
        if (mState.mFocusedItemPosition != -1)
        {
            l = mState.mFocusedItemPosition;
        }
        obj = mState;
        ChildHelper childhelper;
        View view2;
        if (((State) (obj)).mInPreLayout)
        {
            i1 = ((State) (obj)).mPreviousLayoutItemCount - ((State) (obj)).mDeletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            i1 = ((State) (obj)).mItemCount;
        }
        k1 = l;
_L26:
        if (k1 >= i1) goto _L15; else goto _L14
_L14:
        obj = findViewHolderForAdapterPosition(k1);
        if (obj == null) goto _L15; else goto _L16
_L16:
        if (!((ViewHolder) (obj)).itemView.hasFocusable()) goto _L18; else goto _L17
_L17:
        obj = ((ViewHolder) (obj)).itemView;
_L27:
        if (obj != null)
        {
            if ((long)mState.mFocusedSubChildId != -1L)
            {
                obj1 = ((View) (obj)).findViewById(mState.mFocusedSubChildId);
                if (obj1 != null && ((View) (obj1)).isFocusable())
                {
                    obj = obj1;
                }
            }
            ((View) (obj)).requestFocus();
        }
          goto _L3
_L8:
        k1 = mChildHelper.mCallback.getChildCount();
        l = 0;
        obj = null;
_L25:
        if (l >= k1) goto _L20; else goto _L19
_L19:
        obj1 = mChildHelper.mCallback.getChildAt(l);
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((LayoutParams)((View) (obj1)).getLayoutParams()).mViewHolder;
        }
        if (obj1 == null) goto _L22; else goto _L21
_L21:
        if ((((ViewHolder) (obj1)).mFlags & 8) != 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0 || ((ViewHolder) (obj1)).mItemId != l3) goto _L22; else goto _L23
_L23:
        childhelper = mChildHelper;
        view2 = ((ViewHolder) (obj1)).itemView;
        obj = obj1;
        if (!childhelper.mHiddenViews.contains(view2)) goto _L20; else goto _L24
_L24:
        l++;
        obj = obj1;
          goto _L25
_L18:
        k1++;
          goto _L26
_L15:
        l = Math.min(i1, l) - 1;
_L28:
        obj = obj2;
        if (l >= 0)
        {
            obj1 = findViewHolderForAdapterPosition(l);
            obj = obj2;
            if (obj1 != null)
            {
label0:
                {
                    if (!((ViewHolder) (obj1)).itemView.hasFocusable())
                    {
                        break label0;
                    }
                    obj = ((ViewHolder) (obj1)).itemView;
                }
            }
        }
          goto _L27
        l--;
          goto _L28
_L11:
        obj = ((ViewHolder) (obj)).itemView;
          goto _L27
_L13:
        obj = null;
          goto _L27
_L22:
        obj1 = obj;
          goto _L24
        obj = null;
          goto _L20
    }

    private final void dispatchLayoutStep1()
    {
        long l1 = -1L;
        mState.assertLayoutStep(1);
        fillRemainingScrollValues(mState);
        mState.mIsMeasuring = false;
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        Object obj = mViewInfoStore;
        ((ViewInfoStore) (obj)).mLayoutHolderMap.clear();
        ((ViewInfoStore) (obj)).mOldChangedHolders.clear();
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
        processAdapterUpdatesAndSetAnimationFlags();
        Object obj1;
        Object obj2;
        Object obj3;
        ViewInfoStore viewinfostore;
        int i;
        int j;
        boolean flag;
        int k;
        int l;
        int i1;
        boolean flag1;
        if (mPreserveFocusAfterLayout && hasFocus() && mAdapter != null)
        {
            obj = getFocusedChild();
        } else
        {
            obj = null;
        }
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = findContainingItemView(((View) (obj)));
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = getChildViewHolder(((View) (obj)));
            }
        }
        if (obj != null) goto _L2; else goto _L1
_L1:
        mState.mFocusedItemId = -1L;
        mState.mFocusedItemPosition = -1;
        mState.mFocusedSubChildId = -1;
_L12:
        obj = mState;
        if (mState.mRunSimpleAnimations && mItemsChanged)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj.mTrackOldChangeHolders = flag1;
        mItemsChanged = false;
        mItemsAddedOrRemoved = false;
        mState.mInPreLayout = mState.mRunPredictiveAnimations;
        mState.mItemCount = mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        if (mState.mRunSimpleAnimations)
        {
            obj = mChildHelper;
            k = ((ChildHelper) (obj)).mCallback.getChildCount();
            l = ((ChildHelper) (obj)).mHiddenViews.size();
            i = 0;
            while (i < k - l) 
            {
                obj = mChildHelper;
                j = ((ChildHelper) (obj)).getOffset(i);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                }
                if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0)
                {
                    if ((((ViewHolder) (obj)).mFlags & 4) != 0)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j == 0 || mAdapter.mHasStableIds)
                    {
                        obj1 = mItemAnimator;
                        ItemAnimator.buildAdapterChangeFlagsForAnimations(((ViewHolder) (obj)));
                        ((ViewHolder) (obj)).getUnmodifiedPayloads();
                        obj1 = new ItemAnimator.ItemHolderInfo();
                        obj2 = ((ViewHolder) (obj)).itemView;
                        obj1.left = ((View) (obj2)).getLeft();
                        obj1.top = ((View) (obj2)).getTop();
                        ((View) (obj2)).getRight();
                        ((View) (obj2)).getBottom();
                        mViewInfoStore.addToPreLayout(((ViewHolder) (obj)), ((ItemAnimator.ItemHolderInfo) (obj1)));
                        if (mState.mTrackOldChangeHolders)
                        {
                            if ((((ViewHolder) (obj)).mFlags & 2) != 0)
                            {
                                j = 1;
                            } else
                            {
                                j = 0;
                            }
                            if (j != 0)
                            {
                                if ((((ViewHolder) (obj)).mFlags & 8) != 0)
                                {
                                    j = 1;
                                } else
                                {
                                    j = 0;
                                }
                                if (j == 0)
                                {
                                    if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
                                    {
                                        j = 1;
                                    } else
                                    {
                                        j = 0;
                                    }
                                    if (j == 0)
                                    {
                                        if ((((ViewHolder) (obj)).mFlags & 4) != 0)
                                        {
                                            j = 1;
                                        } else
                                        {
                                            j = 0;
                                        }
                                        if (j == 0)
                                        {
                                            if (mAdapter.mHasStableIds)
                                            {
                                                l1 = ((ViewHolder) (obj)).mItemId;
                                            } else
                                            {
                                                l1 = ((ViewHolder) (obj)).mPosition;
                                            }
                                            mViewInfoStore.mOldChangedHolders.put(l1, obj);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i++;
            }
        }
        break MISSING_BLOCK_LABEL_1099;
_L2:
        obj1 = mState;
        if (mAdapter.mHasStableIds)
        {
            l1 = ((ViewHolder) (obj)).mItemId;
        }
        obj1.mFocusedItemId = l1;
        obj1 = mState;
        if (mDataSetHasChangedAfterLayout)
        {
            i = -1;
        } else
        {
            if ((((ViewHolder) (obj)).mFlags & 8) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = ((ViewHolder) (obj)).mOldPosition;
            } else
            if (((ViewHolder) (obj)).mOwnerRecyclerView == null)
            {
                i = -1;
            } else
            {
label0:
                {
                    obj2 = ((ViewHolder) (obj)).mOwnerRecyclerView;
                    if ((0x20c & ((ViewHolder) (obj)).mFlags) != 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        if ((((ViewHolder) (obj)).mFlags & 1) != 0)
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        if (i != 0)
                        {
                            break label0;
                        }
                    }
                    i = -1;
                }
            }
        }
_L4:
        obj1.mFocusedItemPosition = i;
        obj1 = mState;
        obj = ((ViewHolder) (obj)).itemView;
        i = ((View) (obj)).getId();
        do
        {
            if (((View) (obj)).isFocused() || !(obj instanceof ViewGroup) || !((View) (obj)).hasFocus())
            {
                break;
            }
            obj = ((ViewGroup)obj).getFocusedChild();
            if (((View) (obj)).getId() != -1)
            {
                i = ((View) (obj)).getId();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_1024;
        obj2 = ((RecyclerView) (obj2)).mAdapterHelper;
        j = ((ViewHolder) (obj)).mPosition;
        i1 = ((AdapterHelper) (obj2)).mPendingUpdates.size();
        l = 0;
_L9:
        i = j;
        if (l >= i1) goto _L4; else goto _L3
_L3:
        obj3 = (AdapterHelper.UpdateOp)((AdapterHelper) (obj2)).mPendingUpdates.get(l);
        ((AdapterHelper.UpdateOp) (obj3)).cmd;
        JVM INSTR lookupswitch 3: default 868
    //                   1: 885
    //                   2: 912
    //                   8: 961;
           goto _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_961;
_L5:
        i = j;
_L10:
        l++;
        j = i;
          goto _L9
_L6:
        i = j;
        if (((AdapterHelper.UpdateOp) (obj3)).positionStart <= j)
        {
            i = j + ((AdapterHelper.UpdateOp) (obj3)).itemCount;
        }
          goto _L10
_L7:
        i = j;
        if (((AdapterHelper.UpdateOp) (obj3)).positionStart > j) goto _L10; else goto _L11
_L11:
label1:
        {
            if (((AdapterHelper.UpdateOp) (obj3)).positionStart + ((AdapterHelper.UpdateOp) (obj3)).itemCount <= j)
            {
                break label1;
            }
            i = -1;
        }
          goto _L4
        i = j - ((AdapterHelper.UpdateOp) (obj3)).itemCount;
          goto _L10
        if (((AdapterHelper.UpdateOp) (obj3)).positionStart == j)
        {
            i = ((AdapterHelper.UpdateOp) (obj3)).itemCount;
        } else
        {
            k = j;
            if (((AdapterHelper.UpdateOp) (obj3)).positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (((AdapterHelper.UpdateOp) (obj3)).itemCount <= k)
            {
                i = k + 1;
            }
        }
          goto _L10
        obj1.mFocusedSubChildId = i;
          goto _L12
        if (mState.mRunPredictiveAnimations)
        {
            k = mChildHelper.mCallback.getChildCount();
            i = 0;
            while (i < k) 
            {
                obj = mChildHelper.mCallback.getChildAt(i);
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                }
                if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag && ((ViewHolder) (obj)).mOldPosition == -1)
                {
                    obj.mOldPosition = ((ViewHolder) (obj)).mPosition;
                }
                i++;
            }
            flag1 = mState.mStructureChanged;
            mState.mStructureChanged = false;
            mLayout.onLayoutChildren(mRecycler, mState);
            mState.mStructureChanged = flag1;
            i = 0;
            do
            {
                obj = mChildHelper;
                if (i >= ((ChildHelper) (obj)).mCallback.getChildCount() - ((ChildHelper) (obj)).mHiddenViews.size())
                {
                    break;
                }
                obj = mChildHelper;
                flag = ((ChildHelper) (obj)).getOffset(i);
                obj = ((ChildHelper) (obj)).mCallback.getChildAt(flag);
                if (obj == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                }
                if ((((ViewHolder) (obj1)).mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    obj = (ViewInfoStore.InfoRecord)mViewInfoStore.mLayoutHolderMap.get(obj1);
                    if (obj != null && (((ViewInfoStore.InfoRecord) (obj)).flags & 4) != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        ItemAnimator.buildAdapterChangeFlagsForAnimations(((ViewHolder) (obj1)));
                        if ((0x2000 & ((ViewHolder) (obj1)).mFlags) != 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        obj = mItemAnimator;
                        ((ViewHolder) (obj1)).getUnmodifiedPayloads();
                        obj3 = new ItemAnimator.ItemHolderInfo();
                        obj = ((ViewHolder) (obj1)).itemView;
                        obj3.left = ((View) (obj)).getLeft();
                        obj3.top = ((View) (obj)).getTop();
                        ((View) (obj)).getRight();
                        ((View) (obj)).getBottom();
                        if (flag)
                        {
                            recordAnimationInfoIfBouncedHiddenView(((ViewHolder) (obj1)), ((ItemAnimator.ItemHolderInfo) (obj3)));
                        } else
                        {
                            viewinfostore = mViewInfoStore;
                            obj2 = (ViewInfoStore.InfoRecord)viewinfostore.mLayoutHolderMap.get(obj1);
                            obj = obj2;
                            if (obj2 == null)
                            {
                                obj2 = (ViewInfoStore.InfoRecord)ViewInfoStore.InfoRecord.sPool.acquire();
                                obj = obj2;
                                if (obj2 == null)
                                {
                                    obj = new ViewInfoStore.InfoRecord();
                                }
                                viewinfostore.mLayoutHolderMap.put(obj1, obj);
                            }
                            obj.flags = ((ViewInfoStore.InfoRecord) (obj)).flags | 2;
                            obj.preInfo = ((ItemAnimator.ItemHolderInfo) (obj3));
                        }
                    }
                }
                i++;
            } while (true);
            clearOldPositions();
        } else
        {
            clearOldPositions();
        }
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        mState.mLayoutStep = 2;
        return;
    }

    private final void dispatchLayoutStep2()
    {
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
        mState.assertLayoutStep(6);
        mAdapterHelper.consumeUpdatesInOnePass();
        mState.mItemCount = mAdapter.getItemCount();
        mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        mState.mInPreLayout = false;
        mLayout.onLayoutChildren(mRecycler, mState);
        mState.mStructureChanged = false;
        mPendingSavedState = null;
        State state = mState;
        boolean flag;
        if (mState.mRunSimpleAnimations && mItemAnimator != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        state.mRunSimpleAnimations = flag;
        mState.mLayoutStep = 4;
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
    }

    private final void fillRemainingScrollValues(State state)
    {
        if (getScrollState() == 2)
        {
            state = mViewFlinger.mScroller;
            state.getFinalX();
            state.getCurrX();
            state.getFinalY();
            state.getCurrY();
        }
    }

    private final View findContainingItemView(View view)
    {
        ViewParent viewparent;
        for (viewparent = view.getParent(); viewparent != null && viewparent != this && (viewparent instanceof View); viewparent = view.getParent())
        {
            view = (View)viewparent;
        }

        if (viewparent == this)
        {
            return view;
        } else
        {
            return null;
        }
    }

    private final void findMinMaxChildLayoutPositions(int ai[])
    {
        int i;
        int l;
        int j1;
        int k1;
        ChildHelper childhelper = mChildHelper;
        k1 = childhelper.mCallback.getChildCount() - childhelper.mHiddenViews.size();
        if (k1 == 0)
        {
            ai[0] = -1;
            ai[1] = -1;
            return;
        }
        i = 0x7fffffff;
        l = 0x80000000;
        j1 = 0;
_L2:
        int j;
        int i1;
        if (j1 >= k1)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = mChildHelper;
        j = ((ChildHelper) (obj)).getOffset(j1);
        obj = ((ChildHelper) (obj)).mCallback.getChildAt(j);
        int k;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        }
        if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        i1 = i;
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        if (((ViewHolder) (obj)).mPreLayoutPosition == -1)
        {
            j = ((ViewHolder) (obj)).mPosition;
        } else
        {
            j = ((ViewHolder) (obj)).mPreLayoutPosition;
        }
        k = i;
        if (j < i)
        {
            k = j;
        }
        i1 = k;
        if (j <= l)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        i = k;
_L3:
        j1++;
        l = j;
        if (true) goto _L2; else goto _L1
_L1:
        ai[0] = i;
        ai[1] = l;
        return;
        j = l;
        i = i1;
          goto _L3
    }

    static RecyclerView findNestedRecyclerView(View view)
    {
        if (!(view instanceof ViewGroup))
        {
            return null;
        }
        if (view instanceof RecyclerView)
        {
            return (RecyclerView)view;
        }
        view = (ViewGroup)view;
        int j = view.getChildCount();
        for (int i = 0; i < j; i++)
        {
            RecyclerView recyclerview = findNestedRecyclerView(view.getChildAt(i));
            if (recyclerview != null)
            {
                return recyclerview;
            }
        }

        return null;
    }

    public static int getChildAdapterPosition(View view)
    {
        int i;
        if (view == null)
        {
            view = null;
        } else
        {
            view = ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
        if (view != null && ((ViewHolder) (view)).mOwnerRecyclerView != null) goto _L2; else goto _L1
_L1:
        i = -1;
_L6:
        return i;
_L2:
        int j;
        Object obj = ((ViewHolder) (view)).mOwnerRecyclerView;
        int l;
        int i1;
        if ((0x20c & ((ViewHolder) (view)).mFlags) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L1; else goto _L3
_L3:
        if ((((ViewHolder) (view)).mFlags & 1) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L1; else goto _L4
_L4:
        obj = ((RecyclerView) (obj)).mAdapterHelper;
        j = ((ViewHolder) (view)).mPosition;
        i1 = ((AdapterHelper) (obj)).mPendingUpdates.size();
        l = 0;
_L11:
        i = j;
        if (l >= i1) goto _L6; else goto _L5
_L5:
        view = (AdapterHelper.UpdateOp)((AdapterHelper) (obj)).mPendingUpdates.get(l);
        ((AdapterHelper.UpdateOp) (view)).cmd;
        JVM INSTR lookupswitch 3: default 156
    //                   1: 179
    //                   2: 199
    //                   8: 232;
           goto _L7 _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_232;
_L7:
        i = j;
_L12:
        l++;
        j = i;
          goto _L11
_L8:
        i = j;
        if (((AdapterHelper.UpdateOp) (view)).positionStart <= j)
        {
            i = j + ((AdapterHelper.UpdateOp) (view)).itemCount;
        }
          goto _L12
_L9:
        i = j;
        if (((AdapterHelper.UpdateOp) (view)).positionStart > j) goto _L12; else goto _L13
_L13:
        if (((AdapterHelper.UpdateOp) (view)).positionStart + ((AdapterHelper.UpdateOp) (view)).itemCount > j) goto _L1; else goto _L14
_L14:
        i = j - ((AdapterHelper.UpdateOp) (view)).itemCount;
          goto _L12
        if (((AdapterHelper.UpdateOp) (view)).positionStart == j)
        {
            i = ((AdapterHelper.UpdateOp) (view)).itemCount;
        } else
        {
            int k = j;
            if (((AdapterHelper.UpdateOp) (view)).positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (((AdapterHelper.UpdateOp) (view)).itemCount <= k)
            {
                i = k + 1;
            }
        }
          goto _L12
    }

    public static ViewHolder getChildViewHolderInt(View view)
    {
        if (view == null)
        {
            return null;
        } else
        {
            return ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Rect rect1 = layoutparams.mDecorInsets;
        int i = view.getLeft();
        int j = rect1.left;
        int k = layoutparams.leftMargin;
        int l = view.getTop();
        int i1 = rect1.top;
        int j1 = layoutparams.topMargin;
        int k1 = view.getRight();
        int l1 = rect1.right;
        int i2 = layoutparams.rightMargin;
        int j2 = view.getBottom();
        int k2 = rect1.bottom;
        rect.set(i - j - k, l - i1 - j1, k1 + l1 + i2, layoutparams.bottomMargin + (k2 + j2));
    }

    private final void onPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if (motionevent.getPointerId(i) == mScrollPointerId)
        {
            int j;
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            mScrollPointerId = motionevent.getPointerId(i);
            j = (int)(motionevent.getX(i) + 0.5F);
            mLastTouchX = j;
            mInitialTouchX = j;
            i = (int)(motionevent.getY(i) + 0.5F);
            mLastTouchY = i;
            mInitialTouchY = i;
        }
    }

    private final void processAdapterUpdatesAndSetAnimationFlags()
    {
        boolean flag;
        boolean flag1;
        boolean flag2 = true;
        if (mDataSetHasChangedAfterLayout)
        {
            AdapterHelper adapterhelper = mAdapterHelper;
            adapterhelper.recycleUpdateOpsAndClearList(adapterhelper.mPendingUpdates);
            adapterhelper.recycleUpdateOpsAndClearList(adapterhelper.mPostponedList);
            adapterhelper.mExistingUpdateTypes = 0;
        }
        State state;
        if (mItemAnimator != null && mLayout.supportsPredictiveItemAnimations())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mAdapterHelper.preProcess();
        } else
        {
            mAdapterHelper.consumeUpdatesInOnePass();
        }
        if (mItemsAddedOrRemoved || mItemsChanged)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        state = mState;
        if (mFirstLayoutComplete && mItemAnimator != null && (mDataSetHasChangedAfterLayout || flag) && (!mDataSetHasChangedAfterLayout || mAdapter.mHasStableIds))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        state.mRunSimpleAnimations = flag1;
        state = mState;
        if (!mState.mRunSimpleAnimations || !flag || mDataSetHasChangedAfterLayout) goto _L2; else goto _L1
_L1:
        if (mItemAnimator != null && mLayout.supportsPredictiveItemAnimations())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L3
_L3:
        flag1 = flag2;
_L5:
        state.mRunPredictiveAnimations = flag1;
        return;
_L2:
        flag1 = false;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final void requestChildOnScreen(View view, View view1)
    {
        boolean flag1 = true;
        Object obj;
        Rect rect1;
        boolean flag;
        if (view1 != null)
        {
            obj = view1;
        } else
        {
            obj = view;
        }
        mTempRect.set(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
        obj = ((View) (obj)).getLayoutParams();
        if (obj instanceof LayoutParams)
        {
            obj = (LayoutParams)obj;
            if (!((LayoutParams) (obj)).mInsetsDirty)
            {
                obj = ((LayoutParams) (obj)).mDecorInsets;
                Rect rect = mTempRect;
                rect.left = rect.left - ((Rect) (obj)).left;
                rect = mTempRect;
                rect.right = rect.right + ((Rect) (obj)).right;
                rect = mTempRect;
                rect.top = rect.top - ((Rect) (obj)).top;
                rect = mTempRect;
                int i = rect.bottom;
                rect.bottom = ((Rect) (obj)).bottom + i;
            }
        }
        if (view1 != null)
        {
            offsetDescendantRectToMyCoords(view1, mTempRect);
            offsetRectIntoDescendantCoords(view, mTempRect);
        }
        obj = mLayout;
        rect1 = mTempRect;
        if (!mFirstLayoutComplete)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (view1 != null)
        {
            flag1 = false;
        }
        ((LayoutManager) (obj)).requestChildRectangleOnScreen(this, view, rect1, flag, flag1);
    }

    private final boolean scrollByInternal(int i, int j, MotionEvent motionevent)
    {
        int k = 0;
        int j1 = 0;
        int l = 0;
        int i1 = 0;
        consumePendingUpdateOperations();
        if (mAdapter != null)
        {
            scrollStep(i, j, mScrollStepConsumed);
            l = mScrollStepConsumed[0];
            i1 = mScrollStepConsumed[1];
            k = i - l;
            j1 = j - i1;
        }
        if (!mItemDecorations.isEmpty())
        {
            invalidate();
        }
        int ai[] = mScrollOffset;
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        if (mScrollingChildHelper.dispatchNestedScroll(l, i1, k, j1, ai, 0))
        {
            mLastTouchX = mLastTouchX - mScrollOffset[0];
            mLastTouchY = mLastTouchY - mScrollOffset[1];
            if (motionevent != null)
            {
                motionevent.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            }
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        } else
        if (getOverScrollMode() != 2)
        {
            if (motionevent != null)
            {
                boolean flag1;
                if ((motionevent.getSource() & 0x2002) == 8194)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    float f = motionevent.getX();
                    float f1 = k;
                    float f2 = motionevent.getY();
                    float f3 = j1;
                    boolean flag = false;
                    if (f1 < 0.0F)
                    {
                        ensureLeftGlow();
                        mLeftGlow.onPull(-f1 / (float)getWidth(), 1.0F - f2 / (float)getHeight());
                        flag = true;
                    } else
                    if (f1 > 0.0F)
                    {
                        ensureRightGlow();
                        mRightGlow.onPull(f1 / (float)getWidth(), f2 / (float)getHeight());
                        flag = true;
                    }
                    if (f3 < 0.0F)
                    {
                        ensureTopGlow();
                        mTopGlow.onPull(-f3 / (float)getHeight(), f / (float)getWidth());
                        flag = true;
                    } else
                    if (f3 > 0.0F)
                    {
                        ensureBottomGlow();
                        mBottomGlow.onPull(f3 / (float)getHeight(), 1.0F - f / (float)getWidth());
                        flag = true;
                    }
                    if (flag || f1 != 0.0F || f3 != 0.0F)
                    {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }
                }
            }
            considerReleasingGlowsOnScroll(i, j);
        }
        if (l != 0 || i1 != 0)
        {
            dispatchOnScrolled(l, i1);
        }
        if (!awakenScrollBars())
        {
            invalidate();
        }
        return l != 0 || i1 != 0;
    }

    final void addAnimatingView(ViewHolder viewholder)
    {
        boolean flag1 = false;
        View view = viewholder.itemView;
        boolean flag;
        if (view.getParent() == this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mRecycler.unscrapView(getChildViewHolder(view));
        if ((viewholder.mFlags & 0x100) != 0)
        {
            flag1 = true;
        }
        if (flag1)
        {
            mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!flag)
        {
            mChildHelper.addView(view, -1, true);
            return;
        }
        viewholder = mChildHelper;
        int i = ((ChildHelper) (viewholder)).mCallback.indexOfChild(view);
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("view is not a child, cannot hide ")).append(view).toString());
        } else
        {
            ((ChildHelper) (viewholder)).mBucket.set(i);
            ((ChildHelper) (viewholder)).mHiddenViews.add(view);
            ((ChildHelper) (viewholder)).mCallback.onEnteredHiddenState(view);
            return;
        }
    }

    public final void addItemDecoration(ItemDecoration itemdecoration)
    {
        if (mLayout != null)
        {
            mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (mItemDecorations.isEmpty())
        {
            setWillNotDraw(false);
        }
        mItemDecorations.add(itemdecoration);
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public final void assertNotInLayoutOrScroll(String s)
    {
        boolean flag;
        if (mLayoutOrScrollCounter > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (s == null)
            {
                throw new IllegalStateException((new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")).append(exceptionLabel()).toString());
            } else
            {
                throw new IllegalStateException(s);
            }
        }
        if (mDispatchScrollCounter > 0)
        {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException((new StringBuilder()).append(exceptionLabel()).toString()));
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof LayoutParams)
        {
            boolean flag;
            if ((LayoutParams)layoutparams != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public int computeHorizontalScrollExtent()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollExtent(mState);
    }

    public int computeHorizontalScrollOffset()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollOffset(mState);
    }

    public int computeHorizontalScrollRange()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollRange(mState);
    }

    public int computeVerticalScrollExtent()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollExtent(mState);
    }

    public int computeVerticalScrollOffset()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollOffset(mState);
    }

    public int computeVerticalScrollRange()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollRange(mState);
    }

    final void considerReleasingGlowsOnScroll(int i, int j)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (mLeftGlow != null)
        {
            flag = flag1;
            if (!mLeftGlow.isFinished())
            {
                flag = flag1;
                if (i > 0)
                {
                    mLeftGlow.onRelease();
                    flag = mLeftGlow.isFinished();
                }
            }
        }
        flag1 = flag;
        if (mRightGlow != null)
        {
            flag1 = flag;
            if (!mRightGlow.isFinished())
            {
                flag1 = flag;
                if (i < 0)
                {
                    mRightGlow.onRelease();
                    flag1 = flag | mRightGlow.isFinished();
                }
            }
        }
        flag = flag1;
        if (mTopGlow != null)
        {
            flag = flag1;
            if (!mTopGlow.isFinished())
            {
                flag = flag1;
                if (j > 0)
                {
                    mTopGlow.onRelease();
                    flag = flag1 | mTopGlow.isFinished();
                }
            }
        }
        flag1 = flag;
        if (mBottomGlow != null)
        {
            flag1 = flag;
            if (!mBottomGlow.isFinished())
            {
                flag1 = flag;
                if (j < 0)
                {
                    mBottomGlow.onRelease();
                    flag1 = flag | mBottomGlow.isFinished();
                }
            }
        }
        if (flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    final void consumePendingUpdateOperations()
    {
        boolean flag1 = false;
        if (mFirstLayoutComplete && !mDataSetHasChangedAfterLayout) goto _L2; else goto _L1
_L1:
        Trace.beginSection("RV FullInvalidate");
        dispatchLayout();
        Trace.endSection();
_L16:
        return;
_L2:
        int i;
        int j;
        ChildHelper childhelper;
        int k;
        int l;
        if (mAdapterHelper.mPendingUpdates.size() > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if ((mAdapterHelper.mExistingUpdateTypes & 4) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        if ((mAdapterHelper.mExistingUpdateTypes & 0xb) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L4; else goto _L5
_L5:
        Trace.beginSection("RV PartialInvalidate");
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
        mAdapterHelper.preProcess();
        if (mLayoutWasDefered) goto _L7; else goto _L6
_L6:
        childhelper = mChildHelper;
        k = childhelper.mCallback.getChildCount();
        l = childhelper.mHiddenViews.size();
        i = 0;
_L14:
        j = ((flag1) ? 1 : 0);
        if (i >= k - l) goto _L9; else goto _L8
_L8:
        childhelper = mChildHelper;
        j = childhelper.getOffset(i);
        Object obj = childhelper.mCallback.getChildAt(j);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        }
        if (obj == null) goto _L11; else goto _L10
_L10:
        if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L11; else goto _L12
_L12:
        if ((((ViewHolder) (obj)).mFlags & 2) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L11; else goto _L13
_L13:
        j = 1;
_L9:
        if (j != 0)
        {
            dispatchLayout();
        } else
        {
            mAdapterHelper.consumePostponedUpdates();
        }
_L7:
        stopInterceptRequestLayout(true);
        onExitLayoutOrScroll(true);
        Trace.endSection();
        return;
_L11:
        i++;
        if (true) goto _L14; else goto _L4
_L4:
        boolean flag;
        if (mAdapterHelper.mPendingUpdates.size() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            Trace.endSection();
            return;
        }
        if (true) goto _L16; else goto _L15
_L15:
    }

    final void defaultOnMeasure(int i, int j)
    {
        setMeasuredDimension(LayoutManager.chooseSize(i, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(j, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    final void dispatchChildDetached(View view)
    {
        if (view == null)
        {
            view = null;
        } else
        {
            view = ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
        if (mAdapter != null && view != null)
        {
            mAdapter.onViewDetachedFromWindow(view);
        }
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedFling(f, f1, flag);
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedPreFling(f, f1);
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedPreScroll(i, j, ai, ai1, 0);
    }

    public final boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[], int k)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedPreScroll(i, j, ai, ai1, k);
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedScroll(i, j, k, l, ai, 0);
    }

    public final boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[], int i1)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.dispatchNestedScroll(i, j, k, l, ai, i1);
    }

    final void dispatchOnScrolled(int i, int j)
    {
        mDispatchScrollCounter = mDispatchScrollCounter + 1;
        int k = getScrollX();
        int l = getScrollY();
        onScrollChanged(k, l, k, l);
        onScrolled(i, j);
        if (mScrollListener != null)
        {
            mScrollListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(this, j);
        }
        if (mScrollListeners != null)
        {
            for (i = mScrollListeners.size() - 1; i >= 0; i--)
            {
                ((OnScrollListener)mScrollListeners.get(i))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(this, j);
            }

        }
        mDispatchScrollCounter = mDispatchScrollCounter - 1;
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        dispatchFreezeSelfOnly(sparsearray);
    }

    public void draw(Canvas canvas)
    {
        boolean flag = true;
        boolean flag1 = false;
        super.draw(canvas);
        int k = mItemDecorations.size();
        for (int i = 0; i < k; i++)
        {
            ((ItemDecoration)mItemDecorations.get(i))._mth51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTIILG_0(canvas, this);
        }

        int j;
        if (mLeftGlow != null && !mLeftGlow.isFinished())
        {
            int l = canvas.save();
            int i1;
            if (mClipToPadding)
            {
                j = getPaddingBottom();
            } else
            {
                j = 0;
            }
            canvas.rotate(270F);
            canvas.translate(j + -getHeight(), 0.0F);
            if (mLeftGlow != null && mLeftGlow.draw(canvas))
            {
                k = 1;
            } else
            {
                k = 0;
            }
            canvas.restoreToCount(l);
        } else
        {
            k = 0;
        }
        j = k;
        if (mTopGlow != null)
        {
            j = k;
            if (!mTopGlow.isFinished())
            {
                l = canvas.save();
                if (mClipToPadding)
                {
                    canvas.translate(getPaddingLeft(), getPaddingTop());
                }
                if (mTopGlow != null && mTopGlow.draw(canvas))
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                j = k | j;
                canvas.restoreToCount(l);
            }
        }
        k = j;
        if (mRightGlow != null)
        {
            k = j;
            if (!mRightGlow.isFinished())
            {
                l = canvas.save();
                i1 = getWidth();
                if (mClipToPadding)
                {
                    k = getPaddingTop();
                } else
                {
                    k = 0;
                }
                canvas.rotate(90F);
                canvas.translate(-k, -i1);
                if (mRightGlow != null && mRightGlow.draw(canvas))
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                k = j | k;
                canvas.restoreToCount(l);
            }
        }
        j = k;
        if (mBottomGlow != null)
        {
            j = k;
            if (!mBottomGlow.isFinished())
            {
                l = canvas.save();
                canvas.rotate(180F);
                if (mClipToPadding)
                {
                    canvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
                } else
                {
                    canvas.translate(-getWidth(), -getHeight());
                }
                j = ((flag1) ? 1 : 0);
                if (mBottomGlow != null)
                {
                    j = ((flag1) ? 1 : 0);
                    if (mBottomGlow.draw(canvas))
                    {
                        j = 1;
                    }
                }
                j = k | j;
                canvas.restoreToCount(l);
            }
        }
        if (j == 0 && mItemAnimator != null && mItemDecorations.size() > 0 && mItemAnimator.isRunning())
        {
            j = ((flag) ? 1 : 0);
        }
        if (j != 0)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    final void ensureBottomGlow()
    {
        if (mBottomGlow != null)
        {
            return;
        }
        mBottomGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
            return;
        } else
        {
            mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
    }

    final void ensureLeftGlow()
    {
        if (mLeftGlow != null)
        {
            return;
        }
        mLeftGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
            return;
        } else
        {
            mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
    }

    final void ensureRightGlow()
    {
        if (mRightGlow != null)
        {
            return;
        }
        mRightGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
            return;
        } else
        {
            mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
    }

    final void ensureTopGlow()
    {
        if (mTopGlow != null)
        {
            return;
        }
        mTopGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
            return;
        } else
        {
            mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
    }

    final String exceptionLabel()
    {
        return (new StringBuilder(" ")).append(super.toString()).append(", adapter:").append(mAdapter).append(", layout:").append(mLayout).append(", context:").append(getContext()).toString();
    }

    public final ViewHolder findViewHolderForAdapterPosition(int i)
    {
        if (!mDataSetHasChangedAfterLayout) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L9:
        return ((ViewHolder) (obj1));
_L2:
        int i1;
        int k1;
        k1 = mChildHelper.mCallback.getChildCount();
        obj1 = null;
        i1 = 0;
_L10:
        if (i1 >= k1) goto _L4; else goto _L3
_L3:
        Object obj;
        int j;
        obj = mChildHelper.mCallback.getChildAt(i1);
        ChildHelper childhelper;
        View view;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_446;
        }
        if ((((ViewHolder) (obj)).mFlags & 8) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_446;
        }
        if ((0x20c & ((ViewHolder) (obj)).mFlags) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L6; else goto _L5
_L5:
        if ((((ViewHolder) (obj)).mFlags & 1) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L7; else goto _L6
_L6:
        j = -1;
_L12:
        if (j != i)
        {
            break MISSING_BLOCK_LABEL_446;
        }
        childhelper = mChildHelper;
        view = ((ViewHolder) (obj)).itemView;
        obj1 = obj;
        if (!childhelper.mHiddenViews.contains(view)) goto _L9; else goto _L8
_L8:
        i1++;
        obj1 = obj;
          goto _L10
_L7:
        AdapterHelper adapterhelper;
        int k;
        int j1;
        int l1;
        adapterhelper = mAdapterHelper;
        k = ((ViewHolder) (obj)).mPosition;
        l1 = adapterhelper.mPendingUpdates.size();
        j1 = 0;
_L17:
        j = k;
        if (j1 >= l1) goto _L12; else goto _L11
_L11:
        AdapterHelper.UpdateOp updateop = (AdapterHelper.UpdateOp)adapterhelper.mPendingUpdates.get(j1);
        updateop.cmd;
        JVM INSTR lookupswitch 3: default 288
    //                   1: 305
    //                   2: 332
    //                   8: 381;
           goto _L13 _L14 _L15 _L16
_L16:
        break MISSING_BLOCK_LABEL_381;
_L13:
        j = k;
_L18:
        j1++;
        k = j;
          goto _L17
_L14:
        j = k;
        if (updateop.positionStart <= k)
        {
            j = k + updateop.itemCount;
        }
          goto _L18
_L15:
        j = k;
        if (updateop.positionStart > k) goto _L18; else goto _L19
_L19:
label0:
        {
            if (updateop.positionStart + updateop.itemCount <= k)
            {
                break label0;
            }
            j = -1;
        }
          goto _L12
        j = k - updateop.itemCount;
          goto _L18
        if (updateop.positionStart == k)
        {
            j = updateop.itemCount;
        } else
        {
            int l = k;
            if (updateop.positionStart < k)
            {
                l = k - 1;
            }
            j = l;
            if (updateop.itemCount <= l)
            {
                j = l + 1;
            }
        }
          goto _L18
_L4:
        return ((ViewHolder) (obj1));
        obj = obj1;
          goto _L8
    }

    public View focusSearch(View view, int i)
    {
        Object obj;
        char c;
        char c1;
        byte byte0;
        boolean flag;
        byte0 = -1;
        flag = false;
        if (mAdapter == null || mLayout == null)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        if (mLayoutOrScrollCounter > 0)
        {
            c = '\001';
        } else
        {
            c = '\0';
        }
        if (c != 0 || mLayoutFrozen)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        c = '\001';
_L1:
        obj = FocusFinder.getInstance();
        if (c == 0 || i != 2 && i != 1)
        {
            break MISSING_BLOCK_LABEL_326;
        }
        View view1;
        byte byte1;
        if (mLayout.canScrollVertically())
        {
            if (i == 2)
            {
                c = '\202';
            } else
            {
                c = '!';
            }
            if (((FocusFinder) (obj)).findNextFocus(this, view, c) == null)
            {
                c = '\001';
            } else
            {
                c = '\0';
            }
        } else
        {
            c = '\0';
        }
        c1 = c;
        if (c == 0)
        {
            c1 = c;
            if (mLayout.canScrollHorizontally())
            {
                if (ViewCompat.getLayoutDirection(mLayout.mRecyclerView) == 1)
                {
                    c = '\001';
                } else
                {
                    c = '\0';
                }
                if (i == 2)
                {
                    c1 = '\001';
                } else
                {
                    c1 = '\0';
                }
                if ((c1 ^ c) != 0)
                {
                    c = 'B';
                } else
                {
                    c = '\021';
                }
                if (((FocusFinder) (obj)).findNextFocus(this, view, c) == null)
                {
                    c1 = '\001';
                } else
                {
                    c1 = '\0';
                }
            }
        }
        if (c1 == 0)
        {
            break MISSING_BLOCK_LABEL_293;
        }
        consumePendingUpdateOperations();
        if (findContainingItemView(view) == null)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_241;
        c = '\0';
          goto _L1
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayout._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357CKKOOBECHP6UQB45TR6IPBN5TB6IPBN7C______0(i, mRecycler, mState);
        stopInterceptRequestLayout(false);
        obj = ((FocusFinder) (obj)).findNextFocus(this, view, i);
_L2:
        if (obj != null && !((View) (obj)).hasFocusable())
        {
            if (getFocusedChild() == null)
            {
                return super.focusSearch(view, i);
            } else
            {
                requestChildOnScreen(((View) (obj)), null);
                return view;
            }
        }
        break MISSING_BLOCK_LABEL_428;
        view1 = ((FocusFinder) (obj)).findNextFocus(this, view, i);
        obj = view1;
        if (view1 == null)
        {
            obj = view1;
            if (c != 0)
            {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null)
                {
                    return null;
                }
                mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
                if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
                {
                    mLayoutWasDefered = false;
                }
                obj = mLayout._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H96AORPCDM6ASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFETKM8PR5EGNL4PB3F5HMOPBIAPKMATP4ADQ62T357CKKOOBECHP6UQB45TR6IPBN5TB6IPBN7C______0(i, mRecycler, mState);
                stopInterceptRequestLayout(false);
            }
        }
          goto _L2
        c = flag;
        if (obj != null)
        {
            if (obj == this)
            {
                c = flag;
            } else
            {
                c = flag;
                if (findContainingItemView(((View) (obj))) != null)
                {
                    if (view == null)
                    {
                        c = '\001';
                    } else
                    {
label0:
                        {
                            if (findContainingItemView(view) != null)
                            {
                                break label0;
                            }
                            c = '\001';
                        }
                    }
                }
            }
        }
_L10:
        if (c != 0)
        {
            return ((View) (obj));
        } else
        {
            return super.focusSearch(view, i);
        }
        mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        mTempRect2.set(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
        offsetDescendantRectToMyCoords(view, mTempRect);
        offsetDescendantRectToMyCoords(((View) (obj)), mTempRect2);
        if (ViewCompat.getLayoutDirection(mLayout.mRecyclerView) == 1)
        {
            byte1 = -1;
        } else
        {
            byte1 = 1;
        }
        if ((mTempRect.left < mTempRect2.left || mTempRect.right <= mTempRect2.left) && mTempRect.right < mTempRect2.right)
        {
            c1 = '\001';
        } else
        if ((mTempRect.right > mTempRect2.right || mTempRect.left >= mTempRect2.right) && mTempRect.left > mTempRect2.left)
        {
            c1 = '\uFFFF';
        } else
        {
            c1 = '\0';
        }
        if ((mTempRect.top < mTempRect2.top || mTempRect.bottom <= mTempRect2.top) && mTempRect.bottom < mTempRect2.bottom)
        {
            byte0 = 1;
        } else
        if (mTempRect.bottom <= mTempRect2.bottom && mTempRect.top < mTempRect2.bottom || mTempRect.top <= mTempRect2.top)
        {
            byte0 = 0;
        }
        i;
        JVM INSTR lookupswitch 6: default 724
    //                   1: 968
    //                   2: 936
    //                   17: 876
    //                   33: 906
    //                   66: 891
    //                   130: 921;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        throw new IllegalArgumentException((new StringBuilder("Invalid direction: ")).append(i).append(exceptionLabel()).toString());
_L6:
        c = flag;
        if (c1 < 0)
        {
            c = '\001';
        }
          goto _L10
_L8:
        c = flag;
        if (c1 > 0)
        {
            c = '\001';
        }
          goto _L10
_L7:
        c = flag;
        if (byte0 < 0)
        {
            c = '\001';
        }
          goto _L10
_L9:
        c = flag;
        if (byte0 > 0)
        {
            c = '\001';
        }
          goto _L10
_L5:
        if (byte0 > 0) goto _L12; else goto _L11
_L11:
        c = flag;
        if (byte0 != 0) goto _L10; else goto _L13
_L13:
        c = flag;
        if (byte1 * c1 < 0) goto _L10; else goto _L12
_L12:
        c = '\001';
          goto _L10
_L4:
        if (byte0 < 0) goto _L15; else goto _L14
_L14:
        c = flag;
        if (byte0 != 0) goto _L10; else goto _L16
_L16:
        c = flag;
        if (byte1 * c1 > 0) goto _L10; else goto _L15
_L15:
        c = '\001';
          goto _L10
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        if (mLayout == null)
        {
            throw new IllegalStateException((new StringBuilder("RecyclerView has no LayoutManager")).append(exceptionLabel()).toString());
        } else
        {
            return mLayout.generateDefaultLayoutParams();
        }
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        if (mLayout == null)
        {
            throw new IllegalStateException((new StringBuilder("RecyclerView has no LayoutManager")).append(exceptionLabel()).toString());
        } else
        {
            return new LayoutParams(getContext(), attributeset);
        }
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mLayout == null)
        {
            throw new IllegalStateException((new StringBuilder("RecyclerView has no LayoutManager")).append(exceptionLabel()).toString());
        }
        if (layoutparams instanceof LayoutParams)
        {
            return new LayoutParams((LayoutParams)layoutparams);
        }
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    final int getAdapterPositionFor(ViewHolder viewholder)
    {
        int i;
        boolean flag = true;
        if ((0x20c & viewholder.mFlags) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        if ((viewholder.mFlags & 1) != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L3; else goto _L2
_L2:
        i = -1;
_L5:
        return i;
_L3:
        AdapterHelper adapterhelper;
        int j;
        int l;
        int i1;
        adapterhelper = mAdapterHelper;
        j = viewholder.mPosition;
        i1 = adapterhelper.mPendingUpdates.size();
        l = 0;
_L10:
        i = j;
        if (l >= i1) goto _L5; else goto _L4
_L4:
        viewholder = (AdapterHelper.UpdateOp)adapterhelper.mPendingUpdates.get(l);
        ((AdapterHelper.UpdateOp) (viewholder)).cmd;
        JVM INSTR lookupswitch 3: default 136
    //                   1: 151
    //                   2: 174
    //                   8: 213;
           goto _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_213;
_L6:
        i = j;
_L11:
        l++;
        j = i;
          goto _L10
_L7:
        i = j;
        if (((AdapterHelper.UpdateOp) (viewholder)).positionStart <= j)
        {
            i = j + ((AdapterHelper.UpdateOp) (viewholder)).itemCount;
        }
          goto _L11
_L8:
        i = j;
        if (((AdapterHelper.UpdateOp) (viewholder)).positionStart <= j)
        {
            if (((AdapterHelper.UpdateOp) (viewholder)).positionStart + ((AdapterHelper.UpdateOp) (viewholder)).itemCount > j)
            {
                return -1;
            }
            i = j - ((AdapterHelper.UpdateOp) (viewholder)).itemCount;
        }
          goto _L11
        if (((AdapterHelper.UpdateOp) (viewholder)).positionStart == j)
        {
            i = ((AdapterHelper.UpdateOp) (viewholder)).itemCount;
        } else
        {
            int k = j;
            if (((AdapterHelper.UpdateOp) (viewholder)).positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (((AdapterHelper.UpdateOp) (viewholder)).itemCount <= k)
            {
                i = k + 1;
            }
        }
          goto _L11
    }

    public int getBaseline()
    {
        if (mLayout != null)
        {
            return -1;
        } else
        {
            return super.getBaseline();
        }
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (mChildDrawingOrderCallback == null)
        {
            return super.getChildDrawingOrder(i, j);
        } else
        {
            return mChildDrawingOrderCallback.onGetChildDrawingOrder(i, j);
        }
    }

    public final ViewHolder getChildViewHolder(View view)
    {
        ViewParent viewparent = view.getParent();
        if (viewparent != null && viewparent != this)
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a direct child of ").append(this).toString());
        }
        if (view == null)
        {
            return null;
        } else
        {
            return ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
    }

    public boolean getClipToPadding()
    {
        return mClipToPadding;
    }

    public final Rect getItemDecorInsetsForChild(View view)
    {
        LayoutParams layoutparams;
label0:
        {
            boolean flag1 = true;
            layoutparams = (LayoutParams)view.getLayoutParams();
            if (!layoutparams.mInsetsDirty)
            {
                return layoutparams.mDecorInsets;
            }
            if (!mState.mInPreLayout)
            {
                break label0;
            }
            boolean flag;
            if ((layoutparams.mViewHolder.mFlags & 2) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if ((layoutparams.mViewHolder.mFlags & 4) != 0)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return layoutparams.mDecorInsets;
        }
        Rect rect = layoutparams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int j = mItemDecorations.size();
        for (int i = 0; i < j; i++)
        {
            mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration)mItemDecorations.get(i)).getItemOffsets(mTempRect, view, this, mState);
            rect.left = rect.left + mTempRect.left;
            rect.top = rect.top + mTempRect.top;
            rect.right = rect.right + mTempRect.right;
            rect.bottom = rect.bottom + mTempRect.bottom;
        }

        layoutparams.mInsetsDirty = false;
        return rect;
    }

    public int getScrollState()
    {
        return mScrollState;
    }

    public boolean hasNestedScrollingParent()
    {
        boolean flag = false;
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        if (mScrollingChildHelper.getNestedScrollingParentForType(0) != null)
        {
            flag = true;
        }
        return flag;
    }

    public final boolean hasPendingAdapterUpdates()
    {
label0:
        {
            boolean flag1 = false;
            if (mFirstLayoutComplete && !mDataSetHasChangedAfterLayout)
            {
                boolean flag;
                if (mAdapterHelper.mPendingUpdates.size() > 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final void invalidateItemDecorations()
    {
        if (mItemDecorations.size() == 0)
        {
            return;
        }
        if (mLayout != null)
        {
            mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean isAnimating()
    {
        return mItemAnimator != null && mItemAnimator.isRunning();
    }

    public boolean isAttachedToWindow()
    {
        return mIsAttached;
    }

    public boolean isNestedScrollingEnabled()
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.mIsNestedScrollingEnabled;
    }

    public final void markItemDecorInsetsDirty()
    {
        boolean flag = false;
        int k = mChildHelper.mCallback.getChildCount();
        for (int i = 0; i < k; i++)
        {
            ((LayoutParams)mChildHelper.mCallback.getChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }

        Recycler recycler = mRecycler;
        k = recycler.mCachedViews.size();
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            LayoutParams layoutparams = (LayoutParams)((ViewHolder)recycler.mCachedViews.get(j)).itemView.getLayoutParams();
            if (layoutparams != null)
            {
                layoutparams.mInsetsDirty = true;
            }
        }

    }

    final void offsetPositionRecordsForRemove(int i, int j, boolean flag)
    {
        int i1 = mChildHelper.mCallback.getChildCount();
        int k = 0;
        while (k < i1) 
        {
            Object obj = mChildHelper.mCallback.getChildAt(k);
            boolean flag1;
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if (obj == null)
            {
                continue;
            }
            if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                if (((ViewHolder) (obj)).mPosition >= i + j)
                {
                    ((ViewHolder) (obj)).offsetPosition(-j, flag);
                    mState.mStructureChanged = true;
                } else
                if (((ViewHolder) (obj)).mPosition >= i)
                {
                    int l = -j;
                    obj.mFlags = ((ViewHolder) (obj)).mFlags | 8;
                    ((ViewHolder) (obj)).offsetPosition(l, flag);
                    obj.mPosition = i - 1;
                    mState.mStructureChanged = true;
                }
            }
            k++;
        }
        Recycler recycler = mRecycler;
        k = recycler.mCachedViews.size() - 1;
        while (k >= 0) 
        {
            ViewHolder viewholder = (ViewHolder)recycler.mCachedViews.get(k);
            if (viewholder != null)
            {
                if (viewholder.mPosition >= i + j)
                {
                    viewholder.offsetPosition(-j, flag);
                } else
                if (viewholder.mPosition >= i)
                {
                    viewholder.mFlags = viewholder.mFlags | 8;
                    recycler.addViewHolderToRecycledViewPool((ViewHolder)recycler.mCachedViews.get(k), true);
                    recycler.mCachedViews.remove(k);
                }
            }
            k--;
        }
        requestLayout();
    }

    public void onAttachedToWindow()
    {
        float f;
        boolean flag = true;
        super.onAttachedToWindow();
        mLayoutOrScrollCounter = 0;
        mIsAttached = true;
        Display display;
        if (!mFirstLayoutComplete || isLayoutRequested())
        {
            flag = false;
        }
        mFirstLayoutComplete = flag;
        mPostedAnimatorRunner = false;
        if (!ALLOW_THREAD_GAP_WORK) goto _L2; else goto _L1
_L1:
        mGapWorker = (GapWorker)GapWorker.sGapWorker.get();
        if (mGapWorker != null) goto _L4; else goto _L3
_L3:
        mGapWorker = new GapWorker();
        display = ViewCompat.getDisplay(this);
        if (isInEditMode() || display == null) goto _L6; else goto _L5
_L5:
        f = display.getRefreshRate();
        if (f < 30F) goto _L6; else goto _L7
_L7:
        mGapWorker.mFrameIntervalNs = (long)(1E+09F / f);
        GapWorker.sGapWorker.set(mGapWorker);
_L4:
        mGapWorker.mRecyclerViews.add(this);
_L2:
        return;
_L6:
        f = 60F;
        if (true) goto _L7; else goto _L8
_L8:
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
        }
        stopScroll();
        mIsAttached = false;
        if (mLayout != null)
        {
            mLayout.onDetachedFromWindow(this, mRecycler);
        }
        mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(mItemAnimatorRunner);
        while (ViewInfoStore.InfoRecord.sPool.acquire() != null) ;
        if (ALLOW_THREAD_GAP_WORK && mGapWorker != null)
        {
            mGapWorker.mRecyclerViews.remove(this);
            mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int j = mItemDecorations.size();
        for (int i = 0; i < j; i++)
        {
            mItemDecorations.get(i);
        }

    }

    final void onExitLayoutOrScroll(boolean flag)
    {
        mLayoutOrScrollCounter = mLayoutOrScrollCounter - 1;
        if (mLayoutOrScrollCounter <= 0)
        {
            mLayoutOrScrollCounter = 0;
            if (flag)
            {
                int j = mEatenAccessibilityChangeFlags;
                mEatenAccessibilityChangeFlags = 0;
                if (j != 0)
                {
                    ViewHolder viewholder;
                    int i;
                    if (mAccessibilityManager != null && mAccessibilityManager.isEnabled())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
                        accessibilityevent.setEventType(2048);
                        accessibilityevent.setContentChangeTypes(j);
                        sendAccessibilityEventUnchecked(accessibilityevent);
                    }
                }
                i = mPendingAccessibilityImportanceChange.size() - 1;
                while (i >= 0) 
                {
                    viewholder = (ViewHolder)mPendingAccessibilityImportanceChange.get(i);
                    if (viewholder.itemView.getParent() != this)
                    {
                        continue;
                    }
                    int k;
                    if ((viewholder.mFlags & 0x80) != 0)
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
                    k = viewholder.mPendingAccessibilityState;
                    if (k != -1)
                    {
                        ViewCompat.setImportantForAccessibility(viewholder.itemView, k);
                        viewholder.mPendingAccessibilityState = -1;
                    }
                    i--;
                }
                mPendingAccessibilityImportanceChange.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if (mLayout != null && !mLayoutFrozen && motionevent.getAction() == 8)
        {
            float f;
            float f1;
            if ((motionevent.getSource() & 2) != 0)
            {
                if (mLayout.canScrollVertically())
                {
                    f = -motionevent.getAxisValue(9);
                } else
                {
                    f = 0.0F;
                }
                if (mLayout.canScrollHorizontally())
                {
                    float f2 = motionevent.getAxisValue(10);
                    f1 = f;
                    f = f2;
                } else
                {
                    f1 = f;
                    f = 0.0F;
                }
            } else
            if ((motionevent.getSource() & 0x400000) != 0)
            {
                f = motionevent.getAxisValue(26);
                if (mLayout.canScrollVertically())
                {
                    f1 = -f;
                    f = 0.0F;
                } else
                if (mLayout.canScrollHorizontally())
                {
                    f1 = 0.0F;
                } else
                {
                    f = 0.0F;
                    f1 = 0.0F;
                }
            } else
            {
                f = 0.0F;
                f1 = 0.0F;
            }
            if (f1 != 0.0F || f != 0.0F)
            {
                scrollByInternal((int)(f * mScaledHorizontalScrollFactor), (int)(mScaledVerticalScrollFactor * f1), motionevent);
                return false;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (!mLayoutFrozen) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        int j;
        int l;
        j = motionevent.getAction();
        if (j == 3 || j == 0)
        {
            mActiveOnItemTouchListener = null;
        }
        l = mOnItemTouchListeners.size();
        i = 0;
_L5:
        OnItemTouchListener onitemtouchlistener;
        if (i >= l)
        {
            break MISSING_BLOCK_LABEL_242;
        }
        onitemtouchlistener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
        if (!onitemtouchlistener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIIMG_0(motionevent) || j == 3) goto _L4; else goto _L3
_L3:
        mActiveOnItemTouchListener = onitemtouchlistener;
        i = 1;
_L6:
        if (i != 0)
        {
            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
            stopNestedScroll(0);
            int k;
            boolean flag;
            int i1;
            int j1;
            boolean flag1;
            boolean flag2;
            if (mLeftGlow != null)
            {
                mLeftGlow.onRelease();
                flag2 = mLeftGlow.isFinished();
            } else
            {
                flag2 = false;
            }
            flag1 = flag2;
            if (mTopGlow != null)
            {
                mTopGlow.onRelease();
                flag1 = flag2 | mTopGlow.isFinished();
            }
            flag2 = flag1;
            if (mRightGlow != null)
            {
                mRightGlow.onRelease();
                flag2 = flag1 | mRightGlow.isFinished();
            }
            flag1 = flag2;
            if (mBottomGlow != null)
            {
                mBottomGlow.onRelease();
                flag1 = flag2 | mBottomGlow.isFinished();
            }
            if (flag1)
            {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            setScrollState(0);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        i++;
          goto _L5
        i = 0;
          goto _L6
        if (mLayout == null) goto _L1; else goto _L7
_L7:
        flag1 = mLayout.canScrollHorizontally();
        flag2 = mLayout.canScrollVertically();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        k = motionevent.getActionMasked();
        i = motionevent.getActionIndex();
        k;
        JVM INSTR tableswitch 0 6: default 348
    //                   0 358
    //                   1 745
    //                   2 568
    //                   3 782
    //                   4 348
    //                   5 511
    //                   6 737;
           goto _L8 _L9 _L10 _L11 _L12 _L8 _L13 _L14
_L12:
        break MISSING_BLOCK_LABEL_782;
_L8:
        break; /* Loop/switch isn't completed */
_L9:
        break; /* Loop/switch isn't completed */
_L16:
        if (mScrollState == 1)
        {
            return true;
        }
        if (true) goto _L1; else goto _L15
_L15:
        if (mIgnoreMotionEventTillDown)
        {
            mIgnoreMotionEventTillDown = false;
        }
        mScrollPointerId = motionevent.getPointerId(0);
        i = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        if (mScrollState == 2)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
            setScrollState(1);
        }
        motionevent = mNestedOffsets;
        mNestedOffsets[1] = 0;
        motionevent[0] = 0;
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        k = i;
        if (flag2)
        {
            k = i | 2;
        }
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        mScrollingChildHelper.startNestedScroll(k, 0);
          goto _L16
_L13:
        mScrollPointerId = motionevent.getPointerId(i);
        k = (int)(motionevent.getX(i) + 0.5F);
        mLastTouchX = k;
        mInitialTouchX = k;
        i = (int)(motionevent.getY(i) + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
          goto _L16
_L11:
        k = motionevent.findPointerIndex(mScrollPointerId);
        if (k < 0)
        {
            Log.e("RecyclerView", (new StringBuilder("Error processing scroll; pointer index for id ")).append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        i = (int)(motionevent.getX(k) + 0.5F);
        i1 = (int)(motionevent.getY(k) + 0.5F);
        if (mScrollState != 1)
        {
            k = mInitialTouchX;
            j1 = mInitialTouchY;
            if (flag1 && Math.abs(i - k) > mTouchSlop)
            {
                mLastTouchX = i;
                i = 1;
            } else
            {
                i = 0;
            }
            flag = i;
            if (flag2)
            {
                flag = i;
                if (Math.abs(i1 - j1) > mTouchSlop)
                {
                    mLastTouchY = i1;
                    flag = true;
                }
            }
            if (flag)
            {
                setScrollState(1);
            }
        }
          goto _L16
_L14:
        onPointerUp(motionevent);
          goto _L16
_L10:
        mVelocityTracker.clear();
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        mScrollingChildHelper.stopNestedScroll(0);
          goto _L16
        if (mVelocityTracker != null)
        {
            mVelocityTracker.clear();
        }
        stopNestedScroll(0);
        if (mLeftGlow != null)
        {
            mLeftGlow.onRelease();
            flag2 = mLeftGlow.isFinished();
        } else
        {
            flag2 = false;
        }
        flag1 = flag2;
        if (mTopGlow != null)
        {
            mTopGlow.onRelease();
            flag1 = flag2 | mTopGlow.isFinished();
        }
        flag2 = flag1;
        if (mRightGlow != null)
        {
            mRightGlow.onRelease();
            flag2 = flag1 | mRightGlow.isFinished();
        }
        flag1 = flag2;
        if (mBottomGlow != null)
        {
            mBottomGlow.onRelease();
            flag1 = flag2 | mBottomGlow.isFinished();
        }
        if (flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        setScrollState(0);
          goto _L16
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        Trace.endSection();
        mFirstLayoutComplete = true;
    }

    public void onMeasure(int i, int j)
    {
        boolean flag1 = false;
        if (mLayout != null) goto _L2; else goto _L1
_L1:
        defaultOnMeasure(i, j);
_L4:
        return;
_L2:
        if (!mLayout.isAutoMeasureEnabled())
        {
            break; /* Loop/switch isn't completed */
        }
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        mLayout.mRecyclerView.defaultOnMeasure(i, j);
        boolean flag = flag1;
        if (k == 0x40000000)
        {
            flag = flag1;
            if (l == 0x40000000)
            {
                flag = true;
            }
        }
        if (!flag && mAdapter != null)
        {
            if (mState.mLayoutStep == 1)
            {
                dispatchLayoutStep1();
            }
            mLayout.setMeasureSpecs(i, j);
            mState.mIsMeasuring = true;
            dispatchLayoutStep2();
            mLayout.setMeasuredDimensionFromChildren(i, j);
            if (mLayout.shouldMeasureTwice())
            {
                mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000));
                mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                mLayout.setMeasuredDimensionFromChildren(i, j);
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (mHasFixedSize)
        {
            mLayout.mRecyclerView.defaultOnMeasure(i, j);
            return;
        }
        if (mAdapterUpdateDuringMeasure)
        {
            mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
            if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
            {
                mLayoutWasDefered = false;
            }
            mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll(true);
            if (mState.mRunPredictiveAnimations)
            {
                mState.mInPreLayout = true;
            } else
            {
                mAdapterHelper.consumeUpdatesInOnePass();
                mState.mInPreLayout = false;
            }
            mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else
        if (mState.mRunPredictiveAnimations)
        {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        if (mAdapter != null)
        {
            mState.mItemCount = mAdapter.getItemCount();
        } else
        {
            mState.mItemCount = 0;
        }
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayout.mRecyclerView.defaultOnMeasure(i, j);
        stopInterceptRequestLayout(false);
        mState.mInPreLayout = false;
        return;
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        boolean flag;
        if (mLayoutOrScrollCounter > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return false;
        } else
        {
            return super.onRequestFocusInDescendants(i, rect);
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            mPendingSavedState = (SavedState)parcelable;
            super.onRestoreInstanceState(((AbsSavedState) (mPendingSavedState)).mSuperState);
            if (mLayout != null && mPendingSavedState.mLayoutState != null)
            {
                mLayout.onRestoreInstanceState(mPendingSavedState.mLayoutState);
                return;
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mPendingSavedState != null)
        {
            savedstate.mLayoutState = mPendingSavedState.mLayoutState;
            return savedstate;
        }
        if (mLayout != null)
        {
            savedstate.mLayoutState = mLayout.onSaveInstanceState();
            return savedstate;
        } else
        {
            savedstate.mLayoutState = null;
            return savedstate;
        }
    }

    public void onScrollStateChanged(int i)
    {
    }

    public void onScrolled(int i, int j)
    {
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k || j != l)
        {
            mBottomGlow = null;
            mTopGlow = null;
            mRightGlow = null;
            mLeftGlow = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        if (mLayoutFrozen || mIgnoreMotionEventTillDown)
        {
            return false;
        }
        i = motionevent.getAction();
        if (mActiveOnItemTouchListener == null) goto _L2; else goto _L1
_L1:
        if (i != 0) goto _L4; else goto _L3
_L3:
        mActiveOnItemTouchListener = null;
_L2:
        if (i == 0) goto _L6; else goto _L5
_L5:
        int j;
        j = mOnItemTouchListeners.size();
        i = 0;
_L32:
        if (i >= j) goto _L6; else goto _L7
_L7:
        Object obj = (OnItemTouchListener)mOnItemTouchListeners.get(i);
        if (!((OnItemTouchListener) (obj))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIIMG_0(motionevent)) goto _L9; else goto _L8
_L8:
        mActiveOnItemTouchListener = ((OnItemTouchListener) (obj));
        i = 1;
_L11:
        if (i != 0)
        {
            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
            stopNestedScroll(0);
            boolean flag3 = false;
            if (mLeftGlow != null)
            {
                mLeftGlow.onRelease();
                flag3 = mLeftGlow.isFinished();
            }
            boolean flag = flag3;
            if (mTopGlow != null)
            {
                mTopGlow.onRelease();
                flag = flag3 | mTopGlow.isFinished();
            }
            flag3 = flag;
            if (mRightGlow != null)
            {
                mRightGlow.onRelease();
                flag3 = flag | mRightGlow.isFinished();
            }
            flag = flag3;
            if (mBottomGlow != null)
            {
                mBottomGlow.onRelease();
                flag = flag3 | mBottomGlow.isFinished();
            }
            if (flag)
            {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            setScrollState(0);
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        mActiveOnItemTouchListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0(motionevent);
        if (i == 3 || i == 1)
        {
            mActiveOnItemTouchListener = null;
        }
        i = 1;
        continue; /* Loop/switch isn't completed */
_L9:
        i++;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 0;
        if (true) goto _L11; else goto _L10
_L10:
        boolean flag1;
        boolean flag4;
        if (mLayout == null)
        {
            return false;
        }
        flag1 = mLayout.canScrollHorizontally();
        flag4 = mLayout.canScrollVertically();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        obj = MotionEvent.obtain(motionevent);
        j = motionevent.getActionMasked();
        i = motionevent.getActionIndex();
        if (j == 0)
        {
            int ai[] = mNestedOffsets;
            mNestedOffsets[1] = 0;
            ai[0] = 0;
        }
        ((MotionEvent) (obj)).offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);
        j;
        JVM INSTR tableswitch 0 6: default 448
    //                   0 472
    //                   1 1192
    //                   2 655
    //                   3 1766
    //                   4 448
    //                   5 589
    //                   6 1181;
           goto _L12 _L13 _L14 _L15 _L16 _L12 _L17 _L18
_L12:
        i = 0;
_L19:
        if (i == 0)
        {
            mVelocityTracker.addMovement(((MotionEvent) (obj)));
        }
        ((MotionEvent) (obj)).recycle();
        return true;
_L13:
        mScrollPointerId = motionevent.getPointerId(0);
        i = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = 0;
        if (flag1)
        {
            i = 1;
        }
        j = i;
        if (flag4)
        {
            j = i | 2;
        }
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        mScrollingChildHelper.startNestedScroll(j, 0);
        i = 0;
          goto _L19
_L17:
        mScrollPointerId = motionevent.getPointerId(i);
        j = (int)(motionevent.getX(i) + 0.5F);
        mLastTouchX = j;
        mInitialTouchX = j;
        i = (int)(motionevent.getY(i) + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = 0;
          goto _L19
_L15:
        i = motionevent.findPointerIndex(mScrollPointerId);
        if (i < 0)
        {
            Log.e("RecyclerView", (new StringBuilder("Error processing scroll; pointer index for id ")).append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        int j2 = (int)(motionevent.getX(i) + 0.5F);
        int k2 = (int)(motionevent.getY(i) + 0.5F);
        int j1 = mLastTouchX - j2;
        int k = mLastTouchY - k2;
        motionevent = mScrollConsumed;
        int ai1[] = mScrollOffset;
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        j = j1;
        i = k;
        if (mScrollingChildHelper.dispatchNestedPreScroll(j1, k, motionevent, ai1, 0))
        {
            j = j1 - mScrollConsumed[0];
            i = k - mScrollConsumed[1];
            ((MotionEvent) (obj)).offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        }
        j1 = j;
        int l1 = i;
        if (mScrollState != 1)
        {
            l1 = 0;
            j1 = l1;
            int l = j;
            if (flag1)
            {
                j1 = l1;
                l = j;
                if (Math.abs(j) > mTouchSlop)
                {
                    int i2;
                    if (j > 0)
                    {
                        j -= mTouchSlop;
                    } else
                    {
                        j = mTouchSlop + j;
                    }
                    j1 = 1;
                    l = j;
                }
            }
            i2 = j1;
            j = i;
            if (flag4)
            {
                i2 = j1;
                j = i;
                if (Math.abs(i) > mTouchSlop)
                {
                    if (i > 0)
                    {
                        i -= mTouchSlop;
                    } else
                    {
                        i = mTouchSlop + i;
                    }
                    i2 = 1;
                    j = i;
                }
            }
            j1 = l;
            l1 = j;
            if (i2 != 0)
            {
                setScrollState(1);
                l1 = j;
                j1 = l;
            }
        }
        if (mScrollState == 1)
        {
            mLastTouchX = j2 - mScrollOffset[0];
            mLastTouchY = k2 - mScrollOffset[1];
            if (flag1)
            {
                i = j1;
            } else
            {
                i = 0;
            }
            if (flag4)
            {
                j = l1;
            } else
            {
                j = 0;
            }
            if (scrollByInternal(i, j, ((MotionEvent) (obj))))
            {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            if (mGapWorker != null && (j1 != 0 || l1 != 0))
            {
                mGapWorker.postFromTraversal(this, j1, l1);
            }
        }
        i = 0;
          goto _L19
_L18:
        onPointerUp(motionevent);
        i = 0;
          goto _L19
_L14:
        int i1;
        mVelocityTracker.addMovement(((MotionEvent) (obj)));
        mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
        float f;
        float f1;
        if (flag1)
        {
            f = -mVelocityTracker.getXVelocity(mScrollPointerId);
        } else
        {
            f = 0.0F;
        }
        if (flag4)
        {
            f1 = -mVelocityTracker.getYVelocity(mScrollPointerId);
        } else
        {
            f1 = 0.0F;
        }
        if (f == 0.0F && f1 == 0.0F) goto _L21; else goto _L20
_L20:
        j = (int)f;
        i1 = (int)f1;
        if (mLayout != null) goto _L23; else goto _L22
_L22:
        Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
_L26:
        i = 0;
_L28:
        if (i != 0) goto _L24; else goto _L21
_L21:
        setScrollState(0);
_L24:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.clear();
        }
        stopNestedScroll(0);
        flag4 = false;
        if (mLeftGlow != null)
        {
            mLeftGlow.onRelease();
            flag4 = mLeftGlow.isFinished();
        }
        flag1 = flag4;
        if (mTopGlow != null)
        {
            mTopGlow.onRelease();
            flag1 = flag4 | mTopGlow.isFinished();
        }
        flag4 = flag1;
        if (mRightGlow != null)
        {
            mRightGlow.onRelease();
            flag4 = flag1 | mRightGlow.isFinished();
        }
        flag1 = flag4;
        if (mBottomGlow != null)
        {
            mBottomGlow.onRelease();
            flag1 = flag4 | mBottomGlow.isFinished();
        }
        if (flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        i = 1;
          goto _L19
_L23:
        if (mLayoutFrozen) goto _L26; else goto _L25
_L25:
        boolean flag6;
        flag4 = mLayout.canScrollHorizontally();
        flag6 = mLayout.canScrollVertically();
        if (!flag4 || Math.abs(j) < mMinFlingVelocity)
        {
            j = 0;
        }
        if (!flag6 || Math.abs(i1) < mMinFlingVelocity)
        {
            i1 = 0;
        }
        if (j == 0 && i1 == 0 || dispatchNestedPreFling(j, i1)) goto _L26; else goto _L27
_L27:
        if (flag4 || flag6)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        dispatchNestedFling(j, i1, flag1);
        if (mOnFlingListener == null || !mOnFlingListener.onFling(j, i1))
        {
            continue; /* Loop/switch isn't completed */
        }
        i = 1;
          goto _L28
        if (!flag1) goto _L26; else goto _L29
_L29:
        i = 0;
        if (flag4)
        {
            i = 1;
        }
        int k1 = i;
        if (flag6)
        {
            k1 = i | 2;
        }
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        mScrollingChildHelper.startNestedScroll(k1, 1);
        i = Math.max(-mMaxFlingVelocity, Math.min(j, mMaxFlingVelocity));
        j = Math.max(-mMaxFlingVelocity, Math.min(i1, mMaxFlingVelocity));
        motionevent = mViewFlinger;
        ((ViewFlinger) (motionevent))._fld0.setScrollState(2);
        motionevent.mLastFlingY = 0;
        motionevent.mLastFlingX = 0;
        ((ViewFlinger) (motionevent)).mScroller.fling(0, 0, i, j, 0x80000000, 0x7fffffff, 0x80000000, 0x7fffffff);
        motionevent.postOnAnimation();
        i = 1;
          goto _L28
_L16:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.clear();
        }
        stopNestedScroll(0);
        boolean flag5 = false;
        if (mLeftGlow != null)
        {
            mLeftGlow.onRelease();
            flag5 = mLeftGlow.isFinished();
        }
        boolean flag2 = flag5;
        if (mTopGlow != null)
        {
            mTopGlow.onRelease();
            flag2 = flag5 | mTopGlow.isFinished();
        }
        flag5 = flag2;
        if (mRightGlow != null)
        {
            mRightGlow.onRelease();
            flag5 = flag2 | mRightGlow.isFinished();
        }
        flag2 = flag5;
        if (mBottomGlow != null)
        {
            mBottomGlow.onRelease();
            flag2 = flag5 | mBottomGlow.isFinished();
        }
        if (flag2)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        setScrollState(0);
        if (true) goto _L12; else goto _L30
_L30:
        if (true) goto _L32; else goto _L31
_L31:
    }

    final void processDataSetCompletelyChanged(boolean flag)
    {
        boolean flag2 = false;
        mDispatchItemsChangedEvent = mDispatchItemsChangedEvent | flag;
        mDataSetHasChangedAfterLayout = true;
        int l = mChildHelper.mCallback.getChildCount();
        int i = 0;
        while (i < l) 
        {
            Object obj = mChildHelper.mCallback.getChildAt(i);
            boolean flag1;
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if (obj == null)
            {
                continue;
            }
            if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                obj.mFlags = ((ViewHolder) (obj)).mFlags | 6;
            }
            i++;
        }
        markItemDecorInsetsDirty();
        Recycler recycler = mRecycler;
        int k = recycler.mCachedViews.size();
        for (int j = ((flag2) ? 1 : 0); j < k; j++)
        {
            ViewHolder viewholder = (ViewHolder)recycler.mCachedViews.get(j);
            if (viewholder != null)
            {
                viewholder.mFlags = viewholder.mFlags | 6;
                viewholder.addChangePayload(null);
            }
        }

        if (recycler._fld0.mAdapter == null || !recycler._fld0.mAdapter.mHasStableIds)
        {
            recycler.recycleAndClearCachedViews();
        }
    }

    final void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        boolean flag1 = true;
        viewholder.mFlags = viewholder.mFlags & 0xffffdfff | 0;
        if (mState.mTrackOldChangeHolders)
        {
            boolean flag;
            if ((viewholder.mFlags & 2) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if ((viewholder.mFlags & 8) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    if ((viewholder.mFlags & 0x80) != 0)
                    {
                        flag = flag1;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        long l;
                        if (mAdapter.mHasStableIds)
                        {
                            l = viewholder.mItemId;
                        } else
                        {
                            l = viewholder.mPosition;
                        }
                        mViewInfoStore.mOldChangedHolders.put(l, viewholder);
                    }
                }
            }
        }
        mViewInfoStore.addToPreLayout(viewholder, itemholderinfo);
    }

    public final void removeAndRecycleViews()
    {
        if (mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
        }
        if (mLayout != null)
        {
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
        }
        Recycler recycler = mRecycler;
        recycler.mAttachedScrap.clear();
        recycler.recycleAndClearCachedViews();
    }

    protected void removeDetachedView(View view, boolean flag)
    {
        ViewHolder viewholder;
        if (view == null)
        {
            viewholder = null;
        } else
        {
            viewholder = ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
        if (viewholder != null)
        {
            boolean flag1;
            if ((viewholder.mFlags & 0x100) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                viewholder.mFlags = viewholder.mFlags & 0xfffffeff;
            } else
            {
                boolean flag2;
                if ((viewholder.mFlags & 0x80) != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    throw new IllegalArgumentException((new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.")).append(viewholder).append(exceptionLabel()).toString());
                }
            }
        }
        view.clearAnimation();
        if (view == null)
        {
            viewholder = null;
        } else
        {
            viewholder = ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
        if (mAdapter != null && viewholder != null)
        {
            mAdapter.onViewDetachedFromWindow(viewholder);
        }
        super.removeDetachedView(view, flag);
    }

    public void requestChildFocus(View view, View view1)
    {
        boolean flag1 = true;
        LayoutManager layoutmanager = mLayout;
        boolean flag;
        if (mLayoutOrScrollCounter > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag && view1 != null)
        {
            requestChildOnScreen(view, view1);
        }
        super.requestChildFocus(view, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        return mLayout.requestChildRectangleOnScreen(this, view, rect, flag, false);
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        int j = mOnItemTouchListeners.size();
        for (int i = 0; i < j; i++)
        {
            ((OnItemTouchListener)mOnItemTouchListeners.get(i))._mth51D2ILG_0();
        }

        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        if (mInterceptRequestLayoutDepth == 0 && !mLayoutFrozen)
        {
            super.requestLayout();
            return;
        } else
        {
            mLayoutWasDefered = true;
            return;
        }
    }

    public void scrollBy(int i, int j)
    {
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else
        if (!mLayoutFrozen)
        {
            boolean flag = mLayout.canScrollHorizontally();
            boolean flag1 = mLayout.canScrollVertically();
            if (flag || flag1)
            {
                if (!flag)
                {
                    i = 0;
                }
                if (!flag1)
                {
                    j = 0;
                }
                scrollByInternal(i, j, null);
                return;
            }
        }
    }

    final void scrollStep(int i, int j, int ai[])
    {
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth + 1;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
        Trace.beginSection("RV Scroll");
        fillRemainingScrollValues(mState);
        ChildHelper childhelper;
        int l;
        int i1;
        if (i != 0)
        {
            i = mLayout.scrollHorizontallyBy(i, mRecycler, mState);
        } else
        {
            i = 0;
        }
        if (j != 0)
        {
            j = mLayout.scrollVerticallyBy(j, mRecycler, mState);
        } else
        {
            j = 0;
        }
        Trace.endSection();
        childhelper = mChildHelper;
        l = childhelper.mCallback.getChildCount();
        i1 = childhelper.mHiddenViews.size();
        for (int k = 0; k < l - i1; k++)
        {
            Object obj = mChildHelper;
            int j1 = ((ChildHelper) (obj)).getOffset(k);
            obj = ((ChildHelper) (obj)).mCallback.getChildAt(j1);
            Object obj1 = getChildViewHolder(((View) (obj)));
            if (obj1 == null || ((ViewHolder) (obj1)).mShadowingHolder == null)
            {
                continue;
            }
            obj1 = ((ViewHolder) (obj1)).mShadowingHolder.itemView;
            j1 = ((View) (obj)).getLeft();
            int k1 = ((View) (obj)).getTop();
            if (j1 != ((View) (obj1)).getLeft() || k1 != ((View) (obj1)).getTop())
            {
                ((View) (obj1)).layout(j1, k1, ((View) (obj1)).getWidth() + j1, ((View) (obj1)).getHeight() + k1);
            }
        }

        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        if (ai != null)
        {
            ai[0] = i;
            ai[1] = j;
        }
    }

    public void scrollTo(int i, int j)
    {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public final void scrollToPosition(int i)
    {
        if (mLayoutFrozen)
        {
            return;
        }
        setScrollState(0);
        Object obj = mViewFlinger;
        ((ViewFlinger) (obj))._fld0.removeCallbacks(((Runnable) (obj)));
        ((ViewFlinger) (obj)).mScroller.abortAnimation();
        if (mLayout != null)
        {
            obj = mLayout;
        }
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        } else
        {
            mLayout.scrollToPosition(0);
            awakenScrollBars();
            return;
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityevent)
    {
        boolean flag = false;
        boolean flag1 = false;
        int i;
        if (mLayoutOrScrollCounter > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            if (accessibilityevent != null)
            {
                i = accessibilityevent.getContentChangeTypes();
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                i = ((flag1) ? 1 : 0);
            }
            mEatenAccessibilityChangeFlags = i | mEatenAccessibilityChangeFlags;
            flag = true;
        }
        if (flag)
        {
            return;
        } else
        {
            super.sendAccessibilityEventUnchecked(accessibilityevent);
            return;
        }
    }

    public final void setAdapter(Adapter adapter)
    {
        boolean flag1 = false;
        if (mLayoutFrozen)
        {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            mLayoutFrozen = false;
            if (mLayoutWasDefered && mLayout != null && mAdapter != null)
            {
                requestLayout();
            }
            mLayoutWasDefered = false;
        }
        if (mAdapter != null)
        {
            Adapter adapter1 = mAdapter;
            RecyclerViewDataObserver recyclerviewdataobserver = mObserver;
            adapter1.mObservable.unregisterObserver(recyclerviewdataobserver);
            mAdapter.onDetachedFromRecyclerView(this);
        }
        removeAndRecycleViews();
        Object obj = mAdapterHelper;
        ((AdapterHelper) (obj)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj)).mPendingUpdates);
        ((AdapterHelper) (obj)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj)).mPostponedList);
        obj.mExistingUpdateTypes = 0;
        obj = mAdapter;
        mAdapter = adapter;
        if (adapter != null)
        {
            RecyclerViewDataObserver recyclerviewdataobserver1 = mObserver;
            adapter.mObservable.registerObserver(recyclerviewdataobserver1);
            adapter.onAttachedToRecyclerView(this);
        }
        Object obj1 = mRecycler;
        adapter = mAdapter;
        ((Recycler) (obj1)).mAttachedScrap.clear();
        ((Recycler) (obj1)).recycleAndClearCachedViews();
        if (((Recycler) (obj1)).mRecyclerPool == null)
        {
            obj1.mRecyclerPool = new RecycledViewPool();
        }
        obj1 = ((Recycler) (obj1)).mRecyclerPool;
        if (obj != null)
        {
            obj1.mAttachCount = ((RecycledViewPool) (obj1)).mAttachCount - 1;
        }
        if (((RecycledViewPool) (obj1)).mAttachCount == 0)
        {
            for (int i = 0; i < ((RecycledViewPool) (obj1)).mScrap.size(); i++)
            {
                ((RecycledViewPool.ScrapData)((RecycledViewPool) (obj1)).mScrap.valueAt(i)).mScrapHeap.clear();
            }

        }
        if (adapter != null)
        {
            obj1.mAttachCount = ((RecycledViewPool) (obj1)).mAttachCount + 1;
        }
        mState.mStructureChanged = true;
        mDispatchItemsChangedEvent = mDispatchItemsChangedEvent | false;
        mDataSetHasChangedAfterLayout = true;
        int i1 = mChildHelper.mCallback.getChildCount();
        int j = 0;
        while (j < i1) 
        {
            adapter = mChildHelper.mCallback.getChildAt(j);
            boolean flag;
            if (adapter == null)
            {
                adapter = null;
            } else
            {
                adapter = ((LayoutParams)adapter.getLayoutParams()).mViewHolder;
            }
            if (adapter == null)
            {
                continue;
            }
            if ((((ViewHolder) (adapter)).mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                adapter.mFlags = ((ViewHolder) (adapter)).mFlags | 6;
            }
            j++;
        }
        markItemDecorInsetsDirty();
        adapter = mRecycler;
        int l = ((Recycler) (adapter)).mCachedViews.size();
        for (int k = ((flag1) ? 1 : 0); k < l; k++)
        {
            ViewHolder viewholder = (ViewHolder)((Recycler) (adapter)).mCachedViews.get(k);
            if (viewholder != null)
            {
                viewholder.mFlags = viewholder.mFlags | 6;
                viewholder.addChangePayload(null);
            }
        }

        if (((Recycler) (adapter))._fld0.mAdapter == null || !((Recycler) (adapter))._fld0.mAdapter.mHasStableIds)
        {
            adapter.recycleAndClearCachedViews();
        }
        requestLayout();
    }

    public final void setChildDrawingOrderCallback(ChildDrawingOrderCallback childdrawingordercallback)
    {
        if (childdrawingordercallback == mChildDrawingOrderCallback)
        {
            return;
        }
        mChildDrawingOrderCallback = childdrawingordercallback;
        boolean flag;
        if (mChildDrawingOrderCallback != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setChildrenDrawingOrderEnabled(flag);
    }

    final boolean setChildImportantForAccessibilityInternal(ViewHolder viewholder, int i)
    {
        boolean flag;
        if (mLayoutOrScrollCounter > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            viewholder.mPendingAccessibilityState = i;
            mPendingAccessibilityImportanceChange.add(viewholder);
            return false;
        } else
        {
            ViewCompat.setImportantForAccessibility(viewholder.itemView, i);
            return true;
        }
    }

    public void setClipToPadding(boolean flag)
    {
        if (flag != mClipToPadding)
        {
            mBottomGlow = null;
            mTopGlow = null;
            mRightGlow = null;
            mLeftGlow = null;
        }
        mClipToPadding = flag;
        super.setClipToPadding(flag);
        if (mFirstLayoutComplete)
        {
            requestLayout();
        }
    }

    public void setItemViewCacheSize(int i)
    {
        Recycler recycler = mRecycler;
        recycler.mRequestedCacheMax = i;
        recycler.updateViewCacheSize();
    }

    public final void setLayoutManager(LayoutManager layoutmanager)
    {
        if (layoutmanager == mLayout)
        {
            return;
        }
        if (mScrollState != 0)
        {
            mScrollState = 0;
            Object obj = mViewFlinger;
            ((ViewFlinger) (obj))._fld0.removeCallbacks(((Runnable) (obj)));
            ((ViewFlinger) (obj)).mScroller.abortAnimation();
            if (mLayout != null)
            {
                obj = mLayout;
            }
            if (mLayout != null)
            {
                mLayout.onScrollStateChanged(0);
            }
            onScrollStateChanged(0);
            if (mScrollListener != null)
            {
                mScrollListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(0);
            }
            if (mScrollListeners != null)
            {
                for (int i = mScrollListeners.size() - 1; i >= 0; i--)
                {
                    ((OnScrollListener)mScrollListeners.get(i))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(0);
                }

            }
        }
        Object obj1 = mViewFlinger;
        ((ViewFlinger) (obj1))._fld0.removeCallbacks(((Runnable) (obj1)));
        ((ViewFlinger) (obj1)).mScroller.abortAnimation();
        if (mLayout != null)
        {
            obj1 = mLayout;
        }
        ChildHelper.Bucket bucket;
        if (mLayout != null)
        {
            if (mItemAnimator != null)
            {
                mItemAnimator.endAnimations();
            }
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
            obj1 = mRecycler;
            ((Recycler) (obj1)).mAttachedScrap.clear();
            ((Recycler) (obj1)).recycleAndClearCachedViews();
            if (mIsAttached)
            {
                mLayout.onDetachedFromWindow(this, mRecycler);
            }
            mLayout.setRecyclerView(null);
            mLayout = null;
        } else
        {
            obj1 = mRecycler;
            ((Recycler) (obj1)).mAttachedScrap.clear();
            ((Recycler) (obj1)).recycleAndClearCachedViews();
        }
        obj1 = mChildHelper;
        bucket = ((ChildHelper) (obj1)).mBucket;
        bucket.mData = 0L;
        if (bucket.mNext != null)
        {
            bucket = bucket.mNext;
            bucket.mData = 0L;
            if (bucket.mNext != null)
            {
                bucket.mNext.reset();
            }
        }
        for (int j = ((ChildHelper) (obj1)).mHiddenViews.size() - 1; j >= 0; j--)
        {
            ((ChildHelper) (obj1)).mCallback.onLeftHiddenState((View)((ChildHelper) (obj1)).mHiddenViews.get(j));
            ((ChildHelper) (obj1)).mHiddenViews.remove(j);
        }

        ((ChildHelper) (obj1)).mCallback.removeAllViews();
        mLayout = layoutmanager;
        if (layoutmanager != null)
        {
            if (layoutmanager.mRecyclerView != null)
            {
                throw new IllegalArgumentException((new StringBuilder("LayoutManager ")).append(layoutmanager).append(" is already attached to a RecyclerView:").append(layoutmanager.mRecyclerView.exceptionLabel()).toString());
            }
            mLayout.setRecyclerView(this);
        }
        mRecycler.updateViewCacheSize();
        requestLayout();
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        NestedScrollingChildHelper nestedscrollingchildhelper = mScrollingChildHelper;
        if (nestedscrollingchildhelper.mIsNestedScrollingEnabled)
        {
            ViewCompat.stopNestedScroll(nestedscrollingchildhelper.mView);
        }
        nestedscrollingchildhelper.mIsNestedScrollingEnabled = flag;
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        mScrollListener = onscrolllistener;
    }

    final void setScrollState(int i)
    {
        if (i != mScrollState) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mScrollState = i;
        if (i != 2)
        {
            Object obj = mViewFlinger;
            ((ViewFlinger) (obj))._fld0.removeCallbacks(((Runnable) (obj)));
            ((ViewFlinger) (obj)).mScroller.abortAnimation();
            if (mLayout != null)
            {
                obj = mLayout;
            }
        }
        if (mLayout != null)
        {
            mLayout.onScrollStateChanged(i);
        }
        onScrollStateChanged(i);
        if (mScrollListener != null)
        {
            mScrollListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(i);
        }
        if (mScrollListeners != null)
        {
            int j = mScrollListeners.size() - 1;
            while (j >= 0) 
            {
                ((OnScrollListener)mScrollListeners.get(j))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(i);
                j--;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setScrollingTouchSlop(int i)
    {
        ViewConfiguration viewconfiguration = ViewConfiguration.get(getContext());
        switch (i)
        {
        default:
            Log.w("RecyclerView", (new StringBuilder("setScrollingTouchSlop(): bad argument constant ")).append(i).append("; using default value").toString());
            // fall through

        case 0: // '\0'
            mTouchSlop = viewconfiguration.getScaledTouchSlop();
            return;

        case 1: // '\001'
            mTouchSlop = viewconfiguration.getScaledPagingTouchSlop();
            break;
        }
    }

    public final void smoothScrollBy(int i, int j)
    {
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else
        if (!mLayoutFrozen)
        {
            if (!mLayout.canScrollHorizontally())
            {
                i = 0;
            }
            if (!mLayout.canScrollVertically())
            {
                j = 0;
            }
            if (i != 0 || j != 0)
            {
                ViewFlinger viewflinger = mViewFlinger;
                int k = viewflinger.computeScrollDuration(i, j, 0, 0);
                Interpolator interpolator;
                if (true)
                {
                    interpolator = sQuinticInterpolator;
                } else
                {
                    interpolator = null;
                }
                viewflinger.smoothScrollBy(i, j, k, interpolator);
                return;
            }
        }
    }

    public boolean startNestedScroll(int i)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper.startNestedScroll(i, 0);
    }

    final void stopInterceptRequestLayout(boolean flag)
    {
        if (mInterceptRequestLayoutDepth <= 0)
        {
            mInterceptRequestLayoutDepth = 1;
        }
        if (!flag && !mLayoutFrozen)
        {
            mLayoutWasDefered = false;
        }
        if (mInterceptRequestLayoutDepth == 1)
        {
            if (flag && mLayoutWasDefered && !mLayoutFrozen && mLayout != null && mAdapter != null)
            {
                dispatchLayout();
            }
            if (!mLayoutFrozen)
            {
                mLayoutWasDefered = false;
            }
        }
        mInterceptRequestLayoutDepth = mInterceptRequestLayoutDepth - 1;
    }

    public void stopNestedScroll()
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        NestedScrollingChildHelper nestedscrollingchildhelper = mScrollingChildHelper;
        ViewParent viewparent = nestedscrollingchildhelper.getNestedScrollingParentForType(0);
        if (viewparent != null)
        {
            ViewParentCompat.onStopNestedScroll(viewparent, nestedscrollingchildhelper.mView, 0);
            nestedscrollingchildhelper.setNestedScrollingParentForType(0, null);
        }
    }

    public final void stopNestedScroll(int i)
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        mScrollingChildHelper.stopNestedScroll(i);
    }

    public final void stopScroll()
    {
        setScrollState(0);
        Object obj = mViewFlinger;
        ((ViewFlinger) (obj))._fld0.removeCallbacks(((Runnable) (obj)));
        ((ViewFlinger) (obj)).mScroller.abortAnimation();
        if (mLayout != null)
        {
            obj = mLayout;
        }
    }

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = flag;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = (new Class[] {
            android/content/Context, android/util/AttributeSet, Integer.TYPE, Integer.TYPE
        });
    }





    private class _cls1
        implements Runnable
    {

        private final RecyclerView this$0;

        public final void run()
        {
            if (!mFirstLayoutComplete || isLayoutRequested())
            {
                return;
            }
            if (!mIsAttached)
            {
                requestLayout();
                return;
            }
            if (mLayoutFrozen)
            {
                mLayoutWasDefered = true;
                return;
            } else
            {
                consumePendingUpdateOperations();
                return;
            }
        }

        _cls1()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final RecyclerView this$0;

        public final void run()
        {
            if (mItemAnimator != null)
            {
                mItemAnimator.runPendingAnimations();
            }
            mPostedAnimatorRunner = false;
        }

        _cls2()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }


    private class _cls4
        implements ViewInfoStore.ProcessCallback
    {

        private final RecyclerView this$0;

        public final void processAppeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
        {
            RecyclerView recyclerview = RecyclerView.this;
            viewholder.setIsRecyclable(false);
            if (recyclerview.mItemAnimator.animateAppearance(viewholder, itemholderinfo, itemholderinfo1) && !recyclerview.mPostedAnimatorRunner && recyclerview.mIsAttached)
            {
                ViewCompat.postOnAnimation(recyclerview, recyclerview.mItemAnimatorRunner);
                recyclerview.mPostedAnimatorRunner = true;
            }
        }

        public final void processDisappeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
        {
            mRecycler.unscrapView(viewholder);
            RecyclerView recyclerview = RecyclerView.this;
            recyclerview.addAnimatingView(viewholder);
            viewholder.setIsRecyclable(false);
            if (recyclerview.mItemAnimator.animateDisappearance(viewholder, itemholderinfo, itemholderinfo1) && !recyclerview.mPostedAnimatorRunner && recyclerview.mIsAttached)
            {
                ViewCompat.postOnAnimation(recyclerview, recyclerview.mItemAnimatorRunner);
                recyclerview.mPostedAnimatorRunner = true;
            }
        }

        public final void processPersistent(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
        {
            viewholder.setIsRecyclable(false);
            if (mDataSetHasChangedAfterLayout)
            {
                if (mItemAnimator.animateChange(viewholder, viewholder, itemholderinfo, itemholderinfo1))
                {
                    viewholder = RecyclerView.this;
                    if (!((RecyclerView) (viewholder)).mPostedAnimatorRunner && ((RecyclerView) (viewholder)).mIsAttached)
                    {
                        ViewCompat.postOnAnimation(viewholder, ((RecyclerView) (viewholder)).mItemAnimatorRunner);
                        viewholder.mPostedAnimatorRunner = true;
                    }
                }
            } else
            if (mItemAnimator.animatePersistence(viewholder, itemholderinfo, itemholderinfo1))
            {
                viewholder = RecyclerView.this;
                if (!((RecyclerView) (viewholder)).mPostedAnimatorRunner && ((RecyclerView) (viewholder)).mIsAttached)
                {
                    ViewCompat.postOnAnimation(viewholder, ((RecyclerView) (viewholder)).mItemAnimatorRunner);
                    viewholder.mPostedAnimatorRunner = true;
                    return;
                }
            }
        }

        public final void unused(ViewHolder viewholder)
        {
            Object obj = mLayout;
            viewholder = viewholder.itemView;
            Recycler recycler = mRecycler;
            obj = ((LayoutManager) (obj)).mChildHelper;
            int i = ((ChildHelper) (obj)).mCallback.indexOfChild(viewholder);
            if (i >= 0)
            {
                if (((ChildHelper) (obj)).mBucket.remove(i) && ((ChildHelper) (obj)).mHiddenViews.remove(viewholder))
                {
                    ((ChildHelper) (obj)).mCallback.onLeftHiddenState(viewholder);
                }
                ((ChildHelper) (obj)).mCallback.removeViewAt(i);
            }
            recycler.recycleView(viewholder);
        }

        _cls4()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }


    private class _cls6
        implements AdapterHelper.Callback
    {

        private final RecyclerView this$0;

        private final void dispatchUpdate(AdapterHelper.UpdateOp updateop)
        {
            switch (updateop.cmd)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                return;

            case 1: // '\001'
                updateop = mLayout;
                return;

            case 2: // '\002'
                updateop = mLayout;
                return;

            case 4: // '\004'
                updateop = mLayout;
                return;

            case 8: // '\b'
                updateop = mLayout;
                break;
            }
        }

        public final ViewHolder findViewHolder(int i)
        {
            View view;
            Object obj1;
            int j;
            int k;
            obj1 = RecyclerView.this;
            k = ((RecyclerView) (obj1)).mChildHelper.mCallback.getChildCount();
            j = 0;
            view = null;
_L2:
            Object obj;
            if (j >= k)
            {
                break; /* Loop/switch isn't completed */
            }
            obj = ((RecyclerView) (obj1)).mChildHelper.mCallback.getChildAt(j);
            ChildHelper childhelper;
            View view1;
            boolean flag;
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if (obj == null)
            {
                break MISSING_BLOCK_LABEL_184;
            }
            if ((((ViewHolder) (obj)).mFlags & 8) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag || ((ViewHolder) (obj)).mPosition != i)
            {
                break MISSING_BLOCK_LABEL_184;
            }
            childhelper = ((RecyclerView) (obj1)).mChildHelper;
            view1 = ((ViewHolder) (obj)).itemView;
            view = ((View) (obj));
            if (!childhelper.mHiddenViews.contains(view1))
            {
                break; /* Loop/switch isn't completed */
            }
_L7:
            j++;
            view = ((View) (obj));
            if (true) goto _L2; else goto _L1
_L1:
            if (view != null) goto _L4; else goto _L3
_L3:
            view = null;
_L6:
            return view;
_L4:
            obj = mChildHelper;
            obj1 = ((ViewHolder) (view)).itemView;
            if (!((ChildHelper) (obj)).mHiddenViews.contains(obj1)) goto _L6; else goto _L5
_L5:
            return null;
            obj = view;
              goto _L7
        }

        public final void markViewHoldersUpdated(int i, int j, Object obj)
        {
            RecyclerView recyclerview = RecyclerView.this;
            int j1 = recyclerview.mChildHelper.mCallback.getChildCount();
            int k = 0;
            while (k < j1) 
            {
                View view = recyclerview.mChildHelper.mCallback.getChildAt(k);
                ViewHolder viewholder;
                boolean flag;
                if (view == null)
                {
                    viewholder = null;
                } else
                {
                    viewholder = ((LayoutParams)view.getLayoutParams()).mViewHolder;
                }
                if (viewholder == null)
                {
                    continue;
                }
                if ((viewholder.mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag && viewholder.mPosition >= i && viewholder.mPosition < i + j)
                {
                    viewholder.mFlags = viewholder.mFlags | 2;
                    viewholder.addChangePayload(obj);
                    ((LayoutParams)view.getLayoutParams()).mInsetsDirty = true;
                }
                k++;
            }
            obj = recyclerview.mRecycler;
            for (int l = ((Recycler) (obj)).mCachedViews.size() - 1; l >= 0; l--)
            {
                ViewHolder viewholder1 = (ViewHolder)((Recycler) (obj)).mCachedViews.get(l);
                if (viewholder1 == null)
                {
                    continue;
                }
                int i1 = viewholder1.mPosition;
                if (i1 >= i && i1 < i + j)
                {
                    viewholder1.mFlags = viewholder1.mFlags | 2;
                    ((Recycler) (obj)).addViewHolderToRecycledViewPool((ViewHolder)((Recycler) (obj)).mCachedViews.get(l), true);
                    ((Recycler) (obj)).mCachedViews.remove(l);
                }
            }

            mItemsChanged = true;
        }

        public final void offsetPositionsForAdd(int i, int j)
        {
            boolean flag1 = false;
            RecyclerView recyclerview = RecyclerView.this;
            int j1 = recyclerview.mChildHelper.mCallback.getChildCount();
            int k = 0;
            while (k < j1) 
            {
                Object obj = recyclerview.mChildHelper.mCallback.getChildAt(k);
                boolean flag;
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                }
                if (obj == null)
                {
                    continue;
                }
                if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag && ((ViewHolder) (obj)).mPosition >= i)
                {
                    ((ViewHolder) (obj)).offsetPosition(j, false);
                    recyclerview.mState.mStructureChanged = true;
                }
                k++;
            }
            Recycler recycler = recyclerview.mRecycler;
            int i1 = recycler.mCachedViews.size();
            for (int l = ((flag1) ? 1 : 0); l < i1; l++)
            {
                ViewHolder viewholder = (ViewHolder)recycler.mCachedViews.get(l);
                if (viewholder != null && viewholder.mPosition >= i)
                {
                    viewholder.offsetPosition(j, true);
                }
            }

            recyclerview.requestLayout();
            mItemsAddedOrRemoved = true;
        }

        public final void offsetPositionsForMove(int i, int j)
        {
            int k1 = -1;
            RecyclerView recyclerview = RecyclerView.this;
            int l1 = recyclerview.mChildHelper.mCallback.getChildCount();
            int k;
            int l;
            int i1;
            int j1;
            if (i < j)
            {
                k = -1;
                l = j;
                i1 = i;
            } else
            {
                k = 1;
                l = i;
                i1 = j;
            }
            j1 = 0;
            while (j1 < l1) 
            {
                Object obj = recyclerview.mChildHelper.mCallback.getChildAt(j1);
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((LayoutParams)((View) (obj)).getLayoutParams()).mViewHolder;
                }
                if (obj != null && ((ViewHolder) (obj)).mPosition >= i1 && ((ViewHolder) (obj)).mPosition <= l)
                {
                    if (((ViewHolder) (obj)).mPosition == i)
                    {
                        ((ViewHolder) (obj)).offsetPosition(j - i, false);
                    } else
                    {
                        ((ViewHolder) (obj)).offsetPosition(k, false);
                    }
                    recyclerview.mState.mStructureChanged = true;
                }
                j1++;
            }
            Recycler recycler = recyclerview.mRecycler;
            if (i < j)
            {
                k = j;
                i1 = i;
                l = k1;
            } else
            {
                l = 1;
                k = i;
                i1 = j;
            }
            k1 = recycler.mCachedViews.size();
            j1 = 0;
            while (j1 < k1) 
            {
                ViewHolder viewholder = (ViewHolder)recycler.mCachedViews.get(j1);
                if (viewholder != null && viewholder.mPosition >= i1 && viewholder.mPosition <= k)
                {
                    if (viewholder.mPosition == i)
                    {
                        viewholder.offsetPosition(j - i, false);
                    } else
                    {
                        viewholder.offsetPosition(l, false);
                    }
                }
                j1++;
            }
            recyclerview.requestLayout();
            mItemsAddedOrRemoved = true;
        }

        public final void offsetPositionsForRemovingInvisible(int i, int j)
        {
            offsetPositionRecordsForRemove(i, j, true);
            mItemsAddedOrRemoved = true;
            State state = mState;
            state.mDeletedInvisibleItemCountSincePreviousLayout = state.mDeletedInvisibleItemCountSincePreviousLayout + j;
        }

        public final void offsetPositionsForRemovingLaidOutOrNewView(int i, int j)
        {
            offsetPositionRecordsForRemove(i, j, false);
            mItemsAddedOrRemoved = true;
        }

        public final void onDispatchFirstPass(AdapterHelper.UpdateOp updateop)
        {
            dispatchUpdate(updateop);
        }

        public final void onDispatchSecondPass(AdapterHelper.UpdateOp updateop)
        {
            dispatchUpdate(updateop);
        }

        _cls6()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }


    private class _cls5
        implements ChildHelper.Callback
    {

        private final RecyclerView this$0;

        public final void addView(View view, int i)
        {
            RecyclerView.this.addView(view, i);
            RecyclerView recyclerview = RecyclerView.this;
            if (view == null)
            {
                view = null;
            } else
            {
                view = ((LayoutParams)view.getLayoutParams()).mViewHolder;
            }
            if (recyclerview.mAdapter != null && view != null)
            {
                recyclerview.mAdapter.onViewAttachedToWindow(view);
            }
        }

        public final void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
        {
            boolean flag1 = true;
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder != null)
            {
                boolean flag;
                if ((viewholder.mFlags & 0x100) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    if ((viewholder.mFlags & 0x80) != 0)
                    {
                        flag = flag1;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        throw new IllegalArgumentException((new StringBuilder("Called attach on a child which is not detached: ")).append(viewholder).append(exceptionLabel()).toString());
                    }
                }
                viewholder.mFlags = viewholder.mFlags & 0xfffffeff;
            }
            RecyclerView.this.attachViewToParent(view, i, layoutparams);
        }

        public final void detachViewFromParent(int i)
        {
            boolean flag1 = true;
            Object obj = RecyclerView.this.getChildAt(i);
            if (obj != null)
            {
                obj = RecyclerView.getChildViewHolderInt(((View) (obj)));
                if (obj != null)
                {
                    boolean flag;
                    if ((((ViewHolder) (obj)).mFlags & 0x100) != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        if ((((ViewHolder) (obj)).mFlags & 0x80) != 0)
                        {
                            flag = flag1;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            throw new IllegalArgumentException((new StringBuilder("called detach on an already detached child ")).append(obj).append(exceptionLabel()).toString());
                        }
                    }
                    obj.mFlags = 0x100 | ((ViewHolder) (obj)).mFlags;
                }
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        public final View getChildAt(int i)
        {
            return RecyclerView.this.getChildAt(i);
        }

        public final int getChildCount()
        {
            return RecyclerView.this.getChildCount();
        }

        public final ViewHolder getChildViewHolder(View view)
        {
            return RecyclerView.getChildViewHolderInt(view);
        }

        public final int indexOfChild(View view)
        {
            return RecyclerView.this.indexOfChild(view);
        }

        public final void onEnteredHiddenState(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            if (view != null)
            {
                RecyclerView recyclerview = RecyclerView.this;
                if (((ViewHolder) (view)).mPendingAccessibilityState != -1)
                {
                    view.mWasImportantForAccessibilityBeforeHidden = ((ViewHolder) (view)).mPendingAccessibilityState;
                } else
                {
                    view.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(((ViewHolder) (view)).itemView);
                }
                recyclerview.setChildImportantForAccessibilityInternal(view, 4);
            }
        }

        public final void onLeftHiddenState(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            if (view != null)
            {
                setChildImportantForAccessibilityInternal(view, ((ViewHolder) (view)).mWasImportantForAccessibilityBeforeHidden);
                view.mWasImportantForAccessibilityBeforeHidden = 0;
            }
        }

        public final void removeAllViews()
        {
            int j = RecyclerView.this.getChildCount();
            for (int i = 0; i < j; i++)
            {
                View view = RecyclerView.this.getChildAt(i);
                dispatchChildDetached(view);
                view.clearAnimation();
            }

            RecyclerView.this.removeAllViews();
        }

        public final void removeViewAt(int i)
        {
            View view = RecyclerView.this.getChildAt(i);
            if (view != null)
            {
                dispatchChildDetached(view);
                view.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        _cls5()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }


    private class _cls3
        implements Interpolator
    {

        public final float getInterpolation(float f)
        {
            f--;
            return f * (f * f * f * f) + 1.0F;
        }

        _cls3()
        {
        }
    }

}
