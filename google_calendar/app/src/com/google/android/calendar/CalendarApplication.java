// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ShortcutManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v4.content.LocalBroadcastManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.apps.calendar.config.experiments.Experiment;
import com.google.android.apps.calendar.config.experiments.ExperimentConfiguration;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.feature.VariantFeatureConfig_release;
import com.google.android.apps.calendar.config.phenotypesupport.PhenotypeManager;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.loggers.visualelements.RealVisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.multidex.MultidexInstaller;
import com.google.android.apps.calendar.primes.PerformanceMetricCollectorImpl;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.timely.store.GrooveStore;
import com.google.android.apps.calendar.usernotifications.NotificationsInitializer;
import com.google.android.apps.calendar.util.android.BundleUtil;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ExecutorFactory;
import com.google.android.calendar.alerts.EventNotificationPresenterImpl;
import com.google.android.calendar.alerts.HabitNotificationPresenterImpl;
import com.google.android.calendar.alerts.HabitsIntentReceiver;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.analytics.CalendarAnalyticsLoggerExtension;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.CalendarApiFactory;
import com.google.android.calendar.api.CalendarApiFactoryImpl;
import com.google.android.calendar.common.debug.StrictModeHelper;
import com.google.android.calendar.event.image.cache.FlairsInvalidator;
import com.google.android.calendar.hsv.HsvSnapshotHelper;
import com.google.android.calendar.hsv.HsvSupplier;
import com.google.android.calendar.jobservices.CalendarProviderObserverJobService;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.latency.impl.LatencyLoggerImpl;
import com.google.android.calendar.launch.oobe.DemoUserUtil;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.settings.SettingsActivity;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;
import com.google.android.calendar.utils.app.PackageUtils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.android.calendar.widget.WidgetDateUtils;
import com.google.android.calendar.widgetmonth.MonthViewWidgetProvider;
import com.google.android.libraries.internal.growth.growthkit.noinject.GrowthKitInstall;
import com.google.android.libraries.performance.primes.PrimesStartupMeasure;
import com.google.android.libraries.performance.primes.TimerEvent;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.syncadapters.calendar.ObsoleteDataCleaner;
import com.google.calendar.v2a.android.util.job.FutureJobService;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.calendar.v2a.android.util.metric.SyncOperation;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationComponent, DateTimeFormatHelper, SettingsShimsImpl, CalendarApplicationPropertiesManager

public class CalendarApplication extends Application
    implements android.app.Application.ActivityLifecycleCallbacks, com.google.calendar.v2a.shared.android.AndroidSharedApi.Holder, HasActivityInjector
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/CalendarApplication);
    private final Runnable activityStoppedRunnable = new _cls1();
    public DispatchingAndroidInjector dispatchingAndroidInjector;
    private final Handler handler = new Handler();
    private Locale lastLocale;
    public CalendarApplicationPropertiesManager propertiesManager;
    public final AtomicInteger runningActivityCount = new AtomicInteger(0);
    private Supplier sharedApiSupplier;

    public CalendarApplication()
    {
        lastLocale = null;
        sharedApiSupplier = null;
    }

    private final void initializeOrReinitializeFlairs()
    {
        if (!Locale.getDefault().equals(lastLocale))
        {
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).bugfood_build();
            featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).fishfood_build();
            FlairAllocatorFactory.setContext(this, false);
            lastLocale = Locale.getDefault();
        }
    }

    static final void lambda$initializePerformanceMetrics$1$CalendarApplication(com.google.calendar.v2a.android.util.metric.MetricUtils.MemoryRecording memoryrecording)
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory(memoryrecording.getName());
            return;
        }
    }

    static final void lambda$initializePerformanceMetrics$2$CalendarApplication$51666RRD5TJMURR7DHIIUOR1DHIMSP31E8NNCCJ15TGMSP3IDTKM8BRLEHKMOBRDCLQ74QB35T6MAT3ID5HLAT39DHPI8KJ5EDQMOT1R55B0____0()
    {
    }

    static final void lambda$initializePerformanceMetrics$3$CalendarApplication(TimerEvent timerevent, com.google.calendar.v2a.android.util.metric.MetricUtils.Operation operation, com.google.calendar.v2a.android.util.metric.MetricUtils.Result result)
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).logTime(timerevent, operation.getFullName(), result);
            return;
        }
    }

    static final com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext lambda$initializePerformanceMetrics$4$CalendarApplication(Random random, com.google.calendar.v2a.android.util.metric.MetricUtils.Operation operation)
    {
        class .Lambda._cls9
            implements com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext
        {

            public static final com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext $instance = new .Lambda._cls9();

            public final void finish(com.google.calendar.v2a.android.util.metric.MetricUtils.Result result)
            {
                CalendarApplication.lambda$initializePerformanceMetrics$2$CalendarApplication$51666RRD5TJMURR7DHIIUOR1DHIMSP31E8NNCCJ15TGMSP3IDTKM8BRLEHKMOBRDCLQ74QB35T6MAT3ID5HLAT39DHPI8KJ5EDQMOT1R55B0____0();
            }

            public final void finish(boolean flag)
            {
                com.google.calendar.v2a.android.util.metric.MetricUtils.Result result;
                if (flag)
                {
                    result = com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS;
                } else
                {
                    result = com.google.calendar.v2a.android.util.metric.MetricUtils.Result.FAILURE;
                }
                finish(result);
            }


            private .Lambda._cls9()
            {
            }
        }

        if (random.nextDouble() >= operation.getSampling())
        {
            return .Lambda._cls9..instance;
        }
        random = PerformanceMetricCollectorHolder.instance;
        class .Lambda._cls10
            implements com.google.calendar.v2a.android.util.metric.MetricUtils.MetricContext
        {

            private final TimerEvent arg$1;
            private final com.google.calendar.v2a.android.util.metric.MetricUtils.Operation arg$2;

            public final void finish(com.google.calendar.v2a.android.util.metric.MetricUtils.Result result)
            {
                CalendarApplication.lambda$initializePerformanceMetrics$3$CalendarApplication(arg$1, arg$2, result);
            }

            public final void finish(boolean flag)
            {
                com.google.calendar.v2a.android.util.metric.MetricUtils.Result result;
                if (flag)
                {
                    result = com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS;
                } else
                {
                    result = com.google.calendar.v2a.android.util.metric.MetricUtils.Result.FAILURE;
                }
                finish(result);
            }

            .Lambda._cls10(TimerEvent timerevent, com.google.calendar.v2a.android.util.metric.MetricUtils.Operation operation)
            {
                arg$1 = timerevent;
                arg$2 = operation;
            }
        }

        if (random == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            return new .Lambda._cls10(((PerformanceMetricCollector)random).initializeTimer(), operation);
        }
    }

    public final AndroidInjector activityInjector()
    {
        return dispatchingAndroidInjector;
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        context = Absent.INSTANCE;
        if (context.isPresent())
        {
            LogUtils.i(TAG, "Calendar in multidex", new Object[0]);
            ((MultidexInstaller)context.get()).install$51662RJ4E9NMIP1FC5O70BQ1E1O6OQB3C5Q6IRRE7CKLC___0();
        }
    }

    public final Optional getSharedApi()
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return ((Supplier)obj1).get();
            }


            private .Lambda._cls0()
            {
            }
        }

        Object obj;
        if (true)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(null);
        }
        return ((Optional) (obj)).transform(.Lambda._cls0..instance);
    }

    public final void initializeMandatoryPermissionBasedComponents()
    {
        Object obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        ((ListenableFutureCache)obj).start();
        obj = SettingsCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        ((ListenableFutureCache)obj).start();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).uss();
        if (PrimaryAccountSelector.instance == null)
        {
            PrimaryAccountSelector.instance = new PrimaryAccountSelector(this);
        }
        PrimaryAccountSelector.instance.recompute(this);
        obj = new DemoUserUtil(this);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).android_retail_demo_calendar();
        if (((DemoUserUtil) (obj)).isDemoUser())
        {
            obj = (FluentFuture)CalendarExecutor.DISK.submit(new com.google.android.calendar.launch.oobe.DemoUserUtil..Lambda._cls0(((DemoUserUtil) (obj))));
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
    }

    public void onActivityDestroyed(Activity activity)
    {
    }

    public void onActivityPaused(Activity activity)
    {
    }

    public void onActivityResumed(Activity activity)
    {
        initializeOrReinitializeFlairs();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
        if (BundleUtil.getParcelSize(bundle) >= 0x64000)
        {
            activity = activity.getClass().getSimpleName();
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(this, "TransactionTooLarge", activity);
            if (RemoteFeatureConfig.DUMP_BUNDLE_STATS.enabled())
            {
                BundleUtil.dumpStats(bundle);
            }
        }
    }

    public void onActivityStarted(Activity activity)
    {
        if (runningActivityCount.getAndIncrement() == 0)
        {
            VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
            if (visualelementattacher == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            }
            ((VisualElementAttacher)visualelementattacher).recordAppVisibilityEvent(this, true);
            if (AndroidPermissionUtils.hasMandatoryPermissions(activity))
            {
                initializeMandatoryPermissionBasedComponents();
            }
            activity = CalendarProperties.instance;
            if (activity == null)
            {
                throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
            }
            ((CalendarProperties)activity).checkPropertiesChanged();
        }
    }

    public void onActivityStopped(Activity activity)
    {
        if (activity instanceof SettingsActivity)
        {
            activity = CalendarProperties.instance;
            if (activity == null)
            {
                throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
            }
            ((CalendarProperties)activity).checkPropertiesChanged();
        }
        handler.postDelayed(activityStoppedRunnable, 2000L);
    }

    public void onCreate()
    {
        Trace.beginSection("StartApplication");
        Object obj;
        super.onCreate();
        (new DaggerCalendarApplicationComponent.Builder()).application(this).build().inject(this);
        System.setProperty("org.joda.time.DateTimeZone.Provider", com/google/android/libraries/fastjoda/FastDateTimeZones$FastDateTimeZoneProvider.getName());
        AnalyticsLoggerHolder.set(new CalendarAnalyticsLoggerExtension(this));
        obj = PrimesStartupMeasure.instance;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        com.google.android.libraries.performance.primes.PrimesStartupMeasure._cls1 _lcls1;
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        if (((PrimesStartupMeasure) (obj)).appClassLoadedAt <= 0L || ((PrimesStartupMeasure) (obj)).appOnCreateAt != 0L || this == null)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        obj.appOnCreateAt = SystemClock.elapsedRealtime();
        _lcls1 = new com.google.android.libraries.performance.primes.PrimesStartupMeasure._cls1(((PrimesStartupMeasure) (obj)));
        if (ThreadUtil.sMainThreadHandler == null)
        {
            ThreadUtil.sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        ThreadUtil.sMainThreadHandler.post(_lcls1);
        registerActivityLifecycleCallbacks(new com.google.android.libraries.performance.primes.PrimesStartupMeasure.StartupCallbacks(((PrimesStartupMeasure) (obj)), this));
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        obj;
        Trace.endSection();
        throw obj;
        ((FeatureConfig)obj).public_release();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_236;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).fishfood_debug();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_267;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        FutureJobService.setThrowOnPreconditionFailures(((FeatureConfig)obj).fishfood_debug());
        int ai[];
        Object obj2;
        AnalyticsLogger analyticslogger;
        String s;
        int i;
        long l;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        CalendarFeatureConfigDelegate.useJobs = Boolean.valueOf(flag2);
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        CalendarFeatureConfigDelegate.useModernNotifications = Boolean.valueOf(flag2);
        i = PackageUtils.getVersionCode(this);
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_344;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).bugfood_build();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_375;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).fishfood_build();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_406;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).dogfood_features();
        PhenotypeManager.initialize(this, i, com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties.BuildVariant.RELEASE, new String[] {
            "CALENDAR", "CALENDAR_CLIENT", "CALENDAR_ANDROID_PRIMES"
        });
        StrictModeHelper.configureStrictMode();
        class .Lambda._cls1
            implements Runnable
        {

            public static final Runnable $instance = new .Lambda._cls1();

            public final void run()
            {
                StrictModeHelper.configureStrictMode();
            }


            private .Lambda._cls1()
            {
            }
        }

        handler.postAtFrontOfQueue(.Lambda._cls1..instance);
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_482;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        CalendarExecutor.installExecutorFactory(new ExecutorFactory(((FeatureConfig)obj).threads_strictmode()));
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_522;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).experimental_options();
        UnifiedSyncUtils.applyUssNotReadyOverrides(this);
        UnifiedSyncUtils.logInitialization(getApplicationContext());
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_564;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).experimental_options();
        UnifiedSyncUtils.cleanupUnifiedSync(this);
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_599;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).uss();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_630;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).uss();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_661;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).uss();
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_692;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).uss();
        CalendarApiFactory.instance = new CalendarApiFactoryImpl(false, false, false, false);
        obj = new RealVisualElementAttacher();
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_736;
        }
        throw new NullPointerException();
        VisualElementHolder.instance = (VisualElementAttacher)obj;
        AccountsUtil.initialize(this);
        DateTimeService.initialize(this);
        DateTimeFormatHelper.initialize(this);
        WidgetDateUtils.initialize(this);
        ai = ExperimentConfiguration.getActiveExperimentsIds(this);
        flag2 = ExperimentConfiguration.PRIMES_MEMORY_INSTRUMENTATION.isActive(this);
        obj2 = Features.instance;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_794;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj2).extended_memory_monitoring();
        flag3 = ExperimentConfiguration.PRIMES_UI_LATENCY_INSTRUMENTATION.isActive(this);
        flag4 = ExperimentConfiguration.PRIMES_CRASH_INSTRUMENTATION.isActive(this);
        flag5 = ExperimentConfiguration.PRIMES_PACKAGESTATS_INSTRUMENTATION.isActive(this);
        obj2 = PerformanceMetricCollectorHolder.instance;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_852;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        ((PerformanceMetricCollector)obj2).start(this, ai, flag2, false, flag3, flag4, flag5);
        if (Clock.mockedTimestamp <= 0L)
        {
            break MISSING_BLOCK_LABEL_917;
        }
        l = Clock.mockedTimestamp;
_L1:
        obj2 = new Random(l);
        ai = AnalyticsLoggerHolder.instance;
        if (ai != null)
        {
            break MISSING_BLOCK_LABEL_925;
        }
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        l = System.currentTimeMillis();
          goto _L1
        analyticslogger = (AnalyticsLogger)ai;
        s = ExperimentConfiguration.getActiveExperiments(this);
        ai = RemoteFeatureConfig.getEnabledFeatures();
        s = String.valueOf(s);
        ai = String.valueOf(ai);
        if (ai.length() == 0)
        {
            break MISSING_BLOCK_LABEL_1039;
        }
        ai = s.concat(ai);
_L2:
        analyticslogger.setCustomDimension(this, 51, ai);
        class .Lambda._cls3
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls3();

            public final void accept(Object obj5)
            {
                CalendarApplication.lambda$initializePerformanceMetrics$1$CalendarApplication((com.google.calendar.v2a.android.util.metric.MetricUtils.MemoryRecording)obj5);
            }


            private .Lambda._cls3()
            {
            }
        }

        ai = .Lambda._cls3..instance;
        MetricUtils.memoryBackends.add(ai);
        if (RemoteFeatureConfig.PRIMES_API_LOGGING.enabled())
        {
            class .Lambda._cls4
                implements Function
            {

                private final Random arg$1;

                public final Object apply(Object obj5)
                {
                    return CalendarApplication.lambda$initializePerformanceMetrics$4$CalendarApplication(arg$1, (com.google.calendar.v2a.android.util.metric.MetricUtils.Operation)obj5);
                }

            .Lambda._cls4(Random random)
            {
                arg$1 = random;
            }
            }

            ai = new .Lambda._cls4(((Random) (obj2)));
            MetricUtils.backends.add(ai);
        }
        ai = Features.instance;
        if (ai != null)
        {
            break MISSING_BLOCK_LABEL_1052;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ai = new String(s);
          goto _L2
        ((FeatureConfig)ai).analytics_api_logging();
        SyncOperation.setupLogging(ExperimentConfiguration.SYNC_OPERATION_LOGGING.isActive(this));
        LatencyLoggerHolder.set(new LatencyLoggerImpl(this));
        LatencyLoggerHolder.get().markAt(1);
        initializeOrReinitializeFlairs();
        ai = HsvSupplier.get();
        LogUtils.i(TAG, "HSV is %s", new Object[] {
            ai
        });
        if (!ai.isPresent())
        {
            break MISSING_BLOCK_LABEL_1186;
        }
        obj2 = Features.instance;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_1146;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj2).public_release();
        LogUtils.wtf(TAG, "HSV is %s", new Object[] {
            ai
        });
        ((HsvSnapshotHelper)ai.get()).registerHsv$51662RJ4E9NMIP1FC5O70BQ1E1O6OQB3C5Q6IRRE7CKLC___0();
        SettingsShims.instance = new SettingsShimsImpl();
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            CalendarProviderObserverJobService.scheduleJob(this);
        }
        CalendarApi.initialize(getApplicationContext());
        ai = Features.instance;
        if (ai != null)
        {
            break MISSING_BLOCK_LABEL_1257;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)ai).dogfood_features();
        if (PrefService.instance == null)
        {
            if (PrimaryAccountSelector.instance == null)
            {
                PrimaryAccountSelector.instance = new PrimaryAccountSelector(this);
            }
            PrefService.instance = new PrefService(PrimaryAccountSelector.instance);
        }
        ai = PrefService.instance;
        obj2 = SettingsCache.instance;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_1329;
        }
        throw new NullPointerException(String.valueOf("Not initialized"));
        ai.settingsSubscription = ((ListenableFutureCache)obj2).subscribe(new com.google.android.calendar.prefs.PrefService..Lambda._cls0(ai), CalendarExecutor.MAIN, true);
        ai = Features.instance;
        if (ai != null)
        {
            break MISSING_BLOCK_LABEL_1373;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        VolleyQueueHolder.initialize(this, ((FeatureConfig)ai).bugfood_build());
        FlairsInvalidator.invalidateIfNeeded(getApplicationContext(), i);
        CalendarProperties.initialize(this);
        class .Lambda._cls2
            implements Runnable
        {

            private final CalendarApplication arg$1;

            public final void run()
            {
                AlertServiceHelper.updateAlertNotification(arg$1);
            }

            .Lambda._cls2()
            {
                arg$1 = CalendarApplication.this;
            }
        }

        Object obj1;
        Object obj3;
        Object obj4;
        boolean flag1;
        if (DateTimeService.instance != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_1516;
        }
        obj1 = CalendarProperties.instance;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_1435;
        }
        throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        ((CalendarProperties)obj1).registerListener(0, DateTimeService.getInstance());
_L3:
        LogUtils.d(MonthViewWidgetProvider.TAG, "registerCalendarPropertyChangeListener", new Object[0]);
        if (MonthViewWidgetProvider.propertyChangeListener != null)
        {
            MonthViewWidgetProvider.unregisterPropertyChangeListener(MonthViewWidgetProvider.propertyChangeListener);
        }
        obj1 = new com.google.android.calendar.widgetmonth.MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener(this);
        MonthViewWidgetProvider.propertyChangeListener = ((com.google.android.calendar.widgetmonth.MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener) (obj1));
        MonthViewWidgetProvider.registerPropertyChangeListener(((com.google.android.calendar.widgetmonth.MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener) (obj1)));
        obj1 = propertiesManager;
        obj3 = CalendarProperties.instance;
        if (obj3 != null)
        {
            break MISSING_BLOCK_LABEL_1533;
        }
        throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        LogUtils.wtf(TAG, "DateTimeService is not initialized, not registered as a listener.", new Object[0]);
          goto _L3
        obj3 = (CalendarProperties)obj3;
        obj4 = new CalendarApplicationPropertiesManager..Lambda._cls3(((CalendarApplicationPropertiesManager) (obj1)));
        ((OnPropertyChangedListener) (obj4)).onCalendarPropertyChanged(4, ((CalendarProperties) (obj3)).getPropertyValue(4));
        ((CalendarProperties) (obj3)).registerListener(4, ((OnPropertyChangedListener) (obj4)));
        obj1 = new CalendarApplicationPropertiesManager..Lambda._cls4(((CalendarApplicationPropertiesManager) (obj1)));
        ((OnPropertyChangedListener) (obj1)).onCalendarPropertyChanged(3, ((CalendarProperties) (obj3)).getPropertyValue(3));
        ((CalendarProperties) (obj3)).registerListener(3, ((OnPropertyChangedListener) (obj1)));
        registerActivityLifecycleCallbacks(this);
        GrooveStore.initialize(this);
        obj1 = new IntentFilter("com.google.android.calendar.intent.action.GROOVE_REQUEST_UPSYNCED");
        ((IntentFilter) (obj1)).addAction("com.google.android.calendar.intent.action.GROOVE_SYNCED");
        ((IntentFilter) (obj1)).addAction("com.google.android.calendar.intent.action.TRACKING_SYNC_INITIATED");
        ((IntentFilter) (obj1)).addAction("com.google.android.calendar.intent.action.REFRESH_GROOVE_NOTIFICATIONS");
        LocalBroadcastManager.getInstance(this).registerReceiver(new HabitsIntentReceiver(), ((IntentFilter) (obj1)));
        if (RemoteFeatureConfig.GROWTH_KIT.enabled())
        {
            GrowthKitInstall.initialize((new com.google.android.libraries.internal.growth.growthkit.noinject.AutoValue_GrowthKitInstall_Params.Builder()).setContext(getApplicationContext()).setExecutorService(CalendarExecutor.NET).setApiKey("AIzaSyDP-ww8Di_cGZ0zcTHcECQY4sz20LSS-Mg").build());
        }
        if (android.os.Build.VERSION.SDK_INT >= 25)
        {
            ((ShortcutManager)getSystemService(android/content/pm/ShortcutManager)).disableShortcuts(Collections.singletonList("launcher_shortcuts_shortcut_groom"), getString(0x7f130319));
        }
        obj1 = ObsoleteDataCleaner.maybeRemoveDataForDeletedEvents(this);
        obj3 = new ObsoleteDataCleanerUtils._cls1(this);
        obj4 = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (obj3 != null)
        {
            break MISSING_BLOCK_LABEL_1761;
        }
        throw new NullPointerException();
        ((ListenableFuture) (obj1)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj1)), ((com.google.common.util.concurrent.FutureCallback) (obj3))), ((java.util.concurrent.Executor) (obj4)));
        NotificationsInitializer.initialize(this, new EventNotificationPresenterImpl(this), new HabitNotificationPresenterImpl(this), new .Lambda._cls2());
        Trace.endSection();
        return;
    }

    static 
    {
        Features.instance = new VariantFeatureConfig_release();
        Object obj = new PerformanceMetricCollectorImpl();
        if (PerformanceMetricCollectorHolder.instance == null)
        {
            PerformanceMetricCollectorHolder.instance = ((PerformanceMetricCollector) (obj));
        }
        obj = PrimesStartupMeasure.instance;
        if (((PrimesStartupMeasure) (obj)).appClassLoadedAt == 0L)
        {
            obj.appClassLoadedAt = SystemClock.elapsedRealtime();
        }
        obj = com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements.ACTIVITY_INIT;
        PrimesStartupMeasure primesstartupmeasure = PrimesStartupMeasure.instance;
        primesstartupmeasure.getClass();
        class .Lambda._cls11
            implements Runnable
        {

            private final PrimesStartupMeasure arg$1;

            public final void run()
            {
                PrimesStartupMeasure primesstartupmeasure1 = arg$1;
                if (ThreadUtil.sMainThread == null)
                {
                    ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
                }
                boolean flag;
                if (Thread.currentThread() == ThreadUtil.sMainThread)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && primesstartupmeasure1.appClassLoadedAt > 0L && primesstartupmeasure1.firstOnActivityInitAt == 0L && primesstartupmeasure1.firstOnActivityCreatedAt == 0L)
                {
                    primesstartupmeasure1.firstOnActivityInitAt = SystemClock.elapsedRealtime();
                    primesstartupmeasure1.runOnActivityInitListeners();
                }
            }

            .Lambda._cls11(PrimesStartupMeasure primesstartupmeasure)
            {
                arg$1 = primesstartupmeasure;
            }
        }

        obj.action = new .Lambda._cls11(primesstartupmeasure);
        obj = com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements.APP_INTERACTIVE;
        primesstartupmeasure = PrimesStartupMeasure.instance;
        primesstartupmeasure.getClass();
        class .Lambda._cls12
            implements Runnable
        {

            private final PrimesStartupMeasure arg$1;

            public final void run()
            {
                PrimesStartupMeasure primesstartupmeasure1 = arg$1;
                if (ThreadUtil.sMainThread == null)
                {
                    ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
                }
                boolean flag;
                if (Thread.currentThread() == ThreadUtil.sMainThread)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && primesstartupmeasure1.firstAppInteractiveAt == 0L)
                {
                    primesstartupmeasure1.firstAppInteractiveAt = SystemClock.elapsedRealtime();
                }
            }

            .Lambda._cls12(PrimesStartupMeasure primesstartupmeasure)
            {
                arg$1 = primesstartupmeasure;
            }
        }

        obj.action = new .Lambda._cls12(primesstartupmeasure);
    }

    private class _cls1
        implements Runnable
    {

        private final CalendarApplication this$0;

        public final void run()
        {
            if (runningActivityCount.decrementAndGet() == 0)
            {
                Object obj = CalendarApplication.this;
                if (PrefService.instance == null)
                {
                    if (PrimaryAccountSelector.instance == null)
                    {
                        PrimaryAccountSelector.instance = new PrimaryAccountSelector(((Context) (obj)));
                    }
                    PrefService.instance = new PrefService(PrimaryAccountSelector.instance);
                }
                obj = PrefService.instance;
                if (((PrefService) (obj)).settingsSubscription != null)
                {
                    ((PrefService) (obj)).settingsSubscription.cancel(false);
                    obj.settingsSubscription = null;
                }
                obj = VisualElementHolder.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                }
                ((VisualElementAttacher)obj).recordAppVisibilityEvent(CalendarApplication.this, false);
                obj = CalendarListEntryCache.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Not initialized"));
                }
                ((ListenableFutureCache)obj).stop();
                obj = SettingsCache.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Not initialized"));
                }
                ((ListenableFutureCache)obj).stop();
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).uss();
                LogUtils.v(PermissionsUtil.TAG, "Clearing cached permissions", new Object[0]);
                PermissionsUtil.grantedPermissions.clear();
            }
        }

        _cls1()
        {
            this$0 = CalendarApplication.this;
            super();
        }
    }

}
