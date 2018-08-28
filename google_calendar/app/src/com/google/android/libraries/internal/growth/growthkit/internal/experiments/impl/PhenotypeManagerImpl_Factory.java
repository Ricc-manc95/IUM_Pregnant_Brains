// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeManagerImpl, PhenotypeFlagUpdater

public final class PhenotypeManagerImpl_Factory
    implements Factory
{

    private final Provider appVersionCodeProvider;
    private final Provider flagUpdaterProvider;
    private final Provider googleApiClientBuilderProvider;
    private final Provider growthKitPropertiesProvider;
    private final Provider mendelPackageProvider;
    private final Provider phenotypeApiProvider;
    private final Provider phenotypeProvider;

    public PhenotypeManagerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        googleApiClientBuilderProvider = provider;
        phenotypeApiProvider = provider1;
        phenotypeProvider = provider2;
        flagUpdaterProvider = provider3;
        mendelPackageProvider = provider4;
        appVersionCodeProvider = provider5;
        growthKitPropertiesProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = googleApiClientBuilderProvider;
        Provider provider1 = phenotypeApiProvider;
        Provider provider2 = phenotypeProvider;
        Provider provider3 = flagUpdaterProvider;
        Provider provider4 = mendelPackageProvider;
        Provider provider5 = appVersionCodeProvider;
        Provider provider6 = growthKitPropertiesProvider;
        return new PhenotypeManagerImpl((com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder)provider.get(), (PhenotypeApi)provider1.get(), (Phenotype)provider2.get(), (PhenotypeFlagUpdater)provider3.get(), (String)provider4.get(), ((Integer)provider5.get()).intValue(), (GrowthKitProperties)provider6.get());
    }
}
