// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper

final class this._cls0
    implements allback
{

    private final RecyclerView this$0;

    private final void dispatchUpdate(pdateOp pdateop)
    {
        switch (pdateop.cmd)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return;

        case 1: // '\001'
            pdateop = mLayout;
            return;

        case 2: // '\002'
            pdateop = mLayout;
            return;

        case 4: // '\004'
            pdateop = mLayout;
            return;

        case 8: // '\b'
            pdateop = mLayout;
            break;
        }
    }

    public final ewHolder findViewHolder(int i)
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
            obj = ((youtParams)((View) (obj)).getLayoutParams()).mViewHolder;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        if ((((ewHolder) (obj)).mFlags & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || ((ewHolder) (obj)).mPosition != i)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        childhelper = ((RecyclerView) (obj1)).mChildHelper;
        view1 = ((ewHolder) (obj)).itemView;
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
        obj1 = ((ewHolder) (view)).itemView;
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
            ewHolder ewholder;
            boolean flag;
            if (view == null)
            {
                ewholder = null;
            } else
            {
                ewholder = ((youtParams)view.getLayoutParams()).mViewHolder;
            }
            if (ewholder == null)
            {
                continue;
            }
            if ((ewholder.mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag && ewholder.mPosition >= i && ewholder.mPosition < i + j)
            {
                ewholder.mFlags = ewholder.mFlags | 2;
                ewholder.addChangePayload(obj);
                ((youtParams)view.getLayoutParams()).mInsetsDirty = true;
            }
            k++;
        }
        obj = recyclerview.mRecycler;
        for (int l = ((cycler) (obj)).mCachedViews.size() - 1; l >= 0; l--)
        {
            ewHolder ewholder1 = (ewHolder)((cycler) (obj)).mCachedViews.get(l);
            if (ewholder1 == null)
            {
                continue;
            }
            int i1 = ewholder1.mPosition;
            if (i1 >= i && i1 < i + j)
            {
                ewholder1.mFlags = ewholder1.mFlags | 2;
                ((cycler) (obj)).addViewHolderToRecycledViewPool((ewHolder)((cycler) (obj)).mCachedViews.get(l), true);
                ((cycler) (obj)).mCachedViews.remove(l);
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
                obj = ((youtParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if (obj == null)
            {
                continue;
            }
            if ((((ewHolder) (obj)).mFlags & 0x80) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag && ((ewHolder) (obj)).mPosition >= i)
            {
                ((ewHolder) (obj)).offsetPosition(j, false);
                recyclerview.mState.mStructureChanged = true;
            }
            k++;
        }
        cycler cycler = recyclerview.mRecycler;
        int i1 = cycler.mCachedViews.size();
        for (int l = ((flag1) ? 1 : 0); l < i1; l++)
        {
            ewHolder ewholder = (ewHolder)cycler.mCachedViews.get(l);
            if (ewholder != null && ewholder.mPosition >= i)
            {
                ewholder.offsetPosition(j, true);
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
                obj = ((youtParams)((View) (obj)).getLayoutParams()).mViewHolder;
            }
            if (obj != null && ((ewHolder) (obj)).mPosition >= i1 && ((ewHolder) (obj)).mPosition <= l)
            {
                if (((ewHolder) (obj)).mPosition == i)
                {
                    ((ewHolder) (obj)).offsetPosition(j - i, false);
                } else
                {
                    ((ewHolder) (obj)).offsetPosition(k, false);
                }
                recyclerview.mState.mStructureChanged = true;
            }
            j1++;
        }
        cycler cycler = recyclerview.mRecycler;
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
        k1 = cycler.mCachedViews.size();
        j1 = 0;
        while (j1 < k1) 
        {
            ewHolder ewholder = (ewHolder)cycler.mCachedViews.get(j1);
            if (ewholder != null && ewholder.mPosition >= i1 && ewholder.mPosition <= k)
            {
                if (ewholder.mPosition == i)
                {
                    ewholder.offsetPosition(j - i, false);
                } else
                {
                    ewholder.offsetPosition(l, false);
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
        ate ate = mState;
        ate.mDeletedInvisibleItemCountSincePreviousLayout = ate.mDeletedInvisibleItemCountSincePreviousLayout + j;
    }

    public final void offsetPositionsForRemovingLaidOutOrNewView(int i, int j)
    {
        offsetPositionRecordsForRemove(i, j, false);
        mItemsAddedOrRemoved = true;
    }

    public final void onDispatchFirstPass(pdateOp pdateop)
    {
        dispatchUpdate(pdateop);
    }

    public final void onDispatchSecondPass(pdateOp pdateop)
    {
        dispatchUpdate(pdateop);
    }

    pdateOp()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
