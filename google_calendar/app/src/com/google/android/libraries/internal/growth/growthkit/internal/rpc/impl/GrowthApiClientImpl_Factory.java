// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter2;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            GrowthApiClientImpl

public final class GrowthApiClientImpl_Factory
    implements Factory
{

    private final Provider apiKeyProvider;
    private final Provider appCertFingerprintProvider;
    private final Provider appPackageNameProvider;
    private final Provider channelProvider;
    private final Provider executorProvider;
    private final Provider gkAccountManagerProvider;
    private final Provider streamzIncrementsProvider;
    private final Provider syncsCounterProvider;

    public GrowthApiClientImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7)
    {
        executorProvider = provider;
        appCertFingerprintProvider = provider1;
        appPackageNameProvider = provider2;
        apiKeyProvider = provider3;
        channelProvider = provider4;
        gkAccountManagerProvider = provider5;
        syncsCounterProvider = provider6;
        streamzIncrementsProvider = provider7;
    }

    public final Object get()
    {
        Provider provider = executorProvider;
        Provider provider1 = appCertFingerprintProvider;
        Provider provider2 = appPackageNameProvider;
        Provider provider3 = apiKeyProvider;
        Provider provider4 = channelProvider;
        Provider provider5 = gkAccountManagerProvider;
        Provider provider6 = syncsCounterProvider;
        Provider provider7 = streamzIncrementsProvider;
        return new GrowthApiClientImpl((ListeningExecutorService)provider.get(), (String)provider1.get(), (String)provider2.get(), (String)provider3.get(), provider4, (AccountManager)provider5.get(), (Counter2)provider6.get(), (StreamzIncrements)provider7.get());
    }
}
