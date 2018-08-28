// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import java.util.List;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobParameters, RetryStrategy

public interface JobValidator
{

    public abstract List validate(JobParameters jobparameters);

    public abstract List validate(RetryStrategy retrystrategy);
}
