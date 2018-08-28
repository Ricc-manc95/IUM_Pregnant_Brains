// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Intent;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.GcoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.PhenotypeManager;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeFlagUpdater

public final class PhenotypeManagerImpl
    implements PhenotypeManager
{

    public static final Logger logger = new Logger();
    public final int appVersionCode;
    private final PhenotypeFlagUpdater flagUpdater;
    private final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder googleApiClientBuilder;
    public GrowthKitProperties growthKitProperties;
    public final String mendelPackage;
    public final Phenotype phenotype;
    private final PhenotypeApi phenotypeApi;

    public PhenotypeManagerImpl(com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder builder, PhenotypeApi phenotypeapi, Phenotype phenotype1, PhenotypeFlagUpdater phenotypeflagupdater, String s, int i, GrowthKitProperties growthkitproperties)
    {
        googleApiClientBuilder = builder;
        phenotypeApi = phenotypeapi;
        phenotype = phenotype1;
        flagUpdater = phenotypeflagupdater;
        mendelPackage = s;
        appVersionCode = i;
        growthKitProperties = growthkitproperties;
    }

    public final void handlePhenotypeUpdateIntent(Intent intent)
    {
        intent = intent.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        Logger logger1 = logger;
        if (mendelPackage.equals(intent))
        {
            updateFlags();
        }
    }

    public final void register()
    {
        class .Lambda._cls0
            implements Function
        {

            private final PhenotypeManagerImpl arg$1;

            public final Object apply(Object obj)
            {
                PhenotypeManagerImpl phenotypemanagerimpl = arg$1;
                obj = (GcoreGoogleApiClient)obj;
                Phenotype phenotype1 = phenotypemanagerimpl.phenotype;
                String s = phenotypemanagerimpl.mendelPackage;
                int i = phenotypemanagerimpl.appVersionCode;
                byte abyte0[] = phenotypemanagerimpl.growthKitProperties.toByteArray();
                return phenotype1.register(((GcoreGoogleApiClient) (obj)), s, i, new String[0], abyte0);
            }

            .Lambda._cls0()
            {
                arg$1 = PhenotypeManagerImpl.this;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final PhenotypeManagerImpl arg$1;

            public final Object apply(Object obj)
            {
                PhenotypeManagerImpl phenotypemanagerimpl = arg$1;
                obj = (GcoreStatus)obj;
                obj = PhenotypeManagerImpl.logger;
                phenotypemanagerimpl.updateFlags();
                return null;
            }

            .Lambda._cls1()
            {
                arg$1 = PhenotypeManagerImpl.this;
            }
        }

        class .Lambda._cls2
            implements Receiver
        {

            public static final Receiver $instance = new .Lambda._cls2();

            public final void accept(Object obj)
            {
                obj = (Throwable)obj;
                PhenotypeManagerImpl.logger.w(((Throwable) (obj)), "Failed to register to Phenotype", new Object[0]);
            }


            private .Lambda._cls2()
            {
            }
        }

        MoreFutures.addCallback(GcoreFutures.makeGcoreCall(googleApiClientBuilder, phenotypeApi, new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAndClose(new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), null, .Lambda._cls2..instance);
    }

    public final void updateFlags()
    {
        Object obj = logger;
        obj = flagUpdater;
        MoreFutures.addCallback(GcoreFutures.makeGcoreCall(((PhenotypeFlagUpdater) (obj)).googleApiClientBuilder, ((PhenotypeFlagUpdater) (obj)).phenotypeApi, new PhenotypeFlagUpdater..Lambda._cls2(((PhenotypeFlagUpdater) (obj)), ""), ((PhenotypeFlagUpdater) (obj)).executorService).transformAsyncCloseable(new PhenotypeFlagUpdater..Lambda._cls3(((PhenotypeFlagUpdater) (obj))), ((PhenotypeFlagUpdater) (obj)).executorService).transformAndClose(), PhenotypeFlagUpdater..Lambda._cls0.$instance, PhenotypeFlagUpdater..Lambda._cls1.$instance);
    }

}
