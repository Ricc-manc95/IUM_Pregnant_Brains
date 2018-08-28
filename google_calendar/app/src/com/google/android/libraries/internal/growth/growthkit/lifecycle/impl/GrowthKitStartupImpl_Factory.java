// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.Counter2;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl:
//            GrowthKitStartupImpl

public final class GrowthKitStartupImpl_Factory
    implements Factory
{

    private final Provider applicationContextProvider;
    private final Provider enableFlagProvider;
    private final Provider executorProvider;
    private final Provider growthkitStartedCounterProvider;
    private final Provider listenersProvider;
    private final Provider packageNameProvider;
    private final Provider streamzIncrementsProvider;

    public GrowthKitStartupImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6)
    {
        applicationContextProvider = provider;
        executorProvider = provider1;
        listenersProvider = provider2;
        enableFlagProvider = provider3;
        growthkitStartedCounterProvider = provider4;
        streamzIncrementsProvider = provider5;
        packageNameProvider = provider6;
    }

    public final Object get()
    {
        Provider provider = applicationContextProvider;
        Provider provider1 = executorProvider;
        Provider provider2 = listenersProvider;
        Provider provider3 = enableFlagProvider;
        Provider provider4 = growthkitStartedCounterProvider;
        Provider provider5 = streamzIncrementsProvider;
        Provider provider6 = packageNameProvider;
        return new GrowthKitStartupImpl((Context)provider.get(), (ListeningExecutorService)provider1.get(), provider2, provider3, (Counter2)provider4.get(), (StreamzIncrements)provider5.get(), (String)provider6.get());
    }
}
