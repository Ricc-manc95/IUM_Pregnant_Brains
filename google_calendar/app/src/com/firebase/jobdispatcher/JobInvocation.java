// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import java.util.Arrays;
import org.json.JSONObject;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobParameters, RetryStrategy, JobTrigger, TriggerReason

final class JobInvocation
    implements JobParameters
{

    public final int constraints[];
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    public final String service;
    public final String tag;
    private final JobTrigger trigger;
    private final TriggerReason triggerReason;

    JobInvocation(Builder builder)
    {
        tag = builder.tag;
        service = builder.service;
        trigger = builder.trigger;
        retryStrategy = builder.retryStrategy;
        recurring = builder.recurring;
        lifetime = builder.lifetime;
        constraints = builder.constraints;
        extras = builder.extras;
        replaceCurrent = builder.replaceCurrent;
        triggerReason = builder.triggerReason;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || !getClass().equals(obj.getClass()))
            {
                return false;
            }
            obj = (JobInvocation)obj;
            if (!tag.equals(((JobInvocation) (obj)).tag) || !service.equals(((JobInvocation) (obj)).service))
            {
                return false;
            }
        }
        return true;
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

    public final int hashCode()
    {
        return tag.hashCode() * 31 + service.hashCode();
    }

    public final boolean isRecurring()
    {
        return recurring;
    }

    public final boolean shouldReplaceCurrent()
    {
        return replaceCurrent;
    }

    public final String toString()
    {
        String s = JSONObject.quote(tag);
        String s1 = service;
        String s2 = String.valueOf(trigger);
        boolean flag = recurring;
        int i = lifetime;
        String s3 = Arrays.toString(constraints);
        String s4 = String.valueOf(extras);
        String s5 = String.valueOf(retryStrategy);
        boolean flag1 = replaceCurrent;
        String s6 = String.valueOf(triggerReason);
        return (new StringBuilder(String.valueOf(s).length() + 159 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length())).append("JobInvocation{tag='").append(s).append('\'').append(", service='").append(s1).append('\'').append(", trigger=").append(s2).append(", recurring=").append(flag).append(", lifetime=").append(i).append(", constraints=").append(s3).append(", extras=").append(s4).append(", retryStrategy=").append(s5).append(", replaceCurrent=").append(flag1).append(", triggerReason=").append(s6).append('}').toString();
    }

    private class Builder
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

        Builder()
        {
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        }
    }

}
