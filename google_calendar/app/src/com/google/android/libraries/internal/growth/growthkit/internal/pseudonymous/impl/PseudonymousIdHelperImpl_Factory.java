// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdApi;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl:
//            PseudonymousIdHelperImpl

public final class PseudonymousIdHelperImpl_Factory
    implements Factory
{

    private final Provider executorProvider;
    private final Provider googleApiClientBuilderProvider;
    private final Provider pseudonymousIdApiProvider;
    private final Provider pseudonymousIdProvider;

    public PseudonymousIdHelperImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        executorProvider = provider;
        pseudonymousIdProvider = provider1;
        pseudonymousIdApiProvider = provider2;
        googleApiClientBuilderProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = executorProvider;
        Provider provider1 = pseudonymousIdProvider;
        Provider provider2 = pseudonymousIdApiProvider;
        Provider provider3 = googleApiClientBuilderProvider;
        return new PseudonymousIdHelperImpl((ListeningExecutorService)provider.get(), (GcorePseudonymousId)provider1.get(), (GcorePseudonymousIdApi)provider2.get(), (com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder)provider3.get());
    }
}
