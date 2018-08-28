// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            RetryPolicy, VolleyError

public final class DefaultRetryPolicy
    implements RetryPolicy
{

    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public DefaultRetryPolicy()
    {
        this(2500, 1, 1.0F);
    }

    public DefaultRetryPolicy(int i, int j, float f)
    {
        mCurrentTimeoutMs = i;
        mMaxNumRetries = j;
        mBackoffMultiplier = f;
    }

    public final int getCurrentRetryCount()
    {
        return mCurrentRetryCount;
    }

    public final int getCurrentTimeout()
    {
        return mCurrentTimeoutMs;
    }

    public final void retry(VolleyError volleyerror)
        throws VolleyError
    {
        mCurrentRetryCount = mCurrentRetryCount + 1;
        mCurrentTimeoutMs = mCurrentTimeoutMs + (int)((float)mCurrentTimeoutMs * mBackoffMultiplier);
        boolean flag;
        if (mCurrentRetryCount <= mMaxNumRetries)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw volleyerror;
        } else
        {
            return;
        }
    }
}
