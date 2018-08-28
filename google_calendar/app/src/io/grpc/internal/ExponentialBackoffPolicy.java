// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            BackoffPolicy

public final class ExponentialBackoffPolicy
    implements BackoffPolicy
{

    private long initialBackoffNanos;
    private double jitter;
    private long maxBackoffNanos;
    private double multiplier;
    private long nextBackoffNanos;
    private Random random;

    public ExponentialBackoffPolicy()
    {
        random = new Random();
        initialBackoffNanos = TimeUnit.SECONDS.toNanos(1L);
        maxBackoffNanos = TimeUnit.MINUTES.toNanos(2L);
        multiplier = 1.6000000000000001D;
        jitter = 0.20000000000000001D;
        nextBackoffNanos = initialBackoffNanos;
    }

    public final long nextBackoffNanos()
    {
        long l = nextBackoffNanos;
        nextBackoffNanos = Math.min((long)((double)l * multiplier), maxBackoffNanos);
        double d = -jitter;
        d = (double)l * d;
        double d1 = jitter;
        d1 = (double)l * d1;
        boolean flag;
        if (d1 >= d)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return (long)((d1 - d) * random.nextDouble() + d) + l;
        }
    }
}
