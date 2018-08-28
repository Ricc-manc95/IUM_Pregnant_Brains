// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProvidePhenotypeSharedPreferencesFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider phenotypeSharedPreferencesNameProvider;

    public DaggerExperimentsModule_ProvidePhenotypeSharedPreferencesFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        phenotypeSharedPreferencesNameProvider = provider1;
    }

    public final Object get()
    {
        Object obj = contextProvider;
        Provider provider = phenotypeSharedPreferencesNameProvider;
        obj = DaggerExperimentsModule.providePhenotypeSharedPreferences((Context)((Provider) (obj)).get(), (String)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (SharedPreferences)obj;
        }
    }
}
