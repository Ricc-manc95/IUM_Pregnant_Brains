// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.jobs.impl;

import android.content.Context;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.GooglePlayDriver;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class JobsModule_ProviderGooglePlayDriverFactory
    implements Factory
{

    private final Provider contextProvider;

    public JobsModule_ProviderGooglePlayDriverFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        GooglePlayDriver googleplaydriver = new GooglePlayDriver((Context)contextProvider.get());
        if (googleplaydriver == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Driver)googleplaydriver;
        }
    }
}
