// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            DaggerExperimentsModule

public final class DaggerExperimentsModule_ProvideMendelPackageFactory
    implements Factory
{

    public static final DaggerExperimentsModule_ProvideMendelPackageFactory INSTANCE = new DaggerExperimentsModule_ProvideMendelPackageFactory();

    public DaggerExperimentsModule_ProvideMendelPackageFactory()
    {
    }

    public final Object get()
    {
        String s = DaggerExperimentsModule.provideMendelPackage();
        if (s == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (String)s;
        }
    }

}
