// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

public final class GrowthKitInternalCommonModule_ProvideGrowthKitSharedPrefsFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider executorProvider;

    public GrowthKitInternalCommonModule_ProvideGrowthKitSharedPrefsFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        executorProvider = provider1;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        Provider provider = executorProvider;
        obj = GrowthKitInternalCommonModule.provideGrowthKitSharedPrefs((Context)((Provider) (obj)).get(), (ListeningExecutorService)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListenableFuture)obj;
        }
    }
}
