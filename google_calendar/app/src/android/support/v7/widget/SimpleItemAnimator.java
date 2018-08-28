// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator
{

    private boolean mSupportsChangeAnimations;

    public SimpleItemAnimator()
    {
        mSupportsChangeAnimations = true;
    }

    public abstract boolean animateAdd(RecyclerView.ViewHolder viewholder);

    public final boolean animateAppearance(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        if (itemholderinfo != null && (itemholderinfo.left != itemholderinfo1.left || itemholderinfo.top != itemholderinfo1.top))
        {
            return animateMove(viewholder, itemholderinfo.left, itemholderinfo.top, itemholderinfo1.left, itemholderinfo1.top);
        } else
        {
            return animateAdd(viewholder);
        }
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, int i, int j, int k, int l);

    public final boolean animateChange(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        int k = itemholderinfo.left;
        int l = itemholderinfo.top;
        int i;
        int j;
        if ((viewholder1.mFlags & 0x80) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = itemholderinfo.left;
            j = itemholderinfo.top;
        } else
        {
            i = itemholderinfo1.left;
            j = itemholderinfo1.top;
        }
        return animateChange(viewholder, viewholder1, k, l, i, j);
    }

    public final boolean animateDisappearance(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        int k = itemholderinfo.left;
        int l = itemholderinfo.top;
        itemholderinfo = viewholder.itemView;
        int i;
        int j;
        boolean flag;
        if (itemholderinfo1 == null)
        {
            i = itemholderinfo.getLeft();
        } else
        {
            i = itemholderinfo1.left;
        }
        if (itemholderinfo1 == null)
        {
            j = itemholderinfo.getTop();
        } else
        {
            j = itemholderinfo1.top;
        }
        if ((viewholder.mFlags & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && (k != i || l != j))
        {
            itemholderinfo.layout(i, j, itemholderinfo.getWidth() + i, itemholderinfo.getHeight() + j);
            return animateMove(viewholder, k, l, i, j);
        } else
        {
            return animateRemove(viewholder);
        }
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewholder, int i, int j, int k, int l);

    public final boolean animatePersistence(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        if (itemholderinfo.left != itemholderinfo1.left || itemholderinfo.top != itemholderinfo1.top)
        {
            return animateMove(viewholder, itemholderinfo.left, itemholderinfo.top, itemholderinfo1.left, itemholderinfo1.top);
        }
        if (super.mListener != null)
        {
            super.mListener.onAnimationFinished(viewholder);
        }
        return false;
    }

    public abstract boolean animateRemove(RecyclerView.ViewHolder viewholder);

    public final boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewholder)
    {
label0:
        {
            boolean flag1 = false;
            if (mSupportsChangeAnimations)
            {
                boolean flag;
                if ((viewholder.mFlags & 4) != 0)
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
}
