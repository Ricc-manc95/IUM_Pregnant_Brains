// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            MonitoredEventVisualElementStoreProvider, GrowthDbHelper

public final class StorageCommonModule_ProvideMonitoredEventVisualElementStoreProviderFactory
    implements Factory
{

    private final Provider executorProvider;
    private final Provider openDbHelperProvider;

    public StorageCommonModule_ProvideMonitoredEventVisualElementStoreProviderFactory(Provider provider, Provider provider1)
    {
        executorProvider = provider;
        openDbHelperProvider = provider1;
    }

    public final Object get()
    {
        Object obj = executorProvider;
        Provider provider = openDbHelperProvider;
        obj = new MonitoredEventVisualElementStoreProvider(new StorageCommonModule..Lambda._cls5((ListeningExecutorService)((Provider) (obj)).get(), (GrowthDbHelper)provider.get()));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PerAccountProvider)obj;
        }
    }
}
