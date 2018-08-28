// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, AdapterHelper

final class > extends >
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
        mState. = true;
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

    ()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
