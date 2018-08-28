// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.experiments.PhenotypeManager;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            ExperimentsStartupListener

public final class ExperimentsStartupListener_Factory
    implements Factory
{

    private final Provider phenotypeManagerProvider;

    public ExperimentsStartupListener_Factory(Provider provider)
    {
        phenotypeManagerProvider = provider;
    }

    public final Object get()
    {
        return new ExperimentsStartupListener((PhenotypeManager)phenotypeManagerProvider.get());
    }
}
