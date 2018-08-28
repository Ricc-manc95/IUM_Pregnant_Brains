// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            OrientationHelper

final class mValid
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

    ()
    {
        mPosition = -1;
        mCoordinate = 0x80000000;
        mLayoutFromEnd = false;
        mValid = false;
    }
}
