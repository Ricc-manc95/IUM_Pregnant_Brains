// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProvideMendelPerAppPackageFactory
    implements Factory
{

    private final Provider appPackageProvider;
    private final Provider mendelPackageProvider;

    public DaggerExperimentsModule_ProvideMendelPerAppPackageFactory(Provider provider, Provider provider1)
    {
        mendelPackageProvider = provider;
        appPackageProvider = provider1;
    }

    public final Object get()
    {
        Object obj = mendelPackageProvider;
        Provider provider = appPackageProvider;
        obj = DaggerExperimentsModule.provideMendelPerAppPackage((String)((Provider) (obj)).get(), (String)provider.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (String)obj;
        }
    }
}
