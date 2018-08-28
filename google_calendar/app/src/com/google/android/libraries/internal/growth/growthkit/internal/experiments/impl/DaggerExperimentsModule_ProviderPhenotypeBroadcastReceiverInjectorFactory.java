// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.internal.growth.growthkit.inject.BroadcastReceiverInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProviderPhenotypeBroadcastReceiverInjectorFactory
    implements Factory
{

    private final Provider builderProvider;

    public DaggerExperimentsModule_ProviderPhenotypeBroadcastReceiverInjectorFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        BroadcastReceiverInjector broadcastreceiverinjector = DaggerExperimentsModule.providerPhenotypeBroadcastReceiverInjector((PhenotypeBroadcastReceiver.PhenotypeBroadcastReceiverSubcomponent.Builder)builderProvider.get());
        if (broadcastreceiverinjector == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (BroadcastReceiverInjector)broadcastreceiverinjector;
        }
    }
}
