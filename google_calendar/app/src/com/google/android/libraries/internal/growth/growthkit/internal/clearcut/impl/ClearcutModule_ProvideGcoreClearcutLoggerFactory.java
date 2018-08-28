// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ClearcutModule_ProvideGcoreClearcutLoggerFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider factoryProvider;

    public ClearcutModule_ProvideGcoreClearcutLoggerFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        factoryProvider = provider1;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        obj = new PerAccountProvider(new ClearcutModule..Lambda._cls0(factoryProvider, (Context)((Provider) (obj)).get()));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (PerAccountProvider)obj;
        }
    }
}
