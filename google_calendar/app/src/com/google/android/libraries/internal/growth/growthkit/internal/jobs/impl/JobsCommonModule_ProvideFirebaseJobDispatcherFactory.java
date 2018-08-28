// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class JobsCommonModule_ProvideFirebaseJobDispatcherFactory
    implements Factory
{

    private final Provider driverProvider;

    public JobsCommonModule_ProvideFirebaseJobDispatcherFactory(Provider provider)
    {
        driverProvider = provider;
    }

    public final Object get()
    {
        FirebaseJobDispatcher firebasejobdispatcher = new FirebaseJobDispatcher((Driver)driverProvider.get());
        if (firebasejobdispatcher == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (FirebaseJobDispatcher)firebasejobdispatcher;
        }
    }
}
