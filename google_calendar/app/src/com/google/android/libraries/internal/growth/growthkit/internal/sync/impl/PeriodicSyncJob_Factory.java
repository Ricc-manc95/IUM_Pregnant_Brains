// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PeriodicSyncJob

public final class PeriodicSyncJob_Factory
    implements Factory
{

    private final Provider appContextProvider;
    private final Provider blockingExecutorProvider;
    private final Provider firebaseJobDispatcherProvider;
    private final Provider gcoreProviderInstallerProvider;
    private final Provider growthkitEnabledProvider;
    private final Provider periodProvider;
    private final Provider promotionSyncProvider;
    private final Provider syncRetryMaxDelayMsProvider;
    private final Provider syncRetryMinDelayMsProvider;
    private final Provider syncRetryPolicyProvider;

    public PeriodicSyncJob_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9)
    {
        firebaseJobDispatcherProvider = provider;
        growthkitEnabledProvider = provider1;
        periodProvider = provider2;
        syncRetryMinDelayMsProvider = provider3;
        syncRetryMaxDelayMsProvider = provider4;
        syncRetryPolicyProvider = provider5;
        gcoreProviderInstallerProvider = provider6;
        appContextProvider = provider7;
        promotionSyncProvider = provider8;
        blockingExecutorProvider = provider9;
    }

    public final Object get()
    {
        Provider provider = firebaseJobDispatcherProvider;
        Provider provider1 = growthkitEnabledProvider;
        Provider provider2 = periodProvider;
        Provider provider3 = syncRetryMinDelayMsProvider;
        Provider provider4 = syncRetryMaxDelayMsProvider;
        Provider provider5 = syncRetryPolicyProvider;
        Provider provider6 = gcoreProviderInstallerProvider;
        Provider provider7 = appContextProvider;
        Provider provider8 = promotionSyncProvider;
        Provider provider9 = blockingExecutorProvider;
        return new PeriodicSyncJob((FirebaseJobDispatcher)provider.get(), provider1, provider2, provider3, provider4, provider5, (GcoreProviderInstaller)provider6.get(), (Context)provider7.get(), (PromotionSync)provider8.get(), (ListeningExecutorService)provider9.get());
    }
}
