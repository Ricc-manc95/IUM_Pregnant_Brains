// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import dagger.internal.Factory;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            RpcModule

public final class RpcModule_ProvideGrowthServerChannelFactory
    implements Factory
{

    private final Provider channelBuilderProvider;
    private final RpcModule module;

    public RpcModule_ProvideGrowthServerChannelFactory(RpcModule rpcmodule, Provider provider)
    {
        module = rpcmodule;
        channelBuilderProvider = provider;
    }

    public final Object get()
    {
        Object obj = module;
        obj = ((ManagedChannelBuilder)channelBuilderProvider.get()).build();
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ManagedChannel)obj;
        }
    }
}
