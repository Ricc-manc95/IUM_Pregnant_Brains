// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

final class ViewBoundsCheck
{

    private BoundFlags mBoundFlags;
    private final Callback mCallback;

    ViewBoundsCheck(Callback callback)
    {
        mCallback = callback;
        mBoundFlags = new BoundFlags();
    }

    final View findOneViewWithinBoundFlags(int i, int j, int k, int l)
    {
        int i1 = mCallback.getParentStart();
        int j1 = mCallback.getParentEnd();
        View view;
        byte byte0;
        if (j > i)
        {
            byte0 = 1;
        } else
        {
            byte0 = -1;
        }
        view = null;
        for (; i != j; i += byte0)
        {
            View view1 = mCallback.getChildAt(i);
            int k1 = mCallback.getChildStart(view1);
            int l1 = mCallback.getChildEnd(view1);
            BoundFlags boundflags = mBoundFlags;
            boundflags.mRvStart = i1;
            boundflags.mRvEnd = j1;
            boundflags.mChildStart = k1;
            boundflags.mChildEnd = l1;
            if (k != 0)
            {
                mBoundFlags.mBoundFlags = 0;
                boundflags = mBoundFlags;
                boundflags.mBoundFlags = boundflags.mBoundFlags | k;
                if (mBoundFlags.boundsMatch())
                {
                    return view1;
                }
            }
            if (l == 0)
            {
                continue;
            }
            mBoundFlags.mBoundFlags = 0;
            boundflags = mBoundFlags;
            boundflags.mBoundFlags = boundflags.mBoundFlags | l;
            if (mBoundFlags.boundsMatch())
            {
                view = view1;
            }
        }

        return view;
    }

    private class BoundFlags
    {

        public int mBoundFlags;
        public int mChildEnd;
        public int mChildStart;
        public int mRvEnd;
        public int mRvStart;

        final boolean boundsMatch()
        {
            byte byte0 = 2;
            if ((mBoundFlags & 7) == 0) goto _L2; else goto _L1
_L1:
            int k = mBoundFlags;
            int i = mChildStart;
            int i1 = mRvStart;
            if (i > i1)
            {
                i = 1;
            } else
            if (i == i1)
            {
                i = 2;
            } else
            {
                i = 4;
            }
            if ((i & k) != 0) goto _L2; else goto _L3
_L3:
            return false;
_L2:
            int j;
            int j1;
            if ((mBoundFlags & 0x70) == 0)
            {
                break; /* Loop/switch isn't completed */
            }
            int l = mBoundFlags;
            j = mChildStart;
            j1 = mRvEnd;
            if (j > j1)
            {
                j = 1;
            } else
            if (j == j1)
            {
                j = 2;
            } else
            {
                j = 4;
            }
            if ((j << 4 & l) == 0) goto _L3; else goto _L4
_L4:
            if ((mBoundFlags & 0x700) == 0)
            {
                break; /* Loop/switch isn't completed */
            }
            l = mBoundFlags;
            j = mChildEnd;
            j1 = mRvStart;
            if (j > j1)
            {
                j = 1;
            } else
            if (j == j1)
            {
                j = 2;
            } else
            {
                j = 4;
            }
            if ((j << 8 & l) == 0) goto _L3; else goto _L5
_L5:
            if ((mBoundFlags & 0x7000) == 0)
            {
                break; /* Loop/switch isn't completed */
            }
            l = mBoundFlags;
            j1 = mChildEnd;
            int k1 = mRvEnd;
            if (j1 > k1)
            {
                j = 1;
            } else
            {
                j = byte0;
                if (j1 != k1)
                {
                    j = 4;
                }
            }
            if ((j << 12 & l) == 0) goto _L3; else goto _L6
_L6:
            return true;
        }

        BoundFlags()
        {
            mBoundFlags = 0;
        }
    }


    private class Callback
    {

        public abstract View getChildAt(int i);

        public abstract int getChildEnd(View view);

        public abstract int getChildStart(View view);

        public abstract int getParentEnd();

        public abstract int getParentStart();
    }

}
