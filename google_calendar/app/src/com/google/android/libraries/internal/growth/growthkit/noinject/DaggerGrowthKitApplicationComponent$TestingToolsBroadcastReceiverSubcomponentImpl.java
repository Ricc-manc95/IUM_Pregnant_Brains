// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdApi;
import com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousIdApiImpl;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;
import com.google.android.libraries.gcoreclient.security.impl.GcoreProviderInstallerImpl;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl.PseudonymousIdHelperImpl;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.GrowthApiClient;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.TestingToolsBroadcastReceiver;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.impl.PromotionSyncImpl;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            DaggerGrowthKitApplicationComponent

final class this._cls0
    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.
{

    private final DaggerGrowthKitApplicationComponent this$0;

    public final void inject(BroadcastReceiver broadcastreceiver)
    {
        broadcastreceiver = (TestingToolsBroadcastReceiver)broadcastreceiver;
        broadcastreceiver.promotionsStore = (PerAccountProvider)providePromotionStoreProvider.get();
        broadcastreceiver.cappedPromotionStore = (MessageStore)provideCappedPromotionStoreProvider.get();
        broadcastreceiver.presentedPromosStore = (PerAccountProvider)providePresentedPromosStoreProvider.get();
        broadcastreceiver.clearcutEventsStore = (PerAccountProvider)provideClearcutEventCounterProvider.get();
        broadcastreceiver.visualElementStore = (PerAccountProvider)provideVisualElementEventCounterProvider.get();
        broadcastreceiver.accountManager = getAccountManagerImpl();
        broadcastreceiver.executorService = (ListeningExecutorService)provideExecutorServiceProvider.get();
        Object obj = DaggerGrowthKitApplicationComponent.this;
        Context context = (Context)((DaggerGrowthKitApplicationComponent) (obj)).provideContextProvider.get();
        com.google.common.util.concurrent.ListenableFuture listenablefuture = ((DaggerGrowthKitApplicationComponent) (obj)).getGrowthKitSharedPrefsListenableFutureOfSharedPreferences();
        ListeningExecutorService listeningexecutorservice = (ListeningExecutorService)((DaggerGrowthKitApplicationComponent) (obj)).provideExecutorServiceProvider.get();
        PerAccountProvider peraccountprovider = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).providePresentedPromosStoreProvider.get();
        PerAccountProvider peraccountprovider1 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).providePromotionStoreProvider.get();
        MessageStore messagestore = (MessageStore)((DaggerGrowthKitApplicationComponent) (obj)).provideCappedPromotionStoreProvider.get();
        PerAccountProvider peraccountprovider2 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).provideMonitoredEventClearcutStoreProvider.get();
        PerAccountProvider peraccountprovider3 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).provideMonitoredEventVisualElementStoreProvider.get();
        GrowthApiClient growthapiclient = (GrowthApiClient)((DaggerGrowthKitApplicationComponent) (obj)).provideGrowthApiClientProvider.get();
        com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl.AccountManagerImpl accountmanagerimpl = ((DaggerGrowthKitApplicationComponent) (obj)).getAccountManagerImpl();
        String s = (String)((DaggerGrowthKitApplicationComponent) (obj)).provideApplicationPackageNameProvider.get();
        Optional optional = (Optional)((DaggerGrowthKitApplicationComponent) (obj)).provideAppVersionCodeProvider.get();
        Optional optional1 = (Optional)((DaggerGrowthKitApplicationComponent) (obj)).provideAppVersionNameProvider.get();
        ListeningExecutorService listeningexecutorservice1 = (ListeningExecutorService)((DaggerGrowthKitApplicationComponent) (obj)).provideExecutorServiceProvider.get();
        Object obj1 = ((DaggerGrowthKitApplicationComponent) (obj)).gcorePseudonymousDaggerModule;
        obj1 = new com.google.android.libraries.gcoreclient.pseudonymous.impl.ovider();
        if (obj1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        obj1 = ((com.google.android.libraries.gcoreclient.pseudonymous.r)obj1).r();
        if (obj1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        obj1 = (GcorePseudonymousId)obj1;
        Object obj2 = ((DaggerGrowthKitApplicationComponent) (obj)).gcorePseudonymousDaggerModule;
        obj2 = new GcorePseudonymousIdApiImpl();
        if (obj2 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        broadcastreceiver.promotionSync = new PromotionSyncImpl(context, listenablefuture, listeningexecutorservice, peraccountprovider, peraccountprovider1, messagestore, peraccountprovider2, peraccountprovider3, growthapiclient, accountmanagerimpl, s, optional, optional1, new PseudonymousIdHelperImpl(listeningexecutorservice1, ((GcorePseudonymousId) (obj1)), (GcorePseudonymousIdApi)obj2, ((DaggerGrowthKitApplicationComponent) (obj)).getBuilder()), ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncSetWriteDebugInfoProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncGaiaProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncZwiebackProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncOverrideCountryProvider, (Clock)((DaggerGrowthKitApplicationComponent) (obj)).provideClockProvider.get(), ImmutableMap.of(com.google.identity.growth.proto.vider, ((DaggerGrowthKitApplicationComponent) (obj)).provideMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.DialogImageSizeResolverProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.terialDialogImageSizeResolverProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingBottomsheetlDialogImageSizeResolverProvider));
        broadcastreceiver.trace = (Trace)bindsNoOpTraceProvider.get();
        broadcastreceiver.blockingExecutor = getGrowthKitBlockingExecutorListeningExecutorService();
        obj = gcoreSecurityDaggerModule;
        obj = new GcoreProviderInstallerImpl();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            broadcastreceiver.gcoreProviderInstaller = (GcoreProviderInstaller)obj;
            broadcastreceiver.context = (Context)provideContextProvider.get();
            broadcastreceiver.sharedPrefsFuture = getGrowthKitSharedPrefsListenableFutureOfSharedPreferences();
            obj = DaggerGrowthKitApplicationComponent.this;
            broadcastreceiver.uiImageDownloadManager = ImmutableMap.of(com.google.identity.growth.proto.ownloadManager, ((DaggerGrowthKitApplicationComponent) (obj)).provideMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.DialogImageSizeResolverProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.terialDialogImageSizeResolverProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingBottomsheetlDialogImageSizeResolverProvider);
            broadcastreceiver.testingEnabled = provideTestFeatureTestFlagProvider;
            broadcastreceiver.syncGaiaAccounts = provideSyncGaiaProvider;
            broadcastreceiver.syncZwiebackAccounts = provideSyncZwiebackProvider;
            return;
        }
    }

    nt()
    {
        this$0 = DaggerGrowthKitApplicationComponent.this;
        super();
    }
}
