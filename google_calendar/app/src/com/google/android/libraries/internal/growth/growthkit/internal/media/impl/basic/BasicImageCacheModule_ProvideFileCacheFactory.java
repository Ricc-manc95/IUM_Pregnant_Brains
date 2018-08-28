// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import android.content.Context;
import com.google.android.libraries.social.filecache.FileCache;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BasicImageCacheModule_ProvideFileCacheFactory
    implements Factory
{

    private final Provider contextProvider;

    public BasicImageCacheModule_ProvideFileCacheFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        FileCache filecache = new FileCache((Context)contextProvider.get(), "growthkit_media_cache", 0L, 0x500000L, 0.1F, 0.05F);
        if (filecache == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (FileCache)filecache;
        }
    }
}
