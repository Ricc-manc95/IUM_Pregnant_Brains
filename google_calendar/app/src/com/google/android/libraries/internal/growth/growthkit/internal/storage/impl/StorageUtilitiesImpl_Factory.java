// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            StorageUtilitiesImpl

public final class StorageUtilitiesImpl_Factory
    implements Factory
{

    private final Provider accountManagerProvider;
    private final Provider ageProvider;
    private final Provider clearcutEventStoreProvider;
    private final Provider clockProvider;
    private final Provider veEventStoreProvider;

    public StorageUtilitiesImpl_Factory(Provider provider, Provider provider1, Provider provider2, Provider provider3, Provider provider4)
    {
        clearcutEventStoreProvider = provider;
        veEventStoreProvider = provider1;
        accountManagerProvider = provider2;
        clockProvider = provider3;
        ageProvider = provider4;
    }

    public final Object get()
    {
        Provider provider = clearcutEventStoreProvider;
        Provider provider1 = veEventStoreProvider;
        Provider provider2 = accountManagerProvider;
        Provider provider3 = clockProvider;
        Provider provider4 = ageProvider;
        return new StorageUtilitiesImpl((PerAccountProvider)provider.get(), (PerAccountProvider)provider1.get(), (AccountManager)provider2.get(), (Clock)provider3.get(), ((Long)provider4.get()).longValue());
    }
}
