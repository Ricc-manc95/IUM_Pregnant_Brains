// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.StorageUtilities;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            StorageCleanupJob

public final class StorageCleanupJob_Factory
    implements Factory
{

    private final Provider firebaseJobDispatcherProvider;
    private final Provider periodProvider;
    private final Provider storageUtilitiesProvider;

    public StorageCleanupJob_Factory(Provider provider, Provider provider1, Provider provider2)
    {
        storageUtilitiesProvider = provider;
        periodProvider = provider1;
        firebaseJobDispatcherProvider = provider2;
    }

    public final Object get()
    {
        Provider provider = storageUtilitiesProvider;
        Provider provider1 = periodProvider;
        Provider provider2 = firebaseJobDispatcherProvider;
        return new StorageCleanupJob((StorageUtilities)provider.get(), provider1, (FirebaseJobDispatcher)provider2.get());
    }
}
