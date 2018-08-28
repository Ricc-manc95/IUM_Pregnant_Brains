// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProvideAppVersionCodeFactory
    implements Factory
{

    private final Provider contextProvider;

    public DaggerExperimentsModule_ProvideAppVersionCodeFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        return Integer.valueOf(DaggerExperimentsModule.provideAppVersionCode((Context)contextProvider.get()));
    }
}
