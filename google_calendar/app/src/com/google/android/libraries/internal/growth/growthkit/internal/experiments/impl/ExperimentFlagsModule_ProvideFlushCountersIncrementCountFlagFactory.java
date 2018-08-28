// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            ExperimentFlagsModule

public final class ExperimentFlagsModule_ProvideFlushCountersIncrementCountFlagFactory
    implements Factory
{

    private final Provider flagFactoryProvider;

    public ExperimentFlagsModule_ProvideFlushCountersIncrementCountFlagFactory(Provider provider)
    {
        flagFactoryProvider = provider;
    }

    public final Object get()
    {
        Long long1 = ExperimentFlagsModule.getFlagValue((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory)flagFactoryProvider.get(), "Streamz__flush_counters_increment_count", 10L);
        if (long1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Long)long1;
        }
    }
}
