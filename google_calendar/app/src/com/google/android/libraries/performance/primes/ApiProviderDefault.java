// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ApiProviderFactory, PrimesApiImpl, AppLifecycleMonitor, PrimesConfigurationsProvider, 
//            Supplier, PrimesThreadsConfigurations, ApiProvider

final class ApiProviderDefault
    implements ApiProviderFactory
{

    ApiProviderDefault()
    {
    }

    public final ApiProvider create(final Application application, final PrimesConfigurationsProvider configurationsProvider, final Supplier flagsSupplier, final Supplier sharedPreferencesSupplier, final PrimesThreadsConfigurations threadsConfigurations, final Supplier shutdownSupplier)
    {
        PrimesApiImpl.isPrimesSupported();
        return new _cls2();
    }

    private class _cls2
        implements ApiProvider
    {

        private final AppLifecycleMonitor val$appLifecycleMonitor;
        public final Application val$application;
        private final PrimesConfigurationsProvider val$configurationsProvider;
        private final Supplier val$flagsSupplier;
        private final Supplier val$sharedPreferencesSupplier;
        private final Supplier val$shutdownSupplier;
        private final PrimesThreadsConfigurations val$threadsConfigurations;

        public final PrimesApi getPrimesApi()
        {
            Object obj = application;
            Object obj1 = threadsConfigurations;
            obj1 = new PrimesApiImpl(((Application) (obj)), new PrimesExecutors._cls1(((PrimesThreadsConfigurations) (obj1)).primesExecutorService, ((PrimesThreadsConfigurations) (obj1)).primesMetricExecutorPriority, ((PrimesThreadsConfigurations) (obj1)).primesMetricExecutorPoolSize));
            obj = threadsConfigurations;
            class _cls1
                implements Supplier
            {

                private final _cls2 this$1;

                public final Object get()
                {
                    return Boolean.valueOf(ProcessStats.isMyProcessInForeground(application));
                }

                _cls1()
                {
                    this$1 = _cls2.this;
                    super();
                }
            }

            final PrimesApiImpl._cls1 initTask;
            final PrimesThreadsConfigurations.InitAfterResumedFlag initAfterResumedFlag;
            final PrimesThreadsConfigurations.ActivityResumedCallback activityResumedCallback;
            final AppLifecycleMonitor lifecycleMonitor;
            final _cls1 foregroundOracle;
            boolean flag;
            if (((PrimesThreadsConfigurations) (obj)).primesExecutorService != null)
            {
                obj = ((PrimesThreadsConfigurations) (obj)).primesExecutorService;
            } else
            {
                obj = Executors.newSingleThreadExecutor(new PrimesExecutors.PrimesThreadFactory("Primes-init", ((PrimesThreadsConfigurations) (obj)).primesInitializationPriority));
            }
            initTask = configurationsProvider;
            initAfterResumedFlag = flagsSupplier;
            activityResumedCallback = sharedPreferencesSupplier;
            lifecycleMonitor = shutdownSupplier;
            if (threadsConfigurations.primesExecutorService == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            initTask = new PrimesApiImpl._cls1(((PrimesApiImpl) (obj1)), ((ExecutorService) (obj)), initTask, initAfterResumedFlag, activityResumedCallback, lifecycleMonitor, new PrimesApiImpl.FirstActivityCreateListener(AppLifecycleMonitor.getInstance(((PrimesApiImpl) (obj1)).application)), new PrimesApiImpl.FirstAppToBackgroundListener(AppLifecycleMonitor.getInstance(((PrimesApiImpl) (obj1)).application), ((PrimesApiImpl) (obj1)).executorServiceSupplier), flag);
            initAfterResumedFlag = threadsConfigurations.initAfterResumed;
            activityResumedCallback = threadsConfigurations.activityResumedCallback;
            lifecycleMonitor = appLifecycleMonitor;
            foregroundOracle = new _cls1();
            if (initAfterResumedFlag != null)
            {
                ((ExecutorService) (obj)).submit(new _cls3());
                return ((PrimesApi) (obj1));
            } else
            {
                initTask.run();
                return ((PrimesApi) (obj1));
            }
        }

        _cls2()
        {
            application = application1;
            threadsConfigurations = primesthreadsconfigurations;
            configurationsProvider = primesconfigurationsprovider;
            flagsSupplier = supplier;
            sharedPreferencesSupplier = supplier1;
            shutdownSupplier = supplier2;
            appLifecycleMonitor = applifecyclemonitor;
            super();
        }

        private class _cls3
            implements Runnable
        {

            private final PrimesThreadsConfigurations.ActivityResumedCallback val$activityResumedCallback;
            private final Supplier val$foregroundOracle;
            private final PrimesThreadsConfigurations.InitAfterResumedFlag val$initAfterResumedFlag;
            private final Runnable val$initTask;
            private final AppLifecycleMonitor val$lifecycleMonitor;

            public final void run()
            {
                Object obj = lifecycleMonitor;
                Runnable runnable = initTask;
                PrimesThreadsConfigurations.InitAfterResumedFlag initafterresumedflag = initAfterResumedFlag;
                PrimesThreadsConfigurations.ActivityResumedCallback activityresumedcallback = activityResumedCallback;
                Supplier supplier = foregroundOracle;
                if (initafterresumedflag != null && initafterresumedflag.isEnabled() && ((Boolean)supplier.get()).booleanValue())
                {
                    PrimesExecutors.OnResumeListener onresumelistener = new PrimesExecutors.OnResumeListener(((AppLifecycleMonitor) (obj)), activityresumedcallback);
                    obj = ((AppLifecycleMonitor) (obj)).tracker;
                    if (onresumelistener == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        ((AppLifecycleTracker) (obj)).callbacks.lifecycleListeners.add(onresumelistener);
                        onresumelistener.execute(runnable);
                        return;
                    }
                } else
                {
                    runnable.run();
                    return;
                }
            }

            _cls3()
            {
                lifecycleMonitor = applifecyclemonitor;
                initTask = runnable;
                initAfterResumedFlag = initafterresumedflag;
                activityResumedCallback = activityresumedcallback;
                foregroundOracle = supplier;
                super();
            }
        }

    }

}
