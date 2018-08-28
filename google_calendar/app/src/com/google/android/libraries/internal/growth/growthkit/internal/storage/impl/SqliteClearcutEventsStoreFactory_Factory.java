// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteClearcutEventsStoreFactory

public final class SqliteClearcutEventsStoreFactory_Factory
    implements Factory
{

    private final Provider dbHelperProvider;
    private final Provider executorProvider;

    public SqliteClearcutEventsStoreFactory_Factory(Provider provider, Provider provider1)
    {
        executorProvider = provider;
        dbHelperProvider = provider1;
    }

    public final Object get()
    {
        return new SqliteClearcutEventsStoreFactory(executorProvider, dbHelperProvider);
    }
}
