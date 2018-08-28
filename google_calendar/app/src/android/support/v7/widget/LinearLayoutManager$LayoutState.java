// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.util.List;

final class mScrapList
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
            View view2 = ((mScrapList)mScrapList.get(j)).;
            Object obj = ()view2.getLayoutParams();
            if (view2 == view)
            {
                continue;
            }
            int k;
            if (((() (obj)).older.older & 8) != 0)
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
            obj = ((older) (obj)).older;
            if (((older) (obj)).utPosition == -1)
            {
                k = ((utPosition) (obj)).n;
            } else
            {
                k = ((n) (obj)).utPosition;
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
            view = ((mItemDirection)view1.getLayoutParams()).older;
            if (((older) (view)).utPosition == -1)
            {
                i = ((utPosition) (view)).n;
            } else
            {
                i = ((n) (view)).utPosition;
            }
        }
        mCurrentPosition = i;
    }

    ()
    {
        mRecycle = true;
        mExtra = 0;
        mScrapList = null;
    }
}
