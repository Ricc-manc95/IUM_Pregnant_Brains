// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.task.alternate.CachedTaskDataLoader;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesTaskCacheFactory
    implements Factory
{

    private final Provider loaderProvider;

    public AllInOneActivityModule_ProvidesTaskCacheFactory(Provider provider)
    {
        loaderProvider = provider;
    }

    public final Object get()
    {
        ListenableFutureCache listenablefuturecache = ((CachedTaskDataLoader)loaderProvider.get()).cache;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListenableFutureCache)listenablefuturecache;
        }
    }
}
