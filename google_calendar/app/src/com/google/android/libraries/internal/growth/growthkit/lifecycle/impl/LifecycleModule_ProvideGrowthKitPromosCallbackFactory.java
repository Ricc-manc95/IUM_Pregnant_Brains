// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks;
import dagger.internal.Factory;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl:
//            GrowthKitCallbacksManagerImpl

public final class LifecycleModule_ProvideGrowthKitPromosCallbackFactory
    implements Factory
{

    private final Provider implProvider;

    public LifecycleModule_ProvideGrowthKitPromosCallbackFactory(Provider provider)
    {
        implProvider = provider;
    }

    public final Object get()
    {
        return (GrowthKitCallbacks)((GrowthKitCallbacksManagerImpl)implProvider.get()).callback.get();
    }
}
