// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            Supplier, ScenarioMetricService, PrimesApiImpl

final class this._cls0
    implements Supplier
{

    private final PrimesApiImpl this$0;

    public final Object get()
    {
        return new ScenarioMetricService(PrimesApiImpl.this, executorServiceSupplier, new ProbabilitySampler(1.0F), 10, ScenarioMetricService.DEFAULT_TIMEOUT_MS);
    }

    litySampler()
    {
        this$0 = PrimesApiImpl.this;
        super();
    }
}
