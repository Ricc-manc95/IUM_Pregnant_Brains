// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.phenotype.impl.GcorePhenotypeDaggerModule_GetPhenotypeApiFactory;
import com.google.android.libraries.gcoreclient.phenotype.impl.GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeBroadcastReceiver;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeFlagUpdater;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeManagerImpl;
import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent

final class this._cls0
    implements com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerGrowthKitApplicationComponent.this
{

    private final DaggerGrowthKitApplicationComponent this$0;

    public final void inject(BroadcastReceiver broadcastreceiver)
    {
        broadcastreceiver = (PhenotypeBroadcastReceiver)broadcastreceiver;
        DaggerGrowthKitApplicationComponent daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
        com.google.android.libraries.gcoreclient.common.api.mponentImpl mponentimpl = daggergrowthkitapplicationcomponent.getBuilder();
        com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi phenotypeapi = GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.proxyGetPhenotypeApi();
        Phenotype phenotype = DaggerExperimentsModule.providePhenotype(GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory.proxyGetPhenotypeFactory());
        if (phenotype == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        phenotype = (Phenotype)phenotype;
        Phenotype phenotype1 = DaggerExperimentsModule.providePhenotype(GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory.proxyGetPhenotypeFactory());
        if (phenotype1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            broadcastreceiver.phenotypeManager = new PhenotypeManagerImpl(mponentimpl, phenotypeapi, phenotype, new PhenotypeFlagUpdater((Phenotype)phenotype1, (SharedPreferences)daggergrowthkitapplicationcomponent.providePhenotypeSharedPreferencesProvider.get(), (String)daggergrowthkitapplicationcomponent.provideMendelPerAppPackageProvider.get(), (ListeningExecutorService)daggergrowthkitapplicationcomponent.provideExecutorServiceProvider.get(), daggergrowthkitapplicationcomponent.getBuilder(), GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.proxyGetPhenotypeApi()), (String)daggergrowthkitapplicationcomponent.provideMendelPerAppPackageProvider.get(), ((Integer)daggergrowthkitapplicationcomponent.provideAppVersionCodeProvider2.get()).intValue(), GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory.proxyProvideGrowthKitProperties());
            return;
        }
    }

    ent()
    {
        this$0 = DaggerGrowthKitApplicationComponent.this;
        super();
    }
}
