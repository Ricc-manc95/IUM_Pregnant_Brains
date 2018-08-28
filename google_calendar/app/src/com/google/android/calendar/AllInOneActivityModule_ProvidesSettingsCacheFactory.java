// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import dagger.internal.Factory;

public final class AllInOneActivityModule_ProvidesSettingsCacheFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesSettingsCacheFactory INSTANCE = new AllInOneActivityModule_ProvidesSettingsCacheFactory();

    public AllInOneActivityModule_ProvidesSettingsCacheFactory()
    {
    }

    public final Object get()
    {
        ListenableFutureCache listenablefuturecache = SettingsCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        listenablefuturecache = (ListenableFutureCache)listenablefuturecache;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListenableFutureCache)listenablefuturecache;
        }
    }

}
