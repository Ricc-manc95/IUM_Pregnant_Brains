// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public final class ChildHelper
{

    public final Bucket mBucket = new Bucket();
    public final Callback mCallback;
    public final List mHiddenViews = new ArrayList();

    ChildHelper(Callback callback)
    {
        mCallback = callback;
    }

    final void addView(View view, int i, boolean flag)
    {
        if (i < 0)
        {
            i = mCallback.getChildCount();
        } else
        {
            i = getOffset(i);
        }
        mBucket.insert(i, flag);
        if (flag)
        {
            mHiddenViews.add(view);
            mCallback.onEnteredHiddenState(view);
        }
        mCallback.addView(view, i);
    }

    final void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams, boolean flag)
    {
        if (i < 0)
        {
            i = mCallback.getChildCount();
        } else
        {
            i = getOffset(i);
        }
        mBucket.insert(i, flag);
        if (flag)
        {
            mHiddenViews.add(view);
            mCallback.onEnteredHiddenState(view);
        }
        mCallback.attachViewToParent(view, i, layoutparams);
    }

    final int getOffset(int i)
    {
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_8;
        }
        i = -1;
        return i;
        int j;
        int k;
        int l;
        k = mCallback.getChildCount();
        j = i;
          goto _L1
        continue; /* Loop/switch isn't completed */
        j += l;
    }

    final int indexOfChild(View view)
    {
        int i;
        for (i = mCallback.indexOfChild(view); i == -1 || mBucket.get(i);)
        {
            return -1;
        }

        return i - mBucket.countOnesBefore(i);
    }

    public final String toString()
    {
        return (new StringBuilder()).append(mBucket.toString()).append(", hidden list:").append(mHiddenViews.size()).toString();
    }

    private class Bucket
    {

        public long mData;
        public Bucket mNext;

        final void clear(int i)
        {
            if (i >= 64)
            {
                if (mNext != null)
                {
                    mNext.clear(i - 64);
                }
                return;
            } else
            {
                mData = mData & ~(1L << i);
                return;
            }
        }

        final int countOnesBefore(int i)
        {
            if (mNext == null)
            {
                if (i >= 64)
                {
                    return Long.bitCount(mData);
                } else
                {
                    return Long.bitCount(mData & (1L << i) - 1L);
                }
            }
            if (i < 64)
            {
                return Long.bitCount(mData & (1L << i) - 1L);
            } else
            {
                return mNext.countOnesBefore(i - 64) + Long.bitCount(mData);
            }
        }

        final boolean get(int i)
        {
            Bucket bucket = this;
            for (; i >= 64; i -= 64)
            {
                if (bucket.mNext == null)
                {
                    bucket.mNext = new Bucket();
                }
                bucket = bucket.mNext;
            }

            return (bucket.mData & 1L << i) != 0L;
        }

        final void insert(int i, boolean flag)
        {
            Bucket bucket;
            boolean flag1;
            flag1 = flag;
            bucket = this;
_L6:
            if (i < 64) goto _L2; else goto _L1
_L1:
            if (bucket.mNext == null)
            {
                bucket.mNext = new Bucket();
            }
            bucket.mNext.insert(i - 64, flag1);
_L4:
            return;
_L2:
            long l;
            long l1;
            if ((bucket.mData & 0x8000000000000000L) != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            l = (1L << i) - 1L;
            l1 = bucket.mData;
            bucket.mData = (~l & bucket.mData) << 1 | l1 & l;
            if (flag1)
            {
                bucket.set(i);
            } else
            {
                bucket.clear(i);
            }
            if (!flag && bucket.mNext == null) goto _L4; else goto _L3
_L3:
            if (bucket.mNext == null)
            {
                bucket.mNext = new Bucket();
            }
            bucket = bucket.mNext;
            i = 0;
            flag1 = flag;
            if (true) goto _L6; else goto _L5
_L5:
        }

        final boolean remove(int i)
        {
            Bucket bucket = this;
            for (; i >= 64; i -= 64)
            {
                if (bucket.mNext == null)
                {
                    bucket.mNext = new Bucket();
                }
                bucket = bucket.mNext;
            }

            long l = 1L << i;
            long l1;
            boolean flag;
            if ((bucket.mData & l) != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            bucket.mData = bucket.mData & ~l;
            l--;
            l1 = bucket.mData;
            bucket.mData = Long.rotateRight(~l & bucket.mData, 1) | l1 & l;
            if (bucket.mNext != null)
            {
                if (bucket.mNext.get(0))
                {
                    bucket.set(63);
                }
                bucket.mNext.remove(0);
            }
            return flag;
        }

        final void reset()
        {
            Bucket bucket = this;
            do
            {
                bucket.mData = 0L;
                if (bucket.mNext != null)
                {
                    bucket = bucket.mNext;
                } else
                {
                    return;
                }
            } while (true);
        }

        final void set(int i)
        {
            if (i >= 64)
            {
                if (mNext == null)
                {
                    mNext = new Bucket();
                }
                mNext.set(i - 64);
                return;
            } else
            {
                mData = mData | 1L << i;
                return;
            }
        }

        public final String toString()
        {
            if (mNext == null)
            {
                return Long.toBinaryString(mData);
            } else
            {
                return (new StringBuilder()).append(mNext.toString()).append("xx").append(Long.toBinaryString(mData)).toString();
            }
        }

        Bucket()
        {
            mData = 0L;
        }
    }


    private class Callback
    {

        public abstract void addView(View view, int i);

        public abstract void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams);

        public abstract void detachViewFromParent(int i);

        public abstract View getChildAt(int i);

        public abstract int getChildCount();

        public abstract RecyclerView.ViewHolder getChildViewHolder(View view);

        public abstract int indexOfChild(View view);

        public abstract void onEnteredHiddenState(View view);

        public abstract void onLeftHiddenState(View view);

        public abstract void removeAllViews();

        public abstract void removeViewAt(int i);
    }

}
