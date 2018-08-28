// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.common.base.Function;
import com.google.protobuf.AbstractMessageLite;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeManagerImpl

final class arg._cls1
    implements Function
{

    private final PhenotypeManagerImpl arg$1;

    public final Object apply(Object obj)
    {
        PhenotypeManagerImpl phenotypemanagerimpl = arg$1;
        obj = (GcoreGoogleApiClient)obj;
        Phenotype phenotype = phenotypemanagerimpl.phenotype;
        String s = phenotypemanagerimpl.mendelPackage;
        int i = phenotypemanagerimpl.appVersionCode;
        byte abyte0[] = phenotypemanagerimpl.growthKitProperties.toByteArray();
        return phenotype.register(((GcoreGoogleApiClient) (obj)), s, i, new String[0], abyte0);
    }

    (PhenotypeManagerImpl phenotypemanagerimpl)
    {
        arg$1 = phenotypemanagerimpl;
    }
}
