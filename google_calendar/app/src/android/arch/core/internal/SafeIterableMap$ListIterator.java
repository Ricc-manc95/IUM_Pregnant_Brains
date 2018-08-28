// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.internal;

import java.util.Iterator;

abstract class mNext
    implements , Iterator
{

    private mNext mExpectedEnd;
    private mNext mNext;

    abstract  backward( );

    abstract  forward( );

    public boolean hasNext()
    {
        return mNext != null;
    }

    public Object next()
    {
        mNext mnext1 = mNext;
        mNext mnext;
        if (mNext == mExpectedEnd || mExpectedEnd == null)
        {
            mnext = null;
        } else
        {
            mnext = forward(mNext);
        }
        mNext = mnext;
        return mnext1;
    }

    public final void supportRemove(mNext mnext)
    {
        Object obj = null;
        if (mExpectedEnd == mnext && mnext == mNext)
        {
            mNext = null;
            mExpectedEnd = null;
        }
        if (mExpectedEnd == mnext)
        {
            mExpectedEnd = backward(mExpectedEnd);
        }
        if (mNext == mnext)
        {
            mnext = obj;
            if (mNext != mExpectedEnd)
            {
                if (mExpectedEnd == null)
                {
                    mnext = obj;
                } else
                {
                    mnext = forward(mNext);
                }
            }
            mNext = mnext;
        }
    }

    ( ,  1)
    {
        mExpectedEnd = 1;
        mNext = ;
    }
}
