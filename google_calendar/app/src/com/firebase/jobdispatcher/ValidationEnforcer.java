// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import java.util.List;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobValidator, JobParameters, RetryStrategy

public final class ValidationEnforcer
    implements JobValidator
{

    public final JobValidator validator;

    public ValidationEnforcer(JobValidator jobvalidator)
    {
        validator = jobvalidator;
    }

    public final List validate(JobParameters jobparameters)
    {
        return validator.validate(jobparameters);
    }

    public final List validate(RetryStrategy retrystrategy)
    {
        return validator.validate(retrystrategy);
    }
}
