// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PseudonymousModule_ProvidePhenotypeFactory
    implements Factory
{

    private final Provider builderProvider;

    public PseudonymousModule_ProvidePhenotypeFactory(Provider provider)
    {
        builderProvider = provider;
    }

    public final Object get()
    {
        GcorePseudonymousId gcorepseudonymousid = ((com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId.Builder)builderProvider.get()).build();
        if (gcorepseudonymousid == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (GcorePseudonymousId)gcorepseudonymousid;
        }
    }
}
