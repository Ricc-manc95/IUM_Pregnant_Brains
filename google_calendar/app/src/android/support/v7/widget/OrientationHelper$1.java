// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Rect;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            OrientationHelper, RecyclerView, ChildHelper

final class anager extends OrientationHelper
{

    public final int getDecoratedEnd(View view)
    {
        arams arams = (arams)view.getLayoutParams();
        anager anager = mLayoutManager;
        int i = view.getRight();
        int j = ((arams)view.getLayoutParams()).mDecorInsets.right;
        return arams.rightMargin + (j + i);
    }

    public final int getDecoratedMeasurement(View view)
    {
        arams arams = (arams)view.getLayoutParams();
        Rect rect = ((arams)view.getLayoutParams()).mDecorInsets;
        int i = view.getMeasuredWidth();
        int j = rect.left;
        int k = rect.right;
        int l = arams.leftMargin;
        return arams.rightMargin + (k + (i + j) + l);
    }

    public final int getDecoratedMeasurementInOther(View view)
    {
        arams arams = (arams)view.getLayoutParams();
        Rect rect = ((arams)view.getLayoutParams()).mDecorInsets;
        int i = view.getMeasuredHeight();
        int j = rect.top;
        int k = rect.bottom;
        int l = arams.topMargin;
        return arams.bottomMargin + (k + (i + j) + l);
    }

    public final int getDecoratedStart(View view)
    {
        arams arams = (arams)view.getLayoutParams();
        anager anager = mLayoutManager;
        return view.getLeft() - ((arams)view.getLayoutParams()).mDecorInsets.left - arams.leftMargin;
    }

    public final int getEnd()
    {
        return mLayoutManager.mWidth;
    }

    public final int getEndAfterPadding()
    {
        int j = mLayoutManager.mWidth;
        anager anager = mLayoutManager;
        int i;
        if (anager.mRecyclerView != null)
        {
            i = anager.mRecyclerView.getPaddingRight();
        } else
        {
            i = 0;
        }
        return j - i;
    }

    public final int getEndPadding()
    {
        anager anager = mLayoutManager;
        if (anager.mRecyclerView != null)
        {
            return anager.mRecyclerView.getPaddingRight();
        } else
        {
            return 0;
        }
    }

    public final int getMode()
    {
        return mLayoutManager.mWidthMode;
    }

    public final int getStartAfterPadding()
    {
        anager anager = mLayoutManager;
        if (anager.mRecyclerView != null)
        {
            return anager.mRecyclerView.getPaddingLeft();
        } else
        {
            return 0;
        }
    }

    public final int getTotalSpace()
    {
        int j = 0;
        int k = mLayoutManager.mWidth;
        anager anager = mLayoutManager;
        int i;
        if (anager.mRecyclerView != null)
        {
            i = anager.mRecyclerView.getPaddingLeft();
        } else
        {
            i = 0;
        }
        anager = mLayoutManager;
        if (anager.mRecyclerView != null)
        {
            j = anager.mRecyclerView.getPaddingRight();
        }
        return k - i - j;
    }

    public final int getTransformedEndWithDecoration(View view)
    {
        mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
        return mTmpRect.right;
    }

    public final int getTransformedStartWithDecoration(View view)
    {
        mLayoutManager.getTransformedBoundingBox(view, true, mTmpRect);
        return mTmpRect.left;
    }

    public final void offsetChildren(int i)
    {
        Object obj = mLayoutManager;
        if (((anager) (obj)).mRecyclerView != null)
        {
            obj = ((anager) (obj)).mRecyclerView;
            ChildHelper childhelper = ((RecyclerView) (obj)).mChildHelper;
            int k = childhelper.mCallback.getChildCount();
            int l = childhelper.mHiddenViews.size();
            for (int j = 0; j < k - l; j++)
            {
                ChildHelper childhelper1 = ((RecyclerView) (obj)).mChildHelper;
                int i1 = childhelper1.getOffset(j);
                childhelper1.mCallback.getChildAt(i1).offsetLeftAndRight(i);
            }

        }
    }

    anager(anager anager)
    {
        super(anager, (byte)0);
    }
}
