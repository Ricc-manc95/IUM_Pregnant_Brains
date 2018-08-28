// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.Context;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthUtil;
import com.google.android.libraries.gcoreclient.auth.impl.GcoreAuthDaggerModule;
import com.google.android.libraries.gcoreclient.auth.impl.GcoreAuthDaggerModule_GetGcoreGoogleAuthUtilFactory;
import com.google.android.libraries.gcoreclient.auth.impl.GcoreGoogleAuthUtilImpl;
import com.google.android.libraries.gcoreclient.clearcut.impl.GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory;
import com.google.android.libraries.gcoreclient.common.api.impl.GcoreCommonApiDaggerModule;
import com.google.android.libraries.gcoreclient.common.api.impl.GcoreCommonApiDaggerModule_GetGcoreGoogleApiClientBuilderFactory;
import com.google.android.libraries.gcoreclient.phenotype.impl.GcorePhenotypeDaggerModule_GetPhenotypeApiFactory;
import com.google.android.libraries.gcoreclient.phenotype.impl.GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory;
import com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousDaggerModule;
import com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousDaggerModule_GetPseudonymousIdApiFactory;
import com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousDaggerModule_ProvidePseudonymousIdBuilderFactory;
import com.google.android.libraries.gcoreclient.security.impl.GcoreSecurityDaggerModule;
import com.google.android.libraries.gcoreclient.security.impl.GcoreSecurityDaggerModule_GetGcoreProviderInstallerFactory;
import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import com.google.android.libraries.internal.growth.growthkit.events.impl.EventsModule_ProvideEventManagerFactory;
import com.google.android.libraries.internal.growth.growthkit.events.impl.GrowthKitEventManagerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideAppVersionCodeFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideAppVersionNameFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideApplicationPackageNameFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideBlockingExecutorFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalCommonModule_ProvideGrowthKitSharedPrefsFactory;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitInternalProdModule_ProvideAppCertificateFingerprintFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl.AccountManagerImpl;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl.AccountManagerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl.ClearcutLoggerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl.ClearcutModule_ProvideGcoreClearcutLoggerFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.common.NoOpTrace_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.common.impl.AndroidClock_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvideAppVersionCodeFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvideMendelPackageFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvideMendelPerAppPackageFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvidePhenotypeFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvidePhenotypeSharedPreferencesFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProvideSharedPrefsFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.DaggerExperimentsModule_ProviderPhenotypeBroadcastReceiverInjectorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideClearStorageAgeFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideClearStoragePeriodFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideFlushCountersIncrementCountFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideGeneralEnableFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvidePhenotypeFlagBuilderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvidePromotionDontShowWithAccessibilityFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSaveOnlyMonitoredEventsFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncGaiaFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncOverrideCountryFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncPeriodFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncRetryMaxDelayMsFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncRetryMinDelayMsFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncRetryPolicyFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncSetWriteDebugInfoFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncSyncAfterPromoShownFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncSyncOnStartupAtMostEveryFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncSyncOnStartupFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncUrlFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideSyncZwiebackFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentFlagsModule_ProvideTestFeatureTestFlagFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.ExperimentsStartupListener_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeBroadcastReceiver;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeFlagUpdater_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeManagerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobSchedulerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobService;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.JobsCommonModule_ProvideFirebaseJobDispatcherFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.JobsModule_ProviderGooglePlayDriverFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.JobsStartupListener_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic.BasicImageCacheModule_ProvideFileCacheFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic.BasicImageCacheModule_ProvideURLFactoryFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic.BasicImageCache_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.AppStateTargetingTermPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.BatteryLevelPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.ClearcutTriggeringRulePredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.CompositeTriggeringConditionsPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.DisplayWithoutNewSyncPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.EventCountTargetingTermPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.InstalledAppsPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.LanguagePredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.NetworkPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.TargetingClausePredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.TargetingRulePredicateImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.TimeConstraintPredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl.VisualElementTriggeringRulePredicate_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl.PromotionsManagerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl.PromotionsModule_ProvidePromotionsManagerFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl.TriggeringEventProcessor_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl.PseudonymousIdHelperImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl.PseudonymousModule_ProvidePhenotypeFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl.GrowthApiClientImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl.RpcModule_ProvideGrowthServerChannelFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl.okhttp.RpcOkHttpChannelModule_ProvideGrowthServerChannelFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.GrowthDbHelper_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.SqliteClearcutEventsStoreFactory_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.SqliteVisualElementEventsStoreFactory_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCleanupJob_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvideCappedPromotionStoreProviderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvideClearcutEventCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvideMonitoredEventClearcutStoreProviderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvideMonitoredEventVisualElementStoreProviderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvidePresentedPromosStoreProviderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvidePromotionStoreProviderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageCommonModule_ProvideVisualElementEventCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageModule_ProvideInternalDbExecutorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageModule_ProviderCTestingToolsBroadcastReceiverInjectorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.StorageUtilitiesImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.TestingToolsBroadcastReceiver;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideGrowthkitStartedCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideImpressionsCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideJobCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideLoggingCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideMatricFactoryFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvidePromotionShownCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideStreamzIncrementsFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideSyncsCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideTargetingAppliedCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzCommonModule_ProvideTriggerAppliedCounterFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzProdModule_ProvideStreamzLoggerFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.impl.OneoffSyncJob_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.impl.PeriodicSyncJob_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.impl.PromotionSyncImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.PromoUiRendererImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.TargetElementFinder_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.DialogRenderer_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.DialogsModule_ProvidePromoUiDialogFragmentInjectorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.DialogsModule_ProvideRatingDefaultDialogBuilderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.DialogsModule_ProvideRatingMaterialDialogBuilderFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.MaterialDialogImageDownloadManager_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.PromoUiDialogFragment;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightFragment;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightFragmentRenderer_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightViewFinderFactory_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.TapTargetModule_ProvideFeatureHighlightFragmentInjectorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipFragment;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipFragmentRenderer_Factory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipModule_ProvideTooltipFragmentInjectorFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipViewFinder_Factory;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacksManager;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartup;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.impl.GrowthKitCallbacksManagerImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.impl.GrowthKitStartupImpl_Factory;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.impl.LifecycleModule_ProvideGrowthKitPromosCallbackFactory;
import com.google.common.base.Absent;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.MapProviderFactory;
import dagger.internal.SetFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            GrowthKitApplicationModule_ProvideContextFactory, GrowthKitApplicationModule_ProvideExecutorServiceFactory, GrowthKitApplicationModule_ProvideGcoreContextFactory, GrowthKitApplicationModule_ProvideApiKeyFactory

public final class DaggerGrowthKitApplicationComponent
    implements GrowthKitComponent
{

    private static final Provider ABSENT_GUAVA_OPTIONAL_PROVIDER;
    private AccountManagerImpl_Factory accountManagerImplProvider;
    private BasicImageCache_Factory basicImageCacheProvider;
    private BatteryLevelPredicate_Factory batteryLevelPredicateProvider;
    public Provider bindsNoOpTraceProvider;
    private ClearcutLoggerImpl_Factory clearcutLoggerImplProvider;
    private CompositeTriggeringConditionsPredicate_Factory compositeTriggeringConditionsPredicateProvider;
    private ExperimentsStartupListener_Factory experimentsStartupListenerProvider;
    private FeatureHighlightFragmentRenderer_Factory featureHighlightFragmentRendererProvider;
    private Provider featureHighlightFragmentSubcomponentBuilderProvider;
    private FeatureHighlightViewFinderFactory_Factory featureHighlightViewFinderFactoryProvider;
    private GcoreAuthDaggerModule gcoreAuthDaggerModule;
    private GcoreCommonApiDaggerModule gcoreCommonApiDaggerModule;
    public GcorePseudonymousDaggerModule gcorePseudonymousDaggerModule;
    public GcoreSecurityDaggerModule gcoreSecurityDaggerModule;
    private GcoreCommonApiDaggerModule_GetGcoreGoogleApiClientBuilderFactory getGcoreGoogleApiClientBuilderProvider;
    private GcoreAuthDaggerModule_GetGcoreGoogleAuthUtilFactory getGcoreGoogleAuthUtilProvider;
    private GcoreSecurityDaggerModule_GetGcoreProviderInstallerFactory getGcoreProviderInstallerProvider;
    private GcorePseudonymousDaggerModule_GetPseudonymousIdApiFactory getPseudonymousIdApiProvider;
    private GrowthApiClientImpl_Factory growthApiClientImplProvider;
    private Provider growthDbHelperProvider;
    private Provider growthKitCallbacksManagerImplProvider;
    private GrowthKitEventManagerImpl_Factory growthKitEventManagerImplProvider;
    private Provider growthKitJobSchedulerImplProvider;
    private Provider growthKitJobServiceSubcomponentBuilderProvider;
    private Provider growthKitOptionalBlockingExecutorOptionalOfListeningExecutorServiceProvider;
    private Provider growthKitStartupImplProvider;
    private InstalledAppsPredicate_Factory installedAppsPredicateProvider;
    private JobsStartupListener_Factory jobsStartupListenerProvider;
    private LanguagePredicate_Factory languagePredicateProvider;
    public Provider mapOfStringAndProviderOfGrowthKitJobOfProvider;
    private Provider mapOfUiTypeAndProviderOfImageDownloadManagerProvider;
    private Provider mapOfUiTypeAndProviderOfRendererProvider;
    private MaterialDialogImageDownloadManager_Factory materialDialogImageDownloadManagerProvider;
    private NetworkPredicate_Factory networkPredicateProvider;
    private OneoffSyncJob_Factory oneoffSyncJobProvider;
    private Provider optionalOfIntentBuilderProvider;
    private Provider optionalOfVisualElementViewFinderProvider;
    private PeriodicSyncJob_Factory periodicSyncJobProvider;
    private Provider phenotypeBroadcastReceiverSubcomponentBuilderProvider;
    private PhenotypeFlagUpdater_Factory phenotypeFlagUpdaterProvider;
    private PhenotypeManagerImpl_Factory phenotypeManagerImplProvider;
    private Provider promoUiDialogFragmentSubcomponentBuilderProvider;
    private PromoUiRendererImpl_Factory promoUiRendererImplProvider;
    private PromotionSyncImpl_Factory promotionSyncImplProvider;
    private Provider promotionsManagerImplProvider;
    private Provider provideApiKeyProvider;
    private GrowthKitInternalProdModule_ProvideAppCertificateFingerprintFactory provideAppCertificateFingerprintProvider;
    public Provider provideAppVersionCodeProvider;
    public Provider provideAppVersionCodeProvider2;
    public Provider provideAppVersionNameProvider;
    public Provider provideApplicationPackageNameProvider;
    private GrowthKitInternalCommonModule_ProvideBlockingExecutorFactory provideBlockingExecutorProvider;
    public Provider provideCappedPromotionStoreProvider;
    private ExperimentFlagsModule_ProvideClearStorageAgeFlagFactory provideClearStorageAgeFlagProvider;
    private ExperimentFlagsModule_ProvideClearStoragePeriodFlagFactory provideClearStoragePeriodFlagProvider;
    public Provider provideClearcutEventCounterProvider;
    public Provider provideClearcutLoggerProvider;
    public Provider provideClockProvider;
    public Provider provideContextProvider;
    private Provider provideEventManagerProvider;
    public Provider provideExecutorServiceProvider;
    private TapTargetModule_ProvideFeatureHighlightFragmentInjectorFactory provideFeatureHighlightFragmentInjectorProvider;
    private BasicImageCacheModule_ProvideFileCacheFactory provideFileCacheProvider;
    private JobsCommonModule_ProvideFirebaseJobDispatcherFactory provideFirebaseJobDispatcherProvider;
    private ExperimentFlagsModule_ProvideFlushCountersIncrementCountFlagFactory provideFlushCountersIncrementCountFlagProvider;
    private Provider provideGcoreClearcutLoggerProvider;
    private Provider provideGcoreContextProvider;
    public ExperimentFlagsModule_ProvideGeneralEnableFlagFactory provideGeneralEnableFlagProvider;
    public Provider provideGrowthApiClientProvider;
    private LifecycleModule_ProvideGrowthKitPromosCallbackFactory provideGrowthKitPromosCallbackProvider;
    private GrowthKitInternalCommonModule_ProvideGrowthKitSharedPrefsFactory provideGrowthKitSharedPrefsProvider;
    private RpcOkHttpChannelModule_ProvideGrowthServerChannelFactory provideGrowthServerChannelProvider;
    private RpcModule_ProvideGrowthServerChannelFactory provideGrowthServerChannelProvider2;
    private Provider provideGrowthkitStartedCounterProvider;
    public Provider provideImageCacheProvider;
    private Provider provideImpressionsCounterProvider;
    private Provider provideInternalDbExecutorProvider;
    public Provider provideJobCounterProvider;
    private Provider provideLoggingCounterProvider;
    public Provider provideMaterialDialogImageSizeResolverProvider;
    private Provider provideMatricFactoryProvider;
    private Provider provideMendelPackageProvider;
    public Provider provideMendelPerAppPackageProvider;
    public Provider provideMonitoredEventClearcutStoreProvider;
    public Provider provideMonitoredEventVisualElementStoreProvider;
    private Provider providePhenotypeFlagBuilderProvider;
    private PseudonymousModule_ProvidePhenotypeFactory providePhenotypeProvider;
    private DaggerExperimentsModule_ProvidePhenotypeFactory providePhenotypeProvider2;
    public Provider providePhenotypeSharedPreferencesProvider;
    public Provider providePresentedPromosStoreProvider;
    private Provider providePromoUiDialogFragmentInjectorProvider;
    private ExperimentFlagsModule_ProvidePromotionDontShowWithAccessibilityFactory providePromotionDontShowWithAccessibilityProvider;
    private Provider providePromotionShownCounterProvider;
    public Provider providePromotionStoreProvider;
    private Provider providePromotionsManagerProvider;
    private GcorePseudonymousDaggerModule_ProvidePseudonymousIdBuilderFactory providePseudonymousIdBuilderProvider;
    public Provider provideRatingBottomSheetDialogBuilderProvider;
    private Provider provideRatingBottomSheetDialogRendererProvider;
    public Provider provideRatingBottomsheetlDialogImageSizeResolverProvider;
    public Provider provideRatingDefaultDialogBuilderProvider;
    private Provider provideRatingDefaultDialogRendererProvider;
    public Provider provideRatingMaterialDialogBuilderProvider;
    public Provider provideRatingMaterialDialogImageSizeResolverProvider;
    private Provider provideRatingMaterialDialogRendererProvider;
    private ExperimentFlagsModule_ProvideSaveOnlyMonitoredEventsFactory provideSaveOnlyMonitoredEventsProvider;
    private Provider provideSharedPrefsProvider;
    public Provider provideStreamzIncrementsProvider;
    private Provider provideStreamzLoggerProvider;
    public ExperimentFlagsModule_ProvideSyncGaiaFactory provideSyncGaiaProvider;
    public ExperimentFlagsModule_ProvideSyncOverrideCountryFactory provideSyncOverrideCountryProvider;
    private ExperimentFlagsModule_ProvideSyncPeriodFlagFactory provideSyncPeriodFlagProvider;
    private ExperimentFlagsModule_ProvideSyncRetryMaxDelayMsFactory provideSyncRetryMaxDelayMsProvider;
    private ExperimentFlagsModule_ProvideSyncRetryMinDelayMsFactory provideSyncRetryMinDelayMsProvider;
    private ExperimentFlagsModule_ProvideSyncRetryPolicyFactory provideSyncRetryPolicyProvider;
    public ExperimentFlagsModule_ProvideSyncSetWriteDebugInfoFactory provideSyncSetWriteDebugInfoProvider;
    private ExperimentFlagsModule_ProvideSyncSyncAfterPromoShownFactory provideSyncSyncAfterPromoShownProvider;
    private ExperimentFlagsModule_ProvideSyncSyncOnStartupAtMostEveryFactory provideSyncSyncOnStartupAtMostEveryProvider;
    private ExperimentFlagsModule_ProvideSyncSyncOnStartupFactory provideSyncSyncOnStartupProvider;
    private ExperimentFlagsModule_ProvideSyncUrlFlagFactory provideSyncUrlFlagProvider;
    public ExperimentFlagsModule_ProvideSyncZwiebackFactory provideSyncZwiebackProvider;
    private Provider provideSyncsCounterProvider;
    private Provider provideTargetingAppliedCounterProvider;
    public ExperimentFlagsModule_ProvideTestFeatureTestFlagFactory provideTestFeatureTestFlagProvider;
    private TooltipModule_ProvideTooltipFragmentInjectorFactory provideTooltipFragmentInjectorProvider;
    private Provider provideTriggerAppliedCounterProvider;
    public Provider provideVisualElementEventCounterProvider;
    private StorageModule_ProviderCTestingToolsBroadcastReceiverInjectorFactory providerCTestingToolsBroadcastReceiverInjectorProvider;
    private JobsModule_ProviderGooglePlayDriverFactory providerGooglePlayDriverProvider;
    private DaggerExperimentsModule_ProviderPhenotypeBroadcastReceiverInjectorFactory providerPhenotypeBroadcastReceiverInjectorProvider;
    private JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory providerSGrowthKitJobServiceInejctorProvider;
    private PseudonymousIdHelperImpl_Factory pseudonymousIdHelperImplProvider;
    private Provider setOfGrowthKitStartupListenerProvider;
    private Provider setOfPartialTriggeringConditionsPredicateProvider;
    private Provider setOfTriggeringRulePredicateProvider;
    private SqliteClearcutEventsStoreFactory_Factory sqliteClearcutEventsStoreFactoryProvider;
    private SqliteVisualElementEventsStoreFactory_Factory sqliteVisualElementEventsStoreFactoryProvider;
    private StorageCleanupJob_Factory storageCleanupJobProvider;
    private StorageUtilitiesImpl_Factory storageUtilitiesImplProvider;
    public TargetElementFinder_Factory targetElementFinderProvider;
    private TargetingClausePredicate_Factory targetingClausePredicateProvider;
    private TargetingRulePredicateImpl_Factory targetingRulePredicateImplProvider;
    private Provider testingToolsBroadcastReceiverSubcomponentBuilderProvider;
    private TimeConstraintPredicate_Factory timeConstraintPredicateProvider;
    private TooltipFragmentRenderer_Factory tooltipFragmentRendererProvider;
    private Provider tooltipFragmentSubcomponentBuilderProvider;
    private TooltipViewFinder_Factory tooltipViewFinderProvider;
    private TriggeringEventProcessor_Factory triggeringEventProcessorProvider;
    public UserActionUtil_Factory userActionUtilProvider;

    DaggerGrowthKitApplicationComponent(Builder builder1)
    {
        provideInternalDbExecutorProvider = DoubleCheck.provider(StorageModule_ProvideInternalDbExecutorFactory.INSTANCE);
        provideContextProvider = DoubleCheck.provider(new GrowthKitApplicationModule_ProvideContextFactory(builder1.growthKitApplicationModule));
        growthDbHelperProvider = DoubleCheck.provider(new GrowthDbHelper_Factory(provideContextProvider, provideInternalDbExecutorProvider));
        sqliteClearcutEventsStoreFactoryProvider = new SqliteClearcutEventsStoreFactory_Factory(provideInternalDbExecutorProvider, growthDbHelperProvider);
        provideClearcutEventCounterProvider = DoubleCheck.provider(new StorageCommonModule_ProvideClearcutEventCounterFactory(sqliteClearcutEventsStoreFactoryProvider));
        provideExecutorServiceProvider = DoubleCheck.provider(new GrowthKitApplicationModule_ProvideExecutorServiceFactory(builder1.growthKitApplicationModule));
        provideClockProvider = DoubleCheck.provider(AndroidClock_Factory.INSTANCE);
        providePromotionStoreProvider = DoubleCheck.provider(new StorageCommonModule_ProvidePromotionStoreProviderFactory(provideInternalDbExecutorProvider, growthDbHelperProvider));
        provideCappedPromotionStoreProvider = DoubleCheck.provider(new StorageCommonModule_ProvideCappedPromotionStoreProviderFactory(provideInternalDbExecutorProvider, growthDbHelperProvider));
        sqliteVisualElementEventsStoreFactoryProvider = new SqliteVisualElementEventsStoreFactory_Factory(provideInternalDbExecutorProvider, growthDbHelperProvider, provideClockProvider);
        provideVisualElementEventCounterProvider = DoubleCheck.provider(new StorageCommonModule_ProvideVisualElementEventCounterFactory(sqliteVisualElementEventsStoreFactoryProvider));
        providePresentedPromosStoreProvider = DoubleCheck.provider(new StorageCommonModule_ProvidePresentedPromosStoreProviderFactory(provideInternalDbExecutorProvider, growthDbHelperProvider));
        batteryLevelPredicateProvider = new BatteryLevelPredicate_Factory(provideContextProvider);
        installedAppsPredicateProvider = new InstalledAppsPredicate_Factory(provideContextProvider);
        networkPredicateProvider = new NetworkPredicate_Factory(provideContextProvider);
        provideGrowthKitSharedPrefsProvider = new GrowthKitInternalCommonModule_ProvideGrowthKitSharedPrefsFactory(provideContextProvider, provideExecutorServiceProvider);
        languagePredicateProvider = new LanguagePredicate_Factory(provideContextProvider, provideGrowthKitSharedPrefsProvider);
        timeConstraintPredicateProvider = new TimeConstraintPredicate_Factory(provideClockProvider);
        Object obj = SetFactory.builder(6, 0);
        Object obj1 = DisplayWithoutNewSyncPredicate_Factory.INSTANCE;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = batteryLevelPredicateProvider;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = installedAppsPredicateProvider;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = networkPredicateProvider;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = languagePredicateProvider;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = timeConstraintPredicateProvider;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        setOfPartialTriggeringConditionsPredicateProvider = new SetFactory(((dagger.internal.SetFactory.Builder) (obj)).individualProviders, ((dagger.internal.SetFactory.Builder) (obj)).collectionProviders);
        provideGcoreClearcutLoggerProvider = DoubleCheck.provider(new ClearcutModule_ProvideGcoreClearcutLoggerFactory(provideContextProvider, GcoreClearcutDaggerModule_GetGcoreClearcutLoggerApiFactoryFactory.INSTANCE));
        provideApplicationPackageNameProvider = DoubleCheck.provider(new GrowthKitInternalCommonModule_ProvideApplicationPackageNameFactory(provideContextProvider));
        provideSharedPrefsProvider = DoubleCheck.provider(DaggerExperimentsModule_ProvideSharedPrefsFactory.INSTANCE);
        providePhenotypeFlagBuilderProvider = DoubleCheck.provider(new ExperimentFlagsModule_ProvidePhenotypeFlagBuilderFactory(provideContextProvider, provideSharedPrefsProvider));
        provideFlushCountersIncrementCountFlagProvider = new ExperimentFlagsModule_ProvideFlushCountersIncrementCountFlagFactory(providePhenotypeFlagBuilderProvider);
        provideStreamzLoggerProvider = DoubleCheck.provider(new StreamzProdModule_ProvideStreamzLoggerFactory(provideGcoreClearcutLoggerProvider));
        provideMatricFactoryProvider = DoubleCheck.provider(StreamzCommonModule_ProvideMatricFactoryFactory.INSTANCE);
        provideStreamzIncrementsProvider = DoubleCheck.provider(new StreamzCommonModule_ProvideStreamzIncrementsFactory(provideFlushCountersIncrementCountFlagProvider, provideStreamzLoggerProvider, provideMatricFactoryProvider));
        provideImpressionsCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideImpressionsCounterFactory.INSTANCE);
        clearcutLoggerImplProvider = new ClearcutLoggerImpl_Factory(provideGcoreClearcutLoggerProvider, provideApplicationPackageNameProvider, provideContextProvider, provideStreamzIncrementsProvider, provideImpressionsCounterProvider);
        provideClearcutLoggerProvider = DoubleCheck.provider(clearcutLoggerImplProvider);
        compositeTriggeringConditionsPredicateProvider = new CompositeTriggeringConditionsPredicate_Factory(setOfPartialTriggeringConditionsPredicateProvider, provideClearcutLoggerProvider);
        obj = SetFactory.builder(2, 0);
        obj1 = ClearcutTriggeringRulePredicate_Factory.INSTANCE;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        obj1 = VisualElementTriggeringRulePredicate_Factory.INSTANCE;
        ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
        setOfTriggeringRulePredicateProvider = new SetFactory(((dagger.internal.SetFactory.Builder) (obj)).individualProviders, ((dagger.internal.SetFactory.Builder) (obj)).collectionProviders);
        targetingClausePredicateProvider = new TargetingClausePredicate_Factory(EventCountTargetingTermPredicate_Factory.INSTANCE, AppStateTargetingTermPredicate_Factory.INSTANCE);
        targetingRulePredicateImplProvider = new TargetingRulePredicateImpl_Factory(targetingClausePredicateProvider, provideClearcutLoggerProvider);
        growthKitOptionalBlockingExecutorOptionalOfListeningExecutorServiceProvider = ABSENT_GUAVA_OPTIONAL_PROVIDER;
        provideBlockingExecutorProvider = new GrowthKitInternalCommonModule_ProvideBlockingExecutorFactory(growthKitOptionalBlockingExecutorOptionalOfListeningExecutorServiceProvider, provideExecutorServiceProvider);
        provideGcoreContextProvider = DoubleCheck.provider(new GrowthKitApplicationModule_ProvideGcoreContextFactory(builder1.growthKitApplicationModule));
        getGcoreGoogleAuthUtilProvider = new GcoreAuthDaggerModule_GetGcoreGoogleAuthUtilFactory(builder1.gcoreAuthDaggerModule, provideGcoreContextProvider);
        provideSyncGaiaProvider = new ExperimentFlagsModule_ProvideSyncGaiaFactory(providePhenotypeFlagBuilderProvider);
        accountManagerImplProvider = new AccountManagerImpl_Factory(provideContextProvider, provideBlockingExecutorProvider, getGcoreGoogleAuthUtilProvider, provideSyncGaiaProvider);
        provideRatingMaterialDialogRendererProvider = DoubleCheck.provider(DialogRenderer_Factory.INSTANCE);
        provideRatingDefaultDialogRendererProvider = DoubleCheck.provider(DialogRenderer_Factory.INSTANCE);
        provideRatingBottomSheetDialogRendererProvider = DoubleCheck.provider(DialogRenderer_Factory.INSTANCE);
        optionalOfVisualElementViewFinderProvider = ABSENT_GUAVA_OPTIONAL_PROVIDER;
        targetElementFinderProvider = new TargetElementFinder_Factory(optionalOfVisualElementViewFinderProvider);
        featureHighlightViewFinderFactoryProvider = new FeatureHighlightViewFinderFactory_Factory(targetElementFinderProvider);
        featureHighlightFragmentRendererProvider = new FeatureHighlightFragmentRenderer_Factory(featureHighlightViewFinderFactoryProvider);
        tooltipViewFinderProvider = new TooltipViewFinder_Factory(targetElementFinderProvider);
        tooltipFragmentRendererProvider = new TooltipFragmentRenderer_Factory(tooltipViewFinderProvider);
        obj = new dagger.internal.MapProviderFactory.Builder(5);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG;
        Object obj2 = provideRatingMaterialDialogRendererProvider;
        LinkedHashMap linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG;
        obj2 = provideRatingDefaultDialogRendererProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET;
        obj2 = provideRatingBottomSheetDialogRendererProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_TAP_TARGET;
        obj2 = featureHighlightFragmentRendererProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_TOOLTIP;
        obj2 = tooltipFragmentRendererProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        mapOfUiTypeAndProviderOfRendererProvider = new MapProviderFactory(((dagger.internal.MapProviderFactory.Builder) (obj)).map);
        promoUiRendererImplProvider = new PromoUiRendererImpl_Factory(mapOfUiTypeAndProviderOfRendererProvider);
        provideMonitoredEventClearcutStoreProvider = DoubleCheck.provider(new StorageCommonModule_ProvideMonitoredEventClearcutStoreProviderFactory(provideInternalDbExecutorProvider, growthDbHelperProvider));
        provideMonitoredEventVisualElementStoreProvider = DoubleCheck.provider(new StorageCommonModule_ProvideMonitoredEventVisualElementStoreProviderFactory(provideInternalDbExecutorProvider, growthDbHelperProvider));
        provideAppCertificateFingerprintProvider = new GrowthKitInternalProdModule_ProvideAppCertificateFingerprintFactory(provideContextProvider, provideApplicationPackageNameProvider);
        provideApiKeyProvider = DoubleCheck.provider(new GrowthKitApplicationModule_ProvideApiKeyFactory(builder1.growthKitApplicationModule));
        provideSyncUrlFlagProvider = new ExperimentFlagsModule_ProvideSyncUrlFlagFactory(providePhenotypeFlagBuilderProvider);
        provideGrowthServerChannelProvider = new RpcOkHttpChannelModule_ProvideGrowthServerChannelFactory(provideSyncUrlFlagProvider);
        provideGrowthServerChannelProvider2 = new RpcModule_ProvideGrowthServerChannelFactory(builder1.rpcModule, provideGrowthServerChannelProvider);
        provideSyncsCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideSyncsCounterFactory.INSTANCE);
        growthApiClientImplProvider = new GrowthApiClientImpl_Factory(provideExecutorServiceProvider, provideAppCertificateFingerprintProvider, provideApplicationPackageNameProvider, provideApiKeyProvider, provideGrowthServerChannelProvider2, accountManagerImplProvider, provideSyncsCounterProvider, provideStreamzIncrementsProvider);
        provideGrowthApiClientProvider = DoubleCheck.provider(growthApiClientImplProvider);
        provideAppVersionCodeProvider = DoubleCheck.provider(new GrowthKitInternalCommonModule_ProvideAppVersionCodeFactory(provideContextProvider));
        provideAppVersionNameProvider = DoubleCheck.provider(new GrowthKitInternalCommonModule_ProvideAppVersionNameFactory(provideContextProvider));
        providePseudonymousIdBuilderProvider = new GcorePseudonymousDaggerModule_ProvidePseudonymousIdBuilderFactory(builder1.gcorePseudonymousDaggerModule);
        providePhenotypeProvider = new PseudonymousModule_ProvidePhenotypeFactory(providePseudonymousIdBuilderProvider);
        getPseudonymousIdApiProvider = new GcorePseudonymousDaggerModule_GetPseudonymousIdApiFactory(builder1.gcorePseudonymousDaggerModule);
        getGcoreGoogleApiClientBuilderProvider = new GcoreCommonApiDaggerModule_GetGcoreGoogleApiClientBuilderFactory(builder1.gcoreCommonApiDaggerModule, provideGcoreContextProvider);
        pseudonymousIdHelperImplProvider = new PseudonymousIdHelperImpl_Factory(provideExecutorServiceProvider, providePhenotypeProvider, getPseudonymousIdApiProvider, getGcoreGoogleApiClientBuilderProvider);
        provideSyncSetWriteDebugInfoProvider = new ExperimentFlagsModule_ProvideSyncSetWriteDebugInfoFactory(providePhenotypeFlagBuilderProvider);
        provideSyncZwiebackProvider = new ExperimentFlagsModule_ProvideSyncZwiebackFactory(providePhenotypeFlagBuilderProvider);
        provideSyncOverrideCountryProvider = new ExperimentFlagsModule_ProvideSyncOverrideCountryFactory(providePhenotypeFlagBuilderProvider);
        provideFileCacheProvider = new BasicImageCacheModule_ProvideFileCacheFactory(provideContextProvider);
        basicImageCacheProvider = new BasicImageCache_Factory(provideFileCacheProvider, BasicImageCacheModule_ProvideURLFactoryFactory.INSTANCE, provideExecutorServiceProvider);
        provideImageCacheProvider = DoubleCheck.provider(basicImageCacheProvider);
        materialDialogImageDownloadManagerProvider = new MaterialDialogImageDownloadManager_Factory(provideContextProvider, provideImageCacheProvider);
        provideMaterialDialogImageSizeResolverProvider = DoubleCheck.provider(materialDialogImageDownloadManagerProvider);
        provideRatingMaterialDialogImageSizeResolverProvider = DoubleCheck.provider(materialDialogImageDownloadManagerProvider);
        provideRatingBottomsheetlDialogImageSizeResolverProvider = DoubleCheck.provider(materialDialogImageDownloadManagerProvider);
        obj = new dagger.internal.MapProviderFactory.Builder(3);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG;
        obj2 = provideMaterialDialogImageSizeResolverProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG;
        obj2 = provideRatingMaterialDialogImageSizeResolverProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET;
        obj2 = provideRatingBottomsheetlDialogImageSizeResolverProvider;
        linkedhashmap = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if (obj1 == null)
        {
            throw new NullPointerException("key");
        }
        if (obj2 == null)
        {
            throw new NullPointerException("provider");
        }
        linkedhashmap.put(obj1, (Provider)obj2);
        mapOfUiTypeAndProviderOfImageDownloadManagerProvider = new MapProviderFactory(((dagger.internal.MapProviderFactory.Builder) (obj)).map);
        promotionSyncImplProvider = new PromotionSyncImpl_Factory(provideContextProvider, provideGrowthKitSharedPrefsProvider, provideExecutorServiceProvider, providePresentedPromosStoreProvider, providePromotionStoreProvider, provideCappedPromotionStoreProvider, provideMonitoredEventClearcutStoreProvider, provideMonitoredEventVisualElementStoreProvider, provideGrowthApiClientProvider, accountManagerImplProvider, provideApplicationPackageNameProvider, provideAppVersionCodeProvider, provideAppVersionNameProvider, pseudonymousIdHelperImplProvider, provideSyncSetWriteDebugInfoProvider, provideSyncGaiaProvider, provideSyncZwiebackProvider, provideSyncOverrideCountryProvider, provideClockProvider, mapOfUiTypeAndProviderOfImageDownloadManagerProvider);
        growthKitCallbacksManagerImplProvider = DoubleCheck.provider(GrowthKitCallbacksManagerImpl_Factory.INSTANCE);
        provideGrowthKitPromosCallbackProvider = new LifecycleModule_ProvideGrowthKitPromosCallbackFactory(growthKitCallbacksManagerImplProvider);
        providePromotionDontShowWithAccessibilityProvider = new ExperimentFlagsModule_ProvidePromotionDontShowWithAccessibilityFactory(providePhenotypeFlagBuilderProvider);
        provideSyncSyncAfterPromoShownProvider = new ExperimentFlagsModule_ProvideSyncSyncAfterPromoShownFactory(providePhenotypeFlagBuilderProvider);
        provideTriggerAppliedCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideTriggerAppliedCounterFactory.INSTANCE);
        provideTargetingAppliedCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideTargetingAppliedCounterFactory.INSTANCE);
        providePromotionShownCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvidePromotionShownCounterFactory.INSTANCE);
        optionalOfIntentBuilderProvider = ABSENT_GUAVA_OPTIONAL_PROVIDER;
        userActionUtilProvider = new UserActionUtil_Factory(provideContextProvider, providePresentedPromosStoreProvider, provideClearcutLoggerProvider, optionalOfIntentBuilderProvider);
        bindsNoOpTraceProvider = DoubleCheck.provider(NoOpTrace_Factory.INSTANCE);
        triggeringEventProcessorProvider = new TriggeringEventProcessor_Factory(provideContextProvider, provideClockProvider, providePromotionStoreProvider, provideCappedPromotionStoreProvider, provideClearcutEventCounterProvider, provideVisualElementEventCounterProvider, providePresentedPromosStoreProvider, compositeTriggeringConditionsPredicateProvider, setOfTriggeringRulePredicateProvider, targetingRulePredicateImplProvider, accountManagerImplProvider, promoUiRendererImplProvider, promotionSyncImplProvider, provideGrowthKitPromosCallbackProvider, provideClearcutLoggerProvider, provideExecutorServiceProvider, providePromotionDontShowWithAccessibilityProvider, provideSyncSyncAfterPromoShownProvider, provideTriggerAppliedCounterProvider, provideTargetingAppliedCounterProvider, providePromotionShownCounterProvider, provideStreamzIncrementsProvider, provideApplicationPackageNameProvider, userActionUtilProvider, bindsNoOpTraceProvider);
        promotionsManagerImplProvider = DoubleCheck.provider(new PromotionsManagerImpl_Factory(provideExecutorServiceProvider, triggeringEventProcessorProvider, provideClockProvider, bindsNoOpTraceProvider));
        providePromotionsManagerProvider = DoubleCheck.provider(new PromotionsModule_ProvidePromotionsManagerFactory(builder1.promotionsModule, promotionsManagerImplProvider));
        provideGeneralEnableFlagProvider = new ExperimentFlagsModule_ProvideGeneralEnableFlagFactory(providePhenotypeFlagBuilderProvider);
        provideSaveOnlyMonitoredEventsProvider = new ExperimentFlagsModule_ProvideSaveOnlyMonitoredEventsFactory(providePhenotypeFlagBuilderProvider);
        provideLoggingCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideLoggingCounterFactory.INSTANCE);
        growthKitEventManagerImplProvider = new GrowthKitEventManagerImpl_Factory(provideClearcutEventCounterProvider, providePromotionsManagerProvider, provideApplicationPackageNameProvider, provideGeneralEnableFlagProvider, provideSaveOnlyMonitoredEventsProvider, provideMonitoredEventClearcutStoreProvider, provideMonitoredEventVisualElementStoreProvider, provideVisualElementEventCounterProvider, provideExecutorServiceProvider, provideLoggingCounterProvider, provideStreamzIncrementsProvider, bindsNoOpTraceProvider);
        provideEventManagerProvider = DoubleCheck.provider(new EventsModule_ProvideEventManagerFactory(growthKitEventManagerImplProvider));
        providerGooglePlayDriverProvider = new JobsModule_ProviderGooglePlayDriverFactory(provideContextProvider);
        provideFirebaseJobDispatcherProvider = new JobsCommonModule_ProvideFirebaseJobDispatcherFactory(providerGooglePlayDriverProvider);
        provideSyncPeriodFlagProvider = new ExperimentFlagsModule_ProvideSyncPeriodFlagFactory(providePhenotypeFlagBuilderProvider);
        provideSyncRetryMinDelayMsProvider = new ExperimentFlagsModule_ProvideSyncRetryMinDelayMsFactory(providePhenotypeFlagBuilderProvider);
        provideSyncRetryMaxDelayMsProvider = new ExperimentFlagsModule_ProvideSyncRetryMaxDelayMsFactory(providePhenotypeFlagBuilderProvider);
        provideSyncRetryPolicyProvider = new ExperimentFlagsModule_ProvideSyncRetryPolicyFactory(providePhenotypeFlagBuilderProvider);
        getGcoreProviderInstallerProvider = new GcoreSecurityDaggerModule_GetGcoreProviderInstallerFactory(builder1.gcoreSecurityDaggerModule);
        periodicSyncJobProvider = new PeriodicSyncJob_Factory(provideFirebaseJobDispatcherProvider, provideGeneralEnableFlagProvider, provideSyncPeriodFlagProvider, provideSyncRetryMinDelayMsProvider, provideSyncRetryMaxDelayMsProvider, provideSyncRetryPolicyProvider, getGcoreProviderInstallerProvider, provideContextProvider, promotionSyncImplProvider, provideBlockingExecutorProvider);
        provideSyncSyncOnStartupProvider = new ExperimentFlagsModule_ProvideSyncSyncOnStartupFactory(providePhenotypeFlagBuilderProvider);
        provideSyncSyncOnStartupAtMostEveryProvider = new ExperimentFlagsModule_ProvideSyncSyncOnStartupAtMostEveryFactory(providePhenotypeFlagBuilderProvider);
        oneoffSyncJobProvider = new OneoffSyncJob_Factory(provideFirebaseJobDispatcherProvider, provideGeneralEnableFlagProvider, provideSyncSyncOnStartupProvider, provideSyncSyncOnStartupAtMostEveryProvider, provideSyncRetryMinDelayMsProvider, provideSyncRetryMaxDelayMsProvider, provideSyncRetryPolicyProvider, getGcoreProviderInstallerProvider, provideContextProvider, promotionSyncImplProvider, provideBlockingExecutorProvider, provideGrowthKitSharedPrefsProvider, provideClockProvider);
        provideClearStorageAgeFlagProvider = new ExperimentFlagsModule_ProvideClearStorageAgeFlagFactory(providePhenotypeFlagBuilderProvider);
        storageUtilitiesImplProvider = new StorageUtilitiesImpl_Factory(provideClearcutEventCounterProvider, provideVisualElementEventCounterProvider, accountManagerImplProvider, provideClockProvider, provideClearStorageAgeFlagProvider);
        provideClearStoragePeriodFlagProvider = new ExperimentFlagsModule_ProvideClearStoragePeriodFlagFactory(providePhenotypeFlagBuilderProvider);
        storageCleanupJobProvider = new StorageCleanupJob_Factory(storageUtilitiesImplProvider, provideClearStoragePeriodFlagProvider, provideFirebaseJobDispatcherProvider);
        obj = new dagger.internal.MapProviderFactory.Builder(3);
        obj1 = periodicSyncJobProvider;
        obj2 = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if ("GrowthKit.PeriodicSyncJob" == null)
        {
            throw new NullPointerException("key");
        }
        if (obj1 == null)
        {
            throw new NullPointerException("provider");
        }
        ((LinkedHashMap) (obj2)).put("GrowthKit.PeriodicSyncJob", (Provider)obj1);
        obj1 = oneoffSyncJobProvider;
        obj2 = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if ("GrowthKit.OneoffSyncJob" == null)
        {
            throw new NullPointerException("key");
        }
        if (obj1 == null)
        {
            throw new NullPointerException("provider");
        }
        ((LinkedHashMap) (obj2)).put("GrowthKit.OneoffSyncJob", (Provider)obj1);
        obj1 = storageCleanupJobProvider;
        obj2 = ((dagger.internal.MapProviderFactory.Builder) (obj)).map;
        if ("GrowthKit.StorageCleanupJob" == null)
        {
            throw new NullPointerException("key");
        }
        if (obj1 == null)
        {
            throw new NullPointerException("provider");
        } else
        {
            ((LinkedHashMap) (obj2)).put("GrowthKit.StorageCleanupJob", (Provider)obj1);
            mapOfStringAndProviderOfGrowthKitJobOfProvider = new MapProviderFactory(((dagger.internal.MapProviderFactory.Builder) (obj)).map);
            growthKitJobSchedulerImplProvider = DoubleCheck.provider(new GrowthKitJobSchedulerImpl_Factory(provideFirebaseJobDispatcherProvider, mapOfStringAndProviderOfGrowthKitJobOfProvider));
            jobsStartupListenerProvider = new JobsStartupListener_Factory(growthKitJobSchedulerImplProvider);
            providePhenotypeProvider2 = new DaggerExperimentsModule_ProvidePhenotypeFactory(GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory.INSTANCE);
            providePhenotypeSharedPreferencesProvider = DoubleCheck.provider(new DaggerExperimentsModule_ProvidePhenotypeSharedPreferencesFactory(provideContextProvider, provideSharedPrefsProvider));
            provideMendelPackageProvider = DoubleCheck.provider(DaggerExperimentsModule_ProvideMendelPackageFactory.INSTANCE);
            provideMendelPerAppPackageProvider = DoubleCheck.provider(new DaggerExperimentsModule_ProvideMendelPerAppPackageFactory(provideMendelPackageProvider, provideApplicationPackageNameProvider));
            phenotypeFlagUpdaterProvider = new PhenotypeFlagUpdater_Factory(providePhenotypeProvider2, providePhenotypeSharedPreferencesProvider, provideMendelPerAppPackageProvider, provideExecutorServiceProvider, getGcoreGoogleApiClientBuilderProvider, GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.INSTANCE);
            provideAppVersionCodeProvider2 = DoubleCheck.provider(new DaggerExperimentsModule_ProvideAppVersionCodeFactory(provideContextProvider));
            phenotypeManagerImplProvider = new PhenotypeManagerImpl_Factory(getGcoreGoogleApiClientBuilderProvider, GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.INSTANCE, providePhenotypeProvider2, phenotypeFlagUpdaterProvider, provideMendelPerAppPackageProvider, provideAppVersionCodeProvider2, GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory.INSTANCE);
            experimentsStartupListenerProvider = new ExperimentsStartupListener_Factory(phenotypeManagerImplProvider);
            obj = SetFactory.builder(2, 0);
            obj1 = jobsStartupListenerProvider;
            ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
            obj1 = experimentsStartupListenerProvider;
            ((dagger.internal.SetFactory.Builder) (obj)).individualProviders.add(obj1);
            setOfGrowthKitStartupListenerProvider = new SetFactory(((dagger.internal.SetFactory.Builder) (obj)).individualProviders, ((dagger.internal.SetFactory.Builder) (obj)).collectionProviders);
            provideGrowthkitStartedCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideGrowthkitStartedCounterFactory.INSTANCE);
            growthKitStartupImplProvider = DoubleCheck.provider(new GrowthKitStartupImpl_Factory(provideContextProvider, provideBlockingExecutorProvider, setOfGrowthKitStartupListenerProvider, provideGeneralEnableFlagProvider, provideGrowthkitStartedCounterProvider, provideStreamzIncrementsProvider, provideApplicationPackageNameProvider));
            gcoreAuthDaggerModule = builder1.gcoreAuthDaggerModule;
            gcorePseudonymousDaggerModule = builder1.gcorePseudonymousDaggerModule;
            gcoreCommonApiDaggerModule = builder1.gcoreCommonApiDaggerModule;
            provideRatingMaterialDialogBuilderProvider = DoubleCheck.provider(DialogsModule_ProvideRatingMaterialDialogBuilderFactory.INSTANCE);
            provideRatingDefaultDialogBuilderProvider = DoubleCheck.provider(DialogsModule_ProvideRatingDefaultDialogBuilderFactory.INSTANCE);
            provideRatingBottomSheetDialogBuilderProvider = DoubleCheck.provider(DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory.INSTANCE);
            testingToolsBroadcastReceiverSubcomponentBuilderProvider = new _cls1();
            providerCTestingToolsBroadcastReceiverInjectorProvider = new StorageModule_ProviderCTestingToolsBroadcastReceiverInjectorFactory(testingToolsBroadcastReceiverSubcomponentBuilderProvider);
            phenotypeBroadcastReceiverSubcomponentBuilderProvider = new _cls2();
            providerPhenotypeBroadcastReceiverInjectorProvider = new DaggerExperimentsModule_ProviderPhenotypeBroadcastReceiverInjectorFactory(phenotypeBroadcastReceiverSubcomponentBuilderProvider);
            growthKitJobServiceSubcomponentBuilderProvider = new _cls3();
            providerSGrowthKitJobServiceInejctorProvider = new JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory(growthKitJobServiceSubcomponentBuilderProvider);
            promoUiDialogFragmentSubcomponentBuilderProvider = new _cls4();
            providePromoUiDialogFragmentInjectorProvider = DoubleCheck.provider(new DialogsModule_ProvidePromoUiDialogFragmentInjectorFactory(promoUiDialogFragmentSubcomponentBuilderProvider));
            featureHighlightFragmentSubcomponentBuilderProvider = new _cls5();
            provideFeatureHighlightFragmentInjectorProvider = new TapTargetModule_ProvideFeatureHighlightFragmentInjectorFactory(featureHighlightFragmentSubcomponentBuilderProvider);
            tooltipFragmentSubcomponentBuilderProvider = new _cls6();
            provideTooltipFragmentInjectorProvider = new TooltipModule_ProvideTooltipFragmentInjectorFactory(tooltipFragmentSubcomponentBuilderProvider);
            gcoreSecurityDaggerModule = builder1.gcoreSecurityDaggerModule;
            provideTestFeatureTestFlagProvider = new ExperimentFlagsModule_ProvideTestFeatureTestFlagFactory(providePhenotypeFlagBuilderProvider);
            provideJobCounterProvider = DoubleCheck.provider(StreamzCommonModule_ProvideJobCounterFactory.INSTANCE);
            return;
        }
    }

    public static Builder builder()
    {
        return new Builder();
    }

    final AccountManagerImpl getAccountManagerImpl()
    {
        Context context = (Context)provideContextProvider.get();
        ListeningExecutorService listeningexecutorservice = getGrowthKitBlockingExecutorListeningExecutorService();
        Object obj = gcoreAuthDaggerModule;
        obj = new GcoreGoogleAuthUtilImpl((Context)provideGcoreContextProvider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return new AccountManagerImpl(context, listeningexecutorservice, (GcoreGoogleAuthUtil)obj, provideSyncGaiaProvider);
        }
    }

    final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder getBuilder()
    {
        Object obj = gcoreCommonApiDaggerModule;
        obj = new com.google.android.libraries.gcoreclient.common.api.impl.GcoreGoogleApiClientImpl.Builder((Context)provideGcoreContextProvider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder)obj;
        }
    }

    final ListeningExecutorService getGrowthKitBlockingExecutorListeningExecutorService()
    {
        ListeningExecutorService listeningexecutorservice = GrowthKitInternalCommonModule.provideBlockingExecutor(Absent.INSTANCE, (ListeningExecutorService)provideExecutorServiceProvider.get());
        if (listeningexecutorservice == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListeningExecutorService)listeningexecutorservice;
        }
    }

    public final GrowthKitCallbacksManager getGrowthKitCallbacksManager()
    {
        return (GrowthKitCallbacksManager)growthKitCallbacksManagerImplProvider.get();
    }

    public final GrowthKitEventManager getGrowthKitEventManager()
    {
        return (GrowthKitEventManager)provideEventManagerProvider.get();
    }

    final ListenableFuture getGrowthKitSharedPrefsListenableFutureOfSharedPreferences()
    {
        ListenableFuture listenablefuture = GrowthKitInternalCommonModule.provideGrowthKitSharedPrefs((Context)provideContextProvider.get(), (ListeningExecutorService)provideExecutorServiceProvider.get());
        if (listenablefuture == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListenableFuture)listenablefuture;
        }
    }

    public final GrowthKitStartup getGrowthKitStartup()
    {
        return (GrowthKitStartup)growthKitStartupImplProvider.get();
    }

    public final Map internalBroadcastReceiverInjectors()
    {
        return ImmutableMap.of(com/google/android/libraries/internal/growth/growthkit/internal/storage/impl/TestingToolsBroadcastReceiver, providerCTestingToolsBroadcastReceiverInjectorProvider, com/google/android/libraries/internal/growth/growthkit/internal/experiments/impl/PhenotypeBroadcastReceiver, providerPhenotypeBroadcastReceiverInjectorProvider);
    }

    public final Map internalFragmentInjectors()
    {
        return ImmutableMap.of(com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/dialogs/PromoUiDialogFragment, providePromoUiDialogFragmentInjectorProvider, com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/taptarget/FeatureHighlightFragment, provideFeatureHighlightFragmentInjectorProvider, com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/tooltip/TooltipFragment, provideTooltipFragmentInjectorProvider);
    }

    public final Map internalServiceInjectors()
    {
        JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory jobscommonmodule_providersgrowthkitjobserviceinejctorfactory = providerSGrowthKitJobServiceInejctorProvider;
        CollectPreconditions.checkEntryNotNull(com/google/android/libraries/internal/growth/growthkit/internal/jobs/impl/GrowthKitJobService, jobscommonmodule_providersgrowthkitjobserviceinejctorfactory);
        return RegularImmutableMap.create(1, new Object[] {
            com/google/android/libraries/internal/growth/growthkit/internal/jobs/impl/GrowthKitJobService, jobscommonmodule_providersgrowthkitjobserviceinejctorfactory
        });
    }

    static 
    {
        Absent absent = Absent.INSTANCE;
        if (absent == null)
        {
            throw new NullPointerException("instance cannot be null");
        } else
        {
            ABSENT_GUAVA_OPTIONAL_PROVIDER = new InstanceFactory(absent);
        }
    }

    private class Builder
    {

        public GcoreAuthDaggerModule gcoreAuthDaggerModule;
        public GcoreCommonApiDaggerModule gcoreCommonApiDaggerModule;
        public GcorePseudonymousDaggerModule gcorePseudonymousDaggerModule;
        public GcoreSecurityDaggerModule gcoreSecurityDaggerModule;
        public GrowthKitApplicationModule growthKitApplicationModule;
        public PromotionsModule promotionsModule;
        public RpcModule rpcModule;

        Builder()
        {
        }
    }


    private class _cls1
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new TestingToolsBroadcastReceiverSubcomponentBuilder();
        }

        _cls1()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class TestingToolsBroadcastReceiverSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.TestingToolsBroadcastReceiver.TestingToolsBroadcastReceiverSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.TestingToolsBroadcastReceiver.TestingToolsBroadcastReceiverSubcomponent build()
            {
                return new TestingToolsBroadcastReceiverSubcomponentImpl();
            }

            TestingToolsBroadcastReceiverSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class TestingToolsBroadcastReceiverSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.TestingToolsBroadcastReceiver.TestingToolsBroadcastReceiverSubcomponent
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
                    ListenableFuture listenablefuture = ((DaggerGrowthKitApplicationComponent) (obj)).getGrowthKitSharedPrefsListenableFutureOfSharedPreferences();
                    ListeningExecutorService listeningexecutorservice = (ListeningExecutorService)((DaggerGrowthKitApplicationComponent) (obj)).provideExecutorServiceProvider.get();
                    PerAccountProvider peraccountprovider = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).providePresentedPromosStoreProvider.get();
                    PerAccountProvider peraccountprovider1 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).providePromotionStoreProvider.get();
                    MessageStore messagestore = (MessageStore)((DaggerGrowthKitApplicationComponent) (obj)).provideCappedPromotionStoreProvider.get();
                    PerAccountProvider peraccountprovider2 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).provideMonitoredEventClearcutStoreProvider.get();
                    PerAccountProvider peraccountprovider3 = (PerAccountProvider)((DaggerGrowthKitApplicationComponent) (obj)).provideMonitoredEventVisualElementStoreProvider.get();
                    GrowthApiClient growthapiclient = (GrowthApiClient)((DaggerGrowthKitApplicationComponent) (obj)).provideGrowthApiClientProvider.get();
                    AccountManagerImpl accountmanagerimpl = ((DaggerGrowthKitApplicationComponent) (obj)).getAccountManagerImpl();
                    String s = (String)((DaggerGrowthKitApplicationComponent) (obj)).provideApplicationPackageNameProvider.get();
                    Optional optional = (Optional)((DaggerGrowthKitApplicationComponent) (obj)).provideAppVersionCodeProvider.get();
                    Optional optional1 = (Optional)((DaggerGrowthKitApplicationComponent) (obj)).provideAppVersionNameProvider.get();
                    ListeningExecutorService listeningexecutorservice1 = (ListeningExecutorService)((DaggerGrowthKitApplicationComponent) (obj)).provideExecutorServiceProvider.get();
                    Object obj1 = ((DaggerGrowthKitApplicationComponent) (obj)).gcorePseudonymousDaggerModule;
                    obj1 = new com.google.android.libraries.gcoreclient.pseudonymous.impl.GcorePseudonymousIdImpl.Builder();
                    if (obj1 == null)
                    {
                        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                    }
                    obj1 = ((com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId.Builder)obj1).build();
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
                    broadcastreceiver.promotionSync = new PromotionSyncImpl(context, listenablefuture, listeningexecutorservice, peraccountprovider, peraccountprovider1, messagestore, peraccountprovider2, peraccountprovider3, growthapiclient, accountmanagerimpl, s, optional, optional1, new PseudonymousIdHelperImpl(listeningexecutorservice1, ((GcorePseudonymousId) (obj1)), (GcorePseudonymousIdApi)obj2, ((DaggerGrowthKitApplicationComponent) (obj)).getBuilder()), ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncSetWriteDebugInfoProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncGaiaProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncZwiebackProvider, ((DaggerGrowthKitApplicationComponent) (obj)).provideSyncOverrideCountryProvider, (Clock)((DaggerGrowthKitApplicationComponent) (obj)).provideClockProvider.get(), ImmutableMap.of(com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG, ((DaggerGrowthKitApplicationComponent) (obj)).provideMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingBottomsheetlDialogImageSizeResolverProvider));
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
                        broadcastreceiver.uiImageDownloadManager = ImmutableMap.of(com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG, ((DaggerGrowthKitApplicationComponent) (obj)).provideMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingMaterialDialogImageSizeResolverProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET, ((DaggerGrowthKitApplicationComponent) (obj)).provideRatingBottomsheetlDialogImageSizeResolverProvider);
                        broadcastreceiver.testingEnabled = provideTestFeatureTestFlagProvider;
                        broadcastreceiver.syncGaiaAccounts = provideSyncGaiaProvider;
                        broadcastreceiver.syncZwiebackAccounts = provideSyncZwiebackProvider;
                        return;
                    }
                }

                TestingToolsBroadcastReceiverSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }


    private class _cls2
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new PhenotypeBroadcastReceiverSubcomponentBuilder();
        }

        _cls2()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class PhenotypeBroadcastReceiverSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeBroadcastReceiver.PhenotypeBroadcastReceiverSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeBroadcastReceiver.PhenotypeBroadcastReceiverSubcomponent build()
            {
                return new PhenotypeBroadcastReceiverSubcomponentImpl();
            }

            PhenotypeBroadcastReceiverSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class PhenotypeBroadcastReceiverSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl.PhenotypeBroadcastReceiver.PhenotypeBroadcastReceiverSubcomponent
            {

                private final DaggerGrowthKitApplicationComponent this$0;

                public final void inject(BroadcastReceiver broadcastreceiver)
                {
                    broadcastreceiver = (PhenotypeBroadcastReceiver)broadcastreceiver;
                    DaggerGrowthKitApplicationComponent daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
                    com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder builder1 = daggergrowthkitapplicationcomponent.getBuilder();
                    com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi phenotypeapi = GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.proxyGetPhenotypeApi();
                    Phenotype phenotype = DaggerExperimentsModule.providePhenotype(GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory.proxyGetPhenotypeFactory());
                    if (phenotype == null)
                    {
                        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                    }
                    phenotype = (Phenotype)phenotype;
                    Phenotype phenotype1 = DaggerExperimentsModule.providePhenotype(GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory.proxyGetPhenotypeFactory());
                    if (phenotype1 == null)
                    {
                        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                    } else
                    {
                        broadcastreceiver.phenotypeManager = new PhenotypeManagerImpl(builder1, phenotypeapi, phenotype, new PhenotypeFlagUpdater((Phenotype)phenotype1, (SharedPreferences)daggergrowthkitapplicationcomponent.providePhenotypeSharedPreferencesProvider.get(), (String)daggergrowthkitapplicationcomponent.provideMendelPerAppPackageProvider.get(), (ListeningExecutorService)daggergrowthkitapplicationcomponent.provideExecutorServiceProvider.get(), daggergrowthkitapplicationcomponent.getBuilder(), GcorePhenotypeDaggerModule_GetPhenotypeApiFactory.proxyGetPhenotypeApi()), (String)daggergrowthkitapplicationcomponent.provideMendelPerAppPackageProvider.get(), ((Integer)daggergrowthkitapplicationcomponent.provideAppVersionCodeProvider2.get()).intValue(), GrowthKitInternalCommonModule_ProvideGrowthKitPropertiesFactory.proxyProvideGrowthKitProperties());
                        return;
                    }
                }

                PhenotypeBroadcastReceiverSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }


    private class _cls3
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new GrowthKitJobServiceSubcomponentBuilder();
        }

        _cls3()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class GrowthKitJobServiceSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobService.GrowthKitJobServiceSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobService.GrowthKitJobServiceSubcomponent build()
            {
                return new GrowthKitJobServiceSubcomponentImpl();
            }

            GrowthKitJobServiceSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class GrowthKitJobServiceSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl.GrowthKitJobService.GrowthKitJobServiceSubcomponent
            {

                private final DaggerGrowthKitApplicationComponent this$0;

                public final void inject(Service service)
                {
                    service = (GrowthKitJobService)service;
                    service.enableFlag = provideGeneralEnableFlagProvider;
                    service.jobs = DoubleCheck.lazy(mapOfStringAndProviderOfGrowthKitJobOfProvider);
                    Object obj = new GooglePlayDriver((Context)provideContextProvider.get());
                    if (obj == null)
                    {
                        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                    }
                    obj = new FirebaseJobDispatcher((Driver)obj);
                    if (obj == null)
                    {
                        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
                    } else
                    {
                        service.firebaseJobDispatcher = (FirebaseJobDispatcher)obj;
                        service.trace = (Trace)bindsNoOpTraceProvider.get();
                        service.jobCounter = (Counter3)provideJobCounterProvider.get();
                        service.streamzIncrements = (StreamzIncrements)provideStreamzIncrementsProvider.get();
                        service.packageName = (String)provideApplicationPackageNameProvider.get();
                        return;
                    }
                }

                GrowthKitJobServiceSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }


    private class _cls4
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new PromoUiDialogFragmentSubcomponentBuilder();
        }

        _cls4()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class PromoUiDialogFragmentSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.PromoUiDialogFragment.PromoUiDialogFragmentSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.PromoUiDialogFragment.PromoUiDialogFragmentSubcomponent build()
            {
                return new PromoUiDialogFragmentSubcomponentImpl();
            }

            PromoUiDialogFragmentSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class PromoUiDialogFragmentSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs.PromoUiDialogFragment.PromoUiDialogFragmentSubcomponent
            {

                private final DaggerGrowthKitApplicationComponent this$0;

                public final void inject(Fragment fragment)
                {
                    fragment = (PromoUiDialogFragment)fragment;
                    DaggerGrowthKitApplicationComponent daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
                    fragment.dialogBuilderMap = ImmutableMap.of(com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG, daggergrowthkitapplicationcomponent.provideRatingMaterialDialogBuilderProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG, daggergrowthkitapplicationcomponent.provideRatingDefaultDialogBuilderProvider, com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET, daggergrowthkitapplicationcomponent.provideRatingBottomSheetDialogBuilderProvider);
                    fragment.imageCache = (ImageCache)provideImageCacheProvider.get();
                    daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
                    fragment.userActionUtil = new UserActionUtil((Context)daggergrowthkitapplicationcomponent.provideContextProvider.get(), (PerAccountProvider)daggergrowthkitapplicationcomponent.providePresentedPromosStoreProvider.get(), (ClearcutLogger)daggergrowthkitapplicationcomponent.provideClearcutLoggerProvider.get(), Absent.INSTANCE);
                }

                PromoUiDialogFragmentSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }


    private class _cls5
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new FeatureHighlightFragmentSubcomponentBuilder();
        }

        _cls5()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class FeatureHighlightFragmentSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightFragment.FeatureHighlightFragmentSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightFragment.FeatureHighlightFragmentSubcomponent build()
            {
                return new FeatureHighlightFragmentSubcomponentImpl();
            }

            FeatureHighlightFragmentSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class FeatureHighlightFragmentSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget.FeatureHighlightFragment.FeatureHighlightFragmentSubcomponent
            {

                private final DaggerGrowthKitApplicationComponent this$0;

                public final void inject(Fragment fragment)
                {
                    fragment = (FeatureHighlightFragment)fragment;
                    fragment.controllerFactory = new FeatureHighlightControllerFactory(userActionUtilProvider);
                    fragment.featureHighlightViewFinderFactory = new FeatureHighlightViewFinderFactory(targetElementFinderProvider);
                }

                FeatureHighlightFragmentSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }


    private class _cls6
        implements Provider
    {

        private final DaggerGrowthKitApplicationComponent this$0;

        public final Object get()
        {
            return new TooltipFragmentSubcomponentBuilder();
        }

        _cls6()
        {
            this$0 = DaggerGrowthKitApplicationComponent.this;
            super();
        }

        private class TooltipFragmentSubcomponentBuilder
            implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipFragment.TooltipFragmentSubcomponent.Builder
        {

            private final DaggerGrowthKitApplicationComponent this$0;

            public final com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipFragment.TooltipFragmentSubcomponent build()
            {
                return new TooltipFragmentSubcomponentImpl();
            }

            TooltipFragmentSubcomponentBuilder()
            {
                this$0 = DaggerGrowthKitApplicationComponent.this;
                super();
            }

            private class TooltipFragmentSubcomponentImpl
                implements com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip.TooltipFragment.TooltipFragmentSubcomponent
            {

                private final DaggerGrowthKitApplicationComponent this$0;

                public final void inject(Fragment fragment)
                {
                    fragment = (TooltipFragment)fragment;
                    DaggerGrowthKitApplicationComponent daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
                    fragment.userActionUtil = new UserActionUtil((Context)daggergrowthkitapplicationcomponent.provideContextProvider.get(), (PerAccountProvider)daggergrowthkitapplicationcomponent.providePresentedPromosStoreProvider.get(), (ClearcutLogger)daggergrowthkitapplicationcomponent.provideClearcutLoggerProvider.get(), Absent.INSTANCE);
                    daggergrowthkitapplicationcomponent = DaggerGrowthKitApplicationComponent.this;
                    fragment.tooltipViewFinder = new TooltipViewFinder(new TargetElementFinder(Absent.INSTANCE));
                }

                TooltipFragmentSubcomponentImpl()
                {
                    this$0 = DaggerGrowthKitApplicationComponent.this;
                    super();
                }
            }

        }

    }

}
