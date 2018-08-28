// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            OneoffSyncJob

public final class OneoffSyncJob_Factory
    implements Factory
{

    private final Provider appContextProvider;
    private final Provider blockingExecutorProvider;
    private final Provider clockProvider;
    private final Provider firebaseJobDispatcherProvider;
    private final Provider gcoreProviderInstallerProvider;
    private final Provider growthkitEnabledProvider;
    private final Provider promotionSyncProvider;
    private final Provider sharedPrefsFutureProvider;
    private final Provider syncOnStartupAtMostEveryMsProvider;
    private final Provider syncOnStartupProvider;
    private final Provider syncRetryMaxDelayMsProvider;
    private final Provider syncRetryMinDelayMsProvider;
    private final Provider syncRetryPolicyProvider;

    public OneoffSyncJob_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12)
    {
        firebaseJobDispatcherProvider = provider;
        growthkitEnabledProvider = provider1;
        syncOnStartupProvider = provider2;
        syncOnStartupAtMostEveryMsProvider = provider3;
        syncRetryMinDelayMsProvider = provider4;
        syncRetryMaxDelayMsProvider = provider5;
        syncRetryPolicyProvider = provider6;
        gcoreProviderInstallerProvider = provider7;
        appContextProvider = provider8;
        promotionSyncProvider = provider9;
        blockingExecutorProvider = provider10;
        sharedPrefsFutureProvider = provider11;
        clockProvider = provider12;
    }

    public final Object get()
    {
        Provider provider = firebaseJobDispatcherProvider;
        Provider provider1 = growthkitEnabledProvider;
        Provider provider2 = syncOnStartupProvider;
        Provider provider3 = syncOnStartupAtMostEveryMsProvider;
        Provider provider4 = syncRetryMinDelayMsProvider;
        Provider provider5 = syncRetryMaxDelayMsProvider;
        Provider provider6 = syncRetryPolicyProvider;
        Provider provider7 = gcoreProviderInstallerProvider;
        Provider provider8 = appContextProvider;
        Provider provider9 = promotionSyncProvider;
        Provider provider10 = blockingExecutorProvider;
        Provider provider11 = sharedPrefsFutureProvider;
        Provider provider12 = clockProvider;
        return new OneoffSyncJob((FirebaseJobDispatcher)provider.get(), provider1, provider2, provider3, provider4, provider5, provider6, (GcoreProviderInstaller)provider7.get(), (Context)provider8.get(), (PromotionSync)provider9.get(), (ListeningExecutorService)provider10.get(), (ListenableFuture)provider11.get(), (Clock)provider12.get());
    }
}
