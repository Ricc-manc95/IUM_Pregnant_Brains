// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


// Referenced classes of package com.firebase.jobdispatcher:
//            ValidationEnforcer, Driver, RetryStrategy, JobValidator, 
//            Job

public final class FirebaseJobDispatcher
{

    public final Driver driver;
    private final RetryStrategy.Builder retryStrategyBuilder;
    public final ValidationEnforcer validator;

    public FirebaseJobDispatcher(Driver driver1)
    {
        driver = driver1;
        validator = new ValidationEnforcer(driver1.getValidator());
        retryStrategyBuilder = new RetryStrategy.Builder(validator);
    }

    public final void mustSchedule(Job job)
    {
        driver.isAvailable();
        if (driver.schedule(job) != 0)
        {
            throw new ScheduleFailedException();
        } else
        {
            return;
        }
    }

    public final RetryStrategy newRetryStrategy(int i, int j, int k)
    {
        Object obj = retryStrategyBuilder;
        RetryStrategy retrystrategy = new RetryStrategy(i, j, k);
        obj = ((RetryStrategy.Builder) (obj)).validator.validator.validate(retrystrategy);
        if (obj != null)
        {
            throw new ValidationEnforcer.ValidationException("JobParameters is invalid", ((java.util.List) (obj)));
        } else
        {
            return retrystrategy;
        }
    }

    private class ScheduleFailedException extends RuntimeException
    {

        public ScheduleFailedException()
        {
        }
    }

}
