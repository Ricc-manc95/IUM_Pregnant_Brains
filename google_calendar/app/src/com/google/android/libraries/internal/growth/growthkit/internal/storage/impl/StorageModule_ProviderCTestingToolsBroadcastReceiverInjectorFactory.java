// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.inject.BroadcastReceiverInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StorageModule_ProviderCTestingToolsBroadcastReceiverInjectorFactory
    implements Factory
{

    private final Provider builderProvider;

    public StorageModule_ProviderCTestingToolsBroadcastReceiverInjectorFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        TestingToolsBroadcastReceiver.TestingToolsBroadcastReceiverSubcomponent testingtoolsbroadcastreceiversubcomponent = ((TestingToolsBroadcastReceiver.TestingToolsBroadcastReceiverSubcomponent.Builder)builderProvider.get()).build();
        if (testingtoolsbroadcastreceiversubcomponent == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (BroadcastReceiverInjector)testingtoolsbroadcastreceiversubcomponent;
        }
    }
}
