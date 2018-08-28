// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.PseudonymousIdHelper;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.GrowthApiClient;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

public final class PromotionSyncImpl_Factory
    implements Factory
{

    private final Provider accountManagerProvider;
    private final Provider cappedPromotionStoreProvider;
    private final Provider clockProvider;
    private final Provider contextProvider;
    private final Provider executorProvider;
    private final Provider growthApiClientProvider;
    private final Provider monitoredEventClearcutStoreProvider;
    private final Provider monitoredEventVisualElementStoreProvider;
    private final Provider packageNameProvider;
    private final Provider presentedPromoStoreProvider;
    private final Provider promotionStoreProvider;
    private final Provider pseudonymousIdHelperProvider;
    private final Provider setWriteDebugInfoProvider;
    private final Provider sharedPrefsFutureProvider;
    private final Provider syncGaiaAccountsProvider;
    private final Provider syncOverrideCountryProvider;
    private final Provider syncZwiebackAccountsProvider;
    private final Provider uiImageDownloadManagerProvider;
    private final Provider versionCodeProvider;
    private final Provider versionNameProvider;

    public PromotionSyncImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, 
            Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19)
    {
        contextProvider = provider;
        sharedPrefsFutureProvider = provider1;
        executorProvider = provider2;
        presentedPromoStoreProvider = provider3;
        promotionStoreProvider = provider4;
        cappedPromotionStoreProvider = provider5;
        monitoredEventClearcutStoreProvider = provider6;
        monitoredEventVisualElementStoreProvider = provider7;
        growthApiClientProvider = provider8;
        accountManagerProvider = provider9;
        packageNameProvider = provider10;
        versionCodeProvider = provider11;
        versionNameProvider = provider12;
        pseudonymousIdHelperProvider = provider13;
        setWriteDebugInfoProvider = provider14;
        syncGaiaAccountsProvider = provider15;
        syncZwiebackAccountsProvider = provider16;
        syncOverrideCountryProvider = provider17;
        clockProvider = provider18;
        uiImageDownloadManagerProvider = provider19;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = sharedPrefsFutureProvider;
        Provider provider2 = executorProvider;
        Provider provider3 = presentedPromoStoreProvider;
        Provider provider4 = promotionStoreProvider;
        Provider provider5 = cappedPromotionStoreProvider;
        Provider provider6 = monitoredEventClearcutStoreProvider;
        Provider provider7 = monitoredEventVisualElementStoreProvider;
        Provider provider8 = growthApiClientProvider;
        Provider provider9 = accountManagerProvider;
        Provider provider10 = packageNameProvider;
        Provider provider11 = versionCodeProvider;
        Provider provider12 = versionNameProvider;
        Provider provider13 = pseudonymousIdHelperProvider;
        Provider provider14 = setWriteDebugInfoProvider;
        Provider provider15 = syncGaiaAccountsProvider;
        Provider provider16 = syncZwiebackAccountsProvider;
        Provider provider17 = syncOverrideCountryProvider;
        Provider provider18 = clockProvider;
        Provider provider19 = uiImageDownloadManagerProvider;
        return new PromotionSyncImpl((Context)provider.get(), (ListenableFuture)provider1.get(), (ListeningExecutorService)provider2.get(), (PerAccountProvider)provider3.get(), (PerAccountProvider)provider4.get(), (MessageStore)provider5.get(), (PerAccountProvider)provider6.get(), (PerAccountProvider)provider7.get(), (GrowthApiClient)provider8.get(), (AccountManager)provider9.get(), (String)provider10.get(), (Optional)provider11.get(), (Optional)provider12.get(), (PseudonymousIdHelper)provider13.get(), provider14, provider15, provider16, provider17, (Clock)provider18.get(), (Map)provider19.get());
    }
}
