// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;

// Referenced classes of package com.firebase.jobdispatcher:
//            RetryStrategy, JobInvocation, JobTrigger, TriggerReason

final class EXPONENTIAL
{

    public int constraints[];
    public final Bundle extras = new Bundle();
    public int lifetime;
    public boolean recurring;
    public boolean replaceCurrent;
    public RetryStrategy retryStrategy;
    public String service;
    public String tag;
    public JobTrigger trigger;
    public TriggerReason triggerReason;

    final JobInvocation build()
    {
        if (tag == null || service == null || trigger == null)
        {
            throw new IllegalArgumentException("Required fields were not populated.");
        } else
        {
            return new JobInvocation(this);
        }
    }

    ()
    {
        retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
    }
}
