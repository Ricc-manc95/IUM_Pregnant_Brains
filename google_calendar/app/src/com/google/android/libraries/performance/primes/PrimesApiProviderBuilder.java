// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            ApiProviderFactory, PrimesConfigurationsProvider, Supplier, PrimesThreadsConfigurations

public final class PrimesApiProviderBuilder
{

    public volatile ApiProviderFactory apiProviderFactory;
    public final Application application;
    public volatile PrimesConfigurationsProvider configurationsProvider;
    public volatile Supplier flagsSupplier;
    public volatile Supplier sharedPrefsSupplier;
    public volatile Supplier shutdownSupplier;
    public volatile PrimesThreadsConfigurations threadsConfigurations;

    PrimesApiProviderBuilder(Application application1)
    {
        application = application1;
    }
}
