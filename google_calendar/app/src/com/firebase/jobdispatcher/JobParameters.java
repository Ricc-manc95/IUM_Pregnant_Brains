// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;

// Referenced classes of package com.firebase.jobdispatcher:
//            RetryStrategy, JobTrigger

public interface JobParameters
{

    public abstract int[] getConstraints();

    public abstract Bundle getExtras();

    public abstract int getLifetime();

    public abstract RetryStrategy getRetryStrategy();

    public abstract String getService();

    public abstract String getTag();

    public abstract JobTrigger getTrigger();

    public abstract boolean isRecurring();

    public abstract boolean shouldReplaceCurrent();
}
