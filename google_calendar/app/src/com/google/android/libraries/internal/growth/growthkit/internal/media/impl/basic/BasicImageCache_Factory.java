// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import com.google.android.libraries.social.filecache.FileCache;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic:
//            BasicImageCache

public final class BasicImageCache_Factory
    implements Factory
{

    private final Provider executorProvider;
    private final Provider fileCacheProvider;
    private final Provider urlFactoryProvider;

    public BasicImageCache_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        fileCacheProvider = provider;
        urlFactoryProvider = provider1;
        executorProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = fileCacheProvider;
        Provider provider1 = urlFactoryProvider;
        Provider provider2 = executorProvider;
        return new BasicImageCache((FileCache)provider.get(), (URL.Factory)provider1.get(), (ListeningExecutorService)provider2.get());
    }
}
