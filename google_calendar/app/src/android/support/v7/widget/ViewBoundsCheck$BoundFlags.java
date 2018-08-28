// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


final class mBoundFlags
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

    ()
    {
        mBoundFlags = 0;
    }
}
