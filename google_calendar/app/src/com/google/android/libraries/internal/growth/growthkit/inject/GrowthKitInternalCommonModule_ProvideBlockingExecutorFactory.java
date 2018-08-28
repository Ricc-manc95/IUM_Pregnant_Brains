// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.inject:
//            GrowthKitInternalCommonModule

public final class GrowthKitInternalCommonModule_ProvideBlockingExecutorFactory
    implements Factory
{

    private final Provider backgroundExecutorProvider;
    private final Provider optionalBoundBlockingExecutorProvider;

    public GrowthKitInternalCommonModule_ProvideBlockingExecutorFactory(Provider provider, Provider provider1)
    {
        optionalBoundBlockingExecutorProvider = provider;
        backgroundExecutorProvider = provider1;
    }

    public final Object get()
    {
        Object obj = optionalBoundBlockingExecutorProvider;
        Provider provider = backgroundExecutorProvider;
        obj = GrowthKitInternalCommonModule.provideBlockingExecutor((Optional)((Provider) (obj)).get(), (ListeningExecutorService)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListeningExecutorService)obj;
        }
    }
}
