// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.internal;

import java.util.Iterator;

// Referenced classes of package android.arch.core.internal:
//            SafeIterableMap

public final class mBeforeStart
    implements mBeforeStart, Iterator
{

    private boolean mBeforeStart;
    private mBeforeStart mCurrent;
    private final SafeIterableMap this$0;

    public final boolean hasNext()
    {
        if (!mBeforeStart) goto _L2; else goto _L1
_L1:
        if (mStart == null) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (mCurrent == null || mCurrent.mCurrent == null)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final Object next()
    {
        mCurrent mcurrent;
        if (mBeforeStart)
        {
            mBeforeStart = false;
            mcurrent = mStart;
        } else
        if (mCurrent != null)
        {
            mcurrent = mCurrent.mCurrent;
        } else
        {
            mcurrent = null;
        }
        mCurrent = mcurrent;
        return mCurrent;
    }

    public final void supportRemove(mCurrent mcurrent)
    {
        if (mcurrent == mCurrent)
        {
            mCurrent = mCurrent.mCurrent;
            boolean flag;
            if (mCurrent == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mBeforeStart = flag;
        }
    }

    public ()
    {
        this$0 = SafeIterableMap.this;
        super();
        mBeforeStart = true;
    }
}
