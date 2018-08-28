// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, AdapterHelper

final class etchRegistry
    implements etchRegistry
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
        etchRegistry etchregistry;
        boolean flag1;
        boolean flag3;
        flag1 = true;
        flag3 = false;
        mCount = 0;
        if (mPrefetchArray != null)
        {
            Arrays.fill(mPrefetchArray, -1);
        }
        etchregistry = recyclerview.mLayout;
        if (recyclerview.mAdapter == null || etchregistry == null || !etchregistry.tchEnabled) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        if (recyclerview.mAdapterHelper.mPendingUpdates.size() <= 0)
        {
            flag1 = false;
        }
        if (!flag1)
        {
            etchregistry.tialPrefetchPositions(recyclerview.mAdapter.tialPrefetchPositions(), this);
        }
_L6:
        if (mCount > etchregistry.axCountObserved)
        {
            etchregistry.axCountObserved = mCount;
            etchregistry.axObservedInInitialPrefetch = flag;
            recyclerview.mRecycler.Size();
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
            etchregistry.acentPrefetchPositions(mPrefetchDx, mPrefetchDy, recyclerview.mState, this);
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

    etchRegistry()
    {
    }
}
