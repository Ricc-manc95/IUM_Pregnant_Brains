// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesThreadsConfigurations, PrimesApiProviderBuilder, ApiProviderDefault, PrimesConfigurationsProvider, 
//            ApiProviderFactory, Supplier, ApiProvider

public final class PrimesApiProvider
{

    public static ApiProvider newInstance(Application application, PrimesConfigurationsProvider primesconfigurationsprovider)
    {
        Object obj1 = new PrimesThreadsConfigurations(null, 0, 0, (new PrimesThreadsConfigurations.Builder()).primesMetricExecutorPoolSize, null, null);
        if (application == null)
        {
            throw new NullPointerException();
        }
        application = (Application)application;
        Object obj = new PrimesApiProviderBuilder(application);
        obj.flagsSupplier = new com.google.android.libraries.performance.primes.flags.ServiceFlags.DefaultFlagsSupplier();
        obj.apiProviderFactory = new ApiProviderDefault();
        obj.flagsSupplier = new com.google.android.libraries.performance.primes.flags.ServiceFlags.GserviceFlagsSupplier(application);
        if (primesconfigurationsprovider == null)
        {
            throw new NullPointerException();
        }
        obj.configurationsProvider = (PrimesConfigurationsProvider)primesconfigurationsprovider;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj.threadsConfigurations = (PrimesThreadsConfigurations)obj1;
        application = ((PrimesApiProviderBuilder) (obj)).apiProviderFactory;
        if (application == null)
        {
            throw new NullPointerException();
        }
        primesconfigurationsprovider = (ApiProviderFactory)application;
        application = ((PrimesApiProviderBuilder) (obj)).application;
        if (application == null)
        {
            throw new NullPointerException();
        }
        obj1 = (Application)application;
        application = ((PrimesApiProviderBuilder) (obj)).configurationsProvider;
        if (application == null)
        {
            throw new NullPointerException();
        }
        PrimesConfigurationsProvider primesconfigurationsprovider1 = (PrimesConfigurationsProvider)application;
        application = ((PrimesApiProviderBuilder) (obj)).flagsSupplier;
        if (application == null)
        {
            throw new NullPointerException();
        }
        Supplier supplier = (Supplier)application;
        application = ((PrimesApiProviderBuilder) (obj)).sharedPrefsSupplier;
        PrimesApiProviderBuilder._cls1 _lcls1 = new PrimesApiProviderBuilder._cls1(((PrimesApiProviderBuilder) (obj)));
        if (((PrimesApiProviderBuilder) (obj)).threadsConfigurations == null)
        {
            application = new PrimesThreadsConfigurations(null, 0, 0, (new PrimesThreadsConfigurations.Builder()).primesMetricExecutorPoolSize, null, null);
        } else
        {
            application = ((PrimesApiProviderBuilder) (obj)).threadsConfigurations;
        }
        obj = ((PrimesApiProviderBuilder) (obj)).shutdownSupplier;
        return primesconfigurationsprovider.create(((Application) (obj1)), primesconfigurationsprovider1, supplier, _lcls1, application, new PrimesApiProviderBuilder._cls2());
    }

    static 
    {
        new AtomicInteger();
    }
}
