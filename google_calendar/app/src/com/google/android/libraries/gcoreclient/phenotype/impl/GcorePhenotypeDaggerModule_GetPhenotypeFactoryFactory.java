// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import dagger.internal.Factory;

public final class GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory
    implements Factory
{

    public static final GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory INSTANCE = new GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory();

    public GcorePhenotypeDaggerModule_GetPhenotypeFactoryFactory()
    {
    }

    public static com.google.android.libraries.gcoreclient.phenotype.Phenotype.Factory proxyGetPhenotypeFactory()
    {
        PhenotypeImpl.Factory factory = new PhenotypeImpl.Factory();
        if (factory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.gcoreclient.phenotype.Phenotype.Factory)factory;
        }
    }

    public final Object get()
    {
        PhenotypeImpl.Factory factory = new PhenotypeImpl.Factory();
        if (factory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (com.google.android.libraries.gcoreclient.phenotype.Phenotype.Factory)factory;
        }
    }

}
