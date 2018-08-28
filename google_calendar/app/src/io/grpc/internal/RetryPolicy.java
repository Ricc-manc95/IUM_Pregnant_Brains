// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

final class RetryPolicy
{

    public static final RetryPolicy DEFAULT = new RetryPolicy(1, 0L, 0L, 1.0D, Collections.emptySet());
    public final double backoffMultiplier;
    public final long initialBackoffNanos;
    public final int maxAttempts;
    public final long maxBackoffNanos;
    public final Set retryableStatusCodes;

    RetryPolicy(int i, long l, long l1, double d, 
            Set set)
    {
        maxAttempts = i;
        initialBackoffNanos = l;
        maxBackoffNanos = l1;
        backoffMultiplier = d;
        retryableStatusCodes = ImmutableSet.copyOf(set);
    }

    public final boolean equals(Object obj)
    {
        Object obj1;
        if (obj instanceof RetryPolicy)
        {
            if (maxAttempts == ((RetryPolicy) (obj1 = (RetryPolicy)obj)).maxAttempts && initialBackoffNanos == ((RetryPolicy) (obj1)).initialBackoffNanos && maxBackoffNanos == ((RetryPolicy) (obj1)).maxBackoffNanos && Double.compare(backoffMultiplier, ((RetryPolicy) (obj1)).backoffMultiplier) == 0)
            {
                obj = retryableStatusCodes;
                obj1 = ((RetryPolicy) (obj1)).retryableStatusCodes;
                boolean flag;
                if (obj == obj1 || obj != null && obj.equals(obj1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(maxAttempts), Long.valueOf(initialBackoffNanos), Long.valueOf(maxBackoffNanos), Double.valueOf(backoffMultiplier), retryableStatusCodes
        });
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("maxAttempts", maxAttempts).add("initialBackoffNanos", initialBackoffNanos).add("maxBackoffNanos", maxBackoffNanos);
        double d = backoffMultiplier;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(d);
        if ("backoffMultiplier" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"backoffMultiplier";
            return tostringhelper.add("retryableStatusCodes", retryableStatusCodes).toString();
        }
    }

}
