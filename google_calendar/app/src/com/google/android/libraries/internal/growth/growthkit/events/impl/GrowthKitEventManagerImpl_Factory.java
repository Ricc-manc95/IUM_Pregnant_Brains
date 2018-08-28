// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.events.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter3;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.events.impl:
//            GrowthKitEventManagerImpl

public final class GrowthKitEventManagerImpl_Factory
    implements Factory
{

    private final Provider appPackageNameProvider;
    private final Provider clearcutEventCounterProvider;
    private final Provider enableFlagProvider;
    private final Provider executorProvider;
    private final Provider loggingCounterProvider;
    private final Provider monitoredEventClearcutStoreProvider;
    private final Provider monitoredEventVisualElementStoreProvider;
    private final Provider promotionsManagerLazyProvider;
    private final Provider saveOnlyMonitoredEventsProvider;
    private final Provider streamzIncrementsProvider;
    private final Provider traceProvider;
    private final Provider visualElementEventCounterProvider;

    public GrowthKitEventManagerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, 
            Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11)
    {
        clearcutEventCounterProvider = provider;
        promotionsManagerLazyProvider = provider1;
        appPackageNameProvider = provider2;
        enableFlagProvider = provider3;
        saveOnlyMonitoredEventsProvider = provider4;
        monitoredEventClearcutStoreProvider = provider5;
        monitoredEventVisualElementStoreProvider = provider6;
        visualElementEventCounterProvider = provider7;
        executorProvider = provider8;
        loggingCounterProvider = provider9;
        streamzIncrementsProvider = provider10;
        traceProvider = provider11;
    }

    public final Object get()
    {
        Provider provider = clearcutEventCounterProvider;
        Provider provider1 = promotionsManagerLazyProvider;
        Provider provider2 = appPackageNameProvider;
        Provider provider3 = enableFlagProvider;
        Provider provider4 = saveOnlyMonitoredEventsProvider;
        Provider provider5 = monitoredEventClearcutStoreProvider;
        Provider provider6 = monitoredEventVisualElementStoreProvider;
        Provider provider7 = visualElementEventCounterProvider;
        Provider provider8 = executorProvider;
        Provider provider9 = loggingCounterProvider;
        Provider provider10 = streamzIncrementsProvider;
        Provider provider11 = traceProvider;
        return new GrowthKitEventManagerImpl((PerAccountProvider)provider.get(), DoubleCheck.lazy(provider1), (String)provider2.get(), provider3, provider4, (PerAccountProvider)provider5.get(), (PerAccountProvider)provider6.get(), (PerAccountProvider)provider7.get(), (ListeningExecutorService)provider8.get(), (Counter3)provider9.get(), (StreamzIncrements)provider10.get(), (Trace)provider11.get());
    }
}
