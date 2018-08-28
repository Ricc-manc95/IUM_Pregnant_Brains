// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.streamz;

import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.MetricFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.streamz:
//            StreamzIncrements

public final class StreamzCommonModule_ProvideStreamzIncrementsFactory
    implements Factory
{

    private final Provider incrementsToFlushProvider;
    private final Provider metricFactoryProvider;
    private final Provider streamzLoggerProvider;

    public StreamzCommonModule_ProvideStreamzIncrementsFactory(Provider provider, Provider provider1, Provider provider2)
    {
        incrementsToFlushProvider = provider;
        streamzLoggerProvider = provider1;
        metricFactoryProvider = provider2;
    }

    public final Object get()
    {
        Object obj = incrementsToFlushProvider;
        Provider provider = streamzLoggerProvider;
        Provider provider1 = metricFactoryProvider;
        obj = new StreamzIncrements(((Provider) (obj)), (GcoreClearcutStreamzLogger)provider.get(), (MetricFactory)provider1.get());
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (StreamzIncrements)obj;
        }
    }
}
