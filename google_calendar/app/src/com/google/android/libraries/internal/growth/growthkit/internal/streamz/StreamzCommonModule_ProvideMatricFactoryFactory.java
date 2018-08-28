// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.streamz;

import com.google.android.libraries.streamz.MetricFactory;
import dagger.internal.Factory;

public final class StreamzCommonModule_ProvideMatricFactoryFactory
    implements Factory
{

    public static final StreamzCommonModule_ProvideMatricFactoryFactory INSTANCE = new StreamzCommonModule_ProvideMatricFactoryFactory();

    public StreamzCommonModule_ProvideMatricFactoryFactory()
    {
    }

    public final Object get()
    {
        MetricFactory metricfactory = MetricFactory.defaultFactory;
        if (metricfactory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (MetricFactory)metricfactory;
        }
    }

}
