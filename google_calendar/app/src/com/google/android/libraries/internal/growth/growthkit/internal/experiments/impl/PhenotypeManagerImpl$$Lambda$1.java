// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeManagerImpl

final class arg._cls1
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

    (PhenotypeManagerImpl phenotypemanagerimpl)
    {
        arg$1 = phenotypemanagerimpl;
    }
}
