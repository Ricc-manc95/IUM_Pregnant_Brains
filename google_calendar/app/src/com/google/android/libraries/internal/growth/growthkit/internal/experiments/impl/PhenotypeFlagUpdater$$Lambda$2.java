// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeFlagUpdater

final class arg._cls2
    implements Function
{

    private final PhenotypeFlagUpdater arg$1;
    private final String arg$2;

    public final Object apply(Object obj)
    {
        PhenotypeFlagUpdater phenotypeflagupdater = arg$1;
        String s = arg$2;
        obj = (GcoreGoogleApiClient)obj;
        return phenotypeflagupdater.phenotype.getConfigurationSnapshot(((GcoreGoogleApiClient) (obj)), phenotypeflagupdater.phenotypeMendelPackage, s);
    }

    (PhenotypeFlagUpdater phenotypeflagupdater, String s)
    {
        arg$1 = phenotypeflagupdater;
        arg$2 = s;
    }
}
