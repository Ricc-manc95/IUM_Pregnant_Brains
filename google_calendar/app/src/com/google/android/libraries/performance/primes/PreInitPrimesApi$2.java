// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            ConfiguredPrimesApi, LazyMetricServices, PrimesConfigurations, PrimesMemoryConfigurations, 
//            MemoryMetricService

final class 
    implements heduledApiCall
{

    public final void callApi(ConfiguredPrimesApi configuredprimesapi)
    {
        if (configuredprimesapi.lazyServices.configs.memoryConfigurations().enabled)
        {
            configuredprimesapi.lazyServices.memoryMetricService().startMonitoring();
        }
    }

    ()
    {
    }
}
