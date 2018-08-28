// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.internal.Factory;
import java.util.concurrent.Executors;

public final class StorageModule_ProvideInternalDbExecutorFactory
    implements Factory
{

    public static final StorageModule_ProvideInternalDbExecutorFactory INSTANCE = new StorageModule_ProvideInternalDbExecutorFactory();

    public StorageModule_ProvideInternalDbExecutorFactory()
    {
    }

    public final Object get()
    {
        ListeningExecutorService listeningexecutorservice = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));
        if (listeningexecutorservice == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListeningExecutorService)listeningexecutorservice;
        }
    }

}
