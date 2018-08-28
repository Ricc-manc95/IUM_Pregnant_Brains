// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import com.google.android.libraries.internal.growth.growthkit.inject.ServiceInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory
    implements Factory
{

    private final Provider builderProvider;

    public JobsCommonModule_ProviderSGrowthKitJobServiceInejctorFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        GrowthKitJobService.GrowthKitJobServiceSubcomponent growthkitjobservicesubcomponent = ((GrowthKitJobService.GrowthKitJobServiceSubcomponent.Builder)builderProvider.get()).build();
        if (growthkitjobservicesubcomponent == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ServiceInjector)growthkitjobservicesubcomponent;
        }
    }
}
