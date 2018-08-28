// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore, GrowthDbHelper

public final class StorageCommonModule_ProvideCappedPromotionStoreProviderFactory
    implements Factory
{

    private final Provider executorProvider;
    private final Provider openDbHelperProvider;

    public StorageCommonModule_ProvideCappedPromotionStoreProviderFactory(Provider provider, Provider provider1)
    {
        executorProvider = provider;
        openDbHelperProvider = provider1;
    }

    public final Object get()
    {
        Object obj = executorProvider;
        Provider provider = openDbHelperProvider;
        obj = new SqliteMessageStore((ListeningExecutorService)((Provider) (obj)).get(), (GrowthDbHelper)provider.get(), "capped_promos", StorageCommonModule..Lambda._cls2.$instance);
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (MessageStore)obj;
        }
    }
}
