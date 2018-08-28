// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Trace;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper

final class GapWorker
    implements Runnable
{

    public static final ThreadLocal sGapWorker = new ThreadLocal();
    private static Comparator sTaskComparator = new _cls1();
    public long mFrameIntervalNs;
    private long mPostTimeNs;
    public ArrayList mRecyclerViews;
    private ArrayList mTasks;

    GapWorker()
    {
        mRecyclerViews = new ArrayList();
        mTasks = new ArrayList();
    }

    private static RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerview, int i, long l)
    {
        int j;
        boolean flag1;
        int k;
        flag1 = true;
        k = recyclerview.mChildHelper.mCallback.getChildCount();
        j = 0;
_L3:
        if (j >= k) goto _L2; else goto _L1
_L1:
        RecyclerView.ViewHolder viewholder = RecyclerView.getChildViewHolderInt(recyclerview.mChildHelper.mCallback.getChildAt(j));
        if (viewholder.mPosition != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag;
        if ((viewholder.mFlags & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = 1;
_L4:
        if (j != 0)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_104;
        j++;
          goto _L3
_L2:
        j = 0;
          goto _L4
        RecyclerView.Recycler recycler = recyclerview.mRecycler;
        RecyclerView.ViewHolder viewholder1;
        recyclerview.mLayoutOrScrollCounter = recyclerview.mLayoutOrScrollCounter + 1;
        viewholder1 = recycler.tryGetViewHolderForPositionByDeadline(i, false, l);
        if (viewholder1 == null)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        if ((viewholder1.mFlags & 1) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        if ((viewholder1.mFlags & 4) != 0)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        recycler.recycleView(viewholder1.itemView);
_L6:
        recyclerview.onExitLayoutOrScroll(false);
        return viewholder1;
        recycler.addViewHolderToRecycledViewPool(viewholder1, false);
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        recyclerview.onExitLayoutOrScroll(false);
        throw exception;
    }

    final void postFromTraversal(RecyclerView recyclerview, int i, int j)
    {
        long l = 0L;
        if (recyclerview.isAttachedToWindow() && mPostTimeNs == 0L)
        {
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                l = System.nanoTime();
            }
            mPostTimeNs = l;
            recyclerview.post(this);
        }
        recyclerview = recyclerview.mPrefetchRegistry;
        recyclerview.mPrefetchDx = i;
        recyclerview.mPrefetchDy = j;
    }

    public final void run()
    {
        boolean flag;
        Trace.beginSection("RV Prefetch");
        flag = mRecyclerViews.isEmpty();
        if (flag)
        {
            mPostTimeNs = 0L;
            Trace.endSection();
            return;
        }
        int k = mRecyclerViews.size();
        int i;
        long l1;
        l1 = 0L;
        i = 0;
_L3:
        if (i >= k) goto _L2; else goto _L1
_L1:
        RecyclerView recyclerview = (RecyclerView)mRecyclerViews.get(i);
        if (recyclerview.getWindowVisibility() == 0)
        {
            l1 = Math.max(recyclerview.getDrawingTime(), l1);
        }
        i++;
          goto _L3
_L2:
        if (l1 == 0L)
        {
            mPostTimeNs = 0L;
            Trace.endSection();
            return;
        }
        int i1;
        long l3;
        l3 = TimeUnit.MILLISECONDS.toNanos(l1) + mFrameIntervalNs;
        i1 = mRecyclerViews.size();
        i = 0;
        k = 0;
_L11:
        if (k >= i1)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        RecyclerView recyclerview1 = (RecyclerView)mRecyclerViews.get(k);
        if (recyclerview1.getWindowVisibility() == 0)
        {
            recyclerview1.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerview1, false);
            i = recyclerview1.mPrefetchRegistry.mCount + i;
        }
        break MISSING_BLOCK_LABEL_739;
        mTasks.ensureCapacity(i);
        int j = 0;
        Task task;
        Exception exception;
        Object obj;
        LayoutPrefetchRegistryImpl layoutprefetchregistryimpl;
        RecyclerView.State state;
        RecyclerView.Adapter adapter;
        int l;
        int j1;
        int k1;
        long l2;
        boolean flag1;
        for (k = 0; k >= i1; k++)
        {
            break MISSING_BLOCK_LABEL_380;
        }

        obj = (RecyclerView)mRecyclerViews.get(k);
        if (((RecyclerView) (obj)).getWindowVisibility() != 0)
        {
            break MISSING_BLOCK_LABEL_754;
        }
        layoutprefetchregistryimpl = ((RecyclerView) (obj)).mPrefetchRegistry;
        j1 = Math.abs(layoutprefetchregistryimpl.mPrefetchDx) + Math.abs(layoutprefetchregistryimpl.mPrefetchDy);
        l = 0;
_L4:
        if (l >= layoutprefetchregistryimpl.mCount << 1)
        {
            break MISSING_BLOCK_LABEL_754;
        }
        if (j < mTasks.size())
        {
            break MISSING_BLOCK_LABEL_364;
        }
        task = new Task();
        mTasks.add(task);
_L5:
        k1 = layoutprefetchregistryimpl.mPrefetchArray[l + 1];
        if (k1 <= j1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        task.immediate = flag1;
        task.viewVelocity = j1;
        task.distanceToItem = k1;
        task.view = ((RecyclerView) (obj));
        task.position = layoutprefetchregistryimpl.mPrefetchArray[l];
        j++;
        l += 2;
          goto _L4
        task = (Task)mTasks.get(j);
          goto _L5
        Collections.sort(mTasks, sTaskComparator);
        j = 0;
_L9:
        if (j >= mTasks.size())
        {
            break; /* Loop/switch isn't completed */
        }
        task = (Task)mTasks.get(j);
        if (task.view == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (task.immediate)
        {
            l2 = 0x7fffffffffffffffL;
        } else
        {
            l2 = l3;
        }
        obj = prefetchPositionWithDeadline(task.view, task.position, l2);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        if (((RecyclerView.ViewHolder) (obj)).mNestedRecyclerView == null)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        if ((((RecyclerView.ViewHolder) (obj)).mFlags & 1) != 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        if ((((RecyclerView.ViewHolder) (obj)).mFlags & 4) != 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        obj = (RecyclerView)((RecyclerView.ViewHolder) (obj)).mNestedRecyclerView.get();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        if (((RecyclerView) (obj)).mDataSetHasChangedAfterLayout && ((RecyclerView) (obj)).mChildHelper.mCallback.getChildCount() != 0)
        {
            ((RecyclerView) (obj)).removeAndRecycleViews();
        }
        layoutprefetchregistryimpl = ((RecyclerView) (obj)).mPrefetchRegistry;
        layoutprefetchregistryimpl.collectPrefetchPositionsFromView(((RecyclerView) (obj)), true);
        k = layoutprefetchregistryimpl.mCount;
        if (k == 0)
        {
            break MISSING_BLOCK_LABEL_670;
        }
        Trace.beginSection("RV Nested Prefetch");
        state = ((RecyclerView) (obj)).mState;
        adapter = ((RecyclerView) (obj)).mAdapter;
        state.mLayoutStep = 1;
        state.mItemCount = adapter.getItemCount();
        state.mInPreLayout = false;
        state.mTrackOldChangeHolders = false;
        state.mIsMeasuring = false;
        k = 0;
_L7:
        if (k >= layoutprefetchregistryimpl.mCount << 1)
        {
            break; /* Loop/switch isn't completed */
        }
        prefetchPositionWithDeadline(((RecyclerView) (obj)), layoutprefetchregistryimpl.mPrefetchArray[k], l3);
        k += 2;
        if (true) goto _L7; else goto _L6
_L6:
        Trace.endSection();
        task.immediate = false;
        task.viewVelocity = 0;
        task.distanceToItem = 0;
        task.view = null;
        task.position = 0;
        j++;
        if (true) goto _L9; else goto _L8
        exception;
        Trace.endSection();
        throw exception;
        exception;
        mPostTimeNs = 0L;
        Trace.endSection();
        throw exception;
_L8:
        mPostTimeNs = 0L;
        Trace.endSection();
        return;
        k++;
        if (true) goto _L11; else goto _L10
_L10:
    }


    private class LayoutPrefetchRegistryImpl
        implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
    {

        public int mCount;
        public int mPrefetchArray[];
        public int mPrefetchDx;
        public int mPrefetchDy;

        public final void addPosition(int i, int j)
        {
            int k;
            if (i < 0)
            {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (j < 0)
            {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            k = mCount << 1;
            if (mPrefetchArray != null) goto _L2; else goto _L1
_L1:
            mPrefetchArray = new int[4];
            Arrays.fill(mPrefetchArray, -1);
_L4:
            mPrefetchArray[k] = i;
            mPrefetchArray[k + 1] = j;
            mCount = mCount + 1;
            return;
_L2:
            if (k >= mPrefetchArray.length)
            {
                int ai[] = mPrefetchArray;
                mPrefetchArray = new int[k << 1];
                System.arraycopy(ai, 0, mPrefetchArray, 0, ai.length);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        final void collectPrefetchPositionsFromView(RecyclerView recyclerview, boolean flag)
        {
            RecyclerView.LayoutManager layoutmanager;
            boolean flag1;
            boolean flag3;
            flag1 = true;
            flag3 = false;
            mCount = 0;
            if (mPrefetchArray != null)
            {
                Arrays.fill(mPrefetchArray, -1);
            }
            layoutmanager = recyclerview.mLayout;
            if (recyclerview.mAdapter == null || layoutmanager == null || !layoutmanager.mItemPrefetchEnabled) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            if (recyclerview.mAdapterHelper.mPendingUpdates.size() <= 0)
            {
                flag1 = false;
            }
            if (!flag1)
            {
                layoutmanager.collectInitialPrefetchPositions(recyclerview.mAdapter.getItemCount(), this);
            }
_L6:
            if (mCount > layoutmanager.mPrefetchMaxCountObserved)
            {
                layoutmanager.mPrefetchMaxCountObserved = mCount;
                layoutmanager.mPrefetchMaxObservedInInitialPrefetch = flag;
                recyclerview.mRecycler.updateViewCacheSize();
            }
_L2:
            return;
_L4:
label0:
            {
                if (recyclerview.mFirstLayoutComplete && !recyclerview.mDataSetHasChangedAfterLayout)
                {
                    boolean flag2;
                    if (recyclerview.mAdapterHelper.mPendingUpdates.size() > 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (!flag2)
                    {
                        break label0;
                    }
                }
                flag3 = true;
            }
            if (!flag3)
            {
                layoutmanager.collectAdjacentPrefetchPositions(mPrefetchDx, mPrefetchDy, recyclerview.mState, this);
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        final boolean lastPrefetchIncludedPosition(int i)
        {
            boolean flag;
            boolean flag1;
            flag1 = false;
            flag = flag1;
            if (mPrefetchArray == null) goto _L2; else goto _L1
_L1:
            int j;
            int k;
            k = mCount;
            j = 0;
_L7:
            flag = flag1;
            if (j >= k << 1) goto _L2; else goto _L3
_L3:
            if (mPrefetchArray[j] != i) goto _L5; else goto _L4
_L4:
            flag = true;
_L2:
            return flag;
_L5:
            j += 2;
            if (true) goto _L7; else goto _L6
_L6:
        }

        LayoutPrefetchRegistryImpl()
        {
        }
    }


    private class Task
    {

        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task()
        {
        }
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            int i;
            boolean flag1;
            flag1 = true;
            obj = (Task)obj;
            obj1 = (Task)obj1;
            boolean flag;
            if (((Task) (obj)).view == null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (((Task) (obj1)).view == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (i == flag) goto _L2; else goto _L1
_L1:
            if (((Task) (obj)).view != null) goto _L4; else goto _L3
_L3:
            i = ((flag1) ? 1 : 0);
_L6:
            return i;
_L4:
            return -1;
_L2:
            if (((Task) (obj)).immediate == ((Task) (obj1)).immediate)
            {
                break; /* Loop/switch isn't completed */
            }
            i = ((flag1) ? 1 : 0);
            if (((Task) (obj)).immediate)
            {
                return -1;
            }
            if (true) goto _L6; else goto _L5
_L5:
            int j = ((Task) (obj1)).viewVelocity - ((Task) (obj)).viewVelocity;
            i = j;
            if (j == 0)
            {
                int k = ((Task) (obj)).distanceToItem - ((Task) (obj1)).distanceToItem;
                i = k;
                if (k == 0)
                {
                    return 0;
                }
            }
            if (true) goto _L6; else goto _L7
_L7:
        }

        _cls1()
        {
        }
    }

}
