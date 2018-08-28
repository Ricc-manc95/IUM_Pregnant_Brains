// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import dagger.internal.Factory;

public final class BasicImageCacheModule_ProvideURLFactoryFactory
    implements Factory
{

    public static final BasicImageCacheModule_ProvideURLFactoryFactory INSTANCE = new BasicImageCacheModule_ProvideURLFactoryFactory();

    public BasicImageCacheModule_ProvideURLFactoryFactory()
    {
    }

    public final Object get()
    {
        URL.Factory factory = BasicImageCacheModule..Lambda._cls0.$instance;
        if (factory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (URL.Factory)factory;
        }
    }

}
