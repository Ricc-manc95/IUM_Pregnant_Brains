// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            GrowthDbHelper

public final class GrowthDbHelper_Factory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider executorProvider;

    public GrowthDbHelper_Factory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        executorProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = contextProvider;
        Provider provider1 = executorProvider;
        return new GrowthDbHelper((Context)provider.get(), (ListeningExecutorService)provider1.get());
    }
}
