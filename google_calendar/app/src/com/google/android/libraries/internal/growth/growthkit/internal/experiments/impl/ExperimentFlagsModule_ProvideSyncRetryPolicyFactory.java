// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            ExperimentFlagsModule

public final class ExperimentFlagsModule_ProvideSyncRetryPolicyFactory
    implements Factory
{

    private final Provider flagFactoryProvider;

    public ExperimentFlagsModule_ProvideSyncRetryPolicyFactory(Provider provider)
    {
        flagFactoryProvider = provider;
    }

    public final Object get()
    {
        Integer integer;
        if (ExperimentFlagsModule.getFlagValue((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory)flagFactoryProvider.get(), "Sync__sync_retry_policy", "").equals("LINEAR_RETRY_POLICY"))
        {
            integer = Integer.valueOf(2);
        } else
        {
            integer = Integer.valueOf(1);
        }
        if (integer == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Integer)integer;
        }
    }
}
