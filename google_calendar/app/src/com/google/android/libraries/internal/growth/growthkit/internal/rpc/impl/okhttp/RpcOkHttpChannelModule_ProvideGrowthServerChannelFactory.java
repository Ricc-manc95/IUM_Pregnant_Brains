// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl.okhttp;

import dagger.internal.Factory;
import io.grpc.ManagedChannelBuilder;
import io.grpc.okhttp.OkHttpChannelBuilder;
import javax.inject.Provider;

public final class RpcOkHttpChannelModule_ProvideGrowthServerChannelFactory
    implements Factory
{

    private final Provider syncUrlProvider;

    public RpcOkHttpChannelModule_ProvideGrowthServerChannelFactory(Provider provider)
    {
        syncUrlProvider = provider;
    }

    public final Object get()
    {
        OkHttpChannelBuilder okhttpchannelbuilder = new OkHttpChannelBuilder((String)syncUrlProvider.get());
        if (okhttpchannelbuilder == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ManagedChannelBuilder)okhttpchannelbuilder;
        }
    }
}
