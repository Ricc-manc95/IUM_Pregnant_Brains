// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesApi, Supplier

final class ScenarioMetricService
{

    public static final long DEFAULT_TIMEOUT_MS;

    ScenarioMetricService(PrimesApi primesapi, Supplier supplier, ProbabilitySampler probabilitysampler, int i, long l)
    {
        new ConcurrentHashMap();
    }

    static 
    {
        DEFAULT_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5L);
    }
}
