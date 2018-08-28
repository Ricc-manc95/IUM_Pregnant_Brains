// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            ExperimentFlagsModule

public final class ExperimentFlagsModule_ProvidePhenotypeFlagBuilderFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider mendelSharedPrefsNameProvider;

    public ExperimentFlagsModule_ProvidePhenotypeFlagBuilderFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        mendelSharedPrefsNameProvider = provider1;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        Provider provider = mendelSharedPrefsNameProvider;
        obj = ExperimentFlagsModule.providePhenotypeFlagBuilder((Context)((Provider) (obj)).get(), (String)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory)obj;
        }
    }
}
