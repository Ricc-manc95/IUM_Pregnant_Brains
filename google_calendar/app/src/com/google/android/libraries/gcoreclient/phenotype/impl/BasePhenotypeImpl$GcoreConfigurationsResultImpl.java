// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreStatusImpl;
import com.google.android.libraries.gcoreclient.common.api.support.ResultWrapper;
import com.google.android.libraries.gcoreclient.phenotype.GcoreConfigurations;

// Referenced classes of package com.google.android.libraries.gcoreclient.phenotype.impl:
//            GcoreConfigurationsImpl

final class configurationResult
    implements com.google.android.libraries.gcoreclient.phenotype.Impl
{

    public static final ResultWrapper CONFIGURATIONS_RESULT_WRAPPER = new _cls1();
    private final com.google.android.gms.phenotype.nfigurationsResultImpl._cls1 configurationResult;

    public final GcoreConfigurations getConfigurations()
    {
        return new GcoreConfigurationsImpl(configurationResult.ons());
    }

    public final GcoreStatus getStatus()
    {
        return new GcoreStatusImpl(configurationResult.configurationResult());
    }


    _cls1(com.google.android.gms.phenotype.nfigurationsResultImpl nfigurationsresultimpl)
    {
        configurationResult = nfigurationsresultimpl;
    }

    class _cls1
        implements ResultWrapper
    {

        public final GcoreResult wrap(Result result)
        {
            return new BasePhenotypeImpl.GcoreConfigurationsResultImpl((com.google.android.gms.phenotype.PhenotypeApi.ConfigurationsResult)result);
        }

            _cls1()
            {
            }
    }

}
