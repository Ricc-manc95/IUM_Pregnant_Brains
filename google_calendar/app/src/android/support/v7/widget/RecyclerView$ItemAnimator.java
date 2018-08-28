// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static abstract class mChangeDuration
{

    public long mAddDuration;
    public long mChangeDuration;
    private ArrayList mFinishedListeners;
    public ItemAnimatorListener mListener;
    public long mMoveDuration;
    public long mRemoveDuration;

    static int buildAdapterChangeFlagsForAnimations(mChangeDuration mchangeduration)
    {
        int k = mchangeduration.lags & 0xe;
        int i;
        if ((mchangeduration.lags & 4) != 0)
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
                int l = mchangeduration.ldPosition;
                int j;
                if (mchangeduration.wnerRecyclerView == null)
                {
                    j = -1;
                } else
                {
                    j = mchangeduration.wnerRecyclerView.getAdapterPositionFor(mchangeduration);
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

    public abstract boolean animateAppearance(ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1, ItemHolderInfo itemholderinfo2);

    public abstract boolean animateChange(ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1, ItemHolderInfo itemholderinfo2, ItemHolderInfo itemholderinfo3);

    public abstract boolean animateDisappearance(ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1, ItemHolderInfo itemholderinfo2);

    public abstract boolean animatePersistence(ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1, ItemHolderInfo itemholderinfo2);

    public boolean canReuseUpdatedViewHolder(ItemHolderInfo itemholderinfo)
    {
        return true;
    }

    public boolean canReuseUpdatedViewHolder(ItemHolderInfo itemholderinfo, List list)
    {
        return canReuseUpdatedViewHolder(itemholderinfo);
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

    public abstract void endAnimation(mFinishedListeners mfinishedlisteners);

    public abstract void endAnimations();

    public abstract boolean isRunning();

    public final ItemHolderInfo recordPreLayoutInformation$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNEQB4CTIN8BQICLHNIORCCLP5CQB5ESI5CQB5ET46UR34CLP3MIACD9GNCO9FELQ6IR1F9HKN6T1R55662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H4N8PBD85N6IRB1EHNN4929EHIMQI3FDHI6ASI9DPJ6UEO_0(mFinishedListeners mfinishedlisteners)
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
        mfinishedlisteners = mfinishedlisteners.emView;
        itemholderinfo.left = mfinishedlisteners.getLeft();
        itemholderinfo.top = mfinishedlisteners.getTop();
        mfinishedlisteners.getRight();
        mfinishedlisteners.getBottom();
        return itemholderinfo;
    }

    public abstract void runPendingAnimations();

    public ItemHolderInfo()
    {
        mListener = null;
        mFinishedListeners = new ArrayList();
        mAddDuration = 120L;
        mRemoveDuration = 120L;
        mMoveDuration = 250L;
        mChangeDuration = 250L;
    }
}
