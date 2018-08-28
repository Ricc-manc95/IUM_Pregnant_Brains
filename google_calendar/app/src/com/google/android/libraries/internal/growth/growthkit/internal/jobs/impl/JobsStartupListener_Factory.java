// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.jobs.GrowthKitJobScheduler;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl:
//            JobsStartupListener

public final class JobsStartupListener_Factory
    implements Factory
{

    private final Provider growthKitJobSchedulerProvider;

    public JobsStartupListener_Factory(Provider provider)
    {
        growthKitJobSchedulerProvider = provider;
    }

    public final Object get()
    {
        return new JobsStartupListener((GrowthKitJobScheduler)growthKitJobSchedulerProvider.get());
    }
}
