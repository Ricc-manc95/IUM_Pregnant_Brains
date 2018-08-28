// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl:
//            GrowthKitJobSchedulerImpl

public final class GrowthKitJobSchedulerImpl_Factory
    implements Factory
{

    private final Provider firebaseJobDispatcherProvider;
    private final Provider jobsProvider;

    public GrowthKitJobSchedulerImpl_Factory(Provider provider, Provider provider1)
    {
        firebaseJobDispatcherProvider = provider;
        jobsProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = firebaseJobDispatcherProvider;
        Provider provider1 = jobsProvider;
        return new GrowthKitJobSchedulerImpl((FirebaseJobDispatcher)provider.get(), DoubleCheck.lazy(provider1));
    }
}
