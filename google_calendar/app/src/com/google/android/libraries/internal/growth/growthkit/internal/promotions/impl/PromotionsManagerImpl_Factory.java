// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Trace;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl:
//            PromotionsManagerImpl, TriggeringEventProcessor

public final class PromotionsManagerImpl_Factory
    implements Factory
{

    private final Provider clockProvider;
    private final Provider eventProcessorProvider;
    private final Provider executorProvider;
    private final Provider traceProvider;

    public PromotionsManagerImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        executorProvider = provider;
        eventProcessorProvider = provider1;
        clockProvider = provider2;
        traceProvider = provider3;
    }

    public final Object get()
    {
        Provider provider = executorProvider;
        Provider provider1 = eventProcessorProvider;
        Provider provider2 = clockProvider;
        Provider provider3 = traceProvider;
        return new PromotionsManagerImpl((ListeningExecutorService)provider.get(), (TriggeringEventProcessor)provider1.get(), (Clock)provider2.get(), (Trace)provider3.get());
    }
}
