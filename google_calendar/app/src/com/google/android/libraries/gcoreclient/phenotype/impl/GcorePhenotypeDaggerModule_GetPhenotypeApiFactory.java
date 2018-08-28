// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.gcoreclient.phenotype.impl:
//            PhenotypeApiImpl

public final class GcorePhenotypeDaggerModule_GetPhenotypeApiFactory
    implements Factory
{

    public static final GcorePhenotypeDaggerModule_GetPhenotypeApiFactory INSTANCE = new GcorePhenotypeDaggerModule_GetPhenotypeApiFactory();

    public GcorePhenotypeDaggerModule_GetPhenotypeApiFactory()
    {
    }

    public static PhenotypeApi proxyGetPhenotypeApi()
    {
        PhenotypeApiImpl phenotypeapiimpl = new PhenotypeApiImpl();
        if (phenotypeapiimpl == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PhenotypeApi)phenotypeapiimpl;
        }
    }

    public final Object get()
    {
        PhenotypeApiImpl phenotypeapiimpl = new PhenotypeApiImpl();
        if (phenotypeapiimpl == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PhenotypeApi)phenotypeapiimpl;
        }
    }

}
