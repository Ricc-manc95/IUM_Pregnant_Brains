// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;
import com.google.android.libraries.gcoreclient.phenotype.GcoreConfigurations;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseableFunction;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.GcoreFutures;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeFlagUpdater

final class arg._cls1
    implements AsyncCloseableFunction
{

    private final PhenotypeFlagUpdater arg$1;

    public final AsyncCloseable apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (com.google.android.libraries.gcoreclient.phenotype.sult)obj;
        if (!((com.google.android.libraries.gcoreclient.phenotype.sult) (obj)).getStatus().isSuccess())
        {
            com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = PhenotypeFlagUpdater.logger;
            obj1 = ((PhenotypeFlagUpdater) (obj1)).phenotypeMendelPackage;
            ((com.google.android.libraries.gcoreclient.phenotype.sult) (obj)).getStatus().getStatusMessage();
            obj = ((com.google.android.libraries.gcoreclient.phenotype.sult) (obj)).getStatus();
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.sfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.sfulFuture(obj);
            }
            return AsyncCloseable.fromFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
        } else
        {
            String s = ((com.google.android.libraries.gcoreclient.phenotype.sult) (obj)).getConfigurations().getSnapshotToken();
            ((PhenotypeFlagUpdater) (obj1)).phenotype.writeToSharedPrefs(((PhenotypeFlagUpdater) (obj1)).phenotypeSharedPrefs, ((com.google.android.libraries.gcoreclient.phenotype.sult) (obj)).getConfigurations());
            return GcoreFutures.makeGcoreCall(((PhenotypeFlagUpdater) (obj1)).googleApiClientBuilder, ((PhenotypeFlagUpdater) (obj1)).phenotypeApi, new <init>(((PhenotypeFlagUpdater) (obj1)), s), ((PhenotypeFlagUpdater) (obj1)).executorService);
        }
    }

    (PhenotypeFlagUpdater phenotypeflagupdater)
    {
        arg$1 = phenotypeflagupdater;
    }
}
