// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.SharedPreferences;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeFlagUpdater

public final class PhenotypeFlagUpdater_Factory
    implements Factory
{

    private final Provider executorServiceProvider;
    private final Provider googleApiClientBuilderProvider;
    private final Provider phenotypeApiProvider;
    private final Provider phenotypeMendelPackageProvider;
    private final Provider phenotypeProvider;
    private final Provider phenotypeSharedPrefsProvider;

    public PhenotypeFlagUpdater_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5)
    {
        phenotypeProvider = provider;
        phenotypeSharedPrefsProvider = provider1;
        phenotypeMendelPackageProvider = provider2;
        executorServiceProvider = provider3;
        googleApiClientBuilderProvider = provider4;
        phenotypeApiProvider = provider5;
    }

    public final Object get()
    {
        Provider provider = phenotypeProvider;
        Provider provider1 = phenotypeSharedPrefsProvider;
        Provider provider2 = phenotypeMendelPackageProvider;
        Provider provider3 = executorServiceProvider;
        Provider provider4 = googleApiClientBuilderProvider;
        Provider provider5 = phenotypeApiProvider;
        return new PhenotypeFlagUpdater((Phenotype)provider.get(), (SharedPreferences)provider1.get(), (String)provider2.get(), (ListeningExecutorService)provider3.get(), (com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder)provider4.get(), (PhenotypeApi)provider5.get());
    }
}
