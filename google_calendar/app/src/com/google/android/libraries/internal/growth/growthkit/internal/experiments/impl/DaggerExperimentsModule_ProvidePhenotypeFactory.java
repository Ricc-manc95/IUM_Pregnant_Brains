// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProvidePhenotypeFactory
    implements Factory
{

    private final Provider phenotypeFactoryProvider;

    public DaggerExperimentsModule_ProvidePhenotypeFactory(Provider provider)
    {
        phenotypeFactoryProvider = provider;
    }

    public final Object get()
    {
        Phenotype phenotype = DaggerExperimentsModule.providePhenotype((com.google.android.libraries.gcoreclient.phenotype.Phenotype.Factory)phenotypeFactoryProvider.get());
        if (phenotype == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Phenotype)phenotype;
        }
    }
}
