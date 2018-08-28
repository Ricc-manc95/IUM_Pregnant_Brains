// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobParameters, RetryStrategy, JobTrigger

public final class Job
    implements JobParameters
{

    private final int constraints[];
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    public final String service;
    public final String tag;
    public final JobTrigger trigger;

    Job(Builder builder)
    {
        service = builder.serviceClassName;
        Object obj;
        if (builder.extras == null)
        {
            obj = null;
        } else
        {
            obj = new Bundle(builder.extras);
        }
        extras = ((Bundle) (obj));
        tag = builder.tag;
        trigger = builder.trigger;
        retryStrategy = builder.retryStrategy;
        lifetime = builder.lifetime;
        recurring = builder.recurring;
        if (builder.constraints != null)
        {
            obj = builder.constraints;
        } else
        {
            obj = new int[0];
        }
        constraints = ((int []) (obj));
        replaceCurrent = builder.replaceCurrent;
    }

    public final int[] getConstraints()
    {
        return constraints;
    }

    public final Bundle getExtras()
    {
        return extras;
    }

    public final int getLifetime()
    {
        return lifetime;
    }

    public final RetryStrategy getRetryStrategy()
    {
        return retryStrategy;
    }

    public final String getService()
    {
        return service;
    }

    public final String getTag()
    {
        return tag;
    }

    public final JobTrigger getTrigger()
    {
        return trigger;
    }

    public final boolean isRecurring()
    {
        return recurring;
    }

    public final boolean shouldReplaceCurrent()
    {
        return replaceCurrent;
    }

    private class Builder
        implements JobParameters
    {

        public int constraints[];
        public Bundle extras;
        public int lifetime;
        public boolean recurring;
        public boolean replaceCurrent;
        public RetryStrategy retryStrategy;
        public String serviceClassName;
        public String tag;
        public JobTrigger trigger;
        public final ValidationEnforcer validator;

        public final Job build()
        {
            java.util.List list = validator.validator.validate(this);
            if (list != null)
            {
                throw new ValidationEnforcer.ValidationException("JobParameters is invalid", list);
            } else
            {
                return new Job(this);
            }
        }

        public final int[] getConstraints()
        {
            if (constraints == null)
            {
                return new int[0];
            } else
            {
                return constraints;
            }
        }

        public final Bundle getExtras()
        {
            return extras;
        }

        public final int getLifetime()
        {
            return lifetime;
        }

        public final RetryStrategy getRetryStrategy()
        {
            return retryStrategy;
        }

        public final String getService()
        {
            return serviceClassName;
        }

        public final String getTag()
        {
            return tag;
        }

        public final JobTrigger getTrigger()
        {
            return trigger;
        }

        public final boolean isRecurring()
        {
            return recurring;
        }

        public final boolean shouldReplaceCurrent()
        {
            return replaceCurrent;
        }

        public Builder(ValidationEnforcer validationenforcer)
        {
            trigger = Trigger.NOW;
            lifetime = 1;
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
            replaceCurrent = false;
            recurring = false;
            validator = validationenforcer;
        }

        Builder(ValidationEnforcer validationenforcer, JobParameters jobparameters)
        {
            trigger = Trigger.NOW;
            lifetime = 1;
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
            replaceCurrent = false;
            recurring = false;
            validator = validationenforcer;
            tag = jobparameters.getTag();
            serviceClassName = jobparameters.getService();
            trigger = jobparameters.getTrigger();
            recurring = jobparameters.isRecurring();
            lifetime = jobparameters.getLifetime();
            constraints = jobparameters.getConstraints();
            extras = jobparameters.getExtras();
            retryStrategy = jobparameters.getRetryStrategy();
        }
    }

}
