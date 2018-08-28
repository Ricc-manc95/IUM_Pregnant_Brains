// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


final class mData
{

    public long mData;
    public mData mNext;

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
        mData mdata = this;
        for (; i >= 64; i -= 64)
        {
            if (mdata.mNext == null)
            {
                mdata.mNext = new <init>();
            }
            mdata = mdata.mNext;
        }

        return (mdata.mData & 1L << i) != 0L;
    }

    final void insert(int i, boolean flag)
    {
        mData mdata;
        boolean flag1;
        flag1 = flag;
        mdata = this;
_L6:
        if (i < 64) goto _L2; else goto _L1
_L1:
        if (mdata.mNext == null)
        {
            mdata.mNext = new <init>();
        }
        mdata.mNext.insert(i - 64, flag1);
_L4:
        return;
_L2:
        long l;
        long l1;
        if ((mdata.mData & 0x8000000000000000L) != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = (1L << i) - 1L;
        l1 = mdata.mData;
        mdata.mData = (~l & mdata.mData) << 1 | l1 & l;
        if (flag1)
        {
            mdata.set(i);
        } else
        {
            mdata.clear(i);
        }
        if (!flag && mdata.mNext == null) goto _L4; else goto _L3
_L3:
        if (mdata.mNext == null)
        {
            mdata.mNext = new <init>();
        }
        mdata = mdata.mNext;
        i = 0;
        flag1 = flag;
        if (true) goto _L6; else goto _L5
_L5:
    }

    final boolean remove(int i)
    {
        mNext mnext = this;
        for (; i >= 64; i -= 64)
        {
            if (mnext.mNext == null)
            {
                mnext.mNext = new <init>();
            }
            mnext = mnext.mNext;
        }

        long l = 1L << i;
        long l1;
        boolean flag;
        if ((mnext.mData & l) != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mnext.mData = mnext.mData & ~l;
        l--;
        l1 = mnext.mData;
        mnext.mData = Long.rotateRight(~l & mnext.mData, 1) | l1 & l;
        if (mnext.mNext != null)
        {
            if (mnext.mNext.get(0))
            {
                mnext.set(63);
            }
            mnext.mNext.remove(0);
        }
        return flag;
    }

    final void reset()
    {
        remove remove1 = this;
        do
        {
            remove1.mData = 0L;
            if (remove1.mNext != null)
            {
                remove1 = remove1.mNext;
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
                mNext = new <init>();
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

    ()
    {
        mData = 0L;
    }
}
